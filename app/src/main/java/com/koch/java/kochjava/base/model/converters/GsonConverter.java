package com.koch.java.kochjava.base.model.converters;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.converter.PropertyConverter;

public class GsonConverter implements PropertyConverter<List<String>, String> {
    private static final Gson converter = new Gson();

    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        Object obj = converter.fromJson(databaseValue, new TypeToken<List<String>>() {}.getType());
        if(obj==null)
            return new ArrayList<>();

        //noinspection unchecked
        return (List<String>) obj;
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        return entityProperty == null ? "[]" : converter.toJson(entityProperty);
    }
}
