package com.promiseland.ks;

import android.app.Application;
import android.content.Context;

import com.promiseland.ks.base.utils.logger.CrashlyticsTree;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import timber.log.Timber;

/**
 * Created by Administrator on 2017/6/5.
 */

public class BaseApp extends Application {
    private static final String TAG = "BaseApp";

    // 初始化VbyteP2PModule的相关变量
//    final String APP_ID = "59f68a6d38267b46330aa1b1";
//    final String APP_KEY = "60u2r1AHTXhiX7ZL";
//    final String APP_SECRET = "M5p0mnOoC5cNwCZPRa6gnOJgrBFXz4qA";

    // vbyte demo
    final String APP_ID = "577cdcabe0edd1325444c91f";
    final String APP_KEY = "G9vjcbxMYZ5ybgxy";
    final String APP_SECRET = "xdAEKlyF9XIjDnd9IwMw2b45b4Fq9Nq9";

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashReport.initCrashReport(getApplicationContext(), "a54f458154", true);
        LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }

        Timber.plant(new CrashlyticsTree());

//        // 初始化P2P模块
//        try {
//            VbyteP2PModule.create(this.getBaseContext(), APP_ID, APP_KEY, APP_SECRET);
//            VbyteP2PModule.enableDebug();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
