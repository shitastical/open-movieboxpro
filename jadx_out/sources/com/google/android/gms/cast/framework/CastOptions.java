package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CastOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CastOptions> CREATOR = new zzb();
    private final LaunchOptions zzdd;
    private String zzho;
    private final List<String> zzhp;
    private final boolean zzhq;
    private final boolean zzhr;
    private final CastMediaOptions zzhs;
    private final boolean zzht;
    private final double zzhu;
    private final boolean zzhv;

    CastOptions(String str, List<String> list, boolean z, LaunchOptions launchOptions, boolean z2, CastMediaOptions castMediaOptions, boolean z3, double d, boolean z4) {
        this.zzho = TextUtils.isEmpty(str) ? "" : str;
        int size = list == null ? 0 : list.size();
        ArrayList arrayList = new ArrayList(size);
        this.zzhp = arrayList;
        if (size > 0) {
            arrayList.addAll(list);
        }
        this.zzhq = z;
        this.zzdd = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.zzhr = z2;
        this.zzhs = castMediaOptions;
        this.zzht = z3;
        this.zzhu = d;
        this.zzhv = z4;
    }

    public static final class Builder {
        private String zzho;
        private boolean zzhq;
        private List<String> zzhp = new ArrayList();
        private LaunchOptions zzdd = new LaunchOptions();
        private boolean zzhr = true;
        private CastMediaOptions zzhs = new CastMediaOptions.Builder().build();
        private boolean zzht = true;
        private double zzhu = 0.05000000074505806d;
        private boolean zzhw = false;

        public final Builder setReceiverApplicationId(String str) {
            this.zzho = str;
            return this;
        }

        public final Builder setSupportedNamespaces(List<String> list) {
            this.zzhp = list;
            return this;
        }

        public final Builder setStopReceiverApplicationWhenEndingSession(boolean z) {
            this.zzhq = z;
            return this;
        }

        public final Builder setLaunchOptions(LaunchOptions launchOptions) {
            this.zzdd = launchOptions;
            return this;
        }

        public final Builder setResumeSavedSession(boolean z) {
            this.zzhr = z;
            return this;
        }

        public final Builder setCastMediaOptions(CastMediaOptions castMediaOptions) {
            this.zzhs = castMediaOptions;
            return this;
        }

        public final Builder setEnableReconnectionService(boolean z) {
            this.zzht = z;
            return this;
        }

        public final Builder setVolumeDeltaBeforeIceCreamSandwich(double d) throws IllegalArgumentException {
            if (d <= 0.0d || d > 0.5d) {
                throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
            }
            this.zzhu = d;
            return this;
        }

        public final CastOptions build() {
            return new CastOptions(this.zzho, this.zzhp, this.zzhq, this.zzdd, this.zzhr, this.zzhs, this.zzht, this.zzhu, false);
        }
    }

    public String getReceiverApplicationId() {
        return this.zzho;
    }

    public final void setReceiverApplicationId(String str) {
        this.zzho = str;
    }

    public List<String> getSupportedNamespaces() {
        return DesugarCollections.unmodifiableList(this.zzhp);
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzhq;
    }

    public LaunchOptions getLaunchOptions() {
        return this.zzdd;
    }

    public boolean getResumeSavedSession() {
        return this.zzhr;
    }

    public CastMediaOptions getCastMediaOptions() {
        return this.zzhs;
    }

    public boolean getEnableReconnectionService() {
        return this.zzht;
    }

    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzhu;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getReceiverApplicationId(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedNamespaces(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, getStopReceiverApplicationWhenEndingSession());
        SafeParcelWriter.writeParcelable(parcel, 5, getLaunchOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, getResumeSavedSession());
        SafeParcelWriter.writeParcelable(parcel, 7, getCastMediaOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 8, getEnableReconnectionService());
        SafeParcelWriter.writeDouble(parcel, 9, getVolumeDeltaBeforeIceCreamSandwich());
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzhv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
