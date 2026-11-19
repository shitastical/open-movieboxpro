package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: ContinueCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/ContinueCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getLayoutId", "", "updateUI", "", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ContinueCardView extends BaseVideoCardView<Homelist.Typelist> {
    public ContinueCardView(Context context) {
        super(context);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.home_continue_play;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setTitle((TextView) findViewById(R.id.tvTitle));
        TextView title = getTitle();
        if (title != null) {
            title.setText(item.title);
        }
        ImageView imageView = (ImageView) findViewById(R.id.ivPoster);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivContinue);
        TextView textView = (TextView) findViewById(R.id.tvSeasonEpisode);
        if (Intrinsics.areEqual("0", item.id)) {
            Context context = getContext();
            if (context != null) {
                GlideUtils.loadWithCorner(context, Integer.valueOf(R.drawable.ic_movie_more), imageView, DensityUtils.dp2px(App.getContext(), 8.0f));
            }
            Intrinsics.checkNotNull(progressBar);
            CommonExtKt.gone(progressBar);
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.gone(imageView2);
            Intrinsics.checkNotNull(textView);
            CommonExtKt.gone(textView);
            return;
        }
        Intrinsics.checkNotNull(imageView2);
        CommonExtKt.visible(imageView2);
        Context context2 = getContext();
        if (context2 != null) {
            GlideUtils.loadCornerPortraitHolder(context2, item.poster, imageView, 8);
        }
        Intrinsics.checkNotNull(progressBar);
        CommonExtKt.visible(progressBar);
        if (item.box_type == 2) {
            if (item.getHistory() != null) {
                progressBar.setMax(item.getHistory().getRuntime() * 60);
                progressBar.setProgress(item.getHistory().getSeconds());
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("S%s E%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getHistory().getSeason()), Integer.valueOf(item.getHistory().getEpisode())}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                textView.setText(str);
                Intrinsics.checkNotNull(textView);
                CommonExtKt.visible(textView);
                return;
            }
            Intrinsics.checkNotNull(textView);
            CommonExtKt.gone(textView);
            progressBar.setMax(1);
            progressBar.setProgress(0);
            return;
        }
        Intrinsics.checkNotNull(textView);
        CommonExtKt.gone(textView);
        progressBar.setMax(item.getRuntime() * 60);
        progressBar.setProgress(item.getSeconds());
    }
}
