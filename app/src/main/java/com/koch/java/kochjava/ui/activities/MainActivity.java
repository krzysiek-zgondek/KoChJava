package com.koch.java.kochjava.ui.activities;

import android.os.Bundle;

import com.koch.java.kochjava.R;
import com.koch.java.kochjava.base.model.repositories.UserRepository;
import com.koch.java.kochjava.models.events.ProfileChangedEvent;
import com.koch.java.kochjava.ui.fragments.FeedFragment;
import com.koch.java.kochjava.ui.fragments.ProfileFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    @Inject UserRepository userRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        routeFragments();
    }

    private void routeFragments() {
        replaceFragment(
                userRepository.getProfile() == null ?
                        ProfileFragment.newInstance()
                        :FeedFragment.newInstance()
        );
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProfileChangedEvent event) {
        userRepository.updateProfile(event.profile);
        routeFragments();
    }
}
