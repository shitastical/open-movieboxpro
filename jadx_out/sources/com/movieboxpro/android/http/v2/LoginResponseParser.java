package com.movieboxpro.android.http.v2;

import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.SuperChildModeActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginResponseParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0007J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/http/v2/LoginResponseParser;", "", "<init>", "()V", "parseLoginUserData", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "json", "", "parseUserDataField", "data", "rxTranslate2LoginUserData", "Lio/reactivex/ObservableTransformer;", "applySuperChildMode", "", "superChildMode", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginResponseParser {
    public static final LoginResponseParser INSTANCE = new LoginResponseParser();

    private LoginResponseParser() {
    }

    @JvmStatic
    public static final UserModel.UserData parseLoginUserData(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        JSONObject object = JSONObject.parseObject(json);
        INSTANCE.applySuperChildMode(object.getIntValue(Constant.Prefs.SUPER_CHILD_MODE));
        int intValue = object.getIntValue("code");
        if (intValue == 1) {
            UserModel.UserData userDataField = parseUserDataField(object.get("data"));
            if (userDataField != null) {
                return userDataField;
            }
            throw new ServerException(intValue, object.getString(NotificationCompat.CATEGORY_MESSAGE));
        }
        if (intValue == -10022 || intValue == -10001) {
            Http.getLoginInvalidHandler().handle();
        }
        throw new ServerException(intValue, object.getString(NotificationCompat.CATEGORY_MESSAGE));
    }

    @JvmStatic
    public static final UserModel.UserData parseUserDataField(Object data) {
        if (data == null) {
            return null;
        }
        if (data instanceof String) {
            return (UserModel.UserData) JSONObject.parseObject((String) data, UserModel.UserData.class);
        }
        return data instanceof JSONObject ? (UserModel.UserData) ((JSONObject) data).toJavaObject(UserModel.UserData.class) : (UserModel.UserData) JSONObject.parseObject(JSONObject.toJSONString(data), UserModel.UserData.class);
    }

    @JvmStatic
    public static final ObservableTransformer<String, UserModel.UserData> rxTranslate2LoginUserData() {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.http.v2.LoginResponseParser$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return LoginResponseParser.rxTranslate2LoginUserData$lambda$1(observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource rxTranslate2LoginUserData$lambda$1(Observable upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.map(new Function() { // from class: com.movieboxpro.android.http.v2.LoginResponseParser$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return LoginResponseParser.rxTranslate2LoginUserData$lambda$1$lambda$0((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UserModel.UserData rxTranslate2LoginUserData$lambda$1$lambda$0(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        return parseLoginUserData(json);
    }

    private final void applySuperChildMode(int superChildMode) {
        if (superChildMode == 1) {
            try {
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE, false)) {
                    return;
                }
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, true);
                Intent intent = new Intent(App.getContext(), (Class<?>) SuperChildModeActivity.class);
                intent.addFlags(268468224);
                App.getContext().startActivity(intent);
                return;
            } catch (RuntimeException unused) {
                return;
            }
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE, false)) {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
            Intent intent2 = new Intent(App.getContext(), (Class<?>) MainActivity.class);
            intent2.addFlags(268468224);
            App.getContext().startActivity(intent2);
        }
    }
}
