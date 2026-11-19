package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface zzd extends IInterface {
    int[] getCompactViewActionIndices() throws RemoteException;

    List<NotificationAction> getNotificationActions() throws RemoteException;

    IObjectWrapper zzbi() throws RemoteException;

    int zzs() throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzd {
        public zza() {
            super("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
        }

        @Override // com.google.android.gms.internal.cast.zzb
        protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                int iZzs = zzs();
                parcel2.writeNoException();
                parcel2.writeInt(iZzs);
            } else if (i == 2) {
                IObjectWrapper iObjectWrapperZzbi = zzbi();
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zza(parcel2, iObjectWrapperZzbi);
            } else if (i == 3) {
                List<NotificationAction> notificationActions = getNotificationActions();
                parcel2.writeNoException();
                parcel2.writeTypedList(notificationActions);
            } else {
                if (i != 4) {
                    return false;
                }
                int[] compactViewActionIndices = getCompactViewActionIndices();
                parcel2.writeNoException();
                parcel2.writeIntArray(compactViewActionIndices);
            }
            return true;
        }
    }
}
