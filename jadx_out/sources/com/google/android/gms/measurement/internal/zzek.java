package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrj;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zzek extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    zzek(zzgd zzgdVar, long j) {
        super(zzgdVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:34:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:35:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:36:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:37:0x0100  */
    /* JADX WARN: Code duplicated, block: B:38:0x0110  */
    /* JADX WARN: Code duplicated, block: B:39:0x0120  */
    /* JADX WARN: Code duplicated, block: B:40:0x0130  */
    /* JADX WARN: Code duplicated, block: B:41:0x0140  */
    /* JADX WARN: Code duplicated, block: B:42:0x0150  */
    /* JADX WARN: Code duplicated, block: B:45:0x016a  */
    /* JADX WARN: Code duplicated, block: B:48:0x018a  */
    /* JADX WARN: Code duplicated, block: B:49:0x018b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0194 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:55:0x01ae A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x01bc A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x01d2 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x01d5 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0206  */
    /* JADX WARN: Code duplicated, block: B:71:0x020c  */
    /* JADX WARN: Code duplicated, block: B:72:0x021c  */
    /* JADX WARN: Code duplicated, block: B:75:0x0226  */
    /* JADX WARN: Code duplicated, block: B:78:0x023b  */
    /* JADX WARN: Code duplicated, block: B:80:0x023f  */
    /* JADX WARN: Code duplicated, block: B:82:0x024c  */
    /* JADX WARN: Code duplicated, block: B:92:0x023b A[SYNTHETIC] */
    @Override // com.google.android.gms.measurement.internal.zzf
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    protected final void zzd() {
        String str;
        String string;
        boolean z;
        int iZza;
        List listZzp;
        Iterator it;
        String strZzc;
        String str2;
        Context contextZzaw;
        String strZzz;
        String packageName = this.zzt.zzaw().getPackageName();
        PackageManager packageManager = this.zzt.zzaw().getPackageManager();
        int i = Integer.MIN_VALUE;
        String str3 = "";
        String str4 = "Unknown";
        String installerPackageName = "unknown";
        try {
            if (packageManager != null) {
                try {
                    installerPackageName = packageManager.getInstallerPackageName(packageName);
                } catch (IllegalArgumentException unused) {
                    this.zzt.zzaA().zzd().zzb("Error retrieving app installer package name. appId", zzet.zzn(packageName));
                }
                if (installerPackageName == null) {
                    installerPackageName = "manual_install";
                } else if ("com.android.vending".equals(installerPackageName)) {
                    installerPackageName = "";
                }
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(this.zzt.zzaw().getPackageName(), 0);
                    if (packageInfo != null) {
                        CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                        string = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                        try {
                            str4 = packageInfo.versionName;
                            i = packageInfo.versionCode;
                        } catch (PackageManager.NameNotFoundException unused2) {
                            str = str4;
                            str4 = string;
                            this.zzt.zzaA().zzd().zzc("Error retrieving package info. appId, appName", zzet.zzn(packageName), str4);
                            string = str4;
                            str4 = str;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused3) {
                    str = "Unknown";
                }
                this.zza = packageName;
                this.zzd = installerPackageName;
                this.zzb = str4;
                this.zzc = i;
                this.zze = string;
                this.zzf = 0L;
                if (TextUtils.isEmpty(this.zzt.zzw()) && "am".equals(this.zzt.zzx())) {
                    z = true;
                } else {
                    z = false;
                }
                iZza = this.zzt.zza();
                switch (iZza) {
                    case 0:
                        this.zzt.zzaA().zzj().zza("App measurement collection enabled");
                        break;
                    case 1:
                        this.zzt.zzaA().zzi().zza("App measurement deactivated via the manifest");
                        break;
                    case 2:
                        this.zzt.zzaA().zzj().zza("App measurement deactivated via the init parameters");
                        break;
                    case 3:
                        this.zzt.zzaA().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                        break;
                    case 4:
                        this.zzt.zzaA().zzi().zza("App measurement disabled via the manifest");
                        break;
                    case 5:
                        this.zzt.zzaA().zzj().zza("App measurement disabled via the init parameters");
                        break;
                    case 6:
                        this.zzt.zzaA().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                        break;
                    case 7:
                        this.zzt.zzaA().zzi().zza("App measurement disabled via the global data collection setting");
                        break;
                    default:
                        this.zzt.zzaA().zzi().zza("App measurement disabled due to denied storage consent");
                        break;
                }
                this.zzk = "";
                this.zzl = "";
                this.zzt.zzay();
                if (z) {
                    this.zzl = this.zzt.zzw();
                }
                strZzc = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
                if (TextUtils.isEmpty(strZzc)) {
                    str3 = strZzc;
                }
                this.zzk = str3;
                if (!TextUtils.isEmpty(strZzc)) {
                    contextZzaw = this.zzt.zzaw();
                    strZzz = this.zzt.zzz();
                    Preconditions.checkNotNull(contextZzaw);
                    Resources resources = contextZzaw.getResources();
                    if (!TextUtils.isEmpty(strZzz)) {
                        strZzz = zzfv.zza(contextZzaw);
                    }
                    this.zzl = zzfv.zzb("admob_app_id", resources, strZzz);
                }
                if (iZza == 0) {
                    zzer zzerVarZzj = this.zzt.zzaA().zzj();
                    String str5 = this.zza;
                    if (TextUtils.isEmpty(this.zzk)) {
                        str2 = this.zzl;
                    } else {
                        str2 = this.zzk;
                    }
                    zzerVarZzj.zzc("App measurement enabled for app package, google app id", str5, str2);
                }
                this.zzh = null;
                this.zzt.zzay();
                listZzp = this.zzt.zzf().zzp("analytics.safelisted_events");
                if (listZzp != null) {
                    this.zzh = listZzp;
                } else if (listZzp.isEmpty()) {
                    this.zzt.zzaA().zzl().zza("Safelisted event list is empty. Ignoring");
                } else {
                    it = listZzp.iterator();
                    do {
                        if (it.hasNext()) {
                            this.zzh = listZzp;
                        }
                    } while (this.zzt.zzv().zzac("safelisted event", (String) it.next()));
                }
                if (packageManager != null) {
                    this.zzj = InstantApps.isInstantApp(this.zzt.zzaw()) ? 1 : 0;
                } else {
                    this.zzj = 0;
                }
            }
            this.zzt.zzaA().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzet.zzn(packageName));
            strZzc = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
            if (TextUtils.isEmpty(strZzc)) {
                str3 = strZzc;
            }
            this.zzk = str3;
            if (!TextUtils.isEmpty(strZzc)) {
                contextZzaw = this.zzt.zzaw();
                strZzz = this.zzt.zzz();
                Preconditions.checkNotNull(contextZzaw);
                Resources resources2 = contextZzaw.getResources();
                if (!TextUtils.isEmpty(strZzz)) {
                    strZzz = zzfv.zza(contextZzaw);
                }
                this.zzl = zzfv.zzb("admob_app_id", resources2, strZzz);
            }
            if (iZza == 0) {
                zzer zzerVarZzj2 = this.zzt.zzaA().zzj();
                String str6 = this.zza;
                if (TextUtils.isEmpty(this.zzk)) {
                    str2 = this.zzl;
                } else {
                    str2 = this.zzk;
                }
                zzerVarZzj2.zzc("App measurement enabled for app package, google app id", str6, str2);
            }
        } catch (IllegalStateException e) {
            this.zzt.zzaA().zzd().zzc("Fetching Google App Id failed with exception. appId", zzet.zzn(packageName), e);
        }
        string = "Unknown";
        this.zza = packageName;
        this.zzd = installerPackageName;
        this.zzb = str4;
        this.zzc = i;
        this.zze = string;
        this.zzf = 0L;
        if (TextUtils.isEmpty(this.zzt.zzw())) {
            z = false;
        } else {
            z = false;
        }
        iZza = this.zzt.zza();
        switch (iZza) {
            case 0:
                this.zzt.zzaA().zzj().zza("App measurement collection enabled");
                break;
            case 1:
                this.zzt.zzaA().zzi().zza("App measurement deactivated via the manifest");
                break;
            case 2:
                this.zzt.zzaA().zzj().zza("App measurement deactivated via the init parameters");
                break;
            case 3:
                this.zzt.zzaA().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                break;
            case 4:
                this.zzt.zzaA().zzi().zza("App measurement disabled via the manifest");
                break;
            case 5:
                this.zzt.zzaA().zzj().zza("App measurement disabled via the init parameters");
                break;
            case 6:
                this.zzt.zzaA().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                break;
            case 7:
                this.zzt.zzaA().zzi().zza("App measurement disabled via the global data collection setting");
                break;
            default:
                this.zzt.zzaA().zzi().zza("App measurement disabled due to denied storage consent");
                break;
        }
        this.zzk = "";
        this.zzl = "";
        this.zzt.zzay();
        if (z) {
            this.zzl = this.zzt.zzw();
        }
        this.zzh = null;
        this.zzt.zzay();
        listZzp = this.zzt.zzf().zzp("analytics.safelisted_events");
        if (listZzp != null) {
            this.zzh = listZzp;
        } else if (listZzp.isEmpty()) {
            this.zzt.zzaA().zzl().zza("Safelisted event list is empty. Ignoring");
        } else {
            it = listZzp.iterator();
            do {
                if (it.hasNext()) {
                    this.zzh = listZzp;
                }
            } while (this.zzt.zzv().zzac("safelisted event", (String) it.next()));
        }
        if (packageManager != null) {
            this.zzj = InstantApps.isInstantApp(this.zzt.zzaw()) ? 1 : 0;
        } else {
            this.zzj = 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    final int zzh() {
        zza();
        return this.zzj;
    }

    final int zzi() {
        zza();
        return this.zzc;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0196  */
    /* JADX WARN: Code duplicated, block: B:47:0x019c  */
    /* JADX WARN: Code duplicated, block: B:50:0x01db  */
    /* JADX WARN: Code duplicated, block: B:51:0x01de  */
    /* JADX WARN: Code duplicated, block: B:54:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:57:0x0223  */
    /* JADX WARN: Code duplicated, block: B:59:0x022e  */
    /* JADX WARN: Code duplicated, block: B:60:0x0231  */
    /* JADX WARN: Code duplicated, block: B:69:0x0256  */
    /* JADX WARN: Code duplicated, block: B:71:0x025e  */
    /* JADX WARN: Code duplicated, block: B:74:0x0274  */
    /* JADX WARN: Code duplicated, block: B:75:0x0277  */
    /* JADX WARN: Code duplicated, block: B:78:0x028f  */
    /* JADX WARN: Code duplicated, block: B:85:0x02b7 A[Catch: NameNotFoundException -> 0x02bb, TRY_LEAVE, TryCatch #6 {NameNotFoundException -> 0x02bb, blocks: (B:83:0x02b1, B:85:0x02b7), top: B:108:0x02b1 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:95:0x02a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v45, types: [com.google.android.gms.measurement.internal.zzgw, com.google.android.gms.measurement.internal.zzlp] */
    /* JADX WARN: Type inference failed for: r9v46, types: [com.google.android.gms.measurement.internal.zzgw] */
    /* JADX WARN: Type inference failed for: r9v50 */
    /* JADX WARN: Type inference failed for: r9v55 */
    /* JADX WARN: Type inference failed for: r9v56 */
    /* JADX WARN: Type inference failed for: r9v57 */
    final zzq zzj(String str) {
        boolean z;
        String str2;
        zzgd zzgdVar;
        long jZza;
        long jMin;
        Boolean boolZzk;
        Boolean boolValueOf;
        String str3;
        String str4;
        long j;
        String str5;
        Boolean boolZzk2;
        boolean zBooleanValue;
        zzlp zzlpVarZzv;
        String strZzl;
        int i;
        ApplicationInfo applicationInfo;
        long j2;
        zzg();
        String strZzl2 = zzl();
        String strZzm = zzm();
        zza();
        String str6 = this.zzb;
        zza();
        long j3 = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str7 = this.zzd;
        this.zzt.zzf().zzh();
        zza();
        zzg();
        long j4 = this.zzf;
        long j5 = j4;
        if (j4 == 0) {
            ?? Zzv = this.zzt.zzv();
            Context contextZzaw = this.zzt.zzaw();
            String packageName = this.zzt.zzaw().getPackageName();
            Zzv.zzg();
            Preconditions.checkNotNull(contextZzaw);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = contextZzaw.getPackageManager();
            MessageDigest messageDigestZzF = zzlp.zzF();
            long j6 = -1;
            j6 = -1;
            if (messageDigestZzF == null) {
                Zzv.zzt.zzaA().zzd().zza("Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (Zzv.zzah(contextZzaw, packageName)) {
                            j6 = 0;
                            Zzv = Zzv;
                        } else {
                            PackageInfo packageInfo = Wrappers.packageManager(contextZzaw).getPackageInfo(Zzv.zzt.zzaw().getPackageName(), 64);
                            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                                Zzv.zzt.zzaA().zzk().zza("Could not get signatures");
                                Zzv = Zzv;
                            } else {
                                long jZzp = zzlp.zzp(messageDigestZzF.digest(packageInfo.signatures[0].toByteArray()));
                                j6 = jZzp;
                                Zzv = jZzp;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Zzv.zzt.zzaA().zzd().zzb("Package name not found", e);
                        j2 = 0;
                    }
                }
                j2 = 0;
                this.zzf = j2;
                j5 = j2;
            }
            j2 = j6;
            this.zzf = j2;
            j5 = j2;
        }
        long j7 = j5;
        boolean zZzJ = this.zzt.zzJ();
        boolean z2 = !this.zzt.zzm().zzl;
        zzg();
        if (this.zzt.zzJ()) {
            zzrj.zzc();
            if (this.zzt.zzf().zzs(null, zzeg.zzac)) {
                this.zzt.zzaA().zzj().zza("Disabled IID for tests.");
            } else {
                try {
                    Class<?> clsLoadClass = this.zzt.zzaw().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                    if (clsLoadClass != null) {
                        try {
                            z = z2;
                            try {
                                Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzt.zzaw());
                                if (objInvoke == null) {
                                    str2 = null;
                                } else {
                                    try {
                                        str2 = (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", null).invoke(objInvoke, null);
                                    } catch (Exception unused) {
                                        this.zzt.zzaA().zzl().zza("Failed to retrieve Firebase Instance Id");
                                        str2 = null;
                                    }
                                }
                            } catch (Exception unused2) {
                                this.zzt.zzaA().zzm().zza("Failed to obtain Firebase Analytics instance");
                                str2 = null;
                                zzgdVar = this.zzt;
                                jZza = zzgdVar.zzm().zzc.zza();
                                if (jZza == 0) {
                                    jMin = zzgdVar.zzc;
                                } else {
                                    jMin = Math.min(zzgdVar.zzc, jZza);
                                }
                                zza();
                                int i2 = this.zzj;
                                boolean zZzr = this.zzt.zzf().zzr();
                                zzfi zzfiVarZzm = this.zzt.zzm();
                                zzfiVarZzm.zzg();
                                boolean z3 = zzfiVarZzm.zza().getBoolean("deferred_analytics_collection", false);
                                zza();
                                String str8 = this.zzl;
                                boolZzk = this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals");
                                if (boolZzk == null) {
                                    boolValueOf = null;
                                } else {
                                    boolValueOf = Boolean.valueOf(!boolZzk.booleanValue());
                                }
                                long j8 = this.zzg;
                                List list = this.zzh;
                                String strZzi = this.zzt.zzm().zzc().zzi();
                                if (this.zzi == null) {
                                    this.zzi = this.zzt.zzv().zzC();
                                }
                                str3 = this.zzi;
                                zzqu.zzc();
                                if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
                                    zzg();
                                    j = 0;
                                    if (this.zzn == 0) {
                                        str4 = str3;
                                    } else {
                                        str4 = str3;
                                        long jCurrentTimeMillis = this.zzt.zzax().currentTimeMillis() - this.zzn;
                                        if (this.zzm != null) {
                                            zzo();
                                        }
                                    }
                                    if (this.zzm == null) {
                                        zzo();
                                    }
                                    str5 = this.zzm;
                                } else {
                                    str4 = str3;
                                    j = 0;
                                    str5 = null;
                                }
                                zzag zzagVarZzf = this.zzt.zzf();
                                zzgd zzgdVar2 = zzagVarZzf.zzt;
                                boolZzk2 = zzagVarZzf.zzk("google_analytics_sgtm_upload_enabled");
                                if (boolZzk2 == null) {
                                    zBooleanValue = false;
                                } else {
                                    zBooleanValue = boolZzk2.booleanValue();
                                }
                                zzpz.zzc();
                                if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
                                    zzlpVarZzv = this.zzt.zzv();
                                    strZzl = zzl();
                                    if (zzlpVarZzv.zzt.zzaw().getPackageManager() != null) {
                                        try {
                                            try {
                                                applicationInfo = Wrappers.packageManager(zzlpVarZzv.zzt.zzaw()).getApplicationInfo(strZzl, 0);
                                                if (applicationInfo != null) {
                                                    i = applicationInfo.targetSdkVersion;
                                                } else {
                                                    i = 0;
                                                }
                                            } catch (PackageManager.NameNotFoundException unused3) {
                                                zzlpVarZzv.zzt.zzay();
                                                zzlpVarZzv.zzt.zzaA().zzi().zzb("PackageManager failed to find running app: app_id", strZzl);
                                            }
                                        } catch (PackageManager.NameNotFoundException unused4) {
                                        }
                                        j = i;
                                    }
                                }
                                return new zzq(strZzl2, strZzm, str6, j3, str7, 79000L, j7, str, zZzJ, z, str2, 0L, jMin, i2, zZzr, z3, str8, boolValueOf, j8, list, (String) null, strZzi, str4, str5, zBooleanValue, j);
                            }
                        } catch (Exception unused5) {
                            z = z2;
                        }
                    }
                } catch (ClassNotFoundException unused6) {
                    z = z2;
                }
            }
            str2 = null;
            z = z2;
        } else {
            str2 = null;
            z = z2;
        }
        zzgdVar = this.zzt;
        jZza = zzgdVar.zzm().zzc.zza();
        if (jZza == 0) {
            jMin = zzgdVar.zzc;
        } else {
            jMin = Math.min(zzgdVar.zzc, jZza);
        }
        zza();
        int i3 = this.zzj;
        boolean zZzr2 = this.zzt.zzf().zzr();
        zzfi zzfiVarZzm2 = this.zzt.zzm();
        zzfiVarZzm2.zzg();
        boolean z4 = zzfiVarZzm2.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str9 = this.zzl;
        boolZzk = this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals");
        if (boolZzk == null) {
            boolValueOf = null;
        } else {
            boolValueOf = Boolean.valueOf(!boolZzk.booleanValue());
        }
        long j9 = this.zzg;
        List list2 = this.zzh;
        String strZzi2 = this.zzt.zzm().zzc().zzi();
        if (this.zzi == null) {
            this.zzi = this.zzt.zzv().zzC();
        }
        str3 = this.zzi;
        zzqu.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
            zzg();
            j = 0;
            if (this.zzn == 0) {
                str4 = str3;
            } else {
                str4 = str3;
                long jCurrentTimeMillis2 = this.zzt.zzax().currentTimeMillis() - this.zzn;
                if (this.zzm != null && jCurrentTimeMillis2 > 86400000 && this.zzo == null) {
                    zzo();
                }
            }
            if (this.zzm == null) {
                zzo();
            }
            str5 = this.zzm;
        } else {
            str4 = str3;
            j = 0;
            str5 = null;
        }
        zzag zzagVarZzf2 = this.zzt.zzf();
        zzgd zzgdVar3 = zzagVarZzf2.zzt;
        boolZzk2 = zzagVarZzf2.zzk("google_analytics_sgtm_upload_enabled");
        if (boolZzk2 == null) {
            zBooleanValue = false;
        } else {
            zBooleanValue = boolZzk2.booleanValue();
        }
        zzpz.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
            zzlpVarZzv = this.zzt.zzv();
            strZzl = zzl();
            if (zzlpVarZzv.zzt.zzaw().getPackageManager() != null) {
                applicationInfo = Wrappers.packageManager(zzlpVarZzv.zzt.zzaw()).getApplicationInfo(strZzl, 0);
                if (applicationInfo != null) {
                    i = applicationInfo.targetSdkVersion;
                } else {
                    i = 0;
                }
                j = i;
            }
        }
        return new zzq(strZzl2, strZzm, str6, j3, str7, 79000L, j7, str, zZzJ, z, str2, 0L, jMin, i3, zZzr2, z4, str9, boolValueOf, j9, list2, (String) null, strZzi2, str4, str5, zBooleanValue, j);
    }

    final String zzk() {
        zza();
        return this.zzl;
    }

    final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    final List zzn() {
        return this.zzh;
    }

    final void zzo() {
        String str;
        zzg();
        if (this.zzt.zzm().zzc().zzj(zzha.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            this.zzt.zzv().zzG().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            this.zzt.zzaA().zzc().zza("Analytics Storage consent is not granted");
            str = null;
        }
        this.zzt.zzaA().zzc().zza(String.format("Resetting session stitching token to %s", str == null ? "null" : "not null"));
        this.zzm = str;
        this.zzn = this.zzt.zzax().currentTimeMillis();
    }

    final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
