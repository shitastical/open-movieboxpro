package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdk;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzez;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueue {
    private static MediaQueue zzmr;
    private final Handler handler;
    long zzen;
    private final zzdw zzms;
    private final zzba zzmt;
    List<Integer> zzmu;
    final SparseIntArray zzmv;
    LruCache<Integer, MediaQueueItem> zzmw;
    final List<Integer> zzmx;
    final Deque<Integer> zzmy;
    private final int zzmz;
    private TimerTask zzna;
    PendingResult<RemoteMediaClient.MediaChannelResult> zznb;
    PendingResult<RemoteMediaClient.MediaChannelResult> zznc;
    private ResultCallback<RemoteMediaClient.MediaChannelResult> zznd;
    private ResultCallback<RemoteMediaClient.MediaChannelResult> zzne;
    private zzc zznf;
    private Set<Callback> zzng;

    public static abstract class Callback {
        public void itemsInsertedInRange(int i, int i2) {
        }

        public void itemsReloaded() {
        }

        public void itemsRemovedAtIndexes(int[] iArr) {
        }

        public void itemsUpdatedAtIndexes(int[] iArr) {
        }

        public void mediaQueueChanged() {
        }

        public void mediaQueueWillChange() {
        }
    }

    private class zza implements ResultCallback<RemoteMediaClient.MediaChannelResult> {
        private zza() {
        }

        @Override // com.google.android.gms.common.api.ResultCallback
        public final /* synthetic */ void onResult(Result result) {
            Status status = ((RemoteMediaClient.MediaChannelResult) result).getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode != 0) {
                MediaQueue.this.zzms.w(String.format("Error fetching queue item ids, statusCode=%s, statusMessage=%s", Integer.valueOf(statusCode), status.getStatusMessage()), new Object[0]);
            }
            MediaQueue.this.zznc = null;
            if (MediaQueue.this.zzmy.isEmpty()) {
                return;
            }
            MediaQueue.this.zzbn();
        }

        /* synthetic */ zza(MediaQueue mediaQueue, zzl zzlVar) {
            this();
        }
    }

    private class zzb implements ResultCallback<RemoteMediaClient.MediaChannelResult> {
        private zzb() {
        }

        @Override // com.google.android.gms.common.api.ResultCallback
        public final /* synthetic */ void onResult(Result result) {
            Status status = ((RemoteMediaClient.MediaChannelResult) result).getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode != 0) {
                MediaQueue.this.zzms.w(String.format("Error fetching queue items, statusCode=%s, statusMessage=%s", Integer.valueOf(statusCode), status.getStatusMessage()), new Object[0]);
            }
            MediaQueue.this.zznb = null;
            if (MediaQueue.this.zzmy.isEmpty()) {
                return;
            }
            MediaQueue.this.zzbn();
        }

        /* synthetic */ zzb(MediaQueue mediaQueue, zzl zzlVar) {
            this();
        }
    }

    class zzc extends RemoteMediaClient.Callback {
        zzc() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void zza(int[] iArr) {
            List<Integer> listZzg = zzdk.zzg(iArr);
            if (MediaQueue.this.zzmu.equals(listZzg)) {
                return;
            }
            MediaQueue.this.zzbu();
            MediaQueue.this.zzmw.evictAll();
            MediaQueue.this.zzmx.clear();
            MediaQueue.this.zzmu = listZzg;
            MediaQueue.this.zzbt();
            MediaQueue.this.zzbw();
            MediaQueue.this.zzbv();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void zza(int[] iArr, int i) {
            int size;
            int length = iArr.length;
            if (i == 0) {
                size = MediaQueue.this.zzmu.size();
            } else {
                size = MediaQueue.this.zzmv.get(i, -1);
                if (size == -1) {
                    MediaQueue.this.reload();
                    return;
                }
            }
            MediaQueue.this.zzbu();
            MediaQueue.this.zzmu.addAll(size, zzdk.zzg(iArr));
            MediaQueue.this.zzbt();
            MediaQueue.this.zzb(size, length);
            MediaQueue.this.zzbv();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void zzb(int[] iArr) {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                MediaQueue.this.zzmw.remove(Integer.valueOf(i));
                int i2 = MediaQueue.this.zzmv.get(i, -1);
                if (i2 == -1) {
                    MediaQueue.this.reload();
                    return;
                }
                arrayList.add(Integer.valueOf(i2));
            }
            Collections.sort(arrayList);
            MediaQueue.this.zzbu();
            MediaQueue.this.zzd(zzdk.zza(arrayList));
            MediaQueue.this.zzbv();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void zzc(int[] iArr) {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                MediaQueue.this.zzmw.remove(Integer.valueOf(i));
                int i2 = MediaQueue.this.zzmv.get(i, -1);
                if (i2 == -1) {
                    MediaQueue.this.reload();
                    return;
                } else {
                    MediaQueue.this.zzmv.delete(i);
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            Collections.sort(arrayList);
            MediaQueue.this.zzbu();
            MediaQueue.this.zzmu.removeAll(zzdk.zzg(iArr));
            MediaQueue.this.zzbt();
            MediaQueue.this.zze(zzdk.zza(arrayList));
            MediaQueue.this.zzbv();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
            HashSet hashSet = new HashSet();
            MediaQueue.this.zzmx.clear();
            for (MediaQueueItem mediaQueueItem : mediaQueueItemArr) {
                int itemId = mediaQueueItem.getItemId();
                MediaQueue.this.zzmw.put(Integer.valueOf(itemId), mediaQueueItem);
                int i = MediaQueue.this.zzmv.get(itemId, -1);
                if (i == -1) {
                    MediaQueue.this.reload();
                    return;
                }
                hashSet.add(Integer.valueOf(i));
            }
            Iterator<Integer> it = MediaQueue.this.zzmx.iterator();
            while (it.hasNext()) {
                int i2 = MediaQueue.this.zzmv.get(it.next().intValue(), -1);
                if (i2 != -1) {
                    hashSet.add(Integer.valueOf(i2));
                }
            }
            MediaQueue.this.zzmx.clear();
            ArrayList arrayList = new ArrayList(hashSet);
            Collections.sort(arrayList);
            MediaQueue.this.zzbu();
            MediaQueue.this.zzd(zzdk.zza(arrayList));
            MediaQueue.this.zzbv();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
        public final void onStatusUpdated() {
            long jZzp = MediaQueue.this.zzp();
            if (jZzp != MediaQueue.this.zzen) {
                MediaQueue.this.zzen = jZzp;
                MediaQueue.this.clear();
                if (MediaQueue.this.zzen != 0) {
                    MediaQueue.this.reload();
                }
            }
        }
    }

    public static MediaQueue zzbm() {
        if (zzmr == null) {
            zzmr = new MediaQueue();
        }
        return zzmr;
    }

    MediaQueue() {
        this(20, 20, true);
    }

    private MediaQueue(int i, int i2, boolean z) {
        this.zzng = new HashSet();
        this.zzms = new zzdw("MediaQueue");
        this.zzmz = Math.max(20, 1);
        zzba zzbaVarZzcu = zzba.zzcu();
        this.zzmt = zzbaVarZzcu;
        this.zzmu = new ArrayList();
        this.zzmv = new SparseIntArray();
        this.zzmx = new ArrayList();
        this.zzmy = new ArrayDeque(20);
        this.handler = new zzez(Looper.getMainLooper());
        zzl(20);
        this.zzna = new zzl(this);
        zzl zzlVar = null;
        this.zznd = new zzb(this, zzlVar);
        this.zzne = new zza(this, zzlVar);
        zzc zzcVar = new zzc();
        this.zznf = zzcVar;
        zzbaVarZzcu.registerCallback(zzcVar);
        zzbaVarZzcu.zzqe.zza(new com.google.android.gms.cast.framework.zzad(this) { // from class: com.google.android.gms.cast.framework.media.zzk
            private final MediaQueue zznh;

            {
                this.zznh = this;
            }

            @Override // com.google.android.gms.cast.framework.zzad
            public final void zzap() {
                this.zznh.zzbr();
            }
        });
        zzbr();
    }

    private final void zzl(int i) {
        this.zzmw = new zzm(this, i);
    }

    public void registerCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzng.add(callback);
    }

    public void unregisterCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzng.remove(callback);
    }

    public int getItemCount() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzmu.size();
    }

    public int[] getItemIds() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return zzdk.zza(this.zzmu);
    }

    public void setCacheCapacity(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        LruCache<Integer, MediaQueueItem> lruCache = this.zzmw;
        ArrayList arrayList = new ArrayList();
        zzl(i);
        int size = lruCache.size();
        for (Map.Entry<Integer, MediaQueueItem> entry : lruCache.snapshot().entrySet()) {
            if (size > i) {
                int i2 = this.zzmv.get(entry.getKey().intValue(), -1);
                if (i2 != -1) {
                    arrayList.add(Integer.valueOf(i2));
                }
            } else {
                this.zzmw.put(entry.getKey(), entry.getValue());
            }
            size--;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList);
        zzbu();
        zzd(zzdk.zza(arrayList));
        zzbv();
    }

    public MediaQueueItem getItemAtIndex(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return getItemAtIndex(i, true);
    }

    public MediaQueueItem getItemAtIndex(int i, boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (i < 0 || i >= this.zzmu.size()) {
            return null;
        }
        Integer num = this.zzmu.get(i);
        num.intValue();
        MediaQueueItem mediaQueueItem = this.zzmw.get(num);
        if (mediaQueueItem == null && z && !this.zzmy.contains(num)) {
            while (this.zzmy.size() >= this.zzmz) {
                this.zzmy.removeFirst();
            }
            this.zzmy.add(num);
            zzbn();
        }
        return mediaQueueItem;
    }

    public int itemIdAtIndex(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (i < 0 || i >= this.zzmu.size()) {
            return 0;
        }
        return this.zzmu.get(i).intValue();
    }

    public int indexOfItemWithId(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzmv.get(i, -1);
    }

    public PendingResult<RemoteMediaClient.MediaChannelResult> fetchMoreItemsRelativeToIndex(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbx() || this.zzen == 0) {
            return RemoteMediaClient.zza(2100, "No active media session");
        }
        int iItemIdAtIndex = itemIdAtIndex(i);
        if (iItemIdAtIndex == 0) {
            return RemoteMediaClient.zza(2001, "index out of bound");
        }
        return this.zzmt.zza(iItemIdAtIndex, i2, i3);
    }

    public final void clear() {
        zzbu();
        this.zzmu.clear();
        this.zzmv.clear();
        this.zzmw.evictAll();
        this.zzmx.clear();
        zzbo();
        this.zzmy.clear();
        zzbp();
        zzbq();
        zzbw();
        zzbv();
    }

    public final void reload() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzbx() && this.zzen != 0 && this.zznc == null) {
            zzbp();
            zzbq();
            PendingResult<RemoteMediaClient.MediaChannelResult> pendingResultZzcp = this.zzmt.zzcp();
            this.zznc = pendingResultZzcp;
            pendingResultZzcp.setResultCallback(this.zzne);
        }
    }

    public final void zzbn() {
        zzbo();
        this.handler.postDelayed(this.zzna, 500L);
    }

    private final void zzbo() {
        this.handler.removeCallbacks(this.zzna);
    }

    private final void zzbp() {
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult = this.zznc;
        if (pendingResult != null) {
            pendingResult.cancel();
            this.zznc = null;
        }
    }

    private final void zzbq() {
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult = this.zznb;
        if (pendingResult != null) {
            pendingResult.cancel();
            this.zznb = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zzp() {
        MediaStatus mediaStatus;
        if (!zzbx() || (mediaStatus = this.zzmt.getMediaStatus()) == null || mediaStatus.zzq()) {
            return 0L;
        }
        return mediaStatus.zzp();
    }

    final void zzbr() {
        int iZzax = this.zzmt.zzqe.zzax();
        if (iZzax == 1) {
            long jZzp = zzp();
            this.zzen = jZzp;
            if (jZzp != 0) {
                reload();
                return;
            }
            return;
        }
        if (iZzax == 2) {
            zzbp();
            zzbq();
        } else {
            if (iZzax != 3) {
                return;
            }
            clear();
        }
    }

    final void zzbs() {
        if (!this.zzmy.isEmpty() && this.zznb == null && zzbx() && this.zzen != 0) {
            PendingResult<RemoteMediaClient.MediaChannelResult> pendingResultZzf = this.zzmt.zzf(zzdk.zza(this.zzmy));
            this.zznb = pendingResultZzf;
            pendingResultZzf.setResultCallback(this.zznd);
            this.zzmy.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbt() {
        this.zzmv.clear();
        for (int i = 0; i < this.zzmu.size(); i++) {
            this.zzmv.put(this.zzmu.get(i).intValue(), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbu() {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().mediaQueueWillChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbv() {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().mediaQueueChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbw() {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().itemsReloaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(int i, int i2) {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().itemsInsertedInRange(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(int[] iArr) {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().itemsUpdatedAtIndexes(iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(int[] iArr) {
        Iterator<Callback> it = this.zzng.iterator();
        while (it.hasNext()) {
            it.next().itemsRemovedAtIndexes(iArr);
        }
    }

    private final boolean zzbx() {
        return this.zzmt.zzqe.zzax() == 1;
    }
}
