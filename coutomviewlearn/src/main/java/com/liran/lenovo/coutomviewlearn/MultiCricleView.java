package com.liran.lenovo.coutomviewlearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiRan on 2015-12-31.
 */
public class MultiCricleView extends View {

    /**
     * 描边宽度占比
     */
    private static final float STROKE_WIDTH = 1F / 256F;
    /**
     * 线段长度占比
     */
    private static final float LINE_LENGTH = 3F / 32F;
    /**
     * 大圆半径占比
     */
    private static final float CRICLE_LARGER_RADIU = 3F / 32F;
    /**
     * 小圆半径占比
     */
    private static final float CRICLE_SMALL_RADIU = 5F / 64F;
    /**
     * 弧半径
     */
    private static final float ARC_RADIU = 1F / 8F;
    /**
     * 弧文字半径
     */
    private static final float ARC_TEXT_RADIU = 5F / 32F;
    /**
     * 大圆小圆线段两端间隔占比
     */
    private static final float SPACE = 1F / 64F;

    private static final String TAG = "MultiCricleView";


    /**
     * 描边画笔
     */
    private Paint strokePaint;

    /**
     * 文字画笔
     */
    private Paint textPaint;


    private Paint arcPaint;

    private int size;//控件边长
    private float strokeWidth;// 描边宽度
    private float ccx, ccy;//中心园圆心坐标
    private float largeCircleRadiu;//大圆半径
    private float lineLength;//线段长度
    private float smallCirclRadiu;//小圆半径
    private float space;// 大圆小圆线段两端间隔
    private float textOffsetY;

    public MultiCricleView(Context context) {
        super(context);
        initPaint(context);
    }

    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public MultiCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);


        /*
         * 初始化文字画笔
         */
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);


        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);


        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //强制长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 获取控件边长
        size = w;
        //参数计算
        calcuiation();

    }

    /**
     * 参数计算
     */
    private void calcuiation() {
        //计算描边宽度
        strokeWidth = size * STROKE_WIDTH;
        //计算大圆半径
        largeCircleRadiu = size * CRICLE_LARGER_RADIU;
        //计算小圆半径
        smallCirclRadiu = size * CRICLE_SMALL_RADIU;

        space = size * SPACE;

        //计算线段长度
        lineLength = size * LINE_LENGTH;

        //计算中心圆坐标
        ccx = size / 2;
        ccy = size / 2 + CRICLE_LARGER_RADIU * size;
        //设置参数
        setPara();
    }

    /**
     * 设置参数
     */
    private void setPara() {
        strokePaint.setStrokeWidth(strokeWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        canvas.drawColor(0xFFF29B76);

        //绘制中心圆
        canvas.drawCircle(ccx, ccy, largeCircleRadiu, strokePaint);
        canvas.drawText("冉哥", ccx, ccy - textOffsetY, textPaint);
//        Log.d(TAG, "onDraw: textOffsetY is "+textOffsetY);
        //绘制左上方图形
        drawTopLeft(canvas);

        //绘制右上方图形
        drawTopRight(canvas);


        //绘制正下方图形
        drawBottom(canvas);

        //绘制左下方图形
        drawLeftBottom(canvas);

        //绘制右下方图形
        drawRightBottom(canvas);

    }

    private void drawRightBottom(Canvas canvas) {

        canvas.save();

        canvas.translate(ccx, ccy);
        canvas.rotate(-60);

        canvas.drawLine(0, largeCircleRadiu + space, 0, largeCircleRadiu + space + lineLength, strokePaint);
        canvas.drawCircle(0, largeCircleRadiu + space * 2 + lineLength + smallCirclRadiu, smallCirclRadiu, strokePaint);

        canvas.restore();
    }

    private void drawLeftBottom(Canvas canvas) {

        canvas.save();

        canvas.translate(ccx, ccy);
        canvas.rotate(60);

        canvas.drawLine(0, largeCircleRadiu + space, 0, largeCircleRadiu + space + lineLength, strokePaint);
        canvas.drawCircle(0, largeCircleRadiu + space * 2 + lineLength + smallCirclRadiu, smallCirclRadiu, strokePaint);

        canvas.restore();

    }

    /**
     * 绘制右上方图形
     *
     * @param canvas
     */
    private void drawBottom(Canvas canvas) {
        canvas.save();

        canvas.translate(ccx, ccy);

        canvas.drawLine(0, largeCircleRadiu + space, 0, largeCircleRadiu + space + lineLength, strokePaint);
        canvas.drawCircle(0, largeCircleRadiu + space * 2 + lineLength + smallCirclRadiu, smallCirclRadiu, strokePaint);

        canvas.restore();
    }

    /**
     * 绘制右上方图形
     *
     * @param canvas
     */
    private void drawTopRight(Canvas canvas) {
        float circleY = -3 * lineLength;

        canvas.save();

        canvas.translate(ccx, ccy);
        canvas.rotate(30);

        canvas.drawLine(0, -lineLength, 0, -2 * lineLength, strokePaint);
        canvas.drawCircle(0, circleY, largeCircleRadiu, strokePaint);

        //画弧形
        drawTopRightArc(canvas, circleY);

        canvas.restore();
    }

    /**
     * 画弧形
     *
     * @param canvas
     * @param circleY
     */
    private void drawTopRightArc(Canvas canvas, float circleY) {
        canvas.save();

        canvas.translate(0, circleY);
        canvas.rotate(-30);

        float arcRadiu = size * ARC_RADIU;

        RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);

        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(oval, -22.5F, -135, true, arcPaint);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5F, -135, false, arcPaint);


        float arcTextRadiu = size * ARC_TEXT_RADIU;

        canvas.save();
        // 把画布旋转到扇形左端的方向
        canvas.rotate(-135F / 2F);

        /**
         * 每隔33.75度角画一次文本
         */
        for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
            canvas.save();
            canvas.rotate(i);

            canvas.drawText("冉", 0, -arcTextRadiu, textPaint);

            canvas.restore();
        }

        canvas.restore();
        canvas.restore();

    }


    /**
     * 绘制左上方的图形
     *
     * @param canvas
     */
    private void drawTopLeft(Canvas canvas) {
        //保存当前画布
        canvas.save();

        //平移和旋转
        canvas.translate(ccx, ccy);
        canvas.rotate(-30);//逆时针旋转30度

        // 依次画：线-圈-线-圈
        canvas.drawLine(0, -largeCircleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCircleRadiu, strokePaint);//线段长度和大圆半径是一样的
        canvas.drawLine(0, -largeCircleRadiu * 4, 0, -lineLength * 5, strokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCircleRadiu, strokePaint);//线段长度和大圆半径是一样的

        //还原画布
        canvas.restore();
    }
}

