package com.girlesc.enguard.data.source;

import com.girlesc.enguard.data.User;

public interface UserDataSource {
    interface OnLogInCallback {
        void onSuccess();

        void onFailure();
    }

    interface OnSignUpCallback {
        void onSuccess();

        void onFailure();
    }

    interface OnPasswordRecoveryEmailCallback {
        void onSuccess();

        void onFailure();
    }

    void logInUser(String email, String password, OnLogInCallback callback);

    void signUpUser(String email, String password, OnSignUpCallback callback);

    void sendPasswordRecoveryEmail(String email, OnPasswordRecoveryEmailCallback callback);
}
