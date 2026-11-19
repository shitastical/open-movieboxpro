.class public final Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;
.super Ljava/lang/Object;
.source "CustomExoPlayer.kt"

# interfaces
.implements Landroidx/media3/exoplayer/analytics/AnalyticsListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/movieboxpro/android/exo/CustomExoPlayer;->initPlayer()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nCustomExoPlayer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomExoPlayer.kt\ncom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,648:1\n1869#2,2:649\n1869#2,2:651\n1878#2,3:653\n1869#2,2:656\n1869#2,2:658\n1869#2,2:660\n1869#2,2:662\n1869#2,2:664\n*S KotlinDebug\n*F\n+ 1 CustomExoPlayer.kt\ncom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1\n*L\n101#1:649,2\n112#1:651,2\n117#1:653,3\n140#1:656,2\n150#1:658,2\n159#1:660,2\n172#1:662,2\n190#1:664,2\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000S\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0008\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J(\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J \u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J0\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0016\u00a8\u0006\u001d"
    }
    d2 = {
        "com/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1",
        "Landroidx/media3/exoplayer/analytics/AnalyticsListener;",
        "onPlayerError",
        "",
        "eventTime",
        "Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;",
        "error",
        "Landroidx/media3/common/PlaybackException;",
        "onTracksChanged",
        "tracks",
        "Landroidx/media3/common/Tracks;",
        "onPlayerStateChanged",
        "playWhenReady",
        "",
        "playbackState",
        "",
        "onVideoSizeChanged",
        "videoSize",
        "Landroidx/media3/common/VideoSize;",
        "onLoadStarted",
        "loadEventInfo",
        "Landroidx/media3/exoplayer/source/LoadEventInfo;",
        "mediaLoadData",
        "Landroidx/media3/exoplayer/source/MediaLoadData;",
        "retryCount",
        "onLoadCanceled",
        "onLoadError",
        "Ljava/io/IOException;",
        "wasCanceled",
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
.field final synthetic this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;


# direct methods
.method constructor <init>(Lcom/movieboxpro/android/exo/CustomExoPlayer;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    .line 95
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public synthetic onAudioAttributesChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/AudioAttributes;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioAttributesChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/AudioAttributes;)V

    return-void
.end method

.method public synthetic onAudioCodecError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioCodecError(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V

    return-void
.end method

.method public synthetic onAudioDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;J)V

    return-void
.end method

.method public synthetic onAudioDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;JJ)V
    .locals 0

    invoke-static/range {p0 .. p6}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;JJ)V

    return-void
.end method

.method public synthetic onAudioDecoderReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioDecoderReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;)V

    return-void
.end method

.method public synthetic onAudioDisabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioDisabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V

    return-void
.end method

.method public synthetic onAudioEnabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioEnabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V

    return-void
.end method

.method public synthetic onAudioInputFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Format;Landroidx/media3/exoplayer/DecoderReuseEvaluation;)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioInputFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Format;Landroidx/media3/exoplayer/DecoderReuseEvaluation;)V

    return-void
.end method

.method public synthetic onAudioPositionAdvancing(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioPositionAdvancing(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V

    return-void
.end method

.method public synthetic onAudioSessionIdChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioSessionIdChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onAudioSinkError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioSinkError(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V

    return-void
.end method

.method public synthetic onAudioTrackInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/audio/AudioSink$AudioTrackConfig;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioTrackInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/audio/AudioSink$AudioTrackConfig;)V

    return-void
.end method

.method public synthetic onAudioTrackReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/audio/AudioSink$AudioTrackConfig;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioTrackReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/audio/AudioSink$AudioTrackConfig;)V

    return-void
.end method

.method public synthetic onAudioUnderrun(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJJ)V
    .locals 0

    invoke-static/range {p0 .. p6}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAudioUnderrun(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJJ)V

    return-void
.end method

.method public synthetic onAvailableCommandsChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Player$Commands;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onAvailableCommandsChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Player$Commands;)V

    return-void
.end method

.method public synthetic onBandwidthEstimate(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJJ)V
    .locals 0

    invoke-static/range {p0 .. p6}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onBandwidthEstimate(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJJ)V

    return-void
.end method

.method public synthetic onCues(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/text/CueGroup;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onCues(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/text/CueGroup;)V

    return-void
.end method

.method public synthetic onCues(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/util/List;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onCues(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/util/List;)V

    return-void
.end method

.method public synthetic onDeviceInfoChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/DeviceInfo;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDeviceInfoChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/DeviceInfo;)V

    return-void
.end method

.method public synthetic onDeviceVolumeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IZ)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDeviceVolumeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IZ)V

    return-void
