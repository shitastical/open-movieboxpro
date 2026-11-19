package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.ViewCompat;
import androidx.media3.extractor.ts.TsExtractor;
import com.alibaba.fastjson.asm.Opcodes;
import com.kaopiz.kprogresshud.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import me.jahnen.libaums.core.fs.fat32.FatDirectoryEntry;

/* JADX INFO: loaded from: classes4.dex */
public class ColorTemplate {
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb(207, 248, 246), Color.rgb(Opcodes.LCMP, 212, 212), Color.rgb(TsExtractor.TS_STREAM_TYPE_DTS_HD, Opcodes.GETFIELD, Opcodes.NEW), Color.rgb(118, 174, 175), Color.rgb(42, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TsExtractor.TS_STREAM_TYPE_HDMV_DTS)};
    public static final int[] JOYFUL_COLORS = {Color.rgb(217, 80, TsExtractor.TS_STREAM_TYPE_DTS), Color.rgb(254, Opcodes.FCMPL, 7), Color.rgb(254, 247, BuildConfig.VERSION_CODE), Color.rgb(106, Opcodes.GOTO, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), Color.rgb(53, 194, 209)};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb(Opcodes.FCMPL, Opcodes.IF_ACMPEQ, 124), Color.rgb(217, Opcodes.INVOKESTATIC, Opcodes.IF_ICMPGE), Color.rgb(191, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO), Color.rgb(179, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb(Opcodes.INSTANCEOF, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, Opcodes.IFNONNULL, 0), Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};

    public static int colorWithAlpha(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | ((i2 & 255) << 24);
    }

    public static int rgb(String str) {
        int i = (int) Long.parseLong(str.replace("#", ""), 16);
        return Color.rgb((i >> 16) & 255, (i >> 8) & 255, i & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, Opcodes.PUTFIELD, FatDirectoryEntry.ENTRY_DELETED);
    }

    public static List<Integer> createColors(Resources resources, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(resources.getColor(i)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }
}
