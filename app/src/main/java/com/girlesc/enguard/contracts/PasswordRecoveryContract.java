package com.girlesc.enguard.contracts;

import com.girlesc.enguard.BasePresenter;
import com.girlesc.enguard.BaseView;

public interface PasswordRecoveryContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showInvalidEmail(String message);

        void onResetPasswordSuccess();

        void onResetPasswordFailure(String message);
    }

    interface Presenter extends BasePresenter {
        void sendResetPasswordEmail(String email);
    }
}
