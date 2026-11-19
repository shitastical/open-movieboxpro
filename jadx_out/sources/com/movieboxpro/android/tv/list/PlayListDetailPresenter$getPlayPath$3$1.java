package com.movieboxpro.android.tv.list;

import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: compiled from: PlayListDetailPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final class PlayListDetailPresenter$getPlayPath$3$1 implements Function1<?, ObservableSource<? extends Pair<? extends BaseMediaModel, ? extends BaseMediaModel>>> {
    final /* synthetic */ Ref.IntRef $position;

    PlayListDetailPresenter$getPlayPath$3$1(Ref.IntRef intRef) {
        this.$position = intRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public final ObservableSource<? extends Pair<BaseMediaModel, BaseMediaModel>> invoke(final BaseMediaModel model) {
        int iIntValue;
        int iIntValue2;
        String str;
        String str2;
        Observable map;
        String str3;
        String str4;
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof MovieDetail) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                str4 = "";
                str3 = str4;
            } else {
                str3 = string;
                str4 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, "", str4, str3).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$getPlayPath$3$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PlayListDetailPresenter$getPlayPath$3$1.invoke$lambda$0(model, (BaseMediaModel) obj);
                }
            };
            map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$getPlayPath$3$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return PlayListDetailPresenter$getPlayPath$3$1.invoke$lambda$1(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        } else {
            TvDetail tvDetail = (TvDetail) model;
            String str5 = tvDetail.history;
            if (str5 == null || str5.length() == 0 || Intrinsics.areEqual(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI, tvDetail.history)) {
                iIntValue = 1;
                iIntValue2 = 1;
            } else {
                Object obj = JSONObject.parse(tvDetail.history);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
                JSONObject jSONObject = (JSONObject) obj;
                Integer integer = jSONObject.getInteger(DealWatchNextService.SEASON);
                Intrinsics.checkNotNullExpressionValue(integer, "getInteger(...)");
                iIntValue = integer.intValue();
                Integer integer2 = jSONObject.getInteger(DealWatchNextService.EPISODE);
                Intrinsics.checkNotNullExpressionValue(integer2, "getInteger(...)");
                iIntValue2 = integer2.intValue();
                Integer integer3 = jSONObject.getInteger("over");
                if (integer3 != null && integer3.intValue() == 1) {
                    iIntValue2++;
                }
            }
            this.$position.element = iIntValue2;
            String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string2) && StringsKt.equals("0", string2, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string2;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            Observable<R> observableCompose2 = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(iIntValue), String.valueOf(iIntValue2), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$getPlayPath$3$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return PlayListDetailPresenter$getPlayPath$3$1.invoke$lambda$2(model, (BaseMediaModel) obj2);
                }
            };
            map = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$getPlayPath$3$1$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj2) {
                    return PlayListDetailPresenter$getPlayPath$3$1.invoke$lambda$3(function2, obj2);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair invoke$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Pair) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair invoke$lambda$0(BaseMediaModel baseMediaModel, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Pair(baseMediaModel, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair invoke$lambda$3(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Pair) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair invoke$lambda$2(BaseMediaModel baseMediaModel, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new Pair(baseMediaModel, it);
    }
}
