package com.movieboxpro.android.http.v2;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApiV2Environment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0085\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010-\u001a\u00020\f2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013¨\u00062"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;", "", "appVersion", "", TvContractCompat.PARAM_CHANNEL, "lang", "openUdid", "deviceModel", "deviceName", "platformVersion", "packageName", "childMode", "", "loggedIn", "uidV2", "numericUid", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;)V", "getAppVersion", "()Ljava/lang/String;", "getChannel", "getLang", "getOpenUdid", "getDeviceModel", "getDeviceName", "getPlatformVersion", "getPackageName", "getChildMode", "()Z", "getLoggedIn", "getUidV2", "getNumericUid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ApiV2EnvironmentSnapshot {
    private final String appVersion;
    private final String channel;
    private final boolean childMode;
    private final String deviceModel;
    private final String deviceName;
    private final String lang;
    private final boolean loggedIn;
    private final String numericUid;
    private final String openUdid;
    private final String packageName;
    private final String platformVersion;
    private final String uidV2;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAppVersion() {
        return this.appVersion;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getLoggedIn() {
        return this.loggedIn;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getUidV2() {
        return this.uidV2;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getNumericUid() {
        return this.numericUid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOpenUdid() {
        return this.openUdid;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDeviceModel() {
        return this.deviceModel;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPlatformVersion() {
        return this.platformVersion;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getChildMode() {
        return this.childMode;
    }

    public final ApiV2EnvironmentSnapshot copy(String appVersion, String channel, String lang, String openUdid, String deviceModel, String deviceName, String platformVersion, String packageName, boolean childMode, boolean loggedIn, String uidV2, String numericUid) {
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(openUdid, "openUdid");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(platformVersion, "platformVersion");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new ApiV2EnvironmentSnapshot(appVersion, channel, lang, openUdid, deviceModel, deviceName, platformVersion, packageName, childMode, loggedIn, uidV2, numericUid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApiV2EnvironmentSnapshot)) {
            return false;
        }
        ApiV2EnvironmentSnapshot apiV2EnvironmentSnapshot = (ApiV2EnvironmentSnapshot) other;
        return Intrinsics.areEqual(this.appVersion, apiV2EnvironmentSnapshot.appVersion) && Intrinsics.areEqual(this.channel, apiV2EnvironmentSnapshot.channel) && Intrinsics.areEqual(this.lang, apiV2EnvironmentSnapshot.lang) && Intrinsics.areEqual(this.openUdid, apiV2EnvironmentSnapshot.openUdid) && Intrinsics.areEqual(this.deviceModel, apiV2EnvironmentSnapshot.deviceModel) && Intrinsics.areEqual(this.deviceName, apiV2EnvironmentSnapshot.deviceName) && Intrinsics.areEqual(this.platformVersion, apiV2EnvironmentSnapshot.platformVersion) && Intrinsics.areEqual(this.packageName, apiV2EnvironmentSnapshot.packageName) && this.childMode == apiV2EnvironmentSnapshot.childMode && this.loggedIn == apiV2EnvironmentSnapshot.loggedIn && Intrinsics.areEqual(this.uidV2, apiV2EnvironmentSnapshot.uidV2) && Intrinsics.areEqual(this.numericUid, apiV2EnvironmentSnapshot.numericUid);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((((((this.appVersion.hashCode() * 31) + this.channel.hashCode()) * 31) + this.lang.hashCode()) * 31) + this.openUdid.hashCode()) * 31) + this.deviceModel.hashCode()) * 31) + this.deviceName.hashCode()) * 31) + this.platformVersion.hashCode()) * 31) + this.packageName.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.childMode)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.loggedIn)) * 31;
        String str = this.uidV2;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.numericUid;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ApiV2EnvironmentSnapshot(appVersion=" + this.appVersion + ", channel=" + this.channel + ", lang=" + this.lang + ", openUdid=" + this.openUdid + ", deviceModel=" + this.deviceModel + ", deviceName=" + this.deviceName + ", platformVersion=" + this.platformVersion + ", packageName=" + this.packageName + ", childMode=" + this.childMode + ", loggedIn=" + this.loggedIn + ", uidV2=" + this.uidV2 + ", numericUid=" + this.numericUid + ")";
    }

    public ApiV2EnvironmentSnapshot(String appVersion, String channel, String lang, String openUdid, String deviceModel, String deviceName, String platformVersion, String packageName, boolean z, boolean z2, String str, String str2) {
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(openUdid, "openUdid");
        Intrinsics.checkNotNullParameter(deviceModel, "deviceModel");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(platformVersion, "platformVersion");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.appVersion = appVersion;
        this.channel = channel;
        this.lang = lang;
        this.openUdid = openUdid;
        this.deviceModel = deviceModel;
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
        this.packageName = packageName;
        this.childMode = z;
        this.loggedIn = z2;
        this.uidV2 = str;
        this.numericUid = str2;
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getChannel() {
        return this.channel;
    }

    public final String getLang() {
        return this.lang;
    }

    public final String getOpenUdid() {
        return this.openUdid;
    }

    public final String getDeviceModel() {
        return this.deviceModel;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getPlatformVersion() {
        return this.platformVersion;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean getChildMode() {
        return this.childMode;
    }

    public final boolean getLoggedIn() {
        return this.loggedIn;
    }

    public final String getUidV2() {
        return this.uidV2;
    }

    public final String getNumericUid() {
        return this.numericUid;
    }
}
