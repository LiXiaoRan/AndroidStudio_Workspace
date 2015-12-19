package com.liran.diskcachelrutest.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * DiskLruCache的操作类
 * Created by LiRan on 2015-12-13.
 */
public class MyDiskLruUtils {


    private static final long DISK_MAX_SIZE = 50 * 1024 * 1024;
    private static final String TAG = "MyDiskLruUtils";
    private static DiskLruCache mDiskLruCache;
        private static MyDiskLruUtils mInstance;

    public DiskLruCache getmDiskLruCache() {
        return mDiskLruCache;
    }

    private MyDiskLruUtils(){

    }

    public static MyDiskLruUtils getInstance(){
        if(mInstance==null){
            mInstance=new MyDiskLruUtils();
        }
        return mInstance;
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }


    // Creates a unique subdirectory of the designated app cache directory. Tries to use external
    // but if not mounted, falls back on internal storage.
    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !Environment.isExternalStorageRemovable() ? Environment.getExternalStorageDirectory().getPath() :
                        context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }


    public static void open(Context context) {
        try {
            File cacheDir = getDiskCacheDir(context, "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, DISK_MAX_SIZE);
            if(mDiskLruCache!=null){
                Log.d(TAG, "open: mDiskLruCache!=null");
                Toast.makeText(context, "open1", Toast.LENGTH_SHORT).show();

            }else {
                Log.d(TAG, "open: mDiskLruCache is null");
                Toast.makeText(context, "open2", Toast.LENGTH_SHORT).show();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }





}
