package com.movieboxpro.android.timroes.axmlrpc;

import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes5.dex */
public class XMLRPCClient {
    static final String CONTENT_LENGTH = "Content-Length";
    static final String CONTENT_TYPE = "Content-Type";
    private static final String DEFAULT_USER_AGENT = "aXMLRPC";
    static final String FAULT = "fault";
    public static final int FLAGS_8BYTE_INT = 2;
    public static final int FLAGS_ACCEPT_NULL_DATES = 16384;
    public static final int FLAGS_APACHE_WS = 776;
    public static final int FLAGS_DEBUG = 8192;
    public static final int FLAGS_DEFAULT_TYPE_STRING = 256;
    public static final int FLAGS_ENABLE_COOKIES = 4;
    public static final int FLAGS_FORWARD = 32;
    public static final int FLAGS_IGNORE_NAMESPACES = 512;
    public static final int FLAGS_IGNORE_STATUSCODE = 16;
    public static final int FLAGS_NIL = 8;
    public static final int FLAGS_NONE = 0;
    public static final int FLAGS_NO_STRING_DECODE = 2048;
    public static final int FLAGS_NO_STRING_ENCODE = 4096;
    public static final int FLAGS_SSL_IGNORE_ERRORS = 192;
    public static final int FLAGS_SSL_IGNORE_INVALID_CERT = 128;
    public static final int FLAGS_SSL_IGNORE_INVALID_HOST = 64;
    public static final int FLAGS_STRICT = 1;
    public static final int FLAGS_USE_SYSTEM_PROXY = 1024;
    static final String HOST = "Host";
    static final String HTTP_POST = "POST";
    static final String METHOD_CALL = "methodCall";
    static final String METHOD_NAME = "methodName";
    static final String METHOD_RESPONSE = "methodResponse";
    static final String PARAM = "param";
    static final String PARAMS = "params";
    static final String STRUCT_MEMBER = "member";
    static final String TYPE_XML = "text/xml; charset=utf-8";
    static final String USER_AGENT = "User-Agent";
    public static final String VALUE = "value";
    private AuthenticationManager authManager;
    private Map<Long, Caller> backgroundCalls;
    private CookieManager cookieManager;
    private final int flags;
    private Map<String, String> httpParameters;
    private KeyManager[] keyManagers;
    private Proxy proxy;
    private ResponseParser responseParser;
    private final SerializerHandler serializerHandler;
    private int timeout;
    private TrustManager[] trustManagers;
    private URL url;

    public XMLRPCClient(URL url, String userAgent, int flags) {
        this.httpParameters = new ConcurrentHashMap();
        this.backgroundCalls = new ConcurrentHashMap();
        this.serializerHandler = new SerializerHandler(flags);
        this.url = url;
        this.flags = flags;
        this.responseParser = new ResponseParser();
        this.cookieManager = new CookieManager(flags);
        this.authManager = new AuthenticationManager();
        this.httpParameters.put("Content-Type", TYPE_XML);
        this.httpParameters.put("User-Agent", userAgent);
        if (isFlagSet(128)) {
            this.trustManagers = new TrustManager[]{new X509TrustManager(this) { // from class: com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
        }
        if (isFlagSet(1024)) {
            Properties properties = System.getProperties();
            String property = properties.getProperty("http.proxyHost");
            int i = Integer.parseInt(properties.getProperty("http.proxyPort", "0"));
            if (i <= 0 || property.length() <= 0 || property.equals("null")) {
                return;
            }
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, i));
        }
    }

    public XMLRPCClient(URL url, int flags) {
        this(url, DEFAULT_USER_AGENT, flags);
    }

    public XMLRPCClient(URL url, String userAgent) {
        this(url, userAgent, 0);
    }

    public XMLRPCClient(URL url) {
        this(url, DEFAULT_USER_AGENT, 0);
    }

