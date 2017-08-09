package com.capton.slviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capton.sl.SLView;
import com.capton.sl.Util.DisplayUtil;

public class MainActivity extends AppCompatActivity {

    private SLView slView;
    private SLView slView2;
    private SLView slView3;
    private SLView slView4;
    private SLView slView5;
    private SLView slView6;
    private LinearLayout linerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linerlayout= (LinearLayout) findViewById(R.id.container2);
        slView= (SLView) findViewById(R.id.slview);
        slView2= (SLView) findViewById(R.id.slview2);
        slView3= (SLView) findViewById(R.id.slview3);


        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(DisplayUtil.dip2px(this,50), DisplayUtil.dip2px(this,50));
        lp.setMargins(0,0,0, DisplayUtil.dip2px(this,16));
        lp.gravity=Gravity.CENTER_HORIZONTAL;

        TextView tv=new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(16);
        tv.setText("Java代码生成");
        tv.setTextColor(getResources().getColor(R.color.colorAccent));

        slView4=new SLView(this);
        slView4.setLayoutParams(lp);
        slView4.setStyle(SLView.STYLE_ROTATING_CIRCLE);
        slView4.setInterpolator(new AccelerateInterpolator());
        slView4.setBackgroundColorRes(R.color.colorAccent);


        slView5=new SLView(this);
        slView5.setLayoutParams(lp);
        slView5.setStyle(SLView.STYLE_MOVING_RECTANGLE);
        slView5.setInterpolator(new LinearInterpolator());
        slView5.setBackgroundColorRes(R.color.colorAccent);


        slView6=new SLView(this);
        slView6.setLayoutParams(lp);
        slView6.setStyle(SLView.STYLE_3D_ROTATING_RECTANGLE);
        slView6.setBackgroundColorRes(R.color.colorAccent);


        linerlayout.addView(tv);
        linerlayout.addView(slView4);
        linerlayout.addView(slView5);
        linerlayout.addView(slView6);




    }

}
