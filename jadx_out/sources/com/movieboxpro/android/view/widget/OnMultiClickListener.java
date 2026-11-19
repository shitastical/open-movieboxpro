package com.movieboxpro.android.view.widget;

import android.view.View;
import androidx.media3.exoplayer.ExoPlayer;

/* JADX INFO: loaded from: classes5.dex */
public abstract class OnMultiClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastClickTime >= ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
            lastClickTime = jCurrentTimeMillis;
            onMultiClick(v);
        }
    }
}
