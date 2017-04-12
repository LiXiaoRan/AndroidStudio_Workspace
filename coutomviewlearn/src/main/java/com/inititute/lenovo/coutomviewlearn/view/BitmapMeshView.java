package com.inititute.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.inititute.lenovo.coutomviewlearn.R;

/**
 * Created by LiRan on 2016-01-10.
 */
public class BitmapMeshView extends View {

    private static final int WIDTH = 19; //横向分割成的网格数量
    private static final int HEIGHT = 19; //纵向分割成的网格数量
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    private Bitmap mBitmap;//位图资源

    private float[] verts;//交点的坐标数组

    public BitmapMeshView(Context context) {
        super(context);
        init();
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gir2l);


        verts = new float[COUNT * 2];


        /**
         * 生成各个交点的坐标
         */

  /*      int index = 0;
        float multiple = mBitmap.getWidth();
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = mBitmap.getHeight() * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
//                float fx =mBitmap.getWidth()*x/WIDTH;
                float fx = mBitmap.getWidth() * x / WIDTH + ((HEIGHT - y) * 1.0F / HEIGHT * multiple);
                setXY(fx, fy, index);
                index += 1;
            }*/


        int index = 0;
        float multipleY = mBitmap.getHeight() / HEIGHT;
        float multipleX = mBitmap.getWidth() / WIDTH;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = multipleY * y;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = multipleX * x;

                setXY(fx, fy, index);

                if (5 == y) {
                    if (8 == x) {
                        setXY(fx - multipleX, fy - multipleY, index);
                    }
                    if (9 == x) {
                        setXY(fx + multipleX, fy - multipleY, index);
                    }
                }
                if (6 == y) {
                    if (8 == x) {
                        setXY(fx - multipleX, fy + multipleY, index);
                    }
                    if (9 == x) {
                        setXY(fx + multipleX, fy + multipleY, index);
                    }
                }

                index += 1;


            }


        }


    }

    private void setXY(float fx, float fy, int index) {
        verts[index * 2 + 0] = fx;
        verts[index * 2 + 1] = fy;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制网格位图
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }
}
