package me.jahnen.libaums.core.fs.fat32;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.AbstractUsbFile;
import me.jahnen.libaums.core.fs.UsbFile;

/* JADX INFO: compiled from: FatDirectory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 S2\u00020\u0001:\u0001SB;\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\rJ\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020-H\u0002J\b\u00104\u001a\u000202H\u0016J\u0010\u00105\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020 H\u0016J\u0010\u00106\u001a\u0002072\u0006\u0010\"\u001a\u00020 H\u0016J\b\u00108\u001a\u00020\u0018H\u0016J\b\u00109\u001a\u000202H\u0016J\b\u0010:\u001a\u000202H\u0016J\b\u0010;\u001a\u000202H\u0002J\b\u0010<\u001a\u00020\u0018H\u0016J\b\u0010=\u001a\u00020\u0018H\u0016J\u0013\u0010>\u001a\b\u0012\u0004\u0012\u00020 0?H\u0016¢\u0006\u0002\u0010@J\u0013\u0010A\u001a\b\u0012\u0004\u0012\u00020B0?H\u0016¢\u0006\u0002\u0010CJ\u001d\u0010D\u001a\u0002022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020BH\u0000¢\u0006\u0002\bFJ\u0010\u0010G\u001a\u0002022\u0006\u0010E\u001a\u00020BH\u0016J\u0018\u0010H\u001a\u0002022\u0006\u0010I\u001a\u00020\u00182\u0006\u0010E\u001a\u00020JH\u0016J\b\u0010K\u001a\u000202H\u0002J\u0017\u0010L\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0002\bMJ\u001f\u0010N\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u000b2\u0006\u0010!\u001a\u00020 H\u0000¢\u0006\u0002\bOJ\r\u0010P\u001a\u000202H\u0000¢\u0006\u0002\bQJ\u0018\u0010P\u001a\u0002022\u0006\u0010I\u001a\u00020\u00182\u0006\u0010R\u001a\u00020JH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00188V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000b0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0000X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010/\u001a\u0004\u0018\u00010 2\b\u0010.\u001a\u0004\u0018\u00010 @BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010$¨\u0006T"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "Lme/jahnen/libaums/core/fs/AbstractUsbFile;", "fs", "Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "fat", "Lme/jahnen/libaums/core/fs/fat32/FAT;", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "entry", "Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;", "parent", "(Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem;Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/fs/fat32/FAT;Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;Lme/jahnen/libaums/core/fs/fat32/FatDirectory;)V", "chain", "Lme/jahnen/libaums/core/fs/fat32/ClusterChain;", "entries", "", "hasBeenInited", "", "isDirectory", "()Z", "isRoot", "<anonymous parameter 0>", "", "length", "getLength", "()J", "setLength", "(J)V", "lfnMap", "", "", "newName", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getParent", "()Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "setParent", "(Lme/jahnen/libaums/core/fs/fat32/FatDirectory;)V", "shortNameMap", "Lme/jahnen/libaums/core/fs/fat32/ShortName;", "Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "<set-?>", "volumeLabel", "getVolumeLabel$libaums_release", "addEntry", "", "lfnEntry", "close", "createDirectory", "createFile", "Lme/jahnen/libaums/core/fs/fat32/FatFile;", "createdAt", "delete", "flush", "init", "lastAccessed", "lastModified", "list", "", "()[Ljava/lang/String;", "listFiles", "Lme/jahnen/libaums/core/fs/UsbFile;", "()[Lme/jahnen/libaums/core/fs/UsbFile;", "move", FirebaseAnalytics.Param.DESTINATION, "move$libaums_release", "moveTo", "read", "offset", "Ljava/nio/ByteBuffer;", "readEntries", "removeEntry", "removeEntry$libaums_release", "renameEntry", "renameEntry$libaums_release", "write", "write$libaums_release", FirebaseAnalytics.Param.SOURCE, "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FatDirectory extends AbstractUsbFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "FatDirectory";
    private final BlockDeviceDriver blockDevice;
    private final Fat32BootSector bootSector;
    private ClusterChain chain;
    private List<FatLfnDirectoryEntry> entries;
    private FatLfnDirectoryEntry entry;
    private final FAT fat;
    private final Fat32FileSystem fs;
    private boolean hasBeenInited;
    private final Map<String, FatLfnDirectoryEntry> lfnMap;
    private FatDirectory parent;
    private final Map<ShortName, FatDirectoryEntry> shortNameMap;
    private String volumeLabel;

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public boolean isDirectory() {
        return true;
    }

    public FatDirectory(Fat32FileSystem fs, BlockDeviceDriver blockDevice, FAT fat, Fat32BootSector bootSector, FatLfnDirectoryEntry fatLfnDirectoryEntry, FatDirectory fatDirectory) {
        Intrinsics.checkNotNullParameter(fs, "fs");
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(fat, "fat");
        Intrinsics.checkNotNullParameter(bootSector, "bootSector");
        this.fs = fs;
        this.blockDevice = blockDevice;
        this.fat = fat;
        this.bootSector = bootSector;
        this.entry = fatLfnDirectoryEntry;
        this.parent = fatDirectory;
        this.lfnMap = new HashMap();
        this.shortNameMap = new HashMap();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public FatDirectory getParent() {
        return this.parent;
    }

    public void setParent(FatDirectory fatDirectory) {
        this.parent = fatDirectory;
    }

    /* JADX INFO: renamed from: getVolumeLabel$libaums_release, reason: from getter */
    public final String getVolumeLabel() {
        return this.volumeLabel;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public boolean isRoot() {
        return this.entry == null;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long getLength() {
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void setLength(long j) {
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public String getName() {
        FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
        if (fatLfnDirectoryEntry == null) {
            return "/";
        }
        Intrinsics.checkNotNull(fatLfnDirectoryEntry);
        return fatLfnDirectoryEntry.getName$libaums_release();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void setName(String newName) throws IOException {
        Intrinsics.checkNotNullParameter(newName, "newName");
        if (isRoot()) {
            throw new IllegalStateException("Cannot rename root dir!".toString());
        }
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.renameEntry$libaums_release(this.entry, newName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void init() throws IOException {
        if (this.chain == null) {
            FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
            Intrinsics.checkNotNull(fatLfnDirectoryEntry);
            this.chain = new ClusterChain(fatLfnDirectoryEntry.getStartCluster(), this.blockDevice, this.fat, this.bootSector);
        }
        if (this.entries == null) {
            this.entries = new ArrayList();
        }
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        if (list.size() == 0 && !this.hasBeenInited) {
            readEntries();
        }
        this.hasBeenInited = true;
    }

    private final void readEntries() throws IOException {
        FatDirectoryEntry fatDirectoryEntry;
        ClusterChain clusterChain = this.chain;
        ClusterChain clusterChain2 = null;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        ByteBuffer buffer = ByteBuffer.allocate((int) clusterChain.getLength$libaums_release());
        ClusterChain clusterChain3 = this.chain;
        if (clusterChain3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
        } else {
            clusterChain2 = clusterChain3;
        }
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
        clusterChain2.read$libaums_release(0L, buffer);
        ArrayList arrayList = new ArrayList();
        buffer.flip();
        while (buffer.remaining() > 0 && (fatDirectoryEntry = FatDirectoryEntry.INSTANCE.read(buffer)) != null) {
            if (fatDirectoryEntry.isLfnEntry()) {
                arrayList.add(fatDirectoryEntry);
            } else if (fatDirectoryEntry.isVolumeLabel()) {
                if (!isRoot()) {
                    Log.w(TAG, "volume label in non root dir!");
                }
                this.volumeLabel = fatDirectoryEntry.getVolumeLabel();
                String str = TAG;
                StringBuilder sb = new StringBuilder("volume label: ");
                String str2 = this.volumeLabel;
                Intrinsics.checkNotNull(str2);
                sb.append(str2);
                Log.d(str, sb.toString());
            } else if (fatDirectoryEntry.isDeleted()) {
                arrayList.clear();
            } else {
                addEntry(FatLfnDirectoryEntry.INSTANCE.read(fatDirectoryEntry, arrayList), fatDirectoryEntry);
                arrayList.clear();
            }
        }
    }

    private final void addEntry(FatLfnDirectoryEntry lfnEntry, FatDirectoryEntry entry) {
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        list.add(lfnEntry);
        Map<String, FatLfnDirectoryEntry> map = this.lfnMap;
        String name$libaums_release = lfnEntry.getName$libaums_release();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name$libaums_release.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        map.put(lowerCase, lfnEntry);
        Map<ShortName, FatDirectoryEntry> map2 = this.shortNameMap;
        ShortName shortName = entry.getShortName();
        Intrinsics.checkNotNull(shortName);
        map2.put(shortName, entry);
    }

    public final void removeEntry$libaums_release(FatLfnDirectoryEntry lfnEntry) {
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        TypeIntrinsics.asMutableCollection(list).remove(lfnEntry);
        Map<String, FatLfnDirectoryEntry> map = this.lfnMap;
        Intrinsics.checkNotNull(lfnEntry);
        String name$libaums_release = lfnEntry.getName$libaums_release();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name$libaums_release.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        map.remove(lowerCase);
        Map<ShortName, FatDirectoryEntry> map2 = this.shortNameMap;
        TypeIntrinsics.asMutableMap(map2).remove(lfnEntry.getActualEntry().getShortName());
    }

    public final void renameEntry$libaums_release(FatLfnDirectoryEntry lfnEntry, String newName) throws IOException {
        Intrinsics.checkNotNullParameter(newName, "newName");
        Intrinsics.checkNotNull(lfnEntry);
        if (Intrinsics.areEqual(lfnEntry.getName$libaums_release(), newName)) {
            return;
        }
        removeEntry$libaums_release(lfnEntry);
        lfnEntry.setName(newName, ShortNameGenerator.INSTANCE.generateShortName(newName, this.shortNameMap.keySet()));
        addEntry(lfnEntry, lfnEntry.getActualEntry());
        write$libaums_release();
    }

    public final void write$libaums_release() throws IOException {
        init();
        int entryCount = 0;
        boolean z = isRoot() && this.volumeLabel != null;
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        Iterator<FatLfnDirectoryEntry> it = list.iterator();
        while (it.hasNext()) {
            entryCount += it.next().getEntryCount();
        }
        if (z) {
            entryCount++;
        }
        long j = entryCount * 32;
        ClusterChain clusterChain = this.chain;
        ClusterChain clusterChain2 = null;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.setLength$libaums_release(j);
        ClusterChain clusterChain3 = this.chain;
        if (clusterChain3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain3 = null;
        }
        ByteBuffer buffer = ByteBuffer.allocate((int) clusterChain3.getLength$libaums_release());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        if (z) {
            FatDirectoryEntry.Companion companion = FatDirectoryEntry.INSTANCE;
            String str = this.volumeLabel;
            Intrinsics.checkNotNull(str);
            FatDirectoryEntry fatDirectoryEntryCreateVolumeLabel = companion.createVolumeLabel(str);
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            fatDirectoryEntryCreateVolumeLabel.serialize(buffer);
        }
        List<FatLfnDirectoryEntry> list2 = this.entries;
        Intrinsics.checkNotNull(list2);
        for (FatLfnDirectoryEntry fatLfnDirectoryEntry : list2) {
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            fatLfnDirectoryEntry.serialize(buffer);
        }
        if (j % ((long) this.bootSector.getBytesPerCluster()) != 0 || j == 0) {
            buffer.put(new byte[buffer.remaining()]);
        }
        buffer.flip();
        ClusterChain clusterChain4 = this.chain;
        if (clusterChain4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
        } else {
            clusterChain2 = clusterChain4;
        }
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
        clusterChain2.write$libaums_release(0L, buffer);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public FatFile createFile(String name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        Map<String, FatLfnDirectoryEntry> map = this.lfnMap;
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        if (map.containsKey(lowerCase)) {
            throw new IOException("Item already exists!");
        }
        init();
        ShortName shortNameGenerateShortName = ShortNameGenerator.INSTANCE.generateShortName(name, this.shortNameMap.keySet());
        FatLfnDirectoryEntry fatLfnDirectoryEntry = new FatLfnDirectoryEntry(name, shortNameGenerateShortName);
        fatLfnDirectoryEntry.setStartCluster(this.fat.alloc$libaums_release(new Long[0], 1)[0].longValue());
        Log.d(TAG, "adding entry: " + fatLfnDirectoryEntry + " with short name: " + shortNameGenerateShortName);
        addEntry(fatLfnDirectoryEntry, fatLfnDirectoryEntry.getActualEntry());
        write$libaums_release();
        FatFile fatFile = new FatFile(this.blockDevice, this.fat, this.bootSector, fatLfnDirectoryEntry, this);
        this.fs.getFileCache$libaums_release().put(fatFile.getAbsolutePath(), fatFile);
        return fatFile;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public FatDirectory createDirectory(String name) throws IOException {
        long startCluster;
        Intrinsics.checkNotNullParameter(name, "name");
        Map<String, FatLfnDirectoryEntry> map = this.lfnMap;
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        if (map.containsKey(lowerCase)) {
            throw new IOException("Item already exists!");
        }
        init();
        ShortName shortNameGenerateShortName = ShortNameGenerator.INSTANCE.generateShortName(name, this.shortNameMap.keySet());
        FatLfnDirectoryEntry fatLfnDirectoryEntry = new FatLfnDirectoryEntry(name, shortNameGenerateShortName);
        fatLfnDirectoryEntry.setDirectory();
        long jLongValue = this.fat.alloc$libaums_release(new Long[0], 1)[0].longValue();
        fatLfnDirectoryEntry.setStartCluster(jLongValue);
        Log.d(TAG, "adding entry: " + fatLfnDirectoryEntry + " with short name: " + shortNameGenerateShortName);
        addEntry(fatLfnDirectoryEntry, fatLfnDirectoryEntry.getActualEntry());
        write$libaums_release();
        FatDirectory fatDirectory = new FatDirectory(this.fs, this.blockDevice, this.fat, this.bootSector, fatLfnDirectoryEntry, this);
        fatDirectory.hasBeenInited = true;
        fatDirectory.entries = new ArrayList();
        FatLfnDirectoryEntry fatLfnDirectoryEntry2 = new FatLfnDirectoryEntry((String) null, new ShortName(".", ""));
        fatLfnDirectoryEntry2.setDirectory();
        fatLfnDirectoryEntry2.setStartCluster(jLongValue);
        FatLfnDirectoryEntry.INSTANCE.copyDateTime(fatLfnDirectoryEntry, fatLfnDirectoryEntry2);
        fatDirectory.addEntry(fatLfnDirectoryEntry2, fatLfnDirectoryEntry2.getActualEntry());
        FatLfnDirectoryEntry fatLfnDirectoryEntry3 = new FatLfnDirectoryEntry((String) null, new ShortName("..", ""));
        fatLfnDirectoryEntry3.setDirectory();
        if (isRoot()) {
            startCluster = 0;
        } else {
            FatLfnDirectoryEntry fatLfnDirectoryEntry4 = this.entry;
            Intrinsics.checkNotNull(fatLfnDirectoryEntry4);
            startCluster = fatLfnDirectoryEntry4.getStartCluster();
        }
        fatLfnDirectoryEntry3.setStartCluster(startCluster);
        FatLfnDirectoryEntry.INSTANCE.copyDateTime(fatLfnDirectoryEntry, fatLfnDirectoryEntry3);
        fatDirectory.addEntry(fatLfnDirectoryEntry3, fatLfnDirectoryEntry3.getActualEntry());
        fatDirectory.write$libaums_release();
        this.fs.getFileCache$libaums_release().put(fatDirectory.getAbsolutePath(), fatDirectory);
        return fatDirectory;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long createdAt() {
        if (isRoot()) {
            throw new IllegalStateException("root dir!".toString());
        }
        FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry);
        return fatLfnDirectoryEntry.getActualEntry().getCreatedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long lastModified() {
        if (isRoot()) {
            throw new IllegalStateException("root dir!".toString());
        }
        FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry);
        return fatLfnDirectoryEntry.getActualEntry().getLastModifiedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long lastAccessed() {
        if (isRoot()) {
            throw new IllegalStateException("root dir!".toString());
        }
        FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry);
        return fatLfnDirectoryEntry.getActualEntry().getLastAccessedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public String[] list() throws IOException {
        init();
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        ArrayList arrayList = new ArrayList(list.size());
        List<FatLfnDirectoryEntry> list2 = this.entries;
        Intrinsics.checkNotNull(list2);
        Iterator<FatLfnDirectoryEntry> it = list2.iterator();
        while (it.hasNext()) {
            String name$libaums_release = it.next().getName$libaums_release();
            if (!Intrinsics.areEqual(name$libaums_release, ".") && !Intrinsics.areEqual(name$libaums_release, "..")) {
                arrayList.add(name$libaums_release);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public UsbFile[] listFiles() throws IOException {
        UsbFile fatDirectory;
        init();
        List<FatLfnDirectoryEntry> list = this.entries;
        Intrinsics.checkNotNull(list);
        ArrayList arrayList = new ArrayList(list.size());
        List<FatLfnDirectoryEntry> list2 = this.entries;
        Intrinsics.checkNotNull(list2);
        for (FatLfnDirectoryEntry fatLfnDirectoryEntry : list2) {
            String name$libaums_release = fatLfnDirectoryEntry.getName$libaums_release();
            if (!Intrinsics.areEqual(name$libaums_release, ".") && !Intrinsics.areEqual(name$libaums_release, "..")) {
                String str = isRoot() ? "/" + fatLfnDirectoryEntry.getName$libaums_release() : getAbsolutePath() + '/' + fatLfnDirectoryEntry.getName$libaums_release();
                if (this.fs.getFileCache$libaums_release().get(str) != null) {
                    fatDirectory = this.fs.getFileCache$libaums_release().get(str);
                    Intrinsics.checkNotNull(fatDirectory);
                } else {
                    fatDirectory = fatLfnDirectoryEntry.isDirectory() ? new FatDirectory(this.fs, this.blockDevice, this.fat, this.bootSector, fatLfnDirectoryEntry, this) : new FatFile(this.blockDevice, this.fat, this.bootSector, fatLfnDirectoryEntry, this);
                }
                UsbFile usbFile = fatDirectory;
                Intrinsics.checkNotNullExpressionValue(usbFile, "when {\n                f…ntry, this)\n            }");
                this.fs.getFileCache$libaums_release().put(str, usbFile);
                arrayList.add(usbFile);
            }
        }
        return (UsbFile[]) arrayList.toArray(new UsbFile[0]);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void read(long offset, ByteBuffer destination) throws IOException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void write(long offset, ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void flush() throws IOException {
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw new UnsupportedOperationException("This is a directory!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void moveTo(UsbFile destination) throws IOException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (isRoot()) {
            throw new IllegalStateException("cannot move root dir!".toString());
        }
        if (!destination.isDirectory()) {
            throw new IllegalStateException("destination cannot be a file!".toString());
        }
        if (!(destination instanceof FatDirectory)) {
            throw new IllegalStateException("cannot move between different filesystems!".toString());
        }
        FatDirectory fatDirectory = (FatDirectory) destination;
        Map<String, FatLfnDirectoryEntry> map = fatDirectory.lfnMap;
        FatLfnDirectoryEntry fatLfnDirectoryEntry = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry);
        String name$libaums_release = fatLfnDirectoryEntry.getName$libaums_release();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name$libaums_release.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        if (map.containsKey(lowerCase)) {
            throw new IOException("item already exists in destination!");
        }
        init();
        fatDirectory.init();
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.removeEntry$libaums_release(this.entry);
        FatLfnDirectoryEntry fatLfnDirectoryEntry2 = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry2);
        FatLfnDirectoryEntry fatLfnDirectoryEntry3 = this.entry;
        Intrinsics.checkNotNull(fatLfnDirectoryEntry3);
        fatDirectory.addEntry(fatLfnDirectoryEntry2, fatLfnDirectoryEntry3.getActualEntry());
        FatDirectory parent2 = getParent();
        Intrinsics.checkNotNull(parent2);
        parent2.write$libaums_release();
        fatDirectory.write$libaums_release();
        setParent(fatDirectory);
    }

    public final void move$libaums_release(FatLfnDirectoryEntry entry, UsbFile destination) throws IOException {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!destination.isDirectory()) {
            throw new IllegalStateException("destination cannot be a file!".toString());
        }
        if (!(destination instanceof FatDirectory)) {
            throw new IllegalStateException("cannot move between different filesystems!".toString());
        }
        FatDirectory fatDirectory = (FatDirectory) destination;
        Map<String, FatLfnDirectoryEntry> map = fatDirectory.lfnMap;
        String name$libaums_release = entry.getName$libaums_release();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = name$libaums_release.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        if (map.containsKey(lowerCase)) {
            throw new IOException("item already exists in destination!");
        }
        init();
        fatDirectory.init();
        removeEntry$libaums_release(entry);
        fatDirectory.addEntry(entry, entry.getActualEntry());
        write$libaums_release();
        fatDirectory.write$libaums_release();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void delete() throws IOException {
        if (isRoot()) {
            throw new IllegalStateException("Root dir cannot be deleted!".toString());
        }
        init();
        for (UsbFile usbFile : listFiles()) {
            usbFile.delete();
        }
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.removeEntry$libaums_release(this.entry);
        FatDirectory parent2 = getParent();
        Intrinsics.checkNotNull(parent2);
        parent2.write$libaums_release();
        ClusterChain clusterChain = this.chain;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.setLength$libaums_release(0L);
    }

    /* JADX INFO: compiled from: FatDirectory.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatDirectory$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "readRoot", "Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "fs", "Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "fat", "Lme/jahnen/libaums/core/fs/fat32/FAT;", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "readRoot$libaums_release", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FatDirectory readRoot$libaums_release(Fat32FileSystem fs, BlockDeviceDriver blockDevice, FAT fat, Fat32BootSector bootSector) throws IOException {
            Intrinsics.checkNotNullParameter(fs, "fs");
            Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
            Intrinsics.checkNotNullParameter(fat, "fat");
            Intrinsics.checkNotNullParameter(bootSector, "bootSector");
            FatDirectory fatDirectory = new FatDirectory(fs, blockDevice, fat, bootSector, null, null);
            fatDirectory.chain = new ClusterChain(bootSector.getRootDirStartCluster(), blockDevice, fat, bootSector);
            fatDirectory.init();
            return fatDirectory;
        }
    }
}
