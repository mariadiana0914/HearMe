package com.girlesc.enguard.contracts;

import com.girlesc.enguard.common.BasePresenter;
import com.girlesc.enguard.common.BaseView;

public interface AccountDetailsSetupContract {

    interface View extends BaseView<Presenter> {
        void setNextButtonEnabled(boolean enable);
    }

    interface AccountDetailsView extends BaseView<Presenter> {
        String getPhoneNumber();

        String getCountryCode();

        String getFirstName();

        String getLastName();

        void showInvalidFirstName();

        void showInvalidLastName();

        void showInvalidPhoneNumber();
    }

    interface CodeVerificationView extends BaseView<Presenter> {
        void showInvalidVerificationCode();
    }

    interface SecurityLevelView extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
