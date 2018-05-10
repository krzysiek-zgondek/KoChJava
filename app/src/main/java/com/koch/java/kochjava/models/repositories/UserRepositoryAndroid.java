package com.koch.java.kochjava.models.repositories;

import com.koch.java.kochjava.base.model.User;
import com.koch.java.kochjava.base.model.repositories.UserRepository;

import javax.inject.Inject;

import io.objectbox.Box;

public class UserRepositoryAndroid implements UserRepository{
    private final Box<User> userBox;

    @Inject
    public UserRepositoryAndroid(Box<User> userBox){
        this.userBox = userBox;
    }

    public User getProfile(){
        return userBox.get(UserRepository.Ids.PROFILE_ID);
    }

    public void setProfile(User profile) {
        userBox.put(profile);
    }

    public void removeProfile() {
        userBox.remove(Ids.PROFILE_ID);
    }

    public void updateProfile(User profile) {
        if(profile == null)
            removeProfile();
        else setProfile(profile);
    }
}
