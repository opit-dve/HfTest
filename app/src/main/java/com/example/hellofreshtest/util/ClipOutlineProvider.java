package com.example.hellofreshtest.util;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by srd on 3/26/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ClipOutlineProvider extends ViewOutlineProvider {
    @Override
    public void getOutline(View view, Outline outline) {
        final int margin = Math.min(view.getWidth(), view.getHeight()) / 10;
        outline.setRoundRect(margin, margin, view.getWidth() - margin,
                view.getHeight() - margin, margin / 2);
    }
}