    public URL getURL() {
        return this.url;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setUserAgentString(String userAgent) {
        this.httpParameters.put("User-Agent", userAgent);
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setCustomHttpHeader(String headerName, String headerValue) {
        if ("Content-Type".equals(headerName) || "Host".equals(headerName) || "Content-Length".equals(headerName)) {
            throw new XMLRPCRuntimeException("You cannot modify the Host, Content-Type or Content-Length header.");
        }
        this.httpParameters.put(headerName, headerValue);
    }

    public void setLoginData(String user, String pass) {
        this.authManager.setAuthData(user, pass);
    }

    public void clearLoginData() {
        this.authManager.clearAuthData();
    }

    public Map<String, String> getCookies() {
        return this.cookieManager.getCookies();
    }

    public void clearCookies() {
        this.cookieManager.clearCookies();
    }

    public void installCustomTrustManager(TrustManager trustManager) {
        if (isFlagSet(128)) {
            return;
        }
        this.trustManagers = new TrustManager[]{trustManager};
    }

    public void installCustomTrustManagers(TrustManager[] trustManagers) {
        if (isFlagSet(128)) {
            return;
        }
        this.trustManagers = (TrustManager[]) trustManagers.clone();
    }

    public void installCustomKeyManager(KeyManager keyManager) {
        if (isFlagSet(128)) {
            return;
        }
        this.keyManagers = new KeyManager[]{keyManager};
    }

    public void installCustomKeyManagers(KeyManager[] keyManagers) {
        if (isFlagSet(128)) {
            return;
        }
        this.keyManagers = (KeyManager[]) keyManagers.clone();
    }

    public Object call(String method, Object... params) throws XMLRPCException {
        return new Caller().call(method, params);
    }

    public long callAsync(XMLRPCCallback listener, String methodName, Object... params) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        new Caller(listener, jCurrentTimeMillis, methodName, params).start();
        return jCurrentTimeMillis;
    }

    public void cancel(long id) {
        Caller caller = this.backgroundCalls.get(Long.valueOf(id));
        if (caller == null) {
            return;
        }
        caller.cancel();
        try {
            caller.join();
        } catch (InterruptedException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Call createCall(String method, Object[] params) {
        if (isFlagSet(1) && !method.matches("^[A-Za-z0-9\\._:/]*$")) {
            throw new XMLRPCRuntimeException("Method name must only contain A-Z a-z . : _ / ");
        }
        return new Call(this.serializerHandler, method, params);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFlagSet(int flag) {
        return (flag & this.flags) != 0;
    }

    private class Caller extends Thread {
        private volatile boolean canceled;
        private HttpURLConnection http;
        private XMLRPCCallback listener;
        private String methodName;
        private Object[] params;
        private long threadId;

        public Caller(XMLRPCCallback listener, long threadId, String methodName, Object[] params) {
            this.listener = listener;
            this.threadId = threadId;
            this.methodName = methodName;
            this.params = params;
        }

        public Caller() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (this.listener == null) {
                    return;
                }
                try {
                    try {
                        XMLRPCClient.this.backgroundCalls.put(Long.valueOf(this.threadId), this);
                        this.listener.onResponse(this.threadId, call(this.methodName, this.params));
                    } catch (XMLRPCServerException e) {
                        this.listener.onServerError(this.threadId, e);
                    }
                } catch (CancelException unused) {
                } catch (XMLRPCException e2) {
                    this.listener.onError(this.threadId, e2);
                }
            } finally {
                XMLRPCClient.this.backgroundCalls.remove(Long.valueOf(this.threadId));
            }
        }

        public void cancel() {
            this.canceled = true;
            HttpURLConnection httpURLConnection = this.http;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        public Object call(String methodName, Object[] params) throws XMLRPCException {
            URLConnection uRLConnectionOpenConnection;
            int responseCode;
            InputStream errorStream;
            try {
                try {
                    Call callCreateCall = XMLRPCClient.this.createCall(methodName, params);
                    if (XMLRPCClient.this.proxy != null) {
                        uRLConnectionOpenConnection = XMLRPCClient.this.url.openConnection(XMLRPCClient.this.proxy);
                    } else {
                        uRLConnectionOpenConnection = XMLRPCClient.this.url.openConnection();
                    }
                    HttpURLConnection httpURLConnectionVerifyConnection = verifyConnection(uRLConnectionOpenConnection);
                    this.http = httpURLConnectionVerifyConnection;
                    httpURLConnectionVerifyConnection.setInstanceFollowRedirects(false);
                    this.http.setRequestMethod("POST");
                    this.http.setDoOutput(true);
                    this.http.setDoInput(true);
                    if (XMLRPCClient.this.timeout > 0) {
                        this.http.setConnectTimeout(XMLRPCClient.this.timeout * 1000);
                        this.http.setReadTimeout(XMLRPCClient.this.timeout * 1000);
                    }
                    for (Map.Entry entry : XMLRPCClient.this.httpParameters.entrySet()) {
                        this.http.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                    XMLRPCClient.this.authManager.setAuthentication(this.http);
                    XMLRPCClient.this.cookieManager.setCookies(this.http);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.http.getOutputStream());
                    outputStreamWriter.write(callCreateCall.getXML(XMLRPCClient.this.isFlagSet(8192)));
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    try {
                        responseCode = this.http.getResponseCode();
                    } catch (IOException unused) {
                        responseCode = this.http.getResponseCode();
                    }
                    if (responseCode == 403 || responseCode == 401) {
                        if (XMLRPCClient.this.isFlagSet(16)) {
                            errorStream = this.http.getErrorStream();
                        } else {
                            throw new XMLRPCException("Invalid status code '" + responseCode + "' returned from server.");
                        }
                    } else {
                        errorStream = this.http.getInputStream();
                    }
                    if (responseCode != 301 && responseCode != 302) {
                        if (!XMLRPCClient.this.isFlagSet(16) && responseCode != 200) {
                            throw new XMLRPCException("The status code of the http response must be 200.");
                        }
                        if (XMLRPCClient.this.isFlagSet(1) && !this.http.getContentType().startsWith(XMLRPCClient.TYPE_XML)) {
                            throw new XMLRPCException("The Content-Type of the response must be text/xml.");
                        }
                        XMLRPCClient.this.cookieManager.readCookies(this.http);
                        return XMLRPCClient.this.responseParser.parse(XMLRPCClient.this.serializerHandler, errorStream, XMLRPCClient.this.isFlagSet(8192));
                    }
                    if (XMLRPCClient.this.isFlagSet(32)) {
                        boolean z = responseCode == 302;
                        String headerField = this.http.getHeaderField(HttpHeaders.LOCATION);
                        if (headerField == null || headerField.length() <= 0) {
                            headerField = this.http.getHeaderField("location");
                        }
                        URL url = XMLRPCClient.this.url;
                        XMLRPCClient.this.url = new URL(headerField);
                        this.http.disconnect();
                        Object objCall = call(methodName, params);
                        if (z) {
                            XMLRPCClient.this.url = url;
                        }
                        return objCall;
                    }
                    throw new XMLRPCException("The server responded with a http 301 or 302 status code, but forwarding has not been enabled (FLAGS_FORWARD).");
                } catch (SocketTimeoutException unused2) {
                    throw new XMLRPCTimeoutException("The XMLRPC call timed out.");
                }
            } catch (IOException e) {
                if (!this.canceled || this.threadId <= 0) {
                    throw new XMLRPCException(e);
                }
                throw new CancelException();
            }
        }

        private HttpURLConnection verifyConnection(URLConnection conn) throws XMLRPCException {
            if (!(conn instanceof HttpURLConnection)) {
                throw new IllegalArgumentException("The URL is not valid for a http connection.");
            }
            if (conn instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) conn;
                if (XMLRPCClient.this.isFlagSet(64)) {
                    httpsURLConnection.setHostnameVerifier(new HostnameVerifier(this) { // from class: com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient.Caller.1
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String host, SSLSession ssl) {
                            return true;
                        }
                    });
                }
                if (XMLRPCClient.this.trustManagers != null) {
                    try {
                        String[] strArr = {"TLS", "SSL"};
                        for (int i = 0; i < 2; i++) {
                            SSLContext sSLContext = SSLContext.getInstance(strArr[i]);
                            sSLContext.init(XMLRPCClient.this.keyManagers, XMLRPCClient.this.trustManagers, new SecureRandom());
                            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                        }
                    } catch (Exception e) {
                        throw new XMLRPCException(e);
                    }
                }
                return httpsURLConnection;
            }
            return (HttpURLConnection) conn;
        }
    }

    private class CancelException extends RuntimeException {
        private CancelException() {
        }
    }
}
