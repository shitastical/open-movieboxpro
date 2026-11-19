package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzoy;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzx extends zzy {
    final /* synthetic */ zzaa zza;
    private final com.google.android.gms.internal.measurement.zzek zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzx(zzaa zzaaVar, String str, int i, com.google.android.gms.internal.measurement.zzek zzekVar) {
        super(str, i);
        this.zza = zzaaVar;
        this.zzh = zzekVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzb() {
        return this.zzh.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzy
    final boolean zzc() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:106:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:108:0x0301  */
    /* JADX WARN: Code duplicated, block: B:109:0x0314  */
    /* JADX WARN: Code duplicated, block: B:111:0x031a  */
    /* JADX WARN: Code duplicated, block: B:113:0x0322  */
    /* JADX WARN: Code duplicated, block: B:116:0x032e  */
    /* JADX WARN: Code duplicated, block: B:122:0x0391 A[EDGE_INSN: B:122:0x0391->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159]] */
    /* JADX WARN: Code duplicated, block: B:123:0x03bf A[EDGE_INSN: B:123:0x03bf->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159]] */
    /* JADX WARN: Code duplicated, block: B:159:0x0161 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:160:0x013a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x0201 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x0184 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x01a2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:164:0x018a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:166:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x01a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x016e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:173:0x03e9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x022e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:0x02a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:176:0x025b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x03ed A[EDGE_INSN: B:177:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x029e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x02ae A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x03ed A[EDGE_INSN: B:180:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x02f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x02f7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x02a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x038f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x0365 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:187:0x033a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:188:0x03ed A[EDGE_INSN: B:188:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:189:0x0334 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:192:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:193:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x0117  */
    /* JADX WARN: Code duplicated, block: B:43:0x012a  */
    /* JADX WARN: Code duplicated, block: B:46:0x0159 A[LOOP:0: B:41:0x0124->B:46:0x0159, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:50:0x0174  */
    /* JADX WARN: Code duplicated, block: B:56:0x0194  */
    /* JADX WARN: Code duplicated, block: B:57:0x019d  */
    /* JADX WARN: Code duplicated, block: B:63:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:64:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:68:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:73:0x020f  */
    /* JADX WARN: Code duplicated, block: B:78:0x0223  */
    /* JADX WARN: Code duplicated, block: B:82:0x024d  */
    /* JADX WARN: Code duplicated, block: B:87:0x0286  */
    /* JADX WARN: Code duplicated, block: B:90:0x0298  */
    /* JADX WARN: Code duplicated, block: B:98:0x02d9  */
    final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzft zzftVar, long j, zzaq zzaqVar, boolean z) {
        HashSet hashSet;
        Iterator it;
        ArrayMap arrayMap;
        Iterator it2;
        Iterator it3;
        com.google.android.gms.internal.measurement.zzem zzemVar;
        boolean z2;
        String strZze;
        Object obj;
        Boolean boolZzh;
        Boolean boolZzg;
        String str;
        Boolean boolZzi;
        com.google.android.gms.internal.measurement.zzfx zzfxVar;
        Long lValueOf;
        Double dValueOf;
        com.google.android.gms.internal.measurement.zzem zzemVar2;
        zzoy.zzc();
        boolean zZzs = this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzY);
        long j2 = this.zzh.zzn() ? zzaqVar.zze : j;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        Boolean bool = null;
        if (Log.isLoggable(this.zza.zzt.zzaA().zzr(), 2)) {
            this.zza.zzt.zzaA().zzj().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(this.zzc), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null, this.zza.zzt.zzj().zzd(this.zzh.zzg()));
            this.zza.zzt.zzaA().zzj().zzb("Filter definition", this.zza.zzf.zzu().zzp(this.zzh));
        }
        if (!this.zzh.zzp() || this.zzh.zzb() > 256) {
            this.zza.zzt.zzaA().zzk().zzc("Invalid event filter ID. appId, id", zzet.zzn(this.zzb), String.valueOf(this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null));
            return false;
        }
        boolean z3 = this.zzh.zzk() || this.zzh.zzm() || this.zzh.zzn();
        if (z && !z3) {
            this.zza.zzt.zzaA().zzj().zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzek zzekVar = this.zzh;
        String strZzh = zzftVar.zzh();
        if (!zzekVar.zzo()) {
            hashSet = new HashSet();
            it = zzekVar.zzh().iterator();
            while (true) {
                if (it.hasNext()) {
                    arrayMap = new ArrayMap();
                    it2 = zzftVar.zzi().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            it3 = zzekVar.zzh().iterator();
                            while (true) {
                                if (it3.hasNext()) {
                                    bool = true;
                                    break;
                                }
                                zzemVar = (com.google.android.gms.internal.measurement.zzem) it3.next();
                                if (zzemVar.zzh() || !zzemVar.zzg()) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                strZze = zzemVar.zze();
                                if (strZze.isEmpty()) {
                                    obj = arrayMap.get(strZze);
                                    if (obj instanceof Long) {
                                        if (obj instanceof Double) {
                                            if (obj instanceof String) {
                                                if (obj == null) {
                                                    this.zza.zzt.zzaA().zzk().zzc("Unknown param type. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                    break;
                                                }
                                                this.zza.zzt.zzaA().zzj().zzc("Missing param for filter. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                bool = false;
                                                break;
                                            }
                                            if (zzemVar.zzk()) {
                                                if (zzemVar.zzi()) {
                                                    this.zza.zzt.zzaA().zzk().zzc("No filter for String param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                    break;
                                                }
                                                str = (String) obj;
                                                if (zzlj.zzy(str)) {
                                                    this.zza.zzt.zzaA().zzk().zzc("Invalid param value for number filter. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                    break;
                                                }
                                                boolZzi = zzi(str, zzemVar.zzc());
                                            } else {
                                                boolZzi = zzf((String) obj, zzemVar.zzd(), this.zza.zzt.zzaA());
                                            }
                                            if (boolZzi != null) {
                                                break;
                                            }
                                            if (boolZzi.booleanValue() == z2) {
                                                bool = false;
                                                break;
                                            }
                                        } else {
                                            if (zzemVar.zzi()) {
                                                this.zza.zzt.zzaA().zzk().zzc("No number filter for double param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                break;
                                            }
                                            boolZzg = zzg(((Double) obj).doubleValue(), zzemVar.zzc());
                                            if (boolZzg != null) {
                                                break;
                                            }
                                            if (boolZzg.booleanValue() == z2) {
                                                bool = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        if (zzemVar.zzi()) {
                                            this.zza.zzt.zzaA().zzk().zzc("No number filter for long param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                            break;
                                        }
                                        boolZzh = zzh(((Long) obj).longValue(), zzemVar.zzc());
                                        if (boolZzh != null) {
                                            break;
                                        }
                                        if (boolZzh.booleanValue() == z2) {
                                            bool = false;
                                            break;
                                        }
                                    }
                                } else {
                                    this.zza.zzt.zzaA().zzk().zzb("Event has empty param name. event", this.zza.zzt.zzj().zzd(strZzh));
                                    break;
                                }
                            }
                        } else {
                            zzfxVar = (com.google.android.gms.internal.measurement.zzfx) it2.next();
                            if (!hashSet.contains(zzfxVar.zzg())) {
                                if (zzfxVar.zzw()) {
                                    if (zzfxVar.zzu()) {
                                        if (zzfxVar.zzy()) {
                                            this.zza.zzt.zzaA().zzk().zzc("Unknown value for param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(zzfxVar.zzg()));
                                            break;
                                        }
                                        arrayMap.put(zzfxVar.zzg(), zzfxVar.zzh());
                                    } else {
                                        String strZzg = zzfxVar.zzg();
                                        if (zzfxVar.zzu()) {
                                            dValueOf = Double.valueOf(zzfxVar.zza());
                                        } else {
                                            dValueOf = null;
                                        }
                                        arrayMap.put(strZzg, dValueOf);
                                    }
                                } else {
                                    String strZzg2 = zzfxVar.zzg();
                                    if (zzfxVar.zzw()) {
                                        lValueOf = Long.valueOf(zzfxVar.zzd());
                                    } else {
                                        lValueOf = null;
                                    }
                                    arrayMap.put(strZzg2, lValueOf);
                                }
                            }
                        }
                    }
                } else {
                    zzemVar2 = (com.google.android.gms.internal.measurement.zzem) it.next();
                    if (zzemVar2.zze().isEmpty()) {
                        this.zza.zzt.zzaA().zzk().zzb("null or empty param name in filter. event", this.zza.zzt.zzj().zzd(strZzh));
                        break;
                    }
                    hashSet.add(zzemVar2.zze());
                }
            }
        } else {
            Boolean boolZzh2 = zzh(j2, zzekVar.zzf());
            if (boolZzh2 != null) {
                if (boolZzh2.booleanValue()) {
                    hashSet = new HashSet();
                    it = zzekVar.zzh().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            arrayMap = new ArrayMap();
                            it2 = zzftVar.zzi().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    it3 = zzekVar.zzh().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            bool = true;
                                            break;
                                        }
                                        zzemVar = (com.google.android.gms.internal.measurement.zzem) it3.next();
                                        if (zzemVar.zzh()) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                        strZze = zzemVar.zze();
                                        if (strZze.isEmpty()) {
                                            obj = arrayMap.get(strZze);
                                            if (obj instanceof Long) {
                                                if (obj instanceof Double) {
                                                    if (obj instanceof String) {
                                                        if (obj == null) {
                                                            this.zza.zzt.zzaA().zzk().zzc("Unknown param type. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        this.zza.zzt.zzaA().zzj().zzc("Missing param for filter. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                        bool = false;
                                                        break;
                                                    }
                                                    if (zzemVar.zzk()) {
                                                        if (zzemVar.zzi()) {
                                                            this.zza.zzt.zzaA().zzk().zzc("No filter for String param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        str = (String) obj;
                                                        if (zzlj.zzy(str)) {
                                                            this.zza.zzt.zzaA().zzk().zzc("Invalid param value for number filter. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        boolZzi = zzi(str, zzemVar.zzc());
                                                    } else {
                                                        boolZzi = zzf((String) obj, zzemVar.zzd(), this.zza.zzt.zzaA());
                                                    }
                                                    if (boolZzi != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolZzi.booleanValue() == z2) {
                                                        bool = false;
                                                        break;
                                                    }
                                                } else if (zzemVar.zzi()) {
                                                    boolZzg = zzg(((Double) obj).doubleValue(), zzemVar.zzc());
                                                    if (boolZzg != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolZzg.booleanValue() == z2) {
                                                        bool = false;
                                                        break;
                                                    }
                                                } else {
                                                    this.zza.zzt.zzaA().zzk().zzc("No number filter for double param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                    break;
                                                }
                                            } else if (zzemVar.zzi()) {
                                                boolZzh = zzh(((Long) obj).longValue(), zzemVar.zzc());
                                                if (boolZzh != null) {
                                                    break;
                                                    break;
                                                }
                                                if (boolZzh.booleanValue() == z2) {
                                                    bool = false;
                                                    break;
                                                }
                                            } else {
                                                this.zza.zzt.zzaA().zzk().zzc("No number filter for long param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(strZze));
                                                break;
                                            }
                                        } else {
                                            this.zza.zzt.zzaA().zzk().zzb("Event has empty param name. event", this.zza.zzt.zzj().zzd(strZzh));
                                            break;
                                        }
                                    }
                                } else {
                                    zzfxVar = (com.google.android.gms.internal.measurement.zzfx) it2.next();
                                    if (!hashSet.contains(zzfxVar.zzg())) {
                                        if (zzfxVar.zzw()) {
                                            if (zzfxVar.zzu()) {
                                                if (zzfxVar.zzy()) {
                                                    this.zza.zzt.zzaA().zzk().zzc("Unknown value for param. event, param", this.zza.zzt.zzj().zzd(strZzh), this.zza.zzt.zzj().zze(zzfxVar.zzg()));
                                                    break;
                                                }
                                                arrayMap.put(zzfxVar.zzg(), zzfxVar.zzh());
                                            } else {
                                                String strZzg3 = zzfxVar.zzg();
                                                if (zzfxVar.zzu()) {
                                                    dValueOf = Double.valueOf(zzfxVar.zza());
                                                } else {
                                                    dValueOf = null;
                                                }
                                                arrayMap.put(strZzg3, dValueOf);
                                            }
                                        } else {
                                            String strZzg4 = zzfxVar.zzg();
                                            if (zzfxVar.zzw()) {
                                                lValueOf = Long.valueOf(zzfxVar.zzd());
                                            } else {
                                                lValueOf = null;
                                            }
                                            arrayMap.put(strZzg4, lValueOf);
                                        }
                                    }
                                }
                            }
                        } else {
                            zzemVar2 = (com.google.android.gms.internal.measurement.zzem) it.next();
                            if (zzemVar2.zze().isEmpty()) {
                                this.zza.zzt.zzaA().zzk().zzb("null or empty param name in filter. event", this.zza.zzt.zzj().zzd(strZzh));
                                break;
                            }
                            hashSet.add(zzemVar2.zze());
                        }
                    }
                } else {
                    bool = false;
                }
            }
        }
        this.zza.zzt.zzaA().zzj().zzb("Event filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        this.zzd = true;
        if (!bool.booleanValue()) {
            return true;
        }
        this.zze = true;
        if (z3 && zzftVar.zzu()) {
            Long lValueOf2 = Long.valueOf(zzftVar.zzd());
            if (this.zzh.zzm()) {
                if (zZzs && this.zzh.zzo()) {
                    lValueOf2 = l;
                }
                this.zzg = lValueOf2;
            } else {
                if (zZzs && this.zzh.zzo()) {
                    lValueOf2 = l2;
                }
                this.zzf = lValueOf2;
            }
        }
        return true;
    }
}
