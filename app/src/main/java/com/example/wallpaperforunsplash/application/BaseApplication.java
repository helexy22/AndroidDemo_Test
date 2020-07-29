package com.example.wallpaperforunsplash.application;


import android.app.Application;

import com.example.wallpaperforunsplash.utils.StaticClass;
import com.example.wallpaperforunsplash.utils.UtilTools;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;


/**
 * @author lenovo
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化 Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //初始化 Bomb
        Bmob.initialize(this, StaticClass.BOMB_APP_ID);
    }
}
