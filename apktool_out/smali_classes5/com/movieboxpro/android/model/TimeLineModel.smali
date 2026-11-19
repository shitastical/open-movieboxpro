.class public final Lcom/movieboxpro/android/model/TimeLineModel;
.super Ljava/lang/Object;
.source "TimeLineModel.kt"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0002\u0008\u0019\u0008\u0086\u0008\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u0008\u0012\u0008\u0008\u0002\u0010\t\u001a\u00020\u0008\u0012\u0008\u0008\u0002\u0010\n\u001a\u00020\u0008\u00a2\u0006\u0004\u0008\u000b\u0010\u000cJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0008H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0008H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0008H\u00c6\u0003JE\u0010\u001c\u001a\u00020\u00002\u0008\u0008\u0002\u0010\u0002\u001a\u00020\u00032\u0008\u0008\u0002\u0010\u0004\u001a\u00020\u00052\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u00052\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u00082\u0008\u0008\u0002\u0010\t\u001a\u00020\u00082\u0008\u0008\u0002\u0010\n\u001a\u00020\u0008H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u00082\u0008\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0008\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0007\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0008X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\t\u0010\u0012\"\u0004\u0008\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0008X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\n\u0010\u0012\"\u0004\u0008\u0015\u0010\u0014\u00a8\u0006!"
    }
    d2 = {
        "Lcom/movieboxpro/android/model/TimeLineModel;",
        "",
        "dayNumber",
        "",
        "weekCode",
        "",
        "day",
        "isToday",
        "",
        "isSelect",
        "isFocus",
        "<init>",
        "(ILjava/lang/String;Ljava/lang/String;ZZZ)V",
        "getDayNumber",
        "()I",
        "getWeekCode",
        "()Ljava/lang/String;",
        "getDay",
        "()Z",
        "setSelect",
        "(Z)V",
        "setFocus",
        "component1",
        "component2",
        "component3",
        "component4",
        "component5",
        "component6",
        "copy",
        "equals",
        "other",
        "hashCode",
        "toString",
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
.field private final day:Ljava/lang/String;

.field private final dayNumber:I

.field private isFocus:Z

.field private isSelect:Z

.field private final isToday:Z

.field private final weekCode:Ljava/lang/String;


# direct methods
.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;ZZZ)V
    .locals 1

    const-string v0, "weekCode"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "day"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 4
    iput p1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    .line 5
    iput-object p2, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    .line 6
    iput-object p3, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    .line 7
    iput-boolean p4, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    .line 8
    iput-boolean p5, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    .line 9
    iput-boolean p6, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    return-void
.end method

