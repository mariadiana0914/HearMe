package com.girlesc.enguard.contracts;

import com.girlesc.enguard.BasePresenter;
import com.girlesc.enguard.BaseView;

public interface LogInContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showInvalidEmail();

        void showInvalidPassword();

        void onLogInFailure(String message);

        void onLogInSuccess(String message);

    }

    interface Presenter extends BasePresenter {

        void logIn(String email, String password);
    }
}