.class public final Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;
.super Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;
.source "MovieDetailRelatedFragment.kt"

# interfaces
.implements Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedContract$View;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;,
        Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment<",
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;",
        ">;",
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedContract$View;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nMovieDetailRelatedFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovieDetailRelatedFragment.kt\ncom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment\n+ 2 RxSubscribers.kt\ncom/movieboxpro/android/utils/RxSubscribersKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,278:1\n76#2:279\n88#2:280\n1#3:281\n1869#4,2:282\n*S KotlinDebug\n*F\n+ 1 MovieDetailRelatedFragment.kt\ncom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment\n*L\n161#1:279\n161#1:280\n194#1:282,2\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0010\u000e\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\u0018\u0000 -2\u0008\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002,-B\u0007\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000c\u001a\u00020\rJ\u0008\u0010\u0010\u001a\u00020\u0011H\u0014J\u001a\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0008\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0008\u0010\u0017\u001a\u00020\u000fH\u0016J\u0008\u0010\u0018\u001a\u00020\u000fH\u0002J\u0008\u0010\u0019\u001a\u00020\u000fH\u0002J\u001c\u0010\u001a\u001a\u00020\t2\u0008\u0010\u001b\u001a\u0004\u0018\u00010\u00142\u0008\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0002J\u0008\u0010\u001d\u001a\u00020\u000fH\u0014J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0008\u0010!\u001a\u00020\u000fH\u0014J\u0008\u0010\"\u001a\u00020\u000fH\u0014J\u0008\u0010#\u001a\u00020\u000fH\u0014J\u0008\u0010$\u001a\u00020\u0002H\u0014J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\'H\u0002J\u0018\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020 H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000c\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."
    }
    d2 = {
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;",
        "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;",
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;",
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedContract$View;",
        "<init>",
        "()V",
        "rowAdapter",
        "Landroidx/leanback/widget/ArrayObjectAdapter;",
        "enteredFromTop",
        "",
        "globalFocusChangeListener",
        "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;",
        "focusListener",
        "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;",
        "setFocusListener",
        "",
        "initBackgroundColor",
        "",
        "onViewCreated",
        "view",
        "Landroid/view/View;",
        "savedInstanceState",
        "Landroid/os/Bundle;",
        "onDestroyView",
        "registerGlobalFocusTracker",
        "unRegisterGlobalFocusTracker",
        "isDescendantOf",
        "child",
        "parent",
        "initListener",
        "getActorList",
        "id",
        "",
        "initData",
        "initView",
        "loadData",
        "bindPresenter",
        "playTrailer",
        "item",
        "Lcom/movieboxpro/android/model/TrailerItem;",
        "launchYoutubeApp",
        "context",
        "Landroid/content/Context;",
        "videoId",
        "OnFocusListener",
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
.field public static final Companion:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;


# instance fields
.field private enteredFromTop:Z

.field private focusListener:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;

.field private globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

.field private rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;


# direct methods
.method public static synthetic $r8$lambda$Cyd5h_GwC97utUX7z3PIr7Uo2YA(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->initListener$lambda$3(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V

    return-void
.end method

.method public static synthetic $r8$lambda$OWyW-GV6goAVvkG573fR7kr66zA(Lkotlin/jvm/internal/Ref$ObjectRef;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V
    .locals 0

    invoke-static {p0, p1, p2, p3, p4}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->initListener$lambda$4(Lkotlin/jvm/internal/Ref$ObjectRef;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V

    return-void
.end method

.method public static synthetic $r8$lambda$PXFo4vGDrY1J09HYi-iNN22knPc(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroid/view/View;Landroid/view/View;)V
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->registerGlobalFocusTracker$lambda$0(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroid/view/View;Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$wI_LjKEbgVh4diiiZl4jiwwjaF8(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Ljava/util/List;)Lkotlin/Unit;
    .locals 0

    invoke-static {p0, p1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getActorList$lambda$6(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Ljava/util/List;)Lkotlin/Unit;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->Companion:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 42
    invoke-direct {p0}, Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;-><init>()V

    .line 59
    new-instance v0, Lcom/movieboxpro/android/view/widget/NoHighlightRowPresenter;

    invoke-direct {v0}, Lcom/movieboxpro/android/view/widget/NoHighlightRowPresenter;-><init>()V

    const/4 v1, 0x1

    .line 60
    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/view/widget/NoHighlightRowPresenter;->setNumRows(I)V

    .line 61
    new-instance v1, Landroidx/leanback/widget/ArrayObjectAdapter;

    check-cast v0, Landroidx/leanback/widget/Presenter;

    invoke-direct {v1, v0}, Landroidx/leanback/widget/ArrayObjectAdapter;-><init>(Landroidx/leanback/widget/Presenter;)V

    iput-object v1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    .line 62
    check-cast v1, Landroidx/leanback/widget/ObjectAdapter;

    invoke-virtual {p0, v1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->setAdapter(Landroidx/leanback/widget/ObjectAdapter;)V

    return-void
.end method

.method private final getActorList(Ljava/lang/String;)V
    .locals 10

    .line 158
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getService()Lcom/movieboxpro/android/http/APIService;

    move-result-object v0

    const/4 v1, 0x1

    const/16 v2, 0xa

    const-string v3, "Movie_actor"

    invoke-interface {v0, v3, p1, v1, v2}, Lcom/movieboxpro/android/http/APIService;->Movie_actor(Ljava/lang/String;Ljava/lang/String;II)Lio/reactivex/Observable;

    move-result-object p1

    const-string v0, "Movie_actor(...)"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 161
    move-object v0, p0

    check-cast v0, Landroidx/lifecycle/LifecycleOwner;

    .line 280
    const-class v1, Lcom/movieboxpro/android/model/Actor;

    invoke-static {v1}, Lcom/movieboxpro/android/utils/RxUtils;->rxTranslate2List(Ljava/lang/Class;)Lio/reactivex/ObservableTransformer;

    move-result-object v1

    invoke-virtual {p1, v1}, Lio/reactivex/Observable;->compose(Lio/reactivex/ObservableTransformer;)Lio/reactivex/Observable;

    move-result-object p1

    const-string v1, "compose(...)"

    invoke-static {p1, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    .line 279
    invoke-static {p1, v0}, Lcom/movieboxpro/android/utils/RxSubscribersKt;->transform(Lio/reactivex/Observable;Landroidx/lifecycle/LifecycleOwner;)Lcom/uber/autodispose/ObservableSubscribeProxy;

    move-result-object v2

    .line 162
    new-instance v7, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda0;

    invoke-direct {v7, p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda0;-><init>(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;)V

    const/16 v8, 0xf

    const/4 v9, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v2 .. v9}, Lcom/movieboxpro/android/utils/RxSubscribersKt;->subscribeTo$default(Lcom/uber/autodispose/ObservableSubscribeProxy;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Lio/reactivex/disposables/Disposable;

    return-void
.end method

.method private static final getActorList$lambda$6(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Ljava/util/List;)Lkotlin/Unit;
    .locals 9

    const-string v0, "it"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 164
    check-cast p1, Ljava/util/Collection;

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_2

    .line 165
    new-instance v0, Landroidx/leanback/widget/HeaderItem;

    const-string v1, "Actors"

    invoke-direct {v0, v1}, Landroidx/leanback/widget/HeaderItem;-><init>(Ljava/lang/String;)V

    .line 166
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    if-eqz v1, :cond_0

    new-instance v2, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;

    invoke-direct {v2, v1}, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;-><init>(Landroid/content/Context;)V

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    :goto_0
    check-cast v2, Landroidx/leanback/widget/PresenterSelector;

    new-instance v1, Landroidx/leanback/widget/ArrayObjectAdapter;

    invoke-direct {v1, v2}, Landroidx/leanback/widget/ArrayObjectAdapter;-><init>(Landroidx/leanback/widget/PresenterSelector;)V

    .line 167
    new-instance v2, Lcom/movieboxpro/android/model/Actor;

    const/4 v7, 0x3

    const/4 v8, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const-string v6, "More"

    move-object v3, v2

    invoke-direct/range {v3 .. v8}, Lcom/movieboxpro/android/model/Actor;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-static {v2}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/lang/Iterable;

    invoke-static {p1, v2}, Lkotlin/collections/CollectionsKt;->plus(Ljava/util/Collection;Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/util/Collection;

    const/4 v2, 0x0

    invoke-virtual {v1, v2, p1}, Landroidx/leanback/widget/ArrayObjectAdapter;->addAll(ILjava/util/Collection;)V

    .line 168
    iget-object p1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    invoke-virtual {p1}, Landroidx/leanback/widget/ArrayObjectAdapter;->size()I

    move-result p1

    const/4 v2, 0x1

    if-lt p1, v2, :cond_1

    .line 169
    iget-object p0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    new-instance p1, Landroidx/leanback/widget/ListRow;

    check-cast v1, Landroidx/leanback/widget/ObjectAdapter;

    invoke-direct {p1, v0, v1}, Landroidx/leanback/widget/ListRow;-><init>(Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V

    invoke-virtual {p0, v2, p1}, Landroidx/leanback/widget/ArrayObjectAdapter;->add(ILjava/lang/Object;)V

    goto :goto_1

    .line 171
    :cond_1
    iget-object p0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    new-instance p1, Landroidx/leanback/widget/ListRow;

    check-cast v1, Landroidx/leanback/widget/ObjectAdapter;

    invoke-direct {p1, v0, v1}, Landroidx/leanback/widget/ListRow;-><init>(Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V

    invoke-virtual {p0, p1}, Landroidx/leanback/widget/ArrayObjectAdapter;->add(Ljava/lang/Object;)V

    .line 175
    :cond_2
    :goto_1
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method private static final initListener$lambda$3(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V
    .locals 0

    .line 120
    instance-of p1, p2, Lcom/movieboxpro/android/model/common/Homelist$Typelist;

    const-string p3, "id"

    if-eqz p1, :cond_0

    .line 121
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    if-eqz p0, :cond_7

    sget-object p1, Lcom/movieboxpro/android/tv/movie/MovieDetailActivity;->Companion:Lcom/movieboxpro/android/tv/movie/MovieDetailActivity$Companion;

    check-cast p2, Lcom/movieboxpro/android/model/common/Homelist$Typelist;

    iget-object p4, p2, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->id:Ljava/lang/String;

    invoke-static {p4, p3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    iget-object p2, p2, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->poster:Ljava/lang/String;

    invoke-virtual {p1, p0, p4, p2}, Lcom/movieboxpro/android/tv/movie/MovieDetailActivity$Companion;->starter(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    .line 122
    :cond_0
    instance-of p1, p2, Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;

    if-eqz p1, :cond_1

    .line 123
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    if-eqz p0, :cond_7

    sget-object p1, Lcom/movieboxpro/android/tv/list/PlayListDetailActivity;->Companion:Lcom/movieboxpro/android/tv/list/PlayListDetailActivity$Companion;

    check-cast p2, Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;->getLid()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;->getUsername()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p0, p3, p2}, Lcom/movieboxpro/android/tv/list/PlayListDetailActivity$Companion;->start(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    .line 124
    :cond_1
    instance-of p1, p2, Lcom/movieboxpro/android/model/TrailerItem;

    if-eqz p1, :cond_2

    .line 125
    check-cast p2, Lcom/movieboxpro/android/model/TrailerItem;

    invoke-direct {p0, p2}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->playTrailer(Lcom/movieboxpro/android/model/TrailerItem;)V

    goto :goto_1

    .line 126
    :cond_2
    instance-of p1, p2, Lcom/movieboxpro/android/model/Actor;

    if-eqz p1, :cond_7

    .line 127
    check-cast p2, Lcom/movieboxpro/android/model/Actor;

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/Actor;->getActor_id()Ljava/lang/String;

    move-result-object p1

    check-cast p1, Ljava/lang/CharSequence;

    if-eqz p1, :cond_4

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p1

    if-nez p1, :cond_3

    goto :goto_0

    .line 131
    :cond_3
    sget-object p1, Lcom/movieboxpro/android/view/activity/ActorDetailActivity;->Companion:Lcom/movieboxpro/android/view/activity/ActorDetailActivity$Companion;

    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->requireContext()Landroid/content/Context;

    move-result-object p0

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/Actor;->getActor_id()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p0, p2}, Lcom/movieboxpro/android/view/activity/ActorDetailActivity$Companion;->start(Landroid/content/Context;Ljava/lang/String;)V

    goto :goto_1

    .line 128
    :cond_4
    :goto_0
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getArguments()Landroid/os/Bundle;

    move-result-object p1

    if-eqz p1, :cond_5

    invoke-virtual {p1, p3}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_6

    :cond_5
    const-string p1, ""

    .line 129
    :cond_6
    sget-object p2, Lcom/movieboxpro/android/view/activity/ActorListActivity;->Companion:Lcom/movieboxpro/android/view/activity/ActorListActivity$Companion;

    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->requireContext()Landroid/content/Context;

    move-result-object p0

    const-string p3, "requireContext(...)"

    invoke-static {p0, p3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 p3, 0x1

    invoke-virtual {p2, p0, p1, p3}, Lcom/movieboxpro/android/view/activity/ActorListActivity$Companion;->start(Landroid/content/Context;Ljava/lang/String;I)V

    :cond_7
    :goto_1
    return-void
.end method

.method private static final initListener$lambda$4(Lkotlin/jvm/internal/Ref$ObjectRef;Landroidx/leanback/widget/Presenter$ViewHolder;Ljava/lang/Object;Landroidx/leanback/widget/RowPresenter$ViewHolder;Ljava/lang/Object;)V
    .locals 0

    .line 138
    iget-object p2, p0, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    check-cast p2, Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;->hideTitle()V

    :cond_0
    if-eqz p1, :cond_1

    .line 139
    iget-object p2, p1, Landroidx/leanback/widget/Presenter$ViewHolder;->view:Landroid/view/View;

    goto :goto_0

    :cond_1
    const/4 p2, 0x0

    :goto_0
    if-eqz p2, :cond_2

    .line 140
    iget-object p1, p1, Landroidx/leanback/widget/Presenter$ViewHolder;->view:Landroid/view/View;

    const-string p2, "null cannot be cast to non-null type com.movieboxpro.android.view.tvview.BaseVideoCardView<*>"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;

    .line 141
    invoke-virtual {p1}, Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;->showTitle()V

    .line 152
    iput-object p1, p0, Lkotlin/jvm/internal/Ref$ObjectRef;->element:Ljava/lang/Object;

    :cond_2
    return-void
.end method

.method private final isDescendantOf(Landroid/view/View;Landroid/view/View;)Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_3

    if-nez p2, :cond_0

    goto :goto_1

    :cond_0
    :goto_0
    if-eqz p1, :cond_3

    if-ne p1, p2, :cond_1

    const/4 p1, 0x1

    return p1

    .line 112
    :cond_1
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p1

    instance-of v1, p1, Landroid/view/View;

    if-eqz v1, :cond_2

    check-cast p1, Landroid/view/View;

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    goto :goto_0

    :cond_3
    :goto_1
    return v0
.end method

.method private final launchYoutubeApp(Landroid/content/Context;Ljava/lang/String;)Z
    .locals 5

    .line 243
    sget-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->INSTANCE:Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;

    invoke-virtual {v0, p2}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->launchTargets(Ljava/lang/String;)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;

    .line 244
    new-instance v2, Landroid/content/Intent;

    invoke-virtual {v1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;->getUri()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v3

    const-string v4, "android.intent.action.VIEW"

    invoke-direct {v2, v4, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;Landroid/net/Uri;)V

    .line 245
    invoke-virtual {v1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;->getPackageName()Ljava/lang/String;

    move-result-object v3

    check-cast v3, Ljava/lang/CharSequence;

    if-eqz v3, :cond_1

    invoke-interface {v3}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-nez v3, :cond_0

    goto :goto_1

    .line 246
    :cond_0
    invoke-virtual {v1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    .line 248
    :cond_1
    :goto_1
    const-string v1, "android.intent.category.BROWSABLE"

    invoke-virtual {v2, v1}, Landroid/content/Intent;->addCategory(Ljava/lang/String;)Landroid/content/Intent;

    const/high16 v1, 0x14000000

    .line 249
    invoke-virtual {v2, v1}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 250
    const-string v1, "force_fullscreen"

    const/4 v3, 0x1

    invoke-virtual {v2, v1, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 251
    const-string v1, "finish_on_ended"

    invoke-virtual {v2, v1, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Z)Landroid/content/Intent;

    .line 252
    const-string v1, "VIDEO_ID"

    invoke-virtual {v2, v1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    .line 254
    :try_start_0
    invoke-virtual {p1, v2}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return v3

    :catch_0
    nop

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    return p1
.end method

.method private final playTrailer(Lcom/movieboxpro/android/model/TrailerItem;)V
    .locals 3

    .line 234
    sget-object v0, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->INSTANCE:Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;

    invoke-virtual {p1}, Lcom/movieboxpro/android/model/TrailerItem;->getVideoId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1}, Lcom/movieboxpro/android/model/TrailerItem;->getUrl()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, v1, p1}, Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;->resolveVideoId(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 235
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 236
    move-object v1, p1

    check-cast v1, Ljava/lang/CharSequence;

    if-eqz v1, :cond_1

    invoke-interface {v1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    if-eqz v0, :cond_1

    invoke-direct {p0, v0, p1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->launchYoutubeApp(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    return-void

    .line 239
    :cond_1
    :goto_0
    sget-object v0, Lcom/movieboxpro/android/view/dialog/YoutubePlayDialog;->Companion:Lcom/movieboxpro/android/view/dialog/YoutubePlayDialog$Companion;

    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getChildFragmentManager()Landroidx/fragment/app/FragmentManager;

    move-result-object v1

    const-string v2, "getChildFragmentManager(...)"

    invoke-static {v1, v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    if-nez p1, :cond_2

    const-string p1, ""

    :cond_2
    invoke-virtual {v0, v1, p1}, Lcom/movieboxpro/android/view/dialog/YoutubePlayDialog$Companion;->showDialog(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    return-void
.end method

.method private final registerGlobalFocusTracker()V
    .locals 2

    .line 80
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroidx/fragment/app/FragmentActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 81
    :cond_0
    iget-object v1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

    if-eqz v1, :cond_1

    return-void

    .line 82
    :cond_1
    new-instance v1, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda3;-><init>(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;)V

    iput-object v1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

    .line 95
    invoke-virtual {v0}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    iget-object v1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

    invoke-virtual {v0, v1}, Landroid/view/ViewTreeObserver;->addOnGlobalFocusChangeListener(Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;)V

    :cond_2
    :goto_0
    return-void
.end method

.method private static final registerGlobalFocusTracker$lambda$0(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;Landroid/view/View;Landroid/view/View;)V
    .locals 1

    .line 83
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getView()Landroid/view/View;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 84
    :cond_0
    invoke-direct {p0, p2, v0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->isDescendantOf(Landroid/view/View;Landroid/view/View;)Z

    move-result p2

    .line 85
    invoke-direct {p0, p1, v0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->isDescendantOf(Landroid/view/View;Landroid/view/View;)Z

    move-result v0

    if-eqz p2, :cond_2

    if-nez v0, :cond_2

    .line 87
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object p2

    if-eqz p2, :cond_1

    sget v0, Lcom/movieboxpro/androidtv/R$id;->flTop:I

    invoke-virtual {p2, v0}, Landroidx/fragment/app/FragmentActivity;->findViewById(I)Landroid/view/View;

    move-result-object p2

    goto :goto_0

    :cond_1
    const/4 p2, 0x0

    .line 88
    :goto_0
    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->isDescendantOf(Landroid/view/View;Landroid/view/View;)Z

    move-result p1

    iput-boolean p1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->enteredFromTop:Z

    .line 89
    iget-object p0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->focusListener:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;

    if-eqz p0, :cond_3

    invoke-interface {p0, p1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;->onFocusChanged(Z)V

    goto :goto_1

    :cond_2
    if-nez p2, :cond_3

    if-eqz v0, :cond_3

    const/4 p1, 0x0

    .line 91
    iput-boolean p1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->enteredFromTop:Z

    .line 92
    iget-object p0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->focusListener:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;

    if-eqz p0, :cond_3

    invoke-interface {p0, p1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;->onFocusChanged(Z)V

    :cond_3
    :goto_1
    return-void
.end method

.method private final unRegisterGlobalFocusTracker()V
    .locals 3

    .line 99
    iget-object v0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

    if-nez v0, :cond_0

    return-void

    .line 100
    :cond_0
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getActivity()Landroidx/fragment/app/FragmentActivity;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Landroidx/fragment/app/FragmentActivity;->getWindow()Landroid/view/Window;

    move-result-object v1

    if-eqz v1, :cond_3

    invoke-virtual {v1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 101
    :cond_1
    invoke-virtual {v1}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/ViewTreeObserver;->isAlive()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 102
    invoke-virtual {v1}, Landroid/view/View;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/view/ViewTreeObserver;->removeOnGlobalFocusChangeListener(Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;)V

    :cond_2
    const/4 v0, 0x0

    .line 104
    iput-object v0, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->globalFocusChangeListener:Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;

    :cond_3
    :goto_0
    return-void
.end method


# virtual methods
.method public bridge synthetic bindPresenter()Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;
    .locals 1

    .line 42
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->bindPresenter()Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;

    return-object v0
.end method

.method protected bindPresenter()Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;
    .locals 2

    .line 231
    new-instance v0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;

    move-object v1, p0

    check-cast v1, Landroidx/lifecycle/LifecycleOwner;

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedPresenter;-><init>(Landroidx/lifecycle/LifecycleOwner;)V

    return-object v0
.end method

.method protected initBackgroundColor()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected initData()V
    .locals 8

    .line 180
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    if-eqz v0, :cond_0

    const-string v1, "id"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_1

    :cond_0
    const-string v0, ""

    :cond_1
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getActorList(Ljava/lang/String;)V

    .line 181
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    const-string v2, "movieList"

    invoke-virtual {v0, v2}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v0

    goto :goto_0

    :cond_2
    move-object v0, v1

    :goto_0
    const-string v2, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.movie.MovieDetail.Recommend>"

    invoke-static {v0, v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v0, Ljava/util/ArrayList;

    .line 182
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v2

    if-eqz v2, :cond_3

    const-string v3, "playList"

    invoke-virtual {v2, v3}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v2

    goto :goto_1

    :cond_3
    move-object v2, v1

    :goto_1
    const-string v3, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.movie.MovieListModel.MovieListItem>"

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v2, Ljava/util/ArrayList;

    .line 183
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getArguments()Landroid/os/Bundle;

    move-result-object v3

    if-eqz v3, :cond_4

    const-string v4, "trailer"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v3

    goto :goto_2

    :cond_4
    move-object v3, v1

    :goto_2
    instance-of v4, v3, Ljava/util/ArrayList;

    if-eqz v4, :cond_5

    check-cast v3, Ljava/util/ArrayList;

    goto :goto_3

    :cond_5
    move-object v3, v1

    .line 185
    :goto_3
    move-object v4, v3

    check-cast v4, Ljava/util/Collection;

    const/4 v5, 0x0

    if-eqz v4, :cond_9

    invoke-interface {v4}, Ljava/util/Collection;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_6

    goto :goto_6

    .line 186
    :cond_6
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v6

    if-eqz v6, :cond_7

    new-instance v7, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;

    invoke-direct {v7, v6}, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;-><init>(Landroid/content/Context;)V

    goto :goto_4

    :cond_7
    move-object v7, v1

    :goto_4
    check-cast v7, Landroidx/leanback/widget/PresenterSelector;

    new-instance v6, Landroidx/leanback/widget/ArrayObjectAdapter;

    invoke-direct {v6, v7}, Landroidx/leanback/widget/ArrayObjectAdapter;-><init>(Landroidx/leanback/widget/PresenterSelector;)V

    .line 187
    invoke-virtual {v6, v5, v4}, Landroidx/leanback/widget/ArrayObjectAdapter;->addAll(ILjava/util/Collection;)V

    .line 188
    new-instance v4, Landroidx/leanback/widget/HeaderItem;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v7, 0x1

    if-le v3, v7, :cond_8

    const-string v3, "Trailers"

    goto :goto_5

    :cond_8
    const-string v3, "Trailer"

    :goto_5
    invoke-direct {v4, v3}, Landroidx/leanback/widget/HeaderItem;-><init>(Ljava/lang/String;)V

    .line 189
    iget-object v3, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    new-instance v7, Landroidx/leanback/widget/ListRow;

    check-cast v6, Landroidx/leanback/widget/ObjectAdapter;

    invoke-direct {v7, v4, v6}, Landroidx/leanback/widget/ListRow;-><init>(Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V

    invoke-virtual {v3, v7}, Landroidx/leanback/widget/ArrayObjectAdapter;->add(Ljava/lang/Object;)V

    .line 192
    :cond_9
    :goto_6
    move-object v3, v0

    check-cast v3, Ljava/util/Collection;

    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_c

    .line 193
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 194
    check-cast v0, Ljava/lang/Iterable;

    .line 282
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_7
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_a

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;

    .line 195
    new-instance v6, Lcom/movieboxpro/android/model/common/Homelist$Typelist;

    invoke-direct {v6}, Lcom/movieboxpro/android/model/common/Homelist$Typelist;-><init>()V

    .line 196
    iget-object v7, v4, Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;->poster:Ljava/lang/String;

    iput-object v7, v6, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->poster:Ljava/lang/String;

    .line 197
    iget-object v7, v4, Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;->quality_tag:Ljava/lang/String;

    iput-object v7, v6, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->quality_tag:Ljava/lang/String;

    .line 198
    iget-object v7, v4, Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;->title:Ljava/lang/String;

    iput-object v7, v6, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->title:Ljava/lang/String;

    .line 199
    iget-object v4, v4, Lcom/movieboxpro/android/model/movie/MovieDetail$Recommend;->mid:Ljava/lang/String;

    iput-object v4, v6, Lcom/movieboxpro/android/model/common/Homelist$Typelist;->id:Ljava/lang/String;

    .line 200
    invoke-virtual {v3, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_7

    .line 202
    :cond_a
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-eqz v0, :cond_b

    .line 203
    new-instance v4, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;

    invoke-direct {v4, v0}, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;-><init>(Landroid/content/Context;)V

    goto :goto_8

    :cond_b
    move-object v4, v1

    .line 205
    :goto_8
    new-instance v0, Landroidx/leanback/widget/ArrayObjectAdapter;

    check-cast v4, Landroidx/leanback/widget/PresenterSelector;

    invoke-direct {v0, v4}, Landroidx/leanback/widget/ArrayObjectAdapter;-><init>(Landroidx/leanback/widget/PresenterSelector;)V

    .line 206
    check-cast v3, Ljava/util/Collection;

    invoke-virtual {v0, v5, v3}, Landroidx/leanback/widget/ArrayObjectAdapter;->addAll(ILjava/util/Collection;)V

    .line 207
    new-instance v3, Landroidx/leanback/widget/HeaderItem;

    const-string v4, "Related Movies"

    invoke-direct {v3, v4}, Landroidx/leanback/widget/HeaderItem;-><init>(Ljava/lang/String;)V

    .line 208
    iget-object v4, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    new-instance v6, Landroidx/leanback/widget/ListRow;

    check-cast v0, Landroidx/leanback/widget/ObjectAdapter;

    invoke-direct {v6, v3, v0}, Landroidx/leanback/widget/ListRow;-><init>(Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V

    invoke-virtual {v4, v6}, Landroidx/leanback/widget/ArrayObjectAdapter;->add(Ljava/lang/Object;)V

    .line 213
    :cond_c
    check-cast v2, Ljava/util/Collection;

    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_e

    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    const-string v3, "hide_movie_list"

    invoke-virtual {v0, v3, v5}, Lcom/movieboxpro/android/utils/PrefsUtils;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-nez v0, :cond_e

    .line 214
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-eqz v0, :cond_d

    new-instance v1, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;

    invoke-direct {v1, v0}, Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;-><init>(Landroid/content/Context;)V

    :cond_d
    check-cast v1, Landroidx/leanback/widget/PresenterSelector;

    new-instance v0, Landroidx/leanback/widget/ArrayObjectAdapter;

    invoke-direct {v0, v1}, Landroidx/leanback/widget/ArrayObjectAdapter;-><init>(Landroidx/leanback/widget/PresenterSelector;)V

    .line 215
    invoke-virtual {v0, v5, v2}, Landroidx/leanback/widget/ArrayObjectAdapter;->addAll(ILjava/util/Collection;)V

    .line 216
    new-instance v1, Landroidx/leanback/widget/HeaderItem;

    const-string v2, "Related MovieLists"

    invoke-direct {v1, v2}, Landroidx/leanback/widget/HeaderItem;-><init>(Ljava/lang/String;)V

    .line 217
    iget-object v2, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->rowAdapter:Landroidx/leanback/widget/ArrayObjectAdapter;

    new-instance v3, Landroidx/leanback/widget/ListRow;

    check-cast v0, Landroidx/leanback/widget/ObjectAdapter;

    invoke-direct {v3, v1, v0}, Landroidx/leanback/widget/ListRow;-><init>(Landroidx/leanback/widget/HeaderItem;Landroidx/leanback/widget/ObjectAdapter;)V

    invoke-virtual {v2, v3}, Landroidx/leanback/widget/ArrayObjectAdapter;->add(Ljava/lang/Object;)V

    :cond_e
    return-void
.end method

.method protected initListener()V
    .locals 2

    .line 119
    new-instance v0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda1;-><init>(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;)V

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->setOnItemViewClickedListener(Landroidx/leanback/widget/BaseOnItemViewClickedListener;)V

    .line 136
    new-instance v0, Lkotlin/jvm/internal/Ref$ObjectRef;

    invoke-direct {v0}, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V

    .line 137
    new-instance v1, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda2;

    invoke-direct {v1, v0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$$ExternalSyntheticLambda2;-><init>(Lkotlin/jvm/internal/Ref$ObjectRef;)V

    invoke-virtual {p0, v1}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->setOnItemViewSelectedListener(Landroidx/leanback/widget/BaseOnItemViewSelectedListener;)V

    return-void
.end method

.method protected initView()V
    .locals 2

    .line 223
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-eqz v0, :cond_0

    const/high16 v1, 0x41200000    # 10.0f

    invoke-static {v0, v1}, Lcom/movieboxpro/android/utils/DensityUtils;->dp2px(Landroid/content/Context;F)I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 224
    :goto_0
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->getVerticalGridView()Landroidx/leanback/widget/VerticalGridView;

    move-result-object v1

    invoke-virtual {v1, v0, v0, v0, v0}, Landroidx/leanback/widget/VerticalGridView;->setPadding(IIII)V

    return-void
.end method

.method protected loadData()V
    .locals 0

    return-void
.end method

.method public onDestroyView()V
    .locals 0

    .line 75
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->unRegisterGlobalFocusTracker()V

    .line 76
    invoke-super {p0}, Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;->onDestroyView()V

    return-void
.end method

.method public onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V
    .locals 1

    const-string v0, "view"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 70
    invoke-super {p0, p1, p2}, Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;->onViewCreated(Landroid/view/View;Landroid/os/Bundle;)V

    .line 71
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->registerGlobalFocusTracker()V

    return-void
.end method

.method public final setFocusListener(Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;)V
    .locals 1

    const-string v0, "focusListener"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 55
    iput-object p1, p0, Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment;->focusListener:Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;

    return-void
.end method
