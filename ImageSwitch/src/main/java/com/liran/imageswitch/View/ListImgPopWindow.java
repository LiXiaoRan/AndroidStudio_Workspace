package com.liran.imageswitch.View;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.liran.imageswitch.Bean.FloderBean;
import com.liran.imageswitch.R;
import com.liran.imageswitch.Utils.ImageLoader;
import com.liran.imageswitch.Utils.MeasureUtil;
import com.liran.imageswitch.adapter.ViewHolder;
import com.liran.imageswitch.adapter.myBaseAdapter;

import java.util.List;
import java.util.Set;

/**
 * Created by  李冉 on 2015-09-27.
 */
public class ListImgPopWindow extends PopupWindow {

    private String TAG = "ListImgPopWindow";
    private int mheight;
    private int mWidth;
    private View mConvertView;

    private ListView mListView;
    private myBaseAdapter listAdapter;
    /**
     * listview的数据集
     */
    private List<FloderBean> mDatas;


    public interface OnDirSelectedListener {
        void onSelected(FloderBean floderBean);
    }

    public OnDirSelectedListener mOnDirSelectedListener;

    public void setOnDirSelectedListener(OnDirSelectedListener mOnDirSelectedListener) {
        this.mOnDirSelectedListener = mOnDirSelectedListener;
    }

    public ListImgPopWindow(Context context, List<FloderBean> mDatas) {
        Log.d(TAG, "ListImgPopWindow 构造方法");
        calWidthAndHeigth(context);
        mConvertView = LayoutInflater.from(context).inflate(R.layout.popwindow_main_layout, null);
        setContentView(mConvertView);
        this.mDatas = mDatas;
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
        //这样写是为了解耦，其他地方如果要监听listview的点击的话，直接通过回调就行了
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnDirSelectedListener != null) {
                    mOnDirSelectedListener.onSelected(mDatas.get(position));
                }
            }
        });

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
        Log.d(TAG, "calWidthAndHeigth 计算popWindow的宽度和高度");
        int size[] = MeasureUtil.getScreenWidth(context);
        mWidth = size[0];
        mheight = (int) (size[1] * 0.7);
    }

}
