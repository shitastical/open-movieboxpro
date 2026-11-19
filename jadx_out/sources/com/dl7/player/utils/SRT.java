package com.dl7.player.utils;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public class SRT implements Parcelable {
    public static final Parcelable.Creator<SRT> CREATOR = new Parcelable.Creator<SRT>() { // from class: com.dl7.player.utils.SRT.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SRT createFromParcel(Parcel parcel) {
            return new SRT(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SRT[] newArray(int i) {
            return new SRT[i];
        }
    };
    private int beginTime;
    private int endTime;
    private boolean isSelected;
    private String srtBody;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

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

    public String toString() {
        return "" + this.beginTime + ":" + this.endTime + ":" + this.srtBody;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.beginTime);
        parcel.writeInt(this.endTime);
        parcel.writeString(this.srtBody);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public SRT() {
    }

    protected SRT(Parcel parcel) {
        this.beginTime = parcel.readInt();
        this.endTime = parcel.readInt();
        this.srtBody = parcel.readString();
        this.isSelected = parcel.readByte() != 0;
    }
}
