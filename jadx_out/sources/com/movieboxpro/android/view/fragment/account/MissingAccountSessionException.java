package com.movieboxpro.android.view.fragment.account;

import kotlin.Metadata;

/* JADX INFO: compiled from: AccountListHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/MissingAccountSessionException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "<init>", "()V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MissingAccountSessionException extends IllegalStateException {
    public MissingAccountSessionException() {
        super("Session expired. Remove this account and add it again.");
    }
}
