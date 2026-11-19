.class public Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
.super Landroid/widget/FrameLayout;
.source "VlcPlayerView.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Lcom/movieboxpro/android/player/IPlayerView;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/player/vlc/VlcPlayerView$NetBroadcastReceiver;
    }
.end annotation


# static fields
.field private static final DANMAKU_TAG_ACFUN:I = 0x2be

.field private static final DANMAKU_TAG_BILI:I = 0x2bd

.field private static final DANMAKU_TAG_CUSTOM:I = 0x2bf

.field private static final DEFAULT_HIDE_TIMEOUT:I = 0x1388

.field private static final DEFAULT_QUALITY_TIME:I = 0x12c

.field private static final INTERRUPT_WHEN_PAUSE:I = 0x1f7

.field private static final INTERRUPT_WHEN_PLAY:I = 0x1f6

.field private static final INTERVAL_TIME:I = 0x3e8

.field private static final INVALID_VALUE:I = -0x1

.field private static final MAX_VIDEO_SEEK:I = 0x3e8

.field public static final MEDIA_QUALITY_BD:I = 0x4

.field public static final MEDIA_QUALITY_HIGH:I = 0x2

.field public static final MEDIA_QUALITY_MEDIUM:I = 0x1

.field public static final MEDIA_QUALITY_SMOOTH:I = 0x0

.field public static final MEDIA_QUALITY_SUPER:I = 0x3

.field private static final MSG_ENABLE_ORIENTATION:I = 0x2767

.field private static final MSG_TRY_RELOAD:I = 0x2768

.field private static final MSG_UPDATE_SEEK:I = 0x2766

.field private static final NORMAL_STATUS:I = 0x1f5

.field private static final QUALITY_DRAWABLE_RES:[I

.field private static final TAG:Ljava/lang/String; = "IjkPlayerView"

.field private static final TEST_SPEED_TIME:I = 0x2710

.field public static screenScales:[I


# instance fields
.field private ListTracks:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/dl7/player/model/TrackModel;",
            ">;"
        }
    .end annotation
.end field

.field private QualityListener:Lcom/movieboxpro/android/listener/OnVipClickListener;

.field private audioTrackId:Ljava/lang/String;

.field private audioTrackModels:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/movieboxpro/android/model/AudioTrackModel;",
            ">;"
        }
    .end annotation
.end field

.field private audioTracksDialogFragment:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

.field private bitStream:J

.field private boxType:I

.field private clControl:Landroidx/constraintlayout/widget/ConstraintLayout;

.field private currOriginalSid:Ljava/lang/String;

.field private currSelectServerSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

.field private currSid:Ljava/lang/String;

.field private currSubtitleLanguage:Ljava/lang/String;

.field private currVolume:I

.field private defaultScreenSize:I

.field private firstPlaying:Z

.field private flLastNext:Landroid/widget/FrameLayout;

.field private flSubtitleSetting:Landroid/widget/FrameLayout;

.field private fragmentManager:Landroidx/fragment/app/FragmentManager;

.field getstr:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/dl7/player/model/SRTModel$subTitles;",
            ">;"
        }
    .end annotation
.end field

.field private hideContinueText:Ljava/lang/Runnable;

.field private hidePlayPauseRunnable:Ljava/lang/Runnable;

.field private hideSeekBarRunnable:Ljava/lang/Runnable;

.field private hlsAudioTacks:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;",
            ">;"
        }
    .end annotation
.end field

.field private isAnimation:Z

.field public isFrist:Z

.field private isOpenSubtitleBg:Z

.field private ivHwDecoder:Landroid/widget/ImageView;

.field private ivScreenSize:Landroid/widget/ImageView;

.field private ivSkipImage:Landroid/widget/ImageView;

.field private ivSubtitleBg:Landroid/widget/ImageView;

.field private ivSubtitleDown:Landroid/widget/ImageView;

.field private ivSubtitlePos:Landroid/widget/ImageView;

.field private ivSubtitlePosClose:Landroid/widget/ImageView;

.field private ivSubtitleUp:Landroid/widget/ImageView;

.field private keepControlBarVisible:Z

.field private languages:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/dl7/player/model/languageModel;",
            ">;"
        }
    .end annotation
.end field

.field private lastQualityPos:I

.field private lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

.field private lastSelectedSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

.field private leftBtnHint:Z

.field private listener:Lcom/movieboxpro/android/listener/OnDownloadClickListener;

.field private llContinue:Landroid/widget/LinearLayout;

.field private llEpisodeList:Landroid/widget/LinearLayout;

.field private llLastEpisode:Landroid/widget/LinearLayout;

.field private llNextEpisode:Landroid/widget/LinearLayout;

.field private llSeek:Landroid/widget/LinearLayout;

.field private llSubtitle:Landroid/widget/LinearLayout;

.field private ll_srt_average:Landroid/widget/TextView;

.field private ll_srt_close:Landroid/widget/ImageView;

.field private ll_srt_size:Landroid/widget/RelativeLayout;

.field private ll_srt_speed:Landroid/widget/RelativeLayout;

.field private mAspectOptionsHeight:I

.field private mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

.field private mAudioManager:Landroid/media/AudioManager;

.field protected mBottomCCSize:Landroid/widget/ImageView;

.field protected mBottomCCSpeed:Landroid/widget/ImageView;

.field protected mBottomCCclose:Landroid/widget/ImageView;

.field protected mBottomCClast:Landroid/widget/ImageView;

.field protected mBottomSubtitleColor:Landroid/widget/ImageView;

.field protected mBottomTranslate:Landroid/widget/ImageView;

.field private mCompletionListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnCompletionListener;

.field private mCorrentLanguage:I

.field private mCurBrightness:F

.field private mCurPosition:I

.field public mCurSelectQuality:I

.field private mCurSelectSRT:I

.field private mCurVolume:I

.field private mExitTime:J

.field private mFlMediaTracks:Landroid/widget/ImageView;

.field private mFlReload:Landroid/view/View;

.field private mFlVideoBox:Landroid/widget/FrameLayout;

.field private mGestureDetector:Landroid/view/GestureDetector;

.field private mHandler:Landroid/os/Handler;

.field private mHideBarRunnable:Ljava/lang/Runnable;

.field private mHideSkipTipRunnable:Ljava/lang/Runnable;

.field private mHideTouchViewRunnable:Ljava/lang/Runnable;

.field private mInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

.field private mInitHeight:I

.field private mInterruptPosition:I

.field private mIsAlwaysFullScreen:Z

.field private mIsBufferingStart:Z

.field private mIsForbidOrientation:Z

.field private mIsForbidTouch:Z

.field private mIsFullscreen:Z

.field private mIsNeedRecoverScreen:Z

.field private mIsNetConnected:Z

.field private mIsNeverPlay:Z

.field private mIsPlayComplete:Z

.field private mIsReady:Z

.field private mIsRenderingStart:Z

.field private mIsSeeking:Z

.field private mIsShowBar:Z

.field private mIsShowCC:Z

.field private mIsShowLrc:Z

.field private mIsShowQuality:Z

.field private mIsShowTracker:Z

.field private mIvCancelSkip:Landroid/widget/ImageView;

.field private mIvMediaQuality:Landroid/widget/ImageView;

.field private mIvPlay:Landroid/widget/ImageView;

.field private mIvPlayCircle:Landroid/widget/ImageView;

.field private mIvPlayerLock:Landroid/widget/ImageView;

.field private mList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/dl7/player/utils/SRT;",
            ">;"
        }
    .end annotation
.end field

.field private mLlBottomBar:Landroid/widget/LinearLayout;

.field private mLlSkipLayout:Landroid/view/View;

.field private mLoadingView:Landroid/widget/ProgressBar;

.field private mMaxVolume:I

.field private mMediaQualityDesc:[Ljava/lang/String;

.field private mOrientationListener:Landroid/view/OrientationEventListener;

.field private mOutsideInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

.field private mPlayerGestureListener:Landroid/view/GestureDetector$OnGestureListener;

.field private mPlayerSeek:Landroid/widget/SeekBar;

.field public mPlayerThumb:Landroid/widget/ImageView;

.field private mPlayerTouchListener:Landroid/view/View$OnTouchListener;

.field private mQualityData:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/movieboxpro/android/player/MediaQualityInfo;",
            ">;"
        }
    .end annotation
.end field

.field private mSaveMatrix:Landroid/graphics/Matrix;

.field private mScreenUiVisibility:I

.field private final mSeekListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

.field private mSkipPosition:I

.field private mSrtController:Landroid/widget/LinearLayout;

.field private mSrtSelect:Landroid/widget/ImageView;

.field private mSrtSlelct:I

.field protected mSrtsizeAdd:Landroid/widget/ImageView;

.field protected mSrtsizeClose:Landroid/widget/ImageView;

.field protected mSrtsizeText:Landroid/widget/TextView;

.field protected mSrtsizesubstate:Landroid/widget/ImageView;

.field private mTargetPosition:J

.field private mTvCurTime:Landroid/widget/TextView;

.field private mTvDoSkip:Landroid/widget/TextView;

.field private mTvEndTime:Landroid/widget/TextView;

.field private mTvReload:Landroid/widget/TextView;

.field private mTvSkipTime:Landroid/widget/TextView;

.field private mVideoMatrix:Landroid/graphics/Matrix;

.field private mVideoSource:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mVideoStatus:I

.field private mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

.field private mWidthPixels:I

.field private mediaAudioTracks:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lorg/videolan/libvlc/MediaPlayer$TrackDescription;",
            ">;"
        }
    .end annotation
.end field

.field private mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

.field private movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

.field private openSubtitle:Ljava/lang/String;

.field private playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

.field private playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

.field private prefs:Landroid/content/SharedPreferences;

.field private retryCount:I

.field private rlSubtitlePos:Landroid/widget/LinearLayout;

.field private scaleImgRes:[I

.field private seekToPositionRunnable:Ljava/lang/Runnable;

.field private seekToPositionRunnable2:Ljava/lang/Runnable;

.field private showContinueText:Ljava/lang/Runnable;

.field private showOrg:Z

.field private simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

.field private speeds:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private srtList:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/dl7/player/utils/SRT;",
            ">;"
        }
    .end annotation
.end field

.field private srtadd:Landroid/widget/ImageView;

.field private srtsize:I

.field private srtspeed:I

.field private srtsubstate:Landroid/widget/ImageView;

.field private srttext:Landroid/widget/TextView;

.field private stringListHashMap:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/dl7/player/model/SRTModel$subTitles;",
            ">;>;"
        }
    .end annotation
.end field

.field private subtitleContent:Ljava/lang/String;

.field private subtitleDialogFragment:Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

.field private subtitleLine:Landroid/view/View;

.field private tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

.field private testSpeedRunnable:Ljava/lang/Runnable;

.field private tvContinue:Landroid/widget/TextView;

.field private tvSeekBarBottom:Landroid/view/View;

.field private tvSeekBarTop:Landroid/view/View;

.field private tvSkipText:Landroid/widget/TextView;

.field private tvSpeed:Landroid/widget/TextView;

.field private tvTcpSpeed:Landroid/widget/TextView;

.field private tvVideoTitle:Landroid/widget/TextView;

.field private videoPreviewListener:Lcom/movieboxpro/android/listener/VideoPreviewListener;

.field private videoQualityDialogFragment:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;


