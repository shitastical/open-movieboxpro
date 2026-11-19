package com.movieboxpro.android.utils;

import com.jeffmony.downloader.model.Video;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: YoutubeTrailerLauncher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\n\u001a\u00020\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher;", "", "<init>", "()V", "youtubePackages", "", "", "videoIdInUrl", "Lkotlin/text/Regex;", "resolveVideoId", "videoId", "url", "launchTargets", "Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;", "extractVideoId", "text", "Target", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class YoutubeTrailerLauncher {
    public static final YoutubeTrailerLauncher INSTANCE = new YoutubeTrailerLauncher();
    private static final List<String> youtubePackages = CollectionsKt.listOf((Object[]) new String[]{"com.google.android.youtube.tv", "com.google.android.youtube.googletv", "com.google.android.youtube", "com.amazon.firetv.youtube"});
    private static final Regex videoIdInUrl = new Regex("(?:[?&]v=|/embed/|/shorts/|/live/|youtu\\.be/)([A-Za-z0-9_-]{6,})");

    /* JADX INFO: compiled from: YoutubeTrailerLauncher.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/utils/YoutubeTrailerLauncher$Target;", "", "uri", "", "packageName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "getPackageName", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Target {
        private final String packageName;
        private final String uri;

        public static /* synthetic */ Target copy$default(Target target, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = target.uri;
            }
            if ((i & 2) != 0) {
                str2 = target.packageName;
            }
            return target.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        public final Target copy(String uri, String packageName) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return new Target(uri, packageName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Target)) {
                return false;
            }
            Target target = (Target) other;
            return Intrinsics.areEqual(this.uri, target.uri) && Intrinsics.areEqual(this.packageName, target.packageName);
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.packageName;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "Target(uri=" + this.uri + ", packageName=" + this.packageName + ")";
        }

        public Target(String uri, String str) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uri = uri;
            this.packageName = str;
        }

        public /* synthetic */ Target(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2);
        }

        public final String getUri() {
            return this.uri;
        }

        public final String getPackageName() {
            return this.packageName;
        }
    }

    private YoutubeTrailerLauncher() {
    }

    public final String resolveVideoId(String videoId, String url) {
        String string = videoId != null ? StringsKt.trim((CharSequence) videoId).toString() : null;
        if (string == null) {
            string = "";
        }
        String str = string;
        if (str.length() > 0) {
            String strExtractVideoId = extractVideoId(string);
            if (strExtractVideoId != null) {
                return strExtractVideoId;
            }
            if (!StringsKt.contains$default((CharSequence) str, '/', false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, '?', false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) str, ':', false, 2, (Object) null)) {
                return string;
            }
        }
        String string2 = url != null ? StringsKt.trim((CharSequence) url).toString() : null;
        String str2 = string2 != null ? string2 : "";
        if (str2.length() > 0) {
            return extractVideoId(str2);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<Target> launchTargets(String videoId) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        String str = "vnd.youtube://" + videoId;
        String str2 = "https://www.youtube.com/watch?v=" + videoId;
        List<String> list = youtubePackages;
        int i = 2;
        ArrayList arrayList = new ArrayList((list.size() * 2) + 2);
        for (String str3 : list) {
            arrayList.add(new Target(str2, str3));
            arrayList.add(new Target(str, str3));
        }
        arrayList.add(new Target(str2, null, i, 0 == true ? 1 : 0));
        arrayList.add(new Target(str, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0));
        return arrayList;
    }

    private final String extractVideoId(String text) {
        List<String> groupValues;
        MatchResult matchResultFind$default = Regex.find$default(videoIdInUrl, text, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) {
            return null;
        }
        return groupValues.get(1);
    }
}
