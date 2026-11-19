package com.google.android.exoplayer.upstream.cache;

import android.os.ConditionVariable;
import com.google.android.exoplayer.util.Assertions;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes4.dex */
public final class SimpleCache implements Cache {
    private final File cacheDir;
    private final CacheEvictor evictor;
    private long totalSpace = 0;
    private final HashMap<String, CacheSpan> lockedSpans = new HashMap<>();
    private final HashMap<String, TreeSet<CacheSpan>> cachedSpans = new HashMap<>();
    private final HashMap<String, ArrayList<Cache.Listener>> listeners = new HashMap<>();

    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.exoplayer.upstream.cache.SimpleCache$1] */
    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this.cacheDir = file;
        this.evictor = cacheEvictor;
        final ConditionVariable conditionVariable = new ConditionVariable();
        new Thread("SimpleCache.initialize()") { // from class: com.google.android.exoplayer.upstream.cache.SimpleCache.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (SimpleCache.this) {
                    conditionVariable.open();
                    SimpleCache.this.initialize();
                }
            }
        }.start();
        conditionVariable.block();
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.listeners.put(str, arrayList);
        }
        arrayList.add(listener);
        return getCachedSpans(str);
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized void removeListener(String str, Cache.Listener listener) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList != null) {
            arrayList.remove(listener);
            if (arrayList.isEmpty()) {
                this.listeners.remove(str);
            }
        }
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        TreeSet<CacheSpan> treeSet;
        treeSet = this.cachedSpans.get(str);
        return treeSet == null ? null : new TreeSet((SortedSet) treeSet);
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized Set<String> getKeys() {
        return new HashSet(this.cachedSpans.keySet());
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized long getCacheSpace() {
        return this.totalSpace;
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized CacheSpan startReadWrite(String str, long j) throws InterruptedException {
        CacheSpan cacheSpanStartReadWriteNonBlocking;
        CacheSpan cacheSpanCreateLookup = CacheSpan.createLookup(str, j);
        while (true) {
            cacheSpanStartReadWriteNonBlocking = startReadWriteNonBlocking(cacheSpanCreateLookup);
            if (cacheSpanStartReadWriteNonBlocking == null) {
                wait();
            }
        }
        return cacheSpanStartReadWriteNonBlocking;
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized CacheSpan startReadWriteNonBlocking(String str, long j) {
        return startReadWriteNonBlocking(CacheSpan.createLookup(str, j));
    }

    private synchronized CacheSpan startReadWriteNonBlocking(CacheSpan cacheSpan) {
        CacheSpan span = getSpan(cacheSpan);
        if (span.isCached) {
            TreeSet<CacheSpan> treeSet = this.cachedSpans.get(span.key);
            Assertions.checkState(treeSet.remove(span));
            CacheSpan cacheSpan2 = span.touch();
            treeSet.add(cacheSpan2);
            notifySpanTouched(span, cacheSpan2);
            return cacheSpan2;
        }
        if (this.lockedSpans.containsKey(cacheSpan.key)) {
            return null;
        }
        this.lockedSpans.put(cacheSpan.key, span);
        return span;
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized File startFile(String str, long j, long j2) {
        Assertions.checkState(this.lockedSpans.containsKey(str));
        if (!this.cacheDir.exists()) {
            removeStaleSpans();
            this.cacheDir.mkdirs();
        }
        this.evictor.onStartFile(this, str, j, j2);
        return CacheSpan.getCacheFileName(this.cacheDir, str, j, System.currentTimeMillis());
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized void commitFile(File file) {
        CacheSpan cacheSpanCreateCacheEntry = CacheSpan.createCacheEntry(file);
        Assertions.checkState(cacheSpanCreateCacheEntry != null);
        Assertions.checkState(this.lockedSpans.containsKey(cacheSpanCreateCacheEntry.key));
        if (file.exists()) {
            if (file.length() == 0) {
                file.delete();
            } else {
                addSpan(cacheSpanCreateCacheEntry);
                notifyAll();
            }
        }
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        Assertions.checkState(cacheSpan == this.lockedSpans.remove(cacheSpan.key));
        notifyAll();
    }

    private CacheSpan getSpan(CacheSpan cacheSpan) {
        String str = cacheSpan.key;
        long j = cacheSpan.position;
        TreeSet<CacheSpan> treeSet = this.cachedSpans.get(str);
        if (treeSet == null) {
            return CacheSpan.createOpenHole(str, cacheSpan.position);
        }
        CacheSpan cacheSpanFloor = treeSet.floor(cacheSpan);
        if (cacheSpanFloor != null && cacheSpanFloor.position <= j && j < cacheSpanFloor.position + cacheSpanFloor.length) {
            if (cacheSpanFloor.file.exists()) {
                return cacheSpanFloor;
            }
            removeStaleSpans();
            return getSpan(cacheSpan);
        }
        CacheSpan cacheSpanCeiling = treeSet.ceiling(cacheSpan);
        if (cacheSpanCeiling == null) {
            return CacheSpan.createOpenHole(str, cacheSpan.position);
        }
        return CacheSpan.createClosedHole(str, cacheSpan.position, cacheSpanCeiling.position - cacheSpan.position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initialize() {
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
        }
        File[] fileArrListFiles = this.cacheDir.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.length() == 0) {
                file.delete();
            } else {
                File fileUpgradeIfNeeded = CacheSpan.upgradeIfNeeded(file);
                CacheSpan cacheSpanCreateCacheEntry = CacheSpan.createCacheEntry(fileUpgradeIfNeeded);
                if (cacheSpanCreateCacheEntry == null) {
                    fileUpgradeIfNeeded.delete();
                } else {
                    addSpan(cacheSpanCreateCacheEntry);
                }
            }
        }
        this.evictor.onCacheInitialized();
    }

    private void addSpan(CacheSpan cacheSpan) {
        TreeSet<CacheSpan> treeSet = this.cachedSpans.get(cacheSpan.key);
        if (treeSet == null) {
            treeSet = new TreeSet<>();
            this.cachedSpans.put(cacheSpan.key, treeSet);
        }
        treeSet.add(cacheSpan);
        this.totalSpace += cacheSpan.length;
        notifySpanAdded(cacheSpan);
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized void removeSpan(CacheSpan cacheSpan) {
        TreeSet<CacheSpan> treeSet = this.cachedSpans.get(cacheSpan.key);
        this.totalSpace -= cacheSpan.length;
        Assertions.checkState(treeSet.remove(cacheSpan));
        cacheSpan.file.delete();
        if (treeSet.isEmpty()) {
            this.cachedSpans.remove(cacheSpan.key);
        }
        notifySpanRemoved(cacheSpan);
    }

    private void removeStaleSpans() {
        Iterator<Map.Entry<String, TreeSet<CacheSpan>>> it = this.cachedSpans.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<CacheSpan> it2 = it.next().getValue().iterator();
            boolean z = true;
            while (it2.hasNext()) {
                CacheSpan next = it2.next();
                if (next.file.exists()) {
                    z = false;
                } else {
                    it2.remove();
                    if (next.isCached) {
                        this.totalSpace -= next.length;
                    }
                    notifySpanRemoved(next);
                }
            }
            if (z) {
                it.remove();
            }
        }
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanAdded(CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanAdded(this, cacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, cacheSpan);
    }

    private void notifySpanTouched(CacheSpan cacheSpan, CacheSpan cacheSpan2) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanTouched(this, cacheSpan, cacheSpan2);
            }
        }
        this.evictor.onSpanTouched(this, cacheSpan, cacheSpan2);
    }

    @Override // com.google.android.exoplayer.upstream.cache.Cache
    public synchronized boolean isCached(String str, long j, long j2) {
        TreeSet<CacheSpan> treeSet = this.cachedSpans.get(str);
        if (treeSet == null) {
            return false;
        }
        CacheSpan cacheSpanFloor = treeSet.floor(CacheSpan.createLookup(str, j));
        if (cacheSpanFloor != null && cacheSpanFloor.position + cacheSpanFloor.length > j) {
            long j3 = j + j2;
            long jMax = cacheSpanFloor.position + cacheSpanFloor.length;
            if (jMax >= j3) {
                return true;
            }
            for (CacheSpan cacheSpan : treeSet.tailSet(cacheSpanFloor, false)) {
                if (cacheSpan.position > jMax) {
                    return false;
                }
                jMax = Math.max(jMax, cacheSpan.position + cacheSpan.length);
                if (jMax >= j3) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
