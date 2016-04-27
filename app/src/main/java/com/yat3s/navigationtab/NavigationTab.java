package com.yat3s.navigationtab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;


/**
 * Created by Yat3s on 4/22/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class NavigationTab extends LinearLayout {
    private static final int DividerSize = 2; //dp
    private List<TabView> mTabViews;
    private int mLastIndex = -1;
    private int mTabSize;
    private OnTabSelectedListener mTabSelectedListener;

    public NavigationTab(Context context) {
        this(context, null);
    }

    public NavigationTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setTabViews(List<TabView> tabViews, int animationDuration) {
        this.mTabViews = tabViews;
        mTabSize = mTabViews.size();
        setupTabViews(animationDuration);
    }

    public void setTabViews(List<TabView> tabViews) {
        setTabViews(tabViews, 1000);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener tabSelectedListener) {
        mTabSelectedListener = tabSelectedListener;
    }

    private void setupTabViews(int animationDuration) {
        int screenWidth = OPDisplayUtil.getScreenWidth(getContext());
        int tabWidth = screenWidth / mTabSize;
        int dividerSize = OPDisplayUtil.dip2px(getContext(), DividerSize);
        for (int idx = 0; idx < mTabSize; idx++) {
            TabView tabView = mTabViews.get(idx);
            tabView.setAnimationDuration(animationDuration);
            View dividerView = new View(getContext());
            tabView.setLayoutParams(new LinearLayout.LayoutParams(tabWidth, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

            dividerView.setBackgroundResource(R.color.Black);
            dividerView.setLayoutParams(new ViewGroup.LayoutParams(dividerSize, ViewGroup.LayoutParams.MATCH_PARENT));
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnimation((TabView) v);
                }
            });

            mTabViews.add(tabView);
            this.addView(tabView);
//            if (idx < mTabSize - 1) {
//                this.addView(dividerView);
//            }
        }
    }

    private void startAnimation(TabView tabView) {
        int currentIndex = this.indexOfChild(tabView);
        if (null != mTabSelectedListener) {
            mTabSelectedListener.onSelected(currentIndex);
        }
        if (currentIndex != mLastIndex) {
            if (-1 != mLastIndex) {
                mTabViews.get(mLastIndex).cancelHighLight();
            }
            tabView.highLight();
            mLastIndex = currentIndex;
        }
    }

    public void setViewPager(final ViewPager viewPager) {
        // Detect whether ViewPager mode
        if (viewPager == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                startAnimation(mTabViews.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int which) {
                viewPager.setCurrentItem(which);
            }
        });

    }


    public interface OnTabSelectedListener {
        void onSelected(int which);
    }
}
