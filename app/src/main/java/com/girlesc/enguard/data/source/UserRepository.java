package com.girlesc.enguard.data.source;

import android.util.Log;

import androidx.annotation.NonNull;

import com.girlesc.enguard.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class UserRepository implements UserDataSource {

    private static final String TAG = "UserRepository";

    private static UserRepository INSTANCE = null;

//    private final UserDataSource mRemoteUserDataSource;
//    private final UserDataSource mLocalUserDataSource;

    boolean mCacheIsDirty = false;

//    private UserRepository(UserDataSource mRemoteUserDataSource, UserDataSource mLocalUserDataSource) {
//        this.mRemoteUserDataSource = checkNotNull(mRemoteUserDataSource);
//        this.mLocalUserDataSource = checkNotNull(mLocalUserDataSource);
//    }


    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void logInUser(String email, String password, final OnLogInCallback callback) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callback.onFailure();
                        }
                    }
                });
    }
    @Override
    public void signUpUser(String email, String password, final OnSignUpCallback callback){
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            callback.onSuccess();
                        } else {
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            callback.onFailure();
                        }
                    }
                });

    }

    @Override
    public void sendPasswordRecoveryEmail(String email, final OnPasswordRecoveryEmailCallback callback) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            callback.onSuccess();
                        }
                        else {
                            Log.w(TAG, "sendPasswordResetEmail:failure", task.getException());
                            callback.onFailure();
                        }
                    }
                });
    }
}
