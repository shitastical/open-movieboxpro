package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChooseSeasonBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected DialogChooseSeasonBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogChooseSeasonBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseSeasonBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChooseSeasonBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_season, root, attachToRoot, component);
    }

    public static DialogChooseSeasonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseSeasonBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChooseSeasonBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_season, null, false, component);
    }

    public static DialogChooseSeasonBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseSeasonBinding bind(View view, Object component) {
        return (DialogChooseSeasonBinding) bind(component, view, R.layout.dialog_choose_season);
    }
}
