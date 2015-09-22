package com.liran.handlertest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


/**
 * Created by Lenovo on 2015-09-22.
 */
public class MyThrad extends Thread {

    private String TAG="handlerTest";
    public Handler handler;
    public Looper looper;
    @Override
    public void run() {
        super.run();
        Looper.prepare();
        looper=Looper.myLooper();

      handler=new Handler(){
          @Override
          public void handleMessage(Message msg) {
              super.handleMessage(msg);
              Log.d(TAG, "handleMessage 当前线程是："+Thread.currentThread());
          }
      };

        Looper.loop();
    }
}
