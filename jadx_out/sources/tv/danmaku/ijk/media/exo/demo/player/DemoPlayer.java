package tv.danmaku.ijk.media.exo.demo.player;

import android.media.MediaCodec;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import com.google.android.exoplayer.CodecCounters;
import com.google.android.exoplayer.DummyTrackRenderer;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SingleSampleSource;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioTrack;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.metadata.MetadataTrackRenderer;
import com.google.android.exoplayer.metadata.id3.Id3Frame;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.util.DebugTextViewHelper;
import com.google.android.exoplayer.util.PlayerControl;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes6.dex */
public class DemoPlayer implements ExoPlayer.Listener, ChunkSampleSource.EventListener, HlsSampleSource.EventListener, ExtractorSampleSource.EventListener, SingleSampleSource.EventListener, BandwidthMeter.EventListener, MediaCodecVideoTrackRenderer.EventListener, MediaCodecAudioTrackRenderer.EventListener, StreamingDrmSessionManager.EventListener, DashChunkSource.EventListener, TextRenderer, MetadataTrackRenderer.MetadataRenderer<List<Id3Frame>>, DebugTextViewHelper.Provider {
    private static final int RENDERER_BUILDING_STATE_BUILDING = 2;
    private static final int RENDERER_BUILDING_STATE_BUILT = 3;
    private static final int RENDERER_BUILDING_STATE_IDLE = 1;
    public static final int RENDERER_COUNT = 4;
    public static final int STATE_BUFFERING = 3;
    public static final int STATE_ENDED = 5;
    public static final int STATE_IDLE = 1;
    public static final int STATE_PREPARING = 2;
    public static final int STATE_READY = 4;
    public static final int TRACK_DEFAULT = 0;
    public static final int TRACK_DISABLED = -1;
    public static final int TYPE_AUDIO = 1;
    public static final int TYPE_METADATA = 3;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_VIDEO = 0;
    private boolean backgrounded;
    private BandwidthMeter bandwidthMeter;
    private CaptionListener captionListener;
    private CodecCounters codecCounters;
    private Id3MetadataListener id3MetadataListener;
    private InfoListener infoListener;
    private InternalErrorListener internalErrorListener;
    private boolean lastReportedPlayWhenReady;
    private int lastReportedPlaybackState;
    private final CopyOnWriteArrayList<Listener> listeners;
    private final Handler mainHandler;
    private final ExoPlayer player;
    private final PlayerControl playerControl;
    private final RendererBuilder rendererBuilder;
    private int rendererBuildingState;
    private Surface surface;
    private Format videoFormat;
    private TrackRenderer videoRenderer;
    private int videoTrackToRestore;

    public interface CaptionListener {
        void onCues(List<Cue> list);
    }

    public interface Id3MetadataListener {
        void onId3Metadata(List<Id3Frame> list);
    }

    public interface InfoListener {
        void onAudioFormatEnabled(Format format, int i, long j);

        void onAvailableRangeChanged(int i, TimeRange timeRange);

        void onBandwidthSample(int i, long j, long j2);

        void onDecoderInitialized(String str, long j, long j2);

        void onDroppedFrames(int i, long j);

        void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5);

