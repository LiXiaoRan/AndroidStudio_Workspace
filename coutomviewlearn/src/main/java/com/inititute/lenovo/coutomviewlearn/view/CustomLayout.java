package com.inititute.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 一个完整的自定义viewgroup
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

        //获取当前viewgroup的内边距
        int parentPaddingLeft = getPaddingLeft();
        int parentPaddingTop = getPaddingTop();


        //如果有子元素
        if (getChildCount() > 0) {

            //声明一个临时变量存储高度的倍增值
            int mutilHeight = 0;

            //那么遍历子元素并对其进行定位布局
            for (int i = 0; i < getChildCount(); i++) {

                View child = getChildAt(i);

                CustomLayoutParams clp= (CustomLayoutParams) child.getLayoutParams();

                /**
                 * 通知子元素进行布局
                 * 此时考虑父容器内边距和子元素外边距的影响
                 */
                child.layout(parentPaddingLeft+clp.leftMargin, mutilHeight + parentPaddingTop+clp.topMargin, child.getMeasuredWidth() + parentPaddingLeft+clp.leftMargin, child.getMeasuredHeight() + mutilHeight + parentPaddingTop+clp.topMargin);

                //改变高度倍增值
                mutilHeight += child.getMeasuredHeight()+clp.topMargin+clp.bottomMargin;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //声明临时变量存储父容器的期望值
        int parentDesireWidth=0;
        int parentDesireHeight=0;


        //如果有子元素
        if (getChildCount() > 0) {

            for (int i = 0; i < getChildCount(); i++) {
                //获取子元素
                View child=getChildAt(i);
                //获取子元素的布局参数
                CustomLayoutParams customLayoutParams= (CustomLayoutParams) child.getLayoutParams();
                //测量子元素并且考虑外边距
                measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);

                //计算父容器的期望值
                parentDesireWidth+=child.getMeasuredWidth()+customLayoutParams.leftMargin+customLayoutParams.rightMargin;
                parentDesireHeight+=child.getMeasuredHeight()+customLayoutParams.topMargin+customLayoutParams.bottomMargin;
            }

            //考虑父容器的内边距
            parentDesireWidth+=getPaddingLeft()+getPaddingRight();
            parentDesireHeight+=getPaddingTop()+getPaddingBottom();

            parentDesireWidth=Math.max(parentDesireWidth,getSuggestedMinimumWidth());
            parentDesireHeight=Math.max(parentDesireHeight,getSuggestedMinimumHeight());

        }

        setMeasuredDimension(resolveSize(parentDesireWidth,widthMeasureSpec),resolveSize(parentDesireHeight,heightMeasureSpec));

    }


    //生成默认的布局参数
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    //生成布局参数，将布局参数包装成我们的。
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    //生成布局参数
    //从属性配置中生成我们的布局参数
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(),attrs);
    }


    //检查当前布局参数是否是我们定义的类型 在这code声明布局参数时常常用到
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    /**
     * 用来处理子控件的margin
     */
    public static class CustomLayoutParams extends MarginLayoutParams {

        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }

    }
}
