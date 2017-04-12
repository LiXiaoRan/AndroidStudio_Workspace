package com.inititute.bindertest;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by LiRan on 2015-11-19.
 */
public class myApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();

    }
}
