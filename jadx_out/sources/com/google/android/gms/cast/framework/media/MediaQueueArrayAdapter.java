package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.google.android.gms.cast.MediaQueueItem;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueueArrayAdapter extends ArrayAdapter<MediaQueueItem> {
    private final MediaQueue zznj;
    private final MediaQueue.Callback zznk;

    public MediaQueueArrayAdapter(MediaQueue mediaQueue, Context context, int i) {
        super(context, i);
        MediaQueue mediaQueueZzbm = MediaQueue.zzbm();
        this.zznj = mediaQueueZzbm;
        zza zzaVar = new zza();
        this.zznk = zzaVar;
        mediaQueueZzbm.registerCallback(zzaVar);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    private class zza extends MediaQueue.Callback {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void mediaQueueWillChange() {
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void mediaQueueChanged() {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsReloaded() {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsInsertedInRange(int i, int i2) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsUpdatedAtIndexes(int[] iArr) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        @Override // com.google.android.gms.cast.framework.media.MediaQueue.Callback
        public final void itemsRemovedAtIndexes(int[] iArr) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }
    }

    public void dispose() {
        this.zznj.unregisterCallback(this.zznk);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return this.zznj.getItemAtIndex(i, false) != null;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.zznj.getItemCount();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public MediaQueueItem getItem(int i) {
        return this.zznj.getItemAtIndex(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return this.zznj.itemIdAtIndex(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean isEmpty() {
        return this.zznj.getItemCount() == 0;
    }

    public MediaQueue getMediaQueue() {
        return this.zznj;
    }
}
