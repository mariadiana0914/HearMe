package com.girlesc.enguard.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.girlesc.enguard.R;

public class CustomSeekBar extends LinearLayout {

    private Context mContext;

    public CustomSeekBar(Context context) {
        super(context);
        initializeViews(context, null);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);
    }

    public CustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);
    }

    private void initializeViews(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_custom_seekbar, this, true);
    }
}
