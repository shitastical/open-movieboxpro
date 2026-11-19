package com.movieboxpro.android.utils;

import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Dpad.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/utils/Dpad;", "", "<init>", "()V", "directionPressed", "", "getDirectionPressed", NotificationCompat.CATEGORY_EVENT, "Landroid/view/InputEvent;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Dpad {
    public static final int CENTER = 4;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DOWN = 3;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 0;
    private int directionPressed = -1;

    public final int getDirectionPressed(InputEvent event) {
        int i;
        Intrinsics.checkNotNullParameter(event, "event");
        int i2 = 3;
        if ((event instanceof MotionEvent ? (MotionEvent) event : null) != null) {
            MotionEvent motionEvent = (MotionEvent) event;
            float axisValue = motionEvent.getAxisValue(15);
            float axisValue2 = motionEvent.getAxisValue(16);
            if (Float.compare(axisValue, -1.0f) == 0) {
                i = 1;
            } else if (Float.compare(axisValue, 1.0f) == 0) {
                i = 2;
            } else if (Float.compare(axisValue2, -1.0f) == 0) {
                i = 0;
            } else {
                i = Float.compare(axisValue2, 1.0f) == 0 ? 3 : this.directionPressed;
            }
            this.directionPressed = i;
        }
        if ((event instanceof KeyEvent ? (KeyEvent) event : null) != null) {
            switch (((KeyEvent) event).getKeyCode()) {
                case 19:
                    i2 = 0;
                    break;
                case 20:
                    break;
                case 21:
                    i2 = 1;
                    break;
                case 22:
                    i2 = 2;
                    break;
                case 23:
                    i2 = 4;
                    break;
                default:
                    i2 = this.directionPressed;
                    break;
            }
            this.directionPressed = i2;
        }
        return this.directionPressed;
    }

    /* JADX INFO: compiled from: Dpad.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/utils/Dpad$Companion;", "", "<init>", "()V", "UP", "", "LEFT", "RIGHT", "DOWN", "CENTER", "isDpadDevice", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/InputEvent;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isDpadDevice(InputEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            return (event.getSource() & 513) != 513;
        }
    }
}
