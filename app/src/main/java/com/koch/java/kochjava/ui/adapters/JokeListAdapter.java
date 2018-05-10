package com.koch.java.kochjava.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.koch.java.kochjava.R;
import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.ui.adapters.helpers.JokeListDiff;
import com.koch.java.kochjava.ui.view.viewholders.JokeViewHolder;

import java.util.List;

public class JokeListAdapter extends RecyclerView.Adapter<JokeViewHolder>{
    private List<Joke> jokes;

    public JokeListAdapter(final List<Joke> jokes) {
        setItems(jokes);
    }

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new JokeViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_joke, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final JokeViewHolder holder, final int position) {
        holder.bind(
                jokes.get(holder.getAdapterPosition())
        );
    }

    @Override
    public int getItemCount() {
        return jokes == null ? 0 : jokes.size();
    }

    public Joke getItem(final int position) {
        return position < getItemCount() ? jokes.get(position) : null;
    }

    public void setItems(final List<Joke> items) {
        DiffUtil.calculateDiff(
                new JokeListDiff(switchList(items), items)
        ).dispatchUpdatesTo(this);
    }

    private List<Joke> switchList(final List<Joke> items) {
        List<Joke> oldItems = this.jokes;
        this.jokes = items;
        return oldItems;
    }
}