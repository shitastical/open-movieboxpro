package jp.wasabeef.glide.transformations.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.renderscript.Allocation;
import androidx.renderscript.Element;
import androidx.renderscript.RenderScript;
import androidx.renderscript.ScriptIntrinsicBlur;

/* JADX INFO: loaded from: classes4.dex */
public class SupportRSBlur {
    /* JADX WARN: Code duplicated, block: B:29:0x0064  */
    /* JADX WARN: Code duplicated, block: B:31:0x0068  */
    /* JADX WARN: Code duplicated, block: B:32:0x006c  */
    /* JADX WARN: Code duplicated, block: B:34:0x0071  */
    /* JADX WARN: Code duplicated, block: B:36:0x0076  */
    /* JADX WARN: Code duplicated, block: B:38:0x007b  */
    public static Bitmap blur(Context context, Bitmap bitmap, int i) throws Throwable {
        ScriptIntrinsicBlur scriptIntrinsicBlur;
        Allocation allocationCreateFromBitmap;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreateTyped;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = null;
        try {
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate2 = RenderScript.create(context);
            try {
                scriptIntrinsicBlurCreate2.setMessageHandler(new RenderScript.RSMessageHandler());
                allocationCreateFromBitmap = Allocation.createFromBitmap(scriptIntrinsicBlurCreate2, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
                try {
                    scriptIntrinsicBlurCreateTyped = Allocation.createTyped(scriptIntrinsicBlurCreate2, allocationCreateFromBitmap.getType());
                    try {
                        scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(scriptIntrinsicBlurCreate2, Element.U8_4(scriptIntrinsicBlurCreate2));
                        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
                        scriptIntrinsicBlurCreate.setRadius(i);
                        scriptIntrinsicBlurCreate.forEach(scriptIntrinsicBlurCreateTyped);
                        scriptIntrinsicBlurCreateTyped.copyTo(bitmap);
                        if (scriptIntrinsicBlurCreate2 != null) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                RenderScript.releaseAllContexts();
                            } else {
                                scriptIntrinsicBlurCreate2.destroy();
                            }
                        }
                        if (allocationCreateFromBitmap != null) {
                            allocationCreateFromBitmap.destroy();
                        }
                        if (scriptIntrinsicBlurCreateTyped != null) {
                            scriptIntrinsicBlurCreateTyped.destroy();
                        }
                        if (scriptIntrinsicBlurCreate != null) {
                            scriptIntrinsicBlurCreate.destroy();
                        }
                        return bitmap;
                    } catch (Throwable th) {
                        th = th;
                        ScriptIntrinsicBlur scriptIntrinsicBlur2 = scriptIntrinsicBlurCreate;
                        scriptIntrinsicBlurCreate = scriptIntrinsicBlurCreate2;
                        scriptIntrinsicBlur = scriptIntrinsicBlur2;
                        if (scriptIntrinsicBlurCreate != null) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                RenderScript.releaseAllContexts();
                            } else {
                                scriptIntrinsicBlurCreate.destroy();
                            }
                        }
                        if (allocationCreateFromBitmap != null) {
                            allocationCreateFromBitmap.destroy();
                        }
                        if (scriptIntrinsicBlurCreateTyped != null) {
                            scriptIntrinsicBlurCreateTyped.destroy();
                        }
                        if (scriptIntrinsicBlur != null) {
                            scriptIntrinsicBlur.destroy();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    scriptIntrinsicBlurCreateTyped = null;
                    scriptIntrinsicBlurCreate = scriptIntrinsicBlurCreate2;
                    scriptIntrinsicBlur = scriptIntrinsicBlurCreateTyped;
                    if (scriptIntrinsicBlurCreate != null) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            RenderScript.releaseAllContexts();
                        } else {
                            scriptIntrinsicBlurCreate.destroy();
                        }
                    }
                    if (allocationCreateFromBitmap != null) {
                        allocationCreateFromBitmap.destroy();
                    }
                    if (scriptIntrinsicBlurCreateTyped != null) {
                        scriptIntrinsicBlurCreateTyped.destroy();
                    }
                    if (scriptIntrinsicBlur != null) {
                        scriptIntrinsicBlur.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                allocationCreateFromBitmap = null;
                scriptIntrinsicBlurCreateTyped = null;
            }
        } catch (Throwable th4) {
            th = th4;
            scriptIntrinsicBlur = null;
            allocationCreateFromBitmap = null;
            scriptIntrinsicBlurCreateTyped = null;
        }
    }
}
