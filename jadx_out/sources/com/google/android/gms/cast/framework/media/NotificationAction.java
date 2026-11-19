package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationAction extends AbstractSafeParcelable {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new zzp();
    private final String zznn;
    private final int zzno;
    private final String zznp;

    public static final class Builder {
        private String zznn;
        private int zzno;
        private String zznp;

        public final Builder setAction(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("action cannot be null or an empty string.");
            }
            this.zznn = str;
            return this;
        }

        public final Builder setIconResId(int i) {
            this.zzno = i;
            return this;
        }

        public final Builder setContentDescription(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("contentDescription cannot be null  or an empty string.");
            }
            this.zznp = str;
            return this;
        }

        public final NotificationAction build() {
            return new NotificationAction(this.zznn, this.zzno, this.zznp);
        }
    }

    NotificationAction(String str, int i, String str2) {
        this.zznn = str;
        this.zzno = i;
        this.zznp = str2;
    }

    public String getAction() {
        return this.zznn;
    }

    public int getIconResId() {
        return this.zzno;
    }

    public String getContentDescription() {
        return this.zznp;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAction(), false);
        SafeParcelWriter.writeInt(parcel, 3, getIconResId());
        SafeParcelWriter.writeString(parcel, 4, getContentDescription(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
