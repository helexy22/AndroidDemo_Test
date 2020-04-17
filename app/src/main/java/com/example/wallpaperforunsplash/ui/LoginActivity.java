package com.example.wallpaperforunsplash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wallpaperforunsplash.R;

/*登陆*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //注册按钮
    private Button btn_registered;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        btn_registered=(Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
        }
    }
}
