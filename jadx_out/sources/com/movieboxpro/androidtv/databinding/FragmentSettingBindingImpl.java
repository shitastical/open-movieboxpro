package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentSettingBindingImpl extends FragmentSettingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

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
        sparseIntArray.put(R.id.recyclerView, 1);
        sparseIntArray.put(R.id.llTestSpeed, 2);
        sparseIntArray.put(R.id.llSwitchLanguage, 3);
        sparseIntArray.put(R.id.tvLanguage, 4);
        sparseIntArray.put(R.id.llChildMode, 5);
        sparseIntArray.put(R.id.tvChildMode, 6);
        sparseIntArray.put(R.id.llRememberOrg, 7);
        sparseIntArray.put(R.id.tvRememberOrg, 8);
        sparseIntArray.put(R.id.llHideMovieList, 9);
        sparseIntArray.put(R.id.tvHideMovieList, 10);
        sparseIntArray.put(R.id.llSmartDownload, 11);
        sparseIntArray.put(R.id.tvSmartDownload, 12);
        sparseIntArray.put(R.id.llDownloadLocation, 13);
        sparseIntArray.put(R.id.tvDownloadLocation, 14);
        sparseIntArray.put(R.id.llAutoPlayNext, 15);
        sparseIntArray.put(R.id.tvPlayNext, 16);
        sparseIntArray.put(R.id.llAudioDelay, 17);
        sparseIntArray.put(R.id.tvAudioDelay, 18);
    }

    public FragmentSettingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private FragmentSettingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[17], (LinearLayout) bindings[15], (LinearLayout) bindings[5], (LinearLayout) bindings[13], (LinearLayout) bindings[9], (LinearLayout) bindings[7], (LinearLayout) bindings[11], (LinearLayout) bindings[3], (LinearLayout) bindings[2], (TvRecyclerView) bindings[1], (TextView) bindings[18], (TextView) bindings[6], (TextView) bindings[14], (TextView) bindings[10], (TextView) bindings[4], (TextView) bindings[16], (TextView) bindings[8], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
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
