package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzny extends IllegalArgumentException {
    zzny(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
