package com.girlesc.enguard.contracts;

import com.girlesc.enguard.BasePresenter;
import com.girlesc.enguard.BaseView;

public interface LogInContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showInvalidEmail(String message);

        void showInvalidPassword(String message);

        void onLogInFailure(String message);

        void onLogInSuccess();

    }

    interface Presenter extends BasePresenter {

        void logIn(String email, String password);

    }
}
