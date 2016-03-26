package com.example.hellofreshtest;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by srd on 9/23/2015.
 */
public class HelloFreshApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
