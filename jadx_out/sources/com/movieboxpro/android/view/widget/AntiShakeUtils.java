package com.movieboxpro.android.view.widget;

import android.view.View;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class AntiShakeUtils {
    private static final long INTERNAL_TIME = 1000;

    public static boolean isInvalidClick(View target) {
        return isInvalidClick(target, 1000L);
    }

    public static boolean isInvalidClick(View target, long internalTime) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Object tag = target.getTag(R.id.last_click_time);
        if (tag == null) {
            target.setTag(R.id.last_click_time, Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        boolean z = jCurrentTimeMillis - ((Long) tag).longValue() < internalTime;
        if (!z) {
            target.setTag(R.id.last_click_time, Long.valueOf(jCurrentTimeMillis));
        }
        return z;
    }
}
