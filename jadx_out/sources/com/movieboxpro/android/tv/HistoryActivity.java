package com.movieboxpro.android.tv;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.HistoryModel;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.R;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HistoryActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/tv/HistoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MoreMovieListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HistoryActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);
        FragmentUtils.add(getSupportFragmentManager(), new MoreMovieListFragment(), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: HistoryActivity.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0013H\u0014J\u0012\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0018\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/tv/HistoryActivity$MoreMovieListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/HistoryModel;", "", "<init>", "()V", "uid", "type", "timerDisposable", "Lio/reactivex/disposables/Disposable;", "getServiceData", "Lio/reactivex/Observable;", "onLongClick", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "clearAllHistory", "", "deleteHistory", "item", "position", "", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "timerToast", "onFocus", "onLastFocus", "isOpenLoadMore", "", "initItemLayout", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MoreMovieListFragment extends BaseListFragment<HistoryModel, String> {
        private Disposable timerDisposable;
        private String type;
        private String uid;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            Observable<String> observableMovieRecord = Http.getService().MovieRecord(API.SETTING.MOVIERECORD, App.getUserData().uid_v2, "get", "", String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize));
            Intrinsics.checkNotNullExpressionValue(observableMovieRecord, "MovieRecord(...)");
            return observableMovieRecord;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda10
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    return HistoryActivity.MoreMovieListFragment.onLongClick$lambda$2(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final boolean onLongClick$lambda$2(final MoreMovieListFragment moreMovieListFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            final HistoryModel historyModel = (HistoryModel) moreMovieListFragment.mAdapter.getItem(i);
            new MsgHintDialog.Builder(moreMovieListFragment.getContext()).setContent("Are you sure to delete it").setPositiveText("Delete").setNegativeText("Clear All").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda6
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    HistoryActivity.MoreMovieListFragment.onLongClick$lambda$2$lambda$0(this.f$0, historyModel, i);
                }
            }).setCancelActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda7
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    this.f$0.clearAllHistory();
                }
            }).create().show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onLongClick$lambda$2$lambda$0(MoreMovieListFragment moreMovieListFragment, HistoryModel historyModel, int i) {
            Intrinsics.checkNotNull(historyModel);
            moreMovieListFragment.deleteHistory(historyModel, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearAllHistory() {
            RxSubscribersKt.subscribeTo$default(HttpRequest.INSTANCE.post(this, API.SETTING.MOVIERECORD).param("type", "delall").asMsg(), new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.clearAllHistory$lambda$3(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.clearAllHistory$lambda$4(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.clearAllHistory$lambda$5(this.f$0, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit clearAllHistory$lambda$4(MoreMovieListFragment moreMovieListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit clearAllHistory$lambda$5(MoreMovieListFragment moreMovieListFragment, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.hideLoadingView();
            moreMovieListFragment.startRefresh();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit clearAllHistory$lambda$3(MoreMovieListFragment moreMovieListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        private final void deleteHistory(HistoryModel item, final int position) {
            RxSubscribersKt.subscribeTo$default(HttpRequest.INSTANCE.post(this, API.SETTING.MOVIERECORD).param("type", "del").param("mid", Integer.valueOf(item.getId())).param("box_type", Integer.valueOf(item.getBox_type())).asMsg(), new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.deleteHistory$lambda$6(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.deleteHistory$lambda$7(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.deleteHistory$lambda$8(this.f$0, position, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit deleteHistory$lambda$7(MoreMovieListFragment moreMovieListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final Unit deleteHistory$lambda$8(MoreMovieListFragment moreMovieListFragment, int i, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.hideLoadingView();
            moreMovieListFragment.mAdapter.removeAt(i);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit deleteHistory$lambda$6(MoreMovieListFragment moreMovieListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    HistoryActivity.MoreMovieListFragment.onItemClick$lambda$11(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$11(MoreMovieListFragment moreMovieListFragment, BaseQuickAdapter adapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            Object item = adapter.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.HistoryModel");
            HistoryModel historyModel = (HistoryModel) item;
            if (historyModel.getBox_type() == 1) {
                Context context = moreMovieListFragment.getContext();
                if (context != null) {
                    MovieDetailActivity.INSTANCE.starter(context, String.valueOf(historyModel.getId()), historyModel.getPoster());
                    return;
                }
                return;
            }
            Context context2 = moreMovieListFragment.getContext();
            if (context2 != null) {
                TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                String strValueOf = String.valueOf(historyModel.getId());
                String poster = historyModel.getPoster();
                Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
                companion.starter(context2, strValueOf, poster);
            }
        }

        private final void timerToast() {
            Disposable disposable = this.timerDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            Object objAs = Observable.timer(2L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.timerToast$lambda$12(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.HistoryActivity$MoreMovieListFragment$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HistoryActivity.MoreMovieListFragment.timerToast$lambda$13((Long) obj);
                }
            }, 11, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit timerToast$lambda$12(MoreMovieListFragment moreMovieListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreMovieListFragment.timerDisposable = it;
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit timerToast$lambda$13(Long l) {
            ToastUtils.showShort("Long press to delete", new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void onFocus(int position) {
            View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
            if (viewByPosition != null) {
                CommonExtKt.visible(viewByPosition);
            }
            timerToast();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void onLastFocus(int position) {
            View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
            if (viewByPosition != null) {
                CommonExtKt.invisible(viewByPosition);
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_more_video_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            String string;
            this.mClass = HistoryModel.class;
            String uid_v2 = App.getUserData().uid_v2;
            Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
            this.uid = uid_v2;
            if (arguments == null || (string = arguments.getString("type")) == null) {
                string = "";
            }
            this.type = string;
            this.mPageSize = 18;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, HistoryModel item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.tvTitle, item.getTitle());
            ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
            SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.tvTimeUpdate);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivPoster);
            TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
            GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView2, 8);
            if (item.getBox_type() == 1) {
                CommonExtKt.gone(textView);
                CommonExtKt.gone(slantedTextView);
            } else {
                CommonExtKt.gone(imageView);
            }
            CommonExtKt.gone((TextView) helper.getView(R.id.tvImdbRating));
            helper.setVisible(R.id.ivStar, false);
            helper.setVisible(R.id.tvSeasonEpisode, false);
            helper.setVisible(R.id.ivTag, false);
            helper.setVisible(R.id.tvTimeUpdate, false);
        }
    }
}
