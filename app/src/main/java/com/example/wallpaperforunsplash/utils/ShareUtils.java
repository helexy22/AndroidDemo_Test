package com.example.wallpaperforunsplash.utils;

/*SharedPreferences封装*/

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {

    /*原始的四步骤方法*/
    private void test(Context mConText){
        SharedPreferences sp=mConText.getSharedPreferences("config",Context.MODE_PRIVATE);

        sp.getString("key","未获取到");

        SharedPreferences.Editor editor =sp.edit();

        editor.putString("key","value");
        editor.commit();
    }

    //改进后*/

    //键 值
    public static final String NAME="config";
    public static void putString(Context mContext,String key,String value ){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    //键  默认值
    public static String getString(Context mContext,String key,String defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    //键 值
    public static void putInt(Context mContext,String key,int value ){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    //键  默认值
    public static int getInt(Context mContext,String key,int defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    //键 值
    public static void putBoolean(Context mContext,String key,boolean value ){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    //键  默认值
    public static boolean getBoolean(Context mContext,String key,boolean defValue){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    ////删除 单个
    public static void deleShare(Context mContext,String key){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
    ////删除 全部
    public static void deleAll(Context mContext){
        SharedPreferences sp=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
