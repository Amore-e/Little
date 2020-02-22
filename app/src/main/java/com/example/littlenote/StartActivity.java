package com.example.littlenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.littlenote.uc.FirstFragment;
import com.example.littlenote.uc.SecondFragment;
import com.example.littlenote.uc.StartFragment_1;
import com.example.littlenote.uc.StartFragment_2;
import com.example.littlenote.uc.StartFragment_3;
import com.example.littlenote.uc.ThirdFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private TabLayout oTabLayout;
    private ViewPager oViewPager;
    private ArrayList<String> oTitleList=new ArrayList<>();//页卡标题集合
    private Fragment startFragment_1,startFragment_2,startFragment_3;//页卡视图
    private ArrayList<Fragment> oViewList=new ArrayList<>();//页卡视图集合


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        oViewPager=findViewById(R.id.viewpager1);
        oTabLayout=findViewById(R.id.tab1);

        startFragment_1=new StartFragment_1();
        startFragment_2=new StartFragment_2();
        startFragment_3=new StartFragment_3();

        //添加页卡视图
        oViewList.add(startFragment_1);
        oViewList.add(startFragment_2);
        oViewList.add(startFragment_3);

        //添加页卡标题
        oTitleList.add(".");
        oTitleList.add(".");
        oTitleList.add(".");


        oTabLayout.setTabMode(TabLayout.INDICATOR_GRAVITY_TOP);//设置tab模式
        oTabLayout.addTab(oTabLayout.newTab().setText(oTitleList.get(0)));//添加tab选项卡
        oTabLayout.addTab(oTabLayout.newTab().setText(oTitleList.get(1)));
        oTabLayout.addTab(oTabLayout.newTab().setText(oTitleList.get(2)));

        //给ViewPager设置适配器
        oViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //获取每个页卡
            @Override
            public Fragment getItem(int position) {
                return oViewList.get(position);
            }
            //获取页卡数
            @Override
            public int getCount() {
                return oTitleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return oTitleList.get(position);
            }
        });
        oTabLayout.setupWithViewPager(oViewPager);

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fa_bar1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
