package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.liran.flabbybird.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏朱类
 * Created by LiRan on 2015-12-05.
 */
public class GameFlabbyBird extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private String TAG = "GameFlabbyBird";

    private SurfaceHolder mhHolder;

    /**
     * 分数
     */
    private final int[] mNums = new int[]{R.mipmap.n0, R.mipmap.n1,
            R.mipmap.n2, R.mipmap.n3, R.mipmap.n4, R.mipmap.n5,
            R.mipmap.n6, R.mipmap.n7, R.mipmap.n8, R.mipmap.n9};
    private Bitmap[] mNumBitmap;
    private int mGrade = 100;


    /**
     * 单个数字的盖度的1/15
     */
    public static final float RADIO_SINGLE_NUM_HEIGHT = 1 / 15F;

    /**
     * 单个数字的宽度
     */
    private int mSingleGradeWidth;

    /**
     * 单个数字的高度
     */
    private int mSingleGradeHeight;

    /**
     * 单个数字的范围
     */
    private RectF mSingleGradeRectF;


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

    private Paint mPaint;

    /**
     * 地板
     */
    private Floor mFloor;
    private Bitmap mFloorBg;

    /**
     * 速度
     */
    private int mSpeed;


    /***
     * ***************管道相关*************
     */

    private Bitmap mPipeTop;
    private Bitmap mPipeBettom;
    private RectF mPiperect;
    private int mPipeWidth;

    /**
     * 管道宽度60dp
     */
    public static final int PIPE_WIDTH = 60;

    private List<Pipe> mPipes = new ArrayList<>();


    public GameFlabbyBird(Context context) {
        this(context, null);
    }

    public GameFlabbyBird(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPipeWidth = DensityUtils.dp2px(context, PIPE_WIDTH);

        mhHolder = getHolder();
        mhHolder.addCallback(this);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);//开启抖动

        initBitmaps();
        //初始化速度
        mSpeed = DensityUtils.dp2px(context, 2);

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
        mBirdBitmap = loadImageByResId(R.mipmap.b1);
        mFloorBg = loadImageByResId(R.mipmap.floor_bg2);
        mPipeTop = loadImageByResId(R.mipmap.g2);
        mPipeBettom = loadImageByResId(R.mipmap.g1);

        mNumBitmap = new Bitmap[mNums.length];
        for (int i = 0; i < mNumBitmap.length; i++) {
            mNumBitmap[i] = loadImageByResId(mNums[i]);
        }

    }

    private void draw() {

        try {
            //获得canvas
            mCanvas = mhHolder.lockCanvas();
            if (mCanvas != null) {
                drawBg();
                drawBird();
                drawPipes();
                drawFloor();
                drawGrades();
                //更新地板绘制的X坐标
                mFloor.setX(mFloor.getX() - mSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mhHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    /**
     * 绘制分数
     */
    private void drawGrades() {
        String grade = mGrade + "";
        mCanvas.save(Canvas.MATRIX_SAVE_FLAG);
        mCanvas.translate(mWidth / 2 - grade.length() * mSingleGradeWidth / 2, 1f / 8 * mHeigh);

        for (int i = 0; i < grade.length(); i++) {
            String numStr = grade.substring(i, i + 1);
            int num = Integer.valueOf(numStr);
            mCanvas.drawBitmap(mNumBitmap[num], null, mSingleGradeRectF, null);
            mCanvas.translate(mSingleGradeWidth, 0);
        }

        mCanvas.restore();
    }

    /**
     * 绘制管道
     */
    private void drawPipes() {
        for (Pipe pipe : mPipes) {
            pipe.setX(pipe.getX() - mSpeed);
            pipe.draw(mCanvas, mPiperect);
        }
    }

    /**
     * 绘制地板
     */
    private void drawFloor() {
        mFloor.draw(mCanvas, mPaint);
    }

    /**
     * 绘制小鸟
     */
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
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigh = h;
        mGamePanelRect.set(0, 0, w, h);
        mBird = new Bird(getContext(), mWidth, mHeigh, mBirdBitmap);
        //初始化地板
        mFloor = new Floor(mWidth, mHeigh, mFloorBg);

        //初始化分数
        mSingleGradeHeight = (int) (h * RADIO_SINGLE_NUM_HEIGHT);
        mSingleGradeWidth = (int) (mSingleGradeHeight * 1.0f / mNumBitmap[0].getHeight() * mNumBitmap[0].getWidth());
        mSingleGradeRectF=new RectF(0,0,mSingleGradeWidth,mSingleGradeHeight);

        //初始化管道范围
        mPiperect = new RectF(0, 0, mPipeWidth, mHeigh);
        Pipe pipe = new Pipe(getContext(), w, h, mPipeTop, mPipeBettom);
        mPipes.add(pipe);

    }
}
