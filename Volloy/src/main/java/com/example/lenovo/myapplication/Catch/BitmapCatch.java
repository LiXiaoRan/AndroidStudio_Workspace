package com.example.lenovo.myapplication.Catch;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 李冉 on 2015-08-30.
 */
public class BitmapCatch implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> cache;
    private int max=10*1024*1024;


    public BitmapCatch(){
        cache=new LruCache<String,Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        cache.put(s,bitmap);
    }
}
