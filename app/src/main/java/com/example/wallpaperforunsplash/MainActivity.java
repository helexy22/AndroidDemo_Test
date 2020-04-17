package com.example.wallpaperforunsplash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.wallpaperforunsplash.fragment.BulitFragment;
import com.example.wallpaperforunsplash.fragment.GirlsFragment;
import com.example.wallpaperforunsplash.fragment.UserFragment;
import com.example.wallpaperforunsplash.fragment.WechatFragment;
import com.example.wallpaperforunsplash.ui.settingActivity;
import com.example.wallpaperforunsplash.utils.L;
import com.example.wallpaperforunsplash.utils.ShareUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lenovo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗
    private FloatingActionButton fabSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉阴影
        getSupportActionBar().setElevation(0);

        initDate();
        initView();



        ShareUtils.putString(this,"username","溜溜");
    }

    // 初始化数据
    private void initDate() {
        mTitle = new ArrayList<>();
        mTitle.add("服务管家");
        mTitle.add("微信精选");
        mTitle.add("服务社区");
        mTitle.add("个人中心");

        mFragment = new ArrayList<>();
        mFragment.add(new BulitFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlsFragment());
        mFragment.add(new UserFragment());

    }

    //初始化View
    @SuppressLint("RestrictedApi")
    private void initView() {
        fabSetting=(FloatingActionButton) findViewById(R.id.fabSetting);
        fabSetting.setOnClickListener((View.OnClickListener) this);
        //默认隐藏


        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPager 滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TAG", "position:" + position);
                if (position==0){
                    fabSetting.setVisibility(View.GONE);
                }else{
                    fabSetting.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中 item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回 item 个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabSetting:
                startActivity(new Intent(this, settingActivity.class));
                break;
        }
    }


}
