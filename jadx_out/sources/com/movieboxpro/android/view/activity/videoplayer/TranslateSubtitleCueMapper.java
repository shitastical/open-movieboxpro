package com.movieboxpro.android.view.activity.videoplayer;

import com.dl7.player.utils.SRT;
import com.movieboxpro.android.utils.SrtParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TranslateSubtitleCueMapper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\tJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleCueMapper;", "", "<init>", "()V", "map", "Lcom/dl7/player/utils/SRT;", "cue", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleCue;", "mapAll", "", "cues", "parseTimeOrZero", "", "raw", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitleCueMapper {
    public static final TranslateSubtitleCueMapper INSTANCE = new TranslateSubtitleCueMapper();

    private TranslateSubtitleCueMapper() {
    }

    public final SRT map(TranslateSubtitleCue cue) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(cue, "cue");
        SRT srt = new SRT();
        String content = cue.getContent();
        if (content == null) {
            content = "";
        }
        srt.setSrtBody(content);
        String time = cue.getTime();
        if (time == null || (listEmptyList = StringsKt.split$default((CharSequence) time, new String[]{"-->"}, false, 0, 6, (Object) null)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        srt.setBeginTime(parseTimeOrZero((String) CollectionsKt.getOrNull(listEmptyList, 0)));
        srt.setEndTime(parseTimeOrZero((String) CollectionsKt.getOrNull(listEmptyList, 1)));
        return srt;
    }

    public final List<SRT> mapAll(List<TranslateSubtitleCue> cues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        List<TranslateSubtitleCue> list = cues;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(INSTANCE.map((TranslateSubtitleCue) it.next()));
        }
        return arrayList;
    }

    private final int parseTimeOrZero(String raw) {
        String str = raw;
        if (str == null || StringsKt.isBlank(str)) {
            return 0;
        }
        try {
            return SrtParser.parseSubtitleTime(StringsKt.trim((CharSequence) raw).toString());
        } catch (Exception unused) {
            return 0;
        }
    }
}
