package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

/**
 * 管道类
 * Created by LiRan on 2015-12-07.
 */
public class Pipe {


    /**
     * 上下管道间的距离
     */
    public static final float RADIO_BETWEEN_UP_DOWN = 1 / 5F;

    /**
     * 上管道的最大高度
     */
    private static final float RADIO_MAX_HEIGHT = 2 / 5F;
    /**
     * 上管道最小高度
     */
    private static final float RADIO_MIN_HEIGHT = 1 / 5F;


    /**
     * 管道的横坐标
     */
    private int x;

    /**
     * 上管道的高度
     */
    private int height;

    /**
     * 上下管道间的距离
     */
    private int margin;


    /**
     * 上管道图片
     */
    private Bitmap mTop;

    /**
     * 下管道图片
     */
    private Bitmap mBottom;

    private static Random random = new Random();


    public Pipe(Context context, int gameWidth, int gameHeigh, Bitmap mTop, Bitmap mBottom) {

        margin = (int) (gameHeigh * RADIO_BETWEEN_UP_DOWN);
        x = gameWidth;
        this.mTop = mTop;
        this.mBottom = mBottom;

        randomHeight(gameHeigh);

    }

    /**
     * 随机生成一个高度
     */
    private void randomHeight(int gameHeight) {
        height = random.nextInt((int) (gameHeight * (RADIO_MAX_HEIGHT - RADIO_MIN_HEIGHT)));
        height = (int) (height + gameHeight * RADIO_MIN_HEIGHT);
    }


    /**
     * 绘制自己
     *
     * @param mCanvas
     * @param rect
     */
    public void draw(Canvas mCanvas, RectF rect) {
        mCanvas.save(Canvas.MATRIX_SAVE_FLAG);
        // rect为整个管道，假设完整管道为100，需要绘制20，则向上偏移80
        mCanvas.translate(x, -(rect.bottom - height));
        mCanvas.drawBitmap(mTop, null, rect, null);//绘制上管道
        // 下管道，偏移量为，上管道高度+margin
        mCanvas.translate(0, (rect.bottom - height) + height + margin);
        mCanvas.drawBitmap(mBottom,null,rect,null);
        mCanvas.restore();

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
