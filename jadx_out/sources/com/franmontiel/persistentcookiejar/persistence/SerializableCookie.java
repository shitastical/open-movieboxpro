package com.franmontiel.persistentcookiejar.persistence;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okhttp3.Cookie;

/* JADX INFO: loaded from: classes4.dex */
public class SerializableCookie implements Serializable {
    private static long NON_VALID_EXPIRES_AT = -1;
    private static final String TAG = "SerializableCookie";
    private static final long serialVersionUID = -8594045714036645534L;
    private transient Cookie cookie;

    /* JADX WARN: Code duplicated, block: B:32:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public String encode(Cookie cookie) throws Throwable {
        ObjectOutputStream objectOutputStream;
        this.cookie = cookie;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                try {
                    objectOutputStream.writeObject(this);
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        Log.d(TAG, "Stream not closed in encodeCookie", e);
                    }
                    return byteArrayToHexString(byteArrayOutputStream.toByteArray());
                } catch (IOException e2) {
                    e = e2;
                    Log.d(TAG, "IOException in encodeCookie", e);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            Log.d(TAG, "Stream not closed in encodeCookie", e3);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e4) {
                        Log.d(TAG, "Stream not closed in encodeCookie", e4);
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            objectOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
            throw th;
        }
    }

    private static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:34:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public Cookie decode(String str) throws Throwable {
        ObjectInputStream objectInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(hexStringToByteArray(str));
        Cookie cookie = null;
        try {
            try {
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        cookie = ((SerializableCookie) objectInputStream.readObject()).cookie;
                        objectInputStream.close();
                    } catch (IOException e) {
                        e = e;
                        Log.d(TAG, "IOException in decodeCookie", e);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (ClassNotFoundException e2) {
                        e = e2;
                        Log.d(TAG, "ClassNotFoundException in decodeCookie", e);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                            Log.d(TAG, "Stream not closed in decodeCookie", e3);
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                objectInputStream = null;
            } catch (ClassNotFoundException e5) {
                e = e5;
                objectInputStream = null;
            } catch (Throwable th3) {
                objectInputStream = null;
                th = th3;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            Log.d(TAG, "Stream not closed in decodeCookie", e6);
        }
        return cookie;
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookie.name());
        objectOutputStream.writeObject(this.cookie.value());
        objectOutputStream.writeLong(this.cookie.persistent() ? this.cookie.expiresAt() : NON_VALID_EXPIRES_AT);
        objectOutputStream.writeObject(this.cookie.domain());
        objectOutputStream.writeObject(this.cookie.path());
        objectOutputStream.writeBoolean(this.cookie.secure());
        objectOutputStream.writeBoolean(this.cookie.httpOnly());
        objectOutputStream.writeBoolean(this.cookie.hostOnly());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();
        builder.name((String) objectInputStream.readObject());
        builder.value((String) objectInputStream.readObject());
        long j = objectInputStream.readLong();
        if (j != NON_VALID_EXPIRES_AT) {
            builder.expiresAt(j);
        }
        String str = (String) objectInputStream.readObject();
        builder.domain(str);
        builder.path((String) objectInputStream.readObject());
        if (objectInputStream.readBoolean()) {
            builder.secure();
        }
        if (objectInputStream.readBoolean()) {
            builder.httpOnly();
        }
        if (objectInputStream.readBoolean()) {
            builder.hostOnlyDomain(str);
        }
        this.cookie = builder.build();
    }
}
