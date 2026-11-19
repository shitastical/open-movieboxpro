package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.ViewStubProxy;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentDownloadBindingImpl extends FragmentDownloadBinding {
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
        sparseIntArray.put(R.id.llSmartDownload, 1);
        sparseIntArray.put(R.id.tvSmartDownload, 2);
        sparseIntArray.put(R.id.llDownloading, 3);
        sparseIntArray.put(R.id.tvDownloading, 4);
        sparseIntArray.put(R.id.llEdit, 5);
        sparseIntArray.put(R.id.tvEdit, 6);
        sparseIntArray.put(R.id.llDelete, 7);
        sparseIntArray.put(R.id.tvDelete, 8);
        sparseIntArray.put(R.id.llClearAll, 9);
        sparseIntArray.put(R.id.flList, 10);
        sparseIntArray.put(R.id.recyclerView, 11);
        sparseIntArray.put(R.id.emptyViewStub, 12);
        sparseIntArray.put(R.id.rvStorage, 13);
        sparseIntArray.put(R.id.llBottom, 14);
        sparseIntArray.put(R.id.llHint, 15);
        sparseIntArray.put(R.id.tvDeviceSize, 16);
    }

    public FragmentDownloadBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private FragmentDownloadBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, new ViewStubProxy((ViewStub) bindings[12]), (FrameLayout) bindings[10], (LinearLayout) bindings[14], (LinearLayout) bindings[9], (LinearLayout) bindings[7], (LinearLayout) bindings[3], (LinearLayout) bindings[5], (LinearLayout) bindings[15], (LinearLayout) bindings[1], (TvRecyclerView) bindings[11], (RecyclerView) bindings[13], (TextView) bindings[8], (TextView) bindings[16], (TextView) bindings[4], (TextView) bindings[6], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.emptyViewStub.setContainingBinding(this);
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
        if (this.emptyViewStub.getBinding() != null) {
            executeBindingsOn(this.emptyViewStub.getBinding());
        }
    }
}
