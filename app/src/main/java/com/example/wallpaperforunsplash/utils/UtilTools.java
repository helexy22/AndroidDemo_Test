package com.example.wallpaperforunsplash.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*工具类*/
public class UtilTools {

    //设置字体
    public static void setFont(Context mContext, TextView textView){
        Typeface fonttype=Typeface.createFromAsset(mContext.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(fonttype);
    }

    //保存图片到 ShareUtils
    public static void putImgeToShare(Context mcontext, ImageView imageView){
        BitmapDrawable drawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        //将 BItmap 压缩 ByteArrayOutputStream
        ByteArrayOutputStream byStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,80,byStream);
        //将 ByteArrayOutputStream 转换成 String
        byte[] byteArray=byStream.toByteArray();
        String imgString=new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //将 String 保存在 utils
        ShareUtils.putString(mcontext,"imge_title",imgString);
    }

    //读取图片
    public static void getImgeToShare(Context mcontext,ImageView imageView){
        String imgString=ShareUtils.getString(mcontext,"img_title","");
        if (!imgString.equals("")){
            byte[] byteArray=Base64.decode(imgString,Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
            Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
            imageView.setImageBitmap(bitmap);
        }
    }
}
