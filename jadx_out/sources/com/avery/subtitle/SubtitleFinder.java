package com.avery.subtitle;

import androidx.core.util.Pair;
import com.avery.subtitle.model.Subtitle;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SubtitleFinder {
    private SubtitleFinder() {
        throw new AssertionError("No instance for you");
    }

    public static Pair<Integer, Subtitle> find(long j, List<Subtitle> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size() - 1;
            int i = 0;
            while (i <= size) {
                int i2 = (i + size) / 2;
                Subtitle subtitle = list.get(i2);
                if (subtitle == null || subtitle.start == null || subtitle.end == null) {
                    break;
                }
                if (j < subtitle.start.mseconds) {
                    if (j > subtitle.end.mseconds) {
                        return new Pair<>(Integer.valueOf(i2), subtitle);
                    }
                    size = i2 - 1;
                } else if (j > subtitle.end.mseconds) {
                    if (j < subtitle.start.mseconds) {
                        return new Pair<>(Integer.valueOf(i2), subtitle);
                    }
                    i = i2 + 1;
                } else if (j >= subtitle.start.mseconds && j <= subtitle.end.mseconds) {
                    return new Pair<>(Integer.valueOf(i2), subtitle);
                }
            }
        }
        return null;
    }
}
