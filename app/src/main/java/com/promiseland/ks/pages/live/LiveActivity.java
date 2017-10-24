package com.promiseland.ks.pages.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nagasoft.player.UrlChanged;
import com.nagasoft.player.VJPlayer;

/**
 * Created by Administrator on 2017/10/23.
 */

public class LiveActivity extends AppCompatActivity implements UrlChanged {
    private VJPlayer mVJPlayer = null;

//    static {
//        System.loadLibrary("dbapi");
//        System.loadLibrary("ijkffmpeg");
//        System.loadLibrary("ijkplayer");
//        System.loadLibrary("ijksdl");
//        System.loadLibrary("supertv");
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String str = "vjms://61.216.1.20:8500:5021/live/cid=181#vjms://61.216.1.20:8500:5021/live/cid=181";

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (str.startsWith("vjms://") && str.contains("/live/cid=")) {
                    mVJPlayer = new VJPlayer(LiveActivity.this);
                    mVJPlayer.setURL(new StringBuilder(String.valueOf(str)).append("|001").toString());
//                    mVJPlayer.start();
                    Log.e("MMM", "error 3");
                }
            }
        }).start();
    }

    @Override
    public void onUrlChanged(String str) {
        Log.e("MMM", "error " + str);
    }
}
