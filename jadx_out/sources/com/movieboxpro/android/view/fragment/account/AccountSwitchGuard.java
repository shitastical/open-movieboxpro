package com.movieboxpro.android.view.fragment.account;

import j$.util.concurrent.atomic.DesugarAtomicInteger;
import j$.util.function.IntUnaryOperator$CC;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: AccountSwitchGuard.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\b\u0010\t\u001a\u00020\nH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/AccountSwitchGuard;", "", "<init>", "()V", "activeCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "enter", "", "exit", "isActive", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AccountSwitchGuard {
    public static final AccountSwitchGuard INSTANCE = new AccountSwitchGuard();
    private static final AtomicInteger activeCount = new AtomicInteger(0);

    /* JADX INFO: Access modifiers changed from: private */
    public static final int exit$lambda$0(int i) {
        if (i <= 0) {
            return 0;
        }
        return i - 1;
    }

    private AccountSwitchGuard() {
    }

    public final void enter() {
        activeCount.incrementAndGet();
    }

    public final void exit() {
        DesugarAtomicInteger.updateAndGet(activeCount, new IntUnaryOperator() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchGuard$$ExternalSyntheticLambda0
            public /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
                return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator);
            }

            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return AccountSwitchGuard.exit$lambda$0(i);
            }

            public /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
                return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator);
            }
        });
    }

    @JvmStatic
    public static final boolean isActive() {
        return activeCount.get() > 0;
    }
}
