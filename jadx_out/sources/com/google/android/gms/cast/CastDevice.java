package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import j$.util.DesugarCollections;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzn();
    private int status;
    private String zzan;
    private String zzao;
    private InetAddress zzap;
    private String zzaq;
    private String zzar;
    private String zzas;
    private int zzat;
    private List<WebImage> zzau;
    private int zzav;
    private String zzaw;
    private String zzax;
    private int zzay;
    private String zzaz;
    private byte[] zzba;
    private String zzbb;

    CastDevice(String str, String str2, String str3, String str4, String str5, int i, List<WebImage> list, int i2, int i3, String str6, String str7, int i4, String str8, byte[] bArr, String str9) {
        this.zzan = zza(str);
        String strZza = zza(str2);
        this.zzao = strZza;
        if (!TextUtils.isEmpty(strZza)) {
            try {
                this.zzap = InetAddress.getByName(this.zzao);
            } catch (UnknownHostException e) {
                String str10 = this.zzao;
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(str10).length() + 48 + String.valueOf(message).length());
                sb.append("Unable to convert host address (");
                sb.append(str10);
                sb.append(") to ipaddress: ");
                sb.append(message);
                Log.i("CastDevice", sb.toString());
            }
        }
        this.zzaq = zza(str3);
        this.zzar = zza(str4);
        this.zzas = zza(str5);
        this.zzat = i;
        this.zzau = list != null ? list : new ArrayList<>();
        this.zzav = i2;
        this.status = i3;
        this.zzaw = zza(str6);
        this.zzax = str7;
        this.zzay = i4;
        this.zzaz = str8;
        this.zzba = bArr;
        this.zzbb = str9;
    }

    public String getDeviceId() {
        if (this.zzan.startsWith("__cast_nearby__")) {
            return this.zzan.substring(16);
        }
        return this.zzan;
    }

    public boolean hasIPv4Address() {
        return getInetAddress() != null && (getInetAddress() instanceof Inet4Address);
    }

    public boolean hasIPv6Address() {
        return getInetAddress() != null && (getInetAddress() instanceof Inet6Address);
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        if (hasIPv4Address()) {
            return (Inet4Address) this.zzap;
        }
        return null;
    }

    public InetAddress getInetAddress() {
        return this.zzap;
    }

    public String getFriendlyName() {
        return this.zzaq;
    }

    public String getModelName() {
        return this.zzar;
    }

    public String getDeviceVersion() {
        return this.zzas;
    }

    public int getServicePort() {
        return this.zzat;
    }

    public List<WebImage> getIcons() {
        return DesugarCollections.unmodifiableList(this.zzau);
    }

    public WebImage getIcon(int i, int i2) {
        WebImage webImage = null;
        if (this.zzau.isEmpty()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return this.zzau.get(0);
        }
        WebImage webImage2 = null;
        for (WebImage webImage3 : this.zzau) {
            int width = webImage3.getWidth();
            int height = webImage3.getHeight();
            if (width >= i && height >= i2) {
                if (webImage == null || (webImage.getWidth() > width && webImage.getHeight() > height)) {
                    webImage = webImage3;
                }
            } else if (width < i && height < i2 && (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height))) {
                webImage2 = webImage3;
            }
        }
        if (webImage != null) {
            return webImage;
        }
        return webImage2 != null ? webImage2 : this.zzau.get(0);
    }

    public boolean hasIcons() {
        return !this.zzau.isEmpty();
    }

    public boolean hasCapability(int i) {
        return (this.zzav & i) == i;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (!hasCapability(i)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return String.format("\"%s\" (%s)", this.zzaq, this.zzan);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(parcel, 5, getModelName(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(parcel, 7, getServicePort());
        SafeParcelWriter.writeTypedList(parcel, 8, getIcons(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzav);
        SafeParcelWriter.writeInt(parcel, 10, this.status);
        SafeParcelWriter.writeString(parcel, 11, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzax, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzay);
        SafeParcelWriter.writeString(parcel, 14, this.zzaz, false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.zzba, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzbb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public boolean equals(Object obj) {
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.zzan;
        if (str == null) {
            return castDevice.zzan == null;
        }
        return zzdk.zza(str, castDevice.zzan) && zzdk.zza(this.zzap, castDevice.zzap) && zzdk.zza(this.zzar, castDevice.zzar) && zzdk.zza(this.zzaq, castDevice.zzaq) && zzdk.zza(this.zzas, castDevice.zzas) && this.zzat == castDevice.zzat && zzdk.zza(this.zzau, castDevice.zzau) && this.zzav == castDevice.zzav && this.status == castDevice.status && zzdk.zza(this.zzaw, castDevice.zzaw) && zzdk.zza(Integer.valueOf(this.zzay), Integer.valueOf(castDevice.zzay)) && zzdk.zza(this.zzaz, castDevice.zzaz) && zzdk.zza(this.zzax, castDevice.zzax) && zzdk.zza(this.zzas, castDevice.getDeviceVersion()) && this.zzat == castDevice.getServicePort() && (((bArr = this.zzba) == null && castDevice.zzba == null) || Arrays.equals(bArr, castDevice.zzba)) && zzdk.zza(this.zzbb, castDevice.zzbb);
    }

    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            return zzdk.zza(getDeviceId(), castDevice.getDeviceId());
        }
        if (TextUtils.isEmpty(this.zzaz) || TextUtils.isEmpty(castDevice.zzaz)) {
            return false;
        }
        return zzdk.zza(this.zzaz, castDevice.zzaz);
    }

    public int hashCode() {
        String str = this.zzan;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public void putInBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    public boolean isOnLocalNetwork() {
        return !this.zzan.startsWith("__cast_nearby__");
    }

    private static String zza(String str) {
        return str == null ? "" : str;
    }
}
