package com.promiseland.ks.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.promiseland.ks.R;

/**
 * Created by Administrator on 2017/10/12.
 */

public class CustomToast {
    private Toast mToast;
    private TextView mTextView;

    private CustomToast(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_notify, null);
        mTextView = (TextView) v.findViewById(R.id.text);
        mTextView.setText(text);

        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static CustomToast makeText(Context context, CharSequence text, int duration) {
        return new CustomToast(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }

    public void setText(String text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }
    }
}
