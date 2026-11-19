package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.util.SparseArray;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.model.TrailerItem;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieListModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MovieDetailPresenterSelector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/MovieDetailPresenterSelector;", "Landroidx/leanback/widget/PresenterSelector;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "presenterMap", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/Presenter;", "getPresenter", "p0", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieDetailPresenterSelector extends PresenterSelector {
    public static final int ACTOR = 4;
    public static final int TYPE_MOVIE = 1;
    public static final int TYPE_PLAY_LIST = 2;
    public static final int TYPE_TRAILER = 3;
    private final Context context;
    private final SparseArray<Presenter> presenterMap;

    public MovieDetailPresenterSelector(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.presenterMap = new SparseArray<>();
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object p0) {
        if (p0 instanceof MovieListModel.MovieListItem) {
            Presenter presenter = this.presenterMap.get(2);
            if (presenter != null) {
                return presenter;
            }
            PlayListCardPresenter playListCardPresenter = new PlayListCardPresenter(this.context);
            this.presenterMap.put(2, playListCardPresenter);
            return playListCardPresenter;
        }
        if (p0 instanceof Homelist.Typelist) {
            Presenter presenter2 = this.presenterMap.get(1);
            if (presenter2 != null) {
                return presenter2;
            }
            MovieDetailCardPresenter movieDetailCardPresenter = new MovieDetailCardPresenter(this.context);
            this.presenterMap.put(1, movieDetailCardPresenter);
            return movieDetailCardPresenter;
        }
        if (p0 instanceof TrailerItem) {
            Presenter presenter3 = this.presenterMap.get(3);
            if (presenter3 != null) {
                return presenter3;
            }
            MovieTrailerPresenter movieTrailerPresenter = new MovieTrailerPresenter(this.context);
            this.presenterMap.put(3, movieTrailerPresenter);
            return movieTrailerPresenter;
        }
        if (!(p0 instanceof Actor)) {
            return null;
        }
        Presenter presenter4 = this.presenterMap.get(4);
        if (presenter4 != null) {
            return presenter4;
        }
        ActorPresenter actorPresenter = new ActorPresenter(this.context);
        this.presenterMap.put(4, actorPresenter);
        return actorPresenter;
    }
}
