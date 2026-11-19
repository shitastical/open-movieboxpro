package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class VerticalListDialogBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected VerticalListDialogBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static VerticalListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VerticalListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (VerticalListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.vertical_list_dialog, root, attachToRoot, component);
    }

    public static VerticalListDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VerticalListDialogBinding inflate(LayoutInflater inflater, Object component) {
        return (VerticalListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.vertical_list_dialog, null, false, component);
    }

    public static VerticalListDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VerticalListDialogBinding bind(View view, Object component) {
        return (VerticalListDialogBinding) bind(component, view, R.layout.vertical_list_dialog);
    }
}
