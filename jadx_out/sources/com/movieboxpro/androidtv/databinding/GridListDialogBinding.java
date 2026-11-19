package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class GridListDialogBinding extends ViewDataBinding {
    public final FrameLayout flLoading;
    public final TvRecyclerView recyclerView;
    public final TextView tvName;

    protected GridListDialogBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flLoading, TvRecyclerView recyclerView, TextView tvName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flLoading = flLoading;
        this.recyclerView = recyclerView;
        this.tvName = tvName;
    }

    public static GridListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (GridListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.grid_list_dialog, root, attachToRoot, component);
    }

    public static GridListDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridListDialogBinding inflate(LayoutInflater inflater, Object component) {
        return (GridListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.grid_list_dialog, null, false, component);
    }

    public static GridListDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static GridListDialogBinding bind(View view, Object component) {
        return (GridListDialogBinding) bind(component, view, R.layout.grid_list_dialog);
    }
}