.end method

.method public synthetic onDownstreamFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/MediaLoadData;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDownstreamFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/MediaLoadData;)V

    return-void
.end method

.method public synthetic onDrmKeysLoaded(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmKeysLoaded(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onDrmKeysRemoved(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmKeysRemoved(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onDrmKeysRestored(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmKeysRestored(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onDrmSessionAcquired(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmSessionAcquired(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onDrmSessionAcquired(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmSessionAcquired(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onDrmSessionManagerError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmSessionManagerError(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V

    return-void
.end method

.method public synthetic onDrmSessionReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDrmSessionReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onDroppedVideoFrames(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJ)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onDroppedVideoFrames(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IJ)V

    return-void
.end method

.method public synthetic onEvents(Landroidx/media3/common/Player;Landroidx/media3/exoplayer/analytics/AnalyticsListener$Events;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onEvents(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/common/Player;Landroidx/media3/exoplayer/analytics/AnalyticsListener$Events;)V

    return-void
.end method

.method public synthetic onIsLoadingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onIsLoadingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V

    return-void
.end method

.method public synthetic onIsPlayingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onIsPlayingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V

    return-void
.end method

.method public onLoadCanceled(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;)V
    .locals 2

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "loadEventInfo"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "mediaLoadData"

    invoke-static {p3, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 209
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    const-string v0, "CANCEL"

    const/4 v1, 0x0

    invoke-static {p1, v0, p2, p3, v1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$logHlsLoad(Lcom/movieboxpro/android/exo/CustomExoPlayer;Ljava/lang/String;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;I)V

    return-void
.end method

.method public synthetic onLoadCompleted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onLoadCompleted(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;)V

    return-void
.end method

.method public onLoadError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;Ljava/io/IOException;Z)V
    .locals 1

    const-string p5, "eventTime"

    invoke-static {p1, p5}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "loadEventInfo"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "mediaLoadData"

    invoke-static {p3, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "error"

    invoke-static {p4, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 219
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-virtual {p4}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p4

    invoke-virtual {p4}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object p4

    new-instance p5, Ljava/lang/StringBuilder;

    const-string v0, "ERROR "

    invoke-direct {p5, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p5, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p4

    const/4 p5, 0x0

    invoke-static {p1, p4, p2, p3, p5}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$logHlsLoad(Lcom/movieboxpro/android/exo/CustomExoPlayer;Ljava/lang/String;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;I)V

    return-void
.end method

.method public synthetic onLoadStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onLoadStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;)V

    return-void
.end method

.method public onLoadStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;I)V
    .locals 1

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "loadEventInfo"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "mediaLoadData"

    invoke-static {p3, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 201
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    const-string v0, "START"

    invoke-static {p1, v0, p2, p3, p4}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$logHlsLoad(Lcom/movieboxpro/android/exo/CustomExoPlayer;Ljava/lang/String;Landroidx/media3/exoplayer/source/LoadEventInfo;Landroidx/media3/exoplayer/source/MediaLoadData;I)V

    return-void
.end method

.method public synthetic onLoadingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onLoadingChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V

    return-void
.end method

.method public synthetic onMaxSeekToPreviousPositionChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onMaxSeekToPreviousPositionChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V

    return-void
.end method

.method public synthetic onMediaItemTransition(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaItem;I)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onMediaItemTransition(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaItem;I)V

    return-void
.end method

.method public synthetic onMediaMetadataChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaMetadata;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onMediaMetadataChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaMetadata;)V

    return-void
.end method

.method public synthetic onMetadata(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Metadata;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onMetadata(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Metadata;)V

    return-void
.end method

.method public synthetic onPlayWhenReadyChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;ZI)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlayWhenReadyChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;ZI)V

    return-void
.end method

.method public synthetic onPlaybackParametersChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/PlaybackParameters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlaybackParametersChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/PlaybackParameters;)V

    return-void
.end method

.method public synthetic onPlaybackStateChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlaybackStateChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onPlaybackSuppressionReasonChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlaybackSuppressionReasonChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public onPlayerError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/PlaybackException;)V
    .locals 2

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "error"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 101
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 649
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/exo/PlayListener;

    .line 102
    invoke-virtual {p2}, Landroidx/media3/common/PlaybackException;->getErrorCodeName()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Lcom/movieboxpro/android/exo/PlayListener;->onError(Ljava/lang/String;)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public synthetic onPlayerErrorChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/PlaybackException;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlayerErrorChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/PlaybackException;)V

    return-void
.end method

.method public synthetic onPlayerReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlayerReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public onPlayerStateChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;ZI)V
    .locals 5

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 134
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$isLastReportedPlayWhenReady$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Z

    move-result p1

    if-ne p1, p2, :cond_0

    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getLastReportedPlaybackState$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)I

    move-result p1

    if-eq p1, p3, :cond_9

    .line 136
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getPlayer$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object p1

    invoke-interface {p1}, Landroidx/media3/exoplayer/ExoPlayer;->getBufferedPercentage()I

    .line 137
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$isBuffering$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Z

    move-result p1

    const/4 v0, 0x0

    const/4 v1, 0x4

    const/4 v2, 0x3

    if-eqz p1, :cond_3

    if-eq p3, v2, :cond_1

    if-eq p3, v1, :cond_1

    goto :goto_1

    .line 140
    :cond_1
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 656
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/movieboxpro/android/exo/PlayListener;

    const/high16 v4, 0x42c80000    # 100.0f

    .line 141
    invoke-interface {v3, v4}, Lcom/movieboxpro/android/exo/PlayListener;->onBuffer(F)V

    goto :goto_0

    .line 143
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1, v0}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setBuffering$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;Z)V

    .line 147
    :cond_3
    :goto_1
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$isPreparing$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Z

    move-result p1

    if-eqz p1, :cond_5

    if-ne p3, v2, :cond_5

    .line 150
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 658
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_2
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/movieboxpro/android/exo/PlayListener;

    .line 151
    invoke-interface {v2}, Lcom/movieboxpro/android/exo/PlayListener;->onStart()V

    goto :goto_2

    .line 153
    :cond_4
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1, v0}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setPreparing$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;Z)V

    :cond_5
    const/4 p1, 0x2

    if-eq p3, p1, :cond_7

    if-eq p3, v1, :cond_6

    goto :goto_5

    .line 172
    :cond_6
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 662
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_3
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_9

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/exo/PlayListener;

    .line 173
    invoke-interface {v0}, Lcom/movieboxpro/android/exo/PlayListener;->onComplete()V

    goto :goto_3

    .line 159
    :cond_7
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 660
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_4
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_8

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/exo/PlayListener;

    .line 160
    invoke-interface {v0}, Lcom/movieboxpro/android/exo/PlayListener;->onStart()V

    goto :goto_4

    .line 162
    :cond_8
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setBuffering$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;Z)V

    .line 180
    :cond_9
    :goto_5
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1, p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setLastReportedPlayWhenReady$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;Z)V

    .line 181
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1, p3}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setLastReportedPlaybackState$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;I)V

    return-void
