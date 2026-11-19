package com.movieboxpro.android.event;

import com.dl7.player.utils.SRT;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TranslateSubtitleEvent {
    private List<SRT> subtitles;

    public TranslateSubtitleEvent(List<SRT> subtitles) {
        this.subtitles = subtitles;
    }

    public List<SRT> getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(List<SRT> subtitles) {
        this.subtitles = subtitles;
    }
}
