.class public final Lcom/movieboxpro/android/tv/home/TvGuideFragment;
.super Lcom/movieboxpro/android/base/BaseBindingFragment;
.source "TvGuideFragment.kt"

# interfaces
.implements Lcom/movieboxpro/android/listener/OnVideoListFocusListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nTvGuideFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TvGuideFragment.kt\ncom/movieboxpro/android/tv/home/TvGuideFragment\n+ 2 DataBindingFragment.kt\ncom/snowtop/diskpanda/utils/databinding/DataBindingFragmentKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,317:1\n13#2:318\n1573#3:319\n1604#3,4:320\n*S KotlinDebug\n*F\n+ 1 TvGuideFragment.kt\ncom/movieboxpro/android/tv/home/TvGuideFragment\n*L\n30#1:318\n67#1:319\n67#1:320,4\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u000b\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010!\n\u0002\u0008\u0005\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00019B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0008\u0010\u0018\u001a\u00020\u0015H\u0016J\u0008\u0010\u0019\u001a\u00020\u0015H\u0016J\u0008\u0010\u001a\u001a\u00020\u0015H\u0016J\u0008\u0010\u001b\u001a\u00020\u0015H\u0016J\u0008\u0010\u001c\u001a\u00020\u0015H\u0016J\u0018\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J$\u0010 \u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00172\n\u0008\u0002\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0018\u0010$\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010%\u001a\u00020\rH\u0002J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\rH\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u001c\u0010.\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\u001c\u00102\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\u001c\u00103\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\"\u00104\u001a\u0008\u0012\u0004\u0012\u000200052\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/H\u0002J\u001e\u00104\u001a\u0008\u0012\u0004\u0012\u000200052\u0006\u00107\u001a\u0002002\u0006\u00108\u001a\u000200H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\u000c\n\u0004\u0008\t\u0010\n\u001a\u0004\u0008\u0007\u0010\u0008R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u000cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"
    }
    d2 = {
        "Lcom/movieboxpro/android/tv/home/TvGuideFragment;",
        "Lcom/movieboxpro/android/base/BaseBindingFragment;",
        "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;",
        "<init>",
        "()V",
        "binding",
        "Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;",
        "getBinding",
        "()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;",
        "binding$delegate",
        "Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;",
        "timeLineAdapter",
        "Lcom/chad/library/adapter/base/BaseQuickAdapter;",
        "Lcom/movieboxpro/android/model/TimeLineModel;",
        "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;",
        "listFragment",
        "Lcom/movieboxpro/android/tv/home/TvGuideListFragment;",
        "lastSelectedPosition",
        "",
        "focusedPosition",
        "refreshData",
        "",
        "onBackPressed",
        "",
        "initView",
        "initData",
        "initListener",
        "onFocusUp",
        "onFocus",
        "selectDay",
        "position",
        "moveToList",
        "setDayFocus",
        "focused",
        "itemView",
        "Landroid/view/View;",
        "bindFocusViews",
        "item",
        "bindTimeLine",
        "helper",
        "isToday",
        "calendar",
        "Ljava/util/Calendar;",
        "getWeekDayLetter",
        "",
        "week",
        "getLastTimeInterval",
        "Lkotlin/Pair;",
        "Ljava/util/Date;",
        "date",
        "getTimeInterval",
        "getTimeNextWeek",
        "findDates",
        "",
        "range",
        "dBegin",
        "dEnd",
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
.field static final synthetic $$delegatedProperties:[Lkotlin/reflect/KProperty;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[",
            "Lkotlin/reflect/KProperty<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field public static final Companion:Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;


# instance fields
.field private final binding$delegate:Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;

.field private focusedPosition:I

.field private lastSelectedPosition:I

.field private listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

.field private timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/chad/library/adapter/base/BaseQuickAdapter<",
            "Lcom/movieboxpro/android/model/TimeLineModel;",
            "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$7dAYssMnxx-5Xh914IQJhxfePsc(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V
    .locals 0

    invoke-static {p0, p1, p2, p3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->initListener$lambda$2(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V

    return-void
.end method

.method public static synthetic $r8$lambda$isXBaL_OlONaEI23PbdOXhczg7g(Lcom/movieboxpro/android/tv/home/TvGuideFragment;ILandroid/view/View;)Z
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->initListener$lambda$4(Lcom/movieboxpro/android/tv/home/TvGuideFragment;ILandroid/view/View;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$wyxdEzpco8QcnYuoP0xlVbaU2lc(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Landroid/view/View;Z)V
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->initListener$lambda$3(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Landroid/view/View;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$x0MC47Q1mw0whMaJBJPYQ9GGM_U(Lkotlin/jvm/internal/Ref$IntRef;Ljava/util/List;Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V
    .locals 0

    invoke-static {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->initData$lambda$1(Lkotlin/jvm/internal/Ref$IntRef;Ljava/util/List;Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 6

    const/4 v0, 0x1

    new-array v0, v0, [Lkotlin/reflect/KProperty;

    new-instance v1, Lkotlin/jvm/internal/PropertyReference1Impl;

    const-string v2, "binding"

    const-string v3, "getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;"

    const-class v4, Lcom/movieboxpro/android/tv/home/TvGuideFragment;

    const/4 v5, 0x0

    invoke-direct {v1, v4, v2, v3, v5}, Lkotlin/jvm/internal/PropertyReference1Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    check-cast v1, Lkotlin/jvm/internal/PropertyReference1;

    invoke-static {v1}, Lkotlin/jvm/internal/Reflection;->property1(Lkotlin/jvm/internal/PropertyReference1;)Lkotlin/reflect/KProperty1;

    move-result-object v1

    aput-object v1, v0, v5

    sput-object v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    new-instance v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->Companion:Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 3

    .line 28
    sget v0, Lcom/movieboxpro/androidtv/R$layout;->fragment_tv_guide:I

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/base/BaseBindingFragment;-><init>(I)V

    .line 30
    move-object v0, p0

    check-cast v0, Landroidx/fragment/app/Fragment;

    .line 318
    new-instance v1, Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;

    const-class v2, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    invoke-direct {v1, v2, v0}, Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;-><init>(Ljava/lang/Class;Landroidx/fragment/app/Fragment;)V

    .line 30
    iput-object v1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->binding$delegate:Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;

    const/4 v0, -0x1

    .line 35
    iput v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    return-void
.end method

.method public static final synthetic access$bindTimeLine(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Lcom/movieboxpro/android/model/TimeLineModel;)V
    .locals 0

    .line 28
    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->bindTimeLine(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Lcom/movieboxpro/android/model/TimeLineModel;)V

    return-void
.end method

.method public static final synthetic access$setDayFocus(Lcom/movieboxpro/android/tv/home/TvGuideFragment;IZLandroid/view/View;)V
    .locals 0

    .line 28
    invoke-direct {p0, p1, p2, p3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->setDayFocus(IZLandroid/view/View;)V

    return-void
.end method

.method public static final synthetic access$setFocusedPosition$p(Lcom/movieboxpro/android/tv/home/TvGuideFragment;I)V
    .locals 0

    .line 28
    iput p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->focusedPosition:I

    return-void
.end method

.method private final bindFocusViews(Landroid/view/View;Lcom/movieboxpro/android/model/TimeLineModel;)V
    .locals 2

    .line 195
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvWeek:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 196
    sget v1, Lcom/movieboxpro/androidtv/R$id;->viewFocusRing:I

    invoke-virtual {p1, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object p1

    .line 197
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 198
    invoke-static {p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    invoke-static {p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->visible(Landroid/view/View;)V

    .line 199
    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    sget p1, Lcom/movieboxpro/androidtv/R$color;->color_main_yellow:I

    invoke-static {v0, p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    goto :goto_1

    .line 201
    :cond_0
    invoke-static {p1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    invoke-static {p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->gone(Landroid/view/View;)V

    .line 202
    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect()Z

    move-result p1

    if-eqz p1, :cond_1

    sget p1, Lcom/movieboxpro/androidtv/R$color;->color_main_yellow:I

    goto :goto_0

    :cond_1
    sget p1, Lcom/movieboxpro/androidtv/R$color;->gray_999:I

    :goto_0
    invoke-static {v0, p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    :goto_1
    return-void
.end method

.method private final bindTimeLine(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Lcom/movieboxpro/android/model/TimeLineModel;)V
    .locals 3

    .line 207
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvWeek:I

    invoke-virtual {p1, v0}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->getView(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 208
    sget v1, Lcom/movieboxpro/androidtv/R$id;->tvDay:I

    invoke-virtual {p1, v1}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->getView(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 209
    sget v2, Lcom/movieboxpro/androidtv/R$id;->viewFocusRing:I

    invoke-virtual {p1, v2}, Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;->getView(I)Landroid/view/View;

    move-result-object p1

    .line 210
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->getWeekCode()Ljava/lang/String;

    move-result-object v2

    check-cast v2, Ljava/lang/CharSequence;

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 211
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->getDayNumber()I

    move-result v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    check-cast v2, Ljava/lang/CharSequence;

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 212
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 213
    sget v2, Lcom/movieboxpro/androidtv/R$drawable;->yelow_circle_shape:I

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setBackgroundResource(I)V

    .line 214
    sget v2, Lcom/movieboxpro/androidtv/R$color;->color_main:I

    invoke-static {v1, v2}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    .line 215
    sget v1, Lcom/movieboxpro/androidtv/R$color;->color_main_yellow:I

    invoke-static {v0, v1}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    goto :goto_1

    .line 217
    :cond_0
    sget v2, Lcom/movieboxpro/androidtv/R$color;->white:I

    invoke-static {v1, v2}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    .line 218
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus()Z

    move-result v2

    if-eqz v2, :cond_1

    sget v2, Lcom/movieboxpro/androidtv/R$color;->white:I

    goto :goto_0

    :cond_1
    sget v2, Lcom/movieboxpro/androidtv/R$color;->gray_999:I

    :goto_0
    invoke-static {v0, v2}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    .line 219
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isToday()Z

    move-result v2

    if-eqz v2, :cond_2

    .line 220
    sget v2, Lcom/movieboxpro/androidtv/R$drawable;->yelow_1border_circle_shape:I

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setBackgroundResource(I)V

    goto :goto_1

    .line 222
    :cond_2
    sget v2, Lcom/movieboxpro/androidtv/R$color;->transparent:I

    invoke-static {v2}, Lcom/movieboxpro/android/utils/CommonExtKt;->colorInt(I)I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setBackgroundColor(I)V

    .line 225
    :goto_1
    invoke-virtual {p2}, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 226
    invoke-static {p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->visible(Landroid/view/View;)V

    .line 227
    sget p1, Lcom/movieboxpro/androidtv/R$color;->color_main_yellow:I

    invoke-static {v0, p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->textColor(Landroid/widget/TextView;I)V

    goto :goto_2

    .line 229
    :cond_3
    invoke-static {p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->gone(Landroid/view/View;)V

    :goto_2
    return-void
.end method

.method private final findDates(Ljava/util/Date;Ljava/util/Date;)Ljava/util/List;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Date;",
            "Ljava/util/Date;",
            ")",
            "Ljava/util/List<",
            "Ljava/util/Date;",
            ">;"
        }
    .end annotation

    .line 300
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 301
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 302
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v1

    .line 303
    invoke-virtual {v1, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    .line 304
    :goto_0
    invoke-virtual {v1}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/util/Date;->after(Ljava/util/Date;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x5

    const/4 v2, 0x1

    .line 305
    invoke-virtual {v1, p1, v2}, Ljava/util/Calendar;->add(II)V

    .line 306
    invoke-virtual {v1}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 308
    :cond_0
    check-cast v0, Ljava/util/List;

    return-object v0
.end method

.method private final findDates(Lkotlin/Pair;)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/Pair<",
            "+",
            "Ljava/util/Date;",
            "+",
            "Ljava/util/Date;",
            ">;)",
            "Ljava/util/List<",
            "Ljava/util/Date;",
            ">;"
        }
    .end annotation

    .line 296
    invoke-virtual {p1}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Date;

    invoke-virtual {p1}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/Date;

    invoke-direct {p0, v0, p1}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->findDates(Ljava/util/Date;Ljava/util/Date;)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method

.method private final getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;
    .locals 4

    .line 30
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->binding$delegate:Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;

    move-object v1, p0

    check-cast v1, Landroidx/fragment/app/Fragment;

    sget-object v2, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v3, 0x0

    aget-object v2, v2, v3

    invoke-virtual {v0, v1, v2}, Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;->getValue(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Landroidx/databinding/ViewDataBinding;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    return-object v0
.end method

.method private final getLastTimeInterval(Ljava/util/Date;)Lkotlin/Pair;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Date;",
            ")",
            "Lkotlin/Pair<",
            "Ljava/util/Date;",
            "Ljava/util/Date;",
            ">;"
        }
    .end annotation

    .line 253
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    .line 254
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v1

    .line 255
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    .line 256
    invoke-virtual {v1, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    const/4 p1, 0x7

    .line 257
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->get(I)I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-gtz v2, :cond_0

    goto :goto_0

    :cond_0
    move p1, v2

    :goto_0
    rsub-int/lit8 v2, p1, -0x6

    const/4 v3, 0x5

    .line 261
    invoke-virtual {v0, v3, v2}, Ljava/util/Calendar;->add(II)V

    rsub-int/lit8 p1, p1, 0x0

    .line 262
    invoke-virtual {v1, v3, p1}, Ljava/util/Calendar;->add(II)V

    .line 263
    new-instance p1, Lkotlin/Pair;

    invoke-virtual {v0}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object v0

    invoke-virtual {v1}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object v1

    invoke-direct {p1, v0, v1}, Lkotlin/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p1
.end method

.method private final getTimeInterval(Ljava/util/Date;)Lkotlin/Pair;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Date;",
            ")",
            "Lkotlin/Pair<",
            "Ljava/util/Date;",
            "Ljava/util/Date;",
            ">;"
        }
    .end annotation

    .line 267
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    .line 268
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    const/4 p1, 0x7

    .line 269
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->get(I)I

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x5

    if-ne v2, v1, :cond_0

    const/4 v1, -0x1

    .line 271
    invoke-virtual {v0, v3, v1}, Ljava/util/Calendar;->add(II)V

    :cond_0
    const/4 v1, 0x2

    .line 273
    invoke-virtual {v0, v1}, Ljava/util/Calendar;->setFirstDayOfWeek(I)V

    .line 274
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->get(I)I

    move-result p1

    .line 275
    invoke-virtual {v0}, Ljava/util/Calendar;->getFirstDayOfWeek()I

    move-result v1

    sub-int/2addr v1, p1

    invoke-virtual {v0, v3, v1}, Ljava/util/Calendar;->add(II)V

    .line 276
    invoke-virtual {v0}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object p1

    const/4 v1, 0x6

    .line 277
    invoke-virtual {v0, v3, v1}, Ljava/util/Calendar;->add(II)V

    .line 278
    new-instance v1, Lkotlin/Pair;

    invoke-virtual {v0}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object v0

    invoke-direct {v1, p1, v0}, Lkotlin/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1
.end method

.method private final getTimeNextWeek(Ljava/util/Date;)Lkotlin/Pair;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Date;",
            ")",
            "Lkotlin/Pair<",
            "Ljava/util/Date;",
            "Ljava/util/Date;",
            ">;"
        }
    .end annotation

    .line 282
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    .line 283
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v1

    .line 284
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    .line 285
    invoke-virtual {v1, p1}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    const/4 p1, 0x7

    .line 286
    invoke-virtual {v0, p1}, Ljava/util/Calendar;->get(I)I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    if-gtz v2, :cond_0

    goto :goto_0

    :cond_0
    move p1, v2

    :goto_0
    rsub-int/lit8 v2, p1, 0x8

    const/4 v3, 0x5

    .line 290
    invoke-virtual {v0, v3, v2}, Ljava/util/Calendar;->add(II)V

    rsub-int/lit8 p1, p1, 0xe

    .line 291
    invoke-virtual {v1, v3, p1}, Ljava/util/Calendar;->add(II)V

    .line 292
    new-instance p1, Lkotlin/Pair;

    invoke-virtual {v0}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object v0

    invoke-virtual {v1}, Ljava/util/Calendar;->getTime()Ljava/util/Date;

    move-result-object v1

    invoke-direct {p1, v0, v1}, Lkotlin/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p1
.end method

.method private final getWeekDayLetter(I)Ljava/lang/String;
    .locals 2

    .line 240
    const-string v0, "T"

    const-string v1, "S"

    packed-switch p1, :pswitch_data_0

    .line 248
    const-string v0, ""

    goto :goto_0

    .line 246
    :pswitch_0
    const-string v0, "F"

    goto :goto_0

    .line 244
    :pswitch_1
    const-string v0, "W"

    goto :goto_0

    .line 242
    :pswitch_2
    const-string v0, "M"

    goto :goto_0

    :pswitch_3
    move-object v0, v1

    :goto_0
    :pswitch_4
    return-object v0

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_3
        :pswitch_2
        :pswitch_4
        :pswitch_1
        :pswitch_4
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method private static final initData$lambda$1(Lkotlin/jvm/internal/Ref$IntRef;Ljava/util/List;Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V
    .locals 1

    .line 105
    iget v0, p0, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    invoke-static {v0, p1}, Lcom/movieboxpro/android/utils/CommonExtKt;->checkIndexLegal(ILjava/util/List;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 106
    invoke-direct {p2}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object p1

    iget-object p1, p1, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    iget p0, p0, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    invoke-virtual {p1, p0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->scrollToPosition(I)V

    :cond_0
    return-void
.end method

.method private static final initListener$lambda$2(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Lcom/chad/library/adapter/base/BaseQuickAdapter;Landroid/view/View;I)V
    .locals 1

    const-string v0, "<unused var>"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 p1, 0x0

    .line 117
    invoke-direct {p0, p3, p1}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->selectDay(IZ)V

    return-void
.end method

.method private static final initListener$lambda$3(Lcom/movieboxpro/android/tv/home/TvGuideFragment;Landroid/view/View;Z)V
    .locals 6

    if-nez p2, :cond_0

    .line 136
    iget v1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->focusedPosition:I

    const/4 v4, 0x4

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v5}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->setDayFocus$default(Lcom/movieboxpro/android/tv/home/TvGuideFragment;IZLandroid/view/View;ILjava/lang/Object;)V

    :cond_0
    return-void
.end method

.method private static final initListener$lambda$4(Lcom/movieboxpro/android/tv/home/TvGuideFragment;ILandroid/view/View;)Z
    .locals 1

    const/16 p2, 0x21

    const/4 v0, 0x1

    if-eq p1, p2, :cond_1

    const/16 p2, 0x82

    if-eq p1, p2, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    .line 146
    :cond_0
    iget p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->focusedPosition:I

    invoke-direct {p0, p1, v0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->selectDay(IZ)V

    goto :goto_0

    .line 142
    :cond_1
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p0

    new-instance p1, Lcom/movieboxpro/android/event/ListFocusUpEvent;

    invoke-direct {p1}, Lcom/movieboxpro/android/event/ListFocusUpEvent;-><init>()V

    invoke-virtual {p0, p1}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    :goto_0
    return v0
.end method

.method private final isToday(Ljava/util/Calendar;)Z
    .locals 4

    .line 234
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    const/4 v1, 0x1

    .line 235
    invoke-virtual {v0, v1}, Ljava/util/Calendar;->get(I)I

    move-result v2

    invoke-virtual {p1, v1}, Ljava/util/Calendar;->get(I)I

    move-result v3

    if-ne v2, v3, :cond_0

    const/4 v2, 0x6

    .line 236
    invoke-virtual {v0, v2}, Ljava/util/Calendar;->get(I)I

    move-result v0

    invoke-virtual {p1, v2}, Ljava/util/Calendar;->get(I)I

    move-result p1

    if-ne v0, p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    return v1
.end method

.method private final selectDay(IZ)V
    .locals 6

    .line 162
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    const-string v1, "timeLineAdapter"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_0
    invoke-virtual {v0}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getData()Ljava/util/List;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/movieboxpro/android/utils/CommonExtKt;->checkIndexLegal(ILjava/util/List;)Z

    move-result v0

    if-nez v0, :cond_1

    return-void

    .line 165
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v0, :cond_2

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v2

    :cond_2
    invoke-virtual {v0, p1}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getItemOrNull(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/model/TimeLineModel;

    if-nez v0, :cond_3

    return-void

    .line 166
    :cond_3
    iget v3, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    const-string v4, "listFragment"

    if-eq v3, p1, :cond_b

    .line 167
    iget-object v5, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v5, :cond_4

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v5, v2

    :cond_4
    invoke-virtual {v5}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getData()Ljava/util/List;

    move-result-object v5

    invoke-static {v3, v5}, Lcom/movieboxpro/android/utils/CommonExtKt;->checkIndexLegal(ILjava/util/List;)Z

    move-result v3

    if-eqz v3, :cond_8

    .line 168
    iget-object v3, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v3, :cond_5

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_5
    iget v5, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    invoke-virtual {v3, v5}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getItemOrNull(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/movieboxpro/android/model/TimeLineModel;

    if-eqz v3, :cond_6

    const/4 v5, 0x0

    invoke-virtual {v3, v5}, Lcom/movieboxpro/android/model/TimeLineModel;->setSelect(Z)V

    .line 169
    :cond_6
    iget-object v3, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v3, :cond_7

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_7
    iget v5, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    invoke-virtual {v3, v5}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->notifyItemChanged(I)V

    :cond_8
    const/4 v3, 0x1

    .line 171
    invoke-virtual {v0, v3}, Lcom/movieboxpro/android/model/TimeLineModel;->setSelect(Z)V

    .line 172
    iget-object v3, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v3, :cond_9

    invoke-static {v1}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v3, v2

    :cond_9
    invoke-virtual {v3, p1}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->notifyItemChanged(I)V

    .line 173
    iput p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    .line 174
    iget-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-nez p1, :cond_a

    invoke-static {v4}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object p1, v2

    :cond_a
    invoke-virtual {v0}, Lcom/movieboxpro/android/model/TimeLineModel;->getDay()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->refreshData(Ljava/lang/String;)V

    :cond_b
    if-eqz p2, :cond_d

    .line 177
    iget-object p1, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-nez p1, :cond_c

    invoke-static {v4}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_c
    move-object v2, p1

    :goto_0
    invoke-virtual {v2}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->requestListFocus()V

    :cond_d
    return-void
.end method

.method private final setDayFocus(IZLandroid/view/View;)V
    .locals 3

    .line 182
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    const/4 v1, 0x0

    const-string v2, "timeLineAdapter"

    if-nez v0, :cond_0

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_0
    invoke-virtual {v0}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getData()Ljava/util/List;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/movieboxpro/android/utils/CommonExtKt;->checkIndexLegal(ILjava/util/List;)Z

    move-result v0

    if-nez v0, :cond_1

    return-void

    .line 185
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v0, :cond_2

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v0, v1

    :cond_2
    invoke-virtual {v0, p1}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->getItemOrNull(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/model/TimeLineModel;

    if-nez v0, :cond_3

    return-void

    .line 186
    :cond_3
    invoke-virtual {v0, p2}, Lcom/movieboxpro/android/model/TimeLineModel;->setFocus(Z)V

    if-eqz p3, :cond_4

    .line 188
    invoke-direct {p0, p3, v0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->bindFocusViews(Landroid/view/View;Lcom/movieboxpro/android/model/TimeLineModel;)V

    goto :goto_1

    .line 190
    :cond_4
    iget-object p2, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez p2, :cond_5

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_0

    :cond_5
    move-object v1, p2

    :goto_0
    invoke-virtual {v1, p1}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->notifyItemChanged(I)V

    :goto_1
    return-void
.end method

.method static synthetic setDayFocus$default(Lcom/movieboxpro/android/tv/home/TvGuideFragment;IZLandroid/view/View;ILjava/lang/Object;)V
    .locals 0

    and-int/lit8 p4, p4, 0x4

    if-eqz p4, :cond_0

    const/4 p3, 0x0

    .line 181
    :cond_0
    invoke-direct {p0, p1, p2, p3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->setDayFocus(IZLandroid/view/View;)V

    return-void
.end method


# virtual methods
.method public initData()V
    .locals 21

    move-object/from16 v0, p0

    .line 61
    new-instance v1, Lkotlin/jvm/internal/Ref$IntRef;

    invoke-direct {v1}, Lkotlin/jvm/internal/Ref$IntRef;-><init>()V

    .line 62
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 63
    new-instance v3, Ljava/util/Date;

    invoke-direct {v3}, Ljava/util/Date;-><init>()V

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getLastTimeInterval(Ljava/util/Date;)Lkotlin/Pair;

    move-result-object v3

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->findDates(Lkotlin/Pair;)Ljava/util/List;

    move-result-object v3

    check-cast v3, Ljava/util/Collection;

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 64
    new-instance v3, Ljava/util/Date;

    invoke-direct {v3}, Ljava/util/Date;-><init>()V

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getTimeInterval(Ljava/util/Date;)Lkotlin/Pair;

    move-result-object v3

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->findDates(Lkotlin/Pair;)Ljava/util/List;

    move-result-object v3

    check-cast v3, Ljava/util/Collection;

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 65
    new-instance v3, Ljava/util/Date;

    invoke-direct {v3}, Ljava/util/Date;-><init>()V

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getTimeNextWeek(Ljava/util/Date;)Lkotlin/Pair;

    move-result-object v3

    invoke-direct {v0, v3}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->findDates(Lkotlin/Pair;)Ljava/util/List;

    move-result-object v3

    check-cast v3, Ljava/util/Collection;

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 67
    check-cast v2, Ljava/lang/Iterable;

    .line 319
    new-instance v3, Ljava/util/ArrayList;

    const/16 v4, 0xa

    invoke-static {v2, v4}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    check-cast v3, Ljava/util/Collection;

    .line 321
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    const-string v4, ""

    const/4 v5, 0x0

    const/4 v6, 0x0

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v8, v6, 0x1

    if-gez v6, :cond_0

    .line 322
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_0
    check-cast v7, Ljava/util/Date;

    .line 68
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v9

    .line 69
    invoke-virtual {v9, v7}, Ljava/util/Calendar;->setTime(Ljava/util/Date;)V

    .line 70
    sget-object v7, Lcom/movieboxpro/android/utils/TimeUtils;->INSTANCE:Lcom/movieboxpro/android/utils/TimeUtils;

    invoke-virtual {v9}, Ljava/util/Calendar;->getTimeInMillis()J

    move-result-wide v10

    invoke-virtual {v7, v10, v11}, Lcom/movieboxpro/android/utils/TimeUtils;->formatTime2(J)Ljava/lang/String;

    move-result-object v7

    .line 71
    invoke-static {v9}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    invoke-direct {v0, v9}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->isToday(Ljava/util/Calendar;)Z

    move-result v10

    const/4 v11, 0x7

    const/4 v12, 0x5

    if-eqz v10, :cond_1

    .line 72
    iput v6, v1, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    .line 74
    new-instance v4, Lcom/movieboxpro/android/model/TimeLineModel;

    .line 75
    invoke-virtual {v9, v12}, Ljava/util/Calendar;->get(I)I

    move-result v13

    .line 76
    invoke-virtual {v9, v11}, Ljava/util/Calendar;->get(I)I

    move-result v6

    invoke-direct {v0, v6}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getWeekDayLetter(I)Ljava/lang/String;

    move-result-object v14

    const/16 v19, 0x20

    const/16 v20, 0x0

    const/16 v16, 0x1

    const/16 v17, 0x1

    const/16 v18, 0x0

    move-object v12, v4

    move-object v15, v7

    .line 74
    invoke-direct/range {v12 .. v20}, Lcom/movieboxpro/android/model/TimeLineModel;-><init>(ILjava/lang/String;Ljava/lang/String;ZZZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    goto :goto_1

    .line 82
    :cond_1
    new-instance v6, Lcom/movieboxpro/android/model/TimeLineModel;

    .line 83
    invoke-virtual {v9, v12}, Ljava/util/Calendar;->get(I)I

    move-result v13

    .line 84
    invoke-virtual {v9, v11}, Ljava/util/Calendar;->get(I)I

    move-result v9

    invoke-direct {v0, v9}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getWeekDayLetter(I)Ljava/lang/String;

    move-result-object v14

    const/16 v19, 0x38

    const/16 v20, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    move-object v12, v6

    move-object v15, v7

    .line 82
    invoke-direct/range {v12 .. v20}, Lcom/movieboxpro/android/model/TimeLineModel;-><init>(ILjava/lang/String;Ljava/lang/String;ZZZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v7, v4

    move-object v4, v6

    .line 322
    :goto_1
    invoke-interface {v3, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    move-object v4, v7

    move v6, v8

    goto :goto_0

    .line 323
    :cond_2
    check-cast v3, Ljava/util/List;

    .line 89
    iget v2, v1, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    iput v2, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->lastSelectedPosition:I

    .line 90
    iget v2, v1, Lkotlin/jvm/internal/Ref$IntRef;->element:I

    iput v2, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->focusedPosition:I

    .line 93
    sget v2, Lcom/movieboxpro/androidtv/R$layout;->adapter_time_line_item:I

    .line 94
    new-instance v6, Ljava/util/ArrayList;

    move-object v7, v3

    check-cast v7, Ljava/util/Collection;

    invoke-direct {v6, v7}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 92
    new-instance v7, Lcom/movieboxpro/android/tv/home/TvGuideFragment$initData$1;

    invoke-direct {v7, v0, v2, v6}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$initData$1;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideFragment;ILjava/util/ArrayList;)V

    check-cast v7, Lcom/chad/library/adapter/base/BaseQuickAdapter;

    iput-object v7, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    .line 101
    invoke-direct/range {p0 .. p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v2

    iget-object v2, v2, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    const/4 v6, 0x1

    invoke-virtual {v2, v6}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setSelectedItemAtCentered(Z)V

    .line 102
    invoke-direct/range {p0 .. p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v2

    iget-object v2, v2, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v6, Lcom/movieboxpro/android/view/widget/LayoutManagerItemDecoration;

    const/16 v7, 0x10

    invoke-direct {v6, v5, v7}, Lcom/movieboxpro/android/view/widget/LayoutManagerItemDecoration;-><init>(II)V

    check-cast v6, Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;

    invoke-virtual {v2, v6}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->addItemDecoration(Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;)V

    .line 103
    invoke-direct/range {p0 .. p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v2

    iget-object v2, v2, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    iget-object v5, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    const/4 v6, 0x0

    if-nez v5, :cond_3

    const-string v5, "timeLineAdapter"

    invoke-static {v5}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v5, v6

    :cond_3
    check-cast v5, Landroidx/recyclerview/widget/RecyclerView$Adapter;

    invoke-virtual {v2, v5}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    .line 104
    invoke-direct/range {p0 .. p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v2

    iget-object v2, v2, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v5, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda3;

    invoke-direct {v5, v1, v3, v0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda3;-><init>(Lkotlin/jvm/internal/Ref$IntRef;Ljava/util/List;Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    invoke-virtual {v2, v5}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->post(Ljava/lang/Runnable;)Z

    .line 110
    sget-object v1, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->Companion:Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;

    invoke-virtual {v1, v4}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;->newInstance(Ljava/lang/String;)Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    move-result-object v1

    iput-object v1, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    .line 111
    const-string v2, "listFragment"

    if-nez v1, :cond_4

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    move-object v1, v6

    :cond_4
    move-object v3, v0

    check-cast v3, Lcom/movieboxpro/android/listener/OnVideoListFocusListener;

    invoke-virtual {v1, v3}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->setFocusUpListener(Lcom/movieboxpro/android/listener/OnVideoListFocusListener;)V

    .line 112
    invoke-virtual/range {p0 .. p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getChildFragmentManager()Landroidx/fragment/app/FragmentManager;

    move-result-object v1

    iget-object v3, v0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-nez v3, :cond_5

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    goto :goto_2

    :cond_5
    move-object v6, v3

    :goto_2
    check-cast v6, Landroidx/fragment/app/Fragment;

    sget v2, Lcom/movieboxpro/androidtv/R$id;->frameLayout:I

    invoke-static {v1, v6, v2}, Lcom/movieboxpro/android/utils/FragmentUtils;->add(Landroidx/fragment/app/FragmentManager;Landroidx/fragment/app/Fragment;I)V

    return-void
.end method

.method public initListener()V
    .locals 2

    .line 116
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->timeLineAdapter:Lcom/chad/library/adapter/base/BaseQuickAdapter;

    if-nez v0, :cond_0

    const-string v0, "timeLineAdapter"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    new-instance v1, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda0;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    invoke-virtual {v0, v1}, Lcom/chad/library/adapter/base/BaseQuickAdapter;->setOnItemClickListener(Lcom/chad/library/adapter/base/listener/OnItemClickListener;)V

    .line 119
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v0

    iget-object v0, v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v1, Lcom/movieboxpro/android/tv/home/TvGuideFragment$initListener$2;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$initListener$2;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    check-cast v1, Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnItemListener;

    invoke-virtual {v0, v1}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setOnItemListener(Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnItemListener;)V

    .line 134
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v0

    iget-object v0, v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v1, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda1;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    invoke-virtual {v0, v1}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    .line 139
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v0

    iget-object v0, v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    new-instance v1, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda2;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment$$ExternalSyntheticLambda2;-><init>(Lcom/movieboxpro/android/tv/home/TvGuideFragment;)V

    invoke-virtual {v0, v1}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->setOnInBorderKeyEventListener(Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnInBorderKeyEventListener;)V

    return-void
.end method

.method public initView()V
    .locals 0

    return-void
.end method

.method public final onBackPressed()Z
    .locals 2

    .line 45
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->isAdded()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getView()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-nez v0, :cond_0

    goto :goto_0

    .line 48
    :cond_0
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v0

    iget-object v0, v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    invoke-virtual {v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->hasFocus()Z

    move-result v0

    if-eqz v0, :cond_1

    return v1

    .line 51
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-nez v0, :cond_2

    const-string v0, "listFragment"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_2
    invoke-virtual {v0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->scrollToTop()V

    .line 52
    invoke-virtual {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->onFocusUp()V

    const/4 v0, 0x1

    return v0

    :cond_3
    :goto_0
    return v1
.end method

.method public onFocus()V
    .locals 0

    return-void
.end method

.method public onFocusUp()V
    .locals 1

    .line 155
    invoke-direct {p0}, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;

    move-result-object v0

    iget-object v0, v0, Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;->rvTimeLine:Lcom/owen/tvrecyclerview/widget/TvRecyclerView;

    invoke-virtual {v0}, Lcom/owen/tvrecyclerview/widget/TvRecyclerView;->requestFocus()Z

    return-void
.end method

.method public final refreshData()V
    .locals 1

    .line 39
    iget-object v0, p0, Lcom/movieboxpro/android/tv/home/TvGuideFragment;->listFragment:Lcom/movieboxpro/android/tv/home/TvGuideListFragment;

    if-eqz v0, :cond_1

    if-nez v0, :cond_0

    .line 40
    const-string v0, "listFragment"

    invoke-static {v0}, Lkotlin/jvm/internal/Intrinsics;->throwUninitializedPropertyAccessException(Ljava/lang/String;)V

    const/4 v0, 0x0

    :cond_0
    invoke-virtual {v0}, Lcom/movieboxpro/android/tv/home/TvGuideListFragment;->startRefresh()V

    :cond_1
    return-void
.end method
