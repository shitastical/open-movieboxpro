package com.movieboxpro.android.http.v2;

import com.movieboxpro.android.http.API;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: ApiV2Modules.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2Modules;", "", "<init>", "()V", "APP_CRYPTO_MODULES", "", "", "usesAppCrypto", "", ApiV2RequestInterceptor.MODULE, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiV2Modules {
    public static final ApiV2Modules INSTANCE = new ApiV2Modules();
    private static final Set<String> APP_CRYPTO_MODULES = SetsKt.setOf((Object[]) new String[]{API.Common.ADD_CAPTCHA, API.Common.ADD_QRCODE, API.USER.LOGIN, "Login_captcha", API.Common.CODE_LOGIN_CAPTCHA, "Login_google", "Login_google_v2", "Login_qrcode", API.Common.LOGIN_QRCODE, API.USER.LOGIN_THIRDPART, "Register_v3", API.USER.REGISTER_CODE, "DeleteCode", API.USER.RECOMMEND_USERNAME, "Device_delete", "Network_test", "Ip_info", "Test_network_feedback", "Test_network_url", API.Common.TEST_NETWORK, "Advertisement", "Advertisement_notify", "Advertisement_v2", "Advertisement_v3", "Agreement_list", "Agreement_status", "Agreement_transactions", "Article_list", "Common_problems", "Appstore_active", API.USER.INVITE_ACTIVE});

    private ApiV2Modules() {
    }

    public final boolean usesAppCrypto(String module) {
        return CollectionsKt.contains(APP_CRYPTO_MODULES, module);
    }
}
