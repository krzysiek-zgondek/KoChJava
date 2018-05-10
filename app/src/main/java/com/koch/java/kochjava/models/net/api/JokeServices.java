package com.koch.java.kochjava.models.net.api;

import com.koch.java.kochjava.base.net.responses.JokesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JokeServices {
    interface Config{
        String url = "https://api.icndb.com";
    }

    @GET("jokes/random/{count}")
    Call<JokesResponse> requestJokes(@Path("count") long quantity,
                                     @Query("firstName") String firstName,
                                     @Query("lastName") String lastName);
}
