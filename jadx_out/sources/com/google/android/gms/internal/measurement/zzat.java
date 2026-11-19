package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zzat implements Iterable, zzap {
    private final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0187  */
    /* JADX WARN: Code duplicated, block: B:104:0x0191  */
    /* JADX WARN: Code duplicated, block: B:105:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:106:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:107:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:108:0x01de  */
    /* JADX WARN: Code duplicated, block: B:109:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:110:0x0209  */
    /* JADX WARN: Code duplicated, block: B:112:0x021a  */
    /* JADX WARN: Code duplicated, block: B:113:0x0234  */
    /* JADX WARN: Code duplicated, block: B:116:0x023e  */
    /* JADX WARN: Code duplicated, block: B:117:0x0256  */
    /* JADX WARN: Code duplicated, block: B:119:0x0286  */
    /* JADX WARN: Code duplicated, block: B:121:0x0298  */
    /* JADX WARN: Code duplicated, block: B:122:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:124:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:125:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:127:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:128:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:131:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:132:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:138:0x031d  */
    /* JADX WARN: Code duplicated, block: B:141:0x0324  */
    /* JADX WARN: Code duplicated, block: B:143:0x0328 A[LOOP:0: B:142:0x0326->B:143:0x0328, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:145:0x033c  */
    /* JADX WARN: Code duplicated, block: B:147:0x034e  */
    /* JADX WARN: Code duplicated, block: B:148:0x0362  */
    /* JADX WARN: Code duplicated, block: B:151:0x036e  */
    /* JADX WARN: Code duplicated, block: B:152:0x0379  */
    /* JADX WARN: Code duplicated, block: B:155:0x0389  */
    /* JADX WARN: Code duplicated, block: B:156:0x039c  */
    /* JADX WARN: Code duplicated, block: B:159:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:160:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:162:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:164:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:167:0x0403  */
    /* JADX WARN: Code duplicated, block: B:168:0x0413  */
    /* JADX WARN: Code duplicated, block: B:169:0x0420  */
    /* JADX WARN: Code duplicated, block: B:171:0x0432  */
    /* JADX WARN: Code duplicated, block: B:173:0x0448  */
    /* JADX WARN: Code duplicated, block: B:176:0x045c  */
    /* JADX WARN: Code duplicated, block: B:178:0x0460  */
    /* JADX WARN: Code duplicated, block: B:181:0x04b1  */
    /* JADX WARN: Code duplicated, block: B:183:0x04c3  */
    /* JADX WARN: Code duplicated, block: B:184:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:187:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:188:0x04fd  */
    /* JADX WARN: Code duplicated, block: B:189:0x0501  */
    /* JADX WARN: Code duplicated, block: B:192:0x0515  */
    /* JADX WARN: Code duplicated, block: B:195:0x052b  */
    /* JADX WARN: Code duplicated, block: B:196:0x052e  */
    /* JADX WARN: Code duplicated, block: B:199:0x0547  */
    /* JADX WARN: Code duplicated, block: B:200:0x054a  */
    /* JADX WARN: Code duplicated, block: B:202:0x055f  */
    /* JADX WARN: Code duplicated, block: B:205:0x0574  */
    /* JADX WARN: Code duplicated, block: B:208:0x058b  */
    /* JADX WARN: Code duplicated, block: B:209:0x058d  */
    /* JADX WARN: Code duplicated, block: B:211:0x05b5  */
    /* JADX WARN: Code duplicated, block: B:213:0x05da  */
    /* JADX WARN: Code duplicated, block: B:214:0x05de  */
    /* JADX WARN: Code duplicated, block: B:222:0x05ff  */
    /* JADX WARN: Code duplicated, block: B:224:0x060b  */
    /* JADX WARN: Code duplicated, block: B:227:0x0619 A[LOOP:1: B:225:0x0613->B:227:0x0619, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:230:0x063a  */
    /* JADX WARN: Code duplicated, block: B:232:0x064c  */
    /* JADX WARN: Code duplicated, block: B:233:0x0665  */
    /* JADX WARN: Code duplicated, block: B:245:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:246:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:247:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:248:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:249:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:250:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:251:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:252:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:253:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:254:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:256:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:257:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00be  */
    /* JADX WARN: Code duplicated, block: B:44:0x00c0 A[PHI: r4
      0x00c0: PHI (r4v81 java.lang.String) = 
      (r4v5 java.lang.String)
      (r4v6 java.lang.String)
      (r4v7 java.lang.String)
      (r4v9 java.lang.String)
      (r4v10 java.lang.String)
      (r4v11 java.lang.String)
      (r4v12 java.lang.String)
      (r4v13 java.lang.String)
      (r4v15 java.lang.String)
      (r4v16 java.lang.String)
      (r4v17 java.lang.String)
      (r4v18 java.lang.String)
      (r4v19 java.lang.String)
      (r4v20 java.lang.String)
      (r4v23 java.lang.String)
      (r4v82 java.lang.String)
     binds: [B:89:0x0154, B:86:0x0146, B:257:?, B:256:?, B:255:?, B:254:?, B:253:?, B:252:?, B:251:?, B:250:?, B:249:?, B:248:?, B:247:?, B:246:?, B:245:?, B:43:0x00be] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:45:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:47:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:48:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:50:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:53:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:54:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:56:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:60:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:63:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:65:0x0102  */
    /* JADX WARN: Code duplicated, block: B:66:0x0105  */
    /* JADX WARN: Code duplicated, block: B:68:0x010b  */
    /* JADX WARN: Code duplicated, block: B:69:0x010e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0114  */
    /* JADX WARN: Code duplicated, block: B:72:0x0116  */
    /* JADX WARN: Code duplicated, block: B:74:0x011c  */
    /* JADX WARN: Code duplicated, block: B:75:0x011f  */
    /* JADX WARN: Code duplicated, block: B:77:0x0125  */
    /* JADX WARN: Code duplicated, block: B:78:0x0127  */
    /* JADX WARN: Code duplicated, block: B:80:0x012d  */
    /* JADX WARN: Code duplicated, block: B:82:0x0132  */
    /* JADX WARN: Code duplicated, block: B:84:0x0138  */
    /* JADX WARN: Code duplicated, block: B:85:0x0140  */
    /* JADX WARN: Code duplicated, block: B:87:0x0148  */
    /* JADX WARN: Code duplicated, block: B:88:0x014e  */
    /* JADX WARN: Code duplicated, block: B:90:0x0156  */
    /* JADX WARN: Code duplicated, block: B:92:0x015d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0167  */
    /* JADX WARN: Code duplicated, block: B:95:0x016c  */
    /* JADX WARN: Code duplicated, block: B:96:0x016f  */
    /* JADX WARN: Code duplicated, block: B:98:0x017b  */
    /* JADX WARN: Code duplicated, block: B:99:0x017d A[PHI: r3 r4 r6
      0x017d: PHI (r3v61 java.lang.String) = (r3v2 java.lang.String), (r3v3 java.lang.String), (r3v62 java.lang.String) binds: [B:97:0x0179, B:95:0x016c, B:44:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x017d: PHI (r4v80 java.lang.String) = (r4v3 java.lang.String), (r4v4 java.lang.String), (r4v81 java.lang.String) binds: [B:97:0x0179, B:95:0x016c, B:44:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x017d: PHI (r6v36 java.lang.String) = (r6v1 java.lang.String), (r6v2 java.lang.String), (r6v37 java.lang.String) binds: [B:97:0x0179, B:95:0x016c, B:44:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String str, zzg zzgVar, List list) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        byte b;
        String strZzi;
        String str7;
        String str8;
        int iZza;
        zzat zzatVar;
        StringBuilder sb;
        int i;
        String str9;
        zzap zzapVarZzb;
        int i2;
        double dDoubleValue;
        double dDoubleValue2;
        double dZza;
        String strZzi2;
        zzap zzapVarZza;
        String str10;
        String str11;
        int iIndexOf;
        zzat zzatVar2;
        String str12;
        double dDoubleValue3;
        double dZza2;
        double dMin;
        double length;
        double dZza3;
        double dMin2;
        String str13;
        ArrayList arrayList;
        String strZzi3;
        long jZzd;
        String[] strArrSplit;
        int length2;
        int i3;
        int i4;
        String str14;
        zzg zzgVar2;
        int iZza2;
        int length3;
        if (!"charAt".equals(str) && !"concat".equals(str) && !"hasOwnProperty".equals(str) && !"indexOf".equals(str) && !"lastIndexOf".equals(str) && !"match".equals(str) && !"replace".equals(str) && !FirebaseAnalytics.Event.SEARCH.equals(str) && !"slice".equals(str) && !"split".equals(str) && !"substring".equals(str) && !"toLowerCase".equals(str) && !"toLocaleLowerCase".equals(str) && !"toString".equals(str)) {
            str3 = "toUpperCase";
            str2 = "toLocaleUpperCase";
            if (!str3.equals(str) && !str2.equals(str)) {
                if (!"trim".equals(str)) {
                    throw new IllegalArgumentException(String.format("%s is not a String function", str));
                }
            }
            switch (str.hashCode()) {
                case -1789698943:
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                    if (str.equals(str5)) {
                        b = 2;
                    } else {
                        b = -1;
                    }
                    break;
                case -1776922004:
                    str4 = "charAt";
                    str6 = "toString";
                    if (str.equals(str6)) {
                        b = Ascii.SO;
                        str5 = r4;
                    } else {
                        str5 = r4;
                        b = -1;
                    }
                    break;
                case -1464939364:
                    str4 = "charAt";
                    if (str.equals("toLocaleLowerCase")) {
                        b = Ascii.FF;
                        str5 = r4;
                        str6 = "toString";
                    }
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                    break;
                case -1361633751:
                    str4 = "charAt";
                    if (str.equals(str4)) {
                        str5 = r4;
                        str6 = "toString";
                        b = 0;
                    } else {
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -1354795244:
                    if (str.equals("concat")) {
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                        b = 1;
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -1137582698:
                    if (str.equals("toLowerCase")) {
                        b = Ascii.CR;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -906336856:
                    if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                        b = 7;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -726908483:
                    if (str.equals(str2)) {
                        b = Ascii.VT;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -467511597:
                    if (str.equals("lastIndexOf")) {
                        b = 4;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case -399551817:
                    if (str.equals(str3)) {
                        b = Ascii.SI;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 3568674:
                    if (str.equals("trim")) {
                        b = Ascii.DLE;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 103668165:
                    if (str.equals("match")) {
                        b = 5;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 109526418:
                    if (str.equals("slice")) {
                        b = 8;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 109648666:
                    if (str.equals("split")) {
                        b = 9;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 530542161:
                    if (str.equals("substring")) {
                        b = 10;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 1094496948:
                    if (str.equals("replace")) {
                        b = 6;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                case 1943291465:
                    if (str.equals("indexOf")) {
                        b = 3;
                        str4 = "charAt";
                        str5 = r4;
                        str6 = "toString";
                    } else {
                        str4 = "charAt";
                        str5 = "hasOwnProperty";
                        str6 = "toString";
                        b = -1;
                    }
                    break;
                default:
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                    break;
            }
            strZzi = "undefined";
            str7 = str5;
            str8 = str4;
            switch (b) {
                case 0:
                    zzh.zzj(str8, 1, list);
                    if (list.isEmpty()) {
                        iZza = 0;
                    } else {
                        iZza = (int) zzh.zza(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue());
                    }
                    String str15 = this.zza;
                    return (iZza >= 0 || iZza >= str15.length()) ? zzap.zzm : new zzat(String.valueOf(str15.charAt(iZza)));
                case 1:
                    zzatVar = this;
                    if (!list.isEmpty()) {
                        sb = new StringBuilder(zzatVar.zza);
                        for (i = 0; i < list.size(); i++) {
                            sb.append(zzgVar.zzb((zzap) list.get(i)).zzi());
                        }
                        return new zzat(sb.toString());
                    }
                    return zzatVar;
                case 2:
                    zzh.zzh(str7, 1, list);
                    str9 = this.zza;
                    zzapVarZzb = zzgVar.zzb((zzap) list.get(0));
                    if ("length".equals(zzapVarZzb.zzi())) {
                        return zzaf.zzk;
                    }
                    double dDoubleValue4 = zzapVarZzb.zzh().doubleValue();
                    return (dDoubleValue4 == Math.floor(dDoubleValue4) || (i2 = (int) dDoubleValue4) < 0 || i2 >= str9.length()) ? zzaf.zzl : zzaf.zzk;
                case 3:
                    zzh.zzj("indexOf", 2, list);
                    String str16 = this.zza;
                    String strZzi4 = list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined";
                    if (list.size() < 2) {
                        dDoubleValue = 0.0d;
                    } else {
                        dDoubleValue = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                    }
                    return new zzah(Double.valueOf(str16.indexOf(strZzi4, (int) zzh.zza(dDoubleValue))));
                case 4:
                    zzh.zzj("lastIndexOf", 2, list);
                    String str17 = this.zza;
                    String strZzi5 = list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined";
                    if (list.size() < 2) {
                        dDoubleValue2 = Double.NaN;
                    } else {
                        dDoubleValue2 = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                    }
                    if (Double.isNaN(dDoubleValue2)) {
                        dZza = Double.POSITIVE_INFINITY;
                    } else {
                        dZza = zzh.zza(dDoubleValue2);
                    }
                    return new zzah(Double.valueOf(str17.lastIndexOf(strZzi5, (int) dZza)));
                case 5:
                    zzh.zzj("match", 1, list);
                    String str18 = this.zza;
                    if (list.size() <= 0) {
                        strZzi2 = "";
                    } else {
                        strZzi2 = zzgVar.zzb((zzap) list.get(0)).zzi();
                    }
                    Matcher matcher = Pattern.compile(strZzi2).matcher(str18);
                    return matcher.find() ? new zzae(Arrays.asList(new zzat(matcher.group()))) : zzap.zzg;
                case 6:
                    zzatVar = this;
                    zzh.zzj("replace", 2, list);
                    zzapVarZza = zzap.zzf;
                    if (!list.isEmpty()) {
                        strZzi = zzgVar.zzb((zzap) list.get(0)).zzi();
                        if (list.size() > 1) {
                            zzapVarZza = zzgVar.zzb((zzap) list.get(1));
                        }
                    }
                    str10 = strZzi;
                    str11 = zzatVar.zza;
                    iIndexOf = str11.indexOf(str10);
                    if (iIndexOf >= 0) {
                        if (zzapVarZza instanceof zzai) {
                            zzapVarZza = ((zzai) zzapVarZza).zza(zzgVar, Arrays.asList(new zzat(str10), new zzah(Double.valueOf(iIndexOf)), zzatVar));
                        }
                        zzatVar2 = new zzat(str11.substring(0, iIndexOf) + zzapVarZza.zzi() + str11.substring(iIndexOf + str10.length()));
                        return zzatVar2;
                    }
                    return zzatVar;
                case 7:
                    zzh.zzj(FirebaseAnalytics.Event.SEARCH, 1, list);
                    Matcher matcher2 = Pattern.compile(list.isEmpty() ? "undefined" : zzgVar.zzb((zzap) list.get(0)).zzi()).matcher(this.zza);
                    return matcher2.find() ? new zzah(Double.valueOf(matcher2.start())) : new zzah(Double.valueOf(-1.0d));
                case 8:
                    zzh.zzj("slice", 2, list);
                    str12 = this.zza;
                    if (list.isEmpty()) {
                        dDoubleValue3 = 0.0d;
                    } else {
                        dDoubleValue3 = zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue();
                    }
                    dZza2 = zzh.zza(dDoubleValue3);
                    if (dZza2 < 0.0d) {
                        dMin = Math.max(((double) str12.length()) + dZza2, 0.0d);
                    } else {
                        dMin = Math.min(dZza2, str12.length());
                    }
                    if (list.size() > 1) {
                        length = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                    } else {
                        length = str12.length();
                    }
                    dZza3 = zzh.zza(length);
                    if (dZza3 < 0.0d) {
                        dMin2 = Math.max(((double) str12.length()) + dZza3, 0.0d);
                    } else {
                        dMin2 = Math.min(dZza3, str12.length());
                    }
                    int i5 = (int) dMin;
                    return new zzat(str12.substring(i5, Math.max(0, ((int) dMin2) - i5) + i5));
                case 9:
                    zzh.zzj("split", 2, list);
                    str13 = this.zza;
                    if (str13.length() == 0) {
                        return new zzae(Arrays.asList(this));
                    }
                    arrayList = new ArrayList();
                    if (list.isEmpty()) {
                        arrayList.add(this);
                    } else {
                        strZzi3 = zzgVar.zzb((zzap) list.get(0)).zzi();
                        if (list.size() > 1) {
                            jZzd = zzh.zzd(zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue());
                        } else {
                            jZzd = 2147483647L;
                        }
                        if (jZzd == 0) {
                            return new zzae();
                        }
                        strArrSplit = str13.split(Pattern.quote(strZzi3), ((int) jZzd) + 1);
                        length2 = strArrSplit.length;
                        if (strZzi3.isEmpty() || length2 <= 0) {
                            i3 = length2;
                            i4 = 0;
                        } else {
                            boolean zIsEmpty = strArrSplit[0].isEmpty();
                            i3 = length2 - 1;
                            i4 = zIsEmpty;
                            if (!strArrSplit[i3].isEmpty()) {
                                i3 = length2;
                                i4 = zIsEmpty;
                            }
                        }
                        if (length2 > jZzd) {
                            i3--;
                        }
                        while (i4 < i3) {
                            arrayList.add(new zzat(strArrSplit[i4]));
                            i4++;
                        }
                    }
                    return new zzae(arrayList);
                case 10:
                    zzh.zzj("substring", 2, list);
                    str14 = this.zza;
                    if (list.isEmpty()) {
                        zzgVar2 = zzgVar;
                        iZza2 = 0;
                    } else {
                        zzgVar2 = zzgVar;
                        iZza2 = (int) zzh.zza(zzgVar2.zzb((zzap) list.get(0)).zzh().doubleValue());
                    }
                    if (list.size() > 1) {
                        length3 = (int) zzh.zza(zzgVar2.zzb((zzap) list.get(1)).zzh().doubleValue());
                    } else {
                        length3 = str14.length();
                    }
                    int iMin = Math.min(Math.max(iZza2, 0), str14.length());
                    int iMin2 = Math.min(Math.max(length3, 0), str14.length());
                    zzatVar2 = new zzat(str14.substring(Math.min(iMin, iMin2), Math.max(iMin, iMin2)));
                    return zzatVar2;
                case 11:
                    zzh.zzh(str2, 0, list);
                    return new zzat(this.zza.toUpperCase());
                case 12:
                    zzh.zzh("toLocaleLowerCase", 0, list);
                    return new zzat(this.zza.toLowerCase());
                case 13:
                    zzh.zzh("toLowerCase", 0, list);
                    return new zzat(this.zza.toLowerCase(Locale.ENGLISH));
                case 14:
                    zzatVar = this;
                    zzh.zzh(str6, 0, list);
                    return zzatVar;
                case 15:
                    zzh.zzh(str3, 0, list);
                    return new zzat(this.zza.toUpperCase(Locale.ENGLISH));
                case 16:
                    zzh.zzh(str3, 0, list);
                    return new zzat(this.zza.trim());
                default:
                    throw new IllegalArgumentException("Command not supported");
            }
        }
        str2 = "toLocaleUpperCase";
        str3 = "toUpperCase";
        switch (str.hashCode()) {
            case -1789698943:
                str4 = "charAt";
                str5 = r4;
                str6 = "toString";
                if (str.equals(str5)) {
                    b = 2;
                } else {
                    b = -1;
                }
                break;
            case -1776922004:
                str4 = "charAt";
                str6 = "toString";
                if (str.equals(str6)) {
                    b = Ascii.SO;
                    str5 = r4;
                } else {
                    str5 = r4;
                    b = -1;
                }
                break;
            case -1464939364:
                str4 = "charAt";
                if (str.equals("toLocaleLowerCase")) {
                    b = Ascii.FF;
                    str5 = r4;
                    str6 = "toString";
                }
                str5 = "hasOwnProperty";
                str6 = "toString";
                b = -1;
                break;
            case -1361633751:
                str4 = "charAt";
                if (str.equals(str4)) {
                    str5 = r4;
                    str6 = "toString";
                    b = 0;
                } else {
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -1354795244:
                if (str.equals("concat")) {
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                    b = 1;
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -1137582698:
                if (str.equals("toLowerCase")) {
                    b = Ascii.CR;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -906336856:
                if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                    b = 7;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -726908483:
                if (str.equals(str2)) {
                    b = Ascii.VT;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -467511597:
                if (str.equals("lastIndexOf")) {
                    b = 4;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case -399551817:
                if (str.equals(str3)) {
                    b = Ascii.SI;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 3568674:
                if (str.equals("trim")) {
                    b = Ascii.DLE;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 103668165:
                if (str.equals("match")) {
                    b = 5;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 109526418:
                if (str.equals("slice")) {
                    b = 8;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 109648666:
                if (str.equals("split")) {
                    b = 9;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 530542161:
                if (str.equals("substring")) {
                    b = 10;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 1094496948:
                if (str.equals("replace")) {
                    b = 6;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            case 1943291465:
                if (str.equals("indexOf")) {
                    b = 3;
                    str4 = "charAt";
                    str5 = r4;
                    str6 = "toString";
                } else {
                    str4 = "charAt";
                    str5 = "hasOwnProperty";
                    str6 = "toString";
                    b = -1;
                }
                break;
            default:
                str4 = "charAt";
                str5 = "hasOwnProperty";
                str6 = "toString";
                b = -1;
                break;
        }
        strZzi = "undefined";
        str7 = str5;
        str8 = str4;
        switch (b) {
            case 0:
                zzh.zzj(str8, 1, list);
                if (list.isEmpty()) {
                    iZza = (int) zzh.zza(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue());
                } else {
                    iZza = 0;
                }
                String str19 = this.zza;
                if (iZza >= 0) {
                }
                break;
            case 1:
                zzatVar = this;
                if (!list.isEmpty()) {
                    sb = new StringBuilder(zzatVar.zza);
                    while (i < list.size()) {
                        sb.append(zzgVar.zzb((zzap) list.get(i)).zzi());
                    }
                    return new zzat(sb.toString());
                }
                return zzatVar;
            case 2:
                zzh.zzh(str7, 1, list);
                str9 = this.zza;
                zzapVarZzb = zzgVar.zzb((zzap) list.get(0));
                if ("length".equals(zzapVarZzb.zzi())) {
                    return zzaf.zzk;
                }
                double dDoubleValue5 = zzapVarZzb.zzh().doubleValue();
                if (dDoubleValue5 == Math.floor(dDoubleValue5)) {
                }
                break;
            case 3:
                zzh.zzj("indexOf", 2, list);
                String str110 = this.zza;
                String strZzi6 = list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined";
                if (list.size() < 2) {
                    dDoubleValue = 0.0d;
                } else {
                    dDoubleValue = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                }
                return new zzah(Double.valueOf(str110.indexOf(strZzi6, (int) zzh.zza(dDoubleValue))));
            case 4:
                zzh.zzj("lastIndexOf", 2, list);
                String str111 = this.zza;
                String strZzi7 = list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined";
                if (list.size() < 2) {
                    dDoubleValue2 = Double.NaN;
                } else {
                    dDoubleValue2 = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                }
                if (Double.isNaN(dDoubleValue2)) {
                    dZza = Double.POSITIVE_INFINITY;
                } else {
                    dZza = zzh.zza(dDoubleValue2);
                }
                return new zzah(Double.valueOf(str111.lastIndexOf(strZzi7, (int) dZza)));
            case 5:
                zzh.zzj("match", 1, list);
                String str112 = this.zza;
                if (list.size() <= 0) {
                    strZzi2 = "";
                } else {
                    strZzi2 = zzgVar.zzb((zzap) list.get(0)).zzi();
                }
                Matcher matcher3 = Pattern.compile(strZzi2).matcher(str112);
                if (matcher3.find()) {
                }
            case 6:
                zzatVar = this;
                zzh.zzj("replace", 2, list);
                zzapVarZza = zzap.zzf;
                if (!list.isEmpty()) {
                    strZzi = zzgVar.zzb((zzap) list.get(0)).zzi();
                    if (list.size() > 1) {
                        zzapVarZza = zzgVar.zzb((zzap) list.get(1));
                    }
                }
                str10 = strZzi;
                str11 = zzatVar.zza;
                iIndexOf = str11.indexOf(str10);
                if (iIndexOf >= 0) {
                    if (zzapVarZza instanceof zzai) {
                        zzapVarZza = ((zzai) zzapVarZza).zza(zzgVar, Arrays.asList(new zzat(str10), new zzah(Double.valueOf(iIndexOf)), zzatVar));
                    }
                    zzatVar2 = new zzat(str11.substring(0, iIndexOf) + zzapVarZza.zzi() + str11.substring(iIndexOf + str10.length()));
                    return zzatVar2;
                }
                return zzatVar;
            case 7:
                zzh.zzj(FirebaseAnalytics.Event.SEARCH, 1, list);
                Matcher matcher4 = Pattern.compile(list.isEmpty() ? "undefined" : zzgVar.zzb((zzap) list.get(0)).zzi()).matcher(this.zza);
                if (matcher4.find()) {
                }
            case 8:
                zzh.zzj("slice", 2, list);
                str12 = this.zza;
                if (list.isEmpty()) {
                    dDoubleValue3 = zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue();
                } else {
                    dDoubleValue3 = 0.0d;
                }
                dZza2 = zzh.zza(dDoubleValue3);
                if (dZza2 < 0.0d) {
                    dMin = Math.max(((double) str12.length()) + dZza2, 0.0d);
                } else {
                    dMin = Math.min(dZza2, str12.length());
                }
                if (list.size() > 1) {
                    length = zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue();
                } else {
                    length = str12.length();
                }
                dZza3 = zzh.zza(length);
                if (dZza3 < 0.0d) {
                    dMin2 = Math.max(((double) str12.length()) + dZza3, 0.0d);
                } else {
                    dMin2 = Math.min(dZza3, str12.length());
                }
                int i6 = (int) dMin;
                return new zzat(str12.substring(i6, Math.max(0, ((int) dMin2) - i6) + i6));
            case 9:
                zzh.zzj("split", 2, list);
                str13 = this.zza;
                if (str13.length() == 0) {
                    return new zzae(Arrays.asList(this));
                }
                arrayList = new ArrayList();
                if (list.isEmpty()) {
                    arrayList.add(this);
                } else {
                    strZzi3 = zzgVar.zzb((zzap) list.get(0)).zzi();
                    if (list.size() > 1) {
                        jZzd = zzh.zzd(zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue());
                    } else {
                        jZzd = 2147483647L;
                    }
                    if (jZzd == 0) {
                        return new zzae();
                    }
                    strArrSplit = str13.split(Pattern.quote(strZzi3), ((int) jZzd) + 1);
                    length2 = strArrSplit.length;
                    if (strZzi3.isEmpty()) {
                        i3 = length2;
                        i4 = 0;
                    } else {
                        i3 = length2;
                        i4 = 0;
                    }
                    if (length2 > jZzd) {
                        i3--;
                    }
                    while (i4 < i3) {
                        arrayList.add(new zzat(strArrSplit[i4]));
                        i4++;
                    }
                }
                return new zzae(arrayList);
            case 10:
                zzh.zzj("substring", 2, list);
                str14 = this.zza;
                if (list.isEmpty()) {
                    zzgVar2 = zzgVar;
                    iZza2 = (int) zzh.zza(zzgVar2.zzb((zzap) list.get(0)).zzh().doubleValue());
                } else {
                    zzgVar2 = zzgVar;
                    iZza2 = 0;
                }
                if (list.size() > 1) {
                    length3 = (int) zzh.zza(zzgVar2.zzb((zzap) list.get(1)).zzh().doubleValue());
                } else {
                    length3 = str14.length();
                }
                int iMin3 = Math.min(Math.max(iZza2, 0), str14.length());
                int iMin4 = Math.min(Math.max(length3, 0), str14.length());
                zzatVar2 = new zzat(str14.substring(Math.min(iMin3, iMin4), Math.max(iMin3, iMin4)));
                return zzatVar2;
            case 11:
                zzh.zzh(str2, 0, list);
                return new zzat(this.zza.toUpperCase());
            case 12:
                zzh.zzh("toLocaleLowerCase", 0, list);
                return new zzat(this.zza.toLowerCase());
            case 13:
                zzh.zzh("toLowerCase", 0, list);
                return new zzat(this.zza.toLowerCase(Locale.ENGLISH));
            case 14:
                zzatVar = this;
                zzh.zzh(str6, 0, list);
                return zzatVar;
            case 15:
                zzh.zzh(str3, 0, list);
                return new zzat(this.zza.toUpperCase(Locale.ENGLISH));
            case 16:
                zzh.zzh(str3, 0, list);
                return new zzat(this.zza.trim());
            default:
                throw new IllegalArgumentException("Command not supported");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzar(this);
    }
}
