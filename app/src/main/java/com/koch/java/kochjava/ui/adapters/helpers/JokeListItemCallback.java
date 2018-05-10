package com.koch.java.kochjava.ui.adapters.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.ui.adapters.JokeListAdapter;
import com.koch.java.kochjava.ui.view.viewmodel.JokeViewModel;

public class JokeListItemCallback extends ItemTouchHelper.SimpleCallback {
    private final JokeListAdapter adapter;
    private final JokeViewModel jokeViewModel;

    public static ItemTouchHelper create(JokeListAdapter jokeListAdapter, JokeViewModel jokeViewModel) {
        return new ItemTouchHelper(
                new JokeListItemCallback(jokeListAdapter, jokeViewModel)
        );
    }

    private JokeListItemCallback(JokeListAdapter adapter, JokeViewModel jokeViewModel) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.jokeViewModel = jokeViewModel;
    }

    @Override
    public boolean onMove(
            RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Joke joke = adapter.getItem(viewHolder.getAdapterPosition());
        jokeViewModel.removeAndGetNewJoke(joke);
    }
}
