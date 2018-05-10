package com.koch.java.kochjava.models.events;

import com.koch.java.kochjava.base.model.User;

public class ProfileChangedEvent {
    public final User profile;

    public ProfileChangedEvent(User profile) {
        this.profile = profile;
    }
}
