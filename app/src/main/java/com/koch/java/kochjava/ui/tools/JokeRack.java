package com.koch.java.kochjava.ui.tools;

import android.text.Html;
import android.text.Spanned;

import com.koch.java.kochjava.base.model.Joke;

public class JokeRack {
    public static String formatId(Joke joke){
        return String.format("%s.", String.valueOf(joke.id));
    }

    public static Spanned formatJoke(Joke joke){
        return Html.fromHtml(joke.joke);
    }

    public static String formatCategory(Joke joke, String empty) {
        if(joke.categories.isEmpty())
            return empty;
        return joke.categories.get(0);
    }

//    public static
}
