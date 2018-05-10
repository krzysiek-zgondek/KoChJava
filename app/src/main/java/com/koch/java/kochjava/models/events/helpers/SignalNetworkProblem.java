package com.koch.java.kochjava.models.events.helpers;

import com.koch.java.kochjava.models.events.NetworkErrorEvent;

import org.greenrobot.eventbus.EventBus;

public class SignalNetworkProblem implements Runnable {
    private final Throwable error;

    public SignalNetworkProblem(Throwable error) {
        this.error = error;
    }

    @Override
    public void run() {
        EventBus.getDefault().post(new NetworkErrorEvent(error));
    }
}
