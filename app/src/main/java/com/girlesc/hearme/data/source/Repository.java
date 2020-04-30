package com.girlesc.hearme.data.source;

import com.google.android.gms.tasks.Task;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class Repository implements DataSource {

    private static Repository INSTANCE = null;

    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;

    boolean mCacheIsDirty = false;

    private Repository(DataSource mRemoteDataSource, DataSource mLocalDataSource) {
        this.mRemoteDataSource = checkNotNull(mRemoteDataSource);
        this.mLocalDataSource = mLocalDataSource;
    }

    public static Repository getInstance(DataSource remoteDataSource, DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getUser(String email, String hashedPassword, GetUserCallback callback) {
        checkNotNull(email);
        checkNotNull(hashedPassword);
        checkNotNull(callback);

        //TODO: Implement the function for retrieving the user from the database
    }
}
