package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzat;
import com.google.android.gms.internal.cast.zzau;
import com.google.android.gms.internal.cast.zzav;
import com.google.android.gms.internal.cast.zzax;
import com.google.android.gms.internal.cast.zzaz;
import com.google.android.gms.internal.cast.zzba;
import com.google.android.gms.internal.cast.zzbb;
import com.google.android.gms.internal.cast.zzbc;
import com.google.android.gms.internal.cast.zzbd;
import com.google.android.gms.internal.cast.zzbf;
import com.google.android.gms.internal.cast.zzbg;
import com.google.android.gms.internal.cast.zzbh;
import com.google.android.gms.internal.cast.zzbk;
import com.google.android.gms.internal.cast.zzbl;
import com.google.android.gms.internal.cast.zzbn;
import com.google.android.gms.internal.cast.zzbo;
import com.google.android.gms.internal.cast.zzbp;
import com.google.android.gms.internal.cast.zzbq;
import com.google.android.gms.internal.cast.zzbr;
import com.google.android.gms.internal.cast.zzbs;
import com.google.android.gms.internal.cast.zzbt;
import com.google.android.gms.internal.cast.zzbu;
import com.google.android.gms.internal.cast.zzbv;
import com.google.android.gms.internal.cast.zzdw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class UIMediaController implements SessionManagerListener<CastSession>, RemoteMediaClient.Listener {
    private static final zzdw zzbf = new zzdw("UIMediaController");
    private final SessionManager zzhg;
    private RemoteMediaClient zzid;
    private final Activity zzij;
    private RemoteMediaClient.Listener zzsc;
    private final Map<View, List<UIController>> zzsa = new HashMap();
    private final Set<zzbt> zzsb = new HashSet();
    zzbh zzrw = new zzbh();

    public UIMediaController(Activity activity) {
        this.zzij = activity;
        CastContext castContextZzb = CastContext.zzb(activity);
        SessionManager sessionManager = castContextZzb != null ? castContextZzb.getSessionManager() : null;
        this.zzhg = sessionManager;
        if (sessionManager != null) {
            SessionManager sessionManager2 = CastContext.getSharedInstance(activity).getSessionManager();
            sessionManager2.addSessionManagerListener(this, CastSession.class);
            zza(sessionManager2.getCurrentCastSession());
        }
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnding(CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResuming(CastSession castSession, String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarting(CastSession castSession) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionSuspended(CastSession castSession, int i) {
    }

    public boolean isActive() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzid != null;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzid;
    }

    public void setPostRemoteMediaClientListener(RemoteMediaClient.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzsc = listener;
    }

    public void dispose() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzdi();
        this.zzsa.clear();
        SessionManager sessionManager = this.zzhg;
        if (sessionManager != null) {
            sessionManager.removeSessionManagerListener(this, CastSession.class);
        }
        this.zzsc = null;
    }

    public void bindImageViewToPlayPauseToggle(ImageView imageView, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        imageView.setOnClickListener(new zza(this));
        zza(imageView, new zzbf(imageView, this.zzij, drawable, drawable2, drawable3, view, z));
    }

    public void bindViewToSkipNext(View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzb(this));
        zza(view, new zzbo(view, i));
    }

    public void bindViewToSkipPrev(View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzc(this));
        zza(view, new zzbp(view, i));
    }

    public void bindViewToForward(View view, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzd(this, j));
        zza(view, new zzau(view, this.zzrw));
    }

    public void bindViewToRewind(View view, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zze(this, j));
        zza(view, new zzbk(view, this.zzrw));
    }

    public void bindViewToLoadingIndicator(View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(view, new zzba(view));
    }

    public void bindProgressBar(ProgressBar progressBar) {
        bindProgressBar(progressBar, 1000L);
    }

    public void bindProgressBar(ProgressBar progressBar, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(progressBar, new zzbg(progressBar, j));
    }

    public void bindSeekBar(SeekBar seekBar) {
        bindSeekBar(seekBar, 1000L);
    }

    public void bindSeekBar(SeekBar seekBar, long j) {
        zza(seekBar, j, null);
    }

    public final void zza(SeekBar seekBar, long j, zzbn zzbnVar) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        seekBar.setOnSeekBarChangeListener(new zzf(this, zzbnVar, seekBar));
        zza(seekBar, new zzbl(seekBar, j, this.zzrw, zzbnVar));
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z) {
        bindTextViewToStreamPosition(textView, z, 1000L);
    }

    public void bindTextViewToStreamPosition(TextView textView, boolean z, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzbs zzbsVar = new zzbs(textView, j, this.zzij.getString(R.string.cast_invalid_stream_position_text));
        if (z) {
            this.zzsb.add(zzbsVar);
        }
        zza(textView, zzbsVar);
    }

    public void bindTextViewToStreamDuration(TextView textView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(textView, new zzbr(textView, this.zzij.getString(R.string.cast_invalid_stream_duration_text), null));
    }

    public void bindTextViewToStreamDuration(TextView textView, View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(textView, new zzbr(textView, this.zzij.getString(R.string.cast_invalid_stream_duration_text), view));
    }

    public void bindViewToLaunchExpandedController(View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzg(this));
        zza(view, new zzaz(view));
    }

    public void bindViewToClosedCaption(View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        view.setOnClickListener(new zzh(this));
        zza(view, new zzat(view, this.zzij));
    }

    public void bindImageViewToMuteToggle(ImageView imageView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        imageView.setOnClickListener(new zzi(this));
        zza(imageView, new zzbd(imageView, this.zzij));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfCurrentItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView textView, List<String> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(textView, new zzbc(textView, list));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i, int i2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzax(imageView, this.zzij, new ImageHints(i, 0, 0), i2, null));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView imageView, int i, View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzax(imageView, this.zzij, new ImageHints(i, 0, 0), 0, view));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzax(imageView, this.zzij, imageHints, i, null));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView imageView, ImageHints imageHints, View view) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzax(imageView, this.zzij, imageHints, 0, view));
    }

    public void bindViewVisibilityToMediaSession(View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(view, new zzbv(view, i));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfPreloadedItem(textView, Collections.singletonList(str));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView textView, List<String> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(textView, new zzbb(textView, list));
    }

    @Deprecated
    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, int i, int i2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzav(imageView, this.zzij, new ImageHints(i, 0, 0), i2));
    }

    public void bindImageViewToImageOfPreloadedItem(ImageView imageView, ImageHints imageHints, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(imageView, new zzav(imageView, this.zzij, imageHints, i));
    }

    public void bindViewVisibilityToPreloadingEvent(View view, int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(view, new zzbu(view, i));
    }

    public void bindViewToUIController(View view, UIController uIController) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(view, uIController);
    }

    public void bindTextViewToSmartSubtitle(TextView textView) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zza(textView, new zzbq(textView));
    }

    public final void zza(zzbt zzbtVar) {
        this.zzsb.add(zzbtVar);
    }

    protected void onMuteToggleClicked(ImageView imageView) {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzij.getApplicationContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return;
        }
        try {
            currentCastSession.setMute(!currentCastSession.isMute());
        } catch (IOException | IllegalArgumentException e) {
            zzbf.e("Unable to call CastSession.setMute(boolean).", e);
        }
    }

    protected void onPlayPauseToggleClicked(ImageView imageView) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.togglePlayback();
    }

    protected void onSeekBarStopTrackingTouch(SeekBar seekBar) {
        if (this.zzsa.containsKey(seekBar)) {
            for (UIController uIController : this.zzsa.get(seekBar)) {
                if (uIController instanceof zzbl) {
                    ((zzbl) uIController).zzk(true);
                }
            }
        }
        Iterator<zzbt> it = this.zzsb.iterator();
        while (it.hasNext()) {
            it.next().zzk(true);
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.seek(((long) seekBar.getProgress()) + this.zzrw.zzdt());
    }

    protected void onSeekBarStartTrackingTouch(SeekBar seekBar) {
        if (this.zzsa.containsKey(seekBar)) {
            for (UIController uIController : this.zzsa.get(seekBar)) {
                if (uIController instanceof zzbl) {
                    ((zzbl) uIController).zzk(false);
                }
            }
        }
        Iterator<zzbt> it = this.zzsb.iterator();
        while (it.hasNext()) {
            it.next().zzk(false);
        }
    }

    protected void onSeekBarProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            Iterator<zzbt> it = this.zzsb.iterator();
            while (it.hasNext()) {
                it.next().zzg(((long) i) + this.zzrw.zzdt());
            }
        }
    }

    protected void onClosedCaptionClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (this.zzij instanceof FragmentActivity)) {
            TracksChooserDialogFragment tracksChooserDialogFragmentNewInstance = TracksChooserDialogFragment.newInstance();
            FragmentActivity fragmentActivity = (FragmentActivity) this.zzij;
            FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            Fragment fragmentFindFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag("TRACKS_CHOOSER_DIALOG_TAG");
            if (fragmentFindFragmentByTag != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            }
            tracksChooserDialogFragmentNewInstance.show(fragmentTransactionBeginTransaction, "TRACKS_CHOOSER_DIALOG_TAG");
        }
    }

    protected void onForwardClicked(View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (this.zzrw.zzdo()) {
            long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition() + j;
            zzbh zzbhVar = this.zzrw;
            remoteMediaClient.seek(Math.min(approximateStreamPosition, ((long) zzbhVar.zzds()) + zzbhVar.zzdt()));
            return;
        }
        remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() + j);
    }

    protected void onLaunchExpandedControllerClicked(View view) {
        CastMediaOptions castMediaOptions = CastContext.getSharedInstance(this.zzij).getCastOptions().getCastMediaOptions();
        if (castMediaOptions == null || TextUtils.isEmpty(castMediaOptions.getExpandedControllerActivityClassName())) {
            return;
        }
        ComponentName componentName = new ComponentName(this.zzij.getApplicationContext(), castMediaOptions.getExpandedControllerActivityClassName());
        Intent intent = new Intent();
        intent.setComponent(componentName);
        this.zzij.startActivity(intent);
    }

    protected void onRewindClicked(View view, long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        if (this.zzrw.zzdo()) {
            long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition() - j;
            zzbh zzbhVar = this.zzrw;
            remoteMediaClient.seek(Math.max(approximateStreamPosition, ((long) zzbhVar.zzdr()) + zzbhVar.zzdt()));
            return;
        }
        remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() - j);
    }

    protected void onSkipNextClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queueNext(null);
    }

    protected void onSkipPrevClicked(View view) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return;
        }
        remoteMediaClient.queuePrev(null);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStarted(CastSession castSession, String str) {
        zza(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumed(CastSession castSession, boolean z) {
        zza(castSession);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionStartFailed(CastSession castSession, int i) {
        zzdi();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionEnded(CastSession castSession, int i) {
        zzdi();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public void onSessionResumeFailed(CastSession castSession, int i) {
        zzdi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onStatusUpdated() {
        zzdj();
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onMetadataUpdated() {
        zzdj();
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onMetadataUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onQueueStatusUpdated() {
        zzdj();
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onQueueStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onPreloadStatusUpdated() {
        zzdj();
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onAdBreakStatusUpdated() {
        zzdj();
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onAdBreakStatusUpdated();
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onSendingRemoteMediaRequest() {
        Iterator<List<UIController>> it = this.zzsa.values().iterator();
        while (it.hasNext()) {
            Iterator<UIController> it2 = it.next().iterator();
            while (it2.hasNext()) {
                it2.next().onSendingRemoteMediaRequest();
            }
        }
        RemoteMediaClient.Listener listener = this.zzsc;
        if (listener != null) {
            listener.onSendingRemoteMediaRequest();
        }
    }

    public final zzbh zzdh() {
        return this.zzrw;
    }

    private final void zza(Session session) {
        if (!isActive() && (session instanceof CastSession) && session.isConnected()) {
            CastSession castSession = (CastSession) session;
            RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
            this.zzid = remoteMediaClient;
            if (remoteMediaClient != null) {
                remoteMediaClient.addListener(this);
                this.zzrw.onSessionConnected(castSession);
                Iterator<List<UIController>> it = this.zzsa.values().iterator();
                while (it.hasNext()) {
                    Iterator<UIController> it2 = it.next().iterator();
                    while (it2.hasNext()) {
                        it2.next().onSessionConnected(castSession);
                    }
                }
                zzdj();
            }
        }
    }

    private final void zzdi() {
        if (isActive()) {
            this.zzrw.onSessionEnded();
            Iterator<List<UIController>> it = this.zzsa.values().iterator();
            while (it.hasNext()) {
                Iterator<UIController> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    it2.next().onSessionEnded();
                }
            }
            this.zzid.removeListener(this);
            this.zzid = null;
        }
    }

    private final void zza(View view, UIController uIController) {
        if (this.zzhg == null) {
            return;
        }
        List<UIController> arrayList = this.zzsa.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzsa.put(view, arrayList);
        }
        arrayList.add(uIController);
        if (isActive()) {
            uIController.onSessionConnected(this.zzhg.getCurrentCastSession());
            zzdj();
        }
    }

    private final void zzdj() {
        Iterator<List<UIController>> it = this.zzsa.values().iterator();
        while (it.hasNext()) {
            Iterator<UIController> it2 = it.next().iterator();
            while (it2.hasNext()) {
                it2.next().onMediaStatusUpdated();
            }
        }
    }
}
