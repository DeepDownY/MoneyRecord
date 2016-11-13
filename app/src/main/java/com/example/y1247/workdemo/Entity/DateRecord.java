package com.example.y1247.workdemo.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by y1247 on 2016/11/2 0002.
 */

public class DateRecord extends TimeEntity{
    public Date date;
    public float use;

    public DateRecord(Date dateLong,float use){
        super(use,dateLong,DateRecord.TYPE_DATEREC,"日期");
        date = dateLong;

        this.use = use;
    }
}
