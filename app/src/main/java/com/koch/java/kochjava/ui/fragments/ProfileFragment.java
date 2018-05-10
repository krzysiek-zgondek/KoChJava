package com.koch.java.kochjava.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.koch.java.kochjava.R;
import com.koch.java.kochjava.base.model.User;
import com.koch.java.kochjava.models.events.helpers.SignalProfileChanged;
import com.koch.java.kochjava.ui.animations.AnimateClose;
import com.koch.java.kochjava.ui.animations.AnimatePop;
import com.koch.java.kochjava.ui.tools.ProfileRack;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ProfileFragment extends BaseFragment implements TextView.OnEditorActionListener {
    @BindView(R.id.first_name_input) TextInputEditText firstNameInput;
    @BindView(R.id.last_name_input) TextInputEditText lastNameInput;
    @BindView(R.id.proceed_button) FloatingActionButton proceedButton;

    @NonNull private final User user = new User();

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
    }

    @Override
    public void onBindView(@Nullable View root) {
        super.onBindView(root);
        lastNameInput.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE)
            onProceed();

        return false;
    }

    @OnTextChanged(value = R.id.first_name_input, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void updateFirstName(CharSequence source, int start, int before, int count){
        user.setFirstName(source.toString());
        validateProfile();
    }

    @OnTextChanged(value = R.id.last_name_input, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void updateLastName(Editable source){
        user.setLastName(source.toString());
        validateProfile();
    }

    @OnClick(R.id.proceed_button)
    void onProceed(){
        if(ProfileRack.isProfileCorrect(user))
            AnimateClose.with(proceedButton, new SignalProfileChanged(user));
        else Toast.makeText(getContext(), R.string.dont_lie, Toast.LENGTH_SHORT).show();
    }

    private void validateProfile() {
        if(getContext()!=null)
            AnimatePop.with(getContext(), proceedButton, ProfileRack.isProfileCorrect(user));
    }
}
