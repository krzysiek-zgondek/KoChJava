package com.koch.java.kochjava.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.koch.java.kochjava.R;
import com.koch.java.kochjava.base.model.Joke;
import com.koch.java.kochjava.models.events.NetworkErrorEvent;
import com.koch.java.kochjava.models.events.ProfileChangedEvent;
import com.koch.java.kochjava.ui.adapters.JokeListAdapter;
import com.koch.java.kochjava.ui.adapters.helpers.JokeListItemCallback;
import com.koch.java.kochjava.ui.view.viewmodel.JokeViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedFragment extends BaseFragment{
    @BindView(R.id.joke_list_refresh) SwipeRefreshLayout jokeSwipeRefresh;
    @BindView(R.id.joke_list) RecyclerView jokeList;

    @Inject protected ViewModelProvider.Factory viewModelFactory;

    private JokeViewModel jokeViewModel;
    private JokeListAdapter jokeListAdapter;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_feed);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBindView(@Nullable View root) {
        super.onBindView(root);

        configureSwipe();
        configureViewModel();
        configureJokeList();
    }

    private void configureSwipe() {
        jokeSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jokeViewModel.refreshJokes();
            }
        });
        jokeSwipeRefresh.setRefreshing(true);
    }

    private void configureJokeList() {
        jokeListAdapter = new JokeListAdapter(null);
        jokeList.setAdapter(jokeListAdapter);

        JokeListItemCallback.create(jokeListAdapter, jokeViewModel).attachToRecyclerView(jokeList);
    }

    private void configureViewModel() {
        jokeViewModel = ViewModelProviders.of(this, viewModelFactory).get(JokeViewModel.class);
        jokeViewModel.getData().observe(this, new Observer<List<Joke>>() {
            @Override
            public void onChanged(@Nullable List<Joke> jokes) {
                jokeListAdapter.setItems(jokes);
                jokeSwipeRefresh.setRefreshing(
                        jokes == null || jokes.isEmpty()
                );
            }
        });
    }

    @OnClick(R.id.change_name_button)
    void onChangeProfile() {
        EventBus.getDefault().post(new ProfileChangedEvent(null));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetworkErrorEvent event) {
        jokeSwipeRefresh.setRefreshing(false);
        Toast.makeText(getContext(), R.string.huston_problem, Toast.LENGTH_SHORT).show();
    }
}
