package com.koch.java.kochjava.models.events.helpers;

import com.koch.java.kochjava.base.model.User;
import com.koch.java.kochjava.models.events.ProfileChangedEvent;

import org.greenrobot.eventbus.EventBus;

public class SignalProfileChanged implements Runnable {
    private final User user;

    public SignalProfileChanged(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        EventBus.getDefault().post(new ProfileChangedEvent(user));
    }
}
