package com.uber.autodispose;

/* JADX INFO: loaded from: classes5.dex */
final class AutoDisposeUtil {
    private AutoDisposeUtil() {
        throw new InstantiationError();
    }

    static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
