package com.yat3s.navigationtab;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.tab)
    NavigationTab tab;
    @Bind(R.id.content_vp)
    ViewPager contentVp;

    String[] titles = new String[]{"Movie", "Sport", "Dance", "News", "Car"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<TabView> tabs = new ArrayList<>();
        for (int idx = 0; idx < titles.length; idx++) {
            tabs.add(new TabView(this, titles[idx], R.mipmap.ic_launcher, idx));
        }
        tab.setTabViews(tabs);

        tab.setViewPager(contentVp);

        contentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(getPageTitle(position));
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        contentVp.setCurrentItem(1);
    }
}
