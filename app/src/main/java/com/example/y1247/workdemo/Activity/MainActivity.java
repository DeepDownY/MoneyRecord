package com.example.y1247.workdemo.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.y1247.workdemo.R;
import com.example.y1247.workdemo.Adapter.FragmentAdapter;
import com.example.y1247.workdemo.UI.TimeLinetFragment;
import com.example.y1247.workdemo.UI.MainFragment;
import com.example.y1247.workdemo.UI.StatisticsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private int currentIndex = 0;

    private int screenWidth;


    private TextView mTabMainTv, mTabIoTv, mTabStatTv;
    private ImageView mTabLineIv;
    TabLayout tl_TopLayout;
    Toolbar toolbar = null;
    FloatingActionButton fab = null;
    DrawerLayout drawer = null;
    ActionBarDrawerToggle toggle = null;
    NavigationView navigationView = null;
    TimeLinetFragment timeLinetFragment = null;
    MainFragment mainFragment = null;
    StatisticsFragment statisticsFragment = null;
    ViewPager viewPager = null;
    FragmentAdapter pagerAdapter = null;
    LinearLayout inOutLL,mainLL,statLL,viewLL;
    int lineWidth;
    DisplayMetrics dpMetrics;
    ArrayList<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        initView();

        //初始化数据
        initData();


        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        //viewPager.addOnPageChangeListener(this);

        tl_TopLayout.setupWithViewPager(viewPager);
        tl_TopLayout.setTabMode(TabLayout.MODE_FIXED);
        tl_TopLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        list.add(mainFragment);
        list.add(timeLinetFragment);
        list.add(statisticsFragment);
        lineWidth = screenWidth/list.size();

    }

    /**
     * 组件初始化
     */
    private void initView() {

        mTabMainTv = (TextView) this.findViewById(R.id.id_Main_tv);
        mTabIoTv = (TextView) this.findViewById(R.id.id_inout_tv);
        mTabStatTv = (TextView) this.findViewById(R.id.id_stat_tv);

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentIndex){
                    case 0:
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 1:
                        Snackbar.make(view, "111", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 2:
                        Snackbar.make(view, "222", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }

            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);

        /*
         * Fragment
         */
        timeLinetFragment = new TimeLinetFragment();
        mainFragment = new MainFragment();
        statisticsFragment = new StatisticsFragment();

        tl_TopLayout = (TabLayout) findViewById(R.id.tl_toplayout);


        /*
         * 顶部linearLayout
         */
//        inOutLL = (LinearLayout) findViewById(R.id.id_tab_inout_ll);
//        mainLL = (LinearLayout) findViewById(R.id.id_tab_Main_ll);
//        statLL = (LinearLayout) findViewById(R.id.id_tab_stat_ll);

//        inOutLL.setOnClickListener(this);
//        mainLL.setOnClickListener(this);
//        statLL.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initTabLineWidth() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        int color = getResources().getColor(R.color.primary_light);
        mTabIoTv.setTextColor(color);
        mTabMainTv.setTextColor(color);
        mTabStatTv.setTextColor(color);
    }

//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
//                .getLayoutParams();
//
//
//        /**
//         * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
//         * 设置mTabLineIv的左边距 滑动场景：
//         * 记3个页面,
//         * 从左到右分别为0,1,2
//         * 0->1; 1->2; 2->1; 1->0
//         */
//
//        if (currentIndex == 0 && position == 0)// 0->1
//        {
//            lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
//                    * (screenWidth / 3));
//
//        } else if (currentIndex == 1 && position == 0) // 1->0
//        {
//            lp.leftMargin = (int) (-(1 - positionOffset)
//                    * (screenWidth * 1.0 / 3) + currentIndex
//                    * (screenWidth / 3));
//
//        } else if (currentIndex == 1 && position == 1) // 1->2
//        {
//            lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex
//                    * (screenWidth / 3));
//        } else if (currentIndex == 2 && position == 1) // 2->1
//        {
//            lp.leftMargin = (int) (-(1 - positionOffset)
//                    * (screenWidth * 1.0 / 3) + currentIndex
//                    * (screenWidth / 3));
//        }
//        mTabLineIv.setLayoutParams(lp);
//        initTabLineWidth();
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        resetTextView();
//        switch (position) {
//            case 0:
//                mTabMainTv.setTextColor(Color.WHITE);
//                break;
//            case 1:
//                mTabIoTv.setTextColor(Color.WHITE);
//                break;
//            case 2:
//                mTabStatTv.setTextColor(Color.WHITE);
//                break;
//        }
//        currentIndex = position;
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    private void changeState(int position){
//        resetTextView();
//        switch (position) {
//            case 0:
//                mTabMainTv.setTextColor(Color.WHITE);
//                break;
//            case 1:
//                mTabIoTv.setTextColor(Color.WHITE);
//                break;
//            case 2:
//                mTabStatTv.setTextColor(Color.WHITE);
//                break;
//        }
//        currentIndex = position;
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.id_tab_inout_ll:
//
//                viewPager.setCurrentItem(1);
//                break;
//            case R.id.id_tab_Main_ll:
//
//                viewPager.setCurrentItem(0);
//                break;
//            case R.id.id_tab_stat_ll:
//
//                viewPager.setCurrentItem(2);
//                break;
//
//        }
//    }
}
