package com.liran.imageswitch.Utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * 加载图片的工具类
 * Created by Lenovo on 2015-09-18.
 */
public class ImageLoader {


    private static ImageLoader mInstance;

    /**
     * 图片缓存的核心对象
     */
    private LruCache<String,Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;

    private static final int DEFULT_THREAD_COUNT=1;

    /**
     * 队列的调度方式
     */
    private Type mType=Type.LIFO;

    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTaskQueue;


    /**
     * 后台轮询线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;


    /**
     * UI线程中的handler
     */
    private Handler mUIHandler;


    public enum Type{
        FIFO,LIFO;
    }


    private ImageLoader(int ThreadCount,Type type) {
        init(ThreadCount,type);
    }

    /**
     * 初始化操作
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {

        //后台轮询线程
        mPoolThread=new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
            }
        };

    }

    /**
     * 获取一个Imageloader实例
     * @return ImageLoader
     */
    private static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFULT_THREAD_COUNT,Type.LIFO);
                }
            }
        }
        return mInstance;

    }


}
