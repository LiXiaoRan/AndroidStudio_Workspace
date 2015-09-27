package com.liran.imageswitch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lr on 2015-08-20.
 */
public abstract class myBaseAdapter<T> extends BaseAdapter {

    private static Set<String> mSelectImg = new HashSet<String>();
    protected LayoutInflater inflater;
    protected Context mcontext;
    protected List<T> mDatas;  //封装的数据
    protected final int itemLayoutId;  //listview的每个Item布局的Id 就是viewholder中的layoutId 要使用LayoutInflater加载
    protected String mDirPath;

    public myBaseAdapter(Context mcontext, List<T> mDatas, String dirPath, int itemLayoutId) {
//        this.inflater = inflater;
        this.mcontext = mcontext;
        this.mDatas = mDatas;
        this.itemLayoutId = itemLayoutId;
        this.mDirPath = dirPath;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract void convert(final ViewHolder holder, T item, String mDirPath, Set<String> mSelectImg);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, parent, convertView);
        convert(viewHolder, getItem(position), mDirPath,mSelectImg);
        return viewHolder.getmConvertView();
    }


    private ViewHolder getViewHolder(int position, ViewGroup parent, View convertView) {
        return ViewHolder.get(mcontext, parent, itemLayoutId, position, convertView);
    }

}
