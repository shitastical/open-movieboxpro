package com.movieboxpro.android.model;

import com.dl7.player.model.SRTModel;
import com.jeffmony.downloader.model.Video;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\u000b\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/model/MySubtitleResponse;", "", "srt_list", "Ljava/util/ArrayList;", "Lcom/dl7/player/model/SRTModel$subTitles;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "getSrt_list", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MySubtitleResponse {
    private final ArrayList<SRTModel.subTitles> srt_list;

    /* JADX WARN: Multi-variable type inference failed */
    public MySubtitleResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MySubtitleResponse copy$default(MySubtitleResponse mySubtitleResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = mySubtitleResponse.srt_list;
        }
        return mySubtitleResponse.copy(arrayList);
    }

    public final ArrayList<SRTModel.subTitles> component1() {
        return this.srt_list;
    }

    public final MySubtitleResponse copy(ArrayList<SRTModel.subTitles> srt_list) {
        return new MySubtitleResponse(srt_list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MySubtitleResponse) && Intrinsics.areEqual(this.srt_list, ((MySubtitleResponse) other).srt_list);
    }

    public int hashCode() {
        ArrayList<SRTModel.subTitles> arrayList = this.srt_list;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.hashCode();
    }

    public String toString() {
        return "MySubtitleResponse(srt_list=" + this.srt_list + ")";
    }

    public MySubtitleResponse(ArrayList<SRTModel.subTitles> arrayList) {
        this.srt_list = arrayList;
    }

    public /* synthetic */ MySubtitleResponse(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList);
    }

    public final ArrayList<SRTModel.subTitles> getSrt_list() {
        return this.srt_list;
    }
}
