package com.promiseland.ks.pages.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.promiseland.ks.R;
import com.promiseland.ks.base.Constants;
import com.promiseland.ks.base.model.ActivityData;
import com.promiseland.ks.base.utils.ActivityDataHelper;
import com.promiseland.ks.pages.LauncherActivity;

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
            LauncherActivity.launch(SplashActivity.this);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initServices();
        handleLaunchParameter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCountDownTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCountDownTimer.cancel();
    }

    private void initServices() {
//        this.mUserService.handleAppStart();
//        this.mInstallationDataService.updateInstallation();
//        this.mCommentService.uploadRemainingImagesAndCleanUp();
//        startService(new Intent(this, KSFirebaseInstanceIdService.class));
    }

    private void handleLaunchParameter() {
        Intent intent = getIntent();
        String searchString = "";
        ActivityData activityData = null;
        if (intent != null) {
            String action = intent.getAction();
            if (intent.getData() != null) {
                activityData = ActivityDataHelper.getActivityData(intent.getDataString(), intent.getData().getPathSegments());
                if (!ActivityDataHelper.isInvalid(activityData)) {
                    ActivityDataHelper.trackDeeplink(activityData);
                }
            } else if ("android.intent.action.SEARCH".equals(action) || "com.google.android.gms.actions.SEARCH_ACTION".equals(action)) {
                searchString = intent.getStringExtra("query");
            }
        }
        if (!isTaskRoot() && ActivityDataHelper.isInvalid(activityData) && TextUtils.isEmpty(searchString)) {
            finish();
        }
    }


}
