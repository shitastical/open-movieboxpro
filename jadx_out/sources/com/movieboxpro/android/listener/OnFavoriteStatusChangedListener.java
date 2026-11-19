package com.movieboxpro.android.listener;

/* JADX INFO: loaded from: classes5.dex */
public interface OnFavoriteStatusChangedListener {
    void hideLoadLoading();

    void onFavoriteStatusChanged(boolean isFavorite);

    void showLoadLoading();

    void showViewToast(String s);
}
