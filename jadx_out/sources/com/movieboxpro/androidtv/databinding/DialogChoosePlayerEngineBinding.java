package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChoosePlayerEngineBinding extends ViewDataBinding {
    public final LinearLayout llExo;
    public final LinearLayout llIjk;
    public final LinearLayout llVlc;

    protected DialogChoosePlayerEngineBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llExo, LinearLayout llIjk, LinearLayout llVlc) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llExo = llExo;
        this.llIjk = llIjk;
        this.llVlc = llVlc;
    }

    public static DialogChoosePlayerEngineBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerEngineBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChoosePlayerEngineBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_player_engine, root, attachToRoot, component);
    }

    public static DialogChoosePlayerEngineBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerEngineBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChoosePlayerEngineBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_player_engine, null, false, component);
    }

    public static DialogChoosePlayerEngineBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlayerEngineBinding bind(View view, Object component) {
        return (DialogChoosePlayerEngineBinding) bind(component, view, R.layout.dialog_choose_player_engine);
    }
}
