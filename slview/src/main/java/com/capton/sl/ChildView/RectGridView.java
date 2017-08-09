package com.capton.sl.ChildView;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.GridLayout;
import android.widget.TextView;

import com.capton.sl.Animation.Animation3D;
import com.capton.sl.Util.DisplayUtil;

/**
 * Created by capton on 2017/8/8.
 */

public class RectGridView extends GridLayout {

   private TextView textViews[]=new TextView[9];
    private int mWidth;
    private int childWidth;
    private Interpolator mInterpolator;
    private int duration=800;

    private AlphaAnimation aAnimation=new AlphaAnimation(1f,0f);
    private AlphaAnimation aAnimation2=new AlphaAnimation(1f,0f);
    private AlphaAnimation aAnimation3=new AlphaAnimation(1f,0f);
    private AlphaAnimation aAnimation4=new AlphaAnimation(1f,0f);
    private AlphaAnimation aAnimation5=new AlphaAnimation(1f,0f);

    public RectGridView(Context context, Paint mPaint,int mWidth) {
        super(context);

        this.mWidth= (int) Math.floor(0.7f*((float)mWidth));
        this.childWidth=this.mWidth/3-mWidth*3/100;
        setColumnCount(3);
        setRowCount(3);
        setRotation(45);

        for (int i = 0; i <textViews.length; i++) {
            textViews[i]=new TextView(context);
            textViews[i].setLayoutParams(new MarginLayoutParams(childWidth,childWidth));
            textViews[i].setBackgroundColor(mPaint.getColor());
            addView(textViews[i]);
        }
    }

    public RectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        setMeasuredDimension(2*mWidth*3/100+3*childWidth,2*mWidth*3/100+3*childWidth);
    }

    private boolean isStartPlay;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

            textViews[0].layout(0,0,childWidth,childWidth);
            textViews[1].layout(childWidth+mWidth*3/100,0,mWidth*3/100+2*childWidth,childWidth);
            textViews[2].layout(mWidth*3/100*2+2*childWidth,0,mWidth*3/100*2+3*childWidth,childWidth);
            textViews[3].layout(0,childWidth+mWidth*3/100,childWidth,mWidth*3/100+2*childWidth);
            textViews[4].layout(childWidth+mWidth*3/100,childWidth+mWidth*3/100,mWidth*3/100+2*childWidth,mWidth*3/100+2*childWidth);
            textViews[5].layout(2*mWidth*3/100+2*childWidth,childWidth+mWidth*3/100,2*mWidth*3/100+3*childWidth,mWidth*3/100+2*childWidth);
            textViews[6].layout(0,2*mWidth*3/100+2*childWidth,childWidth,2*mWidth*3/100+3*childWidth);
            textViews[7].layout(childWidth+mWidth*3/100,2*mWidth*3/100+2*childWidth,mWidth*3/100+2*childWidth,2*mWidth*3/100+3*childWidth);
            textViews[8].layout(2*mWidth*3/100+2*childWidth,2*mWidth*3/100+2*childWidth,2*mWidth*3/100+3*childWidth,2*mWidth*3/100+3*childWidth);

            play();


    }

    private void play(){
        aAnimation.setDuration(duration);
        aAnimation2.setDuration(duration);
        aAnimation3.setDuration(duration);
        aAnimation4.setDuration(duration);
        aAnimation5.setDuration(duration);

        aAnimation.setInterpolator(mInterpolator);
        aAnimation2.setInterpolator(mInterpolator);
        aAnimation3.setInterpolator(mInterpolator);
        aAnimation4.setInterpolator(mInterpolator);
        aAnimation5.setInterpolator(mInterpolator);

        aAnimation.setAnimationListener(new MyAnimationListener(textViews[1],textViews[5],null,aAnimation2));
        aAnimation2.setAnimationListener(new MyAnimationListener(textViews[0],textViews[4],textViews[8],aAnimation3));
        aAnimation3.setAnimationListener(new MyAnimationListener(textViews[3],textViews[7],null,aAnimation4));
        aAnimation4.setAnimationListener(new MyAnimationListener(textViews[6],null,null,aAnimation5));
        aAnimation5.setAnimationListener(new MyAnimationListener(textViews[2],null,null,aAnimation));
        textViews[2].startAnimation(aAnimation);
        isStartPlay=true;
    }


   private class MyAnimationListener implements Animation.AnimationListener{
       TextView textView;
       TextView textView2;
       TextView textView3;
        Animation mAnimation;
        private MyAnimationListener(TextView textView,TextView textView2,TextView textView3,Animation animation) {
          this.textView=textView;
          this.textView2=textView2;
          this.textView3=textView3;
            this.mAnimation=animation;
        }

        @Override
        public void onAnimationStart( Animation animation) {
            final Animation3D animation3D=new Animation3D();
            animation3D.setDuration(duration);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(textView!=null) {
                        textView.measure(0,0);
                        animation3D.setCenter(textView.getWidth()/2,textView.getHeight()/2);
                        AnimationSet set=new AnimationSet(true);
                        set.addAnimation(animation3D);
                        set.addAnimation(mAnimation);
                        set.setDuration(duration);
                        textView.startAnimation(set);
                    }
                    if(textView2!=null) {
                        textView2.measure(0,0);
                        animation3D.setCenter(textView2.getWidth()/2,textView2.getHeight()/2);
                        AnimationSet set=new AnimationSet(true);
                        set.addAnimation(animation3D);
                        set.addAnimation(mAnimation);
                        set.setDuration(duration);
                        textView2.startAnimation(set);
                    }
                    if(textView3!=null) {
                        textView3.measure(0,0);
                        animation3D.setCenter(textView3.getWidth()/2,textView3.getHeight()/2);
                        AnimationSet set=new AnimationSet(true);
                        set.addAnimation(animation3D);
                        set.addAnimation(mAnimation);
                        set.setDuration(duration);
                        textView3.startAnimation(set);
                    }
                }
            },duration/8);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public void setInterpolator(Interpolator interpolator){
        this.mInterpolator=interpolator;
    }
}
