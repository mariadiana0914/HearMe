package com.girlesc.enguard.presenters;

import com.girlesc.enguard.contracts.SignUpContract;
import com.girlesc.enguard.data.source.UserDataSource;
import com.girlesc.enguard.data.source.UserRepository;
import com.girlesc.enguard.utils.CredentialsUtils;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mSignUpView;
    private UserRepository mUserRepository;

    public SignUpPresenter(SignUpContract.View mSignUpView, UserRepository mUserRepository) {
        this.mSignUpView = mSignUpView;
        this.mUserRepository = mUserRepository;
        mSignUpView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void signUp(String email, String password, String confirmPassword) {
        if (!CredentialsUtils.checkEmail(email)) {
            mSignUpView.showInvalidEmail();
            return;
        }
        if (!CredentialsUtils.checkPassword(password)) {
            mSignUpView.showInvalidPassword();
            return;
        }
        if (!CredentialsUtils.checkMatchingPasswords(password, confirmPassword)) {
            mSignUpView.showPasswordsNotMatching();
            return;
        }
        mSignUpView.setLoadingIndicator(true);
        mUserRepository.signUpUser(email, password, new UserDataSource.OnSignUpCallback() {
            @Override
            public void onSuccess() {
                mSignUpView.onSignUpSuccess();
            }

            @Override
            public void onFailure() {
                mSignUpView.onSignUpFailure();
            }
        });
    }
}
