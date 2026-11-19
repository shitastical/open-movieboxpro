package com.movieboxpro.android.tv.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.OnVideoListFocusListener;
import com.movieboxpro.android.model.TvShowsUpdateModel;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: TvGuideListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 '2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0003J\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\nH\u0014J\b\u0010\u001a\u001a\u00020\nH\u0014J\b\u0010\u001b\u001a\u00020\u0015H\u0014J\b\u0010\u001c\u001a\u00020\u0015H\u0014J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u0012\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0018\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/movieboxpro/android/tv/home/TvGuideListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/TvShowsUpdateModel;", "", "<init>", "()V", TvGuideListFragment.ARG_DAY, "listenerVideoList", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "setFocusUpListener", "", "refreshData", "requestListFocus", "scrollToTop", "getServiceData", "Lio/reactivex/Observable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "initItemLayout", "", "isOpenLoadMore", "", "onBackPressed", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "onRecyclerViewFocus", "initFocusUpListener", "enableEventBus", "isNeedListenerFocusUp", "gridLayoutSpan", "initScaleValue", "", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvGuideListFragment extends BaseListFragment<TvShowsUpdateModel, String> {
    private static final String ARG_DAY = "day";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String day = "";
    private OnVideoListFocusListener listenerVideoList;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int gridLayoutSpan() {
        return 2;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected float initScaleValue() {
        return 1.0f;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedListenerFocusUp() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    public final void setFocusUpListener(OnVideoListFocusListener listenerVideoList) {
        Intrinsics.checkNotNullParameter(listenerVideoList, "listenerVideoList");
        this.listenerVideoList = listenerVideoList;
    }

    public final void refreshData(String day) {
        Intrinsics.checkNotNullParameter(day, "day");
        this.day = day;
        startRefresh();
    }

    public final void requestListFocus() {
        TvRecyclerView tvRecyclerView = this.mRecyclerView;
        if (tvRecyclerView != null) {
            tvRecyclerView.requestFocus();
        }
    }

    public final void scrollToTop() {
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter;
        List data;
        if (this.mRecyclerView == null || (baseQuickAdapter = this.mAdapter) == 0 || (data = baseQuickAdapter.getData()) == null || !(!data.isEmpty())) {
            return;
        }
        this.mRecyclerView.smoothScrollToPosition(0);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        Observable<String> observableTV_guide = Http.getService().TV_guide(API.Tv.TV_GUIDE, this.day, this.mPageSize, this.mCurrentPage);
        Intrinsics.checkNotNullExpressionValue(observableTV_guide, "TV_guide(...)");
        return observableTV_guide;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.TvGuideListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TvGuideListFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$1(TvGuideListFragment tvGuideListFragment, BaseQuickAdapter adapter, View view, int i) {
        Context context;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Object item = adapter.getItem(i);
        TvShowsUpdateModel tvShowsUpdateModel = item instanceof TvShowsUpdateModel ? (TvShowsUpdateModel) item : null;
        if (tvShowsUpdateModel == null || (context = tvGuideListFragment.getContext()) == null) {
            return;
        }
        TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
        String id = tvShowsUpdateModel.getId();
        Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
        String poster = tvShowsUpdateModel.getPoster();
        Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
        companion.starter(context, id, poster);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_tv_guide_item;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 7) {
            this.mRecyclerView.smoothScrollToPosition(0);
            OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
            if (onVideoListFocusListener != null) {
                onVideoListFocusListener.onFocusUp();
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onRecyclerViewFocus() {
        OnVideoListFocusListener onVideoListFocusListener = this.listenerVideoList;
        if (onVideoListFocusListener != null) {
            onVideoListFocusListener.onFocus();
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void initFocusUpListener() {
        this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.tv.home.TvGuideListFragment$$ExternalSyntheticLambda1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return TvGuideListFragment.initFocusUpListener$lambda$2(this.f$0, i, view);
            }
        });
        int iDp2px = DensityUtils.dp2px(requireContext(), 10.0f);
        this.mRecyclerView.setSpacingWithMargins(iDp2px, iDp2px);
        int iDp2px2 = DensityUtils.dp2px(requireContext(), 12.0f);
        this.mRecyclerView.setPadding(iDp2px2, iDp2px2, iDp2px2, iDp2px2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initFocusUpListener$lambda$2(TvGuideListFragment tvGuideListFragment, int i, View view) {
        if (i != 33) {
            return false;
        }
        OnVideoListFocusListener onVideoListFocusListener = tvGuideListFragment.listenerVideoList;
        if (onVideoListFocusListener == null) {
            return true;
        }
        onVideoListFocusListener.onFocusUp();
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        String string;
        if (arguments == null || (string = arguments.getString(ARG_DAY)) == null) {
            string = "";
        }
        this.day = string;
        this.mPageSize = 16;
        this.preLoadNum = 9;
        this.mClass = TvShowsUpdateModel.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, TvShowsUpdateModel item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvName, item.getTitle());
        helper.setText(R.id.tvType, item.getCats());
        int i = R.id.tvSeasonInfo;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%s Updated", Arrays.copyOf(new Object[]{item.getSeason_episode()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        helper.setText(i, str);
        GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivPoster), 8);
    }

    /* JADX INFO: compiled from: TvGuideListFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/tv/home/TvGuideListFragment$Companion;", "", "<init>", "()V", "ARG_DAY", "", "newInstance", "Lcom/movieboxpro/android/tv/home/TvGuideListFragment;", TvGuideListFragment.ARG_DAY, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TvGuideListFragment newInstance(String day) {
            Intrinsics.checkNotNullParameter(day, "day");
            TvGuideListFragment tvGuideListFragment = new TvGuideListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TvGuideListFragment.ARG_DAY, day);
            tvGuideListFragment.setArguments(bundle);
            return tvGuideListFragment;
        }
    }
}
