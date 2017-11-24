package com.promiseland.ks.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.promiseland.ks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/24.
 */

public class DemoActivity extends AppCompatActivity implements RecyclerFragment.OnFragmentInteractionListener{
    private String[] TABS = {"列表", "格子", "交错", "拉伸"};
    private int[] RES_IDS = {
            R.layout.demo_layout_list,
            R.layout.demo_layout_grid,
            R.layout.demo_layout_staggered_grid,
            R.layout.demo_layout_spannable_grid
    };

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mTabLayout.removeAllTabs();

        mViewPager.setAdapter(new RecyclerFragmentPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class RecyclerFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public RecyclerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RecyclerFragment.newInstance(RES_IDS[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TABS[position];
        }

        @Override
        public int getCount() {
            return TABS.length;
        }
    }


}
