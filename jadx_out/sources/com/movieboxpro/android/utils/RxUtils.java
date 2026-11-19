package com.movieboxpro.android.utils;

import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.SuperChildModeActivity;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0007J*\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u000b0\b\"\u0004\b\u0000\u0010\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\rH\u0007J0\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000f0\b\"\u0004\b\u0000\u0010\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\rH\u0007J'\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0016\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0013\"\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0016\"\u0004\b\u0000\u0010\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u000b0\b\"\u0004\b\u0000\u0010\u000bH\u0007¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/utils/RxUtils;", "", "<init>", "()V", "intoSuperChildMode", "", "exitChildMode", "rxTranslateMsg", "Lio/reactivex/ObservableTransformer;", "", "rxTranslate2Bean", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "rxTranslate2List", "", "buildType", "Ljava/lang/reflect/Type;", "types", "", "([Ljava/lang/reflect/Type;)Ljava/lang/reflect/Type;", "bindLifecycleOwner", "Lcom/uber/autodispose/AutoDisposeConverter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "rxSchedulerHelper", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RxUtils {
    public static final RxUtils INSTANCE = new RxUtils();

    private RxUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void intoSuperChildMode() {
        try {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                return;
            }
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, true);
            Intent intent = new Intent(App.getContext(), (Class<?>) SuperChildModeActivity.class);
            intent.addFlags(268468224);
            App.getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void exitChildMode() {
        Intent intent = new Intent(App.getContext(), (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        App.getContext().startActivity(intent);
    }

    @JvmStatic
    public static final ObservableTransformer<String, String> rxTranslateMsg() {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.RxUtils$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return RxUtils.rxTranslateMsg$lambda$0(observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource rxTranslateMsg$lambda$0(Observable observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        return observable.map(new Function<String, String>() { // from class: com.movieboxpro.android.utils.RxUtils$rxTranslateMsg$1$1
            @Override // io.reactivex.functions.Function
            public String apply(String json) {
                Intrinsics.checkNotNullParameter(json, "json");
                BaseResponse baseResponse = (BaseResponse) GsonExtensionsKt.getGSON().fromJson(json, RxUtils.buildType(BaseResponse.class, String.class));
                if (baseResponse.getSuper_child_mode() == 1) {
                    RxUtils.INSTANCE.intoSuperChildMode();
                } else if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
                    RxUtils.INSTANCE.exitChildMode();
                }
                if (baseResponse.getCode() == 1) {
                    String msg = baseResponse.getMsg();
                    Intrinsics.checkNotNull(msg);
                    return msg;
                }
                if (baseResponse.getCode() == -10001 || baseResponse.getCode() == -10022) {
                    Http.getLoginInvalidHandler().handle();
                }
                throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
            }
        });
    }

    @JvmStatic
    public static final <T> ObservableTransformer<String, T> rxTranslate2Bean(final Class<T> clazz) {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.RxUtils$$ExternalSyntheticLambda3
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return RxUtils.rxTranslate2Bean$lambda$1(clazz, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource rxTranslate2Bean$lambda$1(final Class cls, Observable upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.map(new Function<String, T>() { // from class: com.movieboxpro.android.utils.RxUtils$rxTranslate2Bean$1$1
            @Override // io.reactivex.functions.Function
            public T apply(String json) {
                Intrinsics.checkNotNullParameter(json, "json");
                BaseResponse baseResponse = (BaseResponse) GsonExtensionsKt.getGSON().fromJson(json, RxUtils.buildType(BaseResponse.class, cls));
                if (baseResponse.getSuper_child_mode() == 1) {
                    RxUtils.INSTANCE.intoSuperChildMode();
                } else if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
                    RxUtils.INSTANCE.exitChildMode();
                }
                if (baseResponse.getCode() == 1) {
                    return (T) baseResponse.getData();
                }
                if (baseResponse.getCode() == -10001 || baseResponse.getCode() == -10022) {
                    Http.getLoginInvalidHandler().handle();
                }
                throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
            }
        });
    }

    @JvmStatic
    public static final <T> ObservableTransformer<String, List<T>> rxTranslate2List(final Class<T> clazz) {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.RxUtils$$ExternalSyntheticLambda2
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return RxUtils.rxTranslate2List$lambda$2(clazz, observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource rxTranslate2List$lambda$2(final Class cls, Observable upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return upstream.map(new Function<String, List<? extends T>>() { // from class: com.movieboxpro.android.utils.RxUtils$rxTranslate2List$1$1
            @Override // io.reactivex.functions.Function
            public List<T> apply(String json) {
                Intrinsics.checkNotNullParameter(json, "json");
                Object objFromJson = GsonExtensionsKt.getGSON().fromJson(json, RxUtils.buildType(BaseResponse.class, List.class, cls));
                Intrinsics.checkNotNull(objFromJson, "null cannot be cast to non-null type com.movieboxpro.android.model.BaseResponse<java.util.ArrayList<T of com.movieboxpro.android.utils.RxUtils.rxTranslate2List>>");
                BaseResponse baseResponse = (BaseResponse) objFromJson;
                if (baseResponse.getSuper_child_mode() == 1) {
                    RxUtils.INSTANCE.intoSuperChildMode();
                } else if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
                    RxUtils.INSTANCE.exitChildMode();
                }
                if (baseResponse.getCode() == 1) {
                    Object data = baseResponse.getData();
                    Intrinsics.checkNotNull(data);
                    return (List) data;
                }
                if (baseResponse.getCode() == -10001 || baseResponse.getCode() == -10022) {
                    Http.getLoginInvalidHandler().handle();
                }
                throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
            }
        });
    }

    @JvmStatic
    public static final Type buildType(Type... types) {
        Intrinsics.checkNotNullParameter(types, "types");
        com.alibaba.fastjson.util.ParameterizedTypeImpl parameterizedTypeImpl = null;
        if (types.length > 0) {
            int length = types.length - 1;
            com.alibaba.fastjson.util.ParameterizedTypeImpl parameterizedTypeImpl2 = null;
            while (length > 0) {
                com.alibaba.fastjson.util.ParameterizedTypeImpl parameterizedTypeImpl3 = new com.alibaba.fastjson.util.ParameterizedTypeImpl(new Type[]{parameterizedTypeImpl2 != null ? parameterizedTypeImpl2 : types[length]}, null, types[length - 1]);
                length--;
                parameterizedTypeImpl2 = parameterizedTypeImpl3;
            }
            parameterizedTypeImpl = parameterizedTypeImpl2;
        }
        return parameterizedTypeImpl;
    }

    @JvmStatic
    public static final <T> AutoDisposeConverter<T> bindLifecycleOwner(LifecycleOwner lifecycleOwner) {
        AutoDisposeConverter<T> autoDisposeConverterAutoDisposable = AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, Lifecycle.Event.ON_DESTROY));
        Intrinsics.checkNotNullExpressionValue(autoDisposeConverterAutoDisposable, "autoDisposable(...)");
        return autoDisposeConverterAutoDisposable;
    }

    @JvmStatic
    public static final <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.RxUtils$$ExternalSyntheticLambda1
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return RxUtils.rxSchedulerHelper$lambda$3(observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource rxSchedulerHelper$lambda$3(Observable observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
