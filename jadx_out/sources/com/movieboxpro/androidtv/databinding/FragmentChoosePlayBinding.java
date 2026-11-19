package com.movieboxpro.androidtv.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentChoosePlayBinding extends ViewDataBinding {
    public final LinearLayout llDelete;
    public final LinearLayout llDetail;
    public final LinearLayout llMark;
    public final LinearLayout llRestart;
    public final LinearLayout llResume;
    public final TextView tvContinue;
    public final TextView tvDetail;

    protected FragmentChoosePlayBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llDelete, LinearLayout llDetail, LinearLayout llMark, LinearLayout llRestart, LinearLayout llResume, TextView tvContinue, TextView tvDetail) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llDelete = llDelete;
        this.llDetail = llDetail;
        this.llMark = llMark;
        this.llRestart = llRestart;
        this.llResume = llResume;
        this.tvContinue = tvContinue;
        this.tvDetail = tvDetail;
    }

    public static FragmentChoosePlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChoosePlayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentChoosePlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_play, root, attachToRoot, component);
    }

    public static FragmentChoosePlayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChoosePlayBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentChoosePlayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_choose_play, null, false, component);
    }

    public static FragmentChoosePlayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentChoosePlayBinding bind(View view, Object component) {
        return (FragmentChoosePlayBinding) bind(component, view, R.layout.fragment_choose_play);
    }
}
