package com.liran.flabbybird.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.liran.flabbybird.bean.Info_score;
import com.orhanobut.logger.Logger;

/**
 * Created by lr on 2015-08-20.
 */
public abstract class myBaseAdapter extends BaseAdapter{

    protected LayoutInflater inflater;
    protected Context mcontext;
//    protected static List<Info_score> mDatas;  //封装的数据
    protected final int itemLayoutId;  //listview的每个Item布局的Id 就是viewholder中的layoutId 要使用LayoutInflater加载

    public myBaseAdapter( Context mcontext,  int itemLayoutId) {
//        this.inflater = inflater;
        this.mcontext = mcontext;
//        this.mDatas = mDatas;
        this.itemLayoutId = itemLayoutId;

        Logger.d("在适配器构造函数中得到的数据是："+StringUtil.infoListToString(ConastClassUtil.infoScoreList));
    }

    @Override
    public int getCount() {
        return ConastClassUtil.infoScoreList.size();
    }

    @Override
    public Info_score getItem(int position) {
        return ConastClassUtil.infoScoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract void convert(ViewHolder holder,Info_score item);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=getViewHolder(position,parent,convertView);
        Logger.d("getView执行了");
        convert(viewHolder,getItem(position));
        return viewHolder.getmConvertView();
    }


    private ViewHolder getViewHolder(int position,ViewGroup parent,View convertView){
       return ViewHolder.get(mcontext,parent,itemLayoutId,position,convertView);
    }

}
