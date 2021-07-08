package com.girlesc.enguard.contracts;

import com.girlesc.enguard.common.BasePresenter;
import com.girlesc.enguard.common.BaseView;
import com.google.firebase.auth.PhoneAuthCredential;

public interface CodeVerificationContract {

    interface View extends BaseView<Presenter> {

        void showInvalidCode();

        void startResendCodeCountdown();

        void setLoadingIndicator(boolean active);

    }

    interface Presenter extends BasePresenter {

        void linkPhoneCredentials(PhoneAuthCredential credential);

    }

}
