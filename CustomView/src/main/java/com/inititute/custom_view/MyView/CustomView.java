package com.inititute.custom_view.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.inititute.custom_view.R;
import com.inititute.custom_view.Utils.DisplayUtil;
import com.inititute.custom_view.Utils.MeasureUtil;

/**
 * Created by lr 李冉 on 2015-08-31.
 */
public class CustomView extends View {
    private String TAG = "CustomView";
    private Paint mpaint;
    private int radious = 50;
    private int x, y;// 位图绘制时左上角的起点坐标
    private Bitmap bitmap;

    public CustomView(Context context) {
        super(context);
        initPaint(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
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
//        mpaint.setStyle(Paint.Style.STROKE);

        mpaint.setStyle(Paint.Style.FILL);
        // 设置画笔颜色为自定义颜色
        mpaint.setColor(Color.argb(255, 255, 128, 103));
        //设置颜色矩阵  注意 是 4x5的float[]类型的矩阵   修改这里的数值就相当于做一个滤镜了
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.9F, 0, 0.5f, 0, 0,  //A
                0, 0.5F, 0, 0.6f, 0,  //R
                0, 0, 0.5F, 0.3f, 0,  //G
                0, 0, 0, 1, 0,     //B
        });

        // 设置颜色过滤
        mpaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        // 设置颜色过滤
//        mpaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));

        // 设置颜色过滤
//        mpaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));

        /*
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
        */
        mpaint.setStrokeWidth(10);

        initRes(context);

    }


    private void initRes(Context context) {

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg2);
       /* x = Math.abs(getMeasuredWidth() / 2 - bitmap.getWidth() / 2);
        y = Math.abs(getMeasuredHeight() / 2 - bitmap.getHeight() / 2);*/
        x = MeasureUtil.getScreenWidth(context)[0] / 2 - bitmap.getWidth() / 2 - 18;
        y = (MeasureUtil.getScreenWidth(context)[1] - MeasureUtil.getStatusBarHeight(context) - DisplayUtil.dip2px(context, 50)) / 2 - bitmap.getHeight() / 2;
        Log.d(TAG, "initRes x=" + x + "y=" + y + " MeasureUtil.getScreenWidth(context)[1]= " + MeasureUtil.getScreenWidth(context)[1]);
        Log.d(TAG, "initRes bitmap.getHeight()=" + bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.d(TAG, "onDraw   with="+getMeasuredWidth()+" heigh="+getMeasuredHeight());
//        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radious, mpaint);
        Log.d(TAG, "onDraw x=" + x + "  y= " + y);
        canvas.drawBitmap(bitmap, x, y, mpaint);
    }

    public synchronized void setRadius(int radius) {
        this.radious = radius;
        invalidate();
    }

    /**
     * 开启一个线程去执行动画
     */
    public void startAnmi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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
