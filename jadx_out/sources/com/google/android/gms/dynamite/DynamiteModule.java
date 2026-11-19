package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzb = null;
    private static String zzc = null;
    private static boolean zzd = false;
    private static int zze = -1;
    private static Boolean zzf;
    private static zzq zzk;
    private static zzr zzl;
    private final Context zzj;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01f8 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:102:0x0201 A[Catch: all -> 0x020a, TRY_ENTER, TryCatch #5 {, blocks: (B:29:0x00a7, B:31:0x00ad, B:32:0x00af, B:102:0x0201, B:103:0x0209), top: B:152:0x00a7 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x027f A[Catch: all -> 0x02c2, TryCatch #0 {all -> 0x02c2, blocks: (B:3:0x002f, B:7:0x0078, B:12:0x0080, B:15:0x0086, B:26:0x00a2, B:108:0x020e, B:109:0x0219, B:111:0x021b, B:113:0x021d, B:114:0x0225, B:132:0x027f, B:133:0x0293, B:116:0x0227, B:118:0x0245, B:120:0x0256, B:130:0x0276, B:131:0x027e, B:134:0x0294, B:135:0x02c1), top: B:146:0x002f, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x00d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:151:0x00a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:152:0x00a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:15:0x0086 A[Catch: all -> 0x02c2, TRY_LEAVE, TryCatch #0 {all -> 0x02c2, blocks: (B:3:0x002f, B:7:0x0078, B:12:0x0080, B:15:0x0086, B:26:0x00a2, B:108:0x020e, B:109:0x0219, B:111:0x021b, B:113:0x021d, B:114:0x0225, B:132:0x027f, B:133:0x0293, B:116:0x0227, B:118:0x0245, B:120:0x0256, B:130:0x0276, B:131:0x027e, B:134:0x0294, B:135:0x02c1), top: B:146:0x002f, inners: #4 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x008e  */
    /* JADX WARN: Code duplicated, block: B:19:0x0092  */
    /* JADX WARN: Code duplicated, block: B:22:0x0099  */
    /* JADX WARN: Code duplicated, block: B:25:0x00a0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x00ad A[Catch: all -> 0x020a, TryCatch #5 {, blocks: (B:29:0x00a7, B:31:0x00ad, B:32:0x00af, B:102:0x0201, B:103:0x0209), top: B:152:0x00a7 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00b2 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TRY_ENTER, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00b9 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00da A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TRY_ENTER, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0150 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x015c A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x017b A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0182 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x018a A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0199 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:82:0x01a4 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x01b4 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x01c9 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TRY_LEAVE, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:90:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:93:0x01df  */
    /* JADX WARN: Code duplicated, block: B:96:0x01e6 A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TRY_ENTER, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x01ef A[Catch: all -> 0x020d, LoadingException -> 0x021a, RemoteException -> 0x021c, TryCatch #7 {RemoteException -> 0x021c, LoadingException -> 0x021a, all -> 0x020d, blocks: (B:28:0x00a6, B:34:0x00b2, B:36:0x00b9, B:37:0x00d4, B:41:0x00da, B:43:0x00e2, B:45:0x00e6, B:46:0x00f3, B:53:0x0100, B:55:0x0105, B:57:0x012c, B:59:0x0134, B:60:0x013b, B:61:0x0143, B:56:0x0119, B:64:0x0146, B:65:0x0147, B:66:0x014f, B:67:0x0150, B:68:0x0158, B:71:0x015b, B:72:0x015c, B:74:0x017b, B:76:0x0182, B:78:0x018a, B:84:0x01c3, B:86:0x01c9, B:96:0x01e6, B:97:0x01ee, B:79:0x0199, B:80:0x01a1, B:82:0x01a4, B:83:0x01b4, B:98:0x01ef, B:99:0x01f7, B:100:0x01f8, B:101:0x0200, B:106:0x020c), top: B:153:0x00a6 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:132:0x027f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:36:0x00b9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:72:0x015c, please report this as an issue */
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        int i;
        Boolean bool;
        zzq zzqVarZzg;
        int iZze;
        IObjectWrapper iObjectWrapperZzh;
        Object objUnwrap;
        DynamiteModule dynamiteModule;
        zzn zznVar;
        Cursor cursor;
        zzr zzrVar;
        zzn zznVar2;
        boolean z;
        Boolean boolValueOf;
        IObjectWrapper iObjectWrapperZze;
        Cursor cursor2;
        ThreadLocal threadLocal = zzg;
        zzn zznVar3 = (zzn) threadLocal.get();
        zzn zznVar4 = new zzn(null);
        threadLocal.set(zznVar4);
        ThreadLocal threadLocal2 = zzh;
        Long l = (Long) threadLocal2.get();
        long jLongValue = l.longValue();
        try {
            threadLocal2.set(Long.valueOf(SystemClock.elapsedRealtime()));
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzi);
            Log.i("DynamiteModule", "Considering local module " + str + ":" + selectionResultSelectModule.localVersion + " and remote module " + str + ":" + selectionResultSelectModule.remoteVersion);
            int i2 = selectionResultSelectModule.selection;
            if (i2 != 0) {
                if (i2 != -1) {
                    if (i2 == 1 || selectionResultSelectModule.remoteVersion != 0) {
                        if (i2 == -1) {
                            DynamiteModule dynamiteModuleZzc = zzc(context, str);
                            if (jLongValue == 0) {
                                threadLocal2.remove();
                            } else {
                                threadLocal2.set(l);
                            }
                            cursor2 = zznVar4.zza;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            threadLocal.set(zznVar3);
                            return dynamiteModuleZzc;
                        }
                        if (i2 == 1) {
                            throw new LoadingException("VersionPolicy returned invalid code:" + i2, null);
                        }
                        try {
                            i = selectionResultSelectModule.remoteVersion;
                            try {
                                synchronized (DynamiteModule.class) {
                                    if (zzf(context)) {
                                        throw new LoadingException("Remote loading disabled", null);
                                    }
                                    bool = zzb;
                                }
                                if (bool != null) {
                                    throw new LoadingException("Failed to determine which loading route to use.", null);
                                }
                                if (bool.booleanValue()) {
                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                    synchronized (DynamiteModule.class) {
                                        zzrVar = zzl;
                                    }
                                    if (zzrVar != null) {
                                        throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                    }
                                    zznVar2 = (zzn) threadLocal.get();
                                    if (zznVar2 != null || zznVar2.zza == null) {
                                        throw new LoadingException("No result cursor", null);
                                    }
                                    Context applicationContext = context.getApplicationContext();
                                    Cursor cursor3 = zznVar2.zza;
                                    ObjectWrapper.wrap(null);
                                    synchronized (DynamiteModule.class) {
                                        z = zze >= 2;
                                        boolValueOf = Boolean.valueOf(z);
                                    }
                                    boolValueOf.getClass();
                                    if (z) {
                                        Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                        iObjectWrapperZze = zzrVar.zzf(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor3));
                                    } else {
                                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                        iObjectWrapperZze = zzrVar.zze(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor3));
                                    }
                                    Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZze);
                                    if (context2 == null) {
                                        throw new LoadingException("Failed to get module context", null);
                                    }
                                    dynamiteModule = new DynamiteModule(context2);
                                } else {
                                    Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                                    zzqVarZzg = zzg(context);
                                    if (zzqVarZzg != null) {
                                        throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                    }
                                    iZze = zzqVarZzg.zze();
                                    if (iZze >= 3) {
                                        zznVar = (zzn) threadLocal.get();
                                        if (zznVar != null) {
                                            throw new LoadingException("No cached result cursor holder", null);
                                        }
                                        iObjectWrapperZzh = zzqVarZzg.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zznVar.zza));
                                    } else if (iZze == 2) {
                                        Log.w("DynamiteModule", "IDynamite loader version = 2");
                                        iObjectWrapperZzh = zzqVarZzg.zzj(ObjectWrapper.wrap(context), str, i);
                                    } else {
                                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                        iObjectWrapperZzh = zzqVarZzg.zzh(ObjectWrapper.wrap(context), str, i);
                                    }
                                    objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                                    if (objUnwrap != null) {
                                        throw new LoadingException("Failed to load remote module.", null);
                                    }
                                    dynamiteModule = new DynamiteModule((Context) objUnwrap);
                                }
                                if (jLongValue == 0) {
                                    threadLocal2.remove();
                                } else {
                                    threadLocal2.set(l);
                                }
                                cursor = zznVar4.zza;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                threadLocal.set(zznVar3);
                                return dynamiteModule;
                            } catch (RemoteException e) {
                                throw new LoadingException("Failed to load remote module.", e, null);
                            } catch (LoadingException e2) {
                                throw e2;
                            } catch (Throwable th) {
                                CrashUtils.addDynamiteErrorToDropBox(context, th);
                                throw new LoadingException("Failed to load remote module.", th, null);
                            }
                        } catch (LoadingException e3) {
                            Log.w("DynamiteModule", "Failed to load remote module: " + e3.getMessage());
                            int i3 = selectionResultSelectModule.localVersion;
                            if (i3 == 0 || versionPolicy.selectModule(context, str, new zzo(i3, 0)).selection != -1) {
                                throw new LoadingException("Remote load failed. No local fallback found.", e3, null);
                            }
                            DynamiteModule dynamiteModuleZzc2 = zzc(context, str);
                            if (jLongValue == 0) {
                                zzh.remove();
                            } else {
                                zzh.set(l);
                            }
                            Cursor cursor4 = zznVar4.zza;
                            if (cursor4 != null) {
                                cursor4.close();
                            }
                            zzg.set(zznVar3);
                            return dynamiteModuleZzc2;
                        }
                    }
                } else if (selectionResultSelectModule.localVersion != 0) {
                    i2 = -1;
                    if (i2 == 1) {
                    }
                    if (i2 == -1) {
                        DynamiteModule dynamiteModuleZzc3 = zzc(context, str);
                        if (jLongValue == 0) {
                            threadLocal2.remove();
                        } else {
                            threadLocal2.set(l);
                        }
                        cursor2 = zznVar4.zza;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        threadLocal.set(zznVar3);
                        return dynamiteModuleZzc3;
                    }
                    if (i2 == 1) {
                        throw new LoadingException("VersionPolicy returned invalid code:" + i2, null);
                    }
                    i = selectionResultSelectModule.remoteVersion;
                    synchronized (DynamiteModule.class) {
                        if (zzf(context)) {
                            throw new LoadingException("Remote loading disabled", null);
                        }
                        bool = zzb;
                        if (bool != null) {
                            throw new LoadingException("Failed to determine which loading route to use.", null);
                        }
                        if (bool.booleanValue()) {
                            Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                            synchronized (DynamiteModule.class) {
                                zzrVar = zzl;
                                if (zzrVar != null) {
                                    throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                }
                                zznVar2 = (zzn) threadLocal.get();
                                if (zznVar2 != null) {
                                }
                                throw new LoadingException("No result cursor", null);
                            }
                        }
                        Log.i("DynamiteModule", "Selected remote version of " + str + ", version >= " + i);
                        zzqVarZzg = zzg(context);
                        if (zzqVarZzg != null) {
                            throw new LoadingException("Failed to create IDynamiteLoader.", null);
                        }
                        iZze = zzqVarZzg.zze();
                        if (iZze >= 3) {
                            zznVar = (zzn) threadLocal.get();
                            if (zznVar != null) {
                                throw new LoadingException("No cached result cursor holder", null);
                            }
                            iObjectWrapperZzh = zzqVarZzg.zzi(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zznVar.zza));
                        } else if (iZze == 2) {
                            Log.w("DynamiteModule", "IDynamite loader version = 2");
                            iObjectWrapperZzh = zzqVarZzg.zzj(ObjectWrapper.wrap(context), str, i);
                        } else {
                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                            iObjectWrapperZzh = zzqVarZzg.zzh(ObjectWrapper.wrap(context), str, i);
                        }
                        objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                        if (objUnwrap != null) {
                            throw new LoadingException("Failed to load remote module.", null);
                        }
                        dynamiteModule = new DynamiteModule((Context) objUnwrap);
                        if (jLongValue == 0) {
                            threadLocal2.remove();
                        } else {
                            threadLocal2.set(l);
                        }
                        cursor = zznVar4.zza;
                        if (cursor != null) {
                            cursor.close();
                        }
                        threadLocal.set(zznVar3);
                        return dynamiteModule;
                    }
                }
            }
            throw new LoadingException("No acceptable module " + str + " found. Local version is " + selectionResultSelectModule.localVersion + " and remote version is " + selectionResultSelectModule.remoteVersion + ".", null);
        } catch (Throwable th2) {
            if (jLongValue == 0) {
                zzh.remove();
            } else {
                zzh.set(l);
            }
            Cursor cursor5 = zznVar4.zza;
            if (cursor5 != null) {
                cursor5.close();
            }
            zzg.set(zznVar3);
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:94:0x016a A[Catch: all -> 0x01c6, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x01c6, blocks: (B:3:0x0002, B:61:0x00dd, B:63:0x00e3, B:68:0x0104, B:90:0x015c, B:94:0x016a, B:115:0x01bf, B:116:0x01c2, B:110:0x01b7, B:66:0x00e9, B:119:0x01c5, B:4:0x0003, B:7:0x0009, B:8:0x0025, B:59:0x00da, B:19:0x0046, B:41:0x009c, B:44:0x009f, B:52:0x00b8, B:60:0x00dc, B:58:0x00be), top: B:129:0x0002, inners: #1, #12 }] */
    public static int zza(Context context, String str, boolean z) {
        Throwable th;
        RemoteException e;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzb;
                Cursor cursor2 = null;
                int iZzf = 0;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else if (classLoader != null) {
                                try {
                                    zzd(classLoader);
                                } catch (LoadingException unused) {
                                }
                                bool = Boolean.TRUE;
                            } else {
                                if (!zzf(context)) {
                                    return 0;
                                }
                                if (zzd || Boolean.TRUE.equals(null)) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        int iZzb = zzb(context, str, z, true);
                                        String str2 = zzc;
                                        if (str2 != null && !str2.isEmpty()) {
                                            ClassLoader classLoaderZza = zzb.zza();
                                            if (classLoaderZza == null) {
                                                if (Build.VERSION.SDK_INT >= 29) {
                                                    Chip$$ExternalSyntheticApiModelOutline0.m547m();
                                                    String str3 = zzc;
                                                    Preconditions.checkNotNull(str3);
                                                    classLoaderZza = Chip$$ExternalSyntheticApiModelOutline0.m(str3, ClassLoader.getSystemClassLoader());
                                                } else {
                                                    String str4 = zzc;
                                                    Preconditions.checkNotNull(str4);
                                                    classLoaderZza = new zzc(str4, ClassLoader.getSystemClassLoader());
                                                }
                                            }
                                            zzd(classLoaderZza);
                                            declaredField.set(null, classLoaderZza);
                                            zzb = Boolean.TRUE;
                                            return iZzb;
                                        }
                                        return iZzb;
                                    } catch (LoadingException unused2) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    }
                                }
                            }
                            zzb = bool;
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e2.toString());
                        bool = Boolean.FALSE;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzb(context, str, z, false);
                    } catch (LoadingException e3) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e3.getMessage());
                        return 0;
                    }
                }
                zzq zzqVarZzg = zzg(context);
                if (zzqVarZzg != null) {
                    try {
                        try {
                            int iZze = zzqVarZzg.zze();
                            if (iZze >= 3) {
                                zzn zznVar = (zzn) zzg.get();
                                if (zznVar == null || (cursor = zznVar.zza) == null) {
                                    Cursor cursor3 = (Cursor) ObjectWrapper.unwrap(zzqVarZzg.zzk(ObjectWrapper.wrap(context), str, z, ((Long) zzh.get()).longValue()));
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.moveToFirst()) {
                                                int i = cursor3.getInt(0);
                                                cursor2 = (i <= 0 || !zze(cursor3)) ? cursor3 : null;
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                                iZzf = i;
                                            } else {
                                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                                if (cursor3 != null) {
                                                    cursor3.close();
                                                }
                                            }
                                        } catch (RemoteException e4) {
                                            e = e4;
                                            cursor2 = cursor3;
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e.getMessage());
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor2 = cursor3;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            throw th;
                                        }
                                    } else {
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                        if (cursor3 != null) {
                                            cursor3.close();
                                        }
                                    }
                                } else {
                                    iZzf = cursor.getInt(0);
                                }
                            } else if (iZze == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                iZzf = zzqVarZzg.zzg(ObjectWrapper.wrap(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                iZzf = zzqVarZzg.zzf(ObjectWrapper.wrap(context), str, z);
                            }
                        } catch (RemoteException e5) {
                            e = e5;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                return iZzf;
            }
        } catch (Throwable th4) {
            CrashUtils.addDynamiteErrorToDropBox(context, th4);
            throw th4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00c9  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    private static int zzb(Context context, String str, boolean z, boolean z2) throws Throwable {
        Throwable th;
        Exception e;
        ?? r0 = 0;
        ?? r1 = 0;
        ?? r2 = 0;
        ?? r3 = 0;
        try {
            try {
                boolean z3 = true;
                Cursor cursorQuery = context.getContentResolver().query(new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartTime", String.valueOf(((Long) zzh.get()).longValue())).build(), null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            boolean z4 = false;
                            int i = cursorQuery.getInt(0);
                            if (i > 0) {
                                synchronized (DynamiteModule.class) {
                                    zzc = cursorQuery.getString(2);
                                    int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                    if (columnIndex >= 0) {
                                        zze = cursorQuery.getInt(columnIndex);
                                    }
                                    int columnIndex2 = cursorQuery.getColumnIndex("disableStandaloneDynamiteLoader2");
                                    if (columnIndex2 >= 0) {
                                        if (cursorQuery.getInt(columnIndex2) == 0) {
                                            z3 = false;
                                        }
                                        zzd = z3;
                                        z4 = z3;
                                    }
                                }
                                if (zze(cursorQuery)) {
                                    cursorQuery = null;
                                }
                            }
                            if (z2 && z4) {
                                throw new LoadingException("forcing fallback to container DynamiteLoader impl", r2 == true ? 1 : 0);
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return i;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        if (e instanceof LoadingException) {
                            throw e;
                        }
                        throw new LoadingException("V2 version check failed", e, r1 == true ? 1 : 0);
                    }
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new LoadingException("Failed to connect to dynamite module ContentResolver.", r3 == true ? 1 : 0);
            } catch (Throwable th2) {
                th = th2;
                r0 = context;
                if (r0 != 0) {
                    r0.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
            if (r0 != 0) {
                r0.close();
            }
            throw th;
        }
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzl = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzpVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zznVar = (zzn) zzg.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    private static boolean zzf(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzf)) {
            return true;
        }
        boolean z = false;
        if (zzf == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                z = true;
            }
            Boolean boolValueOf = Boolean.valueOf(z);
            zzf = boolValueOf;
            boolValueOf.getClass();
            if (z && providerInfoResolveContentProvider != null && providerInfoResolveContentProvider.applicationInfo != null && (providerInfoResolveContentProvider.applicationInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzq zzg(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzk;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzk = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    public Context getModuleContext() {
        return this.zzj;
    }

    public IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e, null);
        }
    }
}
