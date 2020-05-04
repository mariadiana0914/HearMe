package com.girlesc.enguard.presenters;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.girlesc.enguard.contracts.LogInContract;
import com.girlesc.enguard.data.source.UserDataSource;
import com.girlesc.enguard.data.source.UserRepository;

import com.girlesc.enguard.utils.CredentialsUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class LogInPresenter implements LogInContract.Presenter {

    private final UserRepository mUserRepository;
    private final LogInContract.View mLogInView;

    public LogInPresenter(UserRepository mUserRepository, LogInContract.View mLogInView) {
        this.mUserRepository = checkNotNull(mUserRepository, "userRepository cannot be null!");
        this.mLogInView = checkNotNull(mLogInView, "logInView cannot be null!");

        mLogInView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void logIn(String email, String password) {

        if (!CredentialsUtils.checkEmail(email)) {
            mLogInView.showInvalidEmail();
            return;
        }
        if (!CredentialsUtils.checkPassword(password)) {
            mLogInView.showInvalidPassword();
            return;
        }
        mLogInView.setLoadingIndicator(true);
        mUserRepository.logInUser(email, password, new UserDataSource.OnLogInCallback() {
            @Override
            public void onSuccess() {
                mLogInView.onLogInSuccess();
            }

            @Override
            public void onFailure() {
                mLogInView.onLogInFailure();
            }
        });
        mLogInView.setLoadingIndicator(false);
    }
}
