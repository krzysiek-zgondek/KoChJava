package com.koch.java.kochjava.ui.view.viewholders;


import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.koch.java.kochjava.R;
import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.ui.tools.JokeRack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.joke_content) public AppCompatTextView jokeContent;
    @BindView(R.id.joke_category) public AppCompatTextView jokeCategory;
    @BindView(R.id.joke_id) public AppCompatTextView jokeId;

    private final String emptyCategory;

    public JokeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

        emptyCategory = view.getContext().getString(R.string.empty_category);
    }

    public void bind(Joke joke) {
        jokeId.setText(JokeRack.formatId(joke));
        jokeContent.setText(JokeRack.formatJoke(joke));
        jokeCategory.setText(JokeRack.formatCategory(joke, emptyCategory));
    }
}