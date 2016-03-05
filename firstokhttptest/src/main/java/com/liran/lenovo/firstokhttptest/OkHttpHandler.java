package com.liran.lenovo.firstokhttptest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiRan on 2016-03-05.
 */
public class OkHttpHandler {

    private static OkHttpHandler ourInstance = new OkHttpHandler();
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;

    private OKinterface oKinterface;

    public void setoKinterface(OKinterface oKinterface) {
        this.oKinterface = oKinterface;
    }

    public interface OKinterface {
        void dealResponse(Response response, Call call);
    }


    public static OkHttpHandler getInstance() {
        return ourInstance;
    }

    private OkHttpHandler() {
        okHttpClient = new OkHttpClient();
    }


    public void asyncGet(String url) {

        request = new Request.Builder().url(url).build();
        call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (oKinterface != null) {
                    oKinterface.dealResponse(null, call);
                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (oKinterface != null) {
                    oKinterface.dealResponse(response, call);
                }

            }
        });

    }


    public void OKpost(String url) {


    }

}
