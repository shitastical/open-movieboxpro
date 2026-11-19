package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogAddToBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: AddToDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\u0018\u0000 <2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002;<B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010)\u001a\u00020*2\u0006\u0010#\u001a\u00020$J\u001a\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0014\u00100\u001a\u00020*2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001f02J\b\u00103\u001a\u00020*H\u0002J\b\u00104\u001a\u00020*H\u0002J\b\u00105\u001a\u00020*H\u0002J\u0010\u00106\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u00107\u001a\u00020*H\u0002J\u0010\u00108\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u00109\u001a\u00020*H\u0002J\b\u0010:\u001a\u00020*H\u0002R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR/\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0005\u001a\u0004\u0018\u00010\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\"X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddToDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogAddToBinding;", "<init>", "()V", "<set-?>", "", "watched", "getWatched", "()I", "setWatched", "(I)V", "watched$delegate", "Lkotlin/properties/ReadWriteProperty;", "boxType", "getBoxType", "setBoxType", "boxType$delegate", "favorite", "getFavorite", "setFavorite", "favorite$delegate", "", "id", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "id$delegate", "movieList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/AddToDialog$AddToListener;", "ivFavorite", "Landroid/widget/ImageView;", "tvFavorite", "Landroid/widget/TextView;", "setListener", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setMovieList", "list", "", "initData", "initFavoriteStatus", "initWatchedStatus", "changeWatched", "cancelWatch", "markWatched", "changeFavorite", "initListener", "AddToListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AddToDialog extends BaseTransparentDialogFragment<DialogAddToBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddToDialog.class, "watched", "getWatched()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddToDialog.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddToDialog.class, "favorite", "getFavorite()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AddToDialog.class, "id", "getId()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<MovieListModel.MovieListItem> adapter;

    /* JADX INFO: renamed from: boxType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty boxType;

    /* JADX INFO: renamed from: favorite$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty favorite;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty id;
    private ImageView ivFavorite;
    private AddToListener listener;
    private ArrayList<MovieListModel.MovieListItem> movieList;
    private TextView tvFavorite;

    /* JADX INFO: renamed from: watched$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty watched;

    /* JADX INFO: compiled from: AddToDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddToDialog$AddToListener;", "", "onWatchedChange", "", "watched", "", "onFavorite", "favorite", "download", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface AddToListener {
        void download();

        void onFavorite(int favorite);

        void onWatchedChange(int watched);
    }

    private final void initWatchedStatus() {
    }

    public AddToDialog() {
        super(R.layout.dialog_add_to);
        AddToDialog addToDialog = this;
        this.watched = FragmentArgsKt.arg(addToDialog);
        this.boxType = FragmentArgsKt.arg(addToDialog);
        this.favorite = FragmentArgsKt.arg(addToDialog);
        this.id = FragmentArgsKt.argOrNull(addToDialog);
        this.movieList = new ArrayList<>();
    }

    private final int getWatched() {
        return ((Number) this.watched.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWatched(int i) {
        this.watched.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    private final int getBoxType() {
        return ((Number) this.boxType.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    private final int getFavorite() {
        return ((Number) this.favorite.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFavorite(int i) {
        this.favorite.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    private final String getId() {
        return (String) this.id.getValue(this, $$delegatedProperties[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id.setValue(this, $$delegatedProperties[3], str);
    }

    public final void setListener(AddToListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: compiled from: AddToDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/AddToDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/AddToDialog;", "boxType", "", "watched", "favorite", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AddToDialog newInstance(int boxType, int watched, int favorite, String id) {
            AddToDialog addToDialog = new AddToDialog();
            addToDialog.setBoxType(boxType);
            addToDialog.setWatched(watched);
            addToDialog.setFavorite(favorite);
            addToDialog.setId(id);
            return addToDialog;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    public final void setMovieList(List<? extends MovieListModel.MovieListItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.movieList.clear();
        this.movieList.addAll(list);
    }

    private final void initData() {
        initWatchedStatus();
        this.adapter = new CommonBaseAdapter<MovieListModel.MovieListItem>(R.layout.adapter_movie_list_item, this.movieList) { // from class: com.movieboxpro.android.view.dialog.AddToDialog.initData.1
            {
                ArrayList<MovieListModel.MovieListItem> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, MovieListModel.MovieListItem item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.tvName, item.getName());
                CommonExtKt.textShadow((TextView) holder.getView(R.id.tvNum), String.valueOf(item.getCount()), 14, R.color.white);
                Context context = getContext();
                List<String> imgArr = item.getImgArr();
                Intrinsics.checkNotNullExpressionValue(imgArr, "getImgArr(...)");
                GlideUtils.loadWithCorner(context, (String) CollectionsKt.getOrNull(imgArr, 0), (ImageView) holder.getView(R.id.ivPoster), DensityUtils.dp2px(10.0f));
            }
        };
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 3);
        v7GridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.dialog.AddToDialog.initData.2
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                return position == 0 ? 3 : 1;
            }
        });
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        getBinding().recyclerView.setSpacingWithMargins(DensityUtils.dp2px(20.0f), DensityUtils.dp2px(20.0f));
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        CommonBaseAdapter<MovieListModel.MovieListItem> commonBaseAdapter = this.adapter;
        CommonBaseAdapter<MovieListModel.MovieListItem> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int i = R.layout.add_to_top_layout;
        TvRecyclerView tvRecyclerView2 = getBinding().recyclerView;
        Intrinsics.checkNotNull(tvRecyclerView2, "null cannot be cast to non-null type android.view.ViewGroup");
        View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) tvRecyclerView2, false);
        ((LinearLayout) viewInflate.findViewById(R.id.llFavorite)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.changeFavorite();
            }
        });
        View viewFindViewById = viewInflate.findViewById(R.id.ivFavorite);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.ivFavorite = (ImageView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.tvFavorite);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.tvFavorite = (TextView) viewFindViewById2;
        initFavoriteStatus();
        CommonBaseAdapter<MovieListModel.MovieListItem> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        Intrinsics.checkNotNull(viewInflate);
        BaseQuickAdapter.setHeaderView$default(commonBaseAdapter2, viewInflate, 0, 0, 6, null);
    }

    private final void initFavoriteStatus() {
        ImageView imageView = null;
        if (getFavorite() == 1) {
            ImageView imageView2 = this.ivFavorite;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivFavorite");
            } else {
                imageView = imageView2;
            }
            imageView.setImageResource(R.mipmap.ic_favorite_added);
            return;
        }
        ImageView imageView3 = this.ivFavorite;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivFavorite");
        } else {
            imageView = imageView3;
        }
        imageView.setImageResource(R.mipmap.ic_favorite);
    }

    private final void changeWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", Integer.valueOf(getBoxType())).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeWatched$lambda$1(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeWatched$lambda$2(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeWatched$lambda$3(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$2(AddToDialog addToDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$3(AddToDialog addToDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        addToDialog.setWatched(i);
        AddToListener addToListener = addToDialog.listener;
        if (addToListener != null) {
            addToListener.onWatchedChange(i);
        }
        addToDialog.initWatchedStatus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeWatched$lambda$1(AddToDialog addToDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void cancelWatch() {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_del").param("mid", getId()).param("box_type", Integer.valueOf(getBoxType())).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.cancelWatch$lambda$4(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.cancelWatch$lambda$5(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.cancelWatch$lambda$6(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$5(AddToDialog addToDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$6(AddToDialog addToDialog, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        addToDialog.setWatched(-1);
        AddToListener addToListener = addToDialog.listener;
        if (addToListener != null) {
            addToListener.onWatchedChange(addToDialog.getWatched());
        }
        addToDialog.initWatchedStatus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cancelWatch$lambda$4(AddToDialog addToDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void markWatched(final int watched) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_add").param("box_type", Integer.valueOf(getBoxType())).param("mid", getId()).param("watched", Integer.valueOf(watched)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.markWatched$lambda$7(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.markWatched$lambda$8(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.markWatched$lambda$9(this.f$0, watched, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$8(AddToDialog addToDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$9(AddToDialog addToDialog, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        addToDialog.setWatched(i);
        AddToListener addToListener = addToDialog.listener;
        if (addToListener != null) {
            addToListener.onWatchedChange(i);
        }
        addToDialog.initWatchedStatus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$7(AddToDialog addToDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeFavorite() {
        String id = getId();
        if (id == null) {
            id = "";
        }
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(id);
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post(API.Movie.MOVE_COLLECT).param("mids", getBoxType() == 1 ? arrayListArrayListOf : null).param("tids", getBoxType() == 2 ? arrayListArrayListOf : null).param("type", getFavorite() == 1 ? "del" : DealWatchNextService.ADD_ACTION).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeFavorite$lambda$10(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeFavorite$lambda$11(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.changeFavorite$lambda$12(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeFavorite$lambda$11(AddToDialog addToDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeFavorite$lambda$12(AddToDialog addToDialog, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        if (addToDialog.getFavorite() == 1) {
            addToDialog.setFavorite(0);
        } else {
            addToDialog.setFavorite(1);
        }
        addToDialog.initFavoriteStatus();
        AddToListener addToListener = addToDialog.listener;
        if (addToListener != null) {
            addToListener.onFavorite(addToDialog.getFavorite());
        }
        EventBus.getDefault().post(new RefreshFavoriteEvent());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit changeFavorite$lambda$10(AddToDialog addToDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initListener() {
        CommonBaseAdapter<MovieListModel.MovieListItem> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddToDialog.initListener$lambda$16(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16(final AddToDialog addToDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<MovieListModel.MovieListItem> commonBaseAdapter = addToDialog.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        Observable<String> observablePlaylists_video_add = Http.getService().Playlists_video_add("Playlists_video_add", App.getUserData().uid_v2, commonBaseAdapter.getItem(i).getLid(), addToDialog.getBoxType(), addToDialog.getId());
        Intrinsics.checkNotNullExpressionValue(observablePlaylists_video_add, "Playlists_video_add(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(observablePlaylists_video_add, addToDialog), new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.initListener$lambda$16$lambda$13(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.initListener$lambda$16$lambda$14(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.AddToDialog$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AddToDialog.initListener$lambda$16$lambda$15(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$16$lambda$14(AddToDialog addToDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$16$lambda$15(AddToDialog addToDialog, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Added", new Object[0]);
        addToDialog.dismissAllowingStateLoss();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$16$lambda$13(AddToDialog addToDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        addToDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }
}
