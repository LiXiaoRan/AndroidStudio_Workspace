package com.liran.imageswitch.Utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import com.liran.imageswitch.Bean.ImgBeanHolder;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                    }
                };
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


    }

    /**
     * 从任务队列中取出一个任务
     * @return
     */
    private Runnable getTask() {

        return null;
    }

    /**
     * 获取一个Imageloader实例
     *
     * @return ImageLoader
     */
    private static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFULT_THREAD_COUNT, Type.LIFO);
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
    public void loadImage(String path, ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    super.handleMessage(msg);
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
        Bitmap bm = getBitmapLruCache(path);

        if (bm != null) {
            Message message = Message.obtain();
            ImgBeanHolder imgBeanHolder = new ImgBeanHolder();
            imgBeanHolder.bitmap = bm;
            imgBeanHolder.imageView = imageView;
            imgBeanHolder.path = path;
            message.obj = imgBeanHolder;
            mUIHandler.sendMessage(message);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

    }

    private void addTasks(Runnable runnable) {
            //添加到队列中
            mTaskQueue.add(runnable);
            //给后台轮询线程发送一个消息
            mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    /**
     * //根据path从缓存中获取图片
     *
     * @param path
     * @return
     */
    private Bitmap getBitmapLruCache(String path) {
        return mLruCache.get(path);
    }


}
