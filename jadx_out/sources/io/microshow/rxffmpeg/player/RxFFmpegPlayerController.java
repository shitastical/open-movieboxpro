package io.microshow.rxffmpeg.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class RxFFmpegPlayerController extends FrameLayout {
    protected BaseMediaPlayer mPlayer;
    protected RxFFmpegPlayerView mPlayerView;

    protected abstract int getLayoutId();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void onPause();

    protected abstract void onResume();

    public RxFFmpegPlayerController(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(getLayoutId(), (ViewGroup) this, true);
        initView();
    }

    public void setPlayerView(RxFFmpegPlayerView rxFFmpegPlayerView) {
        if (rxFFmpegPlayerView != null) {
            this.mPlayerView = rxFFmpegPlayerView;
            this.mPlayer = rxFFmpegPlayerView.mPlayer;
            initListener();
        }
    }
}
