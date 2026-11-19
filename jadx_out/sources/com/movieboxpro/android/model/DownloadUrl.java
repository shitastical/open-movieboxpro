package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jeffmony.downloader.model.Video;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: DownloadUrl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b=\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010C\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010D\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010L\u001a\u00020\bHÆ\u0003J\t\u0010M\u001a\u00020\bHÆ\u0003J\t\u0010N\u001a\u00020\bHÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010P\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010Q\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010R\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010%J\t\u0010S\u001a\u00020\u000bHÆ\u0003J\t\u0010T\u001a\u00020\u001cHÆ\u0003J\u0098\u0002\u0010U\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cHÆ\u0001¢\u0006\u0002\u0010VJ\u0006\u0010W\u001a\u00020\bJ\u0013\u0010X\u001a\u00020\u001c2\b\u0010Y\u001a\u0004\u0018\u00010ZHÖ\u0003J\t\u0010[\u001a\u00020\bHÖ\u0001J\t\u0010\\\u001a\u00020\u0003HÖ\u0001J\u0016\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b'\u0010%R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b/\u0010%R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0014\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u0011\u0010\u0015\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b7\u0010%R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b8\u0010%R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010&\u001a\u0004\b9\u0010%R\u0011\u0010\u001a\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=¨\u0006b"}, d2 = {"Lcom/movieboxpro/android/model/DownloadUrl;", "Landroid/os/Parcelable;", FileDownloadService.PARAMS_KEY_PATH, "", "real_quality", IjkMediaMeta.IJKM_KEY_FORMAT, "size", "vip_only", "", "count", "dateline", "", "fid", "tmfid", "mmfid", BreakpointSQLiteKey.FILENAME, "vip_link", "mid", "tid", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "h265", "bitstream", "original", "hdr", "colorbit", "size_bytes", "hls", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;JZ)V", "getPath", "()Ljava/lang/String;", "getReal_quality", "getFormat", "getSize", "getVip_only", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCount", "getDateline", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFid", "getTmfid", "getMmfid", "getFilename", "getVip_link", "getMid", "getTid", "getSeason", "()I", "getEpisode", "getH265", "getBitstream", "getOriginal", "getHdr", "getColorbit", "getSize_bytes", "()J", "getHls", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;JZ)Lcom/movieboxpro/android/model/DownloadUrl;", "describeContents", "equals", Video.TypeInfo.OTHER, "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DownloadUrl implements Parcelable {
    public static final Parcelable.Creator<DownloadUrl> CREATOR = new Creator();
    private final String bitstream;
    private final Integer colorbit;
    private final Integer count;
    private final Long dateline;
    private final int episode;
    private final String fid;
    private final String filename;
    private final String format;
    private final int h265;
    private final Integer hdr;
    private final boolean hls;
    private final String mid;
    private final String mmfid;
    private final Integer original;
    private final String path;
    private final String real_quality;
    private final int season;
    private final String size;
    private final long size_bytes;
    private final String tid;
    private final String tmfid;
    private final Integer vip_link;
    private final Integer vip_only;

    /* JADX INFO: compiled from: DownloadUrl.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<DownloadUrl> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DownloadUrl createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DownloadUrl(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readLong(), parcel.readInt() != 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DownloadUrl[] newArray(int i) {
            return new DownloadUrl[i];
        }
    }

    public DownloadUrl() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, null, null, null, 0L, false, 8388607, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getMmfid() {
        return this.mmfid;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Integer getVip_link() {
        return this.vip_link;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getMid() {
        return this.mid;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTid() {
        return this.tid;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getH265() {
        return this.h265;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getBitstream() {
        return this.bitstream;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final Integer getOriginal() {
        return this.original;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReal_quality() {
        return this.real_quality;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final Integer getHdr() {
        return this.hdr;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final Integer getColorbit() {
        return this.colorbit;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final long getSize_bytes() {
        return this.size_bytes;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final boolean getHls() {
        return this.hls;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getVip_only() {
        return this.vip_only;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFid() {
        return this.fid;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getTmfid() {
        return this.tmfid;
    }

    public final DownloadUrl copy(String path, String real_quality, String format, String size, Integer vip_only, Integer count, Long dateline, String fid, String tmfid, String mmfid, String filename, Integer vip_link, String mid, String tid, int season, int episode, int h265, String bitstream, Integer original, Integer hdr, Integer colorbit, long size_bytes, boolean hls) {
        return new DownloadUrl(path, real_quality, format, size, vip_only, count, dateline, fid, tmfid, mmfid, filename, vip_link, mid, tid, season, episode, h265, bitstream, original, hdr, colorbit, size_bytes, hls);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadUrl)) {
            return false;
        }
        DownloadUrl downloadUrl = (DownloadUrl) other;
        return Intrinsics.areEqual(this.path, downloadUrl.path) && Intrinsics.areEqual(this.real_quality, downloadUrl.real_quality) && Intrinsics.areEqual(this.format, downloadUrl.format) && Intrinsics.areEqual(this.size, downloadUrl.size) && Intrinsics.areEqual(this.vip_only, downloadUrl.vip_only) && Intrinsics.areEqual(this.count, downloadUrl.count) && Intrinsics.areEqual(this.dateline, downloadUrl.dateline) && Intrinsics.areEqual(this.fid, downloadUrl.fid) && Intrinsics.areEqual(this.tmfid, downloadUrl.tmfid) && Intrinsics.areEqual(this.mmfid, downloadUrl.mmfid) && Intrinsics.areEqual(this.filename, downloadUrl.filename) && Intrinsics.areEqual(this.vip_link, downloadUrl.vip_link) && Intrinsics.areEqual(this.mid, downloadUrl.mid) && Intrinsics.areEqual(this.tid, downloadUrl.tid) && this.season == downloadUrl.season && this.episode == downloadUrl.episode && this.h265 == downloadUrl.h265 && Intrinsics.areEqual(this.bitstream, downloadUrl.bitstream) && Intrinsics.areEqual(this.original, downloadUrl.original) && Intrinsics.areEqual(this.hdr, downloadUrl.hdr) && Intrinsics.areEqual(this.colorbit, downloadUrl.colorbit) && this.size_bytes == downloadUrl.size_bytes && this.hls == downloadUrl.hls;
    }

    public int hashCode() {
        String str = this.path;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.real_quality;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.format;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.size;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num = this.vip_only;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.count;
        int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l = this.dateline;
        int iHashCode7 = (iHashCode6 + (l == null ? 0 : l.hashCode())) * 31;
        String str5 = this.fid;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.tmfid;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.mmfid;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.filename;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Integer num3 = this.vip_link;
        int iHashCode12 = (iHashCode11 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str9 = this.mid;
        int iHashCode13 = (iHashCode12 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.tid;
        int iHashCode14 = (((((((iHashCode13 + (str10 == null ? 0 : str10.hashCode())) * 31) + this.season) * 31) + this.episode) * 31) + this.h265) * 31;
        String str11 = this.bitstream;
        int iHashCode15 = (iHashCode14 + (str11 == null ? 0 : str11.hashCode())) * 31;
        Integer num4 = this.original;
        int iHashCode16 = (iHashCode15 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.hdr;
        int iHashCode17 = (iHashCode16 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.colorbit;
        return ((((iHashCode17 + (num6 != null ? num6.hashCode() : 0)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.size_bytes)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.hls);
    }

    public String toString() {
        return "DownloadUrl(path=" + this.path + ", real_quality=" + this.real_quality + ", format=" + this.format + ", size=" + this.size + ", vip_only=" + this.vip_only + ", count=" + this.count + ", dateline=" + this.dateline + ", fid=" + this.fid + ", tmfid=" + this.tmfid + ", mmfid=" + this.mmfid + ", filename=" + this.filename + ", vip_link=" + this.vip_link + ", mid=" + this.mid + ", tid=" + this.tid + ", season=" + this.season + ", episode=" + this.episode + ", h265=" + this.h265 + ", bitstream=" + this.bitstream + ", original=" + this.original + ", hdr=" + this.hdr + ", colorbit=" + this.colorbit + ", size_bytes=" + this.size_bytes + ", hls=" + this.hls + ")";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.path);
        dest.writeString(this.real_quality);
        dest.writeString(this.format);
        dest.writeString(this.size);
        Integer num = this.vip_only;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        Integer num2 = this.count;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        Long l = this.dateline;
        if (l == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeLong(l.longValue());
        }
        dest.writeString(this.fid);
        dest.writeString(this.tmfid);
        dest.writeString(this.mmfid);
        dest.writeString(this.filename);
        Integer num3 = this.vip_link;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        dest.writeString(this.mid);
        dest.writeString(this.tid);
        dest.writeInt(this.season);
        dest.writeInt(this.episode);
        dest.writeInt(this.h265);
        dest.writeString(this.bitstream);
        Integer num4 = this.original;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num4.intValue());
        }
        Integer num5 = this.hdr;
        if (num5 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num5.intValue());
        }
        Integer num6 = this.colorbit;
        if (num6 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num6.intValue());
        }
        dest.writeLong(this.size_bytes);
        dest.writeInt(this.hls ? 1 : 0);
    }

    public DownloadUrl(String str, String str2, String str3, String str4, Integer num, Integer num2, Long l, String str5, String str6, String str7, String str8, Integer num3, String str9, String str10, int i, int i2, int i3, String str11, Integer num4, Integer num5, Integer num6, long j, boolean z) {
        this.path = str;
        this.real_quality = str2;
        this.format = str3;
        this.size = str4;
        this.vip_only = num;
        this.count = num2;
        this.dateline = l;
        this.fid = str5;
        this.tmfid = str6;
        this.mmfid = str7;
        this.filename = str8;
        this.vip_link = num3;
        this.mid = str9;
        this.tid = str10;
        this.season = i;
        this.episode = i2;
        this.h265 = i3;
        this.bitstream = str11;
        this.original = num4;
        this.hdr = num5;
        this.colorbit = num6;
        this.size_bytes = j;
        this.hls = z;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getReal_quality() {
        return this.real_quality;
    }

    public final String getFormat() {
        return this.format;
    }

    public final String getSize() {
        return this.size;
    }

    public final Integer getVip_only() {
        return this.vip_only;
    }

    public final Integer getCount() {
        return this.count;
    }

    public final Long getDateline() {
        return this.dateline;
    }

    public final String getFid() {
        return this.fid;
    }

    public final String getTmfid() {
        return this.tmfid;
    }

    public final String getMmfid() {
        return this.mmfid;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final Integer getVip_link() {
        return this.vip_link;
    }

    public final String getMid() {
        return this.mid;
    }

    public final String getTid() {
        return this.tid;
    }

    public final int getSeason() {
        return this.season;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getH265() {
        return this.h265;
    }

    public final String getBitstream() {
        return this.bitstream;
    }

    public /* synthetic */ DownloadUrl(String str, String str2, String str3, String str4, Integer num, Integer num2, Long l, String str5, String str6, String str7, String str8, Integer num3, String str9, String str10, int i, int i2, int i3, String str11, Integer num4, Integer num5, Integer num6, long j, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? null : str2, (i4 & 4) != 0 ? null : str3, (i4 & 8) != 0 ? null : str4, (i4 & 16) != 0 ? null : num, (i4 & 32) != 0 ? null : num2, (i4 & 64) != 0 ? null : l, (i4 & 128) != 0 ? null : str5, (i4 & 256) != 0 ? null : str6, (i4 & 512) != 0 ? null : str7, (i4 & 1024) != 0 ? null : str8, (i4 & 2048) != 0 ? null : num3, (i4 & 4096) != 0 ? null : str9, (i4 & 8192) != 0 ? null : str10, (i4 & 16384) != 0 ? 0 : i, (i4 & 32768) != 0 ? 0 : i2, (i4 & 65536) != 0 ? 0 : i3, (i4 & 131072) != 0 ? null : str11, (i4 & 262144) != 0 ? 0 : num4, (i4 & 524288) != 0 ? 0 : num5, (i4 & 1048576) != 0 ? 0 : num6, (i4 & 2097152) != 0 ? 0L : j, (i4 & 4194304) == 0 ? z : false);
    }

    public final Integer getOriginal() {
        return this.original;
    }

    public final Integer getHdr() {
        return this.hdr;
    }

    public final Integer getColorbit() {
        return this.colorbit;
    }

    public final long getSize_bytes() {
        return this.size_bytes;
    }

    public final boolean getHls() {
        return this.hls;
    }
}
