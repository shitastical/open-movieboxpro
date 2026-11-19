package com.movieboxpro.android.utils;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VlcPlayerInstance.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlayerInstance;", "Lcom/movieboxpro/android/utils/SingletonHolder;", "Lcom/movieboxpro/android/utils/VlcPlayer;", "Landroid/content/Context;", "<init>", "()V", "vlcPlayer", "initPlayer", "context", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VlcPlayerInstance extends SingletonHolder<VlcPlayer, Context> {
    public static final VlcPlayerInstance INSTANCE = new VlcPlayerInstance();
    private static VlcPlayer vlcPlayer;

    private VlcPlayerInstance() {
        super(new Function1() { // from class: com.movieboxpro.android.utils.VlcPlayerInstance$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VlcPlayerInstance._init_$lambda$0((Context) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VlcPlayer _init_$lambda$0(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.initPlayer(context);
    }

    public final VlcPlayer initPlayer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        VlcPlayer vlcPlayer2 = new VlcPlayer(context);
        vlcPlayer = vlcPlayer2;
        Intrinsics.checkNotNull(vlcPlayer2);
        return vlcPlayer2;
    }
}
