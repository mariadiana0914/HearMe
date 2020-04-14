package com.girlesc.hearme;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomEditText extends LinearLayout {

    private Context mContext;
    private TextView mTitleTV;
    private EditText mFieldET;
    private TextView mShowBtn;

    private String mTitle;
    private String mHint;
    private int mInputType;


    public CustomEditText(Context context) {
        super(context);
        initializeViews(context, null);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);
    }

    public CustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);
    }

    private void initializeViews(Context context, AttributeSet attrs) {
        mContext = context;

        setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_custom_edit_text, this, true);

        mTitleTV = findViewById(R.id.titleTV);
        mFieldET = findViewById(R.id.fieldET);
        mShowBtn = findViewById(R.id.showBtn);

        TypedArray styleAttributesArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        try {
            mTitle = styleAttributesArray.getString(R.styleable.CustomEditText_title);
            mHint = styleAttributesArray.getString(R.styleable.CustomEditText_android_hint);
            mInputType = styleAttributesArray.getInt(R.styleable.CustomEditText_android_inputType, InputType.TYPE_CLASS_TEXT);

            mTitleTV.setText(mTitle);
            mFieldET.setHint(mHint);
            mFieldET.setInputType(mInputType);
        }
        finally {
            styleAttributesArray.recycle();
        }
    }
}
