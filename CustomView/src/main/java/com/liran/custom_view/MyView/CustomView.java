package com.liran.custom_view.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lr 李冉 on 2015-08-31.
 */
public class CustomView extends View {
    private String TAG="CustomView";
    private Paint mpaint;

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
        //设置颜色
        mpaint.setColor(Color.BLUE);

        /*
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
        */
        mpaint.setStrokeWidth(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw   with="+getMeasuredWidth()+" heigh="+getMeasuredHeight());
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 100, mpaint);
    }
}
