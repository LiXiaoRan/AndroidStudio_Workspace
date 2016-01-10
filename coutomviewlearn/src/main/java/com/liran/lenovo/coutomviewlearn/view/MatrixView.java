package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liran.lenovo.coutomviewlearn.R;
import com.liran.lenovo.coutomviewlearn.Utils.MeasureUtil;

import java.util.Arrays;

public class MatrixView extends View {
    private static final int RECT_SIZE = 400;// 矩形尺寸的一半
    private static final String TAG = "MatrixView";

    private Paint mPaint;// 画笔

    private int left, top, right, bottom;// 矩形坐上右下坐标  
    private int screenX, screenY;

    public MatrixView(Context context) {
        super(context);
        init(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }

    private void init(Context context) {

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

        //这里把加载的图片尺寸缩小为原尺寸的1/2
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;


        // 获取位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg, options);

        // 实例化一个Shader
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 实例一个矩阵对象
        Matrix matrix = new Matrix();

        // 设置矩阵变换
        matrix.setTranslate(500, 500);

//        matrix.setRotate(5);
        matrix.postRotate(5);


        float[] fs = new float[9];
        // 将从matrix中获取到的浮点数组装载进我们的fs里
        matrix.getValues(fs);
        Log.d(TAG, Arrays.toString(fs));// 输出看看呗！


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