package com.koch.java.kochjava.ui.view.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.base.model.repositories.JokeRepository;
import com.koch.java.kochjava.base.model.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

public class JokeViewModel extends ViewModel {
    private final JokeRepository jokeRepository;
    private final UserRepository userRepository;

    private LiveData<List<Joke>> data;

    @Inject
    public JokeViewModel(JokeRepository jokeRepository, UserRepository userRepository){
        this.jokeRepository = jokeRepository;
        this.userRepository = userRepository;
    }

    public void refreshJokes(){
        jokeRepository.requestMultipleJokes(userRepository.getProfile());
    }

    public void removeAndGetNewJoke(Joke item) {
        jokeRepository.removeJoke(item);
        jokeRepository.requestSingleJoke(userRepository.getProfile());
    }

    public LiveData<List<Joke>> getData() {
        if(data == null) {
            data = jokeRepository.getJokes(userRepository.getProfile());
        }
        return data;
    }
}
