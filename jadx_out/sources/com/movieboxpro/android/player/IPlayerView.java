package com.movieboxpro.android.player;

import android.content.res.Configuration;
import android.widget.ImageView;
import android.widget.MediaController;
import androidx.fragment.app.FragmentManager;
import com.dl7.player.model.SRTModel;
import com.dl7.player.utils.SRT;
import com.movieboxpro.android.listener.OnDownloadClickListener;
import com.movieboxpro.android.listener.OnVipClickListener;
import com.movieboxpro.android.listener.PlayerViewCallback;
import com.movieboxpro.android.listener.VideoPreviewListener;
import com.movieboxpro.android.model.BaseMediaModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes5.dex */
public interface IPlayerView {
    void _toggleControlBar();

    void _togglePlayStatus(boolean focus);

    IPlayerView alwaysFullScreen();

    void clearSubtitle();

    void configurationChanged(Configuration newConfig);

    void doPause();

    void doStart();

    IPlayerView enableOrientation();

    int getCurPosition();

    int getCurrQuality();

    int getDurarion();

    ImageView getImageThumb();

    int getVideoHeight();

    MediaController.MediaPlayerControl getVideoView();

    int getVideoWidth();

    boolean handleVolumeKey(int keyCode);

    void hideLastVideo();

    void hideNextVideo();

    IPlayerView init(int boxType);

    boolean onBackPressed();

    int onDestroy();

    int onStop();

    void releaseAudioPlay();

    void requestEpisodeListFocus();

    void requestRootFocues();

    void seekTo(int progress);

    void setFragmentManager(FragmentManager fragmentManager);

    void setHlsTracks(List<BaseMediaModel.DownloadFile> tracks);

    void setKeepControlBarVisible(boolean keep);

    void setMovieDownload(BaseMediaModel baseMediaModel);

    void setOnCompletionListener(IMediaPlayer.OnCompletionListener listener);

    void setPlayerViewCallback(PlayerViewCallback playerViewCallback);

    IPlayerView setSRTList(LinkedHashMap<String, List<SRTModel.subTitles>> srtList, OnDownloadClickListener listener, SRTModel.subTitles selectSubtitle);

    void setSubtitleMarginBottom(int bottom);

    void setSubtitleSize(int size);

    void setVideoPreviewListener(VideoPreviewListener videoPreviewListener);

    int setVideoSource(List<MediaQualityInfo> mediaMedium, OnVipClickListener listener, int startSeconds, boolean isContinue, int pos);

    void setVideoTitle(String title);

    void showContinueText();

    void showLastVideo();

    void showLoading();

    void showNextVideo();

    void showSkippedTimeHint(String firstText, String secondText, boolean startType);

    void showTranslateSubtitle(List<SRT> srts);

    void startSRT(final ArrayList<SRT> list, String content, String language, String subtitlePath, SRTModel.subTitles subTitles);

    void startSRT2(final ArrayList<SRT> list, String content, String language, String sid);
}
