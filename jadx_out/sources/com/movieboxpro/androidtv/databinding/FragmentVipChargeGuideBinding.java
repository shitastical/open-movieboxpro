package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentVipChargeGuideBinding extends ViewDataBinding {
    public final ConstraintLayout constraintLayout2;
    public final ConstraintLayout constraintLayout3;
    public final ConstraintLayout constraintLayout4;
    public final ConstraintLayout constraintLayout7;
    public final ConstraintLayout constraintLayout8;
    public final ImageView imageView1;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final ImageView imageView5;
    public final ImageView ivQrCode;
    public final ProgressBar progressBar;
    public final TextView textView10;
    public final TextView textView11;
    public final TextView textView12;
    public final TextView textView13;
    public final TextView textView14;
    public final TextView textView15;
    public final TextView textView16;
    public final TextView textView3;
    public final TextView textView5;
    public final TextView textView6;
    public final TextView textView7;
    public final TextView textView9;
    public final TextView tvTryAgain;

    protected FragmentVipChargeGuideBinding(Object _bindingComponent, View _root, int _localFieldCount, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, ImageView imageView1, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView ivQrCode, ProgressBar progressBar, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView3, TextView textView5, TextView textView6, TextView textView7, TextView textView9, TextView tvTryAgain) {
        super(_bindingComponent, _root, _localFieldCount);
        this.constraintLayout2 = constraintLayout2;
        this.constraintLayout3 = constraintLayout3;
        this.constraintLayout4 = constraintLayout4;
        this.constraintLayout7 = constraintLayout7;
        this.constraintLayout8 = constraintLayout8;
        this.imageView1 = imageView1;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.imageView5 = imageView5;
        this.ivQrCode = ivQrCode;
        this.progressBar = progressBar;
        this.textView10 = textView10;
        this.textView11 = textView11;
        this.textView12 = textView12;
        this.textView13 = textView13;
        this.textView14 = textView14;
        this.textView15 = textView15;
        this.textView16 = textView16;
        this.textView3 = textView3;
        this.textView5 = textView5;
        this.textView6 = textView6;
        this.textView7 = textView7;
        this.textView9 = textView9;
        this.tvTryAgain = tvTryAgain;
    }

    public static FragmentVipChargeGuideBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipChargeGuideBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVipChargeGuideBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_vip_charge_guide, root, attachToRoot, component);
    }

    public static FragmentVipChargeGuideBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipChargeGuideBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVipChargeGuideBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_vip_charge_guide, null, false, component);
    }

    public static FragmentVipChargeGuideBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVipChargeGuideBinding bind(View view, Object component) {
        return (FragmentVipChargeGuideBinding) bind(component, view, R.layout.fragment_vip_charge_guide);
    }
}
