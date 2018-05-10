package com.koch.java.kochjava.base;

import android.app.Activity;
import android.app.Application;

import com.koch.java.kochjava.dependencies.Dependencies;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class Core extends Application implements HasActivityInjector {
    @Inject DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Dependencies.initialize(this);
    }
}
