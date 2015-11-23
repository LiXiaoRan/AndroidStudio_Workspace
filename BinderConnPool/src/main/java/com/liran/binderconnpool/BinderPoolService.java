package com.liran.binderconnpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.orhanobut.logger.Logger;

public class BinderPoolService extends Service {

    public static final String TAG = "BinderPoolService";
    private Binder mBinderPool=new BinderPool.BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.t(TAG).d("onBind");
        return mBinderPool;
    }

    @Override
    public void onCreate() {
        Logger.t(TAG).d("onCreate");

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Logger.t(TAG).d("onDestroy");

        super.onDestroy();
    }
}
