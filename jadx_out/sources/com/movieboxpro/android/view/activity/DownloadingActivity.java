package com.movieboxpro.android.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.DiffUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.media.MediaPlayerParams;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.android.view.widget.DownloadingWhiteStatusView;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityDownloadingBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: DownloadingActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u008d\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005*\u0001*\u0018\u0000 D2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001DB\u0007¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!H\u0002¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u001eH\u0002J \u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\t\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\b\u0010(\u001a\u00020\u001eH\u0002J\b\u0010,\u001a\u00020\u001eH\u0014J\b\u0010-\u001a\u00020\u001eH\u0014J\b\u0010.\u001a\u00020\u001eH\u0002J\u0018\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u00020!2\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020\u001eH\u0002J\b\u00104\u001a\u00020\u001eH\u0014J\b\u00105\u001a\u00020\u001eH\u0014J\b\u00106\u001a\u00020\u000eH\u0014J\b\u00107\u001a\u00020\u0011H\u0014J\b\u00108\u001a\u00020\u0002H\u0014J\b\u00109\u001a\u00020\u001eH\u0014J\u001a\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u00112\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0016\u0010>\u001a\u00020\u001e2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\t0@H\u0016J\b\u0010A\u001a\u00020\u001eH\u0016J\u0010\u0010B\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020\tH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+¨\u0006E"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadingActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/DownloadingPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityDownloadingBinding;", "Lcom/movieboxpro/android/view/activity/DownloadingContract$View;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "downloadBinder", "Lcom/movieboxpro/android/service/DownloadService$DownloadBinder;", "Lcom/movieboxpro/android/service/DownloadService;", "isBindService", "", "downloadService", "selectCount", "", "selectSize", "", "downloadCount", "value", "edit", "setEdit", "(Z)V", "focusBorder", "Lcom/owen/focus/FocusBorder;", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "initKeyListener", "", "view", "", "Landroid/view/View;", "([Landroid/view/View;)V", "updateTopActionFocusChain", "findItem", "Lkotlin/Pair;", "tag", "", "checkDownloadingCount", "serviceConnection", "com/movieboxpro/android/view/activity/DownloadingActivity$serviceConnection$1", "Lcom/movieboxpro/android/view/activity/DownloadingActivity$serviceConnection$1;", "initView", "initData", "initDownloadFlow", "focusScale", "v", "scale", "", "getUserInfo", "initListener", "requestData", "isNeedLoadData", "getLayoutResId", "bindPresenter", "onDestroy", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "showDownloadingFiles", "list", "", "deleteComplete", "deleteDownload", "downloadFile", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadingActivity extends BaseMvpActivity<DownloadingPresenter, ActivityDownloadingBinding> implements DownloadingContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<DownloadFile> adapter;
    private DownloadService.DownloadBinder downloadBinder;
    private int downloadCount;
    private DownloadService downloadService;
    private boolean edit;
    private FocusBorder focusBorder;
    private KProgressHUD hud;
    private boolean isBindService;
    private int selectCount;
    private long selectSize;
    private final DownloadingActivity$serviceConnection$1 serviceConnection = new ServiceConnection() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.this$0.isBindService = true;
            this.this$0.downloadBinder = service instanceof DownloadService.DownloadBinder ? (DownloadService.DownloadBinder) service : null;
            DownloadingActivity downloadingActivity = this.this$0;
            DownloadService.DownloadBinder downloadBinder = downloadingActivity.downloadBinder;
            downloadingActivity.downloadService = downloadBinder != null ? downloadBinder.getThis$0() : null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            this.this$0.downloadBinder = null;
        }
    };

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
        ActivityDownloadingBinding activityDownloadingBinding = (ActivityDownloadingBinding) this.binding;
        if (this.edit) {
            LinearLayout llDelete = activityDownloadingBinding.llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete, "llDelete");
            CommonExtKt.visible(llDelete);
            LinearLayout llDone = activityDownloadingBinding.llDone;
            Intrinsics.checkNotNullExpressionValue(llDone, "llDone");
            CommonExtKt.visible(llDone);
            LinearLayout llStartPauseAll = activityDownloadingBinding.llStartPauseAll;
            Intrinsics.checkNotNullExpressionValue(llStartPauseAll, "llStartPauseAll");
            CommonExtKt.gone(llStartPauseAll);
        } else {
            LinearLayout llDone2 = activityDownloadingBinding.llDone;
            Intrinsics.checkNotNullExpressionValue(llDone2, "llDone");
            CommonExtKt.gone(llDone2);
            LinearLayout llDelete2 = activityDownloadingBinding.llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete2, "llDelete");
            CommonExtKt.gone(llDelete2);
            LinearLayout llStartPauseAll2 = activityDownloadingBinding.llStartPauseAll;
            Intrinsics.checkNotNullExpressionValue(llStartPauseAll2, "llStartPauseAll");
            CommonExtKt.visible(llStartPauseAll2);
        }
        if (this.selectCount > 1) {
            activityDownloadingBinding.tvDelete.setText("Delete " + this.selectCount + " videos (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        } else {
            activityDownloadingBinding.tvDelete.setText("Delete " + this.selectCount + " video (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        }
        updateTopActionFocusChain();
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initKeyListener$lambda$2$lambda$1(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    private final void updateTopActionFocusChain() {
        int i = 0;
        List listListOf = CollectionsKt.listOf((Object[]) new LinearLayout[]{((ActivityDownloadingBinding) this.binding).llDone, ((ActivityDownloadingBinding) this.binding).llDelete, ((ActivityDownloadingBinding) this.binding).llStartPauseAll, ((ActivityDownloadingBinding) this.binding).llConcurrent, ((ActivityDownloadingBinding) this.binding).llSmartDownload});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOf) {
            LinearLayout linearLayout = (LinearLayout) obj;
            if (linearLayout.getVisibility() == 0 && linearLayout.isFocusable()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        for (Object obj2 : arrayList2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LinearLayout linearLayout2 = (LinearLayout) obj2;
            LinearLayout linearLayout3 = (LinearLayout) CollectionsKt.getOrNull(arrayList2, i - 1);
            int id = linearLayout3 != null ? linearLayout3.getId() : linearLayout2.getId();
            LinearLayout linearLayout4 = (LinearLayout) CollectionsKt.getOrNull(arrayList2, i2);
            int id2 = linearLayout4 != null ? linearLayout4.getId() : linearLayout2.getId();
            linearLayout2.setNextFocusLeftId(id);
            linearLayout2.setNextFocusRightId(id2);
            i = i2;
        }
    }

    private final Pair<Integer, DownloadFile> findItem(String tag) {
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        int i = 0;
        for (Object obj : commonBaseAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DownloadFile downloadFile = (DownloadFile) obj;
            if (Intrinsics.areEqual(downloadFile.getDownloadId(), tag)) {
                return new Pair<>(Integer.valueOf(i), downloadFile);
            }
            i = i2;
        }
        return null;
    }

    private final void checkDownloadingCount() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingActivity.checkDownloadingCount$lambda$7(this.f$0, (String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda14
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadingActivity.checkDownloadingCount$lambda$8(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingActivity.checkDownloadingCount$lambda$9(this.f$0, (Integer) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkDownloadingCount$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Integer) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer checkDownloadingCount$lambda$7(DownloadingActivity downloadingActivity, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadingActivity.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        int i = 0;
        for (DownloadFile downloadFile : commonBaseAdapter.getData()) {
            if (downloadFile.getStatus() == 6 || downloadFile.getStatus() == 4) {
                i++;
            }
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkDownloadingCount$lambda$9(DownloadingActivity downloadingActivity, Integer num) {
        Intrinsics.checkNotNull(num);
        downloadingActivity.downloadCount = num.intValue();
        if (num.intValue() > 0) {
            ((ActivityDownloadingBinding) downloadingActivity.binding).ivStartPause.setImageResource(R.drawable.suspend_selector);
            ((ActivityDownloadingBinding) downloadingActivity.binding).tvStartPauseAll.setText("Suspend All");
        } else {
            ((ActivityDownloadingBinding) downloadingActivity.binding).ivStartPause.setImageResource(R.drawable.start_all_selector);
            ((ActivityDownloadingBinding) downloadingActivity.binding).tvStartPauseAll.setText("Start All");
        }
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        this.hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f);
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.focusBorder = focusBorderBuild;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.activity.DownloadingActivity$initData$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadingActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.activity.DownloadingActivity$initData$1", f = "DownloadingActivity.kt", i = {}, l = {191}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadingActivity.this.new AnonymousClass1(continuation);
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
                final DownloadingActivity downloadingActivity = DownloadingActivity.this;
                this.label = 1;
                if (downloadingNumFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity.initData.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).longValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(long j, Continuation<? super Unit> continuation) {
                        if (j > 0) {
                            downloadingActivity.getWindow().addFlags(128);
                        } else {
                            downloadingActivity.getWindow().clearFlags(128);
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
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = null;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
        SmartDownloadChangedLiveData.INSTANCE.get().observeInActivity(this, new DownloadingActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingActivity.initData$lambda$10(this.f$0, (Boolean) obj);
            }
        }));
        ((ActivityDownloadingBinding) this.binding).tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
        ((ActivityDownloadingBinding) this.binding).tvConcurrent.setText("Max Concurrent " + PrefsUtils.getInstance().getInt(Constant.Prefs.DOWNLOAD_CONCURRENT, 1));
        DownloadingActivity downloadingActivity = this;
        DownloadService.INSTANCE.bind(downloadingActivity, this.serviceConnection);
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = new CommonBaseAdapter<DownloadFile>(R.layout.adapter_downloading_file, new ArrayList()) { // from class: com.movieboxpro.android.view.activity.DownloadingActivity.initData.3
            {
                ArrayList<DownloadFile> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DownloadFile item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                if (item.getBoxType() == 2) {
                    GlideUtils.loadPortraitHolder(DownloadingActivity.this, item.getThumb(), (ImageView) helper.getView(R.id.imageView));
                } else {
                    GlideUtils.loadPortraitHolder(DownloadingActivity.this, item.getPoster(), (ImageView) helper.getView(R.id.imageView));
                }
                helper.setText(R.id.tvTitle, item.getVideoName());
                TextView textView = (TextView) helper.getView(R.id.tvSpeed);
                String strByte2FitMemorySize = FileUtils.byte2FitMemorySize(item.getSize());
                TextView textView2 = (TextView) helper.getView(R.id.tvEpisode);
                TextView textView3 = (TextView) helper.getView(R.id.tvError);
                if (item.getBoxType() == 2) {
                    CommonExtKt.visible(textView2);
                    textView2.setText(ExifInterface.LATITUDE_SOUTH + item.getSeason() + ExifInterface.LONGITUDE_EAST + item.getEpisode());
                } else {
                    CommonExtKt.gone(textView2);
                }
                DownloadingWhiteStatusView downloadingWhiteStatusView = (DownloadingWhiteStatusView) helper.getView(R.id.downloadStatusView);
                switch (item.getStatus()) {
                    case 2:
                        downloadingWhiteStatusView.setPause();
                        CommonExtKt.gone(textView);
                        break;
                    case 4:
                        downloadingWhiteStatusView.setStartLoading();
                        CommonExtKt.gone(textView);
                        break;
                    case 5:
                        downloadingWhiteStatusView.setStartLoading();
                        CommonExtKt.gone(textView);
                        break;
                    case 6:
                        if (item.getPercent() != 0.0f) {
                            downloadingWhiteStatusView.setProgress(100, (int) (item.getPercent() * 100));
                            CommonExtKt.visible(textView);
                            textView.setText(item.getSpeed());
                        } else {
                            long j = 1000;
                            downloadingWhiteStatusView.setProgress((int) (item.getSize() / j), (int) (item.getDownloadSize() / j));
                            CommonExtKt.visible(textView);
                            textView.setText(item.getSpeed());
                        }
                        break;
                    case 7:
                        textView3.setText(item.getErrorMsg());
                        downloadingWhiteStatusView.setFail();
                        CommonExtKt.gone(textView);
                        break;
                    case 8:
                        downloadingWhiteStatusView.setWaiting();
                        CommonExtKt.gone(textView);
                        break;
                }
                if (item.getStatus() == 7) {
                    CommonExtKt.visible(textView3);
                } else {
                    CommonExtKt.gone(textView3);
                }
                helper.setText(R.id.tvTitle, item.getEpisodeName());
                if (item.getPercent() != 0.0f) {
                    helper.setText(R.id.tvDownloadSize, FileUtils.byte2FitMemorySize(item.getDownloadSize()));
                } else {
                    helper.setText(R.id.tvDownloadSize, FileUtils.byte2FitMemorySize(item.getDownloadSize()) + "/" + strByte2FitMemorySize);
                }
                CheckBox checkBox = (CheckBox) helper.getView(R.id.checkbox);
                if (DownloadingActivity.this.edit) {
                    CommonExtKt.visible(checkBox);
                } else {
                    CommonExtKt.gone(checkBox);
                }
                checkBox.setChecked(item.isCheck());
            }
        };
        this.adapter = commonBaseAdapter2;
        commonBaseAdapter2.setDiffCallback(new DiffUtil.ItemCallback<DownloadFile>() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity.initData.4
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(DownloadFile oldItem, DownloadFile newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return Intrinsics.areEqual(oldItem.getDownloadId(), newItem.getDownloadId());
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(DownloadFile oldItem, DownloadFile newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return oldItem.getStatus() == newItem.getStatus() && oldItem.getSize() == newItem.getSize() && oldItem.getDownloadSize() == newItem.getDownloadSize() && oldItem.isCheck() == newItem.isCheck() && Intrinsics.areEqual(oldItem.getSpeed(), newItem.getSpeed()) && oldItem.getProgress() == newItem.getProgress() && Intrinsics.areEqual(oldItem.getErrorMsg(), newItem.getErrorMsg()) && oldItem.isHls() == newItem.isHls() && oldItem.getDownloadTime() == newItem.getDownloadTime() && Intrinsics.areEqual(oldItem.getRecordTime(), newItem.getRecordTime()) && Intrinsics.areEqual(oldItem.getPoster(), newItem.getPoster()) && Intrinsics.areEqual(oldItem.getThumb(), newItem.getThumb()) && Intrinsics.areEqual(oldItem.getVideoName(), newItem.getVideoName()) && Intrinsics.areEqual(oldItem.getEpisodeName(), newItem.getEpisodeName()) && oldItem.getSeason() == newItem.getSeason() && oldItem.getEpisode() == newItem.getEpisode() && oldItem.getBoxType() == newItem.getBoxType() && oldItem.isFfmpeg() == newItem.isFfmpeg() && Intrinsics.areEqual(oldItem.getPath(), newItem.getPath()) && Intrinsics.areEqual(oldItem.getDownloadId(), newItem.getDownloadId());
            }
        });
        TvRecyclerView recyclerView = ((ActivityDownloadingBinding) this.binding).recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        ((ActivityDownloadingBinding) this.binding).recyclerView.setSpacingWithMargins(DensityUtils.dp2px(downloadingActivity, 20.0f), DensityUtils.dp2px(downloadingActivity, 20.0f));
        TvRecyclerView tvRecyclerView = ((ActivityDownloadingBinding) this.binding).recyclerView;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter3;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
        initDownloadFlow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$10(DownloadingActivity downloadingActivity, Boolean bool) {
        ((ActivityDownloadingBinding) downloadingActivity.binding).tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.activity.DownloadingActivity$initDownloadFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadingActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.activity.DownloadingActivity$initDownloadFlow$1", f = "DownloadingActivity.kt", i = {}, l = {MediaPlayerParams.STATE_IDLE}, m = "invokeSuspend", n = {}, s = {})
    static final class C01761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01761(Continuation<? super C01761> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadingActivity.this.new C01761(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<DownloadFile>> downloadingFilesFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadingFilesFlow();
                final DownloadingActivity downloadingActivity = DownloadingActivity.this;
                this.label = 1;
                if (downloadingFilesFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity.initDownloadFlow.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends DownloadFile>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) {
                        CommonBaseAdapter commonBaseAdapter = downloadingActivity.adapter;
                        if (commonBaseAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            commonBaseAdapter = null;
                        }
                        List<T> data = commonBaseAdapter.getData();
                        ArrayList arrayList = new ArrayList();
                        for (T t : data) {
                            if (((DownloadFile) t).isCheck()) {
                                arrayList.add(t);
                            }
                        }
                        ArrayList arrayList2 = arrayList;
                        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                        Iterator<T> it = arrayList2.iterator();
                        while (it.hasNext()) {
                            arrayList3.add(((DownloadFile) it.next()).getDownloadId());
                        }
                        Set set = CollectionsKt.toSet(arrayList3);
                        for (DownloadFile downloadFile : list) {
                            if (CommonUtils.isFFmpegDownload(downloadFile.getPath())) {
                                downloadFile.setFfmpeg(true);
                            }
                            if (set.contains(downloadFile.getDownloadId())) {
                                downloadFile.setCheck(true);
                            } else {
                                downloadFile.setCheck(false);
                            }
                        }
                        downloadingActivity.showDownloadingFiles(list);
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

    private final void initDownloadFlow() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01761(null), 3, null);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void getUserInfo() {
        Object objAs = Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadingActivity.getUserInfo$lambda$11((UserModel.UserData) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getUserInfo$lambda$11(UserModel.UserData userData) throws Throwable {
        App.updateUser(userData);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityDownloadingBinding) this.binding).llSmartDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadingActivity.initListener$lambda$12(this.f$0, view);
            }
        });
        ((ActivityDownloadingBinding) this.binding).llConcurrent.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadingActivity.initListener$lambda$16(this.f$0, view);
            }
        });
        LinearLayout llDone = ((ActivityDownloadingBinding) this.binding).llDone;
        Intrinsics.checkNotNullExpressionValue(llDone, "llDone");
        LinearLayout llStartPauseAll = ((ActivityDownloadingBinding) this.binding).llStartPauseAll;
        Intrinsics.checkNotNullExpressionValue(llStartPauseAll, "llStartPauseAll");
        initKeyListener(llDone, llStartPauseAll);
        ((ActivityDownloadingBinding) this.binding).recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                DownloadingActivity.initListener$lambda$17(this.f$0, view, z);
            }
        });
        ((ActivityDownloadingBinding) this.binding).recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity.initListener.4
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                CommonBaseAdapter commonBaseAdapter = DownloadingActivity.this.adapter;
                if (commonBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter = null;
                }
                View viewByPosition = commonBaseAdapter.getViewByPosition(position, R.id.tvTitle);
                if (viewByPosition != null) {
                    CommonExtKt.invisible(viewByPosition);
                }
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                DownloadingActivity.this.focusScale(itemView, 1.15f);
                CommonBaseAdapter commonBaseAdapter = DownloadingActivity.this.adapter;
                if (commonBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter = null;
                }
                View viewByPosition = commonBaseAdapter.getViewByPosition(position, R.id.tvTitle);
                if (viewByPosition != null) {
                    CommonExtKt.visible(viewByPosition);
                }
            }
        });
        ((ActivityDownloadingBinding) this.binding).llDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.setEdit(false);
            }
        });
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return DownloadingActivity.initListener$lambda$19(this.f$0, baseQuickAdapter, view, i);
            }
        });
        ((ActivityDownloadingBinding) this.binding).llDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadingActivity.initListener$lambda$21(this.f$0, view);
            }
        });
        ((ActivityDownloadingBinding) this.binding).llStartPauseAll.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadingActivity.initListener$lambda$28(this.f$0, view);
            }
        });
        updateTopActionFocusChain();
        CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        commonBaseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadingActivity.initListener$lambda$31(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(DownloadingActivity downloadingActivity, View view) {
        ((DialogFragment) SmartDownloadSettingDialog.class.newInstance()).show(downloadingActivity.getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(SmartDownloadSettingDialog.class).getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16(final DownloadingActivity downloadingActivity, View view) {
        new ListDialog.Builder(downloadingActivity).setItems(CollectionsKt.arrayListOf(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D)).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda9
            @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
            public final void onClick(View view2, int i) {
                DownloadingActivity.initListener$lambda$16$lambda$15(this.f$0, view2, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16$lambda$15(final DownloadingActivity downloadingActivity, View view, int i) {
        int i2 = i + 1;
        if (i2 > 1) {
            if (App.getUserData().getIsvip() == 1) {
                DownloadDispatcher.setMaxParallelRunningCount(i2);
                ((ActivityDownloadingBinding) downloadingActivity.binding).tvConcurrent.setText("Max Concurrent " + i2);
                PrefsUtils.getInstance().putInt(Constant.Prefs.DOWNLOAD_CONCURRENT, i2);
                return;
            }
            new VipExpireDialog.Builder(downloadingActivity).setCancelable(false).setTitle("You're not VIP yet").setContent("").setNegativeText("Close").setPositiveText("Subscribe").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda11
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    DownloadingActivity.initListener$lambda$16$lambda$15$lambda$13(this.f$0);
                }
            }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda12
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    this.f$0.getUserInfo();
                }
            }).create().show();
            Unit unit = Unit.INSTANCE;
            return;
        }
        DownloadDispatcher.setMaxParallelRunningCount(i2);
        ((ActivityDownloadingBinding) downloadingActivity.binding).tvConcurrent.setText("Max Concurrent " + i2);
        PrefsUtils.getInstance().putInt(Constant.Prefs.DOWNLOAD_CONCURRENT, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16$lambda$15$lambda$13(DownloadingActivity downloadingActivity) {
        new VipChargeGuideDialog().show(downloadingActivity.getSupportFragmentManager(), "VipChargeGuideDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17(DownloadingActivity downloadingActivity, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = downloadingActivity.focusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$19(DownloadingActivity downloadingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (!downloadingActivity.edit) {
            downloadingActivity.setEdit(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$21(DownloadingActivity downloadingActivity, View view) {
        DownloadingPresenter downloadingPresenter = (DownloadingPresenter) downloadingActivity.mPresenter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadingActivity.adapter;
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
        downloadingPresenter.deleteDownloadingFiles(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$28(DownloadingActivity downloadingActivity, View view) {
        DownloadService.DownloadBinder downloadBinder;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = null;
        if (downloadingActivity.downloadCount > 0) {
            CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = downloadingActivity.adapter;
            if (commonBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commonBaseAdapter2 = null;
            }
            List<DownloadFile> data = commonBaseAdapter2.getData();
            ArrayList<DownloadFile> arrayList = new ArrayList();
            for (Object obj : data) {
                if (!CommonUtils.isFFmpegDownload(((DownloadFile) obj).getPath())) {
                    arrayList.add(obj);
                }
            }
            for (DownloadFile downloadFile : arrayList) {
                if (downloadFile.getStatus() != 6 && downloadFile.getStatus() != 4 && (downloadBinder = downloadingActivity.downloadBinder) != null) {
                    downloadBinder.pauseDownload(downloadFile);
                }
            }
            CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = downloadingActivity.adapter;
            if (commonBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commonBaseAdapter = commonBaseAdapter3;
            }
            List<DownloadFile> data2 = commonBaseAdapter.getData();
            ArrayList<DownloadFile> arrayList2 = new ArrayList();
            for (Object obj2 : data2) {
                if (!CommonUtils.isFFmpegDownload(((DownloadFile) obj2).getPath())) {
                    arrayList2.add(obj2);
                }
            }
            for (DownloadFile downloadFile2 : arrayList2) {
                if (downloadFile2.getStatus() == 6 || downloadFile2.getStatus() == 4) {
                    DownloadService.DownloadBinder downloadBinder2 = downloadingActivity.downloadBinder;
                    if (downloadBinder2 != null) {
                        downloadBinder2.pauseDownload(downloadFile2);
                    }
                }
            }
            return;
        }
        CommonBaseAdapter<DownloadFile> commonBaseAdapter4 = downloadingActivity.adapter;
        if (commonBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter4;
        }
        List<DownloadFile> data3 = commonBaseAdapter.getData();
        ArrayList<DownloadFile> arrayList3 = new ArrayList();
        for (Object obj3 : data3) {
            if (!CommonUtils.isFFmpegDownload(((DownloadFile) obj3).getPath())) {
                arrayList3.add(obj3);
            }
        }
        for (DownloadFile downloadFile3 : arrayList3) {
            DownloadService.DownloadBinder downloadBinder3 = downloadingActivity.downloadBinder;
            if (downloadBinder3 != null) {
                downloadBinder3.resumeDownload(downloadFile3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$31(DownloadingActivity downloadingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        DownloadService.DownloadBinder downloadBinder;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadingActivity.adapter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        final DownloadFile item = commonBaseAdapter.getItem(i);
        if (item != null) {
            if (downloadingActivity.edit) {
                item.setCheck(!item.isCheck());
                if (item.isCheck()) {
                    downloadingActivity.selectCount++;
                    downloadingActivity.selectSize += item.getSize();
                } else {
                    downloadingActivity.selectCount--;
                    downloadingActivity.selectSize -= item.getSize();
                }
                if (downloadingActivity.selectCount > 1) {
                    ((ActivityDownloadingBinding) downloadingActivity.binding).tvDelete.setText("Delete " + downloadingActivity.selectCount + " videos (" + FileUtils.byte2FitMemorySize(downloadingActivity.selectSize) + ")");
                } else {
                    ((ActivityDownloadingBinding) downloadingActivity.binding).tvDelete.setText("Delete " + downloadingActivity.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadingActivity.selectSize) + ")");
                }
                CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = downloadingActivity.adapter;
                if (commonBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commonBaseAdapter2 = commonBaseAdapter3;
                }
                commonBaseAdapter2.notifyItemChanged(i);
                return;
            }
            if (CommonUtils.isFFmpegDownload(item.getPath())) {
                new ListDialog.Builder(downloadingActivity).setItems(CollectionsKt.arrayListOf("Cancel download", "Close")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda7
                    @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                    public final void onClick(View view2, int i2) {
                        DownloadingActivity.initListener$lambda$31$lambda$30$lambda$29(item, view2, i2);
                    }
                }).create().show();
                return;
            }
            int status = item.getStatus();
            if (status != 2) {
                if (status == 4) {
                    DownloadService.DownloadBinder downloadBinder2 = downloadingActivity.downloadBinder;
                    if (downloadBinder2 != null) {
                        downloadBinder2.pauseDownload(item);
                        return;
                    }
                    return;
                }
                if (status == 6) {
                    DownloadService.DownloadBinder downloadBinder3 = downloadingActivity.downloadBinder;
                    if (downloadBinder3 != null) {
                        downloadBinder3.pauseDownload(item);
                        return;
                    }
                    return;
                }
                if (status != 7) {
                    if (status == 8) {
                        DownloadService.DownloadBinder downloadBinder4 = downloadingActivity.downloadBinder;
                        if (downloadBinder4 != null) {
                            downloadBinder4.pauseDownload(item);
                            return;
                        }
                        return;
                    }
                    if (item.getStatus() == 8 || (downloadBinder = downloadingActivity.downloadBinder) == null) {
                        return;
                    }
                    downloadBinder.pauseDownload(item);
                    return;
                }
            }
            DownloadService.DownloadBinder downloadBinder5 = downloadingActivity.downloadBinder;
            if (downloadBinder5 != null) {
                downloadBinder5.resumeDownload(item);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$31$lambda$30$lambda$29(DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            DownloadLiveData.INSTANCE.getInstance().cancelDownload(downloadFile.getPath(), downloadFile.getDownloadId());
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_downloading;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public DownloadingPresenter bindPresenter() {
        return new DownloadingPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.isBindService) {
            DownloadService.INSTANCE.unBind(this, this.serviceConnection);
        }
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.edit) {
            setEdit(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* JADX INFO: compiled from: DownloadingActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadingActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            if (context != null) {
                context.startActivity(new Intent(context, (Class<?>) DownloadingActivity.class));
            }
        }
    }

    @Override // com.movieboxpro.android.view.activity.DownloadingContract.View
    public void showDownloadingFiles(List<? extends DownloadFile> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.size() == 0) {
            finish();
            return;
        }
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        BaseQuickAdapter.setDiffNewData$default(commonBaseAdapter, CollectionsKt.toMutableList((Collection) list), null, 2, null);
        checkDownloadingCount();
    }

    @Override // com.movieboxpro.android.view.activity.DownloadingContract.View
    public void deleteComplete() {
        setEdit(false);
        checkDownloadingCount();
    }

    @Override // com.movieboxpro.android.view.activity.DownloadingContract.View
    public void deleteDownload(DownloadFile downloadFile) {
        Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
        DownloadService.DownloadBinder downloadBinder = this.downloadBinder;
        if (downloadBinder != null) {
            downloadBinder.deleteDownload(downloadFile);
        }
    }

    private final void initKeyListener(View... view) {
        for (View view2 : view) {
            view2.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.DownloadingActivity$$ExternalSyntheticLambda10
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view3, int i, KeyEvent keyEvent) {
                    return DownloadingActivity.initKeyListener$lambda$2$lambda$1(view3, i, keyEvent);
                }
            });
        }
    }
}
