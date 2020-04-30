package com.girlesc.hearme.data.source.remote;

import android.app.usage.NetworkStats;

import com.girlesc.hearme.data.source.DataSource;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {
    }

    @Override
    public void getUser(String email, String hashedPassword, GetUserCallback callback) {

    }
}
