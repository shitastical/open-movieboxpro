package com.movieboxpro.android.view.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.ExoPlayer;
import com.adorkable.iosdialog.AlertDialog;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.DownloadCompleteEvent;
import com.movieboxpro.android.event.FeaturedKeyUpEvent;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.event.OpenChildModeEvent;
import com.movieboxpro.android.event.OpenHideMovieListEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.livedata.FocusSettingLiveData;
import com.movieboxpro.android.livedata.HideDownloadLiveData;
import com.movieboxpro.android.livedata.SelectWatchPlanWatchedLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.service.MemoryMonitorService;
import com.movieboxpro.android.tv.BaseFragment;
import com.movieboxpro.android.tv.home.MoviesFragment;
import com.movieboxpro.android.tv.home.TvGuideFragment;
import com.movieboxpro.android.tv.home.TvShowsFragment;
import com.movieboxpro.android.tv.list.PlayListHomeFragment;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.LoginExpireDialog;
import com.movieboxpro.android.view.dialog.TimeIncorrectHintDialog;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import com.movieboxpro.android.view.fragment.DownloadFragment;
import com.movieboxpro.android.view.fragment.SearchFragment;
import com.movieboxpro.android.view.fragment.WatchPlanFragment;
import com.movieboxpro.android.view.fragment.account.AccountFragment;
import com.movieboxpro.android.view.fragment.account.AccountManagerFragment;
import com.movieboxpro.android.view.fragment.account.SessionInvalidRouter;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.android.view.fragment.home.HomeFragment;
import com.movieboxpro.android.view.widget.UpdateChecker;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityMain3Binding;
import com.owen.focus.FocusBorder;
import com.owen.tab.TvTabLayout;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 h2\u00020\u00012\u00020\u0002:\u0002hiB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010)\u001a\u00020'H\u0002J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020+H\u0002J\b\u00100\u001a\u00020+H\u0002J\u0012\u00101\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0002J\b\u00102\u001a\u00020+H\u0002J\b\u00103\u001a\u00020+H\u0002J\b\u00108\u001a\u00020+H\u0002J\b\u00109\u001a\u00020+H\u0002J\b\u0010:\u001a\u00020+H\u0002J\b\u0010<\u001a\u00020+H\u0002J\b\u0010=\u001a\u00020+H\u0002J\b\u0010>\u001a\u00020+H\u0002J\b\u0010?\u001a\u00020+H\u0002J\b\u0010@\u001a\u00020+H\u0002J\u0010\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020'H\u0002J\b\u0010C\u001a\u00020+H\u0002J\b\u0010D\u001a\u00020+H\u0002J\b\u0010E\u001a\u00020+H\u0002J\u0010\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020-H\u0014J\u0010\u0010H\u001a\u00020+2\u0006\u0010I\u001a\u00020JH\u0007J\u0010\u0010K\u001a\u00020+2\u0006\u0010I\u001a\u00020LH\u0007J\u0010\u0010M\u001a\u00020+2\u0006\u0010I\u001a\u00020NH\u0007J\u0010\u0010O\u001a\u00020+2\u0006\u0010I\u001a\u00020PH\u0007J\u0010\u0010Q\u001a\u00020+2\u0006\u0010I\u001a\u00020RH\u0007J\u0010\u0010S\u001a\u00020+2\u0006\u0010I\u001a\u00020TH\u0007J\u0010\u0010U\u001a\u00020+2\u0006\u0010I\u001a\u00020VH\u0007J\b\u0010W\u001a\u00020+H\u0014J\u0010\u0010X\u001a\u00020'2\u0006\u0010I\u001a\u00020YH\u0016J\u0018\u0010\\\u001a\u00020'2\u0006\u0010]\u001a\u00020\b2\u0006\u0010I\u001a\u00020YH\u0016J\b\u0010^\u001a\u00020+H\u0014J\b\u0010_\u001a\u00020+H\u0002J\b\u0010a\u001a\u00020+H\u0014J\b\u0010b\u001a\u00020+H\u0002J\u0012\u0010c\u001a\u00020+2\b\u0010d\u001a\u0004\u0018\u00010eH\u0014J\b\u0010f\u001a\u00020+H\u0002J\b\u0010g\u001a\u00020+H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020[X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/movieboxpro/android/view/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/movieboxpro/android/tv/BaseFragment$FocusBorderHelper;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "lastSaveSelectPos", "", "getFocusBorder", "tabTitles", "", "", "[Ljava/lang/String;", "featuredFragment", "Lcom/movieboxpro/android/view/fragment/home/HomeFragment;", "movieMoreFragment", "Lcom/movieboxpro/android/tv/home/MoviesFragment;", "tvMoreFragment", "Lcom/movieboxpro/android/tv/home/TvShowsFragment;", "tvGuideFragment", "Lcom/movieboxpro/android/tv/home/TvGuideFragment;", "playListFragment", "Lcom/movieboxpro/android/tv/list/PlayListHomeFragment;", "watchPlanFragment", "Lcom/movieboxpro/android/view/fragment/WatchPlanFragment;", "accountFragment", "Lcom/movieboxpro/android/view/fragment/account/AccountFragment;", "searchFragment", "Lcom/movieboxpro/android/view/fragment/SearchFragment;", "downloadFragment", "Lcom/movieboxpro/android/view/fragment/DownloadFragment;", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivityMain3Binding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivityMain3Binding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "isHomePageCreated", "", "allowLegacyFragmentRestore", "haveMoreOneMainActivity", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "maybeShowAccountManagerAfterSessionInvalid", "initUsb", "startMemoryService", "initFragment", "clearFragmentReferences", "purgeRestoredFragments", "isSearchFocus", "isFavoriteFocus", "isDownloadFocus", "isTvGuideFocus", "initListener", "showTvGuideFragment", "onTabItemClick", "isAnimation", "showTabLayout", "hideTabLayout", "initData", "registerDevice", "checkTime", "getUserInfo", "isCheckDate", "showHintVipDate", "checkVipDate", "initView", "onSaveInstanceState", "outState", "onDownloadComplete", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/DownloadCompleteEvent;", "onKeyUp", "Lcom/movieboxpro/android/event/FeaturedKeyUpEvent;", "onListFocusUp", "Lcom/movieboxpro/android/event/ListFocusUpEvent;", "onLoginIn", "Lcom/movieboxpro/android/event/LoginEvent;", "openChildMode", "Lcom/movieboxpro/android/event/OpenChildModeEvent;", "openHideMovieList", "Lcom/movieboxpro/android/event/OpenHideMovieListEvent;", "onLowMemory", "Lcom/movieboxpro/android/event/LowMemoryEvent;", "onStart", "dispatchKeyEvent", "Landroid/view/KeyEvent;", "mExitTime", "", "onKeyDown", "keyCode", "onDestroy", "stopMemoryService", "upgradeChecked", "onResume", "getDownloadingNum", "onNewIntent", "intent", "Landroid/content/Intent;", "showUpdateAlrt", "hideAllFragment", "Companion", "TabSelectedListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainActivity extends AppCompatActivity implements BaseFragment.FocusBorderHelper {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(MainActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivityMain3Binding;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AccountFragment accountFragment;
    private DownloadFragment downloadFragment;
    private HomeFragment featuredFragment;
    private boolean isAnimation;
    private boolean isDownloadFocus;
    private boolean isFavoriteFocus;
    private boolean isHomePageCreated;
    private boolean isSearchFocus;
    private boolean isTvGuideFocus;
    private long mExitTime;
    private FocusBorder mFocusBorder;
    private MoviesFragment movieMoreFragment;
    private PlayListHomeFragment playListFragment;
    private SearchFragment searchFragment;
    private TvGuideFragment tvGuideFragment;
    private TvShowsFragment tvMoreFragment;
    private boolean upgradeChecked;
    private WatchPlanFragment watchPlanFragment;
    private int lastSaveSelectPos = -1;
    private final String[] tabTitles = {"Featured", "Movies", "TV Shows", "MovieLists", "Account"};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivityMain3Binding.class, this);
    private boolean allowLegacyFragmentRestore = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateAlrt$lambda$29(View view) {
    }

    @Override // com.movieboxpro.android.tv.BaseFragment.FocusBorderHelper
    /* JADX INFO: renamed from: getFocusBorder, reason: from getter */
    public FocusBorder getMFocusBorder() {
        return this.mFocusBorder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityMain3Binding getBinding() {
        return (ActivityMain3Binding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/MainActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "startNew", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, (Class<?>) MainActivity.class));
        }

        public final void startNew(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            intent.addFlags(C.BUFFER_FLAG_NOT_DEPENDED_ON);
            context.startActivity(intent);
        }
    }

    private final boolean haveMoreOneMainActivity() {
        Iterator<Activity> it = Utils.getActivityList().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        int i = 0;
        while (it.hasNext()) {
            if (Intrinsics.areEqual(MainActivity.class, it.next().getClass())) {
                i++;
            }
        }
        return i >= 2;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (haveMoreOneMainActivity()) {
            finish();
            return;
        }
        if (savedInstanceState != null) {
            this.allowLegacyFragmentRestore = false;
            purgeRestoredFragments();
            clearFragmentReferences();
        }
        initView();
        initData();
        initFragment(savedInstanceState);
        initListener();
        initUsb();
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_DOWNLOAD)) {
            FrameLayout flDownload = getBinding().flDownload;
            Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
            CommonExtKt.gone(flDownload);
        }
        maybeShowAccountManagerAfterSessionInvalid();
    }

    private final void maybeShowAccountManagerAfterSessionInvalid() {
        if (getIntent().getBooleanExtra(SessionInvalidRouter.EXTRA_SHOW_ACCOUNT_MANAGER, false)) {
            getIntent().removeExtra(SessionInvalidRouter.EXTRA_SHOW_ACCOUNT_MANAGER);
            AccountManagerFragment.INSTANCE.newInstance(true).show(getSupportFragmentManager(), "AccountManagerFragment");
        }
    }

    private final void initUsb() {
        UsbConnectReceiver.INSTANCE.init();
    }

    private final void startMemoryService() {
        if (Utils.isAppForeground()) {
            startService(new Intent(this, (Class<?>) MemoryMonitorService.class));
        }
    }

    private final void initFragment(Bundle savedInstanceState) {
        if (this.allowLegacyFragmentRestore && savedInstanceState != null) {
            this.lastSaveSelectPos = savedInstanceState.getInt("selectTabPosition");
            Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag("HomeFragment");
            this.featuredFragment = fragmentFindFragmentByTag instanceof HomeFragment ? (HomeFragment) fragmentFindFragmentByTag : null;
            Fragment fragmentFindFragmentByTag2 = getSupportFragmentManager().findFragmentByTag("MoviesFragment");
            this.movieMoreFragment = fragmentFindFragmentByTag2 instanceof MoviesFragment ? (MoviesFragment) fragmentFindFragmentByTag2 : null;
            Fragment fragmentFindFragmentByTag3 = getSupportFragmentManager().findFragmentByTag("TvShowsFragment");
            this.tvMoreFragment = fragmentFindFragmentByTag3 instanceof TvShowsFragment ? (TvShowsFragment) fragmentFindFragmentByTag3 : null;
            Fragment fragmentFindFragmentByTag4 = getSupportFragmentManager().findFragmentByTag("TvGuideFragment");
            this.tvGuideFragment = fragmentFindFragmentByTag4 instanceof TvGuideFragment ? (TvGuideFragment) fragmentFindFragmentByTag4 : null;
            Fragment fragmentFindFragmentByTag5 = getSupportFragmentManager().findFragmentByTag("PlayListHomeFragment");
            this.playListFragment = fragmentFindFragmentByTag5 instanceof PlayListHomeFragment ? (PlayListHomeFragment) fragmentFindFragmentByTag5 : null;
            Fragment fragmentFindFragmentByTag6 = getSupportFragmentManager().findFragmentByTag("WatchPlanFragment");
            this.watchPlanFragment = fragmentFindFragmentByTag6 instanceof WatchPlanFragment ? (WatchPlanFragment) fragmentFindFragmentByTag6 : null;
            Fragment fragmentFindFragmentByTag7 = getSupportFragmentManager().findFragmentByTag("AccountFragment");
            this.accountFragment = fragmentFindFragmentByTag7 instanceof AccountFragment ? (AccountFragment) fragmentFindFragmentByTag7 : null;
            Fragment fragmentFindFragmentByTag8 = getSupportFragmentManager().findFragmentByTag("DownloadFragment");
            this.downloadFragment = fragmentFindFragmentByTag8 instanceof DownloadFragment ? (DownloadFragment) fragmentFindFragmentByTag8 : null;
        }
    }

    private final void clearFragmentReferences() {
        this.featuredFragment = null;
        this.movieMoreFragment = null;
        this.tvMoreFragment = null;
        this.tvGuideFragment = null;
        this.playListFragment = null;
        this.watchPlanFragment = null;
        this.accountFragment = null;
        this.searchFragment = null;
        this.downloadFragment = null;
    }

    private final void purgeRestoredFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
        if (fragments.isEmpty()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        Iterator<T> it = fragments.iterator();
        while (it.hasNext()) {
            fragmentTransactionBeginTransaction.remove((Fragment) it.next());
        }
        fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
    }

    private final void initListener() {
        getBinding().ivFavorite.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MainActivity.initListener$lambda$2(this.f$0, view, z);
            }
        });
        getBinding().ivFavorite.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MainActivity.initListener$lambda$3(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().ivTvGuide.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MainActivity.initListener$lambda$4(this.f$0, view, z);
            }
        });
        getBinding().ivTvGuide.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MainActivity.initListener$lambda$5(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().ivSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MainActivity.initListener$lambda$6(this.f$0, view, z);
            }
        });
        getBinding().ivSearch.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MainActivity.initListener$lambda$7(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().flDownload.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MainActivity.initListener$lambda$8(this.f$0, view, z);
            }
        });
        getBinding().flDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.initListener$lambda$9(this.f$0, view);
            }
        });
        getBinding().flDownload.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MainActivity.initListener$lambda$10(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().tabLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MainActivity.initListener$lambda$12(this.f$0, view, z);
            }
        });
        getBinding().tabLayout.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MainActivity.initListener$lambda$13(this.f$0, view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(MainActivity mainActivity, View view, boolean z) {
        if (mainActivity.isFinishing()) {
            return;
        }
        mainActivity.isFavoriteFocus = z;
        if (z) {
            mainActivity.hideAllFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
            if (mainActivity.watchPlanFragment == null) {
                mainActivity.watchPlanFragment = new WatchPlanFragment();
                int i = R.id.content;
                WatchPlanFragment watchPlanFragment = mainActivity.watchPlanFragment;
                Intrinsics.checkNotNull(watchPlanFragment);
                fragmentTransactionBeginTransaction.add(i, watchPlanFragment, "WatchPlanFragment");
            }
            WatchPlanFragment watchPlanFragment2 = mainActivity.watchPlanFragment;
            Intrinsics.checkNotNull(watchPlanFragment2);
            fragmentTransactionBeginTransaction.show(watchPlanFragment2);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            mainActivity.getBinding().ivFavorite.setImageResource(R.mipmap.ic_watch_plan_white);
            return;
        }
        mainActivity.getBinding().ivFavorite.setImageResource(R.mipmap.ic_watch_plan);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$3(MainActivity mainActivity, View view, int i, KeyEvent keyEvent) {
        if (i != 20) {
            if (i != 22 || keyEvent.getAction() != 0) {
                return false;
            }
            mainActivity.hideAllFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
            if (mainActivity.featuredFragment == null) {
                mainActivity.featuredFragment = new HomeFragment();
                int i2 = R.id.content;
                HomeFragment homeFragment = mainActivity.featuredFragment;
                Intrinsics.checkNotNull(homeFragment);
                fragmentTransactionBeginTransaction.add(i2, homeFragment, "HomeFragment");
            }
            HomeFragment homeFragment2 = mainActivity.featuredFragment;
            Intrinsics.checkNotNull(homeFragment2);
            fragmentTransactionBeginTransaction.show(homeFragment2);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            return false;
        }
        mainActivity.hideTabLayout();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(MainActivity mainActivity, View view, boolean z) {
        if (mainActivity.isFinishing()) {
            return;
        }
        mainActivity.isTvGuideFocus = z;
        if (z) {
            mainActivity.showTvGuideFragment();
            mainActivity.getBinding().ivTvGuide.setImageResource(R.mipmap.ic_tv_guide_white);
        } else {
            mainActivity.getBinding().ivTvGuide.setImageResource(R.mipmap.ic_tv_guide);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$5(MainActivity mainActivity, View view, int i, KeyEvent keyEvent) {
        if (i != 20 || keyEvent.getAction() != 0) {
            return false;
        }
        mainActivity.hideTabLayout();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(MainActivity mainActivity, View view, boolean z) {
        if (mainActivity.isFinishing()) {
            return;
        }
        mainActivity.isSearchFocus = z;
        if (z) {
            mainActivity.hideAllFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
            if (mainActivity.searchFragment == null) {
                mainActivity.searchFragment = new SearchFragment();
                int i = R.id.content;
                SearchFragment searchFragment = mainActivity.searchFragment;
                Intrinsics.checkNotNull(searchFragment);
                fragmentTransactionBeginTransaction.add(i, searchFragment, "FavoriteFragment");
            }
            SearchFragment searchFragment2 = mainActivity.searchFragment;
            Intrinsics.checkNotNull(searchFragment2);
            fragmentTransactionBeginTransaction.show(searchFragment2);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            mainActivity.getBinding().ivSearch.setImageResource(R.mipmap.ic_white_search);
            return;
        }
        mainActivity.getBinding().ivSearch.setImageResource(R.mipmap.ic_gray_search);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$7(MainActivity mainActivity, View view, int i, KeyEvent keyEvent) {
        if (i != 20 || keyEvent.getAction() != 0) {
            return false;
        }
        mainActivity.hideTabLayout();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(MainActivity mainActivity, View view, boolean z) {
        if (mainActivity.isFinishing()) {
            return;
        }
        mainActivity.isDownloadFocus = z;
        if (z) {
            DownloadFragment downloadFragment = mainActivity.downloadFragment;
            if (downloadFragment != null) {
                Intrinsics.checkNotNull(downloadFragment);
                if (!downloadFragment.isVisible()) {
                    mainActivity.hideAllFragment();
                    FragmentTransaction fragmentTransactionBeginTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                    DownloadFragment downloadFragment2 = mainActivity.downloadFragment;
                    Intrinsics.checkNotNull(downloadFragment2);
                    fragmentTransactionBeginTransaction.show(downloadFragment2);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                }
            } else {
                mainActivity.hideAllFragment();
                FragmentTransaction fragmentTransactionBeginTransaction2 = mainActivity.getSupportFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "beginTransaction(...)");
                if (mainActivity.downloadFragment == null) {
                    mainActivity.downloadFragment = new DownloadFragment();
                    int i = R.id.content;
                    DownloadFragment downloadFragment3 = mainActivity.downloadFragment;
                    Intrinsics.checkNotNull(downloadFragment3);
                    fragmentTransactionBeginTransaction2.add(i, downloadFragment3, "DownloadFragment");
                }
                DownloadFragment downloadFragment4 = mainActivity.downloadFragment;
                Intrinsics.checkNotNull(downloadFragment4);
                fragmentTransactionBeginTransaction2.show(downloadFragment4);
                fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
            }
            mainActivity.getBinding().ivDownload.setImageResource(R.mipmap.ic_tab_white_download);
            return;
        }
        mainActivity.getBinding().ivDownload.setImageResource(R.mipmap.ic_tab_gray_download);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(MainActivity mainActivity, View view) {
        DownloadFragment downloadFragment = mainActivity.downloadFragment;
        if (downloadFragment != null) {
            Intrinsics.checkNotNull(downloadFragment);
            if (!downloadFragment.isVisible()) {
                mainActivity.hideAllFragment();
                FragmentTransaction fragmentTransactionBeginTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                DownloadFragment downloadFragment2 = mainActivity.downloadFragment;
                Intrinsics.checkNotNull(downloadFragment2);
                fragmentTransactionBeginTransaction.show(downloadFragment2);
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        } else {
            mainActivity.hideAllFragment();
            FragmentTransaction fragmentTransactionBeginTransaction2 = mainActivity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "beginTransaction(...)");
            if (mainActivity.downloadFragment == null) {
                mainActivity.downloadFragment = new DownloadFragment();
                int i = R.id.content;
                DownloadFragment downloadFragment3 = mainActivity.downloadFragment;
                Intrinsics.checkNotNull(downloadFragment3);
                fragmentTransactionBeginTransaction2.add(i, downloadFragment3, "DownloadFragment");
            }
            DownloadFragment downloadFragment4 = mainActivity.downloadFragment;
            Intrinsics.checkNotNull(downloadFragment4);
            fragmentTransactionBeginTransaction2.show(downloadFragment4);
            fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
        }
        mainActivity.getBinding().ivDownload.setImageResource(R.mipmap.ic_tab_white_download);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$10(MainActivity mainActivity, View view, int i, KeyEvent keyEvent) {
        if (i != 20 || keyEvent.getAction() != 0) {
            return false;
        }
        mainActivity.hideTabLayout();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(final MainActivity mainActivity, View view, boolean z) {
        if (mainActivity.isAnimation) {
            return;
        }
        if (z) {
            if (mainActivity.getBinding().llTop.getVisibility() != 0) {
                mainActivity.showTabLayout();
            }
        } else {
            Observable<Long> observableTimer = Observable.timer(100L, TimeUnit.MILLISECONDS);
            Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, mainActivity), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainActivity.initListener$lambda$12$lambda$11(this.f$0, (Long) obj);
                }
            }, 15, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$12$lambda$11(MainActivity mainActivity, Long l) {
        if (!mainActivity.isFinishing() && !mainActivity.isSearchFocus && !mainActivity.isFavoriteFocus && !mainActivity.isDownloadFocus && !mainActivity.isTvGuideFocus) {
            mainActivity.hideTabLayout();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$13(MainActivity mainActivity, View view, int i, KeyEvent keyEvent) {
        AccountFragment accountFragment;
        if ((i == 66 || i == 23) && keyEvent.getAction() == 0) {
            mainActivity.onTabItemClick();
            return true;
        }
        if (i != 20 || keyEvent.getAction() != 0 || (accountFragment = mainActivity.accountFragment) == null || !accountFragment.isVisible()) {
            return false;
        }
        mainActivity.hideTabLayout();
        FocusSettingLiveData.INSTANCE.get().setValue(true);
        return true;
    }

    private final void showTvGuideFragment() {
        hideAllFragment();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        if (this.tvGuideFragment == null) {
            this.tvGuideFragment = TvGuideFragment.INSTANCE.newInstance();
            int i = R.id.content;
            TvGuideFragment tvGuideFragment = this.tvGuideFragment;
            Intrinsics.checkNotNull(tvGuideFragment);
            fragmentTransactionBeginTransaction.add(i, tvGuideFragment, "TvGuideFragment");
        }
        TvGuideFragment tvGuideFragment2 = this.tvGuideFragment;
        Intrinsics.checkNotNull(tvGuideFragment2);
        fragmentTransactionBeginTransaction.show(tvGuideFragment2);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void onTabItemClick() {
        PlayListHomeFragment playListHomeFragment;
        HomeFragment homeFragment = this.featuredFragment;
        if (homeFragment != null && homeFragment.isVisible()) {
            HomeFragment homeFragment2 = this.featuredFragment;
            if (homeFragment2 != null) {
                homeFragment2.refreshData();
                return;
            }
            return;
        }
        MoviesFragment moviesFragment = this.movieMoreFragment;
        if (moviesFragment != null && moviesFragment.isVisible()) {
            MoviesFragment moviesFragment2 = this.movieMoreFragment;
            if (moviesFragment2 != null) {
                moviesFragment2.refreshData();
                return;
            }
            return;
        }
        TvShowsFragment tvShowsFragment = this.tvMoreFragment;
        if (tvShowsFragment != null && tvShowsFragment.isVisible()) {
            TvShowsFragment tvShowsFragment2 = this.tvMoreFragment;
            if (tvShowsFragment2 != null) {
                tvShowsFragment2.refreshData();
                return;
            }
            return;
        }
        PlayListHomeFragment playListHomeFragment2 = this.playListFragment;
        if (playListHomeFragment2 == null || !playListHomeFragment2.isVisible() || (playListHomeFragment = this.playListFragment) == null) {
            return;
        }
        playListHomeFragment.refreshData();
    }

    private final void showTabLayout() {
        if (this.isAnimation || getBinding().llTop.getVisibility() == 0) {
            return;
        }
        LinearLayout llTop = getBinding().llTop;
        Intrinsics.checkNotNullExpressionValue(llTop, "llTop");
        CommonExtKt.visible(llTop);
        getBinding().container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.view.activity.MainActivity.showTabLayout.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                MainActivity.this.isAnimation = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ViewGroup.LayoutParams layoutParams = MainActivity.this.getBinding().container.getLayoutParams();
                layoutParams.height = MainActivity.this.getBinding().container.getHeight() - MainActivity.this.getBinding().llTop.getHeight();
                MainActivity.this.getBinding().container.setLayoutParams(layoutParams);
                MainActivity.this.isAnimation = false;
            }
        }).translationYBy(getBinding().llTop.getHeight());
    }

    private final void hideTabLayout() {
        if (this.isAnimation || getBinding().llTop.getVisibility() == 4) {
            return;
        }
        getBinding().container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.view.activity.MainActivity.hideTabLayout.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                MainActivity.this.isAnimation = true;
                ViewGroup.LayoutParams layoutParams = MainActivity.this.getBinding().container.getLayoutParams();
                layoutParams.height = MainActivity.this.getBinding().container.getHeight() + MainActivity.this.getBinding().llTop.getHeight();
                MainActivity.this.getBinding().container.setLayoutParams(layoutParams);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                LinearLayout llTop = MainActivity.this.getBinding().llTop;
                Intrinsics.checkNotNullExpressionValue(llTop, "llTop");
                CommonExtKt.invisible(llTop);
                MainActivity.this.isAnimation = false;
            }
        }).translationYBy(-getBinding().llTop.getHeight());
    }

    private final void initData() {
        if (App.isLogin()) {
            getUserInfo(true);
            registerDevice();
        }
        checkTime();
        MainActivity mainActivity = this;
        SelectWatchPlanWatchedLiveData.INSTANCE.get().observeInActivity(mainActivity, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.initData$lambda$14(this.f$0, (Boolean) obj);
            }
        }));
        HideDownloadLiveData.INSTANCE.get().observeInActivity(mainActivity, new MainActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.initData$lambda$15(this.f$0, (Boolean) obj);
            }
        }));
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass3(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$14(MainActivity mainActivity, Boolean bool) {
        mainActivity.showTabLayout();
        mainActivity.getBinding().ivFavorite.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$15(MainActivity mainActivity, Boolean bool) {
        if (bool.booleanValue()) {
            if (mainActivity.getBinding().flDownload.isFocused()) {
                mainActivity.getBinding().ivTvGuide.requestFocus();
            }
            FrameLayout flDownload = mainActivity.getBinding().flDownload;
            Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
            CommonExtKt.gone(flDownload);
        } else {
            FrameLayout flDownload2 = mainActivity.getBinding().flDownload;
            Intrinsics.checkNotNullExpressionValue(flDownload2, "flDownload");
            CommonExtKt.visible(flDownload2);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.activity.MainActivity$initData$3, reason: invalid class name */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.activity.MainActivity$initData$3", f = "MainActivity.kt", i = {}, l = {545}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Long> downloadingNumFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadingNumFlow();
                final MainActivity mainActivity = MainActivity.this;
                this.label = 1;
                if (downloadingNumFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.activity.MainActivity.initData.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).longValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(long j, Continuation<? super Unit> continuation) {
                        if (j > 0) {
                            mainActivity.getWindow().addFlags(128);
                        } else {
                            mainActivity.getWindow().clearFlags(128);
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

    private final void registerDevice() {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post(API.USER.DEVICE).param("firebase_token", "").param("device_name", Build.BRAND).param("device_model", Build.MODEL).param("uid", App.getUserData().uid_v2).param("receive", (Object) 1).param("devicetoken", "").param("udid", "").asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.registerDevice$lambda$16((ApiException) obj);
            }
        }, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.registerDevice$lambda$17((String) obj);
            }
        }, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerDevice$lambda$17(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit registerDevice$lambda$16(ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ToastUtils.showShort(it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void checkTime() {
        Observable observableJust = Observable.just("http://time.movieboxpro.app/time.php");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.checkTime$lambda$18((String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda19
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MainActivity.checkTime$lambda$19(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.view.activity.MainActivity.checkTime.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(Boolean bool) {
                onSuccess(bool.booleanValue());
            }

            public void onSuccess(boolean model) {
                if (model) {
                    return;
                }
                TimeIncorrectHintDialog timeIncorrectHintDialog = new TimeIncorrectHintDialog();
                FragmentManager supportFragmentManager = MainActivity.this.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                timeIncorrectHintDialog.showAllowingStateLoss(supportFragmentManager, "TimeIncorrectHintDialog");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean checkTime$lambda$19(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Boolean) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean checkTime$lambda$18(String s) throws IOException {
        Intrinsics.checkNotNullParameter(s, "s");
        Response responseExecute = HttpHelperKt.getOkHttpClient().newCall(new Request.Builder().url(s).build()).execute();
        if (responseExecute.getIsSuccessful()) {
            ResponseBody responseBodyBody = responseExecute.body();
            Intrinsics.checkNotNull(responseBodyBody);
            long j = Long.parseLong(responseBodyBody.string());
            long currentTime = TimeUtils.getCurrentTime() / ((long) 1000);
            long j2 = 600;
            return Boolean.valueOf(currentTime < j + j2 && currentTime > j - j2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getUserInfo(final boolean isCheckDate) {
        ((ObservableSubscribeProxy) Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.activity.MainActivity.getUserInfo.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData model) throws Throwable {
                App.updateUser(model);
                if (isCheckDate && model != null && model.isvip == 1 && model.isvip == 1 && (model.dead_time * ((long) 1000)) - System.currentTimeMillis() <= 604800000) {
                    this.showHintVipDate();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                if ((e != null ? e.getCause() : null) instanceof ServerException) {
                    boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false);
                    App.logout();
                    PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, z);
                    new LoginExpireDialog().show(this.getSupportFragmentManager(), "LoginExpireDialog");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHintVipDate() {
        VipExpireDialog.Builder title = new VipExpireDialog.Builder(this).setCancelable(false).setTitle("Your VIP will soon expire.");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime4(App.getUserData().dead_time * ((long) 1000))}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        title.setContent(str).setNegativeText("Next Time").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda23
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MainActivity.showHintVipDate$lambda$20(this.f$0);
            }
        }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda24
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                this.f$0.getUserInfo(false);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showHintVipDate$lambda$20(MainActivity mainActivity) {
        new VipChargeGuideDialog().show(mainActivity.getSupportFragmentManager(), "VipChargeGuideDialog");
    }

    private final void checkVipDate() {
        VipExpireDialog.Builder title = new VipExpireDialog.Builder(this).setCancelable(false).setTitle("Your VIP has expired.");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime4(App.getUserData().dead_time * ((long) 1000))}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        title.setContent(str).setNegativeText("Close").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda20
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MainActivity.checkVipDate$lambda$22(this.f$0);
            }
        }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda21
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                this.f$0.getUserInfo(false);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkVipDate$lambda$22(MainActivity mainActivity) {
        new VipChargeGuideDialog().show(mainActivity.getSupportFragmentManager(), "VipChargeGuideDialog");
    }

    private final void initView() {
        this.mFocusBorder = new FocusBorder.Builder().asColor().borderColorRes(R.color.color_main_yellow).borderWidth(1, 3.2f).shadowColorRes(R.color.color_main_back).shadowWidth(1, 22.0f).build(this);
        getBinding().tabLayout.addOnTabSelectedListener(new TabSelectedListener());
        String[] strArr = this.tabTitles;
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = strArr[i];
            int i3 = i2 + 1;
            if (getBinding().tabLayout != null) {
                TvTabLayout.Tab text = getBinding().tabLayout.newTab().setText(str);
                Intrinsics.checkNotNullExpressionValue(text, "setText(...)");
                if (this.lastSaveSelectPos == i2) {
                    getBinding().tabLayout.addTab(text, true);
                } else {
                    getBinding().tabLayout.addTab(text);
                }
            }
            i++;
            i2 = i3;
        }
        getBinding().tabLayout.requestFocus();
        boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE);
        if (z || PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
            getBinding().tabLayout.removeTabAt(3);
            if (z) {
                ImageView ivChildMode = getBinding().ivChildMode;
                Intrinsics.checkNotNullExpressionValue(ivChildMode, "ivChildMode");
                CommonExtKt.visible(ivChildMode);
                return;
            }
            return;
        }
        ImageView ivChildMode2 = getBinding().ivChildMode;
        Intrinsics.checkNotNullExpressionValue(ivChildMode2, "ivChildMode");
        CommonExtKt.gone(ivChildMode2);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt("selectTabPosition", getBinding().tabLayout.getSelectedTabPosition());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onDownloadComplete(DownloadCompleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        getDownloadingNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onKeyUp(FeaturedKeyUpEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        HomeFragment homeFragment = this.featuredFragment;
        if (homeFragment != null && homeFragment.isVisible()) {
            HomeFragment homeFragment2 = this.featuredFragment;
            if (homeFragment2 != null && homeFragment2.isErrorShowing()) {
                HomeFragment homeFragment3 = this.featuredFragment;
                if (homeFragment3 != null) {
                    homeFragment3.loadingViewFocus();
                    return;
                }
                return;
            }
            getBinding().tabLayout.requestFocus();
            return;
        }
        PlayListHomeFragment playListHomeFragment = this.playListFragment;
        if (playListHomeFragment == null || !playListHomeFragment.isVisible()) {
            return;
        }
        PlayListHomeFragment playListHomeFragment2 = this.playListFragment;
        if (playListHomeFragment2 != null && playListHomeFragment2.isErrorShowing()) {
            PlayListHomeFragment playListHomeFragment3 = this.playListFragment;
            if (playListHomeFragment3 != null) {
                playListHomeFragment3.loadingViewFocus();
                return;
            }
            return;
        }
        getBinding().tabLayout.requestFocus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onListFocusUp(ListFocusUpEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        WatchPlanFragment watchPlanFragment = this.watchPlanFragment;
        if (watchPlanFragment != null && watchPlanFragment.isVisible()) {
            getBinding().ivFavorite.requestFocus();
            showTabLayout();
            return;
        }
        SearchFragment searchFragment = this.searchFragment;
        if (searchFragment != null && searchFragment.isVisible()) {
            getBinding().ivSearch.requestFocus();
            showTabLayout();
            return;
        }
        DownloadFragment downloadFragment = this.downloadFragment;
        if (downloadFragment != null && downloadFragment.isVisible()) {
            getBinding().flDownload.requestFocus();
            showTabLayout();
            return;
        }
        TvGuideFragment tvGuideFragment = this.tvGuideFragment;
        if (tvGuideFragment != null && tvGuideFragment.isVisible()) {
            getBinding().ivTvGuide.requestFocus();
            showTabLayout();
        } else {
            getBinding().tabLayout.requestFocus();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLoginIn(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) && !PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
            getBinding().tabLayout.addTab(getBinding().tabLayout.newTab().setText(this.tabTitles[3]), 3);
        }
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, false);
        ImageView ivChildMode = getBinding().ivChildMode;
        Intrinsics.checkNotNullExpressionValue(ivChildMode, "ivChildMode");
        CommonExtKt.gone(ivChildMode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void openChildMode(OpenChildModeEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ImageView ivChildMode = getBinding().ivChildMode;
        Intrinsics.checkNotNullExpressionValue(ivChildMode, "ivChildMode");
        CommonExtKt.visible(ivChildMode);
        if (getBinding().tabLayout.getTabCount() == this.tabTitles.length) {
            getBinding().tabLayout.removeTabAt(3);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void openHideMovieList(OpenHideMovieListEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.isOpen()) {
            if (getBinding().tabLayout.getTabCount() == this.tabTitles.length) {
                getBinding().tabLayout.removeTabAt(3);
            }
        } else {
            if (getBinding().tabLayout.getTabCount() >= this.tabTitles.length || PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false)) {
                return;
            }
            getBinding().tabLayout.addTab(getBinding().tabLayout.newTab().setText(this.tabTitles[3]), 3);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLowMemory(LowMemoryEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        getBinding().tabLayout.getSelectedTabPosition();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (!this.isHomePageCreated) {
            App.tempCL = false;
            this.isHomePageCreated = true;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return super.dispatchKeyEvent(event);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i = 4;
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (System.currentTimeMillis() - this.mExitTime > ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS) {
            if (getBinding().llTop.getVisibility() == 0) {
                TvTabLayout.Tab selectedTab = getBinding().tabLayout.getSelectedTab();
                if (selectedTab != null && selectedTab.getPosition() == 0) {
                    if (getBinding().tabLayout.isFocused()) {
                        ToastUtils.showShort("Press again to quit", new Object[0]);
                        this.mExitTime = System.currentTimeMillis();
                    } else {
                        getBinding().tabLayout.requestFocus();
                        getBinding().tabLayout.selectTab(0);
                        hideAllFragment();
                        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
                        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                        if (this.featuredFragment == null) {
                            this.featuredFragment = new HomeFragment();
                            int i2 = R.id.content;
                            HomeFragment homeFragment = this.featuredFragment;
                            Intrinsics.checkNotNull(homeFragment);
                            fragmentTransactionBeginTransaction.add(i2, homeFragment, "HomeFragment");
                        }
                        HomeFragment homeFragment2 = this.featuredFragment;
                        Intrinsics.checkNotNull(homeFragment2);
                        fragmentTransactionBeginTransaction.show(homeFragment2);
                        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                    }
                } else {
                    getBinding().tabLayout.selectTab(0);
                }
                return true;
            }
            WatchPlanFragment watchPlanFragment = this.watchPlanFragment;
            if (watchPlanFragment != null && watchPlanFragment.isVisible()) {
                getBinding().ivFavorite.requestFocus();
                showTabLayout();
                return true;
            }
            DownloadFragment downloadFragment = this.downloadFragment;
            if (downloadFragment != null && downloadFragment.isVisible()) {
                getBinding().flDownload.requestFocus();
                showTabLayout();
                return true;
            }
            TvGuideFragment tvGuideFragment = this.tvGuideFragment;
            if (tvGuideFragment != null && tvGuideFragment.isVisible()) {
                TvGuideFragment tvGuideFragment2 = this.tvGuideFragment;
                if (tvGuideFragment2 != null && tvGuideFragment2.onBackPressed()) {
                    return true;
                }
                getBinding().ivTvGuide.requestFocus();
                showTabLayout();
                return true;
            }
            if (getBinding().ivSearch.isFocused()) {
                ToastUtils.showShort("Press again to quit", new Object[0]);
                this.mExitTime = System.currentTimeMillis();
                return true;
            }
            SearchFragment searchFragment = this.searchFragment;
            if (searchFragment != null && searchFragment.isVisible()) {
                SearchFragment searchFragment2 = this.searchFragment;
                if (searchFragment2 != null && !searchFragment2.needCheckBackPressed()) {
                    getBinding().ivSearch.requestFocus();
                    showTabLayout();
                }
                return true;
            }
            if (!getBinding().tabLayout.isFocused()) {
                HomeFragment homeFragment3 = this.featuredFragment;
                if (homeFragment3 == null || !homeFragment3.isVisible()) {
                    MoviesFragment moviesFragment = this.movieMoreFragment;
                    if (moviesFragment == null || !moviesFragment.isVisible()) {
                        TvShowsFragment tvShowsFragment = this.tvMoreFragment;
                        if (tvShowsFragment == null || !tvShowsFragment.isVisible()) {
                            PlayListHomeFragment playListHomeFragment = this.playListFragment;
                            if (playListHomeFragment == null || !playListHomeFragment.isVisible()) {
                                AccountFragment accountFragment = this.accountFragment;
                                i = (accountFragment == null || !accountFragment.isVisible()) ? -1 : 5;
                            }
                        } else {
                            i = 3;
                        }
                    } else {
                        i = 2;
                    }
                } else {
                    i = 1;
                }
                if (i != -1) {
                    EventBus.getDefault().post(new OnBackPressedEvent(i));
                }
                Observable<Long> observableTimer = Observable.timer(200L, TimeUnit.MILLISECONDS);
                Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
                RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MainActivity.onKeyDown$lambda$25(this.f$0, (Long) obj);
                    }
                }, 15, null);
                return true;
            }
            ToastUtils.showShort("Press again to quit", new Object[0]);
            this.mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onKeyDown$lambda$25(MainActivity mainActivity, Long l) {
        mainActivity.getBinding().tabLayout.requestFocus();
        return Unit.INSTANCE;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    private final void stopMemoryService() {
        stopService(new Intent(this, (Class<?>) MemoryMonitorService.class));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Http.refreshApiSessionIfExpiringAsync();
        if (!this.upgradeChecked) {
            if (ConfigUtils.readIntegerConfig(ConfigKey.APK_VERSION_CODE_MIN, 0) > 126) {
                showUpdateAlrt();
            } else {
                UpdateChecker.INSTANCE.checkUpdate(this);
                this.upgradeChecked = true;
                CommonUtils.onEvent("check_upgrade");
            }
        }
        if (Network.isConnected(this)) {
            App.getInstance().updateOnline();
        }
        getDownloadingNum();
    }

    private final void getDownloadingNum() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.getDownloadingNum$lambda$26((String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MainActivity.getDownloadingNum$lambda$27(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.getDownloadingNum$lambda$28(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingNum$lambda$27(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingNum$lambda$26(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return DownloadFileHelper.INSTANCE.getInstance().getDownloadingFiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadingNum$lambda$28(MainActivity mainActivity, List list) {
        if (list.size() > 0) {
            TextView tvDownloadNum = mainActivity.getBinding().tvDownloadNum;
            Intrinsics.checkNotNullExpressionValue(tvDownloadNum, "tvDownloadNum");
            CommonExtKt.visible(tvDownloadNum);
            if (list.size() < 99) {
                mainActivity.getBinding().tvDownloadNum.setText(String.valueOf(list.size()));
            } else {
                mainActivity.getBinding().tvDownloadNum.setText("99+");
            }
        } else {
            TextView tvDownloadNum2 = mainActivity.getBinding().tvDownloadNum;
            Intrinsics.checkNotNullExpressionValue(tvDownloadNum2, "tvDownloadNum");
            CommonExtKt.gone(tvDownloadNum2);
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Utils.isActivityInList(VideoPlayer.class)) {
            VideoPlayer.start(this);
        } else if (Utils.isActivityInList(VideoPlayerActivity.class)) {
            VideoPlayerActivity.start(this);
        }
    }

    private final void showUpdateAlrt() {
        String stringConfig = ConfigUtils.readStringConfig(ConfigKey.APK_TITLE);
        new AlertDialog(this).builder().setTitle(stringConfig).setMsg(ConfigUtils.readStringConfig(ConfigKey.APK_INFO2)).setNegativeButton("DownLoad Now", new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.MainActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showUpdateAlrt$lambda$29(view);
            }
        }).setCancelable(true).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideAllFragment() {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        HomeFragment homeFragment = this.featuredFragment;
        if (homeFragment != null) {
            fragmentTransactionBeginTransaction.hide(homeFragment);
        }
        MoviesFragment moviesFragment = this.movieMoreFragment;
        if (moviesFragment != null) {
            fragmentTransactionBeginTransaction.hide(moviesFragment);
        }
        TvShowsFragment tvShowsFragment = this.tvMoreFragment;
        if (tvShowsFragment != null) {
            fragmentTransactionBeginTransaction.hide(tvShowsFragment);
        }
        TvGuideFragment tvGuideFragment = this.tvGuideFragment;
        if (tvGuideFragment != null) {
            fragmentTransactionBeginTransaction.hide(tvGuideFragment);
        }
        WatchPlanFragment watchPlanFragment = this.watchPlanFragment;
        if (watchPlanFragment != null) {
            fragmentTransactionBeginTransaction.hide(watchPlanFragment);
        }
        AccountFragment accountFragment = this.accountFragment;
        if (accountFragment != null) {
            fragmentTransactionBeginTransaction.hide(accountFragment);
        }
        PlayListHomeFragment playListHomeFragment = this.playListFragment;
        if (playListHomeFragment != null) {
            fragmentTransactionBeginTransaction.hide(playListHomeFragment);
        }
        SearchFragment searchFragment = this.searchFragment;
        if (searchFragment != null) {
            fragmentTransactionBeginTransaction.hide(searchFragment);
        }
        DownloadFragment downloadFragment = this.downloadFragment;
        if (downloadFragment != null) {
            fragmentTransactionBeginTransaction.hide(downloadFragment);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        if (getBinding().ivTvGuide.isFocused()) {
            return;
        }
        getBinding().ivTvGuide.setImageResource(R.mipmap.ic_tv_guide);
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/MainActivity$TabSelectedListener;", "Lcom/owen/tab/TvTabLayout$OnTabSelectedListener;", "<init>", "(Lcom/movieboxpro/android/view/activity/MainActivity;)V", "onTabSelected", "", "tab", "Lcom/owen/tab/TvTabLayout$Tab;", "onTabUnselected", "onTabReselected", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class TabSelectedListener implements TvTabLayout.OnTabSelectedListener {
        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabReselected(TvTabLayout.Tab tab) {
            Intrinsics.checkNotNullParameter(tab, "tab");
        }

        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabUnselected(TvTabLayout.Tab tab) {
            Intrinsics.checkNotNullParameter(tab, "tab");
        }

        public TabSelectedListener() {
        }

        @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
        public void onTabSelected(TvTabLayout.Tab tab) {
            Intrinsics.checkNotNullParameter(tab, "tab");
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false) || PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
                FragmentTransaction fragmentTransactionBeginTransaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                MainActivity.this.hideAllFragment();
                int position = tab.getPosition();
                if (position == 0) {
                    if (MainActivity.this.featuredFragment == null) {
                        MainActivity.this.featuredFragment = new HomeFragment();
                        int i = R.id.content;
                        HomeFragment homeFragment = MainActivity.this.featuredFragment;
                        Intrinsics.checkNotNull(homeFragment);
                        fragmentTransactionBeginTransaction.add(i, homeFragment, "HomeFragment");
                    }
                    HomeFragment homeFragment2 = MainActivity.this.featuredFragment;
                    Intrinsics.checkNotNull(homeFragment2);
                    fragmentTransactionBeginTransaction.show(homeFragment2);
                } else if (position == 1) {
                    if (MainActivity.this.movieMoreFragment == null) {
                        MainActivity.this.movieMoreFragment = new MoviesFragment();
                        int i2 = R.id.content;
                        MoviesFragment moviesFragment = MainActivity.this.movieMoreFragment;
                        Intrinsics.checkNotNull(moviesFragment);
                        fragmentTransactionBeginTransaction.add(i2, moviesFragment, "MoviesFragment");
                    }
                    MoviesFragment moviesFragment2 = MainActivity.this.movieMoreFragment;
                    Intrinsics.checkNotNull(moviesFragment2);
                    fragmentTransactionBeginTransaction.show(moviesFragment2);
                } else if (position == 2) {
                    if (MainActivity.this.tvMoreFragment == null) {
                        MainActivity.this.tvMoreFragment = new TvShowsFragment();
                        int i3 = R.id.content;
                        TvShowsFragment tvShowsFragment = MainActivity.this.tvMoreFragment;
                        Intrinsics.checkNotNull(tvShowsFragment);
                        fragmentTransactionBeginTransaction.add(i3, tvShowsFragment, "TvShowsFragment");
                    }
                    TvShowsFragment tvShowsFragment2 = MainActivity.this.tvMoreFragment;
                    Intrinsics.checkNotNull(tvShowsFragment2);
                    fragmentTransactionBeginTransaction.show(tvShowsFragment2);
                } else if (position == 3) {
                    if (MainActivity.this.accountFragment == null) {
                        MainActivity.this.accountFragment = new AccountFragment();
                        int i4 = R.id.content;
                        AccountFragment accountFragment = MainActivity.this.accountFragment;
                        Intrinsics.checkNotNull(accountFragment);
                        fragmentTransactionBeginTransaction.add(i4, accountFragment, "AccountFragment");
                    }
                    AccountFragment accountFragment2 = MainActivity.this.accountFragment;
                    Intrinsics.checkNotNull(accountFragment2);
                    fragmentTransactionBeginTransaction.show(accountFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                return;
            }
            FragmentTransaction fragmentTransactionBeginTransaction2 = MainActivity.this.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "beginTransaction(...)");
            MainActivity.this.hideAllFragment();
            int position2 = tab.getPosition();
            if (position2 == 0) {
                if (MainActivity.this.featuredFragment == null) {
                    MainActivity.this.featuredFragment = new HomeFragment();
                    int i5 = R.id.content;
                    HomeFragment homeFragment3 = MainActivity.this.featuredFragment;
                    Intrinsics.checkNotNull(homeFragment3);
                    fragmentTransactionBeginTransaction2.add(i5, homeFragment3, "HomeFragment");
                }
                HomeFragment homeFragment4 = MainActivity.this.featuredFragment;
                Intrinsics.checkNotNull(homeFragment4);
                fragmentTransactionBeginTransaction2.show(homeFragment4);
            } else if (position2 == 1) {
                if (MainActivity.this.movieMoreFragment == null) {
                    MainActivity.this.movieMoreFragment = new MoviesFragment();
                    int i6 = R.id.content;
                    MoviesFragment moviesFragment3 = MainActivity.this.movieMoreFragment;
                    Intrinsics.checkNotNull(moviesFragment3);
                    fragmentTransactionBeginTransaction2.add(i6, moviesFragment3, "MoviesFragment");
                }
                MoviesFragment moviesFragment4 = MainActivity.this.movieMoreFragment;
                Intrinsics.checkNotNull(moviesFragment4);
                fragmentTransactionBeginTransaction2.show(moviesFragment4);
            } else if (position2 == 2) {
                if (MainActivity.this.tvMoreFragment == null) {
                    MainActivity.this.tvMoreFragment = new TvShowsFragment();
                    int i7 = R.id.content;
                    TvShowsFragment tvShowsFragment3 = MainActivity.this.tvMoreFragment;
                    Intrinsics.checkNotNull(tvShowsFragment3);
                    fragmentTransactionBeginTransaction2.add(i7, tvShowsFragment3, "TvShowsFragment");
                }
                TvShowsFragment tvShowsFragment4 = MainActivity.this.tvMoreFragment;
                Intrinsics.checkNotNull(tvShowsFragment4);
                fragmentTransactionBeginTransaction2.show(tvShowsFragment4);
            } else if (position2 == 3) {
                if (MainActivity.this.playListFragment == null) {
                    MainActivity.this.playListFragment = new PlayListHomeFragment();
                    int i8 = R.id.content;
                    PlayListHomeFragment playListHomeFragment = MainActivity.this.playListFragment;
                    Intrinsics.checkNotNull(playListHomeFragment);
                    fragmentTransactionBeginTransaction2.add(i8, playListHomeFragment, "PlayListHomeFragment");
                }
                PlayListHomeFragment playListHomeFragment2 = MainActivity.this.playListFragment;
                Intrinsics.checkNotNull(playListHomeFragment2);
                fragmentTransactionBeginTransaction2.show(playListHomeFragment2);
            } else if (position2 == 4) {
                if (MainActivity.this.accountFragment == null) {
                    MainActivity.this.accountFragment = new AccountFragment();
                    int i9 = R.id.content;
                    AccountFragment accountFragment3 = MainActivity.this.accountFragment;
                    Intrinsics.checkNotNull(accountFragment3);
                    fragmentTransactionBeginTransaction2.add(i9, accountFragment3, "AccountFragment");
                }
                AccountFragment accountFragment4 = MainActivity.this.accountFragment;
                Intrinsics.checkNotNull(accountFragment4);
                fragmentTransactionBeginTransaction2.show(accountFragment4);
            }
            fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
        }
    }
}
