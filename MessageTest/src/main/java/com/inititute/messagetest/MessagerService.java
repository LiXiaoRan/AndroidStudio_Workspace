package com.inititute.messagetest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.orhanobut.logger.Logger;

public class MessagerService extends Service {

    private String TAG = "MessagerService";


    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MyConst.MSG_FORM_CLIENT:
                    Bundle cbundle=msg.getData();
                    Logger.t(TAG).d("来自客户端的信息: "+cbundle.getString("client"));


                    Messenger client = msg.replyTo;
                    Message message = Message.obtain(null,MyConst.MSG_FORM_SERVER);

                    Bundle bundle = new Bundle();
                    bundle.putString("service", "我是服务，已经收到了你的信息 稍后回复你");
                    message.setData(bundle);
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    super.handleMessage(msg);

            }


        }
    };

    private final Messenger serMessenger=new Messenger(handler);

    public MessagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Logger.d("服务已经启动");
        return serMessenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
