.class public Lcom/movieboxpro/android/model/TvShowsUpdateModel;
.super Ljava/lang/Object;
.source "TvShowsUpdateModel.java"


# instance fields
.field private banner_mini:Ljava/lang/String;

.field private cats:Ljava/lang/String;

.field private id:Ljava/lang/String;

.field private poster:Ljava/lang/String;

.field private season_episode:Ljava/lang/String;

.field private title:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getBanner_mini()Ljava/lang/String;
    .locals 1

    .line 23
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->banner_mini:Ljava/lang/String;

    return-object v0
.end method

.method public getCats()Ljava/lang/String;
    .locals 1

    .line 47
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->cats:Ljava/lang/String;

    return-object v0
.end method

.method public getId()Ljava/lang/String;
    .locals 1

    .line 15
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->id:Ljava/lang/String;

    return-object v0
.end method

.method public getPoster()Ljava/lang/String;
    .locals 1

    .line 31
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->poster:Ljava/lang/String;

    return-object v0
.end method

.method public getSeason_episode()Ljava/lang/String;
    .locals 1

    .line 55
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->season_episode:Ljava/lang/String;

    return-object v0
.end method

.method public getTitle()Ljava/lang/String;
    .locals 1

    .line 39
    iget-object v0, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->title:Ljava/lang/String;

    return-object v0
.end method

.method public setBanner_mini(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "banner_mini"
        }
    .end annotation

    .line 27
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->banner_mini:Ljava/lang/String;

    return-void
.end method

.method public setCats(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "cats"
        }
    .end annotation

    .line 51
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->cats:Ljava/lang/String;

    return-void
.end method

.method public setId(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "id"
        }
    .end annotation

    .line 19
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->id:Ljava/lang/String;

    return-void
.end method

.method public setPoster(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "poster"
        }
    .end annotation

    .line 35
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->poster:Ljava/lang/String;

    return-void
.end method

.method public setSeason_episode(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "season_episode"
        }
    .end annotation

    .line 59
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->season_episode:Ljava/lang/String;

    return-void
.end method

.method public setTitle(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "title"
        }
    .end annotation

    .line 43
    iput-object p1, p0, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->title:Ljava/lang/String;

    return-void
.end method
