package com.movieboxpro.android.utils;

import com.movieboxpro.android.player.MediaQualityInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GroupUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0007¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/utils/GroupUtils;", "", "<init>", "()V", "groupQualityList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "Lkotlin/collections/ArrayList;", "list", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GroupUtils {
    public static final GroupUtils INSTANCE = new GroupUtils();

    private GroupUtils() {
    }

    @JvmStatic
    public static final ArrayList<MediaQualityInfo> groupQualityList(List<MediaQualityInfo> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            String title = ((MediaQualityInfo) obj).getTitle();
            Object obj2 = linkedHashMap.get(title);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(title, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList<MediaQualityInfo> arrayList = new ArrayList<>();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll((Collection) ((Map.Entry) it.next()).getValue());
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(list);
        }
        return arrayList;
    }
}
