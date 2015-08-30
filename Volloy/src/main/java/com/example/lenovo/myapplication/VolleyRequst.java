package com.example.lenovo.myapplication;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.lenovo.myapplication.insterface.VolletInterface;

import java.util.Map;

/**
 * Created by Lenovo on 2015-08-30.
 */
public class VolleyRequst {


    private static Context mcContext;

    private static StringRequest stringRequest;

    /**
     * 封装的get方法
     * @param context
     * @param Url
     * @param tag
     * @param vinterface
     */
    public static void RequstGet(Context context, String Url, String tag, VolletInterface vinterface) {
        MyApplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, Url, vinterface.loadListener(), vinterface.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();
    }


    /**
     * 封装的post方法
     * @param context
     * @param Url
     * @param tag
     * @param vif
     */
    public static void RequstPost(Context context, String Url, String tag, VolletInterface vif,final Map<String,String> parmers) {
        MyApplication.getRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, Url, vif.loadListener(), vif.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return parmers;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();
    }

}
