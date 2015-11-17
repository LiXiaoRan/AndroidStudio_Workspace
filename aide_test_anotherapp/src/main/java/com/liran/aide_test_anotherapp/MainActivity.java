package com.liran.aide_test_anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liran.aidl_test.IAppserviceRemoteBinder;

/**
 * 这里演示一下从当前APP启动aidl_test这个app中的的服务
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent intentService;

    private Button btn_start;
    private Button btn_stop;
    private Button btn_bind;
    private Button btn_unbind;
    private String TAG="MainActivity";
    private Button btn_sync;
    private EditText ed_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentService = new Intent();
        //这里要传入服务所在的应用的完整的包名，和完整的服务名
        intentService.setComponent(new ComponentName("com.liran.aidl_test", "com.liran.aidl_test.AppService"));


        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_bind = (Button) findViewById(R.id.btn_bind);
        btn_unbind = (Button) findViewById(R.id.btn_unbind);
        btn_sync = (Button) findViewById(R.id.btn_sync);
        ed_input= (EditText) findViewById(R.id.ed_input);

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
        btn_sync.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_bind:
                Log.d(TAG, "onClick: 点击了绑定外部服务");
                intentService.putExtra("client","你好a ");  //可以将消息传递到其他应用的服务中 onStartCommand方法中取出
                bindService(intentService, this, Context.BIND_AUTO_CREATE);

                break;
            case R.id.btn_unbind:
                Log.d(TAG, "onClick: 点击了解除绑定外部服务");
                unbindService(this);
                Binder=null;
                break;
            case R.id.btn_start:
                startService(intentService);
                Log.d(TAG, "onClick: 点击了启动服务");
                break;
            case R.id.btn_stop:
                stopService(intentService);
                Log.d(TAG, "onClick: 点击了关闭服务");
                break;
            case R.id.btn_sync:
                if(Binder!=null){
                    Log.d(TAG, "onClick: 点击了同步 同步信息是："+ed_input.getText().toString());

                    try {
                        Binder.setData(ed_input.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;

        }


    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "onServiceConnected: 绑定服务");
        Log.d(TAG, "onServiceConnected: service is "+service);
        //获取远程的binder对象
        Binder= IAppserviceRemoteBinder.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


    private IAppserviceRemoteBinder Binder=null;
}
