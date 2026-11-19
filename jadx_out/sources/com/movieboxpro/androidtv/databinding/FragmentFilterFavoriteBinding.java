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
public abstract class FragmentFilterFavoriteBinding extends ViewDataBinding {
    public final TvRecyclerView rvCountry;
    public final TvRecyclerView rvGener;
    public final TvRecyclerView rvRating;
    public final TvRecyclerView rvResolution;
    public final TvRecyclerView rvSort;
    public final TvRecyclerView rvType;
    public final TvRecyclerView rvYear;
    public final TextView tvDone;
    public final TextView tvRating;
    public final TextView tvReset;
    public final TextView tvResolution;

    protected FragmentFilterFavoriteBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvCountry, TvRecyclerView rvGener, TvRecyclerView rvRating, TvRecyclerView rvResolution, TvRecyclerView rvSort, TvRecyclerView rvType, TvRecyclerView rvYear, TextView tvDone, TextView tvRating, TextView tvReset, TextView tvResolution) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvCountry = rvCountry;
        this.rvGener = rvGener;
        this.rvRating = rvRating;
        this.rvResolution = rvResolution;
        this.rvSort = rvSort;
        this.rvType = rvType;
        this.rvYear = rvYear;
        this.tvDone = tvDone;
        this.tvRating = tvRating;
        this.tvReset = tvReset;
        this.tvResolution = tvResolution;
    }

    public static FragmentFilterFavoriteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterFavoriteBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentFilterFavoriteBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_filter_favorite, root, attachToRoot, component);
    }

    public static FragmentFilterFavoriteBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterFavoriteBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentFilterFavoriteBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_filter_favorite, null, false, component);
    }

    public static FragmentFilterFavoriteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFilterFavoriteBinding bind(View view, Object component) {
        return (FragmentFilterFavoriteBinding) bind(component, view, R.layout.fragment_filter_favorite);
    }
}
