package com.liran.lenovo.calculator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lr on 2015-08-20.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }


    /**
     * 拿到一个viewholder对象
     */
    public static ViewHolder get(Context context, ViewGroup parent, int layoutId, int position, View convertview) {

        if (convertview == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertview.getTag();
    }

    /**
     * 通过控件Id从SparseArray中获取控件 没有则加入SparseArray
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @return View 当前的ConvertView
     */
    public View getmConvertView() {
        return mConvertView;
    }


    /**
     * 设置listview中的Textview显示的文本
     *
     * @param id   控件Id     int
     * @param text 控件应该显示的文本 string
     */
    public void setText(int id, String text) {
        TextView textView = this.getView(id);
        textView.setText(text);
    }


    /**
     * 设置listview中的ImageView显示的文本
     * @param viewId 控件Id     int
     * @param bm 控件应该显示的图片 Bitmap
     */
    public void setImageViewBitmap(int viewId,Bitmap bm){
        ImageView imageView=getView(viewId);
        imageView.setImageBitmap(bm);
    }

    /**
     * 设置图片通过id
     * @param viewId 控件Id
     * @param resId 图片ID
     */
    public void setImageViewResourse(int viewId,int resId){
        ImageView imageView=getView(viewId);
        imageView.setImageResource(resId);
    }

    /**
     * 设置listview中的ImageView显示的文本
     * @param viewId 控件Id     int
     * @param bm 控件应该显示的图片 Bitmap
     */
    public void setImageButtonBitmap(int viewId,Bitmap bm){
        ImageButton imageButton=getView(viewId);
        imageButton.setImageBitmap(bm);
    }

    /**
     * 设置图片通过id
     * @param viewId 控件Id
     * @param resId 图片ID
     */
    public void setImageButtonResourse(int viewId,int resId){
        ImageButton imageButton=getView(viewId);
        imageButton.setImageResource(resId);
    }




}
