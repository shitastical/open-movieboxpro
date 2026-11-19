package com.movieboxpro.android.exo;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.download.DownloadTracker;
import com.movieboxpro.android.utils.download.DownloadUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.util.VLCVideoLayout;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: CustomExoPlayer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010)\u001a\u00020*J\b\u0010+\u001a\u00020*H\u0017J\u0012\u0010,\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010.\u001a\u00020*2\b\u0010/\u001a\u0004\u0018\u00010\tH\u0016J\b\u00100\u001a\u00020*H\u0016J\u0012\u00101\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u00102\u001a\u00020\u000b2\b\u0010/\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u00103\u001a\u00020*2\b\u0010/\u001a\u0004\u0018\u00010\tH\u0002J(\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u001dH\u0002J\b\u0010;\u001a\u00020*H\u0002J\b\u0010<\u001a\u00020*H\u0002J\u0010\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020?H\u0002J\u0012\u0010@\u001a\u00020*2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J(\u0010C\u001a\u00020*2\b\u0010D\u001a\u0004\u0018\u00010\t2\u0014\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010FH\u0016J\u001a\u0010C\u001a\u00020*2\b\u0010D\u001a\u0004\u0018\u00010\t2\u0006\u0010G\u001a\u00020\u000fH\u0017J\u0012\u0010C\u001a\u00020*2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\u0012\u0010C\u001a\u00020*2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\b\u0010L\u001a\u00020*H\u0016J\b\u0010M\u001a\u00020*H\u0016J\b\u0010N\u001a\u00020*H\u0016J\b\u0010O\u001a\u00020*H\u0016J\b\u0010P\u001a\u00020*H\u0016J\u0010\u0010Q\u001a\u00020*2\u0006\u0010Q\u001a\u00020\u000bH\u0016J\b\u0010R\u001a\u00020\u000bH\u0016J\u0010\u0010S\u001a\u00020*2\u0006\u0010T\u001a\u00020\u000fH\u0016J\b\u0010U\u001a\u00020*H\u0016J\b\u0010V\u001a\u00020\u000fH\u0016J\b\u0010W\u001a\u00020\u000fH\u0016J\b\u0010X\u001a\u00020\u001dH\u0016J\u0010\u0010Y\u001a\u00020*2\u0006\u0010Z\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\u00020*2\u0006\u0010]\u001a\u00020^H\u0016J\u0018\u0010_\u001a\u00020*2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020aH\u0016J\u0010\u0010_\u001a\u00020*2\u0006\u0010c\u001a\u00020\u001dH\u0016J\u0010\u0010d\u001a\u00020*2\u0006\u0010e\u001a\u00020\u000bH\u0016J\u0010\u0010f\u001a\u00020*2\u0006\u0010g\u001a\u00020\u000bH\u0016J\b\u0010h\u001a\u00020*H\u0016J\u0010\u0010i\u001a\u00020*2\u0006\u0010j\u001a\u00020aH\u0016J\b\u0010k\u001a\u00020\u000fH\u0016J\u0010\u0010l\u001a\u00020*2\u0006\u0010m\u001a\u00020\u000bH\u0016J\b\u0010n\u001a\u00020(H\u0016J\u0010\u0010o\u001a\u00020*2\u0006\u0010p\u001a\u00020\u001dH\u0016J\b\u0010q\u001a\u00020rH\u0016J\u0010\u0010s\u001a\u00020*2\u0006\u0010t\u001a\u00020\u001dH\u0016J\b\u0010u\u001a\u00020\u001dH\u0016J\u0012\u0010v\u001a\u00020\u001d2\b\u0010w\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010x\u001a\u00020*2\b\u0010w\u001a\u0004\u0018\u00010\t2\u0006\u0010y\u001a\u00020\u000bH\u0016J\u0012\u0010z\u001a\u00020*2\b\u0010{\u001a\u0004\u0018\u00010|H\u0016J\u0012\u0010@\u001a\u00020*2\b\u0010A\u001a\u0004\u0018\u00010}H\u0016J\b\u0010~\u001a\u00020\u001dH\u0016J\b\u0010\u007f\u001a\u00020*H\u0016J3\u0010\u0080\u0001\u001a\u00020*2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\t2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\t2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\t2\u0007\u0010\u0084\u0001\u001a\u00020\u001dH\u0016J\u000b\u0010\u0085\u0001\u001a\u0004\u0018\u00010}H\u0016J\u0012\u0010\u0086\u0001\u001a\u00020*2\u0007\u0010\u0087\u0001\u001a\u00020\u000fH\u0016J\u0010\u0010\u0088\u0001\u001a\t\u0012\u0004\u0012\u00020%0\u0089\u0001H\u0016J\u000b\u0010\u008a\u0001\u001a\u0004\u0018\u00010%H\u0016J\u000f\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0019H\u0016J\t\u0010\u008c\u0001\u001a\u00020\u000fH\u0016J\u0011\u0010\u008d\u0001\u001a\u00020*2\u0006\u0010-\u001a\u00020\u001aH\u0016J\u0013\u0010\u008e\u0001\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u001aH\u0016J\t\u0010\u008f\u0001\u001a\u00020\u0017H\u0016J!\u0010\u0090\u0001\u001a\u00020*2\n\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u00012\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\u0019j\b\u0012\u0004\u0012\u00020\u001a`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u0012\u0012\u0004\u0012\u00020%0\u0019j\b\u0012\u0004\u0012\u00020%`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"}, d2 = {"Lcom/movieboxpro/android/exo/CustomExoPlayer;", "Lcom/movieboxpro/android/exo/ExoAbstractPlayer;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "qualityPinHolder", "Lcom/movieboxpro/android/exo/HlsQualityPinHolder;", "pendingVideoQuality", "", "pendingVideoApplied", "", "onVideoPinFailed", "Lcom/movieboxpro/android/exo/OnVideoPinFailed;", "prepareStartedAtRealtimeMs", "", "buildTrackSelectorParameters", "Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector$Parameters;", "mTrackSelector", "Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;", "getMTrackSelector", "()Landroidx/media3/exoplayer/trackselection/DefaultTrackSelector;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "listeners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/exo/PlayListener;", "Lkotlin/collections/ArrayList;", "height", "", "width", "lastReportedPlaybackState", "isLastReportedPlayWhenReady", "isBuffering", "isPreparing", "currSelectId", "audioTracks", "Landroidx/media3/common/Tracks$Group;", "currSelectAudioIndex", "tracks", "Landroidx/media3/common/Tracks;", "setAacOnly", "", "initPlayer", "setOnVideoPinFailed", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPendingVideoQuality", "quality", "clearVideoTrackOverrides", "pinVideoQuality", "stashPending", "applyQualityPin", "logHlsLoad", "action", "loadEventInfo", "Landroidx/media3/exoplayer/source/LoadEventInfo;", "mediaLoadData", "Landroidx/media3/exoplayer/source/MediaLoadData;", "retryCount", "applyPendingVideoQuality", "beginNewPrepareGeneration", "shouldAcceptTracksEvent", "eventTime", "Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;", "setAudioTrack", "trackGroup", "Landroidx/media3/common/TrackSelectionOverride;", "setDataSource", FileDownloadService.PARAMS_KEY_PATH, "headers", "", "startTime", "uri", "Landroid/net/Uri;", IjkMediaPlayer.OnNativeInvokeListener.ARG_FD, "Landroid/content/res/AssetFileDescriptor;", "start", DownloadService.PAUSE, "stop", "prepareAsync", "reset", "mute", "isPlaying", "seekTo", "time", "release", "getCurrentPosition", "getDuration", "getBufferedPercentage", "setSurface", "surface", "Landroid/view/Surface;", "setDisplay", "holder", "Landroid/view/SurfaceHolder;", "setVolume", "v1", "", "v2", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "setLooping", "isLooping", "setEnableMediaCodec", "isEnable", "setOptions", "setSpeed", "speed", "getTcpSpeed", "setEnableHardCodec", "enableHardCodec", "getTrackInfo", "setTrackInfo", "SelecttrackInfo", "getVideoSize", "", "setVideoScale", "scale", "getCurrAudioIndex", "addAudioTrack", "url", "addSubtitle", "select", "setRenderer", "renderer", "Lorg/videolan/libvlc/RendererItem;", "Landroidx/media3/common/TrackGroup;", "getVolume", "releaseView", "setVideoInfo", TvContractCompat.ProgramColumns.COLUMN_TITLE, "poster", "fid", "boxType", "getVideoTrack", "setAudioDelay", "audioDelay", "getAudioTracks", "", "getCurrentAudioTrack", "getTrackIndices", "getAudioDelay", "registerListener", "unRegisterListener", "getMediaPlayer", "updateVideoView", "vlcVideoLayout", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "activity", "Landroid/app/Activity;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomExoPlayer extends ExoAbstractPlayer {
    private final ArrayList<Tracks.Group> audioTracks;
    private int currSelectAudioIndex;
    private String currSelectId;
    private int height;
    private boolean isBuffering;
    private boolean isLastReportedPlayWhenReady;
    private boolean isPreparing;
    private int lastReportedPlaybackState;
    private final ArrayList<PlayListener> listeners;
    private final DefaultTrackSelector mTrackSelector;
    private OnVideoPinFailed onVideoPinFailed;
    private boolean pendingVideoApplied;
    private String pendingVideoQuality;
    private final ExoPlayer player;
    private long prepareStartedAtRealtimeMs;
    private final HlsQualityPinHolder qualityPinHolder;
    private Tracks tracks;
    private int width;

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public int addAudioTrack(String url) {
        return -1;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void addSubtitle(String url, boolean select) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public long getAudioDelay() {
        return 0L;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public long getTcpSpeed() {
        return 0L;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public TrackGroup getVideoTrack() {
        return null;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void prepareAsync() {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void releaseView() {
    }

    public final void setAacOnly() {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setAudioDelay(long audioDelay) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setDataSource(AssetFileDescriptor fd) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setDataSource(String path, Map<String, String> headers) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setEnableMediaCodec(boolean isEnable) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setLooping(boolean isLooping) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setOptions() {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setRenderer(RendererItem renderer) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setSurface(Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setTrackInfo(int SelecttrackInfo) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setVideoInfo(String title, String poster, String fid, int boxType) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setVideoScale(int scale) {
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void updateVideoView(VLCVideoLayout vlcVideoLayout, Activity activity) {
    }

    public CustomExoPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        HlsQualityPinHolder hlsQualityPinHolder = new HlsQualityPinHolder();
        this.qualityPinHolder = hlsQualityPinHolder;
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context, buildTrackSelectorParameters(context), new HlsPinnedAdaptiveTrackSelection.Factory(hlsQualityPinHolder));
        this.mTrackSelector = defaultTrackSelector;
        this.listeners = new ArrayList<>();
        this.isPreparing = true;
        this.currSelectId = "0";
        this.audioTracks = new ArrayList<>();
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context, new MyRenderersFactory(context)).setTrackSelector(defaultTrackSelector).build();
        Intrinsics.checkNotNullExpressionValue(exoPlayerBuild, "build(...)");
        this.player = exoPlayerBuild;
    }

    private final DefaultTrackSelector.Parameters buildTrackSelectorParameters(Context context) {
        DefaultTrackSelector.Parameters.Builder allowVideoMixedDecoderSupportAdaptiveness = new DefaultTrackSelector.Parameters.Builder(context).setViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true).setAllowVideoMixedMimeTypeAdaptiveness(true).setAllowVideoNonSeamlessAdaptiveness(true).setAllowVideoMixedDecoderSupportAdaptiveness(true);
        Intrinsics.checkNotNullExpressionValue(allowVideoMixedDecoderSupportAdaptiveness, "setAllowVideoMixedDecoderSupportAdaptiveness(...)");
        if (!SettingManager.INSTANCE.getBestAudio()) {
            allowVideoMixedDecoderSupportAdaptiveness.setPreferredAudioMimeTypes("audio/mp4a-latm", "audio/mp4").setMaxAudioChannelCount(2);
        } else {
            allowVideoMixedDecoderSupportAdaptiveness.setPreferredAudioMimeTypes("audio/eac3", MimeTypes.AUDIO_E_AC3_JOC).setMaxAudioChannelCount(Integer.MAX_VALUE);
        }
        DefaultTrackSelector.Parameters parametersBuild = allowVideoMixedDecoderSupportAdaptiveness.build();
        Intrinsics.checkNotNullExpressionValue(parametersBuild, "build(...)");
        return parametersBuild;
    }

    public final DefaultTrackSelector getMTrackSelector() {
        return this.mTrackSelector;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void initPlayer() {
        this.player.addAnalyticsListener(new AnalyticsListener() { // from class: com.movieboxpro.android.exo.CustomExoPlayer.initPlayer.1
            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
                AnalyticsListener.CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onAudioCodecError(this, eventTime, exc);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
                AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
                AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j, j2);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
                AnalyticsListener.CC.$default$onAudioDecoderReleased(this, eventTime, str);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onAudioDisabled(this, eventTime, decoderCounters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onAudioEnabled(this, eventTime, decoderCounters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                AnalyticsListener.CC.$default$onAudioInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onAudioPositionAdvancing(this, eventTime, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onAudioSessionIdChanged(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onAudioSinkError(this, eventTime, exc);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioTrackInitialized(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
                AnalyticsListener.CC.$default$onAudioTrackInitialized(this, eventTime, audioTrackConfig);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioTrackReleased(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
                AnalyticsListener.CC.$default$onAudioTrackReleased(this, eventTime, audioTrackConfig);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
                AnalyticsListener.CC.$default$onAudioUnderrun(this, eventTime, i, j, j2);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
                AnalyticsListener.CC.$default$onAvailableCommandsChanged(this, eventTime, commands);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
                AnalyticsListener.CC.$default$onBandwidthEstimate(this, eventTime, i, j, j2);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
                AnalyticsListener.CC.$default$onCues(this, eventTime, cueGroup);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List list) {
                AnalyticsListener.CC.$default$onCues(this, eventTime, list);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
                AnalyticsListener.CC.$default$onDeviceInfoChanged(this, eventTime, deviceInfo);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i, boolean z) {
                AnalyticsListener.CC.$default$onDeviceVolumeChanged(this, eventTime, i, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onDownstreamFormatChanged(this, eventTime, mediaLoadData);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysLoaded(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysRemoved(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmKeysRestored(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onDrmSessionManagerError(this, eventTime, exc);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onDrmSessionReleased(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
                AnalyticsListener.CC.$default$onDroppedVideoFrames(this, eventTime, i, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onEvents(Player player, AnalyticsListener.Events events) {
                AnalyticsListener.CC.$default$onEvents(this, player, events);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onIsLoadingChanged(this, eventTime, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onIsPlayingChanged(this, eventTime, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onLoadCompleted(this, eventTime, loadEventInfo, mediaLoadData);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onLoadingChanged(this, eventTime, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onMaxSeekToPreviousPositionChanged(this, eventTime, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i) {
                AnalyticsListener.CC.$default$onMediaItemTransition(this, eventTime, mediaItem, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
                AnalyticsListener.CC.$default$onMediaMetadataChanged(this, eventTime, mediaMetadata);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onMetadata(AnalyticsListener.EventTime eventTime, androidx.media3.common.Metadata metadata) {
                AnalyticsListener.CC.$default$onMetadata(this, eventTime, metadata);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
                AnalyticsListener.CC.$default$onPlayWhenReadyChanged(this, eventTime, z, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
                AnalyticsListener.CC.$default$onPlaybackParametersChanged(this, eventTime, playbackParameters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPlaybackStateChanged(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPlaybackSuppressionReasonChanged(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
                AnalyticsListener.CC.$default$onPlayerErrorChanged(this, eventTime, playbackException);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onPlayerReleased(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
                AnalyticsListener.CC.$default$onPlaylistMetadataChanged(this, eventTime, mediaMetadata);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, positionInfo, positionInfo2, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j) {
                AnalyticsListener.CC.$default$onRenderedFirstFrame(this, eventTime, obj, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onRendererReadyChanged(AnalyticsListener.EventTime eventTime, int i, int i2, boolean z) {
                AnalyticsListener.CC.$default$onRendererReadyChanged(this, eventTime, i, i2, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onRepeatModeChanged(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onSeekBackIncrementChanged(this, eventTime, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
                AnalyticsListener.CC.$default$onSeekForwardIncrementChanged(this, eventTime, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
                AnalyticsListener.CC.$default$onSeekStarted(this, eventTime);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onShuffleModeChanged(this, eventTime, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
                AnalyticsListener.CC.$default$onSkipSilenceEnabledChanged(this, eventTime, z);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
                AnalyticsListener.CC.$default$onSurfaceSizeChanged(this, eventTime, i, i2);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
                AnalyticsListener.CC.$default$onTimelineChanged(this, eventTime, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
                AnalyticsListener.CC.$default$onTrackSelectionParametersChanged(this, eventTime, trackSelectionParameters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
                AnalyticsListener.CC.$default$onUpstreamDiscarded(this, eventTime, mediaLoadData);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
                AnalyticsListener.CC.$default$onVideoCodecError(this, eventTime, exc);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
                AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
                AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j, j2);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
                AnalyticsListener.CC.$default$onVideoDecoderReleased(this, eventTime, str);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onVideoDisabled(this, eventTime, decoderCounters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
                AnalyticsListener.CC.$default$onVideoEnabled(this, eventTime, decoderCounters);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j, int i) {
                AnalyticsListener.CC.$default$onVideoFrameProcessingOffset(this, eventTime, j, i);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                AnalyticsListener.CC.$default$onVideoInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
                AnalyticsListener.CC.$default$onVideoSizeChanged(this, eventTime, i, i2, i3, f);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public /* synthetic */ void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
                AnalyticsListener.CC.$default$onVolumeChanged(this, eventTime, f);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException error) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(error, "error");
                Iterator it = CustomExoPlayer.this.listeners.iterator();
                while (it.hasNext()) {
                    ((PlayListener) it.next()).onError(error.getErrorCodeName());
                }
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(tracks, "tracks");
                if (CustomExoPlayer.this.shouldAcceptTracksEvent(eventTime)) {
                    CustomExoPlayer.this.audioTracks.clear();
                    CustomExoPlayer.this.tracks = tracks;
                    ImmutableList<Tracks.Group> groups = tracks.getGroups();
                    Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
                    CustomExoPlayer customExoPlayer = CustomExoPlayer.this;
                    for (Tracks.Group group : groups) {
                        if (group.getType() == 1) {
                            customExoPlayer.audioTracks.add(group);
                        }
                    }
                    ArrayList arrayList = CustomExoPlayer.this.audioTracks;
                    CustomExoPlayer customExoPlayer2 = CustomExoPlayer.this;
                    int i = 0;
                    for (Object obj : arrayList) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        if (((Tracks.Group) obj).isSelected()) {
                            customExoPlayer2.currSelectAudioIndex = i;
                        }
                        i = i2;
                    }
                    CustomExoPlayer.this.applyPendingVideoQuality();
                }
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean playWhenReady, int playbackState) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                if (CustomExoPlayer.this.isLastReportedPlayWhenReady != playWhenReady || CustomExoPlayer.this.lastReportedPlaybackState != playbackState) {
                    CustomExoPlayer.this.player.getBufferedPercentage();
                    if (CustomExoPlayer.this.isBuffering && (playbackState == 3 || playbackState == 4)) {
                        Iterator it = CustomExoPlayer.this.listeners.iterator();
                        while (it.hasNext()) {
                            ((PlayListener) it.next()).onBuffer(100.0f);
                        }
                        CustomExoPlayer.this.isBuffering = false;
                    }
                    if (CustomExoPlayer.this.isPreparing && playbackState == 3) {
                        Iterator it2 = CustomExoPlayer.this.listeners.iterator();
                        while (it2.hasNext()) {
                            ((PlayListener) it2.next()).onStart();
                        }
                        CustomExoPlayer.this.isPreparing = false;
                    }
                    if (playbackState == 2) {
                        Iterator it3 = CustomExoPlayer.this.listeners.iterator();
                        while (it3.hasNext()) {
                            ((PlayListener) it3.next()).onStart();
                        }
                        CustomExoPlayer.this.isBuffering = true;
                    } else if (playbackState == 4) {
                        Iterator it4 = CustomExoPlayer.this.listeners.iterator();
                        while (it4.hasNext()) {
                            ((PlayListener) it4.next()).onComplete();
                        }
                    }
                }
                CustomExoPlayer.this.isLastReportedPlayWhenReady = playWhenReady;
                CustomExoPlayer.this.lastReportedPlaybackState = playbackState;
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(videoSize, "videoSize");
                CustomExoPlayer.this.height = videoSize.height;
                CustomExoPlayer.this.width = videoSize.width;
                ArrayList arrayList = CustomExoPlayer.this.listeners;
                CustomExoPlayer customExoPlayer = CustomExoPlayer.this;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((PlayListener) it.next()).onVideoSizeChanged(customExoPlayer.width, customExoPlayer.height);
                }
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int retryCount) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(loadEventInfo, "loadEventInfo");
                Intrinsics.checkNotNullParameter(mediaLoadData, "mediaLoadData");
                CustomExoPlayer.this.logHlsLoad("START", loadEventInfo, mediaLoadData, retryCount);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(loadEventInfo, "loadEventInfo");
                Intrinsics.checkNotNullParameter(mediaLoadData, "mediaLoadData");
                CustomExoPlayer.this.logHlsLoad("CANCEL", loadEventInfo, mediaLoadData, 0);
            }

            @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
            public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException error, boolean wasCanceled) {
                Intrinsics.checkNotNullParameter(eventTime, "eventTime");
                Intrinsics.checkNotNullParameter(loadEventInfo, "loadEventInfo");
                Intrinsics.checkNotNullParameter(mediaLoadData, "mediaLoadData");
                Intrinsics.checkNotNullParameter(error, "error");
                CustomExoPlayer.this.logHlsLoad("ERROR " + error.getClass().getSimpleName(), loadEventInfo, mediaLoadData, 0);
            }
        });
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setOnVideoPinFailed(OnVideoPinFailed listener) {
        this.onVideoPinFailed = listener;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setPendingVideoQuality(String quality) {
        this.pendingVideoQuality = quality;
        this.pendingVideoApplied = false;
        applyQualityPin(quality);
        if (HlsVideoTrackSwitcher.isAutoLabel(quality) || quality == null) {
            clearVideoTrackOverrides();
        }
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void clearVideoTrackOverrides() {
        this.qualityPinHolder.setQualityLabel(null);
        ExoPlayer exoPlayer = this.player;
        exoPlayer.setTrackSelectionParameters(exoPlayer.getTrackSelectionParameters().buildUpon().clearOverridesOfType(2).setMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE).setMinVideoSize(0, 0).build());
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public boolean pinVideoQuality(String quality) {
        if (HlsVideoTrackSwitcher.isAutoLabel(quality)) {
            clearVideoTrackOverrides();
            this.pendingVideoApplied = true;
            this.pendingVideoQuality = null;
            return true;
        }
        Tracks tracks = this.tracks;
        if (tracks == null) {
            return stashPending(quality);
        }
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
        ArrayList arrayList = new ArrayList();
        for (Tracks.Group group : groups) {
            if (group.getType() == 2) {
                arrayList.add(group);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return stashPending(quality);
        }
        ArrayList<Tracks.Group> arrayList3 = arrayList2;
        int i = 10;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (Tracks.Group group2 : arrayList3) {
            boolean zIsSelected = group2.isSelected();
            IntRange intRangeUntil = RangesKt.until(0, group2.length);
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, i));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                Format trackFormat = group2.getTrackFormat(iNextInt);
                Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
                arrayList5.add(new HlsVideoTrackSwitcher.PinTrack(new HlsVideoTrackSwitcher.VideoTrackFormat(trackFormat.height, trackFormat.id, trackFormat.label, trackFormat.width, 0, 16, null), group2.isTrackSupported(iNextInt)));
            }
            arrayList4.add(new HlsVideoTrackSwitcher.PinTrackGroup(zIsSelected, arrayList5));
            i = 10;
        }
        List<HlsVideoTrackSwitcher.VideoTrackFormat> list = HlsVideoTrackSwitcher.formatsAvailableToPin(arrayList4);
        if (list == null) {
            return stashPending(quality);
        }
        if (HlsVideoTrackSwitcher.matchVideoTrack(list, quality) == null) {
            return false;
        }
        applyQualityPin(quality);
        this.pendingVideoApplied = true;
        this.pendingVideoQuality = null;
        return true;
    }

    private final boolean stashPending(String quality) {
        this.pendingVideoQuality = quality;
        this.pendingVideoApplied = false;
        applyQualityPin(quality);
        return true;
    }

    private final void applyQualityPin(String quality) {
        String str;
        if (HlsVideoTrackSwitcher.isAutoLabel(quality) || (str = quality) == null || StringsKt.isBlank(str)) {
            quality = null;
        }
        if (!Intrinsics.areEqual(this.qualityPinHolder.getQualityLabel(), quality)) {
            HlsDebugLog.INSTANCE.i("pin " + (quality == null ? "AUTO" : quality));
        }
        this.qualityPinHolder.setQualityLabel(quality);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:30:0x0061  */
    public final void logHlsLoad(String action, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int retryCount) {
        String str;
        String str2;
        String str3;
        String str4;
        int i = mediaLoadData.dataType;
        if (i == 1) {
            str = "media";
        } else if (i == 2) {
            str = "init";
        } else if (i != 4) {
            return;
        } else {
            str = "manifest";
        }
        Format format = mediaLoadData.trackFormat;
        String str5 = "";
        if (format == null || format.width <= 0 || format.height <= 0) {
            str2 = "";
        } else {
            str2 = " " + format.width + "x" + format.height;
        }
        if (format == null || (str4 = format.id) == null) {
            str3 = "";
        } else {
            if (StringsKt.isBlank(str4)) {
                str4 = null;
            }
            if (str4 != null) {
                str3 = " id=" + str4;
                if (str3 == null) {
                    str3 = "";
                }
            } else {
                str3 = "";
            }
        }
        if (retryCount > 0) {
            str5 = " retry=" + retryCount;
        }
        HlsDebugLog.INSTANCE.i(action + " " + str + " " + HlsDebugLog.INSTANCE.uri(loadEventInfo.uri) + str2 + str3 + str5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyPendingVideoQuality() {
        Tracks tracks;
        OnVideoPinFailed onVideoPinFailed;
        String str = this.pendingVideoQuality;
        if (str == null || this.pendingVideoApplied || (tracks = this.tracks) == null) {
            return;
        }
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
        ImmutableList<Tracks.Group> immutableList = groups;
        if ((immutableList instanceof Collection) && immutableList.isEmpty()) {
            return;
        }
        Iterator<Tracks.Group> it = immutableList.iterator();
        while (it.hasNext()) {
            if (it.next().getType() == 2) {
                if (pinVideoQuality(str)) {
                    return;
                }
                this.pendingVideoApplied = true;
                String str2 = this.pendingVideoQuality;
                this.pendingVideoQuality = null;
                String str3 = str2;
                if (str3 == null || StringsKt.isBlank(str3) || (onVideoPinFailed = this.onVideoPinFailed) == null) {
                    return;
                }
                onVideoPinFailed.onVideoPinFailed(str2);
                return;
            }
        }
    }

    private final void beginNewPrepareGeneration() {
        this.prepareStartedAtRealtimeMs = SystemClock.elapsedRealtime();
        this.tracks = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldAcceptTracksEvent(AnalyticsListener.EventTime eventTime) {
        MediaSource.MediaPeriodId mediaPeriodId = eventTime.mediaPeriodId;
        Boolean boolValueOf = null;
        Object obj = mediaPeriodId != null ? mediaPeriodId.periodUid : null;
        if (obj != null) {
            Timeline currentTimeline = this.player.getCurrentTimeline();
            Intrinsics.checkNotNullExpressionValue(currentTimeline, "getCurrentTimeline(...)");
            boolValueOf = Boolean.valueOf((currentTimeline.isEmpty() || currentTimeline.getIndexOfPeriod(obj) == -1) ? false : true);
        }
        return HlsVideoTrackSwitcher.shouldAcceptTracksEvent(eventTime.realtimeMs, this.prepareStartedAtRealtimeMs, boolValueOf);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setAudioTrack(TrackSelectionOverride trackGroup) {
        if (trackGroup != null) {
            ExoPlayer exoPlayer = this.player;
            exoPlayer.setTrackSelectionParameters(exoPlayer.getTrackSelectionParameters().buildUpon().setOverrideForType(trackGroup).build());
        }
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setDataSource(String path, long startTime) {
        beginNewPrepareGeneration();
        HlsDebugLog.INSTANCE.i("setDataSource " + HlsDebugLog.INSTANCE.uri(path));
        DownloadFile downloadFileFindDownloadFileByUrl = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByUrl(path);
        if (downloadFileFindDownloadFileByUrl != null) {
            DownloadTracker downloadTracker = DownloadUtil.getDownloadTracker(App.getContext(), downloadFileFindDownloadFileByUrl.getPath());
            Uri uri = Uri.parse(path == null ? "" : path);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
            DownloadRequest downloadRequest = downloadTracker.getDownloadRequest(uri);
            if (downloadRequest != null) {
                this.player.setMediaSource(new HlsMediaSource.Factory(DownloadUtil.getDataSourceFactory(App.getContext(), downloadFileFindDownloadFileByUrl.getPath())).setAllowChunklessPreparation(true).createMediaSource(downloadRequest.toMediaItem()));
            } else {
                if (path == null) {
                    path = "";
                }
                MediaItem mediaItemFromUri = MediaItem.fromUri(path);
                Intrinsics.checkNotNullExpressionValue(mediaItemFromUri, "fromUri(...)");
                this.player.setMediaItem(mediaItemFromUri);
            }
        } else {
            if (path == null) {
                path = "";
            }
            MediaItem mediaItemFromUri2 = MediaItem.fromUri(path);
            Intrinsics.checkNotNullExpressionValue(mediaItemFromUri2, "fromUri(...)");
            this.player.setMediaItem(mediaItemFromUri2);
        }
        this.player.setPlayWhenReady(true);
        this.player.prepare();
        this.player.play();
        this.player.seekTo(startTime);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setDataSource(Uri uri) {
        if (uri != null) {
            beginNewPrepareGeneration();
            HlsDebugLog.INSTANCE.i("setDataSource " + HlsDebugLog.INSTANCE.uri(uri));
            MediaItem mediaItemFromUri = MediaItem.fromUri(uri);
            Intrinsics.checkNotNullExpressionValue(mediaItemFromUri, "fromUri(...)");
            this.player.setMediaItem(mediaItemFromUri);
            this.player.setPlayWhenReady(true);
            this.player.prepare();
            this.player.play();
        }
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void start() {
        this.player.play();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void pause() {
        this.player.pause();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void stop() {
        this.player.stop();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void reset() {
        beginNewPrepareGeneration();
        this.player.stop();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void mute(boolean mute) {
        this.player.setDeviceMuted(mute);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void seekTo(long time) {
        this.player.seekTo(time);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void release() {
        this.player.release();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public long getDuration() {
        return this.player.getDuration();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setDisplay(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        this.player.setVideoSurfaceHolder(holder);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setVolume(float v1, float v2) {
        this.player.setVolume((v1 + v2) / 2);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setVolume(int volume) {
        this.player.setVolume(volume);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setSpeed(float speed) {
        this.player.setPlaybackSpeed(speed);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setEnableHardCodec(boolean enableHardCodec) {
        SettingManager.INSTANCE.setHwDecoder(enableHardCodec);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public Tracks getTrackInfo() {
        Tracks currentTracks = this.player.getCurrentTracks();
        Intrinsics.checkNotNullExpressionValue(currentTracks, "getCurrentTracks(...)");
        return currentTracks;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public int[] getVideoSize() {
        return new int[]{this.width, this.height};
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    /* JADX INFO: renamed from: getCurrAudioIndex, reason: from getter */
    public int getCurrSelectAudioIndex() {
        return this.currSelectAudioIndex;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void setAudioTrack(TrackGroup trackGroup) {
        if (trackGroup != null) {
            TrackSelectionOverride trackSelectionOverride = new TrackSelectionOverride(trackGroup, 0);
            ExoPlayer exoPlayer = this.player;
            exoPlayer.setTrackSelectionParameters(exoPlayer.getTrackSelectionParameters().buildUpon().setOverrideForType(trackSelectionOverride).build());
        }
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public int getVolume() {
        return (int) this.player.getVolume();
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public List<Tracks.Group> getAudioTracks() {
        return this.audioTracks;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public Tracks.Group getCurrentAudioTrack() {
        Tracks tracks = this.tracks;
        if (tracks == null) {
            return null;
        }
        Intrinsics.checkNotNull(tracks);
        UnmodifiableIterator<Tracks.Group> it = tracks.getGroups().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            if (next.getType() == 1 && next.isSelected()) {
                return next;
            }
        }
        return null;
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public ArrayList<Integer> getTrackIndices() {
        Tracks.Group currentAudioTrack = getCurrentAudioTrack();
        TrackSelectionParameters trackSelectionParameters = this.player.getTrackSelectionParameters();
        Intrinsics.checkNotNullExpressionValue(trackSelectionParameters, "getTrackSelectionParameters(...)");
        TrackSelectionOverride trackSelectionOverride = trackSelectionParameters.overrides.get(currentAudioTrack != null ? currentAudioTrack.getMediaTrackGroup() : null);
        ImmutableList<Integer> immutableList = trackSelectionOverride != null ? trackSelectionOverride.trackIndices : null;
        return new ArrayList<>(immutableList != null ? immutableList : CollectionsKt.emptyList());
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void registerListener(PlayListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    public void unRegisterListener(PlayListener listener) {
        TypeIntrinsics.asMutableCollection(this.listeners).remove(listener);
    }

    @Override // com.movieboxpro.android.exo.ExoAbstractPlayer
    /* JADX INFO: renamed from: getMediaPlayer, reason: from getter */
    public ExoPlayer getPlayer() {
        return this.player;
    }
}
