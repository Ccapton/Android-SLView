package com.capton.sl.ChildView;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by capton on 2017/8/8.
 */

public class RectangleView extends RelativeLayout {
    private Paint mPaint;
    private int mWidth;

    private Interpolator mInterpolator=new AccelerateInterpolator();

    public Interpolator getInterpolator() {
        return mInterpolator;
    }

    public void setInterpolator(Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }

    private TranslateAnimation tAnimation2_1;
    private TranslateAnimation tAnimation2_2;
    private TranslateAnimation tAnimation2_3;
    private TranslateAnimation tAnimation2_4;

    private TranslateAnimation tAnimation;
    private TranslateAnimation tAnimation2;
    private TranslateAnimation tAnimation3;
    private TranslateAnimation tAnimation4;

    private RotateAnimation rAnimation;
    private RotateAnimation rAnimation2;
    private RotateAnimation rAnimation3;
    private RotateAnimation rAnimation4;

    public RectangleView(Context context, Paint paint , int mWidth) {
        super(context);
        mPaint=paint;
        this.mWidth=mWidth;
        tv = new TextView(getContext());
        tv2 = new TextView(getContext());
        tv3 = new TextView(getContext());
        tv.setLayoutParams(new RelativeLayout.LayoutParams(mWidth,mWidth));
        tv2.setLayoutParams(new RelativeLayout.LayoutParams(mWidth,mWidth));
        tv3.setLayoutParams(new RelativeLayout.LayoutParams(mWidth/4,mWidth/4));
        tv.setBackgroundColor(mPaint.getColor());
        tv2.setBackgroundColor(mPaint.getColor());
        tv3.setBackgroundColor(mPaint.getColor());
        addView(tv);
        addView(tv2);
        addView(tv3);
    }

private TextView tv;
private TextView tv2;
private TextView tv3;
    private boolean measureOnce;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        mWidth=widthMode==MeasureSpec.EXACTLY?widthSize:300;

