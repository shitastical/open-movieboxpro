package me.jahnen.libaums.core;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.driver.BlockDeviceDriverFactory;
import me.jahnen.libaums.core.driver.scsi.commands.sense.MediaNotInserted;
import me.jahnen.libaums.core.partition.Partition;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableEntry;
import me.jahnen.libaums.core.partition.PartitionTableFactory;
import me.jahnen.libaums.core.usb.UsbCommunication;
import me.jahnen.libaums.core.usb.UsbCommunicationFactory;

/* JADX INFO: compiled from: UsbMassStorageDevice.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u001aH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lme/jahnen/libaums/core/UsbMassStorageDevice;", "", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "inEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "outEndpoint", "(Landroid/hardware/usb/UsbManager;Landroid/hardware/usb/UsbDevice;Landroid/hardware/usb/UsbInterface;Landroid/hardware/usb/UsbEndpoint;Landroid/hardware/usb/UsbEndpoint;)V", "inited", "", "partitions", "", "Lme/jahnen/libaums/core/partition/Partition;", "getPartitions", "()Ljava/util/List;", "setPartitions", "(Ljava/util/List;)V", "usbCommunication", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "getUsbDevice", "()Landroid/hardware/usb/UsbDevice;", "close", "", "init", "initPartitions", "partitionTable", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "setupDevice", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbMassStorageDevice {
    private static final int INTERFACE_PROTOCOL = 80;
    private static final int INTERFACE_SUBCLASS = 6;
    private final UsbEndpoint inEndpoint;
    private boolean inited;
    private final UsbEndpoint outEndpoint;
    public List<Partition> partitions;
    private UsbCommunication usbCommunication;
    private final UsbDevice usbDevice;
    private final UsbInterface usbInterface;
    private final UsbManager usbManager;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "UsbMassStorageDevice";

    public /* synthetic */ UsbMassStorageDevice(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2, DefaultConstructorMarker defaultConstructorMarker) {
        this(usbManager, usbDevice, usbInterface, usbEndpoint, usbEndpoint2);
    }

    @JvmStatic
    public static final List<UsbMassStorageDevice> getMassStorageDevices(UsbDevice usbDevice, Context context) {
        return INSTANCE.getMassStorageDevices(usbDevice, context);
    }

    @JvmStatic
    public static final UsbMassStorageDevice[] getMassStorageDevices(Context context) {
        return INSTANCE.getMassStorageDevices(context);
    }

    private UsbMassStorageDevice(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        this.usbManager = usbManager;
        this.usbDevice = usbDevice;
        this.usbInterface = usbInterface;
        this.inEndpoint = usbEndpoint;
        this.outEndpoint = usbEndpoint2;
    }

    public final UsbDevice getUsbDevice() {
        return this.usbDevice;
    }

    public final List<Partition> getPartitions() {
        List<Partition> list = this.partitions;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("partitions");
        return null;
    }

    public final void setPartitions(List<Partition> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.partitions = list;
    }

    public final void init() throws IOException {
        if (this.usbManager.hasPermission(this.usbDevice)) {
            setupDevice();
            this.inited = true;
        } else {
            throw new IllegalStateException("Missing permission to access usb device: " + this.usbDevice);
        }
    }

    private final void setupDevice() throws IOException {
        UsbCommunication usbCommunication;
        List<Partition> listInitPartitions;
        UsbCommunication usbCommunicationCreateUsbCommunication = UsbCommunicationFactory.INSTANCE.createUsbCommunication(this.usbManager, this.usbDevice, this.usbInterface, this.outEndpoint, this.inEndpoint);
        this.usbCommunication = usbCommunicationCreateUsbCommunication;
        byte[] bArr = new byte[1];
        if (usbCommunicationCreateUsbCommunication == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usbCommunication");
            usbCommunication = null;
        } else {
            usbCommunication = usbCommunicationCreateUsbCommunication;
        }
        usbCommunication.controlTransfer(Opcodes.IF_ICMPLT, 254, 0, this.usbInterface.getId(), bArr, 1);
        Log.i(TAG, "MAX LUN " + ((int) bArr[0]));
        IntRange intRange = new IntRange(0, bArr[0]);
        ArrayList<BlockDeviceDriver> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            BlockDeviceDriverFactory blockDeviceDriverFactory = BlockDeviceDriverFactory.INSTANCE;
            UsbCommunication usbCommunication2 = this.usbCommunication;
            if (usbCommunication2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usbCommunication");
                usbCommunication2 = null;
            }
            arrayList.add(blockDeviceDriverFactory.createBlockDevice(usbCommunication2, (byte) iNextInt));
        }
        ArrayList arrayList2 = new ArrayList();
        for (BlockDeviceDriver blockDeviceDriver : arrayList) {
            try {
                blockDeviceDriver.init();
                listInitPartitions = initPartitions(PartitionTableFactory.INSTANCE.createPartitionTable(blockDeviceDriver), blockDeviceDriver);
            } catch (MediaNotInserted unused) {
                listInitPartitions = null;
            }
            if (listInitPartitions != null) {
                arrayList2.add(listInitPartitions);
            }
        }
        setPartitions(CollectionsKt.flatten(arrayList2));
    }

    private final List<Partition> initPartitions(PartitionTable partitionTable, BlockDeviceDriver blockDevice) throws IOException {
        List<PartitionTableEntry> partitionTableEntries = partitionTable.getPartitionTableEntries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = partitionTableEntries.iterator();
        while (it.hasNext()) {
            Partition partitionCreatePartition = Partition.INSTANCE.createPartition((PartitionTableEntry) it.next(), blockDevice);
            if (partitionCreatePartition != null) {
                arrayList.add(partitionCreatePartition);
            }
        }
        return arrayList;
    }

    public final void close() {
        if (this.inited) {
            UsbCommunication usbCommunication = this.usbCommunication;
            if (usbCommunication == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usbCommunication");
                usbCommunication = null;
            }
            usbCommunication.close();
            this.inited = false;
        }
    }

    /* JADX INFO: compiled from: UsbMassStorageDevice.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000eJ\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lme/jahnen/libaums/core/UsbMassStorageDevice$Companion;", "", "()V", "INTERFACE_PROTOCOL", "", "INTERFACE_SUBCLASS", "TAG", "", "kotlin.jvm.PlatformType", "getMassStorageDevices", "", "Lme/jahnen/libaums/core/UsbMassStorageDevice;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)[Lme/jahnen/libaums/core/UsbMassStorageDevice;", "", "Landroid/hardware/usb/UsbDevice;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final List<UsbMassStorageDevice> getMassStorageDevices(UsbDevice usbDevice, Context context) {
            Intrinsics.checkNotNullParameter(usbDevice, "<this>");
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("usb");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.usb.UsbManager");
            UsbManager usbManager = (UsbManager) systemService;
            IntRange intRangeUntil = RangesKt.until(0, usbDevice.getInterfaceCount());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                arrayList.add(usbDevice.getInterface(((IntIterator) it).nextInt()));
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                UsbInterface usbInterface = (UsbInterface) obj;
                if (usbInterface.getInterfaceClass() == 8 && usbInterface.getInterfaceSubclass() == 6 && usbInterface.getInterfaceProtocol() == 80) {
                    arrayList2.add(obj);
                }
            }
            ArrayList<UsbInterface> arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (UsbInterface usbInterface2 : arrayList3) {
                Log.i(UsbMassStorageDevice.TAG, "Found usb interface: " + usbInterface2);
                int endpointCount = usbInterface2.getEndpointCount();
                if (endpointCount != 2) {
                    Log.w(UsbMassStorageDevice.TAG, "Interface endpoint count != 2");
                }
                UsbMassStorageDevice usbMassStorageDevice = null;
                UsbEndpoint usbEndpoint = null;
                UsbEndpoint usbEndpoint2 = null;
                for (int i = 0; i < endpointCount; i++) {
                    UsbEndpoint endpoint = usbInterface2.getEndpoint(i);
                    Log.i(UsbMassStorageDevice.TAG, "Found usb endpoint: " + endpoint);
                    if (endpoint.getType() == 2) {
                        if (endpoint.getDirection() == 0) {
                            usbEndpoint2 = endpoint;
                        } else {
                            usbEndpoint = endpoint;
                        }
                    }
                }
                if (usbEndpoint2 == null || usbEndpoint == null) {
                    String str = UsbMassStorageDevice.TAG;
                    StringBuilder sb = new StringBuilder("Not all needed endpoints found. In: ");
                    sb.append(usbEndpoint2 != null);
                    sb.append(", Out: ");
                    sb.append(usbEndpoint2 != null);
                    Log.e(str, sb.toString());
                } else {
                    Intrinsics.checkNotNullExpressionValue(usbInterface2, "usbInterface");
                    usbMassStorageDevice = new UsbMassStorageDevice(usbManager, usbDevice, usbInterface2, usbEndpoint, usbEndpoint2, null);
                }
                arrayList4.add(usbMassStorageDevice);
            }
            return CollectionsKt.filterNotNull(arrayList4);
        }

        @JvmStatic
        public final UsbMassStorageDevice[] getMassStorageDevices(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("usb");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.usb.UsbManager");
            HashMap<String, UsbDevice> deviceList = ((UsbManager) systemService).getDeviceList();
            Intrinsics.checkNotNullExpressionValue(deviceList, "usbManager.deviceList");
            HashMap<String, UsbDevice> map = deviceList;
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, UsbDevice> entry : map.entrySet()) {
                UsbDevice device = entry.getValue();
                Log.i(UsbMassStorageDevice.TAG, "found usb device: " + entry);
                Companion companion = UsbMassStorageDevice.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(device, "device");
                arrayList.add(companion.getMassStorageDevices(device, context));
            }
            return (UsbMassStorageDevice[]) CollectionsKt.flatten(arrayList).toArray(new UsbMassStorageDevice[0]);
        }
    }
}
