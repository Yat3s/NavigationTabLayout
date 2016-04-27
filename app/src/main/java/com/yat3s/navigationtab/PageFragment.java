package com.yat3s.navigationtab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Yat3s on 4/26/16.
 * Email: yat3s@opentown.cn
 * Copyright (c) 2015 opentown. All rights reserved.
 */

public class PageFragment extends Fragment {
    private static final String KEY_TITLE = "title";

    @Bind(R.id.text)
    TextView text;

    public static PageFragment newInstance(CharSequence title) {
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, rootView);

        text.setText(getArguments().getString(KEY_TITLE));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
