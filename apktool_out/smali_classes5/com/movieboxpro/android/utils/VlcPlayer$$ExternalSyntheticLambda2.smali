.class public final synthetic Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# instance fields
.field public final synthetic f$0:Lcom/movieboxpro/android/utils/VlcPlayer;


# direct methods
.method public synthetic constructor <init>(Lcom/movieboxpro/android/utils/VlcPlayer;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda2;->f$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda2;->f$0:Lcom/movieboxpro/android/utils/VlcPlayer;

    check-cast p1, Ljava/lang/Long;

    invoke-virtual {p1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    check-cast p2, Lkotlin/jvm/functions/Function0;

    invoke-static {v0, v1, v2, p2}, Lcom/movieboxpro/android/utils/VlcPlayer;->$r8$lambda$FMd9Cj0D6K7lLVALM3Yi0heIMXQ(Lcom/movieboxpro/android/utils/VlcPlayer;JLkotlin/jvm/functions/Function0;)Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;

    move-result-object p1

    return-object p1
.end method
