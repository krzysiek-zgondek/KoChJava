package com.koch.java.kochjava.base.net.responses;

import com.koch.java.kochjava.base.model.Joke;

import java.util.List;

public class JokesResponse extends BaseResponse {
    public String type;

    public List<Joke> value;
}
