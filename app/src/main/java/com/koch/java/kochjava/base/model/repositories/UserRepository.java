package com.koch.java.kochjava.base.model.repositories;

import com.koch.java.kochjava.base.model.User;

public interface UserRepository {
    interface Ids{
        long PROFILE_ID = 1L;
    }

    User getProfile();

    void setProfile(User profile);
    void updateProfile(User profile);

    void removeProfile();
}
