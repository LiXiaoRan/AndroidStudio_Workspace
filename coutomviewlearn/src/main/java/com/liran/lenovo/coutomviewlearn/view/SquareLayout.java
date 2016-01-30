package com.liran.lenovo.coutomviewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 一个自定义ViewGroup，主要功能是强制其内的控件都变成正方形，并且可以设置排列方式，和每一行或者每一列最大控件数量
 * Created by LiRan on 2016-01-30.
 */
public class SquareLayout extends ViewGroup {

    private static final int ORIENTATION_HORIZONTAL = 0, ORIENTATION_VERTICAL = 1;// 排列方向的常量标识值
    private static final int DEFAULT_MAX_ROW = Integer.MAX_VALUE, DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;// 最大行列默认值


    /**
     * 最大行数
     */
    private int maxRow = DEFAULT_MAX_ROW;

    /**
     * 最大列数
     */
    private int maxColumn = DEFAULT_MAX_COLUMN;

    /**
     * 排列方向
     */
    private int mOrientation = ORIENTATION_HORIZONTAL;

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 声明临时变量存储父容器的期望值
         * 该值应该等于父容器的内边距加上所有子元素的测量宽高和外边距
         */
        int parentDesirWidth = 0;
        int parentDesirHeight = 0;

        //声明临时变量存储子元素的测量状态
        int childMeasurState = 0;

        //如果父容器中有子元素
        if (getChildCount() > 0) {

            //遍历子元素
            for (int i = 0; i < getChildCount(); i++) {
                //获取子元素
                View child = getChildAt(i);
                //如果该子元素没有以“不占用空间”的方式隐藏则表示其需要被测量计算
                if (child.getVisibility() != View.GONE) {

                    //测量子元素并且考虑外边距
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                    //比较子元素测量的宽高并取最大的值
                    int childMeasureSize = Math.max(child.getMeasuredWidth(), child.getMeasuredHeight());

                    //重新封装子元素测量规格
                    int childMeasureSpec = MeasureSpec.makeMeasureSpec(childMeasureSize, MeasureSpec.EXACTLY);

                    //重新测量子元素
                    child.measure(childMeasureSpec, childMeasureSpec);

                    //获取子元素的布局参数
                    MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                    //考虑外边距计算子元素的实际宽高
                    int childActualWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                    int childActualHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                    //如果为横向排列
                    if (mOrientation == ORIENTATION_HORIZONTAL) {
                        //累加子元素的实际宽度
                        parentDesirWidth += childActualWidth;
                        //获取子元素中高度的最大值
                        parentDesirHeight = Math.max(childActualHeight, parentDesirHeight);

                    }

                    //如果为纵向排列
                    if (mOrientation == ORIENTATION_VERTICAL) {
                        //累加子元素的实际高度
                        parentDesirHeight += childActualHeight;
                        //获取子元素中宽度的最大值
                        parentDesirWidth = Math.max(childActualWidth, parentDesirWidth);
                    }


                    //合并子元素的测量状态
                    childMeasurState = combineMeasuredStates(childMeasurState, child.getMeasuredState());
                }
            }

            //考虑父容器的内将其累加到期望值
            parentDesirWidth += getPaddingLeft() + getPaddingRight();
            parentDesirHeight += getPaddingTop() + getPaddingBottom();

            //比较父容器的期望值和安卓建议的最小值，并且取较大值
            parentDesirWidth = Math.max(parentDesirWidth, getSuggestedMinimumWidth());
            parentDesirHeight = Math.max(parentDesirHeight, getSuggestedMinimumHeight());

        }


        setMeasuredDimension(resolveSizeAndState(parentDesirWidth, widthMeasureSpec, childMeasurState),
                resolveSizeAndState(parentDesirHeight, heightMeasureSpec, childMeasurState << MEASURED_HEIGHT_STATE_SHIFT));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
