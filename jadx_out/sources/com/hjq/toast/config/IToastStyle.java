package com.hjq.toast.config;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public interface IToastStyle<V extends View> {

    /* JADX INFO: renamed from: com.hjq.toast.config.IToastStyle$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static int $default$getGravity(IToastStyle _this) {
            return 17;
        }

        public static float $default$getHorizontalMargin(IToastStyle _this) {
            return 0.0f;
        }

        public static float $default$getVerticalMargin(IToastStyle _this) {
            return 0.0f;
        }

        public static int $default$getXOffset(IToastStyle _this) {
            return 0;
        }

        public static int $default$getYOffset(IToastStyle _this) {
            return 0;
        }
    }

    V createView(Context context);

    int getGravity();

    float getHorizontalMargin();

    float getVerticalMargin();

    int getXOffset();

    int getYOffset();
}
