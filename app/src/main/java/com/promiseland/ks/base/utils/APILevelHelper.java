package com.promiseland.ks.base.utils;

import android.os.Build;

/**
 * Created by Administrator on 2017/10/26.
 */

public class APILevelHelper {
    public static final boolean isAPILevelMinimal(int minAPILevel) {
        return Build.VERSION.SDK_INT >= minAPILevel;
    }
}
