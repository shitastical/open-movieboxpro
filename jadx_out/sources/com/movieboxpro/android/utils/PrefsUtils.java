package com.movieboxpro.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class PrefsUtils {
    private static PrefsUtils instance;
    public Context context;
    public SharedPreferences prefs;

    public static synchronized PrefsUtils getInstance() {
        if (instance == null) {
            init(App.getContext(), Constant.Prefs.SETTING, 4);
        }
        return instance;
    }

    public static void init(Context context, String prefsName, int mode) {
        PrefsUtils prefsUtils = new PrefsUtils();
        instance = prefsUtils;
        prefsUtils.context = context;
        prefsUtils.prefs = context.getSharedPreferences(prefsName, mode);
    }

    private PrefsUtils() {
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }

    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0.0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0L);
    }

    public Set<String> getStringSet(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }

    public Set<String> getStringSet(String key) {
        return this.prefs.getStringSet(key, null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }

    public boolean exists(String key) {
        return this.prefs.contains(key);
    }

    public PrefsUtils putString(String key, String value) {
        this.prefs.edit().putString(key, value).apply();
        return this;
    }

    public PrefsUtils putInt(String key, int value) {
        this.prefs.edit().putInt(key, value).apply();
        return this;
    }

    public PrefsUtils putFloat(String key, float value) {
        this.prefs.edit().putFloat(key, value).apply();
        return this;
    }

    public PrefsUtils putLong(String key, long value) {
        this.prefs.edit().putLong(key, value).apply();
        return this;
    }

    public PrefsUtils putBoolean(String key, boolean value) {
        this.prefs.edit().putBoolean(key, value).apply();
        return this;
    }

    public PrefsUtils putStringSet(String key, Set<String> value) {
        this.prefs.edit().putStringSet(key, value).apply();
        return this;
    }

    public void putObject(String key, Object object) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream2.writeObject(object);
                    this.prefs.edit().putString(key, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0))).apply();
                    byteArrayOutputStream.close();
                    objectOutputStream2.close();
                } catch (IOException e) {
                    e = e;
                    objectOutputStream = objectOutputStream2;
                    e.printStackTrace();
                    byteArrayOutputStream.close();
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    try {
                        byteArrayOutputStream.close();
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    public <T> T getObject(String str, Class<T> cls) throws Throwable {
        ObjectInputStream objectInputStream;
        if (this.prefs.contains(str)) {
            ?? Decode = Base64.decode(this.prefs.getString(str, null), 0);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Decode);
            try {
                try {
                    try {
                        objectInputStream = new ObjectInputStream(byteArrayInputStream);
                        try {
                            T t = (T) objectInputStream.readObject();
                            try {
                                byteArrayInputStream.close();
                                objectInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return t;
                        } catch (StreamCorruptedException e2) {
                            e = e2;
                            e.printStackTrace();
                            byteArrayInputStream.close();
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            return null;
                        } catch (IOException e3) {
                            e = e3;
                            e.printStackTrace();
                            byteArrayInputStream.close();
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            return null;
                        } catch (ClassNotFoundException e4) {
                            e = e4;
                            e.printStackTrace();
                            byteArrayInputStream.close();
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            return null;
                        }
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                } catch (StreamCorruptedException e6) {
                    e = e6;
                    objectInputStream = null;
                } catch (IOException e7) {
                    e = e7;
                    objectInputStream = null;
                } catch (ClassNotFoundException e8) {
                    e = e8;
                    objectInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    Decode = 0;
                    try {
                        byteArrayInputStream.close();
                        if (Decode != 0) {
                            Decode.close();
                        }
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    public PrefsUtils remove(String key) {
        this.prefs.edit().remove(key).apply();
        return this;
    }

    public PrefsUtils removeAll() {
        this.prefs.edit().clear().apply();
        return this;
    }
}
