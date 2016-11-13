package com.example.y1247.workdemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by y1247 on 2016/10/9 0009.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private  static  String[] TITLE  = {"总览","收支","统计"};

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);

        Log.i("adapter", String.valueOf(list.size()));
        fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }
}
