package com.girlesc.enguard.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SeekBarRotator extends ViewGroup {
    public SeekBarRotator(Context context) {
        super(context);
    }

    public SeekBarRotator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeekBarRotator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SeekBarRotator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View child = getChildAt(0);

        if (child.getVisibility() != GONE) {
            measureChild(child, heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(child.getMeasuredHeightAndState(), child.getMeasuredWidthAndState());
        } else {
            setMeasuredDimension(resolveSizeAndState(0, widthMeasureSpec, 0), resolveSizeAndState(0, heightMeasureSpec, 0));
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        final View child = getChildAt(0);
        if (child.getVisibility() != GONE) {
            child.setPivotX(0);
            child.setPivotY(0);
            child.setRotation(-90);

            int width = i2 - i;
            int height = i3 - i1;
            int childWidth = height;
            int childHeight = width;

            child.layout(0, height, childWidth, height + childHeight);
        }
    }
}
