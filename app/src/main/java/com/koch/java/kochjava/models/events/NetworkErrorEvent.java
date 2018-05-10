package com.koch.java.kochjava.models.events;

public class NetworkErrorEvent {
    public final Throwable error;

    public NetworkErrorEvent(Throwable error) {
        this.error = error;
    }
}
