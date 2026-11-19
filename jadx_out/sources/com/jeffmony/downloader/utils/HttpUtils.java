package com.jeffmony.downloader.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: classes5.dex */
public class HttpUtils {
    public static final int MAX_REDIRECT = 20;
    public static final int MAX_RETRY_COUNT = 100;
    public static final int RESPONSE_200 = 200;
    public static final int RESPONSE_206 = 206;
    public static final int RESPONSE_503 = 503;
    private static final String TAG = "HttpUtils";
    private static int sRedirectCount;

    static /* synthetic */ boolean lambda$trustAllCert$0(String str, SSLSession sSLSession) {
        return true;
    }

    public static boolean matchHttpSchema(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String scheme = Uri.parse(str).getScheme();
        return IDataSource.SCHEME_HTTP_TAG.equals(scheme) || IDataSource.SCHEME_HTTPS_TAG.equals(scheme);
    }

    public static HttpURLConnection getConnection(String str, Map<String, String> map, boolean z) throws IOException {
        URL url;
        IOException e;
        URL url2 = new URL(str);
        sRedirectCount = 0;
        while (sRedirectCount < 20) {
            try {
                HttpURLConnection httpURLConnectionMakeConnection = makeConnection(url2, map, z);
                int responseCode = httpURLConnectionMakeConnection.getResponseCode();
                if (responseCode != 300 && responseCode != 301 && responseCode != 302 && (responseCode != 303 || (responseCode != 307 && responseCode != 308))) {
                    return httpURLConnectionMakeConnection;
                }
                String headerField = httpURLConnectionMakeConnection.getHeaderField(HttpHeaders.LOCATION);
                httpURLConnectionMakeConnection.disconnect();
                url = new URL(headerField);
                try {
                    sRedirectCount++;
                } catch (IOException e2) {
                    e = e2;
                    if (!(e instanceof SSLHandshakeException) && !(e instanceof SSLPeerUnverifiedException)) {
                        continue;
                    } else if (!z) {
                        return getConnection(str, map, true);
                    }
                }
            } catch (IOException e3) {
                url = url2;
                e = e3;
            }
            url2 = url;
        }
        throw new NoRouteToHostException("Too many redirects: " + sRedirectCount);
    }

    private static URL handleRedirect(URL url, String str) throws IOException {
        if (str == null) {
            throw new ProtocolException("Null location redirect");
        }
        URL url2 = new URL(url, str);
        String protocol = url2.getProtocol();
        if (IDataSource.SCHEME_HTTPS_TAG.equals(protocol) || IDataSource.SCHEME_HTTP_TAG.equals(protocol)) {
            return url2;
        }
        throw new ProtocolException("Unsupported protocol redirect: " + protocol);
    }

    private static HttpURLConnection makeConnection(URL url, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (z && (httpURLConnection instanceof HttpsURLConnection)) {
            trustAllCert((HttpsURLConnection) httpURLConnection);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(VideoDownloadUtils.getDownloadConfig().getConnTimeOut());
        httpURLConnection.setReadTimeout(VideoDownloadUtils.getDownloadConfig().getReadTimeOut());
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    public static void closeConnection(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public static void trustAllCert(HttpsURLConnection httpsURLConnection) {
        SSLContext sSLContext;
        SSLContext sSLContext2 = null;
        try {
            sSLContext = SSLContext.getInstance("TLS");
            if (sSLContext != null) {
                try {
                    sSLContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: com.jeffmony.downloader.utils.HttpUtils.1
                        @Override // javax.net.ssl.X509TrustManager
                        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                        }

                        @Override // javax.net.ssl.X509TrustManager
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }}, null);
                } catch (Exception unused) {
                    sSLContext2 = sSLContext;
                    LogUtils.w(TAG, "SSLContext init failed");
                    sSLContext = sSLContext2;
                }
            }
        } catch (Exception unused2) {
        }
        if (sSLContext == null) {
            return;
        }
        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.jeffmony.downloader.utils.HttpUtils$$ExternalSyntheticLambda0
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return HttpUtils.lambda$trustAllCert$0(str, sSLSession);
            }
        });
        httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
    }
}
