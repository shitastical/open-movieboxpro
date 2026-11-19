package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.mikephil.charting.data.Entry;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.adapter.TestLoadingAdapter;
import com.movieboxpro.android.adapter.TestNetAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.model.IpInfoModel;
import com.movieboxpro.android.model.SpeedTestFeedbackModel;
import com.movieboxpro.android.model.TestLoadingModel;
import com.movieboxpro.android.model.TestNetMultiModel;
import com.movieboxpro.android.model.TestSpeedModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentTestNetSpeedBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: TestNetSpeedFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002cdB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016J\u001a\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020&H\u0003J\u0010\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u000202H\u0002J$\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u0002062\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0002J\u0010\u00109\u001a\u00020\u00062\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010:\u001a\u0002062\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010;\u001a\u00020\t2\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010<\u001a\u0002062\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010=\u001a\u00020&2\u0006\u00105\u001a\u000206H\u0002J\u0010\u0010>\u001a\u00020&2\u0006\u0010?\u001a\u000206H\u0002J\u0018\u0010A\u001a\u00020&2\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010CH\u0002J\b\u0010D\u001a\u00020&H\u0002J\u001e\u0010E\u001a\u0002082\u0006\u0010F\u001a\u0002062\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00100CH\u0002J\u0018\u0010H\u001a\u0002062\u0006\u0010?\u001a\u0002062\u0006\u0010I\u001a\u00020\u0006H\u0002J\u0018\u0010J\u001a\u0002062\u0006\u0010?\u001a\u0002062\u0006\u0010I\u001a\u00020\tH\u0002J\b\u0010K\u001a\u00020&H\u0002J\b\u0010L\u001a\u00020&H\u0002J\b\u0010M\u001a\u00020&H\u0016J\b\u0010N\u001a\u00020&H\u0016J\b\u0010O\u001a\u00020&H\u0003J(\u0010P\u001a\u0004\u0018\u00010\u001d2\b\u0010Q\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010R\u001a\u0004\u0018\u00010\u001dH\u0002J$\u0010S\u001a\u0004\u0018\u00010\u001d2\u0006\u0010Q\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010R\u001a\u0004\u0018\u00010\u001dH\u0002J\u001a\u0010T\u001a\u0004\u0018\u00010\u001d2\u0006\u0010U\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020\u001dH\u0002J\b\u0010W\u001a\u00020&H\u0003J\u0016\u0010X\u001a\u00020&2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020&0ZH\u0002J!\u0010[\u001a\u00020&2\u0012\u0010\\\u001a\n\u0012\u0006\b\u0001\u0012\u00020^0]\"\u00020^H\u0002¢\u0006\u0002\u0010_J\b\u0010`\u001a\u00020&H\u0002J\b\u0010a\u001a\u00020&H\u0002J\b\u0010b\u001a\u00020&H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\fj\b\u0012\u0004\u0012\u00020\u0010`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020 0\fj\b\u0012\u0004\u0012\u00020 `\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010!\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\"0\fj\n\u0012\u0006\u0012\u0004\u0018\u00010\"`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TestNetSpeedFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentTestNetSpeedBinding;", "<init>", "()V", "testAdapterOne", "Lcom/movieboxpro/android/adapter/TestNetAdapter;", "testAdapterTwo", "loadingAdapterOne", "Lcom/movieboxpro/android/adapter/TestLoadingAdapter;", "loadingAdapterTwo", "fileList", "Ljava/util/ArrayList;", "Ljava/io/File;", "Lkotlin/collections/ArrayList;", "listModel", "Lcom/movieboxpro/android/model/TestNetMultiModel;", "onSelectServerListener", "Lcom/movieboxpro/android/view/dialog/TestNetSpeedFragment$OnSelectServerListener;", "client", "Lokhttp3/OkHttpClient;", "disposable", "Lio/reactivex/disposables/Disposable;", "isAutoTesting", "", "speedTime", "", "speedInterval", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "", "ipLocation", "callList", "Lokhttp3/Call;", "callbacks", "Lokhttp3/Callback;", "handle", "Landroid/os/Handler;", "onStart", "", "onAttach", "context", "Landroid/content/Context;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "requestWritePermission", "formatSpeed", "speedKbS", "", "singleTest", "model", "position", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/TestNetSpeedFragment$OnTestSpeedListener;", "getTestNetAdapter", "getTestNetPosition", "getTestLoadingAdapter", "getTestLoadingPosition", "notifyTestNetAdapter", "deleteCacheFile", "id", "currPosition", "doTest", "list", "", "getIp", "getListener", "size", "info", "findTestIndex", "adapter", "findSpeedIndex", "requestData", "initListener", "onDestroy", "onDestroyView", "sendTestInfo", "addHeadTestSpeedInfo", "server", "speed", "addTestSpeedInfo", "getSpace", "text", "length", "feedBack", "runOnUi", "doSomeThing", "Lkotlin/Function0;", "initRecyclerViews", "recyclerViews", "", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "([Lcom/owen/tvrecyclerview/widget/TvRecyclerView;)V", "initData", "showLoading", "hideLoading", "OnSelectServerListener", "OnTestSpeedListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TestNetSpeedFragment extends BaseTransparentDialogFragment<FragmentTestNetSpeedBinding> {
    private ArrayList<Call> callList;
    private ArrayList<Callback> callbacks;
    private OkHttpClient client;
    private int currPosition;
    private Disposable disposable;
    private ArrayList<File> fileList;
    private final Handler handle;
    private String ip;
    private String ipLocation;
    private boolean isAutoTesting;
    private ArrayList<TestNetMultiModel> listModel;
    private TestLoadingAdapter loadingAdapterOne;
    private TestLoadingAdapter loadingAdapterTwo;
    private OnSelectServerListener onSelectServerListener;
    private final long speedInterval;
    private final long speedTime;
    private TestNetAdapter testAdapterOne;
    private TestNetAdapter testAdapterTwo;

    /* JADX INFO: compiled from: TestNetSpeedFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TestNetSpeedFragment$OnSelectServerListener;", "", "onSelectServer", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnSelectServerListener {
        void onSelectServer();
    }

    /* JADX INFO: compiled from: TestNetSpeedFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/TestNetSpeedFragment$OnTestSpeedListener;", "", "onComplete", "", "position", "", "onFailed", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnTestSpeedListener {
        void onComplete(int position);

        void onFailed(int position);
    }

    public TestNetSpeedFragment() {
        super(R.layout.fragment_test_net_speed);
        this.testAdapterOne = new TestNetAdapter();
        this.testAdapterTwo = new TestNetAdapter();
        this.loadingAdapterOne = new TestLoadingAdapter();
        this.loadingAdapterTwo = new TestLoadingAdapter();
        this.fileList = new ArrayList<>();
        this.listModel = new ArrayList<>();
        this.isAutoTesting = true;
        this.speedTime = 8000L;
        this.speedInterval = 80L;
        this.ip = "";
        this.ipLocation = "";
        this.callList = new ArrayList<>();
        this.callbacks = new ArrayList<>();
        this.handle = new Handler();
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof OnSelectServerListener) {
            this.onSelectServerListener = (OnSelectServerListener) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        getIp();
        requestData();
    }

    private final void requestWritePermission() {
        Observable<Boolean> observableRequest;
        FragmentActivity activity = getActivity();
        RxPermissions rxPermissions = activity != null ? new RxPermissions(activity) : null;
        if (rxPermissions == null || (observableRequest = rxPermissions.request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) == null) {
            return;
        }
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestNetSpeedFragment.requestWritePermission$lambda$2(this.f$0, (Boolean) obj);
            }
        };
        observableRequest.subscribe(new Consumer() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestWritePermission$lambda$2(TestNetSpeedFragment testNetSpeedFragment, Boolean bool) {
        if (bool.booleanValue()) {
            testNetSpeedFragment.getIp();
            testNetSpeedFragment.requestData();
        } else {
            ToastUtils.showShort("Test speed need the permissions", new Object[0]);
            testNetSpeedFragment.requestWritePermission();
        }
        return Unit.INSTANCE;
    }

    private final String formatSpeed(double speedKbS) {
        if (speedKbS < 0.0d) {
            return "0";
        }
        if (speedKbS < 1024.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.getDefault(), "%sB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) speedKbS)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        if (speedKbS < 1048576.0d) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format(Locale.getDefault(), "%sKB/S", Arrays.copyOf(new Object[]{Integer.valueOf((int) (speedKbS / ((double) 1024)))}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            return str2;
        }
        if (speedKbS < 1.073741824E9d) {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str3 = String.format(Locale.getDefault(), "%.1fMB/S", Arrays.copyOf(new Object[]{Double.valueOf(speedKbS / ((double) 1048576))}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            return str3;
        }
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        String str4 = String.format(Locale.getDefault(), "%.1fGB/S", Arrays.copyOf(new Object[]{Double.valueOf(speedKbS / ((double) 1073741824))}, 1));
        Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
        return str4;
    }

    static /* synthetic */ void singleTest$default(TestNetSpeedFragment testNetSpeedFragment, TestNetMultiModel testNetMultiModel, int i, OnTestSpeedListener onTestSpeedListener, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            onTestSpeedListener = null;
        }
        testNetSpeedFragment.singleTest(testNetMultiModel, i, onTestSpeedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void singleTest(final TestNetMultiModel model, final int position, final OnTestSpeedListener listener) {
        this.handle.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                TestNetSpeedFragment.singleTest$lambda$10(model, this, position, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void singleTest$lambda$10(final TestNetMultiModel testNetMultiModel, final TestNetSpeedFragment testNetSpeedFragment, final int i, final OnTestSpeedListener onTestSpeedListener) {
        final File file = new File(Constant.DIR_DOWNLOAD_TEST_SPEED, "testData" + testNetMultiModel.getId() + ".dat");
        if (file.exists()) {
            file.delete();
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.LongRef longRef = new Ref.LongRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Observable observableJust = Observable.just(testNetMultiModel);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestNetSpeedFragment.singleTest$lambda$10$lambda$4(this.f$0, objectRef, testNetMultiModel, i, onTestSpeedListener, booleanRef, file, (TestNetMultiModel) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda14
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TestNetSpeedFragment.singleTest$lambda$10$lambda$5(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestNetSpeedFragment.singleTest$lambda$10$lambda$8(this.f$0, longRef, (Unit) obj);
            }
        };
        ((ObservableSubscribeProxy) map.flatMap(new Function() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda16
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TestNetSpeedFragment.singleTest$lambda$10$lambda$9(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(testNetSpeedFragment))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$singleTest$1$3
            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(Long l) {
                onNext(l.longValue());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                testNetMultiModel.setStatus(3);
                testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestNetPosition(i));
                testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(3);
                testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
                testNetSpeedFragment.deleteCacheFile(testNetMultiModel.getId());
                TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                if (onTestSpeedListener2 != null) {
                    onTestSpeedListener2.onComplete(i);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                objectRef.element = d;
                testNetSpeedFragment.disposable = d;
                TestNetMultiModel item = testNetSpeedFragment.getTestNetAdapter(i).getItem(testNetSpeedFragment.getTestNetPosition(i));
                if (item != null) {
                    item.setStatus(4);
                    testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(i);
                    testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(4);
                    testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
                }
            }

            public void onNext(long t) {
                if (booleanRef.element) {
                    if (longRef.element <= 3) {
                        longRef.element++;
                        return;
                    }
                    long length = file.length();
                    if (length >= testNetMultiModel.getContentLength()) {
                        Disposable disposable = objectRef.element;
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        testNetMultiModel.setStatus(3);
                        testNetMultiModel.setCurrSize(length);
                        testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(i);
                        TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                        if (onTestSpeedListener2 != null) {
                            onTestSpeedListener2.onComplete(i);
                        }
                        testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(3);
                        testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
                        return;
                    }
                    testNetMultiModel.setStatus(1);
                    testNetMultiModel.setCurrSize(length);
                    String strDivide = CommonUtils.divide(String.valueOf(length), String.valueOf(System.currentTimeMillis() - testNetMultiModel.getStartTime()), 5);
                    TestNetMultiModel testNetMultiModel2 = testNetMultiModel;
                    float f = longRef.element;
                    Intrinsics.checkNotNull(strDivide);
                    testNetMultiModel2.setAddEntry(new Entry(f, Float.parseFloat(strDivide)));
                    longRef.element++;
                    testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestNetPosition(i));
                    testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(1);
                    testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ToastUtils.showShort(e.getMessage(), new Object[0]);
                testNetMultiModel.setStatus(2);
                testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestNetPosition(i));
                testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(2);
                testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
                TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener2 = onTestSpeedListener;
                if (onTestSpeedListener2 != null) {
                    onTestSpeedListener2.onFailed(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit singleTest$lambda$10$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit singleTest$lambda$10$lambda$4(TestNetSpeedFragment testNetSpeedFragment, Ref.ObjectRef objectRef, TestNetMultiModel testNetMultiModel, int i, OnTestSpeedListener onTestSpeedListener, Ref.BooleanRef booleanRef, File file, TestNetMultiModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Request.Builder builder = new Request.Builder();
        String newUrl = it.getNewUrl();
        Intrinsics.checkNotNullExpressionValue(newUrl, "getNewUrl(...)");
        Request requestBuild = builder.url(newUrl).build();
        OkHttpClient okHttpClient = testNetSpeedFragment.client;
        if (okHttpClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            okHttpClient = null;
        }
        Call callNewCall = okHttpClient.newCall(requestBuild);
        testNetSpeedFragment.callList.add(callNewCall);
        TestNetSpeedFragment$singleTest$1$1$callback$1 testNetSpeedFragment$singleTest$1$1$callback$1 = new TestNetSpeedFragment$singleTest$1$1$callback$1(testNetSpeedFragment, objectRef, testNetMultiModel, i, onTestSpeedListener, booleanRef, file);
        testNetSpeedFragment.callbacks.add(testNetSpeedFragment$singleTest$1$1$callback$1);
        callNewCall.enqueue(testNetSpeedFragment$singleTest$1$1$callback$1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource singleTest$lambda$10$lambda$9(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource singleTest$lambda$10$lambda$8(final TestNetSpeedFragment testNetSpeedFragment, final Ref.LongRef longRef, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Observable<Long> observableInterval = Observable.interval(100L, testNetSpeedFragment.speedInterval, TimeUnit.MILLISECONDS);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TestNetSpeedFragment.singleTest$lambda$10$lambda$8$lambda$6(longRef, testNetSpeedFragment, (Long) obj));
            }
        };
        return observableInterval.takeUntil(new Predicate() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Object obj) {
                return TestNetSpeedFragment.singleTest$lambda$10$lambda$8$lambda$7(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean singleTest$lambda$10$lambda$8$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) function1.invoke(p0)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean singleTest$lambda$10$lambda$8$lambda$6(Ref.LongRef longRef, TestNetSpeedFragment testNetSpeedFragment, Long it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return longRef.element >= testNetSpeedFragment.speedTime / testNetSpeedFragment.speedInterval;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TestNetAdapter getTestNetAdapter(int position) {
        if (position % 2 == 0) {
            return this.testAdapterOne;
        }
        return this.testAdapterTwo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTestNetPosition(int position) {
        if (position % 2 == 0) {
            return position / 2;
        }
        return (position - 1) / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TestLoadingAdapter getTestLoadingAdapter(int position) {
        if (position % 2 == 0) {
            return this.loadingAdapterOne;
        }
        return this.loadingAdapterTwo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTestLoadingPosition(int position) {
        if (position % 2 == 0) {
            return position / 2;
        }
        return (position - 1) / 2;
    }

    private final void notifyTestNetAdapter(int position) {
        if (position % 2 == 0) {
            this.testAdapterOne.notifyItemChanged(position / 2);
        } else {
            this.testAdapterTwo.notifyItemChanged((position / 2) + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteCacheFile(int id) {
        new File(Constant.DIR_DOWNLOAD_TEST_SPEED, "testData" + id + ".dat").delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doTest(List<? extends TestNetMultiModel> list) {
        int size = list != null ? list.size() : 0;
        if (list == null || list.isEmpty()) {
            return;
        }
        singleTest(list.get(this.currPosition), this.currPosition, getListener(size, list));
    }

    private final void getIp() {
        ((ObservableSubscribeProxy) Http.getService().simple("Ip_info").compose(RxUtils.rxTranslate2Bean(IpInfoModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<IpInfoModel>() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment.getIp.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(IpInfoModel info) {
                IpInfoModel.LocationBean location;
                List<IpInfoModel.LocationBean.SubdivisionsBean> subdivisions;
                String en;
                String en2;
                IpInfoModel.LocationBean.CountryBean country;
                IpInfoModel.NamesBean names;
                IpInfoModel.LocationBean.ContinentBean continent;
                IpInfoModel.NamesBean names2;
                Intrinsics.checkNotNullParameter(info, "info");
                try {
                    if (info.getLocation() != null) {
                        IpInfoModel.LocationBean location2 = info.getLocation();
                        if ((location2 != null ? location2.getSubdivisions() : null) == null || (location = info.getLocation()) == null || (subdivisions = location.getSubdivisions()) == null || !(!subdivisions.isEmpty())) {
                            return;
                        }
                        IpInfoModel.LocationBean.SubdivisionsBean subdivisionsBean = info.getLocation().getSubdivisions().get(0);
                        TestNetSpeedFragment testNetSpeedFragment = TestNetSpeedFragment.this;
                        IpInfoModel.LocationBean location3 = info.getLocation();
                        String str = "";
                        if (location3 == null || (continent = location3.getContinent()) == null || (names2 = continent.getNames()) == null || (en = names2.getEn()) == null) {
                            en = "";
                        }
                        IpInfoModel.LocationBean location4 = info.getLocation();
                        if (location4 == null || (country = location4.getCountry()) == null || (names = country.getNames()) == null || (en2 = names.getEn()) == null) {
                            en2 = "";
                        }
                        String en3 = subdivisionsBean.getNames().getEn();
                        if (en3 != null) {
                            str = en3;
                        }
                        testNetSpeedFragment.ipLocation = en + "," + en2 + "," + str;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnTestSpeedListener getListener(final int size, final List<? extends TestNetMultiModel> info) {
        return new OnTestSpeedListener() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment.getListener.1
            @Override // com.movieboxpro.android.view.dialog.TestNetSpeedFragment.OnTestSpeedListener
            public void onComplete(int position) {
                TestNetSpeedFragment.this.currPosition++;
                if (TestNetSpeedFragment.this.currPosition <= size - 1) {
                    String newUrl = info.get(TestNetSpeedFragment.this.currPosition).getNewUrl();
                    if (newUrl == null || StringsKt.isBlank(newUrl)) {
                        TestNetSpeedFragment.this.currPosition++;
                        if (TestNetSpeedFragment.this.currPosition <= size - 1) {
                            TestNetSpeedFragment testNetSpeedFragment = TestNetSpeedFragment.this;
                            testNetSpeedFragment.singleTest(info.get(testNetSpeedFragment.currPosition), TestNetSpeedFragment.this.currPosition, TestNetSpeedFragment.this.getListener(size, info));
                            return;
                        }
                        return;
                    }
                    TestNetSpeedFragment testNetSpeedFragment2 = TestNetSpeedFragment.this;
                    testNetSpeedFragment2.singleTest(info.get(testNetSpeedFragment2.currPosition), TestNetSpeedFragment.this.currPosition, TestNetSpeedFragment.this.getListener(size, info));
                }
            }

            @Override // com.movieboxpro.android.view.dialog.TestNetSpeedFragment.OnTestSpeedListener
            public void onFailed(int position) {
                TestNetSpeedFragment.this.currPosition++;
                if (TestNetSpeedFragment.this.currPosition <= size - 1) {
                    String newUrl = info.get(TestNetSpeedFragment.this.currPosition).getNewUrl();
                    if (newUrl == null || StringsKt.isBlank(newUrl)) {
                        TestNetSpeedFragment.this.currPosition++;
                        if (TestNetSpeedFragment.this.currPosition <= size - 1) {
                            TestNetSpeedFragment testNetSpeedFragment = TestNetSpeedFragment.this;
                            testNetSpeedFragment.singleTest(info.get(testNetSpeedFragment.currPosition), TestNetSpeedFragment.this.currPosition, TestNetSpeedFragment.this.getListener(size, info));
                            return;
                        }
                        return;
                    }
                    TestNetSpeedFragment testNetSpeedFragment2 = TestNetSpeedFragment.this;
                    testNetSpeedFragment2.singleTest(info.get(testNetSpeedFragment2.currPosition), TestNetSpeedFragment.this.currPosition, TestNetSpeedFragment.this.getListener(size, info));
                }
            }
        };
    }

    private final int findTestIndex(int id, TestNetAdapter adapter) {
        int i = 0;
        for (Object obj : adapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TestNetMultiModel) obj).getId() == id) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    private final int findSpeedIndex(int id, TestLoadingAdapter adapter) {
        int i = 0;
        for (Object obj : adapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TestLoadingModel) obj).getId() == id) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    private final void requestData() {
        ((ObservableSubscribeProxy) Http.getService().Register_NeedCode(API.Common.TEST_NETWORK).compose(RxUtils.rxTranslate2Bean(TestSpeedModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new C01871());
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$requestData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TestNetSpeedFragment.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/movieboxpro/android/view/dialog/TestNetSpeedFragment$requestData$1", "Lcom/movieboxpro/android/base/HttpResponseObserver;", "Lcom/movieboxpro/android/model/TestSpeedModel;", "onStart", "", "disposable", "Lio/reactivex/disposables/Disposable;", "onSuccess", "list", "onError", "e", "Lcom/movieboxpro/android/http/ApiException;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class C01871 extends HttpResponseObserver<TestSpeedModel> {
        C01871() {
        }

        @Override // com.movieboxpro.android.base.HttpResponseObserver
        public void onStart(Disposable disposable) {
            Intrinsics.checkNotNullParameter(disposable, "disposable");
            TestNetSpeedFragment.this.showLoading();
        }

        @Override // com.movieboxpro.android.base.HttpResponseObserver
        public void onSuccess(TestSpeedModel list) {
            Intrinsics.checkNotNullParameter(list, "list");
            TestNetSpeedFragment.this.listModel = new ArrayList();
            TestNetSpeedFragment.this.listModel.addAll(list.getOur());
            TestNetSpeedFragment.this.listModel.addAll(list.getThird());
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            String string = PrefsUtils.getInstance().getString(Constant.SELECT_SERVER_URL);
            int i = 0;
            for (Object obj : TestNetSpeedFragment.this.listModel) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TestNetMultiModel testNetMultiModel = (TestNetMultiModel) obj;
                String str = string;
                if (str == null || str.length() == 0) {
                    if (i == 0) {
                        testNetMultiModel.setSelect(true);
                    }
                } else if (Intrinsics.areEqual(testNetMultiModel.getDomain(), string)) {
                    testNetMultiModel.setSelect(true);
                }
                if (i % 2 == 0) {
                    arrayList.add(testNetMultiModel);
                    arrayList3.add(new TestLoadingModel(testNetMultiModel.getId()));
                } else {
                    arrayList2.add(testNetMultiModel);
                    arrayList4.add(new TestLoadingModel(testNetMultiModel.getId()));
                }
                i = i2;
            }
            TestNetSpeedFragment.this.testAdapterOne.setList(arrayList);
            TestNetSpeedFragment.this.testAdapterTwo.setList(arrayList2);
            TestNetSpeedFragment.this.loadingAdapterOne.setList(arrayList3);
            TestNetSpeedFragment.this.loadingAdapterTwo.setList(arrayList4);
            TextView tvTitle = TestNetSpeedFragment.this.getBinding().tvTitle;
            Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
            CommonExtKt.visible(tvTitle);
            TestNetSpeedFragment.this.hideLoading();
            Handler handler = TestNetSpeedFragment.this.handle;
            final TestNetSpeedFragment testNetSpeedFragment = TestNetSpeedFragment.this;
            handler.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$requestData$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TestNetSpeedFragment.C01871.onSuccess$lambda$1(testNetSpeedFragment);
                }
            }, 1000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$1(TestNetSpeedFragment testNetSpeedFragment) {
            testNetSpeedFragment.doTest(testNetSpeedFragment.listModel);
        }

        @Override // com.movieboxpro.android.base.HttpResponseObserver
        public void onError(ApiException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            TestNetSpeedFragment.this.hideLoading();
            ToastUtils.showShort("Get Test data failed", new Object[0]);
        }
    }

    private final void initListener() {
        this.testAdapterOne.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestNetSpeedFragment.initListener$lambda$13(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.testAdapterTwo.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestNetSpeedFragment.initListener$lambda$14(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.loadingAdapterOne.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestNetSpeedFragment.initListener$lambda$19(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.loadingAdapterTwo.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda8
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TestNetSpeedFragment.initListener$lambda$24(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13(TestNetSpeedFragment testNetSpeedFragment, BaseQuickAdapter adapter, View view, int i) {
        OnSelectServerListener onSelectServerListener;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TestNetMultiModel testNetMultiModel = testNetSpeedFragment.testAdapterOne.getData().get(i);
        if (testNetMultiModel != null && testNetMultiModel.getOur() != 1) {
            ToastUtils.showShort("It's not a server,just for testing speed", new Object[0]);
            return;
        }
        if (i == 0) {
            PrefsUtils.getInstance().putString(Constant.SELECT_SERVER_URL, "");
        } else {
            TestNetMultiModel item = testNetSpeedFragment.testAdapterOne.getItem(i);
            PrefsUtils.getInstance().putString(Constant.SELECT_SERVER_URL, item != null ? item.getDomain() : null);
            ToastUtils.showShort("Chosen '" + (item != null ? item.getCountry() : null) + "' server", new Object[0]);
        }
        TestNetMultiModel item2 = testNetSpeedFragment.testAdapterOne.getItem(i);
        if (item2 != null) {
            if (item2.getId() != 1) {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, item2.getDomain());
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, item2.getGroup_id());
            } else {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, "Error");
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, "");
            }
            if (!item2.isSelect() && (onSelectServerListener = testNetSpeedFragment.onSelectServerListener) != null) {
                onSelectServerListener.onSelectServer();
            }
        }
        testNetSpeedFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$14(TestNetSpeedFragment testNetSpeedFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        OnSelectServerListener onSelectServerListener;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        TestNetMultiModel testNetMultiModel = testNetSpeedFragment.testAdapterTwo.getData().get(i);
        if (testNetMultiModel != null && testNetMultiModel.getOur() != 1) {
            ToastUtils.showShort("It's not a server,just for testing speed", new Object[0]);
            return;
        }
        TestNetMultiModel item = testNetSpeedFragment.testAdapterTwo.getItem(i);
        if (item != null) {
            PrefsUtils.getInstance().putString(Constant.SELECT_SERVER_URL, item.getDomain());
            testNetSpeedFragment.dismiss();
            ToastUtils.showShort("Chosen '" + item.getCountry() + "' server", new Object[0]);
            if (item.getId() != 1) {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, item.getDomain());
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, item.getGroup_id());
            } else {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, "Error");
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, "");
            }
            if (item.isSelect() || (onSelectServerListener = testNetSpeedFragment.onSelectServerListener) == null) {
                return;
            }
            onSelectServerListener.onSelectServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$19(TestNetSpeedFragment testNetSpeedFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        TestLoadingModel item = testNetSpeedFragment.loadingAdapterOne.getItem(i);
        if (item != null) {
            int i2 = 0;
            if (item.getStatus() == 1) {
                Disposable disposable = testNetSpeedFragment.disposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                item.setStatus(3);
                testNetSpeedFragment.testAdapterOne.getData().get(i).setStatus(3);
                if (testNetSpeedFragment.isAutoTesting) {
                    testNetSpeedFragment.isAutoTesting = false;
                    int i3 = 0;
                    for (Object obj : testNetSpeedFragment.testAdapterOne.getData()) {
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestNetMultiModel testNetMultiModel = (TestNetMultiModel) obj;
                        if (i3 > i) {
                            testNetMultiModel.setStatus(5);
                        }
                        testNetSpeedFragment.testAdapterOne.notifyItemChanged(i3);
                        i3 = i4;
                    }
                    int i5 = 0;
                    for (Object obj2 : testNetSpeedFragment.loadingAdapterOne.getData()) {
                        int i6 = i5 + 1;
                        if (i5 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestLoadingModel testLoadingModel = (TestLoadingModel) obj2;
                        if (i5 > i) {
                            testLoadingModel.setStatus(5);
                        }
                        testNetSpeedFragment.loadingAdapterOne.notifyItemChanged(i5);
                        i5 = i6;
                    }
                    int i7 = 0;
                    for (Object obj3 : testNetSpeedFragment.testAdapterTwo.getData()) {
                        int i8 = i7 + 1;
                        if (i7 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestNetMultiModel testNetMultiModel2 = (TestNetMultiModel) obj3;
                        if (testNetMultiModel2.getStatus() != 3 && testNetMultiModel2.getStatus() != 2) {
                            testNetMultiModel2.setStatus(5);
                        }
                        testNetSpeedFragment.testAdapterTwo.notifyItemChanged(i7);
                        i7 = i8;
                    }
                    for (Object obj4 : testNetSpeedFragment.loadingAdapterTwo.getData()) {
                        int i9 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestLoadingModel testLoadingModel2 = (TestLoadingModel) obj4;
                        if (testLoadingModel2.getStatus() != 3 && testLoadingModel2.getStatus() != 2) {
                            testLoadingModel2.setStatus(5);
                        }
                        testNetSpeedFragment.loadingAdapterTwo.notifyItemChanged(i2);
                        i2 = i9;
                    }
                    return;
                }
                testNetSpeedFragment.testAdapterOne.notifyItemChanged(i);
                testNetSpeedFragment.loadingAdapterOne.notifyItemChanged(i);
                return;
            }
            if (item.getStatus() == 3 || item.getStatus() == 2 || item.getStatus() == 5) {
                item.setStatus(5);
                TestNetMultiModel testNetMultiModel3 = testNetSpeedFragment.testAdapterOne.getData().get(i);
                testNetMultiModel3.setStartTime(0L);
                testNetMultiModel3.setAddEntry(null);
                testNetMultiModel3.setCurrSize(0L);
                testNetMultiModel3.setSize(0L);
                testNetMultiModel3.setLineData(null);
                testNetMultiModel3.setInit(false);
                testNetMultiModel3.setStatus(0);
                testNetMultiModel3.setValues(null);
                testNetMultiModel3.setStatus(5);
                testNetSpeedFragment.testAdapterOne.notifyItemChanged(i);
                testNetSpeedFragment.loadingAdapterOne.notifyItemChanged(i);
                singleTest$default(testNetSpeedFragment, testNetMultiModel3, i * 2, null, 4, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$24(TestNetSpeedFragment testNetSpeedFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TestLoadingModel item = testNetSpeedFragment.loadingAdapterTwo.getItem(i);
        if (item != null) {
            int i2 = 0;
            if (item.getStatus() == 1) {
                Disposable disposable = testNetSpeedFragment.disposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                item.setStatus(3);
                testNetSpeedFragment.testAdapterTwo.getData().get(i).setStatus(3);
                if (testNetSpeedFragment.isAutoTesting) {
                    testNetSpeedFragment.isAutoTesting = false;
                    int i3 = 0;
                    for (Object obj : testNetSpeedFragment.testAdapterOne.getData()) {
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestNetMultiModel testNetMultiModel = (TestNetMultiModel) obj;
                        if (testNetMultiModel.getStatus() != 3 && testNetMultiModel.getStatus() != 2) {
                            testNetMultiModel.setStatus(5);
                        }
                        testNetSpeedFragment.testAdapterOne.notifyItemChanged(i3);
                        i3 = i4;
                    }
                    int i5 = 0;
                    for (Object obj2 : testNetSpeedFragment.loadingAdapterOne.getData()) {
                        int i6 = i5 + 1;
                        if (i5 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestLoadingModel testLoadingModel = (TestLoadingModel) obj2;
                        if (testLoadingModel.getStatus() != 3 && testLoadingModel.getStatus() != 2) {
                            testLoadingModel.setStatus(5);
                        }
                        testNetSpeedFragment.loadingAdapterOne.notifyItemChanged(i5);
                        i5 = i6;
                    }
                    int i7 = 0;
                    for (Object obj3 : testNetSpeedFragment.testAdapterTwo.getData()) {
                        int i8 = i7 + 1;
                        if (i7 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestNetMultiModel testNetMultiModel2 = (TestNetMultiModel) obj3;
                        if (i7 > i) {
                            testNetMultiModel2.setStatus(5);
                        }
                        testNetSpeedFragment.testAdapterTwo.notifyItemChanged(i7);
                        i7 = i8;
                    }
                    for (Object obj4 : testNetSpeedFragment.loadingAdapterTwo.getData()) {
                        int i9 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TestLoadingModel testLoadingModel2 = (TestLoadingModel) obj4;
                        if (i2 > i) {
                            testLoadingModel2.setStatus(5);
                        }
                        testNetSpeedFragment.loadingAdapterTwo.notifyItemChanged(i2);
                        i2 = i9;
                    }
                    return;
                }
                testNetSpeedFragment.testAdapterTwo.notifyItemChanged(i);
                testNetSpeedFragment.loadingAdapterTwo.notifyItemChanged(i);
                return;
            }
            if (item.getStatus() == 3 || item.getStatus() == 2 || item.getStatus() == 5) {
                item.setStatus(5);
                TestNetMultiModel testNetMultiModel3 = testNetSpeedFragment.testAdapterTwo.getData().get(i);
                testNetMultiModel3.setStartTime(0L);
                testNetMultiModel3.setAddEntry(null);
                testNetMultiModel3.setCurrSize(0L);
                testNetMultiModel3.setSize(0L);
                testNetMultiModel3.setLineData(null);
                testNetMultiModel3.setInit(false);
                testNetMultiModel3.setStatus(0);
                testNetMultiModel3.setValues(null);
                testNetMultiModel3.setStatus(5);
                testNetSpeedFragment.testAdapterTwo.notifyItemChanged(i);
                testNetSpeedFragment.loadingAdapterTwo.notifyItemChanged(i);
                singleTest$default(testNetSpeedFragment, testNetMultiModel3, (i * 2) + 1, null, 4, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.handle.removeCallbacksAndMessages(null);
        FileUtils.deleteAllInDir(new File(Constant.DIR_DOWNLOAD_TEST_SPEED));
        if (App.isLogin()) {
            feedBack();
            sendTestInfo();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        Iterator<T> it = this.callList.iterator();
        while (it.hasNext()) {
            ((Call) it.next()).cancel();
        }
        this.callList.clear();
        Iterator<T> it2 = this.callbacks.iterator();
        int i = 0;
        while (it2.hasNext()) {
            it2.next();
            this.callbacks.set(i, null);
            i++;
        }
        this.callbacks.clear();
        super.onDestroyView();
    }

    private final void sendTestInfo() {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.testAdapterOne.getData());
        arrayList.addAll(this.testAdapterTwo.getData());
        Observable observableJust = Observable.just(arrayList);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestNetSpeedFragment.sendTestInfo$lambda$27((ArrayList) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TestNetSpeedFragment.sendTestInfo$lambda$28(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestNetSpeedFragment.sendTestInfo$lambda$30(this.f$0, arrayList, (Unit) obj);
            }
        };
        map.flatMap(new Function() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TestNetSpeedFragment.sendTestInfo$lambda$31(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment.sendTestInfo.3
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Log.d(TestNetSpeedFragment.this.getClass().getSimpleName(), "sendTestInfo success");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTestInfo$lambda$28(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTestInfo$lambda$27(ArrayList it) {
        String strSubstring;
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            TestNetMultiModel testNetMultiModel = (TestNetMultiModel) it2.next();
            String domain = testNetMultiModel.getDomain();
            if (domain != null) {
                String domain2 = testNetMultiModel.getDomain();
                Intrinsics.checkNotNullExpressionValue(domain2, "getDomain(...)");
                strSubstring = domain.substring(StringsKt.lastIndexOf$default((CharSequence) domain2, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            } else {
                strSubstring = null;
            }
            testNetMultiModel.setDomainIp(Network.getDomainAddress(strSubstring));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource sendTestInfo$lambda$31(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource sendTestInfo$lambda$30(TestNetSpeedFragment testNetSpeedFragment, ArrayList arrayList, Unit it) {
        int iAverageOfInt;
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(testNetSpeedFragment.addHeadTestSpeedInfo(HttpHeaders.SERVER, "IP", "Speed"));
        Iterator it2 = arrayList.iterator();
        String country = "";
        int i = 0;
        while (it2.hasNext()) {
            TestNetMultiModel testNetMultiModel = (TestNetMultiModel) it2.next();
            if (testNetMultiModel.getSpeeds() == null || testNetMultiModel.getSpeeds().size() <= 5) {
                iAverageOfInt = 0;
            } else {
                List<Integer> speeds = testNetMultiModel.getSpeeds();
                iAverageOfInt = (int) (speeds != null ? CollectionsKt.averageOfInt(speeds) : 0.0d);
            }
            if (iAverageOfInt > i) {
                country = testNetMultiModel.getCountry();
                Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
                i = iAverageOfInt;
            }
            String domain = testNetMultiModel.getDomain();
            if (domain == null) {
                domain = "";
            }
            String domainIp = testNetMultiModel.getDomainIp();
            if (domainIp == null) {
                domainIp = "";
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%sKB/s", Arrays.copyOf(new Object[]{Integer.valueOf(iAverageOfInt)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            sb.append(testNetSpeedFragment.addTestSpeedInfo(domain, domainIp, str));
        }
        String str2 = "(api)Test_network:\n" + SystemUtils.getMsg2() + "\nlatitude: longitude:\nipLocation:" + testNetSpeedFragment.ipLocation + "\ngpsLocation:\n\n\n\n" + ((Object) sb);
        return Http.getService().Movie_feedback2(API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", -1, "", -1, "", "14", str2, 0, 0, "Speed test:" + testNetSpeedFragment.ipLocation + " " + ((Object) country) + " " + i + "KB/s").compose(RxUtils.rxTranslateMsg());
    }

    private final String addHeadTestSpeedInfo(String server, String ip, String speed) {
        return server + "                                                  " + ip + "                                                  " + speed + ShellUtil.COMMAND_LINE_END;
    }

    private final String addTestSpeedInfo(String server, String ip, String speed) {
        StringBuilder sb = new StringBuilder();
        sb.append(server);
        sb.append(getSpace(server, HttpHeaders.SERVER));
        if (TextUtils.isEmpty(ip)) {
            sb.append("               ");
            sb.append(getSpace("145.239.252.191", "IP"));
        } else {
            sb.append(ip);
            sb.append(getSpace(ip, "IP"));
        }
        sb.append(speed);
        sb.append(ShellUtil.COMMAND_LINE_END);
        return sb.toString();
    }

    private final String getSpace(String text, String length) {
        StringBuilder sb = new StringBuilder();
        int length2 = (length.length() + 50) - text.length();
        for (int i = 0; i < length2; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private final void feedBack() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.testAdapterOne.getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TestNetMultiModel testNetMultiModel = (TestNetMultiModel) it.next();
            if (testNetMultiModel.getSpeeds() != null) {
                SpeedTestFeedbackModel speedTestFeedbackModel = new SpeedTestFeedbackModel();
                List<Integer> speeds = testNetMultiModel.getSpeeds();
                Intrinsics.checkNotNullExpressionValue(speeds, "getSpeeds(...)");
                speedTestFeedbackModel.setAverage((int) CollectionsKt.averageOfInt(speeds));
                String group_id = testNetMultiModel.getGroup_id();
                speedTestFeedbackModel.setGroup(group_id != null ? group_id : "");
                speedTestFeedbackModel.setTest_url_id(String.valueOf(testNetMultiModel.getId()));
                arrayList.add(speedTestFeedbackModel);
            }
        }
        for (TestNetMultiModel testNetMultiModel2 : this.testAdapterTwo.getData()) {
            if (testNetMultiModel2.getSpeeds() != null) {
                SpeedTestFeedbackModel speedTestFeedbackModel2 = new SpeedTestFeedbackModel();
                List<Integer> speeds2 = testNetMultiModel2.getSpeeds();
                Intrinsics.checkNotNullExpressionValue(speeds2, "getSpeeds(...)");
                speedTestFeedbackModel2.setAverage((int) CollectionsKt.averageOfInt(speeds2));
                String group_id2 = testNetMultiModel2.getGroup_id();
                if (group_id2 == null) {
                    group_id2 = "";
                }
                speedTestFeedbackModel2.setGroup(group_id2);
                speedTestFeedbackModel2.setTest_url_id(String.valueOf(testNetMultiModel2.getId()));
                arrayList.add(speedTestFeedbackModel2);
            }
        }
        HashMap map = new HashMap();
        HashMap map2 = map;
        String str = App.getUserData().uid_v2;
        if (str == null) {
            str = "";
        }
        map2.put("uid", str);
        String str2 = this.ip;
        if (str2 == null) {
            str2 = "";
        }
        map2.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, str2);
        map2.put("gps_location", "");
        String str3 = this.ipLocation;
        map2.put("ip_location", str3 != null ? str3 : "");
        map2.put("speed", arrayList);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("text/plain; charset=utf-8");
        String jSONString = JSON.toJSONString(map);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        Http.getService().sortFavorite(ApiV2EndpointConfig.moduleUrl("Test_network_feedback"), companion.create(mediaType, jSONString)).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment.feedBack.3
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                Intrinsics.checkNotNullParameter(model, "model");
                Log.d(TestNetSpeedFragment.this.getClass().getSimpleName(), "feedBack success");
            }
        });
    }

    private final void runOnUi(final Function0<Unit> doSomeThing) {
        getBinding().rvTestOne.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                doSomeThing.invoke();
            }
        });
    }

    private final void initData() {
        this.client = new OkHttpClient.Builder().connectTimeout(5L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS).writeTimeout(5L, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
        FragmentTestNetSpeedBinding binding = getBinding();
        TvRecyclerView rvTestOne = binding.rvTestOne;
        Intrinsics.checkNotNullExpressionValue(rvTestOne, "rvTestOne");
        TvRecyclerView rvTestTwo = binding.rvTestTwo;
        Intrinsics.checkNotNullExpressionValue(rvTestTwo, "rvTestTwo");
        TvRecyclerView rvLoadingOne = binding.rvLoadingOne;
        Intrinsics.checkNotNullExpressionValue(rvLoadingOne, "rvLoadingOne");
        TvRecyclerView rvLoadingTwo = binding.rvLoadingTwo;
        Intrinsics.checkNotNullExpressionValue(rvLoadingTwo, "rvLoadingTwo");
        initRecyclerViews(rvTestOne, rvTestTwo, rvLoadingOne, rvLoadingTwo);
        binding.rvTestOne.setAdapter(this.testAdapterOne);
        binding.rvTestTwo.setAdapter(this.testAdapterTwo);
        binding.rvLoadingOne.setAdapter(this.loadingAdapterOne);
        binding.rvLoadingTwo.setAdapter(this.loadingAdapterTwo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading() {
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.visible(flLoading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.gone(flLoading);
    }

    private final void initRecyclerViews(TvRecyclerView... recyclerViews) {
        for (TvRecyclerView tvRecyclerView : recyclerViews) {
            tvRecyclerView.setSpacingWithMargins(DensityUtils.dp2px(App.getContext(), 13.0f), 0);
            tvRecyclerView.setNestedScrollingEnabled(false);
            tvRecyclerView.setHasFixedSize(true);
        }
    }
}
