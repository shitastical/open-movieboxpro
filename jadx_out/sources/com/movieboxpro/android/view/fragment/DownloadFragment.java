package com.movieboxpro.android.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jeffmony.downloader.VideoDownloadManager;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.event.DownloadCompleteEvent;
import com.movieboxpro.android.event.FileDeleteEvent;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.DownloadLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.ExternalStorage;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.service.MyDownloadService;
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
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.download.DownloadUtil;
import com.movieboxpro.android.view.activity.DownloadedTvActivity;
import com.movieboxpro.android.view.activity.DownloadingActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentDownloadBinding;
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
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import me.jessyan.autosize.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DownloadFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u001c\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00122\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010&\u001a\u00020$H\u0002J\u0006\u0010'\u001a\u00020$J\u0006\u0010(\u001a\u00020$J\u0018\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010+\u001a\u00020$H\u0002J\b\u0010,\u001a\u00020$H\u0002J\u0006\u0010-\u001a\u00020\u0014J\u0010\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\bH\u0002J\u0010\u00100\u001a\u00020$2\u0006\u0010/\u001a\u00020\bH\u0002J\"\u00101\u001a\u00020$2\u0006\u00102\u001a\u00020\u00192\u0006\u00103\u001a\u00020\u00192\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0010\u00106\u001a\u00020$2\u0006\u0010/\u001a\u00020\bH\u0002J\b\u00107\u001a\u00020$H\u0002J\b\u00108\u001a\u00020$H\u0002J\b\u00109\u001a\u00020$H\u0016J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020<H\u0007J\u0010\u0010=\u001a\u00020$2\u0006\u0010;\u001a\u00020>H\u0007J\b\u0010?\u001a\u00020$H\u0002J\b\u0010@\u001a\u00020$H\u0002J\u0012\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010BH\u0002J\b\u0010D\u001a\u00020$H\u0002J\b\u0010E\u001a\u00020$H\u0002J\b\u0010F\u001a\u00020$H\u0002J\u001c\u0010G\u001a\u00020$2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\b0IH\u0082@¢\u0006\u0002\u0010JJ\u001c\u0010K\u001a\u00020$2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\b0IH\u0082@¢\u0006\u0002\u0010JJ\b\u0010L\u001a\u00020$H\u0016J\b\u0010M\u001a\u00020$H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/movieboxpro/android/view/fragment/DownloadFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentDownloadBinding;", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "storageAdapter", "Lcom/movieboxpro/android/model/ExternalStorage;", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "focusBorder", "Lcom/owen/focus/FocusBorder;", "scale", "", "emptyView", "Landroid/view/View;", "value", "", "edit", "setEdit", "(Z)V", "selectCount", "", "selectSize", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "initView", "showLoading", "hideLoading", "focusScale", "v", "initListener", "updateTopActionFocusChain", "exitEditDownload", "checkOnlineNum", "item", "goMoviePlayer", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "purgeDownloadFileRecord", "deleteFiles", "clearAllDownloads", "onFragmentResume", "onDownloadComplete", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/DownloadCompleteEvent;", "onFileDelete", "Lcom/movieboxpro/android/event/FileDeleteEvent;", "getDownloadingNum", "getDeviceSize", "findDevice", "", FileDownloadService.PARAMS_KEY_PATH, "getDownloadFile", "initData", "initDownloadFileFlow", "setDownloadFilesView", "downloadFiles", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDownloadNumView", "onStart", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadFragment extends BaseLazyFragment {
    private CommonBaseAdapter<DownloadFile> adapter;
    private FragmentDownloadBinding binding;
    private boolean edit;
    private View emptyView;
    private FocusBorder focusBorder;
    private KProgressHUD hud;
    private final float scale = 1.05f;
    private int selectCount;
    private long selectSize;
    private CommonBaseAdapter<ExternalStorage> storageAdapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onFileDelete(FileDeleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
    }

    private final void setEdit(boolean z) {
        this.edit = z;
        FragmentDownloadBinding fragmentDownloadBinding = this.binding;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = null;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        if (this.edit) {
            LinearLayout llEdit = fragmentDownloadBinding.llEdit;
            Intrinsics.checkNotNullExpressionValue(llEdit, "llEdit");
            CommonExtKt.visible(llEdit);
            LinearLayout llHint = fragmentDownloadBinding.llHint;
            Intrinsics.checkNotNullExpressionValue(llHint, "llHint");
            CommonExtKt.gone(llHint);
            LinearLayout llDelete = fragmentDownloadBinding.llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete, "llDelete");
            CommonExtKt.visible(llDelete);
        } else {
            LinearLayout llEdit2 = fragmentDownloadBinding.llEdit;
            Intrinsics.checkNotNullExpressionValue(llEdit2, "llEdit");
            CommonExtKt.gone(llEdit2);
            LinearLayout llHint2 = fragmentDownloadBinding.llHint;
            Intrinsics.checkNotNullExpressionValue(llHint2, "llHint");
            CommonExtKt.visible(llHint2);
            LinearLayout llDelete2 = fragmentDownloadBinding.llDelete;
            Intrinsics.checkNotNullExpressionValue(llDelete2, "llDelete");
            CommonExtKt.gone(llDelete2);
        }
        if (this.selectCount > 1) {
            fragmentDownloadBinding.tvDelete.setText("Delete " + this.selectCount + " videos (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        } else {
            fragmentDownloadBinding.tvDelete.setText("Delete " + this.selectCount + " video (" + FileUtils.byte2FitMemorySize(this.selectSize) + ")");
        }
        updateTopActionFocusChain();
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = this.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter2;
        }
        commonBaseAdapter.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentDownloadBinding fragmentDownloadBindingInflate = FragmentDownloadBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentDownloadBindingInflate, "inflate(...)");
        this.binding = fragmentDownloadBindingInflate;
        if (fragmentDownloadBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBindingInflate = null;
        }
        return fragmentDownloadBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void initView() {
        this.hud = KProgressHUD.create(requireContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f);
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.focusBorder = focusBorderBuild;
    }

    public final void showLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                return;
            }
        }
        KProgressHUD kProgressHUD2 = this.hud;
        if (kProgressHUD2 != null) {
            Intrinsics.checkNotNull(kProgressHUD2);
            if (kProgressHUD2.isShowing()) {
                return;
            }
        }
        this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
    }

    public final void hideLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                KProgressHUD kProgressHUD2 = this.hud;
                Intrinsics.checkNotNull(kProgressHUD2);
                kProgressHUD2.dismiss();
            }
        }
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

    private final void initListener() {
        FragmentDownloadBinding fragmentDownloadBinding = this.binding;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = null;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        fragmentDownloadBinding.llClearAll.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.initListener$lambda$2(this.f$0, view);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding2 = this.binding;
        if (fragmentDownloadBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding2 = null;
        }
        fragmentDownloadBinding2.llSmartDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.initListener$lambda$3(this.f$0, view);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding3 = this.binding;
        if (fragmentDownloadBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding3 = null;
        }
        fragmentDownloadBinding3.recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                DownloadFragment.initListener$lambda$4(this.f$0, view, z);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding4 = this.binding;
        if (fragmentDownloadBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding4 = null;
        }
        fragmentDownloadBinding4.recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initListener.4
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                CommonBaseAdapter commonBaseAdapter2 = DownloadFragment.this.adapter;
                if (commonBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter2 = null;
                }
                View viewByPosition = commonBaseAdapter2.getViewByPosition(position, R.id.tvTitle);
                if (viewByPosition != null) {
                    CommonExtKt.invisible(viewByPosition);
                }
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                DownloadFragment.this.focusScale(itemView, 1.15f);
                CommonBaseAdapter commonBaseAdapter2 = DownloadFragment.this.adapter;
                if (commonBaseAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commonBaseAdapter2 = null;
                }
                View viewByPosition = commonBaseAdapter2.getViewByPosition(position, R.id.tvTitle);
                if (viewByPosition != null) {
                    CommonExtKt.visible(viewByPosition);
                }
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding5 = this.binding;
        if (fragmentDownloadBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding5 = null;
        }
        fragmentDownloadBinding5.llDownloading.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DownloadFragment.initListener$lambda$5(view, i, keyEvent);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding6 = this.binding;
        if (fragmentDownloadBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding6 = null;
        }
        fragmentDownloadBinding6.llSmartDownload.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DownloadFragment.initListener$lambda$6(view, i, keyEvent);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding7 = this.binding;
        if (fragmentDownloadBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding7 = null;
        }
        fragmentDownloadBinding7.llEdit.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DownloadFragment.initListener$lambda$7(view, i, keyEvent);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding8 = this.binding;
        if (fragmentDownloadBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding8 = null;
        }
        fragmentDownloadBinding8.flList.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DownloadFragment.initListener$lambda$8(view, i, keyEvent);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding9 = this.binding;
        if (fragmentDownloadBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding9 = null;
        }
        fragmentDownloadBinding9.llDelete.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return DownloadFragment.initListener$lambda$9(view, i, keyEvent);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding10 = this.binding;
        if (fragmentDownloadBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding10 = null;
        }
        fragmentDownloadBinding10.llDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.deleteFiles();
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding11 = this.binding;
        if (fragmentDownloadBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding11 = null;
        }
        fragmentDownloadBinding11.llDownloading.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.initListener$lambda$11(this.f$0, view);
            }
        });
        FragmentDownloadBinding fragmentDownloadBinding12 = this.binding;
        if (fragmentDownloadBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding12 = null;
        }
        fragmentDownloadBinding12.llEdit.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFragment.initListener$lambda$12(this.f$0, view);
            }
        });
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = this.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter2 = null;
        }
        commonBaseAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda9
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DownloadFragment.initListener$lambda$14(this.f$0, baseQuickAdapter, view, i);
            }
        });
        CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter3;
        }
        commonBaseAdapter.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda10
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return DownloadFragment.initListener$lambda$16(this.f$0, baseQuickAdapter, view, i);
            }
        });
        updateTopActionFocusChain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(final DownloadFragment downloadFragment, View view) {
        new MsgHintDialog.Builder(downloadFragment.getContext()).setContent("Are you sure you want to clear all downloads? This will remove all tasks and downloaded files from this device.").setPositiveText("Clear all").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda30
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                this.f$0.clearAllDownloads();
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(DownloadFragment downloadFragment, View view) {
        ((DialogFragment) SmartDownloadSettingDialog.class.newInstance()).show(downloadFragment.getChildFragmentManager(), Reflection.getOrCreateKotlinClass(SmartDownloadSettingDialog.class).getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(DownloadFragment downloadFragment, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = downloadFragment.focusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$5(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$6(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$7(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$8(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$9(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11(DownloadFragment downloadFragment, View view) {
        DownloadingActivity.INSTANCE.start(downloadFragment.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(DownloadFragment downloadFragment, View view) {
        downloadFragment.setEdit(!downloadFragment.edit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$14(DownloadFragment downloadFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadFragment.adapter;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        DownloadFile item = commonBaseAdapter.getItem(i);
        if (item != null) {
            if (downloadFragment.edit) {
                item.setCheck(!item.isCheck());
                if (item.isCheck()) {
                    if (item.getBoxType() == 1) {
                        downloadFragment.selectCount++;
                    } else {
                        downloadFragment.selectCount += item.getEpisodeNum();
                    }
                    downloadFragment.selectSize += item.getSize();
                } else {
                    if (item.getBoxType() == 1) {
                        downloadFragment.selectCount--;
                    } else {
                        downloadFragment.selectCount -= item.getEpisodeNum();
                    }
                    downloadFragment.selectSize -= item.getSize();
                }
                if (downloadFragment.selectCount > 1) {
                    FragmentDownloadBinding fragmentDownloadBinding = downloadFragment.binding;
                    if (fragmentDownloadBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDownloadBinding = null;
                    }
                    fragmentDownloadBinding.tvDelete.setText("Delete " + downloadFragment.selectCount + " videos (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
                } else {
                    FragmentDownloadBinding fragmentDownloadBinding2 = downloadFragment.binding;
                    if (fragmentDownloadBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDownloadBinding2 = null;
                    }
                    fragmentDownloadBinding2.tvDelete.setText("Delete " + downloadFragment.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
                }
                CommonBaseAdapter<DownloadFile> commonBaseAdapter3 = downloadFragment.adapter;
                if (commonBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commonBaseAdapter2 = commonBaseAdapter3;
                }
                commonBaseAdapter2.notifyItemChanged(i);
                return;
            }
            if (item.getBoxType() == 1) {
                if (Network.isConnected(downloadFragment.getContext())) {
                    downloadFragment.checkOnlineNum(item);
                    return;
                } else {
                    downloadFragment.goMoviePlayer(item);
                    return;
                }
            }
            DownloadedTvActivity.INSTANCE.start(downloadFragment.getContext(), item.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$16(final DownloadFragment downloadFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (!downloadFragment.edit) {
            downloadFragment.setEdit(true);
            FragmentDownloadBinding fragmentDownloadBinding = downloadFragment.binding;
            if (fragmentDownloadBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDownloadBinding = null;
            }
            fragmentDownloadBinding.recyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda29
                @Override // java.lang.Runnable
                public final void run() {
                    DownloadFragment.initListener$lambda$16$lambda$15(this.f$0);
                }
            }, 300L);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16$lambda$15(DownloadFragment downloadFragment) {
        FragmentDownloadBinding fragmentDownloadBinding = downloadFragment.binding;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        fragmentDownloadBinding.recyclerView.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTopActionFocusChain() {
        FragmentDownloadBinding fragmentDownloadBinding = this.binding;
        FragmentDownloadBinding fragmentDownloadBinding2 = null;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        LinearLayout linearLayout = fragmentDownloadBinding.llSmartDownload;
        FragmentDownloadBinding fragmentDownloadBinding3 = this.binding;
        if (fragmentDownloadBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding3 = null;
        }
        LinearLayout linearLayout2 = fragmentDownloadBinding3.llDownloading;
        FragmentDownloadBinding fragmentDownloadBinding4 = this.binding;
        if (fragmentDownloadBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding4 = null;
        }
        LinearLayout linearLayout3 = fragmentDownloadBinding4.llEdit;
        FragmentDownloadBinding fragmentDownloadBinding5 = this.binding;
        if (fragmentDownloadBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding5 = null;
        }
        LinearLayout linearLayout4 = fragmentDownloadBinding5.llDelete;
        FragmentDownloadBinding fragmentDownloadBinding6 = this.binding;
        if (fragmentDownloadBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDownloadBinding2 = fragmentDownloadBinding6;
        }
        int i = 0;
        List listListOf = CollectionsKt.listOf((Object[]) new LinearLayout[]{linearLayout, linearLayout2, linearLayout3, linearLayout4, fragmentDownloadBinding2.llClearAll});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOf) {
            LinearLayout linearLayout5 = (LinearLayout) obj;
            if (linearLayout5.getVisibility() == 0 && linearLayout5.isFocusable()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        for (Object obj2 : arrayList2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LinearLayout linearLayout6 = (LinearLayout) obj2;
            LinearLayout linearLayout7 = (LinearLayout) CollectionsKt.getOrNull(arrayList2, i - 1);
            int id = linearLayout7 != null ? linearLayout7.getId() : linearLayout6.getId();
            LinearLayout linearLayout8 = (LinearLayout) CollectionsKt.getOrNull(arrayList2, i2);
            int id2 = linearLayout8 != null ? linearLayout8.getId() : linearLayout6.getId();
            linearLayout6.setNextFocusLeftId(id);
            linearLayout6.setNextFocusRightId(id2);
            i = i2;
        }
    }

    public final boolean exitEditDownload() {
        boolean z = this.edit;
        if (!z) {
            return false;
        }
        setEdit(!z);
        return true;
    }

    private final void checkOnlineNum(final DownloadFile item) {
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, item.getId(), SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.checkOnlineNum$lambda$20(this.f$0, item, (String) obj);
            }
        };
        Observable observableCompose = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda34
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadFragment.checkOnlineNum$lambda$21(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.checkOnlineNum$lambda$22(this.f$0, item, (Throwable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.checkOnlineNum$lambda$23(this.f$0, (String) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.checkOnlineNum$lambda$24(this.f$0, (Disposable) obj);
            }
        }, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$21(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource checkOnlineNum$lambda$20(final DownloadFragment downloadFragment, DownloadFile downloadFile, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            FragmentActivity activity = downloadFragment.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda31
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.hideLoading();
                    }
                });
            }
            MovieDetail movieDetail = new MovieDetail();
            movieDetail.id = downloadFile.getId();
            movieDetail.title = downloadFile.getVideoName();
            movieDetail.box_type = downloadFile.getBoxType();
            movieDetail.dateline = downloadFile.getDownloadTime();
            movieDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(downloadFile));
            ScreenManageActivity.INSTANCE.start(downloadFragment, new ArrayList<>(((DeviceModelResponse) baseResponse.getData()).getDevice_list()), 100, baseResponse.getMsg(), movieDetail);
            observableJust = Observable.empty();
        } else {
            downloadFragment.goMoviePlayer(downloadFile);
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$24(DownloadFragment downloadFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$23(DownloadFragment downloadFragment, String str) {
        downloadFragment.hideLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit checkOnlineNum$lambda$22(DownloadFragment downloadFragment, DownloadFile downloadFile, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.hideLoading();
        downloadFragment.goMoviePlayer(downloadFile);
        return Unit.INSTANCE;
    }

    private final void goMoviePlayer(DownloadFile item) {
        if (!item.isHls() && !new File(item.getPath(), item.getFileName()).exists()) {
            ToastUtils.showShort("Downloaded file is not found,please check the download device is available", new Object[0]);
            return;
        }
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.id = item.getId();
        movieDetail.title = item.getVideoName();
        movieDetail.box_type = item.getBoxType();
        movieDetail.imdb_id = item.getImdbId();
        movieDetail.dateline = item.getDownloadTime();
        movieDetail.list = CollectionsKt.arrayListOf(BeanKt.toMediaDownloadFile(item));
        VideoPlayerActivity.start(getContext(), movieDetail, item.getId());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100 && data != null) {
            Serializable serializableExtra = data.getSerializableExtra("detail");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieDetail");
            MovieDetail movieDetail = (MovieDetail) serializableExtra;
            VideoPlayerActivity.start(getContext(), movieDetail, movieDetail.id);
        }
    }

    private final void purgeDownloadFileRecord(DownloadFile item) {
        String path = item.getPath();
        if (path == null) {
            path = "";
        }
        if (item.isHls()) {
            HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
            String downloadId = item.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
            HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
            if (hlsDownloadFindByDownloadId != null) {
                VideoDownloadManager.getInstance().deleteVideoTasks(CollectionsKt.listOf((Object[]) new String[]{hlsDownloadFindByDownloadId.getVideoUrl(), hlsDownloadFindByDownloadId.getAudioUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl()}), true);
                new File(hlsDownloadFindByDownloadId.getM3u8LocalPath()).delete();
                new File(hlsDownloadFindByDownloadId.getPlaybackM3u8LocalPath()).delete();
                App.getDB().hlsDownloadDao().delete(hlsDownloadFindByDownloadId);
            } else {
                DownloadService.sendRemoveDownload(App.getContext(), MyDownloadService.class, item.getDownloadId(), false);
                DownloadUtil.pauseDownload(item.getDownloadId());
            }
        } else if (CommonUtils.isFFmpegDownload(path)) {
            if (item.getBoxType() == 2) {
                path = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH()) + File.separator + "ffmpegDownload" + File.separator + item.getId() + "_" + item.getBoxType();
            }
            FileUtils.deleteDir(new File(path));
            DownloadLiveData.INSTANCE.getInstance().cancelDownload(path, item.getDownloadId());
        } else if (StringsKt.startsWith$default(path, "content:", false, 2, (Object) null)) {
            try {
                DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(App.getContext(), Uri.parse(path));
                if (documentFileFromSingleUri != null) {
                    Boolean.valueOf(documentFileFromSingleUri.delete());
                }
            } catch (Exception unused) {
                Unit unit = Unit.INSTANCE;
            }
        } else {
            String fileName = item.getFileName();
            FileUtils.delete(new File(path, fileName != null ? fileName : ""));
        }
        DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(item.getDownloadId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFiles() {
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        Observable observableJust = Observable.just(commonBaseAdapter.getData());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.deleteFiles$lambda$28(this.f$0, (List) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadFragment.deleteFiles$lambda$29(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.deleteFiles$lambda$30(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.deleteFiles$lambda$31(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.deleteFiles$lambda$32(this.f$0, (Unit) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteFiles$lambda$29(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteFiles$lambda$28(DownloadFragment downloadFragment, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            DownloadFile downloadFile = (DownloadFile) it2.next();
            if (downloadFile.isCheck()) {
                downloadFragment.selectCount--;
                downloadFragment.selectSize -= downloadFile.getSize();
                if (downloadFile.getBoxType() != 2) {
                    downloadFragment.purgeDownloadFileRecord(downloadFile);
                } else {
                    Iterator<T> it3 = DownloadFileHelper.INSTANCE.getInstance().findDownloadedTv(downloadFile.getId()).iterator();
                    while (it3.hasNext()) {
                        downloadFragment.purgeDownloadFileRecord((DownloadFile) it3.next());
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteFiles$lambda$31(DownloadFragment downloadFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteFiles$lambda$32(DownloadFragment downloadFragment, Unit unit) {
        downloadFragment.hideLoading();
        FragmentDownloadBinding fragmentDownloadBinding = null;
        if (downloadFragment.selectCount > 1) {
            FragmentDownloadBinding fragmentDownloadBinding2 = downloadFragment.binding;
            if (fragmentDownloadBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDownloadBinding = fragmentDownloadBinding2;
            }
            fragmentDownloadBinding.tvDelete.setText("Delete " + downloadFragment.selectCount + " videos (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
        } else {
            FragmentDownloadBinding fragmentDownloadBinding3 = downloadFragment.binding;
            if (fragmentDownloadBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDownloadBinding = fragmentDownloadBinding3;
            }
            fragmentDownloadBinding.tvDelete.setText("Delete " + downloadFragment.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
        }
        downloadFragment.getDeviceSize();
        downloadFragment.setEdit(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteFiles$lambda$30(DownloadFragment downloadFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.hideLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearAllDownloads() {
        Object objAs = Observable.fromCallable(new Callable() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda21
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DownloadFragment.clearAllDownloads$lambda$34(this.f$0);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.clearAllDownloads$lambda$35(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.clearAllDownloads$lambda$36(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.clearAllDownloads$lambda$37(this.f$0, (Unit) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearAllDownloads$lambda$34(DownloadFragment downloadFragment) {
        Iterator it = CollectionsKt.toList(DownloadFileHelper.INSTANCE.getInstance().getDownloadFiles()).iterator();
        while (it.hasNext()) {
            downloadFragment.purgeDownloadFileRecord((DownloadFile) it.next());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearAllDownloads$lambda$36(DownloadFragment downloadFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearAllDownloads$lambda$37(DownloadFragment downloadFragment, Unit unit) {
        downloadFragment.hideLoading();
        downloadFragment.selectCount = 0;
        downloadFragment.selectSize = 0L;
        FragmentDownloadBinding fragmentDownloadBinding = downloadFragment.binding;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        fragmentDownloadBinding.tvDelete.setText("Delete " + downloadFragment.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
        downloadFragment.getDeviceSize();
        downloadFragment.setEdit(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearAllDownloads$lambda$35(DownloadFragment downloadFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        downloadFragment.hideLoading();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        getDeviceSize();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onDownloadComplete(DownloadCompleteEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        getDeviceSize();
    }

    private final void getDownloadingNum() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.getDownloadingNum$lambda$38((String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda27
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadFragment.getDownloadingNum$lambda$39(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.getDownloadingNum$lambda$40(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingNum$lambda$39(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadingNum$lambda$38(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return DownloadFileHelper.INSTANCE.getInstance().getDownloadingFiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadingNum$lambda$40(DownloadFragment downloadFragment, List list) {
        String str;
        FragmentDownloadBinding fragmentDownloadBinding = null;
        if (list.size() > 0) {
            FragmentDownloadBinding fragmentDownloadBinding2 = downloadFragment.binding;
            if (fragmentDownloadBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDownloadBinding2 = null;
            }
            LinearLayout llDownloading = fragmentDownloadBinding2.llDownloading;
            Intrinsics.checkNotNullExpressionValue(llDownloading, "llDownloading");
            CommonExtKt.visible(llDownloading);
            if (list.size() > 1) {
                str = "videos";
            } else {
                str = "video";
            }
            FragmentDownloadBinding fragmentDownloadBinding3 = downloadFragment.binding;
            if (fragmentDownloadBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDownloadBinding = fragmentDownloadBinding3;
            }
            fragmentDownloadBinding.tvDownloading.setText(list.size() + " " + str + " downloading");
        } else {
            FragmentDownloadBinding fragmentDownloadBinding4 = downloadFragment.binding;
            if (fragmentDownloadBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentDownloadBinding = fragmentDownloadBinding4;
            }
            LinearLayout llDownloading2 = fragmentDownloadBinding.llDownloading;
            Intrinsics.checkNotNullExpressionValue(llDownloading2, "llDownloading");
            CommonExtKt.gone(llDownloading2);
        }
        downloadFragment.updateTopActionFocusChain();
        return Unit.INSTANCE;
    }

    private final void getDeviceSize() {
        UsbConnectReceiver.INSTANCE.refreshStorage();
    }

    private final String findDevice(String path) {
        List<ConnectUsbDevice> all = App.getDB().connectUsbDeviceDao().getAll();
        Intrinsics.checkNotNull(all);
        for (ConnectUsbDevice connectUsbDevice : all) {
            if (path != null) {
                String downloadPath = connectUsbDevice.getDownloadPath();
                Intrinsics.checkNotNullExpressionValue(downloadPath, "getDownloadPath(...)");
                if (StringsKt.contains$default((CharSequence) path, (CharSequence) downloadPath, false, 2, (Object) null)) {
                    String productName = connectUsbDevice.getProductName();
                    Intrinsics.checkNotNullExpressionValue(productName, "getProductName(...)");
                    return productName;
                }
            }
        }
        return "Usb Device";
    }

    private final void getDownloadFile() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.getDownloadFile$lambda$45((String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadFragment.getDownloadFile$lambda$46(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.getDownloadFile$lambda$47((ApiException) obj);
            }
        }, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.getDownloadFile$lambda$48(this.f$0, (List) obj);
            }
        }, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadFile$lambda$46(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getDownloadFile$lambda$45(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List<DownloadFile> downloadedFiles = DownloadFileHelper.INSTANCE.getInstance().getDownloadedFiles();
        List<DownloadFile> list = downloadedFiles;
        for (DownloadFile downloadFile : list) {
            if (downloadFile.getBoxType() == 2) {
                downloadFile.setSize(0L);
                List<DownloadFile> listFindEpisodeNum = DownloadFileHelper.INSTANCE.getInstance().findEpisodeNum(downloadFile.getId(), 2, 1);
                downloadFile.setEpisodeNum(listFindEpisodeNum.size());
                Iterator<T> it2 = listFindEpisodeNum.iterator();
                while (it2.hasNext()) {
                    downloadFile.setSize(downloadFile.getSize() + ((DownloadFile) it2.next()).getSize());
                }
            }
        }
        for (DownloadFile downloadFile2 : list) {
            String path = downloadFile2.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            if (!StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null)) {
                UsbConnectReceiver usbConnectReceiver = UsbConnectReceiver.INSTANCE;
                String path2 = downloadFile2.getPath();
                Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                downloadFile2.setDevice("USB-" + usbConnectReceiver.getUsbDisplayName(path2));
            }
        }
        return downloadedFiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadFile$lambda$48(DownloadFragment downloadFragment, List list) {
        FragmentDownloadBinding fragmentDownloadBinding = null;
        if (list.isEmpty()) {
            if (downloadFragment.emptyView == null) {
                FragmentDownloadBinding fragmentDownloadBinding2 = downloadFragment.binding;
                if (fragmentDownloadBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDownloadBinding2 = null;
                }
                ViewStub viewStub = fragmentDownloadBinding2.emptyViewStub.getViewStub();
                downloadFragment.emptyView = viewStub != null ? viewStub.inflate() : null;
            }
            FragmentDownloadBinding fragmentDownloadBinding3 = downloadFragment.binding;
            if (fragmentDownloadBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDownloadBinding3 = null;
            }
            fragmentDownloadBinding3.flList.setFocusable(true);
            View view = downloadFragment.emptyView;
            if (view != null) {
                CommonExtKt.visible(view);
            }
        } else {
            FragmentDownloadBinding fragmentDownloadBinding4 = downloadFragment.binding;
            if (fragmentDownloadBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDownloadBinding4 = null;
            }
            fragmentDownloadBinding4.flList.setFocusable(false);
            FragmentDownloadBinding fragmentDownloadBinding5 = downloadFragment.binding;
            if (fragmentDownloadBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentDownloadBinding5 = null;
            }
            fragmentDownloadBinding5.recyclerView.requestFocus();
            View view2 = downloadFragment.emptyView;
            if (view2 != null) {
                CommonExtKt.gone(view2);
            }
        }
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = downloadFragment.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setList(list);
        downloadFragment.selectCount = 0;
        downloadFragment.selectSize = 0L;
        FragmentDownloadBinding fragmentDownloadBinding6 = downloadFragment.binding;
        if (fragmentDownloadBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDownloadBinding = fragmentDownloadBinding6;
        }
        fragmentDownloadBinding.tvDelete.setText("Delete " + downloadFragment.selectCount + " video (" + FileUtils.byte2FitMemorySize(downloadFragment.selectSize) + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getDownloadFile$lambda$47(ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LogUtils.d("");
        return Unit.INSTANCE;
    }

    private final void initData() {
        CommonBaseAdapter<DownloadFile> commonBaseAdapter = new CommonBaseAdapter<DownloadFile>(R.layout.adapter_downloaded_file, new ArrayList()) { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initData.1
            {
                ArrayList<DownloadFile> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DownloadFile item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.imageView), 8);
                if (item.getBoxType() == 1) {
                    TextView textView = (TextView) helper.getView(R.id.tvDevice);
                    helper.setText(R.id.tvSize, FileUtils.byte2FitMemorySize(item.getSize()));
                    String device = item.getDevice();
                    if (device == null || StringsKt.isBlank(device)) {
                        CommonExtKt.gone(textView);
                    } else {
                        textView.setText(item.getDevice());
                        CommonExtKt.visible(textView);
                    }
                } else {
                    helper.setText(R.id.tvSize, FileUtils.byte2FitMemorySize(item.getSize()) + "(" + item.getEpisodeNum() + ")");
                }
                CheckBox checkBox = (CheckBox) helper.getView(R.id.checkbox);
                if (DownloadFragment.this.edit) {
                    CommonExtKt.visible(checkBox);
                } else {
                    CommonExtKt.gone(checkBox);
                }
                checkBox.setChecked(item.isCheck());
                helper.setText(R.id.tvTitle, item.getVideoName());
            }
        };
        this.adapter = commonBaseAdapter;
        commonBaseAdapter.setDiffCallback(new DiffUtil.ItemCallback<DownloadFile>() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initData.2
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
        FragmentDownloadBinding fragmentDownloadBinding = this.binding;
        FragmentDownloadBinding fragmentDownloadBinding2 = null;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        fragmentDownloadBinding.recyclerView.setSpacingWithMargins(DensityUtils.dp2px(getContext(), 20.0f), DensityUtils.dp2px(getContext(), 20.0f));
        FragmentDownloadBinding fragmentDownloadBinding3 = this.binding;
        if (fragmentDownloadBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding3 = null;
        }
        TvRecyclerView tvRecyclerView = fragmentDownloadBinding3.recyclerView;
        CommonBaseAdapter<DownloadFile> commonBaseAdapter2 = this.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter2 = null;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter2);
        this.storageAdapter = new CommonBaseAdapter<ExternalStorage>(R.layout.adapter_storage_space, new ArrayList()) { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initData.3
            {
                ArrayList<ExternalStorage> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, ExternalStorage item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                if (holder.getAdapterPosition() == 0) {
                    holder.setText(R.id.textView, item.getDesc() + ": " + item.getSpaceDesc());
                    return;
                }
                holder.setText(R.id.textView, "USB-" + item.getDesc() + ": " + item.getSpaceDesc());
            }
        };
        FragmentDownloadBinding fragmentDownloadBinding4 = this.binding;
        if (fragmentDownloadBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding4 = null;
        }
        fragmentDownloadBinding4.rvStorage.setLayoutManager(new LinearLayoutManager(getContext()));
        FragmentDownloadBinding fragmentDownloadBinding5 = this.binding;
        if (fragmentDownloadBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding5 = null;
        }
        RecyclerView recyclerView = fragmentDownloadBinding5.rvStorage;
        CommonBaseAdapter<ExternalStorage> commonBaseAdapter3 = this.storageAdapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageAdapter");
            commonBaseAdapter3 = null;
        }
        recyclerView.setAdapter(commonBaseAdapter3);
        UsbConnectReceiver.INSTANCE.getStorages().observe(getViewLifecycleOwner(), new DownloadFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.initData$lambda$49(this.f$0, (List) obj);
            }
        }));
        SmartDownloadChangedLiveData.INSTANCE.get().observeInFragment(this, new DownloadFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadFragment.initData$lambda$50(this.f$0, (Boolean) obj);
            }
        }));
        FragmentDownloadBinding fragmentDownloadBinding6 = this.binding;
        if (fragmentDownloadBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentDownloadBinding2 = fragmentDownloadBinding6;
        }
        fragmentDownloadBinding2.tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
        initDownloadFileFlow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$49(DownloadFragment downloadFragment, List list) {
        CommonBaseAdapter<ExternalStorage> commonBaseAdapter = downloadFragment.storageAdapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storageAdapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setList(list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$50(DownloadFragment downloadFragment, Boolean bool) {
        FragmentDownloadBinding fragmentDownloadBinding = downloadFragment.binding;
        if (fragmentDownloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentDownloadBinding = null;
        }
        fragmentDownloadBinding.tvSmartDownload.setText(SettingManager.INSTANCE.getSmartDownload() ? "ON" : "OFF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.DownloadFragment$initDownloadFileFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.DownloadFragment$initDownloadFileFlow$1", f = "DownloadFragment.kt", i = {}, l = {741}, m = "invokeSuspend", n = {}, s = {})
    static final class C01891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01891(Continuation<? super C01891> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadFragment.this.new C01891(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<DownloadFile>> downloadedFilesFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadedFilesFlow();
                final DownloadFragment downloadFragment = DownloadFragment.this;
                this.label = 1;
                if (downloadedFilesFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initDownloadFileFlow.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends DownloadFile>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) throws Throwable {
                        Object downloadFilesView = downloadFragment.setDownloadFilesView(list, continuation);
                        return downloadFilesView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? downloadFilesView : Unit.INSTANCE;
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
        DownloadFragment downloadFragment = this;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(downloadFragment), Dispatchers.getDefault(), null, new C01891(null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(downloadFragment), null, null, new C01902(null), 3, null);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.DownloadFragment$initDownloadFileFlow$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.DownloadFragment$initDownloadFileFlow$2", f = "DownloadFragment.kt", i = {}, l = {746}, m = "invokeSuspend", n = {}, s = {})
    static final class C01902 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01902(Continuation<? super C01902> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadFragment.this.new C01902(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01902) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<List<DownloadFile>> downloadingFilesFlow = DownloadFileHelper.INSTANCE.getInstance().getDownloadingFilesFlow();
                final DownloadFragment downloadFragment = DownloadFragment.this;
                this.label = 1;
                if (downloadingFilesFlow.collect(new FlowCollector() { // from class: com.movieboxpro.android.view.fragment.DownloadFragment.initDownloadFileFlow.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends DownloadFile>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) throws Throwable {
                        Object downloadNumView = downloadFragment.setDownloadNumView(list, continuation);
                        return downloadNumView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? downloadNumView : Unit.INSTANCE;
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setDownloadFilesView(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) throws Throwable {
        List<? extends DownloadFile> list2 = list;
        for (DownloadFile downloadFile : list2) {
            if (downloadFile.getBoxType() == 2) {
                downloadFile.setSize(0L);
                List<DownloadFile> listFindEpisodeNum = DownloadFileHelper.INSTANCE.getInstance().findEpisodeNum(downloadFile.getId(), 2, 1);
                downloadFile.setEpisodeNum(listFindEpisodeNum.size());
                Iterator<T> it = listFindEpisodeNum.iterator();
                while (it.hasNext()) {
                    downloadFile.setSize(downloadFile.getSize() + ((DownloadFile) it.next()).getSize());
                }
            }
        }
        for (DownloadFile downloadFile2 : list2) {
            String path = downloadFile2.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            if (!StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null) && !downloadFile2.isHls()) {
                UsbConnectReceiver usbConnectReceiver = UsbConnectReceiver.INSTANCE;
                String path2 = downloadFile2.getPath();
                Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                downloadFile2.setDevice("USB-" + usbConnectReceiver.getUsbDisplayName(path2));
            }
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C01914(list, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.DownloadFragment$setDownloadFilesView$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.DownloadFragment$setDownloadFilesView$4", f = "DownloadFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01914 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DownloadFile> $downloadFiles;
        int label;
        final /* synthetic */ DownloadFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01914(List<? extends DownloadFile> list, DownloadFragment downloadFragment, Continuation<? super C01914> continuation) {
            super(2, continuation);
            this.$downloadFiles = list;
            this.this$0 = downloadFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01914(this.$downloadFiles, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01914) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FragmentDownloadBinding fragmentDownloadBinding = null;
            if (this.$downloadFiles.isEmpty()) {
                if (this.this$0.emptyView == null) {
                    DownloadFragment downloadFragment = this.this$0;
                    FragmentDownloadBinding fragmentDownloadBinding2 = downloadFragment.binding;
                    if (fragmentDownloadBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentDownloadBinding2 = null;
                    }
                    ViewStub viewStub = fragmentDownloadBinding2.emptyViewStub.getViewStub();
                    downloadFragment.emptyView = viewStub != null ? viewStub.inflate() : null;
                }
                FragmentDownloadBinding fragmentDownloadBinding3 = this.this$0.binding;
                if (fragmentDownloadBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDownloadBinding3 = null;
                }
                fragmentDownloadBinding3.flList.setFocusable(true);
                View view = this.this$0.emptyView;
                if (view != null) {
                    CommonExtKt.visible(view);
                }
            } else {
                FragmentDownloadBinding fragmentDownloadBinding4 = this.this$0.binding;
                if (fragmentDownloadBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDownloadBinding4 = null;
                }
                fragmentDownloadBinding4.flList.setFocusable(false);
                View view2 = this.this$0.emptyView;
                if (view2 != null) {
                    CommonExtKt.gone(view2);
                }
            }
            CommonBaseAdapter commonBaseAdapter = this.this$0.adapter;
            if (commonBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commonBaseAdapter = null;
            }
            commonBaseAdapter.setList(this.$downloadFiles);
            this.this$0.selectCount = 0;
            this.this$0.selectSize = 0L;
            if (this.this$0.selectCount > 1) {
                FragmentDownloadBinding fragmentDownloadBinding5 = this.this$0.binding;
                if (fragmentDownloadBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDownloadBinding = fragmentDownloadBinding5;
                }
                fragmentDownloadBinding.tvDelete.setText("Delete " + this.this$0.selectCount + " videos (" + FileUtils.byte2FitMemorySize(this.this$0.selectSize) + ")");
            } else {
                FragmentDownloadBinding fragmentDownloadBinding6 = this.this$0.binding;
                if (fragmentDownloadBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDownloadBinding = fragmentDownloadBinding6;
                }
                fragmentDownloadBinding.tvDelete.setText("Delete " + this.this$0.selectCount + " video (" + FileUtils.byte2FitMemorySize(this.this$0.selectSize) + ")");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.DownloadFragment$setDownloadNumView$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.DownloadFragment$setDownloadNumView$2", f = "DownloadFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C01922 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DownloadFile> $downloadFiles;
        int label;
        final /* synthetic */ DownloadFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C01922(List<? extends DownloadFile> list, DownloadFragment downloadFragment, Continuation<? super C01922> continuation) {
            super(2, continuation);
            this.$downloadFiles = list;
            this.this$0 = downloadFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01922(this.$downloadFiles, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01922) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FragmentDownloadBinding fragmentDownloadBinding = null;
            if (this.$downloadFiles.size() > 0) {
                FragmentDownloadBinding fragmentDownloadBinding2 = this.this$0.binding;
                if (fragmentDownloadBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentDownloadBinding2 = null;
                }
                LinearLayout llDownloading = fragmentDownloadBinding2.llDownloading;
                Intrinsics.checkNotNullExpressionValue(llDownloading, "llDownloading");
                CommonExtKt.visible(llDownloading);
                if (this.$downloadFiles.size() > 1) {
                    str = "videos";
                } else {
                    str = "video";
                }
                FragmentDownloadBinding fragmentDownloadBinding3 = this.this$0.binding;
                if (fragmentDownloadBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDownloadBinding = fragmentDownloadBinding3;
                }
                fragmentDownloadBinding.tvDownloading.setText(this.$downloadFiles.size() + " " + str + " downloading");
            } else {
                FragmentDownloadBinding fragmentDownloadBinding4 = this.this$0.binding;
                if (fragmentDownloadBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentDownloadBinding = fragmentDownloadBinding4;
                }
                LinearLayout llDownloading2 = fragmentDownloadBinding.llDownloading;
                Intrinsics.checkNotNullExpressionValue(llDownloading2, "llDownloading");
                CommonExtKt.gone(llDownloading2);
            }
            this.this$0.updateTopActionFocusChain();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setDownloadNumView(List<? extends DownloadFile> list, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C01922(list, this, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
