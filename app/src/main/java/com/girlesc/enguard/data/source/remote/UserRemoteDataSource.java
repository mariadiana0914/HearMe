package com.girlesc.enguard.data.source.remote;

import com.girlesc.enguard.data.source.UserDataSource;

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
}
