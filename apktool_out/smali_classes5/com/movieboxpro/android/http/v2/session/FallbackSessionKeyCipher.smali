.class public final Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;
.super Ljava/lang/Object;
.source "FallbackSessionKeyCipher.kt"

# interfaces
.implements Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\u0008\u0004\u0018\u00002\u00020\u0001B-\u0008\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0008\u0008\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\u0010\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"
    }
    d2 = {
        "Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;",
        "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;",
        "keystore",
        "software",
        "evictKeystore",
        "Ljava/lang/Runnable;",
        "onKeystoreFailure",
        "Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;",
        "<init>",
        "(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;)V",
        "keystoreUnavailable",
        "",
        "encrypt",
        "",
        "value",
        "decrypt",
        "encryptWithSoftware",
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
.field private final evictKeystore:Ljava/lang/Runnable;

.field private final keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

.field private volatile keystoreUnavailable:Z

.field private final onKeystoreFailure:Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;

.field private final software:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;


# direct methods
.method public static synthetic $r8$lambda$AfAKTWKX2Hp4jDj3AcpvHMWfOKU()V
    .locals 0

    invoke-static {}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->_init_$lambda$0()V

    return-void
.end method

.method public static synthetic $r8$lambda$Df0Iu5mQSy8czmAikc27yAuRji0(Ljava/lang/Throwable;)V
    .locals 0

    invoke-static {p0}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->_init_$lambda$1(Ljava/lang/Throwable;)V

    return-void
.end method

.method public constructor <init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;)V
    .locals 8

    const-string v0, "keystore"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "software"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/16 v6, 0xc

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    invoke-direct/range {v1 .. v7}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;)V
    .locals 8

    const-string v0, "keystore"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "software"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "evictKeystore"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    invoke-direct/range {v1 .. v7}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;)V
    .locals 1

    const-string v0, "keystore"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "software"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "evictKeystore"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "onKeystoreFailure"

    invoke-static {p4, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 8
    iput-object p1, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    .line 9
    iput-object p2, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->software:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    .line 10
    iput-object p3, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->evictKeystore:Ljava/lang/Runnable;

    .line 11
    iput-object p4, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->onKeystoreFailure:Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p6, p5, 0x4

    if-eqz p6, :cond_0

    .line 10
    new-instance p3, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher$$ExternalSyntheticLambda0;

    invoke-direct {p3}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher$$ExternalSyntheticLambda0;-><init>()V

    :cond_0
    and-int/lit8 p5, p5, 0x8

    if-eqz p5, :cond_1

    .line 11
    new-instance p4, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher$$ExternalSyntheticLambda1;

    invoke-direct {p4}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher$$ExternalSyntheticLambda1;-><init>()V

    .line 7
    :cond_1
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;)V

    return-void
.end method

.method private static final _init_$lambda$0()V
    .locals 0

    return-void
.end method

.method private static final _init_$lambda$1(Ljava/lang/Throwable;)V
    .locals 1

    const-string v0, "it"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    return-void
.end method

.method private final encryptWithSoftware([B)[B
    .locals 2

    .line 51
    :try_start_0
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->software:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->encrypt([B)[B

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception p1

    .line 55
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Software session wrap failed"

    check-cast p1, Ljava/lang/Throwable;

    invoke-direct {v0, v1, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v0

    :catch_1
    move-exception p1

    .line 53
    throw p1
.end method


# virtual methods
.method public decrypt([B)[B
    .locals 2

    const-string v0, "value"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 39
    array-length v0, p1

    const/4 v1, 0x0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    if-nez v0, :cond_1

    aget-byte v0, p1, v1

    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 41
    :try_start_0
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->software:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->decrypt([B)[B

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    .line 43
    :catch_0
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->decrypt([B)[B

    move-result-object p1

    :goto_1
    return-object p1

    .line 46
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->decrypt([B)[B

    move-result-object p1

    return-object p1
.end method

.method public encrypt([B)[B
    .locals 2

    const-string v0, "value"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 17
    iget-boolean v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystoreUnavailable:Z

    if-nez v0, :cond_0

    .line 19
    :try_start_0
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->encrypt([B)[B

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception v0

    .line 21
    iget-object v1, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->onKeystoreFailure:Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;

    check-cast v0, Ljava/lang/Throwable;

    invoke-interface {v1, v0}, Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;->onFailure(Ljava/lang/Throwable;)V

    .line 23
    :try_start_1
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->evictKeystore:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_0

    :catch_1
    move-exception v0

    .line 25
    iget-object v1, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->onKeystoreFailure:Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;

    check-cast v0, Ljava/lang/Throwable;

    invoke-interface {v1, v0}, Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;->onFailure(Ljava/lang/Throwable;)V

    .line 28
    :goto_0
    :try_start_2
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystore:Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;

    invoke-interface {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;->encrypt([B)[B

    move-result-object p1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    return-object p1

    :catch_2
    move-exception v0

    .line 30
    iget-object v1, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->onKeystoreFailure:Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;

    check-cast v0, Ljava/lang/Throwable;

    invoke-interface {v1, v0}, Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;->onFailure(Ljava/lang/Throwable;)V

    const/4 v0, 0x1

    .line 31
    iput-boolean v0, p0, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->keystoreUnavailable:Z

    .line 35
    :cond_0
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;->encryptWithSoftware([B)[B

    move-result-object p1

    return-object p1
.end method
