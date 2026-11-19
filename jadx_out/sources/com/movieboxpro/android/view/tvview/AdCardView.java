package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import com.movieboxpro.android.event.FeaturedKeyUpEvent;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: AdCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/AdCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "updateUI", "", "item", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AdCardView extends BaseVideoCardView<Homelist.Typelist> {
    public AdCardView(Context context) {
        super(context);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.tvview.AdCardView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return AdCardView.updateUI$lambda$0(view, i, keyEvent);
            }
        });
        GlideUtils.loadCornerLandHolder(getContext(), item.poster, (ImageView) findViewById(R.id.imageView), 8);
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
        return R.layout.adapter_home_ad_item;
    }
}
