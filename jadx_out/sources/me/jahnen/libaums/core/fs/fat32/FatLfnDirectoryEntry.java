package me.jahnen.libaums.core.fs.fat32;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: FatLfnDirectoryEntry.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020#J\u0006\u0010'\u001a\u00020#J\u0006\u0010(\u001a\u00020#J\u0016\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010+\u001a\u00020\u0003H\u0016R\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018¨\u0006-"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;", "", "name", "", "shortName", "Lme/jahnen/libaums/core/fs/fat32/ShortName;", "(Ljava/lang/String;Lme/jahnen/libaums/core/fs/fat32/ShortName;)V", "actualEntry", "Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "lfnName", "(Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;Ljava/lang/String;)V", "<set-?>", "getActualEntry", "()Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "entryCount", "", "getEntryCount", "()I", "newSize", "", "fileSize", "getFileSize", "()J", "setFileSize", "(J)V", "isDirectory", "", "()Z", "getName$libaums_release", "()Ljava/lang/String;", "newStartCluster", "startCluster", "getStartCluster", "setStartCluster", "serialize", "", "buffer", "Ljava/nio/ByteBuffer;", "setDirectory", "setLastAccessedTimeToNow", "setLastModifiedTimeToNow", "setName", "newName", "toString", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FatLfnDirectoryEntry {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FatDirectoryEntry actualEntry;
    private String lfnName;

    public /* synthetic */ FatLfnDirectoryEntry(FatDirectoryEntry fatDirectoryEntry, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(fatDirectoryEntry, str);
    }

    private FatLfnDirectoryEntry(FatDirectoryEntry fatDirectoryEntry, String str) {
        this.lfnName = str;
        this.actualEntry = fatDirectoryEntry;
    }

    public final FatDirectoryEntry getActualEntry() {
        return this.actualEntry;
    }

    public final int getEntryCount() {
        String str = this.lfnName;
        if (str == null) {
            return 1;
        }
        int length = str.length();
        int i = length / 13;
        return length % 13 != 0 ? i + 2 : i + 1;
    }

    public final String getName$libaums_release() {
        List listEmptyList;
        String lowerCase;
        String str = this.lfnName;
        if (str != null) {
            return str;
        }
        ShortName shortName = this.actualEntry.getShortName();
        Intrinsics.checkNotNull(shortName);
        String string = shortName.getString();
        List<String> listSplit = new Regex("\\.").split(string, 0);
        if (!listSplit.isEmpty()) {
            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
        if (strArr.length != 2) {
            lowerCase = "";
        } else {
            string = strArr[0];
            lowerCase = strArr[1];
        }
        if (this.actualEntry.isShortNameLowerCase()) {
            string = string.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(string, "this as java.lang.String).toLowerCase()");
        }
        if (this.actualEntry.isShortNameExtLowerCase()) {
            lowerCase = lowerCase.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        }
        if (lowerCase.length() <= 0) {
            return string;
        }
        return string + '.' + lowerCase;
    }

    public final long getFileSize() {
        return this.actualEntry.getFileSize();
    }

    public final void setFileSize(long j) {
        this.actualEntry.setFileSize(j);
    }

    public final long getStartCluster() {
        return this.actualEntry.getStartCluster();
    }

    public final void setStartCluster(long j) {
        this.actualEntry.setStartCluster(j);
    }

    public final boolean isDirectory() {
        return this.actualEntry.isDirectory();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FatLfnDirectoryEntry(String str, ShortName shortName) {
        this(new FatDirectoryEntry(), str);
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        this.actualEntry.setShortName(shortName);
    }

    public final void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        String str = this.lfnName;
        if (str != null) {
            ShortName shortName = this.actualEntry.getShortName();
            Intrinsics.checkNotNull(shortName);
            byte bCalculateCheckSum = shortName.calculateCheckSum();
            int entryCount = getEntryCount();
            int i = entryCount - 2;
            FatDirectoryEntry.INSTANCE.createLfnPart(str, i * 13, bCalculateCheckSum, entryCount - 1, true).serialize(buffer);
            while (true) {
                int i2 = i;
                i = i2 - 1;
                if (i2 <= 0) {
                    break;
                } else {
                    FatDirectoryEntry.INSTANCE.createLfnPart(str, i * 13, bCalculateCheckSum, i2, false).serialize(buffer);
                }
            }
        }
        this.actualEntry.serialize(buffer);
    }

    public final void setName(String newName, ShortName shortName) {
        Intrinsics.checkNotNullParameter(newName, "newName");
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        this.lfnName = newName;
        this.actualEntry.setShortName(shortName);
    }

    public final void setLastAccessedTimeToNow() {
        this.actualEntry.setLastAccessedDateTime(System.currentTimeMillis());
    }

    public final void setLastModifiedTimeToNow() {
        this.actualEntry.setLastModifiedDateTime(System.currentTimeMillis());
    }

    public final void setDirectory() {
        this.actualEntry.setDirectory();
    }

    public String toString() {
        return "[FatLfnDirectoryEntry getName()=" + getName$libaums_release() + ']';
    }

    /* JADX INFO: compiled from: FatLfnDirectoryEntry.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u001c\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f¨\u0006\r"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry$Companion;", "", "()V", "copyDateTime", "", TypedValues.TransitionType.S_FROM, "Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;", TypedValues.TransitionType.S_TO, "read", "actualEntry", "Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "lfnParts", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FatLfnDirectoryEntry read(FatDirectoryEntry actualEntry, List<FatDirectoryEntry> lfnParts) {
            Intrinsics.checkNotNullParameter(actualEntry, "actualEntry");
            Intrinsics.checkNotNullParameter(lfnParts, "lfnParts");
            StringBuilder sb = new StringBuilder(lfnParts.size() * 13);
            DefaultConstructorMarker defaultConstructorMarker = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            if (!lfnParts.isEmpty()) {
                int size = lfnParts.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i = size - 1;
                        lfnParts.get(size).extractLfnPart(sb);
                        if (i < 0) {
                            break;
                        }
                        size = i;
                    }
                }
                return new FatLfnDirectoryEntry(actualEntry, sb.toString(), defaultConstructorMarker);
            }
            return new FatLfnDirectoryEntry(actualEntry, objArr2 == true ? 1 : 0, objArr == true ? 1 : 0);
        }

        public final void copyDateTime(FatLfnDirectoryEntry from, FatLfnDirectoryEntry to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            FatDirectoryEntry actualEntry = from.getActualEntry();
            FatDirectoryEntry actualEntry2 = to.getActualEntry();
            actualEntry2.setCreatedDateTime(actualEntry.getCreatedDateTime());
            actualEntry2.setLastAccessedDateTime(actualEntry.getLastAccessedDateTime());
            actualEntry2.setLastModifiedDateTime(actualEntry.getLastModifiedDateTime());
        }
    }
}
