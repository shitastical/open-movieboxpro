package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SessionProvider {
    private final String category;
    private final Context zzja;
    private final zza zzjb = new zza();

    protected SessionProvider(Context context, String str) {
        this.zzja = ((Context) Preconditions.checkNotNull(context)).getApplicationContext();
        this.category = Preconditions.checkNotEmpty(str);
    }

    public abstract Session createSession(String str);

    public abstract boolean isSessionRecoverable();

    private class zza extends zzaa {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.zzz
        public final int zzs() {
            return 12451009;
        }

        @Override // com.google.android.gms.cast.framework.zzz
        public final IObjectWrapper zzk(String str) {
            Session sessionCreateSession = SessionProvider.this.createSession(str);
            if (sessionCreateSession == null) {
                return null;
            }
            return sessionCreateSession.zzaf();
        }

        @Override // com.google.android.gms.cast.framework.zzz
        public final boolean isSessionRecoverable() {
            return SessionProvider.this.isSessionRecoverable();
        }

        @Override // com.google.android.gms.cast.framework.zzz
        public final String getCategory() {
            return SessionProvider.this.getCategory();
        }
    }

    public final Context getContext() {
        return this.zzja;
    }

    public final String getCategory() {
        return this.category;
    }

    public final IBinder zzaq() {
        return this.zzjb;
    }
}
