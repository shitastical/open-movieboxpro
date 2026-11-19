package ly.count.android.sdk;

import android.os.AsyncTask;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class ImmediateRequestMaker extends AsyncTask<Object, Void, JSONObject> implements ImmediateRequestI {
    ModuleLog L;
    InternalImmediateRequestCallback callback;

    protected interface InternalImmediateRequestCallback {
        void callback(JSONObject jSONObject);
    }

    ImmediateRequestMaker() {
    }

    @Override // ly.count.android.sdk.ImmediateRequestI
    public void doWork(String str, String str2, ConnectionProcessor connectionProcessor, boolean z, boolean z2, InternalImmediateRequestCallback internalImmediateRequestCallback, ModuleLog moduleLog) {
        execute(str, str2, connectionProcessor, Boolean.valueOf(z), Boolean.valueOf(z2), internalImmediateRequestCallback, moduleLog);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:81:0x019f  */
    /* JADX WARN: Code duplicated, block: B:95:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.AsyncTask
    public JSONObject doInBackground(Object... objArr) throws Throwable {
        BufferedReader bufferedReader;
        HttpURLConnection httpURLConnection;
        InputStream errorStream;
        boolean z = false;
        String str = (String) objArr[0];
        String str2 = (String) objArr[1];
        ConnectionProcessor connectionProcessor = (ConnectionProcessor) objArr[2];
        boolean zBooleanValue = ((Boolean) objArr[3]).booleanValue();
        boolean zBooleanValue2 = ((Boolean) objArr[4]).booleanValue();
        this.callback = (InternalImmediateRequestCallback) objArr[5];
        ModuleLog moduleLog = (ModuleLog) objArr[6];
        this.L = moduleLog;
        HttpURLConnection httpURLConnection2 = null;
        if (!zBooleanValue2) {
            moduleLog.w("[ImmediateRequestMaker] ImmediateRequestMaker, Networking config is disabled, request cancelled. Endpoint[" + str2 + "] request[" + str + "]");
            return null;
        }
        moduleLog.v("[ImmediateRequestMaker] Starting request");
        try {
            ModuleLog moduleLog2 = this.L;
            StringBuilder sb = new StringBuilder("[ImmediateRequestMaker] delayed[");
            sb.append(zBooleanValue);
            sb.append("] hasCallback[");
            sb.append(this.callback != null);
            sb.append("] endpoint[");
            sb.append(str2);
            sb.append("] request[");
            sb.append(str);
            sb.append("] url[");
            sb.append(connectionProcessor.getServerURL());
            sb.append("]");
            moduleLog2.d(sb.toString());
            if (zBooleanValue) {
                this.L.v("[ImmediateRequestMaker] request should be delayed, waiting for 0.5 seconds");
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused) {
                    this.L.w("[ImmediateRequestMaker] While waiting for 0.5 seconds in ImmediateRequestMaker, sleep was interrupted");
                }
            }
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) connectionProcessor.urlConnectionForServerRequest(str, str2);
                try {
                    try {
                        httpURLConnection3.connect();
                        try {
                            errorStream = httpURLConnection3.getInputStream();
                            z = true;
                        } catch (Exception unused2) {
                            errorStream = httpURLConnection3.getErrorStream();
                        }
                        if (errorStream == null) {
                            this.L.e("[ImmediateRequestMaker] Encountered problem while making a immediate server request, received stream was null");
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            return null;
                        }
                        bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                        try {
                            StringBuilder sb2 = new StringBuilder();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb2.append(line);
                                sb2.append(ShellUtil.COMMAND_LINE_END);
                            }
                            String string = sb2.toString();
                            if (!z) {
                                this.L.e("[ImmediateRequestMaker] Encountered problem while making a immediate server request, :[" + string + "]");
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                                try {
                                    bufferedReader.close();
                                } catch (IOException e) {
                                    this.L.e("[ImmediateRequestMaker] ", e);
                                }
                                return null;
                            }
                            this.L.d("[ImmediateRequestMaker] Received the following response, :[" + string + "]");
                            JSONObject jSONObject = new JSONObject(string);
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                this.L.e("[ImmediateRequestMaker] ", e2);
                            }
                            return jSONObject;
                        } catch (Exception e3) {
                            e = e3;
                            Exception exc = e;
                            httpURLConnection = httpURLConnection3;
                            e = exc;
                            try {
                                this.L.e("[ImmediateRequestMaker] Received exception while making a immediate server request", e);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                        this.L.e("[ImmediateRequestMaker] ", e4);
                                    }
                                }
                                this.L.v("[ImmediateRequestMaker] Finished request");
                                return null;
                            } catch (Throwable th) {
                                th = th;
                                httpURLConnection2 = httpURLConnection;
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                        this.L.e("[ImmediateRequestMaker] ", e5);
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            httpURLConnection2 = httpURLConnection3;
                            th = th;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        bufferedReader = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (IOException e7) {
                this.L.e("[ImmediateRequestMaker] IOException while preparing remote config update request :[" + e7.toString() + "]");
                return null;
            }
        } catch (Exception e8) {
            e = e8;
            httpURLConnection = null;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(JSONObject jSONObject) {
        super.onPostExecute(jSONObject);
        this.L.v("[ImmediateRequestMaker] onPostExecute");
        InternalImmediateRequestCallback internalImmediateRequestCallback = this.callback;
        if (internalImmediateRequestCallback != null) {
            internalImmediateRequestCallback.callback(jSONObject);
        }
    }
}
