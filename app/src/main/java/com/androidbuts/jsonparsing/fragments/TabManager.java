package com.androidbuts.jsonparsing.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabManager extends FragmentPagerAdapter{
    private String fragments[] = {"mon", "tue", "wed", "th", "fri", "sat", "sun"};

    public TabManager(FragmentManager supportFrahgmentManager, Context applicationContext) {
        super(supportFrahgmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return new MainPageFragment();
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
