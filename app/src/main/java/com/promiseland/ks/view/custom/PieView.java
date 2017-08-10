package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class PieView extends View {
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080};
    private List<PieData> mPieDatas = null;

    private int mWidth;
    private int mHeight;
    private float mStartAngle = 0;

    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mPieDatas == null) return;

        int r = (int) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);
        canvas.translate(mWidth / 2, mHeight / 2);

        float sumValue = 0;
        float startAngle = mStartAngle;

        for (int i = 0; i < mPieDatas.size(); i++) {
            PieData pieData = mPieDatas.get(i);
            pieData.setColor(mColors[i%mColors.length]);
            sumValue += pieData.getValue();
        }

        for (int i = 0; i < mPieDatas.size(); i++) {
            PieData pieData = mPieDatas.get(i);
            float sweepAngle = pieData.getValue() / sumValue * 360;

            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, startAngle, sweepAngle, true, mPaint);
            startAngle += sweepAngle;
        }
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }

    public void setPieDatas(List<PieData> pieDatas) {
        mPieDatas = pieDatas;
        invalidate();
    }

    public static class PieData {
        private String name;
        private float value;
        private int color = 0;

        public PieData(@NonNull String name, @NonNull float value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }

}
