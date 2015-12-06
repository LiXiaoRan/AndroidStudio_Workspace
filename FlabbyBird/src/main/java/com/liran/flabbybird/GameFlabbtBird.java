package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 像素鸟
 * Created by LiRan on 2015-12-05.
 */
public class GameFlabbtBird extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private String TAG = "GameFlabbtBird";

    private SurfaceHolder mhHolder;
    /**
     * 与surfaceview绑定的画布
     */
    private Canvas mCanvas;
    /**
     * 线程的控制开关
     */
    private boolean isRuning;
    /**
     * 用于绘制的线程
     */
    private Thread t;

    public GameFlabbtBird(Context context) {
        this(context, null);
    }

    public GameFlabbtBird(Context context, AttributeSet attrs) {
        super(context, attrs);

        mhHolder = getHolder();
        mhHolder.addCallback(this);

        setZOrderOnTop(true);
        mhHolder.setFormat(PixelFormat.TRANSLUCENT);//设置画布背景透明

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);

        //设置屏幕常亮
        setKeepScreenOn(true);

    }

    public GameFlabbtBird(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public GameFlabbtBird(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ");
        //开启线程
        isRuning = true;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ");
        isRuning = false;
    }

    @Override
    public void run() {

        while (isRuning) {

            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();

            try {
                if (end - start <= 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }


    private void draw() {

        try {
            //获得canvas
            mCanvas = mhHolder.lockCanvas();
            if (mCanvas != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mhHolder.unlockCanvasAndPost(mCanvas);
        }


    }
}
