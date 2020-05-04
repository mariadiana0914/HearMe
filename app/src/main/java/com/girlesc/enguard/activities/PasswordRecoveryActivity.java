package com.girlesc.enguard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.girlesc.enguard.R;
import com.girlesc.enguard.contracts.PasswordRecoveryContract;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.presenters.PasswordRecoveryPresenter;
import com.girlesc.enguard.utils.ToastUtils;
import com.girlesc.enguard.views.CustomEditText;

public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryContract.View {

    private LinearLayout mBackBtn;
    private Button mResetPasswordBtn;
    private CustomEditText mEmailET;

    private PasswordRecoveryContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        setPresenter(new PasswordRecoveryPresenter(UserRepository.getInstance(), this));

        mBackBtn = findViewById(R.id.backBtn);
        mEmailET = findViewById(R.id.emailET);
        mResetPasswordBtn = findViewById(R.id.resetPasswordBtn);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mResetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendResetPasswordEmail(mEmailET.getText());
            }
        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showInvalidEmail() {
        mEmailET.showErrorMessage(getResources().getString(R.string.tv_invalid_email_address));
    }

    @Override
    public void onResetPasswordSuccess() {
        ToastUtils.showToast(this, getResources().getString(R.string.tv_password_recovery_email_sent));
    }

    @Override
    public void onResetPasswordFailure() {

        ToastUtils.showToast(this, getResources().getString(R.string.tv_password_recovery_failed));
    }

    @Override
    public void setPresenter(PasswordRecoveryContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
