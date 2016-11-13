package com.example.y1247.workdemo.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.y1247.workdemo.Entity.DateRecord;
import com.example.y1247.workdemo.Entity.Expend;
import com.example.y1247.workdemo.Entity.Income;
import com.example.y1247.workdemo.Entity.TimeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by y1247 on 2016/11/9 0009.
 */

public class MySqlHelper extends SQLiteOpenHelper {

    private static final String name = "count"; //数据库名称
    private static final int version = 1; //数据库版本
    public  static final int TYPE_EXPEND = 1;
    public  static final int TYPE_INCOME = 0;

    public MySqlHelper(Context context){
        super(context,name,null,version);
    }
    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE [BillsBookItem](" +
                "[ID] INTEGER,  [itemMoney] REAL, " +
                "[itemType] integer, " +
                "[itemCreateYMD] INTEGER ," +
                "[isDelete] Boolean, [isEdit] Boolean, " +
                "[itemTypeName] Varchar);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
