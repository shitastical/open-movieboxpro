package com.google.android.exoplayer.upstream.cache;

/* JADX INFO: loaded from: classes4.dex */
public interface CacheEvictor extends Cache.Listener {
    void onCacheInitialized();

    void onStartFile(Cache cache, String str, long j, long j2);
}
