package com.koch.java.kochjava.ui.tools;

import android.text.TextUtils;

import com.koch.java.kochjava.base.model.User;

public class ProfileRack {
    public static boolean isProfileCorrect(User user){
        return !(user == null ||
                TextUtils.isEmpty(user.getFirstName()) || TextUtils.isEmpty(user.getLastName()));
    }
}
