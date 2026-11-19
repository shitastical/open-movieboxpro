package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogNoEnoughSpaceBinding extends ViewDataBinding {
    public final TextView tvCancel;
    public final TextView tvManage;

    protected DialogNoEnoughSpaceBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvCancel, TextView tvManage) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvCancel = tvCancel;
        this.tvManage = tvManage;
    }

    public static DialogNoEnoughSpaceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoEnoughSpaceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogNoEnoughSpaceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_no_enough_space, root, attachToRoot, component);
    }

    public static DialogNoEnoughSpaceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoEnoughSpaceBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogNoEnoughSpaceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_no_enough_space, null, false, component);
    }

    public static DialogNoEnoughSpaceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogNoEnoughSpaceBinding bind(View view, Object component) {
        return (DialogNoEnoughSpaceBinding) bind(component, view, R.layout.dialog_no_enough_space);
    }
}
