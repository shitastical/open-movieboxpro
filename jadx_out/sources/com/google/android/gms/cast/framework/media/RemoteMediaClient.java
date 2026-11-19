package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdx;
import com.google.android.gms.internal.cast.zzea;
import com.google.android.gms.internal.cast.zzeb;
import com.google.android.gms.internal.cast.zzec;
import com.google.android.gms.internal.cast.zzez;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteMediaClient implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = zzdx.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    private final zzdx zzff;
    private final Cast.CastApi zzhz;
    private final zza zzpa;
    private GoogleApiClient zzpb;
    private ParseAdsInfoCallback zzpg;
    private final List<Listener> zzpc = new CopyOnWriteArrayList();
    final List<Callback> zzpd = new CopyOnWriteArrayList();
    private final Map<ProgressListener, zze> zzpe = new ConcurrentHashMap();
    private final Map<Long, zze> zzpf = new ConcurrentHashMap();
    private final Object lock = new Object();
    private final Handler handler = new zzez(Looper.getMainLooper());

    public static abstract class Callback {
        public void onAdBreakStatusUpdated() {
        }

        public void onMetadataUpdated() {
        }

        public void onPreloadStatusUpdated() {
        }

        public void onQueueStatusUpdated() {
        }

        public void onSendingRemoteMediaRequest() {
        }

        public void onStatusUpdated() {
        }

        public void zza(int[] iArr) {
        }

        public void zza(int[] iArr, int i) {
        }

        public void zzb(int[] iArr) {
        }

        public void zzb(MediaQueueItem[] mediaQueueItemArr) {
        }

        public void zzc(int[] iArr) {
        }
    }

    @Deprecated
    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    public interface ParseAdsInfoCallback {
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(MediaStatus mediaStatus);

        boolean parseIsPlayingAdFromMediaStatus(MediaStatus mediaStatus);
    }

    public interface ProgressListener {
        void onProgressUpdated(long j, long j2);
    }

    public RemoteMediaClient(zzdx zzdxVar, Cast.CastApi castApi) {
        zza zzaVar = new zza();
        this.zzpa = zzaVar;
        this.zzhz = castApi;
        zzdx zzdxVar2 = (zzdx) Preconditions.checkNotNull(zzdxVar);
        this.zzff = zzdxVar2;
        zzdxVar2.zza(new zzs(this));
        zzdxVar2.zza(zzaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class zzb extends BasePendingResult<MediaChannelResult> {
        zzb() {
            super((GoogleApiClient) null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
        public final MediaChannelResult createFailedResult(Status status) {
            return new zzaw(this, status);
        }
    }

    abstract class zzc extends zzcv<MediaChannelResult> {
        zzec zzgm;
        private final boolean zzpu;

        zzc(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient) {
            this(googleApiClient, false);
        }

        abstract void zzb(zzdd zzddVar) throws zzea;

        zzc(GoogleApiClient googleApiClient, boolean z) {
            super(googleApiClient);
            this.zzpu = z;
            this.zzgm = new zzax(this, RemoteMediaClient.this);
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
        protected /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
            zzdd zzddVar = (zzdd) anyClient;
            if (!this.zzpu) {
                Iterator it = RemoteMediaClient.this.zzpc.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onSendingRemoteMediaRequest();
                }
                Iterator<Callback> it2 = RemoteMediaClient.this.zzpd.iterator();
                while (it2.hasNext()) {
                    it2.next().onSendingRemoteMediaRequest();
                }
            }
            try {
                synchronized (RemoteMediaClient.this.lock) {
                    zzb(zzddVar);
                }
            } catch (zzea unused) {
                setResult((MediaChannelResult) createFailedResult(new Status(2100)));
            }
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzay(this, status);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zza implements zzeb {
        private GoogleApiClient zzgi;
        private long zzgj = 0;

        public zza() {
        }

        public final void zza(GoogleApiClient googleApiClient) {
            this.zzgi = googleApiClient;
        }

        @Override // com.google.android.gms.internal.cast.zzeb
        public final void zza(String str, String str2, long j, String str3) {
            if (this.zzgi == null) {
                throw new IllegalStateException("No GoogleApiClient available");
            }
            RemoteMediaClient.this.zzhz.sendMessage(this.zzgi, str, str2).setResultCallback(new zzav(this, j));
        }

        @Override // com.google.android.gms.internal.cast.zzeb
        public final long zzr() {
            long j = this.zzgj + 1;
            this.zzgj = j;
            return j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class zzd implements MediaChannelResult {
        private final Status zzgq;
        private final JSONObject zzp;

        zzd(Status status, JSONObject jSONObject) {
            this.zzgq = status;
            this.zzp = jSONObject;
        }

        @Override // com.google.android.gms.common.api.Result
        public final Status getStatus() {
            return this.zzgq;
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
        public final JSONObject getCustomData() {
            return this.zzp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zze {
        private final Set<ProgressListener> zzpx = new HashSet();
        private final long zzpy;
        private final Runnable zzpz;
        private boolean zzqa;

        public zze(long j) {
            this.zzpy = j;
            this.zzpz = new zzaz(this, RemoteMediaClient.this);
        }

        public final long zzct() {
            return this.zzpy;
        }

        public final void zza(ProgressListener progressListener) {
            this.zzpx.add(progressListener);
        }

        public final void zzb(ProgressListener progressListener) {
            this.zzpx.remove(progressListener);
        }

        public final boolean hasListener() {
            return !this.zzpx.isEmpty();
        }

        public final void start() {
            RemoteMediaClient.this.handler.removeCallbacks(this.zzpz);
            this.zzqa = true;
            RemoteMediaClient.this.handler.postDelayed(this.zzpz, this.zzpy);
        }

        public final void stop() {
            RemoteMediaClient.this.handler.removeCallbacks(this.zzpz);
            this.zzqa = false;
        }

        public final boolean isStarted() {
            return this.zzqa;
        }
    }

    public final void zzb(GoogleApiClient googleApiClient) {
        GoogleApiClient googleApiClient2 = this.zzpb;
        if (googleApiClient2 == googleApiClient) {
            return;
        }
        if (googleApiClient2 != null) {
            this.zzff.zzeq();
            try {
                this.zzhz.removeMessageReceivedCallbacks(this.zzpb, getNamespace());
            } catch (IOException unused) {
            }
            this.zzpa.zza(null);
            this.handler.removeCallbacksAndMessages(null);
        }
        this.zzpb = googleApiClient;
        if (googleApiClient != null) {
            this.zzpa.zza(googleApiClient);
        }
    }

    public final void zzco() throws IOException {
        GoogleApiClient googleApiClient = this.zzpb;
        if (googleApiClient != null) {
            this.zzhz.setMessageReceivedCallbacks(googleApiClient, getNamespace(), this);
        }
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo) {
        return load(mediaInfo, new MediaLoadOptions.Builder().build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setCustomData(jSONObject).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setActiveTrackIds(jArr).setCustomData(jSONObject).build());
    }

    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzad(this, this.zzpb, mediaInfo, null, mediaLoadOptions));
    }

    public PendingResult<MediaChannelResult> pause() {
        return pause(null);
    }

    public PendingResult<MediaChannelResult> pause(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzao(this, this.zzpb, jSONObject));
    }

    public PendingResult<MediaChannelResult> stop() {
        return stop(null);
    }

    public PendingResult<MediaChannelResult> stop(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzap(this, this.zzpb, jSONObject));
    }

    public PendingResult<MediaChannelResult> play() {
        return play(null);
    }

    public PendingResult<MediaChannelResult> play(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzaq(this, this.zzpb, jSONObject));
    }

    public PendingResult<MediaChannelResult> seek(long j) {
        return seek(j, 0, null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i) {
        return seek(j, i, null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i, JSONObject jSONObject) {
        com.google.android.gms.cast.zzas zzasVarZzo = new com.google.android.gms.cast.zzau().zzc(j).zzd(i).zzh(jSONObject).zzo();
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzar(this, this.zzpb, zzasVarZzo));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d) throws IllegalArgumentException {
        return setStreamVolume(d, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzas(this, this.zzpb, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z) {
        return setStreamMute(z, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzat(this, this.zzpb, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d) {
        return setPlaybackRate(d, null);
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzau(this, this.zzpb, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> skipAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzt(this, this.zzpb));
    }

    public PendingResult<MediaChannelResult> requestStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzu(this, this.zzpb));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(long[] jArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzv(this, this.zzpb, jArr));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(TextTrackStyle textTrackStyle) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzw(this, this.zzpb, textTrackStyle));
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(mediaQueueItemArr, i, i2, -1L, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        com.google.android.gms.cast.zzap zzapVarZzl = new com.google.android.gms.cast.zzar().zzb(i).zzc(i2).zzb(j).zzg(jSONObject).zzl();
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzx(this, this.zzpb, mediaQueueItemArr, null, zzapVarZzl));
    }

    public PendingResult<MediaChannelResult> queueInsertItems(MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzy(this, this.zzpb, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(mediaQueueItem, i, -1L, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzz(this, this.zzpb, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzaa(this, this.zzpb, mediaQueueItemArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzab(this, this.zzpb, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzac(this, this.zzpb, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzae(this, this.zzpb, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzaf(this, this.zzpb, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(int i, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzag(this, this.zzpb, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(int i, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzah(this, this.zzpb, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, JSONObject jSONObject) {
        return queueJumpToItem(i, -1L, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, long j, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzai(this, this.zzpb, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(int i, int i2, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzaj(this, this.zzpb, i, i2, jSONObject));
    }

    public final PendingResult<MediaChannelResult> zzcp() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzak(this, this.zzpb, true));
    }

    public final PendingResult<MediaChannelResult> zzf(int[] iArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzal(this, this.zzpb, true, iArr));
    }

    public final PendingResult<MediaChannelResult> zza(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzam(this, this.zzpb, true, i, i2, i3));
    }

    public final PendingResult<MediaChannelResult> zza(String str, List<zzbx> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzcq()) {
            return zza(17, (String) null);
        }
        return zza(new zzan(this, this.zzpb, true, str, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzf(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        int iIndexOfItemWithId = getMediaQueue().indexOfItemWithId(i);
        if (iIndexOfItemWithId != -1) {
            return iIndexOfItemWithId;
        }
        MediaStatus mediaStatus = getMediaStatus();
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzm(int i) {
        MediaQueueItem queueItem;
        Preconditions.checkMainThread("Must be called from the main thread.");
        int iItemIdAtIndex = getMediaQueue().itemIdAtIndex(i);
        if (iItemIdAtIndex != 0) {
            return iItemIdAtIndex;
        }
        if (getMediaStatus() == null || (queueItem = getMediaStatus().getQueueItem(i)) == null) {
            return 0;
        }
        return queueItem.getItemId();
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            approximateStreamPosition = this.zzff.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public long getApproximateAdBreakClipPositionMs() {
        long approximateAdBreakClipPositionMs;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            approximateAdBreakClipPositionMs = this.zzff.getApproximateAdBreakClipPositionMs();
        }
        return approximateAdBreakClipPositionMs;
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            streamDuration = this.zzff.getStreamDuration();
        }
        return streamDuration;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaStatus = this.zzff.getMediaStatus();
        }
        return mediaStatus;
    }

    public MediaQueue getMediaQueue() {
        MediaQueue mediaQueueZzbm;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaQueueZzbm = MediaQueue.zzbm();
        }
        return mediaQueueZzbm;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaInfo = this.zzff.getMediaInfo();
        }
        return mediaInfo;
    }

    public int getPlayerState() {
        int playerState;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            playerState = mediaStatus != null ? mediaStatus.getPlayerState() : 1;
        }
        return playerState;
    }

    public int getIdleReason() {
        int idleReason;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            idleReason = mediaStatus != null ? mediaStatus.getIdleReason() : 0;
        }
        return idleReason;
    }

    public boolean isLiveStream() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null && mediaInfo.getStreamType() == 2;
    }

    public boolean isPlaying() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 2;
    }

    public boolean isPaused() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return false;
        }
        if (mediaStatus.getPlayerState() != 3) {
            return isLiveStream() && getIdleReason() == 2;
        }
        return true;
    }

    public boolean isBuffering() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 4;
    }

    public boolean isLoadingNextItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || mediaStatus.getLoadingItemId() == 0) ? false : true;
    }

    public MediaQueueItem getCurrentItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getCurrentItemId());
    }

    public MediaQueueItem getLoadingItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getLoadingItemId());
    }

    public MediaQueueItem getPreloadedItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getPreloadedItemId());
    }

    public void togglePlayback() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        int playerState = getPlayerState();
        if (playerState == 4 || playerState == 2) {
            pause();
        } else {
            play();
        }
    }

    public boolean hasMediaSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return isBuffering() || isPlaying() || isPaused() || isLoadingNextItem();
    }

    @Deprecated
    public void addListener(Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzpc.add(listener);
        }
    }

    @Deprecated
    public void removeListener(Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzpc.remove(listener);
        }
    }

    public void registerCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zzpd.add(callback);
        }
    }

    public void unregisterCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zzpd.remove(callback);
        }
    }

    public boolean addProgressListener(ProgressListener progressListener, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (progressListener == null || this.zzpe.containsKey(progressListener)) {
            return false;
        }
        zze zzeVar = this.zzpf.get(Long.valueOf(j));
        if (zzeVar == null) {
            zzeVar = new zze(j);
            this.zzpf.put(Long.valueOf(j), zzeVar);
        }
        zzeVar.zza(progressListener);
        this.zzpe.put(progressListener, zzeVar);
        if (!hasMediaSession()) {
            return true;
        }
        zzeVar.start();
        return true;
    }

    public void removeProgressListener(ProgressListener progressListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zze zzeVarRemove = this.zzpe.remove(progressListener);
        if (zzeVarRemove != null) {
            zzeVarRemove.zzb(progressListener);
            if (zzeVarRemove.hasListener()) {
                return;
            }
            this.zzpf.remove(Long.valueOf(zzeVarRemove.zzct()));
            zzeVarRemove.stop();
        }
    }

    public void setParseAdsInfoCallback(ParseAdsInfoCallback parseAdsInfoCallback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzpg = parseAdsInfoCallback;
    }

    public boolean isPlayingAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.isPlayingAd();
    }

    public String getNamespace() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzff.getNamespace();
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzff.zzo(str2);
    }

    private final boolean zzcq() {
        return this.zzpb != null;
    }

    public static PendingResult<MediaChannelResult> zza(int i, String str) {
        zzb zzbVar = new zzb();
        zzbVar.setResult(zzbVar.createFailedResult(new Status(i, str)));
        return zzbVar;
    }

    private final zzc zza(zzc zzcVar) {
        try {
            this.zzpb.execute(zzcVar);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Throwable unused) {
            zzcVar.setResult((MediaChannelResult) zzcVar.createFailedResult(new Status(2100)));
        }
        return zzcVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Set<ProgressListener> set) {
        HashSet hashSet = new HashSet(set);
        if (isPlaying() || isPaused() || isBuffering()) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((ProgressListener) it.next()).onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
            }
        } else {
            if (isLoadingNextItem()) {
                MediaQueueItem loadingItem = getLoadingItem();
                if (loadingItem == null || loadingItem.getMedia() == null) {
                    return;
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((ProgressListener) it2.next()).onProgressUpdated(0L, loadingItem.getMedia().getStreamDuration());
                }
                return;
            }
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ((ProgressListener) it3.next()).onProgressUpdated(0L, 0L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcr() {
        for (zze zzeVar : this.zzpf.values()) {
            if (hasMediaSession() && !zzeVar.isStarted()) {
                zzeVar.start();
            } else if (!hasMediaSession() && zzeVar.isStarted()) {
                zzeVar.stop();
            }
            if (zzeVar.isStarted() && (isBuffering() || isPaused() || isLoadingNextItem())) {
                zza(zzeVar.zzpx);
            }
        }
    }
}