.end method

.method public synthetic onPlaylistMetadataChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaMetadata;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPlaylistMetadataChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/MediaMetadata;)V

    return-void
.end method

.method public synthetic onPositionDiscontinuity(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPositionDiscontinuity(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onPositionDiscontinuity(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Player$PositionInfo;Landroidx/media3/common/Player$PositionInfo;I)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onPositionDiscontinuity(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Player$PositionInfo;Landroidx/media3/common/Player$PositionInfo;I)V

    return-void
.end method

.method public synthetic onRenderedFirstFrame(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Object;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onRenderedFirstFrame(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Object;J)V

    return-void
.end method

.method public synthetic onRendererReadyChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IIZ)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onRendererReadyChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IIZ)V

    return-void
.end method

.method public synthetic onRepeatModeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onRepeatModeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onSeekBackIncrementChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onSeekBackIncrementChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V

    return-void
.end method

.method public synthetic onSeekForwardIncrementChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onSeekForwardIncrementChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;J)V

    return-void
.end method

.method public synthetic onSeekStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V
    .locals 0

    invoke-static {p0, p1}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onSeekStarted(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)V

    return-void
.end method

.method public synthetic onShuffleModeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onShuffleModeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V

    return-void
.end method

.method public synthetic onSkipSilenceEnabledChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onSkipSilenceEnabledChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Z)V

    return-void
.end method

.method public synthetic onSurfaceSizeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;II)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onSurfaceSizeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;II)V

    return-void
.end method

.method public synthetic onTimelineChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onTimelineChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;I)V

    return-void
.end method

.method public synthetic onTrackSelectionParametersChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/TrackSelectionParameters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onTrackSelectionParametersChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/TrackSelectionParameters;)V

    return-void
.end method

