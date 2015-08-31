package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lenovo.myapplication.model.info;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements  AdapterView.OnItemClickListener {


    private ListView listview;
    private List<info> list;
    private CoordinatorLayout coorlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();

        coorlayout= (CoordinatorLayout) findViewById(R.id.coorlayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //使toolbar替换actionbar

        for(int i=0;i<20;i++){
            info info=new info("失误失误"+i,"这个字好小阿"+i);
            list.add(info);
        }

        listview=(ListView)findViewById(R.id.listview);
        listview.setAdapter(new myBaseAdapter<info>(getApplicationContext(),list,R.layout.listviewitem) {

            @Override
            public void convert(ViewHolder holder, info item) {
               /* TextView tv=holder.getView(R.id.textview1);
                tv.setText(item);*/
                holder.setText(R.id.textview1,item.getTitle());
                holder.setText(R.id.desc,item.getDesc());

            }
        });

        listview.setOnItemClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



//        textView.setOnClickListener(new onc);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         final Snackbar snackbar = Snackbar.make(view,"测试弹出提示"+position,Snackbar.LENGTH_LONG);
//       snackbar.setActionTextColor(R.color.background_material_light);
        snackbar.show();
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();

            }
        });
    }
}
