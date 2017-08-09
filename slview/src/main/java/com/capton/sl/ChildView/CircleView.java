package com.capton.sl.ChildView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by capton on 2017/8/3.
 */

public class CircleView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public CircleView(Context context, Paint paint, int width, int height) {
        super(context);
        mWidth=width;
        mHeight=height;
        mPaint=paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            setMeasuredDimension(mWidth,mHeight);

    }
}
