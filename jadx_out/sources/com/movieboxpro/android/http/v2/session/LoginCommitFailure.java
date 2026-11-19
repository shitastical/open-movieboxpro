package com.movieboxpro.android.http.v2.session;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: ApiSessionManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/LoginCommitFailure;", "", "<init>", "(Ljava/lang/String;I)V", "SESSION_CLEAR_FAILED", "IDENTITY_PERSIST_FAILED", "BLANK_SESSION_KEY", "SESSION_INSTALL_FAILED", "userMessage", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum LoginCommitFailure {
    SESSION_CLEAR_FAILED,
    IDENTITY_PERSIST_FAILED,
    BLANK_SESSION_KEY,
    SESSION_INSTALL_FAILED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: compiled from: ApiSessionManager.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoginCommitFailure.values().length];
            try {
                iArr[LoginCommitFailure.SESSION_CLEAR_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LoginCommitFailure.IDENTITY_PERSIST_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LoginCommitFailure.BLANK_SESSION_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LoginCommitFailure.SESSION_INSTALL_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<LoginCommitFailure> getEntries() {
        return $ENTRIES;
    }

    public final String userMessage() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "failed to clear previous session";
        }
        if (i == 2) {
            return "failed to save user";
        }
        if (i == 3) {
            return "session_key missing";
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return "failed to store session";
    }
}
