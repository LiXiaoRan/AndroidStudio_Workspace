package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lenovo on 2015-08-22.
 */
public class TabPagerAdapter extends PagerAdapter {

    private String[] mdata;
    private Context mcontext;
    public TabPagerAdapter(String[] data,Context Context){
        mdata=data;
        mcontext=Context;
    }

    @Override
    public int getCount() {
        return mdata.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView tvContent = new TextView(mcontext);
        tvContent.setText(mdata[position]);
        tvContent.setGravity(Gravity.CENTER);
        container.addView(tvContent,
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        return tvContent;    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mdata[position];
    }
}
