package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogCheckDownloadPathBinding extends ViewDataBinding {
    protected DialogCheckDownloadPathBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static DialogCheckDownloadPathBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckDownloadPathBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogCheckDownloadPathBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_check_download_path, root, attachToRoot, component);
    }

    public static DialogCheckDownloadPathBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckDownloadPathBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogCheckDownloadPathBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_check_download_path, null, false, component);
    }

    public static DialogCheckDownloadPathBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckDownloadPathBinding bind(View view, Object component) {
        return (DialogCheckDownloadPathBinding) bind(component, view, R.layout.dialog_check_download_path);
    }
}
