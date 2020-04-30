package com.girlesc.hearme.data;

import java.util.Objects;
import java.util.UUID;

public class User {

    private String mUID;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mTelephoneNumber;

    public User(String mFirstName, String mLastName, String mEmail, String mTelephoneNumber) {
        this(UUID.randomUUID().toString(), mFirstName, mLastName, mEmail, mTelephoneNumber);
    }

    private User(String mUID, String mFirstName, String mLastName, String mEmail, String mTelephoneNumber) {
        this.mUID = mUID;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mTelephoneNumber = mTelephoneNumber;
    }

    public String getUID() {
        return mUID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getTelephoneNumber() {
        return mTelephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mUID.equals(user.mUID) &&
                mFirstName.equals(user.mFirstName) &&
                mLastName.equals(user.mLastName) &&
                mEmail.equals(user.mEmail) &&
                mTelephoneNumber.equals(user.mTelephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUID, mFirstName, mLastName, mEmail, mTelephoneNumber);
    }
}
