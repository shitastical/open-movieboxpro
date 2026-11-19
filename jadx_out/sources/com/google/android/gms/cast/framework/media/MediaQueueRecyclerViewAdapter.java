package com.google.android.gms.cast.framework.media;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.gms.cast.MediaQueueItem;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaQueueRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final MediaQueue zznj;
    private final MediaQueue.Callback zznk;

    MediaQueueRecyclerViewAdapter() {
        MediaQueue mediaQueueZzbm = MediaQueue.zzbm();
        this.zznj = mediaQueueZzbm;
        zza zzaVar = new zza();
        this.zznk = zzaVar;
        mediaQueueZzbm.registerCallback(zzaVar);
    }

    private class zza extends MediaQueue.Callback {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void mediaQueueChanged() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void mediaQueueWillChange() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsReloaded() {
            MediaQueueRecyclerViewAdapter.this.notifyDataSetChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsInsertedInRange(int i, int i2) {
            MediaQueueRecyclerViewAdapter.this.notifyItemRangeInserted(i, i2);
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsUpdatedAtIndexes(int[] iArr) {
            for (int i : iArr) {
                MediaQueueRecyclerViewAdapter.this.notifyItemChanged(i);
            }
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsRemovedAtIndexes(int[] iArr) {
            if (iArr.length > 1) {
                MediaQueueRecyclerViewAdapter.this.notifyDataSetChanged();
                return;
            }
            for (int i : iArr) {
                MediaQueueRecyclerViewAdapter.this.notifyItemRemoved(i);
            }
        }
    }

    public MediaQueueRecyclerViewAdapter(MediaQueue mediaQueue) {
        MediaQueue mediaQueueZzbm = MediaQueue.zzbm();
        this.zznj = mediaQueueZzbm;
        zza zzaVar = new zza();
        this.zznk = zzaVar;
        mediaQueueZzbm.registerCallback(zzaVar);
    }

    public void dispose() {
        this.zznj.unregisterCallback(this.zznk);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.zznj.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.zznj.itemIdAtIndex(i);
    }

    public MediaQueueItem getItem(int i) {
        return this.zznj.getItemAtIndex(i);
    }

    public MediaQueue getMediaQueue() {
        return this.zznj;
    }
}
