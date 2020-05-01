package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.enguard.R;
import com.girlesc.enguard.contracts.LogInContract;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.presenters.LogInPresenter;
import com.girlesc.enguard.utils.ToastUtils;
import com.girlesc.enguard.views.CustomEditText;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class LogInActivity extends AppCompatActivity implements LogInContract.View {

    private LogInContract.Presenter mPresenter;

    private LinearLayout backFromLogInBtn;
    private TextView signUpFromLogInBtn;
    private CustomEditText emailEditText;
    private CustomEditText passwordEditText;
    private Button logInBtn;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setPresenter(new LogInPresenter(UserRepository.getInstance(), this));

        backFromLogInBtn = findViewById(R.id.backFromLogInBtn);
        signUpFromLogInBtn = findViewById(R.id.signUpFromLogInBtn);
        logInBtn = findViewById(R.id.logInBtn);
        emailEditText = findViewById(R.id.emailET);
        passwordEditText = findViewById(R.id.passwordET);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.logIn(emailEditText.getText(), passwordEditText.getText());
            }
        });

        backFromLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpFromLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showInvalidEmail() {

    }

    @Override
    public void showInvalidPassword() {

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
