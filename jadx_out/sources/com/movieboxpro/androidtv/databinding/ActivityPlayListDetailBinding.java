package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import de.hdodenhof.circleimageview.CircleImageView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityPlayListDetailBinding extends ViewDataBinding {
    public final ConstraintLayout clAvatar;
    public final FrameLayout frameLayout;
    public final CircleImageView ivAvatar;
    public final ImageView ivCollect;
    public final ImageView ivRandomPlay;
    public final ImageView ivSort;
    public final LinearLayout linearLayout;
    public final TextView tvDesc;
    public final TextView tvLike;
    public final TextView tvTime;
    public final TextView tvTitle;
    public final TextView tvUsername;

    protected ActivityPlayListDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout clAvatar, FrameLayout frameLayout, CircleImageView ivAvatar, ImageView ivCollect, ImageView ivRandomPlay, ImageView ivSort, LinearLayout linearLayout, TextView tvDesc, TextView tvLike, TextView tvTime, TextView tvTitle, TextView tvUsername) {
        super(_bindingComponent, _root, _localFieldCount);
        this.clAvatar = clAvatar;
        this.frameLayout = frameLayout;
        this.ivAvatar = ivAvatar;
        this.ivCollect = ivCollect;
        this.ivRandomPlay = ivRandomPlay;
        this.ivSort = ivSort;
        this.linearLayout = linearLayout;
        this.tvDesc = tvDesc;
        this.tvLike = tvLike;
        this.tvTime = tvTime;
        this.tvTitle = tvTitle;
        this.tvUsername = tvUsername;
    }

    public static ActivityPlayListDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPlayListDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPlayListDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_play_list_detail, root, attachToRoot, component);
    }

    public static ActivityPlayListDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPlayListDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPlayListDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_play_list_detail, null, false, component);
    }

    public static ActivityPlayListDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPlayListDetailBinding bind(View view, Object component) {
        return (ActivityPlayListDetailBinding) bind(component, view, R.layout.activity_play_list_detail);
    }
}
