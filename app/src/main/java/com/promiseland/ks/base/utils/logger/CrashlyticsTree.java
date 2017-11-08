package com.promiseland.ks.base.utils.logger;


import com.promiseland.ks.base.utils.LogUtil;

import timber.log.Timber.Tree;

public class CrashlyticsTree extends Tree {
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority >= 5) {
            LogUtil.e(tag, " TODO : handle crash ");
//            Crashlytics.log(priority, tag, message);
            if (t != null) {
//                Crashlytics.logException(t);
            }
        }
    }
}
