package com.movieboxpro.android.listener;

import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.player.MediaQualityInfo;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface PlayerViewCallback {
    void disSelectSubtitle(String sid);

    void lastVideo();

    void nextVideo();

    void onOnlineQuality(List<MediaQualityInfo> list, List<BaseMediaModel.DownloadFile> files);

    void onOpenSubtitleClicked();

    void saveProgress();

    void showEpisodeList();

    void showPlayerEngine();

    void switchQuality(int pos, MediaQualityInfo qualityInfo);
}
