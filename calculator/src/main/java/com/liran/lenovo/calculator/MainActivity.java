package com.liran.lenovo.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.liran.lenovo.calculator.adapter.ViewHolder;
import com.liran.lenovo.calculator.adapter.myBaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 李冉
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private String TAG = "MainActivity";

    private GridView gridView;
    private myBaseAdapter myAdapter;
    private List<String> list;


    private Button equal;

    private TextView tv_c;
    private TextView tv_del;
    private TextView tv_division;
    private TextView tv_MUL;
    private TextView tv_reuce;
    private TextView tv_sum;
    private TextView tv_zero;
    private TextView tv_point;


    private TextView text;

//    private Button cal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();

    }


    /**
     * 初始化视图
     */
    private void initView() {

        gridView = (GridView) findViewById(R.id.grideview);
        equal = (Button) findViewById(R.id.btn_equal);
        tv_c = (TextView) findViewById(R.id.tv_C);
        tv_del = (TextView) findViewById(R.id.tv_del);
        tv_division = (TextView) findViewById(R.id.tv_division);
        tv_sum = (TextView) findViewById(R.id.tv_sum);
        tv_MUL = (TextView) findViewById(R.id.tv_MUL);
        tv_zero = (TextView) findViewById(R.id.tv_zero);
        tv_reuce = (TextView) findViewById(R.id.tv_reuce);
        tv_point = (TextView) findViewById(R.id.tv_point);
        text = (TextView) findViewById(R.id.text);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        gridView.setOnItemClickListener(this);
        equal.setOnClickListener(this);
        tv_c.setOnClickListener(this);
        tv_del.setOnClickListener(this);
        tv_division.setOnClickListener(this);
        tv_sum.setOnClickListener(this);
        tv_MUL.setOnClickListener(this);
        tv_zero.setOnClickListener(this);
        tv_reuce.setOnClickListener(this);
        tv_point.setOnClickListener(this);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<>();
        initList();
        myAdapter = new myBaseAdapter<String>(getApplicationContext(), list, R.layout.gride_item) {
            @Override
            public void convert(ViewHolder holder, final String item) {
                holder.setText(R.id.btn_number_item, item);
            }
        };
        gridView.setAdapter(myAdapter);

    }

    private void initList() {
        for (int i = 1; i <= 9; i++) {
            list.add(i + "");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        text.setText(text.getText() + list.get(position));

    }

    @Override
    public void onClick(View v) {

        String str = text.getText().toString();
        switch (v.getId()) {
            case R.id.tv_C:
                text.setText("");
                break;
            case R.id.tv_del:
                if (str != null && !str.equals(""))
                    text.setText(str.substring(0, str.length() - 1));
                break;
            case R.id.tv_division:
                text.setText(str + "÷");
                break;
            case R.id.tv_sum:
                text.setText(str + "+");
                break;
            case R.id.tv_MUL:
                text.setText(str + "x");
                break;
            case R.id.tv_point:
                text.setText(str + ".");
                break;
            case R.id.tv_zero:
                text.setText(str + "0");
                break;
            case R.id.tv_reuce:
                text.setText(str + "-");
                break;
            case R.id.btn_equal:
                if (str != null && !str.equals("")) {
                    /*List<String> expression = splitStr(str);
                    text.setText(str + "=\n");
                    for (String s : expression) {
                        Log.d(TAG, "onClick: " + s);
                    }*/
                    String str1 = str.replace("÷", "/");
                    String str2 = str1.replace("x", "*");

                    double result = 0;
                    try {
                        result = SizheTool.jisuanStr(str2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    text.setText(str + "=\n");

                    text.setText(result + "");
                }
                break;

        }

    }


    public static List<String> splitStr(String string) {
        List<String> listSplit = new ArrayList<String>();
        Matcher matcher = Pattern.compile("\\-?\\d+(\\.\\d+)?|[*/()]|\\-")
                .matcher(string);// 用正则拆分成每个元素
        while (matcher.find()) {
            // System.out.println(matcher.group(0));
            listSplit.add(matcher.group(0));
        }
        return listSplit;
    }


}
