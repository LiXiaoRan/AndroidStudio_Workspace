package com.liran.flabbybird.utils;

import android.app.Application;
import android.util.Log;

/**
 * Created by LiRan on 2017-01-25.
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: 哈哈哈");
    }



}
