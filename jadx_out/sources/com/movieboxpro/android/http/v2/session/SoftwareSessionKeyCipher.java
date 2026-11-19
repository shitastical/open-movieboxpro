package com.movieboxpro.android.http.v2.session;

import android.content.Context;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SoftwareSessionKeyCipher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B,\b\u0002\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\t\u0010\fJ\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0004H\u0002R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;", "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;", "wrapKeyProvider", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "forEncryption", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "wrapKey", "([B)V", "encrypt", "value", "decrypt", "keySpec", "Ljavax/crypto/spec/SecretKeySpec;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SoftwareSessionKeyCipher implements SessionKeyCipher {
    private static final String AES_ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 32;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final byte ENVELOPE_VERSION = 2;
    private static final int GCM_TAG_BITS = 128;
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final Function1<Boolean, byte[]> wrapKeyProvider;

    public /* synthetic */ SoftwareSessionKeyCipher(Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this((Function1<? super Boolean, byte[]>) function1);
    }

    @JvmStatic
    public static final SoftwareSessionKeyCipher fromContext(Context context) {
        return INSTANCE.fromContext(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SoftwareSessionKeyCipher(Function1<? super Boolean, byte[]> function1) {
        this.wrapKeyProvider = function1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SoftwareSessionKeyCipher(byte[] wrapKey) {
        this((Function1<? super Boolean, byte[]>) INSTANCE.fixedKeyProvider(wrapKey));
        Intrinsics.checkNotNullParameter(wrapKey, "wrapKey");
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] encrypt(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(value, "value");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, keySpec(true));
        byte[] iv = cipher.getIV();
        if (iv.length > 255) {
            throw new IllegalArgumentException("AES-GCM IV is too large".toString());
        }
        byte[] bArrDoFinal = cipher.doFinal(value);
        byte[] bArr = new byte[iv.length + 2 + bArrDoFinal.length];
        bArr[0] = 2;
        bArr[1] = (byte) iv.length;
        Intrinsics.checkNotNull(iv);
        ArraysKt.copyInto$default(iv, bArr, 2, 0, 0, 12, (Object) null);
        Intrinsics.checkNotNull(bArrDoFinal);
        ArraysKt.copyInto$default(bArrDoFinal, bArr, iv.length + 2, 0, 0, 12, (Object) null);
        return bArr;
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] decrypt(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.length < 3) {
            throw new IllegalArgumentException("Invalid AES-GCM session envelope".toString());
        }
        if (value[0] != 2) {
            throw new IllegalArgumentException("Unsupported session envelope version".toString());
        }
        int i = value[1] & 255;
        if (i <= 0 || value.length <= i + 2) {
            throw new IllegalArgumentException("Invalid AES-GCM session envelope".toString());
        }
        int i2 = i + 2;
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(value, 2, i2);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(value, i2, value.length);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(2, keySpec(false), new GCMParameterSpec(128, bArrCopyOfRange));
        byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "run(...)");
        return bArrDoFinal;
    }

    private final SecretKeySpec keySpec(boolean forEncryption) {
        byte[] bArrInvoke = this.wrapKeyProvider.invoke(Boolean.valueOf(forEncryption));
        try {
            return new SecretKeySpec(bArrInvoke, AES_ALGORITHM);
        } finally {
            ArraysKt.fill$default(bArrInvoke, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    /* JADX INFO: compiled from: SoftwareSessionKeyCipher.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001b\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0000¢\u0006\u0002\b\u0014J\u001c\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher$Companion;", "", "<init>", "()V", "ENVELOPE_VERSION", "", "AES_KEY_SIZE", "", "AES_ALGORITHM", "", "TRANSFORMATION", "GCM_TAG_BITS", "fromContext", "Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;", "context", "Landroid/content/Context;", "fromKeyDirectory", "directory", "Lkotlin/Function0;", "Ljava/io/File;", "fromKeyDirectory$app_movie_tvRelease", "fixedKeyProvider", "Lkotlin/Function1;", "", "", "wrapKey", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SoftwareSessionKeyCipher fromContext(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            final Context applicationContext = context.getApplicationContext();
            return fromKeyDirectory$app_movie_tvRelease(new Function0() { // from class: com.movieboxpro.android.http.v2.session.SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SoftwareSessionKeyCipher.Companion.fromContext$lambda$0(applicationContext);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final File fromContext$lambda$0(Context context) {
            File noBackupFilesDir = context.getNoBackupFilesDir();
            Intrinsics.checkNotNullExpressionValue(noBackupFilesDir, "getNoBackupFilesDir(...)");
            return noBackupFilesDir;
        }

        public final SoftwareSessionKeyCipher fromKeyDirectory$app_movie_tvRelease(final Function0<? extends File> directory) {
            Intrinsics.checkNotNullParameter(directory, "directory");
            return new SoftwareSessionKeyCipher(new Function1() { // from class: com.movieboxpro.android.http.v2.session.SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SoftwareSessionKeyCipher.Companion.fromKeyDirectory$lambda$1(directory, ((Boolean) obj).booleanValue());
                }
            }, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final byte[] fromKeyDirectory$lambda$1(Function0 function0, boolean z) {
            SoftwareSessionWrapKeyStore softwareSessionWrapKeyStore = new SoftwareSessionWrapKeyStore((File) function0.invoke());
            return z ? softwareSessionWrapKeyStore.loadOrCreate() : softwareSessionWrapKeyStore.read();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Function1<Boolean, byte[]> fixedKeyProvider(byte[] wrapKey) {
            final byte[] bArrCopyOf = Arrays.copyOf(wrapKey, wrapKey.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            if (bArrCopyOf.length != 32) {
                throw new IllegalArgumentException("Software session wrap key must be 32 bytes".toString());
            }
            return new Function1() { // from class: com.movieboxpro.android.http.v2.session.SoftwareSessionKeyCipher$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SoftwareSessionKeyCipher.Companion.fixedKeyProvider$lambda$3(bArrCopyOf, ((Boolean) obj).booleanValue());
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final byte[] fixedKeyProvider$lambda$3(byte[] bArr, boolean z) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            return bArrCopyOf;
        }
    }
}
