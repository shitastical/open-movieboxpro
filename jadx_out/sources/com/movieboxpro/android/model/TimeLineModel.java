package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimeLineModel.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0012\"\u0004\b\u0015\u0010\u0014¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/model/TimeLineModel;", "", "dayNumber", "", "weekCode", "", "day", "isToday", "", "isSelect", "isFocus", "<init>", "(ILjava/lang/String;Ljava/lang/String;ZZZ)V", "getDayNumber", "()I", "getWeekCode", "()Ljava/lang/String;", "getDay", "()Z", "setSelect", "(Z)V", "setFocus", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TimeLineModel {
    private final String day;
    private final int dayNumber;
    private boolean isFocus;
    private boolean isSelect;
    private final boolean isToday;
    private final String weekCode;

    public static /* synthetic */ TimeLineModel copy$default(TimeLineModel timeLineModel, int i, String str, String str2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = timeLineModel.dayNumber;
        }
        if ((i2 & 2) != 0) {
            str = timeLineModel.weekCode;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            str2 = timeLineModel.day;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            z = timeLineModel.isToday;
        }
        boolean z4 = z;
        if ((i2 & 16) != 0) {
            z2 = timeLineModel.isSelect;
        }
        boolean z5 = z2;
        if ((i2 & 32) != 0) {
            z3 = timeLineModel.isFocus;
        }
        return timeLineModel.copy(i, str3, str4, z4, z5, z3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getDayNumber() {
        return this.dayNumber;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getWeekCode() {
        return this.weekCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDay() {
        return this.day;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsToday() {
        return this.isToday;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsFocus() {
        return this.isFocus;
    }

    public final TimeLineModel copy(int dayNumber, String weekCode, String day, boolean isToday, boolean isSelect, boolean isFocus) {
        Intrinsics.checkNotNullParameter(weekCode, "weekCode");
        Intrinsics.checkNotNullParameter(day, "day");
        return new TimeLineModel(dayNumber, weekCode, day, isToday, isSelect, isFocus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeLineModel)) {
            return false;
        }
        TimeLineModel timeLineModel = (TimeLineModel) other;
        return this.dayNumber == timeLineModel.dayNumber && Intrinsics.areEqual(this.weekCode, timeLineModel.weekCode) && Intrinsics.areEqual(this.day, timeLineModel.day) && this.isToday == timeLineModel.isToday && this.isSelect == timeLineModel.isSelect && this.isFocus == timeLineModel.isFocus;
    }

    public int hashCode() {
        return (((((((((this.dayNumber * 31) + this.weekCode.hashCode()) * 31) + this.day.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isToday)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isSelect)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isFocus);
    }

    public String toString() {
        return "TimeLineModel(dayNumber=" + this.dayNumber + ", weekCode=" + this.weekCode + ", day=" + this.day + ", isToday=" + this.isToday + ", isSelect=" + this.isSelect + ", isFocus=" + this.isFocus + ")";
    }

    public TimeLineModel(int i, String weekCode, String day, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(weekCode, "weekCode");
        Intrinsics.checkNotNullParameter(day, "day");
        this.dayNumber = i;
        this.weekCode = weekCode;
        this.day = day;
        this.isToday = z;
        this.isSelect = z2;
        this.isFocus = z3;
    }

    public /* synthetic */ TimeLineModel(int i, String str, String str2, boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? false : z3);
    }

    public final int getDayNumber() {
        return this.dayNumber;
    }

    public final String getWeekCode() {
        return this.weekCode;
    }

    public final String getDay() {
        return this.day;
    }

    public final boolean isToday() {
        return this.isToday;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final boolean isFocus() {
        return this.isFocus;
    }

    public final void setFocus(boolean z) {
        this.isFocus = z;
    }
}