# direct methods
.method public static synthetic $r8$lambda$2LKXYyMBm2iN4NCJ-Z5sWW1a9EQ(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lcom/avery/subtitle/model/Subtitle;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_init_cc_controller$5(Lcom/avery/subtitle/model/Subtitle;)V

    return-void
.end method

.method public static synthetic $r8$lambda$4-9Na1vCVwCjzX7mzKnax6lqAtQ(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_initView$2(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$6QOeQFe83h6uKzC--r5LyGlaCSs(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_initView$1(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$O8yxPgOkAPJUUuv5w-mCdLR0xAI(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;Z)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_initView$4(Landroid/view/View;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$RvPOIzC2UlxBt5LycX7I3cFI07Y(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;Z)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_initView$3(Landroid/view/View;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$TLRXcGA8t5h4-SdYww9lfcJfZFA(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_init_cc_size$9(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$TesbDpQkag1V261d3ADAkJTKRcY(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_initView$0(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$pDINoT-CMqO_0FBFWct7eolSFAA(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_init_cc_size$8(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ubQ5PLlYpweCCLI1gUQ2UKEntH8(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$_init_cc_controller$6(Landroid/view/View;)V

    return-void
.end method

.method public static synthetic $r8$lambda$zz4QHTjWUIa7XJIAYTqAg1XrHLw(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lambda$initSubtitlePos$7(Landroid/view/View;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$fgetQualityListener(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/listener/OnVipClickListener;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QualityListener:Lcom/movieboxpro/android/listener/OnVipClickListener;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetaudioTracksDialogFragment(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTracksDialogFragment:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetbitStream(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)J
    .locals 2

    iget-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->bitStream:J

    return-wide v0
.end method

.method static bridge synthetic -$$Nest$fgetboxType(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->boxType:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetclControl(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/constraintlayout/widget/ConstraintLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->clControl:Landroidx/constraintlayout/widget/ConstraintLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetcurrOriginalSid(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currOriginalSid:Ljava/lang/String;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetcurrSid(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSid:Ljava/lang/String;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetcurrSubtitleLanguage(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSubtitleLanguage:Ljava/lang/String;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetcurrVolume(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currVolume:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetflLastNext(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/FrameLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flLastNext:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetflSubtitleSetting(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/FrameLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flSubtitleSetting:Landroid/widget/FrameLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetfragmentManager(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/fragment/app/FragmentManager;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgethideContinueText(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideContinueText:Ljava/lang/Runnable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgethideSeekBarRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideSeekBarRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isAnimation:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetivSubtitleUp(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleUp:Landroid/widget/ImageView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetkeepControlBarVisible(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->keepControlBarVisible:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetleftBtnHint(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->leftBtnHint:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetlistener(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/listener/OnDownloadClickListener;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->listener:Lcom/movieboxpro/android/listener/OnDownloadClickListener;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetllContinue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llContinue:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetllSeek(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetllSubtitle(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSubtitle:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetll_srt_average(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_average:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetll_srt_size(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/RelativeLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_size:Landroid/widget/RelativeLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetll_srt_speed(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/RelativeLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_speed:Landroid/widget/RelativeLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmAttachActivity(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/app/AppCompatActivity;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmGestureDetector(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/view/GestureDetector;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mGestureDetector:Landroid/view/GestureDetector;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmHandler(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/os/Handler;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmHideBarRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsForbidTouch(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsFullscreen(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsNetConnected(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNetConnected:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsNeverPlay(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsSeeking(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsSeeking:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsShowCC(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsShowLrc(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowLrc:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsShowQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIsShowTracker(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmIvPlayCircle(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmLlBottomBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmLlSkipLayout(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmLoadingView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ProgressBar;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmOrientationListener(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/view/OrientationEventListener;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmOutsideInfoListener(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOutsideInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmPlayerSeek(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/SeekBar;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmQualityData(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmSaveMatrix(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/graphics/Matrix;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSaveMatrix:Landroid/graphics/Matrix;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmSrtController(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtController:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmSrtSlelct(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSlelct:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetmTargetPosition(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)J
    .locals 2

    iget-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    return-wide v0
.end method

.method static bridge synthetic -$$Nest$fgetmTvCurTime(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvCurTime:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmTvEndTime(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvEndTime:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmVideoMatrix(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/graphics/Matrix;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoMatrix:Landroid/graphics/Matrix;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetmVideoView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/player/vlc/VlcVideoView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetplayerSeekBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroidx/appcompat/widget/AppCompatSeekBar;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetplayerViewCallback(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/listener/PlayerViewCallback;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetretryCount(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->retryCount:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetrlSubtitlePos(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/LinearLayout;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->rlSubtitlePos:Landroid/widget/LinearLayout;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetseekToPositionRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekToPositionRunnable:Ljava/lang/Runnable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetseekToPositionRunnable2(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/lang/Runnable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekToPositionRunnable2:Ljava/lang/Runnable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetsimpleSubtitleView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/avery/subtitle/widget/SimpleSubtitleView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetspeeds(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->speeds:Ljava/util/List;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetsrtList(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetsrtsize(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetsrtspeed(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    iget p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtspeed:I

    return p0
.end method

.method static bridge synthetic -$$Nest$fgetsrtsubstate(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/ImageView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsubstate:Landroid/widget/ImageView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetsrttext(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srttext:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetsubtitleLine(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleLine:Landroid/view/View;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgettcpSpeedDisposable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lio/reactivex/disposables/Disposable;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgettvContinue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvContinue:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgettvSpeed(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSpeed:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgettvTcpSpeed(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Landroid/widget/TextView;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fgetvideoQualityDialogFragment(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;
    .locals 0

    iget-object p0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->videoQualityDialogFragment:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    return-object p0
.end method

.method static bridge synthetic -$$Nest$fputaudioTrackId(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackId:Ljava/lang/String;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputaudioTrackModels(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/util/ArrayList;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputcurrOriginalSid(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currOriginalSid:Ljava/lang/String;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputcurrSelectServerSubtitle(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lcom/dl7/player/model/SRTModel$subTitles;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSelectServerSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputcurrSid(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSid:Ljava/lang/String;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputcurrSubtitleLanguage(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSubtitleLanguage:Ljava/lang/String;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputcurrVolume(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currVolume:I

    return-void
.end method

.method static bridge synthetic -$$Nest$fputisAnimation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isAnimation:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputlastQualityPos(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastQualityPos:I

    return-void
.end method

.method static bridge synthetic -$$Nest$fputlastSelectedSubtitle(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lcom/dl7/player/model/SRTModel$subTitles;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSelectedSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmIsNeedRecoverScreen(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeedRecoverScreen:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmIsNetConnected(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNetConnected:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmIsSeeking(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsSeeking:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmIsShowBar(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmSaveMatrix(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/graphics/Matrix;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSaveMatrix:Landroid/graphics/Matrix;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmTargetPosition(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;J)V
    .locals 0

    iput-wide p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    return-void
.end method

.method static bridge synthetic -$$Nest$fputmediaQualityInfo(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lcom/movieboxpro/android/player/MediaQualityInfo;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputretryCount(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->retryCount:I

    return-void
.end method

.method static bridge synthetic -$$Nest$fputshowOrg(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showOrg:Z

    return-void
.end method

.method static bridge synthetic -$$Nest$fputsrtsize(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    return-void
.end method

.method static bridge synthetic -$$Nest$fputsrtspeed(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtspeed:I

    return-void
.end method

.method static bridge synthetic -$$Nest$fputsubtitleContent(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleContent:Ljava/lang/String;

    return-void
.end method

.method static bridge synthetic -$$Nest$fputtcpSpeedDisposable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lio/reactivex/disposables/Disposable;)V
    .locals 0

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    return-void
.end method

.method static bridge synthetic -$$Nest$mRequestFlVideoBox(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return-void
.end method

.method static bridge synthetic -$$Nest$mRequestmPlayerSeek(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestmPlayerSeek()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_endGesture(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_endGesture()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_handleOrientation(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_handleOrientation(I)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_hideAllView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideAllView(Z)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_hideSkipTip(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideSkipTip()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_hideTouchView(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideTouchView()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_onBrightnessSlide(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;F)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_onBrightnessSlide(F)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_onProgressSlide(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;F)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_onProgressSlide(F)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_onVolumeSlide(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;F)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_onVolumeSlide(F)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_refreshHideRunnable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_refreshHideRunnable()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_setControlBarVisible(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setControlBarVisible(Z)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_setProgress(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)I
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setProgress()I

    move-result p0

    return p0
.end method

.method static bridge synthetic -$$Nest$m_switchStatus(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;II)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_switchStatus(II)V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_toggleCCSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleCCSelect()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_toggleMediaQuality(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleMediaQuality()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_toggleSrtSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleSrtSelect()V

    return-void
.end method

.method static bridge synthetic -$$Nest$m_toggleTrackSelect(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleTrackSelect()V

    return-void
.end method

.method static bridge synthetic -$$Nest$mcalBitStream(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->calBitStream(Ljava/lang/String;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$mformatDelaySecond(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)Ljava/lang/String;
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->formatDelaySecond(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static bridge synthetic -$$Nest$mrequestFocusable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->requestFocusable(Landroid/view/View;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$msaveSizeValue(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->saveSizeValue(I)V

    return-void
.end method

.method static bridge synthetic -$$Nest$msetFocusable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$msetNoFocusable(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/view/View;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setNoFocusable(Landroid/view/View;)V

    return-void
.end method

.method static bridge synthetic -$$Nest$mshowTestSpeedHint(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V
    .locals 0

    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showTestSpeedHint()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 5

    const/4 v0, 0x1

    const/4 v1, 0x4

    const/4 v2, 0x0

    const/4 v3, 0x3

    .line 1908
    filled-new-array {v2, v3, v0, v1}, [I

    move-result-object v0

    sput-object v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->screenScales:[I

    .line 3623
    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_smooth:I

    sget v1, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_medium:I

    sget v2, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_high:I

    sget v3, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_super:I

    sget v4, Lcom/dl7/player/R$mipmap;->ic_media_quality_bd:I

    filled-new-array {v0, v1, v2, v3, v4}, [I

    move-result-object v0

    sput-object v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "context"
        }
    .end annotation

    const/4 v0, 0x0

    .line 319
    invoke-direct {p0, p1, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 8
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "context",
            "attrs"
        }
    .end annotation

    .line 323
    invoke-direct {p0, p1, p2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 208
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    .line 223
    new-instance p2, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$1;

    invoke-direct {p2, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$1;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/4 p2, 0x0

    .line 258
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    const/4 v0, 0x1

    .line 260
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    .line 264
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsPlayComplete:Z

    const-wide/16 v1, -0x1

    .line 268
    iput-wide v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    const/4 v1, -0x1

    .line 270
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 272
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    const/high16 v2, -0x40800000    # -1.0f

    .line 274
    iput v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    .line 284
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    .line 289
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    .line 291
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsAlwaysFullScreen:Z

    const-wide/16 v2, 0x0

    .line 293
    iput-wide v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mExitTime:J

    .line 295
    new-instance v4, Landroid/graphics/Matrix;

    invoke-direct {v4}, Landroid/graphics/Matrix;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoMatrix:Landroid/graphics/Matrix;

    .line 296
    new-instance v4, Landroid/graphics/Matrix;

    invoke-direct {v4}, Landroid/graphics/Matrix;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSaveMatrix:Landroid/graphics/Matrix;

    .line 298
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeedRecoverScreen:Z

    .line 303
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsReady:Z

    .line 306
    const-string v4, "OpenSubtitle"

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->openSubtitle:Ljava/lang/String;

    .line 313
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaAudioTracks:Ljava/util/List;

    const/16 v4, 0x64

    .line 314
    iput v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currVolume:I

    .line 710
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$4;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$4;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideSeekBarRunnable:Ljava/lang/Runnable;

    .line 718
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$5;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$5;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekToPositionRunnable2:Ljava/lang/Runnable;

    .line 1270
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$8;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$8;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSeekListener:Landroid/widget/SeekBar$OnSeekBarChangeListener;

    .line 1317
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$9;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$9;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    .line 1473
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$12;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$12;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hidePlayPauseRunnable:Ljava/lang/Runnable;

    .line 1540
    iput p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastQualityPos:I

    .line 1653
    const-string v4, ""

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackId:Ljava/lang/String;

    .line 1907
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v4

    const-string v5, "screen_scale"

    invoke-virtual {v4, v5, p2}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInt(Ljava/lang/String;I)I

    move-result v4

    iput v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    .line 1909
    sget v4, Lcom/movieboxpro/androidtv/R$mipmap;->ic_screen_adapt:I

    sget v5, Lcom/movieboxpro/androidtv/R$mipmap;->ic_screen_stretch:I

    sget v6, Lcom/movieboxpro/androidtv/R$mipmap;->ic_screen_fill:I

    sget v7, Lcom/movieboxpro/androidtv/R$mipmap;->ic_screen_16_9:I

    filled-new-array {v4, v5, v6, v7}, [I

    move-result-object v4

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->scaleImgRes:[I

    .line 2151
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$18;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$18;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerGestureListener:Landroid/view/GestureDetector$OnGestureListener;

    .line 2243
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$19;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$19;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideTouchViewRunnable:Ljava/lang/Runnable;

    .line 2253
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$20;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$20;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerTouchListener:Landroid/view/View$OnTouchListener;

    .line 2518
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsRenderingStart:Z

    .line 2520
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsBufferingStart:Z

    .line 2523
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$21;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$21;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

    .line 2539
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$22;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$22;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideContinueText:Ljava/lang/Runnable;

    .line 2545
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->firstPlaying:Z

    .line 2660
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->speeds:Ljava/util/List;

    .line 2747
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$24;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$24;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->testSpeedRunnable:Ljava/lang/Runnable;

    .line 2840
    new-instance v4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$26;

    invoke-direct {v4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$26;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekToPositionRunnable:Ljava/lang/Runnable;

    .line 2904
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    .line 2906
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->languages:Ljava/util/List;

    .line 2908
    new-instance v4, Ljava/util/HashMap;

    invoke-direct {v4}, Ljava/util/HashMap;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stringListHashMap:Ljava/util/HashMap;

    .line 2912
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getstr:Ljava/util/List;

    .line 2916
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSlelct:I

    .line 2919
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCorrentLanguage:I

    .line 2921
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectSRT:I

    const/16 v4, 0x10

    .line 2945
    iput v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    .line 2947
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mList:Ljava/util/List;

    .line 2951
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowLrc:Z

    .line 2953
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isFrist:Z

    .line 2955
    iput p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtspeed:I

    .line 3537
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ListTracks:Ljava/util/List;

    .line 3540
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    .line 3628
    new-instance v4, Landroid/util/SparseArray;

    invoke-direct {v4}, Landroid/util/SparseArray;-><init>()V

    iput-object v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoSource:Landroid/util/SparseArray;

    .line 3640
    iput-boolean p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    .line 3642
    iput p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    .line 3646
    iput-wide v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->bitStream:J

    .line 3866
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    .line 3936
    new-instance p2, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$44;

    invoke-direct {p2, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$44;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideSkipTipRunnable:Ljava/lang/Runnable;

    const/16 p2, 0x1f5

    .line 3954
    iput p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoStatus:I

    .line 4004
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->leftBtnHint:Z

    .line 4023
    new-instance p2, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$45;

    invoke-direct {p2, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$45;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showContinueText:Ljava/lang/Runnable;

    .line 324
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initView(Landroid/content/Context;)V

    return-void
.end method

.method private RequestFlVideoBox()V
    .locals 2

    .line 4043
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlVideoBox:Landroid/widget/FrameLayout;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setFocusableInTouchMode(Z)V

    .line 4044
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlVideoBox:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->requestFocus()Z

    return-void
.end method

.method private RequestSrt()V
    .locals 0

    return-void
.end method

.method private RequestmIvPlay()V
    .locals 2

    .line 4049
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setFocusableInTouchMode(Z)V

    .line 4050
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-virtual {v0}, Landroid/widget/ImageView;->requestFocus()Z

    return-void
.end method

.method private RequestmPlayerSeek()V
    .locals 2

    .line 4054
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setFocusableInTouchMode(Z)V

    .line 4055
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    invoke-virtual {v0}, Landroid/widget/SeekBar;->requestFocus()Z

    return-void
.end method

.method private _changeHeight(Z)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isFullscreen"
        }
    .end annotation

    .line 2049
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsAlwaysFullScreen:Z

    if-eqz v0, :cond_0

    return-void

    .line 2052
    :cond_0
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    if-eqz p1, :cond_1

    .line 2055
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mWidthPixels:I

    iput p1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    goto :goto_0

    .line 2058
    :cond_1
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInitHeight:I

    iput p1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 2060
    :goto_0
    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    return-void
.end method

.method private _endGesture()V
    .locals 5

    .line 2499
    iget-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-ltz v4, :cond_0

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v2

    int-to-long v2, v2

    cmp-long v4, v0, v2

    if-eqz v4, :cond_0

    .line 2501
    iget-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    long-to-int v1, v0

    invoke-virtual {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    .line 2502
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    iget-wide v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    const-wide/16 v3, 0x3e8

    mul-long v1, v1, v3

    iget-object v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v3}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v3

    int-to-long v3, v3

    div-long/2addr v1, v3

    long-to-int v2, v1

    invoke-virtual {v0, v2}, Landroid/widget/SeekBar;->setProgress(I)V

    const-wide/16 v0, -0x1

    .line 2503
    iput-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    .line 2506
    :cond_0
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideTouchView()V

    .line 2507
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_refreshHideRunnable()V

    const/4 v0, -0x1

    .line 2508
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    const/high16 v0, -0x40800000    # -1.0f

    .line 2509
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    return-void
.end method

.method private _exit()V
    .locals 1

    .line 2141
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->finish()V

    return-void
.end method

.method private _handleActionBar(Z)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isFullscreen"
        }
    .end annotation

    .line 2033
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getSupportActionBar()Landroidx/appcompat/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    .line 2036
    invoke-virtual {v0}, Landroidx/appcompat/app/ActionBar;->hide()V

    goto :goto_0

    .line 2038
    :cond_0
    invoke-virtual {v0}, Landroidx/appcompat/app/ActionBar;->show()V

    :cond_1
    :goto_0
    return-void
.end method

.method private _handleOrientation(I)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "orientation"
        }
    .end annotation

    .line 1997
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    if-eqz v0, :cond_0

    return-void

    .line 2000
    :cond_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsAlwaysFullScreen:Z

    if-nez v0, :cond_3

    if-ltz p1, :cond_1

    const/16 v0, 0x1e

    if-le p1, v0, :cond_2

    :cond_1
    const/16 v0, 0x14a

    if-lt p1, v0, :cond_5

    .line 2004
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    goto :goto_0

    :cond_3
    const/16 v0, 0x3c

    if-lt p1, v0, :cond_4

    const/16 v0, 0x78

    if-gt p1, v0, :cond_4

    .line 2009
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    goto :goto_0

    :cond_4
    const/16 v0, 0xf0

    if-lt p1, v0, :cond_5

    const/16 v0, 0x12c

    if-gt p1, v0, :cond_5

    .line 2011
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    :cond_5
    :goto_0
    return-void
.end method

.method private _hideAllView(Z)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isTouchLock"
        }
    .end annotation

    const/4 v0, 0x0

    .line 1338
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->startAnimation(Z)V

    .line 1339
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->toggleTopAnimation(Z)V

    if-nez p1, :cond_0

    .line 1342
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1343
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    .line 1346
    :cond_0
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    if-nez p1, :cond_1

    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    if-nez p1, :cond_1

    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    if-nez p1, :cond_1

    .line 1347
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    :cond_1
    return-void
.end method

.method private _hideSkipTip()V
    .locals 3

    .line 3920
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_0

    return-void

    .line 3923
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    invoke-static {v0}, Landroidx/core/view/ViewCompat;->animate(Landroid/view/View;)Landroidx/core/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v1

    neg-int v1, v1

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroidx/core/view/ViewPropertyAnimatorCompat;->translationX(F)Landroidx/core/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroidx/core/view/ViewPropertyAnimatorCompat;->alpha(F)Landroidx/core/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    const-wide/16 v1, 0x1f4

    invoke-virtual {v0, v1, v2}, Landroidx/core/view/ViewPropertyAnimatorCompat;->setDuration(J)Landroidx/core/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$43;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$43;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    .line 3924
    invoke-virtual {v0, v1}, Landroidx/core/view/ViewPropertyAnimatorCompat;->setListener(Landroidx/core/view/ViewPropertyAnimatorListener;)Landroidx/core/view/ViewPropertyAnimatorCompat;

    move-result-object v0

    .line 3929
    invoke-virtual {v0}, Landroidx/core/view/ViewPropertyAnimatorCompat;->start()V

    const/4 v0, -0x1

    .line 3930
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    return-void
.end method

.method private _hideTouchView()V
    .locals 0

    return-void
.end method

.method private _initCCSlect()V
    .locals 0

    .line 2965
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_init_cc_controller()V

    .line 2966
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_init_cc_lrclist()V

    .line 2967
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_init_cc_speed()V

    .line 2968
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_init_cc_size()V

    .line 2969
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_init_cc_list()V

    .line 2970
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->initSubtitlePos()V

    return-void
.end method

.method private _initMediaPlayer()V
    .locals 3

    .line 821
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const-string v1, "audio"

    invoke-virtual {v0, v1}, Landroidx/appcompat/app/AppCompatActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/media/AudioManager;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAudioManager:Landroid/media/AudioManager;

    const/4 v1, 0x3

    .line 822
    invoke-virtual {v0, v1}, Landroid/media/AudioManager;->getStreamMaxVolume(I)I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMaxVolume:I

    .line 835
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    const/16 v1, 0x1706

    .line 836
    invoke-virtual {v0, v1}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 843
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x400

    invoke-virtual {v0, v1}, Landroid/view/Window;->addFlags(I)V

    .line 845
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setOnInfoListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;)V

    .line 847
    new-instance v0, Landroid/view/GestureDetector;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerGestureListener:Landroid/view/GestureDetector$OnGestureListener;

    invoke-direct {v0, v1, v2}, Landroid/view/GestureDetector;-><init>(Landroid/content/Context;Landroid/view/GestureDetector$OnGestureListener;)V

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mGestureDetector:Landroid/view/GestureDetector;

    .line 848
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    .line 851
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$7;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-direct {v0, p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$7;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Landroid/content/Context;)V

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    .line 857
    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    if-eqz v1, :cond_0

    .line 859
    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->disable()V

    :cond_0
    return-void
.end method

.method private _initMediaQuality()V
    .locals 2

    .line 3652
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/dl7/player/R$array;->media_quality:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMediaQualityDesc:[Ljava/lang/String;

    .line 3653
    sget v0, Lcom/movieboxpro/androidtv/R$id;->iv_media_quality:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    .line 3654
    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3655
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    return-void
.end method

.method private _initVideoSkip()V
    .locals 1

    .line 3872
    sget v0, Lcom/dl7/player/R$id;->ll_skip_layout:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    .line 3873
    sget v0, Lcom/dl7/player/R$id;->iv_cancel_skip:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvCancelSkip:Landroid/widget/ImageView;

    .line 3874
    sget v0, Lcom/dl7/player/R$id;->tv_skip_time:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvSkipTime:Landroid/widget/TextView;

    .line 3875
    sget v0, Lcom/dl7/player/R$id;->tv_do_skip:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvDoSkip:Landroid/widget/TextView;

    .line 3876
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvCancelSkip:Landroid/widget/ImageView;

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3877
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvDoSkip:Landroid/widget/TextView;

    invoke-virtual {v0, p0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private _initView(Landroid/content/Context;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "context"
        }
    .end annotation

    .line 349
    instance-of v0, p1, Landroidx/appcompat/app/AppCompatActivity;

    if-eqz v0, :cond_4

    .line 350
    move-object v0, p1

    check-cast v0, Landroidx/appcompat/app/AppCompatActivity;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    .line 354
    sget v0, Lcom/movieboxpro/androidtv/R$layout;->new_layout_vlc_player_view:I

    invoke-static {p1, v0, p0}, Landroid/view/View;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    .line 355
    sget p1, Lcom/movieboxpro/androidtv/R$id;->video_view:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 356
    sget p1, Lcom/movieboxpro/androidtv/R$id;->iv_thumb:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerThumb:Landroid/widget/ImageView;

    .line 357
    sget p1, Lcom/movieboxpro/androidtv/R$id;->pb_loading:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ProgressBar;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    .line 358
    sget p1, Lcom/movieboxpro/androidtv/R$id;->clControl:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroidx/constraintlayout/widget/ConstraintLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->clControl:Landroidx/constraintlayout/widget/ConstraintLayout;

    .line 359
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvSeekBarTop:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSeekBarTop:Landroid/view/View;

    .line 360
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvSeekBarBottom:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSeekBarBottom:Landroid/view/View;

    .line 362
    sget p1, Lcom/movieboxpro/androidtv/R$id;->ic_iv_play:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    .line 363
    sget p1, Lcom/movieboxpro/androidtv/R$id;->flSubtitleSetting:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/FrameLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flSubtitleSetting:Landroid/widget/FrameLayout;

    .line 364
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tv_cur_time:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvCurTime:Landroid/widget/TextView;

    .line 365
    sget p1, Lcom/movieboxpro/androidtv/R$id;->player_seek:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/SeekBar;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    .line 366
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tv_end_time:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvEndTime:Landroid/widget/TextView;

    .line 367
    sget p1, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_bar:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    .line 368
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvTcpSpeed:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    .line 369
    sget p1, Lcom/movieboxpro/androidtv/R$id;->llSeek:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    .line 370
    sget p1, Lcom/movieboxpro/androidtv/R$id;->playerSeekBar:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroidx/appcompat/widget/AppCompatSeekBar;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

    .line 371
    sget p1, Lcom/movieboxpro/androidtv/R$id;->simpleSubtitleView:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Lcom/avery/subtitle/widget/SimpleSubtitleView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    .line 372
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tv_screen_size:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivScreenSize:Landroid/widget/ImageView;

    .line 373
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->scaleImgRes:[I

    array-length v2, v1

    const/4 v3, 0x0

    if-lt v0, v2, :cond_0

    .line 374
    iput v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    .line 375
    aget v0, v1, v3

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 377
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivScreenSize:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 378
    sget p1, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_bg:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    .line 379
    sget p1, Lcom/movieboxpro/androidtv/R$id;->ivHwDecoder:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivHwDecoder:Landroid/widget/ImageView;

    .line 380
    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 381
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvVideoTitle:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvVideoTitle:Landroid/widget/TextView;

    .line 382
    sget p1, Lcom/movieboxpro/androidtv/R$id;->flLastNext:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/FrameLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flLastNext:Landroid/widget/FrameLayout;

    .line 383
    sget p1, Lcom/movieboxpro/androidtv/R$id;->llLastEpisode:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llLastEpisode:Landroid/widget/LinearLayout;

    .line 384
    invoke-virtual {p1, p0}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 385
    sget p1, Lcom/movieboxpro/androidtv/R$id;->llNextEpisode:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llNextEpisode:Landroid/widget/LinearLayout;

    .line 386
    invoke-virtual {p1, p0}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 387
    sget p1, Lcom/movieboxpro/androidtv/R$id;->llEpisodeList:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llEpisodeList:Landroid/widget/LinearLayout;

    .line 388
    invoke-virtual {p1, p0}, Landroid/widget/LinearLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 389
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvChangeVolume:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 390
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 391
    sget p1, Lcom/movieboxpro/androidtv/R$id;->llContinue:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/LinearLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llContinue:Landroid/widget/LinearLayout;

    .line 392
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvContinue:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvContinue:Landroid/widget/TextView;

    .line 393
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvSkipText:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSkipText:Landroid/widget/TextView;

    .line 394
    sget p1, Lcom/movieboxpro/androidtv/R$id;->ivSkipImage:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSkipImage:Landroid/widget/ImageView;

    .line 395
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvSpeed:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSpeed:Landroid/widget/TextView;

    .line 396
    invoke-virtual {p1, p0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 398
    sget p1, Lcom/movieboxpro/androidtv/R$id;->flPlayerEngine:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/FrameLayout;

    .line 399
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda5;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 404
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tvPlayerEngine:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    .line 405
    const-string v0, "VLC"

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 408
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object p1

    const-string v0, "hw_decoder"

    const/4 v1, 0x1

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 410
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivHwDecoder:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_hw_decoder_enable:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    .line 412
    :cond_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivHwDecoder:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_hw_decoder_disable:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 414
    :goto_0
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tv_pic_mode:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    .line 416
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x1a

    if-lt v0, v2, :cond_2

    .line 417
    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 418
    invoke-virtual {p1, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_1

    :cond_2
    const/16 v0, 0x8

    .line 420
    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 423
    :goto_1
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object p1

    const-string v0, "open_subtitle_bg"

    invoke-virtual {p1, v0, v3}, Lcom/movieboxpro/android/utils/PrefsUtils;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isOpenSubtitleBg:Z

    if-eqz p1, :cond_3

    .line 425
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_cc_bg_open:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 426
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    const-string v0, "#B3000000"

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setBackgroundColor(I)V

    goto :goto_2

    .line 428
    :cond_3
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_cc_bg:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 429
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1, v3}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setBackgroundColor(I)V

    .line 431
    :goto_2
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "#"

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string v2, "subtitle_color"

    const-string v3, "FFFFFF"

    invoke-static {v2, v3}, Lcom/movieboxpro/android/utils/tool/SPStaticUtils;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->changeColor(I)V

    .line 432
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda6;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda6;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 444
    sget p1, Lcom/movieboxpro/androidtv/R$id;->fl_video_box:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/FrameLayout;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlVideoBox:Landroid/widget/FrameLayout;

    .line 445
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$2;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 635
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda7;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda7;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroidx/appcompat/widget/AppCompatSeekBar;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 637
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSeekBarTop:Landroid/view/View;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda8;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda8;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    .line 652
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSeekBarBottom:Landroid/view/View;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda9;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda9;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    .line 667
    sget p1, Lcom/movieboxpro/androidtv/R$id;->iv_player_lock:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    .line 668
    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setFocusable(Z)V

    .line 669
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setFocusableInTouchMode(Z)V

    .line 670
    sget p1, Lcom/movieboxpro/androidtv/R$id;->iv_play_circle:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/ImageView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    .line 671
    sget p1, Lcom/movieboxpro/androidtv/R$id;->tv_reload:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/TextView;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvReload:Landroid/widget/TextView;

    .line 672
    sget p1, Lcom/movieboxpro/androidtv/R$id;->fl_reload_layout:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlReload:Landroid/view/View;

    .line 674
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initMediaQuality()V

    .line 675
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initVideoSkip()V

    .line 677
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initCCSlect()V

    .line 678
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initMediaTrack()V

    .line 685
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 686
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 687
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    invoke-virtual {p1, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 689
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvReload:Landroid/widget/TextView;

    invoke-virtual {p1, p0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 691
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$3;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$3;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setOnErrorListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnErrorListener;)V

    return-void

    .line 352
    :cond_4
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string v0, "Context must be AppCompatActivity"

    invoke-direct {p1, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method private _init_cc_controller()V
    .locals 2

    .line 2975
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_srt_controller:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtController:Landroid/widget/LinearLayout;

    .line 2976
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_lastside:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCClast:Landroid/widget/ImageView;

    .line 2977
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_size:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSpeed:Landroid/widget/ImageView;

    .line 2978
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_more:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSize:Landroid/widget/ImageView;

    .line 2979
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_close:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCclose:Landroid/widget/ImageView;

    .line 2980
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_translate:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomTranslate:Landroid/widget/ImageView;

    .line 2981
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_subtitle_color:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomSubtitleColor:Landroid/widget/ImageView;

    .line 2982
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCClast:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2983
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSpeed:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2984
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSize:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2985
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCclose:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2986
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomTranslate:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2987
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 2989
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCClast:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda4;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3013
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSpeed:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$27;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$27;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3025
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSize:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$28;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$28;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3036
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCclose:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$29;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$29;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3053
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomTranslate:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$30;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$30;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3074
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomSubtitleColor:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$31;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$31;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private _init_cc_list()V
    .locals 1

    .line 3122
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_srtcelsect:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    .line 3123
    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3124
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    return-void
.end method

.method private _init_cc_lrclist()V
    .locals 0

    return-void
.end method

.method private _init_cc_size()V
    .locals 3

    .line 3317
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_srtsize:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/RelativeLayout;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_size:Landroid/widget/RelativeLayout;

    .line 3318
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srtsize_add:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeAdd:Landroid/widget/ImageView;

    .line 3319
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srtsize_subtract:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizesubstate:Landroid/widget/ImageView;

    .line 3320
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srtsize_close:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeClose:Landroid/widget/ImageView;

    .line 3321
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srtsize_speed:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeText:Landroid/widget/TextView;

    .line 3322
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 3324
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeClose:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 3328
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getSizeValue()I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    .line 3329
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {v1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->changeSize(I)V

    .line 3330
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$38;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$38;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->bindOnMediaStatusListener(Lcom/avery/subtitle/OnMediaStatusListener;)V

    .line 3353
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeText:Landroid/widget/TextView;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 3355
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeAdd:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$39;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$39;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3365
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizesubstate:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda0;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3372
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeClose:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda1;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method private _init_cc_speed()V
    .locals 2

    .line 3180
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_srt:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/RelativeLayout;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_speed:Landroid/widget/RelativeLayout;

    .line 3181
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srt_subtract:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsubstate:Landroid/widget/ImageView;

    .line 3182
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srt_add:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtadd:Landroid/widget/ImageView;

    .line 3183
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srt_speed:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srttext:Landroid/widget/TextView;

    .line 3184
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srt_average:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_average:Landroid/widget/TextView;

    .line 3185
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_srt_close:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_close:Landroid/widget/ImageView;

    .line 3187
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 3188
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtadd:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setNoFocusable(Landroid/view/View;)V

    .line 3189
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsubstate:Landroid/widget/ImageView;

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setNoFocusable(Landroid/view/View;)V

    .line 3190
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_close:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$35;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$35;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3202
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srttext:Landroid/widget/TextView;

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtspeed:I

    invoke-direct {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->formatDelaySecond(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 3203
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtadd:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$36;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$36;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    .line 3218
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsubstate:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$37;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$37;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    return-void
.end method

.method private _onBrightnessSlide(F)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "percent"
        }
    .end annotation

    .line 2476
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    const v1, 0x3c23d70a    # 0.01f

    const/4 v2, 0x0

    cmpg-float v0, v0, v2

    if-gez v0, :cond_1

    .line 2477
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    iget v0, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    cmpg-float v2, v0, v2

    if-gez v2, :cond_0

    const/high16 v0, 0x3f000000    # 0.5f

    .line 2479
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    goto :goto_0

    :cond_0
    cmpg-float v0, v0, v1

    if-gez v0, :cond_1

    .line 2481
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    .line 2484
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getAttributes()Landroid/view/WindowManager$LayoutParams;

    move-result-object v0

    .line 2485
    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurBrightness:F

    add-float/2addr v2, p1

    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    .line 2486
    iget p1, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    const/high16 v2, 0x3f800000    # 1.0f

    cmpl-float p1, p1, v2

    if-lez p1, :cond_2

    .line 2487
    iput v2, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    goto :goto_1

    .line 2488
    :cond_2
    iget p1, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    cmpg-float p1, p1, v1

    if-gez p1, :cond_3

    .line 2489
    iput v1, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    .line 2491
    :cond_3
    :goto_1
    iget p1, v0, Landroid/view/WindowManager$LayoutParams;->screenBrightness:F

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setBrightnessInfo(F)V

    .line 2492
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {p1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroid/view/Window;->setAttributes(Landroid/view/WindowManager$LayoutParams;)V

    return-void
.end method

.method private _onProgressSlide(F)V
    .locals 11
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "percent"
        }
    .end annotation

    .line 2379
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    .line 2380
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v1

    int-to-long v1, v1

    const-wide/16 v3, 0x2

    .line 2382
    div-long v3, v1, v3

    const-wide/32 v5, 0x186a0

    invoke-static {v5, v6, v3, v4}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v3

    long-to-float v3, v3

    mul-float v3, v3, p1

    float-to-long v3, v3

    int-to-long v5, v0

    add-long/2addr v3, v5

    .line 2386
    iput-wide v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    cmp-long p1, v3, v1

    if-lez p1, :cond_0

    .line 2388
    iput-wide v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    goto :goto_0

    :cond_0
    const-wide/16 v7, 0x0

    cmp-long p1, v3, v7

    if-gtz p1, :cond_1

    .line 2390
    iput-wide v7, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    .line 2392
    :cond_1
    :goto_0
    iget-wide v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    sub-long v7, v3, v5

    const-wide/16 v9, 0x3e8

    div-long/2addr v7, v9

    long-to-int p1, v7

    .line 2395
    const-string v0, "\u79d2"

    const-string v7, "/"

    cmp-long v8, v3, v5

    if-lez v8, :cond_2

    .line 2396
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    iget-wide v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    invoke-static {v4, v5}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v1, v2}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "\n+"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 2398
    :cond_2
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    iget-wide v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTargetPosition:J

    invoke-static {v4, v5}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {v1, v2}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "\n"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 2400
    :goto_1
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setFastForward(Ljava/lang/String;)V

    return-void
.end method

.method private _onVolumeSlide(F)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "percent"
        }
    .end annotation

    .line 2417
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    const/4 v1, -0x1

    const/4 v2, 0x3

    const/4 v3, 0x0

    if-ne v0, v1, :cond_0

    .line 2418
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAudioManager:Landroid/media/AudioManager;

    invoke-virtual {v0, v2}, Landroid/media/AudioManager;->getStreamVolume(I)I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    if-gez v0, :cond_0

    .line 2420
    iput v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    .line 2423
    :cond_0
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMaxVolume:I

    int-to-float v1, v0

    mul-float p1, p1, v1

    float-to-int p1, p1

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurVolume:I

    add-int/2addr p1, v1

    if-le p1, v0, :cond_1

    goto :goto_0

    :cond_1
    if-gez p1, :cond_2

    const/4 v0, 0x0

    goto :goto_0

    :cond_2
    move v0, p1

    .line 2430
    :goto_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAudioManager:Landroid/media/AudioManager;

    invoke-virtual {p1, v2, v0, v3}, Landroid/media/AudioManager;->setStreamVolume(III)V

    .line 2432
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setVolumeInfo(I)V

    return-void
.end method

.method private _refreshHideRunnable()V
    .locals 4

    .line 1515
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1516
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x1388

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private _refreshOrientationEnable()V
    .locals 4

    .line 2020
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    if-nez v0, :cond_0

    .line 2021
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->disable()V

    .line 2022
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v1, 0x2767

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 2023
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const-wide/16 v2, 0xbb8

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    :cond_0
    return-void
.end method

.method private _setBrightnessInfo(F)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "brightness"
        }
    .end annotation

    return-void
.end method

.method private _setControlBarVisible(Z)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isShowBar"
        }
    .end annotation

    .line 1357
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->startAnimation(Z)V

    .line 1358
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->toggleTopAnimation(Z)V

    return-void
.end method

.method private _setFastForward(Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "time"
        }
    .end annotation

    return-void
.end method

.method private _setFullScreen(Z)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isFullscreen"
        }
    .end annotation

    .line 1962
    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    .line 1964
    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_handleActionBar(Z)V

    .line 1966
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 1967
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    if-eqz p1, :cond_0

    const/4 v1, 0x0

    goto :goto_0

    :cond_0
    const/16 v1, 0x8

    :goto_0
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1969
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    if-eqz v0, :cond_1

    if-nez p1, :cond_1

    .line 1970
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleMediaQuality()V

    .line 1972
    :cond_1
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    if-eqz v0, :cond_2

    if-nez p1, :cond_2

    .line 1973
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleCCSelect()V

    .line 1976
    :cond_2
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    if-eqz v0, :cond_3

    if-nez p1, :cond_3

    .line 1977
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleTrackSelect()V

    .line 1980
    :cond_3
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowLrc:Z

    if-eqz v0, :cond_4

    if-nez p1, :cond_4

    .line 1981
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleSrtSelect()V

    :cond_4
    return-void
.end method

.method private _setProgress()I
    .locals 5

    .line 2337
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    if-eqz v0, :cond_2

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsSeeking:Z

    if-eqz v1, :cond_0

    goto :goto_0

    .line 2341
    :cond_0
    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 2343
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v1

    if-lez v1, :cond_1

    .line 2346
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    iget-object v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v3}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v3

    invoke-virtual {v2, v3}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 2347
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    invoke-virtual {v2, v1}, Landroid/widget/SeekBar;->setMax(I)V

    .line 2350
    :cond_1
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getBufferPercentage()I

    move-result v2

    .line 2351
    iget-object v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    mul-int/lit8 v2, v2, 0xa

    invoke-virtual {v3, v2}, Landroid/widget/SeekBar;->setSecondaryProgress(I)V

    .line 2353
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvCurTime:Landroid/widget/TextView;

    int-to-long v3, v0

    invoke-static {v3, v4}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 2354
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvEndTime:Landroid/widget/TextView;

    sub-int/2addr v1, v0

    int-to-long v3, v1

    invoke-static {v3, v4}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return v0

    :cond_2
    :goto_0
    const/4 v0, 0x0

    return v0
.end method

.method private _setUiLayoutFullscreen()V
    .locals 2

    .line 2069
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    const/16 v1, 0x1706

    .line 2071
    invoke-virtual {v0, v1}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 2078
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x400

    invoke-virtual {v0, v1}, Landroid/view/Window;->addFlags(I)V

    return-void
.end method

.method private _setVolume(Z)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isIncrease"
        }
    .end annotation

    .line 2442
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAudioManager:Landroid/media/AudioManager;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/media/AudioManager;->getStreamVolume(I)I

    move-result v0

    if-eqz p1, :cond_0

    .line 2444
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMaxVolume:I

    div-int/lit8 p1, p1, 0xf

    add-int/2addr v0, p1

    goto :goto_0

    .line 2446
    :cond_0
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMaxVolume:I

    div-int/lit8 p1, p1, 0xf

    sub-int/2addr v0, p1

    .line 2448
    :goto_0
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mMaxVolume:I

    const/4 v2, 0x0

    if-le v0, p1, :cond_1

    move v0, p1

    goto :goto_1

    :cond_1
    if-gez v0, :cond_2

    const/4 v0, 0x0

    .line 2454
    :cond_2
    :goto_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAudioManager:Landroid/media/AudioManager;

    invoke-virtual {p1, v1, v0, v2}, Landroid/media/AudioManager;->setStreamVolume(III)V

    .line 2456
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setVolumeInfo(I)V

    .line 2457
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideTouchViewRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 2458
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideTouchViewRunnable:Ljava/lang/Runnable;

    const-wide/16 v1, 0x3e8

    invoke-virtual {p1, v0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method private _setVolumeInfo(I)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "volume"
        }
    .end annotation

    return-void
.end method

.method private _showSkipTip()V
    .locals 4

    .line 3907
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_0

    .line 3908
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 3909
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mTvSkipTime:Landroid/widget/TextView;

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    int-to-long v2, v2

    invoke-static {v2, v3}, Lcom/dl7/player/utils/StringUtils;->generateTime(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 3910
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlSkipLayout:Landroid/view/View;

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mWidthPixels:I

    const/16 v3, 0x320

    invoke-static {v0, v2, v1, v3}, Lcom/dl7/player/utils/AnimHelper;->doSlideRightIn(Landroid/view/View;III)V

    .line 3911
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideSkipTipRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 3912
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideSkipTipRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x3a98

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_0
    return-void
.end method

.method private _switchStatus(II)V
    .locals 6
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "status",
            "extra"
        }
    .end annotation

    .line 2548
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "status "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "IjkPlayerView"

    invoke-static {v1, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v0, 0x3

    const/4 v1, -0x1

    const/4 v2, 0x1

    const/16 v3, 0x8

    const/4 v4, 0x0

    if-eq p1, v0, :cond_8

    const/16 v0, 0x2bd

    const/16 v5, 0x2768

    if-eq p1, v0, :cond_6

    const/16 v0, 0x2be

    if-eq p1, v0, :cond_9

    packed-switch p1, :pswitch_data_0

    goto/16 :goto_1

    :pswitch_0
    if-lez p2, :cond_b

    .line 2565
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 2566
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v4}, Landroid/widget/TextView;->setVisibility(I)V

    .line 2567
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p2, "%"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto/16 :goto_1

    .line 2637
    :pswitch_1
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    .line 2638
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result p1

    if-eq p1, v1, :cond_1

    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 2639
    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getInterruptPosition()I

    move-result p1

    add-int/lit16 p1, p1, 0x3e8

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result p2

    if-ge p1, p2, :cond_0

    goto :goto_0

    .line 2650
    :cond_0
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsPlayComplete:Z

    .line 2651
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCompletionListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnCompletionListener;

    if-eqz p1, :cond_b

    const/4 p2, 0x0

    .line 2652
    invoke-interface {p1, p2}, Ltv/danmaku/ijk/media/player/IMediaPlayer$OnCompletionListener;->onCompletion(Ltv/danmaku/ijk/media/player/IMediaPlayer;)V

    goto/16 :goto_1

    .line 2640
    :cond_1
    :goto_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getInterruptPosition()I

    move-result p1

    iget p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    .line 2648
    const-string p1, "Playback failed"

    invoke-static {p1}, Lcom/movieboxpro/android/utils/ToastUtils;->showShort(Ljava/lang/CharSequence;)V

    goto/16 :goto_1

    .line 2576
    :pswitch_2
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance p2, Lcom/movieboxpro/android/event/OnPlayStatusEvent;

    const/16 v0, 0x14f

    invoke-direct {p2, v0}, Lcom/movieboxpro/android/event/OnPlayStatusEvent;-><init>(I)V

    invoke-virtual {p1, p2}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    goto/16 :goto_1

    .line 2605
    :pswitch_3
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance p2, Lcom/movieboxpro/android/event/OnPlayStatusEvent;

    const/16 v0, 0x14e

    invoke-direct {p2, v0}, Lcom/movieboxpro/android/event/OnPlayStatusEvent;-><init>(I)V

    invoke-virtual {p1, p2}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    .line 2606
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaAudioTracks:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    if-nez p1, :cond_2

    .line 2607
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getAudioTracks()Ljava/util/ArrayList;

    move-result-object p1

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaAudioTracks:Ljava/util/List;

    .line 2609
    :cond_2
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, v5}, Landroid/os/Handler;->removeMessages(I)V

    .line 2610
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->testSpeedRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 2611
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideTestSpeedHint()V

    .line 2612
    iput v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->retryCount:I

    .line 2613
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v3}, Landroid/widget/TextView;->setVisibility(I)V

    .line 2614
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->initSeekBar()V

    .line 2615
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->initSeekBar2()V

    goto/16 :goto_1

    .line 2571
    :pswitch_4
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance p2, Lcom/movieboxpro/android/event/OnPlayStatusEvent;

    const/16 v0, 0x14d

    invoke-direct {p2, v0}, Lcom/movieboxpro/android/event/OnPlayStatusEvent;-><init>(I)V

    invoke-virtual {p1, p2}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    .line 2572
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->start()V

    .line 2573
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsReady:Z

    goto/16 :goto_1

    .line 2618
    :pswitch_5
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getInterruptPosition()I

    move-result p1

    iget p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result p1

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    .line 2619
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    .line 2620
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, v5}, Landroid/os/Handler;->removeMessages(I)V

    .line 2621
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getMediaPlayer()Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 2622
    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getMediaPlayer()Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object p1

    invoke-virtual {p1}, Lcom/movieboxpro/android/utils/VlcPlayer;->isRecoveryExhausted()Z

    move-result p1

    if-nez p1, :cond_4

    :cond_3
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 2623
    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result p1

    if-ne p1, v1, :cond_5

    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsReady:Z

    if-nez p1, :cond_5

    .line 2624
    :cond_4
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v3}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 2625
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v3}, Landroid/widget/TextView;->setVisibility(I)V

    .line 2626
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerThumb:Landroid/widget/ImageView;

    invoke-virtual {p1, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 2627
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    invoke-virtual {p1, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 2628
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlReload:Landroid/view/View;

    invoke-virtual {p1, v4}, Landroid/view/View;->setVisibility(I)V

    goto/16 :goto_1

    .line 2630
    :cond_5
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 2631
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v4}, Landroid/widget/TextView;->setVisibility(I)V

    .line 2632
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, v5}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    goto/16 :goto_1

    .line 2551
    :cond_6
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsBufferingStart:Z

    .line 2552
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    if-nez p1, :cond_7

    .line 2553
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 2555
    :cond_7
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->firstPlaying:Z

    .line 2556
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlReload:Landroid/view/View;

    invoke-virtual {p1, v3}, Landroid/view/View;->setVisibility(I)V

    .line 2557
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, v5}, Landroid/os/Handler;->removeMessages(I)V

    .line 2558
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->testSpeedRunnable:Ljava/lang/Runnable;

    const-wide/16 v0, 0x2710

    invoke-virtual {p1, p2, v0, v1}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 2561
    :pswitch_6
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance p2, Lcom/movieboxpro/android/event/OnPlayStatusEvent;

    const/16 v0, 0x14c

    invoke-direct {p2, v0}, Lcom/movieboxpro/android/event/OnPlayStatusEvent;-><init>(I)V

    invoke-virtual {p1, p2}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    goto :goto_1

    .line 2579
    :cond_8
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsRenderingStart:Z

    .line 2581
    :cond_9
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance p2, Lcom/movieboxpro/android/event/OnPlayStatusEvent;

    const/16 v0, 0x190

    invoke-direct {p2, v0}, Lcom/movieboxpro/android/event/OnPlayStatusEvent;-><init>(I)V

    invoke-virtual {p1, p2}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    .line 2582
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    const-string p2, "0%"

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 2583
    iput-boolean v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsBufferingStart:Z

    .line 2584
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v3}, Landroid/widget/TextView;->setVisibility(I)V

    .line 2585
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v3}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 2586
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerThumb:Landroid/widget/ImageView;

    invoke-virtual {p1, v3}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 2588
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 p2, 0x2766

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeMessages(I)V

    .line 2589
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, p2}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 2590
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    if-eq p1, v1, :cond_a

    .line 2591
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_showSkipTip()V

    .line 2593
    :cond_a
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setSelected(Z)V

    .line 2594
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->isPlaying()Z

    move-result p1

    if-eqz p1, :cond_b

    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNetConnected:Z

    if-eqz p1, :cond_b

    .line 2595
    iput v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    .line 2596
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->isSelected()Z

    move-result p1

    if-nez p1, :cond_b

    .line 2598
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->start()V

    .line 2599
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setSelected(Z)V

    :cond_b
    :goto_1
    return-void

    :pswitch_data_0
    .packed-switch 0x14b
        :pswitch_5
        :pswitch_6
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private _toggleCCSelect()V
    .locals 0

    return-void
.end method

.method private _toggleFullScreen()V
    .locals 2

    .line 1949
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-static {v0}, Lcom/dl7/player/utils/WindowUtils;->getScreenOrientation(Landroid/app/Activity;)I

    move-result v0

    if-nez v0, :cond_0

    .line 1950
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    goto :goto_0

    .line 1952
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    :goto_0
    return-void
.end method

.method private _toggleMediaQuality()V
    .locals 5

    const/4 v0, 0x0

    .line 1544
    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->videoQualityDialogFragment:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    .line 1545
    sget-object v0, Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;->Companion:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$Companion;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastQualityPos:I

    iget-object v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    iget-boolean v4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showOrg:Z

    invoke-virtual {v0, v1, v2, v3, v4}, Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$Companion;->newInstance(Ljava/util/List;ILcom/movieboxpro/android/model/BaseMediaModel;Z)Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->videoQualityDialogFragment:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    .line 1546
    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$13;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$13;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;->setCallback(Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$SwitchDefinitionCallback;)V

    .line 1633
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->videoQualityDialogFragment:Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v2, Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;

    const-string v2, "VideoQualityDialogFragment"

    invoke-virtual {v0, v1, v2}, Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    return-void
.end method

.method private _togglePlayerLock()V
    .locals 3

    .line 1523
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    const/4 v1, 0x1

    xor-int/2addr v0, v1

    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    .line 1524
    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    invoke-virtual {v2, v0}, Landroid/widget/ImageView;->setSelected(Z)V

    .line 1525
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    if-eqz v0, :cond_0

    .line 1526
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->disable()V

    .line 1527
    invoke-direct {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideAllView(Z)V

    goto :goto_0

    .line 1529
    :cond_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    if-nez v0, :cond_1

    .line 1530
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->enable()V

    .line 1533
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method private _toggleSrtSelect()V
    .locals 3

    .line 1724
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "_toggleSrtSelect"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowLrc:Z

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method private _toggleTrackSelect()V
    .locals 12

    const/4 v0, 0x0

    .line 1657
    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTracksDialogFragment:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    .line 1658
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    if-eqz v0, :cond_0

    .line 1659
    sget-object v1, Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;->Companion:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$Companion;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackId:Ljava/lang/String;

    iget-object v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    new-instance v4, Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 1660
    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getAudioTracks()Ljava/util/ArrayList;

    move-result-object v0

    invoke-direct {v4, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    iget-object v5, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    iget-object v6, v0, Lcom/movieboxpro/android/model/BaseMediaModel;->id:Ljava/lang/String;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    iget v7, v0, Lcom/movieboxpro/android/model/BaseMediaModel;->box_type:I

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 1661
    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrAudioId()I

    move-result v8

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getAudioDelay()J

    move-result-wide v9

    long-to-int v9, v9

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    iget v10, v0, Lcom/movieboxpro/android/model/BaseMediaModel;->season:I

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    iget v11, v0, Lcom/movieboxpro/android/model/BaseMediaModel;->episode:I

    .line 1659
    invoke-virtual/range {v1 .. v11}, Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$Companion;->newInstance(Ljava/lang/String;Lcom/movieboxpro/android/player/MediaQualityInfo;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/String;IIIII)Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTracksDialogFragment:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    .line 1662
    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$14;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$14;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;->setListener(Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment$OnAudioTrackListener;)V

    .line 1714
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTracksDialogFragment:Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v2, Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;

    const-string v2, "VlcAudioTracksDialogFragment"

    invoke-virtual {v0, v1, v2}, Lcom/movieboxpro/android/view/dialog/VlcAudioTracksDialogFragment;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private calBitStream(Ljava/lang/String;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "bitstream"
        }
    .end annotation

    .line 3659
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 3660
    const-string v0, "MB/s"

    invoke-virtual {p1, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 3661
    const-string v1, ""

    invoke-virtual {p1, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    .line 3663
    :try_start_0
    invoke-static {p1}, Ljava/lang/Double;->parseDouble(Ljava/lang/String;)D

    move-result-wide v0

    const-wide/high16 v2, 0x4020000000000000L    # 8.0

    div-double/2addr v0, v2

    const-wide/high16 v2, 0x4090000000000000L    # 1024.0

    mul-double v0, v0, v2

    const-wide v2, 0x3fe3333333333333L    # 0.6

    mul-double v0, v0, v2

    double-to-long v0, v0

    .line 3664
    iput-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->bitStream:J
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    .line 3666
    invoke-virtual {p1}, Ljava/lang/NumberFormatException;->printStackTrace()V

    goto :goto_0

    :cond_0
    const-wide/16 v0, 0x0

    .line 3670
    iput-wide v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->bitStream:J

    :cond_1
    :goto_0
    return-void
.end method

.method private changeQuality(Ljava/lang/String;IZ)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "url",
            "startSeconds",
            "userSurfaceView"
        }
    .end annotation

    if-eqz p1, :cond_0

    .line 1046
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1047
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getRealUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1048
    const-string v1, "\u64ad\u653e\u94fe\u63a5"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1049
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    if-eqz v0, :cond_0

    .line 1050
    invoke-virtual {v0, p1, p2, p3}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->switchQuality(Ljava/lang/String;IZ)V

    :cond_0
    return-void
.end method

.method private formatDelaySecond(I)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "delay"
        }
    .end annotation

    .line 1913
    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    int-to-float p1, p1

    const/high16 v1, 0x447a0000    # 1000.0f

    div-float/2addr p1, v1

    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p1

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const-string p1, "%.1f"

    invoke-static {v0, p1, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private getSizeValue()I
    .locals 3

    .line 3310
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    if-nez v0, :cond_0

    .line 3311
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const-string v1, "setting"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroidx/appcompat/app/AppCompatActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    .line 3313
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "srt_size_value"

    const/16 v2, 0x18

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    return v0
.end method

.method private getTrackInfo()V
    .locals 7

    .line 3550
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ListTracks:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 3551
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 3555
    :goto_0
    array-length v3, v0

    if-ge v2, v3, :cond_6

    .line 3557
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "TrackType:"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    aget-object v4, v0, v2

    invoke-interface {v4}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getTrackType()I

    move-result v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v4, "LivePreviewActivity"

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3559
    const-string v3, "ITrackInfo.MEDIA_TRACK_TYPE_AUDIO:2"

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3561
    aget-object v3, v0, v2

    invoke-interface {v3}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getTrackType()I

    move-result v3

    const/4 v5, 0x2

    if-ne v3, v5, :cond_5

    .line 3563
    aget-object v3, v0, v2

    invoke-interface {v3}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getInfoInline()Ljava/lang/String;

    move-result-object v3

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3565
    aget-object v3, v0, v2

    invoke-interface {v3}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getInfoInline()Ljava/lang/String;

    move-result-object v3

    const-string v6, "kb/s"

    invoke-virtual {v3, v6}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_5

    .line 3566
    new-instance v3, Lcom/dl7/player/model/TrackModel;

    invoke-direct {v3}, Lcom/dl7/player/model/TrackModel;-><init>()V

    .line 3568
    iget-object v6, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v6}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;

    move-result-object v6

    aget-object v6, v6, v2

    invoke-interface {v6}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getFormat()Ltv/danmaku/ijk/media/player/misc/IMediaFormat;

    move-result-object v6

    if-nez v6, :cond_1

    goto :goto_1

    .line 3570
    :cond_1
    instance-of v6, v6, Ltv/danmaku/ijk/media/player/misc/IjkMediaFormat;

    if-eqz v6, :cond_3

    .line 3571
    iget-object v6, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v6}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;

    move-result-object v6

    aget-object v6, v6, v2

    invoke-interface {v6}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getTrackType()I

    move-result v6

    if-eq v6, v5, :cond_2

    goto :goto_1

    .line 3586
    :cond_2
    iget-object v6, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v6}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;

    move-result-object v6

    aget-object v6, v6, v2

    invoke-interface {v6}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getLanguage()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v6}, Lcom/dl7/player/model/TrackModel;->setTrackName(Ljava/lang/String;)V

    .line 3587
    const-string v6, "Audio"

    invoke-virtual {v3, v6}, Lcom/dl7/player/model/TrackModel;->setTrackType(Ljava/lang/String;)V

    .line 3588
    iget-object v6, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v6}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTrackInfo()[Ltv/danmaku/ijk/media/player/misc/ITrackInfo;

    move-result-object v6

    aget-object v6, v6, v2

    invoke-interface {v6}, Ltv/danmaku/ijk/media/player/misc/ITrackInfo;->getInfoInline()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v3, v6}, Lcom/dl7/player/model/TrackModel;->setTrackInfo(Ljava/lang/String;)V

    .line 3589
    invoke-virtual {v3, v2}, Lcom/dl7/player/model/TrackModel;->setTrackModel(I)V

    .line 3590
    invoke-virtual {v3, v1}, Lcom/dl7/player/model/TrackModel;->setSelect(Z)V

    .line 3596
    :cond_3
    :goto_1
    iget-object v6, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v6, v5}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getSelectedTrack(I)I

    move-result v5

    if-ne v2, v5, :cond_4

    const/4 v5, 0x1

    .line 3597
    invoke-virtual {v3, v5}, Lcom/dl7/player/model/TrackModel;->setSelect(Z)V

    .line 3599
    :cond_4
    iget-object v5, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ListTracks:Ljava/util/List;

    invoke-interface {v5, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 3600
    const-string v3, "------------"

    invoke-static {v4, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_5
    add-int/lit8 v2, v2, 0x1

    goto/16 :goto_0

    :cond_6
    return-void
.end method

.method private hideTestSpeedHint()V
    .locals 3

    .line 2759
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object v0

    new-instance v1, Lcom/movieboxpro/android/event/SwitchTestSpeedHintEvent;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/movieboxpro/android/event/SwitchTestSpeedHintEvent;-><init>(Z)V

    invoke-virtual {v0, v1}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    return-void
.end method

.method private initSeekBar()V
    .locals 2

    .line 2763
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setMax(I)V

    .line 2764
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerSeek:Landroid/widget/SeekBar;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$25;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$25;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    return-void
.end method

.method private initSeekBar2()V
    .locals 2

    .line 727
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v1

    invoke-virtual {v0, v1}, Landroidx/appcompat/widget/AppCompatSeekBar;->setMax(I)V

    .line 728
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerSeekBar:Landroidx/appcompat/widget/AppCompatSeekBar;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$6;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$6;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroidx/appcompat/widget/AppCompatSeekBar;->setOnKeyListener(Landroid/view/View$OnKeyListener;)V

    return-void
.end method

.method private initSubtitlePos()V
    .locals 3

    .line 3128
    sget v0, Lcom/movieboxpro/androidtv/R$id;->rlSubtitlePos:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->rlSubtitlePos:Landroid/widget/LinearLayout;

    .line 3129
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ll_bottom_controller_position:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitlePos:Landroid/widget/ImageView;

    .line 3130
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ivSubtitleDown:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleDown:Landroid/widget/ImageView;

    .line 3131
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ivSubtitleUp:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleUp:Landroid/widget/ImageView;

    .line 3132
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ivSubtitlePosClose:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitlePosClose:Landroid/widget/ImageView;

    .line 3133
    sget v0, Lcom/movieboxpro/androidtv/R$id;->llSubtitle:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSubtitle:Landroid/widget/LinearLayout;

    .line 3134
    sget v0, Lcom/movieboxpro/androidtv/R$id;->subtitleLine:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleLine:Landroid/view/View;

    .line 3135
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitlePos:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$32;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$32;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3145
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitlePosClose:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda3;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3150
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleDown:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$33;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$33;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3162
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleUp:Landroid/widget/ImageView;

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$34;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$34;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 3174
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v1

    const/high16 v2, 0x41200000    # 10.0f

    invoke-static {v1, v2}, Lcom/movieboxpro/android/utils/DensityUtils;->dp2px(Landroid/content/Context;F)I

    move-result v1

    const-string v2, "subtitle_pos"

    invoke-virtual {v0, v2, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInt(Ljava/lang/String;I)I

    move-result v0

    .line 3175
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSubtitle:Landroid/widget/LinearLayout;

    const/4 v2, 0x0

    invoke-static {v1, v2, v2, v2, v0}, Lcom/movieboxpro/android/utils/DensityUtils;->setMarginPx(Landroid/view/View;IIII)V

    return-void
.end method

.method private synthetic lambda$_initView$0(Landroid/view/View;)V
    .locals 0

    .line 400
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    if-eqz p1, :cond_0

    .line 401
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayerViewCallback;->showPlayerEngine()V

    :cond_0
    return-void
.end method

.method private synthetic lambda$_initView$1(Landroid/view/View;)V
    .locals 2

    .line 433
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isOpenSubtitleBg:Z

    xor-int/lit8 v0, p1, 0x1

    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isOpenSubtitleBg:Z

    if-nez p1, :cond_0

    .line 435
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    const-string v0, "#B3000000"

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setBackgroundColor(I)V

    .line 436
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_cc_bg_open:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    .line 438
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSubtitleBg:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_cc_bg:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 439
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setBackgroundColor(I)V

    .line 441
    :goto_0
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object p1

    const-string v0, "open_subtitle_bg"

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isOpenSubtitleBg:Z

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->putBoolean(Ljava/lang/String;Z)Lcom/movieboxpro/android/utils/PrefsUtils;

    return-void
.end method

.method private synthetic lambda$_initView$2(Landroid/view/View;)V
    .locals 0

    const/4 p1, 0x0

    .line 635
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_togglePlayStatus(Z)V

    return-void
.end method

.method private synthetic lambda$_initView$3(Landroid/view/View;Z)V
    .locals 0

    if-eqz p2, :cond_1

    .line 639
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isAnimation:Z

    if-eqz p1, :cond_0

    return-void

    .line 642
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideSeekBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 643
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    const/16 p2, 0x8

    invoke-virtual {p1, p2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 644
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->clControl:Landroidx/constraintlayout/widget/ConstraintLayout;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    .line 645
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flSubtitleSetting:Landroid/widget/FrameLayout;

    invoke-virtual {p1, p2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 646
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleControlBar()V

    .line 648
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->requestFocus()Z

    .line 649
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setFocusableInTouchMode(Z)V

    :cond_1
    return-void
.end method

.method private synthetic lambda$_initView$4(Landroid/view/View;Z)V
    .locals 0

    if-eqz p2, :cond_1

    .line 654
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isAnimation:Z

    if-eqz p1, :cond_0

    return-void

    .line 657
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideSeekBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, p2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 658
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    const/16 p2, 0x8

    invoke-virtual {p1, p2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 659
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->clControl:Landroidx/constraintlayout/widget/ConstraintLayout;

    const/4 p2, 0x0

    invoke-virtual {p1, p2}, Landroidx/constraintlayout/widget/ConstraintLayout;->setVisibility(I)V

    .line 660
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flSubtitleSetting:Landroid/widget/FrameLayout;

    invoke-virtual {p1, p2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 661
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleControlBar()V

    .line 663
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    invoke-virtual {p1}, Landroid/widget/ImageView;->requestFocus()Z

    .line 664
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSelect:Landroid/widget/ImageView;

    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setFocusableInTouchMode(Z)V

    :cond_1
    return-void
.end method

.method private synthetic lambda$_init_cc_controller$5(Lcom/avery/subtitle/model/Subtitle;)V
    .locals 4

    .line 2992
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {v0, p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setCurrSubtitle(Lcom/avery/subtitle/model/Subtitle;)J

    move-result-wide v0

    long-to-float p1, v0

    const/high16 v0, 0x447a0000    # 1000.0f

    div-float/2addr p1, v0

    const/high16 v0, 0x40000000    # 2.0f

    mul-float p1, p1, v0

    float-to-double v0, p1

    .line 2993
    invoke-static {v0, v1}, Ljava/lang/Math;->floor(D)D

    move-result-wide v0

    const-wide/high16 v2, 0x4000000000000000L    # 2.0

    div-double/2addr v0, v2

    const-wide v2, 0x408f400000000000L    # 1000.0

    mul-double v0, v0, v2

    double-to-int p1, v0

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtspeed:I

    .line 2994
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srttext:Landroid/widget/TextView;

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->formatDelaySecond(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 2995
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->start()V

    return-void
.end method

.method private synthetic lambda$_init_cc_controller$6(Landroid/view/View;)V
    .locals 2

    .line 2990
    new-instance p1, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    invoke-direct {p1}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    .line 2991
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$$ExternalSyntheticLambda2;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;->setOnItemClickListener(Lcom/movieboxpro/android/view/dialog/LastSaidFragment$OnLastSaidListener;)V

    .line 2997
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->getSubtitles()Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 2998
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->getSubtitles()Ljava/util/List;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;->setSubtitleData(Ljava/util/List;)V

    goto :goto_0

    .line 3000
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;->setSubtitleData(Ljava/util/List;)V

    .line 3003
    :goto_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->getCurrSubtitlePos()I

    move-result v0

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;->setSelectPos(I)V

    .line 3004
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSaidFragment:Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v1, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;

    const-string v1, "LastSaidFragment"

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/view/dialog/LastSaidFragment;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    .line 3005
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    return-void
.end method

.method private synthetic lambda$_init_cc_size$8(Landroid/view/View;)V
    .locals 2

    .line 3366
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    add-int/lit8 p1, p1, -0x1

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    .line 3367
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtsizeText:Landroid/widget/TextView;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 3369
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->changeSize(I)V

    .line 3370
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtsize:I

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->saveSizeValue(I)V

    return-void
.end method

.method private synthetic lambda$_init_cc_size$9(Landroid/view/View;)V
    .locals 1

    .line 3373
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_size:Landroid/widget/RelativeLayout;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/widget/RelativeLayout;->setVisibility(I)V

    .line 3374
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtController:Landroid/widget/LinearLayout;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 3375
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomCCSize:Landroid/widget/ImageView;

    invoke-direct {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->requestFocusable(Landroid/view/View;)V

    return-void
.end method

.method private synthetic lambda$initSubtitlePos$7(Landroid/view/View;)V
    .locals 1

    .line 3146
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->rlSubtitlePos:Landroid/widget/LinearLayout;

    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 3147
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleLine:Landroid/view/View;

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    .line 3148
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return-void
.end method

.method private requestFocusable(Landroid/view/View;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "view"
        }
    .end annotation

    const/4 v0, 0x1

    .line 4063
    invoke-virtual {p1, v0}, Landroid/view/View;->setFocusableInTouchMode(Z)V

    .line 4064
    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    return-void
.end method

.method private saveSizeValue(I)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "value"
        }
    .end annotation

    .line 3298
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    if-nez v0, :cond_0

    .line 3299
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const-string v1, "setting"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroidx/appcompat/app/AppCompatActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    .line 3301
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "srt_size_value"

    invoke-interface {v0, v1, p1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method private setFocusable(Landroid/view/View;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "view"
        }
    .end annotation

    const/4 v0, 0x1

    .line 4068
    invoke-virtual {p1, v0}, Landroid/view/View;->setFocusable(Z)V

    .line 4069
    invoke-virtual {p1, v0}, Landroid/view/View;->setClickable(Z)V

    .line 4070
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$46;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$46;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    return-void
.end method

.method private setNoFocusable(Landroid/view/View;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "view"
        }
    .end annotation

    const/4 v0, 0x1

    .line 4081
    invoke-virtual {p1, v0}, Landroid/view/View;->setFocusable(Z)V

    .line 4082
    invoke-virtual {p1, v0}, Landroid/view/View;->setClickable(Z)V

    .line 4083
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$47;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$47;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Landroid/view/View;->setOnFocusChangeListener(Landroid/view/View$OnFocusChangeListener;)V

    return-void
.end method

.method private showTestSpeedHint()V
    .locals 3

    .line 2755
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object v0

    new-instance v1, Lcom/movieboxpro/android/event/SwitchTestSpeedHintEvent;

    const/4 v2, 0x1

    invoke-direct {v1, v2}, Lcom/movieboxpro/android/event/SwitchTestSpeedHintEvent;-><init>(Z)V

    invoke-virtual {v0, v1}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    return-void
.end method

.method private startAnimation(Z)V
    .locals 21
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "isShowBar"
        }
    .end annotation

    move-object/from16 v0, p0

    const/4 v1, 0x0

    if-eqz p1, :cond_0

    .line 1396
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvVideoTitle:Landroid/widget/TextView;

    invoke-virtual {v2, v1}, Landroid/widget/TextView;->setVisibility(I)V

    goto :goto_0

    .line 1398
    :cond_0
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvVideoTitle:Landroid/widget/TextView;

    const/16 v3, 0x8

    invoke-virtual {v2, v3}, Landroid/widget/TextView;->setVisibility(I)V

    :goto_0
    if-eqz p1, :cond_2

    .line 1402
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    invoke-virtual {v2}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result v2

    if-nez v2, :cond_1

    return-void

    .line 1405
    :cond_1
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    invoke-virtual {v2, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 1406
    new-instance v1, Landroid/view/animation/TranslateAnimation;

    const/4 v10, 0x1

    const/4 v11, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x0

    const/4 v8, 0x1

    const/high16 v9, 0x3f800000    # 1.0f

    move-object v3, v1

    invoke-direct/range {v3 .. v11}, Landroid/view/animation/TranslateAnimation;-><init>(IFIFIFIF)V

    goto :goto_1

    .line 1408
    :cond_2
    new-instance v1, Landroid/view/animation/TranslateAnimation;

    const/16 v19, 0x1

    const/high16 v20, 0x3f800000    # 1.0f

    const/4 v13, 0x1

    const/4 v14, 0x0

    const/4 v15, 0x1

    const/16 v16, 0x0

    const/16 v17, 0x1

    const/16 v18, 0x0

    move-object v12, v1

    invoke-direct/range {v12 .. v20}, Landroid/view/animation/TranslateAnimation;-><init>(IFIFIFIF)V

    .line 1409
    new-instance v2, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$11;

    invoke-direct {v2, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$11;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v1, v2}, Landroid/view/animation/TranslateAnimation;->setAnimationListener(Landroid/view/animation/Animation$AnimationListener;)V

    :goto_1
    const-wide/16 v2, 0xc8

    .line 1427
    invoke-virtual {v1, v2, v3}, Landroid/view/animation/TranslateAnimation;->setDuration(J)V

    .line 1428
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLlBottomBar:Landroid/widget/LinearLayout;

    invoke-virtual {v2, v1}, Landroid/widget/LinearLayout;->startAnimation(Landroid/view/animation/Animation;)V

    return-void
.end method

.method private startShowTcpSpeed()V
    .locals 3

    .line 2663
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    .line 2664
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->speeds:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 2665
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2667
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getVisibility()I

    move-result v0

    const/16 v1, 0x8

    if-ne v0, v1, :cond_1

    .line 2668
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    return-void

    .line 2671
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    if-eqz v0, :cond_2

    .line 2672
    const-string v1, ""

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_2
    const-wide/16 v0, 0x1

    .line 2674
    sget-object v2, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-static {v0, v1, v2}, Lio/reactivex/Observable;->interval(JLjava/util/concurrent/TimeUnit;)Lio/reactivex/Observable;

    move-result-object v0

    .line 2675
    invoke-static {}, Lcom/movieboxpro/android/utils/RxUtils;->rxSchedulerHelper()Lio/reactivex/ObservableTransformer;

    move-result-object v1

    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->compose(Lio/reactivex/ObservableTransformer;)Lio/reactivex/Observable;

    move-result-object v0

    new-instance v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$23;

    invoke-direct {v1, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$23;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    .line 2676
    invoke-virtual {v0, v1}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/Observer;)V

    return-void
.end method

.method private stopShowTcpSpeed()V
    .locals 1

    .line 2741
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v0, :cond_0

    .line 2742
    invoke-interface {v0}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 2743
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->speeds:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    :cond_0
    return-void
.end method

.method private switchScreenSize()V
    .locals 4

    .line 1917
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->screenScales:[I

    array-length v2, v1

    add-int/lit8 v2, v2, -0x1

    const/4 v3, 0x0

    if-ne v0, v2, :cond_0

    .line 1918
    iput v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    goto :goto_0

    :cond_0
    add-int/lit8 v0, v0, 0x1

    .line 1920
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    .line 1922
    :goto_0
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    if-ltz v0, :cond_1

    array-length v1, v1

    if-ge v0, v1, :cond_1

    .line 1923
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivScreenSize:Landroid/widget/ImageView;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->scaleImgRes:[I

    aget v0, v2, v0

    invoke-virtual {v1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1924
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->screenScales:[I

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    aget v1, v1, v2

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setAspectRatio(I)V

    goto :goto_1

    .line 1926
    :cond_1
    iput v3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    .line 1927
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivScreenSize:Landroid/widget/ImageView;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->scaleImgRes:[I

    aget v1, v1, v3

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1928
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->screenScales:[I

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    aget v1, v1, v2

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setAspectRatio(I)V

    .line 1930
    :goto_1
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    const-string v1, "screen_scale"

    iget v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->defaultScreenSize:I

    invoke-virtual {v0, v1, v2}, Lcom/movieboxpro/android/utils/PrefsUtils;->putInt(Ljava/lang/String;I)Lcom/movieboxpro/android/utils/PrefsUtils;

    return-void
.end method

.method private toggleTopAnimation(Z)V
    .locals 21
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "show"
        }
    .end annotation

    move-object/from16 v0, p0

    .line 1362
    iget v1, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->boxType:I

    const/4 v2, 0x1

    if-ne v1, v2, :cond_0

    return-void

    :cond_0
    if-eqz p1, :cond_2

    .line 1367
    iget-object v1, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flLastNext:Landroid/widget/FrameLayout;

    invoke-virtual {v1}, Landroid/widget/FrameLayout;->getVisibility()I

    move-result v1

    if-nez v1, :cond_1

    return-void

    .line 1370
    :cond_1
    iget-object v1, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flLastNext:Landroid/widget/FrameLayout;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 1371
    new-instance v1, Landroid/view/animation/TranslateAnimation;

    const/4 v10, 0x1

    const/4 v11, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x0

    const/4 v8, 0x1

    const/high16 v9, -0x40800000    # -1.0f

    move-object v3, v1

    invoke-direct/range {v3 .. v11}, Landroid/view/animation/TranslateAnimation;-><init>(IFIFIFIF)V

    goto :goto_0

    .line 1373
    :cond_2
    new-instance v1, Landroid/view/animation/TranslateAnimation;

    const/16 v19, 0x1

    const/high16 v20, -0x40800000    # -1.0f

    const/4 v13, 0x1

    const/4 v14, 0x0

    const/4 v15, 0x1

    const/16 v16, 0x0

    const/16 v17, 0x1

    const/16 v18, 0x0

    move-object v12, v1

    invoke-direct/range {v12 .. v20}, Landroid/view/animation/TranslateAnimation;-><init>(IFIFIFIF)V

    .line 1374
    new-instance v2, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$10;

    invoke-direct {v2, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$10;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {v1, v2}, Landroid/view/animation/TranslateAnimation;->setAnimationListener(Landroid/view/animation/Animation$AnimationListener;)V

    :goto_0
    const-wide/16 v2, 0xc8

    .line 1390
    invoke-virtual {v1, v2, v3}, Landroid/view/animation/TranslateAnimation;->setDuration(J)V

    .line 1391
    iget-object v2, v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->flLastNext:Landroid/widget/FrameLayout;

    invoke-virtual {v2, v1}, Landroid/widget/FrameLayout;->startAnimation(Landroid/view/animation/Animation;)V

    return-void
.end method


# virtual methods
.method public _initMediaTrack()V
    .locals 1

    .line 3544
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_meidaTrack:I

    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlMediaTracks:Landroid/widget/ImageView;

    .line 3545
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setFocusable(Landroid/view/View;)V

    .line 3546
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlMediaTracks:Landroid/widget/ImageView;

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method public _showControlBar(I)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "timeout"
        }
    .end annotation

    .line 1459
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    const/4 v1, 0x1

    if-nez v0, :cond_0

    .line 1460
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setProgress()I

    .line 1461
    iput-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    .line 1462
    const-string v0, "IjkPlayerView"

    const-string v2, "SetmIsShowBar true"

    invoke-static {v0, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1464
    :cond_0
    invoke-direct {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setControlBarVisible(Z)V

    .line 1465
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v1, 0x2766

    invoke-virtual {v0, v1}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 1467
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    if-eqz p1, :cond_1

    .line 1469
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    int-to-long v2, p1

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_1
    return-void
.end method

.method public _toggleControlBar()V
    .locals 4

    .line 1435
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    xor-int/lit8 v1, v0, 0x1

    iput-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    if-nez v0, :cond_0

    .line 1437
    const-string v0, "IjkPlayerView"

    const-string v1, "toggleControlBar true"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1439
    :cond_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    if-eqz v0, :cond_1

    .line 1440
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1442
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x1388

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1444
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v1, 0x2766

    invoke-virtual {v0, v1}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 1446
    :cond_1
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setControlBarVisible(Z)V

    return-void
.end method

.method public _togglePlayStatus(Z)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "focus"
        }
    .end annotation

    .line 1484
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    if-eqz v0, :cond_0

    .line 1485
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_0

    .line 1487
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1488
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hidePlayPauseRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1489
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hidePlayPauseRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x5dc

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    :goto_0
    if-eqz p1, :cond_1

    .line 1492
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestmIvPlay()V

    .line 1494
    :cond_1
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->isPlaying()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 1495
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    .line 1496
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    sget v0, Lcom/dueeeke/videocontroller/R$mipmap;->ic_video_pause:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1497
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->pause()V

    goto :goto_1

    .line 1499
    :cond_2
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->start()V

    .line 1500
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    sget v0, Lcom/dueeeke/videocontroller/R$mipmap;->ic_video_play:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1501
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->resume()V

    :goto_1
    return-void
.end method

.method public bridge synthetic alwaysFullScreen()Lcom/movieboxpro/android/player/IPlayerView;
    .locals 1

    .line 128
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->alwaysFullScreen()Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object v0

    return-object v0
.end method

.method public alwaysFullScreen()Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 2

    const/4 v0, 0x1

    .line 1131
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsAlwaysFullScreen:Z

    .line 1132
    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setFullScreen(Z)V

    .line 1133
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    .line 1134
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setUiLayoutFullscreen()V

    return-object p0
.end method

.method public changeMediaQuality(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 6
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "quality"
        }
    .end annotation

    .line 3774
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    .line 3775
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 3777
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    if-eqz v0, :cond_b

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-ge v0, p1, :cond_1

    goto/16 :goto_2

    .line 3780
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v1

    const/4 v2, 0x2

    const/4 v3, 0x0

    const/4 v4, 0x1

    const/4 v5, -0x1

    sparse-switch v1, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string v1, "1080p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    const/4 v5, 0x6

    goto :goto_0

    :sswitch_1
    const-string v1, "auto"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v5, 0x5

    goto :goto_0

    :sswitch_2
    const-string v1, "720p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    :cond_4
    const/4 v5, 0x4

    goto :goto_0

    :sswitch_3
    const-string v1, "360p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    goto :goto_0

    :cond_5
    const/4 v5, 0x3

    goto :goto_0

    :sswitch_4
    const-string v1, "org"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_6

    goto :goto_0

    :cond_6
    const/4 v5, 0x2

    goto :goto_0

    :sswitch_5
    const-string v1, "8k"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_7

    goto :goto_0

    :cond_7
    const/4 v5, 0x1

    goto :goto_0

    :sswitch_6
    const-string v1, "4k"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_8

    goto :goto_0

    :cond_8
    const/4 v5, 0x0

    :goto_0
    packed-switch v5, :pswitch_data_0

    goto :goto_1

    .line 3788
    :pswitch_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3800
    :pswitch_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_auto:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3785
    :pswitch_2
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v4

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3782
    :pswitch_3
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v3

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3797
    :pswitch_4
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_origin:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3794
    :pswitch_5
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/dl7/player/R$mipmap;->ic_media_quality_8k:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3791
    :pswitch_6
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/dl7/player/R$mipmap;->ic_media_quality_4k:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 3803
    :goto_1
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v1, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object v1

    const-string v2, "last_select_qua"

    invoke-virtual {v0, v2, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->putString(Ljava/lang/String;Ljava/lang/String;)Lcom/movieboxpro/android/utils/PrefsUtils;

    .line 3804
    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    .line 3805
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 3807
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getOriginal()I

    move-result v0

    if-eq v0, v4, :cond_9

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object v0

    const-string v1, "4K"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_a

    :cond_9
    const/4 v3, 0x1

    .line 3810
    :cond_a
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getPath()Ljava/lang/String;

    move-result-object p1

    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    invoke-direct {p0, p1, v0, v3}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->changeQuality(Ljava/lang/String;IZ)V

    :cond_b
    :goto_2
    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        0x6b7 -> :sswitch_6
        0x733 -> :sswitch_5
        0x1aee4 -> :sswitch_4
        0x17ffe3 -> :sswitch_3
        0x19c25b -> :sswitch_2
        0x2dddaf -> :sswitch_1
        0x2c929f9 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public clearSubtitle()V
    .locals 2

    .line 3238
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_size:Landroid/widget/RelativeLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout;->setVisibility(I)V

    .line 3239
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ll_srt_speed:Landroid/widget/RelativeLayout;

    invoke-virtual {v0, v1}, Landroid/widget/RelativeLayout;->setVisibility(I)V

    .line 3240
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtController:Landroid/widget/LinearLayout;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 3241
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->clearSubtitles()V

    const/4 v0, 0x0

    .line 3242
    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSelectServerSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

    return-void
.end method

.method public configurationChanged(Landroid/content/res/Configuration;)V
    .locals 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "newConfig"
        }
    .end annotation

    .line 2095
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_refreshOrientationEnable()V

    .line 2098
    iget v0, p1, Landroid/content/res/Configuration;->orientation:I

    const/4 v1, 0x2

    const/16 v2, 0x400

    const/16 v3, 0x1706

    const/4 v4, 0x1

    if-ne v0, v1, :cond_0

    .line 2100
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {p1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p1

    .line 2102
    invoke-virtual {p1}, Landroid/view/View;->getSystemUiVisibility()I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mScreenUiVisibility:I

    .line 2104
    invoke-virtual {p1, v3}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 2111
    invoke-direct {p0, v4}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setFullScreen(Z)V

    .line 2112
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {p1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/view/Window;->addFlags(I)V

    goto :goto_0

    .line 2113
    :cond_0
    iget p1, p1, Landroid/content/res/Configuration;->orientation:I

    if-ne p1, v4, :cond_1

    .line 2114
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {p1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object p1

    .line 2115
    invoke-virtual {p1, v3}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 2122
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {p1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object p1

    invoke-virtual {p1, v2}, Landroid/view/Window;->addFlags(I)V

    .line 2126
    invoke-direct {p0, v4}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setFullScreen(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method public doPause()V
    .locals 0

    .line 333
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    return-void
.end method

.method public doStart()V
    .locals 0

    .line 3682
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->start()V

    return-void
.end method

.method public bridge synthetic enableOrientation()Lcom/movieboxpro/android/player/IPlayerView;
    .locals 1

    .line 128
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->enableOrientation()Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object v0

    return-object v0
.end method

.method public enableOrientation()Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 1

    const/4 v0, 0x0

    .line 1940
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    .line 1941
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->enable()V

    return-object p0
.end method

.method public getCurPosition()I
    .locals 1

    .line 3886
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    return v0
.end method

.method public getCurrQuality()I
    .locals 1

    .line 3676
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    return v0
.end method

.method public getDurarion()I
    .locals 1

    .line 3890
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getDuration()I

    move-result v0

    return v0
.end method

.method public getImageThumb()Landroid/widget/ImageView;
    .locals 1

    .line 1646
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mPlayerThumb:Landroid/widget/ImageView;

    return-object v0
.end method

.method public getRealUrl(Ljava/lang/String;)Ljava/lang/String;
    .locals 8
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "url"
        }
    .end annotation

    .line 1072
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    const-string v1, "network_group"

    const-string v2, ""

    invoke-virtual {v0, v1, v2}, Lcom/movieboxpro/android/utils/PrefsUtils;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1073
    const-string v3, "0"

    invoke-virtual {v3, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 1074
    const-string v0, "http://"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "https://"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 1075
    :cond_0
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v0

    const-string v3, "select_server_url"

    invoke-virtual {v0, v3, v2}, Lcom/movieboxpro/android/utils/PrefsUtils;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1076
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_3

    .line 1077
    const-string v3, "/"

    invoke-virtual {p1, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v4

    .line 1079
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    .line 1080
    array-length v6, v4

    const/4 v7, 0x2

    if-le v6, v7, :cond_4

    .line 1081
    aput-object v0, v4, v7

    .line 1082
    :goto_0
    array-length p1, v4

    if-ge v7, p1, :cond_2

    .line 1083
    array-length p1, v4

    add-int/lit8 p1, p1, -0x1

    if-eq v7, p1, :cond_1

    .line 1084
    aget-object p1, v4, v7

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 1086
    :cond_1
    aget-object p1, v4, v7

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :goto_1
    add-int/lit8 v7, v7, 0x1

    goto :goto_0

    .line 1089
    :cond_2
    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    :cond_3
    return-object p1

    .line 1096
    :cond_4
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v3, "\u64ad\u653e\u94fe\u63a5 : "

    invoke-direct {v0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "groupId:"

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v3

    invoke-virtual {v3, v1, v2}, Lcom/movieboxpro/android/utils/PrefsUtils;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "NormalIjkVideoView"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-object p1
.end method

.method public getTcpSpeed()J
    .locals 2

    .line 1730
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    if-eqz v0, :cond_0

    .line 1731
    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getTcpSpeed()J

    move-result-wide v0

    return-wide v0

    :cond_0
    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public getVideoHeight()I
    .locals 3

    .line 3088
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 3089
    sget-object v0, Lcom/movieboxpro/android/utils/VlcPlayerManager;->INSTANCE:Lcom/movieboxpro/android/utils/VlcPlayerManager;

    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/movieboxpro/android/utils/VlcPlayerManager;->get(Landroid/content/Context;)Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->getMediaPlayer()Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object v0

    check-cast v0, Lorg/videolan/libvlc/Media;

    if-eqz v0, :cond_0

    .line 3090
    invoke-virtual {v0}, Lorg/videolan/libvlc/Media;->getTrackCount()I

    move-result v2

    if-lez v2, :cond_0

    .line 3091
    invoke-virtual {v0, v1}, Lorg/videolan/libvlc/Media;->getTrack(I)Lorg/videolan/libvlc/interfaces/IMedia$Track;

    move-result-object v0

    .line 3092
    instance-of v2, v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;

    if-eqz v2, :cond_0

    .line 3093
    check-cast v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;

    .line 3094
    iget v0, v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;->height:I

    return v0

    :cond_0
    return v1
.end method

.method public bridge synthetic getVideoView()Landroid/widget/MediaController$MediaPlayerControl;
    .locals 1

    .line 128
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getVideoView()Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    move-result-object v0

    return-object v0
.end method

.method public getVideoView()Lcom/movieboxpro/android/player/vlc/VlcVideoView;
    .locals 1

    .line 345
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    return-object v0
.end method

.method public getVideoWidth()I
    .locals 3

    .line 3102
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    .line 3103
    sget-object v0, Lcom/movieboxpro/android/utils/VlcPlayerManager;->INSTANCE:Lcom/movieboxpro/android/utils/VlcPlayerManager;

    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/movieboxpro/android/utils/VlcPlayerManager;->get(Landroid/content/Context;)Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->getMediaPlayer()Lorg/videolan/libvlc/MediaPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lorg/videolan/libvlc/MediaPlayer;->getMedia()Lorg/videolan/libvlc/interfaces/IMedia;

    move-result-object v0

    check-cast v0, Lorg/videolan/libvlc/Media;

    if-eqz v0, :cond_0

    .line 3104
    invoke-virtual {v0}, Lorg/videolan/libvlc/Media;->getTrackCount()I

    move-result v2

    if-lez v2, :cond_0

    .line 3105
    invoke-virtual {v0, v1}, Lorg/videolan/libvlc/Media;->getTrack(I)Lorg/videolan/libvlc/interfaces/IMedia$Track;

    move-result-object v0

    .line 3106
    instance-of v2, v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;

    if-eqz v2, :cond_0

    .line 3107
    check-cast v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;

    .line 3108
    iget v0, v0, Lorg/videolan/libvlc/interfaces/IMedia$VideoTrack;->width:I

    return v0

    :cond_0
    return v1
.end method

.method public handleVolumeKey(I)Z
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "keyCode"
        }
    .end annotation

    const/16 v0, 0x18

    const/4 v1, 0x1

    if-ne p1, v0, :cond_0

    .line 948
    invoke-direct {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setVolume(Z)V

    return v1

    :cond_0
    const/16 v0, 0x19

    const/4 v2, 0x0

    if-ne p1, v0, :cond_1

    .line 951
    invoke-direct {p0, v2}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setVolume(Z)V

    return v1

    :cond_1
    return v2
.end method

.method public hideLastVideo()V
    .locals 2

    .line 3259
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llLastEpisode:Landroid/widget/LinearLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    return-void
.end method

.method public hideNextVideo()V
    .locals 2

    .line 3269
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llNextEpisode:Landroid/widget/LinearLayout;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    return-void
.end method

.method public bridge synthetic init(I)Lcom/movieboxpro/android/player/IPlayerView;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x1000
        }
        names = {
            "boxType"
        }
    .end annotation

    .line 128
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->init(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object p1

    return-object p1
.end method

.method public init(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "boxType"
        }
    .end annotation

    .line 1025
    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->boxType:I

    .line 1026
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_initMediaPlayer()V

    return-object p0
.end method

.method public isControllerShow()Z
    .locals 1

    .line 1450
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    return v0
.end method

.method public isPlaying()Z
    .locals 1

    .line 1215
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->isPlaying()Z

    move-result v0

    return v0
.end method

.method public onBackPressed()Z
    .locals 3

    .line 968
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "mIsShowCC  "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, "  mIsShowQuality  "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, "  mVideoStatus  "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoStatus:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "  mIsFullscreen  "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "VIEWDOPLAY"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 970
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    .line 971
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 972
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleControlBar()V

    .line 973
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return v1

    .line 976
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result v0

    if-nez v0, :cond_1

    .line 977
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideSeekBarRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 978
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llSeek:Landroid/widget/LinearLayout;

    const/16 v2, 0x8

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 979
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return v1

    .line 983
    :cond_1
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    if-eqz v0, :cond_2

    .line 984
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleCCSelect()V

    .line 985
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return v1

    .line 988
    :cond_2
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    if-eqz v0, :cond_3

    .line 989
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleMediaQuality()V

    return v1

    .line 993
    :cond_3
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    if-eqz v0, :cond_4

    .line 994
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleTrackSelect()V

    return v1

    .line 998
    :cond_4
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowLrc:Z

    if-eqz v0, :cond_5

    .line 999
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleSrtSelect()V

    return v1

    .line 1003
    :cond_5
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsAlwaysFullScreen:Z

    if-eqz v0, :cond_6

    .line 1004
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_exit()V

    return v1

    .line 1006
    :cond_6
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsFullscreen:Z

    const/4 v2, 0x0

    if-eqz v0, :cond_8

    .line 1007
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0, v1}, Landroidx/appcompat/app/AppCompatActivity;->setRequestedOrientation(I)V

    .line 1008
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    if-eqz v0, :cond_7

    .line 1010
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    .line 1011
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayerLock:Landroid/widget/ImageView;

    invoke-virtual {v0, v2}, Landroid/widget/ImageView;->setSelected(Z)V

    .line 1012
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    invoke-direct {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setControlBarVisible(Z)V

    :cond_7
    return v1

    :cond_8
    return v2
.end method

.method public onClick(Landroid/view/View;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "v"
        }
    .end annotation

    .line 1739
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_refreshHideRunnable()V

    .line 1740
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result p1

    .line 1754
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ic_iv_play:I

    const/4 v1, 0x1

    if-eq p1, v0, :cond_1a

    sget v0, Lcom/movieboxpro/androidtv/R$id;->iv_play_circle:I

    if-ne p1, v0, :cond_0

    goto/16 :goto_1

    .line 1756
    :cond_0
    sget v0, Lcom/movieboxpro/androidtv/R$id;->iv_player_lock:I

    if-ne p1, v0, :cond_1

    .line 1757
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_togglePlayerLock()V

    goto/16 :goto_2

    .line 1758
    :cond_1
    sget v0, Lcom/movieboxpro/androidtv/R$id;->iv_media_quality:I

    if-ne p1, v0, :cond_2

    .line 1759
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleMediaQuality()V

    goto/16 :goto_2

    .line 1760
    :cond_2
    sget v0, Lcom/dl7/player/R$id;->iv_cancel_skip:I

    if-ne p1, v0, :cond_3

    .line 1761
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideSkipTipRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1762
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideSkipTip()V

    goto/16 :goto_2

    .line 1763
    :cond_3
    sget v0, Lcom/dl7/player/R$id;->tv_do_skip:I

    const/4 v2, 0x0

    if-ne p1, v0, :cond_4

    .line 1764
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, v2}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1765
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, v2}, Landroid/widget/TextView;->setVisibility(I)V

    .line 1767
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    .line 1768
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHideSkipTipRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, v0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 1769
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_hideSkipTip()V

    .line 1770
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_setProgress()I

    goto/16 :goto_2

    .line 1771
    :cond_4
    sget v0, Lcom/dl7/player/R$id;->iv_cancel_send:I

    if-ne p1, v0, :cond_5

    .line 1772
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->recoverFromEditVideo()Z

    goto/16 :goto_2

    .line 1773
    :cond_5
    sget v0, Lcom/dl7/player/R$id;->iv_do_send:I

    if-ne p1, v0, :cond_6

    .line 1774
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->recoverFromEditVideo()Z

    goto/16 :goto_2

    .line 1775
    :cond_6
    sget v0, Lcom/dl7/player/R$id;->input_options_more:I

    if-ne p1, v0, :cond_7

    goto/16 :goto_2

    .line 1776
    :cond_7
    sget v0, Lcom/dl7/player/R$id;->iv_screenshot:I

    if-ne p1, v0, :cond_8

    goto/16 :goto_2

    .line 1778
    :cond_8
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_recover_screen:I

    if-ne p1, v0, :cond_9

    .line 1779
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {p1, v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->resetVideoView(Z)V

    .line 1780
    iput-boolean v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeedRecoverScreen:Z

    goto/16 :goto_2

    .line 1781
    :cond_9
    sget v0, Lcom/dl7/player/R$id;->tv_settings:I

    if-ne p1, v0, :cond_a

    goto/16 :goto_2

    .line 1783
    :cond_a
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_reload:I

    if-ne p1, v0, :cond_b

    .line 1784
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->reload()V

    goto/16 :goto_2

    .line 1785
    :cond_b
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_srtcelsect:I

    const/4 v2, 0x0

    if-ne p1, v0, :cond_e

    .line 1786
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stringListHashMap:Ljava/util/HashMap;

    invoke-virtual {p1}, Ljava/util/HashMap;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_d

    .line 1787
    const-string p1, "No subtitles"

    invoke-static {p1}, Lcom/movieboxpro/android/utils/ToastUtils;->showShort(Ljava/lang/CharSequence;)V

    .line 1788
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    if-eqz p1, :cond_c

    .line 1789
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayerViewCallback;->onOpenSubtitleClicked()V

    :cond_c
    return-void

    .line 1793
    :cond_d
    iput-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleDialogFragment:Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    .line 1794
    new-instance p1, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    invoke-direct {p1}, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;-><init>()V

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleDialogFragment:Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    .line 1795
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stringListHashMap:Ljava/util/HashMap;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSelectedSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

    iget-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v2}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getFilename()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1, v0, v1, v2}, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;->setSubtitles(Ljava/util/HashMap;Lcom/dl7/player/model/SRTModel$subTitles;Ljava/lang/String;)V

    .line 1796
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleDialogFragment:Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$15;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$15;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;->setListener(Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment$OnSubtitleSelectListener;)V

    .line 1840
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleDialogFragment:Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v1, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;

    const-string v1, "SubtitleDialogFragment"

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/view/dialog/SubtitleDialogFragment;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    goto/16 :goto_2

    .line 1841
    :cond_e
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_meidaTrack:I

    if-ne p1, v0, :cond_f

    .line 1842
    iget-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowTracker:Z

    if-nez p1, :cond_1b

    .line 1843
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_toggleTrackSelect()V

    goto/16 :goto_2

    .line 1845
    :cond_f
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_screen_size:I

    if-ne p1, v0, :cond_10

    .line 1846
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->switchScreenSize()V

    goto/16 :goto_2

    .line 1847
    :cond_10
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvChangeVolume:I

    if-ne p1, v0, :cond_11

    .line 1849
    sget-object p1, Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;->Companion:Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$Companion;

    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currVolume:I

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$Companion;->newInstance(I)Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;

    move-result-object p1

    .line 1850
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$16;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$16;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;->setListener(Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$OnVolumeChangeListener;)V

    .line 1867
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v1, Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;

    const-string v1, "ChangeVolumeDialog"

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    goto/16 :goto_2

    .line 1868
    :cond_11
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tv_pic_mode:I

    if-ne p1, v0, :cond_12

    .line 1869
    invoke-static {}, Lorg/greenrobot/eventbus/EventBus;->getDefault()Lorg/greenrobot/eventbus/EventBus;

    move-result-object p1

    new-instance v0, Lcom/movieboxpro/android/event/EnterPicModeEvent;

    invoke-direct {v0}, Lcom/movieboxpro/android/event/EnterPicModeEvent;-><init>()V

    invoke-virtual {p1, v0}, Lorg/greenrobot/eventbus/EventBus;->post(Ljava/lang/Object;)V

    goto/16 :goto_2

    .line 1870
    :cond_12
    sget v0, Lcom/movieboxpro/androidtv/R$id;->ivHwDecoder:I

    if-ne p1, v0, :cond_14

    .line 1871
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object p1

    const-string v0, "hw_decoder"

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    xor-int/lit8 v1, p1, 0x1

    .line 1873
    invoke-static {}, Lcom/movieboxpro/android/utils/PrefsUtils;->getInstance()Lcom/movieboxpro/android/utils/PrefsUtils;

    move-result-object v2

    invoke-virtual {v2, v0, v1}, Lcom/movieboxpro/android/utils/PrefsUtils;->putBoolean(Ljava/lang/String;Z)Lcom/movieboxpro/android/utils/PrefsUtils;

    if-nez p1, :cond_13

    .line 1875
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivHwDecoder:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_hw_decoder_enable:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    .line 1877
    :cond_13
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivHwDecoder:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_hw_decoder_disable:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 1879
    :goto_0
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->changeMediaQuality(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    goto :goto_2

    .line 1880
    :cond_14
    sget v0, Lcom/movieboxpro/androidtv/R$id;->llLastEpisode:I

    if-ne p1, v0, :cond_16

    .line 1881
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    if-eqz p1, :cond_15

    .line 1882
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayerViewCallback;->lastVideo()V

    .line 1884
    :cond_15
    iput-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    goto :goto_2

    .line 1885
    :cond_16
    sget v0, Lcom/movieboxpro/androidtv/R$id;->llNextEpisode:I

    if-ne p1, v0, :cond_18

    .line 1886
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    if-eqz p1, :cond_17

    .line 1887
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayerViewCallback;->nextVideo()V

    .line 1889
    :cond_17
    iput-object v2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    goto :goto_2

    .line 1890
    :cond_18
    sget v0, Lcom/movieboxpro/androidtv/R$id;->llEpisodeList:I

    if-ne p1, v0, :cond_19

    .line 1891
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    if-eqz p1, :cond_1b

    .line 1892
    invoke-interface {p1}, Lcom/movieboxpro/android/listener/PlayerViewCallback;->showEpisodeList()V

    goto :goto_2

    .line 1894
    :cond_19
    sget v0, Lcom/movieboxpro/androidtv/R$id;->tvSpeed:I

    if-ne p1, v0, :cond_1b

    .line 1895
    new-instance p1, Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;

    invoke-direct {p1}, Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;-><init>()V

    .line 1896
    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$17;

    invoke-direct {v0, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$17;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, v0}, Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;->setListener(Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog$ChoosePlaySpeedListener;)V

    .line 1903
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    const-class v1, Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;

    const-string v1, "ChoosePlaySpeedDialog"

    invoke-virtual {p1, v0, v1}, Lcom/movieboxpro/android/view/dialog/ChoosePlaySpeedDialog;->show(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;)V

    goto :goto_2

    .line 1755
    :cond_1a
    :goto_1
    invoke-virtual {p0, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_togglePlayStatus(Z)V

    :cond_1b
    :goto_2
    return-void
.end method

.method public onDestroy()I
    .locals 3

    .line 920
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    .line 924
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    if-eqz v1, :cond_0

    .line 925
    invoke-virtual {v1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->destroy()V

    .line 927
    :cond_0
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tcpSpeedDisposable:Lio/reactivex/disposables/Disposable;

    if-eqz v1, :cond_1

    .line 928
    invoke-interface {v1}, Lio/reactivex/disposables/Disposable;->dispose()V

    .line 930
    :cond_1
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->destroy()V

    .line 931
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v2, 0x2768

    invoke-virtual {v1, v2}, Landroid/os/Handler;->removeMessages(I)V

    .line 932
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v2, 0x2766

    invoke-virtual {v1, v2}, Landroid/os/Handler;->removeMessages(I)V

    .line 935
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Landroid/os/Handler;->removeCallbacksAndMessages(Ljava/lang/Object;)V

    .line 936
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v1}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v1

    const/16 v2, 0x80

    invoke-virtual {v1, v2}, Landroid/view/Window;->clearFlags(I)V

    return v0
.end method

.method protected onLayout(ZIIII)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "changed",
            "left",
            "top",
            "right",
            "bottom"
        }
    .end annotation

    .line 865
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 866
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInitHeight:I

    if-nez p1, :cond_0

    .line 867
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getHeight()I

    move-result p1

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInitHeight:I

    .line 868
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object p1

    iget p1, p1, Landroid/util/DisplayMetrics;->widthPixels:I

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mWidthPixels:I

    :cond_0
    return-void
.end method

.method public onPause()V
    .locals 2

    .line 900
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 901
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->pause()V

    .line 902
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setSelected(Z)V

    .line 903
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    if-eqz v0, :cond_0

    .line 904
    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->disable()V

    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 2

    .line 884
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->resume()V

    .line 885
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidTouch:Z

    if-nez v0, :cond_0

    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsForbidOrientation:Z

    if-nez v0, :cond_0

    .line 886
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOrientationListener:Landroid/view/OrientationEventListener;

    invoke-virtual {v0}, Landroid/view/OrientationEventListener;->enable()V

    .line 888
    :cond_0
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    .line 890
    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    .line 891
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 893
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setSelected(Z)V

    return-void
.end method

.method public onStop()I
    .locals 1

    .line 909
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getCurrentPosition()I

    move-result v0

    return v0
.end method

.method public pause()V
    .locals 2

    .line 1222
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setSelected(Z)V

    const/4 v0, 0x1

    .line 1223
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isFrist:Z

    .line 1224
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->isPlaying()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1225
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->pause()V

    .line 1228
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x80

    invoke-virtual {v0, v1}, Landroid/view/Window;->clearFlags(I)V

    return-void
.end method

.method public recoverFromEditVideo()Z
    .locals 3

    .line 3968
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "mIsShowCC"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowCC:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, "mIsShowQuality"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowQuality:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, "mVideoStatus"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoStatus:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "VIEWDOPLAY"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3970
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoStatus:I

    const/16 v1, 0x1f5

    if-ne v0, v1, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    const/16 v2, 0x1f6

    if-ne v0, v2, :cond_1

    .line 3974
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->start()V

    .line 3977
    :cond_1
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoStatus:I

    const/4 v0, 0x1

    return v0
.end method

.method public releaseAudioPlay()V
    .locals 0

    return-void
.end method

.method public reload()V
    .locals 2

    .line 1182
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mFlReload:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 1183
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 1184
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {v0, v1}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1185
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getMediaPlayer()Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    .line 1186
    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->getMediaPlayer()Lcom/movieboxpro/android/utils/VlcPlayer;

    move-result-object v0

    invoke-virtual {v0}, Lcom/movieboxpro/android/utils/VlcPlayer;->isRecoveryExhausted()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1187
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->retryPlayback()V

    goto :goto_0

    .line 1188
    :cond_0
    iget-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsReady:Z

    if-eqz v0, :cond_1

    .line 1190
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-static {v0}, Lcom/dl7/player/utils/NetWorkUtils;->isNetworkAvailable(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1191
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->reload()V

    .line 1192
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->start()V

    .line 1194
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    if-lez v0, :cond_2

    .line 1195
    invoke-virtual {p0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->seekTo(I)V

    .line 1196
    iput v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mInterruptPosition:I

    goto :goto_0

    .line 1200
    :cond_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->release(Z)V

    .line 1201
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setRender()V

    .line 1202
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->start()V

    .line 1205
    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v1, 0x2766

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 1206
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    return-void
.end method

.method public removeSRTlist(I)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "quality"
        }
    .end annotation

    .line 3503
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "\u590d\u6742"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectSRT:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCorrentLanguage:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSlelct:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v1, "AAAAAA"

    invoke-static {v1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 3504
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectSRT:I

    iget v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCorrentLanguage:I

    if-ne v0, v1, :cond_1

    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSlelct:I

    if-eq v0, p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, -0x1

    .line 3507
    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtSlelct:I

    :cond_1
    :goto_0
    return-void
.end method

.method public requestEpisodeListFocus()V
    .locals 1

    .line 3289
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llEpisodeList:Landroid/widget/LinearLayout;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->getVisibility()I

    move-result v0

    if-nez v0, :cond_0

    .line 3290
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llEpisodeList:Landroid/widget/LinearLayout;

    invoke-virtual {v0}, Landroid/widget/LinearLayout;->requestFocus()Z

    :cond_0
    return-void
.end method

.method public requestRootFocues()V
    .locals 0

    .line 4001
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->RequestFlVideoBox()V

    return-void
.end method

.method public reset()V
    .locals 1

    const/4 v0, 0x1

    .line 1259
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    const/4 v0, 0x0

    .line 1260
    iput v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 1261
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stop()V

    .line 1262
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setRender()V

    return-void
.end method

.method public resume()V
    .locals 1

    .line 1139
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->start()V

    return-void
.end method

.method public seekTo(I)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "position"
        }
    .end annotation

    .line 1244
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0, p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->seekTo(I)V

    return-void
.end method

.method public setDataList(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "srtPraseModels"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/dl7/player/utils/SRT;",
            ">;)V"
        }
    .end annotation

    .line 3522
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 3523
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mList:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    const/4 p1, 0x0

    .line 3524
    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isFrist:Z

    return-void
.end method

.method public setFragmentManager(Landroidx/fragment/app/FragmentManager;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "fragmentManager"
        }
    .end annotation

    .line 337
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->fragmentManager:Landroidx/fragment/app/FragmentManager;

    return-void
.end method

.method public setHlsTracks(Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "tracks"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;",
            ">;)V"
        }
    .end annotation

    .line 3763
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hlsAudioTacks:Ljava/util/List;

    return-void
.end method

.method public setKeepControlBarVisible(Z)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "keep"
        }
    .end annotation

    .line 3279
    iput-boolean p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->keepControlBarVisible:Z

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    .line 3281
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_showControlBar(I)V

    goto :goto_0

    .line 3283
    :cond_0
    invoke-direct {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->_refreshHideRunnable()V

    :goto_0
    return-void
.end method

.method public setMediaQuality(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 6
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "quality"
        }
    .end annotation

    .line 3822
    iget v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    if-eq v0, p1, :cond_6

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoSource:Landroid/util/SparseArray;

    invoke-virtual {v0, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    if-eqz v0, :cond_6

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-ge v0, p1, :cond_0

    goto/16 :goto_2

    .line 3825
    :cond_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v1

    const/4 v2, 0x2

    const/4 v3, 0x1

    const/4 v4, 0x0

    const/4 v5, -0x1

    sparse-switch v1, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string v1, "1080p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    const/4 v5, 0x4

    goto :goto_0

    :sswitch_1
    const-string v1, "720p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    const/4 v5, 0x3

    goto :goto_0

    :sswitch_2
    const-string v1, "360p"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v5, 0x2

    goto :goto_0

    :sswitch_3
    const-string v1, "org"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    :cond_4
    const/4 v5, 0x1

    goto :goto_0

    :sswitch_4
    const-string v1, "4K"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    goto :goto_0

    :cond_5
    const/4 v5, 0x0

    :goto_0
    packed-switch v5, :pswitch_data_0

    goto :goto_1

    .line 3833
    :pswitch_0
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3830
    :pswitch_1
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v3

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3827
    :pswitch_2
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object v1, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget v1, v1, v4

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3839
    :pswitch_3
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_origin:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_1

    .line 3836
    :pswitch_4
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget v1, Lcom/dl7/player/R$mipmap;->ic_media_quality_4k:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 3843
    :goto_1
    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    :cond_6
    :goto_2
    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        0x697 -> :sswitch_4
        0x1aee4 -> :sswitch_3
        0x17ffe3 -> :sswitch_2
        0x19c25b -> :sswitch_1
        0x2c929f9 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public setMovieDownload(Lcom/movieboxpro/android/model/BaseMediaModel;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "baseMediaModel"
        }
    .end annotation

    .line 707
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->movieDownload:Lcom/movieboxpro/android/model/BaseMediaModel;

    return-void
.end method

.method public setOnCompletionListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnCompletionListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "l"
        }
    .end annotation

    .line 2868
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCompletionListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnCompletionListener;

    return-void
.end method

.method public setOnErrorListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnErrorListener;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "l"
        }
    .end annotation

    .line 2881
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0, p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setOnErrorListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnErrorListener;)V

    return-void
.end method

.method public setOnInfoListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "l"
        }
    .end annotation

    .line 2891
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mOutsideInfoListener:Ltv/danmaku/ijk/media/player/IMediaPlayer$OnInfoListener;

    return-void
.end method

.method public setOnPreparedListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnPreparedListener;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "l"
        }
    .end annotation

    .line 2857
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0, p1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setOnPreparedListener(Ltv/danmaku/ijk/media/player/IMediaPlayer$OnPreparedListener;)V

    return-void
.end method

.method public setPlayerViewCallback(Lcom/movieboxpro/android/listener/PlayerViewCallback;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "playerViewCallback"
        }
    .end annotation

    .line 328
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->playerViewCallback:Lcom/movieboxpro/android/listener/PlayerViewCallback;

    return-void
.end method

.method public bridge synthetic setSRTList(Ljava/util/LinkedHashMap;Lcom/movieboxpro/android/listener/OnDownloadClickListener;Lcom/dl7/player/model/SRTModel$subTitles;)Lcom/movieboxpro/android/player/IPlayerView;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x1000,
            0x1000,
            0x1000
        }
        names = {
            "srtList",
            "listener",
            "selectSubtitle"
        }
    .end annotation

    .line 128
    invoke-virtual {p0, p1, p2, p3}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setSRTList(Ljava/util/LinkedHashMap;Lcom/movieboxpro/android/listener/OnDownloadClickListener;Lcom/dl7/player/model/SRTModel$subTitles;)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object p1

    return-object p1
.end method

.method public setSRTList(Ljava/util/LinkedHashMap;Lcom/movieboxpro/android/listener/OnDownloadClickListener;Lcom/dl7/player/model/SRTModel$subTitles;)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "srtList",
            "listener",
            "selectSubtitle"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/LinkedHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/dl7/player/model/SRTModel$subTitles;",
            ">;>;",
            "Lcom/movieboxpro/android/listener/OnDownloadClickListener;",
            "Lcom/dl7/player/model/SRTModel$subTitles;",
            ")",
            "Lcom/movieboxpro/android/player/vlc/VlcPlayerView;"
        }
    .end annotation

    .line 3383
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stringListHashMap:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    .line 3384
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->languages:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 3385
    iput-object p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastSelectedSubtitle:Lcom/dl7/player/model/SRTModel$subTitles;

    .line 3386
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->stringListHashMap:Ljava/util/HashMap;

    .line 3387
    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->listener:Lcom/movieboxpro/android/listener/OnDownloadClickListener;

    .line 3389
    invoke-virtual {p1}, Ljava/util/LinkedHashMap;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/Map$Entry;

    .line 3390
    new-instance p3, Lcom/dl7/player/model/languageModel;

    invoke-direct {p3}, Lcom/dl7/player/model/languageModel;-><init>()V

    .line 3391
    invoke-interface {p2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    invoke-virtual {p3, p2}, Lcom/dl7/player/model/languageModel;->setLanguage(Ljava/lang/String;)V

    const/4 p2, 0x0

    .line 3392
    invoke-virtual {p3, p2}, Lcom/dl7/player/model/languageModel;->setSelected(Z)V

    .line 3393
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->languages:Ljava/util/List;

    invoke-interface {p2, p3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 3405
    :cond_0
    new-instance p1, Lcom/dl7/player/model/languageModel;

    invoke-direct {p1}, Lcom/dl7/player/model/languageModel;-><init>()V

    .line 3406
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->openSubtitle:Ljava/lang/String;

    iput-object p2, p1, Lcom/dl7/player/model/languageModel;->language:Ljava/lang/String;

    .line 3407
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->languages:Ljava/util/List;

    invoke-interface {p2, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object p0
.end method

.method public setSimpleSubtitles(Ljava/util/List;)V
    .locals 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "list"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/dl7/player/utils/SRT;",
            ">;)V"
        }
    .end annotation

    .line 3457
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 3459
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/dl7/player/utils/SRT;

    .line 3460
    new-instance v2, Lcom/avery/subtitle/model/Subtitle;

    invoke-direct {v2}, Lcom/avery/subtitle/model/Subtitle;-><init>()V

    .line 3461
    invoke-virtual {v1}, Lcom/dl7/player/utils/SRT;->getSrtBody()Ljava/lang/String;

    move-result-object v3

    iput-object v3, v2, Lcom/avery/subtitle/model/Subtitle;->content:Ljava/lang/String;

    .line 3462
    new-instance v3, Lcom/avery/subtitle/model/Time;

    invoke-virtual {v1}, Lcom/dl7/player/utils/SRT;->getBeginTime()I

    move-result v4

    invoke-direct {v3, v4}, Lcom/avery/subtitle/model/Time;-><init>(I)V

    iput-object v3, v2, Lcom/avery/subtitle/model/Subtitle;->start:Lcom/avery/subtitle/model/Time;

    .line 3463
    new-instance v3, Lcom/avery/subtitle/model/Time;

    invoke-virtual {v1}, Lcom/dl7/player/utils/SRT;->getEndTime()I

    move-result v1

    invoke-direct {v3, v1}, Lcom/avery/subtitle/model/Time;-><init>(I)V

    iput-object v3, v2, Lcom/avery/subtitle/model/Subtitle;->end:Lcom/avery/subtitle/model/Time;

    .line 3464
    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 3466
    :cond_0
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1, v0}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->setSubtitles(Ljava/util/List;)V

    .line 3467
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    invoke-virtual {p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->refreshSubtitle()V

    return-void
.end method

.method public setSkipTip(I)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "targetPosition"
        }
    .end annotation

    .line 3899
    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSkipPosition:I

    return-object p0
.end method

.method public setSubtitleMarginBottom(I)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "bottom"
        }
    .end annotation

    .line 3247
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    const/4 v1, 0x0

    invoke-static {v0, v1, v1, v1, p1}, Lcom/movieboxpro/android/utils/DensityUtils;->setMarginPx(Landroid/view/View;IIII)V

    return-void
.end method

.method public setSubtitleSize(I)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "size"
        }
    .end annotation

    .line 1506
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->simpleSubtitleView:Lcom/avery/subtitle/widget/SimpleSubtitleView;

    if-eqz v0, :cond_0

    .line 1507
    invoke-virtual {v0, p1}, Lcom/avery/subtitle/widget/SimpleSubtitleView;->changeSize(I)V

    :cond_0
    return-void
.end method

.method public setTitle(Ljava/lang/String;)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "title"
        }
    .end annotation

    return-object p0
.end method

.method public setToPosition(I)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "position"
        }
    .end annotation

    return-void
.end method

.method public setVideoPath(Landroid/net/Uri;IZ)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "uri",
            "startSeconds",
            "useSurfaceView"
        }
    .end annotation

    .line 1107
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0, p1, p2, p3}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->setVideoURI(Landroid/net/Uri;IZ)V

    return-object p0
.end method

.method public setVideoPath(Ljava/lang/String;IZ)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "url",
            "startSeconds",
            "useSurfaceView"
        }
    .end annotation

    if-eqz p1, :cond_0

    .line 1062
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1063
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->getRealUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 1064
    const-string v0, "\u64ad\u653e\u94fe\u63a5"

    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 1065
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    invoke-virtual {p0, p1, p2, p3}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setVideoPath(Landroid/net/Uri;IZ)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object p1

    return-object p1

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public setVideoPreviewListener(Lcom/movieboxpro/android/listener/VideoPreviewListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "videoPreviewListener"
        }
    .end annotation

    .line 3254
    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->videoPreviewListener:Lcom/movieboxpro/android/listener/VideoPreviewListener;

    return-void
.end method

.method public setVideoSource(Ljava/util/List;Lcom/movieboxpro/android/listener/OnVipClickListener;IZI)I
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "mediaMedium",
            "listener",
            "startSeconds",
            "isContinue",
            "pos"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/movieboxpro/android/player/MediaQualityInfo;",
            ">;",
            "Lcom/movieboxpro/android/listener/OnVipClickListener;",
            "IZI)I"
        }
    .end annotation

    const/4 p4, 0x0

    if-eqz p1, :cond_c

    .line 3686
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_4

    .line 3689
    :cond_0
    invoke-static {p1}, Lcom/movieboxpro/android/utils/GroupUtils;->groupQualityList(Ljava/util/List;)Ljava/util/ArrayList;

    move-result-object v0

    .line 3690
    invoke-interface {p1}, Ljava/util/List;->clear()V

    .line 3691
    invoke-interface {p1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 3692
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->audioTrackModels:Ljava/util/ArrayList;

    if-eqz v0, :cond_1

    .line 3693
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 3695
    :cond_1
    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QualityListener:Lcom/movieboxpro/android/listener/OnVipClickListener;

    .line 3697
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    const/4 p2, 0x0

    .line 3698
    :goto_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-ge p2, v0, :cond_2

    .line 3700
    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0, p2}, Lcom/movieboxpro/android/player/MediaQualityInfo;->setIndex(I)V

    .line 3701
    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {v0, p4}, Lcom/movieboxpro/android/player/MediaQualityInfo;->setSelect(Z)V

    .line 3702
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 p2, p2, 0x1

    goto :goto_0

    .line 3704
    :cond_2
    iput p5, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    .line 3706
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p2

    if-lt p5, p2, :cond_3

    .line 3707
    iput p4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    .line 3709
    :cond_3
    iget p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {p2}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->hashCode()I

    invoke-virtual {p2}, Ljava/lang/String;->hashCode()I

    move-result p5

    const/4 v0, 0x2

    const/4 v1, 0x1

    const/4 v2, -0x1

    sparse-switch p5, :sswitch_data_0

    goto :goto_1

    :sswitch_0
    const-string p5, "1080p"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_4

    goto :goto_1

    :cond_4
    const/4 v2, 0x5

    goto :goto_1

    :sswitch_1
    const-string p5, "auto"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_5

    goto :goto_1

    :cond_5
    const/4 v2, 0x4

    goto :goto_1

    :sswitch_2
    const-string p5, "720p"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_6

    goto :goto_1

    :cond_6
    const/4 v2, 0x3

    goto :goto_1

    :sswitch_3
    const-string p5, "360p"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_7

    goto :goto_1

    :cond_7
    const/4 v2, 0x2

    goto :goto_1

    :sswitch_4
    const-string p5, "org"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_8

    goto :goto_1

    :cond_8
    const/4 v2, 0x1

    goto :goto_1

    :sswitch_5
    const-string p5, "4k"

    invoke-virtual {p2, p5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_9

    goto :goto_1

    :cond_9
    const/4 v2, 0x0

    :goto_1
    packed-switch v2, :pswitch_data_0

    goto :goto_2

    .line 3725
    :pswitch_0
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object p5, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget p5, p5, v0

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_2

    .line 3734
    :pswitch_1
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget p5, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_auto:I

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_2

    .line 3719
    :pswitch_2
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object p5, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget p5, p5, v1

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_2

    .line 3713
    :pswitch_3
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget-object p5, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->QUALITY_DRAWABLE_RES:[I

    aget p5, p5, p4

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_2

    .line 3731
    :pswitch_4
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget p5, Lcom/movieboxpro/androidtv/R$mipmap;->ic_media_quality_origin:I

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_2

    .line 3728
    :pswitch_5
    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvMediaQuality:Landroid/widget/ImageView;

    sget p5, Lcom/dl7/player/R$mipmap;->ic_media_quality_4k:I

    invoke-virtual {p2, p5}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 3747
    :goto_2
    iput p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurPosition:I

    .line 3748
    iget p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    invoke-interface {p1, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/movieboxpro/android/player/MediaQualityInfo;

    iput-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    .line 3750
    invoke-virtual {p1}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getHdr()I

    move-result p1

    if-eq p1, v1, :cond_b

    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getReal_quality()Ljava/lang/String;

    move-result-object p1

    const-string p2, "4K"

    invoke-virtual {p2, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_a

    goto :goto_3

    :cond_a
    const/4 v1, 0x0

    .line 3753
    :cond_b
    :goto_3
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mediaQualityInfo:Lcom/movieboxpro/android/player/MediaQualityInfo;

    invoke-virtual {p1}, Lcom/movieboxpro/android/player/MediaQualityInfo;->getPath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1, p3, v1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setVideoPath(Ljava/lang/String;IZ)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    .line 3754
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    iput p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->lastQualityPos:I

    .line 3755
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {p1, p4}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 3756
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {p1, p4}, Landroid/widget/TextView;->setVisibility(I)V

    .line 3758
    iget p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mCurSelectQuality:I

    return p1

    :cond_c
    :goto_4
    return p4

    nop

    :sswitch_data_0
    .sparse-switch
        0x6b7 -> :sswitch_5
        0x1aee4 -> :sswitch_4
        0x17ffe3 -> :sswitch_3
        0x19c25b -> :sswitch_2
        0x2dddaf -> :sswitch_1
        0x2c929f9 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public setVideoTitle(Ljava/lang/String;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "title"
        }
    .end annotation

    .line 1233
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvVideoTitle:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 1234
    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    :cond_0
    return-void
.end method

.method public showContinueText()V
    .locals 4

    .line 4038
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showContinueText:Ljava/lang/Runnable;

    const-wide/16 v2, 0x7d0

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public showLastVideo()V
    .locals 2

    .line 3264
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llLastEpisode:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    return-void
.end method

.method public showLoading()V
    .locals 0

    return-void
.end method

.method public showNextVideo()V
    .locals 2

    .line 3274
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llNextEpisode:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    return-void
.end method

.method public showSkippedTimeHint(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "firstText",
            "secondText",
            "startType"
        }
    .end annotation

    .line 4008
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideContinueText:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 4009
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->showContinueText:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 4010
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->llContinue:Landroid/widget/LinearLayout;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->setVisibility(I)V

    if-eqz p3, :cond_0

    .line 4012
    iget-object p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSkipImage:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_restart_hint:I

    invoke-virtual {p3, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    const/4 p3, 0x1

    .line 4013
    iput-boolean p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->leftBtnHint:Z

    goto :goto_0

    .line 4015
    :cond_0
    iget-object p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->ivSkipImage:Landroid/widget/ImageView;

    sget v0, Lcom/movieboxpro/androidtv/R$mipmap;->ic_right_press_hint:I

    invoke-virtual {p3, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 4016
    iput-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->leftBtnHint:Z

    .line 4018
    :goto_0
    iget-object p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvContinue:Landroid/widget/TextView;

    invoke-virtual {p3, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 4019
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvSkipText:Landroid/widget/TextView;

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 4020
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    iget-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->hideContinueText:Ljava/lang/Runnable;

    const-wide/16 v0, 0xfa0

    invoke-virtual {p1, p2, v0, v1}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method

.method public showTranslateSubtitle(Ljava/util/List;)V
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "srts"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/dl7/player/utils/SRT;",
            ">;)V"
        }
    .end annotation

    .line 3447
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setSimpleSubtitles(Ljava/util/List;)V

    .line 3448
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomTranslate:Landroid/widget/ImageView;

    sget v1, Lcom/movieboxpro/androidtv/R$mipmap;->ic_translate_selected:I

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 3449
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 3450
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method public start()V
    .locals 3

    const/4 v0, 0x0

    .line 1148
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->isFrist:Z

    .line 1149
    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsPlayComplete:Z

    if-eqz v1, :cond_0

    .line 1154
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsPlayComplete:Z

    .line 1156
    :cond_0
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->isPlaying()Z

    move-result v1

    if-nez v1, :cond_1

    .line 1157
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlay:Landroid/widget/ImageView;

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setSelected(Z)V

    .line 1162
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v1}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->start()V

    .line 1164
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    const/16 v2, 0x2766

    invoke-virtual {v1, v2}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 1167
    :cond_1
    iget-boolean v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    if-eqz v1, :cond_2

    .line 1168
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsNeverPlay:Z

    .line 1169
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIvPlayCircle:Landroid/widget/ImageView;

    const/16 v2, 0x8

    invoke-virtual {v1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 1170
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->tvTcpSpeed:Landroid/widget/TextView;

    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 1171
    iget-object v1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mLoadingView:Landroid/widget/ProgressBar;

    invoke-virtual {v1, v0}, Landroid/widget/ProgressBar;->setVisibility(I)V

    .line 1172
    iput-boolean v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mIsShowBar:Z

    .line 1175
    :cond_2
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mAttachActivity:Landroidx/appcompat/app/AppCompatActivity;

    invoke-virtual {v0}, Landroidx/appcompat/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    const/16 v1, 0x80

    invoke-virtual {v0, v1}, Landroid/view/Window;->addFlags(I)V

    return-void
.end method

.method public startSRT(Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/dl7/player/model/SRTModel$subTitles;)V
    .locals 9
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x10,
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "list",
            "content",
            "language",
            "subtitlePath",
            "subTitles"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/dl7/player/utils/SRT;",
            ">;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Lcom/dl7/player/model/SRTModel$subTitles;",
            ")V"
        }
    .end annotation

    .line 3413
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    new-instance v8, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$40;

    move-object v1, v8

    move-object v2, p0

    move-object v3, p5

    move-object v4, p4

    move-object v5, p1

    move-object v6, p2

    move-object v7, p3

    invoke-direct/range {v1 .. v7}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$40;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Lcom/dl7/player/model/SRTModel$subTitles;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v8}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public startSRT2(Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x10,
            0x0,
            0x0,
            0x0
        }
        names = {
            "list",
            "content",
            "language",
            "sid"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/dl7/player/utils/SRT;",
            ">;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .line 3472
    invoke-virtual {p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setSimpleSubtitles(Ljava/util/List;)V

    .line 3473
    iput-object p4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSid:Ljava/lang/String;

    .line 3474
    iget-object p4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    invoke-virtual {p4}, Ljava/util/ArrayList;->clear()V

    .line 3475
    iget-object p4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->srtList:Ljava/util/ArrayList;

    invoke-virtual {p4, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 3476
    iget-object p4, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    new-instance v0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$41;

    invoke-direct {v0, p0, p1}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$41;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;Ljava/util/ArrayList;)V

    invoke-virtual {p4, v0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 3483
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mSrtController:Landroid/widget/LinearLayout;

    const/4 p4, 0x0

    invoke-virtual {p1, p4}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 3486
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mHandler:Landroid/os/Handler;

    new-instance p4, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$42;

    invoke-direct {p4, p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView$42;-><init>(Lcom/movieboxpro/android/player/vlc/VlcPlayerView;)V

    invoke-virtual {p1, p4}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 3492
    iput-object p2, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->subtitleContent:Ljava/lang/String;

    .line 3493
    iput-object p3, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->currSubtitleLanguage:Ljava/lang/String;

    .line 3495
    iget-object p1, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mBottomTranslate:Landroid/widget/ImageView;

    sget p2, Lcom/movieboxpro/androidtv/R$mipmap;->ic_translate_normal:I

    invoke-virtual {p1, p2}, Landroid/widget/ImageView;->setImageResource(I)V

    return-void
.end method

.method public stop()V
    .locals 1

    .line 1251
    invoke-virtual {p0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->pause()V

    .line 1252
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mVideoView:Lcom/movieboxpro/android/player/vlc/VlcVideoView;

    invoke-virtual {v0}, Lcom/movieboxpro/android/player/vlc/VlcVideoView;->stopPlayback()V

    return-void
.end method

.method public switchVideoPath(Landroid/net/Uri;)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "uri"
        }
    .end annotation

    .line 1037
    iget-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    if-eqz v0, :cond_0

    .line 1038
    invoke-interface {v0}, Ljava/util/List;->clear()V

    const/4 v0, 0x0

    .line 1039
    iput-object v0, p0, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->mQualityData:Ljava/util/List;

    :cond_0
    const/4 v0, 0x0

    .line 1042
    invoke-virtual {p0, p1, v0, v0}, Lcom/movieboxpro/android/player/vlc/VlcPlayerView;->setVideoPath(Landroid/net/Uri;IZ)Lcom/movieboxpro/android/player/vlc/VlcPlayerView;

    move-result-object p1

    return-object p1
.end method
