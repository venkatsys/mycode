package com.rssfeed.myapp;

import android.app.Application;
import android.content.Context;

import com.rssfeed.myapp.contentprovider.HinduContentProvider;

/**
 * Created by venkatesanr on 25-05-2015.
 */
public class MyApplication extends Application {
    private static Context context;
    private HinduContentProvider dbContentProvider = null;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        dbContentProvider = new HinduContentProvider();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
