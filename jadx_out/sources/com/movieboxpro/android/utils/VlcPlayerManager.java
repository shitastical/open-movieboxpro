package com.movieboxpro.android.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VlcPlayerManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlayerManager;", "", "<init>", "()V", "instance", "Lcom/movieboxpro/android/utils/VlcPlayer;", "get", "context", "Landroid/content/Context;", "peek", "releaseAndClear", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VlcPlayerManager {
    public static final VlcPlayerManager INSTANCE = new VlcPlayerManager();
    private static volatile VlcPlayer instance;

    private VlcPlayerManager() {
    }

    public final VlcPlayer get(Context context) {
        VlcPlayer vlcPlayer;
        Intrinsics.checkNotNullParameter(context, "context");
        VlcPlayer vlcPlayer2 = instance;
        if (vlcPlayer2 != null) {
            return vlcPlayer2;
        }
        synchronized (this) {
            vlcPlayer = instance;
            if (vlcPlayer == null) {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                vlcPlayer = new VlcPlayer(applicationContext);
                instance = vlcPlayer;
            }
        }
        return vlcPlayer;
    }

    public final VlcPlayer peek() {
        return instance;
    }

    public final void releaseAndClear() {
        synchronized (this) {
            VlcPlayer vlcPlayer = instance;
            if (vlcPlayer != null) {
                vlcPlayer.release();
            }
            instance = null;
            Unit unit = Unit.INSTANCE;
        }
    }
}
