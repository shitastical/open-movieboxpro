package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.LoadingView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentPlayerEpisodeListBindingImpl extends FragmentPlayerEpisodeListBinding {
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
        sparseIntArray.put(R.id.tvCurrSeason, 1);
        sparseIntArray.put(R.id.llSeason, 2);
        sparseIntArray.put(R.id.tvSeasons, 3);
        sparseIntArray.put(R.id.recyclerView, 4);
        sparseIntArray.put(R.id.progressBar, 5);
        sparseIntArray.put(R.id.llError, 6);
        sparseIntArray.put(R.id.tvError, 7);
        sparseIntArray.put(R.id.tvRetry, 8);
    }

    public FragmentPlayerEpisodeListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private FragmentPlayerEpisodeListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[6], (LinearLayout) bindings[2], (LoadingView) bindings[5], (TvRecyclerView) bindings[4], (TextView) bindings[1], (TextView) bindings[7], (TextView) bindings[8], (TextView) bindings[3]);
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
