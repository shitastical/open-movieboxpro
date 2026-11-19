package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzw {
    final /* synthetic */ zzaa zza;
    private com.google.android.gms.internal.measurement.zzft zzb;
    private Long zzc;
    private long zzd;

    /* JADX WARN: Code duplicated, block: B:16:0x0065  */
    /* JADX WARN: Code duplicated, block: B:20:0x008b A[Catch: SQLiteException -> 0x00dc, all -> 0x01d6, TryCatch #1 {all -> 0x01d6, blocks: (B:18:0x0085, B:20:0x008b, B:23:0x009d, B:24:0x00ab, B:25:0x00bb, B:29:0x00c6, B:38:0x00e4), top: B:83:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x009c  */
    /* JADX WARN: Code duplicated, block: B:23:0x009d A[Catch: SQLiteException -> 0x00dc, all -> 0x01d6, TRY_LEAVE, TryCatch #1 {all -> 0x01d6, blocks: (B:18:0x0085, B:20:0x008b, B:23:0x009d, B:24:0x00ab, B:25:0x00bb, B:29:0x00c6, B:38:0x00e4), top: B:83:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:43:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:70:0x01da  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r8v9, types: [long] */
    final com.google.android.gms.internal.measurement.zzft zza(String str, com.google.android.gms.internal.measurement.zzft zzftVar) {
        zzak zzakVarZzh;
        SQLiteException e;
        Cursor cursorRawQuery;
        Pair pairCreate;
        ?? LongValue;
        String strZzh = zzftVar.zzh();
        List listZzi = zzftVar.zzi();
        this.zza.zzf.zzu();
        Long l = (Long) zzlj.zzD(zzftVar, "_eid");
        if (l != null) {
            if (strZzh.equals("_ep")) {
                Preconditions.checkNotNull(l);
                this.zza.zzf.zzu();
                strZzh = (String) zzlj.zzD(zzftVar, "_en");
                ?? r7 = 0;
                if (TextUtils.isEmpty(strZzh)) {
                    this.zza.zzt.zzaA().zzh().zzb("Extra parameter without an event name. eventId", l);
                    return null;
                }
                if (this.zzb == null || this.zzc == null) {
                    zzakVarZzh = this.zza.zzf.zzh();
                    zzakVarZzh.zzg();
                    zzakVarZzh.zzW();
                    try {
                        try {
                            cursorRawQuery = zzakVarZzh.zzh().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, l.toString()});
                            try {
                                if (cursorRawQuery.moveToFirst()) {
                                    try {
                                        pairCreate = Pair.create((com.google.android.gms.internal.measurement.zzft) ((com.google.android.gms.internal.measurement.zzfs) zzlj.zzm(com.google.android.gms.internal.measurement.zzft.zze(), cursorRawQuery.getBlob(0))).zzaD(), Long.valueOf(cursorRawQuery.getLong(1)));
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                        }
                                    } catch (IOException e2) {
                                        zzakVarZzh.zzt.zzaA().zzd().zzd("Failed to merge main event. appId, eventId", zzet.zzn(str), l, e2);
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                        }
                                        pairCreate = null;
                                    }
                                } else {
                                    zzakVarZzh.zzt.zzaA().zzj().zza("Main event not found");
                                    if (cursorRawQuery != null) {
                                        cursorRawQuery.close();
                                    }
                                    pairCreate = null;
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                zzakVarZzh.zzt.zzaA().zzd().zzb("Error selecting main event", e);
                                if (cursorRawQuery != null) {
                                }
                                pairCreate = null;
                                if (pairCreate != null) {
                                }
                                this.zza.zzt.zzaA().zzh().zzc("Extra parameter without existing main event. eventName, eventId", strZzh, l);
                                return null;
                            }
                        } catch (Throwable th) {
                            th = th;
                            r7 = LongValue;
                            if (r7 != 0) {
                                r7.close();
                            }
                            throw th;
                        }
                    } catch (SQLiteException e4) {
                        e = e4;
                        cursorRawQuery = null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (r7 != 0) {
                            r7.close();
                        }
                        throw th;
                    }
                    if (pairCreate != null || pairCreate.first == null) {
                        this.zza.zzt.zzaA().zzh().zzc("Extra parameter without existing main event. eventName, eventId", strZzh, l);
                        return null;
                    }
                    this.zzb = (com.google.android.gms.internal.measurement.zzft) pairCreate.first;
                    this.zzd = ((Long) pairCreate.second).longValue();
                    this.zza.zzf.zzu();
                    this.zzc = (Long) zzlj.zzD(this.zzb, "_eid");
                } else {
                    LongValue = l.longValue();
                    if (LongValue != this.zzc.longValue()) {
                        zzakVarZzh = this.zza.zzf.zzh();
                        zzakVarZzh.zzg();
                        zzakVarZzh.zzW();
                        cursorRawQuery = zzakVarZzh.zzh().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, l.toString()});
                        if (cursorRawQuery.moveToFirst()) {
                            zzakVarZzh.zzt.zzaA().zzj().zza("Main event not found");
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            pairCreate = null;
                        } else {
                            pairCreate = Pair.create((com.google.android.gms.internal.measurement.zzft) ((com.google.android.gms.internal.measurement.zzfs) zzlj.zzm(com.google.android.gms.internal.measurement.zzft.zze(), cursorRawQuery.getBlob(0))).zzaD(), Long.valueOf(cursorRawQuery.getLong(1)));
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                        }
                        if (pairCreate != null) {
                        }
                        this.zza.zzt.zzaA().zzh().zzc("Extra parameter without existing main event. eventName, eventId", strZzh, l);
                        return null;
                    }
                }
                long j = this.zzd - 1;
                this.zzd = j;
                if (j <= 0) {
                    zzak zzakVarZzh2 = this.zza.zzf.zzh();
                    zzakVarZzh2.zzg();
                    zzakVarZzh2.zzt.zzaA().zzj().zzb("Clearing complex main event info. appId", str);
                    try {
                        zzakVarZzh2.zzh().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                    } catch (SQLiteException e5) {
                        zzakVarZzh2.zzt.zzaA().zzd().zzb("Error clearing complex main event", e5);
                    }
                } else {
                    this.zza.zzf.zzh().zzJ(str, l, this.zzd, this.zzb);
                }
                ArrayList arrayList = new ArrayList();
                for (com.google.android.gms.internal.measurement.zzfx zzfxVar : this.zzb.zzi()) {
                    this.zza.zzf.zzu();
                    if (zzlj.zzC(zzftVar, zzfxVar.zzg()) == null) {
                        arrayList.add(zzfxVar);
                    }
                }
                if (arrayList.isEmpty()) {
                    this.zza.zzt.zzaA().zzh().zzb("No unique parameters in main event. eventName", strZzh);
                } else {
                    arrayList.addAll(listZzi);
                    listZzi = arrayList;
                }
            } else {
                this.zzc = l;
                this.zzb = zzftVar;
                this.zza.zzf.zzu();
                Object objZzD = zzlj.zzD(zzftVar, "_epc");
                long jLongValue = ((Long) (objZzD != null ? objZzD : 0L)).longValue();
                this.zzd = jLongValue;
                if (jLongValue <= 0) {
                    this.zza.zzt.zzaA().zzh().zzb("Complex event with zero extra param count. eventName", strZzh);
                } else {
                    this.zza.zzf.zzh().zzJ(str, (Long) Preconditions.checkNotNull(l), this.zzd, zzftVar);
                }
            }
        }
        com.google.android.gms.internal.measurement.zzfs zzfsVar = (com.google.android.gms.internal.measurement.zzfs) zzftVar.zzbB();
        zzfsVar.zzi(strZzh);
        zzfsVar.zzg();
        zzfsVar.zzd(listZzi);
        return (com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaD();
    }
}
