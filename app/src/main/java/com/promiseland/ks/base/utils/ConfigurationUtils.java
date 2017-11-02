package com.promiseland.ks.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.promiseland.ks.R;

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

    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }


    public static int getAppBarOffset(Context context) {
        int offset = getActionBarHeight(context);
        if (APILevelHelper.isAPILevelMinimal(21)) {
            return offset + getStatusBarHeight(context);
        }
        return offset;
    }
}
