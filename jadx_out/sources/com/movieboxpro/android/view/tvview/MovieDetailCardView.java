package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MovieDetailCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/MovieDetailCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getLayoutId", "", "updateUI", "", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieDetailCardView extends BaseVideoCardView<Homelist.Typelist> {
    public MovieDetailCardView(Context context) {
        super(context);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.layout_movelist_item;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:46:0x0137  */
    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setTitle((TextView) findViewById(R.id.tvTitle));
        TextView title = getTitle();
        if (title != null) {
            title.setText(item.title);
        }
        ImageView imageView = (ImageView) findViewById(R.id.movie_item_poster);
        ImageView imageView2 = (ImageView) findViewById(R.id.movie_item_desc);
        ImageView imageView3 = (ImageView) findViewById(R.id.ivStar);
        TextView textView = (TextView) findViewById(R.id.tvImdbRating);
        if (Intrinsics.areEqual(item.id, "0")) {
            GlideUtils.load(getContext(), R.drawable.ic_movie_more, imageView);
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.gone(imageView2);
            Intrinsics.checkNotNull(imageView3);
            CommonExtKt.gone(imageView3);
            Intrinsics.checkNotNull(textView);
            CommonExtKt.gone(textView);
            return;
        }
        if (item.getImdb_rating() == 0.0f) {
            Intrinsics.checkNotNull(imageView3);
            CommonExtKt.gone(imageView3);
            Intrinsics.checkNotNull(textView);
            CommonExtKt.gone(textView);
        } else {
            Intrinsics.checkNotNull(imageView3);
            CommonExtKt.visible(imageView3);
            Intrinsics.checkNotNull(textView);
            CommonExtKt.visible(textView);
            textView.setText(String.valueOf(item.getImdb_rating()));
        }
        String str = item.quality_tag;
        if (str != null) {
            switch (str) {
                case "blu-ray":
                    GlideUtils.load(getContext(), R.drawable.ic_blu_ray, imageView2);
                    break;
                case "3d":
                    GlideUtils.load(getContext(), R.drawable.ic_3d, imageView2);
                    break;
                case "4k":
                    GlideUtils.load(getContext(), R.drawable.ic_4k, imageView2);
                    break;
                case "tc":
                    GlideUtils.load(getContext(), R.drawable.ic_tc, imageView2);
                    break;
                case "dvd":
                    GlideUtils.load(getContext(), R.drawable.ic_dvd, imageView2);
                    break;
                case "hdtv":
                    GlideUtils.load(getContext(), R.drawable.ic_hdtv, imageView2);
                    break;
                case "webdl":
                    GlideUtils.load(getContext(), R.drawable.ic_webdl, imageView2);
                    break;
                default:
                    Intrinsics.checkNotNull(imageView2);
                    CommonExtKt.gone(imageView2);
                    break;
            }
        } else {
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.gone(imageView2);
        }
        GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
    }
}
