package com.movieboxpro.android.view.activity.videoplayer;

import com.dl7.player.utils.SRT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: TranslateSubtitleStreamReducer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamReducer;", "", "<init>", "()V", "reduceLine", "Lkotlin/Pair;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate;", "", "line", "", "appendedSoFar", "reduceLines", "", "lines", "Lkotlin/sequences/Sequence;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitleStreamReducer {
    public static final TranslateSubtitleStreamReducer INSTANCE = new TranslateSubtitleStreamReducer();

    private TranslateSubtitleStreamReducer() {
    }

    public final Pair<TranslateSubtitleUiUpdate, Integer> reduceLine(String line, int appendedSoFar) {
        Intrinsics.checkNotNullParameter(line, "line");
        TranslateSubtitleStreamEvent line2 = TranslateSubtitleStreamParser.parseLine(line);
        if (Intrinsics.areEqual(line2, TranslateSubtitleStreamEvent.Ignore.INSTANCE) || (line2 instanceof TranslateSubtitleStreamEvent.Meta)) {
            return new Pair<>(null, Integer.valueOf(appendedSoFar));
        }
        if (line2 instanceof TranslateSubtitleStreamEvent.Cues) {
            TranslateSubtitleStreamEvent.Cues cues = (TranslateSubtitleStreamEvent.Cues) line2;
            List<SRT> listMapAll = TranslateSubtitleCueMapper.INSTANCE.mapAll(cues.getCues());
            int size = appendedSoFar + listMapAll.size();
            Integer doneCount = cues.getDoneCount();
            return new Pair<>(new TranslateSubtitleUiUpdate.Append(listMapAll, doneCount != null ? doneCount.intValue() : size), Integer.valueOf(size));
        }
        if (line2 instanceof TranslateSubtitleStreamEvent.Done) {
            return new Pair<>(TranslateSubtitleUiUpdate.Finished.INSTANCE, Integer.valueOf(appendedSoFar));
        }
        if (!(line2 instanceof TranslateSubtitleStreamEvent.Error)) {
            throw new NoWhenBranchMatchedException();
        }
        TranslateSubtitleStreamEvent.Error error = (TranslateSubtitleStreamEvent.Error) line2;
        String msg = error.getMsg();
        if (msg == null) {
            msg = "Translate failed";
        }
        return new Pair<>(new TranslateSubtitleUiUpdate.Failed(msg, error.getCode()), Integer.valueOf(appendedSoFar));
    }

    public final List<TranslateSubtitleUiUpdate> reduceLines(Sequence<String> lines) {
        Intrinsics.checkNotNullParameter(lines, "lines");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = lines.iterator();
        int iIntValue = 0;
        while (it.hasNext()) {
            Pair<TranslateSubtitleUiUpdate, Integer> pairReduceLine = reduceLine(it.next(), iIntValue);
            TranslateSubtitleUiUpdate translateSubtitleUiUpdateComponent1 = pairReduceLine.component1();
            iIntValue = pairReduceLine.component2().intValue();
            if (translateSubtitleUiUpdateComponent1 != null) {
                arrayList.add(translateSubtitleUiUpdateComponent1);
                if ((translateSubtitleUiUpdateComponent1 instanceof TranslateSubtitleUiUpdate.Finished) || (translateSubtitleUiUpdateComponent1 instanceof TranslateSubtitleUiUpdate.Failed)) {
                    return arrayList;
                }
            }
        }
        arrayList.add(new TranslateSubtitleUiUpdate.Failed("Translate failed", -1));
        return arrayList;
    }
}
