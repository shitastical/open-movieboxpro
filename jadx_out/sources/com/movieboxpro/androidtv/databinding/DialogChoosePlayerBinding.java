package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChoosePlayerBinding extends ViewDataBinding {
    public final LinearLayout llOther;
    public final LinearLayout llPlay;

    protected DialogChoosePlayerBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llOther, LinearLayout llPlay) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llOther = llOther;
        this.llPlay = llPlay;
    }

    public static DialogChoosePlayerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChoosePlayerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_player, root, attachToRoot, component);
    }

    public static DialogChoosePlayerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChoosePlayerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_player, null, false, component);
    }

    public static DialogChoosePlayerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerBinding bind(View view, Object component) {
        return (DialogChoosePlayerBinding) bind(component, view, R.layout.dialog_choose_player);
    }
}
