.class public final Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;
.super Ljava/lang/Object;
.source "VlcPlaybackRecovery.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;,
        Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u0008\n\u0002\u0010\u0008\n\u0002\u0008\u000f\u0008\u0000\u0018\u00002\u00020\u0001:\u0002%&BI\u0012\u001e\u0010\u0002\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0012\u0010\u0008\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\t\u0012\u000c\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0004\u0008\u000c\u0010\rJ\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020\u000fJ\u0006\u0010!\u001a\u00020\u0006J\u0008\u0010\"\u001a\u0004\u0018\u00010\nJ\u0008\u0010#\u001a\u00020\u0006H\u0002J\u0008\u0010$\u001a\u00020\u0006H\u0002R&\u0010\u0002\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0008\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"
    }
    d2 = {
        "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;",
        "",
        "schedule",
        "Lkotlin/Function2;",
        "",
        "Lkotlin/Function0;",
        "",
        "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;",
        "reopen",
        "Lkotlin/Function1;",
        "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;",
        "onExhausted",
        "<init>",
        "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V",
        "value",
        "",
        "isExhausted",
        "()Z",
        "source",
        "pending",
        "generation",
        "active",
        "needsRecovery",
        "attempts",
        "",
        "lastPositionMs",
        "continuousProgressMs",
        "startSource",
        "onPrematureEnd",
        "positionMs",
        "onProgress",
        "pause",
        "resume",
        "stop",
        "sourceForRetry",
        "scheduleRecovery",
        "cancelPending",
        "Source",
        "Cancellation",
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
.field private active:Z

.field private attempts:I

.field private continuousProgressMs:J

.field private generation:J

.field private volatile isExhausted:Z

.field private lastPositionMs:J

.field private needsRecovery:Z

.field private final onExhausted:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

.field private final reopen:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private final schedule:Lkotlin/jvm/functions/Function2;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function2<",
            "Ljava/lang/Long;",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;",
            "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;",
            ">;"
        }
    .end annotation
.end field

.field private source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;


