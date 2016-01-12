package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.liran.lenovo.coutomviewlearn.R;

/**
 * Created by LiRan on 2016-01-12.
 */
public class BitmapMeshView2 extends View {

    /**
     * 分割数
     */
    private static final int WIDTH = 9, HEIGHT = 9;
    /**
     * 交点数
     */
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    private Bitmap mBitmap;

    /**
     * 基准点的坐标数组
     */
    private float[] matrixOriginal = new float[COUNT * 2];
    /**
     * 变换后点的坐标数组
     */
    private float[] matrixMoved = new float[COUNT * 2];

    /**
     * 触摸屏幕时手指的XY坐标
     */
    private float clickX, clickY;

    /**
     * 基准点变换点和线段的绘制Paint
     */
    private Paint originPaint, movePaint, linePaint;


    public BitmapMeshView2(Context context) {
        super(context);
        init(context);
    }

    public BitmapMeshView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BitmapMeshView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setFocusable(true);

        //实例画笔并且设置颜色
        originPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        originPaint.setColor(0x660000FF);
        movePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        movePaint.setColor(0x99FF0000);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(0xFFFFFB00);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gir2l);

        //初始化坐标数组
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy=mBitmap.getHeight()*y/HEIGHT;
        }

    }


}
