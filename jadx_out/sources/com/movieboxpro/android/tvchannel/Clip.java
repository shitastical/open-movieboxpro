package com.movieboxpro.android.tvchannel;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes5.dex */
public class Clip implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.movieboxpro.android.tvchannel.Clip.1
        @Override // android.os.Parcelable.Creator
        public Clip createFromParcel(Parcel in) {
            return new Clip(in);
        }

        @Override // android.os.Parcelable.Creator
        public Clip[] newArray(int size) {
            return new Clip[size];
        }
    };
    private int boxType;
    private int mAspectRatio;
    private final String mBgImageUrl;
    private String mCardImageUrl;
    private final String mCategory;
    private final String mClipId;
    private final String mContentId;
    private final String mDescription;
    private final boolean mIsVideoProtected;
    private final String mPreviewVideoUrl;
    private long mProgramId;
    private final String mTitle;
    private final String mVideoUrl;
    private int mViewCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Clip(String title, String description, String bgImageUrl, String cardImageUrl, String videoUrl, String previewVideoUrl, boolean isVideoProtected, String category, String clipId, String contentId, int aspectRatio, int boxType) {
        this.mClipId = clipId;
        this.mContentId = contentId;
        this.mTitle = title;
        this.mDescription = description;
        this.mBgImageUrl = bgImageUrl;
        this.mCardImageUrl = cardImageUrl;
        this.mVideoUrl = videoUrl;
        this.mPreviewVideoUrl = previewVideoUrl;
        this.mIsVideoProtected = isVideoProtected;
        this.mCategory = category;
        this.mAspectRatio = aspectRatio;
        this.boxType = boxType;
    }

    private Clip(Parcel in) {
        this.mClipId = in.readString();
        this.mContentId = in.readString();
        this.mTitle = in.readString();
        this.mDescription = in.readString();
        this.mBgImageUrl = in.readString();
        this.mCardImageUrl = in.readString();
        this.mVideoUrl = in.readString();
        this.mPreviewVideoUrl = in.readString();
        this.mIsVideoProtected = in.readByte() == 1;
        this.mCategory = in.readString();
        this.mProgramId = in.readLong();
        this.mViewCount = in.readInt();
        this.boxType = in.readInt();
    }

    public int getBoxType() {
        return this.boxType;
    }

    public void setCardImageUrl(String url) {
        this.mCardImageUrl = url;
    }

    public long getProgramId() {
        return this.mProgramId;
    }

    public void setProgramId(long programId) {
        this.mProgramId = programId;
    }

    public String getClipId() {
        return this.mClipId;
    }

    public String getContentId() {
        return this.mContentId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    String getVideoUrl() {
        return this.mVideoUrl;
    }

    String getPreviewVideoUrl() {
        return this.mPreviewVideoUrl;
    }

    public boolean isVideoProtected() {
        return this.mIsVideoProtected;
    }

    String getBackgroundImageUrl() {
        return this.mBgImageUrl;
    }

    public String getCardImageUrl() {
        return this.mCardImageUrl;
    }

    URI getBackgroundImageURI() {
        try {
            return new URI(this.mBgImageUrl);
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    URI getCardImageURI() {
        try {
            return new URI(getCardImageUrl());
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    int incrementViewCount() {
        int i = this.mViewCount + 1;
        this.mViewCount = i;
        return i;
    }

    void setViewCount(int viewCount) {
        this.mViewCount = viewCount;
    }

    public int getAspectRatio() {
        return this.mAspectRatio;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mClipId);
        parcel.writeString(this.mContentId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mBgImageUrl);
        parcel.writeString(this.mCardImageUrl);
        parcel.writeString(this.mVideoUrl);
        parcel.writeString(this.mPreviewVideoUrl);
        parcel.writeByte(isVideoProtected() ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mCategory);
        parcel.writeLong(this.mProgramId);
        parcel.writeInt(this.mViewCount);
        parcel.writeInt(this.boxType);
    }

    public String toString() {
        return "Clip{clipId=" + this.mClipId + ", contentId='" + this.mContentId + "', title='" + this.mTitle + "', videoUrl='" + this.mVideoUrl + "', backgroundImageUrl='" + this.mBgImageUrl + "', backgroundImageURI='" + getBackgroundImageURI().toString() + "', cardImageUrl='" + this.mCardImageUrl + "', aspectRatio='" + this.mAspectRatio + "', programId='" + this.mProgramId + "', viewCount='" + this.mViewCount + "'}";
    }
}
