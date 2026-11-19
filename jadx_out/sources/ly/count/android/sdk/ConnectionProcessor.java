package ly.count.android.sdk;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.common.net.HttpHeaders;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ConnectionProcessor implements Runnable {
    private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 30000;
    private static final int READ_TIMEOUT_IN_MILLISECONDS = 30000;
    protected static String salt;
    ModuleLog L;
    final ConfigurationProvider configProvider_;
    private final DeviceIdProvider deviceIdProvider_;
    HealthTracker healthTracker;
    private final Map<String, String> requestHeaderCustomValues_;
    final RequestInfoProvider requestInfoProvider_;
    private final String serverURL_;
    private final SSLContext sslContext_;
    private final StorageProvider storageProvider_;

    private enum RequestResult {
        OK,
        RETRY
    }

    ConnectionProcessor(String str, StorageProvider storageProvider, DeviceIdProvider deviceIdProvider, ConfigurationProvider configurationProvider, RequestInfoProvider requestInfoProvider, SSLContext sSLContext, Map<String, String> map, ModuleLog moduleLog, HealthTracker healthTracker) {
        this.serverURL_ = str;
        this.storageProvider_ = storageProvider;
        this.deviceIdProvider_ = deviceIdProvider;
        this.configProvider_ = configurationProvider;
        this.sslContext_ = sSLContext;
        this.requestHeaderCustomValues_ = map;
        this.requestInfoProvider_ = requestInfoProvider;
        this.L = moduleLog;
        this.healthTracker = healthTracker;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:57:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:61:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:80:0x028e A[EDGE_INSN: B:80:0x028e->B:54:0x028e BREAK  A[LOOP:2: B:52:0x0288->B:62:0x02e6], SYNTHETIC] */
    public synchronized URLConnection urlConnectionForServerRequest(String str, String str2) throws IOException {
        long length;
        String str3;
        HttpURLConnection httpURLConnection;
        int i;
        String headerFieldKey;
        boolean z;
        String str4 = str2 != null ? str2 : "/i";
        int i2 = 0;
        boolean z2 = str.contains("&crash=") || str.length() >= 2048 || this.requestInfoProvider_.isHttpPostForced();
        String str5 = this.serverURL_ + str4;
        if (z2) {
            String strSha256Hash = UtilsNetworking.sha256Hash(str + salt);
            String str6 = str + "&checksum256=" + strSha256Hash;
            length = str6.length();
            this.L.v("[Connection Processor] The following checksum was added:[" + strSha256Hash + "]");
            str3 = str6;
        } else {
            String str7 = str5 + "?" + str;
            String strSha256Hash2 = UtilsNetworking.sha256Hash(str + salt);
            this.L.v("[Connection Processor] The following checksum was added:[" + strSha256Hash2 + "]");
            length = 0;
            str3 = str;
            str5 = str7 + "&checksum256=" + strSha256Hash2;
        }
        long length2 = length + ((long) str5.length());
        URL url = new URL(str5);
        if (Countly.publicKeyPinCertificates == null && Countly.certificatePinCertificates == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(this.sslContext_.getSocketFactory());
            httpURLConnection = httpsURLConnection;
        }
        httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_HIGH_WATERMARK_MS);
        httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_HIGH_WATERMARK_MS);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("GET");
        if (this.requestHeaderCustomValues_ != null) {
            this.L.v("[Connection Processor] Adding [" + this.requestHeaderCustomValues_.size() + "] custom header fields");
            for (Map.Entry<String, String> entry : this.requestHeaderCustomValues_.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && value != null && !key.isEmpty()) {
                    httpURLConnection.addRequestProperty(key, value);
                }
            }
        }
        String picturePathFromQuery = ModuleUserProfile.getPicturePathFromQuery(url);
        this.L.v("[Connection Processor] Got picturePath: " + picturePathFromQuery);
        if (!picturePathFromQuery.equals("")) {
            File file = new File(picturePathFromQuery);
            httpURLConnection.setDoOutput(true);
            String hexString = Long.toHexString(System.currentTimeMillis());
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "multipart/form-data; boundary=" + hexString);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            PrintWriter printWriter = new PrintWriter((Writer) new OutputStreamWriter(outputStream, "UTF-8"), true);
            printWriter.append((CharSequence) "--").append((CharSequence) hexString).append((CharSequence) "\r\n");
            printWriter.append((CharSequence) "Content-Disposition: form-data; name=\"binaryFile\"; filename=\"").append((CharSequence) file.getName()).append((CharSequence) "\"").append((CharSequence) "\r\n");
            printWriter.append((CharSequence) "Content-Type: ").append((CharSequence) URLConnection.guessContentTypeFromName(file.getName())).append((CharSequence) "\r\n");
            printWriter.append((CharSequence) "Content-Transfer-Encoding: binary").append((CharSequence) "\r\n");
            printWriter.append((CharSequence) "\r\n").flush();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int i3 = fileInputStream.read(bArr);
                    if (i3 == -1) {
                        break;
                    }
                    outputStream.write(bArr, i2, i3);
                    length2 += (long) i3;
                    i2 = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream.flush();
            printWriter.append((CharSequence) "\r\n").flush();
            fileInputStream.close();
            printWriter.append((CharSequence) "--").append((CharSequence) hexString).append((CharSequence) "--").append((CharSequence) "\r\n").flush();
        } else {
            if (z2) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
                OutputStream outputStream2 = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream2, "UTF-8"));
                bufferedWriter.write(str3);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream2.close();
            } else {
                this.L.v("[Connection Processor] Using HTTP GET");
                httpURLConnection.setDoOutput(false);
            }
            i = 0;
            while (true) {
                headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                if (headerFieldKey == null) {
                    break;
                }
                int i4 = i + 1;
                length2 += ((long) (headerFieldKey.getBytes("US-ASCII").length + httpURLConnection.getHeaderField(i).getBytes("US-ASCII").length)) + 2;
                i = i4;
            }
            ModuleLog moduleLog = this.L;
            StringBuilder sb = new StringBuilder();
            sb.append("[Connection Processor] Using HTTP POST: [");
            sb.append(z2);
            sb.append("] forced:[");
            sb.append(this.requestInfoProvider_.isHttpPostForced());
            sb.append("] length:[");
            if (str3.length() >= 2048) {
                z = true;
            } else {
                z = false;
            }
            sb.append(z);
            sb.append("] crash:[");
            sb.append(str3.contains("&crash="));
            sb.append("] | Approx data size: [");
            sb.append(length2);
            sb.append(" B]");
            moduleLog.v(sb.toString());
        }
        i = 0;
        while (true) {
            headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
            if (headerFieldKey == null) {
                break;
                break;
            }
            int i5 = i + 1;
            length2 += ((long) (headerFieldKey.getBytes("US-ASCII").length + httpURLConnection.getHeaderField(i).getBytes("US-ASCII").length)) + 2;
            i = i5;
        }
        ModuleLog moduleLog2 = this.L;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[Connection Processor] Using HTTP POST: [");
        sb2.append(z2);
        sb2.append("] forced:[");
        sb2.append(this.requestInfoProvider_.isHttpPostForced());
        sb2.append("] length:[");
        if (str3.length() >= 2048) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("] crash:[");
        sb2.append(str3.contains("&crash="));
        sb2.append("] | Approx data size: [");
        sb2.append(length2);
        sb2.append(" B]");
        moduleLog2.v(sb2.toString());
        return httpURLConnection;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x02da A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:128:0x032c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:148:0x02c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:151:0x032e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x02dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:157:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x02d1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:0x02c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:171:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0170 A[Catch: all -> 0x02ea, Exception -> 0x02ee, TRY_LEAVE, TryCatch #1 {Exception -> 0x02ee, blocks: (B:46:0x0167, B:48:0x0170, B:51:0x0178), top: B:140:0x0167 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x0185  */
    /* JADX WARN: Code duplicated, block: B:56:0x01ca A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:70:0x0227  */
    /* JADX WARN: Code duplicated, block: B:72:0x022b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:74:0x0237 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x0239  */
    /* JADX WARN: Code duplicated, block: B:81:0x0254 A[Catch: all -> 0x02e6, Exception -> 0x02e8, TryCatch #13 {Exception -> 0x02e8, all -> 0x02e6, blocks: (B:52:0x017c, B:54:0x0188, B:57:0x01cc, B:59:0x01d2, B:82:0x025d, B:84:0x0261, B:87:0x026c, B:89:0x0272, B:90:0x027c, B:93:0x0299, B:99:0x02d1, B:61:0x01dd, B:65:0x0201, B:66:0x020b, B:68:0x0213, B:69:0x021d, B:63:0x01e4, B:73:0x022d, B:79:0x0240, B:80:0x024a, B:81:0x0254), top: B:155:0x017c, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0261 A[Catch: all -> 0x02e6, Exception -> 0x02e8, TryCatch #13 {Exception -> 0x02e8, all -> 0x02e6, blocks: (B:52:0x017c, B:54:0x0188, B:57:0x01cc, B:59:0x01d2, B:82:0x025d, B:84:0x0261, B:87:0x026c, B:89:0x0272, B:90:0x027c, B:93:0x0299, B:99:0x02d1, B:61:0x01dd, B:65:0x0201, B:66:0x020b, B:68:0x0213, B:69:0x021d, B:63:0x01e4, B:73:0x022d, B:79:0x0240, B:80:0x024a, B:81:0x0254), top: B:155:0x017c, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x026a  */
    /* JADX WARN: Code duplicated, block: B:87:0x026c A[Catch: all -> 0x02e6, Exception -> 0x02e8, TryCatch #13 {Exception -> 0x02e8, all -> 0x02e6, blocks: (B:52:0x017c, B:54:0x0188, B:57:0x01cc, B:59:0x01d2, B:82:0x025d, B:84:0x0261, B:87:0x026c, B:89:0x0272, B:90:0x027c, B:93:0x0299, B:99:0x02d1, B:61:0x01dd, B:65:0x0201, B:66:0x020b, B:68:0x0213, B:69:0x021d, B:63:0x01e4, B:73:0x022d, B:79:0x0240, B:80:0x024a, B:81:0x0254), top: B:155:0x017c, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x027c A[Catch: all -> 0x02e6, Exception -> 0x02e8, TryCatch #13 {Exception -> 0x02e8, all -> 0x02e6, blocks: (B:52:0x017c, B:54:0x0188, B:57:0x01cc, B:59:0x01d2, B:82:0x025d, B:84:0x0261, B:87:0x026c, B:89:0x0272, B:90:0x027c, B:93:0x0299, B:99:0x02d1, B:61:0x01dd, B:65:0x0201, B:66:0x020b, B:68:0x0213, B:69:0x021d, B:63:0x01e4, B:73:0x022d, B:79:0x0240, B:80:0x024a, B:81:0x0254), top: B:155:0x017c, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x0297 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:93:0x0299 A[Catch: all -> 0x02e6, Exception -> 0x02e8, TRY_LEAVE, TryCatch #13 {Exception -> 0x02e8, all -> 0x02e6, blocks: (B:52:0x017c, B:54:0x0188, B:57:0x01cc, B:59:0x01d2, B:82:0x025d, B:84:0x0261, B:87:0x026c, B:89:0x0272, B:90:0x027c, B:93:0x0299, B:99:0x02d1, B:61:0x01dd, B:65:0x0201, B:66:0x020b, B:68:0x0213, B:69:0x021d, B:63:0x01e4, B:73:0x022d, B:79:0x0240, B:80:0x024a, B:81:0x0254), top: B:155:0x017c, inners: #2 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:90:0x027c, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:93:0x0299, please report this as an issue */
    @Override // java.lang.Runnable
    public void run() throws Throwable {
        String strReplace;
        boolean z;
        String str;
        String str2;
        String string;
        InputStream errorStream;
        URLConnection uRLConnectionUrlConnectionForServerRequest;
        String strInputStreamToString;
        int responseCode;
        RequestResult requestResult;
        HttpURLConnection httpURLConnection;
        while (this.configProvider_.getNetworkingEnabled()) {
            String[] requests = this.storageProvider_.getRequests();
            int length = requests == null ? 0 : requests.length;
            if (this.L.logEnabled()) {
                String str3 = "[Connection Processor] Starting to run, there are [" + length + "] requests stored";
                if (length == 0) {
                    this.L.v(str3);
                } else {
                    this.L.i(str3);
                }
            }
            if (requests == null || length == 0) {
                return;
            }
            if (this.deviceIdProvider_.getDeviceId() == null) {
                this.L.i("[Connection Processor] No Device ID available yet, skipping request " + requests[0]);
                return;
            }
            boolean zContains = requests[0].contains("&override_id=CLYTemporaryDeviceID");
            boolean zContains2 = requests[0].contains("&device_id=CLYTemporaryDeviceID");
            if (zContains || zContains2 || this.deviceIdProvider_.isTemporaryIdEnabled()) {
                this.L.i("[Connection Processor] Temporary ID detected, stalling requests. Id override:[" + zContains + "], tmp id tag:[" + zContains2 + "], temp ID set:[" + this.deviceIdProvider_.isTemporaryIdEnabled() + "]");
                return;
            }
            boolean zContains3 = requests[0].contains("&override_id=");
            boolean zContains4 = requests[0].contains("&device_id=");
            URLConnection uRLConnection = null;
            JSONObject jSONObject = null;
            uRLConnection = null;
            if (zContains3) {
                strReplace = requests[0].replace("&override_id=", "&device_id=");
            } else {
                if (zContains4) {
                    String strUrlDecodeString = UtilsNetworking.urlDecodeString(requests[0].substring(requests[0].indexOf("&device_id=") + 11));
                    if (strUrlDecodeString.equals(this.deviceIdProvider_.getDeviceId())) {
                        str = requests[0];
                        this.L.d("[Connection Processor] Provided device_id is the same as the previous one used, nothing will be merged");
                        z = false;
                    } else {
                        z = zContains4;
                        str = requests[0] + "&old_device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
                    }
                    String str4 = str;
                    str2 = strUrlDecodeString;
                    strReplace = str4;
                } else {
                    strReplace = requests[0] + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider_.getDeviceId());
                }
                StringBuilder sb = new StringBuilder();
                sb.append(strReplace);
                sb.append("&rr=");
                sb.append(length - 1);
                string = sb.toString();
                if (this.requestInfoProvider_.isDeviceAppCrawler() || !this.requestInfoProvider_.ifShouldIgnoreCrawlers()) {
                    try {
                        uRLConnectionUrlConnectionForServerRequest = urlConnectionForServerRequest(string, null);
                        try {
                            try {
                                uRLConnectionUrlConnectionForServerRequest.connect();
                                if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                                    httpURLConnection = (HttpURLConnection) uRLConnectionUrlConnectionForServerRequest;
                                    try {
                                        errorStream = httpURLConnection.getInputStream();
                                    } catch (Exception unused) {
                                        errorStream = httpURLConnection.getErrorStream();
                                    }
                                    try {
                                        responseCode = httpURLConnection.getResponseCode();
                                        strInputStreamToString = Utils.inputStreamToString(errorStream);
                                    } catch (Exception e) {
                                        e = e;
                                        uRLConnection = uRLConnectionUrlConnectionForServerRequest;
                                        try {
                                            this.L.d("[Connection Processor] Got exception while trying to submit request data: [" + string + "] [" + e + "]");
                                            if (uRLConnection instanceof HttpURLConnection) {
                                                if (errorStream != null) {
                                                    try {
                                                        errorStream.close();
                                                    } catch (Throwable unused2) {
                                                    }
                                                }
                                                ((HttpURLConnection) uRLConnection).disconnect();
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th) {
                                            th = th;
                                            if (uRLConnection instanceof HttpURLConnection) {
                                                if (errorStream != null) {
                                                    try {
                                                        errorStream.close();
                                                    } catch (Throwable unused3) {
                                                    }
                                                }
                                                ((HttpURLConnection) uRLConnection).disconnect();
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        uRLConnection = uRLConnectionUrlConnectionForServerRequest;
                                        if (uRLConnection instanceof HttpURLConnection) {
                                            if (errorStream != null) {
                                                errorStream.close();
                                            }
                                            ((HttpURLConnection) uRLConnection).disconnect();
                                        }
                                        throw th;
                                    }
                                } else {
                                    errorStream = null;
                                    strInputStreamToString = "";
                                    responseCode = 0;
                                }
                                this.L.d("[Connection Processor] code:[" + responseCode + "], response:[" + strInputStreamToString + "], response size:[" + strInputStreamToString.length() + " B], request: " + string + ", url: " + this.serverURL_);
                                if (responseCode >= 200 || responseCode >= 300) {
                                    if (responseCode < 300 && responseCode < 400) {
                                        this.L.d("[Connection Processor] Encountered redirect, will retry");
                                        requestResult = RequestResult.RETRY;
                                    } else if (responseCode != 400 || responseCode == 404) {
                                        this.L.w("[Connection Processor] Bad request, will still retry");
                                        requestResult = RequestResult.RETRY;
                                    } else if (responseCode > 400) {
                                        this.L.d("[Connection Processor] Server is down, will retry");
                                        requestResult = RequestResult.RETRY;
                                    } else {
                                        this.L.d("[Connection Processor] Bad response code, will retry");
                                        requestResult = RequestResult.RETRY;
                                    }
                                } else if (strInputStreamToString.isEmpty()) {
                                    this.L.v("[Connection Processor] Response was empty, will retry");
                                    requestResult = RequestResult.RETRY;
                                } else {
                                    try {
                                        jSONObject = new JSONObject(strInputStreamToString);
                                    } catch (JSONException unused4) {
                                        this.L.e("[Connection Processor] Failed to parse response [" + strInputStreamToString + "].");
                                    }
                                    if (jSONObject == null) {
                                        this.L.v("[Connection Processor] Response was a unknown, will retry");
                                        requestResult = RequestResult.RETRY;
                                    } else if (jSONObject.has("result")) {
                                        this.L.v("[Connection Processor] Response was a success");
                                        requestResult = RequestResult.OK;
                                    } else {
                                        this.L.v("[Connection Processor] Response does not contain 'result', will retry");
                                        requestResult = RequestResult.RETRY;
                                    }
                                }
                                if (requestResult != RequestResult.OK) {
                                    this.healthTracker.logFailedNetworkRequest(responseCode, strInputStreamToString);
                                    if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                                        if (errorStream != null) {
                                            try {
                                                errorStream.close();
                                            } catch (Throwable unused5) {
                                            }
                                        }
                                        ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                                        return;
                                    }
                                    return;
                                }
                                this.storageProvider_.removeRequest(requests[0]);
                                if (z) {
                                    if (str2 != null || str2.isEmpty()) {
                                        this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                                    } else {
                                        this.deviceIdProvider_.getDeviceIdInstance().changeToCustomId(str2);
                                    }
                                }
                                if (z || zContains3) {
                                    this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                                    Countly.sharedInstance().notifyDeviceIdChange();
                                }
                                if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                                    if (errorStream != null) {
                                        try {
                                            errorStream.close();
                                        } catch (Throwable unused6) {
                                        }
                                    }
                                    ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                                }
                            } catch (Exception e2) {
                                e = e2;
                                errorStream = null;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            errorStream = null;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        errorStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        errorStream = null;
                    }
                } else {
                    this.L.i("[Connection Processor] Device identified as a app crawler, skipping request " + requests[0]);
                    this.storageProvider_.removeRequest(requests[0]);
                }
            }
            z = zContains4;
            str2 = null;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(strReplace);
            sb2.append("&rr=");
            sb2.append(length - 1);
            string = sb2.toString();
            if (this.requestInfoProvider_.isDeviceAppCrawler()) {
                uRLConnectionUrlConnectionForServerRequest = urlConnectionForServerRequest(string, null);
                uRLConnectionUrlConnectionForServerRequest.connect();
                if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) uRLConnectionUrlConnectionForServerRequest;
                    errorStream = httpURLConnection.getInputStream();
                    responseCode = httpURLConnection.getResponseCode();
                    strInputStreamToString = Utils.inputStreamToString(errorStream);
                } else {
                    errorStream = null;
                    strInputStreamToString = "";
                    responseCode = 0;
                }
                this.L.d("[Connection Processor] code:[" + responseCode + "], response:[" + strInputStreamToString + "], response size:[" + strInputStreamToString.length() + " B], request: " + string + ", url: " + this.serverURL_);
                if (responseCode >= 200) {
                    if (responseCode < 300) {
                        if (responseCode != 400) {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        } else {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        }
                    } else if (responseCode != 400) {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    } else {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    }
                    if (requestResult != RequestResult.OK) {
                        this.healthTracker.logFailedNetworkRequest(responseCode, strInputStreamToString);
                        if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                            if (errorStream != null) {
                                errorStream.close();
                            }
                            ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                            return;
                        }
                        return;
                    }
                    this.storageProvider_.removeRequest(requests[0]);
                    if (z) {
                        if (str2 != null) {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        } else {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        }
                    }
                    if (z) {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    } else {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    }
                    if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                        if (errorStream != null) {
                            errorStream.close();
                        }
                        ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                    }
                } else {
                    if (responseCode < 300) {
                        if (responseCode != 400) {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        } else {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        }
                    } else if (responseCode != 400) {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    } else {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    }
                    if (requestResult != RequestResult.OK) {
                        this.healthTracker.logFailedNetworkRequest(responseCode, strInputStreamToString);
                        if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                            if (errorStream != null) {
                                errorStream.close();
                            }
                            ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                            return;
                        }
                        return;
                    }
                    this.storageProvider_.removeRequest(requests[0]);
                    if (z) {
                        if (str2 != null) {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        } else {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        }
                    }
                    if (z) {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    } else {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    }
                    if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                        if (errorStream != null) {
                            errorStream.close();
                        }
                        ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                    }
                }
            } else {
                uRLConnectionUrlConnectionForServerRequest = urlConnectionForServerRequest(string, null);
                uRLConnectionUrlConnectionForServerRequest.connect();
                if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                    httpURLConnection = (HttpURLConnection) uRLConnectionUrlConnectionForServerRequest;
                    errorStream = httpURLConnection.getInputStream();
                    responseCode = httpURLConnection.getResponseCode();
                    strInputStreamToString = Utils.inputStreamToString(errorStream);
                } else {
                    errorStream = null;
                    strInputStreamToString = "";
                    responseCode = 0;
                }
                this.L.d("[Connection Processor] code:[" + responseCode + "], response:[" + strInputStreamToString + "], response size:[" + strInputStreamToString.length() + " B], request: " + string + ", url: " + this.serverURL_);
                if (responseCode >= 200) {
                    if (responseCode < 300) {
                        if (responseCode != 400) {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        } else {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        }
                    } else if (responseCode != 400) {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    } else {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    }
                    if (requestResult != RequestResult.OK) {
                        this.healthTracker.logFailedNetworkRequest(responseCode, strInputStreamToString);
                        if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                            if (errorStream != null) {
                                errorStream.close();
                            }
                            ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                            return;
                        }
                        return;
                    }
                    this.storageProvider_.removeRequest(requests[0]);
                    if (z) {
                        if (str2 != null) {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        } else {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        }
                    }
                    if (z) {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    } else {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    }
                    if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                        if (errorStream != null) {
                            errorStream.close();
                        }
                        ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                    }
                } else {
                    if (responseCode < 300) {
                        if (responseCode != 400) {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        } else {
                            this.L.w("[Connection Processor] Bad request, will still retry");
                            requestResult = RequestResult.RETRY;
                        }
                    } else if (responseCode != 400) {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    } else {
                        this.L.w("[Connection Processor] Bad request, will still retry");
                        requestResult = RequestResult.RETRY;
                    }
                    if (requestResult != RequestResult.OK) {
                        this.healthTracker.logFailedNetworkRequest(responseCode, strInputStreamToString);
                        if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                            if (errorStream != null) {
                                errorStream.close();
                            }
                            ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                            return;
                        }
                        return;
                    }
                    this.storageProvider_.removeRequest(requests[0]);
                    if (z) {
                        if (str2 != null) {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        } else {
                            this.L.e("[Connection Processor] Failed to change device ID with merging because the new ID was empty or null. [" + str2 + "]");
                        }
                    }
                    if (z) {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    } else {
                        this.L.v("[Connection Processor] Device ID changed, change:[" + z + "] | override:[" + zContains3 + "]");
                        Countly.sharedInstance().notifyDeviceIdChange();
                    }
                    if (uRLConnectionUrlConnectionForServerRequest instanceof HttpURLConnection) {
                        if (errorStream != null) {
                            errorStream.close();
                        }
                        ((HttpURLConnection) uRLConnectionUrlConnectionForServerRequest).disconnect();
                    }
                }
            }
        }
        this.L.w("[Connection Processor] run, Networking config is disabled, request queue skipped");
    }

    String getServerURL() {
        return this.serverURL_;
    }

    StorageProvider getCountlyStore() {
        return this.storageProvider_;
    }
}
