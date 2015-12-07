package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 游戏朱类
 * Created by LiRan on 2015-12-05.
 */
public class GameFlabbyBird extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private String TAG = "GameFlabbyBird";

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


    private int mWidth;
    private int mHeigh;
    private RectF mGamePanelRect = new RectF();

    /**
     * 背景
     */
    private Bitmap mbg;


    /**
     * 鸟
     */
    private Bird mBird;
    /**
     * 鸟的图片
     */
    private Bitmap mBirdBitmap;




    public GameFlabbyBird(Context context) {
        this(context, null);
    }

    public GameFlabbyBird(Context context, AttributeSet attrs) {
        super(context, attrs);
        mhHolder = getHolder();
        mhHolder.addCallback(this);
        initBitmaps();
    }
    public GameFlabbyBird(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameFlabbyBird(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }




    /**
     * 初始化图片
     */
    private void initBitmaps() {
        mbg = loadImageByResId(R.mipmap.bg1);
        mBirdBitmap=loadImageByResId(R.mipmap.b1);
    }

    private void draw() {

        try {
            //获得canvas
            mCanvas = mhHolder.lockCanvas();
            if (mCanvas != null) {
                drawBg();
                drawBird();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mhHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void drawBird() {
        mBird.draw(mCanvas);
    }

    /**
     * 绘制背景
     */
    private void drawBg() {
        mCanvas.drawBitmap(mbg, null, mGamePanelRect, null);
    }


    private Bitmap loadImageByResId(int resId) {

        return BitmapFactory.decodeResource(getResources(), resId);
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

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
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

    /**
     * 初始化尺寸
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeigh=h;
        mGamePanelRect.set(0,0,w,h);
        mBird=new Bird(getContext(),mWidth,mHeigh,mBirdBitmap);
    }
}
