package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/model/AlphaColor;", "", "color", "", "select", "", "<init>", "(Ljava/lang/String;Z)V", "getColor", "()Ljava/lang/String;", "getSelect", "()Z", "setSelect", "(Z)V", "component1", "component2", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AlphaColor {
    private final String color;
    private boolean select;

    /* JADX WARN: Multi-variable type inference failed */
    public AlphaColor() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ AlphaColor copy$default(AlphaColor alphaColor, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = alphaColor.color;
        }
        if ((i & 2) != 0) {
            z = alphaColor.select;
        }
        return alphaColor.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getSelect() {
        return this.select;
    }

    public final AlphaColor copy(String color, boolean select) {
        Intrinsics.checkNotNullParameter(color, "color");
        return new AlphaColor(color, select);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AlphaColor)) {
            return false;
        }
        AlphaColor alphaColor = (AlphaColor) other;
        return Intrinsics.areEqual(this.color, alphaColor.color) && this.select == alphaColor.select;
    }

    public int hashCode() {
        return (this.color.hashCode() * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.select);
    }

    public String toString() {
        return "AlphaColor(color=" + this.color + ", select=" + this.select + ")";
    }

    public AlphaColor(String color, boolean z) {
        Intrinsics.checkNotNullParameter(color, "color");
        this.color = color;
        this.select = z;
    }

    public /* synthetic */ AlphaColor(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? false : z);
    }

    public final String getColor() {
        return this.color;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }
}
