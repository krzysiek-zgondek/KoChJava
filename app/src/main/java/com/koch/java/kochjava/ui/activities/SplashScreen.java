package com.koch.java.kochjava.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.koch.java.kochjava.R;

//just for fun
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override public void run() { switchToMainActivity(); }
        }, 1000);
    }

    private void switchToMainActivity() {
        startActivity(
                new Intent(this, MainActivity.class)
        );

        supportFinishAfterTransition();
    }
}
