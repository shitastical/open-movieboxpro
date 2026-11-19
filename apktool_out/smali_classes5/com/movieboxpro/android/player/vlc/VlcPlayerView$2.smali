.class Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;
.super Ljava/lang/Object;
.source "VlcPlayerView.java"

# interfaces
.implements Landroid/view/View$OnKeyListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initView(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;


# direct methods
.method constructor <init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x8010
        }
        names = {
            "this$0"
        }
    .end annotation

    .line 445
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onKey(Landroid/view/View;ILandroid/view/KeyEvent;)Z
    .locals 6
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "view",
            "i",
            "keyEvent"
        }
    .end annotation

    .line 448
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v0, "mIsShowCC--->"

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    const-string v1, "IjkPlayerView"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p1, 0x4

    const/4 v2, 0x0

    if-eq p2, p1, :cond_22

    const/16 p1, 0x42

    .line 449
    const-string v3, "mIsShowBar--->"

    const-string v4, "mIsShowQuality--->"

    const/4 v5, 0x1

    if-eq p2, p1, :cond_1b

    const/16 p1, 0x52

    if-eq p2, p1, :cond_14

    const/16 p1, 0x55

    if-eq p2, p1, :cond_12

    const/16 p1, 0x59

    if-eq p2, p1, :cond_10

    const/16 p1, 0x5a

    if-eq p2, p1, :cond_d

    packed-switch p2, :pswitch_data_0

    return v2

    .line 549
    :pswitch_0
    const-string p1, "right--->"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 550
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_6

    .line 551
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowCC(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_0

    .line 552
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 553
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleCCSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 556
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 557
    invoke-static {v1, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 558
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleMediaQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 562
    :cond_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowTracker(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 563
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleTrackSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 567
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowLrc(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_3

    .line 568
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 571
    :cond_3
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 572
    const-string p1, "mIsShowBar--->true"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 574
    :cond_4
    const-string p1, "mIsShowBar--->false"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 576
    :goto_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-nez p1, :cond_6

    .line 578
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_5

    return v5

    .line 581
    :cond_5
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmSrtController(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 582
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetclControl(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/constraintlayout/widget/ConstraintLayout;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    .line 583
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetflSubtitleSetting(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/FrameLayout;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 584
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleControlBar()V

    .line 587
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/ImageView;->requestFocus()Z

    .line 588
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;

    move-result-object p1

    invoke-virtual {p1, v5}, Landroid/widget/ImageView;->setFocusableInTouchMode(Z)V

    return v5

    .line 594
    :cond_6
    :pswitch_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-nez p1, :cond_c

    .line 595
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_b

    .line 596
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetllContinue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result p1

    const/16 p3, 0x15

    if-eqz p1, :cond_9

    .line 597
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_7

    return v5

    .line 600
    :cond_7
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetllSeek(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 601
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmHandler(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/os/Handler;

    move-result-object p1

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgethideSeekBarRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 602
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmHandler(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/os/Handler;

    move-result-object p1

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgethideSeekBarRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;

    move-result-object v0

    const-wide/16 v1, 0x7d0

    invoke-virtual {p1, v0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 603
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetplayerSeekBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/widget/AppCompatSeekBar;

    move-result-object p1

    invoke-virtual {p1}, Landroidx/appcompat/widget/AppCompatSeekBar;->requestFocus()Z

    .line 604
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetplayerSeekBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/widget/AppCompatSeekBar;

    move-result-object p1

    invoke-virtual {p1, v5}, Landroidx/appcompat/widget/AppCompatSeekBar;->setFocusableInTouchMode(Z)V

    if-ne p2, p3, :cond_8

    .line 606
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetplayerSeekBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/widget/AppCompatSeekBar;

    move-result-object p1

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p2

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result p2

    add-int/lit16 p2, p2, -0x2710

    invoke-virtual {p1, p2}, Landroidx/appcompat/widget/AppCompatSeekBar;->setProgress(I)V

    goto :goto_1

    .line 608
    :cond_8
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetplayerSeekBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/widget/AppCompatSeekBar;

    move-result-object p1

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p2

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result p2

    add-int/lit16 p2, p2, 0x2710

    invoke-virtual {p1, p2}, Landroidx/appcompat/widget/AppCompatSeekBar;->setProgress(I)V

    .line 610
    :goto_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmHandler(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/os/Handler;

    move-result-object p1

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetseekToPositionRunnable2(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 611
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmHandler(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/os/Handler;

    move-result-object p1

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetseekToPositionRunnable2(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;

    move-result-object p2

    const-wide/16 v0, 0x3e8

    invoke-virtual {p1, p2, v0, v1}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_2

    :cond_9
    const/16 p1, 0x8

    if-ne p2, p3, :cond_a

    .line 614
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p2

    invoke-virtual {p2, v2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->seekTo(I)V

    .line 615
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetllContinue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p2

    invoke-virtual {p2, p1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    goto :goto_2

    :cond_a
    const/16 p3, 0x16

    if-ne p2, p3, :cond_b

    .line 617
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetleftBtnHint(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p2

    if-nez p2, :cond_b

    .line 618
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p2

    new-instance p3, Lcom/movieboxpro/android/event/PlayNextEpisodeEvent;

    invoke-direct {p3}, Lcom/movieboxpro/android/event/PlayNextEpisodeEvent;-><init>()V

    invoke-virtual {p2, p3}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    .line 619
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetllContinue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p2

    invoke-virtual {p2, p1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 620
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1, v2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_hideAllView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V

    :cond_b
    :goto_2
    return v5

    :cond_c
    return v2

    .line 463
    :cond_d
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_f

    .line 464
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p1

    if-eqz p1, :cond_e

    .line 465
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p2

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result p2

    add-int/lit16 p2, p2, 0x2710

    invoke-virtual {p1, p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    :cond_e
    return v5

    :cond_f
    return v2

    .line 457
    :cond_10
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_11

    .line 458
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object p2

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result p2

    add-int/lit16 p2, p2, -0x2710

    invoke-virtual {p1, p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    return v5

    :cond_11
    return v2

    .line 451
    :cond_12
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_13

    .line 452
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-virtual {p1, v2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_togglePlayStatus(Z)V

    return v5

    :cond_13
    return v2

    .line 509
    :cond_14
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_1a

    .line 510
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowCC(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_15

    .line 511
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 512
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleCCSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 515
    :cond_15
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_16

    .line 516
    invoke-static {v1, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 517
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleMediaQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 521
    :cond_16
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowTracker(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_17

    .line 522
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleTrackSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 526
    :cond_17
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowLrc(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_18

    .line 527
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 531
    :cond_18
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-nez p1, :cond_1a

    .line 532
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_19

    return v5

    .line 535
    :cond_19
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmSrtController(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;

    move-result-object p2

    invoke-virtual {p2}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result p2

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 536
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleControlBar()V

    .line 537
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$mRequestmPlayerSeek(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    .line 538
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/ImageView;->requestFocus()Z

    :cond_1a
    return v5

    .line 472
    :cond_1b
    :pswitch_2
    const-string p1, "enter--->"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 473
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getAction()I

    move-result p1

    if-nez p1, :cond_21

    .line 474
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowCC(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_1c

    .line 475
    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 476
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleCCSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 479
    :cond_1c
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_1d

    .line 480
    invoke-static {v1, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 481
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleMediaQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 485
    :cond_1d
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowTracker(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    const-string p2, "mIsShowTracker--->"

    if-eqz p1, :cond_1e

    .line 486
    invoke-static {v1, p2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 487
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleTrackSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 491
    :cond_1e
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowLrc(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_1f

    .line 492
    invoke-static {v1, p2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 493
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$m_toggleSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    return v5

    .line 497
    :cond_1f
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-nez p1, :cond_21

    .line 498
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-static {p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->-$$Nest$fgetisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z

    move-result p1

    if-eqz p1, :cond_20

    return v5

    .line 501
    :cond_20
    invoke-static {v1, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 503
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;->this$0:Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    invoke-virtual {p1, v2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_togglePlayStatus(Z)V

    return v5

    :cond_21
    return v2

    .line 544
    :cond_22
    const-string p1, "back--->"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v2

    :pswitch_data_0
    .packed-switch 0x13
        :pswitch_0
        :pswitch_0
        :pswitch_1
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method
