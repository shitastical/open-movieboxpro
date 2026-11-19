package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentSwitchLanguageBinding extends ViewDataBinding {
    public final TvRecyclerView rvLanguage;
    public final TvRecyclerView rvWordIndex;

    protected FragmentSwitchLanguageBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvLanguage, TvRecyclerView rvWordIndex) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvLanguage = rvLanguage;
        this.rvWordIndex = rvWordIndex;
    }

    public static FragmentSwitchLanguageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSwitchLanguageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSwitchLanguageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_switch_language, root, attachToRoot, component);
    }

    public static FragmentSwitchLanguageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSwitchLanguageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSwitchLanguageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_switch_language, null, false, component);
    }

    public static FragmentSwitchLanguageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSwitchLanguageBinding bind(View view, Object component) {
        return (FragmentSwitchLanguageBinding) bind(component, view, R.layout.fragment_switch_language);
    }
}
