package com.liran.flabbybird.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liran.flabbybird.utils.MyApplication;

/**
 * Created by LiRan on 2017-02-16.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getMyApplication().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getMyApplication().removeActivity(this);
    }
}
