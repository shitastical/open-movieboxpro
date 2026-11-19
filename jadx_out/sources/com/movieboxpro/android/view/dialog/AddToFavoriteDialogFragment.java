package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AddToFavoriteDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0016J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddToFavoriteDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseGridTitleListDialogFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "poster", "", "isCollect", "", "Ljava/lang/Boolean;", "onAttach", "", "context", "Landroid/content/Context;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setItemLayout", "", "initItemView", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "updateFavoriteStatus", "isFavorite", "addPlayListNum", "position", "onItemClicked", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AddToFavoriteDialogFragment extends BaseGridTitleListDialogFragment<MovieListModel.MovieListItem> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TYPE = "AddToFavoriteDialogFragment";
    private CommonItemClickListener listener;
    private String poster = "";
    private Boolean isCollect = false;

    /* JADX INFO: compiled from: AddToFavoriteDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddToFavoriteDialogFragment$Companion;", "", "<init>", "()V", "TYPE", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/AddToFavoriteDialogFragment;", "poster", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AddToFavoriteDialogFragment newInstance(String poster) {
            Intrinsics.checkNotNullParameter(poster, "poster");
            AddToFavoriteDialogFragment addToFavoriteDialogFragment = new AddToFavoriteDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("poster", poster);
            addToFavoriteDialogFragment.setArguments(bundle);
            return addToFavoriteDialogFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof CommonItemClickListener) {
            this.listener = (CommonItemClickListener) context;
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        this.poster = arguments != null ? arguments.getString("poster") : null;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment
    public int setItemLayout() {
        return R.layout.adapter_add_to_favorite_item;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment
    public void initItemView(BaseViewHolder helper, MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) helper.getView(R.id.iv_favorite);
        if (helper.getAdapterPosition() == 0) {
            if (Intrinsics.areEqual((Object) this.isCollect, (Object) true)) {
                imageView.setBackgroundResource(R.mipmap.ic_love);
            } else {
                imageView.setBackgroundResource(R.mipmap.ic_empty_love);
            }
            CommonExtKt.visible(imageView);
            helper.setText(R.id.tv_num, "");
            helper.setText(R.id.tv_name, "FAVORITE");
            Context context = getContext();
            String str = this.poster;
            View view = helper.getView(R.id.imageView);
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.ImageView");
            GlideUtils.loadWithCorner(context, str, (ImageView) view, DensityUtils.dp2px(getContext(), 4.0f), R.drawable.ic_default);
            return;
        }
        CommonExtKt.gone(imageView);
        helper.setText(R.id.tv_num, String.valueOf(item.getCount()));
        List<String> imgArr = item.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            Context context2 = getContext();
            String str2 = imgArr.get(0);
            View view2 = helper.getView(R.id.imageView);
            Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.widget.ImageView");
            GlideUtils.loadWithCorner(context2, str2, (ImageView) view2, DensityUtils.dp2px(getContext(), 4.0f), R.drawable.ic_default);
        }
        helper.setText(R.id.tv_name, item.getName());
    }

    public final void updateFavoriteStatus(boolean isFavorite) {
        this.isCollect = Boolean.valueOf(isFavorite);
    }

    public final void addPlayListNum(int position) {
        MovieListModel.MovieListItem movieListItem = getAdapter().getData().get(position);
        movieListItem.setCount(movieListItem.getCount() + 1);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment
    public void onItemClicked(int position) {
        CommonItemClickListener commonItemClickListener = this.listener;
        if (commonItemClickListener != null) {
            commonItemClickListener.onItemClicked(position, TYPE);
        }
    }
}
