package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public class CastMediaOptions extends AbstractSafeParcelable {
    private final String zzlk;
    private final String zzll;
    private final zzb zzlm;
    private final NotificationOptions zzln;
    private final boolean zzlo;
    private static final zzdw zzbf = new zzdw("CastMediaOptions");
    public static final Parcelable.Creator<CastMediaOptions> CREATOR = new zza();

    CastMediaOptions(String str, String str2, IBinder iBinder, NotificationOptions notificationOptions, boolean z) {
        zzb zzcVar;
        this.zzlk = str;
        this.zzll = str2;
        if (iBinder == null) {
            zzcVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker");
            zzcVar = iInterfaceQueryLocalInterface instanceof zzb ? (zzb) iInterfaceQueryLocalInterface : new zzc(iBinder);
        }
        this.zzlm = zzcVar;
        this.zzln = notificationOptions;
        this.zzlo = z;
    }

    public static final class Builder {
        private String zzll;
        private ImagePicker zzlp;
        private String zzlk = MediaIntentReceiver.class.getName();
        private NotificationOptions zzln = new NotificationOptions.Builder().build();

        public final Builder setMediaIntentReceiverClassName(String str) {
            this.zzlk = str;
            return this;
        }

        public final Builder setExpandedControllerActivityClassName(String str) {
            this.zzll = str;
            return this;
        }

        public final Builder setImagePicker(ImagePicker imagePicker) {
            this.zzlp = imagePicker;
            return this;
        }

        public final Builder setNotificationOptions(NotificationOptions notificationOptions) {
            this.zzln = notificationOptions;
            return this;
        }

        public final CastMediaOptions build() {
            ImagePicker imagePicker = this.zzlp;
            return new CastMediaOptions(this.zzlk, this.zzll, imagePicker == null ? null : imagePicker.zzbj().asBinder(), this.zzln, false);
        }
    }

    public String getMediaIntentReceiverClassName() {
        return this.zzlk;
    }

    public NotificationOptions getNotificationOptions() {
        return this.zzln;
    }

    public final boolean zzbh() {
        return this.zzlo;
    }

    public String getExpandedControllerActivityClassName() {
        return this.zzll;
    }

    public ImagePicker getImagePicker() {
        zzb zzbVar = this.zzlm;
        if (zzbVar == null) {
            return null;
        }
        try {
            return (ImagePicker) ObjectWrapper.unwrap(zzbVar.zzbi());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getWrappedClientObject", "zzb");
            return null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getMediaIntentReceiverClassName(), false);
        SafeParcelWriter.writeString(parcel, 3, getExpandedControllerActivityClassName(), false);
        zzb zzbVar = this.zzlm;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbVar == null ? null : zzbVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getNotificationOptions(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzlo);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
