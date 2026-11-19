package androidx.tvprovider.media.tv;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvContract;
import android.net.Uri;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: classes4.dex */
public class ChannelLogoUtils {
    private static final int CONNECTION_TIMEOUT_MS_FOR_URLCONNECTION = 3000;
    private static final int READ_TIMEOUT_MS_FOR_URLCONNECTION = 10000;
    private static final String TAG = "ChannelLogoUtils";

    /* JADX WARN: Code duplicated, block: B:26:0x004f A[PHI: r1 r2
      0x004f: PHI (r1v7 java.net.URLConnection) = (r1v6 java.net.URLConnection), (r1v11 java.net.URLConnection) binds: [B:41:0x0082, B:25:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x004f: PHI (r2v5 android.graphics.Bitmap) = (r2v11 android.graphics.Bitmap), (r2v8 android.graphics.Bitmap) binds: [B:41:0x0082, B:25:0x004d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:44:0x0087  */
    /* JADX WARN: Code duplicated, block: B:57:0x009e  */
    /* JADX WARN: Code duplicated, block: B:59:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:? A[SYNTHETIC] */
    public static boolean storeChannelLogo(Context context, long j, Uri uri) throws Throwable {
        URLConnection urlConnection;
        InputStream inputStream;
        InputStream inputStreamOpenInputStream;
        String scheme = uri.normalizeScheme().getScheme();
        InputStream inputStream2 = null;
        bitmapDecodeStream = null;
        Bitmap bitmapDecodeStream = null;
        inputStream2 = null;
        try {
            if ("android.resource".equals(scheme) || IDataSource.SCHEME_FILE_TAG.equals(scheme) || FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
                inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                urlConnection = null;
            } else {
                urlConnection = getUrlConnection(uri.toString());
                try {
                    inputStreamOpenInputStream = urlConnection.getInputStream();
                } catch (IOException e) {
                    e = e;
                    inputStream = null;
                    try {
                        Log.i(TAG, "Failed to get logo from the URI: " + uri + ShellUtil.COMMAND_LINE_END, e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        if (urlConnection instanceof HttpURLConnection) {
                            ((HttpURLConnection) urlConnection).disconnect();
                        }
                        if (bitmapDecodeStream == null) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException unused2) {
                            }
                        }
                        if (!(urlConnection instanceof HttpURLConnection)) {
                            throw th;
                        }
                        ((HttpURLConnection) urlConnection).disconnect();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (!(urlConnection instanceof HttpURLConnection)) {
                        throw th;
                    }
                    ((HttpURLConnection) urlConnection).disconnect();
                    throw th;
                }
            }
            try {
                bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (urlConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) urlConnection).disconnect();
                }
            } catch (IOException e2) {
                inputStream = inputStreamOpenInputStream;
                e = e2;
                Log.i(TAG, "Failed to get logo from the URI: " + uri + ShellUtil.COMMAND_LINE_END, e);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (urlConnection instanceof HttpURLConnection) {
                }
                if (bitmapDecodeStream == null) {
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream2 = inputStreamOpenInputStream;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (!(urlConnection instanceof HttpURLConnection)) {
                    throw th;
                }
                ((HttpURLConnection) urlConnection).disconnect();
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            urlConnection = null;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            urlConnection = null;
        }
        return bitmapDecodeStream == null && storeChannelLogo(context, j, bitmapDecodeStream);
    }

    public static boolean storeChannelLogo(Context context, long j, Bitmap bitmap) {
        boolean zCompress = false;
        try {
            OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(TvContract.buildChannelLogoUri(j));
            try {
                zCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream);
                outputStreamOpenOutputStream.flush();
                if (outputStreamOpenOutputStream != null) {
                    outputStreamOpenOutputStream.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (outputStreamOpenOutputStream != null) {
                        try {
                            outputStreamOpenOutputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (SQLiteException | IOException e) {
            Log.i(TAG, "Failed to store the logo to the system content provider.\n", e);
        }
        return zCompress;
    }

    public static Bitmap loadChannelLogo(Context context, long j) {
        try {
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(TvContract.buildChannelLogoUri(j)));
        } catch (FileNotFoundException e) {
            Log.i(TAG, "Channel logo for channel (ID:" + j + ") not found.", e);
            return null;
        }
    }

    private static URLConnection getUrlConnection(String str) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        uRLConnectionOpenConnection.setConnectTimeout(3000);
        uRLConnectionOpenConnection.setReadTimeout(10000);
        return uRLConnectionOpenConnection;
    }

    @Deprecated
    public ChannelLogoUtils() {
    }
}
