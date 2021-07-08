package com.girlesc.enguard.presenters;

import com.girlesc.enguard.contracts.PasswordRecoveryContract;
import com.girlesc.enguard.data.source.UserDataSource;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.utils.CredentialsUtils;

public class PasswordRecoveryPresenter implements PasswordRecoveryContract.Presenter {

    private final UserRepository mUserRepository;
    private final PasswordRecoveryContract.View mPasswordRecoveryView;

    public PasswordRecoveryPresenter(UserRepository mUserRepository, PasswordRecoveryContract.View mPasswordRecoveryView) {
        this.mUserRepository = mUserRepository;
        this.mPasswordRecoveryView = mPasswordRecoveryView;

        mPasswordRecoveryView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void sendResetPasswordEmail(String email) {
        if(!CredentialsUtils.checkEmail(email)) {
            mPasswordRecoveryView.showInvalidEmail();
            return;
        }
        mPasswordRecoveryView.setLoadingIndicator(true);
        mUserRepository.sendPasswordRecoveryEmail(email, new UserDataSource.OnPasswordRecoveryEmailCallback() {
            @Override
            public void onSuccess() {
                mPasswordRecoveryView.onResetPasswordSuccess();
            }

            @Override
            public void onFailure() {
                mPasswordRecoveryView.onResetPasswordFailure();
            }
        });
        mPasswordRecoveryView.setLoadingIndicator(false);
    }
}
