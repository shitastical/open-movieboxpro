package com.movieboxpro.android.model;

import com.dueeeke.model.SRTModel;

/* JADX INFO: loaded from: classes5.dex */
public class FileMd5CheckModel {
    private boolean exists;
    private SRTModel.SubTitles srt_info;

    public boolean isExists() {
        return this.exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public SRTModel.SubTitles getSrt_info() {
        return this.srt_info;
    }

    public void setSrt_info(SRTModel.SubTitles srt_info) {
        this.srt_info = srt_info;
    }
}
