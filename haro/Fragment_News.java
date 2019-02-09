package com.wen.xin.xiao.ge.gao.haro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * For Home page's section bar use
 * A placeholder fragment containing a simple view.
 */
public class Fragment_News extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private Fragment_News_Adapter mSectionsPagerAdapter;
    private List<Fragment> sectionFragments;
    private ViewPager mSectionViewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab section_one;
    private TabLayout.Tab section_two;
    private TabLayout.Tab section_three;
    private TabLayout.Tab section_four;
    private TabLayout.Tab section_five;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_layout, container, false);
        mSectionViewPager = (ViewPager) rootView.findViewById(R.id.news_pager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tab_main);
        init_Tabs();
        return rootView;
    }

    public TabLayout getTabLayout () {

        if (tabLayout == null) {
            init_Tabs();
            Log.i("Information","TabLayout is null");
            return tabLayout;
        }
        return tabLayout;
    }

    public Fragment_News_TabOne getTabOne() {
        if (sectionFragments != null){
            return (Fragment_News_TabOne) sectionFragments.get(0);
        }
        else {
            Log.w("Information","tabone is null");
            return null;
        }
    }


    private void init_Tabs() {
        sectionFragments = new ArrayList<>();
        sectionFragments.add(new Fragment_News_TabOne());
        sectionFragments.add(new Fragment_News_TabTwo());
        sectionFragments.add(new Fragment_News_TabThree());
        sectionFragments.add(new Fragment_News_TabFour());
        sectionFragments.add(new Fragment_News_TabFive());

        mSectionsPagerAdapter = new Fragment_News_Adapter(getActivity().getSupportFragmentManager(), sectionFragments);
        mSectionViewPager.setAdapter(mSectionsPagerAdapter);
        mSectionViewPager.setOffscreenPageLimit(5);

        tabLayout.setupWithViewPager(mSectionViewPager);
        section_one = tabLayout.getTabAt(0);
        section_two = tabLayout.getTabAt(1);
        section_three = tabLayout.getTabAt(2);
        section_four = tabLayout.getTabAt(3);
        section_five = tabLayout.getTabAt(4);

    }
}
