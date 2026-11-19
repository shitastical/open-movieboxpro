package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentSpecialFilterBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected FragmentSpecialFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static FragmentSpecialFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSpecialFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_special_filter, root, attachToRoot, component);
    }

    public static FragmentSpecialFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSpecialFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_special_filter, null, false, component);
    }

    public static FragmentSpecialFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSpecialFilterBinding bind(View view, Object component) {
        return (FragmentSpecialFilterBinding) bind(component, view, R.layout.fragment_special_filter);
    }
}
