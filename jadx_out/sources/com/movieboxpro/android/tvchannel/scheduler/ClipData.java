package com.movieboxpro.android.tvchannel.scheduler;

import com.movieboxpro.android.model.BaseMediaModel;

/* JADX INFO: loaded from: classes5.dex */
public class ClipData {
    private final int boxType;
    private final BaseMediaModel mBaseMediaModel;
    private String mCardImageUrl;
    private final String mClipId;
    private final String mContentId;
    private final String mDescription;
    private final long mDuration;
    private final long mProgress;
    private final String mTitle;

    private ClipData(Builder b) {
        this.mClipId = b.mClipId;
        this.mContentId = b.mContentId;
        this.mDuration = b.mDuration;
        this.mProgress = b.mProgress;
        this.mTitle = b.mTitle;
        this.mDescription = b.mDescription;
        this.mCardImageUrl = b.mCardImageUrl;
        this.mBaseMediaModel = b.mBaseMediaModel;
        this.boxType = b.boxType;
    }

    public String getClipId() {
        return this.mClipId;
    }

    public String getContentId() {
        return this.mContentId;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getProgress() {
        return this.mProgress;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getCardImageUrl() {
        return this.mCardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.mCardImageUrl = cardImageUrl;
    }

    public BaseMediaModel getmBaseMediaModel() {
        return this.mBaseMediaModel;
    }

    public int getBoxType() {
        return this.boxType;
    }

    public static class Builder {
        int boxType;
        BaseMediaModel mBaseMediaModel;
        String mCardImageUrl = "";
        String mClipId;
        String mContentId;
        String mDescription;
        long mDuration;
        long mProgress;
        String mTitle;

        public Builder setBoxType(int boxType) {
            this.boxType = boxType;
            return this;
        }

        public Builder setBaseMediaModel(BaseMediaModel baseMediaModel) {
            this.mBaseMediaModel = baseMediaModel;
            return this;
        }

        public Builder setClipId(String id) {
            this.mClipId = id;
            return this;
        }

        public Builder setContentId(String contentId) {
            this.mContentId = contentId;
            return this;
        }

        public Builder setDuration(long duration) {
            this.mDuration = duration;
            return this;
        }

        public Builder setProgress(long progress) {
            this.mProgress = progress;
            return this;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.mDescription = description;
            return this;
        }

        public Builder setCardImageUrl(String cardImageUrl) {
            this.mCardImageUrl = cardImageUrl;
            return this;
        }

        public ClipData build() {
            return new ClipData(this);
        }
    }
}
