package com.dueeeke.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQualityInfo implements Parcelable {
    public static final Parcelable.Creator<MediaQualityInfo> CREATOR = new Parcelable.Creator<MediaQualityInfo>() { // from class: com.dueeeke.model.MediaQualityInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaQualityInfo createFromParcel(Parcel parcel) {
            return new MediaQualityInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaQualityInfo[] newArray(int i) {
            return new MediaQualityInfo[i];
        }
    };
    private String bitstream;
    private String[] errorpath;
    public int fid;
    private int h265;
    private int index;
    private boolean isOriginShow;
    private boolean isSelect;
    private String isVip;
    private String original;
    private String path;
    private String quality;
    private String selected;
    private String size;
    private String time;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaQualityInfo() {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.h265 = 1;
    }

    public MediaQualityInfo(int i, String str, boolean z) {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.h265 = 1;
        this.index = i;
        this.path = str;
        this.isSelect = z;
    }

    public MediaQualityInfo(int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i3, boolean z, String[] strArr, String str8) {
        this.index = i;
        this.h265 = i2;
        this.path = str;
        this.quality = str2;
        this.size = str3;
        this.time = str4;
        this.selected = str5;
        this.isVip = str6;
        this.title = str7;
        this.fid = i3;
        this.isSelect = z;
        this.errorpath = strArr;
        this.bitstream = str8;
    }

    public String getBitstream() {
        return this.bitstream;
    }

    public void setBitstream(String str) {
        this.bitstream = str;
    }

    public boolean isOriginShow() {
        return this.isOriginShow;
    }

    public void setOriginShow(boolean z) {
        this.isOriginShow = z;
    }

    public String getOriginal() {
        return this.original;
    }

    public void setOriginal(String str) {
        this.original = str;
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

    public int getH265() {
        return this.h265;
    }

    public void setH265(int i) {
        this.h265 = i;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public String[] getErrorpath() {
        return this.errorpath;
    }

    public void setErrorpath(String[] strArr) {
        this.errorpath = strArr;
    }

    public String getError() {
        String[] strArr = this.errorpath;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        String str = strArr[0];
        if (strArr.length > 1) {
            this.errorpath = (String[]) Arrays.copyOfRange(strArr, 1, strArr.length);
        } else {
            this.errorpath = null;
        }
        return str;
    }

    public String toString() {
        return "MediaQualityInfo{index=" + this.index + ", desc='" + this.path + "', isSelect=" + this.isSelect + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.index);
        parcel.writeString(this.path);
        parcel.writeStringArray(this.errorpath);
        parcel.writeString(this.quality);
        parcel.writeString(this.size);
        parcel.writeString(this.time);
        parcel.writeString(this.selected);
        parcel.writeString(this.isVip);
        parcel.writeString(this.title);
        parcel.writeInt(this.h265);
        parcel.writeInt(this.fid);
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        parcel.writeString(this.original);
    }

    protected MediaQualityInfo(Parcel parcel) {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.h265 = 1;
        this.index = parcel.readInt();
        this.path = parcel.readString();
        this.errorpath = parcel.createStringArray();
        this.quality = parcel.readString();
        this.size = parcel.readString();
        this.time = parcel.readString();
        this.selected = parcel.readString();
        this.isVip = parcel.readString();
        this.title = parcel.readString();
        this.h265 = parcel.readInt();
        this.fid = parcel.readInt();
        this.isSelect = parcel.readByte() != 0;
        this.original = parcel.readString();
    }
}
