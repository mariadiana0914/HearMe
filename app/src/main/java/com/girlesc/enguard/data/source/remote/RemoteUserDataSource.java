package com.girlesc.enguard.data.source.remote;

import com.girlesc.enguard.data.User;
import com.girlesc.enguard.data.source.UserDataSource;

public class RemoteUserDataSource implements UserDataSource {

    private static RemoteUserDataSource INSTANCE;

    public static RemoteUserDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteUserDataSource();
        }
        return INSTANCE;
    }

    private RemoteUserDataSource() {
    }


    @Override
    public void logInUser(String email, String password) {

    }
}
