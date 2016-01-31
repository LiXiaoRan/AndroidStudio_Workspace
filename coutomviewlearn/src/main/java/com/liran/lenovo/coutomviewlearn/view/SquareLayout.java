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
    private int mOrientation = ORIENTATION_VERTICAL;

    public SquareLayout(Context context) {
        super(context);
        init();
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //初始化最大行列数
        maxRow =2; maxColumn = 3;

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

            //声明两个一位数组存储子元素宽高数据
            int[] childWidths = new int[getChildCount()];
            int[] childHeihts = new int[getChildCount()];

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

                    //考虑外边距计算子元素的实际宽高,并且将数据存入数组
                    childWidths[i] = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
                    childHeihts[i] = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                    //合并子元素的测量状态
                    childMeasurState = combineMeasuredStates(childMeasurState, child.getMeasuredState());

                }

            }


            // 声明临时变量存储行/列宽高
            int indexMultiWidth = 0, indexMultiHeight = 0;


            //如果为横向排列
            if (mOrientation == ORIENTATION_HORIZONTAL) {

                /* //累加子元素的实际宽度
                parentDesirWidth += childActualWidth;
                //获取子元素中高度的最大值
                parentDesirHeight = Math.max(childActualHeight, parentDesirHeight);*/



                /*
                 * 如果子元素数量大于限定值则进行折行计算
                 */
                if (getChildCount() > maxColumn) {

                    // 计算产生的行数
                    int row = getChildCount() / maxColumn;

                    // 计算余数
                    int remainder = getChildCount() % maxColumn;

                    // 声明临时变量存储子元素宽高数组下标值
                    int index = 0;

                    /*
                     * 遍历数组计算父容器期望宽高值
                     */
                    for (int x = 0; x < row; x++) {
                        for (int y = 0; y < maxColumn; y++) {
                            // 单行宽度累加
                            indexMultiWidth += childWidths[index];

                            // 单行高度取最大值
                            indexMultiHeight = Math.max(indexMultiHeight, childHeihts[index++]);
                        }
                        // 每一行遍历完后将该行宽度与上一行宽度比较取最大值,从而得到父控件宽度的期望值
                        parentDesirWidth = Math.max(parentDesirWidth, indexMultiWidth);

                        // 每一行遍历完后累加各行高度
                        parentDesirHeight += indexMultiHeight;

                        // 重置参数
                        indexMultiWidth = indexMultiHeight = 0;
                    }

                    //如果有余数表示有子元素未能占据一行
                    if (remainder != 0) {
                         /*
                         * 遍历剩下的这些子元素将其宽高计算到父容器期望值
                         */
                        for (int i = getChildCount() - remainder; i < getChildCount(); i++) {
                            indexMultiWidth += childWidths[i];
                            indexMultiHeight = Math.max(indexMultiHeight, childHeihts[i]);
                        }
                        parentDesirWidth = Math.max(parentDesirWidth, indexMultiWidth);
                        parentDesirHeight += indexMultiHeight;
                        indexMultiWidth = indexMultiHeight = 0;
                    }
                }
                //如果子元素数量还没有限制值大那么直接计算即可不须折行
                else {
                    for (int i = 0; i < getChildCount(); i++) {
                        // 累加子元素的实际高度
                        parentDesirHeight += childHeihts[i];
                        // 获取子元素中宽度最大值
                        parentDesirWidth = Math.max(parentDesirWidth, childWidths[i]);
                    }
                }
            }


            //如果为纵向排列
            else if (mOrientation == ORIENTATION_VERTICAL) {

                if (getChildCount() > maxRow) {
                    int column = getChildCount() / maxRow;
                    int remainder = getChildCount() % maxRow;
                    int index = 0;

                    for (int x = 0; x < column; x++) {
                        for (int y = 0; y < maxRow; y++) {
                            indexMultiHeight += childHeihts[index];
                            indexMultiWidth = Math.max(indexMultiWidth, childWidths[index++]);
                        }
                        //通过比较上一列的累加高度，求出所有的最大高度，座位父控件的期望高度
                        parentDesirHeight = Math.max(parentDesirHeight, indexMultiHeight);
                        //累加每一列的最大宽度，就可以得到父控件的期望宽度
                        parentDesirWidth += indexMultiWidth;
                        indexMultiWidth = indexMultiHeight = 0;
                    }


                    if (remainder != 0) {
                        for (int i = getChildCount() - remainder; i < getChildCount(); i++) {
                            indexMultiHeight += childHeihts[i];
                            indexMultiWidth = Math.max(indexMultiHeight, childWidths[i]);
                        }
                        parentDesirHeight = Math.max(parentDesirHeight, indexMultiHeight);
                        parentDesirWidth += indexMultiWidth;
                        indexMultiWidth = indexMultiHeight = 0;
                    }
                } else {
                    for (int i = 0; i < getChildCount(); i++) {
                        // 累加子元素的实际宽度
                        parentDesirWidth += childWidths[i];

                        // 获取子元素中高度最大值
                        parentDesirHeight = Math.max(parentDesirHeight, childHeihts[i]);
                    }
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

        if (getChildCount() > 0) {
            //声明临时变量存储宽高倍增值
            int multi = 0;

            //指数倍增值
            int indexMulti = 1;

            // 声明临时变量存储行/列宽高
            int indexMultiWidth = 0, indexMultiHeight = 0;

            // 声明临时变量存储行/列临时宽高
            int tempHeight = 0, tempWidth = 0;


            //遍历子元素
            for (int i = 0; i < getChildCount(); i++) {

                View child = getChildAt(i);

                //如果该子元素没有以“不占用空间”的方式隐藏则表示其需要被测量计算
                if (child.getVisibility() != GONE) {

                    //获取子元素的布局参数
                    MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();

                    //获取控件尺寸
                    int childActulSize = child.getMeasuredWidth();

                    //如果为横向排列
                    if (mOrientation == ORIENTATION_HORIZONTAL) {

                         /*
                         * 如果子元素数量比限定值大
                         */
                        if (getChildCount() > maxColumn) {
                            /*
                             * 根据当前子元素进行布局
                             */
                            if (i < maxColumn * indexMulti) {
                                child.layout(getPaddingLeft() + mlp.leftMargin + indexMultiWidth, getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                        childActulSize + getPaddingLeft() + mlp.leftMargin + indexMultiWidth, childActulSize + getPaddingTop()
                                                + mlp.topMargin + indexMultiHeight);
                                indexMultiWidth += childActulSize + mlp.leftMargin + mlp.rightMargin;
                                tempHeight = Math.max(tempHeight, childActulSize) + mlp.topMargin + mlp.bottomMargin;

                                /*
                                 * 如果下一次遍历到的子元素下标值大于限定值
                                 */
                                if (i + 1 >= maxColumn * indexMulti) {
                                    // 那么累加高度到高度倍增值
                                    indexMultiHeight += tempHeight;

                                    // 重置宽度倍增值
                                    indexMultiWidth = 0;

                                    // 增加指数倍增值
                                    indexMulti++;
                                }
                            }
                        } else {
                            // 确定子元素左上、右下坐标
                            child.layout(getPaddingLeft() + mlp.leftMargin + multi, getPaddingTop() + mlp.topMargin, childActulSize
                                    + getPaddingLeft() + mlp.leftMargin + multi, childActulSize + getPaddingTop() + mlp.topMargin);

                            // 累加倍增值
                            multi += childActulSize + mlp.leftMargin + mlp.rightMargin;
                        }


                        /*   //确定子元素的左上右下坐标
                        child.layout(getPaddingLeft() + mlp.leftMargin + multi, getPaddingTop() + mlp.topMargin,
                                childActulSize + getPaddingLeft() + mlp.leftMargin + multi, childActulSize + getPaddingTop()
                                        + mlp.topMargin);
                        //累加倍增值
                        multi += childActulSize + mlp.leftMargin + mlp.rightMargin;*/
                    }

                    //如果为纵向排列
                    if (mOrientation == ORIENTATION_VERTICAL) {


                        if (getChildCount() > maxRow) {
                            if (i < maxRow * indexMulti) {
                                child.layout(getPaddingLeft() + mlp.leftMargin + indexMultiWidth, getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                        childActulSize + getPaddingLeft() + mlp.leftMargin + indexMultiWidth, childActulSize + getPaddingTop()
                                                + mlp.topMargin + indexMultiHeight);
                                indexMultiHeight += childActulSize + mlp.topMargin + mlp.bottomMargin;
                                tempWidth = Math.max(tempWidth, childActulSize) + mlp.leftMargin + mlp.rightMargin;
                                if (i + 1 >= maxRow * indexMulti) {
                                    indexMultiWidth += tempWidth;
                                    indexMultiHeight = 0;
                                    indexMulti++;
                                }
                            }
                        } else {
                            // 确定子元素左上、右下坐标  
                            child.layout(getPaddingLeft() + mlp.leftMargin, getPaddingTop() + mlp.topMargin + multi, childActulSize
                                    + getPaddingLeft() + mlp.leftMargin, childActulSize + getPaddingTop() + mlp.topMargin + multi);

                            // 累加倍增值  
                            multi += childActulSize + mlp.topMargin + mlp.bottomMargin;
                        }  
                      
                      
                       /* //确定子元素的左上右下坐标
                        child.layout(getPaddingLeft() + mlp.leftMargin, getPaddingTop() + mlp.topMargin + multi, childActulSize +
                                +mlp.leftMargin + getPaddingLeft(), getPaddingTop() + mlp.topMargin + childActulSize + multi);

                        //累加倍增值
                        multi += childActulSize + mlp.topMargin + mlp.bottomMargin;*/
                    }
                }

            }


        }


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
