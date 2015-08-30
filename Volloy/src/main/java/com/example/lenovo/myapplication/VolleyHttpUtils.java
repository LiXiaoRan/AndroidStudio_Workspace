package com.example.lenovo.myapplication;

import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * 封装一个基本的操作
 * Created by 李冉 | lr on 2015-08-25.
 */
public class VolleyHttpUtils {


    /**
     * 在外部进行监听的volley Get
     * @param url
     * @param reslistener
     * @param errorListener
     * @param queue
     */
    public static void VolleyGet(String url,Response.Listener reslistener,Response.ErrorListener errorListener,RequestQueue queue){

        StringRequest stringRequest=new StringRequest(url,reslistener,errorListener);
        queue.add(stringRequest);
//        MyApplication.getRequestQueue().start();
    }




    /**
     * 在外部进行监听的volley Post
     * @param url
     * @param reslistener
     * @param errorListener
     * @param queue
     * @param parmers
     */
    public static void VolleyPost(String url,Response.Listener reslistener,Response.ErrorListener errorListener,RequestQueue queue, final Map<String,String> parmers){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,reslistener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return parmers;
            }

        };

        queue.add(stringRequest);
    }

    /**
     * 通过JsonObjectRequest获取数据
     * @param url String
     * @param reslistener Response.Listener
     * @param errorListener Response.ErrorListener
     * @param queue RequestQueue
     */
    public static void VolleyGetJsonRequest(String url,Response.Listener reslistener,Response.ErrorListener errorListener,RequestQueue queue){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, reslistener,errorListener);
        queue.add(jsonObjectRequest);
    }

    /**
     * 加载一张网络图片
     * @param url
     * @param Imagewidht
     * @param Imageheight
     * @param reslistener
     * @param errorListener
     * @param queue
     */
    public static void VolleyImage(String url,int Imagewidht,int Imageheight,Response.Listener reslistener,Response.ErrorListener errorListener,RequestQueue queue){
        ImageRequest imageRequest=new ImageRequest(url,reslistener,Imagewidht,Imageheight, Bitmap.Config.RGB_565,errorListener);
        queue.add(imageRequest);
    }



    /*
    public static String VolleyGet(String url,RequestQueue queue,String ss){
         final String result=null;
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(stringRequest);
        return "";
    }*/


}
