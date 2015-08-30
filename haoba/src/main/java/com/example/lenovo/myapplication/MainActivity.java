package com.example.lenovo.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.myapplication.adapter.TabPagerAdapter;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewpager;

    private Button button;

    private static final String[] DATA = {"AndroidStudio", "哈哈哈", "Studio", "Android", "Java", "Design"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.button);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager= (ViewPager) findViewById(R.id.tab_vp);

        //viewpager的适配器
        TabPagerAdapter adapter=new TabPagerAdapter(DATA,this);

        //设置tablayout为滑动模式
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //设置viewpager的适配器
        viewpager.setAdapter(adapter);


//        设置正常颜色和选中颜色
      /*  void	setTabTextColors(int normalColor, int selectedColor)
        Sets the text colors for the different states (normal, selected) used for the tabs.*/

//        tablayout.setTabTextColors();

        //给tablayout设置viewpager
        tablayout.setupWithViewPager(viewpager);


        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this,"tab is "+tab.getText().toString(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        /*TabLayout.Tab tab1 = tablayout.newTab().setText("table1");
        TabLayout.Tab tab2 = tablayout.newTab().setText("table2");
        TabLayout.Tab tab3 = tablayout.newTab().setText("table3");
        tablayout.addTab(tab1);
        tablayout.addTab(tab2);
        tablayout.addTab(tab3);*/



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tablayout.setScrollPosition(0, 1f, true);  //跳转到指定位置的tab 没有动画效果  这里的第二个参数最好是0到1 否则可能会计算错误
//                tab3.select();  //跳转到指定位置的tab3 有动画效果
                /* 下面这一段运用了反射 来控制tablayout的切换*/
                Class clz = tablayout.getClass();
                try {
                    Method animateToTab = clz.getDeclaredMethod("animateToTab", new Class[]{int.class});
                    animateToTab.setAccessible(true);
                    animateToTab.invoke(tablayout, 2);    //这样写可以确保滚动到第指定的tab，并且有动画效果

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
