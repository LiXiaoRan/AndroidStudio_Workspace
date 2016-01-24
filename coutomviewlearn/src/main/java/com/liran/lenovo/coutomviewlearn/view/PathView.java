package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiRan on 2016-01-24.
 */
public class PathView extends View {

    /**
     * 路径对象
     */
    private Path mPath;

    /**
     * 画笔对象
     */
    private Paint mPaint;

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        //实例化路径
        mPath=new Path();

//        drawPath();
        mPath.moveTo(100,100);
        draw2Bezier();
        draw3Bezier();

    }

    /**
     * 绘制3阶贝塞尔曲线
     */

    private void draw3Bezier() {
        mPath.moveTo(400,400);

        //前四个参数是控制点后两个是终点
        mPath.cubicTo(200,200,300,0,400,100);

    }

    /**
     * 绘制2阶贝塞尔曲线
     */
    private void draw2Bezier() {

        //200,200 是控制点坐标，300,100是终点坐标
        mPath.quadTo(200,200,300,100);

    }


    /**
     * 绘制路径
     */
    private void drawPath() {
        mPath.moveTo(100,100);

        mPath.lineTo(300,100);
        mPath.lineTo(400,200);
        mPath.lineTo(200,200);


        //闭合路径
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制路径
        canvas.drawPath(mPath,mPaint);

    }
}
