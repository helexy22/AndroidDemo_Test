package com.example.wallpaperforunsplash.ui;

//闪屏页

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wallpaperforunsplash.MainActivity;
import com.example.wallpaperforunsplash.R;
import com.example.wallpaperforunsplash.utils.ShareUtils;
import com.example.wallpaperforunsplash.utils.StaticClass;
import com.example.wallpaperforunsplash.utils.UtilTools;


public class SplashActivity extends AppCompatActivity {

    /*    1.延时2000ms
    2.判断是否第一次运行
    3.自定义字体
    4.Activity全屏主题*/

    private TextView tv_Spalsh;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDER_SPLASH:
                    //判断是否第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        inintView();
    }

    //初始化View
    public void inintView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDER_SPLASH, 2000);
        tv_Spalsh =  (TextView)findViewById(R.id.tv_Spalsh);
        //设置字体
        UtilTools.setFont(this,tv_Spalsh);
    }


    //判断是否第一次运行
    private boolean isFirst() {
       boolean   isFirst= ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
       if (isFirst){
           ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
           return true;
       }else {
           return false;
       }
    }

//    //禁止返回键
//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//    }
}