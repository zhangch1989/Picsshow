package com.zch.picsshow;

import android.app.Application;

import com.zch.picsshow.tools.PerfHelper;

/**
 * Created by Zch on 2020/4/27 13:49.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PerfHelper.getPerferences(this);
    }
}