.method public synthetic constructor <init>(ILjava/lang/String;Ljava/lang/String;ZZZILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 9

    and-int/lit8 v0, p7, 0x8

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    const/4 v6, 0x0

    goto :goto_0

    :cond_0
    move v6, p4

    :goto_0
    and-int/lit8 v0, p7, 0x10

    if-eqz v0, :cond_1

    const/4 v7, 0x0

    goto :goto_1

    :cond_1
    move v7, p5

    :goto_1
    and-int/lit8 v0, p7, 0x20

    if-eqz v0, :cond_2

    const/4 v8, 0x0

    goto :goto_2

    :cond_2
    move v8, p6

    :goto_2
    move-object v2, p0

    move v3, p1

    move-object v4, p2

    move-object v5, p3

    .line 3
    invoke-direct/range {v2 .. v8}, Lcom/movieboxpro/android/model/TimeLineModel;-><init>(ILjava/lang/String;Ljava/lang/String;ZZZ)V

    return-void
.end method

.method public static synthetic copy$default(Lcom/movieboxpro/android/model/TimeLineModel;ILjava/lang/String;Ljava/lang/String;ZZZILjava/lang/Object;)Lcom/movieboxpro/android/model/TimeLineModel;
    .locals 4

    and-int/lit8 p8, p7, 0x1

    if-eqz p8, :cond_0

    iget p1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    :cond_0
    and-int/lit8 p8, p7, 0x2

    if-eqz p8, :cond_1

    iget-object p2, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    :cond_1
    move-object p8, p2

    and-int/lit8 p2, p7, 0x4

    if-eqz p2, :cond_2

    iget-object p3, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    :cond_2
    move-object v0, p3

    and-int/lit8 p2, p7, 0x8

    if-eqz p2, :cond_3

    iget-boolean p4, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    :cond_3
    move v1, p4

    and-int/lit8 p2, p7, 0x10

    if-eqz p2, :cond_4

    iget-boolean p5, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    :cond_4
    move v2, p5

    and-int/lit8 p2, p7, 0x20

    if-eqz p2, :cond_5

    iget-boolean p6, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    :cond_5
    move v3, p6

    move-object p2, p0

    move p3, p1

    move-object p4, p8

    move-object p5, v0

    move p6, v1

    move p7, v2

    move p8, v3

    invoke-virtual/range {p2 .. p8}, Lcom/movieboxpro/android/model/TimeLineModel;->copy(ILjava/lang/String;Ljava/lang/String;ZZZ)Lcom/movieboxpro/android/model/TimeLineModel;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final component1()I
    .locals 1

    iget v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    return v0
.end method

.method public final component2()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    return-object v0
.end method

.method public final component3()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    return-object v0
.end method

.method public final component4()Z
    .locals 1

    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    return v0
.end method

.method public final component5()Z
    .locals 1

    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    return v0
.end method

.method public final component6()Z
    .locals 1

    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    return v0
.end method

.method public final copy(ILjava/lang/String;Ljava/lang/String;ZZZ)Lcom/movieboxpro/android/model/TimeLineModel;
    .locals 8

    const-string v0, "weekCode"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "day"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    new-instance v0, Lcom/movieboxpro/android/model/TimeLineModel;

    move-object v1, v0

    move v2, p1

    move-object v3, p2

    move-object v4, p3

    move v5, p4

    move v6, p5

    move v7, p6

    invoke-direct/range {v1 .. v7}, Lcom/movieboxpro/android/model/TimeLineModel;-><init>(ILjava/lang/String;Ljava/lang/String;ZZZ)V

    return-object v0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/movieboxpro/android/model/TimeLineModel;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/movieboxpro/android/model/TimeLineModel;

    iget v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    iget v3, p1, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    iget-object v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    iget-object v3, p1, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    return v2

    :cond_3
    iget-object v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    iget-object v3, p1, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    return v2

    :cond_4
    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    iget-boolean v3, p1, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    if-eq v1, v3, :cond_5

    return v2

    :cond_5
    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    iget-boolean v3, p1, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    if-eq v1, v3, :cond_6

    return v2

    :cond_6
    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    iget-boolean p1, p1, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    if-eq v1, p1, :cond_7

    return v2

    :cond_7
    return v0
.end method

.method public final getDay()Ljava/lang/String;
    .locals 1

    .line 6
    iget-object v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    return-object v0
.end method

.method public final getDayNumber()I
    .locals 1

    .line 4
    iget v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    return v0
.end method

.method public final getWeekCode()Ljava/lang/String;
    .locals 1

    .line 5
    iget-object v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    return-object v0
.end method

.method public hashCode()I
    .locals 2

    iget v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    invoke-static {v1}, Lcom/movieboxpro/android/db/entity/HlsDownload$$ExternalSyntheticBackport0;->m(Z)I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    invoke-static {v1}, Lcom/movieboxpro/android/db/entity/HlsDownload$$ExternalSyntheticBackport0;->m(Z)I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-boolean v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    invoke-static {v1}, Lcom/movieboxpro/android/db/entity/HlsDownload$$ExternalSyntheticBackport0;->m(Z)I

    move-result v1

    add-int/2addr v0, v1

    return v0
.end method

.method public final isFocus()Z
    .locals 1

    .line 9
    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    return v0
.end method

.method public final isSelect()Z
    .locals 1

    .line 8
    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    return v0
.end method

.method public final isToday()Z
    .locals 1

    .line 7
    iget-boolean v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    return v0
.end method

.method public final setFocus(Z)V
    .locals 0

    .line 9
    iput-boolean p1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    return-void
.end method

.method public final setSelect(Z)V
    .locals 0

    .line 8
    iput-boolean p1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 8

    iget v0, p0, Lcom/movieboxpro/android/model/TimeLineModel;->dayNumber:I

    iget-object v1, p0, Lcom/movieboxpro/android/model/TimeLineModel;->weekCode:Ljava/lang/String;

    iget-object v2, p0, Lcom/movieboxpro/android/model/TimeLineModel;->day:Ljava/lang/String;

    iget-boolean v3, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isToday:Z

    iget-boolean v4, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isSelect:Z

    iget-boolean v5, p0, Lcom/movieboxpro/android/model/TimeLineModel;->isFocus:Z

    new-instance v6, Ljava/lang/StringBuilder;

    const-string v7, "TimeLineModel(dayNumber="

    invoke-direct {v6, v7}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", weekCode="

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", day="

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", isToday="

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", isSelect="

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", isFocus="

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ")"

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
