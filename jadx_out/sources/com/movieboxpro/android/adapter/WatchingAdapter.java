package com.movieboxpro.android.adapter;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WatchingAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0005j\b\u0012\u0004\u0012\u00020\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/adapter/WatchingAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "convert", "", "holder", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WatchingAdapter extends BaseQuickAdapter<FavoriteItem, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchingAdapter(ArrayList<FavoriteItem> list) {
        super(R.layout.adapter_watching_child_item, list);
        Intrinsics.checkNotNullParameter(list, "list");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, FavoriteItem item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressBar);
        ImageView imageView = (ImageView) holder.getView(R.id.ivPoster);
        TextView textView = (TextView) holder.getView(R.id.tvTitle);
        ImageView imageView2 = (ImageView) holder.getView(R.id.ivContinue);
        if (item.getBox_type() == 1) {
            progressBar.setMax(item.getRuntime() * 60);
            progressBar.setProgress(item.getSeconds());
        } else {
            FavoriteItem.LastEpisode last_episode = item.getLast_episode();
            progressBar.setMax((last_episode != null ? last_episode.getRuntime() : 0) * 60);
            FavoriteItem.LastEpisode last_episode2 = item.getLast_episode();
            progressBar.setProgress(last_episode2 != null ? last_episode2.getSeconds() : 0);
        }
        if (item.getLast_episode() != null) {
            textView.setText(ExifInterface.LATITUDE_SOUTH + item.getLast_episode().getSeason() + ExifInterface.LONGITUDE_EAST + item.getLast_episode().getEpisode() + "-" + item.getLast_episode().getTitle());
        } else {
            textView.setText(item.getTitle());
        }
        if (holder.getAdapterPosition() == 10) {
            CommonExtKt.gone(imageView2);
            CommonExtKt.gone(textView);
            CommonExtKt.gone(progressBar);
            GlideUtils.load(getContext(), R.drawable.ic_movie_more, imageView);
            return;
        }
        CommonExtKt.visible(imageView2);
        CommonExtKt.visible(textView);
        CommonExtKt.visible(progressBar);
        GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView, 8);
    }
}
