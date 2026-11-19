package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.android.view.widget.NoScrollViewPager;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogOpenSubtitleBinding extends ViewDataBinding {
    public final TvRecyclerView rvLanguage;
    public final TextView tvMore;
    public final NoScrollViewPager viewPager;

    protected DialogOpenSubtitleBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvLanguage, TextView tvMore, NoScrollViewPager viewPager) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvLanguage = rvLanguage;
        this.tvMore = tvMore;
        this.viewPager = viewPager;
    }

    public static DialogOpenSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogOpenSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogOpenSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_open_subtitle, root, attachToRoot, component);
    }

    public static DialogOpenSubtitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogOpenSubtitleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogOpenSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_open_subtitle, null, false, component);
    }

    public static DialogOpenSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogOpenSubtitleBinding bind(View view, Object component) {
        return (DialogOpenSubtitleBinding) bind(component, view, R.layout.dialog_open_subtitle);
    }
}
