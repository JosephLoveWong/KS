package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joseph on 2017/8/21.
 */

/**
 * TODO 后续完善
 */
public class SearchView extends View {

    private Paint mPaint = new Paint();

    private float centerX;
    private float centerY;

    private Matrix matrix = new Matrix();

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);

        Path path_search = new Path();
        RectF oval_search = new RectF(-50, -50, 50, 50);
        path_search.addArc(oval_search, 45, 359.9f);

        Path path_circle = new Path();
        RectF oval_circle = new RectF(-100, -100, 100, 100);
        path_circle.addArc(oval_circle, 45, -359.9f);

        float[] pos = new float[2];
        PathMeasure pathMeasure = new PathMeasure(path_circle, false);
        pathMeasure.getPosTan(0, pos, null);
        path_search.lineTo(pos[0], pos[1]);

        canvas.drawPath(path_search, mPaint);
        canvas.drawPath(path_circle, mPaint);
    }
}
