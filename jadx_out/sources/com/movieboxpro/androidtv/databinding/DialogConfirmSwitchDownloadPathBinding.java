package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogConfirmSwitchDownloadPathBinding extends ViewDataBinding {
    public final TextView tvCancel;
    public final TextView tvConfirm;

    protected DialogConfirmSwitchDownloadPathBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvCancel, TextView tvConfirm) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
    }

    public static DialogConfirmSwitchDownloadPathBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmSwitchDownloadPathBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogConfirmSwitchDownloadPathBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_confirm_switch_download_path, root, attachToRoot, component);
    }

    public static DialogConfirmSwitchDownloadPathBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmSwitchDownloadPathBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogConfirmSwitchDownloadPathBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_confirm_switch_download_path, null, false, component);
    }

    public static DialogConfirmSwitchDownloadPathBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmSwitchDownloadPathBinding bind(View view, Object component) {
        return (DialogConfirmSwitchDownloadPathBinding) bind(component, view, R.layout.dialog_confirm_switch_download_path);
    }
}
