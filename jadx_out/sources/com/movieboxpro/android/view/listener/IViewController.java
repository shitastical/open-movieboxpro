package com.movieboxpro.android.view.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes5.dex */
public interface IViewController {
    void hideLoading();

    void hideLoading(boolean isDelay);

    void initData();

    void initPresenter();

    void initView();

    View loadView(LayoutInflater inflater, ViewGroup container);

    void showAlert(String msg);

    void showLoading();

    void showNoInternetDialog();

    void showToast(String msg);

    <T> void updateView(T t);
}
