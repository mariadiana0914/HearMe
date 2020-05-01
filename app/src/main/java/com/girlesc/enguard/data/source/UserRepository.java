package com.girlesc.enguard.data.source;

import androidx.annotation.NonNull;

import com.girlesc.enguard.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource mRemoteUserDataSource;
    private final UserDataSource mLocalUserDataSource;

    boolean mCacheIsDirty = false;

    private UserRepository(UserDataSource mRemoteUserDataSource, UserDataSource mLocalUserDataSource) {
        this.mRemoteUserDataSource = checkNotNull(mRemoteUserDataSource);
        this.mLocalUserDataSource = mLocalUserDataSource;
    }

    public static UserRepository getInstance(UserDataSource remoteUserDataSource, UserDataSource localUserDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(remoteUserDataSource, localUserDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void logInUser(String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }
}
