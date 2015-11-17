package com.liran.aidl_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {
    private String TAG="AppService";
    private  String data="AppService的默认数据";
    private boolean running=false;

    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IAppserviceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                   AppService.this.data =data;
            }
        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String clientinfo=intent.getStringExtra("client");
        Log.i(TAG, "onStartCommand: client is "+clientinfo);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 服务已经启动");

        new Thread(new Runnable() {
            @Override
            public void run() {

                running=true;
                while (running){
                    Log.d(TAG, "run: data is "+data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        running=false;
        Log.d(TAG, "onDestroy: 服务已经终止");
    }
}
