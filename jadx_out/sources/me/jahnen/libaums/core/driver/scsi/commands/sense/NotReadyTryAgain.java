package me.jahnen.libaums.core.driver.scsi.commands.sense;

import kotlin.Metadata;

/* JADX INFO: compiled from: SenseException.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/NotReadyTryAgain;", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/NotReady;", "response", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", "(Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;)V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NotReadyTryAgain extends NotReady {
    public NotReadyTryAgain(ScsiRequestSenseResponse scsiRequestSenseResponse) {
        super(scsiRequestSenseResponse, "Not ready; try again later");
    }
}
