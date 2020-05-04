package com.girlesc.enguard.contracts;

import com.girlesc.enguard.BasePresenter;
import com.girlesc.enguard.BaseView;

public interface PasswordRecoveryContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showInvalidEmail();

        void onResetPasswordSuccess();

        void onResetPasswordFailure();
    }

    interface Presenter extends BasePresenter {
        void sendResetPasswordEmail(String email);
    }
}
