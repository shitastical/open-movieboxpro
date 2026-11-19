.class public final Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;
.super Ljava/lang/Object;
.source "SoftwareSessionKeyCipher.kt"

# interfaces
.implements Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSoftwareSessionKeyCipher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SoftwareSessionKeyCipher.kt\ncom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n1#2:83\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0012\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B,\u0008\u0002\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\u000c\u0008\u0005\u0012\u0008\u0008\u0006\u0012\u0004\u0008\u0008(\u0007\u0012\u0004\u0012\u00020\u00080\u0003\u00a2\u0006\u0004\u0008\t\u0010\nB\u0011\u0008\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u0008\u00a2\u0006\u0004\u0008\t\u0010\u000cJ\u0010\u0010\r\u001a\u00020\u00082\u0006\u0010\u000e\u001a\u00020\u0008H\u0016J\u0010\u0010\u000f\u001a\u00020\u00082\u0006\u0010\u000e\u001a\u00020\u0008H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0004H\u0002R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\u000c\u0008\u0005\u0012\u0008\u0008\u0006\u0012\u0004\u0008\u0008(\u0007\u0012\u0004\u0012\u00020\u00080\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"
    }
    d2 = {
        "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;",
        "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;",
        "wrapKeyProvider",
        "Lkotlin/Function1;",
        "",
        "Lkotlin/ParameterName;",
        "name",
        "forEncryption",
        "",
        "<init>",
        "(Lkotlin/jvm/functions/Function1;)V",
        "wrapKey",
        "([B)V",
        "encrypt",
        "value",
        "decrypt",
        "keySpec",
        "Ljavax/crypto/spec/SecretKeySpec;",
        "Companion",
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


# static fields
.field private static final AES_ALGORITHM:Ljava/lang/String; = "AES"

.field private static final AES_KEY_SIZE:I = 0x20

.field public static final Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;

.field public static final ENVELOPE_VERSION:B = 0x2t

.field private static final GCM_TAG_BITS:I = 0x80

.field private static final TRANSFORMATION:Ljava/lang/String; = "AES/GCM/NoPadding"


# instance fields
.field private final wrapKeyProvider:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Ljava/lang/Boolean;",
            "[B>;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;

    return-void
.end method

.method private constructor <init>(Lkotlin/jvm/functions/Function1;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/Boolean;",
            "[B>;)V"
        }
    .end annotation

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 10
    iput-object p1, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->wrapKeyProvider:Lkotlin/jvm/functions/Function1;

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;-><init>(Lkotlin/jvm/functions/Function1;)V

    return-void
.end method

.method public constructor <init>([B)V
    .locals 1

    const-string v0, "wrapKey"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 13
    sget-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;

    invoke-static {v0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->access$fixedKeyProvider(Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;[B)Lkotlin/jvm/functions/Function1;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;-><init>(Lkotlin/jvm/functions/Function1;)V

    return-void
.end method

.method public static final fromContext(Landroid/content/Context;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;

    invoke-virtual {v0, p0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;->fromContext(Landroid/content/Context;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;

    move-result-object p0

    return-object p0
.end method

.method private final keySpec(Z)Ljavax/crypto/spec/SecretKeySpec;
    .locals 6

    .line 47
    iget-object v0, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->wrapKeyProvider:Lkotlin/jvm/functions/Function1;

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    invoke-interface {v0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v0, p1

    check-cast v0, [B

    .line 49
    :try_start_0
    new-instance p1, Ljavax/crypto/spec/SecretKeySpec;

    const-string v1, "AES"

    invoke-direct {p1, v0, v1}, Ljavax/crypto/spec/SecretKeySpec;-><init>([BLjava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v4, 0x6

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    .line 51
    invoke-static/range {v0 .. v5}, Lkotlin/collections/ArraysKt;->fill$default([BBIIILjava/lang/Object;)V

    return-object p1

    :catchall_0
    move-exception p1

    const/4 v4, 0x6

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v0 .. v5}, Lkotlin/collections/ArraysKt;->fill$default([BBIIILjava/lang/Object;)V

    throw p1
.end method


# virtual methods
.method public decrypt([B)[B
    .locals 6

    const-string v0, "value"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 30
    array-length v0, p1

    const/4 v1, 0x3

    const-string v2, "Invalid AES-GCM session envelope"

    if-lt v0, v1, :cond_2

    const/4 v0, 0x0

    .line 31
    aget-byte v1, p1, v0

    const/4 v3, 0x2

    if-ne v1, v3, :cond_1

    const/4 v1, 0x1

    .line 32
    aget-byte v1, p1, v1

    and-int/lit16 v1, v1, 0xff

    if-lez v1, :cond_0

    .line 33
    array-length v4, p1

    add-int/lit8 v5, v1, 0x2

    if-le v4, v5, :cond_0

    add-int/2addr v1, v3

    .line 34
    invoke-static {p1, v3, v1}, Lkotlin/collections/ArraysKt;->copyOfRange([BII)[B

    move-result-object v2

    .line 35
    array-length v4, p1

    invoke-static {p1, v1, v4}, Lkotlin/collections/ArraysKt;->copyOfRange([BII)[B

    move-result-object p1

    .line 36
    const-string v1, "AES/GCM/NoPadding"

    invoke-static {v1}, Ljavax/crypto/Cipher;->getInstance(Ljava/lang/String;)Ljavax/crypto/Cipher;

    move-result-object v1

    .line 39
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->keySpec(Z)Ljavax/crypto/spec/SecretKeySpec;

    move-result-object v0

    check-cast v0, Ljava/security/Key;

    .line 40
    new-instance v4, Ljavax/crypto/spec/GCMParameterSpec;

    const/16 v5, 0x80

    invoke-direct {v4, v5, v2}, Ljavax/crypto/spec/GCMParameterSpec;-><init>(I[B)V

    check-cast v4, Ljava/security/spec/AlgorithmParameterSpec;

    .line 37
    invoke-virtual {v1, v3, v0, v4}, Ljavax/crypto/Cipher;->init(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V

    .line 42
    invoke-virtual {v1, p1}, Ljavax/crypto/Cipher;->doFinal([B)[B

    move-result-object p1

    .line 36
    const-string v0, "run(...)"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object p1

    .line 33
    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 31
    :cond_1
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "Unsupported session envelope version"

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 30
    :cond_2
    new-instance p1, Ljava/lang/IllegalArgumentException;

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public encrypt([B)[B
    .locals 12

    const-string v0, "value"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 16
    const-string v0, "AES/GCM/NoPadding"

    invoke-static {v0}, Ljavax/crypto/Cipher;->getInstance(Ljava/lang/String;)Ljavax/crypto/Cipher;

    move-result-object v0

    const/4 v1, 0x1

    .line 17
    invoke-direct {p0, v1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->keySpec(Z)Ljavax/crypto/spec/SecretKeySpec;

    move-result-object v2

    check-cast v2, Ljava/security/Key;

    invoke-virtual {v0, v1, v2}, Ljavax/crypto/Cipher;->init(ILjava/security/Key;)V

    .line 18
    invoke-virtual {v0}, Ljavax/crypto/Cipher;->getIV()[B

    move-result-object v2

    .line 19
    array-length v3, v2

    const/16 v4, 0xff

    if-gt v3, v4, :cond_0

    .line 20
    invoke-virtual {v0, p1}, Ljavax/crypto/Cipher;->doFinal([B)[B

    move-result-object p1

    .line 21
    array-length v0, v2

    const/4 v10, 0x2

    add-int/2addr v0, v10

    array-length v3, p1

    add-int/2addr v0, v3

    new-array v0, v0, [B

    const/4 v3, 0x0

    .line 22
    aput-byte v10, v0, v3

    .line 23
    array-length v3, v2

    int-to-byte v3, v3

    aput-byte v3, v0, v1

    .line 24
    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    const/16 v8, 0xc

    const/4 v9, 0x0

    const/4 v5, 0x2

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v3, v2

    move-object v4, v0

    invoke-static/range {v3 .. v9}, Lkotlin/collections/ArraysKt;->copyInto$default([B[BIIIILjava/lang/Object;)[B

    .line 25
    invoke-static {p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    array-length v1, v2

    add-int/lit8 v7, v1, 0x2

    const/16 v10, 0xc

    const/4 v11, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object v5, p1

    move-object v6, v0

    invoke-static/range {v5 .. v11}, Lkotlin/collections/ArraysKt;->copyInto$default([B[BIIIILjava/lang/Object;)[B

    return-object v0

    .line 19
    :cond_0
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "AES-GCM IV is too large"

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method
