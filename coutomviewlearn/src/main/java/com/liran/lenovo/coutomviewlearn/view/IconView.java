package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liran.lenovo.coutomviewlearn.R;
import com.liran.lenovo.coutomviewlearn.Utils.MeasureUtil;

/**
 * 一个完整的自定义view的案例
 * Created by LiRan on 2016-01-30.
 */
public class IconView extends View {

    private static final String TAG = "IconView";
    private Bitmap mBitmap;//位图
    private TextPaint textPaint;//绘制文本的画笔
    private String mStr;//绘制的文本

    private float TEXTSIZE;//画笔的文本尺寸


    private enum Ratio {
        WIDTH, HEIGHT
    }

    public IconView(Context context) {
        super(context);
        init(context);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        calArgs(context);

        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.android_1);
        }

        if (mStr == null || mStr.trim().length() == 0) {
            mStr = "就是试试而已";
        }

        //初始化画笔设置参数
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(TEXTSIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void calArgs(Context context) {
        int screenSize[] = MeasureUtil.getScreenWidth(context);
        TEXTSIZE = screenSize[0] * 1 / 10F;
        Log.d(TAG, "calArgs: width is "+screenSize[0]+" height is "+screenSize[1]);
    }


    /**
     * 获取测量后的尺寸
     *
     * @param measureSpec
     * @param ratio
     * @return
     */
    private int getMeasureSize(int measureSpec, Ratio ratio) {
        int result = 0;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {

            case MeasureSpec.EXACTLY:
                result = size;
                break;
            default:// 默认情况下将UNSPECIFIED和AT_MOST一并处理
                if (ratio == Ratio.WIDTH) {
                    float textWidth = textPaint.measureText(mStr);
                    result = (int) textWidth > mBitmap.getWidth() ? (int) textWidth : mBitmap.getWidth() + getPaddingLeft() + getPaddingRight();
                } else if (ratio == Ratio.HEIGHT) {
                    result = (int) ((textPaint.descent() - textPaint.ascent()) * 2 + mBitmap.getHeight()) + getPaddingTop() + getPaddingBottom();
                }

                /**
                 * AT_MOST时判断size和result的大小取小值
                 */
                if (mode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, size);
                }

                break;
        }

        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureSize(widthMeasureSpec, Ratio.WIDTH), getMeasureSize(heightMeasureSpec, Ratio.HEIGHT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw: getWidth= "+getWidth()+" getHeight= "+getHeight());
        canvas.drawBitmap(mBitmap, getWidth() / 2 - mBitmap.getWidth() / 2, getHeight() / 2 - mBitmap.getHeight() / 2, null);
        canvas.drawText(mStr, getWidth() / 2, mBitmap.getHeight() + getHeight() / 2 - mBitmap.getHeight() / 2 - textPaint.ascent(), textPaint);

    }
}
