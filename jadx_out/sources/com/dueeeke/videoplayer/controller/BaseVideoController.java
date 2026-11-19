package com.dueeeke.videoplayer.controller;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.dueeeke.videocontroller.R;
import com.dueeeke.videoplayer.StageState;
import com.dueeeke.videoplayer.player.BaseIjkVideoView;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.dueeeke.videoplayer.widget.StatusView;
import com.movieboxpro.android.utils.TimeUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseVideoController extends FrameLayout {
    protected View mControllerView;
    protected int mCurrentPlayState;
    protected int mDefaultTimeout;
    protected final Runnable mFadeOut;
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;
    protected boolean mIsLocked;
    protected MediaPlayerControl mMediaPlayer;
    protected Runnable mShowProgress;
    protected boolean mShowing;
    protected StatusView mStatusView;

    protected abstract int getLayoutId();

    public void getTrackInfo(IjkTrackInfo[] ijkTrackInfoArr) {
    }

    public void hide(StageState stageState) {
    }

    public void setPlayerState(int i) {
    }

    protected int setProgress() {
        return 0;
    }

    public void show(StageState stageState) {
    }

    public BaseVideoController(Context context) {
        this(context, null);
    }

    public BaseVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultTimeout = 4000;
        this.mShowProgress = new Runnable() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.3
            @Override // java.lang.Runnable
            public void run() {
                int progress = BaseVideoController.this.setProgress();
                if (BaseVideoController.this.mMediaPlayer.isPlaying()) {
                    BaseVideoController baseVideoController = BaseVideoController.this;
                    baseVideoController.postDelayed(baseVideoController.mShowProgress, 1000 - (progress % 1000));
                }
            }
        };
        this.mFadeOut = new Runnable() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.4
            @Override // java.lang.Runnable
            public void run() {
                BaseVideoController.this.hide(StageState.Runnable);
            }
        };
        initView();
    }

    protected void initView() {
        this.mControllerView = LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        this.mStatusView = new StatusView(getContext());
        setClickable(true);
        setFocusable(true);
    }

    public void setPlayState(int i) {
        this.mCurrentPlayState = i;
        hideStatusView();
        if (i != -1) {
            return;
        }
        this.mStatusView.setMessage(getResources().getString(R.string.dkplayer_error_message));
        this.mStatusView.setButtonTextAndAction(getResources().getString(R.string.dkplayer_retry), new View.OnClickListener() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseVideoController.this.hideStatusView();
                BaseVideoController.this.mMediaPlayer.retry();
            }
        });
        addView(this.mStatusView, 0);
    }

    public void showStatusView() {
        removeView(this.mStatusView);
        this.mStatusView.setMessage(getResources().getString(R.string.dkplayer_wifi_tip));
        this.mStatusView.setButtonTextAndAction(getResources().getString(R.string.dkplayer_continue_play), new View.OnClickListener() { // from class: com.dueeeke.videoplayer.controller.BaseVideoController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseVideoController.this.hideStatusView();
                BaseIjkVideoView.IS_PLAY_ON_MOBILE_NETWORK = true;
                BaseVideoController.this.mMediaPlayer.start();
            }
        });
        addView(this.mStatusView);
    }

    public MediaPlayerControl getMediaPlayer() {
        return this.mMediaPlayer;
    }

    public void hideStatusView() {
        removeView(this.mStatusView);
    }

    protected void doPauseResume() {
        if (this.mCurrentPlayState == 6) {
            return;
        }
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
        } else {
            this.mMediaPlayer.start();
        }
    }

    protected void doStartStopFullScreen() {
        Activity activityScanForActivity = PlayerUtils.scanForActivity(getContext());
        if (activityScanForActivity == null) {
            return;
        }
        if (this.mMediaPlayer.isFullScreen()) {
            this.mMediaPlayer.stopFullScreen();
            activityScanForActivity.setRequestedOrientation(1);
        } else {
            activityScanForActivity.setRequestedOrientation(0);
            this.mMediaPlayer.startFullScreen();
        }
    }

    protected String getCurrentSystemTime() {
        return new SimpleDateFormat(TimeUtils.PATTERN_MINUTE_SECOND, Locale.getDefault()).format(new Date());
    }

    protected String stringForTime(int i) {
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / DateTimeConstants.SECONDS_PER_HOUR;
        this.mFormatBuilder.setLength(0);
        return i5 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString() : this.mFormatter.format("%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3)).toString();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(this.mShowProgress);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowProgress);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            post(this.mShowProgress);
        }
    }

    public boolean onBackPressed() {
        setPlayState(0);
        return false;
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mMediaPlayer = mediaPlayerControl;
    }

    public void setTrackInfo(int i) {
        this.mMediaPlayer.setTrackInfo(i);
    }
}
