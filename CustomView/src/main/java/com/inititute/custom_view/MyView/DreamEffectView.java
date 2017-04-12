package com.inititute.custom_view.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.inititute.custom_view.R;
import com.inititute.custom_view.Utils.MeasureUtil;

/**
 * 人像美化
 * Created by LiRan on 2015-12-29.
 */
public class DreamEffectView extends View {

    private Paint mBitmapPaint, mShaderPaint;//位图画笔,shader画笔
    private Bitmap mBitmap, mDarkBitmap;//源图的Bitmap和我们自己画的暗角Bitmap
    private PorterDuffXfermode xfermode;//图像混合模式
    private int x, y;// 位图起点坐标
    private int screenW, screenH;// 屏幕宽高

    public DreamEffectView(Context context) {
        super(context);
        initRes(context);
        initPaint();
    }

    public DreamEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
        initPaint();
    }

    public DreamEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRes(context);
        initPaint();
    }

    private void initRes(Context context) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gir2l);

        //实例化混合模式 采用滤镜模式
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        int screen[] = MeasureUtil.getScreenWidth(context);

        screenW = screen[0];
        screenH = screen[1];

        x = screenW / 2 - mBitmap.getWidth() / 2;
        y = screenH / 2 - mBitmap.getHeight() / 2;

    }

    private void initPaint() {
        // 实例化画笔
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 去饱和、提亮、色相矫正
        mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F, 0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0, 0, 1, 0}));


        mShaderPaint = new Paint();
        //源图的Bitmap和我们自己画的暗角Bitmap
        mDarkBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 将该暗角Bitmap注入Canvas
        Canvas canvas = new Canvas(mDarkBitmap);
        //计算径向渐变的半径
        float radius = canvas.getHeight() * (2f / 3f);
        RadialGradient radialGradient = new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F, radius, new int[]{0, 0, 0xAA000000}, new float[]{0F, 0.7F, 1.0F}, Shader.TileMode.CLAMP);

        // 实例化一个矩阵
        Matrix matrix = new Matrix();
        // 设置矩阵的缩放
        matrix.setScale(canvas.getWidth() / (radius * 2F), 1.0F);
        // 设置矩阵的预平移
        matrix.preTranslate(((radius * 2F) - canvas.getWidth()) / 2F, 0);
        // 将该矩阵注入径向渐变
        radialGradient.setLocalMatrix(matrix);


        // 设置径向渐变，渐变中心当然是图片的中心也是屏幕中心，渐变半径我们直接拿图片的高度但是要稍微小一点
        // 中心颜色为透明而边缘颜色为黑色
        mShaderPaint = new Paint();
//        mShaderPaint.setShader(new RadialGradient(screenW / 2, screenH / 2, mBitmap.getHeight() * 7 / 8, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP));
        mShaderPaint.setShader(radialGradient);

        // 绘制矩形
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mShaderPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);


        //新建图层
        int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);


        //绘制混合颜色
        canvas.drawColor(0xcc1c093e);


        mBitmapPaint.setXfermode(xfermode);

        canvas.drawBitmap(mBitmap, x, y, mBitmapPaint);


        mBitmapPaint.setXfermode(null);

        canvas.restoreToCount(sc);

        canvas.drawRect(x, y, x + mBitmap.getWidth(), y + mBitmap.getHeight(), mShaderPaint);

    }
}
