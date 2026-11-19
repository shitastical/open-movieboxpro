package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BË\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0002\u0010*JÒ\u0001\u0010=\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÆ\u0001¢\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\u0007HÖ\u0001J\t\u0010C\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*¨\u0006D"}, d2 = {"Lcom/movieboxpro/android/model/HlsItem;", "", "type", "", "quality", IjkMediaMeta.IJKM_KEY_FORMAT, "hdr", "", "bit_rate", "default", "label", "vip_only", "is_h265", "m3u8_url", "download_url", IjkMediaMeta.IJKM_KEY_CHANNEL_LAYOUT, IjkMediaMeta.IJKM_KEY_BITRATE, IjkMediaMeta.IJKM_KEY_SAMPLE_RATE, "lang", "name", IjkMediaPlayer.OnNativeInvokeListener.ARG_FILE_SIZE, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getType", "()Ljava/lang/String;", "getQuality", "getFormat", "getHdr", "()I", "getBit_rate", "getDefault", "getLabel", "getVip_only", "getM3u8_url", "getDownload_url", "getChannel_layout", "getBitrate", "getSample_rate", "getLang", "getName", "getFile_size", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/movieboxpro/android/model/HlsItem;", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class HlsItem {
    private final String bit_rate;
    private final String bitrate;
    private final String channel_layout;
    private final int default;
    private final String download_url;
    private final Long file_size;
    private final String format;
    private final int hdr;
    private final int is_h265;
    private final String label;
    private final String lang;
    private final String m3u8_url;
    private final String name;
    private final String quality;
    private final String sample_rate;
    private final String type;
    private final int vip_only;

    public HlsItem() {
        this(null, null, null, 0, null, 0, null, 0, 0, null, null, null, null, null, null, null, null, 131071, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getM3u8_url() {
        return this.m3u8_url;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getDownload_url() {
        return this.download_url;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getChannel_layout() {
        return this.channel_layout;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getBitrate() {
        return this.bitrate;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSample_rate() {
        return this.sample_rate;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final Long getFile_size() {
        return this.file_size;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getHdr() {
        return this.hdr;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBit_rate() {
        return this.bit_rate;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getDefault() {
        return this.default;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getVip_only() {
        return this.vip_only;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getIs_h265() {
        return this.is_h265;
    }

    public final HlsItem copy(String type, String quality, String format, int hdr, String bit_rate, int i, String label, int vip_only, int is_h265, String m3u8_url, String download_url, String channel_layout, String bitrate, String sample_rate, String lang, String name, Long file_size) {
        return new HlsItem(type, quality, format, hdr, bit_rate, i, label, vip_only, is_h265, m3u8_url, download_url, channel_layout, bitrate, sample_rate, lang, name, file_size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HlsItem)) {
            return false;
        }
        HlsItem hlsItem = (HlsItem) other;
        return Intrinsics.areEqual(this.type, hlsItem.type) && Intrinsics.areEqual(this.quality, hlsItem.quality) && Intrinsics.areEqual(this.format, hlsItem.format) && this.hdr == hlsItem.hdr && Intrinsics.areEqual(this.bit_rate, hlsItem.bit_rate) && this.default == hlsItem.default && Intrinsics.areEqual(this.label, hlsItem.label) && this.vip_only == hlsItem.vip_only && this.is_h265 == hlsItem.is_h265 && Intrinsics.areEqual(this.m3u8_url, hlsItem.m3u8_url) && Intrinsics.areEqual(this.download_url, hlsItem.download_url) && Intrinsics.areEqual(this.channel_layout, hlsItem.channel_layout) && Intrinsics.areEqual(this.bitrate, hlsItem.bitrate) && Intrinsics.areEqual(this.sample_rate, hlsItem.sample_rate) && Intrinsics.areEqual(this.lang, hlsItem.lang) && Intrinsics.areEqual(this.name, hlsItem.name) && Intrinsics.areEqual(this.file_size, hlsItem.file_size);
    }

    public int hashCode() {
        String str = this.type;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.quality;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.format;
        int iHashCode3 = (((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.hdr) * 31;
        String str4 = this.bit_rate;
        int iHashCode4 = (((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.default) * 31;
        String str5 = this.label;
        int iHashCode5 = (((((iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.vip_only) * 31) + this.is_h265) * 31;
        String str6 = this.m3u8_url;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.download_url;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.channel_layout;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.bitrate;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.sample_rate;
        int iHashCode10 = (iHashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.lang;
        int iHashCode11 = (iHashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.name;
        int iHashCode12 = (iHashCode11 + (str12 == null ? 0 : str12.hashCode())) * 31;
        Long l = this.file_size;
        return iHashCode12 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "HlsItem(type=" + this.type + ", quality=" + this.quality + ", format=" + this.format + ", hdr=" + this.hdr + ", bit_rate=" + this.bit_rate + ", default=" + this.default + ", label=" + this.label + ", vip_only=" + this.vip_only + ", is_h265=" + this.is_h265 + ", m3u8_url=" + this.m3u8_url + ", download_url=" + this.download_url + ", channel_layout=" + this.channel_layout + ", bitrate=" + this.bitrate + ", sample_rate=" + this.sample_rate + ", lang=" + this.lang + ", name=" + this.name + ", file_size=" + this.file_size + ")";
    }

    public HlsItem(String str, String str2, String str3, int i, String str4, int i2, String str5, int i3, int i4, String str6, String str7, String str8, String str9, String str10, String str11, String str12, Long l) {
        this.type = str;
        this.quality = str2;
        this.format = str3;
        this.hdr = i;
        this.bit_rate = str4;
        this.default = i2;
        this.label = str5;
        this.vip_only = i3;
        this.is_h265 = i4;
        this.m3u8_url = str6;
        this.download_url = str7;
        this.channel_layout = str8;
        this.bitrate = str9;
        this.sample_rate = str10;
        this.lang = str11;
        this.name = str12;
        this.file_size = l;
    }

    public final String getType() {
        return this.type;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final String getFormat() {
        return this.format;
    }

    public final int getHdr() {
        return this.hdr;
    }

    public final String getBit_rate() {
        return this.bit_rate;
    }

    public final int getDefault() {
        return this.default;
    }

    public final String getLabel() {
        return this.label;
    }

    public final int getVip_only() {
        return this.vip_only;
    }

    public final int is_h265() {
        return this.is_h265;
    }

    public final String getM3u8_url() {
        return this.m3u8_url;
    }

    public final String getDownload_url() {
        return this.download_url;
    }

    public final String getChannel_layout() {
        return this.channel_layout;
    }

    public final String getBitrate() {
        return this.bitrate;
    }

    public final String getSample_rate() {
        return this.sample_rate;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getName() {
        return this.name;
    }

    public /* synthetic */ HlsItem(String str, String str2, String str3, int i, String str4, int i2, String str5, int i3, int i4, String str6, String str7, String str8, String str9, String str10, String str11, String str12, Long l, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? null : str, (i5 & 2) != 0 ? null : str2, (i5 & 4) != 0 ? null : str3, (i5 & 8) != 0 ? 0 : i, (i5 & 16) != 0 ? null : str4, (i5 & 32) != 0 ? 0 : i2, (i5 & 64) != 0 ? null : str5, (i5 & 128) != 0 ? 0 : i3, (i5 & 256) == 0 ? i4 : 0, (i5 & 512) != 0 ? null : str6, (i5 & 1024) != 0 ? null : str7, (i5 & 2048) != 0 ? null : str8, (i5 & 4096) != 0 ? null : str9, (i5 & 8192) != 0 ? null : str10, (i5 & 16384) != 0 ? null : str11, (i5 & 32768) != 0 ? null : str12, (i5 & 65536) != 0 ? 0L : l);
    }

    public final Long getFile_size() {
        return this.file_size;
    }
}
