package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbc extends UIController {
    private final TextView zzso;
    private final List<String> zzsp;

    public zzbc(TextView textView, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.zzsp = arrayList;
        this.zzso = textView;
        arrayList.addAll(list);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = remoteMediaClient.getMediaStatus().getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null) {
            return;
        }
        for (String str : this.zzsp) {
            if (metadata.containsKey(str)) {
                this.zzso.setText(metadata.getString(str));
                return;
            }
        }
        this.zzso.setText("");
    }
}
