package com.movieboxpro.android.listener;

import android.widget.SeekBar;

/* JADX INFO: loaded from: classes5.dex */
public interface VideoPreviewListener {
    void onProgressChanged(SeekBar seekBar, int pos, int progress);

    void startDrag();

    void stopDrag();
}
