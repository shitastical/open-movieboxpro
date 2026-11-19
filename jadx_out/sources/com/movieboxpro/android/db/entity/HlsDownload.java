package com.movieboxpro.android.db.entity;

import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HlsDownload.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\bD\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BÃ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010B\u001a\u00020\u0003HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0013HÆ\u0003J\t\u0010Q\u001a\u00020\u0013HÆ\u0003J\t\u0010R\u001a\u00020\u0013HÆ\u0003J\t\u0010S\u001a\u00020\u0013HÆ\u0003J\t\u0010T\u001a\u00020\u0013HÆ\u0003J\t\u0010U\u001a\u00020\u0013HÆ\u0003JÑ\u0001\u0010V\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u0013HÆ\u0001J\u0013\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Z\u001a\u00020[HÖ\u0001J\t\u0010\\\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001e\"\u0004\b%\u0010&R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001e\"\u0004\b(\u0010&R\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010&R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010&R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010/R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001c\"\u0004\b1\u0010/R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001c\"\u0004\b3\u0010/R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u0010\u0014\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00105\"\u0004\b9\u00107R\u001a\u0010\u0015\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00105\"\u0004\b;\u00107R\u001a\u0010\u0016\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\u001a\u0010\u0017\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u00105\"\u0004\b?\u00107R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00105\"\u0004\bA\u00107¨\u0006]"}, d2 = {"Lcom/movieboxpro/android/db/entity/HlsDownload;", "", "id", "", "m3u8Url", "", "m3u8LocalPath", "downloadId", "videoUrl", "audioUrl", "audioEacUrl", "playbackM3u8LocalPath", "videoLocalPath", "audioLocalPath", "audioEacLocalPath", "videoSize", "audioSize", "audioEacSize", "videoSpeed", "", "audioSpeed", "audioEacSpeed", "videoPercent", "audioPercent", "audioEacPercent", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJFFFFFF)V", "getId", "()J", "getM3u8Url", "()Ljava/lang/String;", "getM3u8LocalPath", "getDownloadId", "getVideoUrl", "getAudioUrl", "getAudioEacUrl", "getPlaybackM3u8LocalPath", "setPlaybackM3u8LocalPath", "(Ljava/lang/String;)V", "getVideoLocalPath", "setVideoLocalPath", "getAudioLocalPath", "setAudioLocalPath", "getAudioEacLocalPath", "setAudioEacLocalPath", "getVideoSize", "setVideoSize", "(J)V", "getAudioSize", "setAudioSize", "getAudioEacSize", "setAudioEacSize", "getVideoSpeed", "()F", "setVideoSpeed", "(F)V", "getAudioSpeed", "setAudioSpeed", "getAudioEacSpeed", "setAudioEacSpeed", "getVideoPercent", "setVideoPercent", "getAudioPercent", "setAudioPercent", "getAudioEacPercent", "setAudioEacPercent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class HlsDownload {
    private String audioEacLocalPath;
    private float audioEacPercent;
    private long audioEacSize;
    private float audioEacSpeed;
    private final String audioEacUrl;
    private String audioLocalPath;
    private float audioPercent;
    private long audioSize;
    private float audioSpeed;
    private final String audioUrl;
    private final String downloadId;
    private final long id;
    private final String m3u8LocalPath;
    private final String m3u8Url;
    private String playbackM3u8LocalPath;
    private String videoLocalPath;
    private float videoPercent;
    private long videoSize;
    private float videoSpeed;
    private final String videoUrl;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAudioLocalPath() {
        return this.audioLocalPath;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getAudioEacLocalPath() {
        return this.audioEacLocalPath;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getVideoSize() {
        return this.videoSize;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getAudioSize() {
        return this.audioSize;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getAudioEacSize() {
        return this.audioEacSize;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final float getVideoSpeed() {
        return this.videoSpeed;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final float getAudioSpeed() {
        return this.audioSpeed;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final float getAudioEacSpeed() {
        return this.audioEacSpeed;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final float getVideoPercent() {
        return this.videoPercent;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final float getAudioPercent() {
        return this.audioPercent;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getM3u8Url() {
        return this.m3u8Url;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final float getAudioEacPercent() {
        return this.audioEacPercent;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getM3u8LocalPath() {
        return this.m3u8LocalPath;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDownloadId() {
        return this.downloadId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAudioUrl() {
        return this.audioUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAudioEacUrl() {
        return this.audioEacUrl;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPlaybackM3u8LocalPath() {
        return this.playbackM3u8LocalPath;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getVideoLocalPath() {
        return this.videoLocalPath;
    }

    public final HlsDownload copy(long id, String m3u8Url, String m3u8LocalPath, String downloadId, String videoUrl, String audioUrl, String audioEacUrl, String playbackM3u8LocalPath, String videoLocalPath, String audioLocalPath, String audioEacLocalPath, long videoSize, long audioSize, long audioEacSize, float videoSpeed, float audioSpeed, float audioEacSpeed, float videoPercent, float audioPercent, float audioEacPercent) {
        Intrinsics.checkNotNullParameter(m3u8Url, "m3u8Url");
        Intrinsics.checkNotNullParameter(m3u8LocalPath, "m3u8LocalPath");
        Intrinsics.checkNotNullParameter(downloadId, "downloadId");
        Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        Intrinsics.checkNotNullParameter(audioEacUrl, "audioEacUrl");
        Intrinsics.checkNotNullParameter(playbackM3u8LocalPath, "playbackM3u8LocalPath");
        Intrinsics.checkNotNullParameter(videoLocalPath, "videoLocalPath");
        Intrinsics.checkNotNullParameter(audioLocalPath, "audioLocalPath");
        Intrinsics.checkNotNullParameter(audioEacLocalPath, "audioEacLocalPath");
        return new HlsDownload(id, m3u8Url, m3u8LocalPath, downloadId, videoUrl, audioUrl, audioEacUrl, playbackM3u8LocalPath, videoLocalPath, audioLocalPath, audioEacLocalPath, videoSize, audioSize, audioEacSize, videoSpeed, audioSpeed, audioEacSpeed, videoPercent, audioPercent, audioEacPercent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HlsDownload)) {
            return false;
        }
        HlsDownload hlsDownload = (HlsDownload) other;
        return this.id == hlsDownload.id && Intrinsics.areEqual(this.m3u8Url, hlsDownload.m3u8Url) && Intrinsics.areEqual(this.m3u8LocalPath, hlsDownload.m3u8LocalPath) && Intrinsics.areEqual(this.downloadId, hlsDownload.downloadId) && Intrinsics.areEqual(this.videoUrl, hlsDownload.videoUrl) && Intrinsics.areEqual(this.audioUrl, hlsDownload.audioUrl) && Intrinsics.areEqual(this.audioEacUrl, hlsDownload.audioEacUrl) && Intrinsics.areEqual(this.playbackM3u8LocalPath, hlsDownload.playbackM3u8LocalPath) && Intrinsics.areEqual(this.videoLocalPath, hlsDownload.videoLocalPath) && Intrinsics.areEqual(this.audioLocalPath, hlsDownload.audioLocalPath) && Intrinsics.areEqual(this.audioEacLocalPath, hlsDownload.audioEacLocalPath) && this.videoSize == hlsDownload.videoSize && this.audioSize == hlsDownload.audioSize && this.audioEacSize == hlsDownload.audioEacSize && Float.compare(this.videoSpeed, hlsDownload.videoSpeed) == 0 && Float.compare(this.audioSpeed, hlsDownload.audioSpeed) == 0 && Float.compare(this.audioEacSpeed, hlsDownload.audioEacSpeed) == 0 && Float.compare(this.videoPercent, hlsDownload.videoPercent) == 0 && Float.compare(this.audioPercent, hlsDownload.audioPercent) == 0 && Float.compare(this.audioEacPercent, hlsDownload.audioEacPercent) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((HlsDownload$$ExternalSyntheticBackport0.m(this.id) * 31) + this.m3u8Url.hashCode()) * 31) + this.m3u8LocalPath.hashCode()) * 31) + this.downloadId.hashCode()) * 31) + this.videoUrl.hashCode()) * 31) + this.audioUrl.hashCode()) * 31) + this.audioEacUrl.hashCode()) * 31) + this.playbackM3u8LocalPath.hashCode()) * 31) + this.videoLocalPath.hashCode()) * 31) + this.audioLocalPath.hashCode()) * 31) + this.audioEacLocalPath.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.videoSize)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.audioSize)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.audioEacSize)) * 31) + Float.floatToIntBits(this.videoSpeed)) * 31) + Float.floatToIntBits(this.audioSpeed)) * 31) + Float.floatToIntBits(this.audioEacSpeed)) * 31) + Float.floatToIntBits(this.videoPercent)) * 31) + Float.floatToIntBits(this.audioPercent)) * 31) + Float.floatToIntBits(this.audioEacPercent);
    }

    public String toString() {
        return "HlsDownload(id=" + this.id + ", m3u8Url=" + this.m3u8Url + ", m3u8LocalPath=" + this.m3u8LocalPath + ", downloadId=" + this.downloadId + ", videoUrl=" + this.videoUrl + ", audioUrl=" + this.audioUrl + ", audioEacUrl=" + this.audioEacUrl + ", playbackM3u8LocalPath=" + this.playbackM3u8LocalPath + ", videoLocalPath=" + this.videoLocalPath + ", audioLocalPath=" + this.audioLocalPath + ", audioEacLocalPath=" + this.audioEacLocalPath + ", videoSize=" + this.videoSize + ", audioSize=" + this.audioSize + ", audioEacSize=" + this.audioEacSize + ", videoSpeed=" + this.videoSpeed + ", audioSpeed=" + this.audioSpeed + ", audioEacSpeed=" + this.audioEacSpeed + ", videoPercent=" + this.videoPercent + ", audioPercent=" + this.audioPercent + ", audioEacPercent=" + this.audioEacPercent + ")";
    }

    public HlsDownload(long j, String m3u8Url, String m3u8LocalPath, String downloadId, String videoUrl, String audioUrl, String audioEacUrl, String playbackM3u8LocalPath, String videoLocalPath, String audioLocalPath, String audioEacLocalPath, long j2, long j3, long j4, float f, float f2, float f3, float f4, float f5, float f6) {
        Intrinsics.checkNotNullParameter(m3u8Url, "m3u8Url");
        Intrinsics.checkNotNullParameter(m3u8LocalPath, "m3u8LocalPath");
        Intrinsics.checkNotNullParameter(downloadId, "downloadId");
        Intrinsics.checkNotNullParameter(videoUrl, "videoUrl");
        Intrinsics.checkNotNullParameter(audioUrl, "audioUrl");
        Intrinsics.checkNotNullParameter(audioEacUrl, "audioEacUrl");
        Intrinsics.checkNotNullParameter(playbackM3u8LocalPath, "playbackM3u8LocalPath");
        Intrinsics.checkNotNullParameter(videoLocalPath, "videoLocalPath");
        Intrinsics.checkNotNullParameter(audioLocalPath, "audioLocalPath");
        Intrinsics.checkNotNullParameter(audioEacLocalPath, "audioEacLocalPath");
        this.id = j;
        this.m3u8Url = m3u8Url;
        this.m3u8LocalPath = m3u8LocalPath;
        this.downloadId = downloadId;
        this.videoUrl = videoUrl;
        this.audioUrl = audioUrl;
        this.audioEacUrl = audioEacUrl;
        this.playbackM3u8LocalPath = playbackM3u8LocalPath;
        this.videoLocalPath = videoLocalPath;
        this.audioLocalPath = audioLocalPath;
        this.audioEacLocalPath = audioEacLocalPath;
        this.videoSize = j2;
        this.audioSize = j3;
        this.audioEacSize = j4;
        this.videoSpeed = f;
        this.audioSpeed = f2;
        this.audioEacSpeed = f3;
        this.videoPercent = f4;
        this.audioPercent = f5;
        this.audioEacPercent = f6;
    }

    public /* synthetic */ HlsDownload(long j, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, long j2, long j3, long j4, float f, float f2, float f3, float f4, float f5, float f6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, str2, str3, str4, str5, str6, (i & 128) != 0 ? "" : str7, (i & 256) != 0 ? "" : str8, (i & 512) != 0 ? "" : str9, (i & 1024) != 0 ? "" : str10, (i & 2048) != 0 ? 0L : j2, (i & 4096) != 0 ? 0L : j3, (i & 8192) != 0 ? 0L : j4, (i & 16384) != 0 ? 0.0f : f, (32768 & i) != 0 ? 0.0f : f2, (65536 & i) != 0 ? 0.0f : f3, (131072 & i) != 0 ? 0.0f : f4, (262144 & i) != 0 ? 0.0f : f5, (i & 524288) != 0 ? 0.0f : f6);
    }

    public final long getId() {
        return this.id;
    }

    public final String getM3u8Url() {
        return this.m3u8Url;
    }

    public final String getM3u8LocalPath() {
        return this.m3u8LocalPath;
    }

    public final String getDownloadId() {
        return this.downloadId;
    }

    public final String getVideoUrl() {
        return this.videoUrl;
    }

    public final String getAudioUrl() {
        return this.audioUrl;
    }

    public final String getAudioEacUrl() {
        return this.audioEacUrl;
    }

    public final String getPlaybackM3u8LocalPath() {
        return this.playbackM3u8LocalPath;
    }

    public final void setPlaybackM3u8LocalPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.playbackM3u8LocalPath = str;
    }

    public final String getVideoLocalPath() {
        return this.videoLocalPath;
    }

    public final void setVideoLocalPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.videoLocalPath = str;
    }

    public final String getAudioLocalPath() {
        return this.audioLocalPath;
    }

    public final void setAudioLocalPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.audioLocalPath = str;
    }

    public final String getAudioEacLocalPath() {
        return this.audioEacLocalPath;
    }

    public final void setAudioEacLocalPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.audioEacLocalPath = str;
    }

    public final long getVideoSize() {
        return this.videoSize;
    }

    public final void setVideoSize(long j) {
        this.videoSize = j;
    }

    public final long getAudioSize() {
        return this.audioSize;
    }

    public final void setAudioSize(long j) {
        this.audioSize = j;
    }

    public final long getAudioEacSize() {
        return this.audioEacSize;
    }

    public final void setAudioEacSize(long j) {
        this.audioEacSize = j;
    }

    public final float getVideoSpeed() {
        return this.videoSpeed;
    }

    public final void setVideoSpeed(float f) {
        this.videoSpeed = f;
    }

    public final float getAudioSpeed() {
        return this.audioSpeed;
    }

    public final void setAudioSpeed(float f) {
        this.audioSpeed = f;
    }

    public final float getAudioEacSpeed() {
        return this.audioEacSpeed;
    }

    public final void setAudioEacSpeed(float f) {
        this.audioEacSpeed = f;
    }

    public final float getVideoPercent() {
        return this.videoPercent;
    }

    public final void setVideoPercent(float f) {
        this.videoPercent = f;
    }

    public final float getAudioPercent() {
        return this.audioPercent;
    }

    public final void setAudioPercent(float f) {
        this.audioPercent = f;
    }

    public final float getAudioEacPercent() {
        return this.audioEacPercent;
    }

    public final void setAudioEacPercent(float f) {
        this.audioEacPercent = f;
    }
}
