package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzmo {
    private static final zzmn zza;
    private static final zzmn zzb;

    static {
        zzmn zzmnVar = null;
        try {
            zzmnVar = (zzmn) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzmnVar;
        zzb = new zzmn();
    }

    static zzmn zza() {
        return zza;
    }

    static zzmn zzb() {
        return zzb;
    }
}