        void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3);

        void onVideoFormatEnabled(Format format, int i, long j);
    }

    public interface InternalErrorListener {
        void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException);

        void onAudioTrackUnderrun(int i, long j, long j2);

        void onAudioTrackWriteError(AudioTrack.WriteException writeException);

        void onCryptoError(MediaCodec.CryptoException cryptoException);

        void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException);

        void onDrmSessionManagerError(Exception exc);

        void onLoadError(int i, IOException iOException);

        void onRendererInitializationError(Exception exc);
    }

    public interface Listener {
        void onError(Exception exc);

        void onStateChanged(boolean z, int i);

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    public interface RendererBuilder {
        void buildRenderers(DemoPlayer demoPlayer);

        void cancel();
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDrawnToSurface(Surface surface) {
    }

    @Override // com.google.android.exoplayer.drm.StreamingDrmSessionManager.EventListener
    public void onDrmKeysLoaded() {
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadCanceled(int i, long j) {
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayWhenReadyCommitted() {
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onUpstreamDiscarded(int i, long j, long j2) {
    }

    public DemoPlayer(RendererBuilder rendererBuilder) {
        this.rendererBuilder = rendererBuilder;
        ExoPlayer exoPlayerNewInstance = ExoPlayer.Factory.newInstance(4, 1000, 5000);
        this.player = exoPlayerNewInstance;
        exoPlayerNewInstance.addListener(this);
        this.playerControl = new PlayerControl(exoPlayerNewInstance);
        this.mainHandler = new Handler();
        this.listeners = new CopyOnWriteArrayList<>();
        this.lastReportedPlaybackState = 1;
        this.rendererBuildingState = 1;
        exoPlayerNewInstance.setSelectedTrack(2, -1);
    }

    public PlayerControl getPlayerControl() {
        return this.playerControl;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void setInternalErrorListener(InternalErrorListener internalErrorListener) {
        this.internalErrorListener = internalErrorListener;
    }

    public void setInfoListener(InfoListener infoListener) {
        this.infoListener = infoListener;
    }

    public void setCaptionListener(CaptionListener captionListener) {
        this.captionListener = captionListener;
    }

    public void setMetadataListener(Id3MetadataListener id3MetadataListener) {
        this.id3MetadataListener = id3MetadataListener;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
        pushSurface(false);
    }

    public Surface getSurface() {
        return this.surface;
    }

    public void blockingClearSurface() {
        this.surface = null;
        pushSurface(true);
    }

    public int getTrackCount(int i) {
        return this.player.getTrackCount(i);
    }

    public MediaFormat getTrackFormat(int i, int i2) {
        return this.player.getTrackFormat(i, i2);
    }

    public int getSelectedTrack(int i) {
        return this.player.getSelectedTrack(i);
    }

    public void setSelectedTrack(int i, int i2) {
        CaptionListener captionListener;
        this.player.setSelectedTrack(i, i2);
        if (i != 2 || i2 >= 0 || (captionListener = this.captionListener) == null) {
            return;
        }
        captionListener.onCues(Collections.emptyList());
    }

    public boolean getBackgrounded() {
        return this.backgrounded;
    }

    public void setBackgrounded(boolean z) {
        if (this.backgrounded == z) {
            return;
        }
        this.backgrounded = z;
        if (z) {
            this.videoTrackToRestore = getSelectedTrack(0);
            setSelectedTrack(0, -1);
            blockingClearSurface();
            return;
        }
        setSelectedTrack(0, this.videoTrackToRestore);
    }

    public void prepare() {
        if (this.rendererBuildingState == 3) {
            this.player.stop();
        }
        this.rendererBuilder.cancel();
        this.videoFormat = null;
        this.videoRenderer = null;
        this.rendererBuildingState = 2;
        maybeReportPlayerState();
        this.rendererBuilder.buildRenderers(this);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001b A[PHI: r1
      0x001b: PHI (r1v7 com.google.android.exoplayer.TrackRenderer) = (r1v2 com.google.android.exoplayer.TrackRenderer), (r1v4 com.google.android.exoplayer.TrackRenderer) binds: [B:10:0x0019, B:13:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
    void onRenderers(TrackRenderer[] trackRendererArr, BandwidthMeter bandwidthMeter) {
        CodecCounters codecCounters;
        for (int i = 0; i < 4; i++) {
            if (trackRendererArr[i] == null) {
                trackRendererArr[i] = new DummyTrackRenderer();
            }
        }
        TrackRenderer trackRenderer = trackRendererArr[0];
        this.videoRenderer = trackRenderer;
        if (trackRenderer instanceof MediaCodecTrackRenderer) {
            codecCounters = ((MediaCodecTrackRenderer) trackRenderer).codecCounters;
        } else {
            trackRenderer = trackRendererArr[1];
            if (trackRenderer instanceof MediaCodecTrackRenderer) {
                codecCounters = ((MediaCodecTrackRenderer) trackRenderer).codecCounters;
            } else {
                codecCounters = null;
            }
        }
        this.codecCounters = codecCounters;
        this.bandwidthMeter = bandwidthMeter;
        pushSurface(false);
        this.player.prepare(trackRendererArr);
        this.rendererBuildingState = 3;
    }

    void onRenderersError(Exception exc) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onRendererInitializationError(exc);
        }
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onError(exc);
        }
        this.rendererBuildingState = 1;
        maybeReportPlayerState();
    }

    public void setPlayWhenReady(boolean z) {
        this.player.setPlayWhenReady(z);
    }

    public void seekTo(long j) {
        this.player.seekTo(j);
    }

    public void release() {
        this.rendererBuilder.cancel();
        this.rendererBuildingState = 1;
        this.surface = null;
        this.player.release();
    }

    public int getPlaybackState() {
        if (this.rendererBuildingState == 2) {
            return 2;
        }
        int playbackState = this.player.getPlaybackState();
        if (this.rendererBuildingState == 3 && playbackState == 1) {
            return 2;
        }
        return playbackState;
    }

    @Override // com.google.android.exoplayer.util.DebugTextViewHelper.Provider
    public Format getFormat() {
        return this.videoFormat;
    }

    @Override // com.google.android.exoplayer.util.DebugTextViewHelper.Provider
    public BandwidthMeter getBandwidthMeter() {
        return this.bandwidthMeter;
    }

    @Override // com.google.android.exoplayer.util.DebugTextViewHelper.Provider
    public CodecCounters getCodecCounters() {
        return this.codecCounters;
    }

    @Override // com.google.android.exoplayer.util.DebugTextViewHelper.Provider
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    public long getDuration() {
        return this.player.getDuration();
    }

    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    Looper getPlaybackLooper() {
        return this.player.getPlaybackLooper();
    }

    Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerStateChanged(boolean z, int i) {
        maybeReportPlayerState();
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        this.rendererBuildingState = 1;
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onError(exoPlaybackException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(i, i2, i3, f);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDroppedFrames(int i, long j) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onDroppedFrames(i, j);
        }
    }

    @Override // com.google.android.exoplayer.upstream.BandwidthMeter.EventListener
    public void onBandwidthSample(int i, long j, long j2) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onBandwidthSample(i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onDownstreamFormatChanged(int i, Format format, int i2, long j) {
        InfoListener infoListener = this.infoListener;
        if (infoListener == null) {
            return;
        }
        if (i == 0) {
            this.videoFormat = format;
            infoListener.onVideoFormatEnabled(format, i2, j);
        } else if (i == 1) {
            infoListener.onAudioFormatEnabled(format, i2, j);
        }
    }

    @Override // com.google.android.exoplayer.drm.StreamingDrmSessionManager.EventListener
    public void onDrmSessionManagerError(Exception exc) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onDrmSessionManagerError(exc);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onDecoderInitializationError(decoderInitializationException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackInitializationError(initializationException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackWriteError(AudioTrack.WriteException writeException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackWriteError(writeException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackUnderrun(int i, long j, long j2) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackUnderrun(i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onCryptoError(MediaCodec.CryptoException cryptoException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onCryptoError(cryptoException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitialized(String str, long j, long j2) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onDecoderInitialized(str, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener, com.google.android.exoplayer.extractor.ExtractorSampleSource.EventListener, com.google.android.exoplayer.SingleSampleSource.EventListener
    public void onLoadError(int i, IOException iOException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onLoadError(i, iOException);
        }
    }

    @Override // com.google.android.exoplayer.text.TextRenderer
    public void onCues(List<Cue> list) {
        if (this.captionListener == null || getSelectedTrack(2) == -1) {
            return;
        }
        this.captionListener.onCues(list);
    }

    @Override // com.google.android.exoplayer.metadata.MetadataTrackRenderer.MetadataRenderer
    public void onMetadata(List<Id3Frame> list) {
        if (this.id3MetadataListener == null || getSelectedTrack(3) == -1) {
            return;
        }
        this.id3MetadataListener.onId3Metadata(list);
    }

    @Override // com.google.android.exoplayer.dash.DashChunkSource.EventListener
    public void onAvailableRangeChanged(int i, TimeRange timeRange) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onAvailableRangeChanged(i, timeRange);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onLoadStarted(i, j, i2, i3, format, j2, j3);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onLoadCompleted(i, j, i2, i3, format, j2, j3, j4, j5);
        }
    }

    private void maybeReportPlayerState() {
        boolean playWhenReady = this.player.getPlayWhenReady();
        int playbackState = getPlaybackState();
        if (this.lastReportedPlayWhenReady == playWhenReady && this.lastReportedPlaybackState == playbackState) {
            return;
        }
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(playWhenReady, playbackState);
        }
        this.lastReportedPlayWhenReady = playWhenReady;
        this.lastReportedPlaybackState = playbackState;
    }

    private void pushSurface(boolean z) {
        TrackRenderer trackRenderer = this.videoRenderer;
        if (trackRenderer == null) {
            return;
        }
        if (z) {
            this.player.blockingSendMessage(trackRenderer, 1, this.surface);
        } else {
            this.player.sendMessage(trackRenderer, 1, this.surface);
        }
    }
}
