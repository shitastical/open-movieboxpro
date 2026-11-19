package com.movieboxpro.android.model;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR%\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/model/SubtitleColor;", "", "color", "", "alphaColors", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AlphaColor;", "Lkotlin/collections/ArrayList;", "select", "", "<init>", "(Ljava/lang/String;Ljava/util/ArrayList;Z)V", "getColor", "()Ljava/lang/String;", "getAlphaColors", "()Ljava/util/ArrayList;", "getSelect", "()Z", "setSelect", "(Z)V", "component1", "component2", "component3", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SubtitleColor {
    private final ArrayList<AlphaColor> alphaColors;
    private final String color;
    private boolean select;

    public SubtitleColor() {
        this(null, null, false, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubtitleColor copy$default(SubtitleColor subtitleColor, String str, ArrayList arrayList, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleColor.color;
        }
        if ((i & 2) != 0) {
            arrayList = subtitleColor.alphaColors;
        }
        if ((i & 4) != 0) {
            z = subtitleColor.select;
        }
        return subtitleColor.copy(str, arrayList, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    public final ArrayList<AlphaColor> component2() {
        return this.alphaColors;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getSelect() {
        return this.select;
    }

    public final SubtitleColor copy(String color, ArrayList<AlphaColor> alphaColors, boolean select) {
        Intrinsics.checkNotNullParameter(color, "color");
        return new SubtitleColor(color, alphaColors, select);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleColor)) {
            return false;
        }
        SubtitleColor subtitleColor = (SubtitleColor) other;
        return Intrinsics.areEqual(this.color, subtitleColor.color) && Intrinsics.areEqual(this.alphaColors, subtitleColor.alphaColors) && this.select == subtitleColor.select;
    }

    public int hashCode() {
        int iHashCode = this.color.hashCode() * 31;
        ArrayList<AlphaColor> arrayList = this.alphaColors;
        return ((iHashCode + (arrayList == null ? 0 : arrayList.hashCode())) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.select);
    }

    public String toString() {
        return "SubtitleColor(color=" + this.color + ", alphaColors=" + this.alphaColors + ", select=" + this.select + ")";
    }

    public SubtitleColor(String color, ArrayList<AlphaColor> arrayList, boolean z) {
        Intrinsics.checkNotNullParameter(color, "color");
        this.color = color;
        this.alphaColors = arrayList;
        this.select = z;
    }

    public /* synthetic */ SubtitleColor(String str, ArrayList arrayList, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : arrayList, (i & 4) != 0 ? false : z);
    }

    public final String getColor() {
        return this.color;
    }

    public final ArrayList<AlphaColor> getAlphaColors() {
        return this.alphaColors;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final void setSelect(boolean z) {
        this.select = z;
    }
}
