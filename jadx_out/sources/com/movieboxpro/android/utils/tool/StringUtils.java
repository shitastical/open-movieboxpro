package com.movieboxpro.android.utils.tool;

import android.content.res.Resources;
import java.util.IllegalFormatException;

/* JADX INFO: loaded from: classes5.dex */
public final class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isTrimEmpty(final String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(final CharSequence s1, final CharSequence s2) {
        int length;
        if (s1 == s2) {
            return true;
        }
        if (s1 == null || s2 == null || (length = s1.length()) != s2.length()) {
            return false;
        }
        if ((s1 instanceof String) && (s2 instanceof String)) {
            return s1.equals(s2);
        }
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCase(final String s1, final String s2) {
        if (s1 == null) {
            return s2 == null;
        }
        return s1.equalsIgnoreCase(s2);
    }

    public static String null2Length0(final String s) {
        return s == null ? "" : s;
    }

    public static int length(final CharSequence s) {
        if (s == null) {
            return 0;
        }
        return s.length();
    }

    public static String upperFirstLetter(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (!Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return ((char) (s.charAt(0) - ' ')) + s.substring(1);
    }

    public static String lowerFirstLetter(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (!Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + ' ')) + s.substring(1);
    }

    public static String reverse(final String s) {
        if (s == null) {
            return "";
        }
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        int i = length >> 1;
        char[] charArray = s.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            char c = charArray[i2];
            int i3 = (length - i2) - 1;
            charArray[i2] = charArray[i3];
            charArray[i3] = c;
        }
        return new String(charArray);
    }

    public static String toDBC(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == 12288) {
                charArray[i] = ' ';
            } else if (65281 <= c && c <= 65374) {
                charArray[i] = (char) (c - 65248);
            } else {
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }

    public static String toSBC(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == ' ') {
                charArray[i] = 12288;
            } else if ('!' <= c && c <= '~') {
                charArray[i] = (char) (c + 65248);
            } else {
                charArray[i] = c;
            }
        }
        return new String(charArray);
    }

    public static String getString(int id) {
        return getString(id, null);
    }

    public static String getString(int id, Object... formatArgs) {
        try {
            return format(Utils.getApp().getString(id), formatArgs);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return String.valueOf(id);
        }
    }

    public static String[] getStringArray(int id) {
        try {
            return Utils.getApp().getResources().getStringArray(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return new String[]{String.valueOf(id)};
        }
    }

    public static String format(String str, Object... args) {
        if (str == null || args == null || args.length <= 0) {
            return str;
        }
        try {
            return String.format(str, args);
        } catch (IllegalFormatException e) {
            e.printStackTrace();
            return str;
        }
    }
}
