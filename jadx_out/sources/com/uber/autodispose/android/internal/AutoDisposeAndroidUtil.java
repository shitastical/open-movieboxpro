package com.uber.autodispose.android.internal;

import android.os.Looper;
import com.uber.autodispose.android.AutoDisposeAndroidPlugins;
import io.reactivex.functions.BooleanSupplier;

/* JADX INFO: loaded from: classes5.dex */
public class AutoDisposeAndroidUtil {
    private static final BooleanSupplier MAIN_THREAD_CHECK = new BooleanSupplier() { // from class: com.uber.autodispose.android.internal.AutoDisposeAndroidUtil$$ExternalSyntheticLambda0
        @Override // io.reactivex.functions.BooleanSupplier
        public final boolean getAsBoolean() {
            return AutoDisposeAndroidUtil.lambda$static$0();
        }
    };

    static /* synthetic */ boolean lambda$static$0() throws Exception {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private AutoDisposeAndroidUtil() {
    }

    public static boolean isMainThread() {
        return AutoDisposeAndroidPlugins.onCheckMainThread(MAIN_THREAD_CHECK);
    }
}
