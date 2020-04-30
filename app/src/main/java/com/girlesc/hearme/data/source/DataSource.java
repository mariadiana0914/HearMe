package com.girlesc.hearme.data.source;

import com.girlesc.hearme.data.User;

public interface DataSource {
    interface GetUserCallback {
        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    void getUser(String email, String hashedPassword, GetUserCallback callback);
}
