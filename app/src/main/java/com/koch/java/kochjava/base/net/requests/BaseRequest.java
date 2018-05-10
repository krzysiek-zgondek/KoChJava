package com.koch.java.kochjava.base.net.requests;

public interface BaseRequest<T> {
    void onReceived(T response);

    void onEmpty();
}
