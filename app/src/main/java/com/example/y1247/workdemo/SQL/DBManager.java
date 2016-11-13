package com.example.y1247.workdemo.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.y1247.workdemo.Entity.DateRecord;
import com.example.y1247.workdemo.Entity.Expend;
import com.example.y1247.workdemo.Entity.Income;
import com.example.y1247.workdemo.Entity.TimeEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by y1247 on 2016/11/12 0012.
 */

public class DBManager {
    private MySqlHelper helper;
    private SQLiteDatabase db;
    Context context;

    public DBManager(Context context){
        this.context = context;
        helper = new MySqlHelper(context);
        db = helper.getWritableDatabase();
    }

    public float getDateUSing(long date){
        String sql = "select itemMoney from BillsBookItem where itemCreateYMD = " + date +" and itemType >= 100;";
        Cursor cursor = db.rawQuery(sql,null);
        float use = 0;
        while (cursor.moveToNext()){
            use += cursor.getLong(0);
        }
        cursor.close();
        return use;
    }

    /**
     * 插入收入和支出对象
     * @param type 判断收支类型的依据
     * @param date 日期
     * @param using 具体数目
     */
    public void add(int type,long date,float using,String itemTypeName){
        ContentValues cv = new ContentValues();
        cv.put("itemMoney",using);
        cv.put("itemType",type);
        cv.put("itemCreateYMD",date);
        cv.put("itemTypeName",itemTypeName);
        db.insert("BillsBookItem",null,cv);
    }

    /**
     * 获取时间轴的所有对象
     * @return 返回用于时间轴构造的数据集
     */
    public List<TimeEntity> getTimeLineData(){
        List<TimeEntity> list= new ArrayList<TimeEntity>();
        String sql = "SELECT itemMoney,itemType,itemCreateYMD,itemTypeName,isDelete FROM BillsBookItem order by itemCreateYMD desc";
        Cursor cursor = db.rawQuery(sql,null);
        int lastDate = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        while (cursor.moveToNext()){
            float itemMoney = cursor.getFloat(0);
            int type = cursor.getInt(1);
            int date = cursor.getInt(2);
            String type_name = cursor.getString(3);

            //获取日期对象
            Date date_temp= null;
            try {
                date_temp = sdf.parse(String.valueOf(date));
            }catch (Exception e){
                System.out.print(e.toString());
            }


            if(lastDate!=date){
                DateRecord dateRecord = new DateRecord(date_temp,this.getDateUSing(date));
                list.add(dateRecord);
                lastDate = date;
            }
            if(type>= TimeEntity.TYPE_EXPEND){
                list.add(new Expend(date_temp,itemMoney,type_name));
            }else {
                list.add(new Income(date_temp,itemMoney,type_name));
            }
        }
        cursor.close();
        return list;
    }
}
