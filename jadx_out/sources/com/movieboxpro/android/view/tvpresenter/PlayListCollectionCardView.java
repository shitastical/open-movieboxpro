package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.widget.ImageView;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.tvview.BaseVideoCardView;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayListCollectionCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/PlayListCollectionCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "postEnable", "", "setPostEventEnable", "", "updateUI", "item", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListCollectionCardView extends BaseVideoCardView<MovieListModel.MovieListItem> {
    private boolean postEnable;

    public PlayListCollectionCardView(Context context) {
        super(context);
    }

    public final void setPostEventEnable() {
        this.postEnable = true;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if (item.getIsmore() == 1) {
            GlideUtils.load(getContext(), R.mipmap.ic_land_more, imageView);
        } else {
            GlideUtils.loadWithCorner(getContext(), item.getCover(), imageView, DensityUtils.dp2px(getContext(), 4.0f));
        }
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.adapter_play_list_compilations_item;
    }
}
