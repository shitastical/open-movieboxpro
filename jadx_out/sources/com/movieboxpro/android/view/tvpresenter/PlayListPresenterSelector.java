package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.util.SparseArray;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import com.movieboxpro.android.model.movie.MovieListModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayListPresenterSelector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/PlayListPresenterSelector;", "Landroidx/leanback/widget/PresenterSelector;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "presenterMap", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/Presenter;", "getPresenter", "p0", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListPresenterSelector extends PresenterSelector {
    public static final int COLLECTION = 3;
    public static final int COMPILATIONS = 2;
    public static final int NORMAL = 1;
    private final Context context;
    private final SparseArray<Presenter> presenterMap;

    public PlayListPresenterSelector(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.presenterMap = new SparseArray<>();
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object p0) {
        MovieListModel.MovieListItem movieListItem = p0 instanceof MovieListModel.MovieListItem ? (MovieListModel.MovieListItem) p0 : null;
        if (movieListItem != null) {
            Presenter presenter = this.presenterMap.get(movieListItem.getItemType());
            if (presenter != null) {
                return presenter;
            }
            int itemType = movieListItem.getItemType();
            if (itemType == 1) {
                PlayListCardPresenter playListCardPresenter = new PlayListCardPresenter(this.context);
                this.presenterMap.put(movieListItem.getItemType(), playListCardPresenter);
                return playListCardPresenter;
            }
            if (itemType == 2) {
                PlayListLandCardPresenter playListLandCardPresenter = new PlayListLandCardPresenter(this.context);
                this.presenterMap.put(movieListItem.getItemType(), playListLandCardPresenter);
                return playListLandCardPresenter;
            }
            if (itemType == 3) {
                PlayListCollectionCardPresenter playListCollectionCardPresenter = new PlayListCollectionCardPresenter(this.context);
                this.presenterMap.put(movieListItem.getItemType(), playListCollectionCardPresenter);
                return playListCollectionCardPresenter;
            }
            PlayListCardPresenter playListCardPresenter2 = new PlayListCardPresenter(this.context);
            this.presenterMap.put(movieListItem.getItemType(), playListCardPresenter2);
            return playListCardPresenter2;
        }
        return new PlayListCardPresenter(this.context);
    }
}
