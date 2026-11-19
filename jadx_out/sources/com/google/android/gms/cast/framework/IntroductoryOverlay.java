package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: classes4.dex */
public interface IntroductoryOverlay {

    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    void remove();

    void show();

    public static class zza {
        public static void zzd(Context context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("googlecast-introOverlayShown", true).apply();
        }

        public static boolean zze(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("googlecast-introOverlayShown", false);
        }
    }

    public static class Builder {
        private final Activity zzij;
        private final View zzik;
        private int zzil;
        private String zzim;
        private OnOverlayDismissedListener zzin;
        private boolean zzio;
        private float zzip;
        private String zziq;

        public Builder(Activity activity, MenuItem menuItem) {
            this.zzij = (Activity) Preconditions.checkNotNull(activity);
            this.zzik = ((MenuItem) Preconditions.checkNotNull(menuItem)).getActionView();
        }

        public Builder(Activity activity, MediaRouteButton mediaRouteButton) {
            this.zzij = (Activity) Preconditions.checkNotNull(activity);
            this.zzik = (View) Preconditions.checkNotNull(mediaRouteButton);
        }

        public Builder setOverlayColor(int i) {
            this.zzil = this.zzij.getResources().getColor(i);
            return this;
        }

        public Builder setTitleText(String str) {
            this.zzim = str;
            return this;
        }

        public Builder setTitleText(int i) {
            this.zzim = this.zzij.getResources().getString(i);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zzin = onOverlayDismissedListener;
            return this;
        }

        public Builder setSingleTime() {
            this.zzio = true;
            return this;
        }

        public Builder setFocusRadiusId(int i) {
            this.zzip = this.zzij.getResources().getDimension(i);
            return this;
        }

        public Builder setFocusRadius(float f) {
            this.zzip = f;
            return this;
        }

        public Builder setButtonText(String str) {
            this.zziq = str;
            return this;
        }

        public Builder setButtonText(int i) {
            this.zziq = this.zzij.getResources().getString(i);
            return this;
        }

        public final Activity getActivity() {
            return this.zzij;
        }

        public final View zzai() {
            return this.zzik;
        }

        public final OnOverlayDismissedListener zzaj() {
            return this.zzin;
        }

        public final int zzak() {
            return this.zzil;
        }

        public final boolean zzal() {
            return this.zzio;
        }

        public final String zzam() {
            return this.zzim;
        }

        public final String zzan() {
            return this.zziq;
        }

        public final float zzao() {
            return this.zzip;
        }

        public IntroductoryOverlay build() {
            if (PlatformVersion.isAtLeastJellyBean()) {
                return new com.google.android.gms.internal.cast.zzn(this);
            }
            return new com.google.android.gms.internal.cast.zzr(this);
        }
    }
}
