package com.uber.autodispose.android;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;

/* JADX INFO: loaded from: classes5.dex */
public final class AutoDisposeAndroidPlugins {
    static volatile boolean lockdown;
    private static volatile BooleanSupplier onCheckMainThread;

    private AutoDisposeAndroidPlugins() {
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static void setOnCheckMainThread(BooleanSupplier booleanSupplier) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        onCheckMainThread = booleanSupplier;
    }

    public static boolean onCheckMainThread(BooleanSupplier booleanSupplier) {
        if (booleanSupplier == null) {
            throw new NullPointerException("defaultChecker == null");
        }
        BooleanSupplier booleanSupplier2 = onCheckMainThread;
        try {
            if (booleanSupplier2 == null) {
                return booleanSupplier.getAsBoolean();
            }
            return booleanSupplier2.getAsBoolean();
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    public static void reset() {
        setOnCheckMainThread(null);
    }
}
