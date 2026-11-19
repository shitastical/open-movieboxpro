package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.base.mvp.BaseRowsMvpFragment;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.event.FileDeleteEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.fragment.DownloadedContract;
import com.movieboxpro.android.view.fragment.DownloadedPresenter;
import com.movieboxpro.android.view.tvpresenter.DownloadedTvCardPresenter;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityDownloadedTvBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DownloadedTvActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 62\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u000267B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0014J\b\u0010\u001f\u001a\u00020\u0018H\u0014J\b\u0010 \u001a\u00020\u0018H\u0014J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\tH\u0002J\b\u0010#\u001a\u00020\u0013H\u0014J\u0010\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020&H\u0007J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\tH\u0002J\"\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000f2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0016\u0010-\u001a\u00020\u00182\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0/H\u0016J\u0016\u00100\u001a\u00020\u00182\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\t01H\u0016J\b\u00102\u001a\u00020\u0013H\u0014J\b\u00103\u001a\u00020\u0018H\u0014J\b\u00104\u001a\u00020\u000fH\u0014J\b\u00105\u001a\u00020\u0002H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0015\u0010\u0016¨\u00068"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/DownloadedTvPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityDownloadedTvBinding;", "Lcom/movieboxpro/android/view/activity/DownloadedTvContract$View;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "focusBorder", "Lcom/owen/focus/FocusBorder;", "id", "", "selectCount", "", "selectSize", "", "value", "", "edit", "setEdit", "(Z)V", "initView", "", "focusScale", "v", "Landroid/view/View;", "scale", "", "initData", "onResume", "initListener", "checkOnlineNum", "item", "enableEventBus", "onFileDelete", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/FileDeleteEvent;", "goTvPlayer", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "showTv", "list", "", "deleteComplete", "", "isNeedLoadData", "requestData", "getLayoutResId", "bindPresenter", "Companion", "DownloadedFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadedTvActivity extends BaseMvpActivity<DownloadedTvPresenter, ActivityDownloadedTvBinding> implements DownloadedTvContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<DownloadFile> adapter;
    private boolean edit;
    private FocusBorder focusBorder;
    private String id = "";
    private int selectCount;
    private long selectSize;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEdit(boolean z) {
        this.edit = z;
        if (z) {
            LinearLayout llDone = ((ActivityDownloadedTvBinding) this.binding).llDone;
            Intrinsics.checkNotNullExpressionValue(llDone, "llDone");
            CommonExtKt.visible(llDone);
            LinearLayout llDelete = ((ActivityDownloadedTvBinding) this.binding).llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete, "llDelete");
            CommonExtKt.visible(llDelete);
        } else {
            LinearLayout llDone2 = ((ActivityDownloadedTvBinding) this.binding).llDone;
            Intrinsics.checkNotNullExpressionValue(llDone2, "llDone");
            CommonExtKt.gone(llDone2);
            LinearLayout llDelete2 = ((ActivityDownloadedTvBinding) this.binding).llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete2, "llDelete");
            CommonExtKt.gone(llDelete2);
        }
        if (this.selectCount > 1) {
            ((ActivityDownloadedTvBinding) this.binding).tvDelete.setText("Delete " + this.selectCount + " videos (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        } else {
            ((ActivityDownloadedTvBinding) this.binding).tvDelete.setText("Delete " + this.selectCount + " video (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        }
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.notifyDataSetChanged();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.focusBorder = focusBorderBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.focusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        this.adapter = new CommonBaseAdapter<DownloadFile>(R.layout.adapter_downloaded_tv_card_view, new ArrayList()) { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity.initData.1
            {
                ArrayList<DownloadFile> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DownloadFile item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvSize, FileUtils.byte2FitMemorySize(item.getSize()));
                helper.setText(R.id.tvTitle, ExifInterface.LATITUDE_SOUTH + item.getSeason() + ":E" + item.getEpisode() + "." + item.getEpisodeName());
                CheckBox checkBox = (CheckBox) helper.getView(R.id.checkbox);
                if (DownloadedTvActivity.this.edit) {
                    CommonExtKt.visible(checkBox);
                } else {
                    CommonExtKt.gone(checkBox);
                }
                ((TextView) helper.getView(R.id.tvDevice)).setText(item.getPath());
                checkBox.setChecked(item.isCheck());
                GlideUtils.load((Activity) DownloadedTvActivity.this, item.getThumb(), (ImageView) helper.getView(R.id.imageView), R.drawable.ic_land_loading_holder);
                View view = helper.getView(R.id.view);
                TextView textView = (TextView) helper.getView(R.id.tvRecordTime);
                String recordTime = item.getRecordTime();
                if (recordTime == null || recordTime.length() == 0) {
                    CommonExtKt.gone(view);
                    CommonExtKt.gone(textView);
                } else {
                    CommonExtKt.visible(textView);
                    CommonExtKt.visible(view);
                    textView.setText(item.getRecordTime());
                }
            }
        };
        DownloadedTvActivity downloadedTvActivity = this;
        ((ActivityDownloadedTvBinding) this.binding).recyclerView.setSpacingWithMargins(DensityUtils.dp2px(downloadedTvActivity, 20.0f), DensityUtils.dp2px(downloadedTvActivity, 20.0f));
        TvRecyclerView recyclerView = ((ActivityDownloadedTvBinding) this.binding).recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        TvRecyclerView tvRecyclerView = ((ActivityDownloadedTvBinding) this.binding).recyclerView;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        if (commonBaseAdapter.getData().size() != 0) {
            Object objAs = Observable.timer(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DownloadedTvActivity.onResume$lambda$0((Long) obj);
                }
            }, 15, null);
            ((DownloadedTvPresenter) this.mPresenter).getDownloadedTv(this.id);
            return;
        }
        ((DownloadedTvPresenter) this.mPresenter).getDownloadedTv(this.id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onResume$lambda$0(Long l) {
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityDownloadedTvBinding) this.binding).recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                DownloadedTvActivity.initListener$lambda$1(this.f$0, view, z);
            }
        });
        ((ActivityDownloadedTvBinding) this.binding).recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity.initListener.2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                DownloadedTvActivity.this.focusScale(itemView, 1.15f);
            }
        });
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda10
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadedTvActivity.initListener$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        commonBaseAdapter2.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda11
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return DownloadedTvActivity.initListener$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ((ActivityDownloadedTvBinding) this.binding).llDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.setEdit(false);
            }
        });
        ((ActivityDownloadedTvBinding) this.binding).llDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadedTvActivity.initListener$lambda$7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(DownloadedTvActivity downloadedTvActivity, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = downloadedTvActivity.focusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(DownloadedTvActivity downloadedTvActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadedTvActivity.adapter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        DownloadFile item = commonBaseAdapter.getItem(i);
        if (item != null) {
            if (downloadedTvActivity.edit) {
                item.setCheck(!item.isCheck());
                if (item.isCheck()) {
                    downloadedTvActivity.selectCount++;
                    downloadedTvActivity.selectSize += item.getSize();
                } else {
                    downloadedTvActivity.selectCount--;
                    downloadedTvActivity.selectSize -= item.getSize();
                }
                if (downloadedTvActivity.selectCount > 1) {
                    ((ActivityDownloadedTvBinding) downloadedTvActivity.binding).tvDelete.setText("Delete " + downloadedTvActivity.selectCount + " videos (" + FileUtils.byte2FitMemorySize(downloadedTvActivity.selectSize) + ")");
                } else {
                    ((ActivityDownloadedTvBinding) downloadedTvActivity.binding).tvDelete.setText("Delete " + downloadedTvActivity.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadedTvActivity.selectSize) + ")");
                }
                CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = downloadedTvActivity.adapter;
                if (commonBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commonBaseAdapter2 = commonBaseAdapter3;
                }
                commonBaseAdapter2.notifyItemChanged(i);
                return;
            }
            if (Network.isConnected(downloadedTvActivity)) {
                downloadedTvActivity.checkOnlineNum(item);
            } else {
                downloadedTvActivity.goTvPlayer(item);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$4(DownloadedTvActivity downloadedTvActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (!downloadedTvActivity.edit) {
            downloadedTvActivity.setEdit(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(DownloadedTvActivity downloadedTvActivity, View view) {
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadedTvActivity.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        List<DownloadFile> data = commonBaseAdapter.getData();
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (((DownloadFile) obj).isCheck()) {
                arrayList.add(obj);
            }
        }
        ((DownloadedTvPresenter) downloadedTvActivity.mPresenter).deleteDownloadedTv(arrayList);
    }

    private final void checkOnlineNum(final DownloadFile item) {
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, item.getId(), SystemUtils.getUniqueId(App.getContext()), 2, item.getSeason(), item.getEpisode(), Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvActivity.checkOnlineNum$lambda$9(this.f$0, item, (String) obj);
            }
        };
        Observable observableCompose = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadedTvActivity.checkOnlineNum$lambda$10(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvActivity.checkOnlineNum$lambda$11(this.f$0, item, (Throwable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvActivity.checkOnlineNum$lambda$12(this.f$0, (String) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadedTvActivity.checkOnlineNum$lambda$13(this.f$0, (Disposable) obj);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$10(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$9(final DownloadedTvActivity downloadedTvActivity, DownloadFile downloadFile, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            downloadedTvActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.hideLoadingView();
                }
            });
            TvDetail tvDetail = new TvDetail();
            tvDetail.id = downloadFile.getId();
            tvDetail.title = downloadFile.getVideoName();
            tvDetail.box_type = downloadFile.getBoxType();
            tvDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(downloadFile));
            ScreenManageActivity.INSTANCE.start(downloadedTvActivity, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), 100, baseResponse.getMsg(), tvDetail, downloadFile.getSeason(), downloadFile.getEpisode());
            observableJust = Observable.empty();
        } else {
            downloadedTvActivity.goTvPlayer(downloadFile);
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$13(DownloadedTvActivity downloadedTvActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadedTvActivity.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$12(DownloadedTvActivity downloadedTvActivity, String str) {
        downloadedTvActivity.hideLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$11(DownloadedTvActivity downloadedTvActivity, DownloadFile downloadFile, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadedTvActivity.hideLoadingView();
        downloadedTvActivity.goTvPlayer(downloadFile);
        return Unit.INSTANCE;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onFileDelete(FileDeleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ((DownloadedTvPresenter) this.mPresenter).getDownloadedTv(this.id);
    }

    private final void goTvPlayer(DownloadFile item) {
        if (!item.isHls() && !new File(item.getPath()).exists()) {
            ToastUtils.showShort("Downloaded file is not found,please check the download device is available", new Object[0]);
            return;
        }
        TvDetail tvDetail = new TvDetail();
        tvDetail.id = item.getId();
        tvDetail.title = item.getVideoName();
        tvDetail.box_type = item.getBoxType();
        tvDetail.imdb_id = item.getImdbId();
        tvDetail.poster = item.getPoster();
        tvDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(item));
        VideoPlayer.start(this, tvDetail, item.getSeason(), item.getEpisode(), false, new ArrayList(), true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100 && data != null) {
            Serializable serializableExtra = data.getSerializableExtra("detail");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetail");
            VideoPlayer.start(this, (TvDetail) serializableExtra, data.getIntExtra(DealWatchNextService.SEASON, 1), data.getIntExtra(DealWatchNextService.EPISODE, 1), false, new ArrayList(), true);
        }
    }

    @Override // com.movieboxpro.android.view.activity.DownloadedTvContract.View
    public void showTv(List<DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.isEmpty()) {
            finish();
        }
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setList(list);
    }

    @Override // com.movieboxpro.android.view.activity.DownloadedTvContract.View
    public void deleteComplete(List<? extends DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        for (DownloadFile downloadFile : list) {
            this.selectCount--;
            this.selectSize -= downloadFile.getSize();
        }
        if (this.selectCount > 1) {
            ((ActivityDownloadedTvBinding) this.binding).tvDelete.setText("Delete " + this.selectCount + " videos (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        } else {
            ((ActivityDownloadedTvBinding) this.binding).tvDelete.setText("Delete " + this.selectCount + " video (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        }
        setEdit(false);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_downloaded_tv;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public DownloadedTvPresenter bindPresenter() {
        return new DownloadedTvPresenter(this);
    }

    /* JADX INFO: compiled from: DownloadedTvActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id) {
            if (context != null) {
                Intent intent = new Intent(context, (Class<?>) DownloadedTvActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        }
    }

    /* JADX INFO: compiled from: DownloadedTvActivity.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0016B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\b\u0010\u0015\u001a\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvActivity$DownloadedFragment;", "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;", "Lcom/movieboxpro/android/view/fragment/DownloadedPresenter;", "Lcom/movieboxpro/android/view/fragment/DownloadedContract$View;", "<init>", "()V", "rowAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "list", "", "Lcom/movieboxpro/android/view/tvpresenter/DownloadedTvCardPresenter;", "value", "", "edit", "setEdit", "(Z)V", "initListener", "", "initData", "initView", "loadData", "bindPresenter", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DownloadedFragment extends BaseRowsMvpFragment<DownloadedPresenter> implements DownloadedContract.View {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private boolean edit;
        private List<DownloadedTvCardPresenter> list = new ArrayList();
        private ArrayObjectAdapter rowAdapter;

        @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
        protected void loadData() {
        }

        public DownloadedFragment() {
            ListRowPresenter listRowPresenter = new ListRowPresenter();
            listRowPresenter.setNumRows(1);
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(listRowPresenter);
            this.rowAdapter = arrayObjectAdapter;
            setAdapter(arrayObjectAdapter);
        }

        private final void setEdit(boolean z) {
            this.edit = z;
            Iterator<T> it = this.list.iterator();
            while (it.hasNext()) {
                ((DownloadedTvCardPresenter) it.next()).setEdit(this.edit);
            }
            getAdapter().notifyItemRangeChanged(0, getAdapter().size());
        }

        @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
        protected void initListener() {
            setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$DownloadedFragment$$ExternalSyntheticLambda4
                @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
                public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                    DownloadedTvActivity.DownloadedFragment.initListener$lambda$1(this.f$0, viewHolder, obj, viewHolder2, obj2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initListener$lambda$1(DownloadedFragment downloadedFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
            if (downloadedFragment.edit) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.movieboxpro.android.db.entity.DownloadFile");
                DownloadFile downloadFile = (DownloadFile) obj;
                downloadFile.setCheck(!downloadFile.isCheck());
                downloadedFragment.getAdapter().notifyItemRangeChanged(0, downloadedFragment.getAdapter().size());
                return;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.movieboxpro.android.db.entity.DownloadFile");
            DownloadFile downloadFile2 = (DownloadFile) obj;
            TvDetail tvDetail = new TvDetail();
            tvDetail.id = downloadFile2.getId();
            tvDetail.dateline = downloadFile2.getDownloadTime();
            tvDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(downloadFile2));
            VideoPlayer.start(downloadedFragment.getContext(), tvDetail, downloadFile2.getSeason(), downloadFile2.getEpisode(), false, new ArrayList(), true);
        }

        @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
        protected void initData() {
            Bundle arguments = getArguments();
            final String string = arguments != null ? arguments.getString("id") : null;
            Observable observableJust = Observable.just(string);
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$DownloadedFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DownloadedTvActivity.DownloadedFragment.initData$lambda$3(string, (String) obj);
                }
            };
            Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$DownloadedFragment$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return DownloadedTvActivity.DownloadedFragment.initData$lambda$4(function1, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$DownloadedFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DownloadedTvActivity.DownloadedFragment.initData$lambda$7(this.f$0, (HashMap) obj);
                }
            }, 15, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final HashMap initData$lambda$4(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (HashMap) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final HashMap initData$lambda$3(String str, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            List<DownloadFile> listFindSeasonsFile = DownloadFileHelper.INSTANCE.getInstance().findSeasonsFile(str);
            HashMap map = new HashMap();
            for (DownloadFile downloadFile : listFindSeasonsFile) {
                map.put("SEASON " + downloadFile.getSeason(), DownloadFileHelper.INSTANCE.getInstance().findDownloadFileBySeason(str, downloadFile.getSeason()));
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit initData$lambda$7(final DownloadedFragment downloadedFragment, HashMap map) {
            Intrinsics.checkNotNull(map);
            for (Map.Entry entry : map.entrySet()) {
                DownloadedTvCardPresenter downloadedTvCardPresenter = new DownloadedTvCardPresenter(downloadedFragment.getContext());
                downloadedTvCardPresenter.setLongClickListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.DownloadedTvActivity$DownloadedFragment$$ExternalSyntheticLambda3
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        DownloadedTvActivity.DownloadedFragment.initData$lambda$7$lambda$6$lambda$5(this.f$0);
                    }
                });
                downloadedFragment.list.add(downloadedTvCardPresenter);
                ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(downloadedTvCardPresenter);
                arrayObjectAdapter.addAll(0, (Collection) entry.getValue());
                downloadedFragment.rowAdapter.add(new ListRow(new HeaderItem((String) entry.getKey()), arrayObjectAdapter));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initData$lambda$7$lambda$6$lambda$5(DownloadedFragment downloadedFragment) {
            if (downloadedFragment.edit) {
                return;
            }
            downloadedFragment.setEdit(true);
        }

        @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
        protected void initView() {
            Context context = getContext();
            int iDp2px = context != null ? DensityUtils.dp2px(context, 10.0f) : 0;
            getVerticalGridView().setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
        public DownloadedPresenter bindPresenter() {
            return new DownloadedPresenter(this);
        }

        /* JADX INFO: compiled from: DownloadedTvActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvActivity$DownloadedFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/DownloadedTvActivity$DownloadedFragment;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DownloadedFragment newInstance(String id) {
                DownloadedFragment downloadedFragment = new DownloadedFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                downloadedFragment.setArguments(bundle);
                return downloadedFragment;
            }
        }
    }
}
