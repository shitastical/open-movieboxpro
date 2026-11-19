package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentAudioTrackBindingImpl extends FragmentAudioTrackBinding {
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
        sparseIntArray.put(R.id.llAudioTracks, 1);
        sparseIntArray.put(R.id.recyclerView, 2);
        sparseIntArray.put(R.id.flLoading, 3);
        sparseIntArray.put(R.id.progressBar, 4);
        sparseIntArray.put(R.id.tvTryAgain, 5);
        sparseIntArray.put(R.id.llBestAudio, 6);
        sparseIntArray.put(R.id.tvDefaultAudio, 7);
        sparseIntArray.put(R.id.llDelay, 8);
        sparseIntArray.put(R.id.ivShowTrack, 9);
        sparseIntArray.put(R.id.ivDelaySub, 10);
        sparseIntArray.put(R.id.tvDelay, 11);
        sparseIntArray.put(R.id.ivDelayAdd, 12);
        sparseIntArray.put(R.id.ivAudioSyn, 13);
        sparseIntArray.put(R.id.tvAudioHint, 14);
    }

    public FragmentAudioTrackBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private FragmentAudioTrackBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (FrameLayout) bindings[3], (ImageView) bindings[13], (ImageView) bindings[12], (ImageView) bindings[10], (ImageView) bindings[9], (LinearLayout) bindings[1], (LinearLayout) bindings[6], (LinearLayout) bindings[0], (LinearLayout) bindings[8], (ProgressBar) bindings[4], (TvRecyclerView) bindings[2], (TextView) bindings[14], (TextView) bindings[7], (TextView) bindings[11], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.llContainer.setTag(null);
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
