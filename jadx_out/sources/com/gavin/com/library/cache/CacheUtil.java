package com.gavin.com.library.cache;

import android.util.LruCache;
import android.util.SparseArray;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes4.dex */
public class CacheUtil<T> implements CacheInterface<T> {
    private LruCache<Integer, T> mLruCache;
    private SparseArray<SoftReference<T>> mSoftCache;
    private boolean mUseCache = true;

    public CacheUtil() {
        initLruCache();
    }

    public void isCacheable(boolean z) {
        this.mUseCache = z;
    }

    private void initLruCache() {
        this.mLruCache = new LruCache<Integer, T>(2097152) { // from class: com.gavin.com.library.cache.CacheUtil.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public void entryRemoved(boolean z, Integer num, T t, T t2) {
                super.entryRemoved(z, num, t, t2);
            }
        };
    }

    @Override // com.gavin.com.library.cache.CacheInterface
    public void put(int i, T t) {
        if (this.mUseCache) {
            this.mLruCache.put(Integer.valueOf(i), t);
        }
    }

    @Override // com.gavin.com.library.cache.CacheInterface
    public T get(int i) {
        if (this.mUseCache) {
            return this.mLruCache.get(Integer.valueOf(i));
        }
        return null;
    }

    @Override // com.gavin.com.library.cache.CacheInterface
    public void remove(int i) {
        if (this.mUseCache) {
            this.mLruCache.remove(Integer.valueOf(i));
        }
    }

    @Override // com.gavin.com.library.cache.CacheInterface
    public void clean() {
        this.mLruCache.evictAll();
    }
}
