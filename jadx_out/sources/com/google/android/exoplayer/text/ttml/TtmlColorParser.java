package com.google.android.exoplayer.text.ttml;

import android.text.TextUtils;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
final class TtmlColorParser {
    static final int AQUA = 16777215;
    static final int BLACK = -16777216;
    static final int BLUE = -16776961;
    private static final Map<String, Integer> COLOR_NAME_MAP;
    static final int CYAN = -16711681;
    static final int FUCHSIA = -65281;
    static final int GRAY = -8355712;
    static final int GREEN = -16744448;
    static final int LIME = -16711936;
    static final int MAGENTA = -65281;
    static final int MAROON = -8388608;
    static final int NAVY = -16777088;
    static final int OLIVE = -8355840;
    static final int PURPLE = -8388480;
    static final int RED = -65536;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    static final int SILVER = -4144960;
    static final int TEAL = -16744320;
    static final int TRANSPARENT = 0;
    static final int WHITE = -1;
    static final int YELLOW = -256;
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");

    private static int argb(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    TtmlColorParser() {
    }

    static {
        HashMap map = new HashMap();
        COLOR_NAME_MAP = map;
        map.put("transparent", 0);
        map.put("black", -16777216);
        map.put("silver", Integer.valueOf(SILVER));
        map.put("gray", Integer.valueOf(GRAY));
        map.put("white", -1);
        map.put("maroon", Integer.valueOf(MAROON));
        map.put("red", -65536);
        map.put("purple", Integer.valueOf(PURPLE));
        map.put("fuchsia", -65281);
        map.put("magenta", -65281);
        map.put("green", Integer.valueOf(GREEN));
        map.put("lime", Integer.valueOf(LIME));
        map.put("olive", Integer.valueOf(OLIVE));
        map.put("yellow", -256);
        map.put("navy", Integer.valueOf(NAVY));
        map.put("blue", Integer.valueOf(BLUE));
        map.put("teal", Integer.valueOf(TEAL));
        map.put("aqua", 16777215);
        map.put("cyan", Integer.valueOf(CYAN));
    }

    public static int parseColor(String str) {
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String strReplace = str.replace(" ", "");
        if (strReplace.charAt(0) == '#') {
            int i = (int) Long.parseLong(strReplace.substring(1), 16);
            if (strReplace.length() == 7) {
                return (-16777216) | i;
            }
            if (strReplace.length() == 9) {
                return ((i & 255) << 24) | (i >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (strReplace.startsWith(RGBA)) {
            Matcher matcher = RGBA_PATTERN.matcher(strReplace);
            if (matcher.matches()) {
                return argb(255 - Integer.parseInt(matcher.group(4), 10), Integer.parseInt(matcher.group(1), 10), Integer.parseInt(matcher.group(2), 10), Integer.parseInt(matcher.group(3), 10));
            }
        } else if (strReplace.startsWith(RGB)) {
            Matcher matcher2 = RGB_PATTERN.matcher(strReplace);
            if (matcher2.matches()) {
                return rgb(Integer.parseInt(matcher2.group(1), 10), Integer.parseInt(matcher2.group(2), 10), Integer.parseInt(matcher2.group(3), 10));
            }
        } else {
            Integer num = COLOR_NAME_MAP.get(Util.toLowerInvariant(strReplace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private static int rgb(int i, int i2, int i3) {
        return argb(255, i, i2, i3);
    }
}
