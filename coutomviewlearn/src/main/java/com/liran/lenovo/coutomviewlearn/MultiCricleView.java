package com.liran.lenovo.coutomviewlearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiRan on 2015-12-31.
 */
public class MultiCricleView extends View {

    /**
     * 描边宽度占比
     */
    private static final float STROKE_WIDTH = 1F / 256F;
    /**
     * 线段长度占比
     */
    private static final float LINE_LENGTH = 3F / 32F;
    /**
     * 大圆半径占比
     */
    private static final float CRICLE_LARGER_RADIU = 3F / 32F;
    /**
     * 小圆半径占比
     */
    private static final float CRICLE_SMALL_RADIU =5F / 64F;
    /**
     * 弧半径
     */
    private static final float ARC_RADIU =  1F / 8F;
    /**
     * 弧文字半径
     */
    private static final float ARC_TEXT_RADIU = 5F / 32F;
    private static final String TAG = "aaaa";


    private Paint strokePaint;

    private int size;//控件边长

    private float strokeWidth;// 描边宽度
    private float ccx, ccy;//中心园圆心坐标
    private float largeCircleRadiu;//大圆半径


    public MultiCricleView(Context context) {
        super(context);
        initPaint(context);
    }

    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public MultiCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //强制长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 获取控件边长
        size = w;
        //参数计算
        calcuiation();

    }

    /**
     * 参数计算
     */
    private void calcuiation() {
        //计算描边宽度
        strokeWidth=size*STROKE_WIDTH;
        //计算大圆半径
        largeCircleRadiu=size*CRICLE_LARGER_RADIU;

        //计算中心圆坐标
        ccx=size/2;
        ccy=size/2+CRICLE_LARGER_RADIU*size;
        //设置参数
        setPara();
    }

    /**
     * 设置参数
     */
    private void setPara() {
        strokePaint.setStrokeWidth(strokeWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        canvas.drawColor(0xFFF29B76);

        //绘制中心愿
        canvas.drawCircle(ccx,ccy,largeCircleRadiu,strokePaint);
    }
}

