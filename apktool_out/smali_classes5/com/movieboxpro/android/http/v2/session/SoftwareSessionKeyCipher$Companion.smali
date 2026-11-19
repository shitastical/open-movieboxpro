.class public final Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;
.super Ljava/lang/Object;
.source "SoftwareSessionKeyCipher.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSoftwareSessionKeyCipher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SoftwareSessionKeyCipher.kt\ncom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n1#2:83\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0012\n\u0002\u0008\u0002\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001b\u0010\u0010\u001a\u00020\r2\u000c\u0010\u0011\u001a\u0008\u0012\u0004\u0012\u00020\u00130\u0012H\u0000\u00a2\u0006\u0002\u0008\u0014J\u001c\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"
    }
    d2 = {
        "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;",
        "",
        "<init>",
        "()V",
        "ENVELOPE_VERSION",
        "",
        "AES_KEY_SIZE",
        "",
        "AES_ALGORITHM",
        "",
        "TRANSFORMATION",
        "GCM_TAG_BITS",
        "fromContext",
        "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;",
        "context",
        "Landroid/content/Context;",
        "fromKeyDirectory",
        "directory",
        "Lkotlin/Function0;",
        "Ljava/io/File;",
        "fromKeyDirectory$app_movie_tvRelease",
        "fixedKeyProvider",
        "Lkotlin/Function1;",
        "",
        "",
        "wrapKey",
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


# direct methods
.method public static synthetic $r8$lambda$8g4ye_4Ad4Ciq5rQOGYU102-wQw([BZ)[B
    .locals 0

    invoke-static {p0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fixedKeyProvider$lambda$3([BZ)[B

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$jj1OfR0U1Uv0Uxttoj8hJOX7NdM(Lkotlin/jvm/functions/Function0;Z)[B
    .locals 0

    invoke-static {p0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fromKeyDirectory$lambda$1(Lkotlin/jvm/functions/Function0;Z)[B

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$vzt695-n5NRD_JM9dSH0hhG6_FA(Landroid/content/Context;)Ljava/io/File;
    .locals 0

    invoke-static {p0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fromContext$lambda$0(Landroid/content/Context;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method private constructor <init>()V
    .locals 0

    .line 55
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;-><init>()V

    return-void
.end method

.method public static final synthetic access$fixedKeyProvider(Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;[B)Lkotlin/jvm/functions/Function1;
    .locals 0

    .line 55
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fixedKeyProvider([B)Lkotlin/jvm/functions/Function1;

    move-result-object p0

    return-object p0
.end method

.method private final fixedKeyProvider([B)Lkotlin/jvm/functions/Function1;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([B)",
            "Lkotlin/jvm/functions/Function1<",
            "Ljava/lang/Boolean;",
            "[B>;"
        }
    .end annotation

    .line 76
    array-length v0, p1

    invoke-static {p1, v0}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object p1

    const-string v0, "copyOf(...)"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 77
    array-length v0, p1

    const/16 v1, 0x20

    if-ne v0, v1, :cond_0

    .line 78
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda0;

    invoke-direct {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda0;-><init>([B)V

    return-object v0

    .line 77
    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "Software session wrap key must be 32 bytes"

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method private static final fixedKeyProvider$lambda$3([BZ)[B
    .locals 0

    .line 78
    array-length p1, p0

    invoke-static {p0, p1}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object p0

    const-string p1, "copyOf(...)"

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object p0
.end method

.method private static final fromContext$lambda$0(Landroid/content/Context;)Ljava/io/File;
    .locals 1

    .line 66
    invoke-virtual {p0}, Landroid/content/Context;->getNoBackupFilesDir()Ljava/io/File;

    move-result-object p0

    const-string v0, "getNoBackupFilesDir(...)"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object p0
.end method

.method private static final fromKeyDirectory$lambda$1(Lkotlin/jvm/functions/Function0;Z)[B
    .locals 1

    .line 71
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;-><init>(Ljava/io/File;)V

    if-eqz p1, :cond_0

    .line 72
    invoke-virtual {v0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->loadOrCreate()[B

    move-result-object p0

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->read()[B

    move-result-object p0

    :goto_0
    return-object p0
.end method


# virtual methods
.method public final fromContext(Landroid/content/Context;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    const-string v0, "context"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 64
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    .line 66
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda1;-><init>(Landroid/content/Context;)V

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fromKeyDirectory$app_movie_tvRelease(Lkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;

    move-result-object p1

    return-object p1
.end method

.method public final fromKeyDirectory$app_movie_tvRelease(Lkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function0<",
            "+",
            "Ljava/io/File;",
            ">;)",
            "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;"
        }
    .end annotation

    const-string v0, "directory"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 70
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;

    new-instance v1, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda2;

    invoke-direct {v1, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda2;-><init>(Lkotlin/jvm/functions/Function0;)V

    const/4 p1, 0x0

    invoke-direct {v0, v1, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;-><init>(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object v0
.end method
