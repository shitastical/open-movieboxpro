package com.movieboxpro.android.http.v2;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NativeApiSecrets.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0082 J\t\u0010\u0006\u001a\u00020\u0005H\u0082 J\t\u0010\u0007\u001a\u00020\u0005H\u0082 J\t\u0010\b\u001a\u00020\u0005H\u0082 JI\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\u0010\u0011JI\u0010\u0012\u001a\u0002H\n\"\u0004\b\u0000\u0010\n26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/http/v2/NativeApiSecrets;", "", "<init>", "()V", "appKeyBytes", "", "appSkeyBytes", "clientKeyPemBytes", "clientCertPemBytes", "withAppSecrets", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "appkey", "appSkey", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "withClientTlsPem", "keyPem", "certPem", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NativeApiSecrets {
    public static final NativeApiSecrets INSTANCE = new NativeApiSecrets();

    private final native byte[] appKeyBytes();

    private final native byte[] appSkeyBytes();

    private final native byte[] clientCertPemBytes();

    private final native byte[] clientKeyPemBytes();

    private NativeApiSecrets() {
    }

    static {
        System.loadLibrary("apisecrets");
    }

    public final <T> T withAppSecrets(Function2<? super byte[], ? super byte[], ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        byte[] bArrAppKeyBytes = appKeyBytes();
        byte[] bArrAppSkeyBytes = appSkeyBytes();
        try {
            return block.invoke(bArrAppKeyBytes, bArrAppSkeyBytes);
        } finally {
            byte b = 0;
            int i = 0;
            ArraysKt.fill$default(bArrAppKeyBytes, (byte) 0, (int) b, i, 6, (Object) null);
            ArraysKt.fill$default(bArrAppSkeyBytes, b, i, 0, 6, (Object) null);
        }
    }

    public final <T> T withClientTlsPem(Function2<? super byte[], ? super byte[], ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        byte[] bArrClientKeyPemBytes = clientKeyPemBytes();
        byte[] bArrClientCertPemBytes = clientCertPemBytes();
        try {
            return block.invoke(bArrClientKeyPemBytes, bArrClientCertPemBytes);
        } finally {
            byte b = 0;
            int i = 0;
            ArraysKt.fill$default(bArrClientKeyPemBytes, (byte) 0, (int) b, i, 6, (Object) null);
            ArraysKt.fill$default(bArrClientCertPemBytes, b, i, 0, 6, (Object) null);
        }
    }
}
