package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzix;
import com.google.android.gms.internal.measurement.zzja;
import com.google.android.gms.internal.measurement.zzjb;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzhe;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-api@@21.3.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzc {
    public static final /* synthetic */ int zza = 0;
    private static final zzjb zzb = zzjb.zzi("_in", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", FirebaseAnalytics.Event.CAMPAIGN_DETAILS, "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire");
    private static final zzja zzc = zzja.zzj("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd");
    private static final zzja zzd = zzja.zzi("auto", ApiV2RequestInterceptor.APP_MODE, "am");
    private static final zzja zze = zzja.zzh("_r", "_dbg");
    private static final zzja zzf;
    private static final zzja zzg;

    static {
        zzix zzixVar = new zzix();
        zzixVar.zza(zzhe.zza);
        zzixVar.zza(zzhe.zzb);
        zzf = zzixVar.zzb();
        zzg = zzja.zzh("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0060  */
    public static boolean zza(String str, String str2, Bundle bundle) {
        byte b;
        if (!"_cmp".equals(str2)) {
            return true;
        }
        if (!zzd(str) || bundle == null) {
            return false;
        }
        zzja zzjaVar = zze;
        int size = zzjaVar.size();
        int i = 0;
        while (i < size) {
            boolean zContainsKey = bundle.containsKey((String) zzjaVar.get(i));
            i++;
            if (zContainsKey) {
                return false;
            }
        }
        int iHashCode = str.hashCode();
        if (iHashCode != 101200) {
            if (iHashCode != 101230) {
                if (iHashCode == 3142703 && str.equals(AppMeasurement.FIAM_ORIGIN)) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("fdl")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (str.equals(AppMeasurement.FCM_ORIGIN)) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0) {
            bundle.putString("_cis", "fcm_integration");
            return true;
        }
        if (b == 1) {
            bundle.putString("_cis", "fdl_integration");
            return true;
        }
        if (b != 2) {
            return false;
        }
        bundle.putString("_cis", "fiam_integration");
        return true;
    }

    public static boolean zzb(String str, Bundle bundle) {
        if (zzc.contains(str)) {
            return false;
        }
        if (bundle == null) {
            return true;
        }
        zzja zzjaVar = zze;
        int size = zzjaVar.size();
        int i = 0;
        while (i < size) {
            boolean zContainsKey = bundle.containsKey((String) zzjaVar.get(i));
            i++;
            if (zContainsKey) {
                return false;
            }
        }
        return true;
    }

    public static boolean zzc(String str) {
        return !zzb.contains(str);
    }

    public static boolean zzd(String str) {
        return !zzd.contains(str);
    }

    public static boolean zze(String str, String str2) {
        if ("_ce1".equals(str2) || "_ce2".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals("frc");
        }
        if ("_ln".equals(str2)) {
            return str.equals(AppMeasurement.FCM_ORIGIN) || str.equals(AppMeasurement.FIAM_ORIGIN);
        }
        if (zzf.contains(str2)) {
            return false;
        }
        zzja zzjaVar = zzg;
        int size = zzjaVar.size();
        int i = 0;
        while (i < size) {
            boolean zMatches = str2.matches((String) zzjaVar.get(i));
            i++;
            if (zMatches) {
                return false;
            }
        }
        return true;
    }
}
