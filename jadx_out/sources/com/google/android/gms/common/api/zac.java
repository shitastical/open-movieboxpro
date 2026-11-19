package com.google.android.gms.common.api;

import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class zac {
    private static final Map<Object, zac> zacm = new WeakHashMap();
    private static final Object sLock = new Object();

    public abstract void remove(int i);
}
