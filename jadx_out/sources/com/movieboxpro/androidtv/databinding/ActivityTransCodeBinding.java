package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityTransCodeBinding extends ViewDataBinding {
    public final FrameLayout flDone;
    public final TvRecyclerView rvCode;
    public final TvRecyclerView rvLanguage;
    public final TvRecyclerView rvSubtitle;

    protected ActivityTransCodeBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flDone, TvRecyclerView rvCode, TvRecyclerView rvLanguage, TvRecyclerView rvSubtitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flDone = flDone;
        this.rvCode = rvCode;
        this.rvLanguage = rvLanguage;
        this.rvSubtitle = rvSubtitle;
    }

    public static ActivityTransCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTransCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTransCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_trans_code, root, attachToRoot, component);
    }

    public static ActivityTransCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTransCodeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTransCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_trans_code, null, false, component);
    }

    public static ActivityTransCodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTransCodeBinding bind(View view, Object component) {
        return (ActivityTransCodeBinding) bind(component, view, R.layout.activity_trans_code);
    }
}
