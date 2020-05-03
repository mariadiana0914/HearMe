package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.enguard.R;
import com.girlesc.enguard.contracts.SignUpContract;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.presenters.SignUpPresenter;
import com.girlesc.enguard.utils.ToastUtils;
import com.girlesc.enguard.views.CustomEditText;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private LinearLayout mBackBtn;
    private Button mSignUpBtn;
    private TextView mLogInRedirectBtn;
    private CustomEditText mEmailET;
    private CustomEditText mPasswordET;
    private CustomEditText mConfirmPasswordET;

    private SignUpContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setPresenter(new SignUpPresenter(this, UserRepository.getInstance()));

        mEmailET = findViewById(R.id.emailET);
        mPasswordET = findViewById(R.id.passwordET);
        mConfirmPasswordET = findViewById(R.id.confirmPasswordET);

        mBackBtn = findViewById(R.id.backBtn);
        mSignUpBtn = findViewById(R.id.signUpBtn);
        mLogInRedirectBtn = findViewById(R.id.logInRedirectBtn);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPresenter.signUp(mEmailET.getText(), mPasswordET.getText(), mConfirmPasswordET.getText());
                Intent intent = new Intent(SignUpActivity.this, AccountSetUpActivity.class);
                startActivity(intent);
            }
        });


        mLogInRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void showInvalidEmail(String message) {
        mEmailET.showErrorMessage(message);
    }

    @Override
    public void showInvalidPassword(String message) {
        mPasswordET.showErrorMessage(message);
    }

    @Override
    public void showPasswordsNotMatching(String message) {
        mConfirmPasswordET.showErrorMessage(message);
    }

    @Override
    public void onSignUpFailure(String message) {
        ToastUtils.showToast(this, message);
    }

    @Override
    public void onSignUpSuccess() {
        Intent intent = new Intent(this, AccountSetUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
