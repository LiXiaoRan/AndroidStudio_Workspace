package com.liran.contentprovideripc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.concurrent.Semaphore;

/**
 * 自定义的contentProvider
 * Created by LiRan on 2015-11-20.
 */
public class BookProvider extends ContentProvider {
    private String TAG = "BookProvider";

    public static final String AUTHORITY = "com.liran.contentprovideripc.BookProvider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");

    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sURI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * 控制初始化数据的线程的信号量
     */
    private Semaphore dbcreateSemaphore = new Semaphore(0);

    private SQLiteDatabase mdb;
    private Context context;

    static {
        sURI_MATCHER.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sURI_MATCHER.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        Logger.init();
        Logger.i("onCreate");
        context = getContext();
        initProvideData();

        return true;
    }

    private void initProvideData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mdb = DBOpenHelper.getInstance(context).getWritableDatabase();
                mdb.execSQL("delete from " + DBOpenHelper.BOOK_TABLE_NAME);
                mdb.execSQL("delete from " + DBOpenHelper.USER_TABLE_NAME);
                mdb.execSQL("insert into book values(3,'android')");
                mdb.execSQL("insert into book values(4,'HTML5')");
                mdb.execSQL("insert into book values(5,'IOS')");
                mdb.execSQL("insert into user values(1,'liran',0)");
                mdb.execSQL("insert into user values(2,'haha',1)");
                //初始化完毕的时候释放信号
                dbcreateSemaphore.release();
            }
        }).start();
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Logger.i("query");

        String table = getTableName(uri);
        Logger.i("获取的表名" + table);
        if (table == null) {
            throw new NullPointerException("空的: " + uri);
        }

        if (mdb == null) {
            try {
                //请求一个信号
                dbcreateSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return mdb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert: ");
        String table = getTableName(uri);
        if (table == null) {
            throw new NullPointerException("空的: " + uri);
        }
        if (mdb == null) {
            try {
                //请求一个信号
                dbcreateSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mdb.insert(table, null, values);
        //插入信息后要用纸外界数据发生了变化
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        String table = getTableName(uri);
        if (table == null) {
            throw new NullPointerException("空的: " + uri);
        }
        if (mdb == null) {
            try {
                //请求一个信号
                dbcreateSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //返回数据变动的行数
        int modifCount=mdb.delete(table,selection,selectionArgs);
        if(modifCount>0){
            context.getContentResolver().notifyChange(uri,null);
        }
        return modifCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update: ");
        String table = getTableName(uri);
        if (table == null) {
            throw new NullPointerException("空的: " + uri);
        }
        if (mdb == null) {
            try {
                //请求一个信号
                dbcreateSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int modifCount=mdb.update(table,values,selection,selectionArgs);
        if(modifCount>0){
            context.getContentResolver().notifyChange(uri,null);
        }
        return modifCount;
    }

    /**
     * 获取表明
     *
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {

        String tableName = null;
        switch (sURI_MATCHER.match(uri)) {

            case BOOK_URI_CODE:
                tableName = DBOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DBOpenHelper.USER_TABLE_NAME;
                break;

        }

        return tableName;

    }

}
