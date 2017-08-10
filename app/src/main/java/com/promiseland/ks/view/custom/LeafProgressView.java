package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LeafProgressView extends View{
    // 橙色
    private static final int ORANGE_COLOR = 0xffffa800;

    private Paint mPaint = new Paint();

    private int mProgress;
    private int mMax;

    private float mRadius;
    private float mWidth;
    private float mHeight;

    private int mCurrentProgressPosion;

    public LeafProgressView(Context context) {
        this(context, null);
    }

    public LeafProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        RectF bg = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRect(bg, mPaint);

        mPaint.setColor(Color.RED);

        Log.d("Demo", mCurrentProgressPosion + " -- " + mRadius);
        if(mCurrentProgressPosion < mRadius) {
//            canvas.translate(mRadius, mRadius);
            canvas.translate(300, mRadius);
            float angle = (float) Math.toDegrees(Math.acos((mRadius - mCurrentProgressPosion) / mRadius));
            RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectF, 180 - angle, angle * 2, false,  mPaint);
        }
    }


    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        mCurrentProgressPosion = (int) (progress * mWidth / mMax);

        invalidate();
    }

    public int getMax() {
        return mMax;
    }

    public synchronized void setMax(int max) {
        mMax = max;
    }
}
