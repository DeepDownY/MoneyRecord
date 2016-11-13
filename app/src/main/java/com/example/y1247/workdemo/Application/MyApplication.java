package com.example.y1247.workdemo.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by y1247 on 2016/10/22 0022.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }

}
