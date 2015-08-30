package com.example.lenovo.myapplication.insterface;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lenovo.myapplication.MyApplication;

/**
 * Created by lr on 2015-08-30.
 */
public abstract class VolletInterface {
    public Context mcontext;
    public static Response.Listener<String> mlistener;
    public static Response.ErrorListener merrorListener;

    public VolletInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        mcontext = context;
        mlistener = listener;
        merrorListener = errorListener;
    }

    public abstract void onVolleySuccess(String s);

    public abstract void onVolleyError(VolleyError volleyError);

    public Response.Listener<String> loadListener() {
        mlistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onVolleySuccess(s);
            }
        };
        return mlistener;

    }

    public Response.ErrorListener errorListener() {
        merrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.AppContext, "获取失败 具体原因:" + volleyError.toString(), Toast.LENGTH_SHORT).show();
                onVolleyError(volleyError);
            }
        };
        return merrorListener;
    }
}
