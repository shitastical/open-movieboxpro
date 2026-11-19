package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import de.hdodenhof.circleimageview.CircleImageView;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityPlayListDetailBindingImpl extends ActivityPlayListDetailBinding {
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
        sparseIntArray.put(R.id.ivRandomPlay, 1);
        sparseIntArray.put(R.id.ivSort, 2);
        sparseIntArray.put(R.id.ivCollect, 3);
        sparseIntArray.put(R.id.tvTitle, 4);
        sparseIntArray.put(R.id.tvLike, 5);
        sparseIntArray.put(R.id.tvDesc, 6);
        sparseIntArray.put(R.id.clAvatar, 7);
        sparseIntArray.put(R.id.linearLayout, 8);
        sparseIntArray.put(R.id.tvUsername, 9);
        sparseIntArray.put(R.id.tvTime, 10);
        sparseIntArray.put(R.id.ivAvatar, 11);
        sparseIntArray.put(R.id.frameLayout, 12);
    }

    public ActivityPlayListDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private ActivityPlayListDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[7], (FrameLayout) bindings[12], (CircleImageView) bindings[11], (ImageView) bindings[3], (ImageView) bindings[1], (ImageView) bindings[2], (LinearLayout) bindings[8], (TextView) bindings[6], (TextView) bindings[5], (TextView) bindings[10], (TextView) bindings[4], (TextView) bindings[9]);
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
