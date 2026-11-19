package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SimpleListDialogBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    protected SimpleListDialogBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static SimpleListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleListDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (SimpleListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.simple_list_dialog, root, attachToRoot, component);
    }

    public static SimpleListDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleListDialogBinding inflate(LayoutInflater inflater, Object component) {
        return (SimpleListDialogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.simple_list_dialog, null, false, component);
    }

    public static SimpleListDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SimpleListDialogBinding bind(View view, Object component) {
        return (SimpleListDialogBinding) bind(component, view, R.layout.simple_list_dialog);
    }
}
