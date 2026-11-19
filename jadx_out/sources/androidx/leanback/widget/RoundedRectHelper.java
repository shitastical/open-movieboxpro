package androidx.leanback.widget;

import android.view.View;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes.dex */
final class RoundedRectHelper {
    static boolean supportsRoundedCorner() {
        return true;
    }

    static void setClipToRoundedOutline(View view, boolean z, int i) {
        RoundedRectHelperApi21.setClipToRoundedOutline(view, z, i);
    }

    static void setClipToRoundedOutline(View view, boolean z) {
        RoundedRectHelperApi21.setClipToRoundedOutline(view, z, view.getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius));
    }

    private RoundedRectHelper() {
    }
}
