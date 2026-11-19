package com.dueeeke.model;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ResponseSubtitleRecord2 {
    private List<com.dl7.player.model.SRTModel> list;
    private com.dl7.player.model.SRTModel.subTitles select;

    public com.dl7.player.model.SRTModel.subTitles getSelect() {
        return this.select;
    }

    public void setSelect(com.dl7.player.model.SRTModel.subTitles subtitles) {
        this.select = subtitles;
    }

    public List<com.dl7.player.model.SRTModel> getList() {
        return this.list;
    }

    public void setList(List<com.dl7.player.model.SRTModel> list) {
        this.list = list;
    }
}
