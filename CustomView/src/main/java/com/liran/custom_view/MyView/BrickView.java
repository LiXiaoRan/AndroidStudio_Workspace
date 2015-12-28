package com.liran.custom_view.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.liran.custom_view.R;

/**
 * 探照灯的效果 利用的shader
 * Created by LiRan on 2015-12-28.
 */
public class BrickView extends View {


    private Paint mFillPaint, mStrockPaint;

    private BitmapShader mBitmapShader;

    private float postX, postY;//触摸点的位置

    public BrickView(Context context) {
        super(context);
        initPaint();
    }

    public BrickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BrickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction()==MotionEvent.ACTION_MOVE){

            postX=event.getX();
            postY=event.getY();

            invalidate();
        }



        return true;
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        /**
         * 描边笔的初始化
         */
        mStrockPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrockPaint.setColor(0xFF000000);
        mStrockPaint.setStyle(Paint.Style.STROKE);
        mStrockPaint.setStrokeWidth(5);


        /**
         * 用来填充的画笔
         */
        mFillPaint = new Paint();

        /**
         * shader
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.brick);
        mBitmapShader=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mFillPaint.setShader(mBitmapShader);

    }




    @Override
    protected void onDraw(Canvas canvas) {
        //背景颜色
        canvas.drawColor(Color.DKGRAY);

        canvas.drawCircle(postX,postY,150,mFillPaint);
        canvas.drawCircle(postX,postY,150,mStrockPaint);

    }
}
