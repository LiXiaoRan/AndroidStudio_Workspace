package com.liran.contentprovideripc;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by LiRan on 2015-11-20.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
    }
}
