.class public final Lcom/movieboxpro/android/tv/home/TvGuideListFragment;
.super Lcom/movieboxpro/android/base/BaseListFragment;
.source "TvGuideListFragment.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/movieboxpro/android/base/BaseListFragment<",
        "Lcom/movieboxpro/android/model/TvShowsUpdateModel;",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0007\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0018\u0000 \'2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\'B\u0007\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0008J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0003J\u0006\u0010\u000c\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u0008\u0012\u0004\u0012\u00020\u00030\u000fH\u0014J\u0008\u0010\u0010\u001a\u00020\u0011H\u0014J\u0008\u0010\u0012\u001a\u00020\u0013H\u0014J\u0008\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0008\u0010\u0019\u001a\u00020\nH\u0014J\u0008\u0010\u001a\u001a\u00020\nH\u0014J\u0008\u0010\u001b\u001a\u00020\u0015H\u0014J\u0008\u0010\u001c\u001a\u00020\u0015H\u0014J\u0008\u0010\u001d\u001a\u00020\u0013H\u0014J\u0008\u0010\u001e\u001a\u00020\u001fH\u0014J\u0012\u0010 \u001a\u00020\n2\u0008\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0018\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("
    }
    d2 = {
        "Lcom/movieboxpro/android/tv/home/TvGuideListFragment;",
        "Lcom/movieboxpro/android/base/BaseListFragment;",
        "Lcom/movieboxpro/android/model/TvShowsUpdateModel;",
        "",
        "<init>",
        "()V",
        "day",
        "listenerVideoList",
        "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;",
        "setFocusUpListener",
        "",
        "refreshData",
        "requestListFocus",
        "scrollToTop",
        "getServiceData",
        "Lio/reactivex/Observable;",
        "onItemClick",
        "Lcom/chad/library/adapter/base/listener/OnItemClickListener;",
        "initItemLayout",
        "",
        "isOpenLoadMore",
        "",
        "onBackPressed",
        "event",
        "Lcom/movieboxpro/android/event/OnBackPressedEvent;",
        "onRecyclerViewFocus",
        "initFocusUpListener",
        "enableEventBus",
        "isNeedListenerFocusUp",
        "gridLayoutSpan",
        "initScaleValue",
        "",
        "getBundle",
        "arguments",
        "Landroid/os/Bundle;",
        "initHolder",
        "helper",
        "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;",
        "item",
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
.field private static final ARG_DAY:Ljava/lang/String; = "day"

.field public static final Companion:Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;


# instance fields
.field private day:Ljava/lang/String;

.field private listenerVideoList:Lcom/movieboxpro/android/listener/OnVideoListFocusListener;


# direct methods
.method public static synthetic $r8$lambda$Qs2ANDBRP_x8x44QNashSIvlG50(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;ILandroid/view/View;)Z
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->initFocusUpListener$lambda$2(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;ILandroid/view/View;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$syA58ftsWzoSBZMrEC0J-ZJHv4U(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->onItemClick$lambda$1(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->Companion:Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 22
    invoke-direct {p0}, Lcom/movieboxpro/android/base/BaseListFragment;-><init>()V

    .line 24
    const-string v0, ""

    iput-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->day:Ljava/lang/String;

    return-void
.end method

.method private static final initFocusUpListener$lambda$2(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;ILandroid/view/View;)Z
    .locals 0

    const/16 p2, 0x21

    if-ne p1, p2, :cond_1

    .line 80
    iget-object p0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->listenerVideoList:Lcom/movieboxpro/android/listener/OnVideoListFocusListener;

    if-eqz p0, :cond_0

    invoke-interface {p0}, Lcom/movieboxpro/android/listener/OnVideoListFocusListener;->onFocusUp()V

    :cond_0
    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private static final onItemClick$lambda$1(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V
    .locals 1

    const-string v0, "adapter"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "<unused var>"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 52
    invoke-virtual {p1, p3}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object p1

    instance-of p2, p1, Lcom/movieboxpro/android/model/TvShowsUpdateModel;

    if-eqz p2, :cond_0

    check-cast p1, Lcom/movieboxpro/android/model/TvShowsUpdateModel;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_1

    return-void

    .line 53
    :cond_1
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->getContext()Landroid/content/Context;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 54
    sget-object p2, Lcom/movieboxpro/android/tv/tv/TvDetailActivity;->Companion:Lcom/movieboxpro/android/tv/tv/TvDetailActivity$Companion;

    invoke-virtual {p1}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getId()Ljava/lang/String;

    move-result-object p3

    const-string v0, "getId(...)"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getPoster()Ljava/lang/String;

    move-result-object p1

    const-string v0, "getPoster(...)"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-virtual {p2, p0, p3, p1}, Lcom/movieboxpro/android/tv/tv/TvDetailActivity$Companion;->starter(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    return-void
.end method


# virtual methods
.method protected enableEventBus()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method protected getBundle(Landroid/os/Bundle;)V
    .locals 1

    if-eqz p1, :cond_0

    .line 108
    const-string v0, "day"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_1

    :cond_0
    const-string p1, ""

    :cond_1
    iput-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->day:Ljava/lang/String;

    const/16 p1, 0x10

    .line 109
    iput p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mPageSize:I

    const/16 p1, 0x9

    .line 110
    iput p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->preLoadNum:I

    .line 111
    const-class p1, Lcom/movieboxpro/android/model/TvShowsUpdateModel;

    iput-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mClass:Ljava/lang/Class;

    return-void
.end method

.method protected getServiceData()Lio/reactivex/Observable;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lio/reactivex/Observable<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 47
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getService()Lcom/movieboxpro/android/http/APIService;

    move-result-object v0

    iget-object v1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->day:Ljava/lang/String;

    iget v2, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mPageSize:I

    iget v3, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mCurrentPage:I

    const-string v4, "TV_guide"

    invoke-interface {v0, v4, v1, v2, v3}, Lcom/movieboxpro/android/http/APIService;->TV_guide(Ljava/lang/String;Ljava/lang/String;II)Lio/reactivex/Observable;

    move-result-object v0

    const-string v1, "TV_guide(...)"

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    return-object v0
.end method

.method protected gridLayoutSpan()I
    .locals 1

    const/4 v0, 0x2

    return v0
.end method

.method protected initFocusUpListener()V
    .locals 2

    .line 78
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v1, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$$ExternalSyntheticLambda1;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;)V

    invoke-virtual {v0, v1}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setOnInBorderKeyEventListener(Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnInBorderKeyEventListener;)V

    .line 85
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->requireContext()Landroid/content/Context;

    move-result-object v0

    const/high16 v1, 0x41200000    # 10.0f

    invoke-static {v0, v1}, Lcom/movieboxpro/android/utils/DensityUtils;->dp2px(Landroid/content/Context;F)I

    move-result v0

    .line 86
    iget-object v1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    invoke-virtual {v1, v0, v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setSpacingWithMargins(II)V

    .line 87
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->requireContext()Landroid/content/Context;

    move-result-object v0

    const/high16 v1, 0x41400000    # 12.0f

    invoke-static {v0, v1}, Lcom/movieboxpro/android/utils/DensityUtils;->dp2px(Landroid/content/Context;F)I

    move-result v0

    .line 88
    iget-object v1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    invoke-virtual {v1, v0, v0, v0, v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setPadding(IIII)V

    return-void
.end method

.method protected initHolder(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Lcom/movieboxpro/android/model/TvShowsUpdateModel;)V
    .locals 5

    const-string v0, "helper"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "item"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 115
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvName:I

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getTitle()Ljava/lang/String;

    move-result-object v1

    check-cast v1, Ljava/lang/CharSequence;

    invoke-virtual {p1, v0, v1}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->setText(ILjava/lang/CharSequence;)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;

    .line 116
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvType:I

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getCats()Ljava/lang/String;

    move-result-object v1

    check-cast v1, Ljava/lang/CharSequence;

    invoke-virtual {p1, v0, v1}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->setText(ILjava/lang/CharSequence;)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;

    .line 117
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvSeasonInfo:I

    sget-object v1, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getSeason_episode()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    new-array v3, v2, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object v1, v3, v4

    invoke-static {v3, v2}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v1

    const-string v2, "%s Updated"

    invoke-static {v2, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "format(...)"

    invoke-static {v1, v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast v1, Ljava/lang/CharSequence;

    invoke-virtual {p1, v0, v1}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->setText(ILjava/lang/CharSequence;)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;

    .line 118
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TvShowsUpdateModel;->getPoster()Ljava/lang/String;

    move-result-object p2

    sget v1, Lcom/movieboxpro/androidtv/R$id;->ivPoster:I

    invoke-virtual {p1, v1}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->getView(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-static {v0, p2, p1, v1}, Lcom/movieboxpro/android/utils/GlideUtils;->loadCornerPortraitHolder(Landroid/content/Context;Ljava/lang/String;Landroid/widget/ImageView;I)V

    return-void
.end method

.method public bridge synthetic initHolder(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V
    .locals 0

    .line 22
    check-cast p2, Lcom/movieboxpro/android/model/TvShowsUpdateModel;

    invoke-virtual {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->initHolder(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Lcom/movieboxpro/android/model/TvShowsUpdateModel;)V

    return-void
.end method

.method protected initItemLayout()I
    .locals 1

    .line 59
    sget v0, Lcom/movieboxpro/androidtv/R$layout;->adapter_tv_guide_item:I

    return v0
.end method

.method protected initScaleValue()F
    .locals 1

    const/high16 v0, 0x3f800000    # 1.0f

    return v0
.end method

.method protected isNeedListenerFocusUp()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected isOpenLoadMore()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public final onBackPressed(Lcom/movieboxpro/android/event/OnBackPressedEvent;)V
    .locals 1
    .annotation runtime Lorg/greenrobot/eventbus/Subscribe;
        threadMode = .enum Lorg/greenrobot/eventbus/ThreadMode;->MAIN:Lorg/greenrobot/eventbus/ThreadMode;
    .end annotation

    const-string v0, "event"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 67
    invoke-virtual {p1}, Lcom/movieboxpro/android/event/OnBackPressedEvent;->getType()I

    move-result p1

    const/4 v0, 0x7

    if-ne p1, v0, :cond_0

    .line 68
    iget-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->smoothScrollToPosition(I)V

    .line 69
    iget-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->listenerVideoList:Lcom/movieboxpro/android/listener/OnVideoListFocusListener;

    if-eqz p1, :cond_0

    invoke-interface {p1}, Lcom/movieboxpro/android/listener/OnVideoListFocusListener;->onFocusUp()V

    :cond_0
    return-void
.end method

.method protected onItemClick()Lcom/chad/library/adapter/base/listener/OnItemClickListener;
    .locals 1

    .line 51
    new-instance v0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$$ExternalSyntheticLambda0;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideListFragment;)V

    return-object v0
.end method

.method protected onRecyclerViewFocus()V
    .locals 1

    .line 74
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->listenerVideoList:Lcom/movieboxpro/android/listener/OnVideoListFocusListener;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/movieboxpro/android/listener/OnVideoListFocusListener;->onFocus()V

    :cond_0
    return-void
.end method

.method public final refreshData(Ljava/lang/String;)V
    .locals 1

    const-string v0, "day"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 32
    iput-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->day:Ljava/lang/String;

    .line 33
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->startRefresh()V

    return-void
.end method

.method public final requestListFocus()V
    .locals 1

    .line 37
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->requestFocus()Z

    :cond_0
    return-void
.end method

.method public final scrollToTop()V
    .locals 2

    .line 41
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getData()Ljava/util/List;

    move-result-object v0

    if-eqz v0, :cond_0

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    const/4 v1, 0x1

    xor-int/2addr v0, v1

    if-ne v0, v1, :cond_0

    .line 42
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->mRecyclerView:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->smoothScrollToPosition(I)V

    :cond_0
    return-void
.end method

.method public final setFocusUpListener(Lcom/movieboxpro/android/listener/OnVideoListFocusListener;)V
    .locals 1

    const-string v0, "listenerVideoList"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 28
    iput-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->listenerVideoList:Lcom/movieboxpro/android/listener/OnVideoListFocusListener;

    return-void
.end method
