package com.demo.pagingwithnetwork;

import android.app.Application;
import android.content.Context;
import com.demo.pagingwithnetwork.data.db.MainDatabase;

public class BaseApplication extends Application {

    public static Context mContext = null;

    public static volatile MainDatabase database = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        database = MainDatabase.newInstance();
    }
}
