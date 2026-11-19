.class public final synthetic Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;


# instance fields
.field public final synthetic f$0:Lkotlinx/coroutines/Job;


# direct methods
.method public synthetic constructor <init>(Lkotlinx/coroutines/Job;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda0;->f$0:Lkotlinx/coroutines/Job;

    return-void
.end method


# virtual methods
.method public final cancel()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/movieboxpro/android/utils/VlcPlayer$$ExternalSyntheticLambda0;->f$0:Lkotlinx/coroutines/Job;

    invoke-static {v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->$r8$lambda$lgaU3PVopevdlA4az9sjQ6-f3Kw(Lkotlinx/coroutines/Job;)V

    return-void
.end method
