package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentVipChargeGuideBindingImpl extends FragmentVipChargeGuideBinding {
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
        sparseIntArray.put(R.id.constraintLayout8, 1);
        sparseIntArray.put(R.id.constraintLayout3, 2);
        sparseIntArray.put(R.id.textView3, 3);
        sparseIntArray.put(R.id.textView5, 4);
        sparseIntArray.put(R.id.textView6, 5);
        sparseIntArray.put(R.id.constraintLayout2, 6);
        sparseIntArray.put(R.id.textView7, 7);
        sparseIntArray.put(R.id.textView9, 8);
        sparseIntArray.put(R.id.textView11, 9);
        sparseIntArray.put(R.id.textView10, 10);
        sparseIntArray.put(R.id.constraintLayout4, 11);
        sparseIntArray.put(R.id.textView12, 12);
        sparseIntArray.put(R.id.textView13, 13);
        sparseIntArray.put(R.id.textView14, 14);
        sparseIntArray.put(R.id.textView15, 15);
        sparseIntArray.put(R.id.constraintLayout7, 16);
        sparseIntArray.put(R.id.ivQrCode, 17);
        sparseIntArray.put(R.id.progressBar, 18);
        sparseIntArray.put(R.id.tvTryAgain, 19);
        sparseIntArray.put(R.id.textView16, 20);
        sparseIntArray.put(R.id.imageView1, 21);
        sparseIntArray.put(R.id.imageView2, 22);
        sparseIntArray.put(R.id.imageView3, 23);
        sparseIntArray.put(R.id.imageView4, 24);
        sparseIntArray.put(R.id.imageView5, 25);
    }

    public FragmentVipChargeGuideBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }

    private FragmentVipChargeGuideBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[6], (ConstraintLayout) bindings[2], (ConstraintLayout) bindings[11], (ConstraintLayout) bindings[16], (ConstraintLayout) bindings[1], (ImageView) bindings[21], (ImageView) bindings[22], (ImageView) bindings[23], (ImageView) bindings[24], (ImageView) bindings[25], (ImageView) bindings[17], (ProgressBar) bindings[18], (TextView) bindings[10], (TextView) bindings[9], (TextView) bindings[12], (TextView) bindings[13], (TextView) bindings[14], (TextView) bindings[15], (TextView) bindings[20], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[7], (TextView) bindings[8], (TextView) bindings[19]);
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
