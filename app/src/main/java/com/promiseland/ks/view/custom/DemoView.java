package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joseph on 2017/8/10.
 */

public class DemoView extends View {
    private Paint mPaint = new Paint();

    private float mWidth;
    private float mHeight;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
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

        canvas.translate(mWidth / 2, mHeight / 2);
        int r = (int) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r , r, r);
        canvas.drawArc(rectF, 180 - 30, 60, false, mPaint);
    }
}
