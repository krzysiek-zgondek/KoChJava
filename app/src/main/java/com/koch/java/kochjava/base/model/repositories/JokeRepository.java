package com.koch.java.kochjava.base.model.repositories;

import android.arch.lifecycle.LiveData;

import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.base.model.User;

import java.util.List;

public interface JokeRepository {

    LiveData<List<Joke>> getJokes(User profile);

    void removeJoke(Joke item);

    void requestMultipleJokes(User profile);

    void requestSingleJoke(User profile);
}
