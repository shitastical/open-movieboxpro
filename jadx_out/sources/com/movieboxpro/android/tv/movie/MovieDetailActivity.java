package com.movieboxpro.android.tv.movie;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.storage.StorageVolume;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.MovieDetailKeyDownloadEvent;
import com.movieboxpro.android.event.OnPlayerFinishEvent;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ApiService3;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.FFmpegDownloadService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DetailMetaInfoBinder;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.DownloadActivity;
import com.movieboxpro.android.view.dialog.AddToDialog;
import com.movieboxpro.android.view.dialog.AddToFavoriteDialogFragment;
import com.movieboxpro.android.view.dialog.AddWatchPlanDialog;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment;
import com.movieboxpro.android.view.dialog.ChooseLanguageDialogFragment;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.ChooseTranslateLanguageDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.ListDialog;
import com.movieboxpro.android.view.dialog.LowQualityHintDialog;
import com.movieboxpro.android.view.dialog.ReleasedHintDialog;
import com.movieboxpro.android.view.dialog.VideoInfoDialogFragment;
import com.movieboxpro.android.view.fragment.account.ChoosePlayFragment;
import com.movieboxpro.android.view.fragment.account.OpenChildModeDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityMovieDetail2Binding;
import com.movieboxpro.androidtv.databinding.LayoutDetailMetaRowBinding;
import com.owen.focus.FocusBorder;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: MovieDetailActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0002%(\u0018\u0000 z2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005:\u0001zB\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u0011H\u0002J\u0010\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020+2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00102\u001a\u00020+2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00103\u001a\u00020+2\u0006\u00104\u001a\u000205H\u0007J\u0010\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\rH\u0002J\u001a\u00108\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0012\u0010;\u001a\u00020+2\b\u0010<\u001a\u0004\u0018\u00010\rH\u0002J\u001a\u0010=\u001a\u00020+2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010-\u001a\u00020\u0011H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u00104\u001a\u00020?H\u0007J\u0010\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020BH\u0016J\u0016\u0010C\u001a\u00020+2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0EH\u0016J\b\u0010F\u001a\u00020+H\u0002J\b\u0010G\u001a\u00020+H\u0002J\b\u0010H\u001a\u00020+H\u0002J\u0010\u0010I\u001a\u00020+2\u0006\u0010J\u001a\u00020\u0011H\u0016J\u0018\u0010K\u001a\u00020+2\u0006\u0010A\u001a\u00020B2\u0006\u0010L\u001a\u00020\rH\u0016J\u0010\u0010M\u001a\u00020+2\u0006\u0010N\u001a\u00020OH\u0002J\u0010\u0010P\u001a\u00020+2\u0006\u00104\u001a\u00020QH\u0007J\b\u0010R\u001a\u00020+H\u0014J!\u0010S\u001a\u00020+2\u0012\u0010T\u001a\n\u0012\u0006\b\u0001\u0012\u00020O0U\"\u00020OH\u0002¢\u0006\u0002\u0010VJ\b\u0010W\u001a\u00020+H\u0002J\b\u0010X\u001a\u00020+H\u0014J\b\u0010Y\u001a\u00020+H\u0002J\u0010\u0010Z\u001a\u00020+2\u0006\u0010[\u001a\u00020BH\u0002J\u0012\u0010\\\u001a\u00020+2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J)\u0010]\u001a\u00020+2\u0006\u0010^\u001a\u00020\u00112\u0012\u0010_\u001a\n\u0012\u0006\b\u0001\u0012\u00020O0U\"\u00020OH\u0002¢\u0006\u0002\u0010`J\b\u0010a\u001a\u00020+H\u0002J\b\u0010b\u001a\u00020+H\u0002J\b\u0010c\u001a\u00020+H\u0016J\u0016\u0010d\u001a\u00020+2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020e0EH\u0016J\b\u0010f\u001a\u00020+H\u0016J\b\u0010g\u001a\u00020+H\u0016J4\u0010h\u001a\u00020+2\u0016\u0010D\u001a\u0012\u0012\u0004\u0012\u00020j0ij\b\u0012\u0004\u0012\u00020j`k2\b\u0010l\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010m\u001a\u00020+H\u0016J\b\u0010n\u001a\u00020+H\u0002J\b\u0010o\u001a\u00020+H\u0014J\b\u0010p\u001a\u00020\u0011H\u0014J\b\u0010q\u001a\u00020\u0011H\u0014J\"\u0010r\u001a\u00020+2\u0006\u0010s\u001a\u00020B2\u0006\u0010t\u001a\u00020B2\b\u0010u\u001a\u0004\u0018\u00010vH\u0014J\b\u0010w\u001a\u00020+H\u0014J\b\u0010x\u001a\u00020\u0002H\u0014J\b\u0010y\u001a\u00020BH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0018\u00010\u001fR\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)¨\u0006{"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/tv/movie/MovieDetailPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityMovieDetail2Binding;", "Lcom/movieboxpro/android/tv/movie/MovieDetailContract$View;", "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "scale", "", "id", "", "lang", "poster", "isNeedDown", "", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "selectLanguage", "chooseLanguageDialog", "Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialogFragment;", "videoInfoDialog", "Lcom/movieboxpro/android/view/dialog/VideoInfoDialogFragment;", "addToFavoriteDialog", "Lcom/movieboxpro/android/view/dialog/AddToFavoriteDialogFragment;", "playList", "", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "downloadBinder", "Lcom/movieboxpro/android/service/DownloadService$DownloadBinder;", "Lcom/movieboxpro/android/service/DownloadService;", "isBindService", "downloadService", "currDownloadId", "downloadListener", "com/movieboxpro/android/tv/movie/MovieDetailActivity$downloadListener$1", "Lcom/movieboxpro/android/tv/movie/MovieDetailActivity$downloadListener$1;", "serviceConnection", "com/movieboxpro/android/tv/movie/MovieDetailActivity$serviceConnection$1", "Lcom/movieboxpro/android/tv/movie/MovieDetailActivity$serviceConnection$1;", "initListener", "", "dealPlayClick", "playImmediately", "checkOnlineNum", "item", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "goMoviePlayer", "removeDownload", "onKeyUp", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/MovieDetailKeyDownloadEvent;", "checkChildMode", "contentRating", "goVideoPlayer", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "playerWithOtherPlayer", "url", "startPlay", "onPlayerFinish", "Lcom/movieboxpro/android/event/OnPlayerFinishEvent;", "addToPlayListSuccess", "position", "", "showPlayList", "list", "", "showAddToFavorite", "showVideoInfoDialog", "showChooseLanguage", "addFavoriteSuccess", "isFavorite", "onItemClicked", "type", "focusScale", "v", "Landroid/view/View;", "onLogin", "Lcom/movieboxpro/android/event/LoginEvent;", "initView", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "bindService", "initData", "initDownloadFileFlow", "initWatchedStatus", "watched", "showMovieInfo", "setFocusable", "focus", "view", "(Z[Landroid/view/View;)V", "scrollUp", "scrollDown", "loadInfoError", "showDownloadDialog", "Lcom/movieboxpro/android/model/DownloadUrl;", "showPlayButtonLoading", "hidePlayButtonLoading", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "hideLoadView", "setBlurBackground", "requestData", "isNeedLoadData", "enableEventBus", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "bindPresenter", "getLayoutResId", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieDetailActivity extends BaseMvpActivity<MovieDetailPresenter, ActivityMovieDetail2Binding> implements MovieDetailContract.View, CommonItemClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AddToFavoriteDialogFragment addToFavoriteDialog;
    private ChooseLanguageDialogFragment chooseLanguageDialog;
    private DownloadService.DownloadBinder downloadBinder;
    private DownloadService downloadService;
    private boolean isBindService;
    private FocusBorder mFocusBorder;
    private MovieDetail movieDetail;
    private List<MovieListModel.MovieListItem> playList;
    private VideoInfoDialogFragment videoInfoDialog;
    private final float scale = 1.05f;
    private String id = "";
    private String lang = "";
    private String poster = "";
    private boolean isNeedDown = true;
    private String selectLanguage = "";
    private String currDownloadId = "";
    private MovieDetailActivity$downloadListener$1 downloadListener = new DownloadService.DownloadListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$downloadListener$1
        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onStart(String tag) {
            DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(tag);
            if (downloadFileFindDownloadFileByDownloadId != null) {
                MovieDetailActivity movieDetailActivity = this.this$0;
                if (Intrinsics.areEqual(downloadFileFindDownloadFileByDownloadId.getId(), movieDetailActivity.id)) {
                    String downloadId = downloadFileFindDownloadFileByDownloadId.getDownloadId();
                    Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                    movieDetailActivity.currDownloadId = downloadId;
                    ((ActivityMovieDetail2Binding) movieDetailActivity.binding).downloadStatusView.setStartLoading();
                }
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onProgress(String tag, long downloadSize, long totalSize, String speed) {
            Intrinsics.checkNotNullParameter(speed, "speed");
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                long j = 1000;
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.setProgress((int) (totalSize / j), (int) (downloadSize / j));
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onPause(String tag) {
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.setPause();
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onFailed(String tag, String msg) {
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.setPause();
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onComplete(String tag) {
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.downloadFinish();
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onDelete(String tag) {
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.resumeStatus();
            }
        }

        @Override // com.movieboxpro.android.service.DownloadService.DownloadListener
        public void onWaiting(String tag) {
            if (Intrinsics.areEqual(tag, this.this$0.currDownloadId)) {
                ((ActivityMovieDetail2Binding) this.this$0.binding).downloadStatusView.setWaiting();
            }
        }
    };
    private final MovieDetailActivity$serviceConnection$1 serviceConnection = new ServiceConnection() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            this.this$0.isBindService = true;
            this.this$0.downloadBinder = service instanceof DownloadService.DownloadBinder ? (DownloadService.DownloadBinder) service : null;
            MovieDetailActivity movieDetailActivity = this.this$0;
            DownloadService.DownloadBinder downloadBinder = movieDetailActivity.downloadBinder;
            movieDetailActivity.downloadService = downloadBinder != null ? downloadBinder.getThis$0() : null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            this.this$0.downloadBinder = null;
        }
    };

    private final void setBlurBackground() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onKeyUp(MovieDetailKeyDownloadEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        DownloadLiveData.INSTANCE.getInstance().registerListener(this, new FFmpegDownloadService.DownloadListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.initListener.1
            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onPause(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onStartLoading(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onStart(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(fid);
                if (downloadFileFindDownloadFileByDownloadId != null) {
                    MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                    if (Intrinsics.areEqual(downloadFileFindDownloadFileByDownloadId.getId(), movieDetailActivity.id)) {
                        String downloadId = downloadFileFindDownloadFileByDownloadId.getDownloadId();
                        Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                        movieDetailActivity.currDownloadId = downloadId;
                        ((ActivityMovieDetail2Binding) movieDetailActivity.binding).downloadStatusView.setStartLoading();
                    }
                }
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onProgress(String fid, int progress) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                if (Intrinsics.areEqual(fid, MovieDetailActivity.this.currDownloadId)) {
                    ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).downloadStatusView.setProgress(100, progress);
                }
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onComplete(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                if (Intrinsics.areEqual(fid, MovieDetailActivity.this.currDownloadId)) {
                    ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).downloadStatusView.downloadFinish();
                }
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onFailed(String fid, String msg) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                if (Intrinsics.areEqual(fid, MovieDetailActivity.this.currDownloadId)) {
                    ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).downloadStatusView.setPause();
                    ToastUtils.showShort("Download failed:" + msg, new Object[0]);
                }
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onDelete(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                if (Intrinsics.areEqual(fid, MovieDetailActivity.this.currDownloadId)) {
                    ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).downloadStatusView.resumeStatus();
                }
            }

            @Override // com.movieboxpro.android.service.FFmpegDownloadService.DownloadListener
            public void onWaiting(String fid) {
                Intrinsics.checkNotNullParameter(fid, "fid");
                if (Intrinsics.areEqual(fid, MovieDetailActivity.this.currDownloadId)) {
                    ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).downloadStatusView.setWaiting();
                }
            }
        });
        LinearLayout llMovieInfo = ((ActivityMovieDetail2Binding) this.binding).llMovieInfo;
        Intrinsics.checkNotNullExpressionValue(llMovieInfo, "llMovieInfo");
        ConstraintLayout llMovieName = ((ActivityMovieDetail2Binding) this.binding).llMovieName;
        Intrinsics.checkNotNullExpressionValue(llMovieName, "llMovieName");
        ImageView ivAdd = ((ActivityMovieDetail2Binding) this.binding).ivAdd;
        Intrinsics.checkNotNullExpressionValue(ivAdd, "ivAdd");
        FrameLayout flDownload = ((ActivityMovieDetail2Binding) this.binding).flDownload;
        Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
        ImageView ivWatchPlan = ((ActivityMovieDetail2Binding) this.binding).ivWatchPlan;
        Intrinsics.checkNotNullExpressionValue(ivWatchPlan, "ivWatchPlan");
        setViewFocusListener(llMovieInfo, llMovieName, ivAdd, flDownload, ivWatchPlan);
        ((ActivityMovieDetail2Binding) this.binding).ivWatchPlan.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$0(this.f$0, view);
            }
        });
        ((ActivityMovieDetail2Binding) this.binding).rlPlay.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                MovieDetailActivity.initListener$lambda$1(this.f$0, view, z);
            }
        });
        ((ActivityMovieDetail2Binding) this.binding).llMovieName.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$2(this.f$0, view);
            }
        });
        ((ActivityMovieDetail2Binding) this.binding).llMovieInfo.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$3(this.f$0, view);
            }
        });
        ((ActivityMovieDetail2Binding) this.binding).ivAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$4(this.f$0, view);
            }
        });
        ((ActivityMovieDetail2Binding) this.binding).flDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$17(this.f$0, view);
            }
        });
        final Ref.LongRef longRef = new Ref.LongRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ((ActivityMovieDetail2Binding) this.binding).rlPlay.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieDetailActivity.initListener$lambda$20(objectRef, longRef, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(final MovieDetailActivity movieDetailActivity, View view) {
        String str;
        if (movieDetailActivity.movieDetail == null) {
            return;
        }
        AddWatchPlanDialog.Companion companion = AddWatchPlanDialog.INSTANCE;
        MovieDetail movieDetail = movieDetailActivity.movieDetail;
        int i = movieDetail != null ? movieDetail.plan_watched : -1;
        MovieDetail movieDetail2 = movieDetailActivity.movieDetail;
        if (movieDetail2 == null || (str = movieDetail2.id) == null) {
            str = "";
        }
        AddWatchPlanDialog addWatchPlanDialogNewInstance = companion.newInstance(1, i, str);
        addWatchPlanDialogNewInstance.setListener(new AddWatchPlanListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$initListener$2$1
            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onEpisodeOverChanged(int i2, Integer num, int i3) {
                AddWatchPlanListener.CC.$default$onEpisodeOverChanged(this, i2, num, i3);
            }

            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onWatchedChange(int watched) {
                MovieDetail movieDetail3 = this.this$0.movieDetail;
                if (movieDetail3 != null) {
                    movieDetail3.plan_watched = watched;
                }
                this.this$0.initWatchedStatus(watched);
            }
        });
        addWatchPlanDialogNewInstance.show(movieDetailActivity.getSupportFragmentManager(), "AddWatchPlanDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(MovieDetailActivity movieDetailActivity, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            movieDetailActivity.focusScale(view);
        }
        FocusBorder focusBorder = movieDetailActivity.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(MovieDetailActivity movieDetailActivity, View view) {
        if (movieDetailActivity.movieDetail == null) {
            return;
        }
        movieDetailActivity.showChooseLanguage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(MovieDetailActivity movieDetailActivity, View view) {
        if (movieDetailActivity.movieDetail == null) {
            return;
        }
        movieDetailActivity.showVideoInfoDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(MovieDetailActivity movieDetailActivity, View view) {
        if (movieDetailActivity.movieDetail != null && movieDetailActivity.isLogin()) {
            movieDetailActivity.showAddToFavorite();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17(final MovieDetailActivity movieDetailActivity, View view) {
        if (movieDetailActivity.movieDetail == null) {
            return;
        }
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.initListener$lambda$17$lambda$5(this.f$0, (String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda17
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailActivity.initListener$lambda$17$lambda$6(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(movieDetailActivity));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.initListener$lambda$17$lambda$7(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.initListener$lambda$17$lambda$8(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.initListener$lambda$17$lambda$16(this.f$0, (List) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List initListener$lambda$17$lambda$6(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List initListener$lambda$17$lambda$5(MovieDetailActivity movieDetailActivity, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return DownloadFileHelper.INSTANCE.getInstance().findMovieFile(movieDetailActivity.id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$17$lambda$8(MovieDetailActivity movieDetailActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        movieDetailActivity.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f4  */
    public static final Unit initListener$lambda$17$lambda$16(final MovieDetailActivity movieDetailActivity, List list) {
        String str;
        movieDetailActivity.hideLoadingView();
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            final DownloadFile downloadFile = (DownloadFile) list.get(0);
            String path = downloadFile.getPath();
            if (path == null) {
                path = "";
            }
            if (CommonUtils.isFFmpegDownload(path)) {
                if (((ActivityMovieDetail2Binding) movieDetailActivity.binding).downloadStatusView.getStatus() == 1) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Remove downloaded file", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda9
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$9(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda10
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$10(downloadFile, movieDetailActivity, view, i);
                        }
                    }).create().show();
                }
            } else {
                int status = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).downloadStatusView.getStatus();
                if (status == 1) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Play", "Remove downloaded file", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda14
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$13(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else if (status == 2 || status == 4) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Resume download", "Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda12
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$11(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else if (status == 6) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Pause download", "Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda13
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$12(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else if (status == 7) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Resume download", "Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda12
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$11(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else if (status == 8) {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Pause download", "Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda13
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$12(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                } else {
                    new ListDialog.Builder(movieDetailActivity).setItems(CollectionsKt.arrayListOf("Pause download", "Cancel download", "Manage download")).addAction(new ListDialog.OnItemClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda15
                        @Override // com.movieboxpro.android.view.dialog.ListDialog.OnItemClickListener
                        public final void onClick(View view, int i) {
                            MovieDetailActivity.initListener$lambda$17$lambda$16$lambda$14(this.f$0, downloadFile, view, i);
                        }
                    }).create().show();
                }
            }
        } else {
            MovieDetail movieDetail = movieDetailActivity.movieDetail;
            if (movieDetail != null && (str = movieDetail.id) != null) {
                ((MovieDetailPresenter) movieDetailActivity.mPresenter).getDownloadUrls(str);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$9(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            movieDetailActivity.removeDownload(downloadFile);
        } else {
            if (i != 1) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$10(DownloadFile downloadFile, MovieDetailActivity movieDetailActivity, View view, int i) {
        if (i == 0) {
            DownloadLiveData.INSTANCE.getInstance().cancelDownload(downloadFile.getPath(), downloadFile.getDownloadId());
        } else {
            if (i != 1) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$11(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            DownloadService.DownloadBinder downloadBinder = movieDetailActivity.downloadBinder;
            if (downloadBinder != null) {
                downloadBinder.resumeDownload(downloadFile);
                return;
            }
            return;
        }
        if (i == 1) {
            movieDetailActivity.removeDownload(downloadFile);
        } else {
            if (i != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$12(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            DownloadService.DownloadBinder downloadBinder = movieDetailActivity.downloadBinder;
            if (downloadBinder != null) {
                downloadBinder.pauseDownload(downloadFile);
                return;
            }
            return;
        }
        if (i == 1) {
            movieDetailActivity.removeDownload(downloadFile);
        } else {
            if (i != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$13(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            if (Network.isConnected(movieDetailActivity)) {
                movieDetailActivity.checkOnlineNum(downloadFile);
                return;
            } else {
                movieDetailActivity.goMoviePlayer(downloadFile);
                return;
            }
        }
        if (i == 1) {
            movieDetailActivity.removeDownload(downloadFile);
        } else {
            if (i != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17$lambda$16$lambda$14(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, View view, int i) {
        if (i == 0) {
            DownloadService.DownloadBinder downloadBinder = movieDetailActivity.downloadBinder;
            if (downloadBinder != null) {
                downloadBinder.pauseDownload(downloadFile);
                return;
            }
            return;
        }
        if (i == 1) {
            movieDetailActivity.removeDownload(downloadFile);
        } else {
            if (i != 2) {
                return;
            }
            DownloadActivity.INSTANCE.start(movieDetailActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$17$lambda$7(MovieDetailActivity movieDetailActivity, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        movieDetailActivity.hideLoadingView();
        ToastUtils.showShort("Start download failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$20(final Ref.ObjectRef objectRef, Ref.LongRef longRef, final MovieDetailActivity movieDetailActivity, View view) {
        Disposable disposable = (Disposable) objectRef.element;
        if (disposable != null) {
            disposable.dispose();
        }
        if (System.currentTimeMillis() - longRef.element < 500) {
            movieDetailActivity.dealPlayClick(true);
        } else {
            Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(movieDetailActivity));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.initListener$lambda$20$lambda$18(objectRef, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.initListener$lambda$20$lambda$19(this.f$0, (Long) obj);
                }
            }, 11, null);
        }
        longRef.element = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$20$lambda$19(MovieDetailActivity movieDetailActivity, Long l) {
        movieDetailActivity.dealPlayClick(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit initListener$lambda$20$lambda$18(Ref.ObjectRef objectRef, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        objectRef.element = it;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    private final void dealPlayClick(final boolean playImmediately) {
        String str;
        String str2;
        boolean z;
        if (isLogin()) {
            MovieDetail movieDetail = this.movieDetail;
            if (movieDetail != null) {
                Intrinsics.checkNotNull(movieDetail);
                if (movieDetail.code_file != 1) {
                    MovieDetail movieDetail2 = this.movieDetail;
                    Intrinsics.checkNotNull(movieDetail2);
                    long j = 1000;
                    if (movieDetail2.released_timestamp > System.currentTimeMillis() / j) {
                        MovieDetail movieDetail3 = this.movieDetail;
                        if (movieDetail3 != null) {
                            Intrinsics.checkNotNull(movieDetail3);
                            if (movieDetail3.is_collect == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        ReleasedHintDialog releasedHintDialogNewInstance$default = ReleasedHintDialog.Companion.newInstance$default(ReleasedHintDialog.INSTANCE, z, 1, false, 4, null);
                        releasedHintDialogNewInstance$default.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                MovieDetailActivity.dealPlayClick$lambda$21(this.f$0, view);
                            }
                        });
                        releasedHintDialogNewInstance$default.show(getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    MovieDetail movieDetail4 = this.movieDetail;
                    Intrinsics.checkNotNull(movieDetail4);
                    if (movieDetail4.released_timestamp == 0) {
                        MovieDetail movieDetail5 = this.movieDetail;
                        Intrinsics.checkNotNull(movieDetail5);
                        ReleasedHintDialog releasedHintDialogNewInstance = ReleasedHintDialog.INSTANCE.newInstance(movieDetail5.is_collect == 1, 1, true);
                        releasedHintDialogNewInstance.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda11
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                MovieDetailActivity.dealPlayClick$lambda$22(this.f$0, view);
                            }
                        });
                        releasedHintDialogNewInstance.show(getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    MovieDetail movieDetail6 = this.movieDetail;
                    Intrinsics.checkNotNull(movieDetail6);
                    if (movieDetail6.released_timestamp > 0) {
                        MovieDetail movieDetail7 = this.movieDetail;
                        Intrinsics.checkNotNull(movieDetail7);
                        if (movieDetail7.released_timestamp < System.currentTimeMillis() / j) {
                            ToastUtils.showShort("Not Available", new Object[0]);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            MovieDetail movieDetail8 = this.movieDetail;
            if (movieDetail8 == null || (str = movieDetail8.content_rating) == null) {
                str = "";
            }
            if (checkChildMode(str)) {
                return;
            }
            MovieDetail movieDetail9 = this.movieDetail;
            if (StringsKt.equals("tc", movieDetail9 != null ? movieDetail9.quality_tag_new : null, true)) {
                final LowQualityHintDialog lowQualityHintDialog = new LowQualityHintDialog();
                lowQualityHintDialog.setContinueClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda22
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MovieDetailActivity.dealPlayClick$lambda$24(this.f$0, lowQualityHintDialog, playImmediately, view);
                    }
                });
                lowQualityHintDialog.show(getSupportFragmentManager(), "LowQualityHintDialog");
            } else {
                MovieDetail movieDetail10 = this.movieDetail;
                if (movieDetail10 == null || (str2 = movieDetail10.id) == null) {
                    return;
                }
                ((MovieDetailPresenter) this.mPresenter).getVideoPath(str2, playImmediately);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$21(MovieDetailActivity movieDetailActivity, View view) {
        MovieDetailPresenter movieDetailPresenter = (MovieDetailPresenter) movieDetailActivity.mPresenter;
        MovieDetail movieDetail = movieDetailActivity.movieDetail;
        Intrinsics.checkNotNull(movieDetail);
        String id = movieDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        movieDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$22(MovieDetailActivity movieDetailActivity, View view) {
        MovieDetailPresenter movieDetailPresenter = (MovieDetailPresenter) movieDetailActivity.mPresenter;
        MovieDetail movieDetail = movieDetailActivity.movieDetail;
        Intrinsics.checkNotNull(movieDetail);
        String id = movieDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        movieDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$24(MovieDetailActivity movieDetailActivity, LowQualityHintDialog lowQualityHintDialog, boolean z, View view) {
        String str;
        MovieDetail movieDetail = movieDetailActivity.movieDetail;
        if (movieDetail != null && (str = movieDetail.id) != null) {
            ((MovieDetailPresenter) movieDetailActivity.mPresenter).getVideoPath(str, z);
        }
        lowQualityHintDialog.dismiss();
    }

    private final void checkOnlineNum(final DownloadFile item) {
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, item.getId(), SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.checkOnlineNum$lambda$27(this.f$0, item, (String) obj);
            }
        };
        Observable observableCompose = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda26
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailActivity.checkOnlineNum$lambda$28(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.checkOnlineNum$lambda$29(this.f$0, item, (Throwable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.checkOnlineNum$lambda$30(this.f$0, (String) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.checkOnlineNum$lambda$31(this.f$0, (Disposable) obj);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$28(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$27(final MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            movieDetailActivity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda21
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.hideLoading();
                }
            });
            MovieDetail movieDetail = new MovieDetail();
            movieDetail.id = downloadFile.getId();
            movieDetail.title = downloadFile.getVideoName();
            movieDetail.box_type = downloadFile.getBoxType();
            movieDetail.dateline = downloadFile.getDownloadTime();
            movieDetail.list = CollectionsKt.listOf(BeanKt.toMediaDownloadFile(downloadFile));
            ScreenManageActivity.INSTANCE.start(movieDetailActivity, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), 100, baseResponse.getMsg(), movieDetail);
            observableJust = Observable.empty();
        } else {
            movieDetailActivity.goMoviePlayer(downloadFile);
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$31(MovieDetailActivity movieDetailActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        movieDetailActivity.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$30(MovieDetailActivity movieDetailActivity, String str) {
        movieDetailActivity.hideLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$29(MovieDetailActivity movieDetailActivity, DownloadFile downloadFile, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        movieDetailActivity.hideLoading();
        movieDetailActivity.goMoviePlayer(downloadFile);
        return Unit.INSTANCE;
    }

    private final void goMoviePlayer(DownloadFile item) {
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.id = item.getId();
        movieDetail.title = item.getVideoName();
        movieDetail.box_type = item.getBoxType();
        movieDetail.dateline = item.getDownloadTime();
        movieDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(item));
        VideoPlayerActivity.start(this, movieDetail, item.getId());
    }

    private final void removeDownload(final DownloadFile item) {
        Observable observableJust = Observable.just(new File(item.getPath(), item.getFileName()));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.removeDownload$lambda$32(item, this, (File) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda31
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailActivity.removeDownload$lambda$33(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.removeDownload$lambda$34((Boolean) obj);
            }
        }, 15, null);
        ((ActivityMovieDetail2Binding) this.binding).downloadStatusView.resumeStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean removeDownload$lambda$33(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Boolean) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean removeDownload$lambda$32(DownloadFile downloadFile, MovieDetailActivity movieDetailActivity, File it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!CommonUtils.isFFmpegDownload(downloadFile.getPath())) {
            FileUtils.delete(it);
            DownloadService.DownloadBinder downloadBinder = movieDetailActivity.downloadBinder;
            if (downloadBinder == null) {
                DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
            } else if (downloadBinder != null) {
                downloadBinder.deleteDownload(downloadFile);
            }
        } else {
            FileUtils.deleteDir(new File(downloadFile.getPath()));
            DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFile.getDownloadId());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeDownload$lambda$34(Boolean bool) {
        return Unit.INSTANCE;
    }

    private final boolean checkChildMode(String contentRating) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || !CommonUtils.isChildModeVideo(contentRating)) {
            return false;
        }
        ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this);
        childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda34
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MovieDetailActivity.checkChildMode$lambda$35(this.f$0);
            }
        });
        childModeHintDialog.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkChildMode$lambda$35(MovieDetailActivity movieDetailActivity) {
        new OpenChildModeDialog().show(movieDetailActivity.getSupportFragmentManager(), "OpenChildModeDialog");
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void goVideoPlayer(final BaseMediaModel baseMediaModel, final boolean playImmediately) {
        List<BaseMediaModel.DownloadFile> list = baseMediaModel != null ? baseMediaModel.list : null;
        if (list == null || list.isEmpty()) {
            ToastUtils.showShort("Not Available", new Object[0]);
        } else {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
                ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
                choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.goVideoPlayer.1
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void playWithOther() {
                        List<BaseMediaModel.DownloadFile> list2;
                        ChoosePlayerQualityFragment.Companion companion = ChoosePlayerQualityFragment.INSTANCE;
                        BaseMediaModel baseMediaModel2 = baseMediaModel;
                        ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = companion.newInstance(new ArrayList<>((baseMediaModel2 == null || (list2 = baseMediaModel2.list) == null) ? new ArrayList() : list2));
                        final MovieDetailActivity movieDetailActivity = this;
                        choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$goVideoPlayer$1$playWithOther$1
                            @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                            public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                                movieDetailActivity.playerWithOtherPlayer(downloadUrl.path);
                            }
                        });
                        choosePlayerQualityFragmentNewInstance.show(this.getSupportFragmentManager(), "ChoosePlayerQualityFragment");
                    }

                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void play() {
                        this.startPlay(baseMediaModel, playImmediately);
                    }
                });
                choosePlayerDialog.show(getSupportFragmentManager(), "ChoosePlayerDialog");
                return;
            }
            startPlay(baseMediaModel, playImmediately);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playerWithOtherPlayer(String url) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), "video/mp4");
            startActivity(intent);
            Intent.createChooser(intent, "Choose a player to play!");
        } catch (Exception unused) {
            ToastUtils.showShort("No player available", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlay(BaseMediaModel baseMediaModel, boolean playImmediately) {
        if (playImmediately) {
            MovieDetailActivity movieDetailActivity = this;
            MovieDetail movieDetail = this.movieDetail;
            VideoPlayerActivity.start(movieDetailActivity, movieDetail, movieDetail != null ? movieDetail.id : null);
            return;
        }
        MovieDetail movieDetail2 = this.movieDetail;
        if (movieDetail2 != null) {
            movieDetail2.addDonwload(baseMediaModel);
        }
        if ((baseMediaModel != null ? baseMediaModel.seconds : 0) > 300) {
            ChoosePlayFragment choosePlayFragmentNewInstance = ChoosePlayFragment.INSTANCE.newInstance(1, false);
            choosePlayFragmentNewInstance.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.startPlay.1
                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void delete() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void goDetail() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void markWatched() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseResume() {
                    MovieDetailActivity movieDetailActivity2 = MovieDetailActivity.this;
                    MovieDetailActivity movieDetailActivity3 = movieDetailActivity2;
                    MovieDetail movieDetail3 = movieDetailActivity2.movieDetail;
                    MovieDetail movieDetail4 = MovieDetailActivity.this.movieDetail;
                    VideoPlayerActivity.start(movieDetailActivity3, movieDetail3, movieDetail4 != null ? movieDetail4.id : null);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseRestart() {
                    MovieDetailActivity movieDetailActivity2 = MovieDetailActivity.this;
                    MovieDetailActivity movieDetailActivity3 = movieDetailActivity2;
                    MovieDetail movieDetail3 = movieDetailActivity2.movieDetail;
                    MovieDetail movieDetail4 = MovieDetailActivity.this.movieDetail;
                    VideoPlayerActivity.start(movieDetailActivity3, movieDetail3, movieDetail4 != null ? movieDetail4.id : null, true);
                }
            });
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            choosePlayFragmentNewInstance.showAllowingStateLoss(supportFragmentManager, "ChoosePlayFragment");
            return;
        }
        MovieDetailActivity movieDetailActivity2 = this;
        MovieDetail movieDetail3 = this.movieDetail;
        VideoPlayerActivity.start(movieDetailActivity2, movieDetail3, movieDetail3 != null ? movieDetail3.id : null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onPlayerFinish(OnPlayerFinishEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ObservableSubscribeProxy observableSubscribeProxy = (ObservableSubscribeProxy) Observable.timer(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailActivity.onPlayerFinish$lambda$36(this.f$0, (Long) obj);
            }
        };
        observableSubscribeProxy.subscribe(new Consumer() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda24
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPlayerFinish$lambda$36(MovieDetailActivity movieDetailActivity, Long l) {
        movieDetailActivity.requestData();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void addToPlayListSuccess(int position) {
        AddToFavoriteDialogFragment addToFavoriteDialogFragment = this.addToFavoriteDialog;
        if (addToFavoriteDialogFragment != null) {
            addToFavoriteDialogFragment.addPlayListNum(position);
        }
        EventBus.getDefault().post(new RefreshMovieListEvent());
        ToastUtils.showShort("Add successfully", new Object[0]);
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void showPlayList(List<? extends MovieListModel.MovieListItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        AddToDialog.Companion companion = AddToDialog.INSTANCE;
        MovieDetail movieDetail = this.movieDetail;
        int i = movieDetail != null ? movieDetail.plan_watched : -1;
        MovieDetail movieDetail2 = this.movieDetail;
        int i2 = movieDetail2 != null ? movieDetail2.is_collect : 0;
        MovieDetail movieDetail3 = this.movieDetail;
        AddToDialog addToDialogNewInstance = companion.newInstance(1, i, i2, movieDetail3 != null ? movieDetail3.id : null);
        addToDialogNewInstance.setMovieList(list);
        addToDialogNewInstance.setListener(new AddToDialog.AddToListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.showPlayList.1
            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void download() {
            }

            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void onWatchedChange(int watched) {
                MovieDetail movieDetail4 = MovieDetailActivity.this.movieDetail;
                if (movieDetail4 != null) {
                    movieDetail4.plan_watched = watched;
                }
            }

            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void onFavorite(int favorite) {
                MovieDetail movieDetail4 = MovieDetailActivity.this.movieDetail;
                if (movieDetail4 != null) {
                    movieDetail4.is_collect = favorite;
                }
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        addToDialogNewInstance.showAllowingStateLoss(supportFragmentManager, "AddToDialog");
    }

    private final void showAddToFavorite() {
        MovieDetailPresenter movieDetailPresenter = (MovieDetailPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        movieDetailPresenter.requestPlayList(uid_v2);
    }

    private final void showVideoInfoDialog() {
        VideoInfoDialogFragment.INSTANCE.newInstance(this.movieDetail, ((ActivityMovieDetail2Binding) this.binding).tvMovieDesc.getText().toString()).show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(VideoInfoDialogFragment.class).getSimpleName());
    }

    private final void showChooseLanguage() {
        ChooseLanguageDialogFragment chooseLanguageDialogFragment = new ChooseLanguageDialogFragment();
        MovieDetail movieDetail = this.movieDetail;
        if (movieDetail != null) {
            BaseMediaModel.MoreLanguage moreLanguage = new BaseMediaModel.MoreLanguage();
            moreLanguage.lang = "";
            moreLanguage.title = "Translate";
            movieDetail.language.add(moreLanguage);
            List<BaseMediaModel.MoreLanguage> language = movieDetail.language;
            Intrinsics.checkNotNullExpressionValue(language, "language");
            chooseLanguageDialogFragment.setData(language);
        }
        chooseLanguageDialogFragment.show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ChooseLanguageDialogFragment.class).getSimpleName());
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void addFavoriteSuccess(boolean isFavorite) {
        if (isFavorite) {
            ToastUtils.showShort("Add successfully", new Object[0]);
            MovieDetail movieDetail = this.movieDetail;
            if (movieDetail != null) {
                movieDetail.is_collect = 1;
            }
        } else {
            MovieDetail movieDetail2 = this.movieDetail;
            if (movieDetail2 != null) {
                movieDetail2.is_collect = 0;
            }
        }
        EventBus.getDefault().post(new RefreshFavoriteEvent());
    }

    @Override // com.movieboxpro.android.listener.CommonItemClickListener
    public void onItemClicked(int position, String type) {
        String str;
        String str2;
        String str3;
        String str4;
        List<BaseMediaModel.MoreLanguage> list;
        BaseMediaModel.MoreLanguage moreLanguage;
        List<BaseMediaModel.MoreLanguage> list2;
        BaseMediaModel.MoreLanguage moreLanguage2;
        String str5;
        List<BaseMediaModel.MoreLanguage> list3;
        BaseMediaModel.MoreLanguage moreLanguage3;
        Intrinsics.checkNotNullParameter(type, "type");
        String str6 = "";
        if (Intrinsics.areEqual(type, ChooseLanguageDialogFragment.TYPE)) {
            MovieDetail movieDetail = this.movieDetail;
            if (movieDetail == null || (list3 = movieDetail.language) == null || (moreLanguage3 = list3.get(position)) == null || (str3 = moreLanguage3.lang) == null) {
                str3 = "";
            }
            if (str3.length() == 0) {
                ChooseTranslateLanguageDialog chooseTranslateLanguageDialog = new ChooseTranslateLanguageDialog();
                chooseTranslateLanguageDialog.setListener(new C01301());
                chooseTranslateLanguageDialog.show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ChooseTranslateLanguageDialog.class).getSimpleName());
                return;
            }
            MovieDetail movieDetail2 = this.movieDetail;
            if (movieDetail2 != null && (list2 = movieDetail2.language) != null && (moreLanguage2 = list2.get(position)) != null && (str5 = moreLanguage2.lang) != null) {
                str6 = str5;
            }
            this.selectLanguage = str6;
            MovieDetailPresenter movieDetailPresenter = (MovieDetailPresenter) this.mPresenter;
            String str7 = this.id;
            MovieDetail movieDetail3 = this.movieDetail;
            if (movieDetail3 == null || (list = movieDetail3.language) == null || (moreLanguage = list.get(position)) == null || (str4 = moreLanguage.lang) == null) {
                str4 = "default";
            }
            movieDetailPresenter.requestMovieInfo(str7, str4);
            return;
        }
        if (Intrinsics.areEqual(type, AddToFavoriteDialogFragment.TYPE)) {
            boolean z = false;
            if (position == 0) {
                MovieDetailPresenter movieDetailPresenter2 = (MovieDetailPresenter) this.mPresenter;
                MovieDetail movieDetail4 = this.movieDetail;
                if (movieDetail4 == null || (str2 = movieDetail4.id) == null) {
                    str2 = "";
                }
                String str8 = App.getUserData().uid_v2;
                str6 = str8 != null ? str8 : "";
                MovieDetail movieDetail5 = this.movieDetail;
                if (movieDetail5 != null && movieDetail5.is_collect == 1) {
                    z = true;
                }
                movieDetailPresenter2.addToFavorite(str2, str6, z);
                return;
            }
            List<MovieListModel.MovieListItem> list4 = this.playList;
            MovieListModel.MovieListItem movieListItem = list4 != null ? list4.get(position) : null;
            if (movieListItem != null) {
                MovieDetailPresenter movieDetailPresenter3 = (MovieDetailPresenter) this.mPresenter;
                String lid = movieListItem.getLid();
                Intrinsics.checkNotNullExpressionValue(lid, "getLid(...)");
                MovieDetail movieDetail6 = this.movieDetail;
                String str9 = (movieDetail6 == null || (str = movieDetail6.id) == null) ? "" : str;
                String uid_v2 = App.getUserData().uid_v2;
                Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
                MovieDetail movieDetail7 = this.movieDetail;
                movieDetailPresenter3.addToPlayList(lid, str9, uid_v2, movieDetail7 != null ? movieDetail7.getBoxType() : 0, position);
            }
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/movieboxpro/android/tv/movie/MovieDetailActivity$onItemClicked$1", "Lcom/movieboxpro/android/view/dialog/ChooseTranslateLanguageDialog$OnLanguageListener;", "onLanguageSelected", "", "lang", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class C01301 implements ChooseTranslateLanguageDialog.OnLanguageListener {
        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$0(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$5(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it;
        }

        C01301() {
        }

        @Override // com.movieboxpro.android.view.dialog.ChooseTranslateLanguageDialog.OnLanguageListener
        public void onLanguageSelected(String lang) {
            Intrinsics.checkNotNullParameter(lang, "lang");
            ApiService3 apiService = HttpHelperKt.getApiService();
            MovieDetail movieDetail = MovieDetailActivity.this.movieDetail;
            Intrinsics.checkNotNull(movieDetail);
            Observable<String> observableTranslate = apiService.translate("https://srt.shegu.net/api/srttrans/text/", "auto", lang, movieDetail.description);
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$0((String) obj);
                }
            };
            Observable<R> map = observableTranslate.map(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$1(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            ObservableSubscribeProxy observableSubscribeProxyTransform = RxSubscribersKt.transform(map, MovieDetailActivity.this);
            final MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
            Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$2(movieDetailActivity, (ApiException) obj);
                }
            };
            final MovieDetailActivity movieDetailActivity2 = MovieDetailActivity.this;
            Function1 function3 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$3(movieDetailActivity2, (Disposable) obj);
                }
            };
            final MovieDetailActivity movieDetailActivity3 = MovieDetailActivity.this;
            RxSubscribersKt.subscribeTo$default(observableSubscribeProxyTransform, function2, null, function3, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$4(movieDetailActivity3, (String) obj);
                }
            }, 10, null);
            ApiService3 apiService2 = HttpHelperKt.getApiService();
            MovieDetail movieDetail2 = MovieDetailActivity.this.movieDetail;
            Intrinsics.checkNotNull(movieDetail2);
            Observable<String> observableTranslate2 = apiService2.translate("https://srt.shegu.net/api/srttrans/text/", "auto", lang, movieDetail2.title);
            final Function1 function4 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$5((String) obj);
                }
            };
            Observable<R> map2 = observableTranslate2.map(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda6
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$6(function4, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            ObservableSubscribeProxy observableSubscribeProxyTransform2 = RxSubscribersKt.transform(map2, MovieDetailActivity.this);
            final MovieDetailActivity movieDetailActivity4 = MovieDetailActivity.this;
            Function1 function5 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$7(movieDetailActivity4, (ApiException) obj);
                }
            };
            final MovieDetailActivity movieDetailActivity5 = MovieDetailActivity.this;
            Function1 function6 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$8(movieDetailActivity5, (Disposable) obj);
                }
            };
            final MovieDetailActivity movieDetailActivity6 = MovieDetailActivity.this;
            RxSubscribersKt.subscribeTo$default(observableSubscribeProxyTransform2, function5, null, function6, null, new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$onItemClicked$1$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MovieDetailActivity.C01301.onLanguageSelected$lambda$9(movieDetailActivity6, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$1(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (String) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$3(MovieDetailActivity movieDetailActivity, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            movieDetailActivity.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$4(MovieDetailActivity movieDetailActivity, String str) {
            movieDetailActivity.hideLoadingView();
            ((ActivityMovieDetail2Binding) movieDetailActivity.binding).tvMovieDesc.setText(str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$2(MovieDetailActivity movieDetailActivity, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            movieDetailActivity.hideLoadingView();
            ToastUtils.showShort("Translate failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$6(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (String) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$8(MovieDetailActivity movieDetailActivity, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            movieDetailActivity.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$9(MovieDetailActivity movieDetailActivity, String str) {
            movieDetailActivity.hideLoadingView();
            ((ActivityMovieDetail2Binding) movieDetailActivity.binding).tvMovieName.setText(str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$7(MovieDetailActivity movieDetailActivity, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            movieDetailActivity.hideLoadingView();
            ToastUtils.showShort("Translate failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }
    }

    private final void focusScale(View v) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        float f = this.scale;
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(f, f, 0.0f));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        requestData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$41$lambda$40(MovieDetailActivity movieDetailActivity, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            movieDetailActivity.focusScale(view);
        }
        FocusBorder focusBorder = movieDetailActivity.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindService() {
        DownloadService.INSTANCE.bind(this, this.serviceConnection);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        FrameLayout flTop = ((ActivityMovieDetail2Binding) this.binding).flTop;
        Intrinsics.checkNotNullExpressionValue(flTop, "flTop");
        FrameLayout frameLayout = flTop;
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = ScreenUtils.getScreenHeight() - ScreenUtils.dip2px(this, 100.0f);
            frameLayout.setLayoutParams(layoutParams);
            String stringExtra = getIntent().getStringExtra("id");
            if (stringExtra == null) {
                stringExtra = "";
            }
            this.id = stringExtra;
            String stringExtra2 = getIntent().getStringExtra("poster");
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            this.poster = stringExtra2;
            String str = App.deviceLang;
            this.lang = str != null ? str : "";
            if (this.poster.length() > 0) {
                setBlurBackground();
            }
            bindService();
            requestData();
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_DOWNLOAD)) {
                FrameLayout flDownload = ((ActivityMovieDetail2Binding) this.binding).flDownload;
                Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
                CommonExtKt.gone(flDownload);
            }
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass2(null), 3, null);
            initDownloadFileFlow();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.movie.MovieDetailActivity$initData$2, reason: invalid class name */
    /* JADX INFO: compiled from: MovieDetailActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.tv.movie.MovieDetailActivity$initData$2", f = "MovieDetailActivity.kt", i = {}, l = {1034}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MovieDetailActivity.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Long> downloadingNumFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadingNumFlow();
                final MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                this.label = 1;
                if (downloadingNumFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.initData.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).longValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(long j, Continuation<? super Unit> continuation) {
                        if (j > 0) {
                            movieDetailActivity.getWindow().addFlags(128);
                        } else {
                            movieDetailActivity.getWindow().clearFlags(128);
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

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.movie.MovieDetailActivity$initDownloadFileFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieDetailActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.tv.movie.MovieDetailActivity$initDownloadFileFlow$1", f = "MovieDetailActivity.kt", i = {}, l = {1048}, m = "invokeSuspend", n = {}, s = {})
    static final class C01281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01281(Continuation<? super C01281> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MovieDetailActivity.this.new C01281(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<DownloadFile>> flowFindMovieFileFlow = DownloadFileHelper.INSTANCE.getInstance().findMovieFileFlow(MovieDetailActivity.this.id);
                final MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                this.label = 1;
                if (flowFindMovieFileFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.initDownloadFileFlow.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends DownloadFile>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) throws Throwable {
                        DownloadFile downloadFile = (DownloadFile) CollectionsKt.firstOrNull((List) list);
                        if (downloadFile != null) {
                            MovieDetailActivity movieDetailActivity2 = movieDetailActivity;
                            String downloadId = downloadFile.getDownloadId();
                            Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                            movieDetailActivity2.currDownloadId = downloadId;
                            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new MovieDetailActivity$initDownloadFileFlow$1$1$1$1(downloadFile, movieDetailActivity2, null), continuation);
                            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                return objWithContext;
                            }
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

    private final void initDownloadFileFlow() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C01281(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initWatchedStatus(int watched) {
        if (watched == -1) {
            ((ActivityMovieDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_watch_plan_default);
        } else if (watched == 0) {
            ((ActivityMovieDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_waiting_added);
        } else {
            if (watched != 1) {
                return;
            }
            ((ActivityMovieDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_watched_added);
        }
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void showMovieInfo(MovieDetail movieDetail) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        List<MovieDetail.Recommend> list;
        String deviceLang;
        List<BaseMediaModel.MoreLanguage> list2;
        List<BaseMediaModel.MoreLanguage> list3;
        initWatchedStatus(movieDetail != null ? movieDetail.plan_watched : -1);
        this.movieDetail = movieDetail;
        ((ActivityMovieDetail2Binding) this.binding).tvMovieName.setText(movieDetail != null ? movieDetail.title : null);
        boolean z = false;
        if (this.selectLanguage.length() == 0) {
            if (movieDetail == null || (list3 = movieDetail.language) == null) {
                deviceLang = "en";
                break;
            }
            Iterator<T> it = list3.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual(((BaseMediaModel.MoreLanguage) it.next()).lang, App.deviceLang)) {
                        deviceLang = App.deviceLang;
                        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
                        break;
                    }
                } else {
                    deviceLang = "en";
                    break;
                }
            }
            if (((movieDetail == null || (list2 = movieDetail.language) == null) ? 0 : list2.size()) > 1) {
                TextView tvLanguage = ((ActivityMovieDetail2Binding) this.binding).tvLanguage;
                Intrinsics.checkNotNullExpressionValue(tvLanguage, "tvLanguage");
                CommonExtKt.visible(tvLanguage);
                ((ActivityMovieDetail2Binding) this.binding).tvLanguage.setText(deviceLang);
            } else {
                TextView tvLanguage2 = ((ActivityMovieDetail2Binding) this.binding).tvLanguage;
                Intrinsics.checkNotNullExpressionValue(tvLanguage2, "tvLanguage");
                CommonExtKt.gone(tvLanguage2);
            }
        } else {
            ((ActivityMovieDetail2Binding) this.binding).tvLanguage.setText(this.selectLanguage);
        }
        if (movieDetail == null || movieDetail.seconds != 0) {
            TextView textView = ((ActivityMovieDetail2Binding) this.binding).tvPlay;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("RESUME %s", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.getTime(movieDetail != null ? movieDetail.seconds : 0)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str);
        }
        ((ActivityMovieDetail2Binding) this.binding).tvMovieDesc.setText(movieDetail != null ? movieDetail.description : null);
        TextView textView2 = ((ActivityMovieDetail2Binding) this.binding).tvMovieDirector;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String str2 = String.format("Directors: %s", Arrays.copyOf(new Object[]{movieDetail != null ? movieDetail.director : null}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        textView2.setText(str2);
        TextView textView3 = ((ActivityMovieDetail2Binding) this.binding).tvMovieStars;
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String str3 = String.format("Stars: %s", Arrays.copyOf(new Object[]{movieDetail != null ? movieDetail.actors : null}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
        textView3.setText(str3);
        if (movieDetail != null) {
            DetailMetaInfoBinder detailMetaInfoBinder = DetailMetaInfoBinder.INSTANCE;
            LayoutDetailMetaRowBinding metaInfoRow = ((ActivityMovieDetail2Binding) this.binding).metaInfoRow;
            Intrinsics.checkNotNullExpressionValue(metaInfoRow, "metaInfoRow");
            detailMetaInfoBinder.bindMovieDetail(metaInfoRow, movieDetail);
        }
        GlideUtils.load((Activity) this, movieDetail != null ? movieDetail.banner : null, ((ActivityMovieDetail2Binding) this.binding).ivBackground);
        ((ActivityMovieDetail2Binding) this.binding).rlPlay.requestFocus();
        ((ActivityMovieDetail2Binding) this.binding).llMovieName.setFocusable(true);
        ((ActivityMovieDetail2Binding) this.binding).llMovieInfo.setFocusable(true);
        MovieDetailRelatedFragment.Companion companion = MovieDetailRelatedFragment.INSTANCE;
        String str4 = this.id;
        if (movieDetail == null || (arrayList = movieDetail.recommend) == null) {
            arrayList = new ArrayList();
        }
        if (movieDetail == null || (arrayList2 = movieDetail.playlists) == null) {
            arrayList2 = new ArrayList();
        }
        if (movieDetail == null || (arrayList3 = movieDetail.getTrailer_url_arr()) == null) {
            arrayList3 = new ArrayList();
        }
        MovieDetailRelatedFragment movieDetailRelatedFragmentNewInstance = companion.newInstance(str4, arrayList, arrayList2, arrayList3);
        movieDetailRelatedFragmentNewInstance.setFocusListener(new AnonymousClass3());
        FragmentUtils.add(getSupportFragmentManager(), movieDetailRelatedFragmentNewInstance, R.id.frameLayout);
        if (movieDetail != null && (list = movieDetail.recommend) != null && (!list.isEmpty())) {
            z = true;
        }
        this.isNeedDown = z;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            FrameLayout frameLayout = ((ActivityMovieDetail2Binding) this.binding).frameLayout;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
            CommonExtKt.gone(frameLayout);
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.movie.MovieDetailActivity$showMovieInfo$3, reason: invalid class name */
    /* JADX INFO: compiled from: MovieDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/movieboxpro/android/tv/movie/MovieDetailActivity$showMovieInfo$3", "Lcom/movieboxpro/android/tv/movie/MovieDetailRelatedFragment$OnFocusListener;", "onFocusChanged", "", "focus", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass3 implements MovieDetailRelatedFragment.OnFocusListener {
        AnonymousClass3() {
        }

        @Override // com.movieboxpro.android.tv.movie.MovieDetailRelatedFragment.OnFocusListener
        public void onFocusChanged(boolean focus) {
            if (focus) {
                FrameLayout frameLayout = ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).rlPlay;
                final MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                frameLayout.postDelayed(new Runnable() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$showMovieInfo$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MovieDetailActivity.AnonymousClass3.onFocusChanged$lambda$0(movieDetailActivity);
                    }
                }, 200L);
            } else {
                FrameLayout frameLayout2 = ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).rlPlay;
                final MovieDetailActivity movieDetailActivity2 = MovieDetailActivity.this;
                frameLayout2.postDelayed(new Runnable() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$showMovieInfo$3$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MovieDetailActivity.AnonymousClass3.onFocusChanged$lambda$1(movieDetailActivity2);
                    }
                }, 300L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onFocusChanged$lambda$0(MovieDetailActivity movieDetailActivity) {
            movieDetailActivity.scrollDown();
            FrameLayout rlPlay = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).rlPlay;
            Intrinsics.checkNotNullExpressionValue(rlPlay, "rlPlay");
            ImageView ivWatchPlan = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).ivWatchPlan;
            Intrinsics.checkNotNullExpressionValue(ivWatchPlan, "ivWatchPlan");
            ImageView ivAdd = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).ivAdd;
            Intrinsics.checkNotNullExpressionValue(ivAdd, "ivAdd");
            FrameLayout flDownload = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).flDownload;
            Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
            LinearLayout llMovieInfo = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).llMovieInfo;
            Intrinsics.checkNotNullExpressionValue(llMovieInfo, "llMovieInfo");
            ConstraintLayout llMovieName = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).llMovieName;
            Intrinsics.checkNotNullExpressionValue(llMovieName, "llMovieName");
            movieDetailActivity.setFocusable(false, rlPlay, ivWatchPlan, ivAdd, flDownload, llMovieInfo, llMovieName);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onFocusChanged$lambda$1(MovieDetailActivity movieDetailActivity) {
            FrameLayout rlPlay = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).rlPlay;
            Intrinsics.checkNotNullExpressionValue(rlPlay, "rlPlay");
            ImageView ivWatchPlan = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).ivWatchPlan;
            Intrinsics.checkNotNullExpressionValue(ivWatchPlan, "ivWatchPlan");
            ImageView ivAdd = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).ivAdd;
            Intrinsics.checkNotNullExpressionValue(ivAdd, "ivAdd");
            FrameLayout flDownload = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).flDownload;
            Intrinsics.checkNotNullExpressionValue(flDownload, "flDownload");
            LinearLayout llMovieInfo = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).llMovieInfo;
            Intrinsics.checkNotNullExpressionValue(llMovieInfo, "llMovieInfo");
            ConstraintLayout llMovieName = ((ActivityMovieDetail2Binding) movieDetailActivity.binding).llMovieName;
            Intrinsics.checkNotNullExpressionValue(llMovieName, "llMovieName");
            movieDetailActivity.setFocusable(true, rlPlay, ivWatchPlan, ivAdd, flDownload, llMovieInfo, llMovieName);
            movieDetailActivity.scrollUp();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scrollUp() {
        ObjectAnimator duration = ObjectAnimator.ofInt(((ActivityMovieDetail2Binding) this.binding).scrollView, "scrollY", 0).setDuration(500L);
        Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
        duration.addListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.scrollUp.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).rlPlay.requestFocus();
            }
        });
        duration.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scrollDown() {
        ObjectAnimator duration = ObjectAnimator.ofInt(((ActivityMovieDetail2Binding) this.binding).scrollView, "scrollY", ((ActivityMovieDetail2Binding) this.binding).scrollView.getHeight()).setDuration(500L);
        Intrinsics.checkNotNullExpressionValue(duration, "setDuration(...)");
        duration.addListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.scrollDown.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ((ActivityMovieDetail2Binding) MovieDetailActivity.this.binding).frameLayout.requestFocus();
            }
        });
        duration.start();
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void loadInfoError() {
        LinearLayout llLoading = ((ActivityMovieDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.visible(llLoading);
        ProgressBar progressBar = ((ActivityMovieDetail2Binding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        TextView tvPlay = ((ActivityMovieDetail2Binding) this.binding).tvPlay;
        Intrinsics.checkNotNullExpressionValue(tvPlay, "tvPlay");
        CommonExtKt.gone(tvPlay);
        ((ActivityMovieDetail2Binding) this.binding).tvLoading.setText("load error");
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void showDownloadDialog(List<DownloadUrl> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ChooseDownloadQualityFragment chooseDownloadQualityFragmentNewInstance = ChooseDownloadQualityFragment.INSTANCE.newInstance(list);
        chooseDownloadQualityFragmentNewInstance.setCallback(new ChooseDownloadQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity.showDownloadDialog.1
            @Override // com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment.ChooseDownloadQualityListener
            public void onChooseDownload(DownloadUrl downloadUrl, int position) {
                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                MovieDetail movieDetail = MovieDetailActivity.this.movieDetail;
                if (movieDetail != null) {
                    MovieDetailActivity movieDetailActivity = MovieDetailActivity.this;
                    if (!movieDetailActivity.isBindService) {
                        movieDetailActivity.bindService();
                    }
                    if (downloadUrl.getHls()) {
                        DownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                        return;
                    }
                    if (Build.VERSION.SDK_INT < 24) {
                        DownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                        return;
                    }
                    if (downloadUrl.getSize_bytes() < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
                        DownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                        return;
                    }
                    String string = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
                    if (!Intrinsics.areEqual(string, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())) {
                        List<StorageVolume> value = UsbConnectReceiver.INSTANCE.getStoragesVolumes().getValue();
                        Object objM = null;
                        if (value != null) {
                            for (Object obj : value) {
                                StorageVolume storageVolumeM = App$$ExternalSyntheticApiModelOutline0.m(obj);
                                Intrinsics.checkNotNull(string);
                                if (StringsKt.contains$default((CharSequence) string, (CharSequence) String.valueOf(storageVolumeM.getUuid()), false, 2, (Object) null)) {
                                    objM = obj;
                                    break;
                                }
                            }
                            objM = App$$ExternalSyntheticApiModelOutline0.m(objM);
                        }
                        if (objM != null) {
                            HashMap map = (HashMap) JSONObject.parseObject(JSONObject.toJSONString(objM), HashMap.class);
                            Intrinsics.checkNotNull(map);
                            long j = Long.parseLong(String.valueOf(map.get("maxFileSize")));
                            if (j != 0 && j < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
                                FFmpegDownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                                return;
                            } else {
                                DownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                                return;
                            }
                        }
                        return;
                    }
                    DownloadService.INSTANCE.startDownload(movieDetailActivity, downloadUrl, movieDetail);
                }
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        chooseDownloadQualityFragmentNewInstance.showAllowingStateLoss(supportFragmentManager, "ChooseDownloadQualityFragment");
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void showPlayButtonLoading() {
        LinearLayout llLoading = ((ActivityMovieDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.visible(llLoading);
        LinearLayout llResume = ((ActivityMovieDetail2Binding) this.binding).llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        CommonExtKt.gone(llResume);
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void hidePlayButtonLoading() {
        LinearLayout llLoading = ((ActivityMovieDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.gone(llLoading);
        LinearLayout llResume = ((ActivityMovieDetail2Binding) this.binding).llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        CommonExtKt.visible(llResume);
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id) {
        Intrinsics.checkNotNullParameter(list, "list");
        ScreenManageActivity.Companion.start$default(ScreenManageActivity.INSTANCE, this, list, 100, msg, 1, id, 0, 0, false, false, 960, null);
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.View
    public void hideLoadView() {
        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        ((MovieDetailPresenter) this.mPresenter).requestMovieInfo(this.id, this.lang);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100 && data != null) {
            Serializable serializableExtra = data.getSerializableExtra("detail");
            MovieDetail movieDetail = serializableExtra instanceof MovieDetail ? (MovieDetail) serializableExtra : null;
            if (movieDetail != null) {
                VideoPlayerActivity.start(this, movieDetail, movieDetail.id);
                return;
            }
            MovieDetailPresenter movieDetailPresenter = (MovieDetailPresenter) this.mPresenter;
            String stringExtra = getIntent().getStringExtra("id");
            if (stringExtra == null) {
                stringExtra = "";
            }
            movieDetailPresenter.getVideoPath(stringExtra, false);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.isBindService) {
            DownloadService.INSTANCE.unBind(this, this.serviceConnection);
        }
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.unBoundGlobalFocusListener();
        ((ActivityMovieDetail2Binding) this.binding).ivBackground.setImageDrawable(null);
        ((ActivityMovieDetail2Binding) this.binding).backgroundLayout.setBackground(null);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public MovieDetailPresenter bindPresenter() {
        return new MovieDetailPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_movie_detail2;
    }

    /* JADX INFO: compiled from: MovieDetailActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\t¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailActivity$Companion;", "", "<init>", "()V", "starter", "", "context", "Landroid/content/Context;", "id", "", "poster", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Context context, String id, String poster) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intent intent = new Intent(context, (Class<?>) MovieDetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("poster", poster);
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.tv.movie.MovieDetailActivity$$ExternalSyntheticLambda33
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    MovieDetailActivity.setViewFocusListener$lambda$41$lambda$40(this.f$0, view2, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFocusable(boolean focus, View... view) {
        for (View view2 : view) {
            view2.setFocusable(focus);
        }
    }
}
