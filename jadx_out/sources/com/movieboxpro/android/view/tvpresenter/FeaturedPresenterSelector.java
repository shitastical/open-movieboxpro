package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.util.SparseArray;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import com.movieboxpro.android.model.common.Homelist;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedPresenterSelector.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/FeaturedPresenterSelector;", "Landroidx/leanback/widget/PresenterSelector;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "presenterMap", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/Presenter;", "getPresenter", "p0", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FeaturedPresenterSelector extends PresenterSelector {
    public static final int TYPE_AD = 5;
    public static final int TYPE_CONTINUE = 3;
    public static final int TYPE_LAND_TV = 2;
    public static final int TYPE_MOVIE = 0;
    public static final int TYPE_PLAYLIST = 4;
    public static final int TYPE_TV = 1;
    private final Context context;
    private final SparseArray<Presenter> presenterMap;

    public FeaturedPresenterSelector(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.presenterMap = new SparseArray<>();
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object p0) {
        Intrinsics.checkNotNull(p0, "null cannot be cast to non-null type com.movieboxpro.android.model.common.Homelist.Typelist");
        Homelist.Typelist typelist = (Homelist.Typelist) p0;
        MovieCardPresenter movieCardPresenter = this.presenterMap.get(typelist.viewType);
        if (movieCardPresenter == null) {
            int i = typelist.viewType;
            if (i == 0) {
                movieCardPresenter = new MovieCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            } else if (i == 1) {
                movieCardPresenter = new TvCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            } else if (i == 2) {
                movieCardPresenter = new LandTvCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            } else if (i == 3) {
                movieCardPresenter = new ContinueCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            } else if (i == 4) {
                movieCardPresenter = new HomePlayListCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            } else if (i == 5) {
                movieCardPresenter = new AdCardPresenter(this.context);
                this.presenterMap.put(typelist.viewType, movieCardPresenter);
            }
        }
        Intrinsics.checkNotNull(movieCardPresenter);
        return movieCardPresenter;
    }
}
