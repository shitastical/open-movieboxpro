package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TvCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/TvCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "updateUI", "", "item", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvCardView extends BaseVideoCardView<Homelist.Typelist> {
    public TvCardView(Context context) {
        super(context);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setTitle((TextView) findViewById(R.id.tvTitle));
        TextView title = getTitle();
        if (title != null) {
            title.setText(item.title);
        }
        ImageView imageView = (ImageView) findViewById(R.id.tv_item_poster);
        TextView textView = (TextView) findViewById(R.id.tv_item_season);
        Group group = (Group) findViewById(R.id.group);
        SlantedTextView slantedTextView = (SlantedTextView) findViewById(R.id.slanted_text_view);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivStar);
        TextView textView2 = (TextView) findViewById(R.id.tvImdbRating);
        textView.setText(item.season_episode);
        if (item.update_title != null && !TextUtils.isEmpty(item.update_title)) {
            Intrinsics.checkNotNull(group);
            CommonExtKt.visible(group);
            slantedTextView.setText(item.update_title);
        } else {
            Intrinsics.checkNotNull(group);
            CommonExtKt.gone(group);
        }
        if (item.getImdb_rating() == 0.0f) {
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.gone(imageView2);
            Intrinsics.checkNotNull(textView2);
            CommonExtKt.gone(textView2);
        } else {
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.visible(imageView2);
            Intrinsics.checkNotNull(textView2);
            CommonExtKt.visible(textView2);
            textView2.setText(String.valueOf(item.getImdb_rating()));
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.ivTomato);
        TextView textView3 = (TextView) findViewById(R.id.tvTomatoMeter);
        if (imageView != null) {
            if (Intrinsics.areEqual(item.id, "0")) {
                Intrinsics.checkNotNull(textView);
                CommonExtKt.gone(textView);
                GlideUtils.loadWithCorner(getContext(), Integer.valueOf(R.drawable.ic_movie_more), imageView, DensityUtils.dp2px(App.getContext(), 8.0f));
                CommonExtKt.gone(imageView2);
                CommonExtKt.gone(textView2);
                Intrinsics.checkNotNull(textView3);
                CommonExtKt.gone(textView3);
                Intrinsics.checkNotNull(imageView3);
                CommonExtKt.gone(imageView3);
                return;
            }
            imageView3.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
            if (item.getTomato_meter() == 0) {
                Intrinsics.checkNotNull(textView3);
                CommonExtKt.gone(textView3);
                Intrinsics.checkNotNull(imageView3);
                CommonExtKt.gone(imageView3);
            } else {
                Intrinsics.checkNotNull(textView3);
                CommonExtKt.visible(textView3);
                Intrinsics.checkNotNull(imageView3);
                CommonExtKt.visible(imageView3);
                textView3.setText(item.getTomato_meter() + "%");
            }
            GlideUtils.loadCornerPortraitHolder(getContext(), item.poster, imageView, 8);
        }
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.layout_tvlist_item;
    }
}
