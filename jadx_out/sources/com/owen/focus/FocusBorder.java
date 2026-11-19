package com.owen.focus;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public interface FocusBorder {

    public interface OnFocusCallback {
        Options onFocus(View view, View view2);
    }

    public static abstract class Options {
    }

    void boundGlobalFocusListener(OnFocusCallback onFocusCallback);

    View getView();

    boolean isVisible();

    void onFocus(View view, Options options);

    void setVisible(boolean z);

    void setVisible(boolean z, boolean z2);

    void unBoundGlobalFocusListener();

    public static class Builder {
        public final ColorFocusBorder.Builder asColor() {
            return new ColorFocusBorder.Builder();
        }

        public final DrawableFocusBorder.Builder asDrawable() {
            return new DrawableFocusBorder.Builder();
        }
    }

    public static class OptionsFactory {
        public static Options get() {
            return get(1.0f, 1.0f);
        }

        public static Options get(float f, float f2) {
            return get(f, f2, (String) null);
        }

        public static Options get(float f, float f2, String str) {
            return AbsFocusBorder.Options.get(f, f2, str);
        }

        public static Options get(float f, float f2, float f3) {
            return get(f, f2, f3, null);
        }

        public static Options get(float f, float f2, float f3, String str) {
            return ColorFocusBorder.Options.get(f, f2, f3, str);
        }
    }
}
