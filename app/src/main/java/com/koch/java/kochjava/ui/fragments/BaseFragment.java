package com.koch.java.kochjava.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment extends Fragment {
    @LayoutRes private int layoutResId = 0;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    protected void setContentView(@LayoutRes int resLayout) {
        this.layoutResId = resLayout;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if(layoutResId == 0)
            throw new IllegalStateException("No layout resource id to inflate from. Use setContentView to set it.");

        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBindView(view);
    }

    public void onBindView(@Nullable View root){
        if(root != null)
            ButterKnife.bind(this, root);
    }

    public String tag(){
        return getClass().getSimpleName();
    }
}
