package com.girlesc.enguard.presenters;

import com.girlesc.enguard.contracts.SignUpContract;
import com.girlesc.enguard.data.source.UserDataSource;
import com.girlesc.enguard.data.source.UserRepository;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mSignUpView;
    private UserRepository mUserRepository;

    public SignUpPresenter(SignUpContract.View mSignUpView, UserRepository mUserRepository) {
        this.mSignUpView = mSignUpView;
        this.mUserRepository = mUserRepository;
        mSignUpView.setPresenter(this);
    }

    @Override
    public void signUp(String email, String password, String confirmPassword) {
        if (!checkEmail(email)) {
            mSignUpView.showInvalidEmail();
            return;
        }
        if(!checkPassword(password)){
            mSignUpView.showInvalidPassword();
            return;
        }
        if (!checkMatchingPasswords(password, confirmPassword)){
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
                mSignUpView.onSignUpFailure("Something went wrong! Please try again.");

            }
        });
    }

    @Override
    public boolean checkEmail(String email) {
        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        return true;
    }

    @Override
    public boolean checkMatchingPasswords(String password, String confirmPassword) {
        return true;
    }

    @Override
    public void start() {

    }
}
