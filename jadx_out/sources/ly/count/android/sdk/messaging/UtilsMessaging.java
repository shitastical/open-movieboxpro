package ly.count.android.sdk.messaging;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.ModuleLog;

/* JADX INFO: loaded from: classes6.dex */
public class UtilsMessaging {
    private static final UtilsMessaging utils = new UtilsMessaging();

    static boolean reflectiveClassExists(String str, ModuleLog moduleLog) {
        return utils._reflectiveClassExists(str, moduleLog);
    }

    public boolean _reflectiveClassExists(String str, ModuleLog moduleLog) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            moduleLog.d("Class " + str + " not found");
            return false;
        }
    }

    static Object reflectiveCall(String str, Object obj, String str2, ModuleLog moduleLog, Object... objArr) {
        return utils._reflectiveCall(str, obj, str2, moduleLog, objArr);
    }

    public Object _reflectiveCall(String str, Object obj, String str2, ModuleLog moduleLog, Object... objArr) {
        Class<?>[] clsArr;
        try {
            moduleLog.d("cls " + str + ", inst " + obj);
            if (str == null && obj != null) {
                str = obj.getClass().getName();
            }
            Class<?> cls = obj == null ? Class.forName(str) : obj.getClass();
            if (objArr == null || objArr.length <= 0) {
                clsArr = null;
            } else {
                int length = objArr.length;
                clsArr = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
            }
            return cls.getDeclaredMethod(str2, clsArr).invoke(obj, objArr);
        } catch (ClassNotFoundException e) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e);
            return false;
        } catch (IllegalAccessException e2) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e2);
            return false;
        } catch (NoSuchMethodException e3) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e3);
            return false;
        } catch (InvocationTargetException e4) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e4);
            return false;
        }
    }

    static Object reflectiveCallStrict(String str, Object obj, String str2, ModuleLog moduleLog, Object... objArr) {
        return utils._reflectiveCallStrict(str, obj, str2, moduleLog, objArr);
    }

    public Object _reflectiveCallStrict(String str, Object obj, String str2, ModuleLog moduleLog, Object... objArr) {
        try {
            Log.d(Countly.TAG, "cls " + str + ", inst " + obj);
            Class<?>[] clsArr = null;
            if (objArr != null && objArr.length % 2 != 0) {
                moduleLog.w("wrong arguments passed to reflectiveCallStrict");
                return null;
            }
            if (str == null && obj != null) {
                str = obj.getClass().getName();
            }
            Class<?> cls = obj == null ? Class.forName(str) : obj.getClass();
            if (objArr != null && objArr.length > 0) {
                clsArr = new Class[objArr.length / 2];
            }
            Object[] objArr2 = new Object[objArr != null ? objArr.length / 2 : 0];
            if (objArr != null && objArr.length > 0) {
                for (int i = 0; i < clsArr.length; i += 2) {
                    int i2 = i * 2;
                    objArr2[i] = objArr[i2];
                    clsArr[i] = (Class) objArr[i2 + 1];
                }
            }
            return cls.getDeclaredMethod(str2, clsArr).invoke(obj, objArr2);
        } catch (ClassNotFoundException e) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e);
            return false;
        } catch (IllegalAccessException e2) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e2);
            return false;
        } catch (NoSuchMethodException e3) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e3);
            return false;
        } catch (InvocationTargetException e4) {
            moduleLog.w("Cannot call " + str2 + " of " + str, e4);
            return false;
        }
    }
}
