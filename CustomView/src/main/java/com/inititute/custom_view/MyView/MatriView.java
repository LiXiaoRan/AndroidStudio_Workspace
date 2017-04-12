package com.inititute.custom_view.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.inititute.custom_view.R;
import com.inititute.custom_view.Utils.MeasureUtil;

/**
 * Created by LiRan on 2015-12-29.
 */
public class MatriView extends View {
    private static final int RECT_SIZE = 400;// 矩形尺寸的一半

    private Paint mPaint;// 画笔

    private int left, top, right, bottom;// 矩形坐上右下坐标
    private int screenX, screenY;

    public MatriView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取屏幕尺寸数据
        int[] screenSize = MeasureUtil.getScreenWidth(context);

        // 获取屏幕中点坐标
        screenX = screenSize[0] / 2;
        screenY = screenSize[1] / 2;

        // 计算矩形左上右下坐标值
        left = screenX - RECT_SIZE;
        top = screenY - RECT_SIZE;
        right = screenX + RECT_SIZE;
        bottom = screenY + RECT_SIZE;

        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 获取位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);

        // 实例化一个Shader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 实例一个矩阵对象
        Matrix matrix = new Matrix();

        // 设置矩阵变换
        matrix.setTranslate(500, 500);

        // 设置Shader的变换矩阵
        bitmapShader.setLocalMatrix(matrix);

        // 设置着色器
        mPaint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制矩形
        // canvas.drawRect(left, top, right, bottom, mPaint);
        canvas.drawRect(0, 0, screenX * 2, screenY * 2, mPaint);
    }
}
