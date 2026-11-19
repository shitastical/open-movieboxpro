package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.movieboxpro.android.event.FeaturedKeyUpEvent;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.tvview.BaseVideoCardView;
import com.movieboxpro.androidtv.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: PlayListCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/PlayListCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "postEnable", "", "setPostEventEnable", "", "updateUI", "item", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListCardView extends BaseVideoCardView<MovieListModel.MovieListItem> {
    private boolean postEnable;

    public PlayListCardView(Context context) {
        super(context);
    }

    public final void setPostEventEnable() {
        this.postEnable = true;
    }

    public final void updateUI(Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (this.postEnable) {
            setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.tvpresenter.PlayListCardView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return PlayListCardView.updateUI$lambda$0(view, i, keyEvent);
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.tv_num);
        TextView textView2 = (TextView) findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Intrinsics.checkNotNull(textView);
        CommonExtKt.textShadow$default(textView, item.getCount().toString(), 0, 0, 6, null);
        List<String> imgArr = item.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            GlideUtils.loadCornerLandHolder(getContext(), imgArr.get(0), imageView, DensityUtils.dp2px(getContext(), 4.0f));
        }
        textView2.setText(item.getName());
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
    public void updateUI(MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (this.postEnable) {
            setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.tvpresenter.PlayListCardView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return PlayListCardView.updateUI$lambda$1(view, i, keyEvent);
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.tv_num);
        TextView textView2 = (TextView) findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Intrinsics.checkNotNull(textView);
        CommonExtKt.textShadow$default(textView, String.valueOf(item.getCount()), 0, 0, 6, null);
        if (item.getIsmore() == 1) {
            CommonExtKt.gone(textView);
            textView2.setText("");
            GlideUtils.loadWithCorner(getContext(), Integer.valueOf(R.mipmap.ic_movie_list_more), imageView, 4);
        } else {
            List<String> imgArr = item.getImgArr();
            if (imgArr != null && imgArr.size() > 0) {
                GlideUtils.loadCornerLandHolder(getContext(), imgArr.get(0), imageView, DensityUtils.dp2px(getContext(), 4.0f));
            }
            textView2.setText(item.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean updateUI$lambda$1(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new FeaturedKeyUpEvent());
        return false;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.adapter_detail_movie_list_image_item;
    }
}
