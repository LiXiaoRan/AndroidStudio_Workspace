package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiRan on 2016-01-10.
 */
public class BitmapMeshView extends View {

    public static final int WIDTH=19; //横向分割成的网格数量
    public static final int HEIGHT=19; //纵向分割成的网格数量
    public static final int COUNT=(WIDTH+1)*(HEIGHT+1);


    public BitmapMeshView(Context context) {
        super(context);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
