package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityDownloadedTvBinding extends ViewDataBinding {
    public final LinearLayout llDelete;
    public final LinearLayout llDone;
    public final LinearLayout llHint;
    public final TvRecyclerView recyclerView;
    public final TextView tvDelete;
    public final TextView tvEdit;

    protected ActivityDownloadedTvBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llDelete, LinearLayout llDone, LinearLayout llHint, TvRecyclerView recyclerView, TextView tvDelete, TextView tvEdit) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llDelete = llDelete;
        this.llDone = llDone;
        this.llHint = llHint;
        this.recyclerView = recyclerView;
        this.tvDelete = tvDelete;
        this.tvEdit = tvEdit;
    }

    public static ActivityDownloadedTvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadedTvBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDownloadedTvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_downloaded_tv, root, attachToRoot, component);
    }

    public static ActivityDownloadedTvBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadedTvBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDownloadedTvBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_downloaded_tv, null, false, component);
    }

    public static ActivityDownloadedTvBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDownloadedTvBinding bind(View view, Object component) {
        return (ActivityDownloadedTvBinding) bind(component, view, R.layout.activity_downloaded_tv);
    }
}
