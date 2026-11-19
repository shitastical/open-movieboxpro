package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zac();
    private static final Builder zamb = new zab(new String[0], null);
    private boolean mClosed;
    private final int zali;
    private final String[] zalt;
    private Bundle zalu;
    private final CursorWindow[] zalv;
    private final int zalw;
    private final Bundle zalx;
    private int[] zaly;
    private int zalz;
    private boolean zama;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static class zaa extends RuntimeException {
        public zaa(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.zama = true;
        this.zali = i;
        this.zalt = strArr;
        this.zalv = cursorWindowArr;
        this.zalw = i2;
        this.zalx = bundle;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    public static class Builder {
        private final String[] zalt;
        private final ArrayList<HashMap<String, Object>> zamc;
        private final String zamd;
        private final HashMap<Object, Integer> zame;
        private boolean zamf;
        private String zamg;

        private Builder(String[] strArr, String str) {
            this.zalt = (String[]) Preconditions.checkNotNull(strArr);
            this.zamc = new ArrayList<>();
            this.zamd = str;
            this.zame = new HashMap<>();
            this.zamf = false;
            this.zamg = null;
        }

        public Builder zaa(HashMap<String, Object> map) {
            Object obj;
            int iIntValue;
            Asserts.checkNotNull(map);
            String str = this.zamd;
            if (str == null || (obj = map.get(str)) == null) {
                iIntValue = -1;
            } else {
                Integer num = this.zame.get(obj);
                if (num == null) {
                    this.zame.put(obj, Integer.valueOf(this.zamc.size()));
                    iIntValue = -1;
                } else {
                    iIntValue = num.intValue();
                }
            }
            if (iIntValue == -1) {
                this.zamc.add(map);
            } else {
                this.zamc.remove(iIntValue);
                this.zamc.add(iIntValue, map);
            }
            this.zamf = false;
            return this;
        }

        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return zaa(map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null, (zab) (0 == true ? 1 : 0));
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1, (zab) null);
        }

        /* synthetic */ Builder(String[] strArr, String str, zab zabVar) {
            this(strArr, null);
        }
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zama = true;
        this.zali = 1;
        this.zalt = (String[]) Preconditions.checkNotNull(strArr);
        this.zalv = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zalw = i;
        this.zalx = bundle;
        zaby();
    }

    private DataHolder(CursorWrapper cursorWrapper, int i, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i, bundle);
    }

    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new CursorWrapper(cursor), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zalt, zaa(builder, -1), i, (Bundle) null);
    }

    private DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.zalt, zaa(builder, -1), i, bundle);
    }

    public final void zaby() {
        this.zalu = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zalt;
            if (i2 >= strArr.length) {
                break;
            }
            this.zalu.putInt(strArr[i2], i2);
            i2++;
        }
        this.zaly = new int[this.zalv.length];
        int numRows = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zalv;
            if (i < cursorWindowArr.length) {
                this.zaly[i] = numRows;
                numRows += this.zalv[i].getNumRows() - (numRows - cursorWindowArr[i].getStartPosition());
                i++;
            } else {
                this.zalz = numRows;
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zalt, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zalv, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zali);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final int getStatusCode() {
        return this.zalw;
    }

    public final Bundle getMetadata() {
        return this.zalx;
    }

    private static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        int startPosition;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count && cursorWrapper.moveToPosition(startPosition)) {
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private static CursorWindow[] zaa(Builder builder, int i) {
        if (builder.zalt.length != 0) {
            List listSubList = (i < 0 || i >= builder.zamc.size()) ? builder.zamc : builder.zamc.subList(0, i);
            int size = listSubList.size();
            CursorWindow cursorWindow = new CursorWindow(false);
            ArrayList arrayList = new ArrayList();
            arrayList.add(cursorWindow);
            cursorWindow.setNumColumns(builder.zalt.length);
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                try {
                    if (!cursorWindow.allocRow()) {
                        StringBuilder sb = new StringBuilder(72);
                        sb.append("Allocating additional cursor window for large data set (row ");
                        sb.append(i2);
                        sb.append(")");
                        Log.d("DataHolder", sb.toString());
                        cursorWindow = new CursorWindow(false);
                        cursorWindow.setStartPosition(i2);
                        cursorWindow.setNumColumns(builder.zalt.length);
                        arrayList.add(cursorWindow);
                        if (!cursorWindow.allocRow()) {
                            Log.e("DataHolder", "Unable to allocate row to hold data.");
                            arrayList.remove(cursorWindow);
                            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                        }
                    }
                    Map map = (Map) listSubList.get(i2);
                    boolean zPutDouble = true;
                    for (int i3 = 0; i3 < builder.zalt.length && zPutDouble; i3++) {
                        String str = builder.zalt[i3];
                        Object obj = map.get(str);
                        if (obj == null) {
                            zPutDouble = cursorWindow.putNull(i2, i3);
                        } else if (obj instanceof String) {
                            zPutDouble = cursorWindow.putString((String) obj, i2, i3);
                        } else if (obj instanceof Long) {
                            zPutDouble = cursorWindow.putLong(((Long) obj).longValue(), i2, i3);
                        } else if (obj instanceof Integer) {
                            zPutDouble = cursorWindow.putLong(((Integer) obj).intValue(), i2, i3);
                        } else if (obj instanceof Boolean) {
                            zPutDouble = cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i2, i3);
                        } else if (obj instanceof byte[]) {
                            zPutDouble = cursorWindow.putBlob((byte[]) obj, i2, i3);
                        } else if (obj instanceof Double) {
                            zPutDouble = cursorWindow.putDouble(((Double) obj).doubleValue(), i2, i3);
                        } else if (obj instanceof Float) {
                            zPutDouble = cursorWindow.putDouble(((Float) obj).floatValue(), i2, i3);
                        } else {
                            String strValueOf = String.valueOf(obj);
                            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(strValueOf).length());
                            sb2.append("Unsupported object for column ");
                            sb2.append(str);
                            sb2.append(": ");
                            sb2.append(strValueOf);
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    if (zPutDouble) {
                        z = false;
                    } else {
                        if (z) {
                            throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                        }
                        StringBuilder sb3 = new StringBuilder(74);
                        sb3.append("Couldn't populate window data for row ");
                        sb3.append(i2);
                        sb3.append(" - allocating new window.");
                        Log.d("DataHolder", sb3.toString());
                        cursorWindow.freeLastRow();
                        cursorWindow = new CursorWindow(false);
                        cursorWindow.setStartPosition(i2);
                        cursorWindow.setNumColumns(builder.zalt.length);
                        arrayList.add(cursorWindow);
                        i2--;
                        z = true;
                    }
                    i2++;
                } catch (RuntimeException e) {
                    int size2 = arrayList.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        ((CursorWindow) arrayList.get(i4)).close();
                    }
                    throw e;
                }
            }
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        }
        return new CursorWindow[0];
    }

    private final void zaa(String str, int i) {
        Bundle bundle = this.zalu;
        if (bundle == null || !bundle.containsKey(str)) {
            String strValueOf = String.valueOf(str);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "No such column: ".concat(strValueOf) : new String("No such column: "));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.zalz) {
            throw new CursorIndexOutOfBoundsException(i, this.zalz);
        }
    }

    public final boolean hasColumn(String str) {
        return this.zalu.containsKey(str);
    }

    public final long getLong(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getLong(i, this.zalu.getInt(str));
    }

    public final int getInteger(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getInt(i, this.zalu.getInt(str));
    }

    public final String getString(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getString(i, this.zalu.getInt(str));
    }

    public final boolean getBoolean(String str, int i, int i2) {
        zaa(str, i);
        long j = this.zalv[i2].getLong(i, this.zalu.getInt(str));
        Long.valueOf(j).getClass();
        return j == 1;
    }

    public final float zaa(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getFloat(i, this.zalu.getInt(str));
    }

    public final double zab(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getDouble(i, this.zalu.getInt(str));
    }

    public final byte[] getByteArray(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].getBlob(i, this.zalu.getInt(str));
    }

    public final void zaa(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zaa(str, i);
        this.zalv[i2].copyStringToBuffer(i, this.zalu.getInt(str), charArrayBuffer);
    }

    public final boolean hasNull(String str, int i, int i2) {
        zaa(str, i);
        return this.zalv[i2].isNull(i, this.zalu.getInt(str));
    }

    public final int getCount() {
        return this.zalz;
    }

    public final int getWindowIndex(int i) {
        int[] iArr;
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zalz);
        while (true) {
            iArr = this.zaly;
            if (i2 >= iArr.length) {
                break;
            }
            if (i < iArr[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == iArr.length ? i2 - 1 : i2;
    }

    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                int i = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr = this.zalv;
                    if (i >= cursorWindowArr.length) {
                        break;
                    }
                    cursorWindowArr[i].close();
                    i++;
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zama && this.zalv.length > 0 && !isClosed()) {
                close();
                String string = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(string).length() + Opcodes.GETSTATIC);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(string);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, 0 == true ? 1 : 0);
    }

    public static DataHolder empty(int i) {
        return new DataHolder(zamb, i, (Bundle) null);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, zab zabVar) {
        this(builder, i, (Bundle) null);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, zab zabVar) {
        this(builder, i, bundle, -1);
    }
}
