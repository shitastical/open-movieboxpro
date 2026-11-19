package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DialogSubtitleColorBinding extends ViewDataBinding {
    public final TvRecyclerView rvAlphaColor;
    public final TvRecyclerView rvColor;
    public final TextView tvSave;

    protected DialogSubtitleColorBinding(Object _bindingComponent, View _root, int _localFieldCount, TvRecyclerView rvAlphaColor, TvRecyclerView rvColor, TextView tvSave) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvAlphaColor = rvAlphaColor;
        this.rvColor = rvColor;
        this.tvSave = tvSave;
    }

    public static DialogSubtitleColorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSubtitleColorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSubtitleColorBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_subtitle_color, root, attachToRoot, component);
    }

    public static DialogSubtitleColorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSubtitleColorBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSubtitleColorBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_subtitle_color, null, false, component);
    }

    public static DialogSubtitleColorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSubtitleColorBinding bind(View view, Object component) {
        return (DialogSubtitleColorBinding) bind(component, view, R.layout.dialog_subtitle_color);
    }
}
