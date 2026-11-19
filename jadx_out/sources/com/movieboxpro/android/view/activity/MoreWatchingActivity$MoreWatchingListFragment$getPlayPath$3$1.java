package com.movieboxpro.android.view.activity;

import android.text.TextUtils;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.movie.MovieDetail;
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

/* JADX INFO: compiled from: MoreWatchingActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final class MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1 implements Function1<?, ObservableSource<? extends Pair<? extends BaseMediaModel, ? extends BaseMediaModel>>> {
    final /* synthetic */ int $currEpisode;
    final /* synthetic */ int $currSeason;
    final /* synthetic */ Ref.IntRef $position;

    MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1(Ref.IntRef intRef, int i, int i2) {
        this.$position = intRef;
        this.$currEpisode = i;
        this.$currSeason = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final ObservableSource<? extends Pair<BaseMediaModel, BaseMediaModel>> invoke(final BaseMediaModel model) {
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
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1.invoke$lambda$0(model, (BaseMediaModel) obj);
                }
            };
            map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1.invoke$lambda$1(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        } else {
            this.$position.element = this.$currEpisode;
            String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string2) && StringsKt.equals("0", string2, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string2;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            Observable<R> observableCompose2 = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(this.$currSeason), String.valueOf(this.$currEpisode), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1.invoke$lambda$2(model, (BaseMediaModel) obj);
                }
            };
            map = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1.invoke$lambda$3(function2, obj);
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
