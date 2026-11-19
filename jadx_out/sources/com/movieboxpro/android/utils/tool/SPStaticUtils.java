package com.movieboxpro.android.utils.tool;

import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class SPStaticUtils {
    private static SPUtils sDefaultSPUtils;

    public static void setDefaultSPUtils(final SPUtils spUtils) {
        sDefaultSPUtils = spUtils;
    }

    public static void put(final String key, final String value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final String value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static String getString(final String key) {
        return getString(key, getDefaultSPUtils());
    }

    public static String getString(final String key, final String defaultValue) {
        return getString(key, defaultValue, getDefaultSPUtils());
    }

    public static void put(final String key, final int value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final int value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static int getInt(final String key) {
        return getInt(key, getDefaultSPUtils());
    }

    public static int getInt(final String key, final int defaultValue) {
        return getInt(key, defaultValue, getDefaultSPUtils());
    }

    public static void put(final String key, final long value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final long value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static long getLong(final String key) {
        return getLong(key, getDefaultSPUtils());
    }

    public static long getLong(final String key, final long defaultValue) {
        return getLong(key, defaultValue, getDefaultSPUtils());
    }

    public static void put(final String key, final float value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final float value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static float getFloat(final String key) {
        return getFloat(key, getDefaultSPUtils());
    }

    public static float getFloat(final String key, final float defaultValue) {
        return getFloat(key, defaultValue, getDefaultSPUtils());
    }

    public static void put(final String key, final boolean value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final boolean value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static boolean getBoolean(final String key) {
        return getBoolean(key, getDefaultSPUtils());
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        return getBoolean(key, defaultValue, getDefaultSPUtils());
    }

    public static void put(final String key, final Set<String> value) {
        put(key, value, getDefaultSPUtils());
    }

    public static void put(final String key, final Set<String> value, final boolean isCommit) {
        put(key, value, isCommit, getDefaultSPUtils());
    }

    public static Set<String> getStringSet(final String key) {
        return getStringSet(key, getDefaultSPUtils());
    }

    public static Set<String> getStringSet(final String key, final Set<String> defaultValue) {
        return getStringSet(key, defaultValue, getDefaultSPUtils());
    }

    public static Map<String, ?> getAll() {
        return getAll(getDefaultSPUtils());
    }

    public static boolean contains(final String key) {
        return contains(key, getDefaultSPUtils());
    }

    public static void remove(final String key) {
        remove(key, getDefaultSPUtils());
    }

    public static void remove(final String key, final boolean isCommit) {
        remove(key, isCommit, getDefaultSPUtils());
    }

    public static void clear() {
        clear(getDefaultSPUtils());
    }

    public static void clear(final boolean isCommit) {
        clear(isCommit, getDefaultSPUtils());
    }

    public static void put(final String key, final String value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final String value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static String getString(final String key, final SPUtils spUtils) {
        return spUtils.getString(key);
    }

    public static String getString(final String key, final String defaultValue, final SPUtils spUtils) {
        return spUtils.getString(key, defaultValue);
    }

    public static void put(final String key, final int value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final int value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static int getInt(final String key, final SPUtils spUtils) {
        return spUtils.getInt(key);
    }

    public static int getInt(final String key, final int defaultValue, final SPUtils spUtils) {
        return spUtils.getInt(key, defaultValue);
    }

    public static void put(final String key, final long value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final long value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static long getLong(final String key, final SPUtils spUtils) {
        return spUtils.getLong(key);
    }

    public static long getLong(final String key, final long defaultValue, final SPUtils spUtils) {
        return spUtils.getLong(key, defaultValue);
    }

    public static void put(final String key, final float value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final float value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static float getFloat(final String key, final SPUtils spUtils) {
        return spUtils.getFloat(key);
    }

    public static float getFloat(final String key, final float defaultValue, final SPUtils spUtils) {
        return spUtils.getFloat(key, defaultValue);
    }

    public static void put(final String key, final boolean value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final boolean value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static boolean getBoolean(final String key, final SPUtils spUtils) {
        return spUtils.getBoolean(key);
    }

    public static boolean getBoolean(final String key, final boolean defaultValue, final SPUtils spUtils) {
        return spUtils.getBoolean(key, defaultValue);
    }

    public static void put(final String key, final Set<String> value, final SPUtils spUtils) {
        spUtils.put(key, value);
    }

    public static void put(final String key, final Set<String> value, final boolean isCommit, final SPUtils spUtils) {
        spUtils.put(key, value, isCommit);
    }

    public static Set<String> getStringSet(final String key, final SPUtils spUtils) {
        return spUtils.getStringSet(key);
    }

    public static Set<String> getStringSet(final String key, final Set<String> defaultValue, final SPUtils spUtils) {
        return spUtils.getStringSet(key, defaultValue);
    }

    public static Map<String, ?> getAll(final SPUtils spUtils) {
        return spUtils.getAll();
    }

    public static boolean contains(final String key, final SPUtils spUtils) {
        return spUtils.contains(key);
    }

    public static void remove(final String key, final SPUtils spUtils) {
        spUtils.remove(key);
    }

    public static void remove(final String key, final boolean isCommit, final SPUtils spUtils) {
        spUtils.remove(key, isCommit);
    }

    public static void clear(final SPUtils spUtils) {
        spUtils.clear();
    }

    public static void clear(final boolean isCommit, final SPUtils spUtils) {
        spUtils.clear(isCommit);
    }

    private static SPUtils getDefaultSPUtils() {
        SPUtils sPUtils = sDefaultSPUtils;
        return sPUtils != null ? sPUtils : SPUtils.getInstance();
    }
}
