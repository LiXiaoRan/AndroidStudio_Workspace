package com.inititute.handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * 四种在子线程中更新UI的方法
 */
public class MainActivity extends Activity {

    private String TAG = "handlerTest";

    private TextView textView;

    private HandlerThread handlerThread;
    private Handler handlerTest;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("handleMessage");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview1);

        Log.d(TAG + "main", "oncreat 当前线程是：" + Thread.currentThread());

        /*MyThrad myThrad=new MyThrad();
        myThrad.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThrad.handler.sendEmptyMessage(1);*/

/*
        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        //handlerThread的Looper绑定
        handlerTest = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handleMessage 当前线程是："+Thread.currentThread());
            }
        };
        handlerTest.sendEmptyMessage(1);*/


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**如果前面休眠两秒钟的话，执行下面直接settext就是报异常，如果不休眠直接修改就没有这个异常
                 * 因为安卓底层对非UI线程更新UI的判断放在onResume中进行，OnCreat在这个方法之前，所以如果、
                 * 不进行睡眠，就一定可以更新成功，这算是凑巧在非ui线程中更新了UI。*/
//                textView.setText("试试看看");


                   Handler1();
//                Handler2();
//                updateUI1();
//                updateUI2();

            }
        }).start();

    }

    private void updateUI2() {
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("textView.post");
            }
        });
    }

    private void updateUI1() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("runOnUiThread");
            }
        });
    }

    private void Handler2() {
        handler.sendEmptyMessage(1);
    }

    private void Handler1() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("handler.post");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
