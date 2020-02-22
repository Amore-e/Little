package com.example.littlenote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.littlenote.uc.FirstFragment;
import com.example.littlenote.uc.SecondFragment;
import com.example.littlenote.uc.ThirdFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout aDrawerLayout;

    private TabLayout eTabLayout;
    private ViewPager eViewPager;
    private ArrayList<String> eTitleList=new ArrayList<>();//页卡标题集合
    private Fragment firstFragment,secondFragment,thirdFragment;//页卡视图
    private ArrayList<Fragment> eViewList=new ArrayList<>();//页卡视图集合

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);

        eViewPager=findViewById(R.id.viewpager);
        eTabLayout=findViewById(R.id.tab);

        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        thirdFragment=new ThirdFragment();

        //添加页卡视图
        eViewList.add(firstFragment);
        eViewList.add(secondFragment);
        eViewList.add(thirdFragment);

        //添加页卡标题
        eTitleList.add(".");
        eTitleList.add(".");
        eTitleList.add(".");

        eTabLayout.setTabMode(TabLayout.INDICATOR_GRAVITY_TOP);//设置tab模式
        eTabLayout.addTab(eTabLayout.newTab().setText(eTitleList.get(0)));//添加tab选项卡
        eTabLayout.addTab(eTabLayout.newTab().setText(eTitleList.get(1)));
        eTabLayout.addTab(eTabLayout.newTab().setText(eTitleList.get(2)));

        //给ViewPager设置适配器
        eViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //获取每个页卡
            @Override
            public Fragment getItem(int position) {
                return eViewList.get(position);
            }
            //获取页卡数
            @Override
            public int getCount() {
                return eTitleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return eTitleList.get(position);
            }
        });
        eTabLayout.setupWithViewPager(eViewPager);



        //导航按钮
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_2);
        }

        //侧滑栏菜单的点击事件，返回主界面
        navView.setCheckedItem(R.id.nav_home);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {
                //aDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        aDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_note:
                        Intent intent1=new Intent(MainActivity.this,WriteActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_read:
                        Intent intent2=new Intent(MainActivity.this,ReadActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_shelf:
                        Intent intent3=new Intent(MainActivity.this,ShelfActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        //悬浮按钮的点击事件:切换到“记事”页面
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fa_bar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });

    }
    //导航按钮及菜单的点击事件
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                aDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }


}
