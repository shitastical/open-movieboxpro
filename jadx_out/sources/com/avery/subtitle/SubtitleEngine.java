package com.avery.subtitle;

import android.media.MediaPlayer;
import com.avery.subtitle.model.Subtitle;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface SubtitleEngine {

    public interface OnSubtitleChangeListener {
        void onSubtitleChanged(Subtitle subtitle);
    }

    public interface OnSubtitlePreparedListener {
        void onSubtitlePrepared(List<Subtitle> list);
    }

    void bindOnMediaStatusListener(OnMediaStatusListener onMediaStatusListener);

    void bindToMediaPlayer(MediaPlayer mediaPlayer);

    void clearSubtitles();

    void destroy();

    int getCurrSubtitlePos();

    List<Subtitle> getSubtitles();

    void pause();

    void refreshSubtitle();

    void reset();

    void resume();

    long setCurrSubtitle(Subtitle subtitle);

    void setDelay(int i);

    void setOnSubtitleChangeListener(OnSubtitleChangeListener onSubtitleChangeListener);

    void setOnSubtitlePreparedListener(OnSubtitlePreparedListener onSubtitlePreparedListener);

    void setSubtitlePath(String str);

    void setSubtitles(List<Subtitle> list);

    void start();

    void stop();
}
