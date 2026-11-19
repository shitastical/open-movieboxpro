.class public final Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;
.super Ljava/lang/Object;
.source "SoftwareSessionWrapKeyStore.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSoftwareSessionWrapKeyStore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SoftwareSessionWrapKeyStore.kt\ncom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,54:1\n1#2:55\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0012\n\u0002\u0008\u0003\u0008\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\u0008\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"
    }
    d2 = {
        "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;",
        "",
        "directory",
        "Ljava/io/File;",
        "<init>",
        "(Ljava/io/File;)V",
        "read",
        "",
        "loadOrCreate",
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
.field public static final Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore$Companion;

.field private static final KEY_FILE_LOCK:Ljava/lang/Object;

.field private static final KEY_FILE_NAME:Ljava/lang/String; = "api_v2_session_wrap.key"

.field public static final KEY_SIZE_BYTES:I = 0x20


# instance fields
.field private final directory:Ljava/io/File;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->Companion:Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore$Companion;

    .line 51
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->KEY_FILE_LOCK:Ljava/lang/Object;

    return-void
.end method

.method public constructor <init>(Ljava/io/File;)V
    .locals 1

    const-string v0, "directory"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    return-void
.end method


# virtual methods
.method public final loadOrCreate()[B
    .locals 10

    .line 18
    sget-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->KEY_FILE_LOCK:Ljava/lang/Object;

    monitor-enter v0

    .line 19
    :try_start_0
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    const-string v3, "api_v2_session_wrap.key"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 20
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    const/16 v3, 0x20

    if-eqz v2, :cond_1

    .line 21
    invoke-static {v1}, Lkotlin/io/FilesKt;->readBytes(Ljava/io/File;)[B

    move-result-object v4

    .line 22
    array-length v2, v4

    if-ne v2, v3, :cond_0

    goto :goto_1

    :cond_0
    const/4 v8, 0x6

    const/4 v9, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    .line 23
    invoke-static/range {v4 .. v9}, Lkotlin/collections/ArraysKt;->fill$default([BBIIILjava/lang/Object;)V

    .line 28
    :cond_1
    iget-object v2, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    invoke-virtual {v2}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-nez v2, :cond_3

    iget-object v2, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z

    move-result v2

    if-eqz v2, :cond_2

    goto :goto_0

    :cond_2
    const-string v1, "Cannot create session key directory"

    new-instance v2, Ljava/lang/IllegalStateException;

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v2, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 29
    :cond_3
    :goto_0
    new-array v2, v3, [B

    new-instance v3, Ljava/security/SecureRandom;

    invoke-direct {v3}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v3, v2}, Ljava/security/SecureRandom;->nextBytes([B)V

    .line 30
    new-instance v8, Ljava/io/File;

    iget-object v3, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    const-string v4, "api_v2_session_wrap.key.tmp"

    invoke-direct {v8, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_3

    .line 32
    :try_start_1
    new-instance v3, Ljava/io/FileOutputStream;

    invoke-direct {v3, v8}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    check-cast v3, Ljava/io/Closeable;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_2

    :try_start_2
    move-object v4, v3

    check-cast v4, Ljava/io/FileOutputStream;

    .line 33
    invoke-virtual {v4, v2}, Ljava/io/FileOutputStream;->write([B)V

    .line 34
    invoke-virtual {v4}, Ljava/io/FileOutputStream;->getFD()Ljava/io/FileDescriptor;

    move-result-object v4

    invoke-virtual {v4}, Ljava/io/FileDescriptor;->sync()V

    .line 35
    sget-object v4, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    const/4 v4, 0x0

    .line 32
    :try_start_3
    invoke-static {v3, v4}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 37
    invoke-virtual {v8, v1}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result v1
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    if-eqz v1, :cond_4

    .line 43
    :try_start_4
    invoke-virtual {v8}, Ljava/io/File;->delete()Z
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_3

    move-object v4, v2

    .line 18
    :goto_1
    monitor-exit v0

    return-object v4

    .line 37
    :cond_4
    :try_start_5
    const-string v1, "Cannot persist software session wrap key"

    new-instance v3, Ljava/lang/IllegalStateException;

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v3, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v3
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_0
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    :catchall_0
    move-exception v1

    .line 32
    :try_start_6
    throw v1
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    :catchall_1
    move-exception v4

    :try_start_7
    invoke-static {v3, v1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v4
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_0
    .catchall {:try_start_7 .. :try_end_7} :catchall_2

    :catchall_2
    move-exception v1

    goto :goto_2

    :catch_0
    move-exception v1

    const/4 v6, 0x6

    const/4 v7, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    .line 40
    :try_start_8
    invoke-static/range {v2 .. v7}, Lkotlin/collections/ArraysKt;->fill$default([BBIIILjava/lang/Object;)V

    .line 41
    throw v1
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    .line 43
    :goto_2
    :try_start_9
    invoke-virtual {v8}, Ljava/io/File;->delete()Z

    throw v1
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_3

    :catchall_3
    move-exception v1

    .line 18
    monitor-exit v0

    throw v1
.end method

.method public final read()[B
    .locals 10

    .line 9
    sget-object v0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->KEY_FILE_LOCK:Ljava/lang/Object;

    monitor-enter v0

    .line 10
    :try_start_0
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;->directory:Ljava/io/File;

    const-string v3, "api_v2_session_wrap.key"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-static {v1}, Lkotlin/io/FilesKt;->readBytes(Ljava/io/File;)[B

    move-result-object v4

    .line 11
    array-length v1, v4
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/16 v2, 0x20

    if-ne v1, v2, :cond_0

    .line 9
    monitor-exit v0

    return-object v4

    :cond_0
    const/4 v8, 0x6

    const/4 v9, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    .line 12
    :try_start_1
    invoke-static/range {v4 .. v9}, Lkotlin/collections/ArraysKt;->fill$default([BBIIILjava/lang/Object;)V

    .line 13
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "Invalid software session wrap key file"

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :catchall_0
    move-exception v1

    .line 9
    monitor-exit v0

    throw v1
.end method
