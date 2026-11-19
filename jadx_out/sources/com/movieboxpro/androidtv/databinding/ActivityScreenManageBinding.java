package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityScreenManageBinding extends ViewDataBinding {
    public final RecyclerView rvMembers;
    public final TvRecyclerView rvMyScreen;
    public final TextView tvErrMsg;
    public final TextView tvFamilyName;
    public final TextView tvMineScreenNum;
    public final TextView tvUpgrade;

    protected ActivityScreenManageBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvMembers, TvRecyclerView rvMyScreen, TextView tvErrMsg, TextView tvFamilyName, TextView tvMineScreenNum, TextView tvUpgrade) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvMembers = rvMembers;
        this.rvMyScreen = rvMyScreen;
        this.tvErrMsg = tvErrMsg;
        this.tvFamilyName = tvFamilyName;
        this.tvMineScreenNum = tvMineScreenNum;
        this.tvUpgrade = tvUpgrade;
    }

    public static ActivityScreenManageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityScreenManageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityScreenManageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_screen_manage, root, attachToRoot, component);
    }

    public static ActivityScreenManageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityScreenManageBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityScreenManageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_screen_manage, null, false, component);
    }

    public static ActivityScreenManageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityScreenManageBinding bind(View view, Object component) {
        return (ActivityScreenManageBinding) bind(component, view, R.layout.activity_screen_manage);
    }
}
