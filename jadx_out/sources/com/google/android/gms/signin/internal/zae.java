package com.google.android.gms.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public interface zae extends IInterface {
    void zaa(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException;

    void zaa(zai zaiVar, zac zacVar) throws RemoteException;

    void zam(int i) throws RemoteException;
}
