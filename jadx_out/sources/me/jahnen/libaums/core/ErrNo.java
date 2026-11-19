package me.jahnen.libaums.core;

import android.util.Log;
import kotlin.Metadata;

/* JADX INFO: compiled from: ErrNo.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00068Â\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00048Â\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lme/jahnen/libaums/core/ErrNo;", "", "()V", "TAG", "", "errno", "", "getErrno", "()I", "errnoNative", "errstr", "getErrstr", "()Ljava/lang/String;", "errstrNative", "isInited", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ErrNo {
    public static final ErrNo INSTANCE = new ErrNo();
    private static final String TAG = "ErrNo";
    private static final int errnoNative = 0;
    private static final String errstrNative = null;
    private static boolean isInited;

    private ErrNo() {
    }

    static {
        isInited = true;
        try {
            System.loadLibrary("errno-lib");
        } catch (UnsatisfiedLinkError e) {
            isInited = false;
            Log.e(TAG, "could not load errno-lib", e);
        }
    }

    public final int getErrno() {
        if (isInited) {
            return errnoNative;
        }
        return 1337;
    }

    public final String getErrstr() {
        if (isInited) {
            return errstrNative;
        }
        return "errno-lib could not be loaded!";
    }
}
