package androidx.media3.exoplayer.drm;

import android.media.AudioDeviceInfo;
import android.media.AudioProfile;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.PlaybackParams;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ AudioDeviceInfo m(Object obj) {
        return (AudioDeviceInfo) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AudioProfile m326m(Object obj) {
        return (AudioProfile) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AudioRouting.OnRoutingChangedListener m327m(Object obj) {
        return (AudioRouting.OnRoutingChangedListener) obj;
    }

    public static /* synthetic */ AudioTrack.Builder m() {
        return new AudioTrack.Builder();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MediaDrm.KeyStatus m328m(Object obj) {
        return (MediaDrm.KeyStatus) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MediaDrm.PlaybackComponent m330m(Object obj) {
        return (MediaDrm.PlaybackComponent) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackParams m332m() {
        return new PlaybackParams();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MediaMetricsManager m335m(Object obj) {
        return (MediaMetricsManager) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackErrorEvent.Builder m336m() {
        return new PlaybackErrorEvent.Builder();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PlaybackStateEvent.Builder m337m() {
        return new PlaybackStateEvent.Builder();
    }

    public static /* synthetic */ TrackChangeEvent.Builder m(int i) {
        return new TrackChangeEvent.Builder(i);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m343m(Object obj) {
        return obj instanceof MediaDrmResetException;
    }
}
