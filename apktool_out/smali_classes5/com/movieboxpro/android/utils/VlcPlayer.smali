.class public Lcom/movieboxpro/android/utils/VlcPlayer;
.super Lcom/movieboxpro/android/player/AbstractPlayer;
.source "VlcPlayer.kt"

# interfaces
.implements Lkotlinx/coroutines/CoroutineScope;


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nVlcPlayer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VlcPlayer.kt\ncom/movieboxpro/android/utils/VlcPlayer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,523:1\n1869#2,2:524\n1869#2,2:526\n1869#2,2:528\n1869#2,2:530\n1869#2,2:532\n1869#2,2:534\n1869#2,2:536\n1869#2,2:538\n*S KotlinDebug\n*F\n+ 1 VlcPlayer.kt\ncom/movieboxpro/android/utils/VlcPlayer\n*L\n62#1:524,2\n78#1:526,2\n83#1:528,2\n90#1:530,2\n95#1:532,2\n100#1:534,2\n111#1:536,2\n118#1:538,2\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00e0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u000f\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0007\n\u0002\u0008\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010%\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0015\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0007\u0008\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u0006\u0010*\u001a\u00020\u000cJ\u0008\u0010+\u001a\u0004\u0018\u00010\u0010J\u0008\u0010,\u001a\u0004\u0018\u00010\u0010J\u0008\u0010-\u001a\u0004\u0018\u00010\u0010J\u0006\u0010.\u001a\u00020\u0014J\u0010\u0010/\u001a\u00020\'2\u0006\u00100\u001a\u000201H\u0002J\u0012\u00102\u001a\u0002032\u0008\u0010/\u001a\u0004\u0018\u00010\'H\u0016J\u0012\u00104\u001a\u0002032\u0008\u0008\u0002\u00100\u001a\u000201H\u0002J\u0006\u00105\u001a\u00020\u001eJ\u0006\u00106\u001a\u000203J\u0008\u00107\u001a\u00020\u001eH\u0002J\u0008\u00108\u001a\u000201H\u0016J\u0010\u00109\u001a\u0002032\u0006\u0010:\u001a\u00020\u0014H\u0016J\u0010\u0010;\u001a\u0002032\u0006\u0010<\u001a\u000201H\u0016J\u0012\u0010=\u001a\u0002032\u0008\u0010>\u001a\u0004\u0018\u00010\u0017H\u0016J\u0008\u0010?\u001a\u000203H\u0016J\u0008\u0010@\u001a\u000201H\u0016J\u0010\u0010A\u001a\u0002032\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u0002032\u0006\u0010E\u001a\u00020FH\u0016J\u0018\u0010G\u001a\u0002032\u0006\u0010H\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010I\u001a\u0008\u0012\u0004\u0012\u00020K0JH\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020NH\u0016J\u0008\u0010O\u001a\u000203H\u0016J\u0018\u0010P\u001a\u0002032\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020RH\u0016J\u0010\u0010P\u001a\u0002032\u0006\u0010T\u001a\u00020\u0014H\u0016J\u0013\u0010U\u001a\u0008\u0012\u0004\u0012\u00020W0VH\u0016\u00a2\u0006\u0002\u0010XJ(\u0010Y\u001a\u0002032\u0008\u0010Z\u001a\u0004\u0018\u00010\u00102\u0014\u0010[\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\\H\u0016J\"\u0010Y\u001a\u0002032\u0008\u0010Z\u001a\u0004\u0018\u00010\u00102\u0006\u0010]\u001a\u0002012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010^\u001a\u0002032\u0006\u0010_\u001a\u00020`H\u0002J\u0012\u0010Y\u001a\u0002032\u0008\u0010a\u001a\u0004\u0018\u00010bH\u0016J\u0008\u0010c\u001a\u000201H\u0016J\u0008\u0010d\u001a\u000203H\u0016J\u0008\u0010e\u001a\u000201H\u0016J\u001a\u0010f\u001a\u0002032\u0008\u0010g\u001a\u0004\u0018\u00010\u00102\u0006\u0010h\u001a\u00020\u001eH\u0016J\u0012\u0010i\u001a\u00020\u00142\u0008\u0010g\u001a\u0004\u0018\u00010\u0010H\u0016J\u0008\u0010j\u001a\u00020\u001eH\u0016J\u0010\u0010k\u001a\u0002032\u0006\u0010l\u001a\u00020\u0014H\u0016J\u0012\u0010m\u001a\u0002032\u0008\u0010>\u001a\u0004\u0018\u00010\u0017H\u0016J\u0008\u0010n\u001a\u000203H\u0016J\u0008\u0010o\u001a\u000203H\u0016J\u0010\u0010p\u001a\u0002032\u0006\u0010q\u001a\u00020\u001eH\u0016J\u0010\u0010r\u001a\u0002032\u0006\u0010s\u001a\u00020\u001eH\u0016J\u0010\u0010t\u001a\u0002032\u0006\u0010u\u001a\u00020\u001eH\u0016J\u0012\u0010v\u001a\u0002032\u0008\u0010w\u001a\u0004\u0018\u00010xH\u0016J\u0008\u0010y\u001a\u000203H\u0016J\u0008\u0010z\u001a\u00020\u0014H\u0016J\u0008\u0010{\u001a\u00020\u0014H\u0016J\u0008\u0010|\u001a\u000203H\u0016J\u0010\u0010}\u001a\u0002032\u0006\u0010~\u001a\u00020RH\u0016J\u0008\u0010\u007f\u001a\u000203H\u0016J\n\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0016J1\u0010\u0082\u0001\u001a\u0002032\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00102\u0008\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0085\u0001\u001a\u000203H\u0016J\u0012\u0010\u0086\u0001\u001a\u0002032\u0007\u0010\u0087\u0001\u001a\u000201H\u0016R\u0014\u0010\u0007\u001a\u00020\u0008X\u0096\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u000cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u0015\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0017`\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0088\u0001"
    }
    d2 = {
        "Lcom/movieboxpro/android/utils/VlcPlayer;",
        "Lcom/movieboxpro/android/player/AbstractPlayer;",
        "Lkotlinx/coroutines/CoroutineScope;",
        "context",
        "Landroid/content/Context;",
        "<init>",
        "(Landroid/content/Context;)V",
        "coroutineContext",
        "Lkotlin/coroutines/CoroutineContext;",
        "getCoroutineContext",
        "()Lkotlin/coroutines/CoroutineContext;",
        "mediaPlayer",
        "Lorg/videolan/libvlc/MediaPlayer;",
        "displayManager",
        "Lorg/videolan/libvlc/util/DisplayManager;",
        "videoTitle",
        "",
        "videoPoster",
        "fid",
        "boxType",
        "",
        "listeners",
        "Ljava/util/ArrayList;",
        "Lcom/movieboxpro/android/listener/PlayListener;",
        "Lkotlin/collections/ArrayList;",
        "currVLCVideoLayout",
        "Lorg/videolan/libvlc/util/VLCVideoLayout;",
        "activity",
        "Landroid/app/Activity;",
        "useSurfaceView",
        "",
        "mediaLock",
        "",
        "loadGeneration",
        "Ljava/util/concurrent/atomic/AtomicLong;",
        "released",
        "playRequested",
        "listenerOverridden",
        "overriddenListener",
        "Lorg/videolan/libvlc/MediaPlayer$EventListener;",
        "recovery",
        "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;",
        "getMediaPlayer",
        "getVideoTitle",
        "getVideoPoster",
        "getFid",
        "getBoxType",
        "eventListener",
        "generation",
        "",
        "setPlayListener",
        "",
        "bindEventListener",
        "isRecoveryExhausted",
        "retryPlayback",
        "checkAvailable",
        "getDuration",
        "setTrackInfo",
        "index",
        "seekTo",
        "time",
        "unRegisterListener",
        "listener",
        "initPlayer",
        "getCurrentPosition",
        "setDisplay",
        "holder",
        "Landroid/view/SurfaceHolder;",
        "setTextureView",
        "textureView",
        "Landroid/view/TextureView;",
        "attachView",
        "vlcVideoLayout",
        "getAudioTracks",
        "",
        "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;",
        "setSurface",
        "surface",
        "Landroid/view/Surface;",
        "start",
        "setVolume",
        "v1",
        "",
        "v2",
        "volume",
        "getTrackInfo",
        "",
        "Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;",
        "()[Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;",
        "setDataSource",
        "path",
        "headers",
        "",
        "startTime",
        "openSource",
        "source",
        "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;",
        "fd",
        "Landroid/content/res/AssetFileDescriptor;",
        "getAudioDelay",
        "reset",
        "getTcpSpeed",
        "addSubtitle",
        "url",
        "select",
        "addAudioTrack",
        "isPlaying",
        "setVideoScale",
        "scale",
        "registerListener",
        "pause",
        "prepareAsync",
        "setEnableHardCodec",
        "enableHardCodec",
        "setLooping",
        "isLooping",
        "setEnableMediaCodec",
        "isEnable",
        "setRenderer",
        "renderer",
        "Lorg/videolan/libvlc/RendererItem;",
        "stop",
        "getCurrAudioIndex",
        "getBufferedPercentage",
        "setOptions",
        "setSpeed",
        "speed",
        "releaseView",
        "getVideoTrack",
        "Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;",
        "setVideoInfo",
        "title",
        "poster",
        "release",
        "setAudioDelay",
        "audioDelay",
        "app_movie_tvRelease"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private activity:Landroid/app/Activity;

.field private boxType:I

.field private final coroutineContext:Lkotlin/coroutines/CoroutineContext;

.field private currVLCVideoLayout:Lorg/videolan/libvlc/util/VLCVideoLayout;

.field private displayManager:Lorg/videolan/libvlc/util/DisplayManager;

.field private fid:Ljava/lang/String;

.field private listenerOverridden:Z

.field private final listeners:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/movieboxpro/android/listener/PlayListener;",
            ">;"
        }
    .end annotation
