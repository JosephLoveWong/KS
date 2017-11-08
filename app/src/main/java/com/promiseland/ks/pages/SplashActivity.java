package com.promiseland.ks.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.promiseland.ks.BaseApp;
import com.promiseland.ks.R;
import com.promiseland.ks.base.Constants;

/**
 * Created by Administrator on 2017/10/17.
 */

public class SplashActivity extends AppCompatActivity {
    private CountDownTimer mCountDownTimer = new CountDownTimer(Constants.Splash.DELAY_TIMER, Constants.Time.ONE_SECOND) {
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO
        }

        @Override
        public void onFinish() {
            // TODO
            Intent intent = new Intent();
            intent.setClass(BaseApp.getContext(), LauncherActivity.class);
//            intent.setClass(BaseApp.getContext(), LiveActivity.class);
            startActivity(intent);

            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // TODO
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO
//        mCountDownTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

//        mCountDownTimer.cancel();
    }

    public void vbyte(View view) {
//        ToastUtil.showToast("vbyte");
//
//        Intent intent = new Intent(this, LiveActivity.class);
//        startActivity(intent);
    }

    public void startKS(View view) {
        Intent intent = new Intent();
        intent.setClass(BaseApp.getContext(), LauncherActivity.class);
        startActivity(intent);
    }
}
