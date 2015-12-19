package com.liran.diskcachelrutest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liran.diskcachelrutest.Utils.DiskLruCache;
import com.liran.diskcachelrutest.Utils.MD5_Utils;
import com.liran.diskcachelrutest.Utils.MyDiskLruUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button button;
    private Button button2;
    private ImageView imageView;
    private static final String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    private DiskLruCache mDiskLruCache;
    private MyDiskLruUtils myDiskLruUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDiskLruUtils=MyDiskLruUtils.getInstance();
        MyDiskLruUtils.open(this);
        mDiskLruCache=myDiskLruUtils.getmDiskLruCache();

        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.iv1);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                WriteDate();
                break;
            case R.id.button2:
                ReadDate();
                break;


        }


    }

    private void ReadDate() {
        try {
            String key = MD5_Utils.hashKeyForDisk(imageUrl);
            DiskLruCache.Snapshot snapshot = null;
            snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                InputStream is = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                imageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void WriteDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = MD5_Utils.hashKeyForDisk(imageUrl);
                    Log.d(TAG, "run: key= " + key);
                    if (mDiskLruCache == null) {
                        Log.d(TAG, "run: disklrucachr is null");
                    }
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (MyDiskLruUtils.downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();//提交后才能生效
                        } else {
                            editor.abort();//如果产生异常回滚本次操作
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
