package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.movieboxpro.android.event.FeaturedKeyUpEvent;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: LandTvCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/LandTvCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "updateUI", "", "item", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LandTvCardView extends BaseVideoCardView<Homelist.Typelist> {
    public LandTvCardView(Context context) {
        super(context);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.tvview.LandTvCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return LandTvCardView.updateUI$lambda$0(view, i, keyEvent);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.tv_item_poster);
        TextView textView = (TextView) findViewById(R.id.tv_item_season);
        Group group = (Group) findViewById(R.id.group);
        SlantedTextView slantedTextView = (SlantedTextView) findViewById(R.id.slanted_text_view);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivStar);
        TextView textView2 = (TextView) findViewById(R.id.tvImdbRating);
        setTitle((TextView) findViewById(R.id.tvTitle));
        if (item.box_type == 5) {
            if (textView != null) {
                CommonExtKt.gone(textView);
            }
            if (group != null) {
                CommonExtKt.gone(group);
            }
            if (slantedTextView != null) {
                CommonExtKt.gone(slantedTextView);
            }
            GlideUtils.loadLandHolder(getContext(), item.poster, imageView);
            TextView title = getTitle();
            if (title != null) {
                CommonExtKt.gone(title);
            }
            Intrinsics.checkNotNull(imageView2);
            CommonExtKt.gone(imageView2);
            Intrinsics.checkNotNull(textView2);
            CommonExtKt.gone(textView2);
            return;
        }
        TextView title2 = getTitle();
        if (title2 != null) {
            title2.setText(item.title);
        }
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
        if (getContext() != null) {
            if (Intrinsics.areEqual(item.id, "0")) {
                Intrinsics.checkNotNull(textView);
                CommonExtKt.gone(textView);
                GlideUtils.load(getContext(), R.drawable.ic_movie_more, imageView);
                return;
            }
            GlideUtils.loadLandHolder(getContext(), item.poster, imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean updateUI$lambda$0(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new FeaturedKeyUpEvent());
        return false;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.layout_tvlist_item2;
    }
}
