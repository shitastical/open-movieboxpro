package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogTranslateProgressBinding extends ViewDataBinding {
    public final ProgressBar progressBar;

    protected DialogTranslateProgressBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar progressBar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.progressBar = progressBar;
    }

    public static DialogTranslateProgressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTranslateProgressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogTranslateProgressBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_translate_progress, root, attachToRoot, component);
    }

    public static DialogTranslateProgressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTranslateProgressBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogTranslateProgressBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_translate_progress, null, false, component);
    }

    public static DialogTranslateProgressBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTranslateProgressBinding bind(View view, Object component) {
        return (DialogTranslateProgressBinding) bind(component, view, R.layout.dialog_translate_progress);
    }
}
