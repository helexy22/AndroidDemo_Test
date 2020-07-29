package com.example.wallpaperforunsplash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wallpaperforunsplash.MainActivity;
import com.example.wallpaperforunsplash.R;
import com.example.wallpaperforunsplash.entity.MyUser;
import com.example.wallpaperforunsplash.utils.ShareUtils;
import com.example.wallpaperforunsplash.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*登陆*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //注册按钮
    private Button btn_registered;
    private  EditText et_name;
    private   EditText et_password;
    private  Button btn_login;
    private  CheckBox keep_password;

    private CustomDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        btn_registered=(Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name=findViewById(R.id.et_name);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(this);
        keep_password=findViewById(R.id.keep_password);



        //设置选中状态
       boolean isKeep= ShareUtils.getBoolean(this,"keeppass",false);
       keep_password.setChecked(isKeep);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loading, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);

        if (isKeep){
            String name = ShareUtils.getString(this, "name", "");
            String password = ShareUtils.getString(this, "password", "");
            et_name.setText(name);
            et_password.setText(password);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.btn_Login:
                //获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
              //判断是否为空
                if (!TextUtils.isEmpty(name)& !TextUtils.isEmpty(password)){
                    dialog.show();
                    final MyUser user=new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            dialog.dismiss();
                            //判断结果
                            if (e==null){
                                //邮箱是否验证
                                if(user.getEmailVerified()){
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                }else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(LoginActivity.this, "登陆失败"+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()){
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        }else {
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "password");
        }
    }
}
