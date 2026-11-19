package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogAddToBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected DialogAddToBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogAddToBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogAddToBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_to, root, attachToRoot, component);
    }

    public static DialogAddToBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogAddToBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_add_to, null, false, component);
    }

    public static DialogAddToBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogAddToBinding bind(View view, Object component) {
        return (DialogAddToBinding) bind(component, view, R.layout.dialog_add_to);
    }
}
