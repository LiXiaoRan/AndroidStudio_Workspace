package com.inititute.imageswitch.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inititute.imageswitch.Bean.ImageSize;
import com.inititute.imageswitch.Bean.ImgBeanHolder;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 加载图片的工具类
 * Created by Lenovo on 2015-09-18.
 */
public class ImageLoader {


    private static ImageLoader mInstance;

    /**
     * 图片缓存的核心对象
     */
    private LruCache<String, Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;

    private static final int DEFULT_THREAD_COUNT = 1;

    /**
     * 队列的调度方式
     */
    private Type mType = Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;


    /**每一个Handler创建的时候都会自动与当前线程的looper绑定，然后发送消息就是给这个绑定的looper发送消息
     * UI线程默认有一个looper，其他线程需要自己创建Looper*/

    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;


    /**
     * UI线程中的handler
     */
    private Handler mUIHandler;

    /**
     * 控制后台线程Handler的信号量
     */
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
    /**
     * 控制任务队列的信号量
     */
    private Semaphore mSemaphoreThreadPool;

    public enum Type {
        FIFO, LIFO;
    }


    private ImageLoader(int ThreadCount, Type type) {
        init(ThreadCount, type);
    }

    /**
     * 初始化操作
     *
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {

        //后台轮询线程
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //通过线程池取出一个任务去执行
                        mThreadPool.execute(getTask());

                        try {
                            //如果threadCount设置为3 就是说在执行第4个的时候会阻塞，直到第一个执行完释放。
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //释放一个信号量   acquire造成的的阻塞被取消
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };
        mPoolThread.start();

        //获取应用的最大内存
        int MaxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = MaxMemory / 8;

        //初始化lrucache
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {

            //用来测量每个bitmap所占据的内存
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();//每一行所占据的字节数*高度
            }
        };

        //创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<Runnable>();
        mType = type;

        //根据下面的代码，后台线程同时能支持threadCount个任务并发执行，其他的只是一直在往任务队列中添加，直到有任务被执行完释放
        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    /**
     * 从任务队列中取出一个任务
     *
     * @return
     */
    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else if (mType == Type.LIFO) {
            return mTaskQueue.removeLast();
        }
        return null;
    }


    /**
     * 获取一个Imageloader实例
     * @param ThreadCount
     * @param type
     * @return ImageLoader
     */
    public static ImageLoader getInstance(int ThreadCount,Type type) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(ThreadCount,type);
                }
            }
        }
        return mInstance;

    }


    /**
     * 获取一个Imageloader实例 (默认参数)
     * @return ImageLoader
     */
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(3,Type.LIFO);
                }
            }
        }
        return mInstance;

    }

    /**
     * 根据path为imageview设置图片
     *
     * @param path      图片的路径
     * @param imageView 显示图片的view
     */
    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    //获取得到的图片，为imageview回调设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bitmap = holder.bitmap;
                    ImageView imageview = holder.imageView;
                    String Path = holder.path;
                    //讲path与getTag存储的路径向比较 如果相同才进行显示
                    if (imageview.getTag().toString().equals(Path)) {
                        imageview.setImageBitmap(bitmap);
                    }
                }
            };
        }

        //根据path从缓存中获取图片
        Bitmap bm = getBitmapFromLruCache(path);

        if (bm != null) {
           /* Message message = Message.obtain();
            ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
            imgBeanHolder.bitmap = bm;
            imgBeanHolder.imageView = imageView;
            imgBeanHolder.path = path;
            message.obj = imgBeanHolder;
            mUIHandler.sendMessage(message);*/

            refreshBitmap(imageView, path, bm);

        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {
                    //加载图片

                    //图片压缩
                    //1、获取图片需要显示的大小
                    ImageSize imageSize = getImageViewSize(imageView);
                    //2、压缩图片
                    Bitmap bitmap = decodeSampleBitmapFromPath(path, imageSize.width, imageSize.heigh);
                    //3、把图片加入到缓存中
                    addBitmaptoLruCache(bitmap, path);
                    //4、回调
                  /*  Message message = Message.obtain();
                    ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
                    imgBeanHolder.bitmap = bitmap;
                    imgBeanHolder.imageView = imageView;
                    imgBeanHolder.path = path;
                    message.obj = imgBeanHolder;
                    mUIHandler.sendMessage(message);*/

                    refreshBitmap(imageView, path, bitmap);

                    //执行完队列中的一个任务就释放一个
                    mSemaphoreThreadPool.release();
                }
            });
        }

    }


    //回调UI线程显示图片
    private void refreshBitmap(ImageView imageView, String Path, Bitmap bm) {
        Message message = Message.obtain();
        ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
        imgBeanHolder.bitmap = bm;
        imgBeanHolder.imageView = imageView;
        imgBeanHolder.path = Path;
        message.obj = imgBeanHolder;
        mUIHandler.sendMessage(message);

    }


    /**
     * 将图片加入缓存
     *
     * @param bitmap 图片
     * @param path   路径
     */
    private void addBitmaptoLruCache(Bitmap bitmap, String path) {
        if (getBitmapFromLruCache(path) == null) {

            if (bitmap != null) {
                mLruCache.put(path, bitmap);
            }
        }

    }


    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     *
     * @param path  路径
     * @param width 宽
     * @param heigh 高
     * @return 图片
     */
    private Bitmap decodeSampleBitmapFromPath(String path, int width, int heigh) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//获取图片的大小并不把图片加载到内存当中
        BitmapFactory.decodeFile(path, options);

        //inSampleSize是多少 Bitmap就会变成之前的多少分之一
        options.inSampleSize = caculInSampleSize(options, width, heigh);

        //使用获取到的InSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }

    /**
     * 根据实际的宽和高和需求的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options
     * @param reqheigh 需求的搞
     * @param reqwidth 需求的款
     * @return
     */
    private int caculInSampleSize(BitmapFactory.Options options, int reqwidth, int reqheigh) {

        int width = options.outWidth;   //获取图片实际的款
        int height = options.outHeight; //获取图片实际的高

        int inSampleSize = 1;

        if (width > reqwidth || height > reqheigh) {

            int widthRadio = Math.round(width * 1.0f / reqwidth);
            int heighthRadio = Math.round(height * 1.0f / reqheigh);

            inSampleSize = Math.max(widthRadio, heighthRadio);
        }

        return inSampleSize;
    }


    /**
     * 根据Imageview获取适当的压缩的宽和高
     *
     * @param imageView Imageview
     * @return 适当的压缩的宽和高
     */
    private ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();

        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();

        int width = imageView.getWidth();//获取Imageview的实际宽度

        if (width <= 0) {
            width = lp.width;//获取在layout中声明的宽度
        }
        if (width <= 0) {
//            width = imageView.getMaxWidth();//检查最大值 （这个方法只兼容到API16）
            width = getImageViewFieldValue(imageView, "mMaxWidth");//检查最大值 （这个方法只兼容到API16）
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = imageView.getHeight();//获取Imageview的实际高度

        if (height <= 0) {
            height = lp.height;//获取在layout中声明的宽度
        }
        if (height <= 0) {
            height = getImageViewFieldValue(imageView, "mMaxHeight");//检查最大值
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }

        imageSize.width = width;
        imageSize.heigh = height;
        return imageSize;
    }

    /**
     * 通过反射获取Imageview的某个属性值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object obj, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(obj);

            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 添加一个任务
     *
     * @param runnable
     */
    private synchronized void addTasks(Runnable runnable) {
        //添加到队列中
        mTaskQueue.add(runnable);

        try {
            //这里是为了防止线程并发的时候导致mPoolThreadHandler为Null
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();  //让线程阻塞等待release
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //给后台轮询线程发送一个消息
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    /**
     * //根据path从缓存中获取图片
     *
     * @param path
     * @return
     */
    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }


}
