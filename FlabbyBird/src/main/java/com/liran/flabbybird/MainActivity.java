package com.liran.flabbybird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private GameFlabbyBird gameFlabbyBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameFlabbyBird=new GameFlabbyBird(this);
        setContentView(gameFlabbyBird);
        Logger.init("线程测试");
        Logger.d("就是测试一下 main");


    }
}
