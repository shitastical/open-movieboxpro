package com.movieboxpro.android.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.androidtv.databinding.LayoutDetailMetaRowBinding;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DetailMetaInfoBinder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u001a\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/utils/DetailMetaInfoBinder;", "", "<init>", "()V", "SPACING_TV", "", "SPACING_MOVIE", "SPACING_MOVIE_GENRE", "ICON_TEXT_SPACING", "bindTvDetail", "", "row", "Lcom/movieboxpro/androidtv/databinding/LayoutDetailMetaRowBinding;", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "bindMovieDetail", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "applyItemSpacing", "spacingDp", "bindTomato", "tomatoMeter", "bindAudienceScore", "audienceScore", "bindImdbRating", "imdbRating", "", "setMarginStart", "view", "Landroid/view/View;", "marginStartPx", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DetailMetaInfoBinder {
    private static final int ICON_TEXT_SPACING = 6;
    public static final DetailMetaInfoBinder INSTANCE = new DetailMetaInfoBinder();
    private static final int SPACING_MOVIE = 16;
    private static final int SPACING_MOVIE_GENRE = 24;
    private static final int SPACING_TV = 12;

    private DetailMetaInfoBinder() {
    }

    public final void bindTvDetail(LayoutDetailMetaRowBinding row, TvDetail tvDetail) {
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
        row.tvMetaGenre.setVisibility(8);
        TextView textView = row.tvMetaYear;
        String str = tvDetail.year;
        String string = str != null ? str.toString() : null;
        if (string == null) {
            string = "";
        }
        textView.setText(string);
        TextView textView2 = row.tvMetaRating;
        String str2 = tvDetail.content_rating;
        String string2 = str2 != null ? str2.toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        textView2.setText(string2);
        TextView textView3 = row.tvMetaExtra;
        String str3 = tvDetail.season_episode;
        String string3 = str3 != null ? str3.toString() : null;
        textView3.setText(string3 != null ? string3 : "");
        TextView tvMetaYear = row.tvMetaYear;
        Intrinsics.checkNotNullExpressionValue(tvMetaYear, "tvMetaYear");
        setMarginStart(tvMetaYear, 0);
        applyItemSpacing(row, 12);
        bindTomato(row, tvDetail.tomato_meter);
        bindAudienceScore(row, tvDetail.getAudience_score());
        bindImdbRating(row, tvDetail.imdb_rating);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0056  */
    public final void bindMovieDetail(LayoutDetailMetaRowBinding row, MovieDetail movieDetail) {
        String upperCase;
        List listSplit$default;
        Object next;
        Intrinsics.checkNotNullParameter(row, "row");
        Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
        String str = movieDetail.cats;
        if (str == null || (listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null)) == null) {
            upperCase = null;
        } else {
            Iterator it = listSplit$default.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (StringsKt.isBlank((String) next));
            String str2 = (String) next;
            if (str2 != null) {
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                upperCase = str2.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            } else {
                upperCase = null;
            }
        }
        if (upperCase == null) {
            upperCase = "";
        }
        String str3 = upperCase;
        if (str3.length() > 0) {
            row.tvMetaGenre.setVisibility(0);
            row.tvMetaGenre.setText(str3);
            TextView tvMetaYear = row.tvMetaYear;
            Intrinsics.checkNotNullExpressionValue(tvMetaYear, "tvMetaYear");
            setMarginStart(tvMetaYear, CommonExtKt.dp2Px(24));
        } else {
            row.tvMetaGenre.setVisibility(8);
            TextView tvMetaYear2 = row.tvMetaYear;
            Intrinsics.checkNotNullExpressionValue(tvMetaYear2, "tvMetaYear");
            setMarginStart(tvMetaYear2, 0);
        }
        TextView textView = row.tvMetaYear;
        String str4 = movieDetail.year;
        String string = str4 != null ? str4.toString() : null;
        if (string == null) {
            string = "";
        }
        textView.setText(string);
        TextView textView2 = row.tvMetaRating;
        String str5 = movieDetail.content_rating;
        String string2 = str5 != null ? str5.toString() : null;
        textView2.setText(string2 != null ? string2 : "");
        row.tvMetaExtra.setText(movieDetail.runtime + "min");
        applyItemSpacing(row, 16);
        bindTomato(row, movieDetail.tomato_meter);
        bindAudienceScore(row, movieDetail.getAudience_score());
        bindImdbRating(row, movieDetail.imdb_rating);
    }

    private final void applyItemSpacing(LayoutDetailMetaRowBinding row, int spacingDp) {
        int iDp2Px = CommonExtKt.dp2Px(spacingDp);
        int iDp2Px2 = CommonExtKt.dp2Px(6);
        TextView tvMetaRating = row.tvMetaRating;
        Intrinsics.checkNotNullExpressionValue(tvMetaRating, "tvMetaRating");
        setMarginStart(tvMetaRating, iDp2Px);
        TextView tvMetaExtra = row.tvMetaExtra;
        Intrinsics.checkNotNullExpressionValue(tvMetaExtra, "tvMetaExtra");
        setMarginStart(tvMetaExtra, iDp2Px);
        ImageView ivTomato = row.ivTomato;
        Intrinsics.checkNotNullExpressionValue(ivTomato, "ivTomato");
        setMarginStart(ivTomato, iDp2Px);
        TextView tvTomatoMeter = row.tvTomatoMeter;
        Intrinsics.checkNotNullExpressionValue(tvTomatoMeter, "tvTomatoMeter");
        setMarginStart(tvTomatoMeter, iDp2Px2);
        ImageView ivAudienceScore = row.ivAudienceScore;
        Intrinsics.checkNotNullExpressionValue(ivAudienceScore, "ivAudienceScore");
        setMarginStart(ivAudienceScore, iDp2Px);
        TextView tvAudienceScore = row.tvAudienceScore;
        Intrinsics.checkNotNullExpressionValue(tvAudienceScore, "tvAudienceScore");
        setMarginStart(tvAudienceScore, iDp2Px2);
        ImageView ivStar = row.ivStar;
        Intrinsics.checkNotNullExpressionValue(ivStar, "ivStar");
        setMarginStart(ivStar, iDp2Px);
        TextView tvImdbRating = row.tvImdbRating;
        Intrinsics.checkNotNullExpressionValue(tvImdbRating, "tvImdbRating");
        setMarginStart(tvImdbRating, iDp2Px2);
    }

    private final void bindTomato(LayoutDetailMetaRowBinding row, int tomatoMeter) {
        row.ivTomato.setImageResource(CommonUtils.getTomatoImg(tomatoMeter));
        if (tomatoMeter == 0) {
            ImageView ivTomato = row.ivTomato;
            Intrinsics.checkNotNullExpressionValue(ivTomato, "ivTomato");
            CommonExtKt.gone(ivTomato);
            TextView tvTomatoMeter = row.tvTomatoMeter;
            Intrinsics.checkNotNullExpressionValue(tvTomatoMeter, "tvTomatoMeter");
            CommonExtKt.gone(tvTomatoMeter);
            return;
        }
        ImageView ivTomato2 = row.ivTomato;
        Intrinsics.checkNotNullExpressionValue(ivTomato2, "ivTomato");
        CommonExtKt.visible(ivTomato2);
        TextView tvTomatoMeter2 = row.tvTomatoMeter;
        Intrinsics.checkNotNullExpressionValue(tvTomatoMeter2, "tvTomatoMeter");
        CommonExtKt.visible(tvTomatoMeter2);
        row.tvTomatoMeter.setText(tomatoMeter + "%");
    }

    private final void bindAudienceScore(LayoutDetailMetaRowBinding row, int audienceScore) {
        String str;
        row.ivAudienceScore.setImageResource(CommonUtils.getTomatoScore(audienceScore));
        ImageView ivAudienceScore = row.ivAudienceScore;
        Intrinsics.checkNotNullExpressionValue(ivAudienceScore, "ivAudienceScore");
        CommonExtKt.visible(ivAudienceScore);
        TextView tvAudienceScore = row.tvAudienceScore;
        Intrinsics.checkNotNullExpressionValue(tvAudienceScore, "tvAudienceScore");
        CommonExtKt.visible(tvAudienceScore);
        TextView textView = row.tvAudienceScore;
        if (audienceScore == 0) {
            str = "-.-%";
        } else {
            str = audienceScore + "%";
        }
        textView.setText(str);
    }

    private final void bindImdbRating(LayoutDetailMetaRowBinding row, String imdbRating) {
        String string = imdbRating != null ? imdbRating.toString() : null;
        if (string == null) {
            string = "";
        }
        String str = string;
        if (str.length() == 0 || Intrinsics.areEqual(string, "0") || Intrinsics.areEqual(string, IdManager.DEFAULT_VERSION_NAME)) {
            ImageView ivStar = row.ivStar;
            Intrinsics.checkNotNullExpressionValue(ivStar, "ivStar");
            CommonExtKt.gone(ivStar);
            TextView tvImdbRating = row.tvImdbRating;
            Intrinsics.checkNotNullExpressionValue(tvImdbRating, "tvImdbRating");
            CommonExtKt.gone(tvImdbRating);
            return;
        }
        ImageView ivStar2 = row.ivStar;
        Intrinsics.checkNotNullExpressionValue(ivStar2, "ivStar");
        CommonExtKt.visible(ivStar2);
        TextView tvImdbRating2 = row.tvImdbRating;
        Intrinsics.checkNotNullExpressionValue(tvImdbRating2, "tvImdbRating");
        CommonExtKt.visible(tvImdbRating2);
        row.tvImdbRating.setText(str);
    }

    private final void setMarginStart(View view, int marginStartPx) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.setMarginStart(marginStartPx);
        view.setLayoutParams(marginLayoutParams);
    }
}
