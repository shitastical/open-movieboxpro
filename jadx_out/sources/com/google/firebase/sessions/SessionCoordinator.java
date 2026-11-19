package com.google.firebase.sessions;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallationsApi;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.tasks.TasksKt;

/* JADX INFO: compiled from: SessionCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/google/firebase/sessions/SessionCoordinator;", "", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "eventGDTLogger", "Lcom/google/firebase/sessions/EventGDTLoggerInterface;", "(Lcom/google/firebase/installations/FirebaseInstallationsApi;Lcom/google/firebase/sessions/EventGDTLoggerInterface;)V", "attemptLoggingSessionEvent", "", "sessionEvent", "Lcom/google/firebase/sessions/SessionEvent;", "(Lcom/google/firebase/sessions/SessionEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SessionCoordinator {
    private static final String TAG = "SessionCoordinator";
    private final EventGDTLoggerInterface eventGDTLogger;
    private final FirebaseInstallationsApi firebaseInstallations;

    /* JADX INFO: renamed from: com.google.firebase.sessions.SessionCoordinator$attemptLoggingSessionEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: SessionCoordinator.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SessionCoordinator", f = "SessionCoordinator.kt", i = {0, 0}, l = {36}, m = "attemptLoggingSessionEvent", n = {"this", "sessionEvent"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SessionCoordinator.this.attemptLoggingSessionEvent(null, this);
        }
    }

    public SessionCoordinator(FirebaseInstallationsApi firebaseInstallations, EventGDTLoggerInterface eventGDTLogger) {
        Intrinsics.checkNotNullParameter(firebaseInstallations, "firebaseInstallations");
        Intrinsics.checkNotNullParameter(eventGDTLogger, "eventGDTLogger");
        this.firebaseInstallations = firebaseInstallations;
        this.eventGDTLogger = eventGDTLogger;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public final Object attemptLoggingSessionEvent(SessionEvent sessionEvent, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        SessionEvent sessionEvent2;
        SessionInfo sessionInfo;
        Exception e;
        SessionCoordinator sessionCoordinator;
        SessionInfo sessionInfo2;
        String str;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SessionInfo sessionData = sessionEvent.getSessionData();
            try {
                Task<String> id = this.firebaseInstallations.getId();
                Intrinsics.checkNotNullExpressionValue(id, "firebaseInstallations.id");
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = sessionEvent;
                anonymousClass1.L$2 = sessionData;
                anonymousClass1.L$3 = sessionData;
                anonymousClass1.label = 1;
                Object objAwait = TasksKt.await(id, anonymousClass1);
                if (objAwait == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionEvent2 = sessionEvent;
                sessionInfo2 = sessionData;
                sessionInfo = sessionInfo2;
                obj = objAwait;
                sessionCoordinator = this;
            } catch (Exception e2) {
                sessionEvent2 = sessionEvent;
                sessionInfo = sessionData;
                e = e2;
                sessionCoordinator = this;
                Log.e(TAG, "Error getting Firebase Installation ID: " + e + ". Using an empty ID");
                str = "";
                sessionInfo2 = sessionInfo;
            }
        } else if (i == 1) {
            sessionInfo2 = (SessionInfo) anonymousClass1.L$3;
            sessionInfo = (SessionInfo) anonymousClass1.L$2;
            sessionEvent2 = (SessionEvent) anonymousClass1.L$1;
            sessionCoordinator = (SessionCoordinator) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                e = e3;
                Log.e(TAG, "Error getting Firebase Installation ID: " + e + ". Using an empty ID");
                str = "";
                sessionInfo2 = sessionInfo;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkNotNullExpressionValue(obj, "{\n        firebaseInstallations.id.await()\n      }");
        str = (String) obj;
        sessionInfo2.setFirebaseInstallationId(str);
        try {
            sessionCoordinator.eventGDTLogger.log(sessionEvent2);
            Log.i(TAG, "Successfully logged Session Start event: " + sessionEvent2.getSessionData().getSessionId());
        } catch (RuntimeException e4) {
            Log.e(TAG, "Error logging Session Start event to DataTransport: ", e4);
        }
        return Unit.INSTANCE;
    }
}
