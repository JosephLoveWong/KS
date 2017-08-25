package com.promiseland.ks.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.promiseland.ks.R;

/**
 * Created by joseph on 2017/8/21.
 */

public class PathMeasureView extends View {
    private Paint mPaint = new Paint();

    private float centerX;
    private float centerY;
    private float radius;
    private float pos;
    private Matrix matrix = new Matrix();
    private Bitmap bitmap;
    private BitmapFactory.Options options;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);

        options = new BitmapFactory.Options();
        options.inSampleSize = 20;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        radius = (float) (Math.min(centerX, centerY) * 0.8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);
        Path path = new Path();
        path.addCircle(0, 0, radius, Path.Direction.CCW);
        PathMeasure pathMeasure = new PathMeasure(path, true);

        pos += 0.005;
        if(pos >= 1) {
            pos = 0;
        }
        pathMeasure.getMatrix(pathMeasure.getLength() * pos, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(bitmap, matrix, mPaint);

        invalidate();
    }
}
