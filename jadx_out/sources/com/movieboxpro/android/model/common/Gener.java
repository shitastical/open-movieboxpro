package com.movieboxpro.android.model.common;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public class Gener implements Parcelable {
    public static final Parcelable.Creator<Gener> CREATOR = new Parcelable.Creator<Gener>() { // from class: com.movieboxpro.android.model.common.Gener.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gener createFromParcel(Parcel source) {
            return new Gener(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Gener[] newArray(int size) {
            return new Gener[size];
        }
    };
    private int hot;
    private String id;
    private String img;
    private boolean isSelect;
    private String mark;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean select) {
        this.isSelect = select;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getHot() {
        return this.hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.mark);
        dest.writeInt(this.hot);
        dest.writeString(this.img);
    }

    public Gener() {
    }

    protected Gener(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.mark = in.readString();
        this.hot = in.readInt();
        this.img = in.readString();
    }
}
