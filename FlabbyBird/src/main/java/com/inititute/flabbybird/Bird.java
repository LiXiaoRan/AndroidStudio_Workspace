package com.inititute.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.inititute.flabbybird.utils.DensityUtils;

/**
 * 小鸟
 * Created by LiRan on 2015-12-06.
 */
public class Bird {

    /**
     * 鸟在屏幕高度的 2/3的位置
     */
    public static final float RADIO_POS_HEIGHT = 2 / 3F;

    /**
     * 鸟的宽度30dp
     */
    public static final int BIRD_SIZE = 30;

    /**
     * 鸟的横坐标
     */
    private int x;
    /**
     * 鸟的纵坐标
     */
    private int y;

    /**
     * 鸟的图片的宽度
     */
    private int mWidth;
    /**
     * 鸟的图片的高度
     */
    private int mheight;

    /**
     * 鸟的图片
     */
    private Bitmap bitmap;

    /**
     * 鸟的范围
     */
    private RectF rect = new RectF();

    public Bird(Context context, int gameWidth, int gameHeight, Bitmap bitmap) {

        this.bitmap = bitmap;

        //鸟的位置
        x = gameWidth / 2 - bitmap.getWidth() / 2;
        y = (int) (gameHeight * RADIO_POS_HEIGHT);

        //计算鸟的宽度和高度
        mWidth = DensityUtils.dp2px(context, BIRD_SIZE);
        //通过鸟本身的宽度和图片的宽度多一个对比，求出比例，然后让图片的高度*这个比例，就可以计算出鸟应该有的高度
        mheight = (int) (mWidth * 1.0f / bitmap.getWidth() * bitmap.getHeight());


    }


    /**
     * 绘制自己
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        rect.set(x, y, x + mWidth, y + mheight);
        canvas.drawBitmap(bitmap, null, rect, null);
    }




    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return mWidth;
    }


    public int getHeight() {
        return mheight;
    }


}
