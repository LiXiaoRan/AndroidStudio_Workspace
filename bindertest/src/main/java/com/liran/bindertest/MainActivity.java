package com.liran.bindertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private Intent intentService;
    private IBookManager remoteBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentService = new Intent();
        intentService.setComponent(new ComponentName("com.liran.bindertest", "com.liran.bindertest.BookManagerService"));
        bindService(intentService, this, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        remoteBookManager = IBookManager.Stub.asInterface(service);
        try {
            List<Book> list = remoteBookManager.getBookList();
            Logger.d(list.toString());

            Logger.i("list type is " + list.getClass().getCanonicalName());
            Book book = new Book(3, "安卓进阶");
            remoteBookManager.addBook(book);
            Logger.d(remoteBookManager.getBookList().toString());
            remoteBookManager.registerListener(iOnNewBookArriveListener);


        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Book book = (Book) msg.obj;
            Logger.d("接收到新书 : " + book);

        }
    };


    private String TAG="MainActivitt";
    private IOnNewBookArriveListener.Stub iOnNewBookArriveListener = new IOnNewBookArriveListener.Stub() {
        @Override
        public void onNewBookArriver(Book newBook) throws RemoteException {
            Log.d(TAG, "onNewBookArriver: "+"onNewBookArriver回调");
            Message message = Message.obtain();
            message.obj = newBook;
            handler.sendMessage(message);

        }
    };

    @Override
    public void onServiceDisconnected(ComponentName name) {
        remoteBookManager = null;
        Logger.d("绑定失败");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (remoteBookManager != null && remoteBookManager.asBinder().isBinderAlive()) {
            Logger.d("注销监听器" + iOnNewBookArriveListener);
            try {
                remoteBookManager.unregisterListener(iOnNewBookArriveListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        unbindService(this);
    }
}
