package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentOpenSubtitleBinding extends ViewDataBinding {
    public final FrameLayout flLoading;
    public final TvRecyclerView rvLanguage;
    public final TvRecyclerView rvSubtitle;

    protected FragmentOpenSubtitleBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout flLoading, TvRecyclerView rvLanguage, TvRecyclerView rvSubtitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.flLoading = flLoading;
        this.rvLanguage = rvLanguage;
        this.rvSubtitle = rvSubtitle;
    }

    public static FragmentOpenSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOpenSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentOpenSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_open_subtitle, root, attachToRoot, component);
    }

    public static FragmentOpenSubtitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOpenSubtitleBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentOpenSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_open_subtitle, null, false, component);
    }

    public static FragmentOpenSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOpenSubtitleBinding bind(View view, Object component) {
        return (FragmentOpenSubtitleBinding) bind(component, view, R.layout.fragment_open_subtitle);
    }
}
