package com.google.android.exoplayer.hls;

import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class HlsExtractorWrapper implements ExtractorOutput {
    private final int adaptiveMaxHeight;
    private final int adaptiveMaxWidth;
    private Allocator allocator;
    private final Extractor extractor;
    public final Format format;
    private boolean prepared;
    private MediaFormat[] sampleQueueFormats;
    private final SparseArray<DefaultTrackOutput> sampleQueues = new SparseArray<>();
    private final boolean shouldSpliceIn;
    private boolean spliceConfigured;
    public final long startTimeUs;
    private volatile boolean tracksBuilt;
    public final int trigger;

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void drmInitData(DrmInitData drmInitData) {
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
    }

    public HlsExtractorWrapper(int i, Format format, long j, Extractor extractor, boolean z, int i2, int i3) {
        this.trigger = i;
        this.format = format;
        this.startTimeUs = j;
        this.extractor = extractor;
        this.shouldSpliceIn = z;
        this.adaptiveMaxWidth = i2;
        this.adaptiveMaxHeight = i3;
    }

    public void init(Allocator allocator) {
        this.allocator = allocator;
        this.extractor.init(this);
    }

    public boolean isPrepared() {
        int i;
        if (!this.prepared && this.tracksBuilt) {
            for (int i2 = 0; i2 < this.sampleQueues.size(); i2++) {
                if (!this.sampleQueues.valueAt(i2).hasFormat()) {
                    return false;
                }
            }
            this.prepared = true;
            this.sampleQueueFormats = new MediaFormat[this.sampleQueues.size()];
            for (int i3 = 0; i3 < this.sampleQueueFormats.length; i3++) {
                MediaFormat format = this.sampleQueues.valueAt(i3).getFormat();
                if (MimeTypes.isVideo(format.mimeType) && ((i = this.adaptiveMaxWidth) != -1 || this.adaptiveMaxHeight != -1)) {
                    format = format.copyWithMaxVideoDimensions(i, this.adaptiveMaxHeight);
                }
                this.sampleQueueFormats[i3] = format;
            }
        }
        return this.prepared;
    }

    public void clear() {
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            this.sampleQueues.valueAt(i).clear();
        }
    }

    public long getLargestParsedTimestampUs() {
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            jMax = Math.max(jMax, this.sampleQueues.valueAt(i).getLargestParsedTimestampUs());
        }
        return jMax;
    }

    public final void configureSpliceTo(HlsExtractorWrapper hlsExtractorWrapper) {
        Assertions.checkState(isPrepared());
        if (!this.spliceConfigured && hlsExtractorWrapper.shouldSpliceIn && hlsExtractorWrapper.isPrepared()) {
            int trackCount = getTrackCount();
            boolean zConfigureSpliceTo = true;
            for (int i = 0; i < trackCount; i++) {
                zConfigureSpliceTo &= this.sampleQueues.valueAt(i).configureSpliceTo(hlsExtractorWrapper.sampleQueues.valueAt(i));
            }
            this.spliceConfigured = zConfigureSpliceTo;
        }
    }

    public int getTrackCount() {
        Assertions.checkState(isPrepared());
        return this.sampleQueues.size();
    }

    public MediaFormat getMediaFormat(int i) {
        Assertions.checkState(isPrepared());
        return this.sampleQueueFormats[i];
    }

    public boolean getSample(int i, SampleHolder sampleHolder) {
        Assertions.checkState(isPrepared());
        return this.sampleQueues.valueAt(i).getSample(sampleHolder);
    }

    public void discardUntil(int i, long j) {
        Assertions.checkState(isPrepared());
        this.sampleQueues.valueAt(i).discardUntil(j);
    }

    public boolean hasSamples(int i) {
        Assertions.checkState(isPrepared());
        return !this.sampleQueues.valueAt(i).isEmpty();
    }

    public int read(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i = this.extractor.read(extractorInput, null);
        Assertions.checkState(i != 1);
        return i;
    }

    public long getAdjustedEndTimeUs() {
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            jMax = Math.max(jMax, this.sampleQueues.valueAt(i).getLargestParsedTimestampUs());
        }
        return jMax;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public TrackOutput track(int i) {
        DefaultTrackOutput defaultTrackOutput = new DefaultTrackOutput(this.allocator);
        this.sampleQueues.put(i, defaultTrackOutput);
        return defaultTrackOutput;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksBuilt = true;
    }
}
