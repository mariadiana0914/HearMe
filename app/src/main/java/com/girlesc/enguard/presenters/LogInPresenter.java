package com.girlesc.enguard.presenters;

import com.girlesc.enguard.contracts.LogInContract;
import com.girlesc.enguard.data.source.UserRepository;

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

    }

    private boolean checkEmail(String email) {
        return false;
    }

    private boolean checkPassword(String password) {
        return false;
    }
}
