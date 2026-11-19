package com.movieboxpro.android.model;

import android.app.Activity;
import io.reactivex.Observable;

/* JADX INFO: loaded from: classes5.dex */
public interface MediaFunction {
    int ChoosePlayer();

    void addDonwload(BaseMediaModel donwload);

    int getBoxType();

    int getEpisode();

    String getId();

    int getSeason();

    Observable<String> getSrt();

    String getTid();

    Observable<String> getUrl();

    boolean saveInDao(int i, Activity activity);

    void startService(String type, Activity activity);
}
