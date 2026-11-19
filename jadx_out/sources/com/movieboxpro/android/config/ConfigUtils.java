package com.movieboxpro.android.config;

import android.text.TextUtils;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.MLog;

/* JADX INFO: loaded from: classes5.dex */
public class ConfigUtils {
    private static final String TAG = "ConfigUtils";

    public static boolean containsConfig(String configName) {
        String str = String.format("%s_%s_%s", configName, App.channel, App.versionName);
        boolean zContainsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), str);
        MLog.d(TAG, str + ": " + zContainsKey);
        if (!zContainsKey) {
            str = String.format("%s_%s", configName, App.channel);
            zContainsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), str);
        }
        MLog.d(TAG, str + ": " + zContainsKey);
        if (!zContainsKey) {
            str = String.format("%s_%s", configName, App.versionName);
            zContainsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), str);
        }
        MLog.d(TAG, str + ": " + zContainsKey);
        if (!zContainsKey) {
            str = String.format("%s_%s", App.versionName, configName);
            zContainsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), str);
        }
        MLog.d(TAG, str + ": " + zContainsKey);
        if (zContainsKey) {
            configName = str;
        } else {
            zContainsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), configName);
        }
        MLog.d(TAG, configName + ": " + zContainsKey);
        return zContainsKey;
    }

    public static String readStringConfig(String configName) {
        String str = String.format("%s_%s_%s", configName, App.channel, App.versionName);
        String configParam = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), str);
        if (TextUtils.isEmpty(configParam)) {
            str = String.format("%s_%s", configName, App.channel);
            configParam = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), str);
            MLog.d(TAG, "readOnline: " + str + ": " + configParam);
        }
        if (TextUtils.isEmpty(configParam)) {
            str = String.format("%s_%s", configName, App.versionName);
            configParam = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), str);
            MLog.d(TAG, "readOnline: " + str + ": " + configParam);
        }
        if (TextUtils.isEmpty(str)) {
            String str2 = String.format("%s_%s", App.versionName, configName);
            configParam = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), str2);
            MLog.d(TAG, "readOnline: " + str2 + ": " + configParam);
        }
        if (!TextUtils.isEmpty(configParam)) {
            return configParam;
        }
        String configParam2 = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), configName);
        MLog.d(TAG, "readOnline: " + configName + ": " + configParam2);
        return configParam2;
    }

    public static int readIntBoolConfig(String configName) {
        return readIntBoolConfig(configName, 0);
    }

    public static int readIntBoolConfig(String configName, int defaultVal) {
        String stringConfig = readStringConfig(configName);
        if (stringConfig == null || !stringConfig.matches("[01]")) {
            return defaultVal > 0 ? 1 : 0;
        }
        return Integer.parseInt(stringConfig);
    }

    public static boolean readBooleanConfig(String configName) {
        return readBooleanConfig(configName, false);
    }

    public static boolean readBooleanConfig(String configName, boolean defaultVal) {
        String stringConfig = readStringConfig(configName);
        if (stringConfig == null || !stringConfig.matches("[01]")) {
            return defaultVal;
        }
        return Integer.parseInt(stringConfig) == 1;
    }

    public static int readIntegerConfig(String configName) {
        return readIntegerConfig(configName, -1);
    }

    public static int readIntegerConfig(String configName, int defaultVal) {
        String stringConfig = readStringConfig(configName);
        return (stringConfig == null || !stringConfig.matches("\\d+")) ? defaultVal : Integer.parseInt(stringConfig);
    }

    public static void writeIntgerConfig(String configName, int value) {
        OnlineConfigAgent.getInstance().setConfigParam(App.getContext(), configName, value + "");
    }
}
