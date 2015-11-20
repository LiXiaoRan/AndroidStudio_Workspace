package com.liran.contentprovideripc;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uri = Uri.parse("content://com.liran.contentprovideripc.BookProvider/book");
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "name"}, null, null, null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setBookId(cursor.getInt(cursor.getColumnIndex("_id")));
            book.setBookName(cursor.getString(1));
            Logger.v("查询到的数据: "+book.toString());
        }
        cursor.close();

        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);

    }
}
