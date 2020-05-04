package com.girlesc.enguard.data.source;

import com.google.firebase.auth.AuthCredential;

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

    interface BaseCallback {
        void onSuccess();

        void onFailure();
    }

    void logInUser(String email, String password, OnLogInCallback callback);

    void signUpUser(String email, String password, OnSignUpCallback callback);

    void sendPasswordRecoveryEmail(String email, OnPasswordRecoveryEmailCallback callback);

    void linkCredentials(AuthCredential credential, BaseCallback callback);
}
