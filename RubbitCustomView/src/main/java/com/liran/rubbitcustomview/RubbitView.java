package com.liran.rubbitcustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liran.rubbitcustomview.Utils.MeasureUtil;

/**
 * 自定义的一个类似橡皮擦的一个效果
 * Created by LiRan on 2015-12-23.
 */
public class RubbitView extends View {

    private static final int MIN_MOVE_DIS = 5;// 最小的移动距离：如果我们手指在屏幕上的移动距离小于此值则不会绘制
    /**
     * 前景橡皮擦的Bitmap和背景我们底图的Bitmap
     */
    private Bitmap fgBitmap, bgBitmap;

    private Canvas mCanvas;// 绘制橡皮擦路径的画布
    private Paint mPaint;// 橡皮檫路径画笔
    private Path mPath;// 橡皮擦绘制路径

    private int screenWidth, screenHeight;//屏幕宽高
    private float preX, preY;//记录上一个触摸事件的位置坐标

    public RubbitView(Context context) {
        super(context);
        init(context);
    }

    public RubbitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cal(context);
        init(context);
    }

    public RubbitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 计算参数
     *
     * @param context
     */
    private void cal(Context context) {

        int[] screenSize = MeasureUtil.getScreenWidth(context);

        screenWidth = screenSize[0];
        screenHeight = screenSize[1];
    }

    /**
     * 初始化对象
     *
     * @param context
     */
    private void init(Context context) {

        mPath = new Path();

        //开启抗锯齿和抗抖动
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mPaint.setARGB(128, 255, 0, 0);

        //设置混合模式为DST_IN ：只在源图像和目标图像相交的地方绘制目标图像
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //描边
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置路径结合处样式
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        // 设置笔触类型
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint.setStrokeWidth(50);

        // 生成前景图Bitmap
        fgBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        //将其注入画布
        mCanvas = new Canvas(fgBitmap);
        //设置背景为中性灰
        mCanvas.drawColor(0xFF808080);

        //获取背景底图Bitmap
        bgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);

        // 缩放背景底图Bitmap至屏幕大小
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenWidth, screenHeight, true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景
        canvas.drawBitmap(bgBitmap, 0, 0, null);
        //绘制前景
        canvas.drawBitmap(fgBitmap, 0, 0, null);

         /**
         * 这里要注意canvas和mCanvas是两个不同的画布对象
         * 当我们在屏幕上移动手指绘制路径时会把路径通过mCanvas绘制到fgBitmap上
         * 每当我们手指移动一次均会将路径mPath作为目标图像绘制到mCanvas上，而在上面我们先在mCanvas上绘制了中性灰色
         * 两者会因为DST_IN模式的计算只显示中性灰，但是因为mPath的透明，计算生成的混合图像也会是透明的
         * 所以我们会得到“橡皮擦”的效果
         */
        mCanvas.drawPath(mPath, mPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取当前点击事件的坐标
        float X = event.getX();
        float Y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                 mPath.reset();
                mPath.moveTo(X,Y);
                preX=X;
                preY=Y;
                break;

            case MotionEvent.ACTION_MOVE:

                float dx=Math.abs(X-preX);
                float dy=Math.abs(Y-preY);

                if(dx>=MIN_MOVE_DIS||dy>=MIN_MOVE_DIS){
                    mPath.quadTo(preX,preY,X,Y);
                    preX=X;
                    preY=Y;
                }
                break;

        }

        invalidate();
        return true;
    }
}
