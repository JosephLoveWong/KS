package com.promiseland.ks.pages.live;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vbyte.p2p.OnLoadedListener;

import cn.vbyte.p2p.LiveController;

/**
 * Created by Administrator on 2017/10/23.
 */

public class LiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 异步加载，因为牵扯到线程间通信，所以更加推荐异步加载方式，以免因线程响应太慢造成ANR
        try {
//            LiveController.getInstance().load("14496521645631186", "UHD", 0, new OnLoadedListener() {
            LiveController.getInstance().load("59f69e8638267b46330aa1b2", "UHD", 0, new OnLoadedListener() {
                @Override
                public void onLoaded(Uri uri) {
                    Log.e("mmm", "onLoaded " + uri.toString());
                }
            });

//            LiveController.getInstance().load("59f69e8638267b46330aa1b2", "UHD", new OnLoadedListener() {
//
//                @Override
//                public void onLoaded(Uri uri) {
//                    Log.e("mmm", "onLoaded " + uri.toString());
//                }
//
//            });
        } catch (Exception e) {
            // 如果打印了此exception，说明load/unload没有成对出现
            Log.e("mmm", "error " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveController.getInstance().unload();
    }
}
