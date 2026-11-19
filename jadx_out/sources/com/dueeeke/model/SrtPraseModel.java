package com.dueeeke.model;

/* JADX INFO: loaded from: classes4.dex */
public class SrtPraseModel {
    private int beginTime;
    private int endTime;
    private boolean isSelected;
    private String srtBody;

    public int getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(int i) {
        this.beginTime = i;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public void setEndTime(int i) {
        this.endTime = i;
    }

    public String getSrtBody() {
        return this.srtBody;
    }

    public void setSrtBody(String str) {
        this.srtBody = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String toString() {
        return "" + this.beginTime + ":" + this.endTime + ":" + this.srtBody;
    }
}
