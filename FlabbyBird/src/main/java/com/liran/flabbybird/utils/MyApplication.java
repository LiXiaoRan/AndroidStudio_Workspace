package com.liran.flabbybird.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import net.tsz.afinal.FinalDb;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiRan on 2017-01-25.
 */
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    public static Context mContext;

    private static FinalDb db;

    private static MyApplication instance;

    private List<Activity> activityList = new LinkedList<Activity>();




    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this.getApplicationContext();

        Log.d(TAG, "onCreate: 哈哈哈");


    }

    /**
     * 如果没有则创建并返回一个数据库操作对象
     *
     * @return FinalDb对象
     */
    public static FinalDb getDB() {
        if (db == null) {
            db = FinalDb.create(mContext);
        }
        return db;
    }


    public static MyApplication getMyApplication() {

        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;

    }

    /**
     * 添加Activity到容器中
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }


    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }


    /**
     * 遍历所有Activity并finish
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }


}
