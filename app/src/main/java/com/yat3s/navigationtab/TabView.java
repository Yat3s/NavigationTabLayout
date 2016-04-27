package com.yat3s.navigationtab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 4/22/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class TabView extends FrameLayout {
    @Bind(R.id.icon)
    ImageView icon;
    @Bind(R.id.title)
    TextView title;
    private int mTabViewWidth;
    private String mTitle;
    private int mIconResId;
    private int mAnimationDuration = 1000;
    private int mIndex;

    public TabView(Context context) {
        super(context);
    }

    public TabView(Context context, String title, int iconResId, int index) {
        super(context);
        mTitle = title;
        mIconResId = iconResId;
        mIndex = index;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab, this, true);
        setBackgroundResource(R.color.normal);
        ButterKnife.bind(this);
        icon.setImageDrawable(getResources().getDrawable(mIconResId));
        title.setText(mTitle);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTabViewWidth = getWidth();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public void highLight() {
        setBackgroundColor(Color.parseColor(getResources().getStringArray(R.array.default_preview)[mIndex]));
        startAnimation("tabWidth", (int) (mTabViewWidth * 1.5));
    }

    public void cancelHighLight() {
        setBackgroundResource(R.color.normal);
        startAnimation("tabWidth", mTabViewWidth);
    }

    public void setAnimationDuration(int durationMillis) {
        mAnimationDuration = durationMillis;
    }

    private void startAnimation(String property, int value) {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, property, value).setDuration(mAnimationDuration);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    public int getTabHeight() {
        return this.getLayoutParams().height;
    }

    public int getTabWidth() {
        return this.getLayoutParams().width;
    }

    public void setTabWidth(int width) {
        this.getLayoutParams().width = width;
        this.requestLayout();
    }

    public void setTabHeight(int height) {
        this.getLayoutParams().height = height;
        this.requestLayout();
    }
}
