package com.liran.flabbybird;

import android.content.Context;
import android.graphics.Bitmap;

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
    private int heidht;

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

    private static Random random=new Random();


    public Pipe(Context context,int gameWidth,int gameHeigh,Bitmap mTop, Bitmap mBottom) {

        margin= (int) (gameHeigh*RADIO_BETWEEN_UP_DOWN);
        x=gameWidth;
        this.mTop = mTop;
        this.mBottom = mBottom;

        randomHeight(gameHeigh);

    }

    /**
     * 随机生成一个高度
     */
    private void randomHeight(int gameHeight) {
        heidht=random.nextInt((int) (gameHeight*(RADIO_MAX_HEIGHT-RADIO_MIN_HEIGHT)));
        heidht= (int) (heidht+gameHeight*RADIO_MIN_HEIGHT);
    }






}
