package com.google.android.gms.cast.framework.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;

/* JADX INFO: loaded from: classes4.dex */
public class MediaIntentReceiver extends BroadcastReceiver {
    public static final String ACTION_DISCONNECT = "com.google.android.gms.cast.framework.action.DISCONNECT";
    public static final String ACTION_FORWARD = "com.google.android.gms.cast.framework.action.FORWARD";
    public static final String ACTION_REWIND = "com.google.android.gms.cast.framework.action.REWIND";
    public static final String ACTION_SKIP_NEXT = "com.google.android.gms.cast.framework.action.SKIP_NEXT";
    public static final String ACTION_SKIP_PREV = "com.google.android.gms.cast.framework.action.SKIP_PREV";
    public static final String ACTION_STOP_CASTING = "com.google.android.gms.cast.framework.action.STOP_CASTING";
    public static final String ACTION_TOGGLE_PLAYBACK = "com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK";
    public static final String EXTRA_SKIP_STEP_MS = "googlecast-extra_skip_step_ms";

    protected void onReceiveOtherAction(Context context, String str, Intent intent) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
        }
        SessionManager sessionManager = CastContext.getSharedInstance(context).getSessionManager();
        action.hashCode();
        switch (action) {
            case "com.google.android.gms.cast.framework.action.REWIND":
                onReceiveActionRewind(sessionManager.getCurrentSession(), intent.getLongExtra(EXTRA_SKIP_STEP_MS, 0L));
                break;
            case "com.google.android.gms.cast.framework.action.SKIP_NEXT":
                onReceiveActionSkipNext(sessionManager.getCurrentSession());
                break;
            case "com.google.android.gms.cast.framework.action.SKIP_PREV":
                onReceiveActionSkipPrev(sessionManager.getCurrentSession());
                break;
            case "com.google.android.gms.cast.framework.action.STOP_CASTING":
                sessionManager.endCurrentSession(true);
                break;
            case "com.google.android.gms.cast.framework.action.DISCONNECT":
                sessionManager.endCurrentSession(false);
                break;
            case "com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK":
                onReceiveActionTogglePlayback(sessionManager.getCurrentSession());
                break;
            case "com.google.android.gms.cast.framework.action.FORWARD":
                onReceiveActionForward(sessionManager.getCurrentSession(), intent.getLongExtra(EXTRA_SKIP_STEP_MS, 0L));
                break;
            case "android.intent.action.MEDIA_BUTTON":
                onReceiveActionMediaButton(sessionManager.getCurrentSession(), intent);
                break;
            default:
                onReceiveOtherAction(context, action, intent);
                break;
        }
    }

    protected void onReceiveActionTogglePlayback(Session session) {
        if (session instanceof CastSession) {
            zzh((CastSession) session);
        }
    }

    protected void onReceiveActionSkipNext(Session session) {
        RemoteMediaClient remoteMediaClientZzi;
        if (!(session instanceof CastSession) || (remoteMediaClientZzi = zzi((CastSession) session)) == null || remoteMediaClientZzi.isPlayingAd()) {
            return;
        }
        remoteMediaClientZzi.queueNext(null);
    }

    protected void onReceiveActionSkipPrev(Session session) {
        RemoteMediaClient remoteMediaClientZzi;
        if (!(session instanceof CastSession) || (remoteMediaClientZzi = zzi((CastSession) session)) == null || remoteMediaClientZzi.isPlayingAd()) {
            return;
        }
        remoteMediaClientZzi.queuePrev(null);
    }

    protected void onReceiveActionForward(Session session, long j) {
        if (session instanceof CastSession) {
            zza((CastSession) session, j);
        }
    }

    protected void onReceiveActionRewind(Session session, long j) {
        if (session instanceof CastSession) {
            zza((CastSession) session, -j);
        }
    }

    protected void onReceiveActionMediaButton(Session session, Intent intent) {
        KeyEvent keyEvent;
        if ((session instanceof CastSession) && intent.hasExtra("android.intent.extra.KEY_EVENT") && (keyEvent = (KeyEvent) intent.getExtras().get("android.intent.extra.KEY_EVENT")) != null && keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 85) {
            zzh((CastSession) session);
        }
    }

    @Deprecated
    protected void onReceiveOtherAction(String str, Intent intent) {
        onReceiveOtherAction(null, str, intent);
    }

    private static void zzh(CastSession castSession) {
        RemoteMediaClient remoteMediaClientZzi = zzi(castSession);
        if (remoteMediaClientZzi == null) {
            return;
        }
        remoteMediaClientZzi.togglePlayback();
    }

    private static void zza(CastSession castSession, long j) {
        RemoteMediaClient remoteMediaClientZzi;
        if (j == 0 || (remoteMediaClientZzi = zzi(castSession)) == null || remoteMediaClientZzi.isLiveStream() || remoteMediaClientZzi.isPlayingAd()) {
            return;
        }
        remoteMediaClientZzi.seek(remoteMediaClientZzi.getApproximateStreamPosition() + j);
    }

    private static RemoteMediaClient zzi(CastSession castSession) {
        if (castSession == null || !castSession.isConnected()) {
            return null;
        }
        return castSession.getRemoteMediaClient();
    }
}
