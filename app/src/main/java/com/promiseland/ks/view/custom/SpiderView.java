package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by joseph on 2017/8/10.
 */

public class SpiderView extends View {
    private List<SpiderData> mDatas;

    private int mDimension;
    private int mCapacity = SpiderData.sCapacity;

    private float mRadius;
    private float mAngle;

    private int centerX;
    private int centerY;
    private Paint mPaint = new Paint();
    private Paint mDataPaint = new Paint();

    public SpiderView(Context context) {
        this(context, null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.GRAY);

        mDataPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mDataPaint.setStyle(Paint.Style.FILL);
        mDataPaint.setColor(Color.BLUE);
        mDataPaint.setAlpha(127);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRadius = Math.min(w, h) / 2;
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDatas == null || mDatas.size() == 0) {
            return;
        }

        mDimension = mDatas.size();
        mAngle = (float) (Math.PI * 2 / mDimension);

        canvas.rotate(-90, centerX, centerY);
        drawPolygon(canvas);
        drawLine(canvas);
        drawData(canvas);
    }

    /**
     * 绘制数据
     */
    private void drawData(Canvas canvas) {
        Path path = new Path();
        path.reset();

        for (int i = 0; i < mDatas.size(); i++) {
            SpiderData data = mDatas.get(i);
            float radius = data.getValue() * mRadius / SpiderData.sCapacity;
            if(i == 0) {
                path.moveTo(centerX + radius, centerY);
            } else {
                float x = (float) (centerX + radius * Math.cos(mAngle * i));
                float y = (float) (centerY + radius * Math.sin(mAngle * i));
                path.lineTo(x, y);
            }
        }
        path.close();

        canvas.drawPath(path, mDataPaint);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        path.reset();

        float r = mRadius / mCapacity;
        for (int i = 0; i < mCapacity; i++) {
            float radius = (i + 1) * r;
            for (int j = 0; j < mDimension; j++) {
                if (j == 0) {
                    path.moveTo(centerX + radius, centerY);
                } else {
                    float x = (float) (centerX + radius * Math.cos(mAngle * j));
                    float y = (float) (centerY + radius * Math.sin(mAngle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
        }

        canvas.drawPath(path, mPaint);
    }

    /**
     * 绘制连线
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        path.reset();

        float radius = mRadius;
        for (int j = 0; j < mDimension; j++) {
            float x = (float) (centerX + radius * Math.cos(mAngle * j));
            float y = (float) (centerY + radius * Math.sin(mAngle * j));
            path.moveTo(centerX , centerY);
            path.lineTo(x, y);
        }

        canvas.drawPath(path, mPaint);
    }

    public List<SpiderData> getDatas() {
        return mDatas;
    }

    public void setDatas(List<SpiderData> datas) {
        mDatas = datas;
        invalidate();
    }


    public static class SpiderData{
        public static int sCapacity = 6;

        private final float value;

        public SpiderData(float value) {
            if(value > sCapacity) {
                value = sCapacity;
            }
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }
}
