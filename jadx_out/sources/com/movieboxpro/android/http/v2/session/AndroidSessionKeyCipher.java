package com.movieboxpro.android.http.v2.session;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import com.google.android.gms.stats.CodePackage;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidSessionKeyCipher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00170\u0017H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/AndroidSessionKeyCipher;", "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "applicationContext", "kotlin.jvm.PlatformType", "encrypt", "", "value", "decrypt", "encryptAesGcm", "decryptAesGcm", "getOrCreateAesKey", "Ljavax/crypto/SecretKey;", "encryptRsa", "decryptRsa", "getOrCreateRsaEntry", "Ljava/security/KeyStore$PrivateKeyEntry;", "evictKeys", "", "loadKeyStore", "Ljava/security/KeyStore;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidSessionKeyCipher implements SessionKeyCipher {

    @Deprecated
    public static final String AES_ALIAS = "moviebox_api_v2_aes";

    @Deprecated
    public static final byte AES_ENVELOPE_VERSION = 1;

    @Deprecated
    public static final String AES_TRANSFORMATION = "AES/GCM/NoPadding";

    @Deprecated
    public static final String ANDROID_KEYSTORE = "AndroidKeyStore";

    @Deprecated
    public static final int GCM_TAG_BITS = 128;

    @Deprecated
    public static final String RSA_ALGORITHM = "RSA";

    @Deprecated
    public static final String RSA_ALIAS = "moviebox_api_v2_rsa";

    @Deprecated
    public static final int RSA_KEY_SIZE_BITS = 2048;

    @Deprecated
    public static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private final Context applicationContext;
    private static final Companion Companion = new Companion(null);
    private static final Object KEYSTORE_LOCK = new Object();

    public AndroidSessionKeyCipher(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.applicationContext = context.getApplicationContext();
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] encrypt(byte[] value) {
        byte[] bArrEncryptRsa;
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (KEYSTORE_LOCK) {
            if (Build.VERSION.SDK_INT >= 23) {
                bArrEncryptRsa = encryptAesGcm(value);
            } else {
                bArrEncryptRsa = encryptRsa(value);
            }
        }
        return bArrEncryptRsa;
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] decrypt(byte[] value) {
        byte[] bArrDecryptRsa;
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (KEYSTORE_LOCK) {
            if (Build.VERSION.SDK_INT >= 23) {
                bArrDecryptRsa = decryptAesGcm(value);
            } else {
                bArrDecryptRsa = decryptRsa(value);
            }
        }
        return bArrDecryptRsa;
    }

    private final byte[] encryptAesGcm(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(1, getOrCreateAesKey());
        byte[] iv = cipher.getIV();
        if (iv.length > 255) {
            throw new IllegalArgumentException("AES-GCM IV is too large".toString());
        }
        byte[] bArrDoFinal = cipher.doFinal(value);
        byte[] bArr = new byte[iv.length + 2 + bArrDoFinal.length];
        bArr[0] = 1;
        bArr[1] = (byte) iv.length;
        Intrinsics.checkNotNull(iv);
        ArraysKt.copyInto$default(iv, bArr, 2, 0, 0, 12, (Object) null);
        Intrinsics.checkNotNull(bArrDoFinal);
        ArraysKt.copyInto$default(bArrDoFinal, bArr, iv.length + 2, 0, 0, 12, (Object) null);
        return bArr;
    }

    private final byte[] decryptAesGcm(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (value.length < 3) {
            throw new IllegalArgumentException("Invalid AES-GCM session envelope".toString());
        }
        if (value[0] != 1) {
            throw new IllegalArgumentException("Unsupported session envelope version".toString());
        }
        int i = value[1] & 255;
        if (i <= 0 || value.length <= i + 2) {
            throw new IllegalArgumentException("Invalid AES-GCM session envelope".toString());
        }
        int i2 = i + 2;
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(value, 2, i2);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(value, i2, value.length);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(2, getOrCreateAesKey(), new GCMParameterSpec(128, bArrCopyOfRange));
        byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "run(...)");
        return bArrDoFinal;
    }

    private final SecretKey getOrCreateAesKey() throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Key key = loadKeyStore().getKey(AES_ALIAS, null);
        SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
        App$$ExternalSyntheticApiModelOutline0.m714m();
        keyGenerator.init(App$$ExternalSyntheticApiModelOutline0.m(AES_ALIAS, 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "run(...)");
        return secretKeyGenerateKey;
    }

    private final byte[] encryptRsa(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        KeyStore.PrivateKeyEntry orCreateRsaEntry = getOrCreateRsaEntry();
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(1, orCreateRsaEntry.getCertificate().getPublicKey());
        byte[] bArrDoFinal = cipher.doFinal(value);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "run(...)");
        return bArrDoFinal;
    }

    private final byte[] decryptRsa(byte[] value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        KeyStore.PrivateKeyEntry orCreateRsaEntry = getOrCreateRsaEntry();
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(2, orCreateRsaEntry.getPrivateKey());
        byte[] bArrDoFinal = cipher.doFinal(value);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "run(...)");
        return bArrDoFinal;
    }

    private final KeyStore.PrivateKeyEntry getOrCreateRsaEntry() throws NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        KeyStore.Entry entry = loadKeyStore().getEntry(RSA_ALIAS, null);
        KeyStore.PrivateKeyEntry privateKeyEntry = entry instanceof KeyStore.PrivateKeyEntry ? (KeyStore.PrivateKeyEntry) entry : null;
        if (privateKeyEntry != null) {
            return privateKeyEntry;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 25);
        KeyPairGeneratorSpec keyPairGeneratorSpecBuild = new KeyPairGeneratorSpec.Builder(this.applicationContext).setAlias(RSA_ALIAS).setSubject(new X500Principal("CN=moviebox_api_v2_rsa")).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).setKeySize(2048).build();
        Intrinsics.checkNotNullExpressionValue(keyPairGeneratorSpecBuild, "build(...)");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM, ANDROID_KEYSTORE);
        keyPairGenerator.initialize(keyPairGeneratorSpecBuild);
        keyPairGenerator.generateKeyPair();
        KeyStore.Entry entry2 = loadKeyStore().getEntry(RSA_ALIAS, null);
        Intrinsics.checkNotNull(entry2, "null cannot be cast to non-null type java.security.KeyStore.PrivateKeyEntry");
        return (KeyStore.PrivateKeyEntry) entry2;
    }

    public final void evictKeys() {
        synchronized (KEYSTORE_LOCK) {
            KeyStore keyStoreLoadKeyStore = loadKeyStore();
            for (String str : CollectionsKt.listOf((Object[]) new String[]{AES_ALIAS, RSA_ALIAS})) {
                if (keyStoreLoadKeyStore.containsAlias(str)) {
                    keyStoreLoadKeyStore.deleteEntry(str);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final KeyStore loadKeyStore() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore;
    }

    /* JADX INFO: compiled from: AndroidSessionKeyCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/AndroidSessionKeyCipher$Companion;", "", "<init>", "()V", "ANDROID_KEYSTORE", "", "AES_ALIAS", "RSA_ALIAS", "AES_TRANSFORMATION", "RSA_ALGORITHM", "RSA_TRANSFORMATION", "GCM_TAG_BITS", "", "RSA_KEY_SIZE_BITS", "AES_ENVELOPE_VERSION", "", "KEYSTORE_LOCK", "getKEYSTORE_LOCK", "()Ljava/lang/Object;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getKEYSTORE_LOCK() {
            return AndroidSessionKeyCipher.KEYSTORE_LOCK;
        }
    }
}
