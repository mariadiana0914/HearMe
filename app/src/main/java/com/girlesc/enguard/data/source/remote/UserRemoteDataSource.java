package com.girlesc.enguard.data.source.remote;

import com.girlesc.enguard.data.source.UserDataSource;
import com.google.firebase.auth.AuthCredential;

public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    private UserRemoteDataSource() {
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

    @Override
    public void linkCredentials(AuthCredential credential, BaseCallback callback) {

    }
}
