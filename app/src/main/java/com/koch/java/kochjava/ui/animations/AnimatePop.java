package com.koch.java.kochjava.ui.animations;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class AnimatePop {
    public static void with(Context context, View view, boolean isPopped) {
        final float scale = isPopped ? 1.5f : 1f;

        float delta = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 24, context.getResources().getDisplayMetrics()
        );

        view.setVisibility(isPopped ? View.VISIBLE : View.INVISIBLE);
        view.animate().setInterpolator(new BounceInterpolator())
                .translationX((1f-scale)*delta)
                .translationY((scale-1f)*delta)
                .scaleX(scale).scaleY(scale).start();
    }
}
