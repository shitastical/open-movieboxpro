package ly.count.android.sdk;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes6.dex */
public class UtilsNetworking {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    protected static String urlEncodeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    protected static String encodedArrayBuilder(String[] strArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < strArr.length; i++) {
            sb.append(Typography.quote);
            sb.append(strArr[i]);
            sb.append(Typography.quote);
            if (i < strArr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected static String urlDecodeString(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    protected static String sha256Hash(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = str.getBytes("UTF-8");
            messageDigest.update(bytes, 0, bytes.length);
            return bytesToHex(messageDigest.digest());
        } catch (Throwable th) {
            Countly.sharedInstance().L.e("Cannot tamper-protect params", th);
            return null;
        }
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & Ascii.SI];
        }
        return new String(cArr).toLowerCase();
    }

    static boolean isValidURL(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
