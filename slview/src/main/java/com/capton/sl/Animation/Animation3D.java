package com.capton.sl.Animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by capton on 2017/8/8.
 */

public class Animation3D extends Animation {
     private float mCenterX = 0;
     private float mCenterY = 0;

     public void setCenter(float centerX, float centerY) {
         mCenterX = centerX;
         mCenterY = centerY;
     }

    //        tv.measure(0, 0);
//        rotate.setCenter(tv.getMeasuredWidth() / 2, tv.getMeasuredHeight() / 2);
//        rotate.setFillAfter(true); // 使动画结束后定在最终画面，如果不设置为true，则将会回到初始画面

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        Camera camera = new Camera();
        camera.save();
        camera.rotateY(180 * interpolatedTime);
        camera.getMatrix(matrix);
        camera.restore();
         matrix.preTranslate(-mCenterX, -mCenterY);
         matrix.postTranslate(mCenterX, mCenterY);
    }
}