package com.google.android.exoplayer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioTrack;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* JADX INFO: loaded from: classes4.dex */
public class MediaCodecAudioTrackRenderer extends MediaCodecTrackRenderer implements MediaClock {
    public static final int MSG_SET_PLAYBACK_PARAMS = 2;
    public static final int MSG_SET_VOLUME = 1;
    private boolean allowPositionDiscontinuity;
    private int audioSessionId;
    private final AudioTrack audioTrack;
    private boolean audioTrackHasData;
    private long currentPositionUs;
    private final EventListener eventListener;
    private long lastFeedElapsedRealtimeMs;
    private boolean passthroughEnabled;
    private android.media.MediaFormat passthroughMediaFormat;
    private int pcmEncoding;

    public interface EventListener extends MediaCodecTrackRenderer.EventListener {
        void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException);

        void onAudioTrackUnderrun(int i, long j, long j2);

        void onAudioTrackWriteError(AudioTrack.WriteException writeException);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected MediaClock getMediaClock() {
        return this;
    }

    protected void handleAudioTrackDiscontinuity() {
    }

    protected void onAudioSessionId(int i) {
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector) {
        this(sampleSource, mediaCodecSelector, (DrmSessionManager) null, true);
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z) {
        this(sampleSource, mediaCodecSelector, drmSessionManager, z, null, null);
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, Handler handler, EventListener eventListener) {
        this(sampleSource, mediaCodecSelector, null, true, handler, eventListener);
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z, Handler handler, EventListener eventListener) {
        this(sampleSource, mediaCodecSelector, drmSessionManager, z, handler, eventListener, (AudioCapabilities) null, 3);
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z, Handler handler, EventListener eventListener, AudioCapabilities audioCapabilities, int i) {
        this(new SampleSource[]{sampleSource}, mediaCodecSelector, drmSessionManager, z, handler, eventListener, audioCapabilities, i);
    }

