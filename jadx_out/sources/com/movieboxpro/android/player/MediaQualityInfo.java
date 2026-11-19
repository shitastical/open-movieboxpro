package com.movieboxpro.android.player;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaQualityInfo implements Parcelable {
    public static final Parcelable.Creator<MediaQualityInfo> CREATOR = new Parcelable.Creator<MediaQualityInfo>() { // from class: com.movieboxpro.android.player.MediaQualityInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaQualityInfo createFromParcel(Parcel source) {
            return new MediaQualityInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaQualityInfo[] newArray(int size) {
            return new MediaQualityInfo[size];
        }
    };
    private String bitstream;
    private int colorbit;
    private int fid;
    private String filename;
    private int h265;
    private int hdr;
    private boolean hls;
    private int index;
    private boolean isSelect;
    private String isVip;
    private boolean localFile;
    private int mmfid;
    private int original;
    private String path;
    private String real_quality;
    private String selected;
    private String size;
    private String time;
    private String title;
    private int tmfid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaQualityInfo(int index, String path, boolean isSelect) {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.h265 = 1;
        this.mmfid = -1;
        this.tmfid = -1;
        this.index = index;
        this.path = path;
        this.isSelect = isSelect;
    }

    public MediaQualityInfo(int index, int h265, String path, String quality, String size, String time, String selected, String isVip, String title, boolean isSelect, String bitstream, int original, int hdr, int colorbit, String filename, boolean hls) {
        this(index, h265, path, quality, size, time, selected, isVip, title, isSelect, bitstream, original, hdr, colorbit, filename, hls, -1);
    }

    public MediaQualityInfo(int index, int h265, String path, String quality, String size, String time, String selected, String isVip, String title, boolean isSelect, String bitstream, int original, int hdr, int colorbit, String filename, boolean hls, int mmfid) {
        this(index, h265, path, quality, size, time, selected, isVip, title, isSelect, bitstream, original, hdr, colorbit, filename, hls, mmfid, -1);
    }

    public MediaQualityInfo(int index, int h265, String path, String quality, String size, String time, String selected, String isVip, String title, boolean isSelect, String bitstream, int original, int hdr, int colorbit, String filename, boolean hls, int mmfid, int tmfid) {
        this.index = index;
        this.path = path;
        this.h265 = h265;
        this.real_quality = quality;
        this.size = size;
        this.time = time;
        this.selected = selected;
        this.isVip = isVip;
        this.title = title;
        this.isSelect = isSelect;
        this.bitstream = bitstream;
        this.original = original;
        this.hdr = hdr;
        this.colorbit = colorbit;
        this.filename = filename;
        this.hls = hls;
        this.mmfid = mmfid;
        this.tmfid = tmfid;
    }

    public boolean isHls() {
        return this.hls;
    }

    public void setHls(boolean hls) {
        this.hls = hls;
    }

    public int getMmfid() {
        return this.mmfid;
    }

    public void setMmfid(int mmfid) {
        this.mmfid = mmfid;
    }

    public int getTmfid() {
        return this.tmfid;
    }

    public void setTmfid(int tmfid) {
        this.tmfid = tmfid;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getColorbit() {
        return this.colorbit;
    }

    public void setColorbit(int colorbit) {
        this.colorbit = colorbit;
    }

    public int getHdr() {
        return this.hdr;
    }

    public void setHdr(int hdr) {
        this.hdr = hdr;
    }

    public boolean isLocalFile() {
        return this.localFile;
    }

    public void setLocalFile(boolean localFile) {
        this.localFile = localFile;
    }

    public String getReal_quality() {
        return this.real_quality;
    }

    public void setReal_quality(String real_quality) {
        this.real_quality = real_quality;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getOriginal() {
        return this.original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public String getBitstream() {
        return this.bitstream;
    }

    public void setBitstream(String bitstream) {
        this.bitstream = bitstream;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String desc) {
        this.path = desc;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSelected() {
        return this.selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getIsVip() {
        return this.isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getH265() {
        return this.h265;
    }

    public void setH265(int h265) {
        this.h265 = h265;
    }

    public String toString() {
        return "MediaQualityInfo{index=" + this.index + ", desc='" + this.path + "', isSelect=" + this.isSelect + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.index);
        parcel.writeString(this.path);
        parcel.writeString(this.size);
        parcel.writeString(this.time);
        parcel.writeString(this.selected);
        parcel.writeString(this.isVip);
        parcel.writeString(this.title);
        parcel.writeInt(this.fid);
        parcel.writeInt(this.h265);
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.original);
        parcel.writeString(this.bitstream);
        parcel.writeByte(this.localFile ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.hdr);
        parcel.writeInt(this.colorbit);
        parcel.writeString(this.filename);
        parcel.writeByte(this.hls ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mmfid);
        parcel.writeInt(this.tmfid);
    }

    protected MediaQualityInfo(Parcel in) {
        this.size = "0MB";
        this.selected = "0";
        this.isVip = "0";
        this.h265 = 1;
        this.mmfid = -1;
        this.tmfid = -1;
        this.index = in.readInt();
        this.path = in.readString();
        this.size = in.readString();
        this.time = in.readString();
        this.selected = in.readString();
        this.isVip = in.readString();
        this.title = in.readString();
        this.fid = in.readInt();
        this.h265 = in.readInt();
        this.isSelect = in.readByte() != 0;
        this.original = in.readInt();
        this.bitstream = in.readString();
        this.localFile = in.readByte() != 0;
        this.hdr = in.readInt();
        this.colorbit = in.readInt();
        this.filename = in.readString();
        this.hls = in.readByte() != 0;
        this.mmfid = in.readInt();
        this.tmfid = in.readInt();
    }
}
