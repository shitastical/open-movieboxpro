package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogChooseDownloadLanguageBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    protected DialogChooseDownloadLanguageBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView recyclerView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.recyclerView = recyclerView;
    }

    public static DialogChooseDownloadLanguageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLanguageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogChooseDownloadLanguageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_download_language, root, attachToRoot, component);
    }

    public static DialogChooseDownloadLanguageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLanguageBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogChooseDownloadLanguageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_choose_download_language, null, false, component);
    }

    public static DialogChooseDownloadLanguageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogChooseDownloadLanguageBinding bind(View view, Object component) {
        return (DialogChooseDownloadLanguageBinding) bind(component, view, R.layout.dialog_choose_download_language);
    }
}
