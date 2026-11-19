package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes5.dex */
public class Network {
    private static final String TAG = "Network";

    public enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        public int value;

        NetType(int value) {
            this.value = value;
        }
    }

    public enum NetWorkType {
        UnKnown(-1),
        Wifi(1),
        Net2G(2),
        Net3G(3),
        Net4G(4);

        public int value;

        NetWorkType(int value) {
            this.value = value;
        }
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        if (context != null) {
            return (ConnectivityManager) context.getSystemService("connectivity");
        }
        return null;
    }

    public static String getDomainAddress(final String domain) {
        String str;
        try {
            String[] strArrSplit = domain.split(":");
            if (strArrSplit.length <= 0) {
                str = "";
            } else {
                str = strArrSplit[0];
            }
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        return (context == null || (activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static NetType getConnectedType(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                return NetType.Mobile;
            }
            if (type == 1) {
                return NetType.Wifi;
            }
            return NetType.Other;
        }
        return NetType.None;
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected();
    }

    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || (isMobileAvailable(context) && isMobileEnabled(context));
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        if (context != null && (allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getType() == 1) {
                    return networkInfo.isAvailable();
                }
            }
        }
        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        if (context != null && (allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getType() == 0) {
                    return networkInfo.isAvailable();
                }
            }
        }
        return false;
    }

    public static boolean isMobileEnabled(Context context) {
        if (context != null) {
            try {
                Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", null);
                declaredMethod.setAccessible(true);
                return ((Boolean) declaredMethod.invoke(getConnectivityManager(context), null)).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static int getConnectedTypeINT(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static int getTelNetworkTypeINT(Context context) {
        return getTelephonyManager(context).getNetworkType();
    }

    public static NetWorkType getNetworkType(Context context) {
        int connectedTypeINT = getConnectedTypeINT(context);
        if (connectedTypeINT != 0) {
            if (connectedTypeINT == 1) {
                return NetWorkType.Wifi;
            }
            if (connectedTypeINT != 2 && connectedTypeINT != 3 && connectedTypeINT != 4 && connectedTypeINT != 5) {
                return NetWorkType.UnKnown;
            }
        }
        switch (getTelephonyManager(context).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NetWorkType.Net2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NetWorkType.Net3G;
            case 13:
                return NetWorkType.Net4G;
            default:
                return NetWorkType.UnKnown;
        }
    }
}
