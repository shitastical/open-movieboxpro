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
public abstract class FragmentTvGuideBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;
    public final TvRecyclerView rvTimeLine;

    protected FragmentTvGuideBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout frameLayout, TvRecyclerView rvTimeLine) {
        super(_bindingComponent, _root, _localFieldCount);
        this.frameLayout = frameLayout;
        this.rvTimeLine = rvTimeLine;
    }

    public static FragmentTvGuideBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTvGuideBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentTvGuideBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_tv_guide, root, attachToRoot, component);
    }

    public static FragmentTvGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTvGuideBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentTvGuideBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_tv_guide, null, false, component);
    }

    public static FragmentTvGuideBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTvGuideBinding bind(View view, Object component) {
        return (FragmentTvGuideBinding) bind(component, view, R.layout.fragment_tv_guide);
    }
}
