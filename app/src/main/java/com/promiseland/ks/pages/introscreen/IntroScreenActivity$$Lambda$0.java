package com.promiseland.ks.pages.introscreen;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

final /* synthetic */ class IntroScreenActivity$$Lambda$0 implements OnPreparedListener {
    private final IntroScreenActivity arg$1;

    IntroScreenActivity$$Lambda$0(IntroScreenActivity introScreenActivity) {
        this.arg$1 = introScreenActivity;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.arg$1.lambda$startVideo$0$IntroScreenActivity(mediaPlayer);
    }
}
