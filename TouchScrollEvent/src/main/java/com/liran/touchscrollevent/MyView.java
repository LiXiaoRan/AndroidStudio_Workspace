package com.liran.touchscrollevent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义一个view，可以跟随手指的移动在全屏内滑动
 * Created by LiRan on 2015-11-24.
 */
public class MyView extends View {
    private Paint paint;
    private Bitmap bitmap;

    private int mLastX;
    private int mLastY;
    private String TAG = "MyView";

    public MyView(Context context) {
        super(context);
        initView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }



    /**
     * view初始化
     */
    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.d(TAG, "onTouchEvent: RawX: "+x+" RawY: "+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int deltax = x - mLastX;
                int deltay = y - mLastY;
                Log.d(TAG, "onTouchEvent: deltax: " + deltax + " deltay: " + deltay);
                int translationX = (int) getTranslationX() + deltax;
                int translationY = (int) getTranslationY() + deltay;
                setTranslationX(translationX);
                setTranslationY(translationY);
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }

        mLastX = x;
        mLastY = y;
        return true;
    }

}
