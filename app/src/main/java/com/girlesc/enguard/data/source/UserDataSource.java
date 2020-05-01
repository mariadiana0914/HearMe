package com.girlesc.enguard.data.source;

import com.girlesc.enguard.data.User;

public interface UserDataSource {
    interface OnLogInCallback {
        void onSuccess();

        void onFailure();
    }

    void logInUser(String email, String password, OnLogInCallback callback);
}
