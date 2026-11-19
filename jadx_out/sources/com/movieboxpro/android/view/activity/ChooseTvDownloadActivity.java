package com.movieboxpro.android.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.DiffUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.livedata.DownloadRuleChangedLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadSeasonItem;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.ExternalStorage;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.FFmpegDownloadService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.click.DoubleClickHelper;
import com.movieboxpro.android.view.dialog.ChooseDownloadLocationFragment;
import com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment;
import com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment;
import com.movieboxpro.android.view.dialog.DownloadResolutionDialog;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.widget.DownloadingStatusView;
import com.movieboxpro.android.view.widget.DownloadingWhiteStatusView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityChooseTvDownloadBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: ChooseTvDownloadActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001 \u0018\u0000 G2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005:\u0001GB\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\"\u001a\u00020#H\u0014J\u0018\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020#H\u0014J\b\u0010*\u001a\u00020#H\u0002J\b\u0010+\u001a\u00020#H\u0014J\u0012\u0010,\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0012\u0010/\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\"\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u000104H\u0014J \u00105\u001a\u00020#2\u0006\u0010-\u001a\u0002062\u0006\u00107\u001a\u00020.2\u0006\u00108\u001a\u00020\u000eH\u0002J\b\u00109\u001a\u00020\u0011H\u0014J\b\u0010:\u001a\u00020#H\u0014J\b\u0010;\u001a\u00020\u000eH\u0014J\b\u0010<\u001a\u00020\u0002H\u0014J\b\u0010=\u001a\u00020#H\u0014J\u001e\u0010>\u001a\u00020#2\u0006\u0010?\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0@H\u0016J\u0016\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0@H\u0002J\u001e\u0010D\u001a\u00020#2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0@2\u0006\u0010-\u001a\u000206H\u0016J\u001a\u0010E\u001a\u00020#2\u0006\u00108\u001a\u00020\u000e2\b\u0010F\u001a\u0004\u0018\u00010\fH\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0018\u00010\u001cR\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!¨\u0006H"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityChooseTvDownloadBinding;", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$View;", "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/model/DownloadSeasonItem;", "id", "", "currSeason", "", "totalSeason", "isBindService", "", "focusBorder", "Lcom/owen/focus/FocusBorder;", "seasons", "", "[Ljava/lang/String;", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "videoData", "Lcom/movieboxpro/android/model/BaseMediaModel;", "downloadBinder", "Lcom/movieboxpro/android/service/DownloadService$DownloadBinder;", "Lcom/movieboxpro/android/service/DownloadService;", "downloadService", "serviceConnection", "com/movieboxpro/android/view/activity/ChooseTvDownloadActivity$serviceConnection$1", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadActivity$serviceConnection$1;", "initView", "", "focusScale", "v", "Landroid/view/View;", "scale", "", "initData", "initDownloadFilesFlow", "initListener", "checkOnlineNum", "item", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "goTvPlayer", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "removeDownload", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "downloadFile", "position", "isNeedLoadData", "requestData", "getLayoutResId", "bindPresenter", "onDestroy", "showSeasons", "selectSeason", "", "getAutoDownloadUrl", "Lcom/movieboxpro/android/model/DownloadUrl;", "list", "showDownloadDialog", "onItemClicked", "type", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseTvDownloadActivity extends BaseMvpActivity<ChooseTvDownloadPresenter, ActivityChooseTvDownloadBinding> implements ChooseTvDownloadContract.View, CommonItemClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<DownloadSeasonItem> adapter;
    private DownloadService.DownloadBinder downloadBinder;
    private DownloadService downloadService;
    private FocusBorder focusBorder;
    private boolean isBindService;
    private String[] seasons;
    private TvDetail tvDetail;
    private BaseMediaModel videoData;
    private String id = "";
    private int currSeason = 1;
    private int totalSeason = 1;
    private final ChooseTvDownloadActivity$serviceConnection$1 serviceConnection = new ServiceConnection() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.this$0.isBindService = true;
            this.this$0.downloadBinder = service instanceof DownloadService.DownloadBinder ? (DownloadService.DownloadBinder) service : null;
            ChooseTvDownloadActivity chooseTvDownloadActivity = this.this$0;
            DownloadService.DownloadBinder downloadBinder = chooseTvDownloadActivity.downloadBinder;
            chooseTvDownloadActivity.downloadService = downloadBinder != null ? downloadBinder.getThis$0() : null;
        }
    };

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.focusBorder = focusBorderBuild;
        ((ActivityChooseTvDownloadBinding) this.binding).tvQuality.setText(SettingManager.INSTANCE.getDownloadRule());
        ((ActivityChooseTvDownloadBinding) this.binding).tvOn.setText(SettingManager.INSTANCE.isAutoDownloadSelectQuality() ? "ON" : "OFF");
        UsbConnectReceiver.INSTANCE.getStorages().observe(this, new ChooseTvDownloadActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.initView$lambda$2(this.f$0, (List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$2(ChooseTvDownloadActivity chooseTvDownloadActivity, List list) {
        int i;
        Intrinsics.checkNotNull(list);
        List list2 = list;
        Iterator it = list2.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ((ExternalStorage) it.next()).setSelect(false);
        }
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Uri uri = Uri.parse(string);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        String path = uri.getPath();
        if (path != null && !StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null) && !new File(path).exists()) {
            ToastUtils.showShort("Last selected usb device is unavailable", new Object[0]);
            path = UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH();
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
        }
        int i2 = -1;
        for (Object obj : list2) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ExternalStorage externalStorage = (ExternalStorage) obj;
            if (StringsKt.equals(externalStorage.getPath(), path, true)) {
                externalStorage.setSelect(true);
                i2 = i;
            }
            i = i3;
        }
        if (i2 != -1) {
            ExternalStorage externalStorage2 = (ExternalStorage) list.get(i2);
            String name = externalStorage2.getName();
            if (name == null || StringsKt.isBlank(name)) {
                ((ActivityChooseTvDownloadBinding) chooseTvDownloadActivity.binding).tvDeviceName.setText(externalStorage2.getDesc());
            } else {
                ((ActivityChooseTvDownloadBinding) chooseTvDownloadActivity.binding).tvDeviceName.setText(externalStorage2.getDesc() + " (" + externalStorage2.getName() + ")");
            }
            ((ActivityChooseTvDownloadBinding) chooseTvDownloadActivity.binding).tvSpace.setText(externalStorage2.getSpaceDesc());
        }
        return Unit.INSTANCE;
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

    /* JADX INFO: renamed from: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$initData$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChooseTvDownloadActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$initData$1", f = "ChooseTvDownloadActivity.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ChooseTvDownloadActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Long> downloadingNumFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadingNumFlow();
                final ChooseTvDownloadActivity chooseTvDownloadActivity = ChooseTvDownloadActivity.this;
                this.label = 1;
                if (downloadingNumFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.initData.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).longValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(long j, Continuation<? super Unit> continuation) {
                        if (j > 0) {
                            chooseTvDownloadActivity.getWindow().addFlags(128);
                        } else {
                            chooseTvDownloadActivity.getWindow().clearFlags(128);
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter = null;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
        ChooseTvDownloadActivity chooseTvDownloadActivity = this;
        DownloadService.INSTANCE.bind(chooseTvDownloadActivity, this.serviceConnection);
        String stringExtra = getIntent().getStringExtra("id");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        this.seasons = getIntent().getStringArrayExtra("seasons");
        this.currSeason = getIntent().getIntExtra(DealWatchNextService.SEASON, 1);
        this.totalSeason = getIntent().getIntExtra("totalSeason", 1);
        Serializable serializableExtra = getIntent().getSerializableExtra("tvDetail");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetail");
        this.tvDetail = (TvDetail) serializableExtra;
        BaseMediaModel baseMediaModel = new BaseMediaModel();
        this.videoData = baseMediaModel;
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null) {
            baseMediaModel.id = tvDetail.id;
            BaseMediaModel baseMediaModel2 = this.videoData;
            if (baseMediaModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel2 = null;
            }
            baseMediaModel2.title = tvDetail.title;
            BaseMediaModel baseMediaModel3 = this.videoData;
            if (baseMediaModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel3 = null;
            }
            baseMediaModel3.poster = tvDetail.poster;
            BaseMediaModel baseMediaModel4 = this.videoData;
            if (baseMediaModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel4 = null;
            }
            baseMediaModel4.box_type = tvDetail.box_type;
        }
        ((ActivityChooseTvDownloadBinding) this.binding).tvSeasonEpisode.setText("SEASON " + this.currSeason + "/" + this.totalSeason);
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter2 = new CommonBaseAdapter<DownloadSeasonItem>(R.layout.adapter_tv_download, new ArrayList()) { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.initData.3
            {
                ArrayList<DownloadSeasonItem> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DownloadSeasonItem item) {
                String speed;
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                int i = R.id.tvTitle;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%s.%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getSeason().episode), item.getSeason().title}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                helper.setText(i, str);
                long j = 1000;
                if (item.getSeason().released_timestamp > System.currentTimeMillis() / j) {
                    GlideUtils.loadWithCorner(ChooseTvDownloadActivity.this, Integer.valueOf(R.mipmap.ic_unreleased), (ImageView) helper.getView(R.id.imageView), DensityUtils.dp2px(8.0f));
                } else {
                    GlideUtils.loadCornerLandHolder(ChooseTvDownloadActivity.this, item.getSeason().thumbs, (ImageView) helper.getView(R.id.imageView), 8);
                }
                TextView textView = (TextView) helper.getView(R.id.tvDownloadSize);
                TextView textView2 = (TextView) helper.getView(R.id.tvError);
                DownloadingWhiteStatusView downloadingWhiteStatusView = (DownloadingWhiteStatusView) helper.getView(R.id.downloadStatusView);
                View view = helper.getView(R.id.bgView);
                if (item.getDownloadFile() == null) {
                    CommonExtKt.gone(textView);
                    DownloadingWhiteStatusView downloadingWhiteStatusView2 = downloadingWhiteStatusView;
                    CommonExtKt.gone(downloadingWhiteStatusView2);
                    CommonExtKt.gone(view);
                    CommonExtKt.gone(downloadingWhiteStatusView2);
                    return;
                }
                DownloadingWhiteStatusView downloadingWhiteStatusView3 = downloadingWhiteStatusView;
                CommonExtKt.visible(downloadingWhiteStatusView3);
                CommonExtKt.visible(textView);
                StringBuilder sb = new StringBuilder();
                if (item.getDownloadFile().getDownloadSize() != 0) {
                    sb.append(FileUtils.byte2FitMemorySize(item.getDownloadFile().getDownloadSize()));
                }
                if (item.getDownloadFile().getSize() != 0) {
                    if (item.getDownloadFile().getDownloadSize() != 0) {
                        sb.append("/" + FileUtils.byte2FitMemorySize(item.getDownloadFile().getSize()));
                    } else {
                        sb.append(FileUtils.byte2FitMemorySize(item.getDownloadFile().getSize()));
                    }
                }
                if (item.getDownloadFile().getStatus() == 6 && (speed = item.getDownloadFile().getSpeed()) != null && !StringsKt.isBlank(speed)) {
                    sb.append(ShellUtil.COMMAND_LINE_END + item.getDownloadFile().getSpeed());
                }
                textView.setText(sb.toString());
                int status = item.getDownloadFile().getStatus();
                if (status == 1) {
                    downloadingWhiteStatusView.downloadFinish();
                } else if (status == 2) {
                    downloadingWhiteStatusView.setPause();
                } else if (status == 4) {
                    downloadingWhiteStatusView.setStartLoading();
                } else if (status != 6) {
                    if (status == 7) {
                        textView2.setText("Download failed:" + item.getDownloadFile().getErrorMsg());
                        downloadingWhiteStatusView.setFail();
                    } else if (status == 8) {
                        downloadingWhiteStatusView.setWaiting();
                    }
                } else if (item.getDownloadFile().isFfmpeg()) {
                    downloadingWhiteStatusView.setProgress(100, item.getDownloadFile().getProgress());
                } else if (item.getDownloadFile().getPercent() != 0.0f) {
                    downloadingWhiteStatusView.setProgress(100, (int) (item.getDownloadFile().getPercent() * 100));
                } else {
                    downloadingWhiteStatusView.setProgress((int) (item.getDownloadFile().getSize() / j), (int) (item.getDownloadFile().getDownloadSize() / j));
                }
                if (item.getDownloadFile().getStatus() == 7) {
                    CommonExtKt.visible(textView2);
                } else {
                    CommonExtKt.gone(textView2);
                }
                CommonExtKt.visible(downloadingWhiteStatusView3);
                CommonExtKt.visible(view);
            }
        };
        this.adapter = commonBaseAdapter2;
        commonBaseAdapter2.setDiffCallback(new DiffUtil.ItemCallback<DownloadSeasonItem>() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.initData.4
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(DownloadSeasonItem oldItem, DownloadSeasonItem newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return oldItem.getSeason().episode == newItem.getSeason().episode;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(DownloadSeasonItem oldItem, DownloadSeasonItem newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return Intrinsics.areEqual(oldItem.getDownloadFile(), newItem.getDownloadFile());
            }
        });
        ((ActivityChooseTvDownloadBinding) this.binding).recyclerView.setSpacingWithMargins(DensityUtils.dp2px(chooseTvDownloadActivity, 20.0f), DensityUtils.dp2px(chooseTvDownloadActivity, 20.0f));
        TvRecyclerView recyclerView = ((ActivityChooseTvDownloadBinding) this.binding).recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        TvRecyclerView tvRecyclerView = ((ActivityChooseTvDownloadBinding) this.binding).recyclerView;
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter3;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
        ((ChooseTvDownloadPresenter) this.mPresenter).requestSeasons(this.id, this.currSeason);
        DownloadRuleChangedLiveData.INSTANCE.get().observeInActivity(this, new ChooseTvDownloadActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.initData$lambda$4(this.f$0, (String) obj);
            }
        }));
        initDownloadFilesFlow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$4(ChooseTvDownloadActivity chooseTvDownloadActivity, String str) {
        ((ActivityChooseTvDownloadBinding) chooseTvDownloadActivity.binding).tvQuality.setText(SettingManager.INSTANCE.getDownloadRule());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$initDownloadFilesFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ChooseTvDownloadActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$initDownloadFilesFlow$1", f = "ChooseTvDownloadActivity.kt", i = {}, l = {303}, m = "invokeSuspend", n = {}, s = {})
    static final class C01741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01741(Continuation<? super C01741> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ChooseTvDownloadActivity.this.new C01741(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<DownloadFile>> flowFindDownloadFileByIdSeasonFlow = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByIdSeasonFlow(ChooseTvDownloadActivity.this.id, ChooseTvDownloadActivity.this.currSeason);
                final ChooseTvDownloadActivity chooseTvDownloadActivity = ChooseTvDownloadActivity.this;
                this.label = 1;
                if (flowFindDownloadFileByIdSeasonFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.initDownloadFilesFlow.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends DownloadFile>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) {
                        T next;
                        CommonBaseAdapter commonBaseAdapter = chooseTvDownloadActivity.adapter;
                        if (commonBaseAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            commonBaseAdapter = null;
                        }
                        List<T> data = commonBaseAdapter.getData();
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(data, 10));
                        for (T t : data) {
                            Iterator<T> it = list.iterator();
                            do {
                                if (!it.hasNext()) {
                                    next = (T) null;
                                    break;
                                }
                                next = it.next();
                            } while (((DownloadFile) next).getEpisode() != t.getSeason().episode);
                            arrayList.add(DownloadSeasonItem.copy$default(t, next, null, 2, null));
                        }
                        ArrayList arrayList2 = arrayList;
                        CommonBaseAdapter commonBaseAdapter2 = chooseTvDownloadActivity.adapter;
                        if (commonBaseAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            commonBaseAdapter2 = null;
                        }
                        BaseQuickAdapter.setDiffNewData$default(commonBaseAdapter2, CollectionsKt.toMutableList((Collection) arrayList2), null, 2, null);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void initDownloadFilesFlow() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01741(null), 3, null);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityChooseTvDownloadBinding) this.binding).clDownloadLocation.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseTvDownloadActivity.initListener$lambda$5(this.f$0, view);
            }
        });
        ((ActivityChooseTvDownloadBinding) this.binding).tvOn.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseTvDownloadActivity.initListener$lambda$6(this.f$0, view);
            }
        });
        ((ActivityChooseTvDownloadBinding) this.binding).clQuality.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseTvDownloadActivity.initListener$lambda$7(this.f$0, view);
            }
        });
        ((ActivityChooseTvDownloadBinding) this.binding).recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                ChooseTvDownloadActivity.initListener$lambda$8(this.f$0, view, z);
            }
        });
        DoubleClickHelper.click(((ActivityChooseTvDownloadBinding) this.binding).tvSeasonEpisode, new Runnable() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                ChooseTvDownloadActivity.initListener$lambda$9(this.f$0);
            }
        });
        ((ActivityChooseTvDownloadBinding) this.binding).recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.initListener.6
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                ChooseTvDownloadActivity.this.focusScale(itemView, 1.15f);
            }
        });
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda8
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseTvDownloadActivity.initListener$lambda$15(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(ChooseTvDownloadActivity chooseTvDownloadActivity, View view) {
        ((DialogFragment) ChooseDownloadLocationFragment.class.newInstance()).show(chooseTvDownloadActivity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ChooseDownloadLocationFragment.class).getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(ChooseTvDownloadActivity chooseTvDownloadActivity, View view) {
        SettingManager.INSTANCE.saveAutoDownloadSelectQuality(!SettingManager.INSTANCE.isAutoDownloadSelectQuality());
        ((ActivityChooseTvDownloadBinding) chooseTvDownloadActivity.binding).tvOn.setText(SettingManager.INSTANCE.isAutoDownloadSelectQuality() ? "ON" : "OFF");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(ChooseTvDownloadActivity chooseTvDownloadActivity, View view) {
        ((DialogFragment) DownloadResolutionDialog.class.newInstance()).show(chooseTvDownloadActivity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(DownloadResolutionDialog.class).getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(ChooseTvDownloadActivity chooseTvDownloadActivity, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = chooseTvDownloadActivity.focusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(ChooseTvDownloadActivity chooseTvDownloadActivity) {
        ChooseSeasonDialogFragment chooseSeasonDialogFragmentNewInstance = ChooseSeasonDialogFragment.INSTANCE.newInstance(chooseTvDownloadActivity.seasons, new String[0]);
        chooseSeasonDialogFragmentNewInstance.setSeasonYear(String.valueOf(chooseTvDownloadActivity.currSeason), "");
        chooseSeasonDialogFragmentNewInstance.show(chooseTvDownloadActivity.getSupportFragmentManager(), "ChooseSeasonDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15(final ChooseTvDownloadActivity chooseTvDownloadActivity, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter = chooseTvDownloadActivity.adapter;
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter2 = null;
        BaseMediaModel baseMediaModel = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        final DownloadSeasonItem item = commonBaseAdapter.getItem(i);
        if (item.getDownloadFile() == null) {
            BaseMediaModel baseMediaModel2 = chooseTvDownloadActivity.videoData;
            if (baseMediaModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel2 = null;
            }
            baseMediaModel2.thumb = item.getSeason().thumbs;
            BaseMediaModel baseMediaModel3 = chooseTvDownloadActivity.videoData;
            if (baseMediaModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel3 = null;
            }
            baseMediaModel3.season = item.getSeason().season;
            BaseMediaModel baseMediaModel4 = chooseTvDownloadActivity.videoData;
            if (baseMediaModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel4 = null;
            }
            baseMediaModel4.episode = item.getSeason().episode;
            BaseMediaModel baseMediaModel5 = chooseTvDownloadActivity.videoData;
            if (baseMediaModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
            } else {
                baseMediaModel = baseMediaModel5;
            }
            baseMediaModel.imdb_id = item.getSeason().imdb_id;
            ((ChooseTvDownloadPresenter) chooseTvDownloadActivity.mPresenter).getDownloadUrls(item.getSeason().episode, item.getSeason().season, chooseTvDownloadActivity.id, item.getSeason());
            return;
        }
        if (CommonUtils.isFFmpegDownload(item.getDownloadFile().getPath())) {
            CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter3 = chooseTvDownloadActivity.adapter;
            if (commonBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commonBaseAdapter2 = commonBaseAdapter3;
            }
            View viewByPosition = commonBaseAdapter2.getViewByPosition(i, R.id.downloadStatusView);
            Intrinsics.checkNotNull(viewByPosition, "null cannot be cast to non-null type com.movieboxpro.android.view.widget.DownloadingStatusView");
            if (((DownloadingStatusView) viewByPosition).getStatus() == 1) {
                new ListDialog.Builder(chooseTvDownloadActivity).setItems(CollectionsKt.arrayListOf("Remove downloaded file", "Manage download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda15
                    @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                    public final void onClick(View view2, int i2) {
                        ChooseTvDownloadActivity.initListener$lambda$15$lambda$10(this.f$0, item, i, view2, i2);
                    }
                }).create().show();
                return;
            } else {
                new ListDialog.Builder(chooseTvDownloadActivity).setItems(CollectionsKt.arrayListOf("Cancel download", "Manage download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda16
                    @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                    public final void onClick(View view2, int i2) {
                        ChooseTvDownloadActivity.initListener$lambda$15$lambda$11(item, chooseTvDownloadActivity, view2, i2);
                    }
                }).create().show();
                return;
            }
        }
        int status = item.getDownloadFile().getStatus();
        if (status != 1) {
            if (status != 2 && status != 4) {
                if (status != 6) {
                    if (status != 7) {
                        if (status != 8) {
                            DownloadService.DownloadBinder downloadBinder = chooseTvDownloadActivity.downloadBinder;
                            if (downloadBinder != null) {
                                downloadBinder.pauseDownload(item.getDownloadFile());
                                return;
                            }
                            return;
                        }
                    }
                }
                new ListDialog.Builder(chooseTvDownloadActivity).setItems(CollectionsKt.arrayListOf("Pause download", "Cancel download", "Manage download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda18
                    @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                    public final void onClick(View view2, int i2) {
                        ChooseTvDownloadActivity.initListener$lambda$15$lambda$13(this.f$0, item, i, view2, i2);
                    }
                }).create().show();
                return;
            }
            new ListDialog.Builder(chooseTvDownloadActivity).setItems(CollectionsKt.arrayListOf("Resume download", "Cancel download", "Manage download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda17
                @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                public final void onClick(View view2, int i2) {
                    ChooseTvDownloadActivity.initListener$lambda$15$lambda$12(this.f$0, item, i, view2, i2);
                }
            }).create().show();
            return;
        }
        new ListDialog.Builder(chooseTvDownloadActivity).setItems(CollectionsKt.arrayListOf("Play", "Remove downloaded file", "Manage download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda1
            @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
            public final void onClick(View view2, int i2) {
                ChooseTvDownloadActivity.initListener$lambda$15$lambda$14(this.f$0, item, i, view2, i2);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15$lambda$10(ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadSeasonItem downloadSeasonItem, int i, View view, int i2) {
        if (i2 == 0) {
            chooseTvDownloadActivity.removeDownload(downloadSeasonItem.getSeason(), downloadSeasonItem.getDownloadFile(), i);
        } else {
            if (i2 != 1) {
                return;
            }
            DownloadActivity.INSTANCE.start(chooseTvDownloadActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15$lambda$11(DownloadSeasonItem downloadSeasonItem, ChooseTvDownloadActivity chooseTvDownloadActivity, View view, int i) {
        if (i == 0) {
            DownloadLiveData.INSTANCE.getInstance().cancelDownload(downloadSeasonItem.getDownloadFile().getPath(), downloadSeasonItem.getDownloadFile().getDownloadId());
        } else {
            if (i != 1) {
                return;
            }
            DownloadActivity.INSTANCE.start(chooseTvDownloadActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15$lambda$12(ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadSeasonItem downloadSeasonItem, int i, View view, int i2) {
        if (i2 == 0) {
            DownloadService.DownloadBinder downloadBinder = chooseTvDownloadActivity.downloadBinder;
            if (downloadBinder != null) {
                downloadBinder.resumeDownload(downloadSeasonItem.getDownloadFile());
                return;
            }
            return;
        }
        if (i2 == 1) {
            chooseTvDownloadActivity.removeDownload(downloadSeasonItem.getSeason(), downloadSeasonItem.getDownloadFile(), i);
        } else {
            if (i2 != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(chooseTvDownloadActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15$lambda$13(ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadSeasonItem downloadSeasonItem, int i, View view, int i2) {
        if (i2 == 0) {
            DownloadService.DownloadBinder downloadBinder = chooseTvDownloadActivity.downloadBinder;
            if (downloadBinder != null) {
                downloadBinder.pauseDownload(downloadSeasonItem.getDownloadFile());
                return;
            }
            return;
        }
        if (i2 == 1) {
            chooseTvDownloadActivity.removeDownload(downloadSeasonItem.getSeason(), downloadSeasonItem.getDownloadFile(), i);
        } else {
            if (i2 != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(chooseTvDownloadActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15$lambda$14(ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadSeasonItem downloadSeasonItem, int i, View view, int i2) {
        if (i2 == 0) {
            if (Network.isConnected(chooseTvDownloadActivity)) {
                chooseTvDownloadActivity.checkOnlineNum(downloadSeasonItem.getDownloadFile());
                return;
            } else {
                chooseTvDownloadActivity.goTvPlayer(downloadSeasonItem.getDownloadFile());
                return;
            }
        }
        if (i2 == 1) {
            chooseTvDownloadActivity.removeDownload(downloadSeasonItem.getSeason(), downloadSeasonItem.getDownloadFile(), i);
        } else {
            if (i2 != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(chooseTvDownloadActivity);
        }
    }

    private final void checkOnlineNum(final DownloadFile item) {
        if (item == null) {
            ToastUtils.showShort("This file is unavailable,please re-download it", new Object[0]);
            return;
        }
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, item.getId(), SystemUtils.getUniqueId(App.getContext()), 2, item.getSeason(), item.getEpisode(), Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.checkOnlineNum$lambda$18(this.f$0, item, (String) obj);
            }
        };
        Observable observableCompose = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ChooseTvDownloadActivity.checkOnlineNum$lambda$19(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.checkOnlineNum$lambda$20(this.f$0, item, (Throwable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.checkOnlineNum$lambda$21(this.f$0, (String) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadActivity.checkOnlineNum$lambda$22(this.f$0, (Disposable) obj);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$19(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$18(final ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadFile downloadFile, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            chooseTvDownloadActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.hideLoadingView();
                }
            });
            TvDetail tvDetail = chooseTvDownloadActivity.tvDetail;
            if (tvDetail != null) {
                tvDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(downloadFile));
            }
            TvDetail tvDetail2 = chooseTvDownloadActivity.tvDetail;
            if (tvDetail2 != null) {
                ScreenManageActivity.INSTANCE.start(chooseTvDownloadActivity, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), 100, baseResponse.getMsg(), tvDetail2, downloadFile.getSeason(), downloadFile.getEpisode());
            }
            observableJust = Observable.empty();
        } else {
            chooseTvDownloadActivity.goTvPlayer(downloadFile);
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$22(ChooseTvDownloadActivity chooseTvDownloadActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        chooseTvDownloadActivity.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$21(ChooseTvDownloadActivity chooseTvDownloadActivity, String str) {
        chooseTvDownloadActivity.hideLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$20(ChooseTvDownloadActivity chooseTvDownloadActivity, DownloadFile downloadFile, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        chooseTvDownloadActivity.hideLoadingView();
        chooseTvDownloadActivity.goTvPlayer(downloadFile);
        return Unit.INSTANCE;
    }

    private final void goTvPlayer(DownloadFile item) {
        if (item != null) {
            TvDetail tvDetail = this.tvDetail;
            if (tvDetail != null) {
                tvDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(item));
            }
            VideoPlayer.start(this, this.tvDetail, item.getSeason(), item.getEpisode(), false, new ArrayList(), true);
            return;
        }
        ToastUtils.showShort("This file is unavailable,please re-download it", new Object[0]);
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

    private final void removeDownload(TvDetail.SeasonDetail item, DownloadFile downloadFile, int position) {
        DownloadService.DownloadBinder downloadBinder = this.downloadBinder;
        if (downloadBinder != null) {
            downloadBinder.deleteDownload(downloadFile);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_choose_tv_download;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ChooseTvDownloadPresenter bindPresenter() {
        return new ChooseTvDownloadPresenter(this);
    }

    /* JADX INFO: compiled from: ChooseTvDownloadActivity.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "id", "", DealWatchNextService.SEASON, "", "totalSeason", "seasons", "", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "(Landroid/content/Context;Ljava/lang/String;II[Ljava/lang/String;Lcom/movieboxpro/android/model/tv/TvDetail;)V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id, int season, int totalSeason, String[] seasons, TvDetail tvDetail) {
            Intrinsics.checkNotNullParameter(seasons, "seasons");
            Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
            if (context != null) {
                Intent intent = new Intent(context, (Class<?>) ChooseTvDownloadActivity.class);
                intent.putExtra("id", id);
                intent.putExtra(DealWatchNextService.SEASON, season);
                intent.putExtra("totalSeason", totalSeason);
                intent.putExtra("seasons", seasons);
                intent.putExtra("tvDetail", tvDetail);
                context.startActivity(intent);
            }
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.isBindService) {
            DownloadService.INSTANCE.unBind(this, this.serviceConnection);
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.activity.ChooseTvDownloadContract.View
    public void showSeasons(int selectSeason, List<DownloadSeasonItem> seasons) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        this.currSeason = selectSeason;
        ((ActivityChooseTvDownloadBinding) this.binding).tvSeasonEpisode.setText("SEASON " + selectSeason + "/" + this.totalSeason);
        CommonBaseAdapter<DownloadSeasonItem> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setList(seasons);
        ((ActivityChooseTvDownloadBinding) this.binding).recyclerView.requestFocus();
    }

    private final DownloadUrl getAutoDownloadUrl(List<DownloadUrl> list) {
        Integer original;
        Integer original2;
        String[] strArr = (String[]) StringsKt.split$default((CharSequence) SettingManager.INSTANCE.getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null).toArray(new String[0]);
        String currDownloadRule = SettingManager.INSTANCE.getCurrDownloadRule();
        if (list.size() == 1) {
            return list.get(0);
        }
        for (String str : strArr) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                DownloadUrl downloadUrl = list.get(i);
                if (StringsKt.equals("ORG", currDownloadRule, true)) {
                    if (!TextUtils.isEmpty(downloadUrl.getPath()) && (original2 = downloadUrl.getOriginal()) != null && original2.intValue() == 1) {
                        return downloadUrl;
                    }
                } else if (!TextUtils.isEmpty(downloadUrl.getPath()) && StringsKt.equals(str, downloadUrl.getReal_quality(), true) && ((original = downloadUrl.getOriginal()) == null || original.intValue() != 1)) {
                    return downloadUrl;
                }
            }
        }
        return list.get(0);
    }

    @Override // com.movieboxpro.android.view.activity.ChooseTvDownloadContract.View
    public void showDownloadDialog(List<DownloadUrl> list, final TvDetail.SeasonDetail item) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(item, "item");
        if (SettingManager.INSTANCE.isAutoDownloadSelectQuality()) {
            DownloadUrl autoDownloadUrl = getAutoDownloadUrl(list);
            String tmfid = autoDownloadUrl.getTmfid();
            if (tmfid == null || StringsKt.isBlank(tmfid)) {
                item.downloadId = autoDownloadUrl.getMmfid();
            } else {
                item.downloadId = autoDownloadUrl.getTmfid();
            }
            BaseMediaModel baseMediaModel = this.videoData;
            BaseMediaModel baseMediaModel2 = null;
            if (baseMediaModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                baseMediaModel = null;
            }
            baseMediaModel.episodeName = item.title;
            DownloadService.Companion companion = DownloadService.INSTANCE;
            ChooseTvDownloadActivity chooseTvDownloadActivity = this;
            BaseMediaModel baseMediaModel3 = this.videoData;
            if (baseMediaModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoData");
            } else {
                baseMediaModel2 = baseMediaModel3;
            }
            companion.startDownload(chooseTvDownloadActivity, autoDownloadUrl, baseMediaModel2);
            return;
        }
        ChooseDownloadQualityFragment chooseDownloadQualityFragmentNewInstance = ChooseDownloadQualityFragment.INSTANCE.newInstance(list);
        chooseDownloadQualityFragmentNewInstance.setCallback(new ChooseDownloadQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadActivity.showDownloadDialog.1
            @Override // com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment.ChooseDownloadQualityListener
            public void onChooseDownload(DownloadUrl downloadUrl, int position) {
                StorageVolume storageVolumeM;
                Object next;
                StorageVolume storageVolumeM2;
                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                String tmfid2 = downloadUrl.getTmfid();
                if (tmfid2 == null || StringsKt.isBlank(tmfid2)) {
                    item.downloadId = downloadUrl.getMmfid();
                } else {
                    item.downloadId = downloadUrl.getTmfid();
                }
                BaseMediaModel baseMediaModel4 = this.videoData;
                BaseMediaModel baseMediaModel5 = null;
                if (baseMediaModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoData");
                    baseMediaModel4 = null;
                }
                baseMediaModel4.episodeName = item.title;
                if (downloadUrl.getHls()) {
                    DownloadService.Companion companion2 = DownloadService.INSTANCE;
                    ChooseTvDownloadActivity chooseTvDownloadActivity2 = this;
                    ChooseTvDownloadActivity chooseTvDownloadActivity3 = chooseTvDownloadActivity2;
                    BaseMediaModel baseMediaModel6 = chooseTvDownloadActivity2.videoData;
                    if (baseMediaModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoData");
                    } else {
                        baseMediaModel5 = baseMediaModel6;
                    }
                    companion2.startDownload(chooseTvDownloadActivity3, downloadUrl, baseMediaModel5);
                    return;
                }
                if (Build.VERSION.SDK_INT < 24) {
                    DownloadService.Companion companion3 = DownloadService.INSTANCE;
                    ChooseTvDownloadActivity chooseTvDownloadActivity4 = this;
                    ChooseTvDownloadActivity chooseTvDownloadActivity5 = chooseTvDownloadActivity4;
                    BaseMediaModel baseMediaModel7 = chooseTvDownloadActivity4.videoData;
                    if (baseMediaModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoData");
                    } else {
                        baseMediaModel5 = baseMediaModel7;
                    }
                    companion3.startDownload(chooseTvDownloadActivity5, downloadUrl, baseMediaModel5);
                    return;
                }
                if (downloadUrl.getSize_bytes() < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
                    DownloadService.Companion companion4 = DownloadService.INSTANCE;
                    ChooseTvDownloadActivity chooseTvDownloadActivity6 = this;
                    ChooseTvDownloadActivity chooseTvDownloadActivity7 = chooseTvDownloadActivity6;
                    BaseMediaModel baseMediaModel8 = chooseTvDownloadActivity6.videoData;
                    if (baseMediaModel8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoData");
                    } else {
                        baseMediaModel5 = baseMediaModel8;
                    }
                    companion4.startDownload(chooseTvDownloadActivity7, downloadUrl, baseMediaModel5);
                    return;
                }
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
                if (!Intrinsics.areEqual(string, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())) {
                    List<StorageVolume> value = UsbConnectReceiver.INSTANCE.getStoragesVolumes().getValue();
                    if (value != null) {
                        Iterator<T> it = value.iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            } else {
                                next = it.next();
                                storageVolumeM2 = App$$ExternalSyntheticApiModelOutline0.m(next);
                                Intrinsics.checkNotNull(string);
                            }
                        } while (!StringsKt.contains$default((CharSequence) string, (CharSequence) String.valueOf(storageVolumeM2.getUuid()), false, 2, (Object) null));
                        storageVolumeM = App$$ExternalSyntheticApiModelOutline0.m(next);
                    } else {
                        storageVolumeM = null;
                    }
                    if (storageVolumeM != null) {
                        HashMap map = (HashMap) JSONObject.parseObject(JSONObject.toJSONString(storageVolumeM), HashMap.class);
                        Intrinsics.checkNotNull(map);
                        long j = Long.parseLong(String.valueOf(map.get("maxFileSize")));
                        if (j != 0 && j < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
                            FFmpegDownloadService.Companion companion5 = FFmpegDownloadService.INSTANCE;
                            ChooseTvDownloadActivity chooseTvDownloadActivity8 = this;
                            ChooseTvDownloadActivity chooseTvDownloadActivity9 = chooseTvDownloadActivity8;
                            BaseMediaModel baseMediaModel9 = chooseTvDownloadActivity8.videoData;
                            if (baseMediaModel9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("videoData");
                            } else {
                                baseMediaModel5 = baseMediaModel9;
                            }
                            companion5.startDownload(chooseTvDownloadActivity9, downloadUrl, baseMediaModel5);
                            return;
                        }
                        DownloadService.Companion companion6 = DownloadService.INSTANCE;
                        ChooseTvDownloadActivity chooseTvDownloadActivity10 = this;
                        ChooseTvDownloadActivity chooseTvDownloadActivity11 = chooseTvDownloadActivity10;
                        BaseMediaModel baseMediaModel10 = chooseTvDownloadActivity10.videoData;
                        if (baseMediaModel10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("videoData");
                        } else {
                            baseMediaModel5 = baseMediaModel10;
                        }
                        companion6.startDownload(chooseTvDownloadActivity11, downloadUrl, baseMediaModel5);
                        return;
                    }
                    return;
                }
                DownloadService.Companion companion7 = DownloadService.INSTANCE;
                ChooseTvDownloadActivity chooseTvDownloadActivity12 = this;
                ChooseTvDownloadActivity chooseTvDownloadActivity13 = chooseTvDownloadActivity12;
                BaseMediaModel baseMediaModel11 = chooseTvDownloadActivity12.videoData;
                if (baseMediaModel11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoData");
                } else {
                    baseMediaModel5 = baseMediaModel11;
                }
                companion7.startDownload(chooseTvDownloadActivity13, downloadUrl, baseMediaModel5);
            }
        });
        chooseDownloadQualityFragmentNewInstance.show(getSupportFragmentManager(), "ChooseDownloadQualityFragment");
    }

    @Override // com.movieboxpro.android.listener.CommonItemClickListener
    public void onItemClicked(int position, String type) {
        String str;
        ChooseTvDownloadPresenter chooseTvDownloadPresenter = (ChooseTvDownloadPresenter) this.mPresenter;
        String str2 = this.id;
        String[] strArr = this.seasons;
        if (strArr == null || (str = strArr[position]) == null) {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        chooseTvDownloadPresenter.requestSeasons(str2, Integer.parseInt(str));
    }
}
