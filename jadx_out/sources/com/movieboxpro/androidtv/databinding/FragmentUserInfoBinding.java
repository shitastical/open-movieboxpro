package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;
import de.hdodenhof.circleimageview.CircleImageView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentUserInfoBinding extends ViewDataBinding {
    public final ImageView ivAccount;
    public final CircleImageView ivAvatar;
    public final ImageView ivCrown;
    public final ImageView ivHistory;
    public final ImageView ivSetting;
    public final ImageView ivVip;
    public final TextView tvSignOut;
    public final TextView tvUsername;
    public final TextView tvVersionName;
    public final TextView tvVipDate;

    protected FragmentUserInfoBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivAccount, CircleImageView ivAvatar, ImageView ivCrown, ImageView ivHistory, ImageView ivSetting, ImageView ivVip, TextView tvSignOut, TextView tvUsername, TextView tvVersionName, TextView tvVipDate) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivAccount = ivAccount;
        this.ivAvatar = ivAvatar;
        this.ivCrown = ivCrown;
        this.ivHistory = ivHistory;
        this.ivSetting = ivSetting;
        this.ivVip = ivVip;
        this.tvSignOut = tvSignOut;
        this.tvUsername = tvUsername;
        this.tvVersionName = tvVersionName;
        this.tvVipDate = tvVipDate;
    }

    public static FragmentUserInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentUserInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_user_info, root, attachToRoot, component);
    }

    public static FragmentUserInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfoBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentUserInfoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_user_info, null, false, component);
    }

    public static FragmentUserInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentUserInfoBinding bind(View view, Object component) {
        return (FragmentUserInfoBinding) bind(component, view, R.layout.fragment_user_info);
    }
}
