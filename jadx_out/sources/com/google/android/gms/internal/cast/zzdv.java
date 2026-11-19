package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdv {
    public static final Api.ClientKey<zzdd> zzzf = new Api.ClientKey<>();
    public static final Api.ClientKey<zzer> zzzg = new Api.ClientKey<>();
    public static final Api.ClientKey<zzeq> zzzh = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzzi = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzzj = new Api.ClientKey<>();
    private static final Charset zzzk;
    private static final String zzzl;
    private static boolean zzzm;

    static {
        Charset charsetForName;
        try {
            charsetForName = Charset.forName("UTF-8");
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            charsetForName = null;
        }
        zzzk = charsetForName;
        zzzl = zzdk.zzq("com.google.cast.multizone");
        zzzm = false;
    }
}
