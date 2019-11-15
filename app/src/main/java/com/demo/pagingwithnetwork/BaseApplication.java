package com.demo.pagingwithnetwork;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
