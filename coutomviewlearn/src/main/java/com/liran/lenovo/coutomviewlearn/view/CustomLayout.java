package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义viewgroup
 * Created by LiRan on 2016-01-30.
 */
public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //如果有子元素
        if (getChildCount() > 0) {

            //声明一个临时变量存储高度的倍增值
            int mutilHeight=0;

            //那么遍历子元素并对其进行定位布局
            for (int i = 0; i < getChildCount(); i++) {
                View child=getChildAt(i);
                child.layout(0,mutilHeight,child.getMeasuredWidth(),child.getMeasuredHeight()+mutilHeight);

                //改变高度倍增值
                mutilHeight+=child.getMeasuredHeight();
            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //如果有子元素
        if (getChildCount() > 0) {
            //那么对子元素进行测量
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
