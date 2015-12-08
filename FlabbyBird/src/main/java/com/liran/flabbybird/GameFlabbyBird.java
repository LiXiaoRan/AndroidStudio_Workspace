package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    private int mGrade = 0;


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

    /**
     * 存放管道的集合
     */
    private List<Pipe> mPipes = new ArrayList<>();

    /**
     * 两个管道之间的距离300dp
     */
    private final int PIPE_DIS_BETWEEN_TWO = DensityUtils.dp2px(getContext(), 200);

    /**
     * 记录移动距离，达到PRPE_DIS_BETWEEN_TWO生成一个管道
     */
    private int mTempMoveDistance;


    /**
     * 记录游戏状态
     */
    private GameStatus mStatus = GameStatus.WAITING;


    /**
     * 上升的距离
     */
    public static final int TOUCH_UP_SIZE = -16;

    /**
     * 将上升的距离转化为PX
     */
    public final int mBirdUpDis = DensityUtils.dp2px(getContext(), TOUCH_UP_SIZE);

    /**
     * 鸟的下落速度
     */
    private int mTmpBirdDis;

    /**
     * 鸟自动下落的距离
     */
    private final int mAutoDownSpeed = DensityUtils.dp2px(getContext(), 2);


    /**
     * 记录需要移除的管道
     */
    private List<Pipe> mNeedRemovePipe = new ArrayList<>();
    private int mRemovedPipe;

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
        mSpeed = DensityUtils.dp2px(context, 4);

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
            logic();
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
     * 处理一些逻辑上的计算
     */
    private void logic() {

        switch (mStatus) {

            case RUNNING:

                mGrade=0;
                //更新地板绘制的X坐标
                mFloor.setX(mFloor.getX() - mSpeed);

                logicPipe();
                logicBird();

                mGrade+=mRemovedPipe;
                for (Pipe pipe:mPipes){

                    if(pipe.getX()+mPipeWidth<mBird.getX()){
                        mGrade++;
                    }
                }

                CheckGameOver();


                break;

            case OVER://游戏结束

                //如果鸟还在空中，先让它掉下来
                if (mBird.getY() < mFloor.getY() - mBird.getWidth()) {
                    mTmpBirdDis += mAutoDownSpeed;
                    mBird.setY(mBird.getY() + mTmpBirdDis);
                } else {

                    mStatus = GameStatus.WAITING;
                    initPos();
                }

                break;
        }
    }

    /**
     * 重置鸟的位置等数据
     */
    private void initPos() {

        mPipes.clear();
        mNeedRemovePipe.clear();

        //重置鸟的位置
        mBird.setY(mHeigh*2/3);
        //重置下落速度
        mTmpBirdDis=0;
        //重置管道移动距离
        mTempMoveDistance=0;
        mGrade=0;
        mRemovedPipe=0;
    }

    /**
     * 处理一些小鸟相关的计算
     */
    private void logicBird() {

        //默认下落,,点击瞬间上升
        mTmpBirdDis += mAutoDownSpeed;

        mBird.setY(mBird.getY() + mTmpBirdDis);


        if(mBird.getY()<=0){
            mBird.setY(0);
        }
    }


    /**
     * 处理一些管道相关的计算
     */
    private void logicPipe() {

        //管道移动
        for (Pipe pipe : mPipes) {
            //这个判断条件代表管道已经彻底从屏幕移 '出' 了
            if (pipe.getX() < -mPipeWidth) {
                mNeedRemovePipe.add(pipe);
                mRemovedPipe++;
                continue;//如果发现这个管道需要需要移除，就不用对其进行后续的计算了，所以终止本次循环。
            }
            pipe.setX(pipe.getX() - mSpeed);
        }

        //移除管道
        mPipes.removeAll(mNeedRemovePipe);
//        Log.d(TAG, "logic: 现在管道的数量是: " + mPipes.size());
        mTempMoveDistance += mSpeed;
        //生成一个管道
        if (mTempMoveDistance >= PIPE_DIS_BETWEEN_TWO) {
            Pipe pipe = new Pipe(getContext(), getWidth(), getHeight(), mPipeTop, mPipeBettom);
            mPipes.add(pipe);
            mTempMoveDistance = 0;
        }
    }


    /**
     * 死亡判断
     */
    private void CheckGameOver() {
        //鸟的Y坐标加上鸟的图片的高度，如果大于地板的Y坐标就表示已经接触到地面
        if ((mBird.getY() + mBird.getHeight()) > mFloor.getY()) {

            mStatus = GameStatus.OVER;

        }

        //如果装到墙壁
        for (Pipe pipe : mPipes) {

            //已经穿过的
            if (pipe.getX() + mPipeWidth < mBird.getX()) {
                continue;
            }

            if (pipe.touchBird(mBird)) {
                mStatus = GameStatus.OVER;
                break;
            }


        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            switch (mStatus) {

                case RUNNING:
                    mTmpBirdDis = mBirdUpDis;
                    break;
                case WAITING:
                    mStatus = GameStatus.RUNNING;
                    break;
            }


        }
        return true;
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
        mSingleGradeRectF = new RectF(0, 0, mSingleGradeWidth, mSingleGradeHeight);

        //初始化管道范围
        mPiperect = new RectF(0, 0, mPipeWidth, mHeigh);

        /*Pipe pipe = new Pipe(getContext(), w, h, mPipeTop, mPipeBettom);
        mPipes.add(pipe);*/

    }
}