    public MediaCodecAudioTrackRenderer(SampleSource[] sampleSourceArr, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z, Handler handler, EventListener eventListener, AudioCapabilities audioCapabilities, int i) {
        super(sampleSourceArr, mediaCodecSelector, (DrmSessionManager<FrameworkMediaCrypto>) drmSessionManager, z, handler, eventListener);
        this.eventListener = eventListener;
        this.audioSessionId = 0;
        this.audioTrack = new AudioTrack(audioCapabilities, i);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean handlesTrack(MediaCodecSelector mediaCodecSelector, MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        String str = mediaFormat.mimeType;
        if (MimeTypes.isAudio(str)) {
            return "audio/x-unknown".equals(str) || (allowPassthrough(str) && mediaCodecSelector.getPassthroughDecoderInfo() != null) || mediaCodecSelector.getDecoderInfo(str, false) != null;
        }
        return false;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected DecoderInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
        DecoderInfo passthroughDecoderInfo;
        if (allowPassthrough(str) && (passthroughDecoderInfo = mediaCodecSelector.getPassthroughDecoderInfo()) != null) {
            this.passthroughEnabled = true;
            return passthroughDecoderInfo;
        }
        this.passthroughEnabled = false;
        return super.getDecoderInfo(mediaCodecSelector, str, z);
    }

    protected boolean allowPassthrough(String str) {
        return this.audioTrack.isPassthroughSupported(str);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void configureCodec(MediaCodec mediaCodec, boolean z, android.media.MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        String string = mediaFormat.getString(IMediaFormat.KEY_MIME);
        if (this.passthroughEnabled) {
            mediaFormat.setString(IMediaFormat.KEY_MIME, "audio/raw");
            mediaCodec.configure(mediaFormat, (Surface) null, mediaCrypto, 0);
            mediaFormat.setString(IMediaFormat.KEY_MIME, string);
            this.passthroughMediaFormat = mediaFormat;
            return;
        }
        mediaCodec.configure(mediaFormat, (Surface) null, mediaCrypto, 0);
        this.passthroughMediaFormat = null;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onInputFormatChanged(MediaFormatHolder mediaFormatHolder) throws ExoPlaybackException {
        super.onInputFormatChanged(mediaFormatHolder);
        this.pcmEncoding = "audio/raw".equals(mediaFormatHolder.format.mimeType) ? mediaFormatHolder.format.pcmEncoding : 2;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        android.media.MediaFormat mediaFormat2 = this.passthroughMediaFormat;
        boolean z = mediaFormat2 != null;
        String string = z ? mediaFormat2.getString(IMediaFormat.KEY_MIME) : "audio/raw";
        if (z) {
            mediaFormat = this.passthroughMediaFormat;
        }
        this.audioTrack.configure(string, mediaFormat.getInteger("channel-count"), mediaFormat.getInteger("sample-rate"), this.pcmEncoding);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStarted() {
        super.onStarted();
        this.audioTrack.play();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStopped() {
        this.audioTrack.pause();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return super.isEnded() && !this.audioTrack.hasPendingData();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return this.audioTrack.hasPendingData() || super.isReady();
    }

    @Override // com.google.android.exoplayer.MediaClock
    public long getPositionUs() {
        long currentPositionUs = this.audioTrack.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
        return this.currentPositionUs;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled() throws ExoPlaybackException {
        this.audioSessionId = 0;
        try {
            this.audioTrack.release();
        } finally {
            super.onDisabled();
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
        super.onDiscontinuity(j);
        this.audioTrack.reset();
        this.currentPositionUs = j;
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, int i, boolean z) throws ExoPlaybackException {
        if (this.passthroughEnabled && (bufferInfo.flags & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        }
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.codecCounters.skippedOutputBufferCount++;
            this.audioTrack.handleDiscontinuity();
            return true;
        }
        if (!this.audioTrack.isInitialized()) {
            try {
                int i2 = this.audioSessionId;
                if (i2 != 0) {
                    this.audioTrack.initialize(i2);
                } else {
                    int iInitialize = this.audioTrack.initialize();
                    this.audioSessionId = iInitialize;
                    onAudioSessionId(iInitialize);
                }
                this.audioTrackHasData = false;
                if (getState() == 3) {
                    this.audioTrack.play();
                }
            } catch (AudioTrack.InitializationException e) {
                notifyAudioTrackInitializationError(e);
                throw new ExoPlaybackException(e);
            }
        } else {
            boolean z2 = this.audioTrackHasData;
            boolean zHasPendingData = this.audioTrack.hasPendingData();
            this.audioTrackHasData = zHasPendingData;
            if (z2 && !zHasPendingData && getState() == 3) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - this.lastFeedElapsedRealtimeMs;
                long bufferSizeUs = this.audioTrack.getBufferSizeUs();
                notifyAudioTrackUnderrun(this.audioTrack.getBufferSize(), bufferSizeUs != -1 ? bufferSizeUs / 1000 : -1L, jElapsedRealtime);
            }
        }
        try {
            int iHandleBuffer = this.audioTrack.handleBuffer(byteBuffer, bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs);
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if ((iHandleBuffer & 1) != 0) {
                handleAudioTrackDiscontinuity();
                this.allowPositionDiscontinuity = true;
            }
            if ((iHandleBuffer & 2) == 0) {
                return false;
            }
            mediaCodec.releaseOutputBuffer(i, false);
            this.codecCounters.renderedOutputBufferCount++;
            return true;
        } catch (AudioTrack.WriteException e2) {
            notifyAudioTrackWriteError(e2);
            throw new ExoPlaybackException(e2);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputStreamEnded() {
        this.audioTrack.handleEndOfStream();
    }

    @Override // com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            this.audioTrack.setVolume(((Float) obj).floatValue());
        } else if (i == 2) {
            this.audioTrack.setPlaybackParams(Chip$$ExternalSyntheticApiModelOutline0.m541m(obj));
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void notifyAudioTrackInitializationError(final AudioTrack.InitializationException initializationException) {
        if (this.eventHandler == null || this.eventListener == null) {
            return;
        }
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackInitializationError(initializationException);
            }
        });
    }

    private void notifyAudioTrackWriteError(final AudioTrack.WriteException writeException) {
        if (this.eventHandler == null || this.eventListener == null) {
            return;
        }
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackWriteError(writeException);
            }
        });
    }

    private void notifyAudioTrackUnderrun(final int i, final long j, final long j2) {
        if (this.eventHandler == null || this.eventListener == null) {
            return;
        }
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackUnderrun(i, j, j2);
            }
        });
    }
}