.method public onTracksChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Tracks;)V
    .locals 3

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "tracks"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 107
    iget-object v0, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {v0, p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$shouldAcceptTracksEvent(Lcom/movieboxpro/android/exo/CustomExoPlayer;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;)Z

    move-result p1

    if-nez p1, :cond_0

    return-void

    .line 110
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getAudioTracks$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    .line 111
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1, p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setTracks$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;Landroidx/media3/common/Tracks;)V

    .line 112
    invoke-virtual {p2}, Landroidx/media3/common/Tracks;->getGroups()Lcom/google/common/collect/ImmutableList;

    move-result-object p1

    const-string p2, "getGroups(...)"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Ljava/lang/Iterable;

    iget-object p2, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    .line 651
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/media3/common/Tracks$Group;

    .line 113
    invoke-virtual {v0}, Landroidx/media3/common/Tracks$Group;->getType()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    .line 114
    invoke-static {p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getAudioTracks$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 117
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getAudioTracks$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    iget-object p2, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    .line 654
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    const/4 v0, 0x0

    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    add-int/lit8 v2, v0, 0x1

    if-gez v0, :cond_3

    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_3
    check-cast v1, Landroidx/media3/common/Tracks$Group;

    .line 118
    invoke-virtual {v1}, Landroidx/media3/common/Tracks$Group;->isSelected()Z

    move-result v1

    if-eqz v1, :cond_4

    .line 119
    invoke-static {p2, v0}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setCurrSelectAudioIndex$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;I)V

    :cond_4
    move v0, v2

    goto :goto_1

    .line 122
    :cond_5
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$applyPendingVideoQuality(Lcom/movieboxpro/android/exo/CustomExoPlayer;)V

    return-void
.end method

.method public synthetic onUpstreamDiscarded(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/MediaLoadData;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onUpstreamDiscarded(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/source/MediaLoadData;)V

    return-void
.end method

.method public synthetic onVideoCodecError(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoCodecError(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/Exception;)V

    return-void
.end method

.method public synthetic onVideoDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;J)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;J)V

    return-void
.end method

.method public synthetic onVideoDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;JJ)V
    .locals 0

    invoke-static/range {p0 .. p6}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoDecoderInitialized(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;JJ)V

    return-void
.end method

.method public synthetic onVideoDecoderReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoDecoderReleased(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Ljava/lang/String;)V

    return-void
.end method

.method public synthetic onVideoDisabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoDisabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V

    return-void
.end method

.method public synthetic onVideoEnabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoEnabled(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/exoplayer/DecoderCounters;)V

    return-void
.end method

.method public synthetic onVideoFrameProcessingOffset(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;JI)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoFrameProcessingOffset(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;JI)V

    return-void
.end method

.method public synthetic onVideoInputFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Format;Landroidx/media3/exoplayer/DecoderReuseEvaluation;)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoInputFormatChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/Format;Landroidx/media3/exoplayer/DecoderReuseEvaluation;)V

    return-void
.end method

.method public synthetic onVideoSizeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IIIF)V
    .locals 0

    invoke-static/range {p0 .. p5}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVideoSizeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;IIIF)V

    return-void
.end method

.method public onVideoSizeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;Landroidx/media3/common/VideoSize;)V
    .locals 3

    const-string v0, "eventTime"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string p1, "videoSize"

    invoke-static {p2, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 188
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    iget v0, p2, Landroidx/media3/common/VideoSize;->height:I

    invoke-static {p1, v0}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setHeight$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;I)V

    .line 189
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    iget p2, p2, Landroidx/media3/common/VideoSize;->width:I

    invoke-static {p1, p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$setWidth$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;I)V

    .line 190
    iget-object p1, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getListeners$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)Ljava/util/ArrayList;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    iget-object p2, p0, Lcom/movieboxpro/android/exo/CustomExoPlayer$initPlayer$1;->this$0:Lcom/movieboxpro/android/exo/CustomExoPlayer;

    .line 664
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/exo/PlayListener;

    .line 191
    invoke-static {p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getWidth$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)I

    move-result v1

    invoke-static {p2}, Lcom/movieboxpro/android/exo/CustomExoPlayer;->access$getHeight$p(Lcom/movieboxpro/android/exo/CustomExoPlayer;)I

    move-result v2

    invoke-interface {v0, v1, v2}, Lcom/movieboxpro/android/exo/PlayListener;->onVideoSizeChanged(II)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public synthetic onVolumeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;F)V
    .locals 0

    invoke-static {p0, p1, p2}, Landroidx/media3/exoplayer/analytics/AnalyticsListener$-CC;->$default$onVolumeChanged(Landroidx/media3/exoplayer/analytics/AnalyticsListener;Landroidx/media3/exoplayer/analytics/AnalyticsListener$EventTime;F)V

    return-void
.end method
