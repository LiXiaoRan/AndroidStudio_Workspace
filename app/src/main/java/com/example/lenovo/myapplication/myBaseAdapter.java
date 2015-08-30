package com.example.lenovo.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Lenovo on 2015-08-20.
 */
public abstract class myBaseAdapter<T> extends BaseAdapter{

    protected LayoutInflater inflater;
    protected Context mcontext;
    protected List<T> mDatas;  //封装的数据
    protected final int itemLayoutId;  //listview的每个Item布局的Id 就是viewholder中的layoutId 要使用LayoutInflater加载

    public myBaseAdapter( Context mcontext, List<T> mDatas, int itemLayoutId) {
//        this.inflater = inflater;
        this.mcontext = mcontext;
        this.mDatas = mDatas;
        this.itemLayoutId = itemLayoutId;
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

    public abstract void convert(ViewHolder holder,T item);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=getViewHolder(position,parent,convertView);
        convert(viewHolder,getItem(position));
        return viewHolder.getmConvertView();
    }


    private ViewHolder getViewHolder(int position,ViewGroup parent,View convertView){
       return ViewHolder.get(mcontext,parent,itemLayoutId,position,convertView);
    }

}
