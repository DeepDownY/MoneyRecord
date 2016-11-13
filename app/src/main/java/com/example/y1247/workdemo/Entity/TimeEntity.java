package com.example.y1247.workdemo.Entity;

import java.util.Date;

/**
 * Created by y1247 on 2016/11/12 0012.
 */

public class TimeEntity {
    public  static final int TYPE_EXPEND = 100;
    public  static final int TYPE_INCOME = 0;
    public  static final int TYPE_DATEREC = 2;
    public Date date;
    public float use;
    public int type;
    public String type_name;
    public TimeEntity(float use,Date date,int type,String type_name){
        this.use = use;
        this.date = date;
        this.type = type;
        this.type_name = type_name;
    }

}
