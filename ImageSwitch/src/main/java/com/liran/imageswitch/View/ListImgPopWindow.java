package com.liran.imageswitch.View;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.liran.imageswitch.Bean.FloderBean;
import com.liran.imageswitch.R;
import com.liran.imageswitch.Utils.ImageLoader;
import com.liran.imageswitch.Utils.MeasureUtil;
import com.liran.imageswitch.adapter.ViewHolder;
import com.liran.imageswitch.adapter.myBaseAdapter;

import java.util.List;
import java.util.Set;

/**
 * Created by Lenovo on 2015-09-27.
 */
public class ListImgPopWindow extends PopupWindow {

    private int mheight;
    private int mWidth;
    private View mConvertView;

    private ListView mListView;
    private myBaseAdapter listAdapter;

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

        initViews(context);
        initEvent();

    }

    private void initEvent() {

    }

    private void initViews(Context context) {
        mListView = (ListView) mConvertView.findViewById(R.id.lv_poplistview);
        initAdapter(context);
        mListView.setAdapter(listAdapter);
    }

    private void initAdapter(Context context) {
        listAdapter = new myBaseAdapter<FloderBean>(context, mDatas, null, R.layout.item_popwindow) {
            @Override
            public void convert(ViewHolder holder, FloderBean item, String mDirPath, Set<String> mSelectImg) {
                //重置
                holder.setImageViewResourse(R.id.iv_pop_item_img, R.mipmap.picture_no);

                ImageLoader.getInstance().loadImage(item.getFirstImagePath(), ((ImageView) holder.getView(R.id.iv_pop_item_img)));
                holder.setText(R.id.tv_pop_item_name, item.getName());
                holder.setText(R.id.tv_pop_item_count, item.getCount() + "");
            }
        };
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
        Toast.makeText(context, "mWidth= "+mWidth+" mheight= "+mheight, Toast.LENGTH_SHORT).show();
    }

}
