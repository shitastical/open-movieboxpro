package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class LaunchOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new zzai();
    private boolean zzdb;
    private String zzdc;

    LaunchOptions(boolean z, String str) {
        this.zzdb = z;
        this.zzdc = str;
    }

    public static final class Builder {
        private LaunchOptions zzdd = new LaunchOptions();

        public final Builder setRelaunchIfRunning(boolean z) {
            this.zzdd.setRelaunchIfRunning(z);
            return this;
        }

        public final Builder setLocale(Locale locale) {
            this.zzdd.setLanguage(zzdk.zza(locale));
            return this;
        }

        public final LaunchOptions build() {
            return this.zzdd;
        }
    }

    public LaunchOptions() {
        this(false, zzdk.zza(Locale.getDefault()));
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zzdb = z;
    }

    public boolean getRelaunchIfRunning() {
        return this.zzdb;
    }

    public void setLanguage(String str) {
        this.zzdc = str;
    }

    public String getLanguage() {
        return this.zzdc;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", Boolean.valueOf(this.zzdb), this.zzdc);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(parcel, 3, getLanguage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.zzdb == launchOptions.zzdb && zzdk.zza(this.zzdc, launchOptions.zzdc);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzdb), this.zzdc);
    }
}
