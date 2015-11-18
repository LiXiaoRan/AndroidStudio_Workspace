package com.liran.messagetest;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by LiRan on 2015-11-18.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();

    }
}
