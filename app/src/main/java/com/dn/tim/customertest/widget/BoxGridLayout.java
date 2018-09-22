/*
 * Created by 动脑科技-Tim on 17-7-18 下午9:41
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-7-18 下午9:41
 */

package com.dn.tim.customertest.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.dn.tim.customertest.R;

public class BoxGridLayout extends ViewGroup{

    private static final int DEFAULT_COUNT = 3;

    private Paint mGridPaint;

    private int mColumCount;
    private int mMaxChildren;

    public BoxGridLayout(Context context) {
        this(context,null);
    }

    public BoxGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoxGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BoxGridLayout, 0, defStyleAttr);

        int strokeWidth = a.getDimensionPixelSize(R.styleable.BoxGridLayout_separatorWidth, 0);
        int strokeColor = a.getColor(R.styleable.BoxGridLayout_separatorColor, Color.WHITE);

        mColumCount = a.getInteger(R.styleable.BoxGridLayout_numColumns , DEFAULT_COUNT);
        mMaxChildren = mColumCount * mColumCount;

        a.recycle();

        mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setColor(strokeColor);
        mGridPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize, heightSize;

        widthSize = getDefaultSize(0, widthMeasureSpec);

        heightSize = getDefaultSize(0, heightMeasureSpec);

        int majorDimension = Math.min(widthSize, heightSize);

        int blockDimension = majorDimension / mColumCount;
        int blockSpec = MeasureSpec.makeMeasureSpec(blockDimension, MeasureSpec.EXACTLY);
        measureChildren(blockSpec, blockSpec);

        setMeasuredDimension(majorDimension, majorDimension);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int row, col, left, top;

        for (int i = 0; i < getChildCount(); i++) {
            row = i / mColumCount;
            col = i % mColumCount;
            View child = getChildAt(i);
            left = col * child.getMeasuredWidth();
            top = row * child.getMeasuredHeight();

            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        //drwa the grid lines
        for (int i = 0; i <= getWidth(); i += (getWidth() / mColumCount)) {
            canvas.drawLine(i, 0, i, getHeight(), mGridPaint);
        }
        for (int i =0; i <= getHeight(); i+= (getHeight() / mColumCount)) {
            canvas.drawLine(0, i, getWidth(), i, mGridPaint);
        }
    }

    @Override
    public void addView(View child) {

        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + "direct children");
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + "direct children");
        }
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + "direct children");
        }
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, LayoutParams params) {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + "direct children");
        }
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + "direct children");
        }
        super.addView(child, width, height);
    }
}
