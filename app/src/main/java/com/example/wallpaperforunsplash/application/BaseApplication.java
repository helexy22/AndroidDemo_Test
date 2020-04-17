package com.example.wallpaperforunsplash.application;


import android.app.Application;

import com.example.wallpaperforunsplash.utils.StaticClass;
import com.example.wallpaperforunsplash.utils.UtilTools;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * @author lenovo
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化 Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
    }
}
