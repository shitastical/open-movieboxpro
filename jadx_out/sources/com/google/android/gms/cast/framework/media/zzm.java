package com.google.android.gms.cast.framework.media;

import android.util.LruCache;
import com.google.android.gms.cast.MediaQueueItem;

/* JADX INFO: loaded from: classes4.dex */
final class zzm extends LruCache<Integer, MediaQueueItem> {
    private final /* synthetic */ MediaQueue zzni;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzm(MediaQueue mediaQueue, int i) {
        super(i);
        this.zzni = mediaQueue;
    }

    @Override // android.util.LruCache
    protected final /* synthetic */ void entryRemoved(boolean z, Integer num, MediaQueueItem mediaQueueItem, MediaQueueItem mediaQueueItem2) {
        Integer num2 = num;
        if (z) {
            this.zzni.zzmx.add(num2);
        }
    }
}
