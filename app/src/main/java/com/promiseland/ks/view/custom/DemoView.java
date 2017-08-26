package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by joseph on 2017/8/21.
 */

public class DemoView extends View {
    private Paint mPaint = new Paint();

    private int centerX;
    private int centerY;

    private int radiusOutter;
    private int radiusInner;

    private Path leftPath = new Path();
    private Path upPath = new Path();
    private Path rightPath = new Path();
    private Path downPath = new Path();
    private Path centerPath = new Path();

    private Region leftRegion = new Region();
    private Region upRegion = new Region();
    private Region rightRegion = new Region();
    private Region downRegion = new Region();
    private Region centerRegion = new Region();

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Region globle = new Region(0, 0, w, h);
        centerX = w / 2;
        centerY = h / 2;

        radiusOutter = (int) (Math.min(centerX, centerY) * 0.8);
        radiusInner = radiusOutter / 2;

        RectF outterRectF = new RectF(-radiusOutter, -radiusOutter, radiusOutter, radiusOutter);
        RectF innerRectF = new RectF(-radiusInner, -radiusInner, radiusInner, radiusInner);
        float outterSweepAngle = 90;
        float innerSweepAngle = -90;

        centerPath.addCircle(0, 0, radiusInner, Path.Direction.CCW);
        centerRegion.setPath(centerPath, globle);

        leftPath.addArc(outterRectF, 135, outterSweepAngle);
        leftPath.arcTo(innerRectF, 225, innerSweepAngle);
        leftPath.close();
        leftRegion.setPath(leftPath, globle);

        upPath.addArc(outterRectF, 225, outterSweepAngle);
        upPath.arcTo(innerRectF, 315, innerSweepAngle);
        upPath.close();
        upRegion.setPath(upPath, globle);

        rightPath.addArc(outterRectF, 315, outterSweepAngle);
        rightPath.arcTo(innerRectF, 45, innerSweepAngle);
        rightPath.close();
        rightRegion.setPath(rightPath, globle);

        downPath.addArc(outterRectF, 45, outterSweepAngle);
        downPath.arcTo(innerRectF, 135, innerSweepAngle);
        downPath.close();
        downRegion.setPath(downPath, globle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);
        canvas.drawPath(centerPath, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(leftPath, mPaint);
        canvas.drawPath(upPath, mPaint);
        canvas.drawPath(rightPath, mPaint);
        canvas.drawPath(downPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
        }

        return super.onTouchEvent(event);
    }
}
