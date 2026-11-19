package tv.cjump.jni;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes6.dex */
public class NativeBitmapFactory {
    static Field nativeIntField = null;
    static boolean nativeLibLoaded = false;
    static boolean notLoadAgain = false;

    private static native Bitmap createBitmap(int i, int i2, int i3, boolean z);

    private static native Bitmap createBitmap19(int i, int i2, int i3, boolean z);

    private static native boolean init();

    private static native boolean release();

    public static boolean isInNativeAlloc() {
        return nativeLibLoaded && nativeIntField != null;
    }

    public static void loadLibs() {
        if (notLoadAgain) {
            return;
        }
        if (!DeviceUtils.isRealARMArch() && !DeviceUtils.isRealX86Arch()) {
            notLoadAgain = true;
            nativeLibLoaded = false;
            return;
        }
        if (nativeLibLoaded) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT < 23) {
                System.loadLibrary("ndkbitmap");
                nativeLibLoaded = true;
            } else {
                notLoadAgain = true;
                nativeLibLoaded = false;
            }
        } catch (Error e) {
            e.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        } catch (Exception e2) {
            e2.printStackTrace();
            notLoadAgain = true;
            nativeLibLoaded = false;
        }
        if (nativeLibLoaded) {
            if (!init()) {
                release();
                notLoadAgain = true;
                nativeLibLoaded = false;
            } else {
                initField();
                if (!testLib()) {
                    release();
                    notLoadAgain = true;
                    nativeLibLoaded = false;
                }
            }
        }
        Log.e("NativeBitmapFactory", "loaded" + nativeLibLoaded);
    }

    public static void releaseLibs() {
        if (nativeLibLoaded) {
            release();
        }
        nativeIntField = null;
        nativeLibLoaded = false;
    }

    static void initField() {
        try {
            Field declaredField = Bitmap.Config.class.getDeclaredField("nativeInt");
            nativeIntField = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            nativeIntField = null;
            e.printStackTrace();
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x006b A[ExcHandler: Exception -> 0x006b, PHI: r1
      0x006b: PHI (r1v2 android.graphics.Bitmap) = (r1v1 android.graphics.Bitmap), (r1v5 android.graphics.Bitmap) binds: [B:36:0x0009, B:37:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:31:0x0083 A[DONT_GENERATE] */
    private static boolean testLib() {
        if (nativeIntField == null) {
            return false;
        }
        Bitmap bitmapCreateNativeBitmap = null;
        try {
            bitmapCreateNativeBitmap = createNativeBitmap(2, 2, Bitmap.Config.ARGB_8888, true);
            boolean zIsPremultiplied = bitmapCreateNativeBitmap != null && bitmapCreateNativeBitmap.getWidth() == 2 && bitmapCreateNativeBitmap.getHeight() == 2;
            if (zIsPremultiplied) {
                if (!bitmapCreateNativeBitmap.isPremultiplied()) {
                    bitmapCreateNativeBitmap.setPremultiplied(true);
                }
                Canvas canvas = new Canvas(bitmapCreateNativeBitmap);
                Paint paint = new Paint();
                paint.setColor(SupportMenu.CATEGORY_MASK);
                paint.setTextSize(20.0f);
                canvas.drawRect(0.0f, 0.0f, bitmapCreateNativeBitmap.getWidth(), bitmapCreateNativeBitmap.getHeight(), paint);
                canvas.drawText("TestLib", 0.0f, 0.0f, paint);
                zIsPremultiplied = bitmapCreateNativeBitmap.isPremultiplied();
            }
            return zIsPremultiplied;
        } catch (Exception e) {
            Log.e("NativeBitmapFactory", "exception:" + e.toString());
            return false;
        } catch (Error unused) {
            return false;
        } finally {
            if (bitmapCreateNativeBitmap != null) {
                bitmapCreateNativeBitmap.recycle();
            }
        }
    }

    public static int getNativeConfig(Bitmap.Config config) {
        try {
            Field field = nativeIntField;
            if (field == null) {
                return 0;
            }
            return field.getInt(config);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap createBitmap(int i, int i2, Bitmap.Config config) {
        return createBitmap(i, i2, config, config.equals(Bitmap.Config.ARGB_8888));
    }

    public static void recycle(Bitmap bitmap) {
        bitmap.recycle();
    }

    public static Bitmap createBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        if (!nativeLibLoaded || nativeIntField == null) {
            return Bitmap.createBitmap(i, i2, config);
        }
        return createNativeBitmap(i, i2, config, z);
    }

    private static Bitmap createNativeBitmap(int i, int i2, Bitmap.Config config, boolean z) {
        return createBitmap(i, i2, getNativeConfig(config), z);
    }
}
