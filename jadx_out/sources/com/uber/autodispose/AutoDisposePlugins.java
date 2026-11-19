package com.uber.autodispose;

import io.reactivex.functions.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public final class AutoDisposePlugins {
    private static volatile boolean fillInOutsideScopeExceptionStacktraces;
    static volatile boolean lockdown;
    private static volatile Consumer<? super OutsideScopeException> outsideScopeHandler;

    private AutoDisposePlugins() {
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static boolean getFillInOutsideScopeExceptionStacktraces() {
        return fillInOutsideScopeExceptionStacktraces;
    }

    public static Consumer<? super OutsideScopeException> getOutsideScopeHandler() {
        return outsideScopeHandler;
    }

    public static void setOutsideScopeHandler(Consumer<? super OutsideScopeException> consumer) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        outsideScopeHandler = consumer;
    }

    public static void setFillInOutsideScopeExceptionStacktraces(boolean z) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        fillInOutsideScopeExceptionStacktraces = z;
    }

    public static void reset() {
        setOutsideScopeHandler(null);
    }
}
