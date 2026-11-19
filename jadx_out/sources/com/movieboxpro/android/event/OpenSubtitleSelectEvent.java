package com.movieboxpro.android.event;

import com.dl7.player.utils.SRT;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class OpenSubtitleSelectEvent {
    private String content;
    private String language;
    private String sid;
    private List<SRT> srts;

    public OpenSubtitleSelectEvent(List<SRT> srts, String content, String language, String sid) {
        this.srts = srts;
        this.content = content;
        this.language = language;
        this.sid = sid;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SRT> getSrts() {
        return this.srts;
    }

    public void setSrts(List<SRT> srts) {
        this.srts = srts;
    }
}
