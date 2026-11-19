package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.CustomProgressView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class WatchProgressTopLayoutBindingImpl extends WatchProgressTopLayoutBinding {
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
        sparseIntArray.put(R.id.ivPoster, 1);
        sparseIntArray.put(R.id.tvName, 2);
        sparseIntArray.put(R.id.progressView, 3);
        sparseIntArray.put(R.id.imageView, 4);
        sparseIntArray.put(R.id.tvProgress, 5);
        sparseIntArray.put(R.id.tvAddWaiting, 6);
        sparseIntArray.put(R.id.tvMarkWatched, 7);
        sparseIntArray.put(R.id.linearLayout, 8);
        sparseIntArray.put(R.id.tvSeason, 9);
        sparseIntArray.put(R.id.ivMarkSeason, 10);
    }

    public WatchProgressTopLayoutBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private WatchProgressTopLayoutBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[4], (ImageView) bindings[10], (ImageView) bindings[1], (LinearLayout) bindings[8], (CustomProgressView) bindings[3], (TextView) bindings[6], (TextView) bindings[7], (TextView) bindings[2], (TextView) bindings[5], (TextView) bindings[9]);
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
