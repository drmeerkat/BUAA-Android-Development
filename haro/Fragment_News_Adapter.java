package com.wen.xin.xiao.ge.gao.haro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

public class Fragment_News_Adapter extends FragmentPagerAdapter {

    private List<Fragment> sectionFragments;

    public Fragment_News_Adapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.sectionFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return sectionFragments.get(position);
    }

    @Override
    public int getCount() {
        return sectionFragments.size();
    }


    // Set Label for Section Tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "推荐";
            case 1:
                return "发售";
            case 2:
                return "评测";
            case 3:
                return "游戏";
            case 4:
                return "连载";
        }
        return null;
    }
}
