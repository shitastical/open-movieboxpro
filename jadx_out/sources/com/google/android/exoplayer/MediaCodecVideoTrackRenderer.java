package com.google.android.exoplayer;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* JADX INFO: loaded from: classes4.dex */
public class MediaCodecVideoTrackRenderer extends MediaCodecTrackRenderer {
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    public static final int MSG_SET_SURFACE = 1;
    private final long allowedJoiningTimeUs;
    private int consecutiveDroppedFrameCount;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrameCount;
    private final EventListener eventListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long joiningDeadlineUs;
    private int lastReportedHeight;
    private float lastReportedPixelWidthHeightRatio;
    private int lastReportedUnappliedRotationDegrees;
    private int lastReportedWidth;
    private final int maxDroppedFrameCountToNotify;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private boolean reportedDrawnToSurface;
    private Surface surface;
    private final int videoScalingMode;

    public interface EventListener extends MediaCodecTrackRenderer.EventListener {
        void onDrawnToSurface(Surface surface);

        void onDroppedFrames(int i, long j);

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i) {
        this(context, sampleSource, mediaCodecSelector, i, 0L);
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i, long j) {
        this(context, sampleSource, mediaCodecSelector, i, j, null, null, -1);
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i, long j, Handler handler, EventListener eventListener, int i2) {
        this(context, sampleSource, mediaCodecSelector, i, j, null, false, handler, eventListener, i2);
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, EventListener eventListener, int i2) {
        super(sampleSource, mediaCodecSelector, drmSessionManager, z, handler, eventListener);
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(context);
        this.videoScalingMode = i;
        this.allowedJoiningTimeUs = 1000 * j;
        this.eventListener = eventListener;
        this.maxDroppedFrameCountToNotify = i2;
        this.joiningDeadlineUs = -1L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean handlesTrack(MediaCodecSelector mediaCodecSelector, MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        String str = mediaFormat.mimeType;
        if (MimeTypes.isVideo(str)) {
            return "video/x-unknown".equals(str) || mediaCodecSelector.getDecoderInfo(str, false) != null;
        }
        return false;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        super.onEnabled(i, j, z);
        if (z && this.allowedJoiningTimeUs > 0) {
            this.joiningDeadlineUs = (SystemClock.elapsedRealtime() * 1000) + this.allowedJoiningTimeUs;
        }
        this.frameReleaseTimeHelper.enable();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
        super.onDiscontinuity(j);
        this.renderedFirstFrame = false;
        this.consecutiveDroppedFrameCount = 0;
        this.joiningDeadlineUs = -1L;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        if (super.isReady() && (this.renderedFirstFrame || !codecInitialized() || getSourceState() == 2)) {
            this.joiningDeadlineUs = -1L;
            return true;
        }
        if (this.joiningDeadlineUs == -1) {
            return false;
        }
        if (SystemClock.elapsedRealtime() * 1000 < this.joiningDeadlineUs) {
            return true;
        }
        this.joiningDeadlineUs = -1L;
        return false;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStarted() {
        super.onStarted();
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStopped() {
        this.joiningDeadlineUs = -1L;
        maybeNotifyDroppedFrameCount();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled() throws ExoPlaybackException {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
        this.frameReleaseTimeHelper.disable();
        super.onDisabled();
    }

    @Override // com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface) throws ExoPlaybackException {
        if (this.surface == surface) {
            return;
        }
        this.surface = surface;
        this.reportedDrawnToSurface = false;
        int state = getState();
        if (state == 2 || state == 3) {
            releaseCodec();
            maybeInitCodec();
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean shouldInitCodec() {
        Surface surface;
        return super.shouldInitCodec() && (surface = this.surface) != null && surface.isValid();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void configureCodec(MediaCodec mediaCodec, boolean z, android.media.MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        maybeSetMaxInputSize(mediaFormat, z);
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onInputFormatChanged(MediaFormatHolder mediaFormatHolder) throws ExoPlaybackException {
        super.onInputFormatChanged(mediaFormatHolder);
        this.pendingPixelWidthHeightRatio = mediaFormatHolder.format.pixelWidthHeightRatio == -1.0f ? 1.0f : mediaFormatHolder.format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = mediaFormatHolder.format.rotationDegrees == -1 ? 0 : mediaFormatHolder.format.rotationDegrees;
    }

    protected final boolean haveRenderedFirstFrame() {
        return this.renderedFirstFrame;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        int integer;
        int integer2;
        boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
        if (z) {
            integer = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.currentWidth = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.currentHeight = integer2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            int i = this.pendingRotationDegrees;
            if (i == 90 || i == 270) {
                int i2 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i2;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.videoScalingMode);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
        return mediaFormat2.mimeType.equals(mediaFormat.mimeType) && (z || (mediaFormat.width == mediaFormat2.width && mediaFormat.height == mediaFormat2.height));
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, int i, boolean z) {
        if (z) {
            skipOutputBuffer(mediaCodec, i);
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (!this.renderedFirstFrame) {
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec, i, System.nanoTime());
            } else {
                renderOutputBuffer(mediaCodec, i);
            }
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (getState() != 3) {
            return false;
        }
        long jElapsedRealtime = (bufferInfo.presentationTimeUs - j) - ((SystemClock.elapsedRealtime() * 1000) - j2);
        long jNanoTime = System.nanoTime();
        long jAdjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(bufferInfo.presentationTimeUs, (jElapsedRealtime * 1000) + jNanoTime);
        long j3 = (jAdjustReleaseTime - jNanoTime) / 1000;
        if (j3 < -30000) {
            dropOutputBuffer(mediaCodec, i);
            return true;
        }
        if (Util.SDK_INT >= 21) {
            if (j3 < 50000) {
                renderOutputBufferV21(mediaCodec, i, jAdjustReleaseTime);
                this.consecutiveDroppedFrameCount = 0;
                return true;
            }
        } else if (j3 < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) {
            if (j3 > 11000) {
                try {
                    Thread.sleep((j3 - 10000) / 1000);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            renderOutputBuffer(mediaCodec, i);
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        return false;
    }

    protected void skipOutputBuffer(MediaCodec mediaCodec, int i) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.codecCounters.skippedOutputBufferCount++;
    }

    protected void dropOutputBuffer(MediaCodec mediaCodec, int i) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.codecCounters.droppedOutputBufferCount++;
        this.droppedFrameCount++;
        this.consecutiveDroppedFrameCount++;
        this.codecCounters.maxConsecutiveDroppedOutputBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.codecCounters.maxConsecutiveDroppedOutputBufferCount);
        if (this.droppedFrameCount == this.maxDroppedFrameCountToNotify) {
            maybeNotifyDroppedFrameCount();
        }
    }

    protected void renderOutputBuffer(MediaCodec mediaCodec, int i) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.codecCounters.renderedOutputBufferCount++;
        this.renderedFirstFrame = true;
        maybeNotifyDrawnToSurface();
    }

    protected void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j);
        TraceUtil.endSection();
        this.codecCounters.renderedOutputBufferCount++;
        this.renderedFirstFrame = true;
        maybeNotifyDrawnToSurface();
    }

    private void maybeSetMaxInputSize(android.media.MediaFormat mediaFormat, boolean z) {
        int i;
        if (mediaFormat.containsKey("max-input-size")) {
        }
        int integer = mediaFormat.getInteger("height");
        if (z && mediaFormat.containsKey("max-height")) {
            integer = Math.max(integer, mediaFormat.getInteger("max-height"));
        }
        int integer2 = mediaFormat.getInteger("width");
        if (z && mediaFormat.containsKey("max-width")) {
            integer2 = Math.max(integer, mediaFormat.getInteger("max-width"));
        }
        String string = mediaFormat.getString(IMediaFormat.KEY_MIME);
        string.hashCode();
        int i2 = 4;
        switch (string) {
            case "video/3gpp":
            case "video/mp4v-es":
            case "video/x-vnd.on2.vp8":
                i = integer2 * integer;
                i2 = 2;
                mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                break;
            case "video/hevc":
            case "video/x-vnd.on2.vp9":
                i = integer2 * integer;
                mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                break;
            case "video/avc":
                if (!"BRAVIA 4K 2015".equals(Util.MODEL)) {
                    i = ((integer2 + 15) / 16) * ((integer + 15) / 16) * 256;
                    i2 = 2;
                    mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
                    break;
                }
                break;
        }
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.eventHandler == null || this.eventListener == null) {
            return;
        }
        int i = this.lastReportedWidth;
        final int i2 = this.currentWidth;
        if (i == i2 && this.lastReportedHeight == this.currentHeight && this.lastReportedUnappliedRotationDegrees == this.currentUnappliedRotationDegrees && this.lastReportedPixelWidthHeightRatio == this.currentPixelWidthHeightRatio) {
            return;
        }
        final int i3 = this.currentHeight;
        final int i4 = this.currentUnappliedRotationDegrees;
        final float f = this.currentPixelWidthHeightRatio;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecVideoTrackRenderer.this.eventListener.onVideoSizeChanged(i2, i3, i4, f);
            }
        });
        this.lastReportedWidth = i2;
        this.lastReportedHeight = i3;
        this.lastReportedUnappliedRotationDegrees = i4;
        this.lastReportedPixelWidthHeightRatio = f;
    }

    private void maybeNotifyDrawnToSurface() {
        if (this.eventHandler == null || this.eventListener == null || this.reportedDrawnToSurface) {
            return;
        }
        final Surface surface = this.surface;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecVideoTrackRenderer.this.eventListener.onDrawnToSurface(surface);
            }
        });
        this.reportedDrawnToSurface = true;
    }

    private void maybeNotifyDroppedFrameCount() {
        if (this.eventHandler == null || this.eventListener == null || this.droppedFrameCount == 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        final int i = this.droppedFrameCount;
        final long j = jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecVideoTrackRenderer.this.eventListener.onDroppedFrames(i, j);
            }
        });
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
    }
}
