package com.liran.lenovo.coutomviewlearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by LiRan on 2015-12-29.
 */
public class MatrixImageView extends ImageView {

    /**
     * 默认的触摸模式
     */
    private static final int MODE_NONE = 0x00123;
    /**
     * 拖拽模式
     */
    private static final int MODE_DRAG = 0x00321;
    /**
     * 缩放or旋转模式
     */
    private static final int MODE_ZOOM = 0x00132;


    private int mod;//当前的触摸模式

    /**
     * 上一次手指移动的距离
     */
    private float preMove = 1F;
    private float saveRotate = 0F;//保存了的角度值
    private float rotate = 0F;//旋转的角度

    /**
     * 上一次各触摸点的坐标集合
     */
    private float[] preEventCoor;
    private PointF start, mid;// 起点、中点对象
    private Matrix currentMatrix, savedMatrix;// 当前和保存了的Matrix对象
    private Context mContext;

    public MatrixImageView(Context context) {
        super(context);
        init(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        //实例化对象
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();

        //模块初始化
        mod = MODE_NONE;

        //设置图片资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        setImageBitmap(bitmap);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {  //这样可以监听多点触摸事件
            case MotionEvent.ACTION_DOWN://单点接触屏幕时

                savedMatrix.set(currentMatrix);
                start.set(event.getX(), event.getY());
                mod = MODE_DRAG;
                preEventCoor = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN://第二个点接触屏幕时

                preMove = calSpacing(event);
                if (preMove > 10F) {
                    savedMatrix.set(currentMatrix);
                    calMidPoint(mid, event);
                    mod = MODE_ZOOM;
                }
                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getY(1);
                preEventCoor[2] = event.getX(0);
                preEventCoor[3] = event.getY(1);

                break;

            case MotionEvent.ACTION_UP://单点离开屏幕时
            case MotionEvent.ACTION_POINTER_UP://第二个点离开屏幕时
                mod = MODE_NONE;
                preEventCoor = null;
                break;


            case MotionEvent.ACTION_MOVE://触摸点移动时
                /**
                 *单点触摸拖拽平移
                 */
                if (mod == MODE_DRAG) {

                    currentMatrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getX() - start.y;
                    currentMatrix.postTranslate(dx, dy);

                } else if (mod == MODE_ZOOM && event.getPointerCount() == 2) {
                    /**
                     * 两点触摸拖放旋转
                     */
                    float currentMove = calSpacing(event);
                    currentMatrix.set(savedMatrix);

                    //指间移动距离大于10缩放
                    if (currentMove > 10F) {
                        float sacle = currentMove / preMove;
                        currentMatrix.postScale(sacle,sacle,mid.x,mid.y);
                    }
                    //保持两点时旋转
                    if(preEventCoor!=null){
                        rotate=calRotation(event);
                        float r=rotate-saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                    }

                }


                break;

        }

        setImageMatrix(currentMatrix);
        return true;
    }

    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }

    private void calMidPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private float calSpacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);


    }
}
