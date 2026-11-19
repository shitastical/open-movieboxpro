package com.movieboxpro.android.utils;

import android.content.Context;
import android.util.Log;
import com.movieboxpro.android.app.App;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.ILibVLCFactory;
import org.videolan.libvlc.util.VLCUtil;

/* JADX INFO: compiled from: VLCInstance.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00028\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/utils/VLCInstance;", "Lcom/movieboxpro/android/utils/SingletonHolder;", "Lorg/videolan/libvlc/interfaces/ILibVLC;", "Landroid/content/Context;", "<init>", "()V", "TAG", "", "sLibVLC", "libVLCFactory", "Lorg/videolan/libvlc/interfaces/ILibVLCFactory;", "init", "ctx", "restart", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VLCInstance extends SingletonHolder<ILibVLC, Context> {
    public static final VLCInstance INSTANCE = new VLCInstance();
    public static final String TAG = "VLC/UiTools/VLCInstance";
    private static final ILibVLCFactory libVLCFactory;
    private static ILibVLC sLibVLC;

    private VLCInstance() {
        super(new Function1() { // from class: com.movieboxpro.android.utils.VLCInstance$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VLCInstance._init_$lambda$0((Context) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ILibVLC _init_$lambda$0(Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        VLCInstance vLCInstance = INSTANCE;
        Context applicationContext = it.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return vLCInstance.init(applicationContext);
    }

    static {
        IComponentFactory factory = FactoryManager.getFactory(ILibVLCFactory.factoryId);
        Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.ILibVLCFactory");
        libVLCFactory = (ILibVLCFactory) factory;
    }

    public final ILibVLC init(Context ctx) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        if (!VLCUtil.hasCompatibleCPU(ctx)) {
            Log.e(TAG, VLCUtil.getErrorMsg());
            throw new IllegalStateException("LibVLC initialisation failed: " + VLCUtil.getErrorMsg());
        }
        ILibVLC fromOptions = libVLCFactory.getFromOptions(ctx, VLCOptions.INSTANCE.getLibOptions());
        Intrinsics.checkNotNullExpressionValue(fromOptions, "getFromOptions(...)");
        sLibVLC = fromOptions;
        if (fromOptions != null) {
            return fromOptions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
        return null;
    }

    public final void restart() throws IllegalStateException {
        ILibVLC iLibVLC = sLibVLC;
        if (iLibVLC == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
            iLibVLC = null;
        }
        iLibVLC.release();
        ILibVLC fromOptions = libVLCFactory.getFromOptions(App.getContext(), VLCOptions.INSTANCE.getLibOptions());
        Intrinsics.checkNotNullExpressionValue(fromOptions, "getFromOptions(...)");
        sLibVLC = fromOptions;
    }
}
