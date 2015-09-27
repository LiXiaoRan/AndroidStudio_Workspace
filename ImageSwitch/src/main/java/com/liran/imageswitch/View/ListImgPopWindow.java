package com.liran.imageswitch.View;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.liran.imageswitch.Bean.FloderBean;
import com.liran.imageswitch.R;
import com.liran.imageswitch.Utils.MeasureUtil;

import java.util.List;

/**
 * Created by Lenovo on 2015-09-27.
 */
public class ListImgPopWindow extends PopupWindow {

    private int mheight;
    private int mWidth;
    private View mConvertView;
    private ListView mListView;

    private List<FloderBean> mDatas;


    public void ListImgPopWindow(Context context, List<FloderBean> mDatas) {
        calWidthAndHeigth(context);
        mConvertView = LayoutInflater.from(context).inflate(R.layout.popwindow_main_layout, null);
        setContentView(mConvertView);
        setWidth(mWidth);
        setHeight(mheight);
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(true); //外部可以点击
        setBackgroundDrawable(new BitmapDrawable());  //点击外部后消失


        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }

                return false;
            }
        });

        initViews();
        initEvent();

    }

    private void initEvent() {

    }

    private void initViews() {


    }

    /**
     * 计算popWindow的宽度和高度
     *
     * @param context Context
     */
    private void calWidthAndHeigth(Context context) {
        int size[] = MeasureUtil.getScreenWidth(context);
        mWidth = size[0];
        mheight = (int) (size[1] * 0.7);

    }

}
