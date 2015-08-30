package com.example.lenovo.myapplication;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lr on 2015-08-30.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue;
    public static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext=getApplicationContext();
        requestQueue= Volley.newRequestQueue(AppContext);
    }

    public static RequestQueue getRequestQueue() {
        if(requestQueue==null){
            //一开始这里总是报空指针异常，其实是我忘了在清单文件中加入这个application了
            requestQueue= Volley.newRequestQueue(AppContext);
        }
        return requestQueue;

    }
}
