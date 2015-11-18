package com.liran.messagetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Button button;
    private Button btn_send;
    private TextView textView;

    private Messenger Client;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConst.MSG_FORM_SERVER:
                    Bundle bundle = msg.getData();
                    Logger.t(TAG).d("来自服务器的消息：" + bundle.getString("service"));
                    textView.setText(bundle.getString("service"));

                    break;

                default:
                    super.handleMessage(msg);

            }

        }
    };

    /**
     * 服务端的Messenger对象
     */
    private Messenger mService;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_bind);
        btn_send = (Button) findViewById(R.id.btn_send);
        textView = (TextView) findViewById(R.id.text);

        button.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        Client = new Messenger(handler);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                Intent intentservice = new Intent();
                Intent intent = new Intent(this, MessagerService.class);
                intentservice.setComponent(new ComponentName("com.liran.messagetest", "com.liran.messagetest.MessagerService"));
                //上面这两种类型的Intent都可以正常的连接
                bindService(intentservice, this, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btn_send:
                senMessagetoService("哈哈哈 这是第信息: " + (int) (Math.random() * 10));
                break;
        }


    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Logger.t(TAG).d("连接成功");
        //这里链接远程服务器成功就可以获取Messenger对象，并且向远程服务端发送消息了
        mService = new Messenger(service);

        senMessagetoService("我是客户端 连接成功");

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Logger.t(TAG).d("连接失败");
    }

    /**
     * 发送消息
     *
     * @param info 消息
     */
    private void senMessagetoService(String info) {
        Bundle bundle = new Bundle();
        bundle.putString("client", info);
        Message message = Message.obtain(null, MyConst.MSG_FORM_CLIENT);
        message.setData(bundle);

        //将客户端的messenge传递过去 以便于服务端获取
        message.replyTo = Client;
        try {
            mService.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }
}
