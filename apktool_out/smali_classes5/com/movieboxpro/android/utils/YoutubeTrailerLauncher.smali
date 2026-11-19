.class public final Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;
.super Ljava/lang/Object;
.source "YoutubeTrailerLauncher.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nYoutubeTrailerLauncher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YoutubeTrailerLauncher.kt\ncom/movieboxpro/android/utils/YoutubeTrailerLauncher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,52:1\n1#2:53\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0004\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u001c\u0010\t\u001a\u0004\u0018\u00010\u00062\u0008\u0010\n\u001a\u0004\u0018\u00010\u00062\u0008\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0014\u0010\u000c\u001a\u0008\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\n\u001a\u00020\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"
    }
    d2 = {
        "Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;",
        "",
        "<init>",
        "()V",
        "youtubePackages",
        "",
        "",
        "videoIdInUrl",
        "Lkotlin/text/Regex;",
        "resolveVideoId",
        "videoId",
        "url",
        "launchTargets",
        "Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;",
        "extractVideoId",
        "text",
        "Target",
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
.field public static final INSTANCE:Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;

.field private static final videoIdInUrl:Lkotlin/text/Regex;

.field private static final youtubePackages:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;

    invoke-direct {v0}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;-><init>()V

    sput-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->INSTANCE:Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;

    .line 12
    const-string v0, "com.google.android.youtube"

    .line 13
    const-string v1, "com.amazon.firetv.youtube"

    const-string v2, "com.google.android.youtube.tv"

    const-string v3, "com.google.android.youtube.googletv"

    filled-new-array {v2, v3, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    .line 9
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->listOf([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->youtubePackages:Ljava/util/List;

    .line 16
    new-instance v0, Lkotlin/text/Regex;

    .line 17
    const-string v1, "(?:[?&]v=|/embed/|/shorts/|/live/|youtu\\.be/)([A-Za-z0-9_-]{6,})"

    .line 16
    invoke-direct {v0, v1}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->videoIdInUrl:Lkotlin/text/Regex;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private final extractVideoId(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 49
    sget-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->videoIdInUrl:Lkotlin/text/Regex;

    check-cast p1, Ljava/lang/CharSequence;

    const/4 v1, 0x0

    const/4 v2, 0x2

    const/4 v3, 0x0

    invoke-static {v0, p1, v1, v2, v3}, Lkotlin/text/Regex;->find$default(Lkotlin/text/Regex;Ljava/lang/CharSequence;IILjava/lang/Object;)Lkotlin/text/MatchResult;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-interface {p1}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_0

    const/4 v0, 0x1

    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    move-object v3, p1

    check-cast v3, Ljava/lang/String;

    :cond_0
    return-object v3
.end method


# virtual methods
.method public final launchTargets(Ljava/lang/String;)Ljava/util/List;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;",
            ">;"
        }
    .end annotation

    const-string v0, "videoId"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 36
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "vnd.youtube://"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 37
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "https://www.youtube.com/watch?v="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 38
    new-instance v1, Ljava/util/ArrayList;

    sget-object v2, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->youtubePackages:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x2

    mul-int/lit8 v3, v3, 0x2

    add-int/2addr v3, v4

    invoke-direct {v1, v3}, Ljava/util/ArrayList;-><init>(I)V

    .line 39
    invoke-interface {v2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 40
    new-instance v5, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;

    invoke-direct {v5, p1, v3}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 41
    new-instance v5, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;

    invoke-direct {v5, v0, v3}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 43
    :cond_0
    new-instance v2, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;

    const/4 v3, 0x0

    invoke-direct {v2, p1, v3, v4, v3}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;-><init>(Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 44
    new-instance p1, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;

    invoke-direct {p1, v0, v3, v4, v3}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;-><init>(Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 45
    check-cast v1, Ljava/util/List;

    return-object v1
.end method

.method public final resolveVideoId(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 6

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    .line 21
    check-cast p1, Ljava/lang/CharSequence;

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    move-object p1, v0

    :goto_0
    const-string v1, ""

    if-nez p1, :cond_1

    move-object p1, v1

    .line 22
    :cond_1
    move-object v2, p1

    check-cast v2, Ljava/lang/CharSequence;

    invoke-interface {v2}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-lez v3, :cond_3

    .line 23
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->extractVideoId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_2

    return-object v3

    :cond_2
    const/16 v3, 0x2f

    const/4 v4, 0x0

    const/4 v5, 0x2

    .line 24
    invoke-static {v2, v3, v4, v5, v0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    const/16 v3, 0x3f

    invoke-static {v2, v3, v4, v5, v0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    const/16 v3, 0x3a

    invoke-static {v2, v3, v4, v5, v0}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;CZILjava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_3

    return-object p1

    :cond_3
    if-eqz p2, :cond_4

    .line 28
    check-cast p2, Ljava/lang/CharSequence;

    invoke-static {p2}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_4
    move-object p1, v0

    :goto_1
    if-nez p1, :cond_5

    goto :goto_2

    :cond_5
    move-object v1, p1

    .line 29
    :goto_2
    move-object p1, v1

    check-cast p1, Ljava/lang/CharSequence;

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p1

    if-lez p1, :cond_6

    .line 30
    invoke-direct {p0, v1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->extractVideoId(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_6
    return-object v0
.end method
