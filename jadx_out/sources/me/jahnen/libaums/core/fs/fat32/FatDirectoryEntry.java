package me.jahnen.libaums.core.fs.fat32;

import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.fs.FileSystemFactory;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: FatDirectoryEntry.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u0000 J2\u00020\u0001:\u0001JB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\b\u0002\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u00107\u001a\u0002082\n\u00109\u001a\u00060:j\u0002`;J\u0010\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u0012H\u0002J\u0010\u0010>\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0012H\u0002J\u0010\u0010?\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u0012H\u0002J\u0010\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u0012H\u0002J\u000e\u0010B\u001a\u0002082\u0006\u0010C\u001a\u00020\u0004J\u0006\u0010D\u001a\u000208J\u0010\u0010E\u001a\u0002082\u0006\u0010A\u001a\u00020\u0012H\u0002J\u0018\u0010F\u001a\u0002082\u0006\u0010=\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0012H\u0002J\u0018\u0010H\u001a\u0002082\u0006\u0010=\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\u0007H\u0002J\b\u0010I\u001a\u000204H\u0016R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u001b\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u001d\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u001e\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u001f\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010 \u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b \u0010\u0017R\u0011\u0010!\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b!\u0010\u0017R$\u0010\"\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010\n\"\u0004\b$\u0010\fR$\u0010%\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010\n\"\u0004\b'\u0010\fR*\u0010*\u001a\u0004\u0018\u00010)2\b\u0010(\u001a\u0004\u0018\u00010)8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010\n\"\u0004\b2\u0010\fR\u0011\u00103\u001a\u0002048F¢\u0006\u0006\u001a\u0004\b5\u00106¨\u0006K"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "", "()V", "data", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "dateTime", "", "createdDateTime", "getCreatedDateTime", "()J", "setCreatedDateTime", "(J)V", "newSize", "fileSize", "getFileSize", "setFileSize", "flags", "", "getFlags", "()I", "isArchive", "", "()Z", "isDeleted", "isDirectory", "isHidden", "isLfnEntry", "isReadOnly", "isShortNameExtLowerCase", "isShortNameLowerCase", "isSystem", "isVolume", "isVolumeLabel", "lastAccessedDateTime", "getLastAccessedDateTime", "setLastAccessedDateTime", "lastModifiedDateTime", "getLastModifiedDateTime", "setLastModifiedDateTime", "sn", "Lme/jahnen/libaums/core/fs/fat32/ShortName;", "shortName", "getShortName", "()Lme/jahnen/libaums/core/fs/fat32/ShortName;", "setShortName", "(Lme/jahnen/libaums/core/fs/fat32/ShortName;)V", "newStartCluster", "startCluster", "getStartCluster", "setStartCluster", "volumeLabel", "", "getVolumeLabel", "()Ljava/lang/String;", "extractLfnPart", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getUnsignedInt16", "offset", "getUnsignedInt32", "getUnsignedInt8", "isFlagSet", "flag", "serialize", "buffer", "setDirectory", "setFlag", "setUnsignedInt16", "value", "setUnsignedInt32", "toString", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FatDirectoryEntry {
    private static final int ATTR_OFF = 11;
    private static final int CREATED_DATE_OFF = 16;
    private static final int CREATED_TIME_OFF = 14;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int ENTRY_DELETED = 229;
    private static final int FILE_SIZE_OFF = 28;
    private static final int FLAG_ARCHIVE = 32;
    private static final int FLAG_DIRECTORY = 16;
    private static final int FLAG_HIDDEN = 2;
    private static final int FLAG_READONLY = 1;
    private static final int FLAG_SYSTEM = 4;
    private static final int FLAG_VOLUME_ID = 8;
    private static final int LAST_ACCESSED_DATE_OFF = 18;
    private static final int LAST_WRITE_DATE_OFF = 24;
    private static final int LAST_WRITE_TIME_OFF = 22;
    private static final int LSB_CLUSTER_OFF = 26;
    private static final int MSB_CLUSTER_OFF = 20;
    private static final int SHORTNAME_CASE_OFF = 12;
    public static final int SIZE = 32;
    private ByteBuffer data;
    private ShortName shortName;

    public /* synthetic */ FatDirectoryEntry(ByteBuffer byteBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer);
    }

    private FatDirectoryEntry(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        setShortName(ShortName.INSTANCE.parse(this.data));
        this.data.clear();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* synthetic */ FatDirectoryEntry(ByteBuffer byteBuffer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            byteBuffer = ByteBuffer.allocate(32);
            Intrinsics.checkNotNullExpressionValue(byteBuffer, "allocate(SIZE)");
        }
        this(byteBuffer);
    }

    public final ShortName getShortName() {
        if (this.data.get(0) == 0) {
            return null;
        }
        return this.shortName;
    }

    public final void setShortName(ShortName shortName) {
        this.shortName = shortName;
        this.data.clear();
        ShortName shortName2 = this.shortName;
        if (shortName2 != null) {
            shortName2.serialize(this.data);
        }
        this.data.clear();
    }

    private final int getFlags() {
        return this.data.get(11);
    }

    public final boolean isShortNameLowerCase() {
        return ((byte) (this.data.get(12) & 8)) != 0;
    }

    public final boolean isShortNameExtLowerCase() {
        return ((byte) (this.data.get(12) & Ascii.DLE)) != 0;
    }

    public final boolean isLfnEntry() {
        return isHidden() && isVolume() && isReadOnly() && isSystem();
    }

    public final boolean isDirectory() {
        return (getFlags() & 24) == 16;
    }

    public final boolean isVolumeLabel() {
        return !isLfnEntry() && (getFlags() & 24) == 8;
    }

    public final boolean isSystem() {
        return isFlagSet(4);
    }

    public final boolean isHidden() {
        return isFlagSet(2);
    }

    public final boolean isArchive() {
        return isFlagSet(32);
    }

    public final boolean isReadOnly() {
        return isFlagSet(1);
    }

    public final boolean isVolume() {
        return isFlagSet(8);
    }

    public final boolean isDeleted() {
        return getUnsignedInt8(0) == 229;
    }

    public final long getCreatedDateTime() {
        return INSTANCE.decodeDateTime(getUnsignedInt16(16), getUnsignedInt16(14));
    }

    public final void setCreatedDateTime(long j) {
        Companion companion = INSTANCE;
        setUnsignedInt16(16, companion.encodeDate(j));
        setUnsignedInt16(14, companion.encodeTime(j));
    }

    public final long getLastModifiedDateTime() {
        return INSTANCE.decodeDateTime(getUnsignedInt16(24), getUnsignedInt16(22));
    }

    public final void setLastModifiedDateTime(long j) {
        Companion companion = INSTANCE;
        setUnsignedInt16(24, companion.encodeDate(j));
        setUnsignedInt16(22, companion.encodeTime(j));
    }

    public final long getLastAccessedDateTime() {
        return INSTANCE.decodeDateTime(getUnsignedInt16(18), 0);
    }

    public final void setLastAccessedDateTime(long j) {
        setUnsignedInt16(18, INSTANCE.encodeDate(j));
    }

    public final String getVolumeLabel() {
        byte b;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11 && (b = this.data.get(i)) != 0; i++) {
            sb.append((char) b);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }

    public final long getStartCluster() {
        return ((long) getUnsignedInt16(26)) | (((long) getUnsignedInt16(20)) << 16);
    }

    public final void setStartCluster(long j) {
        setUnsignedInt16(20, (int) ((j >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        setUnsignedInt16(26, (int) (j & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    public final long getFileSize() {
        return getUnsignedInt32(28);
    }

    public final void setFileSize(long j) {
        setUnsignedInt32(28, j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FatDirectoryEntry() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(32);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(SIZE)");
        this(byteBufferAllocate);
        long jCurrentTimeMillis = System.currentTimeMillis();
        setCreatedDateTime(jCurrentTimeMillis);
        setLastAccessedDateTime(jCurrentTimeMillis);
        setLastModifiedDateTime(jCurrentTimeMillis);
    }

    public final void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.put(this.data.array());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFlag(int flag) {
        this.data.put(11, (byte) (flag | getFlags()));
    }

    private final boolean isFlagSet(int flag) {
        return (flag & getFlags()) != 0;
    }

    public final void setDirectory() {
        setFlag(16);
    }

    public final void extractLfnPart(StringBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        char[] cArr = {(char) this.data.getShort(1), (char) this.data.getShort(3), (char) this.data.getShort(5), (char) this.data.getShort(7), (char) this.data.getShort(9), (char) this.data.getShort(14), (char) this.data.getShort(16), (char) this.data.getShort(18), (char) this.data.getShort(20), (char) this.data.getShort(22), (char) this.data.getShort(24), (char) this.data.getShort(28), (char) this.data.getShort(30)};
        int i = 0;
        while (i < 13 && cArr[i] != 0) {
            i++;
        }
        builder.append(cArr, 0, i);
    }

    private final int getUnsignedInt8(int offset) {
        return this.data.get(offset) & 255;
    }

    private final int getUnsignedInt16(int offset) {
        return ((this.data.get(offset + 1) & 255) << 8) | (this.data.get(offset) & 255);
    }

    private final long getUnsignedInt32(int offset) {
        return (this.data.get(offset) & 255) | ((this.data.get(offset + 1) & 255) << 8) | (((long) (this.data.get(offset + 2) & 255)) << 16) | (((long) (this.data.get(offset + 3) & 255)) << 24);
    }

    private final void setUnsignedInt16(int offset, int value) {
        this.data.put(offset, (byte) (value & 255));
        this.data.put(offset + 1, (byte) ((value >>> 8) & 255));
    }

    private final void setUnsignedInt32(int offset, long value) {
        this.data.put(offset, (byte) (value & 255));
        this.data.put(offset + 1, (byte) ((value >>> 8) & 255));
        this.data.put(offset + 2, (byte) ((value >>> 16) & 255));
        this.data.put(offset + 3, (byte) ((value >>> 24) & 255));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[FatDirectoryEntry shortName=");
        ShortName shortName = getShortName();
        Intrinsics.checkNotNull(shortName);
        sb.append(shortName.getString());
        sb.append(']');
        return sb.toString();
    }

    /* JADX INFO: compiled from: FatDirectoryEntry.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0019J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0004H\u0002J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020#H\u0002J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020#H\u0002J\u0010\u0010)\u001a\u0004\u0018\u00010\u00172\u0006\u0010*\u001a\u00020+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry$Companion;", "", "()V", "ATTR_OFF", "", "CREATED_DATE_OFF", "CREATED_TIME_OFF", "ENTRY_DELETED", "FILE_SIZE_OFF", "FLAG_ARCHIVE", "FLAG_DIRECTORY", "FLAG_HIDDEN", "FLAG_READONLY", "FLAG_SYSTEM", "FLAG_VOLUME_ID", "LAST_ACCESSED_DATE_OFF", "LAST_WRITE_DATE_OFF", "LAST_WRITE_TIME_OFF", "LSB_CLUSTER_OFF", "MSB_CLUSTER_OFF", "SHORTNAME_CASE_OFF", "SIZE", "createLfnPart", "Lme/jahnen/libaums/core/fs/fat32/FatDirectoryEntry;", "unicode", "", "offset", "checksum", "", FirebaseAnalytics.Param.INDEX, "isLast", "", "createVolumeLabel", "volumeLabel", "decodeDateTime", "", "date", "time", "encodeDate", "timeInMillis", "encodeTime", "read", "data", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FatDirectoryEntry read(ByteBuffer data) {
            Intrinsics.checkNotNullParameter(data, "data");
            byte[] bArr = new byte[32];
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (data.get(data.position()) == 0) {
                return null;
            }
            data.get(bArr);
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(buffer)");
            return new FatDirectoryEntry(byteBufferWrap, defaultConstructorMarker);
        }

        public final FatDirectoryEntry createVolumeLabel(String volumeLabel) {
            Intrinsics.checkNotNullParameter(volumeLabel, "volumeLabel");
            FatDirectoryEntry fatDirectoryEntry = new FatDirectoryEntry();
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            Charset charsetForName = Charset.forName("ASCII");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(\"ASCII\")");
            byte[] bytes = volumeLabel.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            System.arraycopy(bytes, 0, buffer.array(), 0, volumeLabel.length());
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            fatDirectoryEntry.data = buffer;
            fatDirectoryEntry.setFlag(8);
            return fatDirectoryEntry;
        }

        public final FatDirectoryEntry createLfnPart(String unicode, int offset, byte checksum, int index, boolean isLast) {
            int length;
            Intrinsics.checkNotNullParameter(unicode, "unicode");
            FatDirectoryEntry fatDirectoryEntry = new FatDirectoryEntry();
            if (isLast && (length = unicode.length() - offset) < 13) {
                StringBuilder sb = new StringBuilder(13);
                sb.append((CharSequence) unicode, offset, unicode.length());
                sb.append((char) 0);
                int i = 13 - length;
                for (int i2 = 0; i2 < i; i2++) {
                    sb.append(CharCompanionObject.MAX_VALUE);
                }
                unicode = sb.toString();
                Intrinsics.checkNotNullExpressionValue(unicode, "builder.toString()");
                offset = 0;
            }
            ByteBuffer buffer = ByteBuffer.allocate(32);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            if (isLast) {
                index += 64;
            }
            buffer.put(0, (byte) index);
            buffer.putShort(1, (short) unicode.charAt(offset));
            buffer.putShort(3, (short) unicode.charAt(offset + 1));
            buffer.putShort(5, (short) unicode.charAt(offset + 2));
            buffer.putShort(7, (short) unicode.charAt(offset + 3));
            buffer.putShort(9, (short) unicode.charAt(offset + 4));
            buffer.put(11, Ascii.SI);
            buffer.put(12, (byte) 0);
            buffer.put(13, checksum);
            buffer.putShort(14, (short) unicode.charAt(offset + 5));
            buffer.putShort(16, (short) unicode.charAt(offset + 6));
            buffer.putShort(18, (short) unicode.charAt(offset + 7));
            buffer.putShort(20, (short) unicode.charAt(offset + 8));
            buffer.putShort(22, (short) unicode.charAt(offset + 9));
            buffer.putShort(24, (short) unicode.charAt(offset + 10));
            buffer.putShort(26, (short) 0);
            buffer.putShort(28, (short) unicode.charAt(offset + 11));
            buffer.putShort(30, (short) unicode.charAt(offset + 12));
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            fatDirectoryEntry.data = buffer;
            return fatDirectoryEntry;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long decodeDateTime(int date, int time) {
            Calendar calendar = Calendar.getInstance(FileSystemFactory.getTimeZone());
            calendar.set(1, (date >> 9) + 1980);
            calendar.set(2, ((date >> 5) & 15) - 1);
            calendar.set(5, date & 31);
            calendar.set(11, time >> 11);
            calendar.set(12, (time >> 5) & 63);
            calendar.set(13, (time & 31) * 2);
            return calendar.getTimeInMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int encodeDate(long timeInMillis) {
            Calendar calendar = Calendar.getInstance(FileSystemFactory.getTimeZone());
            calendar.setTimeInMillis(timeInMillis);
            return ((calendar.get(1) - 1980) << 9) + ((calendar.get(2) + 1) << 5) + calendar.get(5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int encodeTime(long timeInMillis) {
            Calendar calendar = Calendar.getInstance(FileSystemFactory.getTimeZone());
            calendar.setTimeInMillis(timeInMillis);
            return (calendar.get(11) << 11) + (calendar.get(12) << 5) + (calendar.get(13) / 2);
        }
    }
}
