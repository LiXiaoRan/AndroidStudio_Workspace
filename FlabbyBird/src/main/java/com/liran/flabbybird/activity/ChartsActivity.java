package com.liran.flabbybird.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.liran.flabbybird.R;
import com.liran.flabbybird.bean.Info_score;
import com.liran.flabbybird.utils.MyApplication;
import com.liran.flabbybird.utils.ViewHolder;
import com.liran.flabbybird.utils.myBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 排行榜页面
 * Created by LiRan on 2017-02-16.
 */

public class ChartsActivity extends BaseActivity {


    private static List<Info_score> infoScoreList=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);


        //添加的假数据
        for (int i = 0; i < 10; i++) {
            Info_score info_score = new Info_score();
            infoScoreList.add(info_score);
        }




        listView = (ListView) findViewById(R.id.cast_listview);
        listView.setAdapter(myBaseAdapter);


    }

    /**
     * 万能的适配器
     */
    private myBaseAdapter myBaseAdapter = new myBaseAdapter<Info_score>(MyApplication.mContext, infoScoreList, R.layout.item_liatview_charts) {

        @Override
        public void convert(ViewHolder holder, Info_score item) {
            holder.setText(R.id.tv_username, item.getUsername());
            holder.setText(R.id.tv_score, item.getScore() + "");
            holder.setText(R.id.tv_time, item.getTime());
        }
    };


}
