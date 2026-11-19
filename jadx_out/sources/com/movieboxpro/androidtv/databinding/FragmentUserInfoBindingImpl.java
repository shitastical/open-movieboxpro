package com.movieboxpro.androidtv.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import de.hdodenhof.circleimageview.CircleImageView;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentUserInfoBindingImpl extends FragmentUserInfoBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

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
        sparseIntArray.put(R.id.tvVersionName, 1);
        sparseIntArray.put(R.id.ivAvatar, 2);
        sparseIntArray.put(R.id.tvUsername, 3);
        sparseIntArray.put(R.id.ivVip, 4);
        sparseIntArray.put(R.id.tvVipDate, 5);
        sparseIntArray.put(R.id.ivAccount, 6);
        sparseIntArray.put(R.id.tvSignOut, 7);
        sparseIntArray.put(R.id.ivHistory, 8);
        sparseIntArray.put(R.id.ivCrown, 9);
        sparseIntArray.put(R.id.ivSetting, 10);
    }

    public FragmentUserInfoBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentUserInfoBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[6], (CircleImageView) bindings[2], (ImageView) bindings[9], (ImageView) bindings[8], (ImageView) bindings[10], (ImageView) bindings[4], (TextView) bindings[7], (TextView) bindings[3], (TextView) bindings[1], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
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
