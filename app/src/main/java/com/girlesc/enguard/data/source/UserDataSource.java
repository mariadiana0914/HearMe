package com.girlesc.enguard.data.source;

import com.girlesc.enguard.data.User;

public interface UserDataSource {
    interface GetUserCallback {
        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    void logInUser(String email, String password);
}
