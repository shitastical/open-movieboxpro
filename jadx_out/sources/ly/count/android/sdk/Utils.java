package ly.count.android.sdk;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class Utils {
    private static final ExecutorService bg = Executors.newSingleThreadExecutor();

    public static Future<?> runInBackground(Runnable runnable) {
        return bg.submit(runnable);
    }

    public static <T> Future<T> runInBackground(Callable<T> callable) {
        return bg.submit(callable);
    }

    static <T> String join(Collection<T> collection, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    static String joinCountlyStore(Collection<String> collection, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            sb.append(it.next());
            i++;
            if (i < collection.size()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean API(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static byte[] readStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Countly.sharedInstance().L.e("Couldn't read stream: " + e);
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
                inputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    static String inputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (sb.length() > 0) {
                    sb.append(ShellUtil.COMMAND_LINE_END);
                }
                sb.append(line);
            } catch (IOException e) {
                Countly.sharedInstance().L.e("", e);
            }
        }
        return sb.toString();
    }

    public static String safeRandomVal() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr = new byte[6];
        new SecureRandom().nextBytes(bArr);
        return Base64.encodeToString(bArr, 2) + jCurrentTimeMillis;
    }

    static boolean removeUnsupportedDataTypes(Map<String, Object> map) {
        boolean z = false;
        if (map == null) {
            return false;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            String key = next.getKey();
            Object value = next.getValue();
            if (key == null || key.isEmpty() || (!(value instanceof String) && !(value instanceof Integer) && !(value instanceof Double) && !(value instanceof Boolean))) {
                it.remove();
                z = true;
            }
        }
        if (z) {
            Countly.sharedInstance().L.w("[Utils] Unsupported data types were removed from provided segmentation");
        }
        return z;
    }

    static void removeReservedKeysFromSegmentation(Map<String, Object> map, String[] strArr, String str, ModuleLog moduleLog) {
        if (map == null) {
            return;
        }
        for (String str2 : strArr) {
            if (map.containsKey(str2)) {
                moduleLog.w(str + " provided segmentation contains protected key [" + str2 + "]");
                map.remove(str2);
            }
        }
    }

    protected static synchronized void fillInSegmentation(Map<String, Object> map, Map<String, String> map2, Map<String, Integer> map3, Map<String, Double> map4, Map<String, Boolean> map5, Map<String, Object> map6) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                map3.put(key, (Integer) value);
            } else if (value instanceof Double) {
                map4.put(key, (Double) value);
            } else if (value instanceof String) {
                map2.put(key, (String) value);
            } else if (value instanceof Boolean) {
                map5.put(key, (Boolean) value);
            } else if (map6 != null) {
                map6.put(key, value);
            }
        }
    }

    static void fillJSONIfValuesNotEmpty(JSONObject jSONObject, String... strArr) {
        try {
            if (strArr.length <= 0 || strArr.length % 2 != 0) {
                return;
            }
            for (int i = 0; i < strArr.length; i += 2) {
                String str = strArr[i];
                String str2 = strArr[i + 1];
                if (str2 != null && str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            }
        } catch (JSONException unused) {
        }
    }

    static boolean isDeviceTablet(Context context) {
        return context != null && (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    static boolean isDeviceTv(Context context) {
        UiModeManager uiModeManager;
        return (context == null || (uiModeManager = (UiModeManager) context.getSystemService("uimode")) == null || uiModeManager.getCurrentModeType() != 4) ? false : true;
    }

    static void truncateSegmentationValues(Map<String, Object> map, int i, String str, ModuleLog moduleLog) {
        if (map == null) {
            return;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext() && map.size() > i) {
            moduleLog.w(str + ", Value exceeded the maximum segmentation count key:[" + it.next().getKey() + "]");
            it.remove();
        }
    }
}
