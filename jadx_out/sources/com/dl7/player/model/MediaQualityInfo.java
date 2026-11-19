package com.dl7.player.model;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQualityInfo {
    private String bitstream;
    private int h265;
    private int index;
    private boolean isSelect;
    private String isVip;
    private String path;
    private String quality;
    private String selected;
    private String size;
    private String time;
    private String title;

    public MediaQualityInfo(int i, String str, boolean z) {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.index = i;
        this.path = str;
        this.isSelect = z;
    }

    public MediaQualityInfo(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        this.index = i;
        this.path = str;
        this.quality = str2;
        this.size = str3;
        this.time = str4;
        this.selected = str5;
        this.isVip = str6;
        this.title = str7;
        this.isSelect = z;
    }

    public MediaQualityInfo(int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, String str8) {
        this.index = i;
        this.path = str;
        this.h265 = i2;
        this.quality = str2;
        this.size = str3;
        this.time = str4;
        this.selected = str5;
        this.isVip = str6;
        this.title = str7;
        this.isSelect = z;
        this.bitstream = str8;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getSelected() {
        return this.selected;
    }

    public void setSelected(String str) {
        this.selected = str;
    }

    public String getIsVip() {
        return this.isVip;
    }

    public void setIsVip(String str) {
        this.isVip = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "MediaQualityInfo{index=" + this.index + ", desc='" + this.path + "', isSelect=" + this.isSelect + '}';
    }
}
