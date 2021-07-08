package com.girlesc.enguard.contracts;

import com.girlesc.enguard.common.BasePresenter;
import com.girlesc.enguard.common.BaseView;

public interface LogInContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showInvalidEmail();

        void showInvalidPassword();

        void onLogInFailure();

        void onLogInSuccess();

    }

    interface Presenter extends BasePresenter {

        void logIn(String email, String password);

    }
}
