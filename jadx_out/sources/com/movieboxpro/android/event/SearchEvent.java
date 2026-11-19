package com.movieboxpro.android.event;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes5.dex */
public class SearchEvent {
    public Bitmap bitmap;
    public String keyWord;
    public String tab;

    public SearchEvent(String keyWord, String tab) {
        this.keyWord = keyWord;
        this.tab = tab;
    }

    public SearchEvent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
