package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentChooseSeasonBinding extends ViewDataBinding {
    public final TvRecyclerView recyclerView;

    protected FragmentChooseSeasonBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static FragmentChooseSeasonBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseSeasonBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentChooseSeasonBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_season, root, attachToRoot, component);
    }

    public static FragmentChooseSeasonBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseSeasonBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentChooseSeasonBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_season, null, false, component);
    }

    public static FragmentChooseSeasonBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChooseSeasonBinding bind(View view, Object component) {
        return (FragmentChooseSeasonBinding) bind(component, view, R.layout.fragment_choose_season);
    }
}
