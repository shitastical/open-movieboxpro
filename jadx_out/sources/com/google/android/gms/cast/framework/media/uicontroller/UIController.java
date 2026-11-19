package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: loaded from: classes4.dex */
public class UIController {
    private RemoteMediaClient zzid;

    public void onMediaStatusUpdated() {
    }

    public void onSendingRemoteMediaRequest() {
    }

    protected RemoteMediaClient getRemoteMediaClient() {
        return this.zzid;
    }

    public void onSessionConnected(CastSession castSession) {
        if (castSession != null) {
            this.zzid = castSession.getRemoteMediaClient();
        } else {
            this.zzid = null;
        }
    }

    public void onSessionEnded() {
        this.zzid = null;
    }
}
