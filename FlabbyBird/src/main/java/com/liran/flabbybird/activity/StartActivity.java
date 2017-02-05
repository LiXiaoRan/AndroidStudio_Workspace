package com.liran.flabbybird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liran.flabbybird.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LiRan on 2017-02-05.
 */

public class StartActivity extends AppCompatActivity {


    private Intent localIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        //停留一段时间启动登陆界面
        localIntent = new Intent(this, LoginActivity.class);
        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(localIntent);
                finish();
            }
        };
        timer.schedule(tast, 3000);


    }
}
