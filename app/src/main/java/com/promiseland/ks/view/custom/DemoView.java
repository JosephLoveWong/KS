package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joseph on 2017/8/10.
 */

public class DemoView extends View {
    private Paint mPaint = new Paint();

    private int mDimension = 5;
    private int mCapacity = 5;

    private float mRadius;
    private float mAngle;

    private int centerX;
    private int centerY;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
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

        mAngle = (float) (Math.PI * 2 / mDimension);

        canvas.rotate(-90, centerX, centerY);
        drawPolygon(canvas);
        drawLine(canvas);
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
}
