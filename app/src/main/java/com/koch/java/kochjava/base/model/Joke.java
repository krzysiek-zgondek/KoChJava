package com.koch.java.kochjava.base.model;

import com.koch.java.kochjava.base.model.converters.GsonConverter;

import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;

@Entity
public class Joke extends BaseModel {
    public String joke;

    @Convert(converter = GsonConverter.class, dbType = String.class)
    public List<String> categories;
}
