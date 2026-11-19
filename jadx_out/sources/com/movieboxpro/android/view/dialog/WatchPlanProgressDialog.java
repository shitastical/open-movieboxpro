package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.model.FavoriteEpisodeItem;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteSeasonItem;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.widget.CustomProgressView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogWatchPlanProgresBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WatchPlanProgressDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 H2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001HB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010.\u001a\u00020/2\u0006\u0010,\u001a\u00020-J\b\u00100\u001a\u00020/H\u0016J\u001a\u00101\u001a\u00020/2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0018\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\b\u00109\u001a\u00020/H\u0002J\b\u0010:\u001a\u00020/H\u0002JJ\u0010;\u001a\u00020/2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020\u00192\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010B\u001a\u00020/2\u0006\u0010 \u001a\u00020\u0019H\u0002J\u0010\u0010C\u001a\u00020/2\u0006\u0010 \u001a\u00020\u0019H\u0002J\b\u0010D\u001a\u00020/H\u0002J\b\u0010G\u001a\u00020/H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR+\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR+\u0010\u001a\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00198B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010 \u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u00198B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0010\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u001e\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00190%j\b\u0012\u0004\u0012\u00020\u0019`&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WatchPlanProgressDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogWatchPlanProgresBinding;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "Lcom/movieboxpro/android/model/FavoriteEpisodeItem;", "<set-?>", "", "id", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "id$delegate", "Lkotlin/properties/ReadWriteProperty;", "name", "getName", "setName", "name$delegate", "poster", "getPoster", "setPoster", "poster$delegate", "", DealWatchNextService.SEASON, "getSeason", "()I", "setSeason", "(I)V", "season$delegate", "watched", "getWatched", "setWatched", "watched$delegate", "watchList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "totalSeason", "totalEpisode", "watchedEpisode", "favoriteItem", "Lcom/movieboxpro/android/model/FavoriteItem;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "setListener", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "calPercent", "diliverNum", "queryMailNum", "getSeasons", "initListener", "markTv", DealWatchNextService.EPISODE, "over", "position", "favoriteSeasonItem", "Lcom/movieboxpro/android/model/FavoriteSeasonItem;", "favoriteEpisodeItem", "markWatched", "changeWatched", "cancelWatch", "first", "", "initData", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WatchPlanProgressDialog extends BaseTransparentDialogFragment<DialogWatchPlanProgresBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchPlanProgressDialog.class, "id", "getId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchPlanProgressDialog.class, "name", "getName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchPlanProgressDialog.class, "poster", "getPoster()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchPlanProgressDialog.class, DealWatchNextService.SEASON, "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchPlanProgressDialog.class, "watched", "getWatched()I", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommMultiBaseAdapter<FavoriteEpisodeItem> adapter;
    private FavoriteItem favoriteItem;
    private boolean first;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty id;
    private AddWatchPlanListener listener;

    /* JADX INFO: renamed from: name$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty name;

    /* JADX INFO: renamed from: poster$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty poster;

    /* JADX INFO: renamed from: season$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty season;
    private int totalEpisode;
    private int totalSeason;
    private final ArrayList<Integer> watchList;

    /* JADX INFO: renamed from: watched$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty watched;
    private int watchedEpisode;

    private final void initListener() {
    }

    public WatchPlanProgressDialog() {
        super(R.layout.dialog_watch_plan_progres);
        WatchPlanProgressDialog watchPlanProgressDialog = this;
        this.id = FragmentArgsKt.arg(watchPlanProgressDialog);
        this.name = FragmentArgsKt.arg(watchPlanProgressDialog);
        this.poster = FragmentArgsKt.arg(watchPlanProgressDialog);
        this.season = FragmentArgsKt.arg(watchPlanProgressDialog);
        this.watched = FragmentArgsKt.arg(watchPlanProgressDialog);
        this.watchList = new ArrayList<>();
        this.first = true;
    }

    private final String getId() {
        return (String) this.id.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getName() {
        return (String) this.name.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setName(String str) {
        this.name.setValue(this, $$delegatedProperties[1], str);
    }

    private final String getPoster() {
        return (String) this.poster.getValue(this, $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPoster(String str) {
        this.poster.setValue(this, $$delegatedProperties[2], str);
    }

    private final int getSeason() {
        return ((Number) this.season.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final int getWatched() {
        return ((Number) this.watched.getValue(this, $$delegatedProperties[4])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWatched(int i) {
        this.watched.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    public final void setListener(AddWatchPlanListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    /* JADX INFO: compiled from: WatchPlanProgressDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WatchPlanProgressDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/WatchPlanProgressDialog;", "id", "", "name", "poster", DealWatchNextService.SEASON, "", "watched", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WatchPlanProgressDialog newInstance(String id, String name, String poster, int season, int watched) {
            Intrinsics.checkNotNullParameter(id, "id");
            WatchPlanProgressDialog watchPlanProgressDialog = new WatchPlanProgressDialog();
            watchPlanProgressDialog.setId(id);
            if (name == null) {
                name = "";
            }
            watchPlanProgressDialog.setName(name);
            if (poster == null) {
                poster = "";
            }
            watchPlanProgressDialog.setPoster(poster);
            watchPlanProgressDialog.setSeason(season);
            watchPlanProgressDialog.setWatched(watched);
            return watchPlanProgressDialog;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        getSeasons();
    }

    private final String calPercent(int diliverNum, int queryMailNum) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        String str = numberFormat.format((diliverNum / queryMailNum) * 100);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final void getSeasons() {
        Observable<R> observableCompose = HttpRequest.INSTANCE.post("User_watched_plan_episode_list").param("tid", getId()).asRequest().compose(RxUtils.rxTranslate2Bean(FavoriteItem.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.getSeasons$lambda$3(this.f$0, (FavoriteItem) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getSeasons$lambda$3(WatchPlanProgressDialog watchPlanProgressDialog, FavoriteItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.favoriteItem = it;
        watchPlanProgressDialog.totalSeason = it.getEpisode_progress_list().size();
        watchPlanProgressDialog.watchList.clear();
        watchPlanProgressDialog.watchedEpisode = 0;
        watchPlanProgressDialog.totalEpisode = 0;
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setList(new ArrayList());
        FavoriteEpisodeItem favoriteEpisodeItem = new FavoriteEpisodeItem();
        favoriteEpisodeItem.setViewType(1);
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter3 = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.addData(favoriteEpisodeItem);
        List<FavoriteSeasonItem> episode_progress_list = it.getEpisode_progress_list();
        Intrinsics.checkNotNullExpressionValue(episode_progress_list, "getEpisode_progress_list(...)");
        for (FavoriteSeasonItem favoriteSeasonItem : episode_progress_list) {
            if (favoriteSeasonItem.getSeason() == watchPlanProgressDialog.getSeason()) {
                CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter4 = watchPlanProgressDialog.adapter;
                if (commMultiBaseAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter4 = null;
                }
                List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list, "getEpisode_list(...)");
                commMultiBaseAdapter4.addData(episode_list);
            }
            List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem.getEpisode_list();
            Intrinsics.checkNotNullExpressionValue(episode_list2, "getEpisode_list(...)");
            for (FavoriteEpisodeItem favoriteEpisodeItem2 : episode_list2) {
                watchPlanProgressDialog.totalEpisode++;
                if (favoriteEpisodeItem2.getOver() == 1) {
                    favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getWatchedCount() + 1);
                    watchPlanProgressDialog.watchedEpisode++;
                }
                watchPlanProgressDialog.watchList.add(Integer.valueOf(favoriteEpisodeItem2.getOver()));
            }
        }
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter5 = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter5;
        }
        commMultiBaseAdapter2.notifyItemChanged(0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markTv(String id, final String season, final String episode, final int over, final int position, FavoriteSeasonItem favoriteSeasonItem, final FavoriteEpisodeItem favoriteEpisodeItem) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("TV_over_v2").param("over", Integer.valueOf(over)).param("tid", id).param(DealWatchNextService.SEASON, season).param(DealWatchNextService.EPISODE, episode).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markTv$lambda$4(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markTv$lambda$5(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markTv$lambda$11(this.f$0, episode, season, over, favoriteEpisodeItem, position, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markTv$lambda$5(WatchPlanProgressDialog watchPlanProgressDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markTv$lambda$11(WatchPlanProgressDialog watchPlanProgressDialog, String str, String str2, int i, FavoriteEpisodeItem favoriteEpisodeItem, int i2, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = null;
        if (StringsKt.isBlank(str)) {
            FavoriteItem favoriteItem = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem = null;
            }
            FavoriteSeasonItem favoriteSeasonItem = favoriteItem.getEpisode_progress_list().get(Integer.parseInt(str2) - 1);
            watchPlanProgressDialog.watchList.clear();
            if (i == 1) {
                favoriteSeasonItem.setWatchedCount(favoriteSeasonItem.getEpisode_list().size());
                watchPlanProgressDialog.watchedEpisode += favoriteSeasonItem.getEpisode_list().size();
                CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter2 = watchPlanProgressDialog.adapter;
                if (commMultiBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter2 = null;
                }
                Iterator<T> it2 = commMultiBaseAdapter2.getData().iterator();
                while (it2.hasNext()) {
                    ((FavoriteEpisodeItem) it2.next()).setOver(1);
                }
            } else {
                favoriteSeasonItem.setWatchedCount(0);
                watchPlanProgressDialog.watchedEpisode -= favoriteSeasonItem.getEpisode_list().size();
                CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter3 = watchPlanProgressDialog.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter3 = null;
                }
                Iterator<T> it3 = commMultiBaseAdapter3.getData().iterator();
                while (it3.hasNext()) {
                    ((FavoriteEpisodeItem) it3.next()).setOver(0);
                }
            }
            FavoriteItem favoriteItem2 = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem2 = null;
            }
            List<FavoriteSeasonItem> episode_progress_list = favoriteItem2.getEpisode_progress_list();
            Intrinsics.checkNotNullExpressionValue(episode_progress_list, "getEpisode_progress_list(...)");
            Iterator<T> it4 = episode_progress_list.iterator();
            while (it4.hasNext()) {
                List<FavoriteEpisodeItem> episode_list = ((FavoriteSeasonItem) it4.next()).getEpisode_list();
                Intrinsics.checkNotNullExpressionValue(episode_list, "getEpisode_list(...)");
                Iterator<T> it5 = episode_list.iterator();
                while (it5.hasNext()) {
                    watchPlanProgressDialog.watchList.add(Integer.valueOf(((FavoriteEpisodeItem) it5.next()).getOver()));
                }
            }
            CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter4 = watchPlanProgressDialog.adapter;
            if (commMultiBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter4 = null;
            }
            commMultiBaseAdapter4.notifyDataSetChanged();
            AddWatchPlanListener addWatchPlanListener = watchPlanProgressDialog.listener;
            if (addWatchPlanListener != null) {
                addWatchPlanListener.onEpisodeOverChanged(Integer.parseInt(str2), null, i);
            }
        }
        if (favoriteEpisodeItem != null) {
            if (favoriteEpisodeItem.getOver() == 1) {
                favoriteEpisodeItem.setOver(0);
                watchPlanProgressDialog.watchedEpisode--;
                FavoriteItem favoriteItem3 = watchPlanProgressDialog.favoriteItem;
                if (favoriteItem3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                    favoriteItem3 = null;
                }
                FavoriteSeasonItem favoriteSeasonItem2 = favoriteItem3.getEpisode_progress_list().get(favoriteEpisodeItem.getSeason() - 1);
                favoriteSeasonItem2.setWatchedCount(favoriteSeasonItem2.getWatchedCount() - 1);
                watchPlanProgressDialog.watchList.set(favoriteEpisodeItem.getEpisode() - 1, 0);
            } else {
                favoriteEpisodeItem.setOver(1);
                watchPlanProgressDialog.watchedEpisode++;
                FavoriteItem favoriteItem4 = watchPlanProgressDialog.favoriteItem;
                if (favoriteItem4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                    favoriteItem4 = null;
                }
                FavoriteSeasonItem favoriteSeasonItem3 = favoriteItem4.getEpisode_progress_list().get(favoriteEpisodeItem.getSeason() - 1);
                favoriteSeasonItem3.setWatchedCount(favoriteSeasonItem3.getWatchedCount() + 1);
                watchPlanProgressDialog.watchList.set(favoriteEpisodeItem.getEpisode() - 1, 1);
            }
            CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter5 = watchPlanProgressDialog.adapter;
            if (commMultiBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter5 = null;
            }
            commMultiBaseAdapter5.notifyItemChanged(i2);
            CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter6 = watchPlanProgressDialog.adapter;
            if (commMultiBaseAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commMultiBaseAdapter = commMultiBaseAdapter6;
            }
            commMultiBaseAdapter.notifyItemChanged(0);
            AddWatchPlanListener addWatchPlanListener2 = watchPlanProgressDialog.listener;
            if (addWatchPlanListener2 != null) {
                addWatchPlanListener2.onEpisodeOverChanged(favoriteEpisodeItem.getSeason(), Integer.valueOf(favoriteEpisodeItem.getEpisode()), favoriteEpisodeItem.getOver());
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markTv$lambda$4(WatchPlanProgressDialog watchPlanProgressDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        return Unit.INSTANCE;
    }

    private final void markWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_add").param("box_type", (Object) 2).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markWatched$lambda$12(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markWatched$lambda$13(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.markWatched$lambda$14(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$13(WatchPlanProgressDialog watchPlanProgressDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$14(WatchPlanProgressDialog watchPlanProgressDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        watchPlanProgressDialog.setWatched(i);
        AddWatchPlanListener addWatchPlanListener = watchPlanProgressDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(i);
        }
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(0);
        watchPlanProgressDialog.getSeasons();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$12(WatchPlanProgressDialog watchPlanProgressDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void changeWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", (Object) 2).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.changeWatched$lambda$15(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.changeWatched$lambda$16(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.changeWatched$lambda$17(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$16(WatchPlanProgressDialog watchPlanProgressDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$17(WatchPlanProgressDialog watchPlanProgressDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        watchPlanProgressDialog.setWatched(i);
        AddWatchPlanListener addWatchPlanListener = watchPlanProgressDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(i);
        }
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(0);
        watchPlanProgressDialog.getSeasons();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$15(WatchPlanProgressDialog watchPlanProgressDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void cancelWatch() {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_del").param("mid", getId()).param("box_type", (Object) 2).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.cancelWatch$lambda$18(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.cancelWatch$lambda$19(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanProgressDialog.cancelWatch$lambda$20(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$19(WatchPlanProgressDialog watchPlanProgressDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$20(WatchPlanProgressDialog watchPlanProgressDialog, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        watchPlanProgressDialog.setWatched(-1);
        AddWatchPlanListener addWatchPlanListener = watchPlanProgressDialog.listener;
        if (addWatchPlanListener != null) {
            addWatchPlanListener.onWatchedChange(watchPlanProgressDialog.getWatched());
        }
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(0);
        watchPlanProgressDialog.getSeasons();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$18(WatchPlanProgressDialog watchPlanProgressDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanProgressDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initData() {
        this.adapter = new CommMultiBaseAdapter<>(new Function2() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return WatchPlanProgressDialog.initData$lambda$22(this.f$0, (BaseViewHolder) obj, (FavoriteEpisodeItem) obj2);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(WatchPlanProgressDialog.initData$lambda$23((FavoriteEpisodeItem) obj));
            }
        }, CollectionsKt.arrayListOf(TuplesKt.to(0, Integer.valueOf(R.layout.adapter_watch_plan_episode)), TuplesKt.to(1, Integer.valueOf(R.layout.watch_progress_top_layout))));
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 2);
        v7GridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog.initData.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                CommMultiBaseAdapter commMultiBaseAdapter = WatchPlanProgressDialog.this.adapter;
                if (commMultiBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter = null;
                }
                FavoriteEpisodeItem favoriteEpisodeItem = (FavoriteEpisodeItem) commMultiBaseAdapter.getItemOrNull(position);
                return (favoriteEpisodeItem == null || favoriteEpisodeItem.getViewType() != 0) ? 2 : 1;
            }
        });
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = this.adapter;
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.addChildClickViewIds(R.id.tvSeason, R.id.ivMark, R.id.ivMarkSeason, R.id.tvAddWaiting, R.id.tvMarkWatched);
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda14
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WatchPlanProgressDialog.initData$lambda$24(this.f$0, baseQuickAdapter, view, i);
            }
        });
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter4 = this.adapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter4 = null;
        }
        commMultiBaseAdapter4.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda15
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WatchPlanProgressDialog.initData$lambda$25(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter5 = this.adapter;
        if (commMultiBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter5;
        }
        tvRecyclerView.setAdapter(commMultiBaseAdapter2);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        getBinding().recyclerView.setSpacingWithMargins(CommonExtKt.dp2Px(16), CommonExtKt.dp2Px(16));
        getBinding().recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog.initData.6
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$22(WatchPlanProgressDialog watchPlanProgressDialog, BaseViewHolder holder, FavoriteEpisodeItem item) {
        List<FavoriteEpisodeItem> episode_list;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getViewType() == 0) {
            GlideUtils.loadCornerLandHolder(watchPlanProgressDialog.getContext(), item.getThumbs(), (ImageView) holder.getView(R.id.ivPoster), 4);
            ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressBar);
            if (item.getSeconds() != 0) {
                holder.setText(R.id.tvProgress, TimeUtils.INSTANCE.getTime(item.getSeconds()) + "/" + TimeUtils.INSTANCE.getTime(item.getRuntime() * 60));
                CommonExtKt.visible(progressBar);
                progressBar.setMax(item.getRuntime() * 60);
                progressBar.setProgress(item.getSeconds());
            } else {
                holder.setText(R.id.tvProgress, TimeUtils.INSTANCE.getTime(item.getRuntime() * 60));
                CommonExtKt.gone(progressBar);
            }
            holder.setText(R.id.tvTitle, ExifInterface.LATITUDE_SOUTH + CommonExtKt.format2(item.getSeason()) + ExifInterface.LONGITUDE_EAST + CommonExtKt.format2(item.getEpisode()) + "." + item.getTitle());
            ImageView imageView = (ImageView) holder.getView(R.id.ivMark);
            if (item.getOver() == 1) {
                imageView.setImageResource(R.mipmap.ic_blue_mark);
            } else {
                imageView.setImageResource(R.mipmap.ic_gray_mark);
            }
        } else {
            GlideUtils.load(watchPlanProgressDialog.getContext(), watchPlanProgressDialog.getPoster(), (ImageView) holder.getView(R.id.ivPoster));
            holder.setText(R.id.tvName, watchPlanProgressDialog.getName());
            holder.setText(R.id.tvSeason, "SEASON " + watchPlanProgressDialog.getSeason() + "/" + watchPlanProgressDialog.totalSeason);
            ImageView imageView2 = (ImageView) holder.getView(R.id.ivMarkSeason);
            FavoriteItem favoriteItem = watchPlanProgressDialog.favoriteItem;
            Integer numValueOf = null;
            if (favoriteItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem = null;
            }
            List<FavoriteSeasonItem> episode_progress_list = favoriteItem.getEpisode_progress_list();
            Intrinsics.checkNotNullExpressionValue(episode_progress_list, "getEpisode_progress_list(...)");
            FavoriteSeasonItem favoriteSeasonItem = (FavoriteSeasonItem) CollectionsKt.getOrNull(episode_progress_list, watchPlanProgressDialog.getSeason() - 1);
            Integer numValueOf2 = favoriteSeasonItem != null ? Integer.valueOf(favoriteSeasonItem.getWatchedCount()) : null;
            FavoriteItem favoriteItem2 = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem2 = null;
            }
            List<FavoriteSeasonItem> episode_progress_list2 = favoriteItem2.getEpisode_progress_list();
            Intrinsics.checkNotNullExpressionValue(episode_progress_list2, "getEpisode_progress_list(...)");
            FavoriteSeasonItem favoriteSeasonItem2 = (FavoriteSeasonItem) CollectionsKt.getOrNull(episode_progress_list2, watchPlanProgressDialog.getSeason() - 1);
            if (favoriteSeasonItem2 != null && (episode_list = favoriteSeasonItem2.getEpisode_list()) != null) {
                numValueOf = Integer.valueOf(episode_list.size());
            }
            if (Intrinsics.areEqual(numValueOf2, numValueOf)) {
                imageView2.setImageResource(R.mipmap.ic_blue_mark);
            } else {
                imageView2.setImageResource(R.mipmap.ic_gray_mark);
            }
            ((CustomProgressView) holder.getView(R.id.progressView)).setEpisodes(watchPlanProgressDialog.watchList);
            holder.setText(R.id.tvProgress, watchPlanProgressDialog.calPercent(watchPlanProgressDialog.watchedEpisode, watchPlanProgressDialog.totalEpisode) + "% WATCHED " + watchPlanProgressDialog.watchedEpisode + "/" + watchPlanProgressDialog.totalEpisode + " EPISODES");
            final TextView textView = (TextView) holder.getView(R.id.tvAddWaiting);
            TextView textView2 = (TextView) holder.getView(R.id.tvMarkWatched);
            if (watchPlanProgressDialog.first) {
                watchPlanProgressDialog.first = false;
                Observable<Long> observableTimer = Observable.timer(200L, TimeUnit.MILLISECONDS);
                Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
                RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, watchPlanProgressDialog), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanProgressDialog.initData$lambda$22$lambda$21(textView, (Long) obj);
                    }
                }, 15, null);
            }
            if (watchPlanProgressDialog.getWatched() == -1) {
                textView.setText("ADD TO WAITING");
                textView2.setText("MARK AS WATCHED");
            } else if (watchPlanProgressDialog.getWatched() == 0) {
                textView.setText("MARK AS WATCHED");
                textView2.setText("REMOVE FROM WAITING");
            } else if (watchPlanProgressDialog.getWatched() == 1) {
                textView.setText("ADD TO WAITING");
                textView2.setText("REMOVE FROM WATCHED");
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$22$lambda$21(TextView textView, Long l) {
        textView.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int initData$lambda$23(FavoriteEpisodeItem t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return t.getViewType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$24(WatchPlanProgressDialog watchPlanProgressDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        FavoriteEpisodeItem item = commMultiBaseAdapter.getItem(i);
        watchPlanProgressDialog.markTv(watchPlanProgressDialog.getId(), String.valueOf(item.getSeason()), String.valueOf(item.getEpisode()), item.getOver() == 1 ? 0 : 1, i, null, item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$25(final WatchPlanProgressDialog watchPlanProgressDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "view");
        CommMultiBaseAdapter<FavoriteEpisodeItem> commMultiBaseAdapter = watchPlanProgressDialog.adapter;
        FavoriteItem favoriteItem = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.getItem(i);
        int id = view.getId();
        if (id == R.id.ivMarkSeason) {
            FavoriteItem favoriteItem2 = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem2 = null;
            }
            int watchedCount = favoriteItem2.getEpisode_progress_list().get(watchPlanProgressDialog.getSeason() - 1).getWatchedCount();
            FavoriteItem favoriteItem3 = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                favoriteItem3 = null;
            }
            int i2 = watchedCount == favoriteItem3.getEpisode_progress_list().get(watchPlanProgressDialog.getSeason() - 1).getEpisode_list().size() ? 0 : 1;
            String id2 = watchPlanProgressDialog.getId();
            String strValueOf = String.valueOf(watchPlanProgressDialog.getSeason());
            FavoriteItem favoriteItem4 = watchPlanProgressDialog.favoriteItem;
            if (favoriteItem4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
            } else {
                favoriteItem = favoriteItem4;
            }
            watchPlanProgressDialog.markTv(id2, strValueOf, "", i2, i, favoriteItem.getEpisode_progress_list().get(watchPlanProgressDialog.getSeason() - 1), null);
            return;
        }
        if (id == R.id.tvAddWaiting) {
            if (watchPlanProgressDialog.getWatched() == -1) {
                watchPlanProgressDialog.markWatched(0);
                return;
            } else if (watchPlanProgressDialog.getWatched() == 0) {
                watchPlanProgressDialog.changeWatched(1);
                return;
            } else {
                if (watchPlanProgressDialog.getWatched() == 1) {
                    watchPlanProgressDialog.changeWatched(0);
                    return;
                }
                return;
            }
        }
        if (id == R.id.tvMarkWatched) {
            if (watchPlanProgressDialog.getWatched() == -1) {
                watchPlanProgressDialog.markWatched(1);
                return;
            } else if (watchPlanProgressDialog.getWatched() == 0) {
                watchPlanProgressDialog.cancelWatch();
                return;
            } else {
                if (watchPlanProgressDialog.getWatched() == 1) {
                    watchPlanProgressDialog.cancelWatch();
                    return;
                }
                return;
            }
        }
        if (id == R.id.tvSeason) {
            ChooseSeasonDialog chooseSeasonDialogNewInstance = ChooseSeasonDialog.INSTANCE.newInstance(watchPlanProgressDialog.totalSeason, watchPlanProgressDialog.getSeason());
            chooseSeasonDialogNewInstance.setListener(new ChooseSeasonDialog.ChooseSeasonListener() { // from class: com.movieboxpro.android.view.dialog.WatchPlanProgressDialog$initData$5$1
                @Override // com.movieboxpro.android.view.dialog.ChooseSeasonDialog.ChooseSeasonListener
                public void onChooseSeason(int season) {
                    this.this$0.setSeason(season);
                    CommMultiBaseAdapter commMultiBaseAdapter2 = this.this$0.adapter;
                    CommMultiBaseAdapter commMultiBaseAdapter3 = null;
                    if (commMultiBaseAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter2 = null;
                    }
                    commMultiBaseAdapter2.setList(new ArrayList());
                    FavoriteEpisodeItem favoriteEpisodeItem = new FavoriteEpisodeItem();
                    favoriteEpisodeItem.setViewType(1);
                    this.this$0.watchedEpisode = 0;
                    this.this$0.totalEpisode = 0;
                    this.this$0.watchList.clear();
                    CommMultiBaseAdapter commMultiBaseAdapter4 = this.this$0.adapter;
                    if (commMultiBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter4 = null;
                    }
                    commMultiBaseAdapter4.addData(favoriteEpisodeItem);
                    FavoriteItem favoriteItem5 = this.this$0.favoriteItem;
                    if (favoriteItem5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("favoriteItem");
                        favoriteItem5 = null;
                    }
                    List<FavoriteSeasonItem> episode_progress_list = favoriteItem5.getEpisode_progress_list();
                    Intrinsics.checkNotNullExpressionValue(episode_progress_list, "getEpisode_progress_list(...)");
                    WatchPlanProgressDialog watchPlanProgressDialog2 = this.this$0;
                    for (FavoriteSeasonItem favoriteSeasonItem : episode_progress_list) {
                        if (favoriteSeasonItem.getSeason() == season) {
                            CommMultiBaseAdapter commMultiBaseAdapter5 = watchPlanProgressDialog2.adapter;
                            if (commMultiBaseAdapter5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                commMultiBaseAdapter5 = null;
                            }
                            List<FavoriteEpisodeItem> episode_list = favoriteSeasonItem.getEpisode_list();
                            Intrinsics.checkNotNullExpressionValue(episode_list, "getEpisode_list(...)");
                            commMultiBaseAdapter5.addData((Collection) episode_list);
                        }
                        List<FavoriteEpisodeItem> episode_list2 = favoriteSeasonItem.getEpisode_list();
                        Intrinsics.checkNotNullExpressionValue(episode_list2, "getEpisode_list(...)");
                        for (FavoriteEpisodeItem favoriteEpisodeItem2 : episode_list2) {
                            watchPlanProgressDialog2.totalEpisode++;
                            if (favoriteEpisodeItem2.getOver() == 1) {
                                watchPlanProgressDialog2.watchedEpisode++;
                            }
                            watchPlanProgressDialog2.watchList.add(Integer.valueOf(favoriteEpisodeItem2.getOver()));
                        }
                    }
                    CommMultiBaseAdapter commMultiBaseAdapter6 = this.this$0.adapter;
                    if (commMultiBaseAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commMultiBaseAdapter3 = commMultiBaseAdapter6;
                    }
                    commMultiBaseAdapter3.notifyItemChanged(0);
                }
            });
            chooseSeasonDialogNewInstance.show(watchPlanProgressDialog.getChildFragmentManager(), "ChooseSeasonDialog");
        }
    }
}
