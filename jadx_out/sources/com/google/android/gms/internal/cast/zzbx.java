package com.google.android.gms.internal.cast;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbx extends UIController implements RemoteMediaClient.ProgressListener {
    private final zzbh zzrw;
    private final TextView zzvp;
    private final ImageView zzvq;
    private final int zzvr;

    public zzbx(View view, zzbh zzbhVar) {
        this.zzvp = (TextView) view.findViewById(R.id.live_indicator_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.live_indicator_dot);
        this.zzvq = imageView;
        this.zzrw = zzbhVar;
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castLiveIndicatorColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        int color = view.getContext().getResources().getColor(resourceId);
        this.zzvr = color;
        imageView.getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, 1000L);
        }
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zzdj();
    }

    private final void zzdj() {
        boolean zZzdq;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream()) {
            this.zzvp.setVisibility(8);
            this.zzvq.setVisibility(8);
            return;
        }
        if (!this.zzrw.zzdo()) {
            zZzdq = remoteMediaClient.isPlaying();
        } else {
            zZzdq = this.zzrw.zzdq();
        }
        this.zzvp.setVisibility(0);
        this.zzvq.setVisibility(zZzdq ? 0 : 8);
    }
}
