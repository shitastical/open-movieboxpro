package com.google.android.exoplayer.extractor.mp3;

/* JADX INFO: loaded from: classes4.dex */
final class ConstantBitrateSeeker implements Mp3Extractor.Seeker {
    private static final int BITS_PER_BYTE = 8;
    private final int bitrate;
    private final long durationUs;
    private final long firstFramePosition;

    public ConstantBitrateSeeker(long j, int i, long j2) {
        this.firstFramePosition = j;
        this.bitrate = i;
        this.durationUs = j2 != -1 ? getTimeUs(j2) : -1L;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return this.durationUs != -1;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        if (this.durationUs == -1) {
            return 0L;
        }
        return ((j * ((long) this.bitrate)) / 8000000) + this.firstFramePosition;
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j) {
        return (Math.max(0L, j - this.firstFramePosition) * 8000000) / ((long) this.bitrate);
    }

    @Override // com.google.android.exoplayer.extractor.mp3.Mp3Extractor.Seeker
    public long getDurationUs() {
        return this.durationUs;
    }
}
