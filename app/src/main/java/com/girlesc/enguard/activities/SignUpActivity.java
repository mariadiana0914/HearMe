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

    private LinearLayout backFromSignUpBtn;
    private Button signUpToSetUpBtn;
    private TextView logInFromSignUpBtn;
    private SignUpContract.Presenter mPresenter;
    private CustomEditText mEmailET;
    private CustomEditText mPasswordET;
    private CustomEditText mConfirmPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setPresenter(new SignUpPresenter(this, UserRepository.getInstance()));
        mEmailET = findViewById(R.id.emailET);
        mPasswordET = findViewById(R.id.passwordET);
        mConfirmPasswordET = findViewById(R.id.confirmPasswordET);

        backFromSignUpBtn = findViewById(R.id.backFromSignUpBtn);
        backFromSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpToSetUpBtn = findViewById(R.id.signUpToSetUpBtn);
        signUpToSetUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mPresenter.signUp(mEmailET.getText(), mPasswordET.getText(), mConfirmPasswordET.getText());
                Intent intent = new Intent(SignUpActivity.this, AccountSetUpActivity.class);
                startActivity(intent);
            }
        });

        logInFromSignUpBtn = findViewById(R.id.logInFromSignUpBtn);
        logInFromSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void showInvalidEmail() {

    }

    @Override
    public void showInvalidPassword() {

    }

    @Override
    public void showPasswordsNotMatching() {

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
