package com.google.android.exoplayer;

/* JADX INFO: loaded from: classes4.dex */
public final class CodecCounters {
    public int codecInitCount;
    public int codecReleaseCount;
    public int droppedOutputBufferCount;
    public int inputBufferCount;
    public int maxConsecutiveDroppedOutputBufferCount;
    public int outputBuffersChangedCount;
    public int outputFormatChangedCount;
    public int renderedOutputBufferCount;
    public int skippedOutputBufferCount;

    public synchronized void ensureUpdated() {
    }

    public String getDebugString() {
        ensureUpdated();
        return "cic:" + this.codecInitCount + " crc:" + this.codecReleaseCount + " ibc:" + this.inputBufferCount + " ofc:" + this.outputFormatChangedCount + " obc:" + this.outputBuffersChangedCount + " ren:" + this.renderedOutputBufferCount + " sob:" + this.skippedOutputBufferCount + " dob:" + this.droppedOutputBufferCount + " mcdob:" + this.maxConsecutiveDroppedOutputBufferCount;
    }
}
