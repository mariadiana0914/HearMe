package com.girlesc.enguard.contracts;

import com.girlesc.enguard.common.BasePresenter;
import com.girlesc.enguard.common.BaseView;

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
