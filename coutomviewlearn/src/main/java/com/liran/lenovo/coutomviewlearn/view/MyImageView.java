package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiRan on 2016-01-26.
 */
public class MyImageView extends View {

    private Bitmap mBitmap;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultWidth = 0;
        int resultHeight = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heighSize = MeasureSpec.getSize(heightMeasureSpec);

        //如果爹心里有数
        if (widthMode == MeasureSpec.EXACTLY) {
            // 那么儿子也不要让爹难做就取爹给的大小吧
            resultWidth = widthSize;
        }
        //如果父控件心里没数
        else {
            //  那么儿子可要自己看看自己需要多大了
            resultWidth = mBitmap.getWidth();

            //如果爹给儿子的是一个限制值
            if (widthMode == MeasureSpec.AT_MOST) {
                // 那么儿子自己的需求就要跟爹的限制比比看谁小要谁
                resultWidth = Math.min(resultWidth, widthSize);
            }
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            resultHeight = heighSize;

        } else {

            resultHeight = mBitmap.getHeight();

            if (heightMode == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, heighSize);
            }
        }

        //计算上padding的大小
        resultHeight = resultHeight + getPaddingTop() + getPaddingBottom();
        resultWidth = resultHeight + getPaddingLeft() + getPaddingRight();

        setMeasuredDimension(resultWidth, resultHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }


}
