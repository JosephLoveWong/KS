package com.promiseland.ks.pages.introscreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.promiseland.ks.R;
import com.promiseland.ks.base.utils.APILevelHelper;
import com.promiseland.ks.base.utils.ConfigurationUtils;
import com.promiseland.ks.base.utils.FieldHelper;
import com.promiseland.ks.pages.BaseActivity;
import com.promiseland.ks.pages.LauncherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class IntroScreenActivity extends BaseActivity {
    private Uri mBackgroundVideoUri;
    @BindView(R.id.btn_been_here)
    Button mBeenHereButton;
    @BindView(R.id.btn_skip)
    TextView mButtonSkip;
    @BindView(R.id.btn_i_am_new)
    Button mIamNewButton;
    private boolean mInitSuccessfull = false;
    private boolean mIsFirstScreen = true;
    MediaPlayer mMediaPlayer;
    private int mSavedPlaybackPosition;
    private boolean mShouldStartFeed = false;
    private SurfaceTextureListener mTextureListener;
    @BindView(R.id.bg_video)
    TextureView mVideoBackground;
    @BindView(R.id.welcome_text)
    TextView mWelcome;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, IntroScreenActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            this.mIsFirstScreen = savedInstanceState.getBoolean("IS_FIRST_SCREEN", true);
        }
        if (APILevelHelper.isAPILevelMinimal(19)) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        } else {
            getWindow().setFlags(1024, 1024);
        }
        initBackgroundVideo(savedInstanceState);
    }

    private void initBackgroundVideo(Bundle savedInstanceState) {
        this.mBackgroundVideoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introscreen_bg);
        if (savedInstanceState != null) {
            this.mSavedPlaybackPosition = (int) savedInstanceState.getLong("STATE_PLAYBACK_POSITION", 0);
        }
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setLooping(true);
        this.mTextureListener = new SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
                IntroScreenActivity.this.mMediaPlayer.setSurface(new Surface(surfaceTexture));
                IntroScreenActivity.this.startVideo();
                IntroScreenActivity.this.centerCropVideo();
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                if (IntroScreenActivity.this.mMediaPlayer.isPlaying()) {
                    IntroScreenActivity.this.mMediaPlayer.pause();
                }
                return true;
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        };
    }

    void startVideo() {
        try {
            this.mMediaPlayer.setDataSource(this, this.mBackgroundVideoUri);
            this.mMediaPlayer.setOnPreparedListener(
                    (payer) -> {
                        if (payer != null) {
                            this.mInitSuccessfull = true;
                            payer.start();
                            if (this.mSavedPlaybackPosition != 0) {
                                payer.seekTo(this.mSavedPlaybackPosition);
                            }
                            payer.setOnPreparedListener(null);
                        }
                    }
            );
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            Timber.e(e, "Error playing video on IntroScreen");
        }
    }

    void centerCropVideo() {
        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        metaRetriever.setDataSource(this, this.mBackgroundVideoUri);
        String height = metaRetriever.extractMetadata(19);
        String width = metaRetriever.extractMetadata(18);
        float videoHeight = FieldHelper.INSTANCE.isEmpty(height) ? (float) this.mVideoBackground.getHeight() : Float.parseFloat(height);
        float videoWidth = FieldHelper.INSTANCE.isEmpty(width) ? (float) this.mVideoBackground.getWidth() : Float.parseFloat(width);
        float viewWidth = (float) this.mVideoBackground.getWidth();
        float viewHeight = (float) this.mVideoBackground.getHeight();
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        if (videoWidth > viewWidth && videoHeight > viewHeight) {
            scaleX = videoWidth / viewWidth;
            scaleY = videoHeight / viewHeight;
        } else if (videoWidth < viewWidth && videoHeight < viewHeight) {
            scaleY = viewWidth / videoWidth;
            scaleX = viewHeight / videoHeight;
        } else if (viewWidth > videoWidth) {
            scaleY = (viewWidth / videoWidth) / (viewHeight / videoHeight);
        } else if (viewHeight > videoHeight) {
            scaleX = (viewHeight / videoHeight) / (viewWidth / videoWidth);
        }
        int pivotPointX = (int) (viewWidth / 2.0f);
        int pivotPointY = (int) (viewHeight / 2.0f);
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY, (float) pivotPointX, (float) pivotPointY);
        this.mVideoBackground.setTransform(matrix);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setOnPreparedListener(null);
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    protected void onResume() {
        super.onResume();
        this.mVideoBackground.setSurfaceTextureListener(this.mTextureListener);
        if (!(!this.mInitSuccessfull || this.mMediaPlayer == null || this.mMediaPlayer.isPlaying())) {
            this.mMediaPlayer.start();
        }
        if (this.mShouldStartFeed) {
            showFeed();
        }
    }

    protected void onPause() {
        super.onPause();
        this.mVideoBackground.setSurfaceTextureListener(null);
        if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("IS_FIRST_SCREEN", this.mIsFirstScreen);
        if (this.mMediaPlayer != null) {
            savedInstanceState.putLong("STATE_PLAYBACK_POSITION", (long) this.mMediaPlayer.getCurrentPosition());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 32465 && resultCode == -1) {
            this.mShouldStartFeed = true;
        }
    }

    public void onBackPressed() {
        showFeed();
    }

    @OnClick
    public void onIAmNewClick() {
//        TrackEvent.event("BUTTON_ONBOARDING_IAMNEW").post();
    }

    @OnClick(R.id.btn_been_here)
    public void onBeenHereClick() {
        showFeed();
//        TrackEvent.event("BUTTON_ONBOARDING_IVEBEENHERE").post();
    }

    @OnClick(R.id.btn_skip)
    public void onSkipClick() {
        showFeed();
//        TrackEvent.event("BUTTON_ONBOARDING_SKIP").post();
    }

    private void showFeed() {
        LauncherActivity.launch(this);
        finish();
    }

    private void animateAppearFromRight(View... views) {
        if (views != null && views.length != 0) {
            TranslateAnimation anim = new TranslateAnimation((float) ConfigurationUtils.getRealScreenSize(this).x, 0.0f, 0.0f, 0.0f);
            anim.setDuration(300);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setFillAfter(true);
            for (View v : views) {
                if (v != null) {
                    v.startAnimation(anim);
                }
            }
        }
    }

}
