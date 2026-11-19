package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LayoutDetailMetaRowBinding extends ViewDataBinding {
    public final ImageView ivAudienceScore;
    public final ImageView ivStar;
    public final ImageView ivTomato;
    public final LinearLayout metaInfoRoot;
    public final TextView tvAudienceScore;
    public final TextView tvImdbRating;
    public final TextView tvMetaExtra;
    public final TextView tvMetaGenre;
    public final TextView tvMetaRating;
    public final TextView tvMetaYear;
    public final TextView tvTomatoMeter;

    protected LayoutDetailMetaRowBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAudienceScore, ImageView ivStar, ImageView ivTomato, LinearLayout metaInfoRoot, TextView tvAudienceScore, TextView tvImdbRating, TextView tvMetaExtra, TextView tvMetaGenre, TextView tvMetaRating, TextView tvMetaYear, TextView tvTomatoMeter) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAudienceScore = ivAudienceScore;
        this.ivStar = ivStar;
        this.ivTomato = ivTomato;
        this.metaInfoRoot = metaInfoRoot;
        this.tvAudienceScore = tvAudienceScore;
        this.tvImdbRating = tvImdbRating;
        this.tvMetaExtra = tvMetaExtra;
        this.tvMetaGenre = tvMetaGenre;
        this.tvMetaRating = tvMetaRating;
        this.tvMetaYear = tvMetaYear;
        this.tvTomatoMeter = tvTomatoMeter;
    }

    public static LayoutDetailMetaRowBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDetailMetaRowBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutDetailMetaRowBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_detail_meta_row, root, attachToRoot, component);
    }

    public static LayoutDetailMetaRowBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDetailMetaRowBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutDetailMetaRowBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_detail_meta_row, null, false, component);
    }

    public static LayoutDetailMetaRowBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDetailMetaRowBinding bind(View view, Object component) {
        return (LayoutDetailMetaRowBinding) bind(component, view, R.layout.layout_detail_meta_row);
    }
}
