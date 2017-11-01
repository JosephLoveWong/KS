package com.promiseland.ks.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

public class ConfigurationUtils {

    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        return height;
    }

    public static void adjustToolbarHeight(Context context, ViewGroup toolbar) {
        if (toolbar != null) {
            int statusBarHeight = getStatusBarHeight(context);
            toolbar.setPadding(toolbar.getPaddingLeft(), toolbar.getPaddingTop() + statusBarHeight, toolbar.getPaddingRight(), toolbar.getPaddingBottom());
            toolbar.getLayoutParams().height += statusBarHeight;
        }
    }
}
