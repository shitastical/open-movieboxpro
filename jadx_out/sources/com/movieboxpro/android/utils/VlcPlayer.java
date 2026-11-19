package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.listener.PlayListener;
import com.movieboxpro.android.player.AbstractPlayer;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.FileDownloadService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.SupervisorKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: compiled from: VlcPlayer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010*\u001a\u00020\fJ\b\u0010+\u001a\u0004\u0018\u00010\u0010J\b\u0010,\u001a\u0004\u0018\u00010\u0010J\b\u0010-\u001a\u0004\u0018\u00010\u0010J\u0006\u0010.\u001a\u00020\u0014J\u0010\u0010/\u001a\u00020'2\u0006\u00100\u001a\u000201H\u0002J\u0012\u00102\u001a\u0002032\b\u0010/\u001a\u0004\u0018\u00010'H\u0016J\u0012\u00104\u001a\u0002032\b\b\u0002\u00100\u001a\u000201H\u0002J\u0006\u00105\u001a\u00020\u001eJ\u0006\u00106\u001a\u000203J\b\u00107\u001a\u00020\u001eH\u0002J\b\u00108\u001a\u000201H\u0016J\u0010\u00109\u001a\u0002032\u0006\u0010:\u001a\u00020\u0014H\u0016J\u0010\u0010;\u001a\u0002032\u0006\u0010<\u001a\u000201H\u0016J\u0012\u0010=\u001a\u0002032\b\u0010>\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010?\u001a\u000203H\u0016J\b\u0010@\u001a\u000201H\u0016J\u0010\u0010A\u001a\u0002032\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u0002032\u0006\u0010E\u001a\u00020FH\u0016J\u0018\u0010G\u001a\u0002032\u0006\u0010H\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JH\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u000203H\u0016J\u0018\u0010P\u001a\u0002032\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020RH\u0016J\u0010\u0010P\u001a\u0002032\u0006\u0010T\u001a\u00020\u0014H\u0016J\u0013\u0010U\u001a\b\u0012\u0004\u0012\u00020W0VH\u0016¢\u0006\u0002\u0010XJ(\u0010Y\u001a\u0002032\b\u0010Z\u001a\u0004\u0018\u00010\u00102\u0014\u0010[\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\\H\u0016J\"\u0010Y\u001a\u0002032\b\u0010Z\u001a\u0004\u0018\u00010\u00102\u0006\u0010]\u001a\u0002012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010^\u001a\u0002032\u0006\u0010_\u001a\u00020`H\u0002J\u0012\u0010Y\u001a\u0002032\b\u0010a\u001a\u0004\u0018\u00010bH\u0016J\b\u0010c\u001a\u000201H\u0016J\b\u0010d\u001a\u000203H\u0016J\b\u0010e\u001a\u000201H\u0016J\u001a\u0010f\u001a\u0002032\b\u0010g\u001a\u0004\u0018\u00010\u00102\u0006\u0010h\u001a\u00020\u001eH\u0016J\u0012\u0010i\u001a\u00020\u00142\b\u0010g\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010j\u001a\u00020\u001eH\u0016J\u0010\u0010k\u001a\u0002032\u0006\u0010l\u001a\u00020\u0014H\u0016J\u0012\u0010m\u001a\u0002032\b\u0010>\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010n\u001a\u000203H\u0016J\b\u0010o\u001a\u000203H\u0016J\u0010\u0010p\u001a\u0002032\u0006\u0010q\u001a\u00020\u001eH\u0016J\u0010\u0010r\u001a\u0002032\u0006\u0010s\u001a\u00020\u001eH\u0016J\u0010\u0010t\u001a\u0002032\u0006\u0010u\u001a\u00020\u001eH\u0016J\u0012\u0010v\u001a\u0002032\b\u0010w\u001a\u0004\u0018\u00010xH\u0016J\b\u0010y\u001a\u000203H\u0016J\b\u0010z\u001a\u00020\u0014H\u0016J\b\u0010{\u001a\u00020\u0014H\u0016J\b\u0010|\u001a\u000203H\u0016J\u0010\u0010}\u001a\u0002032\u0006\u0010~\u001a\u00020RH\u0016J\b\u0010\u007f\u001a\u000203H\u0016J\n\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0016J1\u0010\u0082\u0001\u001a\u0002032\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0085\u0001\u001a\u000203H\u0016J\u0012\u0010\u0086\u0001\u001a\u0002032\u0007\u0010\u0087\u0001\u001a\u000201H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0017`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0088\u0001"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlayer;", "Lcom/movieboxpro/android/player/AbstractPlayer;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "mediaPlayer", "Lorg/videolan/libvlc/MediaPlayer;", "displayManager", "Lorg/videolan/libvlc/util/DisplayManager;", "videoTitle", "", "videoPoster", "fid", "boxType", "", "listeners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/listener/PlayListener;", "Lkotlin/collections/ArrayList;", "currVLCVideoLayout", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "activity", "Landroid/app/Activity;", "useSurfaceView", "", "mediaLock", "", "loadGeneration", "Ljava/util/concurrent/atomic/AtomicLong;", "released", "playRequested", "listenerOverridden", "overriddenListener", "Lorg/videolan/libvlc/MediaPlayer$EventListener;", "recovery", "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;", "getMediaPlayer", "getVideoTitle", "getVideoPoster", "getFid", "getBoxType", "eventListener", "generation", "", "setPlayListener", "", "bindEventListener", "isRecoveryExhausted", "retryPlayback", "checkAvailable", "getDuration", "setTrackInfo", FirebaseAnalytics.Param.INDEX, "seekTo", "time", "unRegisterListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "initPlayer", "getCurrentPosition", "setDisplay", "holder", "Landroid/view/SurfaceHolder;", "setTextureView", "textureView", "Landroid/view/TextureView;", "attachView", "vlcVideoLayout", "getAudioTracks", "", "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;", "setSurface", "surface", "Landroid/view/Surface;", "start", "setVolume", "v1", "", "v2", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "getTrackInfo", "", "Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;", "()[Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;", "setDataSource", FileDownloadService.PARAMS_KEY_PATH, "headers", "", "startTime", "openSource", FirebaseAnalytics.Param.SOURCE, "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;", IjkMediaPlayer.OnNativeInvokeListener.ARG_FD, "Landroid/content/res/AssetFileDescriptor;", "getAudioDelay", "reset", "getTcpSpeed", "addSubtitle", "url", "select", "addAudioTrack", "isPlaying", "setVideoScale", "scale", "registerListener", DownloadService.PAUSE, "prepareAsync", "setEnableHardCodec", "enableHardCodec", "setLooping", "isLooping", "setEnableMediaCodec", "isEnable", "setRenderer", "renderer", "Lorg/videolan/libvlc/RendererItem;", "stop", "getCurrAudioIndex", "getBufferedPercentage", "setOptions", "setSpeed", "speed", "releaseView", "getVideoTrack", "Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;", "setVideoInfo", TvContractCompat.ProgramColumns.COLUMN_TITLE, "poster", "release", "setAudioDelay", "audioDelay", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class VlcPlayer extends AbstractPlayer implements CoroutineScope {
    private Activity activity;
    private int boxType;
    private final CoroutineContext coroutineContext;
    private VLCVideoLayout currVLCVideoLayout;
    private DisplayManager displayManager;
    private String fid;
    private boolean listenerOverridden;
    private final ArrayList<PlayListener> listeners;
    private final AtomicLong loadGeneration;
    private final Object mediaLock;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.EventListener overriddenListener;
    private volatile boolean playRequested;
    private final VlcPlaybackRecovery recovery;
    private volatile boolean released;
    private boolean useSurfaceView;
    private String videoPoster;
    private String videoTitle;

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void addSubtitle(String url, boolean select) {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public int getBufferedPercentage() {
        return 0;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public long getTcpSpeed() {
        return 0L;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public IjkTrackInfo[] getTrackInfo() {
        return new IjkTrackInfo[0];
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void initPlayer() {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void prepareAsync() {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setDataSource(AssetFileDescriptor fd) {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setDataSource(String path, Map<String, String> headers) {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setDisplay(SurfaceHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setEnableMediaCodec(boolean isEnable) {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setLooping(boolean isLooping) {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setOptions() {
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setSurface(Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setVolume(float v1, float v2) {
    }

    public VlcPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
        this.mediaPlayer = new MediaPlayer(VLCInstance.INSTANCE.getInstance(context));
        this.videoTitle = "";
        this.videoPoster = "";
        this.fid = "";
        this.boxType = 1;
        this.listeners = new ArrayList<>();
        this.mediaLock = new Object();
        this.loadGeneration = new AtomicLong();
        this.recovery = new VlcPlaybackRecovery(new Function2() { // from class: com.movieboxpro.android.utils.VlcPlayer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return VlcPlayer.recovery$lambda$1(this.f$0, ((Long) obj).longValue(), (Function0) obj2);
            }
        }, new Function1() { // from class: com.movieboxpro.android.utils.VlcPlayer$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VlcPlayer.recovery$lambda$2(this.f$0, (VlcPlaybackRecovery.Source) obj);
            }
        }, new Function0() { // from class: com.movieboxpro.android.utils.VlcPlayer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VlcPlayer.recovery$lambda$4(this.f$0);
            }
        });
        bindEventListener$default(this, 0L, 1, null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VlcPlaybackRecovery.Cancellation recovery$lambda$1(VlcPlayer vlcPlayer, long j, Function0 action) {
        Intrinsics.checkNotNullParameter(action, "action");
        final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(vlcPlayer, null, null, new VlcPlayer$recovery$1$job$1(j, action, null), 3, null);
        return new VlcPlaybackRecovery.Cancellation() { // from class: com.movieboxpro.android.utils.VlcPlayer$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.utils.VlcPlaybackRecovery.Cancellation
            public final void cancel() {
                Job.DefaultImpls.cancel$default(jobLaunch$default, (CancellationException) null, 1, (Object) null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit recovery$lambda$2(VlcPlayer vlcPlayer, VlcPlaybackRecovery.Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        vlcPlayer.openSource(source);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit recovery$lambda$4(VlcPlayer vlcPlayer) {
        vlcPlayer.playRequested = false;
        vlcPlayer.loadGeneration.incrementAndGet();
        for (PlayListener playListener : CollectionsKt.toList(vlcPlayer.listeners)) {
            if (playListener != null) {
                playListener.onError();
            }
        }
        return Unit.INSTANCE;
    }

    public final MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public final String getVideoTitle() {
        return this.videoTitle;
    }

    public final String getVideoPoster() {
        return this.videoPoster;
    }

    public final String getFid() {
        return this.fid;
    }

    public final int getBoxType() {
        return this.boxType;
    }

    private final MediaPlayer.EventListener eventListener(final long generation) {
        return new MediaPlayer.EventListener() { // from class: com.movieboxpro.android.utils.VlcPlayer$$ExternalSyntheticLambda1
            @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
            public final void onEvent(AbstractVLCEvent abstractVLCEvent) {
                VlcPlayer.eventListener$lambda$12(this.f$0, generation, (MediaPlayer.Event) abstractVLCEvent);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void eventListener$lambda$12(VlcPlayer vlcPlayer, long j, MediaPlayer.Event event) {
        if (vlcPlayer.released || j != vlcPlayer.loadGeneration.get()) {
            return;
        }
        switch (event.type) {
            case 258:
                for (PlayListener playListener : vlcPlayer.listeners) {
                    if (playListener != null) {
                        playListener.onStart();
                    }
                }
                break;
            case 259:
                for (PlayListener playListener2 : vlcPlayer.listeners) {
                    if (event.getBuffering() != 16.666668f && playListener2 != null) {
                        playListener2.onBuffer(event.getBuffering());
                    }
                }
                break;
            case 260:
                for (PlayListener playListener3 : vlcPlayer.listeners) {
                    if (playListener3 != null) {
                        playListener3.onPlay();
                    }
                }
                break;
            case 261:
                for (PlayListener playListener4 : vlcPlayer.listeners) {
                    if (playListener4 != null) {
                        playListener4.onPause();
                    }
                }
                break;
            case MediaPlayer.Event.EndReached /* 265 */:
                long time = vlcPlayer.mediaPlayer.getTime();
                if (((long) DefaultLoadControl.DEFAULT_LOW_WATERMARK_MS) + time < vlcPlayer.mediaPlayer.getLength()) {
                    vlcPlayer.recovery.onPrematureEnd(time);
                } else {
                    vlcPlayer.recovery.pause();
                    for (PlayListener playListener5 : vlcPlayer.listeners) {
                        if (playListener5 != null) {
                            playListener5.onComplete();
                        }
                    }
                }
                break;
            case MediaPlayer.Event.EncounteredError /* 266 */:
                vlcPlayer.recovery.pause();
                for (PlayListener playListener6 : vlcPlayer.listeners) {
                    if (playListener6 != null) {
                        playListener6.onError();
                    }
                }
                break;
            case MediaPlayer.Event.TimeChanged /* 267 */:
                vlcPlayer.recovery.onProgress(event.getTimeChanged());
                break;
            case MediaPlayer.Event.PositionChanged /* 268 */:
                for (PlayListener playListener7 : vlcPlayer.listeners) {
                    if (playListener7 != null) {
                        playListener7.onPositionChanged();
                    }
                }
                break;
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setPlayListener(MediaPlayer.EventListener eventListener) {
        this.listenerOverridden = true;
        this.overriddenListener = eventListener;
        this.mediaPlayer.setEventListener(eventListener);
    }

    static /* synthetic */ void bindEventListener$default(VlcPlayer vlcPlayer, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bindEventListener");
        }
        if ((i & 1) != 0) {
            j = vlcPlayer.loadGeneration.get();
        }
        vlcPlayer.bindEventListener(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindEventListener(long generation) {
        this.mediaPlayer.setEventListener(this.listenerOverridden ? this.overriddenListener : eventListener(generation));
    }

    public final boolean isRecoveryExhausted() {
        return this.recovery.getIsExhausted();
    }

    public final void retryPlayback() {
        VlcPlaybackRecovery.Source sourceSourceForRetry = this.recovery.sourceForRetry();
        if (sourceSourceForRetry != null) {
            setDataSource(sourceSourceForRetry.getPath(), sourceSourceForRetry.getPositionMs(), sourceSourceForRetry.getUseSurfaceView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkAvailable() {
        return !this.mediaPlayer.isReleased() && this.mediaPlayer.hasMedia();
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public long getDuration() {
        if (this.mediaPlayer.hasMedia()) {
            return this.mediaPlayer.getLength();
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setTrackInfo(int index) {
        if (checkAvailable()) {
            this.mediaPlayer.setAudioTrack(index);
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void seekTo(long time) {
        if (checkAvailable()) {
            this.mediaPlayer.setPosition(time / getDuration());
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void unRegisterListener(PlayListener listener) {
        this.listeners.remove(listener);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public long getCurrentPosition() {
        if (checkAvailable()) {
            return this.mediaPlayer.getTime();
        }
        return 0L;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setTextureView(TextureView textureView) {
        Intrinsics.checkNotNullParameter(textureView, "textureView");
        this.mediaPlayer.getVLCVout().detachViews();
        this.mediaPlayer.getVLCVout().setVideoView(textureView);
        this.mediaPlayer.getVLCVout().attachViews();
        this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
        if (checkAvailable() && this.playRequested && !this.recovery.getIsExhausted()) {
            this.mediaPlayer.play();
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void attachView(VLCVideoLayout vlcVideoLayout, Activity activity) {
        Intrinsics.checkNotNullParameter(vlcVideoLayout, "vlcVideoLayout");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.currVLCVideoLayout = vlcVideoLayout;
        if (this.mediaPlayer.getVLCVout().areViewsAttached()) {
            this.mediaPlayer.detachViews();
        }
        if (this.displayManager == null) {
            this.displayManager = new DisplayManager(activity, null, false, false, false);
        }
        this.mediaPlayer.attachViews(vlcVideoLayout, this.displayManager, true, false);
        this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
        if (checkAvailable() && this.playRequested && !this.recovery.getIsExhausted()) {
            this.mediaPlayer.play();
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public List<MediaPlayer.TrackDescription> getAudioTracks() {
        List<MediaPlayer.TrackDescription> list;
        if (checkAvailable()) {
            MediaPlayer.TrackDescription[] audioTracks = this.mediaPlayer.getAudioTracks();
            return (audioTracks == null || (list = ArraysKt.toList(audioTracks)) == null) ? new ArrayList() : list;
        }
        return new ArrayList();
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void start() {
        if (this.released) {
            return;
        }
        this.playRequested = true;
        if (this.recovery.resume()) {
            synchronized (this.mediaLock) {
                if (this.released) {
                    return;
                }
                if (checkAvailable()) {
                    boolean z = false;
                    if (!this.mediaPlayer.getVLCVout().areViewsAttached() && this.activity != null && this.currVLCVideoLayout != null) {
                        if (this.displayManager == null) {
                            Activity activity = this.activity;
                            Intrinsics.checkNotNull(activity);
                            this.displayManager = new DisplayManager(activity, null, false, false, false);
                        }
                        MediaPlayer mediaPlayer = this.mediaPlayer;
                        VLCVideoLayout vLCVideoLayout = this.currVLCVideoLayout;
                        Intrinsics.checkNotNull(vLCVideoLayout);
                        mediaPlayer.attachViews(vLCVideoLayout, this.displayManager, true, false);
                        this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.values()[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()]);
                        z = true;
                    }
                    bindEventListener$default(this, 0L, 1, null);
                    this.mediaPlayer.play();
                    if (z) {
                        this.mediaPlayer.setTime(getCurrentPosition());
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setVolume(int volume) {
        this.mediaPlayer.setVolume(volume);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setDataSource(String path, long startTime, boolean useSurfaceView) {
        String str;
        if (this.released || (str = path) == null || StringsKt.isBlank(str)) {
            return;
        }
        VlcPlaybackRecovery.Source source = new VlcPlaybackRecovery.Source(path, RangesKt.coerceAtLeast(startTime, 0L), useSurfaceView);
        this.recovery.startSource(source);
        this.playRequested = true;
        openSource(source);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.utils.VlcPlayer$openSource$1, reason: invalid class name */
    /* JADX INFO: compiled from: VlcPlayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.utils.VlcPlayer$openSource$1", f = "VlcPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $generation;
        final /* synthetic */ VlcPlaybackRecovery.Source $source;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, VlcPlaybackRecovery.Source source, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$generation = j;
            this.$source = source;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VlcPlayer.this.new AnonymousClass1(this.$generation, this.$source, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Media media;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = VlcPlayer.this.mediaLock;
                VlcPlayer vlcPlayer = VlcPlayer.this;
                long j = this.$generation;
                VlcPlaybackRecovery.Source source = this.$source;
                synchronized (obj2) {
                    if (!vlcPlayer.released && j == vlcPlayer.loadGeneration.get()) {
                        if (vlcPlayer.mediaPlayer.hasMedia()) {
                            vlcPlayer.mediaPlayer.stop();
                        }
                        vlcPlayer.mediaPlayer.setAudioDigitalOutputEnabled(true);
                        vlcPlayer.useSurfaceView = source.getUseSurfaceView();
                        if (StringsKt.startsWith$default(source.getPath(), IDataSource.SCHEME_HTTP_TAG, false, 2, (Object) null)) {
                            media = new Media(vlcPlayer.mediaPlayer.getLibVLC(), Uri.parse(source.getPath()));
                        } else {
                            media = new Media(vlcPlayer.mediaPlayer.getLibVLC(), source.getPath());
                        }
                        try {
                            if (!vlcPlayer.released && j == vlcPlayer.loadGeneration.get()) {
                                media.addOption(":start-time=" + (source.getPositionMs() / 1000));
                                media.setHWDecoderEnabled(SettingManager.INSTANCE.isHwDecoder(), false);
                                IMedia media2 = vlcPlayer.mediaPlayer.getMedia();
                                if (media2 != null) {
                                    media2.release();
                                }
                                vlcPlayer.mediaPlayer.setMedia(media);
                                vlcPlayer.bindEventListener(j);
                                if (vlcPlayer.playRequested && j == vlcPlayer.loadGeneration.get() && vlcPlayer.checkAvailable()) {
                                    vlcPlayer.mediaPlayer.play();
                                }
                                media.release();
                                Unit unit = Unit.INSTANCE;
                                return Unit.INSTANCE;
                            }
                            Unit unit2 = Unit.INSTANCE;
                            media.release();
                            return unit2;
                        } catch (Throwable th) {
                            media.release();
                            throw th;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void openSource(VlcPlaybackRecovery.Source source) {
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new AnonymousClass1(this.loadGeneration.incrementAndGet(), source, null), 2, null);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public long getAudioDelay() {
        return this.mediaPlayer.getAudioDelay() / ((long) 1000);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void reset() {
        this.playRequested = false;
        this.recovery.stop();
        this.loadGeneration.incrementAndGet();
        synchronized (this.mediaLock) {
            if (checkAvailable()) {
                this.mediaPlayer.stop();
                IMedia media = this.mediaPlayer.getMedia();
                if (media != null) {
                    media.release();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public int addAudioTrack(String url) {
        try {
            this.mediaPlayer.addSlave(1, Uri.parse(new URL(url).toURI().toString()), true);
        } catch (Exception e) {
            ToastUtils.showShort("Add audio track failed:" + e.getMessage(), new Object[0]);
        }
        return 1;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public boolean isPlaying() {
        if (checkAvailable()) {
            return this.mediaPlayer.isPlaying();
        }
        return false;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setVideoScale(int scale) {
        if (scale == 0) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_BEST_FIT);
            return;
        }
        if (scale == 1) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_FIT_SCREEN);
        } else if (scale == 3) {
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_FILL);
        } else {
            if (scale != 4) {
                return;
            }
            this.mediaPlayer.setVideoScale(MediaPlayer.ScaleType.SURFACE_16_9);
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void registerListener(PlayListener listener) {
        this.listeners.add(listener);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void pause() {
        this.playRequested = false;
        this.recovery.pause();
        synchronized (this.mediaLock) {
            if (checkAvailable()) {
                this.mediaPlayer.pause();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setEnableHardCodec(boolean enableHardCodec) {
        SettingManager.INSTANCE.setHwDecoder(enableHardCodec);
        if (this.mediaPlayer.hasMedia()) {
            if (enableHardCodec) {
                IMedia media = this.mediaPlayer.getMedia();
                if (media != null) {
                    media.setDefaultMediaPlayerOptions();
                    return;
                }
                return;
            }
            IMedia media2 = this.mediaPlayer.getMedia();
            if (media2 != null) {
                media2.setHWDecoderEnabled(false, false);
            }
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setRenderer(RendererItem renderer) {
        this.mediaPlayer.setRenderer(renderer);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void stop() {
        this.playRequested = false;
        this.recovery.stop();
        this.loadGeneration.incrementAndGet();
        synchronized (this.mediaLock) {
            if (checkAvailable()) {
                this.mediaPlayer.stop();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public int getCurrAudioIndex() {
        return this.mediaPlayer.getAudioTrack();
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setSpeed(float speed) {
        this.mediaPlayer.setRate(speed);
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void releaseView() {
        if (this.mediaPlayer.isReleased() || !this.mediaPlayer.getVLCVout().areViewsAttached()) {
            return;
        }
        this.mediaPlayer.getVLCVout().detachViews();
        this.mediaPlayer.detachViews();
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public IMedia.VideoTrack getVideoTrack() {
        IMedia.VideoTrack currentVideoTrack = this.mediaPlayer.getCurrentVideoTrack();
        Intrinsics.checkNotNullExpressionValue(currentVideoTrack, "getCurrentVideoTrack(...)");
        return currentVideoTrack;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setVideoInfo(String title, String poster, String fid, int boxType) {
        this.videoTitle = title;
        this.videoPoster = poster;
        this.fid = fid;
        this.boxType = boxType;
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        this.playRequested = false;
        this.recovery.stop();
        this.loadGeneration.incrementAndGet();
        synchronized (this.mediaLock) {
            this.mediaPlayer.setEventListener((MediaPlayer.EventListener) null);
            if (!this.mediaPlayer.isReleased() && this.mediaPlayer.getVLCVout().areViewsAttached()) {
                this.mediaPlayer.getVLCVout().detachViews();
                this.mediaPlayer.detachViews();
            }
            IMedia media = this.mediaPlayer.getMedia();
            if (media != null) {
                media.setEventListener(null);
                media.release();
                Unit unit = Unit.INSTANCE;
            }
        }
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new AnonymousClass2(null), 2, null);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.utils.VlcPlayer$release$2, reason: invalid class name */
    /* JADX INFO: compiled from: VlcPlayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.utils.VlcPlayer$release$2", f = "VlcPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VlcPlayer.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = VlcPlayer.this.mediaLock;
                VlcPlayer vlcPlayer = VlcPlayer.this;
                synchronized (obj2) {
                    Log.d("release", "release start");
                    try {
                        vlcPlayer.mediaPlayer.stop();
                    } catch (Exception unused) {
                    }
                    try {
                        vlcPlayer.mediaPlayer.release();
                    } catch (Exception unused2) {
                    }
                    Log.d("release", "release end");
                }
                JobKt__JobKt.cancel$default(VlcPlayer.this.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.movieboxpro.android.player.AbstractPlayer
    public void setAudioDelay(long audioDelay) {
        long audioDelay2 = this.mediaPlayer.getAudioDelay() + (audioDelay * ((long) 1000));
        if (this.mediaPlayer.hasMedia()) {
            Log.d("VlcPlayer", "setAudioDelay: " + this.mediaPlayer.setAudioDelay(audioDelay2));
        }
    }
}
