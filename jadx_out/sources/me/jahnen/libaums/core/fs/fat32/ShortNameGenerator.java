package me.jahnen.libaums.core.fs.fat32;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import ly.count.android.sdk.internal.RemoteConfigValueStore;

/* JADX INFO: compiled from: ShortNameGenerator.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\u0017"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/ShortNameGenerator;", "", "()V", "containShortName", "", "shortNames", "", "Lme/jahnen/libaums/core/fs/fat32/ShortName;", "shortName", "containsInvalidChars", "str", "", "generateShortName", "lfnName", "existingShortNames", "getNextHexPart", "hexPart", "limit", "", "isValidChar", RemoteConfigValueStore.keyCacheFlag, "", "replaceInvalidChars", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ShortNameGenerator {
    public static final ShortNameGenerator INSTANCE = new ShortNameGenerator();

    private final boolean isValidChar(char c) {
        if ('0' > c || c >= ':') {
            return ('A' <= c && c < '[') || c == '$' || c == '%' || c == '\'' || c == '-' || c == '_' || c == '@' || c == '~' || c == '`' || c == '!' || c == '(' || c == ')' || c == '{' || c == '}' || c == '^' || c == '#' || c == '&';
        }
        return true;
    }

    private ShortNameGenerator() {
    }

    private final boolean containsInvalidChars(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isValidChar(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private final String replaceInvalidChars(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (isValidChar(cCharAt)) {
                sb.append(cCharAt);
            } else {
                sb.append("_");
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }

    private final String getNextHexPart(String hexPart, int limit) {
        String hexString = Long.toHexString(Long.parseLong(hexPart, 16) + 1);
        if (hexString.length() > limit) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = limit - hexString.length();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        return sb.toString() + hexString;
    }

    public final ShortName generateShortName(String lfnName, Collection<ShortName> existingShortNames) {
        String strSubstring;
        Intrinsics.checkNotNullParameter(lfnName, "lfnName");
        Intrinsics.checkNotNullParameter(existingShortNames, "existingShortNames");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String upperCase = lfnName.toUpperCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        String str = upperCase;
        int length = str.length() - 1;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i2, length + 1).toString();
        int i3 = 0;
        while (i3 < string.length() && string.charAt(i3) == '.') {
            i3++;
        }
        String strSubstring2 = string.substring(i3);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
        String strReplace$default = StringsKt.replace$default(strSubstring2, " ", "", false, 4, (Object) null);
        int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) strReplace$default, ".", 0, false, 6, (Object) null);
        if (iLastIndexOf$default == -1) {
            strSubstring = "";
        } else {
            String strSubstring3 = strReplace$default.substring(0, iLastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            String strSubstring4 = strReplace$default.substring(iLastIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String).substring(startIndex)");
            if (strSubstring4.length() > 3) {
                strSubstring = strSubstring4.substring(0, 3);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            } else {
                strSubstring = strSubstring4;
            }
            strReplace$default = strSubstring3;
        }
        if (containsInvalidChars(strReplace$default)) {
            strReplace$default = replaceInvalidChars(strReplace$default);
        }
        if (containsInvalidChars(strSubstring)) {
            strSubstring = replaceInvalidChars(strSubstring);
        }
        if (strReplace$default.length() == 0) {
            strReplace$default = "__";
        } else if (strReplace$default.length() == 1) {
            strReplace$default = strReplace$default + '_';
        } else if (strReplace$default.length() != 2 && strReplace$default.length() > 2) {
            strReplace$default = strReplace$default.substring(0, 2);
            Intrinsics.checkNotNullExpressionValue(strReplace$default, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        if (strSubstring.length() == 0) {
            strSubstring = "000";
        } else if (strSubstring.length() == 1) {
            strSubstring = strSubstring + "00";
        } else if (strSubstring.length() == 2) {
            strSubstring = strSubstring + '0';
        }
        ShortName shortName = new ShortName(strReplace$default + "0000~0", strSubstring);
        String nextHexPart = "0000";
        while (containShortName(existingShortNames, shortName)) {
            nextHexPart = getNextHexPart(nextHexPart, 4);
            if (nextHexPart == null) {
                i++;
                if (i >= 10) {
                    break;
                }
                nextHexPart = "0000";
            }
            shortName = new ShortName(strReplace$default + nextHexPart + '~' + i, strSubstring);
        }
        return shortName;
    }

    private final boolean containShortName(Collection<ShortName> shortNames, ShortName shortName) {
        Iterator<ShortName> it = shortNames.iterator();
        while (it.hasNext()) {
            if (StringsKt.equals(it.next().getString(), shortName.getString(), true)) {
                return true;
            }
        }
        return false;
    }
}
