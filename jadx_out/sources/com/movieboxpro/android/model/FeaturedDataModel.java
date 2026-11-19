package com.movieboxpro.android.model;

import com.movieboxpro.android.model.common.Homelist;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class FeaturedDataModel {
    private List<AdvertisementModel> advertisementModels;
    private List<Homelist> homelists;

    public FeaturedDataModel() {
    }

    public FeaturedDataModel(List<AdvertisementModel> advertisementModel, List<Homelist> homelists) {
        this.advertisementModels = advertisementModel;
        this.homelists = homelists;
    }

    public List<AdvertisementModel> getAdvertisementModels() {
        return this.advertisementModels;
    }

    public void setAdvertisementModels(List<AdvertisementModel> advertisementModels) {
        this.advertisementModels = advertisementModels;
    }

    public List<Homelist> getHomelists() {
        return this.homelists;
    }

    public void setHomelists(List<Homelist> homelists) {
        this.homelists = homelists;
    }
}
