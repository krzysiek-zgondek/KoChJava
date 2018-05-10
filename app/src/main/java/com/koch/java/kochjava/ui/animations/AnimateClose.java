package com.koch.java.kochjava.ui.animations;

import android.view.View;
import android.view.animation.BounceInterpolator;

public class AnimateClose {
    public static void with(View view, Runnable endAction) {
        view.animate().setInterpolator(new BounceInterpolator())
                .scaleX(0f).scaleY(0f).setStartDelay(250).withEndAction(endAction).start();
    }
}
