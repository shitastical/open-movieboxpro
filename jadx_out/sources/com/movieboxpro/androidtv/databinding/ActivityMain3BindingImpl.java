package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tab.TvTabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityMain3BindingImpl extends ActivityMain3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.llTop, 1);
        sparseIntArray.put(R.id.flDownload, 2);
        sparseIntArray.put(R.id.ivDownload, 3);
        sparseIntArray.put(R.id.tvDownloadNum, 4);
        sparseIntArray.put(R.id.ivTvGuide, 5);
        sparseIntArray.put(R.id.ivSearch, 6);
        sparseIntArray.put(R.id.ivFavorite, 7);
        sparseIntArray.put(R.id.tabLayout, 8);
        sparseIntArray.put(R.id.ivChildMode, 9);
        sparseIntArray.put(R.id.content, 10);
    }

    public ActivityMain3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ActivityMain3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[0], (FrameLayout) bindings[10], (FrameLayout) bindings[2], (ImageView) bindings[9], (ImageView) bindings[3], (ImageView) bindings[7], (ImageView) bindings[6], (ImageView) bindings[5], (LinearLayout) bindings[1], (TvTabLayout) bindings[8], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.container.setTag(null);
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
