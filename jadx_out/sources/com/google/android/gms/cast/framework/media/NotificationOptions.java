package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationOptions extends AbstractSafeParcelable {
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;
    private final long zzlz;
    private final List<String> zznu;
    private final int[] zznv;
    private final String zznw;
    private final int zznx;
    private final int zzny;
    private final int zznz;
    private final int zzoa;
    private final int zzob;
    private final int zzoc;
    private final int zzod;
    private final int zzoe;
    private final int zzof;
    private final int zzog;
    private final int zzoh;
    private final int zzoi;
    private final int zzoj;
    private final int zzok;
    private final int zzol;
    private final int zzom;
    private final int zzon;
    private final int zzoo;
    private final int zzop;
    private final int zzoq;
    private final int zzor;
    private final int zzos;
    private final int zzot;
    private final int zzou;
    private final int zzov;
    private final int zzow;
    private final int zzox;
    private final zzd zzoy;
    private static final List<String> zzns = Arrays.asList(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING);
    private static final int[] zznt = {0, 1};
    public static final Parcelable.Creator<NotificationOptions> CREATOR = new zzr();

    public NotificationOptions(List<String> list, int[] iArr, long j, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, IBinder iBinder) {
        zzd zzeVar = null;
        if (list != null) {
            this.zznu = new ArrayList(list);
        } else {
            this.zznu = null;
        }
        if (iArr != null) {
            this.zznv = Arrays.copyOf(iArr, iArr.length);
        } else {
            this.zznv = null;
        }
        this.zzlz = j;
        this.zznw = str;
        this.zznx = i;
        this.zzny = i2;
        this.zznz = i3;
        this.zzoa = i4;
        this.zzob = i5;
        this.zzoc = i6;
        this.zzod = i7;
        this.zzoe = i8;
        this.zzof = i9;
        this.zzog = i10;
        this.zzoh = i11;
        this.zzoi = i12;
        this.zzoj = i13;
        this.zzok = i14;
        this.zzol = i15;
        this.zzom = i16;
        this.zzon = i17;
        this.zzoo = i18;
        this.zzop = i19;
        this.zzoq = i20;
        this.zzor = i21;
        this.zzos = i22;
        this.zzot = i23;
        this.zzou = i24;
        this.zzov = i25;
        this.zzow = i26;
        this.zzox = i27;
        if (iBinder != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
            zzeVar = iInterfaceQueryLocalInterface instanceof zzd ? (zzd) iInterfaceQueryLocalInterface : new zze(iBinder);
        }
        this.zzoy = zzeVar;
    }

    public static final class Builder {
        private String zznw;
        private NotificationActionsProvider zzoz;
        private List<String> zznu = NotificationOptions.zzns;
        private int[] zznv = NotificationOptions.zznt;
        private int zznx = R.drawable.cast_ic_notification_small_icon;
        private int zzny = R.drawable.cast_ic_notification_stop_live_stream;
        private int zznz = R.drawable.cast_ic_notification_pause;
        private int zzoa = R.drawable.cast_ic_notification_play;
        private int zzob = R.drawable.cast_ic_notification_skip_next;
        private int zzoc = R.drawable.cast_ic_notification_skip_prev;
        private int zzod = R.drawable.cast_ic_notification_forward;
        private int zzoe = R.drawable.cast_ic_notification_forward10;
        private int zzof = R.drawable.cast_ic_notification_forward30;
        private int zzog = R.drawable.cast_ic_notification_rewind;
        private int zzoh = R.drawable.cast_ic_notification_rewind10;
        private int zzoi = R.drawable.cast_ic_notification_rewind30;
        private int zzoj = R.drawable.cast_ic_notification_disconnect;
        private long zzlz = 10000;

        public final Builder setActions(List<String> list, int[] iArr) {
            if (list == null && iArr != null) {
                throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
            }
            if (list != null && iArr == null) {
                throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
            }
            if (list != null && iArr != null) {
                int size = list.size();
                if (iArr.length > size) {
                    throw new IllegalArgumentException(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", Integer.valueOf(iArr.length), Integer.valueOf(size)));
                }
                for (int i : iArr) {
                    if (i < 0 || i >= size) {
                        throw new IllegalArgumentException(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", Integer.valueOf(i), Integer.valueOf(size - 1)));
                    }
                }
                this.zznu = new ArrayList(list);
                this.zznv = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.zznu = NotificationOptions.zzns;
                this.zznv = NotificationOptions.zznt;
            }
            return this;
        }

        public final Builder setNotificationActionsProvider(NotificationActionsProvider notificationActionsProvider) {
            if (notificationActionsProvider == null) {
                throw new IllegalArgumentException("notificationActionsProvider cannot be null.");
            }
            this.zzoz = notificationActionsProvider;
            return this;
        }

        public final Builder setSkipStepMs(long j) {
            Preconditions.checkArgument(j > 0, "skipStepMs must be positive.");
            this.zzlz = j;
            return this;
        }

        public final Builder setTargetActivityClassName(String str) {
            this.zznw = str;
            return this;
        }

        public final Builder setSmallIconDrawableResId(int i) {
            this.zznx = i;
            return this;
        }

        public final Builder setStopLiveStreamDrawableResId(int i) {
            this.zzny = i;
            return this;
        }

        public final Builder setPauseDrawableResId(int i) {
            this.zznz = i;
            return this;
        }

        public final Builder setPlayDrawableResId(int i) {
            this.zzoa = i;
            return this;
        }

        public final Builder setDisconnectDrawableResId(int i) {
            this.zzoj = i;
            return this;
        }

        public final Builder setSkipNextDrawableResId(int i) {
            this.zzob = i;
            return this;
        }

        public final Builder setSkipPrevDrawableResId(int i) {
            this.zzoc = i;
            return this;
        }

        public final Builder setForwardDrawableResId(int i) {
            this.zzod = i;
            return this;
        }

        public final Builder setForward10DrawableResId(int i) {
            this.zzoe = i;
            return this;
        }

        public final Builder setForward30DrawableResId(int i) {
            this.zzof = i;
            return this;
        }

        public final Builder setRewindDrawableResId(int i) {
            this.zzog = i;
            return this;
        }

        public final Builder setRewind10DrawableResId(int i) {
            this.zzoh = i;
            return this;
        }

        public final Builder setRewind30DrawableResId(int i) {
            this.zzoi = i;
            return this;
        }

        public final NotificationOptions build() {
            NotificationActionsProvider notificationActionsProvider = this.zzoz;
            return new NotificationOptions(this.zznu, this.zznv, this.zzlz, this.zznw, this.zznx, this.zzny, this.zznz, this.zzoa, this.zzob, this.zzoc, this.zzod, this.zzoe, this.zzof, this.zzog, this.zzoh, this.zzoi, this.zzoj, R.dimen.cast_notification_image_size, R.string.cast_casting_to_device, R.string.cast_stop_live_stream, R.string.cast_pause, R.string.cast_play, R.string.cast_skip_next, R.string.cast_skip_prev, R.string.cast_forward, R.string.cast_forward_10, R.string.cast_forward_30, R.string.cast_rewind, R.string.cast_rewind_10, R.string.cast_rewind_30, R.string.cast_disconnect, notificationActionsProvider == null ? null : notificationActionsProvider.zzby().asBinder());
        }
    }

    public List<String> getActions() {
        return this.zznu;
    }

    public int[] getCompatActionIndices() {
        int[] iArr = this.zznv;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public long getSkipStepMs() {
        return this.zzlz;
    }

    public String getTargetActivityClassName() {
        return this.zznw;
    }

    public int getSmallIconDrawableResId() {
        return this.zznx;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzny;
    }

    public int getPauseDrawableResId() {
        return this.zznz;
    }

    public int getPlayDrawableResId() {
        return this.zzoa;
    }

    public int getSkipNextDrawableResId() {
        return this.zzob;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzoc;
    }

    public int getForwardDrawableResId() {
        return this.zzod;
    }

    public int getForward10DrawableResId() {
        return this.zzoe;
    }

    public int getForward30DrawableResId() {
        return this.zzof;
    }

    public int getRewindDrawableResId() {
        return this.zzog;
    }

    public int getRewind10DrawableResId() {
        return this.zzoh;
    }

    public int getRewind30DrawableResId() {
        return this.zzoi;
    }

    public int getDisconnectDrawableResId() {
        return this.zzoj;
    }

    public final int zzbz() {
        return this.zzok;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzol;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzom;
    }

    public final int zzca() {
        return this.zzon;
    }

    public final int zzcb() {
        return this.zzoo;
    }

    public final int zzcc() {
        return this.zzop;
    }

    public final int zzcd() {
        return this.zzoq;
    }

    public final int zzce() {
        return this.zzor;
    }

    public final int zzcf() {
        return this.zzos;
    }

    public final int zzcg() {
        return this.zzot;
    }

    public final int zzch() {
        return this.zzou;
    }

    public final int zzci() {
        return this.zzov;
    }

    public final int zzcj() {
        return this.zzow;
    }

    public final int zzck() {
        return this.zzox;
    }

    public final zzd zzcl() {
        return this.zzoy;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, getActions(), false);
        SafeParcelWriter.writeIntArray(parcel, 3, getCompatActionIndices(), false);
        SafeParcelWriter.writeLong(parcel, 4, getSkipStepMs());
        SafeParcelWriter.writeString(parcel, 5, getTargetActivityClassName(), false);
        SafeParcelWriter.writeInt(parcel, 6, getSmallIconDrawableResId());
        SafeParcelWriter.writeInt(parcel, 7, getStopLiveStreamDrawableResId());
        SafeParcelWriter.writeInt(parcel, 8, getPauseDrawableResId());
        SafeParcelWriter.writeInt(parcel, 9, getPlayDrawableResId());
        SafeParcelWriter.writeInt(parcel, 10, getSkipNextDrawableResId());
        SafeParcelWriter.writeInt(parcel, 11, getSkipPrevDrawableResId());
        SafeParcelWriter.writeInt(parcel, 12, getForwardDrawableResId());
        SafeParcelWriter.writeInt(parcel, 13, getForward10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 14, getForward30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 15, getRewindDrawableResId());
        SafeParcelWriter.writeInt(parcel, 16, getRewind10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 17, getRewind30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 18, getDisconnectDrawableResId());
        SafeParcelWriter.writeInt(parcel, 19, this.zzok);
        SafeParcelWriter.writeInt(parcel, 20, getCastingToDeviceStringResId());
        SafeParcelWriter.writeInt(parcel, 21, getStopLiveStreamTitleResId());
        SafeParcelWriter.writeInt(parcel, 22, this.zzon);
        SafeParcelWriter.writeInt(parcel, 23, this.zzoo);
        SafeParcelWriter.writeInt(parcel, 24, this.zzop);
        SafeParcelWriter.writeInt(parcel, 25, this.zzoq);
        SafeParcelWriter.writeInt(parcel, 26, this.zzor);
        SafeParcelWriter.writeInt(parcel, 27, this.zzos);
        SafeParcelWriter.writeInt(parcel, 28, this.zzot);
        SafeParcelWriter.writeInt(parcel, 29, this.zzou);
        SafeParcelWriter.writeInt(parcel, 30, this.zzov);
        SafeParcelWriter.writeInt(parcel, 31, this.zzow);
        SafeParcelWriter.writeInt(parcel, 32, this.zzox);
        zzd zzdVar = this.zzoy;
        SafeParcelWriter.writeIBinder(parcel, 33, zzdVar == null ? null : zzdVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
