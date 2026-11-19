package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import com.movieboxpro.android.model.movie.MovieListModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayListCardPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u0002H\u0014J\u001a\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/PlayListCardPresenter;", "Lcom/movieboxpro/android/view/tvpresenter/AbstractCardPresenter;", "Lcom/movieboxpro/android/view/tvpresenter/PlayListCardView;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "postEnable", "", "setPostEnable", "", "onCreateView", "onBindViewHolder", "card", "cardView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListCardPresenter extends AbstractCardPresenter<PlayListCardView, MovieListModel.MovieListItem> {
    private boolean postEnable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayListCardPresenter(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void setPostEnable() {
        this.postEnable = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public PlayListCardView onCreateView() {
        PlayListCardView playListCardView = new PlayListCardView(getContext());
        if (this.postEnable) {
            playListCardView.setPostEventEnable();
        }
        return playListCardView;
    }

    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public void onBindViewHolder(MovieListModel.MovieListItem card, PlayListCardView cardView) {
        Intrinsics.checkNotNullParameter(card, "card");
        if (cardView != null) {
            cardView.updateUI(card);
        }
    }
}
