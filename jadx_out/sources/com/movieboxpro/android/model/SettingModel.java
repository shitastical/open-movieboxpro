package com.movieboxpro.android.model;

import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.model.Video;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingModel.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+BQ\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003JU\u0010%\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0007HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/model/SettingModel;", "", TvContractCompat.ProgramColumns.COLUMN_TITLE, "", "desc", NotificationCompat.CATEGORY_STATUS, "resId", "", "type", "viewType", "icon", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIII)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getDesc", "setDesc", "getStatus", "setStatus", "getResId", "()I", "setResId", "(I)V", "getType", "setType", "getViewType", "setViewType", "getIcon", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SettingModel {
    public static final int AUDIO_DELAY = 11;
    public static final int AUTO_PLAY_NEXT = 7;
    public static final int AUTO_SELECT_SUBTITLE = 10;
    public static final int CHECK_UPDATE = 13;
    public static final int CHILD_MODE = 3;
    public static final int DOWNLOAD_LANGUAGE = 20;
    public static final int DOWNLOAD_LOCATION = 6;
    public static final int HIDE_DOWNLOAD = 17;
    public static final int HIDE_MOVIE_LIST = 5;
    public static final int HW_DECODER = 21;
    public static final int IMAGE_HOST = 19;
    public static final int MANAGER_ACCOUNT = 12;
    public static final int PLAYER_ENGINE = 9;
    public static final int PLAY_WITH_OTHER = 8;
    public static final int PRIVATE_MODE = 18;
    public static final int REMEMBER_QUALITY = 4;
    public static final int SKIP_OPENING_AND_ENDING = 14;
    public static final int SMART_DOWNLOAD = 16;
    public static final int SOLID_BLACK_BACKGROUND = 15;
    public static final int TYPE_CHANGE_LANGUAGE = 2;
    public static final int TYPE_TEST_SPEED = 1;
    private String desc;
    private final int icon;
    private int resId;
    private String status;
    private String title;
    private int type;
    private int viewType;

    public static /* synthetic */ SettingModel copy$default(SettingModel settingModel, String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            str = settingModel.title;
        }
        if ((i5 & 2) != 0) {
            str2 = settingModel.desc;
        }
        String str4 = str2;
        if ((i5 & 4) != 0) {
            str3 = settingModel.status;
        }
        String str5 = str3;
        if ((i5 & 8) != 0) {
            i = settingModel.resId;
        }
        int i6 = i;
        if ((i5 & 16) != 0) {
            i2 = settingModel.type;
        }
        int i7 = i2;
        if ((i5 & 32) != 0) {
            i3 = settingModel.viewType;
        }
        int i8 = i3;
        if ((i5 & 64) != 0) {
            i4 = settingModel.icon;
        }
        return settingModel.copy(str, str4, str5, i6, i7, i8, i4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getResId() {
        return this.resId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getViewType() {
        return this.viewType;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getIcon() {
        return this.icon;
    }

    public final SettingModel copy(String title, String desc, String status, int resId, int type, int viewType, int icon) {
        return new SettingModel(title, desc, status, resId, type, viewType, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingModel)) {
            return false;
        }
        SettingModel settingModel = (SettingModel) other;
        return Intrinsics.areEqual(this.title, settingModel.title) && Intrinsics.areEqual(this.desc, settingModel.desc) && Intrinsics.areEqual(this.status, settingModel.status) && this.resId == settingModel.resId && this.type == settingModel.type && this.viewType == settingModel.viewType && this.icon == settingModel.icon;
    }

    public int hashCode() {
        String str = this.title;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.desc;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.status;
        return ((((((((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.resId) * 31) + this.type) * 31) + this.viewType) * 31) + this.icon;
    }

    public String toString() {
        return "SettingModel(title=" + this.title + ", desc=" + this.desc + ", status=" + this.status + ", resId=" + this.resId + ", type=" + this.type + ", viewType=" + this.viewType + ", icon=" + this.icon + ")";
    }

    public SettingModel(String str, String str2, String str3, int i, int i2, int i3, int i4) {
        this.title = str;
        this.desc = str2;
        this.status = str3;
        this.resId = i;
        this.type = i2;
        this.viewType = i3;
        this.icon = i4;
    }

    public /* synthetic */ SettingModel(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? null : str, (i5 & 2) != 0 ? null : str2, (i5 & 4) != 0 ? null : str3, (i5 & 8) != 0 ? 0 : i, i2, (i5 & 32) != 0 ? 1 : i3, (i5 & 64) != 0 ? 0 : i4);
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final void setDesc(String str) {
        this.desc = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final int getResId() {
        return this.resId;
    }

    public final void setResId(int i) {
        this.resId = i;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }

    public final int getIcon() {
        return this.icon;
    }
}