        setMeasuredDimension(mWidth, mWidth);

    }

    private boolean isStartPlay;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

         getChildAt(0).layout(-mWidth*3/4,-mWidth*3/4,mWidth/4,mWidth/4);
         getChildAt(1).layout(mWidth*3/4,mWidth*3/4,mWidth*7/4,mWidth*7/4);
         getChildAt(2).layout(mWidth*3/8,mWidth*3/8,mWidth*5/8,mWidth*5/8);

         play();
    }

    public void play(){
        rAnimation=new RotateAnimation(0,180,mWidth/8,mWidth/8);
        rAnimation2=new RotateAnimation(180,360,mWidth/8,mWidth/8);
        rAnimation3=new RotateAnimation(360,540,mWidth/8,mWidth/8);
        rAnimation4=new RotateAnimation(540,720,mWidth/8,mWidth/8);

        rAnimation.setDuration(800);
        rAnimation2.setDuration(800);
        rAnimation3.setDuration(800);
        rAnimation4.setDuration(800);

        rAnimation.setInterpolator(mInterpolator);
        rAnimation2.setInterpolator(mInterpolator);
        rAnimation3.setInterpolator(mInterpolator);
        rAnimation4.setInterpolator(mInterpolator);

        rAnimation.setAnimationListener(new MyAnimationListener(getChildAt(2),rAnimation2));
        rAnimation2.setAnimationListener(new MyAnimationListener(getChildAt(2),rAnimation3));
        rAnimation3.setAnimationListener(new MyAnimationListener(getChildAt(2),rAnimation4));
        rAnimation4.setAnimationListener(new MyAnimationListener(getChildAt(2),rAnimation));
        getChildAt(2).setAnimation(rAnimation);
        rAnimation.start();

        tAnimation=new TranslateAnimation(0,3*mWidth/2,0,0);
        tAnimation2=new TranslateAnimation(3*mWidth/2,3*mWidth/2,0,3*mWidth/2);
        tAnimation3=new TranslateAnimation(3*mWidth/2,0,3*mWidth/2,3*mWidth/2);
        tAnimation4=new TranslateAnimation(0,0,3*mWidth/2,0);

        tAnimation.setDuration(800);
        tAnimation2.setDuration(800);
        tAnimation3.setDuration(800);
        tAnimation4.setDuration(800);

        tAnimation.setInterpolator(mInterpolator);
        tAnimation2.setInterpolator(mInterpolator);
        tAnimation3.setInterpolator(mInterpolator);
        tAnimation4.setInterpolator(mInterpolator);

        tAnimation.setAnimationListener(new MyAnimationListener(getChildAt(0),tAnimation2));
        tAnimation2.setAnimationListener(new MyAnimationListener(getChildAt(0),tAnimation3));
        tAnimation3.setAnimationListener(new MyAnimationListener(getChildAt(0),tAnimation4));
        tAnimation4.setAnimationListener(new MyAnimationListener(getChildAt(0),tAnimation));
        getChildAt(0).setAnimation(tAnimation);
        tAnimation.start();

        tAnimation2_1=new TranslateAnimation(0,-3*mWidth/2,0,0);
        tAnimation2_2=new TranslateAnimation(-3*mWidth/2,-3*mWidth/2,0,-3*mWidth/2);
        tAnimation2_3=new TranslateAnimation(-3*mWidth/2,0,-3*mWidth/2,-3*mWidth/2);
        tAnimation2_4=new TranslateAnimation(0,0,-3*mWidth/2,0);

        tAnimation2_1.setDuration(800);
        tAnimation2_2.setDuration(800);
        tAnimation2_3.setDuration(800);
        tAnimation2_4.setDuration(800);

        tAnimation2_1.setInterpolator(mInterpolator);
        tAnimation2_2.setInterpolator(mInterpolator);
        tAnimation2_3.setInterpolator(mInterpolator);
        tAnimation2_4.setInterpolator(mInterpolator);

        tAnimation2_1.setAnimationListener(new MyAnimationListener(getChildAt(1),tAnimation2_2));
        tAnimation2_2.setAnimationListener(new MyAnimationListener(getChildAt(1),tAnimation2_3));
        tAnimation2_3.setAnimationListener(new MyAnimationListener(getChildAt(1),tAnimation2_4));
        tAnimation2_4.setAnimationListener(new MyAnimationListener(getChildAt(1),tAnimation2_1));
        getChildAt(1).setAnimation(tAnimation2_1);
        tAnimation2_1.start();

        isStartPlay=true;
    }

    private class MyAnimationListener implements Animation.AnimationListener{
        private View view;
        private Animation animation;

        public MyAnimationListener(View view, Animation animation) {
            this.view = view;
            this.animation = animation;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            view.setAnimation(this.animation);
            this.animation.start();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    /*
    *   draw方法绘制矩形
    *    private boolean isAtRightBottom;
    *    private int speed=4;
    *       if(!isAtRightBottom) {
            if (right < mWidth) {
                right+=speed;
                canvas.drawRect(0, 0, right,mWidth/4, mPaint);
            } else {
                if (left < mWidth / 2) {
                    left+=speed;
                    canvas.drawRect(left, 0, mWidth, mWidth/4, mPaint);
                } else {
                    if (bottom < mWidth) {
                        bottom+=speed;
                        canvas.drawRect(3*mWidth/4, 0, mWidth, bottom, mPaint);
                    } else {
                        if (top < mWidth / 2) {
                                top+=speed;
                                canvas.drawRect(3 * mWidth / 4, top, mWidth, bottom, mPaint);
                        } else {
                            canvas.drawRect(3*mWidth/4, mWidth/2, mWidth, bottom, mPaint);
                            isAtRightBottom = true;
                        }
                    }
                }
            }
        }else {
            if(left>0) {
                left-=4;
                canvas.drawRect(left,3*mWidth/4, right, bottom, mPaint);
            }else {
                if(right>mWidth/2){
                    right-=speed;
                    canvas.drawRect(left,3*mWidth/4, right, bottom, mPaint);
                }else {
                    if(top>0){
                        top-=speed;
                        canvas.drawRect(left,top,mWidth/4, bottom, mPaint);
                    }else {
                        if(bottom>mWidth/2){
                                bottom-=speed;
                                canvas.drawRect(left, top, mWidth / 4, bottom, mPaint);
                        }else {
                            canvas.drawRect(0,0, mWidth/2,mWidth/4, mPaint);
                            isAtRightBottom=false;
                        }
                    }
                }
            }
        }
    *
    *
    * */
}
