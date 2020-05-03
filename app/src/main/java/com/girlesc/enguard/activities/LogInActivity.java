package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.enguard.R;
import com.girlesc.enguard.contracts.LogInContract;
import com.girlesc.enguard.contracts.PasswordRecoveryContract;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.presenters.LogInPresenter;
import com.girlesc.enguard.utils.ToastUtils;
import com.girlesc.enguard.views.CustomEditText;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class LogInActivity extends AppCompatActivity implements LogInContract.View {

    private LogInContract.Presenter mPresenter;

    private LinearLayout mBackBtn;
    private TextView mSignUpRedirectBtn;
    private CustomEditText mEmailET;
    private CustomEditText mPasswordET;
    private Button mLogInBtn;
    private LinearLayout mPasswordRecoveryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setPresenter(new LogInPresenter(UserRepository.getInstance(), this));

        mBackBtn = findViewById(R.id.backBtn);
        mSignUpRedirectBtn = findViewById(R.id.signUpRedirectBtn);
        mLogInBtn = findViewById(R.id.logInBtn);
        mEmailET = findViewById(R.id.emailET);
        mPasswordET = findViewById(R.id.passwordET);
        mPasswordRecoveryBtn = findViewById(R.id.forgotPasswordBtn);

        mLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logIn(mEmailET.getText(), mPasswordET.getText());
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mSignUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mPasswordRecoveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, PasswordRecoveryActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {

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
    public void onLogInFailure(String message) {
        ToastUtils.showToast(this, message);
    }

    @Override
    public void onLogInSuccess() {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(LogInContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
