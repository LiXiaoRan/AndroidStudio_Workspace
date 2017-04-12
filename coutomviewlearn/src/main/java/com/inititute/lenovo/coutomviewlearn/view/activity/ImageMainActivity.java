package com.inititute.lenovo.coutomviewlearn.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inititute.lenovo.coutomviewlearn.R;
import com.inititute.lenovo.coutomviewlearn.view.MyImageView;

/**
 *
 * Created by LiRan on 2016-01-26.
 */
public class ImageMainActivity extends AppCompatActivity {

   private MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagelayout);

        myImageView= (MyImageView) findViewById(R.id.myimage);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.android_1);
        myImageView.setmBitmap(bitmap);
    }
}
