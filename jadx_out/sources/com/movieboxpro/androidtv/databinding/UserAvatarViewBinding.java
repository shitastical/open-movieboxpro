package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UserAvatarViewBinding extends ViewDataBinding {
    public final ImageView ivAvatars;
    public final TextView tvNameAvatar;

    protected UserAvatarViewBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAvatars, TextView tvNameAvatar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAvatars = ivAvatars;
        this.tvNameAvatar = tvNameAvatar;
    }

    public static UserAvatarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UserAvatarViewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (UserAvatarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.user_avatar_view, root, attachToRoot, component);
    }

    public static UserAvatarViewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UserAvatarViewBinding inflate(LayoutInflater inflater, Object component) {
        return (UserAvatarViewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.user_avatar_view, null, false, component);
    }

    public static UserAvatarViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static UserAvatarViewBinding bind(View view, Object component) {
        return (UserAvatarViewBinding) bind(component, view, R.layout.user_avatar_view);
    }
}
