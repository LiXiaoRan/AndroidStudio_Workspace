package com.liran.baidumap.demo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by liran on 2015-10-15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBaiDuMap();
    }

    private void initBaiDuMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
//        LocationUtils locationUtils=LocationUtils.getInstance(getApplicationContext());
    }
}
