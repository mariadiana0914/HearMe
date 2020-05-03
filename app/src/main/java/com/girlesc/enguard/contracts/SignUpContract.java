package com.girlesc.enguard.contracts;

import com.girlesc.enguard.BasePresenter;
import com.girlesc.enguard.BaseView;

public interface SignUpContract {

    interface View extends BaseView<Presenter> {
        void showInvalidEmail(String message);

        void showInvalidPassword(String message);

        void showPasswordsNotMatching(String message);

        void onSignUpFailure(String message);

        void onSignUpSuccess();

        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter {

        void signUp(String email, String password, String confirmPassword);

    }
}
