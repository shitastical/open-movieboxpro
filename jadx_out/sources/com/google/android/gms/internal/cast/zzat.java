package com.google.android.gms.internal.cast;

import android.content.Context;
import android.view.View;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzat extends UIController {
    private final View view;
    private final String zznp;
    private final String zzsh;

    public zzat(View view, Context context) {
        this.view = view;
        this.zznp = context.getString(R.string.cast_closed_captions);
        this.zzsh = context.getString(R.string.cast_closed_captions_unavailable);
        view.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.view.setEnabled(true);
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.view.setEnabled(false);
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.view.setEnabled(false);
    }

    private final void zzdk() {
        MediaInfo mediaInfo;
        List<MediaTrack> mediaTracks;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (mediaInfo = remoteMediaClient.getMediaInfo()) != null && (mediaTracks = mediaInfo.getMediaTracks()) != null && !mediaTracks.isEmpty()) {
            Iterator<MediaTrack> it = mediaTracks.iterator();
            int i = 0;
            while (true) {
                if (it.hasNext()) {
                    MediaTrack next = it.next();
                    if (next.getType() == 2) {
                        i++;
                        if (i > 1) {
                            break;
                        }
                    } else if (next.getType() == 1) {
                        break;
                    }
                }
            }
            if (!remoteMediaClient.isPlayingAd()) {
                this.view.setEnabled(true);
                this.view.setContentDescription(this.zznp);
                return;
            }
        }
        this.view.setEnabled(false);
        this.view.setContentDescription(this.zzsh);
    }
}
