package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpk;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpq;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import ly.count.android.sdk.Countly;
import org.joda.time.DateTimeConstants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zzlh implements zzgy {
    private static volatile zzlh zzb;
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zzir zzD;
    private String zzE;
    long zza;
    private final zzfu zzc;
    private final zzez zzd;
    private zzak zze;
    private zzfb zzf;
    private zzks zzg;
    private zzaa zzh;
    private final zzlj zzi;
    private zzip zzj;
    private zzkb zzk;
    private final zzkw zzl;
    private zzfl zzm;
    private final zzgd zzn;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;
    private boolean zzo = false;
    private final zzlo zzF = new zzlc(this);

    zzlh(zzli zzliVar, zzgd zzgdVar) {
        Preconditions.checkNotNull(zzliVar);
        this.zzn = zzgd.zzp(zzliVar.zza, null, null);
        this.zzA = -1L;
        this.zzl = new zzkw(this);
        zzlj zzljVar = new zzlj(this);
        zzljVar.zzX();
        this.zzi = zzljVar;
        zzez zzezVar = new zzez(this);
        zzezVar.zzX();
        this.zzd = zzezVar;
        zzfu zzfuVar = new zzfu(this);
        zzfuVar.zzX();
        this.zzc = zzfuVar;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        zzaB().zzp(new zzkx(this, zzliVar));
    }

    static final void zzaa(com.google.android.gms.internal.measurement.zzfs zzfsVar, int i, String str) {
        List listZzp = zzfsVar.zzp();
        for (int i2 = 0; i2 < listZzp.size(); i2++) {
            if ("_err".equals(((com.google.android.gms.internal.measurement.zzfx) listZzp.get(i2)).zzg())) {
                return;
            }
        }
        com.google.android.gms.internal.measurement.zzfw zzfwVarZze = com.google.android.gms.internal.measurement.zzfx.zze();
        zzfwVarZze.zzj("_err");
        long j = i;
        Long.valueOf(j).getClass();
        zzfwVarZze.zzi(j);
        com.google.android.gms.internal.measurement.zzfx zzfxVar = (com.google.android.gms.internal.measurement.zzfx) zzfwVarZze.zzaD();
        com.google.android.gms.internal.measurement.zzfw zzfwVarZze2 = com.google.android.gms.internal.measurement.zzfx.zze();
        zzfwVarZze2.zzj("_ev");
        zzfwVarZze2.zzk(str);
        com.google.android.gms.internal.measurement.zzfx zzfxVar2 = (com.google.android.gms.internal.measurement.zzfx) zzfwVarZze2.zzaD();
        zzfsVar.zzf(zzfxVar);
        zzfsVar.zzf(zzfxVar2);
    }

    static final void zzab(com.google.android.gms.internal.measurement.zzfs zzfsVar, String str) {
        List listZzp = zzfsVar.zzp();
        for (int i = 0; i < listZzp.size(); i++) {
            if (str.equals(((com.google.android.gms.internal.measurement.zzfx) listZzp.get(i)).zzg())) {
                zzfsVar.zzh(i);
                return;
            }
        }
    }

    private final zzq zzac(String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzhVarZzj = zzakVar.zzj(str);
        if (zzhVarZzj == null || TextUtils.isEmpty(zzhVarZzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping", str);
            return null;
        }
        Boolean boolZzad = zzad(zzhVarZzj);
        if (boolZzad != null && !boolZzad.booleanValue()) {
            zzaA().zzd().zzb("App version does not match; dropping. appId", zzet.zzn(str));
            return null;
        }
        String strZzA = zzhVarZzj.zzA();
        String strZzy = zzhVarZzj.zzy();
        long jZzb = zzhVarZzj.zzb();
        String strZzx = zzhVarZzj.zzx();
        long jZzm = zzhVarZzj.zzm();
        long jZzj = zzhVarZzj.zzj();
        boolean zZzan = zzhVarZzj.zzan();
        String strZzz = zzhVarZzj.zzz();
        zzhVarZzj.zza();
        return new zzq(str, strZzA, strZzy, jZzb, strZzx, jZzm, jZzj, (String) null, zZzan, false, strZzz, 0L, 0L, 0, zzhVarZzj.zzam(), false, zzhVarZzj.zzt(), zzhVarZzj.zzs(), zzhVarZzj.zzk(), zzhVarZzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzhVarZzj.zzap(), zzhVarZzj.zzr());
    }

    private final Boolean zzad(zzh zzhVar) {
        try {
            if (zzhVar.zzb() != -2147483648L) {
                if (zzhVar.zzb() == Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzhVar.zzv(), 0).versionName;
                String strZzy = zzhVar.zzy();
                if (strZzy != null && strZzy.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final void zzae() {
        zzaB().zzg();
        if (this.zzt || this.zzu || this.zzv) {
            zzaA().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
            return;
        }
        zzaA().zzj().zza("Stopping uploading service(s)");
        List list = this.zzq;
        if (list == null) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    private final void zzaf(com.google.android.gms.internal.measurement.zzgc zzgcVar, long j, boolean z) {
        zzlm zzlmVar;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = true != z ? "_lte" : "_se";
        zzlm zzlmVarZzp = zzakVar.zzp(zzgcVar.zzaq(), str);
        if (zzlmVarZzp == null || zzlmVarZzp.zze == null) {
            zzlmVar = new zzlm(zzgcVar.zzaq(), "auto", str, zzax().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzlmVar = new zzlm(zzgcVar.zzaq(), "auto", str, zzax().currentTimeMillis(), Long.valueOf(((Long) zzlmVarZzp.zze).longValue() + j));
        }
        com.google.android.gms.internal.measurement.zzgl zzglVarZzd = com.google.android.gms.internal.measurement.zzgm.zzd();
        zzglVarZzd.zzf(str);
        zzglVarZzd.zzg(zzax().currentTimeMillis());
        zzglVarZzd.zze(((Long) zzlmVar.zze).longValue());
        com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) zzglVarZzd.zzaD();
        int iZza = zzlj.zza(zzgcVar, str);
        if (iZza >= 0) {
            zzgcVar.zzan(iZza, zzgmVar);
        } else {
            zzgcVar.zzm(zzgmVar);
        }
        if (j > 0) {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzL(zzlmVar);
            zzaA().zzj().zzc("Updated engagement user property. scope, value", true != z ? "lifetime" : "session-scoped", zzlmVar.zze);
        }
    }

    private final void zzag() {
        long jMax;
        long jMax2;
        zzaB().zzg();
        zzB();
        if (this.zza > 0) {
            long jAbs = 3600000 - Math.abs(zzax().elapsedRealtime() - this.zza);
            if (jAbs > 0) {
                zzaA().zzj().zzb("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(jAbs));
                zzl().zzc();
                zzks zzksVar = this.zzg;
                zzal(zzksVar);
                zzksVar.zza();
                return;
            }
            this.zza = 0L;
        }
        if (!this.zzn.zzM() || !zzai()) {
            zzaA().zzj().zza("Nothing to upload or uploading impossible");
            zzl().zzc();
            zzks zzksVar2 = this.zzg;
            zzal(zzksVar2);
            zzksVar2.zza();
            return;
        }
        long jCurrentTimeMillis = zzax().currentTimeMillis();
        zzg();
        long jMax3 = Math.max(0L, ((Long) zzeg.zzA.zza(null)).longValue());
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        boolean z = true;
        if (!zzakVar.zzH()) {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            if (!zzakVar2.zzG()) {
                z = false;
            }
        }
        if (z) {
            String strZzl = zzg().zzl();
            if (TextUtils.isEmpty(strZzl) || ".none.".equals(strZzl)) {
                zzg();
                jMax = Math.max(0L, ((Long) zzeg.zzu.zza(null)).longValue());
            } else {
                zzg();
                jMax = Math.max(0L, ((Long) zzeg.zzv.zza(null)).longValue());
            }
        } else {
            zzg();
            jMax = Math.max(0L, ((Long) zzeg.zzt.zza(null)).longValue());
        }
        long jZza = this.zzk.zzc.zza();
        long jZza2 = this.zzk.zzd.zza();
        zzak zzakVar3 = this.zze;
        zzal(zzakVar3);
        boolean z2 = z;
        long jZzd = zzakVar3.zzd();
        zzak zzakVar4 = this.zze;
        zzal(zzakVar4);
        long jMax4 = Math.max(jZzd, zzakVar4.zze());
        if (jMax4 != 0) {
            long jAbs2 = jCurrentTimeMillis - Math.abs(jMax4 - jCurrentTimeMillis);
            long jAbs3 = jCurrentTimeMillis - Math.abs(jZza - jCurrentTimeMillis);
            long jAbs4 = jCurrentTimeMillis - Math.abs(jZza2 - jCurrentTimeMillis);
            jMax2 = jAbs2 + jMax3;
            long jMax5 = Math.max(jAbs3, jAbs4);
            if (z2 && jMax5 > 0) {
                jMax2 = Math.min(jAbs2, jMax5) + jMax;
            }
            zzlj zzljVar = this.zzi;
            zzal(zzljVar);
            if (!zzljVar.zzx(jMax5, jMax)) {
                jMax2 = jMax5 + jMax;
            }
            if (jAbs4 != 0 && jAbs4 >= jAbs2) {
                int i = 0;
                while (true) {
                    zzg();
                    if (i >= Math.min(20, Math.max(0, ((Integer) zzeg.zzC.zza(null)).intValue()))) {
                        jMax2 = 0;
                        break;
                    }
                    zzg();
                    jMax2 += Math.max(0L, ((Long) zzeg.zzB.zza(null)).longValue()) * (1 << i);
                    if (jMax2 > jAbs4) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            jMax2 = 0;
            break;
        }
        if (jMax2 == 0) {
            zzaA().zzj().zza("Next upload time is 0");
            zzl().zzc();
            zzks zzksVar3 = this.zzg;
            zzal(zzksVar3);
            zzksVar3.zza();
            return;
        }
        zzez zzezVar = this.zzd;
        zzal(zzezVar);
        if (!zzezVar.zza()) {
            zzaA().zzj().zza("No network");
            zzl().zzb();
            zzks zzksVar4 = this.zzg;
            zzal(zzksVar4);
            zzksVar4.zza();
            return;
        }
        long jZza3 = this.zzk.zzb.zza();
        zzg();
        long jMax6 = Math.max(0L, ((Long) zzeg.zzr.zza(null)).longValue());
        zzlj zzljVar2 = this.zzi;
        zzal(zzljVar2);
        if (!zzljVar2.zzx(jZza3, jMax6)) {
            jMax2 = Math.max(jMax2, jZza3 + jMax6);
        }
        zzl().zzc();
        long jCurrentTimeMillis2 = jMax2 - zzax().currentTimeMillis();
        if (jCurrentTimeMillis2 <= 0) {
            zzg();
            jCurrentTimeMillis2 = Math.max(0L, ((Long) zzeg.zzw.zza(null)).longValue());
            this.zzk.zzc.zzb(zzax().currentTimeMillis());
        }
        zzaA().zzj().zzb("Upload scheduled in approximately ms", Long.valueOf(jCurrentTimeMillis2));
        zzks zzksVar5 = this.zzg;
        zzal(zzksVar5);
        zzksVar5.zzd(jCurrentTimeMillis2);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:102:0x03a7 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:104:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:108:0x03be  */
    /* JADX WARN: Code duplicated, block: B:109:0x03c0 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:117:0x03f5 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x0406  */
    /* JADX WARN: Code duplicated, block: B:122:0x040d A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:124:0x0417 A[Catch: all -> 0x0cc9, LOOP:5: B:120:0x0407->B:124:0x0417, LOOP_END, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0436  */
    /* JADX WARN: Code duplicated, block: B:129:0x0443 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0456  */
    /* JADX WARN: Code duplicated, block: B:136:0x0475 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:139:0x0485 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:141:0x0491 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:148:0x04c3 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:150:0x04d1  */
    /* JADX WARN: Code duplicated, block: B:160:0x0523 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:162:0x052e A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:164:0x0534 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:165:0x053d  */
    /* JADX WARN: Code duplicated, block: B:267:0x08a0 A[PHI: r6
      0x08a0: PHI (r6v39 com.google.android.gms.measurement.internal.zzaq) = (r6v38 com.google.android.gms.measurement.internal.zzaq), (r6v47 com.google.android.gms.measurement.internal.zzaq) binds: [B:263:0x084a, B:265:0x085f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:353:0x0b37 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:420:0x03b8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:422:0x041d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:426:0x054d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x034e A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x0366 A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x037f A[Catch: all -> 0x0cc9, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0395 A[Catch: all -> 0x0cc9, TRY_ENTER, TryCatch #3 {all -> 0x0cc9, blocks: (B:3:0x000e, B:5:0x0026, B:8:0x002e, B:9:0x0040, B:12:0x0054, B:15:0x007b, B:17:0x00b1, B:20:0x00c3, B:22:0x00cd, B:152:0x04ea, B:24:0x00f7, B:26:0x0105, B:29:0x0125, B:31:0x012b, B:33:0x013d, B:35:0x014b, B:37:0x015b, B:38:0x0168, B:39:0x016d, B:42:0x0186, B:52:0x01bc, B:55:0x01c6, B:57:0x01d4, B:61:0x021b, B:58:0x01f1, B:60:0x0202, B:65:0x022a, B:67:0x0256, B:68:0x0280, B:70:0x02b6, B:72:0x02bc, B:75:0x02c8, B:77:0x02fe, B:78:0x0319, B:80:0x031f, B:82:0x032d, B:86:0x0340, B:83:0x0335, B:89:0x0347, B:92:0x034e, B:93:0x0366, B:95:0x037f, B:96:0x038b, B:99:0x0395, B:105:0x03b8, B:102:0x03a7, B:127:0x0437, B:129:0x0443, B:132:0x0458, B:134:0x0469, B:136:0x0475, B:151:0x04d6, B:139:0x0485, B:141:0x0491, B:144:0x04a6, B:146:0x04b7, B:148:0x04c3, B:109:0x03c0, B:111:0x03cc, B:113:0x03d8, B:125:0x041d, B:117:0x03f5, B:120:0x0407, B:122:0x040d, B:124:0x0417, B:155:0x0502, B:157:0x0510, B:159:0x051b, B:170:0x054d, B:160:0x0523, B:162:0x052e, B:164:0x0534, B:167:0x0540, B:169:0x0548, B:171:0x0550, B:172:0x055c, B:175:0x0564, B:177:0x0576, B:178:0x0582, B:180:0x058a, B:184:0x05af, B:186:0x05d4, B:188:0x05e5, B:190:0x05eb, B:192:0x05f7, B:193:0x0628, B:195:0x062e, B:197:0x063c, B:198:0x0640, B:199:0x0643, B:200:0x0646, B:201:0x0654, B:203:0x065a, B:205:0x066a, B:206:0x0671, B:208:0x067d, B:209:0x0684, B:210:0x0687, B:212:0x06c7, B:213:0x06da, B:215:0x06e0, B:218:0x06fa, B:220:0x0715, B:222:0x072e, B:224:0x0733, B:226:0x0737, B:228:0x073b, B:230:0x0745, B:231:0x074f, B:233:0x0753, B:235:0x0759, B:236:0x0767, B:237:0x0770, B:305:0x09c4, B:239:0x077d, B:241:0x0794, B:247:0x07b0, B:249:0x07d4, B:250:0x07dc, B:252:0x07e2, B:254:0x07f4, B:261:0x081d, B:262:0x0840, B:264:0x084c, B:266:0x0861, B:268:0x08a2, B:272:0x08ba, B:274:0x08c1, B:276:0x08cf, B:278:0x08d3, B:280:0x08d7, B:282:0x08db, B:283:0x08e7, B:284:0x08ec, B:286:0x08f2, B:288:0x090e, B:289:0x0913, B:304:0x09c1, B:290:0x0931, B:292:0x093c, B:296:0x0963, B:298:0x098e, B:299:0x0998, B:300:0x09a8, B:302:0x09af, B:293:0x0949, B:259:0x0808, B:245:0x079b, B:306:0x09cf, B:308:0x09dc, B:309:0x09e2, B:310:0x09ea, B:312:0x09f0, B:315:0x0a0a, B:317:0x0a1b, B:337:0x0a8f, B:339:0x0a95, B:341:0x0aad, B:344:0x0ab4, B:349:0x0ae3, B:351:0x0b25, B:354:0x0b5a, B:355:0x0b5e, B:356:0x0b69, B:358:0x0bac, B:359:0x0bb9, B:361:0x0bc8, B:365:0x0be2, B:367:0x0bfb, B:353:0x0b37, B:345:0x0abc, B:347:0x0ac8, B:348:0x0acc, B:368:0x0c13, B:369:0x0c2b, B:372:0x0c33, B:373:0x0c38, B:374:0x0c48, B:376:0x0c62, B:377:0x0c7d, B:378:0x0c86, B:383:0x0ca5, B:382:0x0c92, B:318:0x0a33, B:320:0x0a39, B:322:0x0a43, B:324:0x0a4a, B:330:0x0a5a, B:332:0x0a61, B:334:0x0a80, B:336:0x0a87, B:335:0x0a84, B:331:0x0a5e, B:323:0x0a47, B:181:0x058f, B:183:0x0595, B:386:0x0cb7), top: B:398:0x000e, inners: #0, #1, #2, #4 }] */
    private final boolean zzah(String str, long j) {
        int i;
        com.google.android.gms.internal.measurement.zzgc zzgcVar;
        zzle zzleVar;
        long j2;
        int iZzc;
        zzle zzleVar2;
        int i2;
        com.google.android.gms.internal.measurement.zzfx zzfxVarZzC;
        Long lValueOf;
        String str2;
        int i3;
        int i4;
        String str3;
        String str4;
        com.google.android.gms.internal.measurement.zzfs zzfsVar;
        com.google.android.gms.internal.measurement.zzgc zzgcVar2;
        com.google.android.gms.internal.measurement.zzfs zzfsVar2;
        int i5;
        com.google.android.gms.internal.measurement.zzfs zzfsVar3;
        ArrayList arrayList;
        int i6;
        int i7;
        int i8;
        String strZzh;
        int iCharCount;
        int iCodePointAt;
        com.google.android.gms.internal.measurement.zzgc zzgcVar3;
        String str5;
        String str6 = "_npa";
        String str7 = "_ai";
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzw();
        try {
            zzle zzleVar3 = new zzle(this, null);
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzakVar2.zzU(null, j, this.zzA, zzleVar3);
            List list = zzleVar3.zzc;
            if (list != null && !list.isEmpty()) {
                com.google.android.gms.internal.measurement.zzgc zzgcVar4 = (com.google.android.gms.internal.measurement.zzgc) zzleVar3.zza.zzbB();
                zzgcVar4.zzr();
                com.google.android.gms.internal.measurement.zzfs zzfsVar4 = null;
                com.google.android.gms.internal.measurement.zzfs zzfsVar5 = null;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = -1;
                int i13 = -1;
                while (true) {
                    String str8 = "_fr";
                    String str9 = "_et";
                    i = i11;
                    int i14 = i12;
                    com.google.android.gms.internal.measurement.zzfs zzfsVar6 = zzfsVar5;
                    if (i9 >= zzleVar3.zzc.size()) {
                        break;
                    }
                    com.google.android.gms.internal.measurement.zzfs zzfsVar7 = (com.google.android.gms.internal.measurement.zzfs) ((com.google.android.gms.internal.measurement.zzft) zzleVar3.zzc.get(i9)).zzbB();
                    zzfu zzfuVar = this.zzc;
                    zzal(zzfuVar);
                    String str10 = str6;
                    if (zzfuVar.zzr(zzleVar3.zza.zzy(), zzfsVar7.zzo())) {
                        zzaA().zzk().zzc("Dropping blocked raw event. appId", zzet.zzn(zzleVar3.zza.zzy()), this.zzn.zzj().zzd(zzfsVar7.zzo()));
                        zzfu zzfuVar2 = this.zzc;
                        zzal(zzfuVar2);
                        if (!zzfuVar2.zzp(zzleVar3.zza.zzy())) {
                            zzfu zzfuVar3 = this.zzc;
                            zzal(zzfuVar3);
                            if (!zzfuVar3.zzs(zzleVar3.zza.zzy()) && !"_err".equals(zzfsVar7.zzo())) {
                                zzv().zzO(this.zzF, zzleVar3.zza.zzy(), 11, "_ev", zzfsVar7.zzo(), 0);
                            }
                        }
                        str2 = str7;
                        i5 = i9;
                        zzfsVar = zzfsVar4;
                        i11 = i;
                        i12 = i14;
                        zzfsVar5 = zzfsVar6;
                        zzgcVar2 = zzgcVar4;
                    } else {
                        if (zzfsVar7.zzo().equals(zzhc.zza(str7))) {
                            zzfsVar7.zzi(str7);
                            zzaA().zzj().zza("Renaming ad_impression to _ai");
                            if (Log.isLoggable(zzaA().zzr(), 5)) {
                                int i15 = 0;
                                while (i15 < zzfsVar7.zza()) {
                                    String str11 = str7;
                                    if (FirebaseAnalytics.Param.AD_PLATFORM.equals(zzfsVar7.zzn(i15).zzg()) && !zzfsVar7.zzn(i15).zzh().isEmpty() && "admob".equalsIgnoreCase(zzfsVar7.zzn(i15).zzh())) {
                                        zzaA().zzl().zza("AdMob ad impression logged from app. Potentially duplicative.");
                                    }
                                    i15++;
                                    str7 = str11;
                                }
                            }
                        }
                        str2 = str7;
                        zzfu zzfuVar4 = this.zzc;
                        zzal(zzfuVar4);
                        boolean zZzq = zzfuVar4.zzq(zzleVar3.zza.zzy(), zzfsVar7.zzo());
                        if (zZzq) {
                            i3 = i9;
                            i4 = i10;
                        } else {
                            zzal(this.zzi);
                            String strZzo = zzfsVar7.zzo();
                            Preconditions.checkNotEmpty(strZzo);
                            i4 = i10;
                            i3 = i9;
                            if (strZzo.hashCode() != 95027 || !strZzo.equals("_ui")) {
                                str3 = "_fr";
                                str4 = "_et";
                                zzfsVar = zzfsVar4;
                                zZzq = false;
                                zzgcVar2 = zzgcVar4;
                            }
                            if (zZzq) {
                                arrayList = new ArrayList(zzfsVar7.zzp());
                                i7 = -1;
                                i8 = -1;
                                for (i6 = 0; i6 < arrayList.size(); i6++) {
                                    if ("value".equals(((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i6)).zzg())) {
                                        i7 = i6;
                                    } else if (FirebaseAnalytics.Param.CURRENCY.equals(((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i6)).zzg())) {
                                        i8 = i6;
                                    }
                                }
                                if (i7 != -1) {
                                    if (!((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i7)).zzw() || ((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i7)).zzu()) {
                                        if (i8 == -1) {
                                            strZzh = ((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i8)).zzh();
                                            if (strZzh.length() == 3) {
                                                iCharCount = 0;
                                                while (iCharCount < strZzh.length()) {
                                                    iCodePointAt = strZzh.codePointAt(iCharCount);
                                                    if (Character.isLetter(iCodePointAt)) {
                                                        iCharCount += Character.charCount(iCodePointAt);
                                                    }
                                                }
                                            }
                                        }
                                        zzaA().zzl().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                        zzfsVar7.zzh(i7);
                                        zzab(zzfsVar7, "_c");
                                        zzaa(zzfsVar7, 19, FirebaseAnalytics.Param.CURRENCY);
                                        break;
                                    }
                                    zzaA().zzl().zza("Value must be specified with a numeric type.");
                                    zzfsVar7.zzh(i7);
                                    zzab(zzfsVar7, "_c");
                                    zzaa(zzfsVar7, 18, "value");
                                }
                            }
                            if ("_e".equals(zzfsVar7.zzo())) {
                                zzal(this.zzi);
                                if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD(), str3) == null) {
                                    if (zzfsVar6 != null && Math.abs(zzfsVar6.zzc() - zzfsVar7.zzc()) <= 1000) {
                                        zzfsVar3 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar6.clone();
                                        if (zzaj(zzfsVar7, zzfsVar3)) {
                                            zzgcVar2.zzS(i13, zzfsVar3);
                                            i12 = i14;
                                            zzfsVar5 = null;
                                            zzfsVar = null;
                                        }
                                    }
                                    zzfsVar = zzfsVar7;
                                    zzfsVar5 = zzfsVar6;
                                    i12 = i4;
                                } else {
                                    i12 = i14;
                                    zzfsVar5 = zzfsVar6;
                                }
                            } else if ("_vs".equals(zzfsVar7.zzo())) {
                                zzal(this.zzi);
                                if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD(), str4) == null) {
                                    if (zzfsVar != null && Math.abs(zzfsVar.zzc() - zzfsVar7.zzc()) <= 1000) {
                                        zzfsVar2 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar.clone();
                                        if (zzaj(zzfsVar2, zzfsVar7)) {
                                            zzgcVar2.zzS(i14, zzfsVar2);
                                            i12 = i14;
                                            zzfsVar5 = null;
                                            zzfsVar = null;
                                        }
                                    }
                                    i12 = i14;
                                    zzfsVar5 = zzfsVar7;
                                    i13 = i4;
                                } else {
                                    i12 = i14;
                                    zzfsVar5 = zzfsVar6;
                                }
                            } else {
                                i12 = i14;
                                zzfsVar5 = zzfsVar6;
                            }
                            i5 = i3;
                            zzleVar3.zzc.set(i5, (com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD());
                            i10 = i4 + 1;
                            zzgcVar2.zzk(zzfsVar7);
                            i11 = i;
                        }
                        zzfsVar = zzfsVar4;
                        int i16 = 0;
                        boolean z = false;
                        boolean z2 = false;
                        while (true) {
                            str4 = str9;
                            if (i16 >= zzfsVar7.zza()) {
                                break;
                            }
                            if ("_c".equals(zzfsVar7.zzn(i16).zzg())) {
                                com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) zzfsVar7.zzn(i16).zzbB();
                                zzgcVar3 = zzgcVar4;
                                str5 = str8;
                                zzfwVar.zzi(1L);
                                zzfsVar7.zzk(i16, (com.google.android.gms.internal.measurement.zzfx) zzfwVar.zzaD());
                                z = true;
                            } else {
                                zzgcVar3 = zzgcVar4;
                                str5 = str8;
                                if ("_r".equals(zzfsVar7.zzn(i16).zzg())) {
                                    com.google.android.gms.internal.measurement.zzfw zzfwVar2 = (com.google.android.gms.internal.measurement.zzfw) zzfsVar7.zzn(i16).zzbB();
                                    zzfwVar2.zzi(1L);
                                    zzfsVar7.zzk(i16, (com.google.android.gms.internal.measurement.zzfx) zzfwVar2.zzaD());
                                    z2 = true;
                                }
                            }
                            i16++;
                            zzgcVar4 = zzgcVar3;
                            str9 = str4;
                            str8 = str5;
                        }
                        zzgcVar2 = zzgcVar4;
                        str3 = str8;
                        if (!z && zZzq) {
                            zzaA().zzj().zzb("Marking event as conversion", this.zzn.zzj().zzd(zzfsVar7.zzo()));
                            com.google.android.gms.internal.measurement.zzfw zzfwVarZze = com.google.android.gms.internal.measurement.zzfx.zze();
                            zzfwVarZze.zzj("_c");
                            zzfwVarZze.zzi(1L);
                            zzfsVar7.zze(zzfwVarZze);
                        }
                        if (!z2) {
                            zzaA().zzj().zzb("Marking event as real-time", this.zzn.zzj().zzd(zzfsVar7.zzo()));
                            com.google.android.gms.internal.measurement.zzfw zzfwVarZze2 = com.google.android.gms.internal.measurement.zzfx.zze();
                            zzfwVarZze2.zzj("_r");
                            zzfwVarZze2.zzi(1L);
                            zzfsVar7.zze(zzfwVarZze2);
                        }
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        if (zzakVar3.zzl(zza(), zzleVar3.zza.zzy(), false, false, false, false, true).zze > zzg().zze(zzleVar3.zza.zzy(), zzeg.zzo)) {
                            zzab(zzfsVar7, "_r");
                        } else {
                            i = 1;
                        }
                        if (zzlp.zzak(zzfsVar7.zzo()) && zZzq) {
                            zzak zzakVar4 = this.zze;
                            zzal(zzakVar4);
                            if (zzakVar4.zzl(zza(), zzleVar3.zza.zzy(), false, false, true, false, false).zzc > zzg().zze(zzleVar3.zza.zzy(), zzeg.zzn)) {
                                zzaA().zzk().zzb("Too many conversions. Not logging as conversion. appId", zzet.zzn(zzleVar3.zza.zzy()));
                                com.google.android.gms.internal.measurement.zzfw zzfwVar3 = null;
                                boolean z3 = false;
                                int i17 = -1;
                                for (int i18 = 0; i18 < zzfsVar7.zza(); i18++) {
                                    com.google.android.gms.internal.measurement.zzfx zzfxVarZzn = zzfsVar7.zzn(i18);
                                    if ("_c".equals(zzfxVarZzn.zzg())) {
                                        zzfwVar3 = (com.google.android.gms.internal.measurement.zzfw) zzfxVarZzn.zzbB();
                                        i17 = i18;
                                    } else if ("_err".equals(zzfxVarZzn.zzg())) {
                                        z3 = true;
                                    }
                                }
                                if (z3) {
                                    if (zzfwVar3 != null) {
                                        zzfsVar7.zzh(i17);
                                    } else {
                                        zzfwVar3 = null;
                                        if (zzfwVar3 != null) {
                                            com.google.android.gms.internal.measurement.zzfw zzfwVar4 = (com.google.android.gms.internal.measurement.zzfw) zzfwVar3.clone();
                                            zzfwVar4.zzj("_err");
                                            zzfwVar4.zzi(10L);
                                            zzfsVar7.zzk(i17, (com.google.android.gms.internal.measurement.zzfx) zzfwVar4.zzaD());
                                        } else {
                                            zzaA().zzd().zzb("Did not find conversion parameter. appId", zzet.zzn(zzleVar3.zza.zzy()));
                                        }
                                    }
                                } else if (zzfwVar3 != null) {
                                    com.google.android.gms.internal.measurement.zzfw zzfwVar5 = (com.google.android.gms.internal.measurement.zzfw) zzfwVar3.clone();
                                    zzfwVar5.zzj("_err");
                                    zzfwVar5.zzi(10L);
                                    zzfsVar7.zzk(i17, (com.google.android.gms.internal.measurement.zzfx) zzfwVar5.zzaD());
                                } else {
                                    zzaA().zzd().zzb("Did not find conversion parameter. appId", zzet.zzn(zzleVar3.zza.zzy()));
                                }
                            }
                        }
                        if (zZzq) {
                            arrayList = new ArrayList(zzfsVar7.zzp());
                            i7 = -1;
                            i8 = -1;
                            while (i6 < arrayList.size()) {
                                if ("value".equals(((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i6)).zzg())) {
                                    i7 = i6;
                                } else if (FirebaseAnalytics.Param.CURRENCY.equals(((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i6)).zzg())) {
                                    i8 = i6;
                                }
                            }
                            if (i7 != -1) {
                                if (((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i7)).zzw()) {
                                }
                                if (i8 == -1) {
                                    strZzh = ((com.google.android.gms.internal.measurement.zzfx) arrayList.get(i8)).zzh();
                                    if (strZzh.length() == 3) {
                                        iCharCount = 0;
                                        while (iCharCount < strZzh.length()) {
                                            iCodePointAt = strZzh.codePointAt(iCharCount);
                                            if (Character.isLetter(iCodePointAt)) {
                                                iCharCount += Character.charCount(iCodePointAt);
                                            }
                                        }
                                    }
                                }
                                zzaA().zzl().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
                                zzfsVar7.zzh(i7);
                                zzab(zzfsVar7, "_c");
                                zzaa(zzfsVar7, 19, FirebaseAnalytics.Param.CURRENCY);
                                break;
                            }
                        }
                        if ("_e".equals(zzfsVar7.zzo())) {
                            zzal(this.zzi);
                            if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD(), str3) == null) {
                                if (zzfsVar6 != null) {
                                    zzfsVar3 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar6.clone();
                                    if (zzaj(zzfsVar7, zzfsVar3)) {
                                        zzgcVar2.zzS(i13, zzfsVar3);
                                        i12 = i14;
                                        zzfsVar5 = null;
                                        zzfsVar = null;
                                    }
                                }
                                zzfsVar = zzfsVar7;
                                zzfsVar5 = zzfsVar6;
                                i12 = i4;
                            } else {
                                i12 = i14;
                                zzfsVar5 = zzfsVar6;
                            }
                        } else if ("_vs".equals(zzfsVar7.zzo())) {
                            zzal(this.zzi);
                            if (zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD(), str4) == null) {
                                if (zzfsVar != null) {
                                    zzfsVar2 = (com.google.android.gms.internal.measurement.zzfs) zzfsVar.clone();
                                    if (zzaj(zzfsVar2, zzfsVar7)) {
                                        zzgcVar2.zzS(i14, zzfsVar2);
                                        i12 = i14;
                                        zzfsVar5 = null;
                                        zzfsVar = null;
                                    }
                                }
                                i12 = i14;
                                zzfsVar5 = zzfsVar7;
                                i13 = i4;
                            } else {
                                i12 = i14;
                                zzfsVar5 = zzfsVar6;
                            }
                        } else {
                            i12 = i14;
                            zzfsVar5 = zzfsVar6;
                        }
                        i5 = i3;
                        zzleVar3.zzc.set(i5, (com.google.android.gms.internal.measurement.zzft) zzfsVar7.zzaD());
                        i10 = i4 + 1;
                        zzgcVar2.zzk(zzfsVar7);
                        i11 = i;
                    }
                    i9 = i5 + 1;
                    zzgcVar4 = zzgcVar2;
                    str6 = str10;
                    str7 = str2;
                    zzfsVar4 = zzfsVar;
                }
                String str12 = str6;
                com.google.android.gms.internal.measurement.zzgc zzgcVar5 = zzgcVar4;
                long jLongValue = 0;
                int i19 = 0;
                while (i19 < i10) {
                    com.google.android.gms.internal.measurement.zzft zzftVarZze = zzgcVar5.zze(i19);
                    if ("_e".equals(zzftVarZze.zzh())) {
                        zzal(this.zzi);
                        if (zzlj.zzC(zzftVarZze, "_fr") != null) {
                            zzgcVar5.zzA(i19);
                            i10--;
                            i19--;
                        } else {
                            zzal(this.zzi);
                            zzfxVarZzC = zzlj.zzC(zzftVarZze, "_et");
                            if (zzfxVarZzC == null) {
                                if (zzfxVarZzC.zzw()) {
                                    lValueOf = Long.valueOf(zzfxVarZzC.zzd());
                                } else {
                                    lValueOf = null;
                                }
                                if (lValueOf == null && lValueOf.longValue() > 0) {
                                    jLongValue += lValueOf.longValue();
                                }
                            }
                        }
                    } else {
                        zzal(this.zzi);
                        zzfxVarZzC = zzlj.zzC(zzftVarZze, "_et");
                        if (zzfxVarZzC == null) {
                            if (zzfxVarZzC.zzw()) {
                                lValueOf = Long.valueOf(zzfxVarZzC.zzd());
                            } else {
                                lValueOf = null;
                            }
                            if (lValueOf == null) {
                            }
                        }
                    }
                    i19++;
                }
                zzaf(zzgcVar5, jLongValue, false);
                Iterator it = zzgcVar5.zzat().iterator();
                while (it.hasNext()) {
                    if ("_s".equals(((com.google.android.gms.internal.measurement.zzft) it.next()).zzh())) {
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(zzgcVar5.zzaq(), "_se");
                        break;
                    }
                }
                if (zzlj.zza(zzgcVar5, "_sid") >= 0) {
                    zzaf(zzgcVar5, jLongValue, true);
                } else {
                    int iZza = zzlj.zza(zzgcVar5, "_se");
                    if (iZza >= 0) {
                        zzgcVar5.zzB(iZza);
                        zzaA().zzd().zzb("Session engagement user property is in the bundle without session ID. appId", zzet.zzn(zzleVar3.zza.zzy()));
                    }
                }
                zzlj zzljVar = this.zzi;
                zzal(zzljVar);
                zzljVar.zzt.zzaA().zzj().zza("Checking account type status for ad personalization signals");
                zzfu zzfuVar5 = zzljVar.zzf.zzc;
                zzal(zzfuVar5);
                if (zzfuVar5.zzn(zzgcVar5.zzaq())) {
                    zzak zzakVar6 = zzljVar.zzf.zze;
                    zzal(zzakVar6);
                    zzh zzhVarZzj = zzakVar6.zzj(zzgcVar5.zzaq());
                    if (zzhVarZzj != null && zzhVarZzj.zzam() && zzljVar.zzt.zzg().zze()) {
                        zzljVar.zzt.zzaA().zzc().zza("Turning off ad personalization due to account type");
                        com.google.android.gms.internal.measurement.zzgl zzglVarZzd = com.google.android.gms.internal.measurement.zzgm.zzd();
                        zzglVarZzd.zzf(str12);
                        zzglVarZzd.zzg(zzljVar.zzt.zzg().zza());
                        zzglVarZzd.zze(1L);
                        com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) zzglVarZzd.zzaD();
                        int i20 = 0;
                        while (true) {
                            if (i20 >= zzgcVar5.zzb()) {
                                zzgcVar5.zzm(zzgmVar);
                                break;
                            }
                            if (str12.equals(zzgcVar5.zzap(i20).zzf())) {
                                zzgcVar5.zzan(i20, zzgmVar);
                                break;
                            }
                            i20++;
                        }
                    }
                }
                zzgcVar5.zzai(Long.MAX_VALUE);
                zzgcVar5.zzQ(Long.MIN_VALUE);
                for (int i21 = 0; i21 < zzgcVar5.zza(); i21++) {
                    com.google.android.gms.internal.measurement.zzft zzftVarZze2 = zzgcVar5.zze(i21);
                    if (zzftVarZze2.zzd() < zzgcVar5.zzd()) {
                        zzgcVar5.zzai(zzftVarZze2.zzd());
                    }
                    if (zzftVarZze2.zzd() > zzgcVar5.zzc()) {
                        zzgcVar5.zzQ(zzftVarZze2.zzd());
                    }
                }
                zzgcVar5.zzz();
                zzgcVar5.zzo();
                zzaa zzaaVar = this.zzh;
                zzal(zzaaVar);
                zzgcVar5.zzf(zzaaVar.zza(zzgcVar5.zzaq(), zzgcVar5.zzat(), zzgcVar5.zzau(), Long.valueOf(zzgcVar5.zzd()), Long.valueOf(zzgcVar5.zzc())));
                if (zzg().zzw(zzleVar3.zza.zzy())) {
                    HashMap map = new HashMap();
                    ArrayList arrayList2 = new ArrayList();
                    SecureRandom secureRandomZzG = zzv().zzG();
                    int i22 = 0;
                    while (i22 < zzgcVar5.zza()) {
                        com.google.android.gms.internal.measurement.zzfs zzfsVar8 = (com.google.android.gms.internal.measurement.zzfs) zzgcVar5.zze(i22).zzbB();
                        if (zzfsVar8.zzo().equals("_ep")) {
                            zzal(this.zzi);
                            String str13 = (String) zzlj.zzD((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD(), "_en");
                            zzaq zzaqVarZzn = (zzaq) map.get(str13);
                            if (zzaqVarZzn == null) {
                                zzak zzakVar7 = this.zze;
                                zzal(zzakVar7);
                                zzaqVarZzn = zzakVar7.zzn(zzleVar3.zza.zzy(), (String) Preconditions.checkNotNull(str13));
                                if (zzaqVarZzn != null) {
                                    map.put(str13, zzaqVarZzn);
                                }
                            }
                            if (zzaqVarZzn != null && zzaqVarZzn.zzi == null) {
                                Long l = zzaqVarZzn.zzj;
                                if (l != null && l.longValue() > 1) {
                                    zzal(this.zzi);
                                    zzlj.zzA(zzfsVar8, "_sr", zzaqVarZzn.zzj);
                                }
                                Boolean bool = zzaqVarZzn.zzk;
                                if (bool != null && bool.booleanValue()) {
                                    zzal(this.zzi);
                                    zzlj.zzA(zzfsVar8, "_efs", 1L);
                                }
                                arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                            }
                            zzgcVar5.zzS(i22, zzfsVar8);
                        } else {
                            zzfu zzfuVar6 = this.zzc;
                            zzal(zzfuVar6);
                            String strZzy = zzleVar3.zza.zzy();
                            String strZza = zzfuVar6.zza(strZzy, "measurement.account.time_zone_offset_minutes");
                            if (TextUtils.isEmpty(strZza)) {
                                j2 = 0;
                            } else {
                                try {
                                    j2 = Long.parseLong(strZza);
                                } catch (NumberFormatException e) {
                                    zzfuVar6.zzt.zzaA().zzk().zzc("Unable to parse timezone offset. appId", zzet.zzn(strZzy), e);
                                    j2 = 0;
                                }
                            }
                            long jZzr = zzv().zzr(zzfsVar8.zzc(), j2);
                            com.google.android.gms.internal.measurement.zzft zzftVar = (com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD();
                            Long l2 = 1L;
                            long j3 = j2;
                            if (!TextUtils.isEmpty("_dbg")) {
                                Iterator it2 = zzftVar.zzi().iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        com.google.android.gms.internal.measurement.zzfx zzfxVar = (com.google.android.gms.internal.measurement.zzfx) it2.next();
                                        Iterator it3 = it2;
                                        if ("_dbg".equals(zzfxVar.zzg())) {
                                            if (l2.equals(Long.valueOf(zzfxVar.zzd()))) {
                                                iZzc = 1;
                                                break;
                                            }
                                        } else {
                                            it2 = it3;
                                        }
                                    }
                                    zzfu zzfuVar7 = this.zzc;
                                    zzal(zzfuVar7);
                                    iZzc = zzfuVar7.zzc(zzleVar3.zza.zzy(), zzfsVar8.zzo());
                                    break;
                                }
                            }
                            zzfu zzfuVar8 = this.zzc;
                            zzal(zzfuVar8);
                            iZzc = zzfuVar8.zzc(zzleVar3.zza.zzy(), zzfsVar8.zzo());
                            break;
                            if (iZzc <= 0) {
                                zzaA().zzk().zzc("Sample rate must be positive. event, rate", zzfsVar8.zzo(), Integer.valueOf(iZzc));
                                arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                zzgcVar5.zzS(i22, zzfsVar8);
                            } else {
                                zzaq zzaqVarZza = (zzaq) map.get(zzfsVar8.zzo());
                                if (zzaqVarZza == null) {
                                    zzak zzakVar8 = this.zze;
                                    zzal(zzakVar8);
                                    zzaqVarZza = zzakVar8.zzn(zzleVar3.zza.zzy(), zzfsVar8.zzo());
                                    if (zzaqVarZza == null) {
                                        zzaA().zzk().zzc("Event being bundled has no eventAggregate. appId, eventName", zzleVar3.zza.zzy(), zzfsVar8.zzo());
                                        zzaqVarZza = new zzaq(zzleVar3.zza.zzy(), zzfsVar8.zzo(), 1L, 1L, 1L, zzfsVar8.zzc(), 0L, null, null, null, null);
                                    }
                                }
                                zzal(this.zzi);
                                Long l3 = (Long) zzlj.zzD((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD(), "_eid");
                                boolean z4 = l3 != null;
                                Boolean boolValueOf = Boolean.valueOf(z4);
                                if (iZzc == 1) {
                                    arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                    boolValueOf.getClass();
                                    if (z4 && (zzaqVarZza.zzi != null || zzaqVarZza.zzj != null || zzaqVarZza.zzk != null)) {
                                        map.put(zzfsVar8.zzo(), zzaqVarZza.zza(null, null, null));
                                    }
                                    zzgcVar5.zzS(i22, zzfsVar8);
                                } else {
                                    if (secureRandomZzG.nextInt(iZzc) == 0) {
                                        zzal(this.zzi);
                                        zzle zzleVar4 = zzleVar3;
                                        Long lValueOf2 = Long.valueOf(iZzc);
                                        zzlj.zzA(zzfsVar8, "_sr", lValueOf2);
                                        arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                        boolValueOf.getClass();
                                        if (z4) {
                                            zzaqVarZza = zzaqVarZza.zza(null, lValueOf2, null);
                                        }
                                        map.put(zzfsVar8.zzo(), zzaqVarZza.zzb(zzfsVar8.zzc(), jZzr));
                                        zzleVar2 = zzleVar4;
                                        i2 = i22;
                                    } else {
                                        zzleVar2 = zzleVar3;
                                        int i23 = i22;
                                        Long l4 = zzaqVarZza.zzh;
                                        if ((l4 != null ? l4.longValue() : zzv().zzr(zzfsVar8.zzb(), j3)) != jZzr) {
                                            zzal(this.zzi);
                                            zzlj.zzA(zzfsVar8, "_efs", 1L);
                                            zzal(this.zzi);
                                            Long lValueOf3 = Long.valueOf(iZzc);
                                            zzlj.zzA(zzfsVar8, "_sr", lValueOf3);
                                            arrayList2.add((com.google.android.gms.internal.measurement.zzft) zzfsVar8.zzaD());
                                            boolValueOf.getClass();
                                            if (z4) {
                                                zzaqVarZza = zzaqVarZza.zza(null, lValueOf3, true);
                                            }
                                            map.put(zzfsVar8.zzo(), zzaqVarZza.zzb(zzfsVar8.zzc(), jZzr));
                                        } else {
                                            boolValueOf.getClass();
                                            if (z4) {
                                                map.put(zzfsVar8.zzo(), zzaqVarZza.zza(l3, null, null));
                                            }
                                        }
                                        i2 = i23;
                                    }
                                    zzgcVar5.zzS(i2, zzfsVar8);
                                }
                                i22 = i2 + 1;
                                zzgcVar5 = zzgcVar5;
                                secureRandomZzG = secureRandomZzG;
                                zzleVar3 = zzleVar2;
                            }
                        }
                        zzleVar2 = zzleVar3;
                        secureRandomZzG = secureRandomZzG;
                        i2 = i22;
                        zzgcVar5 = zzgcVar5;
                        i22 = i2 + 1;
                        zzgcVar5 = zzgcVar5;
                        secureRandomZzG = secureRandomZzG;
                        zzleVar3 = zzleVar2;
                    }
                    zzle zzleVar5 = zzleVar3;
                    zzgcVar = zzgcVar5;
                    if (arrayList2.size() < zzgcVar.zza()) {
                        zzgcVar.zzr();
                        zzgcVar.zzg(arrayList2);
                    }
                    for (Map.Entry entry : map.entrySet()) {
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzakVar9.zzE((zzaq) entry.getValue());
                    }
                    zzleVar = zzleVar5;
                } else {
                    zzgcVar = zzgcVar5;
                    zzleVar = zzleVar3;
                }
                String strZzy2 = zzleVar.zza.zzy();
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzh zzhVarZzj2 = zzakVar10.zzj(strZzy2);
                if (zzhVarZzj2 == null) {
                    zzaA().zzd().zzb("Bundling raw events w/o app info. appId", zzet.zzn(zzleVar.zza.zzy()));
                } else if (zzgcVar.zza() > 0) {
                    long jZzn = zzhVarZzj2.zzn();
                    if (jZzn != 0) {
                        zzgcVar.zzab(jZzn);
                    } else {
                        zzgcVar.zzv();
                    }
                    long jZzp = zzhVarZzj2.zzp();
                    if (jZzp != 0) {
                        jZzn = jZzp;
                    }
                    if (jZzn != 0) {
                        zzgcVar.zzac(jZzn);
                    } else {
                        zzgcVar.zzw();
                    }
                    zzhVarZzj2.zzG();
                    zzgcVar.zzI((int) zzhVarZzj2.zzo());
                    zzhVarZzj2.zzad(zzgcVar.zzd());
                    zzhVarZzj2.zzab(zzgcVar.zzc());
                    String strZzu = zzhVarZzj2.zzu();
                    if (strZzu != null) {
                        zzgcVar.zzW(strZzu);
                    } else {
                        zzgcVar.zzs();
                    }
                    zzak zzakVar11 = this.zze;
                    zzal(zzakVar11);
                    zzakVar11.zzD(zzhVarZzj2);
                }
                if (zzgcVar.zza() > 0) {
                    this.zzn.zzay();
                    zzfu zzfuVar9 = this.zzc;
                    zzal(zzfuVar9);
                    com.google.android.gms.internal.measurement.zzff zzffVarZze = zzfuVar9.zze(zzleVar.zza.zzy());
                    if (zzffVarZze != null && zzffVarZze.zzu()) {
                        zzgcVar.zzK(zzffVarZze.zzc());
                    } else if (zzleVar.zza.zzG().isEmpty()) {
                        zzgcVar.zzK(-1L);
                    } else {
                        zzaA().zzk().zzb("Did not find measurement config or missing version info. appId", zzet.zzn(zzleVar.zza.zzy()));
                    }
                    zzak zzakVar12 = this.zze;
                    zzal(zzakVar12);
                    com.google.android.gms.internal.measurement.zzgd zzgdVar = (com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD();
                    zzakVar12.zzg();
                    zzakVar12.zzW();
                    Preconditions.checkNotNull(zzgdVar);
                    Preconditions.checkNotEmpty(zzgdVar.zzy());
                    Preconditions.checkState(zzgdVar.zzbg());
                    zzakVar12.zzz();
                    long jCurrentTimeMillis = zzakVar12.zzt.zzax().currentTimeMillis();
                    long jZzk = zzgdVar.zzk();
                    zzakVar12.zzt.zzf();
                    if (jZzk >= jCurrentTimeMillis - zzag.zzA()) {
                        long jZzk2 = zzgdVar.zzk();
                        zzakVar12.zzt.zzf();
                        if (jZzk2 > zzag.zzA() + jCurrentTimeMillis) {
                            zzakVar12.zzt.zzaA().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzet.zzn(zzgdVar.zzy()), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zzgdVar.zzk()));
                        }
                    } else {
                        zzakVar12.zzt.zzaA().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzet.zzn(zzgdVar.zzy()), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zzgdVar.zzk()));
                    }
                    byte[] bArrZzbx = zzgdVar.zzbx();
                    try {
                        zzlj zzljVar2 = zzakVar12.zzf.zzi;
                        zzal(zzljVar2);
                        byte[] bArrZzz = zzljVar2.zzz(bArrZzbx);
                        zzakVar12.zzt.zzaA().zzj().zzb("Saving bundle, size", Integer.valueOf(bArrZzz.length));
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", zzgdVar.zzy());
                        contentValues.put("bundle_end_timestamp", Long.valueOf(zzgdVar.zzk()));
                        contentValues.put("data", bArrZzz);
                        contentValues.put("has_realtime", Integer.valueOf(i));
                        if (zzgdVar.zzbm()) {
                            contentValues.put("retry_count", Integer.valueOf(zzgdVar.zze()));
                        }
                        try {
                            if (zzakVar12.zzh().insert("queue", null, contentValues) == -1) {
                                zzakVar12.zzt.zzaA().zzd().zzb("Failed to insert bundle (got -1). appId", zzet.zzn(zzgdVar.zzy()));
                            }
                        } catch (SQLiteException e2) {
                            zzakVar12.zzt.zzaA().zzd().zzc("Error storing bundle. appId", zzet.zzn(zzgdVar.zzy()), e2);
                        }
                    } catch (IOException e3) {
                        zzakVar12.zzt.zzaA().zzd().zzc("Data loss. Failed to serialize bundle. appId", zzet.zzn(zzgdVar.zzy()), e3);
                    }
                }
                zzak zzakVar13 = this.zze;
                zzal(zzakVar13);
                List list2 = zzleVar.zzb;
                Preconditions.checkNotNull(list2);
                zzakVar13.zzg();
                zzakVar13.zzW();
                StringBuilder sb = new StringBuilder("rowid in (");
                for (int i24 = 0; i24 < list2.size(); i24++) {
                    if (i24 != 0) {
                        sb.append(",");
                    }
                    sb.append(((Long) list2.get(i24)).longValue());
                }
                sb.append(")");
                int iDelete = zzakVar13.zzh().delete("raw_events", sb.toString(), null);
                if (iDelete != list2.size()) {
                    zzakVar13.zzt.zzaA().zzd().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(iDelete), Integer.valueOf(list2.size()));
                }
                zzak zzakVar14 = this.zze;
                zzal(zzakVar14);
                try {
                    zzakVar14.zzh().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{strZzy2, strZzy2});
                } catch (SQLiteException e4) {
                    zzakVar14.zzt.zzaA().zzd().zzc("Failed to remove unused event metadata. appId", zzet.zzn(strZzy2), e4);
                }
                zzak zzakVar15 = this.zze;
                zzal(zzakVar15);
                zzakVar15.zzC();
                zzak zzakVar16 = this.zze;
                zzal(zzakVar16);
                zzakVar16.zzx();
                return true;
            }
            zzak zzakVar17 = this.zze;
            zzal(zzakVar17);
            zzakVar17.zzC();
            zzak zzakVar18 = this.zze;
            zzal(zzakVar18);
            zzakVar18.zzx();
            return false;
        } catch (Throwable th) {
            zzak zzakVar19 = this.zze;
            zzal(zzakVar19);
            zzakVar19.zzx();
            throw th;
        }
    }

    private final boolean zzai() {
        zzaB().zzg();
        zzB();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        if (zzakVar.zzF()) {
            return true;
        }
        zzak zzakVar2 = this.zze;
        zzal(zzakVar2);
        return !TextUtils.isEmpty(zzakVar2.zzr());
    }

    private final boolean zzaj(com.google.android.gms.internal.measurement.zzfs zzfsVar, com.google.android.gms.internal.measurement.zzfs zzfsVar2) {
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzfxVarZzC = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_sc");
        String strZzh = zzfxVarZzC == null ? null : zzfxVarZzC.zzh();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzfxVarZzC2 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_pc");
        String strZzh2 = zzfxVarZzC2 != null ? zzfxVarZzC2.zzh() : null;
        if (strZzh2 == null || !strZzh2.equals(strZzh)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzfsVar.zzo()));
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzfxVarZzC3 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD(), "_et");
        if (zzfxVarZzC3 == null || !zzfxVarZzC3.zzw() || zzfxVarZzC3.zzd() <= 0) {
            return true;
        }
        long jZzd = zzfxVarZzC3.zzd();
        zzal(this.zzi);
        com.google.android.gms.internal.measurement.zzfx zzfxVarZzC4 = zzlj.zzC((com.google.android.gms.internal.measurement.zzft) zzfsVar2.zzaD(), "_et");
        if (zzfxVarZzC4 != null && zzfxVarZzC4.zzd() > 0) {
            jZzd += zzfxVarZzC4.zzd();
        }
        zzal(this.zzi);
        zzlj.zzA(zzfsVar2, "_et", Long.valueOf(jZzd));
        zzal(this.zzi);
        zzlj.zzA(zzfsVar, "_fr", 1L);
        return true;
    }

    private static final boolean zzak(zzq zzqVar) {
        return (TextUtils.isEmpty(zzqVar.zzb) && TextUtils.isEmpty(zzqVar.zzq)) ? false : true;
    }

    private static final zzku zzal(zzku zzkuVar) {
        if (zzkuVar == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if (zzkuVar.zzY()) {
            return zzkuVar;
        }
        throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(String.valueOf(zzkuVar.getClass()))));
    }

    public static zzlh zzt(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzlh.class) {
                if (zzb == null) {
                    zzb = new zzlh((zzli) Preconditions.checkNotNull(new zzli(context)), null);
                }
            }
        }
        return zzb;
    }

    static /* bridge */ /* synthetic */ void zzy(zzlh zzlhVar, zzli zzliVar) {
        zzlhVar.zzaB().zzg();
        zzlhVar.zzm = new zzfl(zzlhVar);
        zzak zzakVar = new zzak(zzlhVar);
        zzakVar.zzX();
        zzlhVar.zze = zzakVar;
        zzlhVar.zzg().zzq((zzaf) Preconditions.checkNotNull(zzlhVar.zzc));
        zzkb zzkbVar = new zzkb(zzlhVar);
        zzkbVar.zzX();
        zzlhVar.zzk = zzkbVar;
        zzaa zzaaVar = new zzaa(zzlhVar);
        zzaaVar.zzX();
        zzlhVar.zzh = zzaaVar;
        zzip zzipVar = new zzip(zzlhVar);
        zzipVar.zzX();
        zzlhVar.zzj = zzipVar;
        zzks zzksVar = new zzks(zzlhVar);
        zzksVar.zzX();
        zzlhVar.zzg = zzksVar;
        zzlhVar.zzf = new zzfb(zzlhVar);
        if (zzlhVar.zzr != zzlhVar.zzs) {
            zzlhVar.zzaA().zzd().zzc("Not all upload components initialized", Integer.valueOf(zzlhVar.zzr), Integer.valueOf(zzlhVar.zzs));
        }
        zzlhVar.zzo = true;
    }

    final void zzA() {
        zzaB().zzg();
        zzB();
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        if (zzZ()) {
            FileChannel fileChannel = this.zzx;
            zzaB().zzg();
            int i = 0;
            if (fileChannel == null || !fileChannel.isOpen()) {
                zzaA().zzd().zza("Bad channel to read from");
            } else {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0L);
                    int i2 = fileChannel.read(byteBufferAllocate);
                    if (i2 == 4) {
                        byteBufferAllocate.flip();
                        i = byteBufferAllocate.getInt();
                    } else if (i2 != -1) {
                        zzaA().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(i2));
                    }
                } catch (IOException e) {
                    zzaA().zzd().zzb("Failed to read from channel", e);
                }
            }
            int iZzi = this.zzn.zzh().zzi();
            zzaB().zzg();
            if (i > iZzi) {
                zzaA().zzd().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
                return;
            }
            if (i < iZzi) {
                FileChannel fileChannel2 = this.zzx;
                zzaB().zzg();
                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                    zzaA().zzd().zza("Bad channel to read from");
                } else {
                    ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
                    byteBufferAllocate2.putInt(iZzi);
                    byteBufferAllocate2.flip();
                    try {
                        fileChannel2.truncate(0L);
                        fileChannel2.write(byteBufferAllocate2);
                        fileChannel2.force(true);
                        if (fileChannel2.size() != 4) {
                            zzaA().zzd().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                        }
                        zzaA().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
                        return;
                    } catch (IOException e2) {
                        zzaA().zzd().zzb("Failed to write to channel", e2);
                    }
                }
                zzaA().zzd().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(iZzi));
            }
        }
    }

    final void zzB() {
        if (!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    final void zzC(String str, com.google.android.gms.internal.measurement.zzgc zzgcVar) {
        int iZza;
        int iIndexOf;
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        Set setZzk = zzfuVar.zzk(str);
        if (setZzk != null) {
            zzgcVar.zzi(setZzk);
        }
        zzfu zzfuVar2 = this.zzc;
        zzal(zzfuVar2);
        if (zzfuVar2.zzv(str)) {
            zzgcVar.zzp();
        }
        zzfu zzfuVar3 = this.zzc;
        zzal(zzfuVar3);
        if (zzfuVar3.zzy(str)) {
            if (zzg().zzs(str, zzeg.zzar)) {
                String strZzas = zzgcVar.zzas();
                if (!TextUtils.isEmpty(strZzas) && (iIndexOf = strZzas.indexOf(".")) != -1) {
                    zzgcVar.zzY(strZzas.substring(0, iIndexOf));
                }
            } else {
                zzgcVar.zzu();
            }
        }
        zzfu zzfuVar4 = this.zzc;
        zzal(zzfuVar4);
        if (zzfuVar4.zzz(str) && (iZza = zzlj.zza(zzgcVar, VideoDownloadSQLiteHelper.Columns._ID)) != -1) {
            zzgcVar.zzB(iZza);
        }
        zzfu zzfuVar5 = this.zzc;
        zzal(zzfuVar5);
        if (zzfuVar5.zzx(str)) {
            zzgcVar.zzq();
        }
        zzfu zzfuVar6 = this.zzc;
        zzal(zzfuVar6);
        if (zzfuVar6.zzu(str)) {
            zzgcVar.zzn();
            zzlg zzlgVar = (zzlg) this.zzC.get(str);
            if (zzlgVar == null || zzlgVar.zzb + zzg().zzi(str, zzeg.zzT) < zzax().elapsedRealtime()) {
                zzlgVar = new zzlg(this);
                this.zzC.put(str, zzlgVar);
            }
            zzgcVar.zzR(zzlgVar.zza);
        }
        zzfu zzfuVar7 = this.zzc;
        zzal(zzfuVar7);
        if (zzfuVar7.zzw(str)) {
            zzgcVar.zzy();
        }
    }

    final void zzD(zzh zzhVar) {
        zzaB().zzg();
        if (TextUtils.isEmpty(zzhVar.zzA()) && TextUtils.isEmpty(zzhVar.zzt())) {
            zzI((String) Preconditions.checkNotNull(zzhVar.zzv()), 204, null, null, null);
            return;
        }
        zzkw zzkwVar = this.zzl;
        Uri.Builder builder = new Uri.Builder();
        String strZzA = zzhVar.zzA();
        if (TextUtils.isEmpty(strZzA)) {
            strZzA = zzhVar.zzt();
        }
        ArrayMap arrayMap = null;
        Uri.Builder builderAppendQueryParameter = builder.scheme((String) zzeg.zze.zza(null)).encodedAuthority((String) zzeg.zzf.zza(null)).path("config/app/".concat(String.valueOf(strZzA))).appendQueryParameter("platform", "android");
        zzkwVar.zzt.zzf().zzh();
        builderAppendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(79000L)).appendQueryParameter("runtime_version", "0");
        String string = builder.build().toString();
        try {
            String str = (String) Preconditions.checkNotNull(zzhVar.zzv());
            URL url = new URL(string);
            zzaA().zzj().zzb("Fetching remote configuration", str);
            zzfu zzfuVar = this.zzc;
            zzal(zzfuVar);
            com.google.android.gms.internal.measurement.zzff zzffVarZze = zzfuVar.zze(str);
            zzfu zzfuVar2 = this.zzc;
            zzal(zzfuVar2);
            String strZzh = zzfuVar2.zzh(str);
            if (zzffVarZze != null) {
                if (!TextUtils.isEmpty(strZzh)) {
                    ArrayMap arrayMap2 = new ArrayMap();
                    arrayMap2.put(HttpHeaders.IF_MODIFIED_SINCE, strZzh);
                    arrayMap = arrayMap2;
                }
                zzfu zzfuVar3 = this.zzc;
                zzal(zzfuVar3);
                String strZzf = zzfuVar3.zzf(str);
                if (!TextUtils.isEmpty(strZzf)) {
                    if (arrayMap == null) {
                        arrayMap = new ArrayMap();
                    }
                    arrayMap.put(HttpHeaders.IF_NONE_MATCH, strZzf);
                }
            }
            this.zzt = true;
            zzez zzezVar = this.zzd;
            zzal(zzezVar);
            zzkz zzkzVar = new zzkz(this);
            zzezVar.zzg();
            zzezVar.zzW();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkzVar);
            zzezVar.zzt.zzaB().zzo(new zzey(zzezVar, str, url, null, arrayMap, zzkzVar));
        } catch (MalformedURLException unused) {
            zzaA().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzet.zzn(zzhVar.zzv()), string);
        }
    }

    final void zzE(zzau zzauVar, zzq zzqVar) {
        zzau zzauVar2;
        List<zzac> listZzt;
        List<zzac> listZzt2;
        List<zzac> listZzt3;
        String str;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzaB().zzg();
        zzB();
        String str2 = zzqVar.zza;
        long j = zzauVar.zzd;
        zzeu zzeuVarZzb = zzeu.zzb(zzauVar);
        zzaB().zzg();
        zzir zzirVar = null;
        if (this.zzD != null && (str = this.zzE) != null && str.equals(str2)) {
            zzirVar = this.zzD;
        }
        zzlp.zzK(zzirVar, zzeuVarZzb.zzd, false);
        zzau zzauVarZza = zzeuVarZzb.zza();
        zzal(this.zzi);
        if (zzlj.zzB(zzauVarZza, zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            List list = zzqVar.zzt;
            if (list == null) {
                zzauVar2 = zzauVarZza;
            } else if (!list.contains(zzauVarZza.zza)) {
                zzaA().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", str2, zzauVarZza.zza, zzauVarZza.zzc);
                return;
            } else {
                Bundle bundleZzc = zzauVarZza.zzb.zzc();
                bundleZzc.putLong("ga_safelisted", 1L);
                zzauVar2 = new zzau(zzauVarZza.zza, new zzas(bundleZzc), zzauVarZza.zzc, zzauVarZza.zzd);
            }
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                Preconditions.checkNotEmpty(str2);
                zzakVar2.zzg();
                zzakVar2.zzW();
                if (j < 0) {
                    zzakVar2.zzt.zzaA().zzk().zzc("Invalid time querying timed out conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    listZzt = Collections.emptyList();
                } else {
                    listZzt = zzakVar2.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzac zzacVar : listZzt) {
                    if (zzacVar != null) {
                        zzaA().zzj().zzd("User property timed out", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb), zzacVar.zzc.zza());
                        zzau zzauVar3 = zzacVar.zzg;
                        if (zzauVar3 != null) {
                            zzY(new zzau(zzauVar3, j), zzqVar);
                        }
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zza(str2, zzacVar.zzc.zzb);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                Preconditions.checkNotEmpty(str2);
                zzakVar4.zzg();
                zzakVar4.zzW();
                if (j < 0) {
                    zzakVar4.zzt.zzaA().zzk().zzc("Invalid time querying expired conditional properties", zzet.zzn(str2), Long.valueOf(j));
                    listZzt2 = Collections.emptyList();
                } else {
                    listZzt2 = zzakVar4.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(listZzt2.size());
                for (zzac zzacVar2 : listZzt2) {
                    if (zzacVar2 != null) {
                        zzaA().zzj().zzd("User property expired", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzA(str2, zzacVar2.zzc.zzb);
                        zzau zzauVar4 = zzacVar2.zzk;
                        if (zzauVar4 != null) {
                            arrayList.add(zzauVar4);
                        }
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        zzakVar6.zza(str2, zzacVar2.zzc.zzb);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    zzY(new zzau((zzau) it.next(), j), zzqVar);
                }
                zzak zzakVar7 = this.zze;
                zzal(zzakVar7);
                String str3 = zzauVar2.zza;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzakVar7.zzg();
                zzakVar7.zzW();
                if (j < 0) {
                    zzakVar7.zzt.zzaA().zzk().zzd("Invalid time querying triggered conditional properties", zzet.zzn(str2), zzakVar7.zzt.zzj().zzd(str3), Long.valueOf(j));
                    listZzt3 = Collections.emptyList();
                } else {
                    listZzt3 = zzakVar7.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList2 = new ArrayList(listZzt3.size());
                for (zzac zzacVar3 : listZzt3) {
                    if (zzacVar3 != null) {
                        zzlk zzlkVar = zzacVar3.zzc;
                        zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar3.zza), zzacVar3.zzb, zzlkVar.zzb, j, Preconditions.checkNotNull(zzlkVar.zza()));
                        zzak zzakVar8 = this.zze;
                        zzal(zzakVar8);
                        if (zzakVar8.zzL(zzlmVar)) {
                            zzaA().zzj().zzd("User property triggered", zzacVar3.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        } else {
                            zzaA().zzd().zzd("Too many active user properties, ignoring", zzet.zzn(zzacVar3.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                        }
                        zzau zzauVar5 = zzacVar3.zzi;
                        if (zzauVar5 != null) {
                            arrayList2.add(zzauVar5);
                        }
                        zzacVar3.zzc = new zzlk(zzlmVar);
                        zzacVar3.zze = true;
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzakVar9.zzK(zzacVar3);
                    }
                }
                zzY(zzauVar2, zzqVar);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    zzY(new zzau((zzau) it2.next(), j), zzqVar);
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzC();
            } finally {
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            }
        }
    }

    final void zzF(zzau zzauVar, String str) {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzhVarZzj = zzakVar.zzj(str);
        if (zzhVarZzj == null || TextUtils.isEmpty(zzhVarZzj.zzy())) {
            zzaA().zzc().zzb("No app data available; dropping event", str);
            return;
        }
        Boolean boolZzad = zzad(zzhVarZzj);
        if (boolZzad == null) {
            if (!"_ui".equals(zzauVar.zza)) {
                zzaA().zzk().zzb("Could not find package. appId", zzet.zzn(str));
            }
        } else if (!boolZzad.booleanValue()) {
            zzaA().zzd().zzb("App version does not match; dropping event. appId", zzet.zzn(str));
            return;
        }
        String strZzA = zzhVarZzj.zzA();
        String strZzy = zzhVarZzj.zzy();
        long jZzb = zzhVarZzj.zzb();
        String strZzx = zzhVarZzj.zzx();
        long jZzm = zzhVarZzj.zzm();
        long jZzj = zzhVarZzj.zzj();
        boolean zZzan = zzhVarZzj.zzan();
        String strZzz = zzhVarZzj.zzz();
        zzhVarZzj.zza();
        zzG(zzauVar, new zzq(str, strZzA, strZzy, jZzb, strZzx, jZzm, jZzj, (String) null, zZzan, false, strZzz, 0L, 0L, 0, zzhVarZzj.zzam(), false, zzhVarZzj.zzt(), zzhVarZzj.zzs(), zzhVarZzj.zzk(), zzhVarZzj.zzE(), (String) null, zzq(str).zzi(), "", (String) null, zzhVarZzj.zzap(), zzhVarZzj.zzr()));
    }

    final void zzG(zzau zzauVar, zzq zzqVar) {
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzeu zzeuVarZzb = zzeu.zzb(zzauVar);
        zzlp zzlpVarZzv = zzv();
        Bundle bundle = zzeuVarZzb.zzd;
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzlpVarZzv.zzL(bundle, zzakVar.zzi(zzqVar.zza));
        zzv().zzN(zzeuVarZzb, zzg().zzd(zzqVar.zza));
        zzau zzauVarZza = zzeuVarZzb.zza();
        if ("_cmp".equals(zzauVarZza.zza) && "referrer API v2".equals(zzauVarZza.zzb.zzg("_cis"))) {
            String strZzg = zzauVarZza.zzb.zzg("gclid");
            if (!TextUtils.isEmpty(strZzg)) {
                zzW(new zzlk("_lgclid", zzauVarZza.zzd, strZzg, "auto"), zzqVar);
            }
        }
        zzE(zzauVarZza, zzqVar);
    }

    final void zzH() {
        this.zzs++;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0045  */
    /* JADX WARN: Code duplicated, block: B:14:0x0047 A[PHI: r9
      0x0047: PHI (r9v11 int) = (r9v2 int), (r9v0 int) binds: [B:12:0x0043, B:10:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    final void zzI(String str, int i, Throwable th, byte[] bArr, Map map) {
        boolean z;
        zzaB().zzg();
        zzB();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzt = false;
                zzae();
                throw th2;
            }
        }
        zzer zzerVarZzj = zzaA().zzj();
        Integer numValueOf = Integer.valueOf(bArr.length);
        zzerVarZzj.zzb("onConfigFetched. Response size", numValueOf);
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzw();
        try {
            zzak zzakVar2 = this.zze;
            zzal(zzakVar2);
            zzh zzhVarZzj = zzakVar2.zzj(str);
            if (i == 200 || i == 204) {
                if (th == null) {
                    z = true;
                } else {
                    z = false;
                }
            } else if (i == 304) {
                i = 304;
                if (th == null) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (zzhVarZzj == null) {
                zzaA().zzk().zzb("App does not exist in onConfigFetched. appId", zzet.zzn(str));
            } else if (z || i == 404) {
                List list = map != null ? (List) map.get(HttpHeaders.LAST_MODIFIED) : null;
                String str2 = (list == null || list.isEmpty()) ? null : (String) list.get(0);
                List list2 = map != null ? (List) map.get(HttpHeaders.ETAG) : null;
                String str3 = (list2 == null || list2.isEmpty()) ? null : (String) list2.get(0);
                if (i == 404 || i == 304) {
                    zzfu zzfuVar = this.zzc;
                    zzal(zzfuVar);
                    if (zzfuVar.zze(str) == null) {
                        zzfu zzfuVar2 = this.zzc;
                        zzal(zzfuVar2);
                        zzfuVar2.zzt(str, null, null, null);
                    }
                } else {
                    zzfu zzfuVar3 = this.zzc;
                    zzal(zzfuVar3);
                    zzfuVar3.zzt(str, bArr, str2, str3);
                }
                zzhVarZzj.zzN(zzax().currentTimeMillis());
                zzak zzakVar3 = this.zze;
                zzal(zzakVar3);
                zzakVar3.zzD(zzhVarZzj);
                if (i == 404) {
                    zzaA().zzl().zzb("Config not found. Using empty config. appId", str);
                } else {
                    zzaA().zzj().zzc("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), numValueOf);
                }
                zzez zzezVar = this.zzd;
                zzal(zzezVar);
                if (zzezVar.zza() && zzai()) {
                    zzX();
                } else {
                    zzag();
                }
            } else {
                zzhVarZzj.zzW(zzax().currentTimeMillis());
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                zzakVar4.zzD(zzhVarZzj);
                zzaA().zzj().zzc("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzfu zzfuVar4 = this.zzc;
                zzal(zzfuVar4);
                zzfuVar4.zzl(str);
                this.zzk.zzd.zzb(zzax().currentTimeMillis());
                if (i == 503 || i == 429) {
                    this.zzk.zzb.zzb(zzax().currentTimeMillis());
                }
                zzag();
            }
            zzak zzakVar5 = this.zze;
            zzal(zzakVar5);
            zzakVar5.zzC();
            zzak zzakVar6 = this.zze;
            zzal(zzakVar6);
            zzakVar6.zzx();
            this.zzt = false;
            zzae();
        } catch (Throwable th3) {
            zzak zzakVar7 = this.zze;
            zzal(zzakVar7);
            zzakVar7.zzx();
            throw th3;
        }
    }

    final void zzJ(boolean z) {
        zzag();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0069 A[Catch: all -> 0x00ed, TRY_LEAVE, TryCatch #3 {all -> 0x00ed, blocks: (B:13:0x005f, B:14:0x0063, B:16:0x0069, B:17:0x006f, B:18:0x008a, B:21:0x0096, B:22:0x009d, B:24:0x009f, B:25:0x00ae, B:27:0x00b0, B:29:0x00b4, B:32:0x00bb, B:33:0x00bc), top: B:61:0x005f, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e3 A[Catch: SQLiteException -> 0x00f7, all -> 0x016b, TryCatch #0 {SQLiteException -> 0x00f7, blocks: (B:12:0x0026, B:34:0x00c4, B:36:0x00d9, B:38:0x00df, B:40:0x00ea, B:39:0x00e3, B:42:0x00ee, B:43:0x00f6), top: B:57:0x0026, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0123 A[Catch: all -> 0x016b, PHI: r8
      0x0123: PHI (r8v18 int) = (r8v2 int), (r8v0 int) binds: [B:11:0x0024, B:9:0x0020] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {all -> 0x016b, blocks: (B:4:0x000d, B:5:0x000f, B:46:0x0123, B:51:0x015a, B:50:0x014b, B:12:0x0026, B:34:0x00c4, B:36:0x00d9, B:38:0x00df, B:40:0x00ea, B:39:0x00e3, B:42:0x00ee, B:43:0x00f6, B:45:0x00f8), top: B:60:0x000d, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0147  */
    /* JADX WARN: Code duplicated, block: B:50:0x014b A[Catch: all -> 0x016b, TryCatch #2 {all -> 0x016b, blocks: (B:4:0x000d, B:5:0x000f, B:46:0x0123, B:51:0x015a, B:50:0x014b, B:12:0x0026, B:34:0x00c4, B:36:0x00d9, B:38:0x00df, B:40:0x00ea, B:39:0x00e3, B:42:0x00ee, B:43:0x00f6, B:45:0x00f8), top: B:60:0x000d, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0095 A[SYNTHETIC] */
    final void zzK(int i, Throwable th, byte[] bArr, String str) {
        zzez zzezVar;
        zzak zzakVar;
        long jLongValue;
        zzaB().zzg();
        zzB();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzu = false;
                zzae();
                throw th2;
            }
        }
        List<Long> list = (List) Preconditions.checkNotNull(this.zzy);
        this.zzy = null;
        if (i == 200) {
            if (th == null) {
                try {
                    this.zzk.zzc.zzb(zzax().currentTimeMillis());
                    this.zzk.zzd.zzb(0L);
                    zzag();
                    zzaA().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                    zzak zzakVar2 = this.zze;
                    zzal(zzakVar2);
                    zzakVar2.zzw();
                    try {
                        for (Long l : list) {
                            try {
                                zzakVar = this.zze;
                                zzal(zzakVar);
                                jLongValue = l.longValue();
                                zzakVar.zzg();
                                zzakVar.zzW();
                                try {
                                    if (zzakVar.zzh().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) == 1) {
                                        throw new SQLiteException("Deleted fewer rows from queue than expected");
                                    }
                                } catch (SQLiteException e) {
                                    zzakVar.zzt.zzaA().zzd().zzb("Failed to delete a bundle in a queue table", e);
                                    throw e;
                                }
                            } catch (SQLiteException e2) {
                                List list2 = this.zzz;
                                if (list2 == null || !list2.contains(l)) {
                                    throw e2;
                                }
                            }
                        }
                        zzak zzakVar3 = this.zze;
                        zzal(zzakVar3);
                        zzakVar3.zzC();
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzx();
                        this.zzz = null;
                        zzezVar = this.zzd;
                        zzal(zzezVar);
                        if (zzezVar.zza() || !zzai()) {
                            this.zzA = -1L;
                            zzag();
                        } else {
                            zzX();
                        }
                        this.zza = 0L;
                    } catch (Throwable th3) {
                        zzak zzakVar5 = this.zze;
                        zzal(zzakVar5);
                        zzakVar5.zzx();
                        throw th3;
                    }
                } catch (SQLiteException e3) {
                    zzaA().zzd().zzb("Database error while trying to delete uploaded bundles", e3);
                    this.zza = zzax().elapsedRealtime();
                    zzaA().zzj().zzb("Disable upload, time", Long.valueOf(this.zza));
                }
            } else {
                zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
                this.zzk.zzd.zzb(zzax().currentTimeMillis());
                if (i != 503 || i == 429) {
                    this.zzk.zzb.zzb(zzax().currentTimeMillis());
                }
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzy(list);
                zzag();
            }
        } else if (i == 204) {
            i = 204;
            if (th == null) {
                this.zzk.zzc.zzb(zzax().currentTimeMillis());
                this.zzk.zzd.zzb(0L);
                zzag();
                zzaA().zzj().zzc("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzak zzakVar7 = this.zze;
                zzal(zzakVar7);
                zzakVar7.zzw();
                while (r8.hasNext()) {
                    zzakVar = this.zze;
                    zzal(zzakVar);
                    jLongValue = l.longValue();
                    zzakVar.zzg();
                    zzakVar.zzW();
                    if (zzakVar.zzh().delete("queue", "rowid=?", new String[]{String.valueOf(jLongValue)}) == 1) {
                        throw new SQLiteException("Deleted fewer rows from queue than expected");
                    }
                }
                zzak zzakVar8 = this.zze;
                zzal(zzakVar8);
                zzakVar8.zzC();
                zzak zzakVar9 = this.zze;
                zzal(zzakVar9);
                zzakVar9.zzx();
                this.zzz = null;
                zzezVar = this.zzd;
                zzal(zzezVar);
                if (zzezVar.zza()) {
                    this.zzA = -1L;
                    zzag();
                } else {
                    this.zzA = -1L;
                    zzag();
                }
                this.zza = 0L;
            } else {
                zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
                this.zzk.zzd.zzb(zzax().currentTimeMillis());
                if (i != 503) {
                    this.zzk.zzb.zzb(zzax().currentTimeMillis());
                } else {
                    this.zzk.zzb.zzb(zzax().currentTimeMillis());
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzy(list);
                zzag();
            }
        } else {
            zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzk.zzd.zzb(zzax().currentTimeMillis());
            if (i != 503) {
                this.zzk.zzb.zzb(zzax().currentTimeMillis());
            } else {
                this.zzk.zzb.zzb(zzax().currentTimeMillis());
            }
            zzak zzakVar11 = this.zze;
            zzal(zzakVar11);
            zzakVar11.zzy(list);
            zzag();
        }
        this.zzu = false;
        zzae();
    }

    final void zzL(zzq zzqVar) {
        String str;
        String str2;
        zzaq zzaqVarZzn;
        boolean z;
        PackageInfo packageInfo;
        String str3;
        String str4;
        ApplicationInfo applicationInfo;
        ApplicationInfo applicationInfo2;
        long j;
        boolean z2;
        zzaB().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        if (zzak(zzqVar)) {
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzh zzhVarZzj = zzakVar.zzj(zzqVar.zza);
            if (zzhVarZzj != null && TextUtils.isEmpty(zzhVarZzj.zzA()) && !TextUtils.isEmpty(zzqVar.zzb)) {
                zzhVarZzj.zzN(0L);
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzakVar2.zzD(zzhVarZzj);
                zzfu zzfuVar = this.zzc;
                zzal(zzfuVar);
                zzfuVar.zzm(zzqVar.zza);
            }
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            long jCurrentTimeMillis = zzqVar.zzm;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = zzax().currentTimeMillis();
            }
            this.zzn.zzg().zzd();
            int i = zzqVar.zzn;
            if (i != 0 && i != 1) {
                zzaA().zzk().zzc("Incorrect app type, assuming installed app. appId, appType", zzet.zzn(zzqVar.zza), Integer.valueOf(i));
                i = 0;
            }
            zzak zzakVar3 = this.zze;
            zzal(zzakVar3);
            zzakVar3.zzw();
            try {
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                zzlm zzlmVarZzp = zzakVar4.zzp(zzqVar.zza, "_npa");
                if (zzlmVarZzp == null || "auto".equals(zzlmVarZzp.zzb)) {
                    if (zzqVar.zzr != null) {
                        zzlk zzlkVar = new zzlk("_npa", jCurrentTimeMillis, Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto");
                        if (zzlmVarZzp == null || !zzlmVarZzp.zze.equals(zzlkVar.zzd)) {
                            zzW(zzlkVar, zzqVar);
                        }
                    } else if (zzlmVarZzp != null) {
                        zzP("_npa", zzqVar);
                    }
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzh zzhVarZzj2 = zzakVar5.zzj((String) Preconditions.checkNotNull(zzqVar.zza));
                if (zzhVarZzj2 == null || !zzv().zzao(zzqVar.zzb, zzhVarZzj2.zzA(), zzqVar.zzq, zzhVarZzj2.zzt())) {
                    str = "_pfo";
                    str2 = "_sysu";
                } else {
                    zzaA().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzet.zzn(zzhVarZzj2.zzv()));
                    zzak zzakVar6 = this.zze;
                    zzal(zzakVar6);
                    String strZzv = zzhVarZzj2.zzv();
                    zzakVar6.zzW();
                    zzakVar6.zzg();
                    Preconditions.checkNotEmpty(strZzv);
                    try {
                        SQLiteDatabase sQLiteDatabaseZzh = zzakVar6.zzh();
                        String[] strArr = {strZzv};
                        int iDelete = sQLiteDatabaseZzh.delete(Countly.CountlyFeatureNames.events, "app_id=?", strArr) + sQLiteDatabaseZzh.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("apps", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("event_filters", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("property_filters", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("consent_settings", "app_id=?", strArr);
                        zzpk.zzc();
                        str2 = "_sysu";
                        try {
                            str = "_pfo";
                            try {
                                if (zzakVar6.zzt.zzf().zzs(null, zzeg.zzat)) {
                                    iDelete += sQLiteDatabaseZzh.delete("default_event_params", "app_id=?", strArr);
                                }
                                if (iDelete > 0) {
                                    zzakVar6.zzt.zzaA().zzj().zzc("Deleted application data. app, records", strZzv, Integer.valueOf(iDelete));
                                }
                            } catch (SQLiteException e) {
                                e = e;
                                zzakVar6.zzt.zzaA().zzd().zzc("Error deleting application data. appId, error", zzet.zzn(strZzv), e);
                            }
                        } catch (SQLiteException e2) {
                            e = e2;
                            str = "_pfo";
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                        str = "_pfo";
                        str2 = "_sysu";
                    }
                    zzhVarZzj2 = null;
                }
                if (zzhVarZzj2 != null) {
                    boolean z3 = (zzhVarZzj2.zzb() == -2147483648L || zzhVarZzj2.zzb() == zzqVar.zzj) ? false : true;
                    String strZzy = zzhVarZzj2.zzy();
                    if (((zzhVarZzj2.zzb() != -2147483648L || strZzy == null || strZzy.equals(zzqVar.zzc)) ? false : true) | z3) {
                        Bundle bundle = new Bundle();
                        bundle.putString("_pv", strZzy);
                        zzE(new zzau("_au", new zzas(bundle), "auto", jCurrentTimeMillis), zzqVar);
                    }
                }
                zzd(zzqVar);
                if (i == 0) {
                    zzak zzakVar7 = this.zze;
                    zzal(zzakVar7);
                    zzaqVarZzn = zzakVar7.zzn(zzqVar.zza, "_f");
                    z = false;
                } else {
                    zzak zzakVar8 = this.zze;
                    zzal(zzakVar8);
                    zzaqVarZzn = zzakVar8.zzn(zzqVar.zza, "_v");
                    z = true;
                }
                if (zzaqVarZzn == null) {
                    long j2 = ((jCurrentTimeMillis / 3600000) + 1) * 3600000;
                    if (z) {
                        zzW(new zzlk("_fvt", jCurrentTimeMillis, Long.valueOf(j2), "auto"), zzqVar);
                        zzaB().zzg();
                        zzB();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1L);
                        bundle2.putLong("_r", 1L);
                        bundle2.putLong("_et", 1L);
                        if (zzqVar.zzp) {
                            bundle2.putLong("_dac", 1L);
                        }
                        zzG(new zzau("_v", new zzas(bundle2), "auto", jCurrentTimeMillis), zzqVar);
                    } else {
                        zzW(new zzlk("_fot", jCurrentTimeMillis, Long.valueOf(j2), "auto"), zzqVar);
                        zzaB().zzg();
                        zzfl zzflVar = (zzfl) Preconditions.checkNotNull(this.zzm);
                        String str5 = zzqVar.zza;
                        if (str5 == null || str5.isEmpty()) {
                            zzflVar.zza.zzaA().zzm().zza("Install Referrer Reporter was called with invalid app package name");
                        } else {
                            zzflVar.zza.zzaB().zzg();
                            if (zzflVar.zza()) {
                                zzfk zzfkVar = new zzfk(zzflVar, str5);
                                zzflVar.zza.zzaB().zzg();
                                Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                                intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                                PackageManager packageManager = zzflVar.zza.zzaw().getPackageManager();
                                if (packageManager == null) {
                                    zzflVar.zza.zzaA().zzm().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
                                } else {
                                    List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
                                    if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
                                        zzflVar.zza.zzaA().zzi().zza("Play Service for fetching Install Referrer is unavailable on device");
                                    } else {
                                        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
                                        if (resolveInfo.serviceInfo != null) {
                                            String str6 = resolveInfo.serviceInfo.packageName;
                                            if (resolveInfo.serviceInfo.name != null && "com.android.vending".equals(str6) && zzflVar.zza()) {
                                                try {
                                                    try {
                                                        zzflVar.zza.zzaA().zzj().zzb("Install Referrer Service is", ConnectionTracker.getInstance().bindService(zzflVar.zza.zzaw(), new Intent(intent), zzfkVar, 1) ? "available" : "not available");
                                                    } catch (RuntimeException e4) {
                                                        e = e4;
                                                        zzflVar.zza.zzaA().zzd().zzb("Exception occurred while binding to Install Referrer Service", e.getMessage());
                                                    }
                                                } catch (RuntimeException e5) {
                                                    e = e5;
                                                }
                                            } else {
                                                zzflVar.zza.zzaA().zzk().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                                            }
                                        }
                                    }
                                }
                            } else {
                                zzflVar.zza.zzaA().zzi().zza("Install Referrer Reporter is not available");
                            }
                        }
                        zzaB().zzg();
                        zzB();
                        Bundle bundle3 = new Bundle();
                        bundle3.putLong("_c", 1L);
                        bundle3.putLong("_r", 1L);
                        bundle3.putLong("_uwa", 0L);
                        String str7 = str;
                        bundle3.putLong(str7, 0L);
                        bundle3.putLong("_sys", 0L);
                        String str8 = str2;
                        bundle3.putLong(str8, 0L);
                        bundle3.putLong("_et", 1L);
                        if (zzqVar.zzp) {
                            bundle3.putLong("_dac", 1L);
                        }
                        String str9 = (String) Preconditions.checkNotNull(zzqVar.zza);
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        Preconditions.checkNotEmpty(str9);
                        zzakVar9.zzg();
                        zzakVar9.zzW();
                        long jZzc = zzakVar9.zzc(str9, "first_open_count");
                        if (this.zzn.zzaw().getPackageManager() == null) {
                            zzaA().zzd().zzb("PackageManager is null, first open report might be inaccurate. appId", zzet.zzn(str9));
                        } else {
                            try {
                                packageInfo = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(str9, 0);
                            } catch (PackageManager.NameNotFoundException e6) {
                                zzaA().zzd().zzc("Package info is null, first open report might be inaccurate. appId", zzet.zzn(str9), e6);
                                packageInfo = null;
                            }
                            if (packageInfo == null || packageInfo.firstInstallTime == 0) {
                                str3 = "_sys";
                                str4 = str8;
                                applicationInfo = null;
                            } else {
                                if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                    applicationInfo = null;
                                    if (!zzg().zzs(null, zzeg.zzad)) {
                                        bundle3.putLong("_uwa", 1L);
                                    } else if (jZzc == 0) {
                                        bundle3.putLong("_uwa", 1L);
                                        jZzc = 0;
                                    }
                                    z2 = false;
                                } else {
                                    applicationInfo = null;
                                    z2 = true;
                                }
                                str3 = "_sys";
                                str4 = str8;
                                zzW(new zzlk("_fi", jCurrentTimeMillis, Long.valueOf(true != z2 ? 0L : 1L), "auto"), zzqVar);
                            }
                            try {
                                applicationInfo2 = Wrappers.packageManager(this.zzn.zzaw()).getApplicationInfo(str9, 0);
                            } catch (PackageManager.NameNotFoundException e7) {
                                zzaA().zzd().zzc("Application info is null, first open report might be inaccurate. appId", zzet.zzn(str9), e7);
                                applicationInfo2 = applicationInfo;
                            }
                            if (applicationInfo2 != null) {
                                if ((applicationInfo2.flags & 1) != 0) {
                                    j = 1;
                                    bundle3.putLong(str3, 1L);
                                } else {
                                    j = 1;
                                }
                                if ((applicationInfo2.flags & 128) != 0) {
                                    bundle3.putLong(str4, j);
                                }
                            }
                        }
                        if (jZzc >= 0) {
                            bundle3.putLong(str7, jZzc);
                        }
                        zzG(new zzau("_f", new zzas(bundle3), "auto", jCurrentTimeMillis), zzqVar);
                    }
                } else if (zzqVar.zzi) {
                    zzG(new zzau("_cd", new zzas(new Bundle()), "auto", jCurrentTimeMillis), zzqVar);
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzC();
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            } catch (Throwable th) {
                zzak zzakVar12 = this.zze;
                zzal(zzakVar12);
                zzakVar12.zzx();
                throw th;
            }
        }
    }

    final void zzM() {
        this.zzr++;
    }

    final void zzN(zzac zzacVar) {
        zzq zzqVarZzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzqVarZzac != null) {
            zzO(zzacVar, zzqVarZzac);
        }
    }

    final void zzO(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzd(zzqVar);
                String str = (String) Preconditions.checkNotNull(zzacVar.zza);
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzac zzacVarZzk = zzakVar2.zzk(str, zzacVar.zzc.zzb);
                if (zzacVarZzk != null) {
                    zzaA().zzc().zzc("Removing conditional user property", zzacVar.zza, this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    zzakVar3.zza(str, zzacVar.zzc.zzb);
                    if (zzacVarZzk.zze) {
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzA(str, zzacVar.zzc.zzb);
                    }
                    zzau zzauVar = zzacVar.zzk;
                    if (zzauVar != null) {
                        zzas zzasVar = zzauVar.zzb;
                        zzY((zzau) Preconditions.checkNotNull(zzv().zzz(str, ((zzau) Preconditions.checkNotNull(zzacVar.zzk)).zza, zzasVar != null ? zzasVar.zzc() : null, zzacVarZzk.zzb, zzacVar.zzk.zzd, true, true)), zzqVar);
                    }
                } else {
                    zzaA().zzk().zzc("Conditional user property doesn't exist", zzet.zzn(zzacVar.zza), this.zzn.zzj().zzf(zzacVar.zzc.zzb));
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzC();
            } finally {
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzx();
            }
        }
    }

    final void zzP(String str, zzq zzqVar) {
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            if ("_npa".equals(str) && zzqVar.zzr != null) {
                zzaA().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zzW(new zzlk("_npa", zzax().currentTimeMillis(), Long.valueOf(true != zzqVar.zzr.booleanValue() ? 0L : 1L), "auto"), zzqVar);
                return;
            }
            zzaA().zzc().zzb("Removing user property", this.zzn.zzj().zzf(str));
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzd(zzqVar);
                if (VideoDownloadSQLiteHelper.Columns._ID.equals(str)) {
                    zzak zzakVar2 = this.zze;
                    zzal(zzakVar2);
                    zzakVar2.zzA((String) Preconditions.checkNotNull(zzqVar.zza), "_lair");
                }
                zzak zzakVar3 = this.zze;
                zzal(zzakVar3);
                zzakVar3.zzA((String) Preconditions.checkNotNull(zzqVar.zza), str);
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                zzakVar4.zzC();
                zzaA().zzc().zzb("User property removed", this.zzn.zzj().zzf(str));
            } finally {
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzx();
            }
        }
    }

    final void zzQ(zzq zzqVar) {
        if (this.zzy != null) {
            ArrayList arrayList = new ArrayList();
            this.zzz = arrayList;
            arrayList.addAll(this.zzy);
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        String str = (String) Preconditions.checkNotNull(zzqVar.zza);
        Preconditions.checkNotEmpty(str);
        zzakVar.zzg();
        zzakVar.zzW();
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzakVar.zzh();
            String[] strArr = {str};
            int iDelete = sQLiteDatabaseZzh.delete("apps", "app_id=?", strArr) + sQLiteDatabaseZzh.delete(Countly.CountlyFeatureNames.events, "app_id=?", strArr) + sQLiteDatabaseZzh.delete("user_attributes", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("conditional_properties", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("raw_events_metadata", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("queue", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("audience_filter_values", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("main_event_params", "app_id=?", strArr) + sQLiteDatabaseZzh.delete("default_event_params", "app_id=?", strArr);
            if (iDelete > 0) {
                zzakVar.zzt.zzaA().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error resetting analytics data. appId, error", zzet.zzn(str), e);
        }
        if (zzqVar.zzh) {
            zzL(zzqVar);
        }
    }

    public final void zzR(String str, zzir zzirVar) {
        zzaB().zzg();
        String str2 = this.zzE;
        if (str2 == null || str2.equals(str) || zzirVar != null) {
            this.zzE = str;
            this.zzD = zzirVar;
        }
    }

    protected final void zzS() {
        zzaB().zzg();
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzakVar.zzz();
        if (this.zzk.zzc.zza() == 0) {
            this.zzk.zzc.zzb(zzax().currentTimeMillis());
        }
        zzag();
    }

    final void zzT(zzac zzacVar) {
        zzq zzqVarZzac = zzac((String) Preconditions.checkNotNull(zzacVar.zza));
        if (zzqVarZzac != null) {
            zzU(zzacVar, zzqVarZzac);
        }
    }

    final void zzU(zzac zzacVar, zzq zzqVar) {
        Preconditions.checkNotNull(zzacVar);
        Preconditions.checkNotEmpty(zzacVar.zza);
        Preconditions.checkNotNull(zzacVar.zzb);
        Preconditions.checkNotNull(zzacVar.zzc);
        Preconditions.checkNotEmpty(zzacVar.zzc.zzb);
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzac zzacVar2 = new zzac(zzacVar);
            boolean z = false;
            zzacVar2.zze = false;
            zzak zzakVar = this.zze;
            zzal(zzakVar);
            zzakVar.zzw();
            try {
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                zzac zzacVarZzk = zzakVar2.zzk((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzc.zzb);
                if (zzacVarZzk != null && !zzacVarZzk.zzb.equals(zzacVar2.zzb)) {
                    zzaA().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzb, zzacVarZzk.zzb);
                }
                if (zzacVarZzk != null && zzacVarZzk.zze) {
                    zzacVar2.zzb = zzacVarZzk.zzb;
                    zzacVar2.zzd = zzacVarZzk.zzd;
                    zzacVar2.zzh = zzacVarZzk.zzh;
                    zzacVar2.zzf = zzacVarZzk.zzf;
                    zzacVar2.zzi = zzacVarZzk.zzi;
                    zzacVar2.zze = true;
                    zzlk zzlkVar = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlk(zzlkVar.zzb, zzacVarZzk.zzc.zzc, zzlkVar.zza(), zzacVarZzk.zzc.zzf);
                } else if (TextUtils.isEmpty(zzacVar2.zzf)) {
                    zzlk zzlkVar2 = zzacVar2.zzc;
                    zzacVar2.zzc = new zzlk(zzlkVar2.zzb, zzacVar2.zzd, zzlkVar2.zza(), zzacVar2.zzc.zzf);
                    zzacVar2.zze = true;
                    z = true;
                }
                if (zzacVar2.zze) {
                    zzlk zzlkVar3 = zzacVar2.zzc;
                    zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzacVar2.zza), zzacVar2.zzb, zzlkVar3.zzb, zzlkVar3.zzc, Preconditions.checkNotNull(zzlkVar3.zza()));
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzL(zzlmVar)) {
                        zzaA().zzc().zzd("User property updated immediately", zzacVar2.zza, this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    } else {
                        zzaA().zzd().zzd("(2)Too many active user properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    }
                    if (z && zzacVar2.zzi != null) {
                        zzY(new zzau(zzacVar2.zzi, zzacVar2.zzd), zzqVar);
                    }
                }
                zzak zzakVar4 = this.zze;
                zzal(zzakVar4);
                if (zzakVar4.zzK(zzacVar2)) {
                    zzaA().zzc().zzd("Conditional property added", zzacVar2.zza, this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                } else {
                    zzaA().zzd().zzd("Too many conditional properties, ignoring", zzet.zzn(zzacVar2.zza), this.zzn.zzj().zzf(zzacVar2.zzc.zzb), zzacVar2.zzc.zza());
                }
                zzak zzakVar5 = this.zze;
                zzal(zzakVar5);
                zzakVar5.zzC();
            } finally {
                zzak zzakVar6 = this.zze;
                zzal(zzakVar6);
                zzakVar6.zzx();
            }
        }
    }

    final void zzV(String str, zzhb zzhbVar) {
        zzaB().zzg();
        zzB();
        this.zzB.put(str, zzhbVar);
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzhbVar);
        zzakVar.zzg();
        zzakVar.zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzhbVar.zzi());
        try {
            if (zzakVar.zzh().insertWithOnConflict("consent_settings", null, contentValues, 5) == -1) {
                zzakVar.zzt.zzaA().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzet.zzn(str));
            }
        } catch (SQLiteException e) {
            zzakVar.zzt.zzaA().zzd().zzc("Error storing consent setting. appId, error", zzet.zzn(str), e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00de A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:43:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:44:0x0110  */
    final void zzW(zzlk zzlkVar, zzq zzqVar) {
        zzaq zzaqVarZzn;
        long jLongValue;
        zzaB().zzg();
        zzB();
        if (zzak(zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            int iZzl = zzv().zzl(zzlkVar.zzb);
            if (iZzl != 0) {
                zzlp zzlpVarZzv = zzv();
                String str = zzlkVar.zzb;
                zzg();
                String strZzD = zzlpVarZzv.zzD(str, 24, true);
                String str2 = zzlkVar.zzb;
                zzv().zzO(this.zzF, zzqVar.zza, iZzl, "_ev", strZzD, str2 != null ? str2.length() : 0);
                return;
            }
            int iZzd = zzv().zzd(zzlkVar.zzb, zzlkVar.zza());
            if (iZzd != 0) {
                zzlp zzlpVarZzv2 = zzv();
                String str3 = zzlkVar.zzb;
                zzg();
                String strZzD2 = zzlpVarZzv2.zzD(str3, 24, true);
                Object objZza = zzlkVar.zza();
                zzv().zzO(this.zzF, zzqVar.zza, iZzd, "_ev", strZzD2, (objZza == null || !((objZza instanceof String) || (objZza instanceof CharSequence))) ? 0 : objZza.toString().length());
                return;
            }
            Object objZzB = zzv().zzB(zzlkVar.zzb, zzlkVar.zza());
            if (objZzB == null) {
                return;
            }
            if ("_sid".equals(zzlkVar.zzb)) {
                long j = zzlkVar.zzc;
                String str4 = zzlkVar.zzf;
                String str5 = (String) Preconditions.checkNotNull(zzqVar.zza);
                zzak zzakVar = this.zze;
                zzal(zzakVar);
                zzlm zzlmVarZzp = zzakVar.zzp(str5, "_sno");
                if (zzlmVarZzp != null) {
                    Object obj = zzlmVarZzp.zze;
                    if (obj instanceof Long) {
                        jLongValue = ((Long) obj).longValue();
                    } else {
                        if (zzlmVarZzp != null) {
                            zzaA().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzlmVarZzp.zze);
                        }
                        zzak zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        zzaqVarZzn = zzakVar2.zzn(str5, "_s");
                        if (zzaqVarZzn != null) {
                            jLongValue = zzaqVarZzn.zzc;
                            zzaA().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(jLongValue));
                        } else {
                            jLongValue = 0;
                        }
                    }
                } else {
                    if (zzlmVarZzp != null) {
                        zzaA().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzlmVarZzp.zze);
                    }
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    zzaqVarZzn = zzakVar3.zzn(str5, "_s");
                    if (zzaqVarZzn != null) {
                        jLongValue = zzaqVarZzn.zzc;
                        zzaA().zzj().zzb("Backfill the session number. Last used session number", Long.valueOf(jLongValue));
                    } else {
                        jLongValue = 0;
                    }
                }
                zzW(new zzlk("_sno", j, Long.valueOf(jLongValue + 1), str4), zzqVar);
            }
            zzlm zzlmVar = new zzlm((String) Preconditions.checkNotNull(zzqVar.zza), (String) Preconditions.checkNotNull(zzlkVar.zzf), zzlkVar.zzb, zzlkVar.zzc, objZzB);
            zzaA().zzj().zzc("Setting user property", this.zzn.zzj().zzf(zzlmVar.zzc), objZzB);
            zzak zzakVar4 = this.zze;
            zzal(zzakVar4);
            zzakVar4.zzw();
            try {
                if (VideoDownloadSQLiteHelper.Columns._ID.equals(zzlmVar.zzc)) {
                    zzak zzakVar5 = this.zze;
                    zzal(zzakVar5);
                    zzlm zzlmVarZzp2 = zzakVar5.zzp(zzqVar.zza, VideoDownloadSQLiteHelper.Columns._ID);
                    if (zzlmVarZzp2 != null && !zzlmVar.zze.equals(zzlmVarZzp2.zze)) {
                        zzak zzakVar6 = this.zze;
                        zzal(zzakVar6);
                        zzakVar6.zzA(zzqVar.zza, "_lair");
                    }
                }
                zzd(zzqVar);
                zzak zzakVar7 = this.zze;
                zzal(zzakVar7);
                boolean zZzL = zzakVar7.zzL(zzlmVar);
                if (zzg().zzs(null, zzeg.zzaH) && "_sid".equals(zzlkVar.zzb)) {
                    zzlj zzljVar = this.zzi;
                    zzal(zzljVar);
                    long jZzd = zzljVar.zzd(zzqVar.zzx);
                    zzak zzakVar8 = this.zze;
                    zzal(zzakVar8);
                    zzh zzhVarZzj = zzakVar8.zzj(zzqVar.zza);
                    if (zzhVarZzj != null) {
                        zzhVarZzj.zzaj(jZzd);
                        if (zzhVarZzj.zzao()) {
                            zzak zzakVar9 = this.zze;
                            zzal(zzakVar9);
                            zzakVar9.zzD(zzhVarZzj);
                        }
                    }
                }
                zzak zzakVar10 = this.zze;
                zzal(zzakVar10);
                zzakVar10.zzC();
                if (!zZzL) {
                    zzaA().zzd().zzc("Too many unique user properties are set. Ignoring user property", this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                    zzv().zzO(this.zzF, zzqVar.zza, 9, null, null, 0);
                }
            } finally {
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzakVar11.zzx();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x022c  */
    /* JADX WARN: Code duplicated, block: B:117:0x0264 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:121:0x0270 A[Catch: all -> 0x0535, TRY_ENTER, TRY_LEAVE, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:136:0x02a5 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x02b1 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:141:0x02bb A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:149:0x02de A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:151:0x02f2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:152:0x02f3 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x0321 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:161:0x032f  */
    /* JADX WARN: Code duplicated, block: B:164:0x0354 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:166:0x038e A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:168:0x0393 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:170:0x039b A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:173:0x03a3 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:176:0x03b2 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:180:0x03e1 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:181:0x03f1  */
    /* JADX WARN: Code duplicated, block: B:185:0x0414 A[Catch: MalformedURLException -> 0x048c, all -> 0x0535, TryCatch #19 {MalformedURLException -> 0x048c, blocks: (B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422), top: B:253:0x0407, outer: #15 }] */
    /* JADX WARN: Code duplicated, block: B:186:0x0422 A[Catch: MalformedURLException -> 0x048c, all -> 0x0535, TryCatch #19 {MalformedURLException -> 0x048c, blocks: (B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422), top: B:253:0x0407, outer: #15 }] */
    /* JADX WARN: Code duplicated, block: B:189:0x0434 A[Catch: MalformedURLException -> 0x048c, all -> 0x0535, TryCatch #19 {MalformedURLException -> 0x048c, blocks: (B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422), top: B:253:0x0407, outer: #15 }] */
    /* JADX WARN: Code duplicated, block: B:223:0x051a A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:225:0x0525 A[Catch: all -> 0x0535, TRY_LEAVE, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:263:0x02d4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:264:0x02cf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:265:? A[LOOP:1: B:139:0x02b5->B:265:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:272:0x03c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:275:0x026e A[ADDED_TO_REGION, EDGE_INSN: B:275:0x026e->B:120:0x026e BREAK  A[LOOP:4: B:79:0x01ab->B:119:0x0267], REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:277:0x01d5 A[EDGE_INSN: B:277:0x01d5->B:85:0x01d5 BREAK  A[LOOP:5: B:242:0x01cf->B:102:0x022d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x010a A[Catch: all -> 0x0535, PHI: r7 r11
      0x010a: PHI (r7v11 long) = (r7v0 long), (r7v13 long), (r7v0 long) binds: [B:54:0x012a, B:45:0x0112, B:41:0x0108] A[DONT_GENERATE, DONT_INLINE]
      0x010a: PHI (r11v16 android.database.Cursor) = (r11v14 android.database.Cursor), (r11v18 android.database.Cursor), (r11v18 android.database.Cursor) binds: [B:54:0x012a, B:45:0x0112, B:41:0x0108] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0134 A[Catch: all -> 0x0535, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x015d  */
    /* JADX WARN: Code duplicated, block: B:65:0x015f  */
    /* JADX WARN: Code duplicated, block: B:68:0x0165  */
    /* JADX WARN: Code duplicated, block: B:69:0x0167  */
    /* JADX WARN: Code duplicated, block: B:74:0x0198 A[Catch: SQLiteException -> 0x0277, all -> 0x04a3, TRY_LEAVE, TryCatch #0 {SQLiteException -> 0x0277, blocks: (B:72:0x0192, B:74:0x0198, B:78:0x01a5, B:79:0x01ab, B:80:0x01af, B:81:0x01ba), top: B:235:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x019e A[Catch: all -> 0x0535, TRY_ENTER, TRY_LEAVE, TryCatch #15 {all -> 0x0535, blocks: (B:3:0x0010, B:5:0x0021, B:9:0x0034, B:11:0x003a, B:13:0x004a, B:15:0x0052, B:17:0x0058, B:19:0x0063, B:21:0x0073, B:23:0x007e, B:25:0x0091, B:27:0x00b0, B:29:0x00b6, B:30:0x00b9, B:32:0x00c5, B:33:0x00dc, B:35:0x00ed, B:37:0x00f3, B:42:0x010a, B:56:0x012d, B:60:0x0134, B:61:0x0137, B:62:0x0138, B:66:0x0160, B:70:0x0168, B:76:0x019e, B:134:0x029f, B:136:0x02a5, B:138:0x02b1, B:139:0x02b5, B:141:0x02bb, B:143:0x02cf, B:147:0x02d8, B:149:0x02de, B:155:0x0303, B:152:0x02f3, B:154:0x02fd, B:156:0x0306, B:158:0x0321, B:162:0x0330, B:164:0x0354, B:166:0x038e, B:168:0x0393, B:170:0x039b, B:171:0x039e, B:173:0x03a3, B:174:0x03a6, B:176:0x03b2, B:177:0x03c8, B:178:0x03d0, B:180:0x03e1, B:182:0x03f2, B:183:0x0407, B:185:0x0414, B:187:0x0429, B:189:0x0434, B:190:0x043d, B:186:0x0422, B:192:0x048c, B:121:0x0270, B:133:0x029c, B:196:0x04a7, B:197:0x04aa, B:198:0x04ab, B:204:0x04eb, B:221:0x0514, B:223:0x051a, B:225:0x0525, B:209:0x04f6, B:230:0x0531, B:231:0x0534), top: B:252:0x0010, inners: #19 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x01a5 A[Catch: SQLiteException -> 0x0277, all -> 0x04a3, TRY_ENTER, TryCatch #0 {SQLiteException -> 0x0277, blocks: (B:72:0x0192, B:74:0x0198, B:78:0x01a5, B:79:0x01ab, B:80:0x01af, B:81:0x01ba), top: B:235:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:96:0x01fc A[Catch: SQLiteException -> 0x0275, all -> 0x04a3, TryCatch #3 {all -> 0x04a3, blocks: (B:72:0x0192, B:74:0x0198, B:78:0x01a5, B:79:0x01ab, B:80:0x01af, B:81:0x01ba, B:83:0x01cf, B:85:0x01d5, B:86:0x01df, B:88:0x01e5, B:92:0x01eb, B:94:0x01f6, B:96:0x01fc, B:97:0x0203, B:115:0x025e, B:99:0x0218, B:102:0x022d, B:108:0x0236, B:109:0x0245, B:114:0x024b, B:131:0x0283), top: B:239:0x0168 }] */
    final void zzX() {
        Cursor cursor;
        Cursor cursorRawQuery;
        String string;
        zzh zzhVarZzj;
        int iZze;
        int iMax;
        zzak zzakVar;
        boolean z;
        boolean z2;
        Cursor cursor2;
        long j;
        Cursor cursorQuery;
        List listEmptyList;
        com.google.android.gms.internal.measurement.zzga zzgaVarZza;
        int size;
        ArrayList arrayList;
        boolean z3;
        boolean zZzj;
        boolean zZzj2;
        boolean zZzs;
        int i;
        String strZzo;
        zzkv zzkvVarZza;
        com.google.android.gms.internal.measurement.zzgc zzgcVar;
        Iterator it;
        String strZzK;
        int i2;
        com.google.android.gms.internal.measurement.zzgd zzgdVar;
        com.google.android.gms.internal.measurement.zzgd zzgdVar2;
        ArrayList arrayList2;
        int length;
        long j2;
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        int i3;
        byte[] byteArray;
        com.google.android.gms.internal.measurement.zzgc zzgcVar2;
        Cursor cursorRawQuery2;
        zzaB().zzg();
        zzB();
        int i4 = 1;
        this.zzv = true;
        int i5 = 0;
        try {
            this.zzn.zzay();
            Boolean boolZzj = this.zzn.zzt().zzj();
            if (boolZzj == null) {
                zzaA().zzk().zza("Upload data called on the client side before use of service was decided");
                this.zzv = false;
            } else if (boolZzj.booleanValue()) {
                zzaA().zzd().zza("Upload called in the client side when service should be used");
                this.zzv = false;
            } else if (this.zza > 0) {
                zzag();
                this.zzv = false;
            } else {
                zzaB().zzg();
                if (this.zzy != null) {
                    zzaA().zzj().zza("Uploading requested multiple times");
                    this.zzv = false;
                } else {
                    zzez zzezVar = this.zzd;
                    zzal(zzezVar);
                    if (zzezVar.zza()) {
                        long jCurrentTimeMillis = zzax().currentTimeMillis();
                        Cursor cursor3 = null;
                        int iZze2 = zzg().zze(null, zzeg.zzR);
                        zzg();
                        long jZzz = jCurrentTimeMillis - zzag.zzz();
                        for (int i6 = 0; i6 < iZze2 && zzah(null, jZzz); i6++) {
                        }
                        long jZza = this.zzk.zzc.zza();
                        if (jZza != 0) {
                            zzaA().zzc().zzb("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(jCurrentTimeMillis - jZza)));
                        }
                        zzak zzakVar2 = this.zze;
                        zzal(zzakVar2);
                        String strZzr = zzakVar2.zzr();
                        long j3 = -1;
                        if (TextUtils.isEmpty(strZzr)) {
                            try {
                                this.zzA = -1L;
                                zzak zzakVar3 = this.zze;
                                zzal(zzakVar3);
                                zzg();
                                long jZzz2 = jCurrentTimeMillis - zzag.zzz();
                                zzakVar3.zzg();
                                zzakVar3.zzW();
                                try {
                                    cursorRawQuery = zzakVar3.zzh().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(jZzz2)});
                                    try {
                                        if (cursorRawQuery.moveToFirst()) {
                                            string = cursorRawQuery.getString(0);
                                            if (cursorRawQuery != null) {
                                                cursorRawQuery.close();
                                            }
                                        } else {
                                            zzakVar3.zzt.zzaA().zzj().zza("No expired configs for apps with pending events");
                                            if (cursorRawQuery != null) {
                                                cursorRawQuery.close();
                                            }
                                            string = null;
                                        }
                                    } catch (SQLiteException e) {
                                        e = e;
                                        zzakVar3.zzt.zzaA().zzd().zzb("Error selecting expired configs", e);
                                        if (cursorRawQuery != null) {
                                        }
                                        string = null;
                                        if (!TextUtils.isEmpty(string)) {
                                            zzak zzakVar4 = this.zze;
                                            zzal(zzakVar4);
                                            zzhVarZzj = zzakVar4.zzj(string);
                                            if (zzhVarZzj != null) {
                                                zzD(zzhVarZzj);
                                            }
                                        }
                                        this.zzv = false;
                                        zzae();
                                    }
                                } catch (SQLiteException e2) {
                                    e = e2;
                                    cursorRawQuery = null;
                                } catch (Throwable th) {
                                    th = th;
                                    cursor = null;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th;
                                }
                                if (!TextUtils.isEmpty(string)) {
                                    zzak zzakVar5 = this.zze;
                                    zzal(zzakVar5);
                                    zzhVarZzj = zzakVar5.zzj(string);
                                    if (zzhVarZzj != null) {
                                        zzD(zzhVarZzj);
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                cursor = null;
                            }
                        } else {
                            if (this.zzA == -1) {
                                zzak zzakVar6 = this.zze;
                                zzal(zzakVar6);
                                try {
                                    cursorRawQuery2 = zzakVar6.zzh().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                                    try {
                                        try {
                                            if (cursorRawQuery2.moveToFirst()) {
                                                j3 = cursorRawQuery2.getLong(0);
                                                if (cursorRawQuery2 != null) {
                                                    cursorRawQuery2.close();
                                                }
                                            } else if (cursorRawQuery2 != null) {
                                                cursorRawQuery2.close();
                                            }
                                        } catch (SQLiteException e3) {
                                            e = e3;
                                            zzakVar6.zzt.zzaA().zzd().zzb("Error querying raw events", e);
                                            if (cursorRawQuery2 != null) {
                                            }
                                            this.zzA = j3;
                                            iZze = zzg().zze(strZzr, zzeg.zzg);
                                            iMax = Math.max(0, zzg().zze(strZzr, zzeg.zzh));
                                            zzakVar = this.zze;
                                            zzal(zzakVar);
                                            zzakVar.zzg();
                                            zzakVar.zzW();
                                            if (iZze > 0) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            Preconditions.checkArgument(z);
                                            if (iMax > 0) {
                                                z2 = true;
                                            } else {
                                                z2 = false;
                                            }
                                            Preconditions.checkArgument(z2);
                                            Preconditions.checkNotEmpty(strZzr);
                                            cursorQuery = zzakVar.zzh().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{strZzr}, null, null, "rowid", String.valueOf(iZze));
                                            try {
                                                if (cursorQuery.moveToFirst()) {
                                                    arrayList2 = new ArrayList();
                                                    length = 0;
                                                    while (true) {
                                                        j2 = cursorQuery.getLong(i5);
                                                        try {
                                                            byte[] blob = cursorQuery.getBlob(i4);
                                                            zzlj zzljVar = zzakVar.zzf.zzi;
                                                            zzal(zzljVar);
                                                            try {
                                                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(blob);
                                                                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                                                                byteArrayOutputStream = new ByteArrayOutputStream();
                                                                bArr = new byte[1024];
                                                                j = jCurrentTimeMillis;
                                                                while (true) {
                                                                    try {
                                                                        try {
                                                                            i3 = gZIPInputStream.read(bArr);
                                                                            if (i3 <= 0) {
                                                                                break;
                                                                            } else {
                                                                                byteArrayOutputStream.write(bArr, 0, i3);
                                                                            }
                                                                        } catch (SQLiteException e4) {
                                                                            e = e4;
                                                                            zzakVar.zzt.zzaA().zzd().zzc("Error querying bundles. appId", zzet.zzn(strZzr), e);
                                                                            listEmptyList = Collections.emptyList();
                                                                            if (cursorQuery != null) {
                                                                                cursorQuery.close();
                                                                            }
                                                                        }
                                                                    } catch (IOException e5) {
                                                                        e = e5;
                                                                        try {
                                                                            zzljVar.zzt.zzaA().zzd().zzb("Failed to ungzip content", e);
                                                                            throw e;
                                                                        } catch (IOException e6) {
                                                                            e = e6;
                                                                            zzakVar.zzt.zzaA().zzd().zzc("Failed to unzip queued bundle. appId", zzet.zzn(strZzr), e);
                                                                            if (cursorQuery.moveToNext()) {
                                                                                break;
                                                                            } else {
                                                                                break;
                                                                            }
                                                                            if (cursorQuery != null) {
                                                                                cursorQuery.close();
                                                                            }
                                                                            listEmptyList = arrayList2;
                                                                            if (!listEmptyList.isEmpty()) {
                                                                                if (zzq(strZzr).zzj(zzha.AD_STORAGE)) {
                                                                                    it = listEmptyList.iterator();
                                                                                    while (true) {
                                                                                        if (!it.hasNext()) {
                                                                                            strZzK = null;
                                                                                            break;
                                                                                        }
                                                                                        zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) ((Pair) it.next()).first;
                                                                                        if (!zzgdVar2.zzK().isEmpty()) {
                                                                                            strZzK = zzgdVar2.zzK();
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    if (strZzK != null) {
                                                                                        for (i2 = 0; i2 < listEmptyList.size(); i2++) {
                                                                                            zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i2)).first;
                                                                                            if (zzgdVar.zzK().isEmpty()) {
                                                                                                listEmptyList = listEmptyList.subList(0, i2);
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                zzgaVarZza = com.google.android.gms.internal.measurement.zzgb.zza();
                                                                                size = listEmptyList.size();
                                                                                arrayList = new ArrayList(listEmptyList.size());
                                                                                if (zzg().zzt(strZzr)) {
                                                                                    z3 = false;
                                                                                } else {
                                                                                    z3 = false;
                                                                                }
                                                                                zZzj = zzq(strZzr).zzj(zzha.AD_STORAGE);
                                                                                zZzj2 = zzq(strZzr).zzj(zzha.ANALYTICS_STORAGE);
                                                                                zzqu.zzc();
                                                                                zZzs = zzg().zzs(strZzr, zzeg.zzao);
                                                                                i = 0;
                                                                                while (i < size) {
                                                                                    zzgcVar = (com.google.android.gms.internal.measurement.zzgc) ((com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i)).first).zzbB();
                                                                                    arrayList.add((Long) ((Pair) listEmptyList.get(i)).second);
                                                                                    zzg().zzh();
                                                                                    zzgcVar.zzam(79000L);
                                                                                    long j4 = j;
                                                                                    zzgcVar.zzal(j4);
                                                                                    this.zzn.zzay();
                                                                                    zzgcVar.zzag(false);
                                                                                    if (!z3) {
                                                                                        zzgcVar.zzq();
                                                                                    }
                                                                                    if (!zZzj) {
                                                                                        zzgcVar.zzx();
                                                                                        zzgcVar.zzt();
                                                                                    }
                                                                                    if (!zZzj2) {
                                                                                        zzgcVar.zzn();
                                                                                    }
                                                                                    zzC(strZzr, zzgcVar);
                                                                                    if (!zZzs) {
                                                                                        zzgcVar.zzy();
                                                                                    }
                                                                                    if (zzg().zzs(strZzr, zzeg.zzV)) {
                                                                                        byte[] bArrZzbx = ((com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD()).zzbx();
                                                                                        zzlj zzljVar2 = this.zzi;
                                                                                        zzal(zzljVar2);
                                                                                        zzgcVar.zzJ(zzljVar2.zzf(bArrZzbx));
                                                                                    }
                                                                                    zzgaVarZza.zza(zzgcVar);
                                                                                    i++;
                                                                                    j = j4;
                                                                                }
                                                                                long j5 = j;
                                                                                if (Log.isLoggable(zzaA().zzr(), 2)) {
                                                                                    zzlj zzljVar3 = this.zzi;
                                                                                    zzal(zzljVar3);
                                                                                    strZzo = zzljVar3.zzo((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD());
                                                                                } else {
                                                                                    strZzo = null;
                                                                                }
                                                                                zzal(this.zzi);
                                                                                byte[] bArrZzbx2 = ((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD()).zzbx();
                                                                                zzkvVarZza = this.zzl.zza(strZzr);
                                                                                try {
                                                                                    Preconditions.checkArgument(!arrayList.isEmpty());
                                                                                    if (this.zzy != null) {
                                                                                        zzaA().zzd().zza("Set uploading progress before finishing the previous upload");
                                                                                    } else {
                                                                                        this.zzy = new ArrayList(arrayList);
                                                                                    }
                                                                                    this.zzk.zzd.zzb(j5);
                                                                                    zzaA().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zzgaVarZza.zzb(0).zzy() : "?", Integer.valueOf(bArrZzbx2.length), strZzo);
                                                                                    this.zzu = true;
                                                                                    zzez zzezVar2 = this.zzd;
                                                                                    zzal(zzezVar2);
                                                                                    URL url = new URL(zzkvVarZza.zza());
                                                                                    Map mapZzb = zzkvVarZza.zzb();
                                                                                    zzky zzkyVar = new zzky(this, strZzr);
                                                                                    zzezVar2.zzg();
                                                                                    zzezVar2.zzW();
                                                                                    Preconditions.checkNotNull(url);
                                                                                    Preconditions.checkNotNull(bArrZzbx2);
                                                                                    Preconditions.checkNotNull(zzkyVar);
                                                                                    zzezVar2.zzt.zzaB().zzo(new zzey(zzezVar2, strZzr, url, bArrZzbx2, mapZzb, zzkyVar));
                                                                                } catch (MalformedURLException unused) {
                                                                                    zzaA().zzd().zzc("Failed to parse upload URL. Not uploading. appId", zzet.zzn(strZzr), zzkvVarZza.zza());
                                                                                }
                                                                            }
                                                                            this.zzv = false;
                                                                            zzae();
                                                                        }
                                                                    }
                                                                }
                                                                gZIPInputStream.close();
                                                                byteArrayInputStream.close();
                                                                byteArray = byteArrayOutputStream.toByteArray();
                                                                if (arrayList2.isEmpty()) {
                                                                }
                                                                try {
                                                                    zzgcVar2 = (com.google.android.gms.internal.measurement.zzgc) zzlj.zzm(com.google.android.gms.internal.measurement.zzgd.zzu(), byteArray);
                                                                    if (!cursorQuery.isNull(2)) {
                                                                        zzgcVar2.zzaf(cursorQuery.getInt(2));
                                                                    }
                                                                    length += byteArray.length;
                                                                    arrayList2.add(Pair.create((com.google.android.gms.internal.measurement.zzgd) zzgcVar2.zzaD(), Long.valueOf(j2)));
                                                                } catch (IOException e7) {
                                                                    zzakVar.zzt.zzaA().zzd().zzc("Failed to merge queued bundle. appId", zzet.zzn(strZzr), e7);
                                                                }
                                                            } catch (IOException e8) {
                                                                e = e8;
                                                                j = jCurrentTimeMillis;
                                                            }
                                                        } catch (IOException e9) {
                                                            e = e9;
                                                            j = jCurrentTimeMillis;
                                                        }
                                                        if (cursorQuery.moveToNext()) {
                                                            break;
                                                            break;
                                                        } else {
                                                            break;
                                                            break;
                                                        }
                                                        jCurrentTimeMillis = j;
                                                        i4 = 1;
                                                        i5 = 0;
                                                    }
                                                    if (cursorQuery != null) {
                                                        cursorQuery.close();
                                                    }
                                                    listEmptyList = arrayList2;
                                                } else {
                                                    listEmptyList = Collections.emptyList();
                                                    if (cursorQuery != null) {
                                                        cursorQuery.close();
                                                    }
                                                    j = jCurrentTimeMillis;
                                                }
                                            } catch (SQLiteException e10) {
                                                e = e10;
                                                j = jCurrentTimeMillis;
                                            }
                                            if (!listEmptyList.isEmpty()) {
                                                if (zzq(strZzr).zzj(zzha.AD_STORAGE)) {
                                                    it = listEmptyList.iterator();
                                                    while (true) {
                                                        if (!it.hasNext()) {
                                                            strZzK = null;
                                                            break;
                                                        }
                                                        zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) ((Pair) it.next()).first;
                                                        if (!zzgdVar2.zzK().isEmpty()) {
                                                            strZzK = zzgdVar2.zzK();
                                                            break;
                                                        }
                                                    }
                                                    if (strZzK != null) {
                                                        while (i2 < listEmptyList.size()) {
                                                            zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i2)).first;
                                                            if (zzgdVar.zzK().isEmpty()) {
                                                                listEmptyList = listEmptyList.subList(0, i2);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                                zzgaVarZza = com.google.android.gms.internal.measurement.zzgb.zza();
                                                size = listEmptyList.size();
                                                arrayList = new ArrayList(listEmptyList.size());
                                                if (zzg().zzt(strZzr)) {
                                                    z3 = false;
                                                } else {
                                                    z3 = false;
                                                }
                                                zZzj = zzq(strZzr).zzj(zzha.AD_STORAGE);
                                                zZzj2 = zzq(strZzr).zzj(zzha.ANALYTICS_STORAGE);
                                                zzqu.zzc();
                                                zZzs = zzg().zzs(strZzr, zzeg.zzao);
                                                i = 0;
                                                while (i < size) {
                                                    zzgcVar = (com.google.android.gms.internal.measurement.zzgc) ((com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i)).first).zzbB();
                                                    arrayList.add((Long) ((Pair) listEmptyList.get(i)).second);
                                                    zzg().zzh();
                                                    zzgcVar.zzam(79000L);
                                                    long j6 = j;
                                                    zzgcVar.zzal(j6);
                                                    this.zzn.zzay();
                                                    zzgcVar.zzag(false);
                                                    if (!z3) {
                                                        zzgcVar.zzq();
                                                    }
                                                    if (!zZzj) {
                                                        zzgcVar.zzx();
                                                        zzgcVar.zzt();
                                                    }
                                                    if (!zZzj2) {
                                                        zzgcVar.zzn();
                                                    }
                                                    zzC(strZzr, zzgcVar);
                                                    if (!zZzs) {
                                                        zzgcVar.zzy();
                                                    }
                                                    if (zzg().zzs(strZzr, zzeg.zzV)) {
                                                        byte[] bArrZzbx3 = ((com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD()).zzbx();
                                                        zzlj zzljVar4 = this.zzi;
                                                        zzal(zzljVar4);
                                                        zzgcVar.zzJ(zzljVar4.zzf(bArrZzbx3));
                                                    }
                                                    zzgaVarZza.zza(zzgcVar);
                                                    i++;
                                                    j = j6;
                                                }
                                                long j7 = j;
                                                if (Log.isLoggable(zzaA().zzr(), 2)) {
                                                    zzlj zzljVar5 = this.zzi;
                                                    zzal(zzljVar5);
                                                    strZzo = zzljVar5.zzo((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD());
                                                } else {
                                                    strZzo = null;
                                                }
                                                zzal(this.zzi);
                                                byte[] bArrZzbx4 = ((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD()).zzbx();
                                                zzkvVarZza = this.zzl.zza(strZzr);
                                                Preconditions.checkArgument(!arrayList.isEmpty());
                                                if (this.zzy != null) {
                                                    zzaA().zzd().zza("Set uploading progress before finishing the previous upload");
                                                } else {
                                                    this.zzy = new ArrayList(arrayList);
                                                }
                                                this.zzk.zzd.zzb(j7);
                                                zzaA().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zzgaVarZza.zzb(0).zzy() : "?", Integer.valueOf(bArrZzbx4.length), strZzo);
                                                this.zzu = true;
                                                zzez zzezVar3 = this.zzd;
                                                zzal(zzezVar3);
                                                URL url2 = new URL(zzkvVarZza.zza());
                                                Map mapZzb2 = zzkvVarZza.zzb();
                                                zzky zzkyVar2 = new zzky(this, strZzr);
                                                zzezVar3.zzg();
                                                zzezVar3.zzW();
                                                Preconditions.checkNotNull(url2);
                                                Preconditions.checkNotNull(bArrZzbx4);
                                                Preconditions.checkNotNull(zzkyVar2);
                                                zzezVar3.zzt.zzaB().zzo(new zzey(zzezVar3, strZzr, url2, bArrZzbx4, mapZzb2, zzkyVar2));
                                            }
                                            this.zzv = false;
                                            zzae();
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        cursor3 = cursorRawQuery2;
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                        throw th;
                                    }
                                } catch (SQLiteException e11) {
                                    e = e11;
                                    cursorRawQuery2 = null;
                                } catch (Throwable th4) {
                                    th = th4;
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                    throw th;
                                }
                                this.zzA = j3;
                            }
                            iZze = zzg().zze(strZzr, zzeg.zzg);
                            iMax = Math.max(0, zzg().zze(strZzr, zzeg.zzh));
                            zzakVar = this.zze;
                            zzal(zzakVar);
                            zzakVar.zzg();
                            zzakVar.zzW();
                            if (iZze > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            Preconditions.checkArgument(z);
                            if (iMax > 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            try {
                                Preconditions.checkArgument(z2);
                                Preconditions.checkNotEmpty(strZzr);
                                try {
                                    cursorQuery = zzakVar.zzh().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{strZzr}, null, null, "rowid", String.valueOf(iZze));
                                    if (cursorQuery.moveToFirst()) {
                                        listEmptyList = Collections.emptyList();
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        j = jCurrentTimeMillis;
                                    } else {
                                        arrayList2 = new ArrayList();
                                        length = 0;
                                        while (true) {
                                            j2 = cursorQuery.getLong(i5);
                                            byte[] blob2 = cursorQuery.getBlob(i4);
                                            zzlj zzljVar6 = zzakVar.zzf.zzi;
                                            zzal(zzljVar6);
                                            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(blob2);
                                            gZIPInputStream = new GZIPInputStream(byteArrayInputStream2);
                                            byteArrayOutputStream = new ByteArrayOutputStream();
                                            bArr = new byte[1024];
                                            j = jCurrentTimeMillis;
                                            while (true) {
                                                i3 = gZIPInputStream.read(bArr);
                                                if (i3 <= 0) {
                                                    break;
                                                    break;
                                                }
                                                byteArrayOutputStream.write(bArr, 0, i3);
                                            }
                                            gZIPInputStream.close();
                                            byteArrayInputStream2.close();
                                            byteArray = byteArrayOutputStream.toByteArray();
                                            if (arrayList2.isEmpty() && byteArray.length + length > iMax) {
                                                break;
                                            }
                                            zzgcVar2 = (com.google.android.gms.internal.measurement.zzgc) zzlj.zzm(com.google.android.gms.internal.measurement.zzgd.zzu(), byteArray);
                                            if (!cursorQuery.isNull(2)) {
                                                zzgcVar2.zzaf(cursorQuery.getInt(2));
                                            }
                                            length += byteArray.length;
                                            arrayList2.add(Pair.create((com.google.android.gms.internal.measurement.zzgd) zzgcVar2.zzaD(), Long.valueOf(j2)));
                                            if (cursorQuery.moveToNext() || length > iMax) {
                                                break;
                                                break;
                                            } else {
                                                jCurrentTimeMillis = j;
                                                i4 = 1;
                                                i5 = 0;
                                            }
                                        }
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                        listEmptyList = arrayList2;
                                    }
                                } catch (SQLiteException e12) {
                                    e = e12;
                                    j = jCurrentTimeMillis;
                                    cursorQuery = null;
                                } catch (Throwable th5) {
                                    th = th5;
                                    cursor2 = null;
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    throw th;
                                }
                                if (!listEmptyList.isEmpty()) {
                                    if (zzq(strZzr).zzj(zzha.AD_STORAGE)) {
                                        it = listEmptyList.iterator();
                                        while (true) {
                                            if (!it.hasNext()) {
                                                strZzK = null;
                                                break;
                                            }
                                            zzgdVar2 = (com.google.android.gms.internal.measurement.zzgd) ((Pair) it.next()).first;
                                            if (!zzgdVar2.zzK().isEmpty()) {
                                                strZzK = zzgdVar2.zzK();
                                                break;
                                            }
                                        }
                                        if (strZzK != null) {
                                            while (i2 < listEmptyList.size()) {
                                                zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i2)).first;
                                                if (zzgdVar.zzK().isEmpty() && !zzgdVar.zzK().equals(strZzK)) {
                                                    listEmptyList = listEmptyList.subList(0, i2);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    zzgaVarZza = com.google.android.gms.internal.measurement.zzgb.zza();
                                    size = listEmptyList.size();
                                    arrayList = new ArrayList(listEmptyList.size());
                                    if (zzg().zzt(strZzr) || !zzq(strZzr).zzj(zzha.AD_STORAGE)) {
                                        z3 = false;
                                    } else {
                                        z3 = true;
                                    }
                                    zZzj = zzq(strZzr).zzj(zzha.AD_STORAGE);
                                    zZzj2 = zzq(strZzr).zzj(zzha.ANALYTICS_STORAGE);
                                    zzqu.zzc();
                                    zZzs = zzg().zzs(strZzr, zzeg.zzao);
                                    i = 0;
                                    while (i < size) {
                                        zzgcVar = (com.google.android.gms.internal.measurement.zzgc) ((com.google.android.gms.internal.measurement.zzgd) ((Pair) listEmptyList.get(i)).first).zzbB();
                                        arrayList.add((Long) ((Pair) listEmptyList.get(i)).second);
                                        zzg().zzh();
                                        zzgcVar.zzam(79000L);
                                        long j8 = j;
                                        zzgcVar.zzal(j8);
                                        this.zzn.zzay();
                                        zzgcVar.zzag(false);
                                        if (!z3) {
                                            zzgcVar.zzq();
                                        }
                                        if (!zZzj) {
                                            zzgcVar.zzx();
                                            zzgcVar.zzt();
                                        }
                                        if (!zZzj2) {
                                            zzgcVar.zzn();
                                        }
                                        zzC(strZzr, zzgcVar);
                                        if (!zZzs) {
                                            zzgcVar.zzy();
                                        }
                                        if (zzg().zzs(strZzr, zzeg.zzV)) {
                                            byte[] bArrZzbx5 = ((com.google.android.gms.internal.measurement.zzgd) zzgcVar.zzaD()).zzbx();
                                            zzlj zzljVar7 = this.zzi;
                                            zzal(zzljVar7);
                                            zzgcVar.zzJ(zzljVar7.zzf(bArrZzbx5));
                                        }
                                        zzgaVarZza.zza(zzgcVar);
                                        i++;
                                        j = j8;
                                    }
                                    long j9 = j;
                                    if (Log.isLoggable(zzaA().zzr(), 2)) {
                                        zzlj zzljVar8 = this.zzi;
                                        zzal(zzljVar8);
                                        strZzo = zzljVar8.zzo((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD());
                                    } else {
                                        strZzo = null;
                                    }
                                    zzal(this.zzi);
                                    byte[] bArrZzbx6 = ((com.google.android.gms.internal.measurement.zzgb) zzgaVarZza.zzaD()).zzbx();
                                    zzkvVarZza = this.zzl.zza(strZzr);
                                    Preconditions.checkArgument(!arrayList.isEmpty());
                                    if (this.zzy != null) {
                                        zzaA().zzd().zza("Set uploading progress before finishing the previous upload");
                                    } else {
                                        this.zzy = new ArrayList(arrayList);
                                    }
                                    this.zzk.zzd.zzb(j9);
                                    zzaA().zzj().zzd("Uploading data. app, uncompressed size, data", size > 0 ? zzgaVarZza.zzb(0).zzy() : "?", Integer.valueOf(bArrZzbx6.length), strZzo);
                                    this.zzu = true;
                                    zzez zzezVar4 = this.zzd;
                                    zzal(zzezVar4);
                                    URL url3 = new URL(zzkvVarZza.zza());
                                    Map mapZzb3 = zzkvVarZza.zzb();
                                    zzky zzkyVar3 = new zzky(this, strZzr);
                                    zzezVar4.zzg();
                                    zzezVar4.zzW();
                                    Preconditions.checkNotNull(url3);
                                    Preconditions.checkNotNull(bArrZzbx6);
                                    Preconditions.checkNotNull(zzkyVar3);
                                    zzezVar4.zzt.zzaB().zzo(new zzey(zzezVar4, strZzr, url3, bArrZzbx6, mapZzb3, zzkyVar3));
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                cursor2 = cursorRawQuery2;
                            }
                        }
                        this.zzv = false;
                    } else {
                        zzaA().zzj().zza("Network not connected, ignoring upload request");
                        zzag();
                        this.zzv = false;
                    }
                }
            }
            zzae();
        } catch (Throwable th7) {
            this.zzv = false;
            zzae();
            throw th7;
        }
    }

    /* JADX WARN: Code duplicated, block: B:220:0x07a3  */
    /* JADX WARN: Code duplicated, block: B:52:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:56:0x01e3 A[Catch: all -> 0x0b2d, TRY_ENTER, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x01fa A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0247 A[Catch: all -> 0x0b2d, TRY_ENTER, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0257 A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0265 A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x027a A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0280  */
    /* JADX WARN: Code duplicated, block: B:79:0x0281 A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x02ab A[Catch: all -> 0x0b2d, TRY_LEAVE, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x0319 A[Catch: all -> 0x0b2d, TryCatch #8 {all -> 0x0b2d, blocks: (B:38:0x019f, B:41:0x01af, B:43:0x01b9, B:48:0x01c5, B:95:0x0349, B:104:0x037f, B:106:0x03bd, B:108:0x03c2, B:109:0x03d9, B:113:0x03ec, B:115:0x0404, B:117:0x040b, B:118:0x0422, B:123:0x044c, B:127:0x046f, B:128:0x0486, B:131:0x0497, B:134:0x04b4, B:135:0x04c8, B:137:0x04d2, B:139:0x04df, B:141:0x04e5, B:142:0x04ee, B:143:0x04fc, B:145:0x0511, B:155:0x0545, B:156:0x055a, B:158:0x057f, B:161:0x0597, B:164:0x05da, B:166:0x0606, B:168:0x0645, B:169:0x064a, B:171:0x0652, B:172:0x0657, B:174:0x065f, B:175:0x0664, B:177:0x066f, B:179:0x067c, B:181:0x068a, B:182:0x068f, B:184:0x0698, B:185:0x069c, B:187:0x06a9, B:188:0x06ae, B:190:0x06d7, B:192:0x06df, B:193:0x06e4, B:195:0x06ec, B:196:0x06ef, B:198:0x0713, B:201:0x071e, B:204:0x0726, B:205:0x0740, B:207:0x0746, B:209:0x075a, B:211:0x0766, B:213:0x0773, B:217:0x078d, B:218:0x079d, B:222:0x07a6, B:223:0x07a9, B:225:0x07c7, B:227:0x07cb, B:229:0x07dd, B:231:0x07e1, B:233:0x07ec, B:234:0x07f7, B:236:0x083e, B:237:0x0843, B:239:0x084b, B:241:0x0855, B:242:0x0858, B:244:0x0865, B:246:0x0887, B:247:0x0894, B:248:0x08ca, B:250:0x08d2, B:252:0x08dc, B:253:0x08e9, B:255:0x08f3, B:256:0x0900, B:257:0x090c, B:259:0x0912, B:261:0x094c, B:263:0x095c, B:265:0x0966, B:267:0x0979, B:269:0x097f, B:270:0x09c5, B:272:0x09cf, B:273:0x09d2, B:274:0x09de, B:276:0x09e4, B:285:0x0a31, B:286:0x0a7f, B:288:0x0a8e, B:308:0x0afa, B:291:0x0aa6, B:293:0x0aaa, B:279:0x09f0, B:281:0x0a1c, B:307:0x0ae5, B:302:0x0ac9, B:303:0x0ae0, B:199:0x0718, B:165:0x05f8, B:152:0x052a, B:98:0x035f, B:99:0x0366, B:101:0x036c, B:103:0x0378, B:53:0x01d7, B:56:0x01e3, B:58:0x01fa, B:63:0x0213, B:70:0x0251, B:72:0x0257, B:74:0x0265, B:76:0x027a, B:79:0x0281, B:92:0x030e, B:94:0x0319, B:80:0x02ab, B:81:0x02c8, B:83:0x02ce, B:91:0x02f5, B:90:0x02e2, B:66:0x0221, B:69:0x0247), top: B:330:0x019f, inners: #1, #10 }] */
    final void zzY(zzau zzauVar, zzq zzqVar) {
        String strZzg;
        long jLongValue;
        String upperCase;
        String strConcat;
        zzlm zzlmVarZzp;
        zzak zzakVar;
        String str;
        zzlm zzlmVar;
        String str2;
        zzak zzakVar2;
        Object obj;
        double dDoubleValue;
        long length;
        long jDelete;
        zzaq zzaqVarZzc;
        ArrayList arrayList;
        com.google.android.gms.internal.measurement.zzgc zzgcVar;
        int i;
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        long jNanoTime = System.nanoTime();
        zzaB().zzg();
        zzB();
        String str3 = zzqVar.zza;
        zzal(this.zzi);
        if (zzlj.zzB(zzauVar, zzqVar)) {
            if (!zzqVar.zzh) {
                zzd(zzqVar);
                return;
            }
            zzfu zzfuVar = this.zzc;
            zzal(zzfuVar);
            String str4 = "_err";
            if (zzfuVar.zzr(str3, zzauVar.zza)) {
                zzaA().zzk().zzc("Dropping blocked event. appId", zzet.zzn(str3), this.zzn.zzj().zzd(zzauVar.zza));
                zzfu zzfuVar2 = this.zzc;
                zzal(zzfuVar2);
                if (!zzfuVar2.zzp(str3)) {
                    zzfu zzfuVar3 = this.zzc;
                    zzal(zzfuVar3);
                    if (!zzfuVar3.zzs(str3)) {
                        if ("_err".equals(zzauVar.zza)) {
                            return;
                        }
                        zzv().zzO(this.zzF, str3, 11, "_ev", zzauVar.zza, 0);
                        return;
                    }
                }
                zzak zzakVar3 = this.zze;
                zzal(zzakVar3);
                zzh zzhVarZzj = zzakVar3.zzj(str3);
                if (zzhVarZzj != null) {
                    long jAbs = Math.abs(zzax().currentTimeMillis() - Math.max(zzhVarZzj.zzl(), zzhVarZzj.zzc()));
                    zzg();
                    if (jAbs > ((Long) zzeg.zzz.zza(null)).longValue()) {
                        zzaA().zzc().zza("Fetching config for blocked app");
                        zzD(zzhVarZzj);
                        return;
                    }
                    return;
                }
                return;
            }
            zzeu zzeuVarZzb = zzeu.zzb(zzauVar);
            zzv().zzN(zzeuVarZzb, zzg().zzd(str3));
            zzpq.zzc();
            int iZzf = zzg().zzs(null, zzeg.zzaA) ? zzg().zzf(str3, zzeg.zzQ, 10, 35) : 0;
            Iterator it = new TreeSet(zzeuVarZzb.zzd.keySet()).iterator();
            while (it.hasNext()) {
                String str5 = (String) it.next();
                if (FirebaseAnalytics.Param.ITEMS.equals(str5)) {
                    zzlp zzlpVarZzv = zzv();
                    Iterator it2 = it;
                    Parcelable[] parcelableArray = zzeuVarZzb.zzd.getParcelableArray(str5);
                    zzpq.zzc();
                    zzlpVarZzv.zzM(parcelableArray, iZzf, zzg().zzs(null, zzeg.zzaA));
                    it = it2;
                    str4 = str4;
                    jNanoTime = jNanoTime;
                }
            }
            long j = jNanoTime;
            String str6 = str4;
            zzau zzauVarZza = zzeuVarZzb.zza();
            if (Log.isLoggable(zzaA().zzr(), 2)) {
                zzaA().zzj().zzb("Logging event", this.zzn.zzj().zzc(zzauVarZza));
            }
            zzpn.zzc();
            zzg().zzs(null, zzeg.zzax);
            zzak zzakVar4 = this.zze;
            zzal(zzakVar4);
            zzakVar4.zzw();
            try {
                zzd(zzqVar);
                boolean z = "ecommerce_purchase".equals(zzauVarZza.zza) || FirebaseAnalytics.Event.PURCHASE.equals(zzauVarZza.zza) || FirebaseAnalytics.Event.REFUND.equals(zzauVarZza.zza);
                if ("_iap".equals(zzauVarZza.zza)) {
                    strZzg = zzauVarZza.zzb.zzg(FirebaseAnalytics.Param.CURRENCY);
                    if (z) {
                        dDoubleValue = zzauVarZza.zzb.zzd("value").doubleValue() * 1000000.0d;
                        if (dDoubleValue == 0.0d) {
                            dDoubleValue = zzauVarZza.zzb.zze("value").longValue() * 1000000.0d;
                        }
                        if (dDoubleValue <= 9.223372036854776E18d || dDoubleValue < -9.223372036854776E18d) {
                            zzaA().zzk().zzc("Data lost. Currency value is too big. appId", zzet.zzn(str3), Double.valueOf(dDoubleValue));
                            zzak zzakVar5 = this.zze;
                            zzal(zzakVar5);
                            zzakVar5.zzC();
                            zzak zzakVar6 = this.zze;
                            zzal(zzakVar6);
                            zzakVar6.zzx();
                            return;
                        }
                        jLongValue = Math.round(dDoubleValue);
                        if (FirebaseAnalytics.Event.REFUND.equals(zzauVarZza.zza)) {
                            jLongValue = -jLongValue;
                        }
                    } else {
                        jLongValue = zzauVarZza.zzb.zze("value").longValue();
                    }
                    if (TextUtils.isEmpty(strZzg)) {
                        str2 = str6;
                    } else {
                        upperCase = strZzg.toUpperCase(Locale.US);
                        if (upperCase.matches("[A-Z]{3}")) {
                            strConcat = "_ltv_".concat(String.valueOf(upperCase));
                            zzak zzakVar7 = this.zze;
                            zzal(zzakVar7);
                            zzlmVarZzp = zzakVar7.zzp(str3, strConcat);
                            if (zzlmVarZzp != null) {
                                obj = zzlmVarZzp.zze;
                                if (obj instanceof Long) {
                                    str2 = str6;
                                    zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(((Long) obj).longValue() + jLongValue));
                                } else {
                                    zzakVar = this.zze;
                                    zzal(zzakVar);
                                    int iZze = zzg().zze(str3, zzeg.zzE) - 1;
                                    Preconditions.checkNotEmpty(str3);
                                    zzakVar.zzg();
                                    zzakVar.zzW();
                                    try {
                                        str = str6;
                                        try {
                                            zzakVar.zzh().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str3, str3, String.valueOf(iZze)});
                                        } catch (SQLiteException e) {
                                            e = e;
                                            zzakVar.zzt.zzaA().zzd().zzc("Error pruning currencies. appId", zzet.zzn(str3), e);
                                        }
                                    } catch (SQLiteException e2) {
                                        e = e2;
                                        str = str6;
                                    }
                                    str2 = str;
                                    zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(jLongValue));
                                }
                            } else {
                                zzakVar = this.zze;
                                zzal(zzakVar);
                                int iZze2 = zzg().zze(str3, zzeg.zzE) - 1;
                                Preconditions.checkNotEmpty(str3);
                                zzakVar.zzg();
                                zzakVar.zzW();
                                str = str6;
                                zzakVar.zzh().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str3, str3, String.valueOf(iZze2)});
                                str2 = str;
                                zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(jLongValue));
                            }
                            zzakVar2 = this.zze;
                            zzal(zzakVar2);
                            if (!zzakVar2.zzL(zzlmVar)) {
                                zzaA().zzd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzet.zzn(str3), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                                zzv().zzO(this.zzF, str3, 9, null, null, 0);
                            }
                        } else {
                            str2 = str6;
                        }
                    }
                } else if (z) {
                    z = true;
                    strZzg = zzauVarZza.zzb.zzg(FirebaseAnalytics.Param.CURRENCY);
                    if (z) {
                        dDoubleValue = zzauVarZza.zzb.zzd("value").doubleValue() * 1000000.0d;
                        if (dDoubleValue == 0.0d) {
                            dDoubleValue = zzauVarZza.zzb.zze("value").longValue() * 1000000.0d;
                        }
                        if (dDoubleValue <= 9.223372036854776E18d) {
                        }
                        zzaA().zzk().zzc("Data lost. Currency value is too big. appId", zzet.zzn(str3), Double.valueOf(dDoubleValue));
                        zzak zzakVar8 = this.zze;
                        zzal(zzakVar8);
                        zzakVar8.zzC();
                        zzak zzakVar9 = this.zze;
                        zzal(zzakVar9);
                        zzakVar9.zzx();
                        return;
                    }
                    jLongValue = zzauVarZza.zzb.zze("value").longValue();
                    if (TextUtils.isEmpty(strZzg)) {
                        upperCase = strZzg.toUpperCase(Locale.US);
                        if (upperCase.matches("[A-Z]{3}")) {
                            strConcat = "_ltv_".concat(String.valueOf(upperCase));
                            zzak zzakVar10 = this.zze;
                            zzal(zzakVar10);
                            zzlmVarZzp = zzakVar10.zzp(str3, strConcat);
                            if (zzlmVarZzp != null) {
                                obj = zzlmVarZzp.zze;
                                if (obj instanceof Long) {
                                    zzakVar = this.zze;
                                    zzal(zzakVar);
                                    int iZze3 = zzg().zze(str3, zzeg.zzE) - 1;
                                    Preconditions.checkNotEmpty(str3);
                                    zzakVar.zzg();
                                    zzakVar.zzW();
                                    str = str6;
                                    zzakVar.zzh().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str3, str3, String.valueOf(iZze3)});
                                    str2 = str;
                                    zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(jLongValue));
                                } else {
                                    str2 = str6;
                                    zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(((Long) obj).longValue() + jLongValue));
                                }
                            } else {
                                zzakVar = this.zze;
                                zzal(zzakVar);
                                int iZze4 = zzg().zze(str3, zzeg.zzE) - 1;
                                Preconditions.checkNotEmpty(str3);
                                zzakVar.zzg();
                                zzakVar.zzW();
                                str = str6;
                                zzakVar.zzh().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str3, str3, String.valueOf(iZze4)});
                                str2 = str;
                                zzlmVar = new zzlm(str3, zzauVarZza.zzc, strConcat, zzax().currentTimeMillis(), Long.valueOf(jLongValue));
                            }
                            zzakVar2 = this.zze;
                            zzal(zzakVar2);
                            if (!zzakVar2.zzL(zzlmVar)) {
                                zzaA().zzd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzet.zzn(str3), this.zzn.zzj().zzf(zzlmVar.zzc), zzlmVar.zze);
                                zzv().zzO(this.zzF, str3, 9, null, null, 0);
                            }
                        } else {
                            str2 = str6;
                        }
                    } else {
                        str2 = str6;
                    }
                } else {
                    str2 = str6;
                }
                boolean zZzak = zzlp.zzak(zzauVarZza.zza);
                boolean zEquals = str2.equals(zzauVarZza.zza);
                zzv();
                zzas zzasVar = zzauVarZza.zzb;
                if (zzasVar == null) {
                    length = 0;
                } else {
                    zzar zzarVar = new zzar(zzasVar);
                    length = 0;
                    while (zzarVar.hasNext()) {
                        Object objZzf = zzasVar.zzf(zzarVar.next());
                        if (objZzf instanceof Parcelable[]) {
                            length += (long) ((Parcelable[]) objZzf).length;
                        }
                    }
                }
                zzak zzakVar11 = this.zze;
                zzal(zzakVar11);
                zzai zzaiVarZzm = zzakVar11.zzm(zza(), str3, length + 1, true, zZzak, false, zEquals, false);
                long j2 = zzaiVarZzm.zzb;
                zzg();
                long jIntValue = j2 - ((long) ((Integer) zzeg.zzk.zza(null)).intValue());
                if (jIntValue > 0) {
                    if (jIntValue % 1000 == 1) {
                        zzaA().zzd().zzc("Data loss. Too many events logged. appId, count", zzet.zzn(str3), Long.valueOf(zzaiVarZzm.zzb));
                    }
                    zzak zzakVar12 = this.zze;
                    zzal(zzakVar12);
                    zzakVar12.zzC();
                    zzak zzakVar13 = this.zze;
                    zzal(zzakVar13);
                    zzakVar13.zzx();
                    return;
                }
                if (zZzak) {
                    long j3 = zzaiVarZzm.zza;
                    zzg();
                    long jIntValue2 = j3 - ((long) ((Integer) zzeg.zzm.zza(null)).intValue());
                    if (jIntValue2 > 0) {
                        if (jIntValue2 % 1000 == 1) {
                            zzaA().zzd().zzc("Data loss. Too many public events logged. appId, count", zzet.zzn(str3), Long.valueOf(zzaiVarZzm.zza));
                        }
                        zzv().zzO(this.zzF, str3, 16, "_ev", zzauVarZza.zza, 0);
                        zzak zzakVar14 = this.zze;
                        zzal(zzakVar14);
                        zzakVar14.zzC();
                        zzak zzakVar15 = this.zze;
                        zzal(zzakVar15);
                        zzakVar15.zzx();
                        return;
                    }
                }
                if (zEquals) {
                    long jMax = zzaiVarZzm.zzd - ((long) Math.max(0, Math.min(1000000, zzg().zze(zzqVar.zza, zzeg.zzl))));
                    if (jMax > 0) {
                        if (jMax == 1) {
                            zzaA().zzd().zzc("Too many error events logged. appId, count", zzet.zzn(str3), Long.valueOf(zzaiVarZzm.zzd));
                        }
                        zzak zzakVar16 = this.zze;
                        zzal(zzakVar16);
                        zzakVar16.zzC();
                        zzak zzakVar17 = this.zze;
                        zzal(zzakVar17);
                        zzakVar17.zzx();
                        return;
                    }
                }
                Bundle bundleZzc = zzauVarZza.zzb.zzc();
                zzv().zzP(bundleZzc, "_o", zzauVarZza.zzc);
                if (zzv().zzaf(str3)) {
                    zzv().zzP(bundleZzc, "_dbg", 1L);
                    zzv().zzP(bundleZzc, "_r", 1L);
                }
                if ("_s".equals(zzauVarZza.zza)) {
                    zzak zzakVar18 = this.zze;
                    zzal(zzakVar18);
                    zzlm zzlmVarZzp2 = zzakVar18.zzp(zzqVar.zza, "_sno");
                    if (zzlmVarZzp2 != null && (zzlmVarZzp2.zze instanceof Long)) {
                        zzv().zzP(bundleZzc, "_sno", zzlmVarZzp2.zze);
                    }
                }
                zzak zzakVar19 = this.zze;
                zzal(zzakVar19);
                Preconditions.checkNotEmpty(str3);
                zzakVar19.zzg();
                zzakVar19.zzW();
                try {
                    try {
                        jDelete = zzakVar19.zzh().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str3, String.valueOf(Math.max(0, Math.min(1000000, zzakVar19.zzt.zzf().zze(str3, zzeg.zzp))))});
                    } catch (SQLiteException e3) {
                        e = e3;
                        zzakVar19.zzt.zzaA().zzd().zzc("Error deleting over the limit events. appId", zzet.zzn(str3), e);
                        jDelete = 0;
                    }
                } catch (SQLiteException e4) {
                    e = e4;
                }
                if (jDelete > 0) {
                    zzaA().zzk().zzc("Data lost. Too many events stored on disk, deleted. appId", zzet.zzn(str3), Long.valueOf(jDelete));
                }
                zzap zzapVar = new zzap(this.zzn, zzauVarZza.zzc, str3, zzauVarZza.zza, zzauVarZza.zzd, 0L, bundleZzc);
                zzak zzakVar20 = this.zze;
                zzal(zzakVar20);
                zzaq zzaqVarZzn = zzakVar20.zzn(str3, zzapVar.zzb);
                if (zzaqVarZzn == null) {
                    zzak zzakVar21 = this.zze;
                    zzal(zzakVar21);
                    if (zzakVar21.zzf(str3) >= zzg().zzb(str3) && zZzak) {
                        zzaA().zzd().zzd("Too many event names used, ignoring event. appId, name, supported count", zzet.zzn(str3), this.zzn.zzj().zzd(zzapVar.zzb), Integer.valueOf(zzg().zzb(str3)));
                        zzv().zzO(this.zzF, str3, 8, null, null, 0);
                        zzak zzakVar22 = this.zze;
                        zzal(zzakVar22);
                        zzakVar22.zzx();
                        return;
                    }
                    zzaqVarZzc = new zzaq(str3, zzapVar.zzb, 0L, 0L, 0L, zzapVar.zzd, 0L, null, null, null, null);
                } else {
                    zzapVar = zzapVar.zza(this.zzn, zzaqVarZzn.zzf);
                    zzaqVarZzc = zzaqVarZzn.zzc(zzapVar.zzd);
                }
                zzak zzakVar23 = this.zze;
                zzal(zzakVar23);
                zzakVar23.zzE(zzaqVarZzc);
                zzaB().zzg();
                zzB();
                Preconditions.checkNotNull(zzapVar);
                Preconditions.checkNotNull(zzqVar);
                Preconditions.checkNotEmpty(zzapVar.zza);
                Preconditions.checkArgument(zzapVar.zza.equals(zzqVar.zza));
                com.google.android.gms.internal.measurement.zzgc zzgcVarZzu = com.google.android.gms.internal.measurement.zzgd.zzu();
                zzgcVarZzu.zzad(1);
                zzgcVarZzu.zzZ("android");
                if (!TextUtils.isEmpty(zzqVar.zza)) {
                    zzgcVarZzu.zzD(zzqVar.zza);
                }
                if (!TextUtils.isEmpty(zzqVar.zzd)) {
                    zzgcVarZzu.zzF(zzqVar.zzd);
                }
                if (!TextUtils.isEmpty(zzqVar.zzc)) {
                    zzgcVarZzu.zzG(zzqVar.zzc);
                }
                zzqu.zzc();
                if (!TextUtils.isEmpty(zzqVar.zzx) && (zzg().zzs(null, zzeg.zzam) || zzg().zzs(zzqVar.zza, zzeg.zzao))) {
                    zzgcVarZzu.zzah(zzqVar.zzx);
                }
                long j4 = zzqVar.zzj;
                if (j4 != -2147483648L) {
                    zzgcVarZzu.zzH((int) j4);
                }
                zzgcVarZzu.zzV(zzqVar.zze);
                if (!TextUtils.isEmpty(zzqVar.zzb)) {
                    zzgcVarZzu.zzU(zzqVar.zzb);
                }
                zzgcVarZzu.zzL(zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100)).zzi());
                if (zzgcVarZzu.zzar().isEmpty() && !TextUtils.isEmpty(zzqVar.zzq)) {
                    zzgcVarZzu.zzC(zzqVar.zzq);
                }
                long j5 = zzqVar.zzf;
                if (j5 != 0) {
                    zzgcVarZzu.zzM(j5);
                }
                zzgcVarZzu.zzP(zzqVar.zzs);
                zzlj zzljVar = this.zzi;
                zzal(zzljVar);
                com.google.android.gms.internal.measurement.zzhf zzhfVarZza = com.google.android.gms.internal.measurement.zzhf.zza(zzljVar.zzf.zzn.zzaw().getContentResolver(), com.google.android.gms.internal.measurement.zzhq.zza("com.google.android.gms.measurement"), new Runnable() { // from class: com.google.android.gms.measurement.internal.zzaw
                    @Override // java.lang.Runnable
                    public final void run() {
                        com.google.android.gms.internal.measurement.zzib.zzc();
                    }
                });
                Map mapEmptyMap = zzhfVarZza == null ? Collections.emptyMap() : zzhfVarZza.zzc();
                if (mapEmptyMap == null || mapEmptyMap.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList();
                    int iIntValue = ((Integer) zzeg.zzP.zza(null)).intValue();
                    for (Map.Entry entry : mapEmptyMap.entrySet()) {
                        if (((String) entry.getKey()).startsWith("measurement.id.")) {
                            try {
                                int i2 = Integer.parseInt((String) entry.getValue());
                                if (i2 != 0) {
                                    arrayList.add(Integer.valueOf(i2));
                                    if (arrayList.size() >= iIntValue) {
                                        zzljVar.zzt.zzaA().zzk().zzb("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                                        break;
                                    }
                                    continue;
                                } else {
                                    continue;
                                }
                            } catch (NumberFormatException e5) {
                                zzljVar.zzt.zzaA().zzk().zzb("Experiment ID NumberFormatException", e5);
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        arrayList = null;
                    }
                }
                if (arrayList != null) {
                    zzgcVarZzu.zzh(arrayList);
                }
                zzhb zzhbVarZzd = zzq((String) Preconditions.checkNotNull(zzqVar.zza)).zzd(zzhb.zzc(zzqVar.zzv, 100));
                if (zzhbVarZzd.zzj(zzha.AD_STORAGE) && zzqVar.zzo) {
                    Pair pairZzd = this.zzk.zzd(zzqVar.zza, zzhbVarZzd);
                    if (!TextUtils.isEmpty((CharSequence) pairZzd.first) && zzqVar.zzo) {
                        zzgcVarZzu.zzae((String) pairZzd.first);
                        if (pairZzd.second != null) {
                            zzgcVarZzu.zzX(((Boolean) pairZzd.second).booleanValue());
                        }
                    }
                }
                this.zzn.zzg().zzv();
                zzgcVarZzu.zzN(Build.MODEL);
                this.zzn.zzg().zzv();
                zzgcVarZzu.zzY(Build.VERSION.RELEASE);
                zzgcVarZzu.zzak((int) this.zzn.zzg().zzb());
                zzgcVarZzu.zzao(this.zzn.zzg().zzc());
                zzpz.zzc();
                if (zzg().zzs(null, zzeg.zzaE)) {
                    zzgcVarZzu.zzaj(zzqVar.zzz);
                }
                if (this.zzn.zzJ()) {
                    zzgcVarZzu.zzaq();
                    if (!TextUtils.isEmpty(null)) {
                        zzgcVarZzu.zzO(null);
                    }
                }
                zzak zzakVar24 = this.zze;
                zzal(zzakVar24);
                zzh zzhVarZzj2 = zzakVar24.zzj(zzqVar.zza);
                if (zzhVarZzj2 == null) {
                    zzhVarZzj2 = new zzh(this.zzn, zzqVar.zza);
                    zzhVarZzj2.zzJ(zzw(zzhbVarZzd));
                    zzhVarZzj2.zzX(zzqVar.zzk);
                    zzhVarZzj2.zzY(zzqVar.zzb);
                    if (zzhbVarZzd.zzj(zzha.AD_STORAGE)) {
                        zzhVarZzj2.zzag(this.zzk.zzf(zzqVar.zza, zzqVar.zzo));
                    }
                    zzhVarZzj2.zzac(0L);
                    zzhVarZzj2.zzad(0L);
                    zzhVarZzj2.zzab(0L);
                    zzhVarZzj2.zzL(zzqVar.zzc);
                    zzhVarZzj2.zzM(zzqVar.zzj);
                    zzhVarZzj2.zzK(zzqVar.zzd);
                    zzhVarZzj2.zzZ(zzqVar.zze);
                    zzhVarZzj2.zzU(zzqVar.zzf);
                    zzhVarZzj2.zzae(zzqVar.zzh);
                    zzhVarZzj2.zzV(zzqVar.zzs);
                    zzak zzakVar25 = this.zze;
                    zzal(zzakVar25);
                    zzakVar25.zzD(zzhVarZzj2);
                }
                if (zzhbVarZzd.zzj(zzha.ANALYTICS_STORAGE) && !TextUtils.isEmpty(zzhVarZzj2.zzw())) {
                    zzgcVarZzu.zzE((String) Preconditions.checkNotNull(zzhVarZzj2.zzw()));
                }
                if (!TextUtils.isEmpty(zzhVarZzj2.zzz())) {
                    zzgcVarZzu.zzT((String) Preconditions.checkNotNull(zzhVarZzj2.zzz()));
                }
                zzak zzakVar26 = this.zze;
                zzal(zzakVar26);
                List listZzu = zzakVar26.zzu(zzqVar.zza);
                for (int i3 = 0; i3 < listZzu.size(); i3++) {
                    com.google.android.gms.internal.measurement.zzgl zzglVarZzd = com.google.android.gms.internal.measurement.zzgm.zzd();
                    zzglVarZzd.zzf(((zzlm) listZzu.get(i3)).zzc);
                    zzglVarZzd.zzg(((zzlm) listZzu.get(i3)).zzd);
                    zzlj zzljVar2 = this.zzi;
                    zzal(zzljVar2);
                    zzljVar2.zzv(zzglVarZzd, ((zzlm) listZzu.get(i3)).zze);
                    zzgcVarZzu.zzl(zzglVarZzd);
                    if (zzg().zzs(null, zzeg.zzaH) && "_sid".equals(((zzlm) listZzu.get(i3)).zzc) && zzhVarZzj2.zzq() != 0) {
                        zzlj zzljVar3 = this.zzi;
                        zzal(zzljVar3);
                        if (zzljVar3.zzd(zzqVar.zzx) != zzhVarZzj2.zzq()) {
                            zzgcVarZzu.zzy();
                        }
                    }
                }
                try {
                    zzak zzakVar27 = this.zze;
                    zzal(zzakVar27);
                    com.google.android.gms.internal.measurement.zzgd zzgdVar = (com.google.android.gms.internal.measurement.zzgd) zzgcVarZzu.zzaD();
                    zzakVar27.zzg();
                    zzakVar27.zzW();
                    Preconditions.checkNotNull(zzgdVar);
                    Preconditions.checkNotEmpty(zzgdVar.zzy());
                    byte[] bArrZzbx = zzgdVar.zzbx();
                    zzlj zzljVar4 = zzakVar27.zzf.zzi;
                    zzal(zzljVar4);
                    long jZzf = zzljVar4.zzf(bArrZzbx);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", zzgdVar.zzy());
                    contentValues.put("metadata_fingerprint", Long.valueOf(jZzf));
                    contentValues.put("metadata", bArrZzbx);
                    try {
                        zzgcVar = zzgcVarZzu;
                        try {
                            try {
                                zzakVar27.zzh().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                                zzak zzakVar28 = this.zze;
                                zzal(zzakVar28);
                                zzar zzarVar2 = new zzar(zzapVar.zzf);
                                while (true) {
                                    if (!zzarVar2.hasNext()) {
                                        zzfu zzfuVar4 = this.zzc;
                                        zzal(zzfuVar4);
                                        boolean zZzq = zzfuVar4.zzq(zzapVar.zza, zzapVar.zzb);
                                        zzak zzakVar29 = this.zze;
                                        zzal(zzakVar29);
                                        zzai zzaiVarZzl = zzakVar29.zzl(zza(), zzapVar.zza, false, false, false, false, false);
                                        if (!zZzq || zzaiVarZzl.zze >= zzg().zze(zzapVar.zza, zzeg.zzo)) {
                                            i = 0;
                                            break;
                                        }
                                    } else if ("_r".equals(zzarVar2.next())) {
                                    }
                                    i = 1;
                                    break;
                                }
                                zzakVar28.zzg();
                                zzakVar28.zzW();
                                Preconditions.checkNotNull(zzapVar);
                                Preconditions.checkNotEmpty(zzapVar.zza);
                                zzlj zzljVar5 = zzakVar28.zzf.zzi;
                                zzal(zzljVar5);
                                byte[] bArrZzbx2 = zzljVar5.zzl(zzapVar).zzbx();
                                ContentValues contentValues2 = new ContentValues();
                                contentValues2.put("app_id", zzapVar.zza);
                                contentValues2.put("name", zzapVar.zzb);
                                contentValues2.put("timestamp", Long.valueOf(zzapVar.zzd));
                                contentValues2.put("metadata_fingerprint", Long.valueOf(jZzf));
                                contentValues2.put("data", bArrZzbx2);
                                contentValues2.put("realtime", Integer.valueOf(i));
                                try {
                                    if (zzakVar28.zzh().insert("raw_events", null, contentValues2) == -1) {
                                        zzakVar28.zzt.zzaA().zzd().zzb("Failed to insert raw event (got -1). appId", zzet.zzn(zzapVar.zza));
                                    } else {
                                        this.zza = 0L;
                                    }
                                } catch (SQLiteException e6) {
                                    zzakVar28.zzt.zzaA().zzd().zzc("Error storing raw event. appId", zzet.zzn(zzapVar.zza), e6);
                                }
                            } catch (SQLiteException e7) {
                                e = e7;
                                SQLiteException sQLiteException = e;
                                zzakVar27.zzt.zzaA().zzd().zzc("Error storing raw event metadata. appId", zzet.zzn(zzgdVar.zzy()), sQLiteException);
                                throw sQLiteException;
                            }
                        } catch (IOException e8) {
                            e = e8;
                            zzaA().zzd().zzc("Data loss. Failed to insert raw event metadata. appId", zzet.zzn(zzgcVar.zzaq()), e);
                        }
                    } catch (SQLiteException e9) {
                        e = e9;
                        zzgcVar = zzgcVarZzu;
                    }
                } catch (IOException e10) {
                    e = e10;
                    zzgcVar = zzgcVarZzu;
                }
                zzak zzakVar30 = this.zze;
                zzal(zzakVar30);
                zzakVar30.zzC();
                zzak zzakVar31 = this.zze;
                zzal(zzakVar31);
                zzakVar31.zzx();
                zzag();
                zzaA().zzj().zzb("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j) + 500000) / 1000000));
            } catch (Throwable th) {
                zzak zzakVar32 = this.zze;
                zzal(zzakVar32);
                zzakVar32.zzx();
                throw th;
            }
        }
    }

    final boolean zzZ() {
        zzaB().zzg();
        FileLock fileLock = this.zzw;
        if (fileLock != null && fileLock.isValid()) {
            zzaA().zzj().zza("Storage concurrent access okay");
            return true;
        }
        this.zze.zzt.zzf();
        try {
            FileChannel channel = new RandomAccessFile(new File(this.zzn.zzaw().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzx = channel;
            FileLock fileLockTryLock = channel.tryLock();
            this.zzw = fileLockTryLock;
            if (fileLockTryLock != null) {
                zzaA().zzj().zza("Storage concurrent access okay");
                return true;
            }
            zzaA().zzd().zza("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzaA().zzd().zzb("Failed to acquire storage lock", e);
            return false;
        } catch (IOException e2) {
            zzaA().zzd().zzb("Failed to access storage lock file", e2);
            return false;
        } catch (OverlappingFileLockException e3) {
            zzaA().zzk().zzb("Storage lock already acquired", e3);
            return false;
        }
    }

    final long zza() {
        long jCurrentTimeMillis = zzax().currentTimeMillis();
        zzkb zzkbVar = this.zzk;
        zzkbVar.zzW();
        zzkbVar.zzg();
        long jZza = zzkbVar.zze.zza();
        if (jZza == 0) {
            jZza = ((long) zzkbVar.zzt.zzv().zzG().nextInt(DateTimeConstants.MILLIS_PER_DAY)) + 1;
            zzkbVar.zze.zzb(jZza);
        }
        return ((((jCurrentTimeMillis + jZza) / 1000) / 60) / 60) / 24;
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzet zzaA() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaA();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzga zzaB() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzaB();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Context zzaw() {
        return this.zzn.zzaw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final Clock zzax() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzax();
    }

    @Override // com.google.android.gms.measurement.internal.zzgy
    public final zzab zzay() {
        throw null;
    }

    final zzh zzd(zzq zzqVar) {
        zzaB().zzg();
        zzB();
        Preconditions.checkNotNull(zzqVar);
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzlf zzlfVar = null;
        if (!zzqVar.zzw.isEmpty()) {
            this.zzC.put(zzqVar.zza, new zzlg(this, zzqVar.zzw));
        }
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        zzh zzhVarZzj = zzakVar.zzj(zzqVar.zza);
        zzhb zzhbVarZzd = zzq(zzqVar.zza).zzd(zzhb.zzc(zzqVar.zzv, 100));
        String strZzf = zzhbVarZzd.zzj(zzha.AD_STORAGE) ? this.zzk.zzf(zzqVar.zza, zzqVar.zzo) : "";
        if (zzhVarZzj == null) {
            zzhVarZzj = new zzh(this.zzn, zzqVar.zza);
            if (zzhbVarZzd.zzj(zzha.ANALYTICS_STORAGE)) {
                zzhVarZzj.zzJ(zzw(zzhbVarZzd));
            }
            if (zzhbVarZzd.zzj(zzha.AD_STORAGE)) {
                zzhVarZzj.zzag(strZzf);
            }
        } else if (zzhbVarZzd.zzj(zzha.AD_STORAGE) && strZzf != null && !strZzf.equals(zzhVarZzj.zzC())) {
            zzhVarZzj.zzag(strZzf);
            if (zzqVar.zzo && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.zzd(zzqVar.zza, zzhbVarZzd).first)) {
                zzhVarZzj.zzJ(zzw(zzhbVarZzd));
                zzak zzakVar2 = this.zze;
                zzal(zzakVar2);
                if (zzakVar2.zzp(zzqVar.zza, VideoDownloadSQLiteHelper.Columns._ID) != null) {
                    zzak zzakVar3 = this.zze;
                    zzal(zzakVar3);
                    if (zzakVar3.zzp(zzqVar.zza, "_lair") == null) {
                        zzlm zzlmVar = new zzlm(zzqVar.zza, "auto", "_lair", zzax().currentTimeMillis(), 1L);
                        zzak zzakVar4 = this.zze;
                        zzal(zzakVar4);
                        zzakVar4.zzL(zzlmVar);
                    }
                }
            }
        } else if (TextUtils.isEmpty(zzhVarZzj.zzw()) && zzhbVarZzd.zzj(zzha.ANALYTICS_STORAGE)) {
            zzhVarZzj.zzJ(zzw(zzhbVarZzd));
        }
        zzhVarZzj.zzY(zzqVar.zzb);
        zzhVarZzj.zzH(zzqVar.zzq);
        if (!TextUtils.isEmpty(zzqVar.zzk)) {
            zzhVarZzj.zzX(zzqVar.zzk);
        }
        long j = zzqVar.zze;
        if (j != 0) {
            zzhVarZzj.zzZ(j);
        }
        if (!TextUtils.isEmpty(zzqVar.zzc)) {
            zzhVarZzj.zzL(zzqVar.zzc);
        }
        zzhVarZzj.zzM(zzqVar.zzj);
        String str = zzqVar.zzd;
        if (str != null) {
            zzhVarZzj.zzK(str);
        }
        zzhVarZzj.zzU(zzqVar.zzf);
        zzhVarZzj.zzae(zzqVar.zzh);
        if (!TextUtils.isEmpty(zzqVar.zzg)) {
            zzhVarZzj.zzaa(zzqVar.zzg);
        }
        zzhVarZzj.zzI(zzqVar.zzo);
        zzhVarZzj.zzaf(zzqVar.zzr);
        zzhVarZzj.zzV(zzqVar.zzs);
        zzqu.zzc();
        if (zzg().zzs(null, zzeg.zzam) || zzg().zzs(zzqVar.zza, zzeg.zzao)) {
            zzhVarZzj.zzai(zzqVar.zzx);
        }
        zzop.zzc();
        if (zzg().zzs(null, zzeg.zzal)) {
            zzhVarZzj.zzah(zzqVar.zzt);
        } else {
            zzop.zzc();
            if (zzg().zzs(null, zzeg.zzak)) {
                zzhVarZzj.zzah(null);
            }
        }
        zzrd.zzc();
        if (zzg().zzs(null, zzeg.zzaq)) {
            zzhVarZzj.zzak(zzqVar.zzy);
        }
        zzpz.zzc();
        if (zzg().zzs(null, zzeg.zzaE)) {
            zzhVarZzj.zzal(zzqVar.zzz);
        }
        if (zzhVarZzj.zzao()) {
            zzak zzakVar5 = this.zze;
            zzal(zzakVar5);
            zzakVar5.zzD(zzhVarZzj);
        }
        return zzhVarZzj;
    }

    public final zzaa zzf() {
        zzaa zzaaVar = this.zzh;
        zzal(zzaaVar);
        return zzaaVar;
    }

    public final zzag zzg() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzak zzh() {
        zzak zzakVar = this.zze;
        zzal(zzakVar);
        return zzakVar;
    }

    public final zzeo zzi() {
        return this.zzn.zzj();
    }

    public final zzez zzj() {
        zzez zzezVar = this.zzd;
        zzal(zzezVar);
        return zzezVar;
    }

    public final zzfb zzl() {
        zzfb zzfbVar = this.zzf;
        if (zzfbVar != null) {
            return zzfbVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzfu zzm() {
        zzfu zzfuVar = this.zzc;
        zzal(zzfuVar);
        return zzfuVar;
    }

    final zzgd zzp() {
        return this.zzn;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0055 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final com.google.android.gms.measurement.internal.zzhb zzq(java.lang.String r6) {
        /*
            r5 = this;
            com.google.android.gms.measurement.internal.zzhb r0 = com.google.android.gms.measurement.internal.zzhb.zza
            com.google.android.gms.measurement.internal.zzga r0 = r5.zzaB()
            r0.zzg()
            r5.zzB()
            java.util.Map r0 = r5.zzB
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.measurement.internal.zzhb r0 = (com.google.android.gms.measurement.internal.zzhb) r0
            if (r0 != 0) goto L6e
            com.google.android.gms.measurement.internal.zzak r0 = r5.zze
            zzal(r0)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            r0.zzg()
            r0.zzW()
            java.lang.String[] r1 = new java.lang.String[]{r6}
            android.database.sqlite.SQLiteDatabase r2 = r0.zzh()
            java.lang.String r3 = "select consent_state from consent_settings where app_id=? limit 1;"
            r4 = 0
            android.database.Cursor r4 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            boolean r1 = r4.moveToFirst()     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            if (r1 == 0) goto L44
            r1 = 0
            java.lang.String r0 = r4.getString(r1)     // Catch: java.lang.Throwable -> L55 android.database.sqlite.SQLiteException -> L57
            if (r4 == 0) goto L4b
            r4.close()
            goto L4b
        L44:
            if (r4 == 0) goto L49
            r4.close()
        L49:
            java.lang.String r0 = "G1"
        L4b:
            r1 = 100
            com.google.android.gms.measurement.internal.zzhb r0 = com.google.android.gms.measurement.internal.zzhb.zzc(r0, r1)
            r5.zzV(r6, r0)
            goto L6e
        L55:
            r6 = move-exception
            goto L68
        L57:
            r6 = move-exception
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt     // Catch: java.lang.Throwable -> L55
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzaA()     // Catch: java.lang.Throwable -> L55
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzd()     // Catch: java.lang.Throwable -> L55
            java.lang.String r1 = "Database error"
            r0.zzc(r1, r3, r6)     // Catch: java.lang.Throwable -> L55
            throw r6     // Catch: java.lang.Throwable -> L55
        L68:
            if (r4 == 0) goto L6d
            r4.close()
        L6d:
            throw r6
        L6e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlh.zzq(java.lang.String):com.google.android.gms.measurement.internal.zzhb");
    }

    public final zzip zzr() {
        zzip zzipVar = this.zzj;
        zzal(zzipVar);
        return zzipVar;
    }

    public final zzkb zzs() {
        return this.zzk;
    }

    public final zzlj zzu() {
        zzlj zzljVar = this.zzi;
        zzal(zzljVar);
        return zzljVar;
    }

    public final zzlp zzv() {
        return ((zzgd) Preconditions.checkNotNull(this.zzn)).zzv();
    }

    final String zzw(zzhb zzhbVar) {
        if (!zzhbVar.zzj(zzha.ANALYTICS_STORAGE)) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzv().zzG().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    final String zzx(zzq zzqVar) {
        try {
            return (String) zzaB().zzh(new zzla(this, zzqVar)).get(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzaA().zzd().zzc("Failed to get app instance id. appId", zzet.zzn(zzqVar.zza), e);
            return null;
        }
    }

    final void zzz(Runnable runnable) {
        zzaB().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }
}
