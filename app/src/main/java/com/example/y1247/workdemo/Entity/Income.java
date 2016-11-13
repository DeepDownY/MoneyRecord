package com.example.y1247.workdemo.Entity;

import java.util.Date;

/**
 * Created by y1247 on 2016/11/2 0002.
 */

public class Income extends TimeEntity {
    public float use;
    public Date date;
    public int type = 0;
    String type_name;

    /**
     *
     * @param date 传入long类型参数，用于构造日期
     * @param use  支出
     * @param type 类型名
     */
    public Income(Date date,float use,String type){
        super(use,date,DateRecord.TYPE_INCOME,type);
        this.use = use;
        this.date = date;
        type_name = type;
    }
}
