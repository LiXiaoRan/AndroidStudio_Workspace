package com.example.lenovo.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    private RequestQueue requestQueue;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView;
    private ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);
        textView = (TextView) findViewById(R.id.textview);
        imageview= (ImageView) findViewById(R.id.imageview);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:

                VolleyHttpUtils.VolleyGet("http://m.weather.com.cn/data/101010100.html", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String o) {
                        Log.d("TAG", o);
                        textView.setText(o);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG", volleyError.getMessage(), volleyError);
                        textView.setText(volleyError.toString());
                    }
                }, requestQueue);
                break;

            case R.id.button2:
                Map<String, String> map = new HashMap<>();
                map.put("token", "f0bf906de836b81407967100");
                map.put("catid", "0");
                map.put("lat", "32.97082");
                map.put("lon", "112.559668");
                map.put("lon_range", "960");
                map.put("lat_range", "720");
                map.put("level", "15");

                VolleyHttpUtils.VolleyPost(MyConst.UrlPost, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", s);
                        textView.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG", volleyError.getMessage(), volleyError);
                        textView.setText(volleyError.toString());
                    }
                }, requestQueue, map);
                break;


            case R.id.button3:
                VolleyHttpUtils.VolleyGetJsonRequest(MyConst.UrlGetWeater, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("TAG", s);
                          textView.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG", volleyError.getMessage(), volleyError);
                        textView.setText(volleyError.toString());
                    }
                }, requestQueue);

                break;

            case R.id.button4:
                VolleyHttpUtils.VolleyImage(MyConst.UrlImage, 0, 0, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageview.setImageBitmap(bitmap);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("TAG", volleyError.getMessage(), volleyError);
                        textView.setText(volleyError.toString());
                    }
                },requestQueue);
                break;
        }


    }


}
