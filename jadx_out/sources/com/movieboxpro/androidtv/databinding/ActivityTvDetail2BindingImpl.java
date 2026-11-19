package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityTvDetail2BindingImpl extends ActivityTvDetail2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(28);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"layout_detail_meta_row"}, new int[]{2}, new int[]{R.layout.layout_detail_meta_row});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ivBackground, 3);
        sparseIntArray.put(R.id.scrollView, 4);
        sparseIntArray.put(R.id.flTop, 5);
        sparseIntArray.put(R.id.llTvName, 6);
        sparseIntArray.put(R.id.tvName, 7);
        sparseIntArray.put(R.id.tvLanguage, 8);
        sparseIntArray.put(R.id.rlPlay, 9);
        sparseIntArray.put(R.id.llResume, 10);
        sparseIntArray.put(R.id.tvPlay, 11);
        sparseIntArray.put(R.id.llLoading, 12);
        sparseIntArray.put(R.id.progressBar, 13);
        sparseIntArray.put(R.id.tvLoading, 14);
        sparseIntArray.put(R.id.tvSeason, 15);
        sparseIntArray.put(R.id.ivWatchPlan, 16);
        sparseIntArray.put(R.id.ivWatchPlanProgress, 17);
        sparseIntArray.put(R.id.ivAdd, 18);
        sparseIntArray.put(R.id.ivDownload, 19);
        sparseIntArray.put(R.id.llTvInfo, 20);
        sparseIntArray.put(R.id.tvDesc, 21);
        sparseIntArray.put(R.id.tvDirector, 22);
        sparseIntArray.put(R.id.tvStars, 23);
        sparseIntArray.put(R.id.tvGenre, 24);
        sparseIntArray.put(R.id.recyclerView, 25);
        sparseIntArray.put(R.id.tvActors, 26);
        sparseIntArray.put(R.id.rvActors, 27);
    }

    public ActivityTvDetail2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }

    private ActivityTvDetail2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ConstraintLayout) bindings[1], (FrameLayout) bindings[5], (ImageView) bindings[18], (ImageView) bindings[3], (ImageView) bindings[19], (ImageView) bindings[16], (ImageView) bindings[17], (LinearLayout) bindings[12], (LinearLayout) bindings[10], (LinearLayout) bindings[20], (ConstraintLayout) bindings[6], (LayoutDetailMetaRowBinding) bindings[2], (ProgressBar) bindings[13], (TvRecyclerView) bindings[25], (FrameLayout) bindings[9], (TvRecyclerView) bindings[27], (ScrollView) bindings[4], (TextView) bindings[26], (TextView) bindings[21], (TextView) bindings[22], (TextView) bindings[24], (TextView) bindings[8], (TextView) bindings[14], (TextView) bindings[7], (TextView) bindings[11], (TextView) bindings[15], (TextView) bindings[23]);
        this.mDirtyFlags = -1L;
        this.backgroundLayout.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        setContainedBinding(this.metaInfoRow);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.metaInfoRow.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.metaInfoRow.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.metaInfoRow.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeMetaInfoRow((LayoutDetailMetaRowBinding) object, fieldId);
    }

    private boolean onChangeMetaInfoRow(LayoutDetailMetaRowBinding MetaInfoRow, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        executeBindingsOn(this.metaInfoRow);
    }
}
