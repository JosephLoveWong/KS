package com.promiseland.ks;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/6/5.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashReport.initCrashReport(getApplicationContext(), "a54f458154", true);
    }

}
