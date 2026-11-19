package com.dl7.player.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.alibaba.fastjson.asm.Opcodes;
import org.videolan.libvlc.MediaPlayer;

/* JADX INFO: loaded from: classes4.dex */
public final class WindowUtils {
    private WindowUtils() {
        throw new Error("Do not need instantiate!");
    }

    public static int getDisplayRotation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return Opcodes.GETFIELD;
        }
        if (rotation != 3) {
            return 0;
        }
        return MediaPlayer.Event.PausableChanged;
    }

    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static void dimBackground(float f, float f2, Activity activity) {
        final Window window = activity.getWindow();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        valueAnimatorOfFloat.setDuration(500L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dl7.player.utils.WindowUtils.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                window.setAttributes(attributes);
            }
        });
        valueAnimatorOfFloat.start();
    }

    public static int getScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (((rotation == 0 || rotation == 2) && i2 > i) || ((rotation == 1 || rotation == 3) && i > i2)) {
            if (rotation != 0) {
                if (rotation != 1) {
                    if (rotation != 2) {
                        if (rotation == 3) {
                            return 8;
                        }
                    }
                    return 9;
                }
                return 0;
            }
            return 1;
        }
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation == 2) {
                    return 8;
                }
                if (rotation == 3) {
                    return 9;
                }
            }
            return 1;
        }
        return 0;
    }
}
