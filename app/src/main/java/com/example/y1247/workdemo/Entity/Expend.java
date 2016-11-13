package com.example.y1247.workdemo.Entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by y1247 on 2016/11/2 0002.
 */

public class Expend extends TimeEntity {
    public float use;
    public Date date;
    String type_name ;
    public int type = 1;

    /**
     *
     * @param date 传入long类型参数，用于构造日期
     * @param use  支出
     * @param type 类型名
     */
    public Expend(Date date,float use,String type){
        super(use,date,DateRecord.TYPE_EXPEND,type);
        this.use = use;
        this.date = date;
        type_name = type;
    }
}
