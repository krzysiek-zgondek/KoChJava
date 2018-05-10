package com.koch.java.kochjava.ui.adapters.helpers;

import android.support.v7.util.DiffUtil;

import com.koch.java.kochjava.base.model.Joke;

import java.util.List;

public class JokeListDiff extends DiffUtil.Callback {
    private final List<Joke> oldList;
    private final List<Joke> newList;

    public JokeListDiff(final List<Joke> oldList, final List<Joke> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).id == newList.get(newPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Joke oldJoke = oldList.get(oldItemPosition);
        final Joke newJoke = newList.get(newItemPosition);

        return oldJoke.joke.equals(newJoke.joke);
    }
}
