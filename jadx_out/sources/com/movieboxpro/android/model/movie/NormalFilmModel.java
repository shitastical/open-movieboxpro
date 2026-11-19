package com.movieboxpro.android.model.movie;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes5.dex */
public class NormalFilmModel implements Parcelable {
    public static final Parcelable.Creator<NormalFilmModel> CREATOR = new Parcelable.Creator<NormalFilmModel>() { // from class: com.movieboxpro.android.model.movie.NormalFilmModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalFilmModel createFromParcel(Parcel source) {
            return new NormalFilmModel(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalFilmModel[] newArray(int size) {
            return new NormalFilmModel[size];
        }
    };

    @SerializedName("3d")
    private int _$3d;
    private String actors;
    private int box_type;
    private boolean checked;
    private String description;
    private int id;
    private String imdb_rating;
    private String mark;
    private String poster;
    private String quality_tag;
    private long runtime;
    private String title;
    private int updateCount;
    private int year;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NormalFilmModel)) {
            return false;
        }
        NormalFilmModel normalFilmModel = (NormalFilmModel) obj;
        return normalFilmModel.box_type == this.box_type && normalFilmModel.id == this.id;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int hashCode() {
        int i = this.id;
        int i2 = this.box_type;
        return (i * i2) + i2 + 31;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int box_type) {
        this.box_type = box_type;
    }

    public String getImdb_rating() {
        return this.imdb_rating;
    }

    public void setImdb_rating(String imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getRuntime() {
        return this.runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int get_$3d() {
        return this._$3d;
    }

    public void set_$3d(int _$3d) {
        this._$3d = _$3d;
    }

    public String getQuality_tag() {
        return this.quality_tag;
    }

    public void setQuality_tag(String quality_tag) {
        this.quality_tag = quality_tag;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.poster);
        parcel.writeInt(this.box_type);
        parcel.writeString(this.imdb_rating);
        parcel.writeInt(this.year);
        parcel.writeLong(this.runtime);
        parcel.writeString(this.actors);
        parcel.writeString(this.description);
        parcel.writeInt(this.updateCount);
        parcel.writeInt(this._$3d);
        parcel.writeString(this.quality_tag);
        parcel.writeByte(this.checked ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mark);
    }

    public NormalFilmModel() {
    }

    protected NormalFilmModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster = in.readString();
        this.box_type = in.readInt();
        this.imdb_rating = in.readString();
        this.year = in.readInt();
        this.runtime = in.readLong();
        this.actors = in.readString();
        this.description = in.readString();
        this.updateCount = in.readInt();
        this._$3d = in.readInt();
        this.quality_tag = in.readString();
        this.checked = in.readByte() != 0;
        this.mark = in.readString();
    }
}
