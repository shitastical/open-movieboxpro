package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b5\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\tHÆ\u0003JÁ\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0006\u0010=\u001a\u00020\u0006J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AHÖ\u0003J\t\u0010B\u001a\u00020\u0006HÖ\u0001J\t\u0010C\u001a\u00020\tHÖ\u0001J\u0016\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010*R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001e¨\u0006I"}, d2 = {"Lcom/movieboxpro/android/model/OnlineAudioTrack;", "Landroid/os/Parcelable;", "add_time", "", IjkMediaPlayer.OnNativeInvokeListener.ARG_FILE_SIZE, "map", "", "org", "lang", "", IjkMediaMeta.IJKM_KEY_FORMAT, IjkMediaMeta.IJKM_KEY_BITRATE, "frequency", TvContractCompat.PARAM_CHANNEL, IjkMediaMeta.IJKM_KEY_SAMPLE_RATE, IjkMediaMeta.IJKM_KEY_CHANNEL_LAYOUT, "download_url", "m3u8_url", "quality", "trackId", "name", "<init>", "(JJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAdd_time", "()J", "getFile_size", "getMap", "()I", "getOrg", "getLang", "()Ljava/lang/String;", "getFormat", "getBitrate", "getFrequency", "getChannel", "getSample_rate", "getChannel_layout", "getDownload_url", "getM3u8_url", "getQuality", "getTrackId", "setTrackId", "(Ljava/lang/String;)V", "getName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "describeContents", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class OnlineAudioTrack implements Parcelable {
    public static final Parcelable.Creator<OnlineAudioTrack> CREATOR = new Creator();
    private final long add_time;
    private final String bitrate;
    private final String channel;
    private final String channel_layout;
    private final String download_url;
    private final long file_size;
    private final String format;
    private final String frequency;
    private final String lang;
    private final String m3u8_url;
    private final int map;
    private final String name;
    private final int org;
    private final String quality;
    private final String sample_rate;
    private String trackId;

    /* JADX INFO: compiled from: Bean.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<OnlineAudioTrack> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnlineAudioTrack createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new OnlineAudioTrack(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnlineAudioTrack[] newArray(int i) {
            return new OnlineAudioTrack[i];
        }
    }

    public OnlineAudioTrack() {
        this(0L, 0L, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getAdd_time() {
        return this.add_time;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSample_rate() {
        return this.sample_rate;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getChannel_layout() {
        return this.channel_layout;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getDownload_url() {
        return this.download_url;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getM3u8_url() {
        return this.m3u8_url;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getTrackId() {
        return this.trackId;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getFile_size() {
        return this.file_size;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMap() {
        return this.map;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getOrg() {
        return this.org;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getBitrate() {
        return this.bitrate;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFrequency() {
        return this.frequency;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    public final OnlineAudioTrack copy(long add_time, long file_size, int map, int org2, String lang, String format, String bitrate, String frequency, String channel, String sample_rate, String channel_layout, String download_url, String m3u8_url, String quality, String trackId, String name) {
        return new OnlineAudioTrack(add_time, file_size, map, org2, lang, format, bitrate, frequency, channel, sample_rate, channel_layout, download_url, m3u8_url, quality, trackId, name);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnlineAudioTrack)) {
            return false;
        }
        OnlineAudioTrack onlineAudioTrack = (OnlineAudioTrack) other;
        return this.add_time == onlineAudioTrack.add_time && this.file_size == onlineAudioTrack.file_size && this.map == onlineAudioTrack.map && this.org == onlineAudioTrack.org && Intrinsics.areEqual(this.lang, onlineAudioTrack.lang) && Intrinsics.areEqual(this.format, onlineAudioTrack.format) && Intrinsics.areEqual(this.bitrate, onlineAudioTrack.bitrate) && Intrinsics.areEqual(this.frequency, onlineAudioTrack.frequency) && Intrinsics.areEqual(this.channel, onlineAudioTrack.channel) && Intrinsics.areEqual(this.sample_rate, onlineAudioTrack.sample_rate) && Intrinsics.areEqual(this.channel_layout, onlineAudioTrack.channel_layout) && Intrinsics.areEqual(this.download_url, onlineAudioTrack.download_url) && Intrinsics.areEqual(this.m3u8_url, onlineAudioTrack.m3u8_url) && Intrinsics.areEqual(this.quality, onlineAudioTrack.quality) && Intrinsics.areEqual(this.trackId, onlineAudioTrack.trackId) && Intrinsics.areEqual(this.name, onlineAudioTrack.name);
    }

    public int hashCode() {
        int iM = ((((((HlsDownload$$ExternalSyntheticBackport0.m(this.add_time) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.file_size)) * 31) + this.map) * 31) + this.org) * 31;
        String str = this.lang;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.format;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.bitrate;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.frequency;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.channel;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sample_rate;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.channel_layout;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.download_url;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.m3u8_url;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.quality;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.trackId;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.name;
        return iHashCode11 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        return "OnlineAudioTrack(add_time=" + this.add_time + ", file_size=" + this.file_size + ", map=" + this.map + ", org=" + this.org + ", lang=" + this.lang + ", format=" + this.format + ", bitrate=" + this.bitrate + ", frequency=" + this.frequency + ", channel=" + this.channel + ", sample_rate=" + this.sample_rate + ", channel_layout=" + this.channel_layout + ", download_url=" + this.download_url + ", m3u8_url=" + this.m3u8_url + ", quality=" + this.quality + ", trackId=" + this.trackId + ", name=" + this.name + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeLong(this.add_time);
        dest.writeLong(this.file_size);
        dest.writeInt(this.map);
        dest.writeInt(this.org);
        dest.writeString(this.lang);
        dest.writeString(this.format);
        dest.writeString(this.bitrate);
        dest.writeString(this.frequency);
        dest.writeString(this.channel);
        dest.writeString(this.sample_rate);
        dest.writeString(this.channel_layout);
        dest.writeString(this.download_url);
        dest.writeString(this.m3u8_url);
        dest.writeString(this.quality);
        dest.writeString(this.trackId);
        dest.writeString(this.name);
    }

    public OnlineAudioTrack(long j, long j2, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.add_time = j;
        this.file_size = j2;
        this.map = i;
        this.org = i2;
        this.lang = str;
        this.format = str2;
        this.bitrate = str3;
        this.frequency = str4;
        this.channel = str5;
        this.sample_rate = str6;
        this.channel_layout = str7;
        this.download_url = str8;
        this.m3u8_url = str9;
        this.quality = str10;
        this.trackId = str11;
        this.name = str12;
    }

    public /* synthetic */ OnlineAudioTrack(long j, long j2, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0L : j, (i3 & 2) == 0 ? j2 : 0L, (i3 & 4) != 0 ? 0 : i, (i3 & 8) == 0 ? i2 : 0, (i3 & 16) != 0 ? null : str, (i3 & 32) != 0 ? null : str2, (i3 & 64) != 0 ? null : str3, (i3 & 128) != 0 ? null : str4, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? null : str6, (i3 & 1024) != 0 ? null : str7, (i3 & 2048) != 0 ? null : str8, (i3 & 4096) != 0 ? null : str9, (i3 & 8192) != 0 ? null : str10, (i3 & 16384) != 0 ? null : str11, (i3 & 32768) != 0 ? null : str12);
    }

    public final long getAdd_time() {
        return this.add_time;
    }

    public final long getFile_size() {
        return this.file_size;
    }

    public final int getMap() {
        return this.map;
    }

    public final int getOrg() {
        return this.org;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getFormat() {
        return this.format;
    }

    public final String getBitrate() {
        return this.bitrate;
    }

    public final String getFrequency() {
        return this.frequency;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final String getSample_rate() {
        return this.sample_rate;
    }

    public final String getChannel_layout() {
        return this.channel_layout;
    }

    public final String getDownload_url() {
        return this.download_url;
    }

    public final String getM3u8_url() {
        return this.m3u8_url;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final String getTrackId() {
        return this.trackId;
    }

    public final void setTrackId(String str) {
        this.trackId = str;
    }

    public final String getName() {
        return this.name;
    }
}