# direct methods
.method public static synthetic $r8$lambda$KsufWLb39IQOiCjlHMcWYVnC3-g(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;
    .locals 0

    invoke-static {p0, p1, p2, p3}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->scheduleRecovery$lambda$1(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function2<",
            "-",
            "Ljava/lang/Long;",
            "-",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;+",
            "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;",
            ">;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;",
            "Lkotlin/Unit;",
            ">;",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    const-string v0, "schedule"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "reopen"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "onExhausted"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 4
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 5
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->schedule:Lkotlin/jvm/functions/Function2;

    .line 6
    iput-object p2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->reopen:Lkotlin/jvm/functions/Function1;

    .line 7
    iput-object p3, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->onExhausted:Lkotlin/jvm/functions/Function0;

    return-void
.end method

.method private final cancelPending()V
    .locals 4

    .line 119
    iget-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    .line 120
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;->cancel()V

    :cond_0
    const/4 v0, 0x0

    .line 121
    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    return-void
.end method

.method private final scheduleRecovery()V
    .locals 9

    .line 95
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    if-nez v0, :cond_0

    return-void

    .line 96
    :cond_0
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    if-eqz v1, :cond_1

    return-void

    .line 97
    :cond_1
    iget v1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    const/4 v2, 0x2

    if-lt v1, v2, :cond_2

    const/4 v0, 0x1

    .line 98
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    const/4 v0, 0x0

    .line 99
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    .line 100
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    .line 101
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->onExhausted:Lkotlin/jvm/functions/Function0;

    invoke-interface {v0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void

    .line 104
    :cond_2
    iget-wide v1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    const-wide/16 v3, 0x1

    add-long v7, v1, v3

    iput-wide v7, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    .line 105
    iget-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    const/4 v5, 0x5

    const/4 v6, 0x0

    const/4 v1, 0x0

    const/4 v4, 0x0

    invoke-static/range {v0 .. v6}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->copy$default(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;Ljava/lang/String;JZILjava/lang/Object;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    move-result-object v0

    .line 106
    iget-object v1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->schedule:Lkotlin/jvm/functions/Function2;

    const-wide/16 v2, 0x3e8

    iget v4, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    shl-long/2addr v2, v4

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    new-instance v3, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$$ExternalSyntheticLambda0;

    invoke-direct {v3, p0, v7, v8, v0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$$ExternalSyntheticLambda0;-><init>(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V

    invoke-interface {v1, v2, v3}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    return-void
.end method

.method private static final scheduleRecovery$lambda$1(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;JLcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)Lkotlin/Unit;
    .locals 3

    .line 107
    monitor-enter p0

    .line 108
    :try_start_0
    iget-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    cmp-long v2, p1, v0

    if-nez v2, :cond_1

    iget-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    if-eqz p1, :cond_1

    iget-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    .line 109
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    .line 110
    iget-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    const-wide/16 v0, 0x1

    add-long/2addr p1, v0

    iput-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->generation:J

    .line 111
    iget p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    const/4 p1, 0x0

    .line 112
    iput-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    .line 113
    iget-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->reopen:Lkotlin/jvm/functions/Function1;

    invoke-interface {p1, p3}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    .line 114
    :cond_1
    :goto_0
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 107
    monitor-exit p0

    .line 115
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :catchall_0
    move-exception p1

    .line 107
    monitor-exit p0

    throw p1
.end method


# virtual methods
.method public final isExhausted()Z
    .locals 1

    .line 15
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    return v0
.end method

.method public final declared-synchronized onPrematureEnd(J)V
    .locals 2

    monitor-enter p0

    .line 42
    :try_start_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pending:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x0

    .line 43
    invoke-static {p1, p2, v0, v1}, Lkotlin/ranges/RangesKt;->coerceAtLeast(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    .line 44
    iput-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J

    const/4 p1, 0x1

    .line 45
    iput-boolean p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    .line 46
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->scheduleRecovery()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 47
    monitor-exit p0

    return-void

    .line 42
    :cond_1
    :goto_0
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public final declared-synchronized onProgress(J)V
    .locals 5

    monitor-enter p0

    .line 51
    :try_start_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    if-eqz v0, :cond_4

    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    if-nez v0, :cond_4

    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    if-eqz v0, :cond_0

    goto :goto_1

    .line 52
    :cond_0
    iget-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    sub-long v0, p1, v0

    .line 53
    iput-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    const-wide/16 p1, 0x1

    const-wide/16 v2, 0x0

    cmp-long v4, p1, v0

    if-gtz v4, :cond_1

    const-wide/16 p1, 0x1389

    cmp-long v4, v0, p1

    if-gez v4, :cond_1

    .line 56
    iget-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J

    add-long/2addr p1, v0

    iput-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J

    goto :goto_0

    :cond_1
    cmp-long p1, v0, v2

    if-eqz p1, :cond_2

    .line 58
    iput-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J

    .line 60
    :cond_2
    :goto_0
    iget-wide p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J

    const-wide/16 v0, 0x7530

    cmp-long v4, p1, v0

    if-ltz v4, :cond_3

    const/4 p1, 0x0

    .line 61
    iput p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    .line 62
    iput-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 64
    :cond_3
    monitor-exit p0

    return-void

    .line 51
    :cond_4
    :goto_1
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public final declared-synchronized pause()V
    .locals 1

    monitor-enter p0

    .line 68
    :try_start_0
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->cancelPending()V

    const/4 v0, 0x0

    .line 69
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 70
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public final declared-synchronized resume()Z
    .locals 3

    monitor-enter p0

    .line 75
    :try_start_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    monitor-exit p0

    return v1

    :cond_0
    const/4 v0, 0x1

    .line 76
    :try_start_1
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    .line 77
    iget-boolean v2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    if-eqz v2, :cond_1

    .line 78
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->scheduleRecovery()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 79
    monitor-exit p0

    return v1

    .line 81
    :cond_1
    monitor-exit p0

    return v0

    :catchall_0
    move-exception v0

    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method public final declared-synchronized sourceForRetry()Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;
    .locals 7

    monitor-enter p0

    .line 92
    :try_start_0
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    if-eqz v0, :cond_0

    iget-wide v2, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    const/4 v5, 0x5

    const/4 v6, 0x0

    const/4 v1, 0x0

    const/4 v4, 0x0

    invoke-static/range {v0 .. v6}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->copy$default(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;Ljava/lang/String;JZILjava/lang/Object;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public final declared-synchronized startSource(Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;)V
    .locals 2

    monitor-enter p0

    :try_start_0
    const-string v0, "source"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 30
    invoke-direct {p0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->cancelPending()V

    .line 31
    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;

    const/4 v0, 0x1

    .line 32
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->active:Z

    const/4 v0, 0x0

    .line 33
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    .line 34
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->isExhausted:Z

    .line 35
    iput v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->attempts:I

    .line 36
    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;->getPositionMs()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->lastPositionMs:J

    const-wide/16 v0, 0x0

    .line 37
    iput-wide v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->continuousProgressMs:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 38
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public final declared-synchronized stop()V
    .locals 1

    monitor-enter p0

    .line 86
    :try_start_0
    invoke-virtual {p0}, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->pause()V

    const/4 v0, 0x0

    .line 87
    iput-boolean v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->needsRecovery:Z

    const/4 v0, 0x0

    .line 88
    iput-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;->source:Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 89
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method
