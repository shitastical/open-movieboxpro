package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.cast.zzef;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzam {
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("id", (Object) null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("entity", (Object) null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("name", (Object) null);
            }
            String strZza = zzef.zza(0);
            if (strZza != null) {
                jSONObject.put("repeatMode", strZza);
            }
            jSONObject.put("startIndex", 0);
            jSONObject.put("startTime", 0.0d);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzam) && TextUtils.equals(null, null) && TextUtils.equals(null, null) && TextUtils.equals(null, null) && Objects.equal(null, null) && Objects.equal(null, null);
    }

    public final int hashCode() {
        return Objects.hashCode(null, null, 0, null, null, 0, null, 0, Double.valueOf(0.0d));
    }
}
