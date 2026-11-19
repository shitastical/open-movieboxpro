package ly.count.android.sdk;

/* JADX INFO: loaded from: classes6.dex */
interface DeviceIdProvider {
    String getDeviceId();

    DeviceId getDeviceIdInstance();

    boolean isTemporaryIdEnabled();
}
