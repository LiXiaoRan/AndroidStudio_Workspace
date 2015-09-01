package com.liran.custom_view.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lr 李冉 on 2015-08-31.
 */
public class CustomView extends View {
    private String TAG="CustomView";
    private Paint mpaint;
    private int radious=50;

    public CustomView(Context context) {
        super(context);
        initPaint();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mpaint = new Paint();
        mpaint.setAntiAlias(true);

        /*
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mpaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色为自定义颜色
        mpaint.setColor(Color.argb(255, 255, 128, 103));

        /*
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
        */
        mpaint.setStrokeWidth(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.d(TAG, "onDraw   with="+getMeasuredWidth()+" heigh="+getMeasuredHeight());
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radious, mpaint);
    }

    public synchronized  void setRadius(int radius){
        this.radious=radius;
        invalidate();
    }

    /**
     * 开启一个线程去执行动画
     */
    public void startAnmi(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){


                 /*
                 * 确保线程不断执行不断刷新界面
                 */
                    while (true) {
                        try {
                        /*
                         * 如果半径小于200则自加否则大于200后重置半径值以实现往复
                         */
                            if (radious <= 200) {
                                radious += 10;
                                //非主线程刷新view
                               postInvalidate();
                            } else {
                                radious = 0;
                            }

                            // 每执行一次暂停40毫秒
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }


            }
        }).start();

    }






}
