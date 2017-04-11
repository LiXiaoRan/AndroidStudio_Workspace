package com.liran.flabbybird.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.liran.flabbybird.R;
import com.liran.flabbybird.bean.Info_score;
import com.liran.flabbybird.utils.ConastClassUtil;
import com.liran.flabbybird.utils.MyApplication;
import com.liran.flabbybird.utils.StringUtil;
import com.liran.flabbybird.utils.ViewHolder;
import com.liran.flabbybird.utils.myBaseAdapter;
import com.orhanobut.logger.Logger;

import java.util.Collections;

/**
 * 排行榜页面
 * Created by LiRan on 2017-02-16.
 */

public class ChartsActivity extends BaseActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);


        addListData();//数据检索和填充

        Logger.d("排行榜页面从数据库中的数据数量为：" + ConastClassUtil.infoScoreList.size());
        Logger.d("排行榜页面从数据库中读取的数据是：" + StringUtil.infoListToString(ConastClassUtil.infoScoreList));

        listView = (ListView) findViewById(R.id.cast_listview);
        listView.setAdapter(myBaseAdapter);


    }

    /**
     * 填充数据和检索
     */
    private void addListData() {

        ConastClassUtil.infoScoreList = MyApplication.getDB().findAll(Info_score.class);
        Collections.sort(ConastClassUtil.infoScoreList);//排序
        Collections.reverse(ConastClassUtil.infoScoreList);
    }

    /**
     * 万能的适配器
     */
    private myBaseAdapter myBaseAdapter = new myBaseAdapter(MyApplication.mContext, R.layout.item_liatview_charts) {

        @Override
        public void convert(ViewHolder holder, Info_score item) {
            holder.setText(R.id.tv_username, item.getUsername());
            holder.setText(R.id.tv_score, item.getScore() + "");
            holder.setText(R.id.tv_time, item.getTime());
            Logger.d("适配器中的数据为：" + "name is " + item.getUsername() + " score is " + item.getScore());
        }
    };


}
