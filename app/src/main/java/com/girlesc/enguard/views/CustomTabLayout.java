package com.girlesc.enguard.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.girlesc.enguard.R;

public class CustomTabLayout extends LinearLayout {

    private ViewPager viewPager;

    private int orientation = -1;

    private int indicatorMargin = -1;
    private int indicatorHeight = -1;
    private int indicatorWidth = -1;
    private int indicatorBackgroundResId;

    private int indicatorSelectedHeight = -1;
    private int indicatorSelectedWidth = -1;
    private int indicatorSelectedBackgroundId;

    private int lastPosition = -1;


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (viewPager.getAdapter() == null) return;
            if (viewPager.getAdapter().getCount() <= 0) return;

            final View selectedIndicator = (lastPosition >= 0) ? getChildAt(lastPosition) : null;
            final View unselectedIndicator = getChildAt(position);

            ValueAnimator unselectedIndicatorAnimator = ValueAnimator.ofInt(indicatorWidth, indicatorSelectedWidth);
            unselectedIndicatorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            unselectedIndicatorAnimator.setDuration(50);
            unselectedIndicatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int progress = (int) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = unselectedIndicator.getLayoutParams();
                    layoutParams.width = progress;
                    unselectedIndicator.setLayoutParams(layoutParams);
                }
            });

            ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(unselectedIndicator, "alpha", .5f, 1f);
            fadeInAnimator.setDuration(50);
            fadeInAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

            ValueAnimator selectedIndicatorAnimator = null;
            ObjectAnimator fadeOutAnimator = null;
            if(selectedIndicator != null) {
                selectedIndicatorAnimator = ValueAnimator.ofInt(indicatorSelectedWidth, indicatorWidth);
                selectedIndicatorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                selectedIndicatorAnimator.setDuration(50);
                selectedIndicatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int progress = (int) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = selectedIndicator.getLayoutParams();
                        layoutParams.width = progress;
                        selectedIndicator.setLayoutParams(layoutParams);
                    }
                });

                fadeOutAnimator = ObjectAnimator.ofFloat(selectedIndicator, "alpha", 1f, .5f);
                fadeOutAnimator.setDuration(50);
                fadeOutAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            }

            AnimatorSet animatorSet = new AnimatorSet();
            if(selectedIndicatorAnimator != null && fadeOutAnimator != null) {
                animatorSet.play(selectedIndicatorAnimator).with(fadeOutAnimator);
                animatorSet.play(unselectedIndicatorAnimator).with(fadeInAnimator);
                animatorSet.play(unselectedIndicatorAnimator).with(selectedIndicatorAnimator);
                animatorSet.start();
            } else {
                animatorSet.play(unselectedIndicatorAnimator).with(fadeInAnimator);
                animatorSet.start();
            }

            unselectedIndicator.setBackgroundResource(indicatorSelectedBackgroundId);
            ViewGroup.LayoutParams layoutParams = unselectedIndicator.getLayoutParams();
            layoutParams.width = indicatorSelectedWidth;
            layoutParams.height = indicatorSelectedHeight;
            unselectedIndicator.setLayoutParams(layoutParams);

            if(selectedIndicator != null) {
                selectedIndicator.setBackgroundResource(indicatorBackgroundResId);
                layoutParams = selectedIndicator.getLayoutParams();
                layoutParams.width = indicatorWidth;
                layoutParams.height = indicatorHeight;
                selectedIndicator.setLayoutParams(layoutParams);
            }

            lastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            int newCount = viewPager.getAdapter().getCount();
            int currentCount = getChildCount();
            if (currentCount != newCount) {
                if (lastPosition < newCount)
                    lastPosition = viewPager.getCurrentItem();
                else
                    lastPosition = -1;
            }
            createIndicators();
        }
    };

    public CustomTabLayout(@NonNull Context context) {
        super(context);
        initializeView(context, null);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray styleAttributesArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout);

        indicatorWidth = styleAttributesArray.getDimensionPixelSize(R.styleable.CustomTabLayout_indicator_width, 0);
        indicatorHeight = styleAttributesArray.getDimensionPixelSize(R.styleable.CustomTabLayout_indicator_height, 0);
        indicatorMargin = styleAttributesArray.getDimensionPixelSize(R.styleable.CustomTabLayout_indicator_margin, 0);
        indicatorBackgroundResId = styleAttributesArray.getResourceId(R.styleable.CustomTabLayout_indicator_drawable, R.drawable.tab_item_selected);

        indicatorSelectedBackgroundId = styleAttributesArray.getResourceId(R.styleable.CustomTabLayout_indicator_drawable_selected, R.drawable.tab_item_unselected);
        indicatorSelectedHeight = styleAttributesArray.getDimensionPixelOffset(R.styleable.CustomTabLayout_indicator_height_selected, 0);
        indicatorSelectedWidth = styleAttributesArray.getDimensionPixelOffset(R.styleable.CustomTabLayout_indicator_width_selected, 0);

        orientation = styleAttributesArray.getInt(R.styleable.CustomTabLayout_indicator_orientation, -1);
        setOrientation((orientation == LinearLayout.VERTICAL) ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);

        setGravity(Gravity.CENTER);

        styleAttributesArray.recycle();

    }

    public void setupWithViewPager(ViewPager v) {
        viewPager = v;
        if (viewPager.getAdapter() != null) {
            lastPosition = -1;
            createIndicators();
            viewPager.removeOnPageChangeListener(onPageChangeListener);
            viewPager.addOnPageChangeListener(onPageChangeListener);
            onPageChangeListener.onPageSelected(viewPager.getCurrentItem());
            viewPager.getAdapter().registerDataSetObserver(dataSetObserver);
        }
    }

    private void createIndicators() {
        removeAllViews();

        if (viewPager.getAdapter() == null) return;

        int count = viewPager.getAdapter().getCount();
        if (count <= 0) return;

        int currentItem = viewPager.getCurrentItem();
        for (int i = 0; i < count; i++) {
            if (currentItem == i) addSelectedIndicator();
            else addIndicator();
        }

    }

    private void addSelectedIndicator() {
        View indicator = new View(getContext());
        indicator.setBackgroundResource(indicatorSelectedBackgroundId);

        addView(indicator, indicatorSelectedWidth, indicatorSelectedHeight);

        LayoutParams layoutParams = (LayoutParams) indicator.getLayoutParams();
        layoutParams.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
        indicator.setLayoutParams(layoutParams);
    }

    private void addIndicator() {
        View indicator = new View(getContext());

        addView(indicator, indicatorWidth, indicatorHeight);

        indicator.setBackgroundResource(indicatorBackgroundResId);
        LayoutParams layoutParams = (LayoutParams) indicator.getLayoutParams();
        layoutParams.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
        indicator.setLayoutParams(layoutParams);
        indicator.setAlpha(.5f);
    }

}
