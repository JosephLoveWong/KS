package com.promiseland.ks.pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.promiseland.ks.R;
import com.promiseland.ks.view.custom.PieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        List<PieView.PieData> mDatas = new ArrayList<>();
        mDatas.add(new PieView.PieData("hello", 30));
        mDatas.add(new PieView.PieData("hello", 80));
        mDatas.add(new PieView.PieData("hello", 20));
        mDatas.add(new PieView.PieData("hello", 70));
        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPieDatas(mDatas);
    }
}
