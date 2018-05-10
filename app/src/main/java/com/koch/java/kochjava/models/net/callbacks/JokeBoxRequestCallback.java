package com.koch.java.kochjava.models.net.callbacks;

import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.base.net.responses.JokesResponse;
import com.koch.java.kochjava.models.net.RetrofitRequest;

import io.objectbox.Box;

public class JokeBoxRequestCallback extends RetrofitRequest<JokesResponse> {
    private final Box<Joke> jokeBox;
    private final boolean removeOld;

    public JokeBoxRequestCallback(Box<Joke> jokeBox, boolean removeOld) {
        this.jokeBox = jokeBox;
        this.removeOld = removeOld;
    }

    @Override
    public void onReceived(JokesResponse response) {
        if (removeOld)
            jokeBox.removeAll();
        jokeBox.put(response.value);
    }

    @Override
    public void onEmpty() {}
}
