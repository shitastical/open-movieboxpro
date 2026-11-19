package com.google.android.gms.cast.framework;

/* JADX INFO: loaded from: classes4.dex */
public class MediaNotificationManager {
    private final SessionManager zzhg;

    public MediaNotificationManager(SessionManager sessionManager) {
        this.zzhg = sessionManager;
    }

    public void updateNotification() {
        CastSession currentCastSession = this.zzhg.getCurrentCastSession();
        if (currentCastSession != null) {
            currentCastSession.zzy().zzg(true);
        }
    }
}
