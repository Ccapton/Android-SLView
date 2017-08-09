package com.capton.sl.ChildView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by capton on 2017/8/3.
 */

public class CircleViewWrapper extends RelativeLayout{
    private int mWidth;
    private Interpolator mInterpolator;
    public CircleViewWrapper(Context context ,int mWidth,View view,View view2) {
        this(context,null);
        this.mWidth=mWidth;
        addView(view);
        addView(view2);
    }

    public CircleViewWrapper(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth,mWidth);
    }

    private boolean isStartPlay;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(0,0,mWidth,mWidth);
        getChildAt(1).layout(mWidth*3/8,mWidth/12,5*mWidth/8,mWidth/3);

        play();

    }

    public Interpolator getInterpolator() {
        return mInterpolator;
    }

    public void setInterpolator(Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }

    private RotateAnimation animation;

    private void play(){
        animation = new RotateAnimation(0, 3600, mWidth / 8, mWidth*5/12);
        animation.setDuration(10000);
        animation.setRepeatCount(-1);
        animation.setInterpolator(mInterpolator);
        getChildAt(1).setAnimation(animation);
        animation.start();
        isStartPlay=true;
    }

}
