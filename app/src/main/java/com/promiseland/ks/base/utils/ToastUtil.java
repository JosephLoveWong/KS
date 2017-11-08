package com.promiseland.ks.base.utils;

import android.widget.Toast;

import com.promiseland.ks.BaseApp;
import com.promiseland.ks.view.CustomToast;


/**
 * Created by Administrator on 2017/9/29 0029.
 */
public class ToastUtil {
    private static CustomToast mToast = CustomToast.makeText(BaseApp.getContext(), "", Toast.LENGTH_SHORT);

    public static void showToast(String msg){
        if(mToast != null){
            mToast.setText(msg);
            mToast.show();
        }
    }
}
