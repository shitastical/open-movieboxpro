package ly.count.android.sdk;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes6.dex */
public final class CertificateTrustManager implements X509TrustManager {
    private final List<byte[]> certificates;
    private final List<byte[]> keys;

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public CertificateTrustManager(String[] strArr, String[] strArr2) throws CertificateException {
        if ((strArr == null || strArr.length == 0) && (strArr2 == null || strArr2.length == 0)) {
            throw new IllegalArgumentException("You must specify non-empty keys list or certs list");
        }
        this.keys = new ArrayList();
        if (strArr != null) {
            for (String str : strArr) {
                this.keys.add(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(str, 0))).getPublicKey().getEncoded());
            }
        }
        this.certificates = new ArrayList();
        if (strArr2 != null) {
            for (String str2 : strArr2) {
                this.certificates.add(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(str2, 0))).getEncoded());
            }
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null) {
            throw new IllegalArgumentException("PublicKeyManager: X509Certificate array is null");
        }
        if (x509CertificateArr.length <= 0) {
            throw new IllegalArgumentException("PublicKeyManager: X509Certificate is empty");
        }
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init((KeyStore) null);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
            }
            byte[] encoded = x509CertificateArr[0].getPublicKey().getEncoded();
            byte[] encoded2 = x509CertificateArr[0].getEncoded();
            Iterator<byte[]> it = this.keys.iterator();
            while (it.hasNext()) {
                if (Arrays.equals(it.next(), encoded)) {
                    return;
                }
            }
            Iterator<byte[]> it2 = this.certificates.iterator();
            while (it2.hasNext()) {
                if (Arrays.equals(it2.next(), encoded2)) {
                    return;
                }
            }
            throw new CertificateException("Public keys didn't pass checks");
        } catch (Exception e) {
            throw new CertificateException(e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new UnsupportedOperationException("checkClientTrusted: Not supported yet.");
    }
}
