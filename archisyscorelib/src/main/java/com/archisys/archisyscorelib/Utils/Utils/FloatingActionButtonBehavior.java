package com.archisys.archisyscorelib.Utils.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;


public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FrameLayout> {

    boolean checkView = true;

    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FrameLayout child, View dependency) {

        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FrameLayout child, View dependency) {

        if (child.getTranslationY() == 0)
        {
            float translationY = Math.max(0, dependency.getHeight());
            child.setTranslationY(translationY);
            return true;
        }
        return true;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull FrameLayout child, @NonNull View dependency) {
        super.onDependentViewRemoved(parent, child, dependency);

        child.setTranslationY(0.0f);
    }
}