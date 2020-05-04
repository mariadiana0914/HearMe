package com.girlesc.enguard.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.girlesc.enguard.R;

public class CustomViewPager extends ViewPager {

    private boolean mSwipe = true;

    public CustomViewPager(@NonNull Context context) {
        super(context);
        initializeView(context, null);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {

        TypedArray styleAttributesArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
        try {
            mSwipe = styleAttributesArray.getBoolean(R.styleable.CustomViewPager_swipe, false);
        } finally {
            styleAttributesArray.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mSwipe) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mSwipe) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setSwipe(boolean swipe) { mSwipe = swipe; }
    
}
