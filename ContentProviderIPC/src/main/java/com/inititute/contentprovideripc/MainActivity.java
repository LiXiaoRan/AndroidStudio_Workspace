package com.inititute.contentprovideripc;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(BookProvider.BOOK_CONTENT_URI, new String[]{"_id", "name"}, null, null, null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setBookId(cursor.getInt(cursor.getColumnIndex("_id")));
            book.setBookName(cursor.getString(1));
            Logger.v("查询到的数据: "+book.toString());
        }
        cursor.close();

        ContentValues contentValues=new ContentValues();
        contentValues.put("_id",5);
        contentValues.put("name","啊哈");
        contentValues.put("sex","男");
        getContentResolver().insert(BookProvider.USER_CONTENT_URI, contentValues);

        queryUserByProvider();



    }

    private void queryUserByProvider() {

        Cursor cursor=getContentResolver().query(BookProvider.USER_CONTENT_URI,new String[]{"_id","name","sex"},null,null,null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            Logger.v("查询到的数据: "+user.toString());
        }

    }
}
