package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0023zaa> zaco;

    public zaa(Activity activity) {
        this(C0023zaa.zaa(activity));
    }

    private zaa(C0023zaa c0023zaa) {
        this.zaco = new WeakReference<>(c0023zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0023zaa c0023zaa = this.zaco.get();
        if (c0023zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0023zaa.zaa(runnable);
        return this;
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    static class C0023zaa extends LifecycleCallback {
        private List<Runnable> zacn;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0023zaa zaa(Activity activity) {
            C0023zaa c0023zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0023zaa = (C0023zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0023zaa.class);
                if (c0023zaa == null) {
                    c0023zaa = new C0023zaa(fragment);
                }
            }
            return c0023zaa;
        }

        private C0023zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacn = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacn.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacn;
                this.zacn = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}
