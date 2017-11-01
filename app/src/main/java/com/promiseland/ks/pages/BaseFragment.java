package com.promiseland.ks.pages;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/1.
 */

public class BaseFragment extends Fragment {

    protected void translateView(View view, int statusBarSize) {
        if (view != null) {
            ViewGroup.LayoutParams containerParams = view.getLayoutParams();
            if (containerParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams relContainerParams = (ViewGroup.MarginLayoutParams) containerParams;
                relContainerParams.setMargins(relContainerParams.leftMargin, relContainerParams.topMargin - statusBarSize, relContainerParams.rightMargin, relContainerParams.bottomMargin);
            }
        }
    }
}
