package com.movieboxpro.android.model;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/model/ImageHost;", "", "original", "", "thumb", TvContractCompat.ProgramColumns.COLUMN_TITLE, "defaultHost", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getOriginal", "()Ljava/lang/String;", "getThumb", "getTitle", "getDefaultHost", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ImageHost {
    private final boolean defaultHost;
    private final String original;
    private final String thumb;
    private final String title;

    public static /* synthetic */ ImageHost copy$default(ImageHost imageHost, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imageHost.original;
        }
        if ((i & 2) != 0) {
            str2 = imageHost.thumb;
        }
        if ((i & 4) != 0) {
            str3 = imageHost.title;
        }
        if ((i & 8) != 0) {
            z = imageHost.defaultHost;
        }
        return imageHost.copy(str, str2, str3, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOriginal() {
        return this.original;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getThumb() {
        return this.thumb;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getDefaultHost() {
        return this.defaultHost;
    }

    public final ImageHost copy(String original, String thumb, String title, boolean defaultHost) {
        Intrinsics.checkNotNullParameter(original, "original");
        Intrinsics.checkNotNullParameter(thumb, "thumb");
        Intrinsics.checkNotNullParameter(title, "title");
        return new ImageHost(original, thumb, title, defaultHost);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageHost)) {
            return false;
        }
        ImageHost imageHost = (ImageHost) other;
        return Intrinsics.areEqual(this.original, imageHost.original) && Intrinsics.areEqual(this.thumb, imageHost.thumb) && Intrinsics.areEqual(this.title, imageHost.title) && this.defaultHost == imageHost.defaultHost;
    }

    public int hashCode() {
        return (((((this.original.hashCode() * 31) + this.thumb.hashCode()) * 31) + this.title.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.defaultHost);
    }

    public String toString() {
        return "ImageHost(original=" + this.original + ", thumb=" + this.thumb + ", title=" + this.title + ", defaultHost=" + this.defaultHost + ")";
    }

    public ImageHost(String original, String thumb, String title, boolean z) {
        Intrinsics.checkNotNullParameter(original, "original");
        Intrinsics.checkNotNullParameter(thumb, "thumb");
        Intrinsics.checkNotNullParameter(title, "title");
        this.original = original;
        this.thumb = thumb;
        this.title = title;
        this.defaultHost = z;
    }

    public final String getOriginal() {
        return this.original;
    }

    public final String getThumb() {
        return this.thumb;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean getDefaultHost() {
        return this.defaultHost;
    }
}
