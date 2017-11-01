package com.promiseland.ks.base.utils;

import android.util.Log;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

/**
 * Created by Administrator on 2017/11/1.
 */

public class LogUtil {
    public static final String TAG = "LogUtil";
    private static boolean sLogOn = true;

    private static void print(int type, String tag, String msg, Throwable tr) {
        if (sLogOn) {
            switch (type) {
                case VERBOSE:
                    if (tr == null) {
                        Log.v(tag, msg);
                    } else {
                        Log.v(tag, msg, tr);
                    }
                    break;
                case DEBUG:
                    if (tr == null) {
                        Log.d(tag, msg);
                    } else {
                        Log.d(tag, msg, tr);
                    }
                    break;
                case INFO:
                    if (tr == null) {
                        Log.i(tag, msg);
                    } else {
                        Log.i(tag, msg, tr);
                    }
                    break;
                case WARN:
                    if (tr == null) {
                        Log.w(tag, msg);
                    } else {
                        Log.w(tag, msg, tr);
                    }
                    break;
                case ERROR:
                    if (tr == null) {
                        Log.e(tag, msg);
                    } else {
                        Log.e(tag, msg, tr);
                    }
                    break;
            }
        }
    }

    public static void v(String tag, String msg) {
        print(VERBOSE, tag, msg, null);
    }

    public static void v(String tag, String msg, Throwable tr) {
        print(VERBOSE, tag, msg, tr);
    }

    public static void d(String tag, String msg) {
        print(DEBUG, tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable tr) {
        print(DEBUG, tag, msg, tr);
    }

    public static void i(String tag, String msg) {
        print(INFO, tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        print(INFO, tag, msg, tr);
    }

    public static void w(String tag, String msg) {
        print(WARN, tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable tr) {
        print(WARN, tag, msg, tr);
    }

    public static void e(String tag, String msg) {
        print(ERROR, tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        print(ERROR, tag, msg, tr);
    }

}
