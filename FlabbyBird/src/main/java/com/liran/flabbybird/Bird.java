package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;

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
     * 鸟的高度30dp
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
     * 鸟的宽度
     */
    private int mWidth;
    /**
     * 鸟的高度
     */
    private int mheight;

    /**
     * 鸟的图片
     */
    private Bitmap bitmap;

    /**
     * 鸟的范围
     */
    private RectF rect=new RectF();

    public Bird(Context context,int gameWidth, int gameHeight, Bitmap bitmap) {

        this.bitmap = bitmap;

        //鸟的位置
        x=gameWidth/2-bitmap.getWidth()/2;
        y= (int) (gameHeight*RADIO_POS_HEIGHT);



    }
}
