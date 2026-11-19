package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.view.tvview.MovieCardView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MovieCardPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0002H\u0014J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/MovieCardPresenter;", "Lcom/movieboxpro/android/view/tvpresenter/AbstractCardPresenter;", "Lcom/movieboxpro/android/view/tvview/MovieCardView;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreateView", "onBindViewHolder", "", "card", "cardView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieCardPresenter extends AbstractCardPresenter<MovieCardView, Homelist.Typelist> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieCardPresenter(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public MovieCardView onCreateView() {
        return new MovieCardView(getContext());
    }

    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public void onBindViewHolder(Homelist.Typelist card, MovieCardView cardView) {
        Intrinsics.checkNotNullParameter(card, "card");
        if (cardView != null) {
            cardView.updateUI(card);
        }
    }
}
