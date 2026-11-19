package me.jahnen.libaums.core.driver.scsi.commands;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommandStatusWrapper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0014"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/CommandStatusWrapper;", "", "()V", "<set-?>", "", "bCswStatus", "getBCswStatus", "()B", "", "dCswDataResidue", "getDCswDataResidue", "()I", "dCswSignature", "dCswTag", "getDCswTag", "read", "", "buffer", "Ljava/nio/ByteBuffer;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CommandStatusWrapper {
    public static final int COMMAND_FAILED = 1;
    public static final int COMMAND_PASSED = 0;
    private static final int D_CSW_SIGNATURE = 1396855637;
    public static final int PHASE_ERROR = 2;
    public static final int SIZE = 13;
    private byte bCswStatus;
    private int dCswDataResidue;
    private int dCswSignature = D_CSW_SIGNATURE;
    private int dCswTag;
    private static final String TAG = "CommandStatusWrapper";

    public final int getDCswTag() {
        return this.dCswTag;
    }

    public final int getDCswDataResidue() {
        return this.dCswDataResidue;
    }

    public final byte getBCswStatus() {
        return this.bCswStatus;
    }

    public final void read(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int i = buffer.getInt();
        this.dCswSignature = i;
        if (i != D_CSW_SIGNATURE) {
            Log.e(TAG, "unexpected dCSWSignature " + this.dCswSignature);
        }
        this.dCswTag = buffer.getInt();
        this.dCswDataResidue = buffer.getInt();
        this.bCswStatus = buffer.get();
    }
}
