package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzoy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ly.count.android.sdk.Countly;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zzaa extends zzku {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzlh zzlhVar) {
        super(zzlhVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzuVar.zze.get(i2);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0255  */
    /* JADX WARN: Code duplicated, block: B:101:0x025d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0268  */
    /* JADX WARN: Code duplicated, block: B:107:0x0294 A[Catch: all -> 0x02cc, SQLiteException -> 0x02cf, LOOP:11: B:107:0x0294->B:512:?, LOOP_START, TryCatch #22 {all -> 0x02cc, blocks: (B:105:0x028e, B:107:0x0294, B:109:0x02a5, B:110:0x02ad, B:114:0x02c5, B:125:0x02d7), top: B:462:0x0284 }] */
    /* JADX WARN: Code duplicated, block: B:109:0x02a5 A[Catch: all -> 0x02cc, SQLiteException -> 0x02cf, TryCatch #22 {all -> 0x02cc, blocks: (B:105:0x028e, B:107:0x0294, B:109:0x02a5, B:110:0x02ad, B:114:0x02c5, B:125:0x02d7), top: B:462:0x0284 }] */
    /* JADX WARN: Code duplicated, block: B:113:0x02c1 A[PHI: r0 r5
      0x02c1: PHI (r0v59 java.util.Map) = (r0v43 java.util.Map), (r0v61 java.util.Map), (r0v37 java.util.Map) binds: [B:126:0x02ee, B:115:0x02c9, B:112:0x02bf] A[DONT_GENERATE, DONT_INLINE]
      0x02c1: PHI (r5v16 android.database.Cursor) = (r5v10 android.database.Cursor), (r5v17 android.database.Cursor), (r5v17 android.database.Cursor) binds: [B:126:0x02ee, B:115:0x02c9, B:112:0x02bf] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:114:0x02c5 A[Catch: all -> 0x02cc, SQLiteException -> 0x02cf, TRY_ENTER, TRY_LEAVE, TryCatch #22 {all -> 0x02cc, blocks: (B:105:0x028e, B:107:0x0294, B:109:0x02a5, B:110:0x02ad, B:114:0x02c5, B:125:0x02d7), top: B:462:0x0284 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0306  */
    /* JADX WARN: Code duplicated, block: B:134:0x0314  */
    /* JADX WARN: Code duplicated, block: B:136:0x032b  */
    /* JADX WARN: Code duplicated, block: B:160:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:162:0x0400  */
    /* JADX WARN: Code duplicated, block: B:166:0x040d  */
    /* JADX WARN: Code duplicated, block: B:168:0x042e  */
    /* JADX WARN: Code duplicated, block: B:174:0x0443  */
    /* JADX WARN: Code duplicated, block: B:178:0x045d  */
    /* JADX WARN: Code duplicated, block: B:179:0x0466  */
    /* JADX WARN: Code duplicated, block: B:183:0x0472  */
    /* JADX WARN: Code duplicated, block: B:189:0x0487  */
    /* JADX WARN: Code duplicated, block: B:196:0x04bf  */
    /* JADX WARN: Code duplicated, block: B:199:0x04c8  */
    /* JADX WARN: Code duplicated, block: B:201:0x04d2  */
    /* JADX WARN: Code duplicated, block: B:203:0x04f4  */
    /* JADX WARN: Code duplicated, block: B:204:0x04f8  */
    /* JADX WARN: Code duplicated, block: B:209:0x0510 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:234:0x05b8  */
    /* JADX WARN: Code duplicated, block: B:237:0x05cd  */
    /* JADX WARN: Code duplicated, block: B:241:0x05f1  */
    /* JADX WARN: Code duplicated, block: B:242:0x0630  */
    /* JADX WARN: Code duplicated, block: B:245:0x067c  */
    /* JADX WARN: Code duplicated, block: B:253:0x06c1  */
    /* JADX WARN: Code duplicated, block: B:260:0x06e9  */
    /* JADX WARN: Code duplicated, block: B:262:0x06f4  */
    /* JADX WARN: Code duplicated, block: B:269:0x0718  */
    /* JADX WARN: Code duplicated, block: B:271:0x071d A[LOOP:8: B:254:0x06c3->B:271:0x071d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:274:0x0724  */
    /* JADX WARN: Code duplicated, block: B:277:0x072e A[PHI: r0 r5 r23 r27 r28
      0x072e: PHI (r0v90 java.util.Map) = (r0v92 java.util.Map), (r0v98 java.util.Map) binds: [B:295:0x0766, B:276:0x072c] A[DONT_GENERATE, DONT_INLINE]
      0x072e: PHI (r5v31 android.database.Cursor) = (r5v32 android.database.Cursor), (r5v33 android.database.Cursor) binds: [B:295:0x0766, B:276:0x072c] A[DONT_GENERATE, DONT_INLINE]
      0x072e: PHI (r23v9 com.google.android.gms.measurement.internal.zzaq) = (r23v10 com.google.android.gms.measurement.internal.zzaq), (r23v14 com.google.android.gms.measurement.internal.zzaq) binds: [B:295:0x0766, B:276:0x072c] A[DONT_GENERATE, DONT_INLINE]
      0x072e: PHI (r27v7 java.lang.String) = (r27v8 java.lang.String), (r27v11 java.lang.String) binds: [B:295:0x0766, B:276:0x072c] A[DONT_GENERATE, DONT_INLINE]
      0x072e: PHI (r28v8 java.lang.String) = (r28v9 java.lang.String), (r28v11 java.lang.String) binds: [B:295:0x0766, B:276:0x072c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:301:0x0773  */
    /* JADX WARN: Code duplicated, block: B:305:0x0789  */
    /* JADX WARN: Code duplicated, block: B:311:0x07ba  */
    /* JADX WARN: Code duplicated, block: B:313:0x07e7 A[LOOP:10: B:309:0x07b4->B:313:0x07e7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:317:0x07fd  */
    /* JADX WARN: Code duplicated, block: B:323:0x0819  */
    /* JADX WARN: Code duplicated, block: B:326:0x0828  */
    /* JADX WARN: Code duplicated, block: B:328:0x083b  */
    /* JADX WARN: Code duplicated, block: B:334:0x0878 A[Catch: SQLiteException -> 0x08e3, all -> 0x0912, LOOP:4: B:334:0x0878->B:351:0x08d2, LOOP_START, PHI: r3
      0x0878: PHI (r3v54 java.util.Iterator) = (r3v47 java.util.Iterator), (r3v56 java.util.Iterator) binds: [B:333:0x0876, B:351:0x08d2] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #16 {SQLiteException -> 0x08e3, blocks: (B:332:0x0872, B:334:0x0878, B:335:0x087d, B:337:0x088e, B:339:0x089e, B:341:0x08a9, B:343:0x08b0), top: B:454:0x0872 }] */
    /* JADX WARN: Code duplicated, block: B:339:0x089e A[Catch: SQLiteException -> 0x08e3, all -> 0x0912, TryCatch #16 {SQLiteException -> 0x08e3, blocks: (B:332:0x0872, B:334:0x0878, B:335:0x087d, B:337:0x088e, B:339:0x089e, B:341:0x08a9, B:343:0x08b0), top: B:454:0x0872 }] */
    /* JADX WARN: Code duplicated, block: B:340:0x08a7  */
    /* JADX WARN: Code duplicated, block: B:349:0x08cd  */
    /* JADX WARN: Code duplicated, block: B:351:0x08d2 A[LOOP:4: B:334:0x0878->B:351:0x08d2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:352:0x08d5  */
    /* JADX WARN: Code duplicated, block: B:355:0x08dd A[PHI: r0 r13 r65
      0x08dd: PHI (r0v139 java.util.Map) = (r0v141 java.util.Map), (r0v146 java.util.Map) binds: [B:368:0x090b, B:354:0x08db] A[DONT_GENERATE, DONT_INLINE]
      0x08dd: PHI (r13v37 android.database.Cursor) = (r13v38 android.database.Cursor), (r13v39 android.database.Cursor) binds: [B:368:0x090b, B:354:0x08db] A[DONT_GENERATE, DONT_INLINE]
      0x08dd: PHI (r65v8 java.util.Iterator) = (r65v9 java.util.Iterator), (r65v12 java.util.Iterator) binds: [B:368:0x090b, B:354:0x08db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:376:0x091a  */
    /* JADX WARN: Code duplicated, block: B:380:0x092e  */
    /* JADX WARN: Code duplicated, block: B:383:0x094f  */
    /* JADX WARN: Code duplicated, block: B:386:0x0960  */
    /* JADX WARN: Code duplicated, block: B:388:0x0977  */
    /* JADX WARN: Code duplicated, block: B:390:0x0987  */
    /* JADX WARN: Code duplicated, block: B:391:0x0990  */
    /* JADX WARN: Code duplicated, block: B:393:0x09c0  */
    /* JADX WARN: Code duplicated, block: B:396:0x09c8  */
    /* JADX WARN: Code duplicated, block: B:405:0x0a11  */
    /* JADX WARN: Code duplicated, block: B:406:0x0a1a  */
    /* JADX WARN: Code duplicated, block: B:410:0x0a29 A[PHI: r66
      0x0a29: PHI (r66v6 java.util.Map) = (r66v7 java.util.Map), (r0v119 java.util.Map) binds: [B:409:0x0a27, B:407:0x0a1b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:416:0x0a54  */
    /* JADX WARN: Code duplicated, block: B:421:0x0ab0 A[Catch: SQLiteException -> 0x0ac4, TRY_LEAVE, TryCatch #28 {SQLiteException -> 0x0ac4, blocks: (B:419:0x0aa6, B:421:0x0ab0), top: B:467:0x0aa6 }] */
    /* JADX WARN: Code duplicated, block: B:433:0x0ae4  */
    /* JADX WARN: Code duplicated, block: B:484:0x0adb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:489:0x08cb A[EDGE_INSN: B:489:0x08cb->B:348:0x08cb BREAK  A[LOOP:4: B:334:0x0878->B:351:0x08d2], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:490:0x0940 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:492:0x0a2e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:493:0x0a25 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:494:0x09fb A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:498:0x05db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:500:0x05c7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:502:0x0716 A[EDGE_INSN: B:502:0x0716->B:268:0x0716 BREAK  A[LOOP:8: B:254:0x06c3->B:271:0x071d], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:504:0x07a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:505:0x079b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:509:0x07f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:510:0x07f2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:514:0x056e A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:521:0x044f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:523:0x043d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:526:0x0493 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:529:0x0481 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:541:0x03e7 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:555:0x020f A[EDGE_INSN: B:555:0x020f->B:79:0x020f BREAK  A[LOOP:20: B:68:0x01c3->B:82:0x0217], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x0178  */
    /* JADX WARN: Code duplicated, block: B:63:0x01ae A[Catch: SQLiteException -> 0x0222, all -> 0x0ae0, TRY_LEAVE, TryCatch #0 {all -> 0x0ae0, blocks: (B:61:0x01a8, B:63:0x01ae, B:67:0x01be, B:68:0x01c3, B:69:0x01cd, B:70:0x01dd, B:77:0x0209, B:72:0x01ec, B:74:0x01fc, B:76:0x0202, B:94:0x0232), top: B:435:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:67:0x01be A[Catch: SQLiteException -> 0x0222, all -> 0x0ae0, TRY_ENTER, TryCatch #0 {all -> 0x0ae0, blocks: (B:61:0x01a8, B:63:0x01ae, B:67:0x01be, B:68:0x01c3, B:69:0x01cd, B:70:0x01dd, B:77:0x0209, B:72:0x01ec, B:74:0x01fc, B:76:0x0202, B:94:0x0232), top: B:435:0x0192 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x0211  */
    /* JADX WARN: Code duplicated, block: B:82:0x0217 A[LOOP:20: B:68:0x01c3->B:82:0x0217, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:96:0x024b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v201, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v28, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v49, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v53, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v54 */
    /* JADX WARN: Type inference failed for: r5v55, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v56 */
    /* JADX WARN: Type inference failed for: r5v57 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9, types: [android.database.Cursor] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    final List zza(String str, List list, List list2, Long l, Long l2) throws Throwable {
        int i;
        int i2;
        boolean z;
        ?? r5;
        ?? r4;
        Map map;
        ?? r6;
        zzak zzakVarZzh;
        String str2;
        ?? r7;
        String str3;
        Cursor cursorQuery;
        Map map2;
        String str4;
        Map map3;
        Map map4;
        String str5;
        String str6;
        String str7;
        com.google.android.gms.internal.measurement.zzgi zzgiVar;
        BitSet bitSet;
        BitSet bitSet2;
        ArrayMap arrayMap;
        List<com.google.android.gms.internal.measurement.zzek> list3;
        int i3;
        String str8;
        Iterator it;
        com.google.android.gms.internal.measurement.zzgk zzgkVar;
        Long lValueOf;
        String str9;
        Map arrayMap2;
        ?? Zzh;
        Cursor cursorRawQuery;
        ArrayMap arrayMap3;
        Iterator it2;
        Integer num;
        com.google.android.gms.internal.measurement.zzgi zzgiVar2;
        List list4;
        Map map5;
        String str10;
        Integer numValueOf;
        List arrayList;
        zzw zzwVar;
        ArrayMap arrayMap4;
        Iterator it3;
        com.google.android.gms.internal.measurement.zzft zzftVar;
        com.google.android.gms.internal.measurement.zzft zzftVarZza;
        zzak zzakVarZzh2;
        String str11;
        String strZzh;
        zzaq zzaqVarZzn;
        zzaq zzaqVar;
        long j;
        String strZzh2;
        Map mapEmptyMap;
        zzaq zzaqVar2;
        int iIntValue;
        Iterator it4;
        boolean z2;
        Map map6;
        zzx zzxVar;
        boolean zZzd;
        zzak zzakVarZzh3;
        String str12;
        ArrayMap arrayMap5;
        String str13;
        String str14;
        Cursor cursorQuery2;
        Integer numValueOf2;
        List list5;
        List arrayList2;
        String str15;
        ArrayMap arrayMap6;
        Iterator it5;
        String strZzf;
        Map mapEmptyMap2;
        String str16;
        String str17;
        Iterator it6;
        boolean zZzd2;
        com.google.android.gms.internal.measurement.zzet zzetVar;
        Integer numValueOf3;
        zzz zzzVar;
        Integer numValueOf4;
        zzak zzakVarZzh4;
        String str18;
        ArrayMap arrayMap7;
        Cursor cursor;
        Cursor cursorQuery3;
        Integer numValueOf5;
        List list6;
        List arrayList3;
        String str19;
        ArrayList arrayList4;
        zzak zzakVarZzh5;
        String str20;
        ContentValues contentValues;
        ArrayMap arrayMap8;
        int i4;
        Cursor cursorQuery4;
        List arrayList5;
        String str21 = "current_results";
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l;
        this.zze = l2;
        Iterator it7 = list.iterator();
        while (true) {
            i = 0;
            i2 = 1;
            if (!it7.hasNext()) {
                z = false;
                break;
            }
            if ("_s".equals(((com.google.android.gms.internal.measurement.zzft) it7.next()).zzh())) {
                z = true;
                break;
            }
        }
        zzoy.zzc();
        boolean zZzs = this.zzt.zzf().zzs(this.zza, zzeg.zzY);
        zzoy.zzc();
        boolean zZzs2 = this.zzt.zzf().zzs(this.zza, zzeg.zzX);
        if (z) {
            zzak zzakVarZzh6 = this.zzf.zzh();
            String str22 = this.zza;
            zzakVarZzh6.zzW();
            zzakVarZzh6.zzg();
            Preconditions.checkNotEmpty(str22);
            ?? contentValues2 = new ContentValues();
            ?? r8 = 0;
            contentValues2.put("current_session_count", r8);
            try {
                ?? Zzh2 = zzakVarZzh6.zzh();
                r8 = Countly.CountlyFeatureNames.events;
                Zzh2.update(Countly.CountlyFeatureNames.events, contentValues2, "app_id = ?", new String[]{str22});
                r4 = Zzh2;
                r5 = Countly.CountlyFeatureNames.events;
            } catch (SQLiteException e) {
                zzakVarZzh6.zzt.zzaA().zzd().zzc("Error resetting session-scoped event counts. appId", zzet.zzn(str22), e);
                r4 = "Error resetting session-scoped event counts. appId";
                r5 = r8;
            }
        }
        Map mapEmptyMap3 = Collections.emptyMap();
        String str23 = "Failed to merge filter. appId";
        String str24 = "Database error querying filters. appId";
        String str25 = "data";
        String str26 = "audience_id";
        ?? r9 = r4;
        r9 = r4;
        if (zZzs2 && zZzs) {
            zzak zzakVarZzh7 = this.zzf.zzh();
            String str27 = this.zza;
            Preconditions.checkNotEmpty(str27);
            ArrayMap arrayMap9 = new ArrayMap();
            try {
                try {
                    cursorQuery4 = zzakVarZzh7.zzh().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str27}, null, null, null);
                    try {
                        if (cursorQuery4.moveToFirst()) {
                            while (true) {
                                try {
                                    com.google.android.gms.internal.measurement.zzek zzekVar = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursorQuery4.getBlob(i2))).zzaD();
                                    if (zzekVar.zzo()) {
                                        Integer numValueOf6 = Integer.valueOf(cursorQuery4.getInt(i));
                                        List list7 = (List) arrayMap9.get(numValueOf6);
                                        if (list7 == null) {
                                            arrayList5 = new ArrayList();
                                            arrayMap9.put(numValueOf6, arrayList5);
                                        } else {
                                            arrayList5 = list7;
                                        }
                                        arrayList5.add(zzekVar);
                                    }
                                } catch (IOException e2) {
                                    zzakVarZzh7.zzt.zzaA().zzd().zzc("Failed to merge filter. appId", zzet.zzn(str27), e2);
                                }
                                if (!cursorQuery4.moveToNext()) {
                                    break;
                                }
                                i = 0;
                                i2 = 1;
                            }
                            if (cursorQuery4 != null) {
                                cursorQuery4.close();
                            }
                            map = arrayMap9;
                            r6 = arrayMap9;
                        } else {
                            mapEmptyMap3 = Collections.emptyMap();
                            r9 = arrayMap9;
                            if (cursorQuery4 != null) {
                                cursorQuery4.close();
                                r9 = arrayMap9;
                            }
                            map = mapEmptyMap3;
                            r6 = r9;
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                        zzakVarZzh7.zzt.zzaA().zzd().zzc("Database error querying filters. appId", zzet.zzn(str27), e);
                        mapEmptyMap3 = Collections.emptyMap();
                        r9 = arrayMap9;
                        if (cursorQuery4 != null) {
                        }
                        map = mapEmptyMap3;
                        r6 = r9;
                        zzakVarZzh = this.zzf.zzh();
                        str2 = this.zza;
                        zzakVarZzh.zzW();
                        zzakVarZzh.zzg();
                        Preconditions.checkNotEmpty(str2);
                        try {
                            cursorQuery = zzakVarZzh.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str2}, null, null, null);
                            try {
                                if (cursorQuery.moveToFirst()) {
                                    arrayMap8 = new ArrayMap();
                                    while (true) {
                                        i4 = cursorQuery.getInt(0);
                                        try {
                                            arrayMap8.put(Integer.valueOf(i4), (com.google.android.gms.internal.measurement.zzgi) ((com.google.android.gms.internal.measurement.zzgh) zzlj.zzm(com.google.android.gms.internal.measurement.zzgi.zze(), cursorQuery.getBlob(1))).zzaD());
                                            str3 = str26;
                                        } catch (IOException e4) {
                                            str3 = str26;
                                            try {
                                                zzakVarZzh.zzt.zzaA().zzd().zzd("Failed to merge filter results. appId, audienceId, error", zzet.zzn(str2), Integer.valueOf(i4), e4);
                                            } catch (SQLiteException e5) {
                                                e = e5;
                                                str25 = str25;
                                                zzakVarZzh.zzt.zzaA().zzd().zzc("Database error querying filter results. appId", zzet.zzn(str2), e);
                                                Map mapEmptyMap4 = Collections.emptyMap();
                                                if (cursorQuery != null) {
                                                    cursorQuery.close();
                                                }
                                                map2 = mapEmptyMap4;
                                                if (map2.isEmpty()) {
                                                    str7 = "Database error querying filters. appId";
                                                    str5 = "Failed to merge filter. appId";
                                                    str6 = str3;
                                                } else {
                                                    HashSet<Integer> hashSet = new HashSet(map2.keySet());
                                                    if (z) {
                                                        String str28 = this.zza;
                                                        zzak zzakVarZzh8 = this.zzf.zzh();
                                                        str9 = this.zza;
                                                        zzakVarZzh8.zzW();
                                                        zzakVarZzh8.zzg();
                                                        Preconditions.checkNotEmpty(str9);
                                                        arrayMap2 = new ArrayMap();
                                                        Zzh = zzakVarZzh8.zzh();
                                                        try {
                                                            try {
                                                                cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str9, str9});
                                                                try {
                                                                    if (cursorRawQuery.moveToFirst()) {
                                                                        do {
                                                                            numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                                                                            arrayList = (List) arrayMap2.get(numValueOf);
                                                                            if (arrayList == null) {
                                                                                arrayList = new ArrayList();
                                                                                arrayMap2.put(numValueOf, arrayList);
                                                                            }
                                                                            arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                                                                        } while (cursorRawQuery.moveToNext());
                                                                        if (cursorRawQuery != null) {
                                                                            cursorRawQuery.close();
                                                                        }
                                                                    } else {
                                                                        arrayMap2 = Collections.emptyMap();
                                                                        if (cursorRawQuery != null) {
                                                                            cursorRawQuery.close();
                                                                        }
                                                                    }
                                                                } catch (SQLiteException e6) {
                                                                    e = e6;
                                                                    zzakVarZzh8.zzt.zzaA().zzd().zzc("Database error querying scoped filters. appId", zzet.zzn(str9), e);
                                                                    arrayMap2 = Collections.emptyMap();
                                                                    if (cursorRawQuery != null) {
                                                                    }
                                                                    Preconditions.checkNotEmpty(str28);
                                                                    Preconditions.checkNotNull(map2);
                                                                    arrayMap3 = new ArrayMap();
                                                                    if (!map2.isEmpty()) {
                                                                        it2 = map2.keySet().iterator();
                                                                        while (it2.hasNext()) {
                                                                            num = (Integer) it2.next();
                                                                            num.intValue();
                                                                            zzgiVar2 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num);
                                                                            list4 = (List) arrayMap2.get(num);
                                                                            if (list4 != null) {
                                                                            }
                                                                            map5 = arrayMap2;
                                                                            str10 = str24;
                                                                            arrayMap3.put(num, zzgiVar2);
                                                                            arrayMap2 = map5;
                                                                            it2 = it2;
                                                                            str24 = str10;
                                                                        }
                                                                    }
                                                                    str4 = str24;
                                                                    map3 = arrayMap3;
                                                                    map4 = map;
                                                                    for (Integer num2 : hashSet) {
                                                                        num2.intValue();
                                                                        zzgiVar = (com.google.android.gms.internal.measurement.zzgi) map3.get(num2);
                                                                        bitSet = new BitSet();
                                                                        bitSet2 = new BitSet();
                                                                        arrayMap = new ArrayMap();
                                                                        if (zzgiVar != null) {
                                                                            for (com.google.android.gms.internal.measurement.zzfr zzfrVar : zzgiVar.zzh()) {
                                                                                if (zzfrVar.zzh()) {
                                                                                    Integer numValueOf7 = Integer.valueOf(zzfrVar.zza());
                                                                                    if (zzfrVar.zzg()) {
                                                                                        lValueOf = Long.valueOf(zzfrVar.zzb());
                                                                                    } else {
                                                                                        lValueOf = null;
                                                                                    }
                                                                                    arrayMap.put(numValueOf7, lValueOf);
                                                                                }
                                                                            }
                                                                        }
                                                                        ArrayMap arrayMap10 = new ArrayMap();
                                                                        if (zzgiVar != null) {
                                                                            it = zzgiVar.zzj().iterator();
                                                                            while (it.hasNext()) {
                                                                                zzgkVar = (com.google.android.gms.internal.measurement.zzgk) it.next();
                                                                                if (!zzgkVar.zzi()) {
                                                                                }
                                                                            }
                                                                        }
                                                                        Map map7 = map3;
                                                                        if (zzgiVar != null) {
                                                                            i3 = 0;
                                                                            while (i3 < zzgiVar.zzd() * 64) {
                                                                                if (zzlj.zzw(zzgiVar.zzk(), i3)) {
                                                                                    str8 = str23;
                                                                                    this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", num2, Integer.valueOf(i3));
                                                                                    bitSet2.set(i3);
                                                                                    if (zzlj.zzw(zzgiVar.zzi(), i3)) {
                                                                                        bitSet.set(i3);
                                                                                    }
                                                                                    i3++;
                                                                                    str23 = str8;
                                                                                } else {
                                                                                    str8 = str23;
                                                                                }
                                                                                arrayMap.remove(Integer.valueOf(i3));
                                                                                i3++;
                                                                                str23 = str8;
                                                                            }
                                                                        }
                                                                        String str29 = str23;
                                                                        com.google.android.gms.internal.measurement.zzgi zzgiVar3 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num2);
                                                                        if (!zZzs2) {
                                                                        }
                                                                        this.zzc.put(num2, new zzu(this, this.zza, zzgiVar3, bitSet, bitSet2, arrayMap, arrayMap10, null));
                                                                        map2 = map2;
                                                                        str23 = str29;
                                                                        zZzs = zZzs;
                                                                        map4 = map4;
                                                                        map3 = map7;
                                                                        str3 = str3;
                                                                    }
                                                                    str5 = str23;
                                                                    str6 = str3;
                                                                    str7 = str4;
                                                                    if (!list.isEmpty()) {
                                                                        zzwVar = new zzw(this, null);
                                                                        arrayMap4 = new ArrayMap();
                                                                        it3 = list.iterator();
                                                                        while (it3.hasNext()) {
                                                                            zzftVar = (com.google.android.gms.internal.measurement.zzft) it3.next();
                                                                            zzftVarZza = zzwVar.zza(this.zza, zzftVar);
                                                                            if (zzftVarZza != null) {
                                                                                zzakVarZzh2 = this.zzf.zzh();
                                                                                str11 = this.zza;
                                                                                strZzh = zzftVarZza.zzh();
                                                                                zzaqVarZzn = zzakVarZzh2.zzn(str11, zzftVar.zzh());
                                                                                if (zzaqVarZzn == null) {
                                                                                    zzakVarZzh2.zzt.zzaA().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzet.zzn(str11), zzakVarZzh2.zzt.zzj().zzd(strZzh));
                                                                                    zzaqVar = new zzaq(str11, zzftVar.zzh(), 1L, 1L, 1L, zzftVar.zzd(), 0L, null, null, null, null);
                                                                                } else {
                                                                                    zzaqVar = new zzaq(zzaqVarZzn.zza, zzaqVarZzn.zzb, zzaqVarZzn.zzc + 1, zzaqVarZzn.zzd + 1, zzaqVarZzn.zze + 1, zzaqVarZzn.zzf, zzaqVarZzn.zzg, zzaqVarZzn.zzh, zzaqVarZzn.zzi, zzaqVarZzn.zzj, zzaqVarZzn.zzk);
                                                                                }
                                                                                this.zzf.zzh().zzE(zzaqVar);
                                                                                j = zzaqVar.zzc;
                                                                                strZzh2 = zzftVarZza.zzh();
                                                                                mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                                                                if (mapEmptyMap == null) {
                                                                                    zzakVarZzh3 = this.zzf.zzh();
                                                                                    str12 = this.zza;
                                                                                    zzakVarZzh3.zzW();
                                                                                    zzakVarZzh3.zzg();
                                                                                    Preconditions.checkNotEmpty(str12);
                                                                                    Preconditions.checkNotEmpty(strZzh2);
                                                                                    arrayMap5 = new ArrayMap();
                                                                                    str13 = str6;
                                                                                    str14 = str25;
                                                                                    try {
                                                                                        try {
                                                                                            str25 = str14;
                                                                                            try {
                                                                                                cursorQuery2 = zzakVarZzh3.zzh().query("event_filters", new String[]{str13, str14}, "app_id=? AND event_name=?", new String[]{str12, strZzh2}, null, null, null);
                                                                                                try {
                                                                                                    try {
                                                                                                        if (cursorQuery2.moveToFirst()) {
                                                                                                            str6 = str13;
                                                                                                            while (true) {
                                                                                                                try {
                                                                                                                    try {
                                                                                                                        com.google.android.gms.internal.measurement.zzek zzekVar2 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursorQuery2.getBlob(1))).zzaD();
                                                                                                                        numValueOf2 = Integer.valueOf(cursorQuery2.getInt(0));
                                                                                                                        list5 = (List) arrayMap5.get(numValueOf2);
                                                                                                                        if (list5 == null) {
                                                                                                                            zzaqVar2 = zzaqVar;
                                                                                                                            try {
                                                                                                                                arrayList2 = new ArrayList();
                                                                                                                                arrayMap5.put(numValueOf2, arrayList2);
                                                                                                                            } catch (SQLiteException e7) {
                                                                                                                                e = e7;
                                                                                                                                zzakVarZzh3.zzt.zzaA().zzd().zzc(str7, zzet.zzn(str12), e);
                                                                                                                                mapEmptyMap = Collections.emptyMap();
                                                                                                                                if (cursorQuery2 != null) {
                                                                                                                                    cursorQuery2.close();
                                                                                                                                }
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            zzaqVar2 = zzaqVar;
                                                                                                                            arrayList2 = list5;
                                                                                                                        }
                                                                                                                        arrayList2.add(zzekVar2);
                                                                                                                    } catch (IOException e8) {
                                                                                                                        zzaqVar2 = zzaqVar;
                                                                                                                        zzakVarZzh3.zzt.zzaA().zzd().zzc(str5, zzet.zzn(str12), e8);
                                                                                                                    }
                                                                                                                    if (!cursorQuery2.moveToNext()) {
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    zzaqVar = zzaqVar2;
                                                                                                                } catch (SQLiteException e9) {
                                                                                                                    e = e9;
                                                                                                                    zzaqVar2 = zzaqVar;
                                                                                                                }
                                                                                                            }
                                                                                                            if (cursorQuery2 != null) {
                                                                                                                cursorQuery2.close();
                                                                                                            }
                                                                                                            mapEmptyMap = arrayMap5;
                                                                                                        } else {
                                                                                                            zzaqVar2 = zzaqVar;
                                                                                                            str6 = str13;
                                                                                                            mapEmptyMap = Collections.emptyMap();
                                                                                                            if (cursorQuery2 != null) {
                                                                                                                cursorQuery2.close();
                                                                                                            }
                                                                                                        }
                                                                                                    } catch (Throwable th) {
                                                                                                        th = th;
                                                                                                        if (cursorQuery2 != null) {
                                                                                                            cursorQuery2.close();
                                                                                                        }
                                                                                                        throw th;
                                                                                                    }
                                                                                                } catch (SQLiteException e10) {
                                                                                                    e = e10;
                                                                                                    zzaqVar2 = zzaqVar;
                                                                                                    str6 = str13;
                                                                                                }
                                                                                            } catch (SQLiteException e11) {
                                                                                                e = e11;
                                                                                                zzaqVar2 = zzaqVar;
                                                                                                str6 = str13;
                                                                                                cursorQuery2 = null;
                                                                                                zzakVarZzh3.zzt.zzaA().zzd().zzc(str7, zzet.zzn(str12), e);
                                                                                                mapEmptyMap = Collections.emptyMap();
                                                                                                if (cursorQuery2 != null) {
                                                                                                    cursorQuery2.close();
                                                                                                }
                                                                                                arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                                for (Integer num3 : mapEmptyMap.keySet()) {
                                                                                                    iIntValue = num3.intValue();
                                                                                                    if (this.zzb.contains(num3)) {
                                                                                                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num3);
                                                                                                    } else {
                                                                                                        it4 = ((List) mapEmptyMap.get(num3)).iterator();
                                                                                                        z2 = true;
                                                                                                        while (true) {
                                                                                                            if (!it4.hasNext()) {
                                                                                                                map6 = mapEmptyMap;
                                                                                                                break;
                                                                                                            }
                                                                                                            com.google.android.gms.internal.measurement.zzek zzekVar3 = (com.google.android.gms.internal.measurement.zzek) it4.next();
                                                                                                            zzxVar = new zzx(this, this.zza, iIntValue, zzekVar3);
                                                                                                            map6 = mapEmptyMap;
                                                                                                            zZzd = zzxVar.zzd(this.zzd, this.zze, zzftVarZza, j, zzaqVar2, zzf(iIntValue, zzekVar3.zzb()));
                                                                                                            if (!zZzd) {
                                                                                                                this.zzb.add(num3);
                                                                                                                z2 = zZzd;
                                                                                                                break;
                                                                                                            }
                                                                                                            zzd(num3).zzc(zzxVar);
                                                                                                            z2 = zZzd;
                                                                                                            mapEmptyMap = map6;
                                                                                                        }
                                                                                                        if (!z2) {
                                                                                                            this.zzb.add(num3);
                                                                                                        }
                                                                                                        mapEmptyMap = map6;
                                                                                                    }
                                                                                                }
                                                                                                zzwVar = zzwVar;
                                                                                                it3 = it3;
                                                                                                str21 = str21;
                                                                                            }
                                                                                        } catch (Throwable th2) {
                                                                                            th = th2;
                                                                                            cursorQuery2 = null;
                                                                                        }
                                                                                    } catch (SQLiteException e12) {
                                                                                        e = e12;
                                                                                        str25 = str14;
                                                                                    }
                                                                                    arrayMap4.put(strZzh2, mapEmptyMap);
                                                                                } else {
                                                                                    zzaqVar2 = zzaqVar;
                                                                                }
                                                                                while (r2.hasNext()) {
                                                                                    iIntValue = num3.intValue();
                                                                                    if (this.zzb.contains(num3)) {
                                                                                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num3);
                                                                                    } else {
                                                                                        it4 = ((List) mapEmptyMap.get(num3)).iterator();
                                                                                        z2 = true;
                                                                                        while (true) {
                                                                                            if (!it4.hasNext()) {
                                                                                                map6 = mapEmptyMap;
                                                                                                break;
                                                                                            }
                                                                                            com.google.android.gms.internal.measurement.zzek zzekVar4 = (com.google.android.gms.internal.measurement.zzek) it4.next();
                                                                                            zzxVar = new zzx(this, this.zza, iIntValue, zzekVar4);
                                                                                            map6 = mapEmptyMap;
                                                                                            zZzd = zzxVar.zzd(this.zzd, this.zze, zzftVarZza, j, zzaqVar2, zzf(iIntValue, zzekVar4.zzb()));
                                                                                            if (!zZzd) {
                                                                                                this.zzb.add(num3);
                                                                                                z2 = zZzd;
                                                                                                break;
                                                                                            }
                                                                                            zzd(num3).zzc(zzxVar);
                                                                                            z2 = zZzd;
                                                                                            mapEmptyMap = map6;
                                                                                        }
                                                                                        if (!z2) {
                                                                                            this.zzb.add(num3);
                                                                                        }
                                                                                        mapEmptyMap = map6;
                                                                                    }
                                                                                }
                                                                                zzwVar = zzwVar;
                                                                                it3 = it3;
                                                                                str21 = str21;
                                                                            }
                                                                        }
                                                                    }
                                                                    str15 = str21;
                                                                    if (!list2.isEmpty()) {
                                                                        arrayMap6 = new ArrayMap();
                                                                        it5 = list2.iterator();
                                                                        while (it5.hasNext()) {
                                                                            com.google.android.gms.internal.measurement.zzgm zzgmVar = (com.google.android.gms.internal.measurement.zzgm) it5.next();
                                                                            strZzf = zzgmVar.zzf();
                                                                            mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                                                            if (mapEmptyMap2 == null) {
                                                                                zzakVarZzh4 = this.zzf.zzh();
                                                                                str18 = this.zza;
                                                                                zzakVarZzh4.zzW();
                                                                                zzakVarZzh4.zzg();
                                                                                Preconditions.checkNotEmpty(str18);
                                                                                Preconditions.checkNotEmpty(strZzf);
                                                                                arrayMap7 = new ArrayMap();
                                                                                str16 = str6;
                                                                                str17 = str25;
                                                                                try {
                                                                                    cursorQuery3 = zzakVarZzh4.zzh().query("property_filters", new String[]{str16, str17}, "app_id=? AND property_name=?", new String[]{str18, strZzf}, null, null, null);
                                                                                    try {
                                                                                        try {
                                                                                            if (cursorQuery3.moveToFirst()) {
                                                                                                while (true) {
                                                                                                    try {
                                                                                                        com.google.android.gms.internal.measurement.zzet zzetVar2 = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) zzlj.zzm(com.google.android.gms.internal.measurement.zzet.zzc(), cursorQuery3.getBlob(1))).zzaD();
                                                                                                        numValueOf5 = Integer.valueOf(cursorQuery3.getInt(0));
                                                                                                        list6 = (List) arrayMap7.get(numValueOf5);
                                                                                                        if (list6 == null) {
                                                                                                            arrayList3 = new ArrayList();
                                                                                                            arrayMap7.put(numValueOf5, arrayList3);
                                                                                                        } else {
                                                                                                            arrayList3 = list6;
                                                                                                        }
                                                                                                        arrayList3.add(zzetVar2);
                                                                                                    } catch (IOException e13) {
                                                                                                        zzakVarZzh4.zzt.zzaA().zzd().zzc("Failed to merge filter", zzet.zzn(str18), e13);
                                                                                                    }
                                                                                                    try {
                                                                                                        if (!cursorQuery3.moveToNext()) {
                                                                                                            break;
                                                                                                        }
                                                                                                        it5 = it5;
                                                                                                    } catch (SQLiteException e14) {
                                                                                                        e = e14;
                                                                                                        zzakVarZzh4.zzt.zzaA().zzd().zzc(str7, zzet.zzn(str18), e);
                                                                                                        mapEmptyMap2 = Collections.emptyMap();
                                                                                                        if (cursorQuery3 != null) {
                                                                                                            cursorQuery3.close();
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (cursorQuery3 != null) {
                                                                                                    cursorQuery3.close();
                                                                                                }
                                                                                                mapEmptyMap2 = arrayMap7;
                                                                                            } else {
                                                                                                it5 = it5;
                                                                                                mapEmptyMap2 = Collections.emptyMap();
                                                                                                if (cursorQuery3 != null) {
                                                                                                    cursorQuery3.close();
                                                                                                }
                                                                                            }
                                                                                        } catch (SQLiteException e15) {
                                                                                            e = e15;
                                                                                            it5 = it5;
                                                                                        }
                                                                                    } catch (Throwable th3) {
                                                                                        th = th3;
                                                                                        cursor = cursorQuery3;
                                                                                        if (cursor != null) {
                                                                                            cursor.close();
                                                                                        }
                                                                                        throw th;
                                                                                    }
                                                                                } catch (SQLiteException e16) {
                                                                                    e = e16;
                                                                                    it5 = it5;
                                                                                    cursorQuery3 = null;
                                                                                } catch (Throwable th4) {
                                                                                    th = th4;
                                                                                    cursor = null;
                                                                                }
                                                                                arrayMap6.put(strZzf, mapEmptyMap2);
                                                                            } else {
                                                                                it5 = it5;
                                                                                str16 = str6;
                                                                                str17 = str25;
                                                                            }
                                                                            for (Integer num4 : mapEmptyMap2.keySet()) {
                                                                                int iIntValue2 = num4.intValue();
                                                                                if (this.zzb.contains(num4)) {
                                                                                    this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num4);
                                                                                    break;
                                                                                }
                                                                                it6 = ((List) mapEmptyMap2.get(num4)).iterator();
                                                                                zZzd2 = true;
                                                                                while (true) {
                                                                                    if (it6.hasNext()) {
                                                                                        zzetVar = (com.google.android.gms.internal.measurement.zzet) it6.next();
                                                                                        if (Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                                                                                            zzer zzerVarZzj = this.zzt.zzaA().zzj();
                                                                                            if (zzetVar.zzj()) {
                                                                                                numValueOf4 = Integer.valueOf(zzetVar.zza());
                                                                                            } else {
                                                                                                numValueOf4 = null;
                                                                                            }
                                                                                            zzerVarZzj.zzd("Evaluating filter. audience, filter, property", num4, numValueOf4, this.zzt.zzj().zzf(zzetVar.zze()));
                                                                                            this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzetVar));
                                                                                        }
                                                                                        if (zzetVar.zzj()) {
                                                                                        }
                                                                                        zzer zzerVarZzk = this.zzt.zzaA().zzk();
                                                                                        Object objZzn = zzet.zzn(this.zza);
                                                                                        if (zzetVar.zzj()) {
                                                                                            numValueOf3 = Integer.valueOf(zzetVar.zza());
                                                                                        } else {
                                                                                            numValueOf3 = null;
                                                                                        }
                                                                                        zzerVarZzk.zzc("Invalid property filter ID. appId, id", objZzn, String.valueOf(numValueOf3));
                                                                                        this.zzb.add(num4);
                                                                                        mapEmptyMap2 = mapEmptyMap2;
                                                                                    } else {
                                                                                        mapEmptyMap2 = mapEmptyMap2;
                                                                                    }
                                                                                    if (!zZzd2) {
                                                                                        this.zzb.add(num4);
                                                                                    }
                                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                                    zzd(num4).zzc(zzzVar);
                                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                                }
                                                                            }
                                                                            it5 = it5;
                                                                            str25 = str17;
                                                                            str6 = str16;
                                                                        }
                                                                    }
                                                                    str19 = str6;
                                                                    arrayList4 = new ArrayList();
                                                                    Set<Integer> setKeySet = this.zzc.keySet();
                                                                    setKeySet.removeAll(this.zzb);
                                                                    for (Integer num5 : setKeySet) {
                                                                        int iIntValue3 = num5.intValue();
                                                                        zzu zzuVar = (zzu) this.zzc.get(num5);
                                                                        Preconditions.checkNotNull(zzuVar);
                                                                        com.google.android.gms.internal.measurement.zzfp zzfpVarZza = zzuVar.zza(iIntValue3);
                                                                        arrayList4.add(zzfpVarZza);
                                                                        zzakVarZzh5 = this.zzf.zzh();
                                                                        str20 = this.zza;
                                                                        com.google.android.gms.internal.measurement.zzgi zzgiVarZzd = zzfpVarZza.zzd();
                                                                        zzakVarZzh5.zzW();
                                                                        zzakVarZzh5.zzg();
                                                                        Preconditions.checkNotEmpty(str20);
                                                                        Preconditions.checkNotNull(zzgiVarZzd);
                                                                        byte[] bArrZzbx = zzgiVarZzd.zzbx();
                                                                        contentValues = new ContentValues();
                                                                        contentValues.put("app_id", str20);
                                                                        contentValues.put(str19, num5);
                                                                        String str30 = str15;
                                                                        contentValues.put(str30, bArrZzbx);
                                                                        try {
                                                                            try {
                                                                                if (zzakVarZzh5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                                                                    zzakVarZzh5.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", zzet.zzn(str20));
                                                                                }
                                                                            } catch (SQLiteException e17) {
                                                                                e = e17;
                                                                                zzakVarZzh5.zzt.zzaA().zzd().zzc("Error storing filter results. appId", zzet.zzn(str20), e);
                                                                            }
                                                                        } catch (SQLiteException e18) {
                                                                            e = e18;
                                                                        }
                                                                        str15 = str30;
                                                                    }
                                                                    return arrayList4;
                                                                }
                                                            } catch (Throwable th5) {
                                                                th = th5;
                                                                if (Zzh != 0) {
                                                                    Zzh.close();
                                                                }
                                                                throw th;
                                                            }
                                                        } catch (SQLiteException e19) {
                                                            e = e19;
                                                            cursorRawQuery = null;
                                                        } catch (Throwable th6) {
                                                            th = th6;
                                                            Zzh = 0;
                                                            if (Zzh != 0) {
                                                                Zzh.close();
                                                            }
                                                            throw th;
                                                        }
                                                        Preconditions.checkNotEmpty(str28);
                                                        Preconditions.checkNotNull(map2);
                                                        arrayMap3 = new ArrayMap();
                                                        if (!map2.isEmpty()) {
                                                            it2 = map2.keySet().iterator();
                                                            while (it2.hasNext()) {
                                                                num = (Integer) it2.next();
                                                                num.intValue();
                                                                zzgiVar2 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num);
                                                                list4 = (List) arrayMap2.get(num);
                                                                if (list4 != null) {
                                                                }
                                                                map5 = arrayMap2;
                                                                str10 = str24;
                                                                arrayMap3.put(num, zzgiVar2);
                                                                arrayMap2 = map5;
                                                                it2 = it2;
                                                                str24 = str10;
                                                            }
                                                        }
                                                        str4 = str24;
                                                        map3 = arrayMap3;
                                                    } else {
                                                        str4 = "Database error querying filters. appId";
                                                        map3 = map2;
                                                    }
                                                    map4 = map;
                                                    while (r16.hasNext()) {
                                                        num2.intValue();
                                                        zzgiVar = (com.google.android.gms.internal.measurement.zzgi) map3.get(num2);
                                                        bitSet = new BitSet();
                                                        bitSet2 = new BitSet();
                                                        arrayMap = new ArrayMap();
                                                        if (zzgiVar != null) {
                                                            while (r2.hasNext()) {
                                                                if (zzfrVar.zzh()) {
                                                                    Integer numValueOf8 = Integer.valueOf(zzfrVar.zza());
                                                                    if (zzfrVar.zzg()) {
                                                                        lValueOf = Long.valueOf(zzfrVar.zzb());
                                                                    } else {
                                                                        lValueOf = null;
                                                                    }
                                                                    arrayMap.put(numValueOf8, lValueOf);
                                                                }
                                                            }
                                                        }
                                                        ArrayMap arrayMap11 = new ArrayMap();
                                                        if (zzgiVar != null) {
                                                            it = zzgiVar.zzj().iterator();
                                                            while (it.hasNext()) {
                                                                zzgkVar = (com.google.android.gms.internal.measurement.zzgk) it.next();
                                                                if (!zzgkVar.zzi()) {
                                                                }
                                                            }
                                                        }
                                                        Map map8 = map3;
                                                        if (zzgiVar != null) {
                                                            i3 = 0;
                                                            while (i3 < zzgiVar.zzd() * 64) {
                                                                if (zzlj.zzw(zzgiVar.zzk(), i3)) {
                                                                    str8 = str23;
                                                                    this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", num2, Integer.valueOf(i3));
                                                                    bitSet2.set(i3);
                                                                    if (zzlj.zzw(zzgiVar.zzi(), i3)) {
                                                                        bitSet.set(i3);
                                                                    }
                                                                    i3++;
                                                                    str23 = str8;
                                                                } else {
                                                                    str8 = str23;
                                                                }
                                                                arrayMap.remove(Integer.valueOf(i3));
                                                                i3++;
                                                                str23 = str8;
                                                            }
                                                        }
                                                        String str210 = str23;
                                                        com.google.android.gms.internal.measurement.zzgi zzgiVar4 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num2);
                                                        if (!zZzs2) {
                                                        }
                                                        this.zzc.put(num2, new zzu(this, this.zza, zzgiVar4, bitSet, bitSet2, arrayMap, arrayMap11, null));
                                                        map2 = map2;
                                                        str23 = str210;
                                                        zZzs = zZzs;
                                                        map4 = map4;
                                                        map3 = map8;
                                                        str3 = str3;
                                                    }
                                                    str5 = str23;
                                                    str6 = str3;
                                                    str7 = str4;
                                                }
                                                if (!list.isEmpty()) {
                                                    zzwVar = new zzw(this, null);
                                                    arrayMap4 = new ArrayMap();
                                                    it3 = list.iterator();
                                                    while (it3.hasNext()) {
                                                        zzftVar = (com.google.android.gms.internal.measurement.zzft) it3.next();
                                                        zzftVarZza = zzwVar.zza(this.zza, zzftVar);
                                                        if (zzftVarZza != null) {
                                                            zzakVarZzh2 = this.zzf.zzh();
                                                            str11 = this.zza;
                                                            strZzh = zzftVarZza.zzh();
                                                            zzaqVarZzn = zzakVarZzh2.zzn(str11, zzftVar.zzh());
                                                            if (zzaqVarZzn == null) {
                                                                zzakVarZzh2.zzt.zzaA().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzet.zzn(str11), zzakVarZzh2.zzt.zzj().zzd(strZzh));
                                                                zzaqVar = new zzaq(str11, zzftVar.zzh(), 1L, 1L, 1L, zzftVar.zzd(), 0L, null, null, null, null);
                                                            } else {
                                                                zzaqVar = new zzaq(zzaqVarZzn.zza, zzaqVarZzn.zzb, zzaqVarZzn.zzc + 1, zzaqVarZzn.zzd + 1, zzaqVarZzn.zze + 1, zzaqVarZzn.zzf, zzaqVarZzn.zzg, zzaqVarZzn.zzh, zzaqVarZzn.zzi, zzaqVarZzn.zzj, zzaqVarZzn.zzk);
                                                            }
                                                            this.zzf.zzh().zzE(zzaqVar);
                                                            j = zzaqVar.zzc;
                                                            strZzh2 = zzftVarZza.zzh();
                                                            mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                                            if (mapEmptyMap == null) {
                                                                zzakVarZzh3 = this.zzf.zzh();
                                                                str12 = this.zza;
                                                                zzakVarZzh3.zzW();
                                                                zzakVarZzh3.zzg();
                                                                Preconditions.checkNotEmpty(str12);
                                                                Preconditions.checkNotEmpty(strZzh2);
                                                                arrayMap5 = new ArrayMap();
                                                                str13 = str6;
                                                                str14 = str25;
                                                                str25 = str14;
                                                                cursorQuery2 = zzakVarZzh3.zzh().query("event_filters", new String[]{str13, str14}, "app_id=? AND event_name=?", new String[]{str12, strZzh2}, null, null, null);
                                                                if (cursorQuery2.moveToFirst()) {
                                                                    str6 = str13;
                                                                    while (true) {
                                                                        com.google.android.gms.internal.measurement.zzek zzekVar5 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursorQuery2.getBlob(1))).zzaD();
                                                                        numValueOf2 = Integer.valueOf(cursorQuery2.getInt(0));
                                                                        list5 = (List) arrayMap5.get(numValueOf2);
                                                                        if (list5 == null) {
                                                                            zzaqVar2 = zzaqVar;
                                                                            arrayList2 = new ArrayList();
                                                                            arrayMap5.put(numValueOf2, arrayList2);
                                                                        } else {
                                                                            zzaqVar2 = zzaqVar;
                                                                            arrayList2 = list5;
                                                                        }
                                                                        arrayList2.add(zzekVar5);
                                                                        if (!cursorQuery2.moveToNext()) {
                                                                            break;
                                                                            break;
                                                                        }
                                                                        zzaqVar = zzaqVar2;
                                                                    }
                                                                    if (cursorQuery2 != null) {
                                                                        cursorQuery2.close();
                                                                    }
                                                                    mapEmptyMap = arrayMap5;
                                                                } else {
                                                                    zzaqVar2 = zzaqVar;
                                                                    str6 = str13;
                                                                    mapEmptyMap = Collections.emptyMap();
                                                                    if (cursorQuery2 != null) {
                                                                        cursorQuery2.close();
                                                                    }
                                                                }
                                                                arrayMap4.put(strZzh2, mapEmptyMap);
                                                            } else {
                                                                zzaqVar2 = zzaqVar;
                                                            }
                                                            while (r2.hasNext()) {
                                                                iIntValue = num3.intValue();
                                                                if (this.zzb.contains(num3)) {
                                                                    this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num3);
                                                                } else {
                                                                    it4 = ((List) mapEmptyMap.get(num3)).iterator();
                                                                    z2 = true;
                                                                    while (true) {
                                                                        if (!it4.hasNext()) {
                                                                            map6 = mapEmptyMap;
                                                                            break;
                                                                        }
                                                                        com.google.android.gms.internal.measurement.zzek zzekVar6 = (com.google.android.gms.internal.measurement.zzek) it4.next();
                                                                        zzxVar = new zzx(this, this.zza, iIntValue, zzekVar6);
                                                                        map6 = mapEmptyMap;
                                                                        zZzd = zzxVar.zzd(this.zzd, this.zze, zzftVarZza, j, zzaqVar2, zzf(iIntValue, zzekVar6.zzb()));
                                                                        if (!zZzd) {
                                                                            this.zzb.add(num3);
                                                                            z2 = zZzd;
                                                                            break;
                                                                        }
                                                                        zzd(num3).zzc(zzxVar);
                                                                        z2 = zZzd;
                                                                        mapEmptyMap = map6;
                                                                    }
                                                                    if (!z2) {
                                                                        this.zzb.add(num3);
                                                                    }
                                                                    mapEmptyMap = map6;
                                                                }
                                                            }
                                                            zzwVar = zzwVar;
                                                            it3 = it3;
                                                            str21 = str21;
                                                        }
                                                    }
                                                }
                                                str15 = str21;
                                                if (!list2.isEmpty()) {
                                                    arrayMap6 = new ArrayMap();
                                                    it5 = list2.iterator();
                                                    while (it5.hasNext()) {
                                                        com.google.android.gms.internal.measurement.zzgm zzgmVar2 = (com.google.android.gms.internal.measurement.zzgm) it5.next();
                                                        strZzf = zzgmVar2.zzf();
                                                        mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                                        if (mapEmptyMap2 == null) {
                                                            zzakVarZzh4 = this.zzf.zzh();
                                                            str18 = this.zza;
                                                            zzakVarZzh4.zzW();
                                                            zzakVarZzh4.zzg();
                                                            Preconditions.checkNotEmpty(str18);
                                                            Preconditions.checkNotEmpty(strZzf);
                                                            arrayMap7 = new ArrayMap();
                                                            str16 = str6;
                                                            str17 = str25;
                                                            cursorQuery3 = zzakVarZzh4.zzh().query("property_filters", new String[]{str16, str17}, "app_id=? AND property_name=?", new String[]{str18, strZzf}, null, null, null);
                                                            if (cursorQuery3.moveToFirst()) {
                                                                while (true) {
                                                                    com.google.android.gms.internal.measurement.zzet zzetVar3 = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) zzlj.zzm(com.google.android.gms.internal.measurement.zzet.zzc(), cursorQuery3.getBlob(1))).zzaD();
                                                                    numValueOf5 = Integer.valueOf(cursorQuery3.getInt(0));
                                                                    list6 = (List) arrayMap7.get(numValueOf5);
                                                                    if (list6 == null) {
                                                                        arrayList3 = new ArrayList();
                                                                        arrayMap7.put(numValueOf5, arrayList3);
                                                                    } else {
                                                                        arrayList3 = list6;
                                                                    }
                                                                    arrayList3.add(zzetVar3);
                                                                    if (!cursorQuery3.moveToNext()) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    it5 = it5;
                                                                }
                                                                if (cursorQuery3 != null) {
                                                                    cursorQuery3.close();
                                                                }
                                                                mapEmptyMap2 = arrayMap7;
                                                            } else {
                                                                it5 = it5;
                                                                mapEmptyMap2 = Collections.emptyMap();
                                                                if (cursorQuery3 != null) {
                                                                    cursorQuery3.close();
                                                                }
                                                            }
                                                            arrayMap6.put(strZzf, mapEmptyMap2);
                                                        } else {
                                                            it5 = it5;
                                                            str16 = str6;
                                                            str17 = str25;
                                                        }
                                                        while (r3.hasNext()) {
                                                            int iIntValue4 = num4.intValue();
                                                            if (this.zzb.contains(num4)) {
                                                                this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num4);
                                                                break;
                                                                break;
                                                            }
                                                            it6 = ((List) mapEmptyMap2.get(num4)).iterator();
                                                            zZzd2 = true;
                                                            while (true) {
                                                                if (it6.hasNext()) {
                                                                    zzetVar = (com.google.android.gms.internal.measurement.zzet) it6.next();
                                                                    if (Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                                                                        zzer zzerVarZzj2 = this.zzt.zzaA().zzj();
                                                                        if (zzetVar.zzj()) {
                                                                            numValueOf4 = Integer.valueOf(zzetVar.zza());
                                                                        } else {
                                                                            numValueOf4 = null;
                                                                        }
                                                                        zzerVarZzj2.zzd("Evaluating filter. audience, filter, property", num4, numValueOf4, this.zzt.zzj().zzf(zzetVar.zze()));
                                                                        this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzetVar));
                                                                    }
                                                                    if (zzetVar.zzj()) {
                                                                    }
                                                                    zzer zzerVarZzk2 = this.zzt.zzaA().zzk();
                                                                    Object objZzn2 = zzet.zzn(this.zza);
                                                                    if (zzetVar.zzj()) {
                                                                        numValueOf3 = Integer.valueOf(zzetVar.zza());
                                                                    } else {
                                                                        numValueOf3 = null;
                                                                    }
                                                                    zzerVarZzk2.zzc("Invalid property filter ID. appId, id", objZzn2, String.valueOf(numValueOf3));
                                                                    this.zzb.add(num4);
                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                } else {
                                                                    mapEmptyMap2 = mapEmptyMap2;
                                                                }
                                                                if (!zZzd2) {
                                                                    this.zzb.add(num4);
                                                                }
                                                                mapEmptyMap2 = mapEmptyMap2;
                                                                zzd(num4).zzc(zzzVar);
                                                                mapEmptyMap2 = mapEmptyMap2;
                                                            }
                                                        }
                                                        it5 = it5;
                                                        str25 = str17;
                                                        str6 = str16;
                                                    }
                                                }
                                                str19 = str6;
                                                arrayList4 = new ArrayList();
                                                Set<Integer> setKeySet2 = this.zzc.keySet();
                                                setKeySet2.removeAll(this.zzb);
                                                while (r2.hasNext()) {
                                                    int iIntValue5 = num5.intValue();
                                                    zzu zzuVar2 = (zzu) this.zzc.get(num5);
                                                    Preconditions.checkNotNull(zzuVar2);
                                                    com.google.android.gms.internal.measurement.zzfp zzfpVarZza2 = zzuVar2.zza(iIntValue5);
                                                    arrayList4.add(zzfpVarZza2);
                                                    zzakVarZzh5 = this.zzf.zzh();
                                                    str20 = this.zza;
                                                    com.google.android.gms.internal.measurement.zzgi zzgiVarZzd2 = zzfpVarZza2.zzd();
                                                    zzakVarZzh5.zzW();
                                                    zzakVarZzh5.zzg();
                                                    Preconditions.checkNotEmpty(str20);
                                                    Preconditions.checkNotNull(zzgiVarZzd2);
                                                    byte[] bArrZzbx2 = zzgiVarZzd2.zzbx();
                                                    contentValues = new ContentValues();
                                                    contentValues.put("app_id", str20);
                                                    contentValues.put(str19, num5);
                                                    String str31 = str15;
                                                    contentValues.put(str31, bArrZzbx2);
                                                    if (zzakVarZzh5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                                        zzakVarZzh5.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", zzet.zzn(str20));
                                                    }
                                                    str15 = str31;
                                                }
                                                return arrayList4;
                                            }
                                        }
                                        try {
                                            if (!cursorQuery.moveToNext()) {
                                                break;
                                            }
                                            arrayMap8 = arrayMap8;
                                            str26 = str3;
                                            str25 = str25;
                                        } catch (SQLiteException e20) {
                                            e = e20;
                                            zzakVarZzh.zzt.zzaA().zzd().zzc("Database error querying filter results. appId", zzet.zzn(str2), e);
                                            Map mapEmptyMap5 = Collections.emptyMap();
                                            if (cursorQuery != null) {
                                                cursorQuery.close();
                                            }
                                            map2 = mapEmptyMap5;
                                        }
                                    }
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    map2 = arrayMap8;
                                } else {
                                    Map mapEmptyMap6 = Collections.emptyMap();
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    map2 = mapEmptyMap6;
                                    str3 = "audience_id";
                                    str25 = "data";
                                }
                            } catch (SQLiteException e21) {
                                e = e21;
                                str3 = "audience_id";
                            }
                            if (map2.isEmpty()) {
                                str7 = "Database error querying filters. appId";
                                str5 = "Failed to merge filter. appId";
                                str6 = str3;
                            } else {
                                HashSet<Integer> hashSet2 = new HashSet(map2.keySet());
                                if (z) {
                                    String str211 = this.zza;
                                    zzak zzakVarZzh9 = this.zzf.zzh();
                                    str9 = this.zza;
                                    zzakVarZzh9.zzW();
                                    zzakVarZzh9.zzg();
                                    Preconditions.checkNotEmpty(str9);
                                    arrayMap2 = new ArrayMap();
                                    Zzh = zzakVarZzh9.zzh();
                                    cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str9, str9});
                                    if (cursorRawQuery.moveToFirst()) {
                                        do {
                                            numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                                            arrayList = (List) arrayMap2.get(numValueOf);
                                            if (arrayList == null) {
                                                arrayList = new ArrayList();
                                                arrayMap2.put(numValueOf, arrayList);
                                            }
                                            arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                                        } while (cursorRawQuery.moveToNext());
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                        }
                                    } else {
                                        arrayMap2 = Collections.emptyMap();
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                        }
                                    }
                                    Preconditions.checkNotEmpty(str211);
                                    Preconditions.checkNotNull(map2);
                                    arrayMap3 = new ArrayMap();
                                    if (!map2.isEmpty()) {
                                        it2 = map2.keySet().iterator();
                                        while (it2.hasNext()) {
                                            num = (Integer) it2.next();
                                            num.intValue();
                                            zzgiVar2 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num);
                                            list4 = (List) arrayMap2.get(num);
                                            if (list4 != null) {
                                            }
                                            map5 = arrayMap2;
                                            str10 = str24;
                                            arrayMap3.put(num, zzgiVar2);
                                            arrayMap2 = map5;
                                            it2 = it2;
                                            str24 = str10;
                                        }
                                    }
                                    str4 = str24;
                                    map3 = arrayMap3;
                                } else {
                                    str4 = "Database error querying filters. appId";
                                    map3 = map2;
                                }
                                map4 = map;
                                while (r16.hasNext()) {
                                    num2.intValue();
                                    zzgiVar = (com.google.android.gms.internal.measurement.zzgi) map3.get(num2);
                                    bitSet = new BitSet();
                                    bitSet2 = new BitSet();
                                    arrayMap = new ArrayMap();
                                    if (zzgiVar != null) {
                                        while (r2.hasNext()) {
                                            if (zzfrVar.zzh()) {
                                                Integer numValueOf9 = Integer.valueOf(zzfrVar.zza());
                                                if (zzfrVar.zzg()) {
                                                    lValueOf = Long.valueOf(zzfrVar.zzb());
                                                } else {
                                                    lValueOf = null;
                                                }
                                                arrayMap.put(numValueOf9, lValueOf);
                                            }
                                        }
                                    }
                                    ArrayMap arrayMap12 = new ArrayMap();
                                    if (zzgiVar != null) {
                                        it = zzgiVar.zzj().iterator();
                                        while (it.hasNext()) {
                                            zzgkVar = (com.google.android.gms.internal.measurement.zzgk) it.next();
                                            if (!zzgkVar.zzi()) {
                                            }
                                        }
                                    }
                                    Map map9 = map3;
                                    if (zzgiVar != null) {
                                        i3 = 0;
                                        while (i3 < zzgiVar.zzd() * 64) {
                                            if (zzlj.zzw(zzgiVar.zzk(), i3)) {
                                                str8 = str23;
                                                this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", num2, Integer.valueOf(i3));
                                                bitSet2.set(i3);
                                                if (zzlj.zzw(zzgiVar.zzi(), i3)) {
                                                    bitSet.set(i3);
                                                }
                                                i3++;
                                                str23 = str8;
                                            } else {
                                                str8 = str23;
                                            }
                                            arrayMap.remove(Integer.valueOf(i3));
                                            i3++;
                                            str23 = str8;
                                        }
                                    }
                                    String str212 = str23;
                                    com.google.android.gms.internal.measurement.zzgi zzgiVar5 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num2);
                                    if (!zZzs2) {
                                    }
                                    this.zzc.put(num2, new zzu(this, this.zza, zzgiVar5, bitSet, bitSet2, arrayMap, arrayMap12, null));
                                    map2 = map2;
                                    str23 = str212;
                                    zZzs = zZzs;
                                    map4 = map4;
                                    map3 = map9;
                                    str3 = str3;
                                }
                                str5 = str23;
                                str6 = str3;
                                str7 = str4;
                            }
                            if (!list.isEmpty()) {
                                zzwVar = new zzw(this, null);
                                arrayMap4 = new ArrayMap();
                                it3 = list.iterator();
                                while (it3.hasNext()) {
                                    zzftVar = (com.google.android.gms.internal.measurement.zzft) it3.next();
                                    zzftVarZza = zzwVar.zza(this.zza, zzftVar);
                                    if (zzftVarZza != null) {
                                        zzakVarZzh2 = this.zzf.zzh();
                                        str11 = this.zza;
                                        strZzh = zzftVarZza.zzh();
                                        zzaqVarZzn = zzakVarZzh2.zzn(str11, zzftVar.zzh());
                                        if (zzaqVarZzn == null) {
                                            zzakVarZzh2.zzt.zzaA().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzet.zzn(str11), zzakVarZzh2.zzt.zzj().zzd(strZzh));
                                            zzaqVar = new zzaq(str11, zzftVar.zzh(), 1L, 1L, 1L, zzftVar.zzd(), 0L, null, null, null, null);
                                        } else {
                                            zzaqVar = new zzaq(zzaqVarZzn.zza, zzaqVarZzn.zzb, zzaqVarZzn.zzc + 1, zzaqVarZzn.zzd + 1, zzaqVarZzn.zze + 1, zzaqVarZzn.zzf, zzaqVarZzn.zzg, zzaqVarZzn.zzh, zzaqVarZzn.zzi, zzaqVarZzn.zzj, zzaqVarZzn.zzk);
                                        }
                                        this.zzf.zzh().zzE(zzaqVar);
                                        j = zzaqVar.zzc;
                                        strZzh2 = zzftVarZza.zzh();
                                        mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                                        if (mapEmptyMap == null) {
                                            zzakVarZzh3 = this.zzf.zzh();
                                            str12 = this.zza;
                                            zzakVarZzh3.zzW();
                                            zzakVarZzh3.zzg();
                                            Preconditions.checkNotEmpty(str12);
                                            Preconditions.checkNotEmpty(strZzh2);
                                            arrayMap5 = new ArrayMap();
                                            str13 = str6;
                                            str14 = str25;
                                            str25 = str14;
                                            cursorQuery2 = zzakVarZzh3.zzh().query("event_filters", new String[]{str13, str14}, "app_id=? AND event_name=?", new String[]{str12, strZzh2}, null, null, null);
                                            if (cursorQuery2.moveToFirst()) {
                                                str6 = str13;
                                                while (true) {
                                                    com.google.android.gms.internal.measurement.zzek zzekVar7 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursorQuery2.getBlob(1))).zzaD();
                                                    numValueOf2 = Integer.valueOf(cursorQuery2.getInt(0));
                                                    list5 = (List) arrayMap5.get(numValueOf2);
                                                    if (list5 == null) {
                                                        zzaqVar2 = zzaqVar;
                                                        arrayList2 = new ArrayList();
                                                        arrayMap5.put(numValueOf2, arrayList2);
                                                    } else {
                                                        zzaqVar2 = zzaqVar;
                                                        arrayList2 = list5;
                                                    }
                                                    arrayList2.add(zzekVar7);
                                                    if (!cursorQuery2.moveToNext()) {
                                                        break;
                                                        break;
                                                    }
                                                    zzaqVar = zzaqVar2;
                                                }
                                                if (cursorQuery2 != null) {
                                                    cursorQuery2.close();
                                                }
                                                mapEmptyMap = arrayMap5;
                                            } else {
                                                zzaqVar2 = zzaqVar;
                                                str6 = str13;
                                                mapEmptyMap = Collections.emptyMap();
                                                if (cursorQuery2 != null) {
                                                    cursorQuery2.close();
                                                }
                                            }
                                            arrayMap4.put(strZzh2, mapEmptyMap);
                                        } else {
                                            zzaqVar2 = zzaqVar;
                                        }
                                        while (r2.hasNext()) {
                                            iIntValue = num3.intValue();
                                            if (this.zzb.contains(num3)) {
                                                this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num3);
                                            } else {
                                                it4 = ((List) mapEmptyMap.get(num3)).iterator();
                                                z2 = true;
                                                while (true) {
                                                    if (!it4.hasNext()) {
                                                        map6 = mapEmptyMap;
                                                        break;
                                                    }
                                                    com.google.android.gms.internal.measurement.zzek zzekVar8 = (com.google.android.gms.internal.measurement.zzek) it4.next();
                                                    zzxVar = new zzx(this, this.zza, iIntValue, zzekVar8);
                                                    map6 = mapEmptyMap;
                                                    zZzd = zzxVar.zzd(this.zzd, this.zze, zzftVarZza, j, zzaqVar2, zzf(iIntValue, zzekVar8.zzb()));
                                                    if (!zZzd) {
                                                        this.zzb.add(num3);
                                                        z2 = zZzd;
                                                        break;
                                                    }
                                                    zzd(num3).zzc(zzxVar);
                                                    z2 = zZzd;
                                                    mapEmptyMap = map6;
                                                }
                                                if (!z2) {
                                                    this.zzb.add(num3);
                                                }
                                                mapEmptyMap = map6;
                                            }
                                        }
                                        zzwVar = zzwVar;
                                        it3 = it3;
                                        str21 = str21;
                                    }
                                }
                            }
                            str15 = str21;
                            if (!list2.isEmpty()) {
                                arrayMap6 = new ArrayMap();
                                it5 = list2.iterator();
                                while (it5.hasNext()) {
                                    com.google.android.gms.internal.measurement.zzgm zzgmVar3 = (com.google.android.gms.internal.measurement.zzgm) it5.next();
                                    strZzf = zzgmVar3.zzf();
                                    mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                                    if (mapEmptyMap2 == null) {
                                        zzakVarZzh4 = this.zzf.zzh();
                                        str18 = this.zza;
                                        zzakVarZzh4.zzW();
                                        zzakVarZzh4.zzg();
                                        Preconditions.checkNotEmpty(str18);
                                        Preconditions.checkNotEmpty(strZzf);
                                        arrayMap7 = new ArrayMap();
                                        str16 = str6;
                                        str17 = str25;
                                        cursorQuery3 = zzakVarZzh4.zzh().query("property_filters", new String[]{str16, str17}, "app_id=? AND property_name=?", new String[]{str18, strZzf}, null, null, null);
                                        if (cursorQuery3.moveToFirst()) {
                                            while (true) {
                                                com.google.android.gms.internal.measurement.zzet zzetVar4 = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) zzlj.zzm(com.google.android.gms.internal.measurement.zzet.zzc(), cursorQuery3.getBlob(1))).zzaD();
                                                numValueOf5 = Integer.valueOf(cursorQuery3.getInt(0));
                                                list6 = (List) arrayMap7.get(numValueOf5);
                                                if (list6 == null) {
                                                    arrayList3 = new ArrayList();
                                                    arrayMap7.put(numValueOf5, arrayList3);
                                                } else {
                                                    arrayList3 = list6;
                                                }
                                                arrayList3.add(zzetVar4);
                                                if (!cursorQuery3.moveToNext()) {
                                                    break;
                                                    break;
                                                }
                                                it5 = it5;
                                            }
                                            if (cursorQuery3 != null) {
                                                cursorQuery3.close();
                                            }
                                            mapEmptyMap2 = arrayMap7;
                                        } else {
                                            it5 = it5;
                                            mapEmptyMap2 = Collections.emptyMap();
                                            if (cursorQuery3 != null) {
                                                cursorQuery3.close();
                                            }
                                        }
                                        arrayMap6.put(strZzf, mapEmptyMap2);
                                    } else {
                                        it5 = it5;
                                        str16 = str6;
                                        str17 = str25;
                                    }
                                    while (r3.hasNext()) {
                                        int iIntValue6 = num4.intValue();
                                        if (this.zzb.contains(num4)) {
                                            this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num4);
                                            break;
                                            break;
                                        }
                                        it6 = ((List) mapEmptyMap2.get(num4)).iterator();
                                        zZzd2 = true;
                                        while (true) {
                                            if (it6.hasNext()) {
                                                zzetVar = (com.google.android.gms.internal.measurement.zzet) it6.next();
                                                if (Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                                                    zzer zzerVarZzj3 = this.zzt.zzaA().zzj();
                                                    if (zzetVar.zzj()) {
                                                        numValueOf4 = Integer.valueOf(zzetVar.zza());
                                                    } else {
                                                        numValueOf4 = null;
                                                    }
                                                    zzerVarZzj3.zzd("Evaluating filter. audience, filter, property", num4, numValueOf4, this.zzt.zzj().zzf(zzetVar.zze()));
                                                    this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzetVar));
                                                }
                                                if (zzetVar.zzj()) {
                                                }
                                                zzer zzerVarZzk3 = this.zzt.zzaA().zzk();
                                                Object objZzn3 = zzet.zzn(this.zza);
                                                if (zzetVar.zzj()) {
                                                    numValueOf3 = Integer.valueOf(zzetVar.zza());
                                                } else {
                                                    numValueOf3 = null;
                                                }
                                                zzerVarZzk3.zzc("Invalid property filter ID. appId, id", objZzn3, String.valueOf(numValueOf3));
                                                this.zzb.add(num4);
                                                mapEmptyMap2 = mapEmptyMap2;
                                            } else {
                                                mapEmptyMap2 = mapEmptyMap2;
                                            }
                                            if (!zZzd2) {
                                                this.zzb.add(num4);
                                            }
                                            mapEmptyMap2 = mapEmptyMap2;
                                            zzd(num4).zzc(zzzVar);
                                            mapEmptyMap2 = mapEmptyMap2;
                                        }
                                    }
                                    it5 = it5;
                                    str25 = str17;
                                    str6 = str16;
                                }
                            }
                            str19 = str6;
                            arrayList4 = new ArrayList();
                            Set<Integer> setKeySet3 = this.zzc.keySet();
                            setKeySet3.removeAll(this.zzb);
                            while (r2.hasNext()) {
                                int iIntValue7 = num5.intValue();
                                zzu zzuVar3 = (zzu) this.zzc.get(num5);
                                Preconditions.checkNotNull(zzuVar3);
                                com.google.android.gms.internal.measurement.zzfp zzfpVarZza3 = zzuVar3.zza(iIntValue7);
                                arrayList4.add(zzfpVarZza3);
                                zzakVarZzh5 = this.zzf.zzh();
                                str20 = this.zza;
                                com.google.android.gms.internal.measurement.zzgi zzgiVarZzd3 = zzfpVarZza3.zzd();
                                zzakVarZzh5.zzW();
                                zzakVarZzh5.zzg();
                                Preconditions.checkNotEmpty(str20);
                                Preconditions.checkNotNull(zzgiVarZzd3);
                                byte[] bArrZzbx3 = zzgiVarZzd3.zzbx();
                                contentValues = new ContentValues();
                                contentValues.put("app_id", str20);
                                contentValues.put(str19, num5);
                                String str32 = str15;
                                contentValues.put(str32, bArrZzbx3);
                                if (zzakVarZzh5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                    zzakVarZzh5.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", zzet.zzn(str20));
                                }
                                str15 = str32;
                            }
                            return arrayList4;
                        } catch (Throwable th7) {
                            th = th7;
                            r7 = r6;
                            if (r7 != 0) {
                                r7.close();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th8) {
                    th = th8;
                    if (r5 != 0) {
                        r5.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e22) {
                e = e22;
                cursorQuery4 = null;
            } catch (Throwable th9) {
                th = th9;
                r5 = 0;
                if (r5 != 0) {
                    r5.close();
                }
                throw th;
            }
        } else {
            map = mapEmptyMap3;
            r6 = r9;
        }
        zzakVarZzh = this.zzf.zzh();
        str2 = this.zza;
        zzakVarZzh.zzW();
        zzakVarZzh.zzg();
        Preconditions.checkNotEmpty(str2);
        try {
            cursorQuery = zzakVarZzh.zzh().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str2}, null, null, null);
            if (cursorQuery.moveToFirst()) {
                Map mapEmptyMap7 = Collections.emptyMap();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                map2 = mapEmptyMap7;
                str3 = "audience_id";
                str25 = "data";
            } else {
                arrayMap8 = new ArrayMap();
                while (true) {
                    i4 = cursorQuery.getInt(0);
                    arrayMap8.put(Integer.valueOf(i4), (com.google.android.gms.internal.measurement.zzgi) ((com.google.android.gms.internal.measurement.zzgh) zzlj.zzm(com.google.android.gms.internal.measurement.zzgi.zze(), cursorQuery.getBlob(1))).zzaD());
                    str3 = str26;
                    if (!cursorQuery.moveToNext()) {
                        break;
                        break;
                    }
                    arrayMap8 = arrayMap8;
                    str26 = str3;
                    str25 = str25;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                map2 = arrayMap8;
            }
        } catch (SQLiteException e23) {
            e = e23;
            str3 = "audience_id";
            str25 = "data";
            cursorQuery = null;
        } catch (Throwable th10) {
            th = th10;
            r7 = 0;
            if (r7 != 0) {
                r7.close();
            }
            throw th;
        }
        if (map2.isEmpty()) {
            str7 = "Database error querying filters. appId";
            str5 = "Failed to merge filter. appId";
            str6 = str3;
        } else {
            HashSet<Integer> hashSet3 = new HashSet(map2.keySet());
            if (z) {
                String str213 = this.zza;
                zzak zzakVarZzh10 = this.zzf.zzh();
                str9 = this.zza;
                zzakVarZzh10.zzW();
                zzakVarZzh10.zzg();
                Preconditions.checkNotEmpty(str9);
                arrayMap2 = new ArrayMap();
                Zzh = zzakVarZzh10.zzh();
                cursorRawQuery = Zzh.rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str9, str9});
                if (cursorRawQuery.moveToFirst()) {
                    do {
                        numValueOf = Integer.valueOf(cursorRawQuery.getInt(0));
                        arrayList = (List) arrayMap2.get(numValueOf);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            arrayMap2.put(numValueOf, arrayList);
                        }
                        arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                    } while (cursorRawQuery.moveToNext());
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } else {
                    arrayMap2 = Collections.emptyMap();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                }
                Preconditions.checkNotEmpty(str213);
                Preconditions.checkNotNull(map2);
                arrayMap3 = new ArrayMap();
                if (!map2.isEmpty()) {
                    it2 = map2.keySet().iterator();
                    while (it2.hasNext()) {
                        num = (Integer) it2.next();
                        num.intValue();
                        zzgiVar2 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num);
                        list4 = (List) arrayMap2.get(num);
                        if (list4 != null || list4.isEmpty()) {
                            map5 = arrayMap2;
                            str10 = str24;
                            arrayMap3.put(num, zzgiVar2);
                            arrayMap2 = map5;
                            it2 = it2;
                            str24 = str10;
                        } else {
                            map5 = arrayMap2;
                            List listZzr = this.zzf.zzu().zzr(zzgiVar2.zzi(), list4);
                            if (listZzr.isEmpty()) {
                                arrayMap2 = map5;
                            } else {
                                com.google.android.gms.internal.measurement.zzgh zzghVar = (com.google.android.gms.internal.measurement.zzgh) zzgiVar2.zzbB();
                                zzghVar.zzf();
                                zzghVar.zzb(listZzr);
                                List listZzr2 = this.zzf.zzu().zzr(zzgiVar2.zzk(), list4);
                                zzghVar.zzh();
                                zzghVar.zzd(listZzr2);
                                ArrayList arrayList6 = new ArrayList();
                                Iterator it8 = zzgiVar2.zzh().iterator();
                                while (it8.hasNext()) {
                                    Iterator it9 = it8;
                                    com.google.android.gms.internal.measurement.zzfr zzfrVar2 = (com.google.android.gms.internal.measurement.zzfr) it8.next();
                                    String str33 = str24;
                                    if (!list4.contains(Integer.valueOf(zzfrVar2.zza()))) {
                                        arrayList6.add(zzfrVar2);
                                    }
                                    it8 = it9;
                                    str24 = str33;
                                }
                                str10 = str24;
                                zzghVar.zze();
                                zzghVar.zza(arrayList6);
                                ArrayList arrayList7 = new ArrayList();
                                for (com.google.android.gms.internal.measurement.zzgk zzgkVar2 : zzgiVar2.zzj()) {
                                    if (!list4.contains(Integer.valueOf(zzgkVar2.zzb()))) {
                                        arrayList7.add(zzgkVar2);
                                    }
                                }
                                zzghVar.zzg();
                                zzghVar.zzc(arrayList7);
                                arrayMap3.put(num, (com.google.android.gms.internal.measurement.zzgi) zzghVar.zzaD());
                                arrayMap2 = map5;
                                it2 = it2;
                                str24 = str10;
                            }
                        }
                    }
                }
                str4 = str24;
                map3 = arrayMap3;
            } else {
                str4 = "Database error querying filters. appId";
                map3 = map2;
            }
            map4 = map;
            while (r16.hasNext()) {
                num2.intValue();
                zzgiVar = (com.google.android.gms.internal.measurement.zzgi) map3.get(num2);
                bitSet = new BitSet();
                bitSet2 = new BitSet();
                arrayMap = new ArrayMap();
                if (zzgiVar != null && zzgiVar.zza() != 0) {
                    while (r2.hasNext()) {
                        if (zzfrVar.zzh()) {
                            Integer numValueOf10 = Integer.valueOf(zzfrVar.zza());
                            if (zzfrVar.zzg()) {
                                lValueOf = Long.valueOf(zzfrVar.zzb());
                            } else {
                                lValueOf = null;
                            }
                            arrayMap.put(numValueOf10, lValueOf);
                        }
                    }
                }
                ArrayMap arrayMap13 = new ArrayMap();
                if (zzgiVar != null && zzgiVar.zzc() != 0) {
                    it = zzgiVar.zzj().iterator();
                    while (it.hasNext()) {
                        zzgkVar = (com.google.android.gms.internal.measurement.zzgk) it.next();
                        if (!zzgkVar.zzi() && zzgkVar.zza() > 0) {
                            arrayMap13.put(Integer.valueOf(zzgkVar.zzb()), Long.valueOf(zzgkVar.zzc(zzgkVar.zza() - 1)));
                            map3 = map3;
                            it = it;
                        }
                    }
                }
                Map map10 = map3;
                if (zzgiVar != null) {
                    i3 = 0;
                    while (i3 < zzgiVar.zzd() * 64) {
                        if (zzlj.zzw(zzgiVar.zzk(), i3)) {
                            str8 = str23;
                            this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", num2, Integer.valueOf(i3));
                            bitSet2.set(i3);
                            if (zzlj.zzw(zzgiVar.zzi(), i3)) {
                                bitSet.set(i3);
                            }
                            i3++;
                            str23 = str8;
                        } else {
                            str8 = str23;
                        }
                        arrayMap.remove(Integer.valueOf(i3));
                        i3++;
                        str23 = str8;
                    }
                }
                String str214 = str23;
                com.google.android.gms.internal.measurement.zzgi zzgiVar6 = (com.google.android.gms.internal.measurement.zzgi) map2.get(num2);
                if (!zZzs2 && zZzs && (list3 = (List) map4.get(num2)) != null && this.zze != null && this.zzd != null) {
                    for (com.google.android.gms.internal.measurement.zzek zzekVar9 : list3) {
                        int iZzb = zzekVar9.zzb();
                        long jLongValue = this.zze.longValue() / 1000;
                        if (zzekVar9.zzm()) {
                            jLongValue = this.zzd.longValue() / 1000;
                        }
                        Integer numValueOf11 = Integer.valueOf(iZzb);
                        if (arrayMap.containsKey(numValueOf11)) {
                            arrayMap.put(numValueOf11, Long.valueOf(jLongValue));
                        }
                        if (arrayMap13.containsKey(numValueOf11)) {
                            arrayMap13.put(numValueOf11, Long.valueOf(jLongValue));
                        }
                    }
                }
                this.zzc.put(num2, new zzu(this, this.zza, zzgiVar6, bitSet, bitSet2, arrayMap, arrayMap13, null));
                map2 = map2;
                str23 = str214;
                zZzs = zZzs;
                map4 = map4;
                map3 = map10;
                str3 = str3;
            }
            str5 = str23;
            str6 = str3;
            str7 = str4;
        }
        if (!list.isEmpty()) {
            zzwVar = new zzw(this, null);
            arrayMap4 = new ArrayMap();
            it3 = list.iterator();
            while (it3.hasNext()) {
                zzftVar = (com.google.android.gms.internal.measurement.zzft) it3.next();
                zzftVarZza = zzwVar.zza(this.zza, zzftVar);
                if (zzftVarZza != null) {
                    zzakVarZzh2 = this.zzf.zzh();
                    str11 = this.zza;
                    strZzh = zzftVarZza.zzh();
                    zzaqVarZzn = zzakVarZzh2.zzn(str11, zzftVar.zzh());
                    if (zzaqVarZzn == null) {
                        zzakVarZzh2.zzt.zzaA().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzet.zzn(str11), zzakVarZzh2.zzt.zzj().zzd(strZzh));
                        zzaqVar = new zzaq(str11, zzftVar.zzh(), 1L, 1L, 1L, zzftVar.zzd(), 0L, null, null, null, null);
                    } else {
                        zzaqVar = new zzaq(zzaqVarZzn.zza, zzaqVarZzn.zzb, zzaqVarZzn.zzc + 1, zzaqVarZzn.zzd + 1, zzaqVarZzn.zze + 1, zzaqVarZzn.zzf, zzaqVarZzn.zzg, zzaqVarZzn.zzh, zzaqVarZzn.zzi, zzaqVarZzn.zzj, zzaqVarZzn.zzk);
                    }
                    this.zzf.zzh().zzE(zzaqVar);
                    j = zzaqVar.zzc;
                    strZzh2 = zzftVarZza.zzh();
                    mapEmptyMap = (Map) arrayMap4.get(strZzh2);
                    if (mapEmptyMap == null) {
                        zzakVarZzh3 = this.zzf.zzh();
                        str12 = this.zza;
                        zzakVarZzh3.zzW();
                        zzakVarZzh3.zzg();
                        Preconditions.checkNotEmpty(str12);
                        Preconditions.checkNotEmpty(strZzh2);
                        arrayMap5 = new ArrayMap();
                        str13 = str6;
                        str14 = str25;
                        str25 = str14;
                        cursorQuery2 = zzakVarZzh3.zzh().query("event_filters", new String[]{str13, str14}, "app_id=? AND event_name=?", new String[]{str12, strZzh2}, null, null, null);
                        if (cursorQuery2.moveToFirst()) {
                            str6 = str13;
                            while (true) {
                                com.google.android.gms.internal.measurement.zzek zzekVar10 = (com.google.android.gms.internal.measurement.zzek) ((com.google.android.gms.internal.measurement.zzej) zzlj.zzm(com.google.android.gms.internal.measurement.zzek.zzc(), cursorQuery2.getBlob(1))).zzaD();
                                numValueOf2 = Integer.valueOf(cursorQuery2.getInt(0));
                                list5 = (List) arrayMap5.get(numValueOf2);
                                if (list5 == null) {
                                    zzaqVar2 = zzaqVar;
                                    arrayList2 = new ArrayList();
                                    arrayMap5.put(numValueOf2, arrayList2);
                                } else {
                                    zzaqVar2 = zzaqVar;
                                    arrayList2 = list5;
                                }
                                arrayList2.add(zzekVar10);
                                if (!cursorQuery2.moveToNext()) {
                                    break;
                                    break;
                                }
                                zzaqVar = zzaqVar2;
                            }
                            if (cursorQuery2 != null) {
                                cursorQuery2.close();
                            }
                            mapEmptyMap = arrayMap5;
                        } else {
                            zzaqVar2 = zzaqVar;
                            str6 = str13;
                            mapEmptyMap = Collections.emptyMap();
                            if (cursorQuery2 != null) {
                                cursorQuery2.close();
                            }
                        }
                        arrayMap4.put(strZzh2, mapEmptyMap);
                    } else {
                        zzaqVar2 = zzaqVar;
                    }
                    while (r2.hasNext()) {
                        iIntValue = num3.intValue();
                        if (this.zzb.contains(num3)) {
                            this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num3);
                        } else {
                            it4 = ((List) mapEmptyMap.get(num3)).iterator();
                            z2 = true;
                            while (true) {
                                if (!it4.hasNext()) {
                                    map6 = mapEmptyMap;
                                    break;
                                }
                                com.google.android.gms.internal.measurement.zzek zzekVar11 = (com.google.android.gms.internal.measurement.zzek) it4.next();
                                zzxVar = new zzx(this, this.zza, iIntValue, zzekVar11);
                                map6 = mapEmptyMap;
                                zZzd = zzxVar.zzd(this.zzd, this.zze, zzftVarZza, j, zzaqVar2, zzf(iIntValue, zzekVar11.zzb()));
                                if (!zZzd) {
                                    this.zzb.add(num3);
                                    z2 = zZzd;
                                    break;
                                }
                                zzd(num3).zzc(zzxVar);
                                z2 = zZzd;
                                mapEmptyMap = map6;
                            }
                            if (!z2) {
                                this.zzb.add(num3);
                            }
                            mapEmptyMap = map6;
                        }
                    }
                    zzwVar = zzwVar;
                    it3 = it3;
                    str21 = str21;
                }
            }
        }
        str15 = str21;
        if (!list2.isEmpty()) {
            arrayMap6 = new ArrayMap();
            it5 = list2.iterator();
            while (it5.hasNext()) {
                com.google.android.gms.internal.measurement.zzgm zzgmVar4 = (com.google.android.gms.internal.measurement.zzgm) it5.next();
                strZzf = zzgmVar4.zzf();
                mapEmptyMap2 = (Map) arrayMap6.get(strZzf);
                if (mapEmptyMap2 == null) {
                    zzakVarZzh4 = this.zzf.zzh();
                    str18 = this.zza;
                    zzakVarZzh4.zzW();
                    zzakVarZzh4.zzg();
                    Preconditions.checkNotEmpty(str18);
                    Preconditions.checkNotEmpty(strZzf);
                    arrayMap7 = new ArrayMap();
                    str16 = str6;
                    str17 = str25;
                    cursorQuery3 = zzakVarZzh4.zzh().query("property_filters", new String[]{str16, str17}, "app_id=? AND property_name=?", new String[]{str18, strZzf}, null, null, null);
                    if (cursorQuery3.moveToFirst()) {
                        while (true) {
                            com.google.android.gms.internal.measurement.zzet zzetVar5 = (com.google.android.gms.internal.measurement.zzet) ((com.google.android.gms.internal.measurement.zzes) zzlj.zzm(com.google.android.gms.internal.measurement.zzet.zzc(), cursorQuery3.getBlob(1))).zzaD();
                            numValueOf5 = Integer.valueOf(cursorQuery3.getInt(0));
                            list6 = (List) arrayMap7.get(numValueOf5);
                            if (list6 == null) {
                                arrayList3 = new ArrayList();
                                arrayMap7.put(numValueOf5, arrayList3);
                            } else {
                                arrayList3 = list6;
                            }
                            arrayList3.add(zzetVar5);
                            if (!cursorQuery3.moveToNext()) {
                                break;
                                break;
                            }
                            it5 = it5;
                        }
                        if (cursorQuery3 != null) {
                            cursorQuery3.close();
                        }
                        mapEmptyMap2 = arrayMap7;
                    } else {
                        it5 = it5;
                        mapEmptyMap2 = Collections.emptyMap();
                        if (cursorQuery3 != null) {
                            cursorQuery3.close();
                        }
                    }
                    arrayMap6.put(strZzf, mapEmptyMap2);
                } else {
                    it5 = it5;
                    str16 = str6;
                    str17 = str25;
                }
                while (r3.hasNext()) {
                    int iIntValue8 = num4.intValue();
                    if (this.zzb.contains(num4)) {
                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", num4);
                        break;
                        break;
                    }
                    it6 = ((List) mapEmptyMap2.get(num4)).iterator();
                    zZzd2 = true;
                    while (true) {
                        if (it6.hasNext()) {
                            zzetVar = (com.google.android.gms.internal.measurement.zzet) it6.next();
                            if (Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                                zzer zzerVarZzj4 = this.zzt.zzaA().zzj();
                                if (zzetVar.zzj()) {
                                    numValueOf4 = Integer.valueOf(zzetVar.zza());
                                } else {
                                    numValueOf4 = null;
                                }
                                zzerVarZzj4.zzd("Evaluating filter. audience, filter, property", num4, numValueOf4, this.zzt.zzj().zzf(zzetVar.zze()));
                                this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzetVar));
                            }
                            if (zzetVar.zzj() || zzetVar.zza() > 256) {
                                zzer zzerVarZzk4 = this.zzt.zzaA().zzk();
                                Object objZzn4 = zzet.zzn(this.zza);
                                if (zzetVar.zzj()) {
                                    numValueOf3 = Integer.valueOf(zzetVar.zza());
                                } else {
                                    numValueOf3 = null;
                                }
                                zzerVarZzk4.zzc("Invalid property filter ID. appId, id", objZzn4, String.valueOf(numValueOf3));
                                this.zzb.add(num4);
                                mapEmptyMap2 = mapEmptyMap2;
                            } else {
                                zzzVar = new zzz(this, this.zza, iIntValue8, zzetVar);
                                zZzd2 = zzzVar.zzd(this.zzd, this.zze, zzgmVar4, zzf(iIntValue8, zzetVar.zza()));
                                if (zZzd2) {
                                    zzd(num4).zzc(zzzVar);
                                    mapEmptyMap2 = mapEmptyMap2;
                                } else {
                                    this.zzb.add(num4);
                                }
                            }
                        } else {
                            mapEmptyMap2 = mapEmptyMap2;
                        }
                        if (!zZzd2) {
                            this.zzb.add(num4);
                        }
                        mapEmptyMap2 = mapEmptyMap2;
                    }
                }
                it5 = it5;
                str25 = str17;
                str6 = str16;
            }
        }
        str19 = str6;
        arrayList4 = new ArrayList();
        Set<Integer> setKeySet4 = this.zzc.keySet();
        setKeySet4.removeAll(this.zzb);
        while (r2.hasNext()) {
            int iIntValue9 = num5.intValue();
            zzu zzuVar4 = (zzu) this.zzc.get(num5);
            Preconditions.checkNotNull(zzuVar4);
            com.google.android.gms.internal.measurement.zzfp zzfpVarZza4 = zzuVar4.zza(iIntValue9);
            arrayList4.add(zzfpVarZza4);
            zzakVarZzh5 = this.zzf.zzh();
            str20 = this.zza;
            com.google.android.gms.internal.measurement.zzgi zzgiVarZzd4 = zzfpVarZza4.zzd();
            zzakVarZzh5.zzW();
            zzakVarZzh5.zzg();
            Preconditions.checkNotEmpty(str20);
            Preconditions.checkNotNull(zzgiVarZzd4);
            byte[] bArrZzbx4 = zzgiVarZzd4.zzbx();
            contentValues = new ContentValues();
            contentValues.put("app_id", str20);
            contentValues.put(str19, num5);
            String str34 = str15;
            contentValues.put(str34, bArrZzbx4);
            if (zzakVarZzh5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                zzakVarZzh5.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", zzet.zzn(str20));
            }
            str15 = str34;
        }
        return arrayList4;
    }

    @Override // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }
}
