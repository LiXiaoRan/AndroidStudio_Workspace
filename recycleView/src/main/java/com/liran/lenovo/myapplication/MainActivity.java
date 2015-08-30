package com.liran.lenovo.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<String> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        adapter = new HomeAdapter(MainActivity.this, mDatas);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));//这里用线性宫格显示 类似于grid view
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//      recyclerView.addItemDecoration();
        adapter.setonItemOnClickListener(new HomeAdapter.onItemOnClickListener() {
            @Override
            public void onClickListener(View itemView, int position) {
                Toast.makeText(MainActivity.this,"onClickListener--->position="+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickListener(View itemView, int position) {
                Toast.makeText(MainActivity.this,"onLongClickListener--->position="+position,Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_add:
                adapter.addData(1);
                break;
            case R.id.action_remove:
                adapter.removeData(1);
                break;
        }

        return true;
    }
}
