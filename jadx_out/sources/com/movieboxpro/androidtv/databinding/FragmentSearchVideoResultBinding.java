package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tab.TvTabLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentSearchVideoResultBinding extends ViewDataBinding {
    public final FrameLayout frameLayout;
    public final TvTabLayout tvTabLayout;

    protected FragmentSearchVideoResultBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout frameLayout, TvTabLayout tvTabLayout) {
        super(_bindingComponent, _root, _localFieldCount);
        this.frameLayout = frameLayout;
        this.tvTabLayout = tvTabLayout;
    }

    public static FragmentSearchVideoResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSearchVideoResultBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSearchVideoResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_search_video_result, root, attachToRoot, component);
    }

    public static FragmentSearchVideoResultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSearchVideoResultBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSearchVideoResultBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_search_video_result, null, false, component);
    }

    public static FragmentSearchVideoResultBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSearchVideoResultBinding bind(View view, Object component) {
        return (FragmentSearchVideoResultBinding) bind(component, view, R.layout.fragment_search_video_result);
    }
}
