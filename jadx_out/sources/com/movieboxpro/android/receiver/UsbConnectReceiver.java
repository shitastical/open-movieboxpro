package com.movieboxpro.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.movieboxpro.android.LiveDataSet;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.model.ExternalStorage;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.androidtv.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.videolan.libvlc.util.AndroidUtil;

/* JADX INFO: compiled from: UsbConnectReceiver.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J#\u0010\u001d\u001a\u00020\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001f\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J\u0006\u00109\u001a\u00020%J\b\u0010:\u001a\u00020%H\u0007J\b\u0010;\u001a\u00020%H\u0007J\b\u0010<\u001a\u00020%H\u0007J\u0010\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020'H\u0002J\u000e\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u0002J\u000e\u0010@\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0007J\u0010\u0010B\u001a\u00020%2\u0006\u0010A\u001a\u00020\u0007H\u0002J\u0006\u0010C\u001a\u00020%J\u001c\u0010D\u001a\b\u0012\u0004\u0012\u0002020\u00182\f\u0010E\u001a\b\u0012\u0004\u0012\u0002060\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R2\u0010\u0017\u001a&\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00070\u0007 \u0019*\u0012\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\u000b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R \u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R \u00101\u001a\b\u0012\u0004\u0012\u0002020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010.\"\u0004\b4\u00100R \u00105\u001a\b\u0012\u0004\u0012\u0002060+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010.\"\u0004\b8\u00100R\u0012\u0010F\u001a\u00020GX\u0096\u0005¢\u0006\u0006\u001a\u0004\bH\u0010I¨\u0006J"}, d2 = {"Lcom/movieboxpro/android/receiver/UsbConnectReceiver;", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/LifecycleObserver;", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "()V", "ACTION_USB_PERMISSION", "", "registered", "", "typeBL", "", "EXTERNAL_PUBLIC_DIRECTORY", "getEXTERNAL_PUBLIC_DIRECTORY", "()Ljava/lang/String;", "INTERNAL_DOWNLOAD_PATH", "getINTERNAL_DOWNLOAD_PATH", "mountBL", "", "getMountBL", "()[Ljava/lang/String;", "[Ljava/lang/String;", "deviceWL", "typeWL", "", "kotlin.jvm.PlatformType", "mountWL", "getStorageTag", "uuid", "startsWith", SerializerHandler.TYPE_ARRAY, "text", "([Ljava/lang/String;Ljava/lang/String;)Z", "containsName", "", "list", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "devices", "Lcom/movieboxpro/android/LiveDataSet;", "Landroid/hardware/usb/UsbDevice;", "getDevices", "()Lcom/movieboxpro/android/LiveDataSet;", "setDevices", "(Lcom/movieboxpro/android/LiveDataSet;)V", "storages", "Lcom/movieboxpro/android/model/ExternalStorage;", "getStorages", "setStorages", "storagesVolumes", "Landroid/os/storage/StorageVolume;", "getStoragesVolumes", "setStoragesVolumes", "init", "register", "onRefresh", "unregister", "checkNewStorages", "ctx", "getExternalStorage", "getUsbDisplayName", FileDownloadService.PARAMS_KEY_PATH, "writeSize", "refreshStorage", "getStorageList", "storageVolumes", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UsbConnectReceiver extends BroadcastReceiver implements LifecycleObserver, CoroutineScope {
    public static final String ACTION_USB_PERMISSION = "com.movieboxpro.androidtv.USB_PERMISSION";
    private static final String EXTERNAL_PUBLIC_DIRECTORY;
    public static final UsbConnectReceiver INSTANCE;
    private static final String INTERNAL_DOWNLOAD_PATH;
    private static final String[] deviceWL;
    private static LiveDataSet<UsbDevice> devices;
    private static final String[] mountBL;
    private static final String[] mountWL;
    private static boolean registered;
    private static LiveDataSet<ExternalStorage> storages;
    private static LiveDataSet<StorageVolume> storagesVolumes;
    private static final List<String> typeBL;
    private static final List<String> typeWL;
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public final void init() {
    }

    private UsbConnectReceiver() {
    }

    static {
        String path;
        UsbConnectReceiver usbConnectReceiver = new UsbConnectReceiver();
        INSTANCE = usbConnectReceiver;
        typeBL = CollectionsKt.listOf("tmpfs");
        String path2 = Environment.getExternalStorageDirectory().getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
        EXTERNAL_PUBLIC_DIRECTORY = path2;
        File externalFilesDir = App.getContext().getExternalFilesDir("Download");
        if (externalFilesDir == null || (path = externalFilesDir.getPath()) == null) {
            path = Environment.getExternalStorageDirectory().getPath() + File.separator + "Android/data" + File.separator + BuildConfig.APPLICATION_ID + File.separator + "files" + File.separator + "Download";
        }
        INTERNAL_DOWNLOAD_PATH = path;
        mountBL = new String[]{path2, "/mnt/secure", "/mnt/shell", "/mnt/asec", "/mnt/nand", "/mnt/runtime", "/mnt/obb", "/mnt/media_rw/extSdCard", "/mnt/media_rw/sdcard", "/storage/emulated", "/var/run/arc"};
        deviceWL = new String[]{"/dev/block/vold", "/dev/fuse", "/mnt/media_rw", "passthrough"};
        typeWL = Arrays.asList("vfat", "exfat", "sdcardfs", "fuse", "ntfs", "fat32", "ext3", "ext4", "esdfs");
        mountWL = new String[]{"/mnt", "/Removable", "/storage"};
        devices = new LiveDataSet<>();
        storages = new LiveDataSet<>();
        storagesVolumes = new LiveDataSet<>();
        BuildersKt__Builders_commonKt.launch$default(usbConnectReceiver, null, null, new AnonymousClass1(null), 3, null);
    }

    public final String getEXTERNAL_PUBLIC_DIRECTORY() {
        return EXTERNAL_PUBLIC_DIRECTORY;
    }

    public final String getINTERNAL_DOWNLOAD_PATH() {
        return INTERNAL_DOWNLOAD_PATH;
    }

    public final String[] getMountBL() {
        return mountBL;
    }

    private final String getStorageTag(String uuid) {
        if (!AndroidUtil.isMarshMallowOrLater) {
            return null;
        }
        try {
            StorageManager storageManager = (StorageManager) App.getContext().getSystemService(StorageManager.class);
            Class<?> cls = storageManager.getClass();
            Method declaredMethod = cls.getDeclaredMethod("findVolumeByUuid", uuid.getClass());
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(storageManager, uuid);
            Method declaredMethod2 = cls.getDeclaredMethod("getBestVolumeDescription", Class.forName("android.os.storage.VolumeInfo"));
            declaredMethod2.setAccessible(true);
            Object objInvoke2 = declaredMethod2.invoke(storageManager, objInvoke);
            Intrinsics.checkNotNull(objInvoke2, "null cannot be cast to non-null type kotlin.String");
            return (String) objInvoke2;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode == -1514214344) {
                if (!action.equals("android.intent.action.MEDIA_MOUNTED") || intent.getData() == null) {
                    return;
                }
                INSTANCE.refreshStorage();
                return;
            }
            if (iHashCode != -963871873) {
                if (iHashCode != -625887599 || !action.equals("android.intent.action.MEDIA_EJECT")) {
                    return;
                }
            } else if (!action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                return;
            }
            if (intent.getData() != null) {
                INSTANCE.refreshStorage();
            }
        }
    }

    public final LiveDataSet<UsbDevice> getDevices() {
        return devices;
    }

    public final void setDevices(LiveDataSet<UsbDevice> liveDataSet) {
        Intrinsics.checkNotNullParameter(liveDataSet, "<set-?>");
        devices = liveDataSet;
    }

    public final LiveDataSet<ExternalStorage> getStorages() {
        return storages;
    }

    public final void setStorages(LiveDataSet<ExternalStorage> liveDataSet) {
        Intrinsics.checkNotNullParameter(liveDataSet, "<set-?>");
        storages = liveDataSet;
    }

    public final LiveDataSet<StorageVolume> getStoragesVolumes() {
        return storagesVolumes;
    }

    public final void setStoragesVolumes(LiveDataSet<StorageVolume> liveDataSet) {
        Intrinsics.checkNotNullParameter(liveDataSet, "<set-?>");
        storagesVolumes = liveDataSet;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.receiver.UsbConnectReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: UsbConnectReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.receiver.UsbConnectReceiver$1", f = "UsbConnectReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ProcessLifecycleOwner.INSTANCE.get().getLifecycle().addObserver(UsbConnectReceiver.INSTANCE);
            return Unit.INSTANCE;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void register() {
        if (registered) {
            return;
        }
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme(IDataSource.SCHEME_FILE_TAG);
        new IntentFilter("android.hardware.usb.action.USB_DEVICE_ATTACHED").addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        context.registerReceiver(this, intentFilter);
        registered = true;
        checkNewStorages(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public final void onRefresh() {
        refreshStorage();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void unregister() {
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (registered) {
            try {
                context.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
        }
        registered = false;
        devices.clear();
    }

    private final List<String> getExternalStorage() throws Throwable {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/mounts"));
            try {
                String line = bufferedReader2.readLine();
                while (line != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                    String strNextToken = stringTokenizer.nextToken();
                    String strNextToken2 = stringTokenizer.nextToken();
                    Intrinsics.checkNotNullExpressionValue(strNextToken2, "nextToken(...)");
                    String strReplace = new Regex("\\\\040").replace(strNextToken2, " ");
                    String strNextToken3 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
                    if (arrayList.contains(strReplace) || CollectionsKt.contains(typeBL, strNextToken3) || startsWith(mountBL, strReplace)) {
                        line = bufferedReader2.readLine();
                    } else {
                        String[] strArr = deviceWL;
                        Intrinsics.checkNotNull(strNextToken);
                        if (startsWith(strArr, strNextToken) && (typeWL.contains(strNextToken3) || startsWith(mountWL, strReplace))) {
                            int iContainsName = containsName(arrayList, StringsKt.substringBeforeLast$default(strReplace, '/', (String) null, 2, (Object) null));
                            if (iContainsName > -1) {
                                arrayList.remove(iContainsName);
                            }
                            arrayList.add(strReplace);
                        }
                        line = bufferedReader2.readLine();
                    }
                }
                bufferedReader2.close();
            } catch (IOException unused) {
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        arrayList.remove(EXTERNAL_PUBLIC_DIRECTORY);
        return arrayList;
    }

    public final String getUsbDisplayName(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        List listSplit$default = StringsKt.split$default((CharSequence) path, new String[]{"/"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() >= 3) {
            if (((CharSequence) listSplit$default.get(0)).length() == 0) {
                return (String) listSplit$default.get(2);
            }
            return (String) listSplit$default.get(1);
        }
        return "External Storage";
    }

    private final void writeSize(String path) throws IOException {
        File file = new File(path, "temp.data");
        file.createNewFile();
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = App.getContext().getContentResolver().openFileDescriptor(Uri.fromFile(file), "rw");
        try {
            Intrinsics.checkNotNull(parcelFileDescriptorOpenFileDescriptor);
            Os.posix_fallocate(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor(), 0L, 4294867296L);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (th instanceof ErrnoException) {
                ErrnoException errnoException = th;
                if (errnoException.errno == OsConstants.ENOSYS || errnoException.errno == OsConstants.ENOTSUP) {
                    Log.d("writeSize", "fallocate() not supported; falling back to ftruncate()");
                    try {
                        Intrinsics.checkNotNull(parcelFileDescriptorOpenFileDescriptor);
                        Os.ftruncate(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor(), 4294867296L);
                    } catch (Throwable th2) {
                        Log.d("writeSize", "It can't pre-allocate length(4294867296) on the sdk version(" + Build.VERSION.SDK_INT + "), because of " + th2);
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                return;
            }
            Integer.valueOf(Log.d("writeSize", "It can't pre-allocate length(4294867296) on the sdk version(" + Build.VERSION.SDK_INT + "), because of " + th));
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.receiver.UsbConnectReceiver$refreshStorage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: UsbConnectReceiver.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.receiver.UsbConnectReceiver$refreshStorage$1", f = "UsbConnectReceiver.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C01201(Continuation<? super C01201> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01201 c01201 = new C01201(continuation);
            c01201.L$0 = obj;
            return c01201;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            List list;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                final StorageManager storageManager = (StorageManager) ContextCompat.getSystemService(App.getContext(), StorageManager.class);
                if (Build.VERSION.SDK_INT >= 24) {
                    Intrinsics.checkNotNull(storageManager);
                    list = storageManager.getStorageVolumes();
                    Intrinsics.checkNotNull(list);
                } else {
                    Method declaredMethod = StorageManager.class.getDeclaredMethod("getVolumeList", null);
                    declaredMethod.setAccessible(true);
                    Object objInvoke = declaredMethod.invoke(coroutineScope, null);
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Array<android.os.storage.StorageVolume>");
                    list = ArraysKt.toList((StorageVolume[]) objInvoke);
                }
                UsbConnectReceiver.INSTANCE.getStoragesVolumes().postValue(new ArrayList(list));
                Context context = App.getContext();
                BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.movieboxpro.android.receiver.UsbConnectReceiver.refreshStorage.1.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
                        List list2;
                        Intrinsics.checkNotNullParameter(context2, "context");
                        Intrinsics.checkNotNullParameter(intent, "intent");
                        if (Build.VERSION.SDK_INT >= 24) {
                            StorageManager storageManager2 = storageManager;
                            Intrinsics.checkNotNull(storageManager2);
                            list2 = storageManager2.getStorageVolumes();
                            Intrinsics.checkNotNull(list2);
                        } else {
                            Method declaredMethod2 = StorageManager.class.getDeclaredMethod("getVolumeList", null);
                            declaredMethod2.setAccessible(true);
                            Object objInvoke2 = declaredMethod2.invoke(this, null);
                            Intrinsics.checkNotNull(objInvoke2, "null cannot be cast to non-null type kotlin.Array<android.os.storage.StorageVolume>");
                            list2 = ArraysKt.toList((StorageVolume[]) objInvoke2);
                        }
                        UsbConnectReceiver.INSTANCE.getStorages().postValue(UsbConnectReceiver.INSTANCE.getStorageList(list2));
                        UsbConnectReceiver.INSTANCE.getStoragesVolumes().postValue(new ArrayList(list2));
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
                intentFilter.addAction("android.intent.action.MEDIA_CHECKING");
                intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
                intentFilter.addAction("android.intent.action.MEDIA_EJECT");
                intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
                intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
                intentFilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
                intentFilter.addDataScheme(IDataSource.SCHEME_FILE_TAG);
                Unit unit = Unit.INSTANCE;
                ContextCompat.registerReceiver(context, broadcastReceiver, intentFilter, 4);
                UsbConnectReceiver.INSTANCE.getStorages().postValue(UsbConnectReceiver.INSTANCE.getStorageList(list));
            } catch (Exception e) {
                Log.d("UsbConnectReceiver", "refreshStorage: " + e);
            }
            return Unit.INSTANCE;
        }
    }

    public final void refreshStorage() {
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getDefault(), null, new C01201(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ExternalStorage> getStorageList(List<StorageVolume> storageVolumes) {
        Object next;
        String string;
        String desc;
        ArrayList<ExternalStorage> arrayList = new ArrayList();
        File[] externalFilesDirs = App.getContext().getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
        Intrinsics.checkNotNull(externalFilesDirs);
        for (File file : externalFilesDirs) {
            if (file != null) {
                ExternalStorage externalStorage = new ExternalStorage();
                externalStorage.setPath(file.getPath());
                if (!new File(file.getPath()).exists()) {
                    String path = file.getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                    if (StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null)) {
                        externalStorage.setDesc("Internal Storage");
                    } else {
                        UsbConnectReceiver usbConnectReceiver = INSTANCE;
                        String path2 = file.getPath();
                        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                        externalStorage.setDesc(usbConnectReceiver.getUsbDisplayName(path2) + " (unmount)");
                    }
                } else {
                    for (DownloadFile downloadFile : DownloadFileHelper.INSTANCE.getInstance().getDownloadFiles()) {
                        new File(downloadFile.getPath(), downloadFile.getFileName()).length();
                    }
                    externalStorage.setSpaceDesc(FileUtils.byte2FitMemorySize(FileUtils.getAvailableSpace(file.getPath())) + " Free, " + FileUtils.byte2FitMemorySize(FileUtils.getTotalSpace(file.getPath())) + " Total");
                    String path3 = file.getPath();
                    Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
                    if (StringsKt.startsWith$default(path3, "/storage/emulated/0", false, 2, (Object) null)) {
                        externalStorage.setDesc("Internal Storage");
                    } else {
                        UsbConnectReceiver usbConnectReceiver2 = INSTANCE;
                        String path4 = file.getPath();
                        Intrinsics.checkNotNullExpressionValue(path4, "getPath(...)");
                        externalStorage.setDesc(usbConnectReceiver2.getUsbDisplayName(path4));
                    }
                }
                externalStorage.setUri(Uri.fromFile(file));
                arrayList.add(externalStorage);
            }
        }
        for (ExternalStorage externalStorage2 : arrayList) {
            Iterator<T> it = storageVolumes.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                string = App$$ExternalSyntheticApiModelOutline0.m(next).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                desc = externalStorage2.getDesc();
                Intrinsics.checkNotNullExpressionValue(desc, "getDesc(...)");
            } while (!StringsKt.contains$default((CharSequence) string, (CharSequence) desc, false, 2, (Object) null));
            StorageVolume storageVolumeM = App$$ExternalSyntheticApiModelOutline0.m(next);
            if (storageVolumeM != null) {
                externalStorage2.setStorageVolume(storageVolumeM);
            }
        }
        return arrayList;
    }

    private final boolean startsWith(String[] array, String text) {
        for (String str : array) {
            if (StringsKt.startsWith$default(text, str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private final int containsName(List<String> list, String text) {
        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (StringsKt.endsWith$default(listIterator.previous(), text, false, 2, (Object) null)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    private final void checkNewStorages(Context ctx) {
        UsbManager usbManager = (UsbManager) ContextCompat.getSystemService(ctx, UsbManager.class);
        if (usbManager == null) {
            return;
        }
        devices.add(new ArrayList(usbManager.getDeviceList().values()));
        refreshStorage();
    }
}
