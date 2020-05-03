package com.girlesc.enguard.views;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.girlesc.enguard.R;

public class CustomEditText extends LinearLayout {

    Context mContext;
    TextView mTitleTV;
    EditText mFieldET;
    TextView mShowBtn;
    ImageView mErrorIV;
    TextView mErrorTV;

    String mTitle;
    String mHint;
    int mInputType;

    boolean mPasswordToggle = false;
    boolean mPasswordVisible = false;
    TextWatcher mTextWatcher;

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
        LayoutInflater.from(mContext).inflate(R.layout.layout_custom_edit_text, this, true);

        TypedArray styleAttributesArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        try {
            mTitle = styleAttributesArray.getString(R.styleable.CustomEditText_title);
            mHint = styleAttributesArray.getString(R.styleable.CustomEditText_android_hint);
            mInputType = styleAttributesArray.getInt(R.styleable.CustomEditText_android_inputType, InputType.TYPE_CLASS_TEXT);
            mPasswordToggle = styleAttributesArray.getBoolean(R.styleable.CustomEditText_passwordToggleEnabled, false);
        } finally {
            styleAttributesArray.recycle();
        }

        mTitleTV = findViewById(R.id.titleTV);
        mFieldET = findViewById(R.id.fieldET);
        mShowBtn = findViewById(R.id.showBtn);
        mErrorIV = findViewById(R.id.errorIV);
        mErrorTV = findViewById(R.id.errorTV);

        Typeface mTypeface = mFieldET.getTypeface();

        mTitleTV.setText(mTitle);
        mFieldET.setHint(mHint);
        mFieldET.setInputType(mInputType);
        mFieldET.setTypeface(mTypeface);

        mErrorTV.setVisibility(View.GONE);
        mErrorIV.setVisibility(View.GONE);
        mShowBtn.setVisibility(View.GONE);

        mShowBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mPasswordVisible)
                    mFieldET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    mFieldET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mPasswordVisible = !mPasswordVisible;
            }
        });

        mFieldET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mTextWatcher != null) mTextWatcher.beforeTextChanged(charSequence, i, i1, i2);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mTextWatcher != null) mTextWatcher.onTextChanged(charSequence, i, i1, i2);
                if(mPasswordToggle) {
                    if (!mFieldET.getText().toString().isEmpty()) {
                        mShowBtn.setVisibility(View.VISIBLE);
                    } else {
                        mShowBtn.setVisibility(View.GONE);
                    }
                }
                hideErrorMessage();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mTextWatcher != null) mTextWatcher.afterTextChanged(editable);
            }
        });
    }

    public void setTitle(String title) {
        mTitle = title;
        mTitleTV.setText(title);
    }

    public void setInputType(int inputType) {
        mInputType = inputType;
        mFieldET.setInputType(inputType);
    }

    public void setHint(String hint) {
        mHint = hint;
        mFieldET.setHint(hint);
    }

    public String getText() {
        return mFieldET.getText().toString();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getHint() {
        return mHint;
    }

    public int getInputType() {
        return mInputType;
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        mTextWatcher = textWatcher;
    }

    public void showErrorMessage(String message) {
        mErrorTV.setVisibility(View.VISIBLE);
        mErrorIV.setVisibility(View.VISIBLE);
        mErrorTV.setText(message);
    }

    public void hideErrorMessage() {
        mErrorTV.setVisibility(View.GONE);
        mErrorIV.setVisibility(View.GONE);
    }
}
