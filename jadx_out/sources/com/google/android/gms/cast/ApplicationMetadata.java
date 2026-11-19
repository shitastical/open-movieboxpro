package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zzd();
    private String name;
    private String zzaa;
    private Uri zzab;

    @Nullable
    private String zzac;
    private String zzy;
    private List<String> zzz;

    ApplicationMetadata(String str, String str2, @Nullable List<WebImage> list, List<String> list2, String str3, Uri uri, @Nullable String str4) {
        this.zzy = str;
        this.name = str2;
        this.zzz = list2;
        this.zzaa = str3;
        this.zzab = uri;
        this.zzac = str4;
    }

    public List<WebImage> getImages() {
        return null;
    }

    private ApplicationMetadata() {
        this.zzz = new ArrayList();
    }

    public String getApplicationId() {
        return this.zzy;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNamespaceSupported(String str) {
        List<String> list = this.zzz;
        return list != null && list.contains(str);
    }

    public List<String> getSupportedNamespaces() {
        return DesugarCollections.unmodifiableList(this.zzz);
    }

    public boolean areNamespacesSupported(List<String> list) {
        List<String> list2 = this.zzz;
        return list2 != null && list2.containsAll(list);
    }

    public String getSenderAppIdentifier() {
        return this.zzaa;
    }

    public String toString() {
        String str = this.zzy;
        String str2 = this.name;
        List<String> list = this.zzz;
        int size = list == null ? 0 : list.size();
        String str3 = this.zzaa;
        String strValueOf = String.valueOf(this.zzab);
        String str4 = this.zzac;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + IjkMediaMeta.FF_PROFILE_H264_HIGH_10 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(strValueOf).length() + String.valueOf(str4).length());
        sb.append("applicationId: ");
        sb.append(str);
        sb.append(", name: ");
        sb.append(str2);
        sb.append(", namespaces.count: ");
        sb.append(size);
        sb.append(", senderAppIdentifier: ");
        sb.append(str3);
        sb.append(", senderAppLaunchUrl: ");
        sb.append(strValueOf);
        sb.append(", iconUrl: ");
        sb.append(str4);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getApplicationId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getImages(), false);
        SafeParcelWriter.writeStringList(parcel, 5, getSupportedNamespaces(), false);
        SafeParcelWriter.writeString(parcel, 6, getSenderAppIdentifier(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzab, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzac, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzy, this.name, this.zzz, this.zzaa, this.zzab, this.zzac);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return zzdk.zza(this.zzy, applicationMetadata.zzy) && zzdk.zza(this.name, applicationMetadata.name) && zzdk.zza(this.zzz, applicationMetadata.zzz) && zzdk.zza(this.zzaa, applicationMetadata.zzaa) && zzdk.zza(this.zzab, applicationMetadata.zzab) && zzdk.zza(this.zzac, applicationMetadata.zzac);
    }
}
