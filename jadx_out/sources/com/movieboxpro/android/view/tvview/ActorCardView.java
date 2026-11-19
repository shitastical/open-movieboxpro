package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.widget.TextView;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActorCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/ActorCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/Actor;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getLayoutId", "", "updateUI", "", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ActorCardView extends BaseVideoCardView<Actor> {
    public ActorCardView(Context context) {
        super(context);
        setBackground(null);
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.actor_item;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(Actor item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ((UserAvatarView) findViewById(R.id.avatarView)).setAvatar(item.getAvatar(), item.getName());
        ((TextView) findViewById(R.id.tvName)).setText(item.getName());
    }
}
