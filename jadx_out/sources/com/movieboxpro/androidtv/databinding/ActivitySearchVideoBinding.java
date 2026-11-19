package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivitySearchVideoBinding extends ViewDataBinding {
    public final EditText etSearch;
    public final FrameLayout frameLayout;
    public final ImageView ivFilter;
    public final TvRecyclerView rvHistory;
    public final TvRecyclerView rvHotSearch;
    public final TvRecyclerView rvKeyWord;
    public final TextView tvDoSearch;

    protected ActivitySearchVideoBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText etSearch, FrameLayout frameLayout, ImageView ivFilter, TvRecyclerView rvHistory, TvRecyclerView rvHotSearch, TvRecyclerView rvKeyWord, TextView tvDoSearch) {
        super(_bindingComponent, _root, _localFieldCount);
        this.etSearch = etSearch;
        this.frameLayout = frameLayout;
        this.ivFilter = ivFilter;
        this.rvHistory = rvHistory;
        this.rvHotSearch = rvHotSearch;
        this.rvKeyWord = rvKeyWord;
        this.tvDoSearch = tvDoSearch;
    }

    public static ActivitySearchVideoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchVideoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySearchVideoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_search_video, root, attachToRoot, component);
    }

    public static ActivitySearchVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchVideoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySearchVideoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_search_video, null, false, component);
    }

    public static ActivitySearchVideoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchVideoBinding bind(View view, Object component) {
        return (ActivitySearchVideoBinding) bind(component, view, R.layout.activity_search_video);
    }
}
