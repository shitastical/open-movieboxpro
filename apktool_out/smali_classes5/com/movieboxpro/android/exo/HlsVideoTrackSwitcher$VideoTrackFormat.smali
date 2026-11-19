.class public final Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;
.super Ljava/lang/Object;
.source "HlsVideoTrackSwitcher.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "VideoTrackFormat"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0013\n\u0002\u0010\u000b\n\u0002\u0008\u0004\u0008\u0086\u0008\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0008\u0008\u0002\u0010\u0008\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J?\u0010\u0017\u001a\u00020\u00002\u0008\u0008\u0002\u0010\u0002\u001a\u00020\u00032\n\u0008\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0008\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u00032\u0008\u0008\u0002\u0010\u0008\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\u0008\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000b\u0010\u000cR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0010\u0010\u000cR\u0011\u0010\u0008\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0011\u0010\u000c\u00a8\u0006\u001d"
    }
    d2 = {
        "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;",
        "",
        "height",
        "",
        "id",
        "",
        "label",
        "width",
        "bitrate",
        "<init>",
        "(ILjava/lang/String;Ljava/lang/String;II)V",
        "getHeight",
        "()I",
        "getId",
        "()Ljava/lang/String;",
        "getLabel",
        "getWidth",
        "getBitrate",
        "component1",
        "component2",
        "component3",
        "component4",
        "component5",
        "copy",
        "equals",
        "",
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
.field private final bitrate:I

.field private final height:I

.field private final id:Ljava/lang/String;

.field private final label:Ljava/lang/String;

.field private final width:I


# direct methods
.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;II)V
    .locals 0

    .line 7
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 8
    iput p1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    .line 9
    iput-object p2, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    .line 10
    iput-object p3, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    .line 11
    iput p4, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    .line 12
    iput p5, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    return-void
.end method

.method public synthetic constructor <init>(ILjava/lang/String;Ljava/lang/String;IIILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 7

    and-int/lit8 p7, p6, 0x8

    const/4 v0, 0x0

    if-eqz p7, :cond_0

    const/4 v5, 0x0

    goto :goto_0

    :cond_0
    move v5, p4

    :goto_0
    and-int/lit8 p4, p6, 0x10

    if-eqz p4, :cond_1

    const/4 v6, 0x0

    goto :goto_1

    :cond_1
    move v6, p5

    :goto_1
    move-object v1, p0

    move v2, p1

    move-object v3, p2

    move-object v4, p3

    .line 7
    invoke-direct/range {v1 .. v6}, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;-><init>(ILjava/lang/String;Ljava/lang/String;II)V

    return-void
.end method

.method public static synthetic copy$default(Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;ILjava/lang/String;Ljava/lang/String;IIILjava/lang/Object;)Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;
    .locals 3

    and-int/lit8 p7, p6, 0x1

    if-eqz p7, :cond_0

    iget p1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    :cond_0
    and-int/lit8 p7, p6, 0x2

    if-eqz p7, :cond_1

    iget-object p2, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    :cond_1
    move-object p7, p2

    and-int/lit8 p2, p6, 0x4

    if-eqz p2, :cond_2

    iget-object p3, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    :cond_2
    move-object v0, p3

    and-int/lit8 p2, p6, 0x8

    if-eqz p2, :cond_3

    iget p4, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    :cond_3
    move v1, p4

    and-int/lit8 p2, p6, 0x10

    if-eqz p2, :cond_4

    iget p5, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    :cond_4
    move v2, p5

    move-object p2, p0

    move p3, p1

    move-object p4, p7

    move-object p5, v0

    move p6, v1

    move p7, v2

    invoke-virtual/range {p2 .. p7}, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->copy(ILjava/lang/String;Ljava/lang/String;II)Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final component1()I
    .locals 1

    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    return v0
.end method

.method public final component2()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    return-object v0
.end method

.method public final component3()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    return-object v0
.end method

.method public final component4()I
    .locals 1

    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    return v0
.end method

.method public final component5()I
    .locals 1

    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    return v0
.end method

.method public final copy(ILjava/lang/String;Ljava/lang/String;II)Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;
    .locals 7

    new-instance v6, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;

    move-object v0, v6

    move v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move v5, p5

    invoke-direct/range {v0 .. v5}, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;-><init>(ILjava/lang/String;Ljava/lang/String;II)V

    return-object v6
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;

    iget v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    iget v3, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    iget-object v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    iget-object v3, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    return v2

    :cond_3
    iget-object v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    iget-object v3, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    return v2

    :cond_4
    iget v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    iget v3, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    if-eq v1, v3, :cond_5

    return v2

    :cond_5
    iget v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    iget p1, p1, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    if-eq v1, p1, :cond_6

    return v2

    :cond_6
    return v0
.end method

.method public final getBitrate()I
    .locals 1

    .line 12
    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    return v0
.end method

.method public final getHeight()I
    .locals 1

    .line 8
    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    return v0
.end method

.method public final getId()Ljava/lang/String;
    .locals 1

    .line 9
    iget-object v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    return-object v0
.end method

.method public final getLabel()Ljava/lang/String;
    .locals 1

    .line 10
    iget-object v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    return-object v0
.end method

.method public final getWidth()I
    .locals 1

    .line 11
    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    return v0
.end method

.method public hashCode()I
    .locals 3

    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    const/4 v1, 0x0

    goto :goto_0

    :cond_0
    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v1

    :goto_0
    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    if-nez v1, :cond_1

    goto :goto_1

    :cond_1
    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v2

    :goto_1
    add-int/2addr v0, v2

    mul-int/lit8 v0, v0, 0x1f

    iget v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    add-int/2addr v0, v1

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 7

    iget v0, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->height:I

    iget-object v1, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->id:Ljava/lang/String;

    iget-object v2, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->label:Ljava/lang/String;

    iget v3, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->width:I

    iget v4, p0, Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;->bitrate:I

    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, "VideoTrackFormat(height="

    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", id="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", label="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", width="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", bitrate="

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ")"

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
