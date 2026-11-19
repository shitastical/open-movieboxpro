.class final Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "VlcPlayer.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/movieboxpro/android/utils/VlcPlayer;->openSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lkotlinx/coroutines/CoroutineScope;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"
    }
    d2 = {
        "<anonymous>",
        "",
        "Lkotlinx/coroutines/CoroutineScope;"
    }
    k = 0x3
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.movieboxpro.android.utils.VlcPlayer$openSource$1"
    f = "VlcPlayer.kt"
    i = {}
    l = {}
    m = "invokeSuspend"
    n = {}
    s = {}
.end annotation


# instance fields
.field final synthetic $generation:J

.field final synthetic $source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

.field label:I

.field final synthetic this$0:Lcom/movieboxpro/android/utils/VlcPlayer;


# direct methods
.method constructor <init>(Lcom/movieboxpro/android/utils/VlcPlayer;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;Lkotlin/coroutines/Continuation;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/movieboxpro/android/utils/VlcPlayer;",
            "J",
            "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->this$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    iput-wide p2, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$generation:J

    iput-object p4, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p5}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance p1, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;

    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->this$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    iget-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$generation:J

    iget-object v4, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    move-object v0, p1

    move-object v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;-><init>(Lcom/movieboxpro/android/utils/VlcPlayer;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;Lkotlin/coroutines/Continuation;)V

    check-cast p1, Lkotlin/coroutines/Continuation;

    return-object p1
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method

.method public final invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlinx/coroutines/CoroutineScope;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    invoke-virtual {p0, p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;

    sget-object p2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p1, p2}, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 12

    const-string v0, ":start-time="

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 285
    iget v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->label:I

    if-nez v1, :cond_8

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 286
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->this$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    invoke-static {p1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaLock$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/lang/Object;

    move-result-object p1

    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->this$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    iget-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$generation:J

    iget-object v4, p0, Lcom/movieboxpro/android/utils/VlcPlayer$openSource$1;->$source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    monitor-enter p1

    .line 287
    :try_start_0
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getReleased$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Z

    move-result v5

    if-nez v5, :cond_7

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getLoadGeneration$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/util/concurrent/atomic/AtomicLong;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide v5

    cmp-long v7, v2, v5

    if-eqz v7, :cond_0

    goto/16 :goto_2

    .line 288
    :cond_0
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v5

    invoke-virtual {v5}, Lorg/videolan/libvlc/MediaPlayer;->hasMedia()Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v5

    invoke-virtual {v5}, Lorg/videolan/libvlc/MediaPlayer;->stop()V

    .line 289
    :cond_1
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v5

    const/4 v6, 0x1

    invoke-virtual {v5, v6}, Lorg/videolan/libvlc/MediaPlayer;->setAudioDigitalOutputEnabled(Z)Z

    .line 290
    invoke-virtual {v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getUseSurfaceView()Z

    move-result v5

    invoke-static {v1, v5}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$setUseSurfaceView$p(Lcom/movieboxpro/android/utils/VlcPlayer;Z)V

    .line 291
    invoke-virtual {v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPath()Ljava/lang/String;

    move-result-object v5

    const-string v6, "http"

    const/4 v7, 0x2

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static {v5, v6, v9, v7, v8}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 292
    new-instance v5, Lorg/videolan/libvlc/Media;

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v6

    invoke-virtual {v6}, Lorg/videolan/libvlc/MediaPlayer;->getLibVLC()Lorg/videolan/libvlc/interfaces/ILibVLC;

    move-result-object v6

    invoke-virtual {v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPath()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v7

    invoke-direct {v5, v6, v7}, Lorg/videolan/libvlc/Media;-><init>(Lorg/videolan/libvlc/interfaces/ILibVLC;Landroid/net/Uri;)V

    goto :goto_0

    .line 294
    :cond_2
    new-instance v5, Lorg/videolan/libvlc/Media;

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v6

    invoke-virtual {v6}, Lorg/videolan/libvlc/MediaPlayer;->getLibVLC()Lorg/videolan/libvlc/interfaces/ILibVLC;

    move-result-object v6

    invoke-virtual {v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPath()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v5, v6, v7}, Lorg/videolan/libvlc/Media;-><init>(Lorg/videolan/libvlc/interfaces/ILibVLC;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 297
    :goto_0
    :try_start_1
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getReleased$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Z

    move-result v6

    if-nez v6, :cond_6

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getLoadGeneration$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/util/concurrent/atomic/AtomicLong;

    move-result-object v6

    invoke-virtual {v6}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide v6

    cmp-long v8, v2, v6

    if-eqz v8, :cond_3

    goto :goto_1

    .line 298
    :cond_3
    invoke-virtual {v4}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPositionMs()J

    move-result-wide v6

    const-wide/16 v10, 0x3e8

    div-long/2addr v6, v10

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v5, v0}, Lorg/videolan/libvlc/Media;->addOption(Ljava/lang/String;)V

    .line 299
    sget-object v0, Lcom/movieboxpro/android/utils/SettingManager;->INSTANCE:Lcom/movieboxpro/android/utils/SettingManager;

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/SettingManager;->isHwDecoder()Z

    move-result v0

    invoke-virtual {v5, v0, v9}, Lorg/videolan/libvlc/Media;->setHWDecoderEnabled(ZZ)V

    .line 300
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object v0

    if-eqz v0, :cond_4

    invoke-interface {v0}, Lorg/videolan/libvlc/interfaces/IMedia;->release()V

    .line 301
    :cond_4
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v0

    move-object v4, v5

    check-cast v4, Lorg/videolan/libvlc/interfaces/IMedia;

    invoke-virtual {v0, v4}, Lorg/videolan/libvlc/MediaPlayer;->setMedia(Lorg/videolan/libvlc/interfaces/IMedia;)V

    .line 302
    invoke-static {v1, v2, v3}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$bindEventListener(Lcom/movieboxpro/android/utils/VlcPlayer;J)V

    .line 303
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getPlayRequested$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getLoadGeneration$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Ljava/util/concurrent/atomic/AtomicLong;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicLong;->get()J

    move-result-wide v6

    cmp-long v0, v2, v6

    if-nez v0, :cond_5

    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$checkAvailable(Lcom/movieboxpro/android/utils/VlcPlayer;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 304
    invoke-static {v1}, Lcom/movieboxpro/android/utils/VlcPlayer;->access$getMediaPlayer$p(Lcom/movieboxpro/android/utils/VlcPlayer;)Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->play()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 307
    :cond_5
    :try_start_2
    invoke-virtual {v5}, Lorg/videolan/libvlc/Media;->release()V

    .line 309
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 286
    monitor-exit p1

    .line 310
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p1

    .line 297
    :cond_6
    :goto_1
    :try_start_3
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 307
    :try_start_4
    invoke-virtual {v5}, Lorg/videolan/libvlc/Media;->release()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 297
    monitor-exit p1

    return-object v0

    :catchall_0
    move-exception v0

    .line 307
    :try_start_5
    invoke-virtual {v5}, Lorg/videolan/libvlc/Media;->release()V

    throw v0

    .line 287
    :cond_7
    :goto_2
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    monitor-exit p1

    return-object v0

    :catchall_1
    move-exception v0

    .line 286
    monitor-exit p1

    throw v0

    .line 285
    :cond_8
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method
