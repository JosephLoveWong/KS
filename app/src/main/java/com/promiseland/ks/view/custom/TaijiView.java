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
 * Created by joseph on 2017/8/21.
 */

public class TaijiView extends View {
    private Paint mPaint = new Paint();

    private float centerX;
    private float centerY;
    private float radius;

    public TaijiView(Context context) {
        this(context, null);
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        radius = Math.min(centerX, centerY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);

        Path path = new Path();
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, radius, Path.Direction.CCW);
        path2.addRect(0, -radius, radius, radius, Path.Direction.CCW);
        path3.addCircle(0, -radius / 2, radius / 2, Path.Direction.CCW);
        path4.addCircle(0, radius / 2, radius / 2, Path.Direction.CCW);

        path.op(path1, path2, Path.Op.DIFFERENCE);
        path.op(path3, Path.Op.UNION);
        path.op(path4, Path.Op.DIFFERENCE);
        canvas.drawPath(path, mPaint);
    }
}
