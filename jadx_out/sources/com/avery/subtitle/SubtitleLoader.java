package com.avery.subtitle;

import android.text.TextUtils;
import android.util.Log;
import com.avery.subtitle.exception.FatalParsingException;
import com.avery.subtitle.format.FormatASS;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.format.FormatSTL;
import com.avery.subtitle.model.TimedTextObject;
import com.avery.subtitle.runtime.AppTaskExecutor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class SubtitleLoader {
    private static final String TAG = "SubtitleLoader";

    public interface Callback {
        void onError(Exception exc);

        void onSuccess(TimedTextObject timedTextObject);
    }

    private SubtitleLoader() {
        throw new AssertionError("No instance for you.");
    }

    public static void loadSubtitle(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            loadFromRemoteAsync(str, callback);
        } else {
            loadFromLocalAsync(str, callback);
        }
    }

    private static void loadFromRemoteAsync(final String str, final Callback callback) {
        AppTaskExecutor.deskIO().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final TimedTextObject timedTextObjectLoadFromRemote = SubtitleLoader.loadFromRemote(str);
                    if (callback != null) {
                        AppTaskExecutor.mainThread().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onSuccess(timedTextObjectLoadFromRemote);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (callback != null) {
                        AppTaskExecutor.mainThread().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onError(e);
                            }
                        });
                    }
                }
            }
        });
    }

    private static void loadFromLocalAsync(final String str, final Callback callback) {
        AppTaskExecutor.deskIO().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final TimedTextObject timedTextObjectLoadFromLocal = SubtitleLoader.loadFromLocal(str);
                    if (callback != null) {
                        AppTaskExecutor.mainThread().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onSuccess(timedTextObjectLoadFromLocal);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (callback != null) {
                        AppTaskExecutor.mainThread().execute(new Runnable() { // from class: com.avery.subtitle.SubtitleLoader.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onError(e);
                            }
                        });
                    }
                }
            }
        });
    }

    public TimedTextObject loadSubtitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                return loadFromLocal(str);
            }
            return loadFromRemote(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TimedTextObject loadFromRemote(String str) throws IOException, FatalParsingException {
        Log.d(TAG, "parseRemote: remoteSubtitlePath = " + str);
        URL url = new URL(str);
        return loadAndParse(url.openStream(), url.getPath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TimedTextObject loadFromLocal(String str) throws IOException, FatalParsingException {
        Log.d(TAG, "parseLocal: localSubtitlePath = " + str);
        File file = new File(str);
        return loadAndParse(new FileInputStream(file), file.getPath());
    }

    private static TimedTextObject loadAndParse(InputStream inputStream, String str) throws IOException, FatalParsingException {
        String strSubstring = str.substring(str.lastIndexOf("/") + 1);
        String strSubstring2 = strSubstring.substring(strSubstring.lastIndexOf("."));
        Log.d(TAG, "parse: name = " + strSubstring + ", ext = " + strSubstring2);
        if (".srt".equalsIgnoreCase(strSubstring2)) {
            return new FormatSRT().parseFile(strSubstring, inputStream);
        }
        if (".ass".equalsIgnoreCase(strSubstring2)) {
            return new FormatASS().parseFile(strSubstring, inputStream);
        }
        if (".stl".equalsIgnoreCase(strSubstring2)) {
            return new FormatSTL().parseFile(strSubstring, inputStream);
        }
        if (".ttml".equalsIgnoreCase(strSubstring2)) {
            return new FormatSTL().parseFile(strSubstring, inputStream);
        }
        return new FormatSRT().parseFile(strSubstring, inputStream);
    }
}
