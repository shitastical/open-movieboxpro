package me.jahnen.libaums.core.fs.fat32;

import com.jeffmony.downloader.model.Video;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortName.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u000f\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\f\u001a\u00020\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/ShortName;", "", "name", "", "extension", "(Ljava/lang/String;Ljava/lang/String;)V", "data", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)V", "string", "getString", "()Ljava/lang/String;", "calculateCheckSum", "", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "serialize", "", "buffer", "toString", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ShortName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int SIZE = 11;
    private ByteBuffer data;

    public /* synthetic */ ShortName(ByteBuffer byteBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteBuffer);
    }

    public final String getString() {
        char[] cArr = new char[8];
        char[] cArr2 = new char[3];
        for (int i = 0; i < 8; i++) {
            cArr[i] = (char) this.data.get(i);
        }
        if (this.data.get(0) == 5) {
            cArr[0] = 229;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            cArr2[i2] = (char) this.data.get(i2 + 8);
        }
        String str = new String(cArr);
        int length = str.length() - 1;
        int i3 = 0;
        boolean z = false;
        while (i3 <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i3 : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i3++;
            } else {
                z = true;
            }
        }
        String string = str.subSequence(i3, length + 1).toString();
        String str2 = new String(cArr2);
        int length2 = str2.length() - 1;
        int i4 = 0;
        boolean z3 = false;
        while (i4 <= length2) {
            boolean z4 = Intrinsics.compare((int) str2.charAt(!z3 ? i4 : length2), 32) <= 0;
            if (z3) {
                if (!z4) {
                    break;
                }
                length2--;
            } else if (z4) {
                i4++;
            } else {
                z3 = true;
            }
        }
        String string2 = str2.subSequence(i4, length2 + 1).toString();
        if (string2.length() == 0) {
            return string;
        }
        return string + '.' + string2;
    }

    public ShortName(String name, String extension) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(extension, "extension");
        byte[] bArr = new byte[11];
        Arrays.fill(bArr, (byte) 32);
        int iMin = Math.min(name.length(), 8);
        Charset charsetForName = Charset.forName("ASCII");
        Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(\"ASCII\")");
        byte[] bytes = name.getBytes(charsetForName);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        System.arraycopy(bytes, 0, bArr, 0, iMin);
        Charset charsetForName2 = Charset.forName("ASCII");
        Intrinsics.checkNotNullExpressionValue(charsetForName2, "forName(\"ASCII\")");
        byte[] bytes2 = extension.getBytes(charsetForName2);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        System.arraycopy(bytes2, 0, bArr, 8, extension.length());
        if (bArr[0] == -27) {
            bArr[0] = 5;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(tmp)");
        this.data = byteBufferWrap;
    }

    private ShortName(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    public final void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.put(this.data.array(), 0, 11);
    }

    public final byte calculateCheckSum() {
        int i = 0;
        for (int i2 = 0; i2 < 11; i2++) {
            i = this.data.get(i2) + ((i & 1) == 1 ? 128 : 0) + ((i & 255) >> 1);
        }
        return (byte) (i & 255);
    }

    public boolean equals(Object other) {
        if (other instanceof ShortName) {
            return Arrays.equals(this.data.array(), ((ShortName) other).data.array());
        }
        return false;
    }

    public String toString() {
        return getString();
    }

    public int hashCode() {
        return getString().hashCode();
    }

    /* JADX INFO: compiled from: ShortName.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/ShortName$Companion;", "", "()V", "SIZE", "", "parse", "Lme/jahnen/libaums/core/fs/fat32/ShortName;", "data", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ShortName parse(ByteBuffer data) {
            Intrinsics.checkNotNullParameter(data, "data");
            byte[] bArr = new byte[11];
            data.get(bArr);
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(tmp)");
            return new ShortName(byteBufferWrap, (DefaultConstructorMarker) null);
        }
    }
}
