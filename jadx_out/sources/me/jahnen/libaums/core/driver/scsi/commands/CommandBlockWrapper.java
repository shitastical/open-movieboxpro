package me.jahnen.libaums.core.driver.scsi.commands;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommandBlockWrapper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \"2\u00020\u0001:\u0002\"#B1\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000f\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006$"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "", "dCbwDataTransferLength", "", "direction", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper$Direction;", "bCbwLun", "", "bCbwcbLength", "bCbwDynamicSize", "", "(ILme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper$Direction;BBZ)V", "getBCbwDynamicSize", "()Z", "getBCbwcbLength", "()B", "bmCbwFlags", "getBmCbwFlags", "setBmCbwFlags", "(B)V", "getDCbwDataTransferLength", "()I", "setDCbwDataTransferLength", "(I)V", "dCbwTag", "getDCbwTag", "setDCbwTag", "getDirection", "()Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper$Direction;", "dynamicSizeFromPartialResponse", "buffer", "Ljava/nio/ByteBuffer;", "serialize", "", "Companion", "Direction", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CommandBlockWrapper {
    private static final int D_CBW_SIGNATURE = 1128420181;
    private final boolean bCbwDynamicSize;
    private final byte bCbwLun;
    private final byte bCbwcbLength;
    private byte bmCbwFlags;
    private int dCbwDataTransferLength;
    private int dCbwTag;
    private final Direction direction;

    /* JADX INFO: compiled from: CommandBlockWrapper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper$Direction;", "", "(Ljava/lang/String;I)V", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN, HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT, "NONE", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Direction {
        IN,
        OUT,
        NONE
    }

    protected CommandBlockWrapper(int i, Direction direction, byte b, byte b2, boolean z) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        this.dCbwDataTransferLength = i;
        this.direction = direction;
        this.bCbwLun = b;
        this.bCbwcbLength = b2;
        this.bCbwDynamicSize = z;
        if (direction == Direction.IN) {
            this.bmCbwFlags = (byte) -128;
        }
    }

    public /* synthetic */ CommandBlockWrapper(int i, Direction direction, byte b, byte b2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, direction, b, b2, (i2 & 16) != 0 ? false : z);
    }

    public final int getDCbwDataTransferLength() {
        return this.dCbwDataTransferLength;
    }

    public final void setDCbwDataTransferLength(int i) {
        this.dCbwDataTransferLength = i;
    }

    public final Direction getDirection() {
        return this.direction;
    }

    public final byte getBCbwcbLength() {
        return this.bCbwcbLength;
    }

    public final boolean getBCbwDynamicSize() {
        return this.bCbwDynamicSize;
    }

    public final int getDCbwTag() {
        return this.dCbwTag;
    }

    public final void setDCbwTag(int i) {
        this.dCbwTag = i;
    }

    public final byte getBmCbwFlags() {
        return this.bmCbwFlags;
    }

    public final void setBmCbwFlags(byte b) {
        this.bmCbwFlags = b;
    }

    public void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(D_CBW_SIGNATURE);
        buffer.putInt(this.dCbwTag);
        buffer.putInt(this.dCbwDataTransferLength);
        buffer.put(this.bmCbwFlags);
        buffer.put(this.bCbwLun);
        buffer.put(this.bCbwcbLength);
    }

    public int dynamicSizeFromPartialResponse(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        throw new NotImplementedError("If dynamic length possible override in subclass");
    }
}
