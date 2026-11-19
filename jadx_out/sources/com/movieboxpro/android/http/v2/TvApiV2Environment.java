package com.movieboxpro.android.http.v2;

import android.os.Build;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.androidtv.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApiV2Environment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/http/v2/TvApiV2Environment;", "Lcom/movieboxpro/android/http/v2/ApiV2Environment;", "<init>", "()V", "snapshot", "Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvApiV2Environment implements ApiV2Environment {
    public static final TvApiV2Environment INSTANCE = new TvApiV2Environment();

    private TvApiV2Environment() {
    }

    @Override // com.movieboxpro.android.http.v2.ApiV2Environment
    public ApiV2EnvironmentSnapshot snapshot() {
        boolean zIsLogin = App.isLogin();
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
        String deviceLang = App.deviceLang;
        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
        String uniqueId = SystemUtils.getUniqueId(App.getContext());
        Intrinsics.checkNotNullExpressionValue(uniqueId, "getUniqueId(...)");
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        String BRAND = Build.BRAND;
        Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
        String packageName = App.packageName;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new ApiV2EnvironmentSnapshot(BuildConfig.VERSION_NAME, BuildConfig.CHANNEL, deviceLang, uniqueId, MODEL, BRAND, ApiV2RequestInterceptor.PLATFORM, packageName, PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false), zIsLogin, zIsLogin ? userData.uid_v2 : null, zIsLogin ? userData.uid : null);
    }
}
