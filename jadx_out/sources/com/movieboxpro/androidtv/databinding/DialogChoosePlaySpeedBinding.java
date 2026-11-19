package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChoosePlaySpeedBinding extends ViewDataBinding {
    public final TextView tvSpeed05;
    public final TextView tvSpeed1;
    public final TextView tvSpeed125;
    public final TextView tvSpeed15;
    public final TextView tvSpeed2;

    protected DialogChoosePlaySpeedBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvSpeed05, TextView tvSpeed1, TextView tvSpeed125, TextView tvSpeed15, TextView tvSpeed2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvSpeed05 = tvSpeed05;
        this.tvSpeed1 = tvSpeed1;
        this.tvSpeed125 = tvSpeed125;
        this.tvSpeed15 = tvSpeed15;
        this.tvSpeed2 = tvSpeed2;
    }

    public static DialogChoosePlaySpeedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlaySpeedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChoosePlaySpeedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_play_speed, root, attachToRoot, component);
    }

    public static DialogChoosePlaySpeedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlaySpeedBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChoosePlaySpeedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_play_speed, null, false, component);
    }

    public static DialogChoosePlaySpeedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChoosePlaySpeedBinding bind(View view, Object component) {
        return (DialogChoosePlaySpeedBinding) bind(component, view, R.layout.dialog_choose_play_speed);
    }
}
