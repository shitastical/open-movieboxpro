package com.google.android.exoplayer;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SampleSourceTrackRenderer extends TrackRenderer {
    private long durationUs;
    private SampleSource.SampleSourceReader enabledSource;
    private int enabledSourceTrackIndex;
    private int[] handledSourceIndices;
    private int[] handledSourceTrackIndices;
    private final SampleSource.SampleSourceReader[] sources;

    protected abstract void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException;

    protected abstract boolean handlesTrack(MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException;

    protected abstract void onDiscontinuity(long j) throws ExoPlaybackException;

    protected long shiftInputPosition(long j) {
        return j;
    }

    public SampleSourceTrackRenderer(SampleSource... sampleSourceArr) {
        this.sources = new SampleSource.SampleSourceReader[sampleSourceArr.length];
        for (int i = 0; i < sampleSourceArr.length; i++) {
            this.sources[i] = sampleSourceArr[i].register();
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x006e A[PHI: r9
      0x006e: PHI (r9v3 int) = (r9v2 int), (r9v5 int) binds: [B:18:0x0049, B:20:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.exoplayer.TrackRenderer
    protected final boolean doPrepare(long j) throws ExoPlaybackException {
        SampleSource.SampleSourceReader[] sampleSourceReaderArr;
        int[] iArr;
        int i = 0;
        boolean zPrepare = true;
        while (true) {
            SampleSource.SampleSourceReader[] sampleSourceReaderArr2 = this.sources;
            if (i >= sampleSourceReaderArr2.length) {
                break;
            }
            zPrepare &= sampleSourceReaderArr2[i].prepare(j);
            i++;
        }
        if (!zPrepare) {
            return false;
        }
        int i2 = 0;
        int trackCount = 0;
        while (true) {
            sampleSourceReaderArr = this.sources;
            if (i2 >= sampleSourceReaderArr.length) {
                break;
            }
            trackCount += sampleSourceReaderArr[i2].getTrackCount();
            i2++;
        }
        int[] iArr2 = new int[trackCount];
        int[] iArr3 = new int[trackCount];
        int length = sampleSourceReaderArr.length;
        long jMax = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            SampleSource.SampleSourceReader sampleSourceReader = this.sources[i4];
            int trackCount2 = sampleSourceReader.getTrackCount();
            int i5 = 0;
            while (i5 < trackCount2) {
                MediaFormat format = sampleSourceReader.getFormat(i5);
                try {
                    if (handlesTrack(format)) {
                        iArr2[i3] = i4;
                        iArr3[i3] = i5;
                        i3++;
                        if (jMax == -1) {
                            iArr = iArr2;
                        } else {
                            iArr = iArr2;
                            long j2 = format.durationUs;
                            if (j2 == -1) {
                                jMax = -1;
                            } else if (j2 != -2) {
                                jMax = Math.max(jMax, j2);
                            }
                        }
                    } else {
                        iArr = iArr2;
                    }
                    i5++;
                    iArr2 = iArr;
                } catch (MediaCodecUtil.DecoderQueryException e) {
                    throw new ExoPlaybackException(e);
                }
            }
        }
        this.durationUs = jMax;
        this.handledSourceIndices = Arrays.copyOf(iArr2, i3);
        this.handledSourceTrackIndices = Arrays.copyOf(iArr3, i3);
        return true;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        long jShiftInputPosition = shiftInputPosition(j);
        SampleSource.SampleSourceReader sampleSourceReader = this.sources[this.handledSourceIndices[i]];
        this.enabledSource = sampleSourceReader;
        int i2 = this.handledSourceTrackIndices[i];
        this.enabledSourceTrackIndex = i2;
        sampleSourceReader.enable(i2, jShiftInputPosition);
        onDiscontinuity(jShiftInputPosition);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final void seekTo(long j) throws ExoPlaybackException {
        long jShiftInputPosition = shiftInputPosition(j);
        this.enabledSource.seekToUs(jShiftInputPosition);
        checkForDiscontinuity(jShiftInputPosition);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final void doSomeWork(long j, long j2) throws ExoPlaybackException {
        long jShiftInputPosition = shiftInputPosition(j);
        doSomeWork(checkForDiscontinuity(jShiftInputPosition), j2, this.enabledSource.continueBuffering(this.enabledSourceTrackIndex, jShiftInputPosition));
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return this.enabledSource.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void maybeThrowError() throws ExoPlaybackException {
        SampleSource.SampleSourceReader sampleSourceReader = this.enabledSource;
        if (sampleSourceReader != null) {
            maybeThrowError(sampleSourceReader);
            return;
        }
        int length = this.sources.length;
        for (int i = 0; i < length; i++) {
            maybeThrowError(this.sources[i]);
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onDisabled() throws ExoPlaybackException {
        this.enabledSource.disable(this.enabledSourceTrackIndex);
        this.enabledSource = null;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void onReleased() throws ExoPlaybackException {
        int length = this.sources.length;
        for (int i = 0; i < length; i++) {
            this.sources[i].release();
        }
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final int getTrackCount() {
        return this.handledSourceTrackIndices.length;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected final MediaFormat getFormat(int i) {
        return this.sources[this.handledSourceIndices[i]].getFormat(this.handledSourceTrackIndices[i]);
    }

    protected final int readSource(long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        return this.enabledSource.readData(this.enabledSourceTrackIndex, j, mediaFormatHolder, sampleHolder);
    }

    private long checkForDiscontinuity(long j) throws ExoPlaybackException {
        long discontinuity = this.enabledSource.readDiscontinuity(this.enabledSourceTrackIndex);
        if (discontinuity == Long.MIN_VALUE) {
            return j;
        }
        onDiscontinuity(discontinuity);
        return discontinuity;
    }

    private void maybeThrowError(SampleSource.SampleSourceReader sampleSourceReader) throws ExoPlaybackException {
        try {
            sampleSourceReader.maybeThrowError();
        } catch (IOException e) {
            throw new ExoPlaybackException(e);
        }
    }
}
