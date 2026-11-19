package com.movieboxpro.android.http.v2;

import com.alibaba.fastjson.JSON;
import com.movieboxpro.android.http.v2.session.AndroidSessionKeyCipher;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: ApiEncryptV2.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000b\u001a\u00020\f2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bJ\f\u0010\u0011\u001a\u00020\f*\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiEncryptV2;", "", SerializerHandler.TYPE_BASE64, "Lcom/movieboxpro/android/http/v2/ApiV2Base64;", "clockSeconds", "Lkotlin/Function0;", "", "nonceSource", "", "<init>", "(Lcom/movieboxpro/android/http/v2/ApiV2Base64;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "encrypt", "", "payload", "", "skey", "appkey", "toLowerHex", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiEncryptV2 {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String EXPIRED_DATE = "expired_date";

    @Deprecated
    public static final int NONCE_SIZE_BYTES = 12;

    @Deprecated
    public static final long PAYLOAD_LIFETIME_SECONDS = 43200;

    @Deprecated
    public static final int TAG_SIZE_BITS = 128;

    @Deprecated
    public static final int TAG_SIZE_BYTES = 16;
    private final ApiV2Base64 base64;
    private final Function0<Long> clockSeconds;
    private final Function0<byte[]> nonceSource;

    public ApiEncryptV2() {
        this(null, null, null, 7, null);
    }

    public ApiEncryptV2(ApiV2Base64 base64, Function0<Long> clockSeconds, Function0<byte[]> nonceSource) {
        Intrinsics.checkNotNullParameter(base64, "base64");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        Intrinsics.checkNotNullParameter(nonceSource, "nonceSource");
        this.base64 = base64;
        this.clockSeconds = clockSeconds;
        this.nonceSource = nonceSource;
    }

    public /* synthetic */ ApiEncryptV2(AndroidApiV2Base64 androidApiV2Base64, Function0 function0, Function0 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? AndroidApiV2Base64.INSTANCE : androidApiV2Base64, (i & 2) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.ApiEncryptV2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(ApiEncryptV2._init_$lambda$0());
            }
        } : function0, (i & 4) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.ApiEncryptV2$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ApiEncryptV2._init_$lambda$1();
            }
        } : function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long _init_$lambda$0() {
        return System.currentTimeMillis() / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] _init_$lambda$1() {
        byte[] bArr = new byte[12];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public final String encrypt(Map<String, ? extends Object> payload, byte[] skey, byte[] appkey) throws Throwable {
        byte[] bArr;
        byte[] bArrCopyOfRange;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(skey, "skey");
        Intrinsics.checkNotNullParameter(appkey, "appkey");
        LinkedHashMap linkedHashMap = new LinkedHashMap(payload);
        if (!linkedHashMap.containsKey(EXPIRED_DATE)) {
            linkedHashMap.put(EXPIRED_DATE, Long.valueOf(this.clockSeconds.invoke().longValue() + 43200));
        }
        String jSONString = JSON.toJSONString(linkedHashMap);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        byte[] bytes = jSONString.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(skey);
        byte[] bArrInvoke = this.nonceSource.invoke();
        byte[] bArr6 = new byte[0];
        byte[] bArr7 = new byte[0];
        byte[] bArr8 = new byte[0];
        byte[] bArrDigest2 = new byte[0];
        byte[] bArr9 = new byte[0];
        byte[] bArr10 = new byte[0];
        byte[] bArr11 = new byte[0];
        try {
            bArr = bArr11;
            if (bArrInvoke.length != 12) {
                bArrCopyOfRange = bArr7;
                bArr2 = bArr8;
                try {
                    throw new IllegalArgumentException("AES-GCM nonce must be 12 bytes".toString());
                } catch (Throwable th) {
                    th = th;
                    bArr3 = bArr6;
                    bArr4 = bArr2;
                    ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                    Intrinsics.checkNotNull(bArrDigest);
                    ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                    throw th;
                }
            }
            try {
                Cipher cipher = Cipher.getInstance(AndroidSessionKeyCipher.AES_TRANSFORMATION);
                bArrCopyOfRange = bArr7;
                try {
                    bArr2 = bArr8;
                    try {
                        cipher.init(1, new SecretKeySpec(bArrDigest, "AES"), new GCMParameterSpec(128, bArrInvoke));
                        byte[] bArrDoFinal = cipher.doFinal(bytes);
                        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
                        try {
                            int length = bArrDoFinal.length - 16;
                            bArr9 = bArr9;
                            try {
                                bArrCopyOfRange = ArraysKt.copyOfRange(bArrDoFinal, 0, length);
                                try {
                                    byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(bArrDoFinal, length, bArrDoFinal.length);
                                    try {
                                        String strEncode = this.base64.encode(bArrInvoke);
                                        String strEncode2 = this.base64.encode(bArrCopyOfRange);
                                        try {
                                            String strEncode3 = this.base64.encode(bArrCopyOfRange2);
                                            bArr4 = bArrCopyOfRange2;
                                            try {
                                                bArrDigest2 = MessageDigest.getInstance("MD5").digest(appkey);
                                                Intrinsics.checkNotNullExpressionValue(bArrDigest2, "digest(...)");
                                                try {
                                                    String lowerHex = toLowerHex(bArrDigest2);
                                                    bArrCopyOfRange = bArrCopyOfRange;
                                                    try {
                                                        byte[] bytes2 = (lowerHex + strEncode + strEncode2 + strEncode3).getBytes(Charsets.UTF_8);
                                                        Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                                                        try {
                                                            Mac mac = Mac.getInstance("HmacSHA256");
                                                            try {
                                                                mac.init(new SecretKeySpec(bArrDigest, "HmacSHA256"));
                                                                byte[] bArrDoFinal2 = mac.doFinal(bytes2);
                                                                Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "doFinal(...)");
                                                                try {
                                                                    Pair[] pairArr = new Pair[6];
                                                                    try {
                                                                        pairArr[0] = TuplesKt.to("v", 2);
                                                                        pairArr[1] = TuplesKt.to("app_key", lowerHex);
                                                                        pairArr[2] = TuplesKt.to("nonce", strEncode);
                                                                        pairArr[3] = TuplesKt.to("ciphertext", strEncode2);
                                                                        pairArr[4] = TuplesKt.to("tag", strEncode3);
                                                                        pairArr[5] = TuplesKt.to("verify", toLowerHex(bArrDoFinal2));
                                                                        String jSONString2 = JSON.toJSONString(MapsKt.linkedMapOf(pairArr));
                                                                        Intrinsics.checkNotNullExpressionValue(jSONString2, "toJSONString(...)");
                                                                        byte[] bytes3 = jSONString2.getBytes(Charsets.UTF_8);
                                                                        Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
                                                                        try {
                                                                            String strEncode4 = this.base64.encode(bytes3);
                                                                            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                                            Intrinsics.checkNotNull(bArrDigest);
                                                                            ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrDoFinal, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bytes2, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrDoFinal2, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bytes3, (byte) 0, 0, 0, 6, (Object) null);
                                                                            return strEncode4;
                                                                        } catch (Throwable th2) {
                                                                            th = th2;
                                                                            bArr4 = bArr4;
                                                                            bytes2 = bytes2;
                                                                            bArr10 = bArrDoFinal2;
                                                                            bArr = bytes3;
                                                                            bArr3 = bArrDoFinal;
                                                                            bArr9 = bytes2;
                                                                            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                                            Intrinsics.checkNotNull(bArrDigest);
                                                                            ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                                                            ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                            throw th;
                                                                        }
                                                                    } catch (Throwable th3) {
                                                                        th = th3;
                                                                        bytes2 = bytes2;
                                                                        bArr10 = bArrDoFinal2;
                                                                        bArr3 = bArrDoFinal;
                                                                        bArr9 = bytes2;
                                                                        ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                                        Intrinsics.checkNotNull(bArrDigest);
                                                                        ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                        throw th;
                                                                    }
                                                                } catch (Throwable th4) {
                                                                    th = th4;
                                                                    bytes2 = bytes2;
                                                                }
                                                            } catch (Throwable th5) {
                                                                th = th5;
                                                                bArr3 = bArrDoFinal;
                                                                bArr9 = bytes2;
                                                                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                                Intrinsics.checkNotNull(bArrDigest);
                                                                ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                                                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                throw th;
                                                            }
                                                        } catch (Throwable th6) {
                                                            th = th6;
                                                        }
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                        bArr10 = bArr10;
                                                        bArr5 = bArrDoFinal;
                                                        bArr3 = bArr5;
                                                        ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                        Intrinsics.checkNotNull(bArrDigest);
                                                        ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                        throw th;
                                                    }
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    bArrCopyOfRange = bArrCopyOfRange;
                                                }
                                            } catch (Throwable th9) {
                                                th = th9;
                                                bArr4 = bArr4;
                                                bArr5 = bArrDoFinal;
                                                bArrDigest2 = bArrDigest2;
                                                bArr3 = bArr5;
                                                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                                Intrinsics.checkNotNull(bArrDigest);
                                                ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                throw th;
                                            }
                                        } catch (Throwable th10) {
                                            th = th10;
                                            bArr4 = bArrCopyOfRange2;
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        bArr4 = bArrCopyOfRange2;
                                        bArr5 = bArrDoFinal;
                                    }
                                } catch (Throwable th12) {
                                    th = th12;
                                    bArrCopyOfRange = bArrCopyOfRange;
                                    bArr10 = bArr10;
                                    bArr3 = bArrDoFinal;
                                    bArr4 = bArr2;
                                    ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                                    Intrinsics.checkNotNull(bArrDigest);
                                    ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                    throw th;
                                }
                            } catch (Throwable th13) {
                                th = th13;
                            }
                        } catch (Throwable th14) {
                            th = th14;
                            bArr9 = bArr9;
                        }
                    } catch (Throwable th15) {
                        th = th15;
                        bArr3 = bArr6;
                        bArr4 = bArr2;
                        ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                        Intrinsics.checkNotNull(bArrDigest);
                        ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                        throw th;
                    }
                } catch (Throwable th16) {
                    th = th16;
                    bArr2 = bArr8;
                    bArr3 = bArr6;
                    bArr4 = bArr2;
                    ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                    Intrinsics.checkNotNull(bArrDigest);
                    ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                    throw th;
                }
            } catch (Throwable th17) {
                th = th17;
                bArrCopyOfRange = bArr7;
                bArr2 = bArr8;
                bArr3 = bArr6;
                bArr4 = bArr2;
                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                Intrinsics.checkNotNull(bArrDigest);
                ArraysKt.fill$default(bArrDigest, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArrCopyOfRange, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArr4, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArrDigest2, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArr9, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArr10, (byte) 0, 0, 0, 6, (Object) null);
                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                throw th;
            }
        } catch (Throwable th18) {
            th = th18;
            bArr = bArr11;
        }
    }

    private final String toLowerHex(byte[] bArr) {
        return ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.movieboxpro.android.http.v2.ApiEncryptV2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApiEncryptV2.toLowerHex$lambda$3(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toLowerHex$lambda$3(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Integer.valueOf(b & 255)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: compiled from: ApiEncryptV2.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiEncryptV2$Companion;", "", "<init>", "()V", "EXPIRED_DATE", "", "PAYLOAD_LIFETIME_SECONDS", "", "NONCE_SIZE_BYTES", "", "TAG_SIZE_BYTES", "TAG_SIZE_BITS", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
