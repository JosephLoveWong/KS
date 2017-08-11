package com.promiseland.ks.pages;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.promiseland.ks.R;
import com.promiseland.ks.view.custom.LeafProgressView;
import com.promiseland.ks.view.custom.PieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoDemo();
//        pieDemo();
    }

    Handler mHandler = new Handler();
    private void demoDemo() {
        final LeafProgressView view
                = (LeafProgressView) findViewById(R.id.demoView);
        view.setMax(500);
        view.setProgress(0);
        view.setBorder(10);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view.getProgress() >= view.getMax()) return;

                view.setProgress(view.getProgress() + 5);
                mHandler.postDelayed(this, 100);
            }
        }, 100);

    }

    private void pieDemo() {
        List<PieView.PieData> mDatas = new ArrayList<>();
        mDatas.add(new PieView.PieData("hello", 30));
        mDatas.add(new PieView.PieData("hello", 80));
        mDatas.add(new PieView.PieData("hello", 20));
        mDatas.add(new PieView.PieData("hello", 70));
        PieView pieView = (PieView) findViewById(R.id.demoView);
        pieView.setPieDatas(mDatas);
    }
}
