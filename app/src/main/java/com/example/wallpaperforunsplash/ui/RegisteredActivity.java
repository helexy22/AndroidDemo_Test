package com.example.wallpaperforunsplash.ui;

/*
 *  描述：    注册
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wallpaperforunsplash.R;
import com.example.wallpaperforunsplash.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisteredActivity extends BaseActivity implements OnClickListener {

    private EditText et_user,et_age,text_desc,et_pass,et_password,et_email;
    private RadioGroup mRadioGroup;
    private Button btnregistered;
    private boolean isGender=true;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registered);

        initView();
    }

    private void initView(){
        et_user= this.<EditText>findViewById(R.id.et_user);
        et_age= this.<EditText>findViewById(R.id.et_age);
        text_desc= this.<EditText>findViewById(R.id.text_desc);
        et_pass= this.<EditText>findViewById(R.id.et_pass);
        et_password= this.<EditText>findViewById(R.id.et_password);
        et_email= this.<EditText>findViewById(R.id.et_email);
        mRadioGroup= this.<RadioGroup>findViewById(R.id.mRadioGroup);
        btnregistered= this.<Button>findViewById(R.id.btnregistered);
        btnregistered.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistered:
                //获取输入框的值
                String name=et_user.getText().toString().trim();
                String age=et_age.getText().toString().trim();
                String desc=text_desc.getText().toString().trim();
                String pass=et_pass.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                String email=et_email.getText().toString().trim();

                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)) {

                    //判断两次输入的密码是否相同
                    if(pass.equals(password)) {

                        //判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_boy) {
                                    isGender = true;
                                } else if (checkedId == R.id.rb_girl) {
                                    isGender = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)) {
                            desc = getString(R.string.text_nothing);
                        }
                        //注册
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);
                        user.setDesc(desc);

                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisteredActivity.this, "注册失败" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

