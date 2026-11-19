package com.movieboxpro.android.model;

import com.dueeeke.model.ExtrModel;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class OpenSubtitleModel {
    private String language;
    private List<ExtrModel> subtitls;

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<ExtrModel> getSubtitls() {
        return this.subtitls;
    }

    public void setSubtitls(List<ExtrModel> subtitls) {
        this.subtitls = subtitls;
    }
}
