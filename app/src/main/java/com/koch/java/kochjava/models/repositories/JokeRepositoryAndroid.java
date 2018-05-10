package com.koch.java.kochjava.models.repositories;

import android.arch.lifecycle.LiveData;

import com.koch.java.kochjava.base.BaseConfig;
import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.base.model.User;
import com.koch.java.kochjava.base.model.repositories.JokeRepository;
import com.koch.java.kochjava.models.net.api.JokeServices;
import com.koch.java.kochjava.models.net.callbacks.JokeBoxRequestCallback;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;

public class JokeRepositoryAndroid implements JokeRepository {
    private final JokeServices api;
    private final Box<Joke> jokeBox;

    @Inject
    public JokeRepositoryAndroid(JokeServices api, Box<Joke> jokeBox){
        this.api = api;
        this.jokeBox = jokeBox;
    }

    public LiveData<List<Joke>> getJokes(User profile){
        requestMultipleJokes(profile);
        return new ObjectBoxLiveData<>(jokeBox.query().build());
    }

    public void removeJoke(Joke item) {
        jokeBox.remove(item);
    }

    public void requestMultipleJokes(User profile) {
        requestJokes(profile, BaseConfig.Jokes.Quantity, true);
    }

    public void requestSingleJoke(User profile){
        requestJokes(profile,1, false);
    }

    private void requestJokes(final User profile, final int quantity, final boolean removeOld){
        api.requestJokes(quantity, profile.getFirstName(), profile.getLastName())
                .enqueue(new JokeBoxRequestCallback(jokeBox, removeOld));
    }
}
