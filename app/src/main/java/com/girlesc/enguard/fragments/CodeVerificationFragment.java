package com.girlesc.enguard.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.girlesc.enguard.R;
import com.girlesc.enguard.contracts.CodeVerificationContract;
import com.girlesc.enguard.utils.ToastUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CodeVerificationFragment extends Fragment implements CodeVerificationContract.View {

    private TextView mErrorTV;
    private LinearLayout mResendCodeBtn;
    private TextView mCountDownTV;

    private String mPhoneNumber;
    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private CodeVerificationContract.Presenter mCodeVerificationPresenter;
    private boolean mVerificationInProgress = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_code_verification, container, false);

        mErrorTV = view.findViewById(R.id.errorTV);
        mResendCodeBtn = view.findViewById(R.id.resendCodeBtn);
        mCountDownTV = view.findViewById(R.id.countDownTV);

        mResendCodeBtn.setEnabled(false);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                mVerificationInProgress = false;
                mCodeVerificationPresenter.linkPhoneCredentials(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                mVerificationInProgress = false;
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mErrorTV.setVisibility(View.VISIBLE);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    ToastUtils.showToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.quota_exceeded));
                }
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                mVerificationId = s;
                mResendToken = forceResendingToken;
            }
        };

        mResendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resendVerificationCode();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mVerificationInProgress) {
            sendPhoneVerificationCode(mPhoneNumber);
        }
    }

    public void sendPhoneVerificationCode(String phoneNumber) {
        mResendCodeBtn.setEnabled(false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                Objects.requireNonNull(getActivity()),
                mCallbacks
        );

        mVerificationInProgress = true;
        startResendCodeCountdown();
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        mResendCodeBtn.setEnabled(false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                Objects.requireNonNull(getActivity()),
                mCallbacks,
                token
        );
        mVerificationInProgress = true;
        startResendCodeCountdown();
    }

    @Override
    public void showInvalidCode() {
        mErrorTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void startResendCodeCountdown() {
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                mCountDownTV.setText(getResources().getString(R.string.tv_resend_code, l / 1000));
            }

            @Override
            public void onFinish() {
                mVerificationInProgress = false;
                mResendCodeBtn.setEnabled(true);
            }
        };
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setPresenter(CodeVerificationContract.Presenter presenter) {
        mCodeVerificationPresenter = presenter;
    }
}
