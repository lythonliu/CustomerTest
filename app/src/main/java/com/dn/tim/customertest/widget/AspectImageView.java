/*
 * Created by 动脑科技-Tim on 17-7-18 下午5:38
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-7-18 下午5:38
 */

package com.dn.tim.customertest.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class AspectImageView extends ImageView{


    public AspectImageView(Context context) {
        this(context,null);
    }

    public AspectImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredSize;
        float aspect;

        Drawable d = getDrawable();
        if (d == null){
            desiredSize = 0;
            aspect = 1f;
        } else {
            desiredSize = d.getIntrinsicWidth();
            aspect = (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight();
        }
        //
        int widthSize = View.resolveSize(desiredSize, widthMeasureSpec);

        int heightSize = (int) (widthSize / aspect);

        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.EXACTLY) {
            if (heightSize > specSize) {
                heightSize = specSize;
                widthSize = (int) (heightSize * aspect);
            }
        }

        setMeasuredDimension(widthSize, heightSize);

    }
}
