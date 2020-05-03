package com.girlesc.enguard.data.source.local;

import com.girlesc.enguard.data.source.UserDataSource;
import com.girlesc.enguard.utils.AppExecutors;

public class UserLocalDataSource implements UserDataSource {

    private static volatile UserLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;

    public UserLocalDataSource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
    }

    public static UserLocalDataSource getInstance(AppExecutors mAppExecutors) {
        if (INSTANCE == null) {
            synchronized (UserLocalDataSource.class) {
                if(INSTANCE == null) {
                    INSTANCE = new UserLocalDataSource(mAppExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void logInUser(String email, String password, OnLogInCallback callback) {

    }

    @Override
    public void signUpUser(String email, String password, OnSignUpCallback callback) {

    }

    @Override
    public void sendPasswordRecoveryEmail(String email, OnPasswordRecoveryEmailCallback callback) {

    }
}
