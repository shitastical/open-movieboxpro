package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentFilterBinding extends ViewDataBinding {
    public final TvRecyclerView rvCountry;
    public final TvRecyclerView rvGener;
    public final TvRecyclerView rvRating;
    public final TvRecyclerView rvResolution;
    public final TvRecyclerView rvSort;
    public final TvRecyclerView rvYear;
    public final TextView tvDone;
    public final TextView tvImdbEnd;
    public final TextView tvImdbStart;
    public final TextView tvRating;
    public final TextView tvReset;
    public final TextView tvResolution;
    public final TextView tvTomatoEnd;
    public final TextView tvTomatoStart;

    protected FragmentFilterBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvCountry, TvRecyclerView rvGener, TvRecyclerView rvRating, TvRecyclerView rvResolution, TvRecyclerView rvSort, TvRecyclerView rvYear, TextView tvDone, TextView tvImdbEnd, TextView tvImdbStart, TextView tvRating, TextView tvReset, TextView tvResolution, TextView tvTomatoEnd, TextView tvTomatoStart) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvCountry = rvCountry;
        this.rvGener = rvGener;
        this.rvRating = rvRating;
        this.rvResolution = rvResolution;
        this.rvSort = rvSort;
        this.rvYear = rvYear;
        this.tvDone = tvDone;
        this.tvImdbEnd = tvImdbEnd;
        this.tvImdbStart = tvImdbStart;
        this.tvRating = tvRating;
        this.tvReset = tvReset;
        this.tvResolution = tvResolution;
        this.tvTomatoEnd = tvTomatoEnd;
        this.tvTomatoStart = tvTomatoStart;
    }

    public static FragmentFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_filter, root, attachToRoot, component);
    }

    public static FragmentFilterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentFilterBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_filter, null, false, component);
    }

    public static FragmentFilterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterBinding bind(View view, Object component) {
        return (FragmentFilterBinding) bind(component, view, R.layout.fragment_filter);
    }
}
