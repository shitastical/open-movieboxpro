package com.google.android.exoplayer.util.extensions;

import com.google.android.exoplayer.SampleHolder;

/* JADX INFO: loaded from: classes4.dex */
public class InputBuffer extends Buffer {
    public final SampleHolder sampleHolder = new SampleHolder(2);

    @Override // com.google.android.exoplayer.util.extensions.Buffer
    public void reset() {
        super.reset();
        this.sampleHolder.clearData();
    }
}
