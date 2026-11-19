package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityTranslateSubtitleBinding extends ViewDataBinding {
    public final ImageView imageView;
    public final RecyclerView recyclerView;
    public final TextView tvCancel;
    public final TextView tvFrom;
    public final TextView tvOk;
    public final TextView tvProgress;
    public final TextView tvTo;

    protected ActivityTranslateSubtitleBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView imageView, RecyclerView recyclerView, TextView tvCancel, TextView tvFrom, TextView tvOk, TextView tvProgress, TextView tvTo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.imageView = imageView;
        this.recyclerView = recyclerView;
        this.tvCancel = tvCancel;
        this.tvFrom = tvFrom;
        this.tvOk = tvOk;
        this.tvProgress = tvProgress;
        this.tvTo = tvTo;
    }

    public static ActivityTranslateSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTranslateSubtitleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityTranslateSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_translate_subtitle, root, attachToRoot, component);
    }

    public static ActivityTranslateSubtitleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTranslateSubtitleBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityTranslateSubtitleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_translate_subtitle, null, false, component);
    }

    public static ActivityTranslateSubtitleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTranslateSubtitleBinding bind(View view, Object component) {
        return (ActivityTranslateSubtitleBinding) bind(component, view, R.layout.activity_translate_subtitle);
    }
}
