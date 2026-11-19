package com.movieboxpro.android.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.dialog.EpisodeSeasonMapper;
import com.movieboxpro.androidtv.R;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayerEpisodeAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/adapter/PlayerEpisodeAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "playingSeason", "", "playingEpisode", "<init>", "(Ljava/util/List;II)V", "setPlayingEpisode", "", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "convert", "helper", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayerEpisodeAdapter extends BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> {
    private int playingEpisode;
    private int playingSeason;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerEpisodeAdapter(List<TvDetail.SeasonDetail> list, int i, int i2) {
        super(R.layout.adapter_player_episode_item, list);
        Intrinsics.checkNotNullParameter(list, "list");
        this.playingSeason = i;
        this.playingEpisode = i2;
    }

    public final void setPlayingEpisode(int season, int episode) {
        this.playingSeason = season;
        this.playingEpisode = episode;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, TvDetail.SeasonDetail item) {
        Integer num;
        Integer intOrNull;
        Integer num2;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivPoster);
        ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
        TextView textView = (TextView) helper.getView(R.id.tvName);
        TextView textView2 = (TextView) helper.getView(R.id.tvRate);
        TextView textView3 = (TextView) helper.getView(R.id.tvTime);
        View view = helper.getView(R.id.viewSelected);
        View view2 = helper.getView(R.id.viewPlayingBg);
        String str = item.quality_tag;
        if (str == null || str.length() == 0) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
            imageView.setImageResource(CommonUtils.getMovieTag(item.quality_tag));
        }
        GlideUtils.loadCornerLandHolder(getContext(), item.thumbs, imageView2, 4);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        int iIntValue = 0;
        String str2 = String.format("%s %s", Arrays.copyOf(new Object[]{Integer.valueOf(item.episode), item.title}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        textView.setText(str2);
        textView3.setText(item.released);
        if (EpisodeSeasonMapper.INSTANCE.isCurrentEpisode(item, this.playingSeason, this.playingEpisode)) {
            textView.setTextColor(CommonExtKt.colorInt(R.color.color_main_blue));
            CommonExtKt.visible(view2);
            CommonExtKt.visible(view);
        } else {
            textView.setTextColor(CommonExtKt.colorInt(R.color.white));
            CommonExtKt.gone(view2);
            CommonExtKt.gone(view);
        }
        String str3 = item.imdb_rating;
        if (str3 == null || str3.length() == 0) {
            textView2.setTextColor(CommonExtKt.colorInt(R.color.white_70alpha));
            textView2.setText("-.-");
        } else {
            textView2.setTextColor(CommonExtKt.colorInt(R.color.white));
            textView2.setText(item.imdb_rating);
        }
        HashMap<String, Integer> map = item.play_progress;
        if (((map == null || (num2 = map.get("over")) == null) ? -1 : num2.intValue()) == 0) {
            CommonExtKt.visible(progressBar);
            String str4 = item.runtime;
            progressBar.setMax(((str4 == null || (intOrNull = StringsKt.toIntOrNull(str4)) == null) ? 0 : intOrNull.intValue()) * 60);
            HashMap<String, Integer> map2 = item.play_progress;
            if (map2 != null && (num = map2.get("seconds")) != null) {
                iIntValue = num.intValue();
            }
            progressBar.setProgress(iIntValue);
            return;
        }
        CommonExtKt.gone(progressBar);
    }
}
