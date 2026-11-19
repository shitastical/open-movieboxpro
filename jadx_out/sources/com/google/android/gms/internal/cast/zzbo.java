package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbo extends UIController {
    private final View view;
    private final int zztm;

    public zzbo(View view, int i) {
        this.view = view;
        this.zztm = i;
        view.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzed();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.view.setEnabled(false);
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzed();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.view.setEnabled(false);
    }

    private final void zzed() {
        Integer indexById;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.view.setEnabled(false);
            return;
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if ((mediaStatus.getQueueRepeatMode() != 0 || ((indexById = mediaStatus.getIndexById(mediaStatus.getCurrentItemId())) != null && indexById.intValue() < mediaStatus.getQueueItemCount() - 1)) && !remoteMediaClient.isPlayingAd()) {
            this.view.setVisibility(0);
            this.view.setEnabled(true);
        } else {
            this.view.setVisibility(this.zztm);
            this.view.setEnabled(false);
        }
    }
}
