package com.girlesc.enguard.presenters;

import com.girlesc.enguard.contracts.CodeVerificationContract;
import com.girlesc.enguard.data.source.UserRepository;
import com.google.firebase.auth.PhoneAuthCredential;

public class CodeVerificationPresenter implements CodeVerificationContract.Presenter {

    private UserRepository mUserRepository;
    private CodeVerificationContract.View mCodeVerificationView;

    public CodeVerificationPresenter(UserRepository mUserRepository, CodeVerificationContract.View mCodeVerificationView) {
        this.mUserRepository = mUserRepository;
        this.mCodeVerificationView = mCodeVerificationView;

        mCodeVerificationView.setPresenter(this);
    }

    @Override
    public void sendVerificationCode(String phoneNumber) {
        mCodeVerificationView.startResendCodeCountdown();
    }

    @Override
    public void linkPhoneCredentials(PhoneAuthCredential credential) {
        mCodeVerificationView.setLoadingIndicator(true);
        mUserRepository.linkPhoneCredentials(credential);
    }

    @Override
    public void start() {

    }
}
