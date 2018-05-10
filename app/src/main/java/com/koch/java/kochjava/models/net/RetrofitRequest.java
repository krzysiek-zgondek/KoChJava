package com.koch.java.kochjava.models.net;

import android.support.annotation.NonNull;

import com.koch.java.kochjava.base.net.requests.BaseRequest;
import com.koch.java.kochjava.models.events.helpers.SignalNetworkProblem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitRequest<T> implements BaseRequest<T>, Callback<T> {
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            if(response.body() == null)
                onEmpty();
            else onReceived(response.body());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        t.printStackTrace();

        new SignalNetworkProblem(t).run();
    }
}
