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
public abstract class FragmentSubtitleBinding extends ViewDataBinding {
    public final TvRecyclerView rvLanguage;
    public final TvRecyclerView rvSubtitle;
    public final TextView tvFilename;
    public final TextView tvOpenSubtitle;

    protected FragmentSubtitleBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvLanguage, TvRecyclerView rvSubtitle, TextView tvFilename, TextView tvOpenSubtitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvLanguage = rvLanguage;
        this.rvSubtitle = rvSubtitle;
        this.tvFilename = tvFilename;
        this.tvOpenSubtitle = tvOpenSubtitle;
    }

    public static FragmentSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_subtitle, root, attachToRoot, component);
    }

    public static FragmentSubtitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubtitleBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_subtitle, null, false, component);
    }

    public static FragmentSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSubtitleBinding bind(View view, Object component) {
        return (FragmentSubtitleBinding) bind(component, view, R.layout.fragment_subtitle);
    }
}
