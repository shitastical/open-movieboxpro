package com.movieboxpro.android.view.fragment.account;

import com.movieboxpro.android.http.v2.session.LoginCommitFailure;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PendingLoginCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"loginCouldNotBeSavedMessage", "", "reason", "Lcom/movieboxpro/android/http/v2/session/LoginCommitFailure;", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PendingLoginCoordinatorKt {
    public static final String loginCouldNotBeSavedMessage(LoginCommitFailure reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return "Login could not be saved: " + reason.userMessage();
    }
}
