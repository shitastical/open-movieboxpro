package com.movieboxpro.android.http.v2.session;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginInvalidHandler.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;", "", "logout", "Lkotlin/Function0;", "", "eventPublisher", "shouldSuppress", "", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "handled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "handle", "reset", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginInvalidHandler {
    private final Function0<Unit> eventPublisher;
    private final AtomicBoolean handled;
    private final Function0<Unit> logout;
    private final Function0<Boolean> shouldSuppress;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0() {
        return false;
    }

    public LoginInvalidHandler(Function0<Unit> logout, Function0<Unit> eventPublisher, Function0<Boolean> shouldSuppress) {
        Intrinsics.checkNotNullParameter(logout, "logout");
        Intrinsics.checkNotNullParameter(eventPublisher, "eventPublisher");
        Intrinsics.checkNotNullParameter(shouldSuppress, "shouldSuppress");
        this.logout = logout;
        this.eventPublisher = eventPublisher;
        this.shouldSuppress = shouldSuppress;
        this.handled = new AtomicBoolean(false);
    }

    public /* synthetic */ LoginInvalidHandler(Function0 function0, Function0 function1, Function0 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, function1, (i & 4) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.session.LoginInvalidHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(LoginInvalidHandler._init_$lambda$0());
            }
        } : function2);
    }

    public final void handle() {
        if (!this.shouldSuppress.invoke().booleanValue() && this.handled.compareAndSet(false, true)) {
            try {
                this.logout.invoke();
                this.eventPublisher.invoke();
            } catch (RuntimeException unused) {
                this.handled.set(false);
            } catch (Throwable th) {
                this.handled.set(false);
                throw th;
            }
        }
    }

    public final void reset() {
        this.handled.set(false);
    }
}
