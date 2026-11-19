package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentFilterFavoriteBindingImpl extends FragmentFilterFavoriteBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

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
        sparseIntArray.put(R.id.rvType, 1);
        sparseIntArray.put(R.id.rvSort, 2);
        sparseIntArray.put(R.id.tvRating, 3);
        sparseIntArray.put(R.id.rvRating, 4);
        sparseIntArray.put(R.id.tvResolution, 5);
        sparseIntArray.put(R.id.rvResolution, 6);
        sparseIntArray.put(R.id.rvYear, 7);
        sparseIntArray.put(R.id.rvGener, 8);
        sparseIntArray.put(R.id.rvCountry, 9);
        sparseIntArray.put(R.id.tvReset, 10);
        sparseIntArray.put(R.id.tvDone, 11);
    }

    public FragmentFilterFavoriteBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private FragmentFilterFavoriteBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TvRecyclerView) bindings[9], (TvRecyclerView) bindings[8], (TvRecyclerView) bindings[4], (TvRecyclerView) bindings[6], (TvRecyclerView) bindings[2], (TvRecyclerView) bindings[1], (TvRecyclerView) bindings[7], (TextView) bindings[11], (TextView) bindings[3], (TextView) bindings[10], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
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
