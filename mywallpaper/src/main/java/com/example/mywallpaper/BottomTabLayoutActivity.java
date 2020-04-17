package com.example.mywallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BottomTabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private Fragment []mFragmensts;
    @Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_layout_ac);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");

    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
                for(int i=0;i< mTabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    }else{
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_home_selector)).setText(DataGenerator.mTabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_discovery_selector)).setText(DataGenerator.mTabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_attention_selector)).setText(DataGenerator.mTabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_profile_selector)).setText(DataGenerator.mTabTitle[3]));

    }
}
