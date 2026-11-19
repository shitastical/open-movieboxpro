package ly.count.android.sdk;

import android.content.Context;

/* JADX INFO: loaded from: classes6.dex */
interface MetricProvider {
    String getAppVersion(Context context);

    String getBatteryLevel(Context context);

    String getCarrier(Context context);

    String getCpu();

    String getDensity(Context context);

    String getDevice();

    String getDeviceType(Context context);

    String getDiskCurrent();

    String getDiskTotal();

    String getLocale();

    String getManufacturer();

    String getOS();

    String getOSVersion();

    String getOpenGL(Context context);

    String getOrientation(Context context);

    String getRamCurrent(Context context);

    String getRamTotal();

    String getResolution(Context context);

    String getStore(Context context);

    int getTimezoneOffset();

    long getTotalRAM();

    String isMuted(Context context);

    String isOnline(Context context);

    String isRooted();
}
