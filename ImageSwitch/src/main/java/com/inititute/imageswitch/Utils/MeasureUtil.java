package com.inititute.imageswitch.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 非常实用的工具类
 * Created by lr on 2015-09-01.
 */
public class MeasureUtil {


    /**
     *获取屏幕尺寸的像素  包括标题栏和状态栏
     * @param context
     * @return int size[2]  size[0] 宽  size[1] 高
     */
    public static int [] getScreenWidth(Context context){
        int size[]=new int[2];
        DisplayMetrics displayMetrics=new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        size[0]=displayMetrics.widthPixels;
        size[1]=displayMetrics.heightPixels;

        return size;
    }


    /**
     * 获取状态栏高度
     * @param context
     * @return statusBarHeight int
     */
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
