package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LeafProgressView extends View{
    // 淡白色
    private static final int WHITE_COLOR = 0xfffde399;
    // 橙色
    private static final int ORANGE_COLOR = 0xffffa800;

    private Paint mPaint = new Paint();

    private int mProgress;
    private int mMax;

    private float mWidth;
    private float mHeight;
    private float mBgRadius;
    private float mRadius;
    private float mBorder;

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
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBgRadius = mHeight / 2;
        mRadius = mBgRadius - mBorder;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(WHITE_COLOR);
        canvas.save();
        canvas.translate(mBgRadius, mBgRadius);
        RectF rectHead = new RectF(-mBgRadius, -mBgRadius, mBgRadius, mBgRadius);
        canvas.drawArc(rectHead, 90, 180, true, mPaint);

        canvas.restore();
        canvas.save();
        canvas.translate(mBgRadius, 0);
        RectF rectBody = new RectF(0, 0, mWidth - mBgRadius * 2, mHeight);
        canvas.drawRect(rectBody, mPaint);

        canvas.restore();
        canvas.save();
        canvas.translate(mWidth - mBgRadius, mBgRadius);
        RectF rectTail = new RectF(-mBgRadius, -mBgRadius, mBgRadius, mBgRadius);
        canvas.drawArc(rectTail, -90, 180, true, mPaint);

        canvas.restore();
        canvas.save();
        mPaint.setColor(ORANGE_COLOR);

        if(mCurrentProgressPosion <= mRadius) {
            canvas.translate(mRadius + mBorder, mRadius + mBorder);
            float angle = (float) Math.toDegrees(Math.acos((mRadius - mCurrentProgressPosion) / mRadius));
            RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectF, 180 - angle, angle * 2, false,  mPaint);
        } else if(mCurrentProgressPosion <= mWidth - mRadius - mBorder * 2){
            canvas.translate(mRadius + mBorder, mRadius + mBorder);
            RectF rectInHead = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectInHead, 90, 180, true, mPaint);

            canvas.restore();
            canvas.save();
            canvas.translate(mRadius + mBorder, mBorder);
            RectF rectInBody = new RectF(0, 0, mCurrentProgressPosion - mRadius, mHeight - mBorder * 2);
            canvas.drawRect(rectInBody, mPaint);
        } else {
            canvas.translate(mRadius + mBorder, mRadius + mBorder);
            RectF rectInHead = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectInHead, 90, 180, true, mPaint);

            canvas.restore();
            canvas.save();
            canvas.translate(mRadius + mBorder, mBorder);
            RectF rectInBody = new RectF(0, 0, mWidth - mRadius * 2 - mBorder * 2, mHeight - mBorder * 2);
            canvas.drawRect(rectInBody, mPaint);

            canvas.restore();
            canvas.save();
            canvas.translate(mWidth - mRadius - mBorder, mRadius + mBorder);
            RectF rectInTail = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectInTail, -90, 180, true, mPaint);
        }
    }


    public int getProgress() {
        return mProgress;
    }

    public synchronized void setProgress(int progress) {
        mProgress = progress;
        mCurrentProgressPosion = (int) (mProgress * (mWidth - mBorder * 2) / mMax);

        invalidate();
    }

    public int getMax() {
        return mMax;
    }

    public synchronized void setMax(int max) {
        mMax = max;
    }

    public void setBorder(float border) {
        mBorder = border;
    }
}
