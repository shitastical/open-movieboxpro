package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [TResult] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
final class zaab<TResult> implements OnCompleteListener<TResult> {
    private final /* synthetic */ TaskCompletionSource zafp;
    private final /* synthetic */ zaz zafq;

    zaab(zaz zazVar, TaskCompletionSource taskCompletionSource) {
        this.zafq = zazVar;
        this.zafp = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<TResult> task) {
        this.zafq.zafn.remove(this.zafp);
    }
}
