package com.capton.sl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.capton.sl.ChildView.CircleView;
import com.capton.sl.ChildView.CircleViewWrapper;
import com.capton.sl.ChildView.RectGridView;
import com.capton.sl.ChildView.RectangleView;
import com.capton.sl.Exception.MethodUseException;
import com.capton.sl.Util.DisplayUtil;


/**
 * Created by capton on 2017/8/3.
 */

public class SLView extends RelativeLayout {

    private int mWidth;   //主视图宽度
    private int mHeight;  //主视图高度（设计成与宽度相同）
    private int backgroudColor;  //主体颜色
    private int ballColor;       //动画一的小球颜色
    private Interpolator interpolator;  //动画 的插值器，下同
    private Interpolator interpolator2;
    private Interpolator interpolator3;


    public static final String STYLE_ROTATING_CIRCLE="rotatingCircle";     //动画风格常量，下同
    public static final String STYLE_MOVING_RECTANGLE="movingRectangle";
    public static final String STYLE_3D_ROTATING_RECTANGLE="3DRotatingRectangle";
    private String style=STYLE_ROTATING_CIRCLE;

    private Paint mPaint;      //主体画笔
    private Paint mPaint2;     //次级画笔


    public SLView(Context context) {
        this(context,null);
    }

    public SLView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        interpolator=new LinearInterpolator();
        interpolator2=new AccelerateInterpolator();
        interpolator3=new AccelerateInterpolator();

        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.SLView);
        style=ta.getString(R.styleable.SLView_style);
        //判断xml文件中的style字符是否符合标准，若不符合则默认使用动画一
        if(!STYLE_ROTATING_CIRCLE.equals(style)&&!STYLE_MOVING_RECTANGLE.equals(style)&&!STYLE_3D_ROTATING_RECTANGLE.equals(style)){
            style=STYLE_ROTATING_CIRCLE;
        }
        backgroudColor = ta.getColor(R.styleable.SLView_backgroudColor, getResources().getColor(R.color.colorPrimary));
        ballColor = ta.getColor(R.styleable.SLView_ballColor, Color.WHITE);
        ta.recycle();

        mPaint=new Paint();
        mPaint2=new Paint();
        mPaint.setColor(backgroudColor);
        mPaint.setAntiAlias(true);
        mPaint2.setColor(ballColor);
        mPaint2.setAntiAlias(true);
    }

    private boolean isChildLayouted;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        mHeight=mWidth=widthMode==MeasureSpec.EXACTLY?widthSize: DisplayUtil.dip2px(getContext(),DisplayUtil.dip2px(getContext(),50));

        if(!isChildExisted){
            switch (style){
                case STYLE_ROTATING_CIRCLE:
                    CircleView backgroundView = new CircleView(getContext(), mPaint, mWidth, mHeight);
                    CircleView ballView = new CircleView(getContext(), mPaint2, mWidth / 4, mHeight / 4);

                    CircleViewWrapper viewWrapper = new CircleViewWrapper(getContext(), mWidth, backgroundView, ballView);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(mWidth, mHeight);
                    viewWrapper.setLayoutParams(lp);

                    addView(viewWrapper);
                    ((CircleViewWrapper)getChildAt(0)).setInterpolator(interpolator);
                    break;
                case STYLE_MOVING_RECTANGLE:
                    RectangleView rectangleView=new RectangleView(getContext(),mPaint,mWidth);
                    rectangleView.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    addView(rectangleView);
                    ((RectangleView)getChildAt(0)).setInterpolator(interpolator2);
                    break;
                case STYLE_3D_ROTATING_RECTANGLE:
                    RectGridView rectGridView=new RectGridView(getContext(),mPaint,mWidth);
                    addView(rectGridView);
                    ((RectGridView)getChildAt(0)).setInterpolator(interpolator3);
                    break;
            }
            isChildExisted=true;
        }
    }

   private boolean isChildExisted;
    private boolean isStartPlay;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(!isChildLayouted) {
            switch (style){
                case STYLE_ROTATING_CIRCLE:
                    getChildAt(0).layout(0, 0, mWidth, mHeight);
                    break;
                case STYLE_MOVING_RECTANGLE:
                    getChildAt(0).layout(0, 0, mWidth, mHeight);
                    break;
                case STYLE_3D_ROTATING_RECTANGLE:
                    getChildAt(0).layout((int)(0.15f*(float)mWidth), (int)(0.15f*(float)mWidth),(int)(0.85f*(float)mWidth),(int)(0.85f*(float)mWidth));
                    break;
            }


            isChildLayouted=true;
        }
    }


    /**
     * 为动画设置插值器
     * @param interpolator 插值器 例如 LinearInterpolator、 AccelerateInterpolator、FastOutLinearInInterpolator等等
     */
    public void setInterpolator(Interpolator interpolator){
        if(isStyleSeted) {
            switch (style) {
                case STYLE_ROTATING_CIRCLE:
                    this.interpolator = interpolator;
                    break;
                case STYLE_MOVING_RECTANGLE:
                    this.interpolator2 = interpolator;
                    break;
                case STYLE_3D_ROTATING_RECTANGLE:
                    this.interpolator3 = interpolator;
                    break;
            }
        }else {
            try {
                throw new MethodUseException("设置插值器之前先选择动画风格！先调用setStyle(String style)方法！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                throw new MethodUseException("You must use public method 'setStyle(String style)' firstly if you need to set an Interpolator!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isStyleSeted; //是否已设置动画类型的标志变量
    /**
     * 选择动画样式: SLView.STYLE_ROTATING_CIRCLE
     *             SLView.STYLE_MOVING_RECTANGLE
     *             SLView.STYLE_3D_ROTATING_RECTANGLE
     * @param style 动画样式
     */
    public void setStyle(String style){
        if(!STYLE_ROTATING_CIRCLE.equals(style)&&!STYLE_MOVING_RECTANGLE.equals(style)&&!STYLE_3D_ROTATING_RECTANGLE.equals(style)){
            this.style=STYLE_ROTATING_CIRCLE;
        }else {
            this.style=style;
            isStyleSeted=true;
        }
    }

    /**
     * 设置动画主体颜色
     * @param colorRes color资源
     */
    public void setBackgroundColorRes(int colorRes){
        backgroudColor=getResources().getColor(colorRes);
        mPaint.setColor(backgroudColor);
    }

    /**
     * 当动画样式为：STYLE_ROTATING_CIRCLE时，设置小球的颜色
     * @param colorRes color资源
     */
    public void setBallColorRes(int colorRes){
        ballColor=getResources().getColor(colorRes);
        mPaint2.setColor(ballColor);
    }



}
