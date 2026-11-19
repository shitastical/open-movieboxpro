package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbb extends UIController {
    private final TextView zzso;
    private final List<String> zzsp;

    public zzbb(TextView textView, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.zzsp = arrayList;
        this.zzso = textView;
        arrayList.addAll(list);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        MediaQueueItem preloadedItem;
        MediaInfo media;
        MediaMetadata metadata;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (preloadedItem = remoteMediaClient.getPreloadedItem()) == null || (media = preloadedItem.getMedia()) == null || (metadata = media.getMetadata()) == null) {
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