.end field

.field private final loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

.field private final mediaLock:Ljava/lang/Object;

.field private mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

.field private overriddenListener:Lorg/videolan/libvlc/MediaPlayer$EventListener;

.field private volatile playRequested:Z

.field private final recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

.field private volatile released:Z

.field private useSurfaceView:Z

.field private videoPoster:Ljava/lang/String;

.field private videoTitle:Ljava/lang/String;


# direct methods
.method public static synthetic $r8$lambda$6zXBCMbKDHEll_vdi6mZhdJwHn4(Lcom/movieboxpro/android/utils/VlcPlayer;Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;
    .locals 0

    invoke-static {p0, p1}, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery$lambda$2(Lcom/movieboxpro/android/utils/VlcPlayer;Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$FMd9Cj0D6K7lLVALM3Yi0heIMXQ(Lcom/movieboxpro/android/utils/VlcPlayer;JLkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;
    .locals 0

    invoke-static {p0, p1, p2, p3}, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery$lambda$1(Lcom/movieboxpro/android/utils/VlcPlayer;JLkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$h-J6BbJG7ezXxzeFwG-Bns1vFZQ(Lcom/movieboxpro/android/utils/VlcPlayer;)Lkotlin/Unit;
    .locals 0

    invoke-static {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery$lambda$4(Lcom/movieboxpro/android/utils/VlcPlayer;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$lgaU3PVopevdlA4az9sjQ6-f3Kw(Lkotlinx/coroutines/Job;)V
    .locals 0

    invoke-static {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery$lambda$1$lambda$0(Lkotlinx/coroutines/Job;)V

    return-void
.end method

.method public static synthetic $r8$lambda$xhH2hCqUc1xgK6KFKYePbIGY13I(Lcom/movieboxpro/android/utils/VlcPlayer;JLorg/videolan/libvlc/MediaPlayer$Event;)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Lcom/movieboxpro/android/utils/VlcPlayer;->eventListener$lambda$12(Lcom/movieboxpro/android/utils/VlcPlayer;JLorg/videolan/libvlc/MediaPlayer$Event;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 5

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 30
    invoke-direct {p0}, Lcom/movieboxpro/android/player/AbstractPlayer;-><init>()V

    .line 31
    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getMain()Lkotlinx/coroutines/MainCoroutineDispatcher;

    move-result-object v0

    invoke-virtual {v0}, Lkotlinx/coroutines/MainCoroutineDispatcher;->getImmediate()Lkotlinx/coroutines/MainCoroutineDispatcher;

    move-result-object v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-static {v1, v2, v1}, Lkotlinx/coroutines/SupervisorKt;->SupervisorJob$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableJob;

    move-result-object v3

    check-cast v3, Lkotlin/coroutines/CoroutineContext;

    invoke-virtual {v0, v3}, Lkotlinx/coroutines/MainCoroutineDispatcher;->plus(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->coroutineContext:Lkotlin/coroutines/CoroutineContext;

    .line 33
    new-instance v0, Lorg/videolan/libvlc/MediaPlayer;

    sget-object v3, Lcom/movieboxpro/android/utils/VLCInstance;->INSTANCE:Lcom/movieboxpro/android/utils/VLCInstance;

    invoke-virtual {v3, p1}, Lcom/movieboxpro/android/utils/VLCInstance;->getInstance(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/videolan/libvlc/interfaces/ILibVLC;

    invoke-direct {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;-><init>(Lorg/videolan/libvlc/interfaces/ILibVLC;)V

    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    .line 36
    const-string p1, ""

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoTitle:Ljava/lang/String;

    .line 37
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoPoster:Ljava/lang/String;

    .line 38
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->fid:Ljava/lang/String;

    .line 39
    iput v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->boxType:I

    .line 40
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    .line 44
    new-instance p1, Ljava/lang/Object;

    invoke-direct {p1}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    .line 45
    new-instance p1, Ljava/util/concurrent/atomic/AtomicLong;

    invoke-direct {p1}, Ljava/util/concurrent/atomic/AtomicLong;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    .line 50
    new-instance p1, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    .line 51
    new-instance v0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda2;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;)V

    .line 58
    new-instance v3, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda3;

    invoke-direct {v3, p0}, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda3;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;)V

    .line 59
    new-instance v4, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda4;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda4;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;)V

    .line 50
    invoke-direct {p1, v0, v3, v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;-><init>(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    const-wide/16 v3, 0x0

    .line 126
    invoke-static {p0, v3, v4, v2, v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->bindEventListener$default(Lcom/movieboxpro/android/utils/VlcPlayer;JILjava/lang/Object;)V

    return-void
.end method

.method public static final synthetic access$bindEventListener(Lcom/movieboxpro/android/utils/VlcPlayer;J)V
    .locals 0

    .line 30
    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer;->bindEventListener(J)V

    return-void
.end method

.method public static final synthetic access$checkAvailable(Lcom/movieboxpro/android/utils/VlcPlayer;)Z
    .locals 0

    .line 30
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result p0

    return p0
.end method

.method public static final synthetic access$getLoadGeneration$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/util/concurrent/atomic/AtomicLong;
    .locals 0

    .line 30
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    return-object p0
.end method

.method public static final synthetic access$getMediaLock$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/lang/Object;
    .locals 0

    .line 30
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    return-object p0
.end method

.method public static final synthetic access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;
    .locals 0

    .line 30
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    return-object p0
.end method

.method public static final synthetic access$getPlayRequested$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Z
    .locals 0

    .line 30
    iget-boolean p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    return p0
.end method

.method public static final synthetic access$getReleased$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Z
    .locals 0

    .line 30
    iget-boolean p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    return p0
.end method

.method public static final synthetic access$setUseSurfaceView$p(Lcom/movieboxpro/android/utils/VlcPlayer;Z)V
    .locals 0

    .line 30
    iput-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->useSurfaceView:Z

    return-void
.end method

.method private final bindEventListener(J)V
    .locals 2

    .line 136
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    .line 137
    iget-boolean v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listenerOverridden:Z

    if-eqz v1, :cond_0

    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->overriddenListener:Lorg/videolan/libvlc/MediaPlayer$EventListener;

    goto :goto_0

    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer;->eventListener(J)Lorg/videolan/libvlc/MediaPlayer$EventListener;

    move-result-object p1

    .line 136
    :goto_0
    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setEventListener(Lorg/videolan/libvlc/MediaPlayer$EventListener;)V

    return-void
.end method

.method static synthetic bindEventListener$default(Lcom/movieboxpro/android/utils/VlcPlayer;JILjava/lang/Object;)V
    .locals 0

    if-nez p4, :cond_1

    and-int/lit8 p3, p3, 0x1

    if-eqz p3, :cond_0

    .line 135
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide p1

    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer;->bindEventListener(J)V

    return-void

    :cond_1
    new-instance p0, Ljava/lang/UnsupportedOperationException;

    const-string p1, "Super calls with default arguments not supported in this target, function: bindEventListener"

    invoke-direct {p0, p1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method private final checkAvailable()Z
    .locals 1

    .line 150
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->isReleased()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->hasMedia()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private final eventListener(J)Lorg/videolan/libvlc/MediaPlayer$EventListener;
    .locals 1

    .line 73
    new-instance v0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;J)V

    return-object v0
.end method

.method private static final eventListener$lambda$12(Lcom/movieboxpro/android/utils/VlcPlayer;JLorg/videolan/libvlc/MediaPlayer$Event;)V
    .locals 4

    .line 74
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    if-nez v0, :cond_a

    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide v0

    cmp-long v2, p1, v0

    if-eqz v2, :cond_0

    goto/16 :goto_7

    .line 75
    :cond_0
    iget p1, p3, Lorg/videolan/libvlc/MediaPlayer$Event;->type:I

    packed-switch p1, :pswitch_data_0

    :pswitch_0
    goto/16 :goto_7

    .line 118
    :pswitch_1
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 538
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_1

    .line 119
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onPositionChanged()V

    goto :goto_0

    .line 116
    :pswitch_2
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p3}, Lorg/videolan/libvlc/MediaPlayer$Event;->getTimeChanged()J

    move-result-wide p1

    invoke-virtual {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->onProgress(J)V

    goto/16 :goto_7

    .line 77
    :pswitch_3
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pause()V

    .line 78
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 526
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_2
    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_2

    .line 79
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onError()V

    goto :goto_1

    .line 105
    :pswitch_4
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->getTime()J

    move-result-wide p1

    .line 106
    iget-object p3, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p3}, Lorg/videolan/libvlc/MediaPlayer;->getLength()J

    move-result-wide v0

    const/16 p3, 0x3a98

    int-to-long v2, p3

    add-long/2addr v2, p1

    cmp-long p3, v2, v0

    if-gez p3, :cond_3

    .line 108
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->onPrematureEnd(J)V

    goto/16 :goto_7

    .line 110
    :cond_3
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pause()V

    .line 111
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 536
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_4
    :goto_2
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_4

    .line 112
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onComplete()V

    goto :goto_2

    .line 100
    :pswitch_5
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 534
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_5
    :goto_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_5

    .line 101
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onPause()V

    goto :goto_3

    .line 90
    :pswitch_6
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 530
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_6
    :goto_4
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_6

    .line 91
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onPlay()V

    goto :goto_4

    .line 83
    :pswitch_7
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 528
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_7
    :goto_5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    .line 84
    invoke-virtual {p3}, Lorg/videolan/libvlc/MediaPlayer$Event;->getBuffering()F

    move-result p2

    const v0, 0x41855556

    cmpg-float p2, p2, v0

    if-nez p2, :cond_8

    goto :goto_5

    :cond_8
    if-eqz p1, :cond_7

    .line 85
    invoke-virtual {p3}, Lorg/videolan/libvlc/MediaPlayer$Event;->getBuffering()F

    move-result p2

    invoke-interface {p1, p2}, Lcom/movieboxpro/android/listener/PlayListener;->onBuffer(F)V

    goto :goto_5

    .line 95
    :pswitch_8
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    .line 532
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_9
    :goto_6
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz p1, :cond_9

    .line 96
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayListener;->onStart()V

    goto :goto_6

    :cond_a
    :goto_7
    return-void

    :pswitch_data_0
    .packed-switch 0x102
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
    .end packed-switch
.end method

.method private final openSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V
    .locals 11

    .line 284
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->incrementAndGet()J

    move-result-wide v3

    .line 285
    move-object v0, p0

    check-cast v0, Lkotlinx/coroutines/CoroutineScope;

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    move-object v7, v1

    check-cast v7, Lkotlin/coroutines/CoroutineContext;

    new-instance v8, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;

    const/4 v6, 0x0

    move-object v1, v8

    move-object v2, p0

    move-object v5, p1

    invoke-direct/range {v1 .. v6}, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;Lkotlin/coroutines/Continuation;)V

    check-cast v8, Lkotlin/jvm/functions/Function2;

    const/4 v9, 0x2

    const/4 v10, 0x0

    const/4 p1, 0x0

    move-object v5, v0

    move-object v6, v7

    move-object v7, p1

    invoke-static/range {v5 .. v10}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method private static final recovery$lambda$1(Lcom/movieboxpro/android/utils/VlcPlayer;JLkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;
    .locals 7

    const-string v0, "action"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 52
    move-object v1, p0

    check-cast v1, Lkotlinx/coroutines/CoroutineScope;

    new-instance p0, Lcom/movieboxpro/android/utils/VlcPlayer$recovery$1$job$1;

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, p3, v0}, Lcom/movieboxpro/android/utils/VlcPlayer$recovery$1$job$1;-><init>(JLkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)V

    move-object v4, p0

    check-cast v4, Lkotlin/jvm/functions/Function2;

    const/4 v5, 0x3

    const/4 v6, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v1 .. v6}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p0

    .line 56
    new-instance p1, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda0;-><init>(Lkotlinx/coroutines/Job;)V

    return-object p1
.end method

.method private static final recovery$lambda$1$lambda$0(Lkotlinx/coroutines/Job;)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 56
    invoke-static {p0, v0, v1, v0}, Lkotlinx/coroutines/Job$DefaultImpls;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    return-void
.end method

.method private static final recovery$lambda$2(Lcom/movieboxpro/android/utils/VlcPlayer;Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;
    .locals 1

    const-string v0, "source"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 58
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/utils/VlcPlayer;->openSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private static final recovery$lambda$4(Lcom/movieboxpro/android/utils/VlcPlayer;)Lkotlin/Unit;
    .locals 1

    const/4 v0, 0x0

    .line 60
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 61
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->incrementAndGet()J

    .line 62
    iget-object p0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    check-cast p0, Ljava/lang/Iterable;

    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->toList(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 524
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/listener/PlayListener;

    if-eqz v0, :cond_0

    .line 62
    invoke-interface {v0}, Lcom/movieboxpro/android/listener/PlayListener;->onError()V

    goto :goto_0

    .line 63
    :cond_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method


# virtual methods
.method public addAudioTrack(Ljava/lang/String;)I
    .locals 3

    const/4 v0, 0x1

    .line 343
    :try_start_0
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    .line 345
    new-instance v2, Ljava/net/URL;

    invoke-direct {v2, p1}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/net/URL;->toURI()Ljava/net/URI;

    move-result-object p1

    invoke-virtual {p1}, Ljava/net/URI;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    .line 343
    invoke-virtual {v1, v0, p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->addSlave(ILandroid/net/Uri;Z)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    .line 349
    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p1

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Add audio track failed:"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p1, v1}, Lcom/movieboxpro/android/utils/ToastUtils;->showShort(Ljava/lang/String;[Ljava/lang/Object;)V

    :goto_0
    return v0
.end method

.method public addSubtitle(Ljava/lang/String;Z)V
    .locals 0

    return-void
.end method

.method public attachView(Lorg/videolan/libvlc/util/VLCVideoLayout;Landroid/app/Activity;)V
    .locals 7

    const-string v0, "vlcVideoLayout"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "activity"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 205
    iput-object p2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->activity:Landroid/app/Activity;

    .line 206
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->currVLCVideoLayout:Lorg/videolan/libvlc/util/VLCVideoLayout;

    .line 207
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v0

    invoke-interface {v0}, Lorg/videolan/libvlc/interfaces/IVLCVout;->areViewsAttached()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 208
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->detachViews()V

    .line 210
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    if-nez v0, :cond_1

    .line 211
    new-instance v0, Lorg/videolan/libvlc/util/DisplayManager;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    move-object v1, v0

    move-object v2, p2

    invoke-direct/range {v1 .. v6}, Lorg/videolan/libvlc/util/DisplayManager;-><init>(Landroid/app/Activity;Landroidx/lifecycle/LiveData;ZZZ)V

    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    .line 213
    :cond_1
    iget-object p2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {p2, p1, v0, v1, v2}, Lorg/videolan/libvlc/MediaPlayer;->attachViews(Lorg/videolan/libvlc/util/VLCVideoLayout;Lorg/videolan/libvlc/util/DisplayManager;ZZ)V

    .line 214
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-static {}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->values()[Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    move-result-object p2

    sget-object v0, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_BEST_FIT:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->ordinal()I

    move-result v0

    aget-object p2, p2, v0

    invoke-virtual {p1, p2}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    .line 215
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result p1

    if-eqz p1, :cond_2

    iget-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted()Z

    move-result p1

    if-nez p1, :cond_2

    .line 216
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->play()V

    :cond_2
    return-void
.end method

.method public getAudioDelay()J
    .locals 4

    .line 318
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getAudioDelay()J

    move-result-wide v0

    const/16 v2, 0x3e8

    int-to-long v2, v2

    div-long/2addr v0, v2

    return-wide v0
.end method

.method public getAudioTracks()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;",
            ">;"
        }
    .end annotation

    .line 221
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 222
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getAudioTracks()[Lorg/videolan/libvlc/MediaPlayer$TrackDescription;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {v0}, Lkotlin/collections/ArraysKt;->toList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    if-nez v0, :cond_2

    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    check-cast v0, Ljava/util/List;

    goto :goto_0

    .line 224
    :cond_1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    check-cast v0, Ljava/util/List;

    :cond_2
    :goto_0
    return-object v0
.end method

.method public final getBoxType()I
    .locals 1

    .line 71
    iget v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->boxType:I

    return v0
.end method

.method public getBufferedPercentage()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public getCoroutineContext()Lkotlin/coroutines/CoroutineContext;
    .locals 1

    .line 31
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->coroutineContext:Lkotlin/coroutines/CoroutineContext;

    return-object v0
.end method

.method public getCurrAudioIndex()I
    .locals 1

    .line 445
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getAudioTrack()I

    move-result v0

    return v0
.end method

.method public getCurrentPosition()J
    .locals 2

    .line 183
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 184
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getTime()J

    move-result-wide v0

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x0

    :goto_0
    return-wide v0
.end method

.method public getDuration()J
    .locals 2

    .line 154
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->hasMedia()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 155
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getLength()J

    move-result-wide v0

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x0

    :goto_0
    return-wide v0
.end method

.method public final getFid()Ljava/lang/String;
    .locals 1

    .line 70
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->fid:Ljava/lang/String;

    return-object v0
.end method

.method public final getMediaPlayer()Lorg/videolan/libvlc/MediaPlayer;
    .locals 1

    .line 66
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    return-object v0
.end method

.method public getTcpSpeed()J
    .locals 2

    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;
    .locals 1

    const/4 v0, 0x0

    new-array v0, v0, [Ltv/danmaku/ijk/media/player/misc/IjkTrackInfo;

    return-object v0
.end method

.method public final getVideoPoster()Ljava/lang/String;
    .locals 1

    .line 69
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoPoster:Ljava/lang/String;

    return-object v0
.end method

.method public final getVideoTitle()Ljava/lang/String;
    .locals 1

    .line 68
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoTitle:Ljava/lang/String;

    return-object v0
.end method

.method public getVideoTrack()Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;
    .locals 2

    .line 468
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getCurrentVideoTrack()Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;

    move-result-object v0

    const-string v1, "getCurrentVideoTrack(...)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object v0
.end method

.method public initPlayer()V
    .locals 0

    return-void
.end method

.method public isPlaying()Z
    .locals 1

    .line 355
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 356
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->isPlaying()Z

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public final isRecoveryExhausted()Z
    .locals 1

    .line 141
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted()Z

    move-result v0

    return v0
.end method

.method public pause()V
    .locals 2

    const/4 v0, 0x0

    .line 391
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 392
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pause()V

    .line 393
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    monitor-enter v0

    .line 394
    :try_start_0
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 395
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->pause()V

    .line 397
    :cond_0
    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 393
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public prepareAsync()V
    .locals 0

    return-void
.end method

.method public registerListener(Lcom/movieboxpro/android/listener/PlayListener;)V
    .locals 1

    .line 387
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public release()V
    .locals 8

    .line 479
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 480
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    const/4 v0, 0x0

    .line 481
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 482
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->stop()V

    .line 483
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->incrementAndGet()J

    .line 484
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    monitor-enter v0

    .line 485
    :try_start_0
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Lorg/videolan/libvlc/MediaPlayer;->setEventListener(Lorg/videolan/libvlc/MediaPlayer$EventListener;)V

    .line 486
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->isReleased()Z

    move-result v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v1

    invoke-interface {v1}, Lorg/videolan/libvlc/interfaces/IVLCVout;->areViewsAttached()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 487
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v1

    invoke-interface {v1}, Lorg/videolan/libvlc/interfaces/IVLCVout;->detachViews()V

    .line 488
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->detachViews()V

    .line 490
    :cond_1
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 491
    invoke-interface {v1, v2}, Lorg/videolan/libvlc/interfaces/IMedia;->setEventListener(Lorg/videolan/libvlc/interfaces/IMedia$EventListener;)V

    .line 492
    invoke-interface {v1}, Lorg/videolan/libvlc/interfaces/IMedia;->release()V

    .line 490
    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 484
    :cond_2
    monitor-exit v0

    .line 496
    move-object v0, p0

    check-cast v0, Lkotlinx/coroutines/CoroutineScope;

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object v1

    move-object v3, v1

    check-cast v3, Lkotlin/coroutines/CoroutineContext;

    new-instance v1, Lcom/movieboxpro/android/utils/VlcPlayer$release$2;

    invoke-direct {v1, p0, v2}, Lcom/movieboxpro/android/utils/VlcPlayer$release$2;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;Lkotlin/coroutines/Continuation;)V

    move-object v5, v1

    check-cast v5, Lkotlin/jvm/functions/Function2;

    const/4 v6, 0x2

    const/4 v7, 0x0

    const/4 v4, 0x0

    move-object v2, v0

    invoke-static/range {v2 .. v7}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    return-void

    :catchall_0
    move-exception v1

    .line 484
    monitor-exit v0

    throw v1
.end method

.method public releaseView()V
    .locals 1

    .line 461
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->isReleased()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v0

    invoke-interface {v0}, Lorg/videolan/libvlc/interfaces/IVLCVout;->areViewsAttached()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 462
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v0

    invoke-interface {v0}, Lorg/videolan/libvlc/interfaces/IVLCVout;->detachViews()V

    .line 463
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->detachViews()V

    :cond_0
    return-void
.end method

.method public reset()V
    .locals 2

    const/4 v0, 0x0

    .line 322
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 323
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->stop()V

    .line 324
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->incrementAndGet()J

    .line 325
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    monitor-enter v0

    .line 326
    :try_start_0
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 327
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->stop()V

    .line 328
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-interface {v1}, Lorg/videolan/libvlc/interfaces/IMedia;->release()V

    .line 330
    :cond_0
    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 325
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public final retryPlayback()V
    .locals 4

    .line 144
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->sourceForRetry()Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 145
    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPath()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPositionMs()J

    move-result-wide v2

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getUseSurfaceView()Z

    move-result v0

    invoke-virtual {p0, v1, v2, v3, v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->setDataSource(Ljava/lang/String;JZ)V

    :cond_0
    return-void
.end method

.method public seekTo(J)V
    .locals 3

    .line 168
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 170
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    long-to-float p1, p1

    invoke-virtual {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->getDuration()J

    move-result-wide v1

    long-to-float p2, v1

    div-float/2addr p1, p2

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setPosition(F)V

    :cond_0
    return-void
.end method

.method public setAudioDelay(J)V
    .locals 4

    .line 513
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getAudioDelay()J

    move-result-wide v0

    const/16 v2, 0x3e8

    int-to-long v2, v2

    mul-long p1, p1, v2

    add-long/2addr v0, p1

    .line 514
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->hasMedia()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 515
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1, v0, v1}, Lorg/videolan/libvlc/MediaPlayer;->setAudioDelay(J)Z

    move-result p1

    .line 516
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "setAudioDelay: "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "VlcPlayer"

    invoke-static {p2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    return-void
.end method

.method public setDataSource(Landroid/content/res/AssetFileDescriptor;)V
    .locals 0

    return-void
.end method

.method public setDataSource(Ljava/lang/String;JZ)V
    .locals 3

    .line 276
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    if-nez v0, :cond_1

    move-object v0, p1

    check-cast v0, Ljava/lang/CharSequence;

    if-eqz v0, :cond_1

    invoke-static {v0}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 277
    :cond_0
    new-instance v0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    const-wide/16 v1, 0x0

    invoke-static {p2, p3, v1, v2}, Lkotlin/ranges/RangesKt;->coerceAtLeast(JJ)J

    move-result-wide p2

    invoke-direct {v0, p1, p2, p3, p4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;-><init>(Ljava/lang/String;JZ)V

    .line 278
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->startSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V

    const/4 p1, 0x1

    .line 279
    iput-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 280
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->openSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public setDataSource(Ljava/lang/String;Ljava/util/Map;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    return-void
.end method

.method public setDisplay(Landroid/view/SurfaceHolder;)V
    .locals 1

    const-string v0, "holder"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public setEnableHardCodec(Z)V
    .locals 1

    .line 405
    sget-object v0, Lcom/movieboxpro/android/utils/SettingManager;->INSTANCE:Lcom/movieboxpro/android/utils/SettingManager;

    invoke-virtual {v0, p1}, Lcom/movieboxpro/android/utils/SettingManager;->setHwDecoder(Z)V

    .line 406
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->hasMedia()Z

    move-result v0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    .line 408
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 410
    invoke-interface {p1}, Lorg/videolan/libvlc/interfaces/IMedia;->setDefaultMediaPlayerOptions()V

    goto :goto_0

    .line 413
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object p1

    if-eqz p1, :cond_1

    const/4 v0, 0x0

    .line 415
    invoke-interface {p1, v0, v0}, Lorg/videolan/libvlc/interfaces/IMedia;->setHWDecoderEnabled(ZZ)V

    :cond_1
    :goto_0
    return-void
.end method

.method public setEnableMediaCodec(Z)V
    .locals 0

    return-void
.end method

.method public setLooping(Z)V
    .locals 0

    return-void
.end method

.method public setOptions()V
    .locals 0

    return-void
.end method

.method public setPlayListener(Lorg/videolan/libvlc/MediaPlayer$EventListener;)V
    .locals 1

    const/4 v0, 0x1

    .line 130
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listenerOverridden:Z

    .line 131
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->overriddenListener:Lorg/videolan/libvlc/MediaPlayer$EventListener;

    .line 132
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setEventListener(Lorg/videolan/libvlc/MediaPlayer$EventListener;)V

    return-void
.end method

.method public setRenderer(Lorg/videolan/libvlc/RendererItem;)V
    .locals 1

    .line 430
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setRenderer(Lorg/videolan/libvlc/RendererItem;)I

    return-void
.end method

.method public setSpeed(F)V
    .locals 1

    .line 457
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setRate(F)V

    return-void
.end method

.method public setSurface(Landroid/view/Surface;)V
    .locals 1

    const-string v0, "surface"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method public setTextureView(Landroid/view/TextureView;)V
    .locals 2

    const-string v0, "textureView"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 195
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v0

    invoke-interface {v0}, Lorg/videolan/libvlc/interfaces/IVLCVout;->detachViews()V

    .line 196
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v0

    invoke-interface {v0, p1}, Lorg/videolan/libvlc/interfaces/IVLCVout;->setVideoView(Landroid/view/TextureView;)V

    .line 197
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object p1

    invoke-interface {p1}, Lorg/videolan/libvlc/interfaces/IVLCVout;->attachViews()V

    .line 198
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-static {}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->values()[Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    move-result-object v0

    sget-object v1, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_BEST_FIT:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->ordinal()I

    move-result v1

    aget-object v0, v0, v1

    invoke-virtual {p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    .line 199
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted()Z

    move-result p1

    if-nez p1, :cond_0

    .line 200
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p1}, Lorg/videolan/libvlc/MediaPlayer;->play()V

    :cond_0
    return-void
.end method

.method public setTrackInfo(I)V
    .locals 1

    .line 162
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 163
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setAudioTrack(I)Z

    :cond_0
    return-void
.end method

.method public setVideoInfo(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
    .locals 0

    .line 472
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoTitle:Ljava/lang/String;

    .line 473
    iput-object p2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->videoPoster:Ljava/lang/String;

    .line 474
    iput-object p3, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->fid:Ljava/lang/String;

    .line 475
    iput p4, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->boxType:I

    return-void
.end method

.method public setVideoScale(I)V
    .locals 1

    if-eqz p1, :cond_3

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/4 v0, 0x4

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 374
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    sget-object v0, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_16_9:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    goto :goto_0

    .line 368
    :cond_1
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    sget-object v0, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_FILL:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    goto :goto_0

    .line 371
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    sget-object v0, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_FIT_SCREEN:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    goto :goto_0

    .line 365
    :cond_3
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    sget-object v0, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_BEST_FIT:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {p1, v0}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    :goto_0
    return-void
.end method

.method public setVolume(FF)V
    .locals 0

    return-void
.end method

.method public setVolume(I)V
    .locals 1

    .line 264
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0, p1}, Lorg/videolan/libvlc/MediaPlayer;->setVolume(I)I

    return-void
.end method

.method public start()V
    .locals 10

    .line 235
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 236
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 237
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->resume()Z

    move-result v1

    if-nez v1, :cond_1

    return-void

    .line 238
    :cond_1
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    monitor-enter v1

    .line 239
    :try_start_0
    iget-boolean v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->released:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_2

    monitor-exit v1

    return-void

    .line 240
    :cond_2
    :try_start_1
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v2

    if-eqz v2, :cond_5

    .line 242
    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v2}, Lorg/videolan/libvlc/MediaPlayer;->getVLCVout()Lorg/videolan/libvlc/interfaces/IVLCVout;

    move-result-object v2

    invoke-interface {v2}, Lorg/videolan/libvlc/interfaces/IVLCVout;->areViewsAttached()Z

    move-result v2

    const/4 v3, 0x0

    if-nez v2, :cond_4

    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->activity:Landroid/app/Activity;

    if-eqz v2, :cond_4

    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->currVLCVideoLayout:Lorg/videolan/libvlc/util/VLCVideoLayout;

    if-eqz v2, :cond_4

    .line 243
    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    if-nez v2, :cond_3

    .line 244
    new-instance v2, Lorg/videolan/libvlc/util/DisplayManager;

    iget-object v5, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->activity:Landroid/app/Activity;

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v4, v2

    invoke-direct/range {v4 .. v9}, Lorg/videolan/libvlc/util/DisplayManager;-><init>(Landroid/app/Activity;Landroidx/lifecycle/LiveData;ZZZ)V

    iput-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    .line 246
    :cond_3
    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    iget-object v4, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->currVLCVideoLayout:Lorg/videolan/libvlc/util/VLCVideoLayout;

    invoke-static {v4}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    iget-object v5, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->displayManager:Lorg/videolan/libvlc/util/DisplayManager;

    invoke-virtual {v2, v4, v5, v0, v3}, Lorg/videolan/libvlc/MediaPlayer;->attachViews(Lorg/videolan/libvlc/util/VLCVideoLayout;Lorg/videolan/libvlc/util/DisplayManager;ZZ)V

    .line 247
    iget-object v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-static {}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->values()[Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    move-result-object v3

    sget-object v4, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->SURFACE_BEST_FIT:Lorg/videolan/libvlc/MediaPlayer$ScaleType;

    invoke-virtual {v4}, Lorg/videolan/libvlc/MediaPlayer$ScaleType;->ordinal()I

    move-result v4

    aget-object v3, v3, v4

    invoke-virtual {v2, v3}, Lorg/videolan/libvlc/MediaPlayer;->setVideoScale(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)V

    const/4 v3, 0x1

    :cond_4
    const-wide/16 v4, 0x0

    const/4 v2, 0x0

    .line 250
    invoke-static {p0, v4, v5, v0, v2}, Lcom/movieboxpro/android/utils/VlcPlayer;->bindEventListener$default(Lcom/movieboxpro/android/utils/VlcPlayer;JILjava/lang/Object;)V

    .line 251
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->play()V

    if-eqz v3, :cond_5

    .line 253
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->getCurrentPosition()J

    move-result-wide v2

    invoke-virtual {v0, v2, v3}, Lorg/videolan/libvlc/MediaPlayer;->setTime(J)J

    .line 256
    :cond_5
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 238
    monitor-exit v1

    return-void

    :catchall_0
    move-exception v0

    monitor-exit v1

    throw v0
.end method

.method public stop()V
    .locals 2

    const/4 v0, 0x0

    .line 434
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->playRequested:Z

    .line 435
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->recovery:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->stop()V

    .line 436
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->loadGeneration:Ljava/util/concurrent/atomic/AtomicLong;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->incrementAndGet()J

    .line 437
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaLock:Ljava/lang/Object;

    monitor-enter v0

    .line 438
    :try_start_0
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlayer;->checkAvailable()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 439
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->mediaPlayer:Lorg/videolan/libvlc/MediaPlayer;

    invoke-virtual {v1}, Lorg/videolan/libvlc/MediaPlayer;->stop()V

    .line 441
    :cond_0
    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 437
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0

    throw v1
.end method

.method public unRegisterListener(Lcom/movieboxpro/android/listener/PlayListener;)V
    .locals 1

    .line 175
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer;->listeners:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    return-void
.end method
