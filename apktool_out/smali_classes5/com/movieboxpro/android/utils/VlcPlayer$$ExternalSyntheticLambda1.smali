.class public final synthetic Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Lorg/videolan/libvlc/MediaPlayer$EventListener;


# instance fields
.field public final synthetic f$0:Lcom/movieboxpro/android/utils/VlcPlayer;

.field public final synthetic f$1:J


# direct methods
.method public synthetic constructor <init>(Lcom/movieboxpro/android/utils/VlcPlayer;J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;->f$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    iput-wide p2, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;->f$1:J

    return-void
.end method


# virtual methods
.method public final onEvent(Lorg/videolan/libvlc/interfaces/AbstractVLCEvent;)V
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;->f$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    iget-wide v1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda1;->f$1:J

    check-cast p1, Lorg/videolan/libvlc/MediaPlayer$Event;

    invoke-static {v0, v1, v2, p1}, Lcom/movieboxpro/android/utils/VlcPlayer;->$r8$lambda$xhH2hCqUc1xgK6KFKYePbIGY13I(Lcom/movieboxpro/android/utils/VlcPlayer;JLorg/videolan/libvlc/MediaPlayer$Event;)V

    return-void
.end method
