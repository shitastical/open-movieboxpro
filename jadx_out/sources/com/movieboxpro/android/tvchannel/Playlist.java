package com.movieboxpro.android.tvchannel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class Playlist implements Parcelable {
    public static final Parcelable.Creator<Playlist> CREATOR = new Parcelable.Creator<Playlist>() { // from class: com.movieboxpro.android.tvchannel.Playlist.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Playlist createFromParcel(Parcel source) {
            return new Playlist(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };
    private String mBgImage;
    private long mChannelId;
    private boolean mChannelPublished;
    private List<Clip> mClips;
    private String mDescription;
    private String mName;
    private String mPlaylistId;
    private String mTitle;
    private String mVideoUri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Playlist() {
    }

    public Playlist(String name, List<Clip> clip, String playlistId) {
        this.mName = name;
        this.mTitle = "playlist title";
        this.mDescription = "playlist description";
        this.mVideoUri = "dsf";
        this.mBgImage = "asdf";
        this.mClips = clip;
        this.mPlaylistId = playlistId;
    }

    public String getmName() {
        return this.mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return this.mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoUri() {
        return this.mVideoUri;
    }

    public void setmVideoUri(String mVideoUri) {
        this.mVideoUri = mVideoUri;
    }

    public String getmBgImage() {
        return this.mBgImage;
    }

    public void setmBgImage(String mBgImage) {
        this.mBgImage = mBgImage;
    }

    public String getmTitle() {
        return this.mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPlaylistId() {
        return this.mPlaylistId;
    }

    public void setmPlaylistId(String mPlaylistId) {
        this.mPlaylistId = mPlaylistId;
    }

    public List<Clip> getmClips() {
        return this.mClips;
    }

    public void setmClips(List<Clip> mClips) {
        this.mClips = mClips;
    }

    public boolean ismChannelPublished() {
        return this.mChannelPublished;
    }

    public void setmChannelPublished(boolean mChannelPublished) {
        this.mChannelPublished = mChannelPublished;
    }

    public long getmChannelId() {
        return this.mChannelId;
    }

    public void setmChannelId(long mChannelId) {
        this.mChannelId = mChannelId;
    }

    public String getName() {
        return this.mName;
    }

    List<Clip> getClips() {
        return this.mClips;
    }

    public String getDescription() {
        return this.mDescription;
    }

    String getPlaylistId() {
        return this.mPlaylistId;
    }

    public boolean isChannelPublished() {
        return this.mChannelPublished;
    }

    public void setChannelPublished(boolean channelPublished) {
        this.mChannelPublished = channelPublished;
    }

    void setChannelPublishedId(long id) {
        this.mChannelPublished = true;
        this.mChannelId = id;
    }

    long getChannelId() {
        return this.mChannelId;
    }

    public String toString() {
        return "Playlist { mName = '" + this.mName + "' mDescription = '" + this.mDescription + "' mVideoUri = '" + this.mVideoUri + "' mBgImage = '" + this.mBgImage + "' mTitle = '" + this.mTitle + "' mList = '" + this.mClips + "' mId = '" + this.mPlaylistId + "' mChannelPublished" + this.mChannelPublished + "'";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mVideoUri);
        parcel.writeString(this.mBgImage);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mPlaylistId);
        parcel.writeTypedList(this.mClips);
        parcel.writeByte(this.mChannelPublished ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mChannelId);
    }

    protected Playlist(Parcel in) {
        this.mName = in.readString();
        this.mDescription = in.readString();
        this.mVideoUri = in.readString();
        this.mBgImage = in.readString();
        this.mTitle = in.readString();
        this.mPlaylistId = in.readString();
        this.mClips = in.createTypedArrayList(Clip.CREATOR);
        this.mChannelPublished = in.readByte() != 0;
        this.mChannelId = in.readLong();
    }
}
