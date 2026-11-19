package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityDownloadingBindingImpl extends ActivityDownloadingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.llDone, 1);
        sparseIntArray.put(R.id.tvEdit, 2);
        sparseIntArray.put(R.id.llDelete, 3);
        sparseIntArray.put(R.id.tvDelete, 4);
        sparseIntArray.put(R.id.llStartPauseAll, 5);
        sparseIntArray.put(R.id.ivStartPause, 6);
        sparseIntArray.put(R.id.tvStartPauseAll, 7);
        sparseIntArray.put(R.id.llConcurrent, 8);
        sparseIntArray.put(R.id.tvConcurrent, 9);
        sparseIntArray.put(R.id.llSmartDownload, 10);
        sparseIntArray.put(R.id.tvSmartDownload, 11);
        sparseIntArray.put(R.id.recyclerView, 12);
        sparseIntArray.put(R.id.llHint, 13);
    }

    public ActivityDownloadingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivityDownloadingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[6], (LinearLayout) bindings[8], (LinearLayout) bindings[3], (LinearLayout) bindings[1], (LinearLayout) bindings[13], (LinearLayout) bindings[10], (LinearLayout) bindings[5], (TvRecyclerView) bindings[12], (TextView) bindings[9], (TextView) bindings[4], (TextView) bindings[2], (TextView) bindings[11], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
