package com.movieboxpro.android.view.fragment.account;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.http.v2.session.LoginCommitFailure;
import com.movieboxpro.android.http.v2.session.LoginCommitResult;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PendingLoginCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ<\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00060\u000fJ\u0014\u0010\u0013\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003J\u0014\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003J\u0006\u0010\u0017\u001a\u00020\u0006J\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\n2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/PendingLoginCoordinator;", "", FirebaseAnalytics.Event.LOGIN, "Lkotlin/Function0;", "Lcom/movieboxpro/android/http/v2/session/LoginCommitResult;", "logout", "", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "pending", "", "finished", "start", "bindDevice", "onLoginFailure", "Lkotlin/Function1;", "Lcom/movieboxpro/android/http/v2/session/LoginCommitFailure;", "onBindFailure", "", "commit", "onCommitted", "fail", "onFailed", "cancel", "finish", "rollback", "onFinished", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PendingLoginCoordinator {
    private boolean finished;
    private final Function0<LoginCommitResult> login;
    private final Function0<Unit> logout;
    private boolean pending;

    /* JADX WARN: Multi-variable type inference failed */
    public PendingLoginCoordinator(Function0<? extends LoginCommitResult> login, Function0<Unit> logout) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(logout, "logout");
        this.login = login;
        this.logout = logout;
    }

    public final void start(Function0<Unit> bindDevice, Function1<? super LoginCommitFailure, Unit> onLoginFailure, final Function1<? super Throwable, Unit> onBindFailure) {
        Intrinsics.checkNotNullParameter(bindDevice, "bindDevice");
        Intrinsics.checkNotNullParameter(onLoginFailure, "onLoginFailure");
        Intrinsics.checkNotNullParameter(onBindFailure, "onBindFailure");
        synchronized (this) {
            if (!this.finished && !this.pending) {
                LoginCommitResult loginCommitResultInvoke = this.login.invoke();
                if (!(loginCommitResultInvoke instanceof LoginCommitResult.Success)) {
                    if (!(loginCommitResultInvoke instanceof LoginCommitResult.Failure)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    this.finished = true;
                    onLoginFailure.invoke(((LoginCommitResult.Failure) loginCommitResultInvoke).getReason());
                    return;
                }
                this.pending = true;
                Unit unit = Unit.INSTANCE;
                try {
                    bindDevice.invoke();
                } catch (Throwable th) {
                    fail(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.PendingLoginCoordinator$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PendingLoginCoordinator.start$lambda$1(onBindFailure, th);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit start$lambda$1(Function1 function1, Throwable th) {
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final void commit(Function0<Unit> onCommitted) {
        Intrinsics.checkNotNullParameter(onCommitted, "onCommitted");
        finish(false, onCommitted);
    }

    public final void fail(Function0<Unit> onFailed) {
        Intrinsics.checkNotNullParameter(onFailed, "onFailed");
        finish(true, onFailed);
    }

    public final void cancel() {
        finish(true, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.PendingLoginCoordinator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
    }

    private final void finish(boolean rollback, Function0<Unit> onFinished) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.pending && !this.finished) {
                this.pending = false;
                z = true;
                this.finished = true;
            }
        }
        if (z) {
            if (rollback) {
                this.logout.invoke();
            }
            onFinished.invoke();
        }
    }
}
