package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.player.MediaQualityInfo;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentVideoQualityBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: VideoQualityDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001c\u001dB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0002J\u001a\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\b\u0010\u0018\u001a\u00020\fH\u0002J\u001a\u0010\u0019\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentVideoQualityBinding;", "<init>", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "callback", "Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$SwitchDefinitionCallback;", "onStart", "", "setCallback", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "requestUrls", "videoData", "Lcom/movieboxpro/android/model/BaseMediaModel;", "mediaQualityInfo", "initData", "setViewVisibility", "visible", "", "Companion", "SwitchDefinitionCallback", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VideoQualityDialogFragment extends BaseTransparentDialogFragment<FragmentVideoQualityBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> adapter;
    private SwitchDefinitionCallback callback;

    /* JADX INFO: compiled from: VideoQualityDialogFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J$\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\nH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$SwitchDefinitionCallback;", "", "onSwitchDefinition", "", "qualityInfo", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "position", "", "onOnlineQuality", "list", "", "files", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "onShowOrg", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SwitchDefinitionCallback {
        void onOnlineQuality(List<MediaQualityInfo> list, List<? extends BaseMediaModel.DownloadFile> files);

        void onShowOrg();

        void onSwitchDefinition(MediaQualityInfo qualityInfo, int position);
    }

    public VideoQualityDialogFragment() {
        super(R.layout.fragment_video_quality);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.5d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    public final void setCallback(SwitchDefinitionCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private final void initListener() {
        BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                VideoQualityDialogFragment.initListener$lambda$2(this.f$0, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(VideoQualityDialogFragment videoQualityDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SwitchDefinitionCallback switchDefinitionCallback;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> baseQuickAdapter2 = videoQualityDialogFragment.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter2 = null;
        }
        MediaQualityInfo item = baseQuickAdapter2.getItem(i);
        if (item == null || (switchDefinitionCallback = videoQualityDialogFragment.callback) == null) {
            return;
        }
        switchDefinitionCallback.onSwitchDefinition(item, i);
    }

    private final void requestUrls(BaseMediaModel videoData, final MediaQualityInfo mediaQualityInfo) {
        String str;
        String str2;
        Observable observableCompose;
        if (videoData != null) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            if (videoData.box_type == 1) {
                observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", videoData.id, "", str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            } else {
                observableCompose = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", videoData.id, String.valueOf(videoData.season), String.valueOf(videoData.episode), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            }
            Object objAs = observableCompose.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VideoQualityDialogFragment.requestUrls$lambda$7$lambda$3(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VideoQualityDialogFragment.requestUrls$lambda$7$lambda$4(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VideoQualityDialogFragment.requestUrls$lambda$7$lambda$6(this.f$0, mediaQualityInfo, (BaseMediaModel) obj);
                }
            }, 10, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrls$lambda$7$lambda$4(VideoQualityDialogFragment videoQualityDialogFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = videoQualityDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrls$lambda$7$lambda$6(VideoQualityDialogFragment videoQualityDialogFragment, MediaQualityInfo mediaQualityInfo, BaseMediaModel baseMediaModel) {
        ProgressBar progressBar = videoQualityDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(mediaQualityInfo);
        List<BaseMediaModel.DownloadFile> list = baseMediaModel.list;
        if (list != null) {
            Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object next = it.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) next;
                if (Intrinsics.areEqual(downloadFile.type, "video")) {
                    arrayList.add(new MediaQualityInfo(i, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime4(downloadFile.dateline * ((long) 1000)), downloadFile.count, String.valueOf(downloadFile.vip_only), downloadFile.filename, false, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.hls, downloadFile.mmfid, downloadFile.tmfid));
                }
                it = it;
                i = i2;
            }
        }
        SwitchDefinitionCallback switchDefinitionCallback = videoQualityDialogFragment.callback;
        if (switchDefinitionCallback != null) {
            List<BaseMediaModel.DownloadFile> list2 = baseMediaModel.list;
            Intrinsics.checkNotNullExpressionValue(list2, "list");
            switchDefinitionCallback.onOnlineQuality(arrayList, list2);
        }
        BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> baseQuickAdapter = videoQualityDialogFragment.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(arrayList);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrls$lambda$7$lambda$3(VideoQualityDialogFragment videoQualityDialogFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ProgressBar progressBar = videoQualityDialogFragment.getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        ToastUtils.showShort("Load failed", new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initData() {
        Bundle arguments = getArguments();
        BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> baseQuickAdapter = null;
        ArrayList parcelableArrayList = arguments != null ? arguments.getParcelableArrayList("list") : null;
        Intrinsics.checkNotNull(parcelableArrayList, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.player.MediaQualityInfo>");
        Bundle arguments2 = getArguments();
        Serializable serializable = arguments2 != null ? arguments2.getSerializable("data") : null;
        BaseMediaModel baseMediaModel = serializable instanceof BaseMediaModel ? (BaseMediaModel) serializable : null;
        ArrayList<MediaQualityInfo> arrayList = parcelableArrayList;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((MediaQualityInfo) it.next()).setSelect(false);
        }
        Bundle arguments3 = getArguments();
        int i = arguments3 != null ? arguments3.getInt("pos", 0) : 0;
        if (CommonExtKt.checkIndexLegal(i, parcelableArrayList)) {
            ((MediaQualityInfo) parcelableArrayList.get(i)).setSelect(true);
        }
        if (parcelableArrayList.size() == 1) {
            String path = ((MediaQualityInfo) parcelableArrayList.get(0)).getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            if (!StringsKt.startsWith$default(path, IDataSource.SCHEME_HTTP_TAG, false, 2, (Object) null)) {
                MediaQualityInfo mediaQualityInfo = (MediaQualityInfo) parcelableArrayList.get(0);
                String path2 = ((MediaQualityInfo) parcelableArrayList.get(0)).getPath();
                mediaQualityInfo.setSize(FileUtils.byte2FitMemorySize2(new File(path2 != null ? path2 : "").length()));
                ((MediaQualityInfo) parcelableArrayList.get(0)).setLocalFile(true);
                if (Network.isConnected(getContext())) {
                    Object obj = parcelableArrayList.get(0);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    requestUrls(baseMediaModel, (MediaQualityInfo) obj);
                }
            }
        } else {
            for (MediaQualityInfo mediaQualityInfo2 : arrayList) {
                String path3 = mediaQualityInfo2.getPath();
                Intrinsics.checkNotNullExpressionValue(path3, "getPath(...)");
                if (!StringsKt.startsWith$default(path3, IDataSource.SCHEME_HTTP_TAG, false, 2, (Object) null) && !TextUtils.isEmpty(mediaQualityInfo2.getPath())) {
                    String path4 = mediaQualityInfo2.getPath();
                    if (path4 == null) {
                        path4 = "";
                    }
                    mediaQualityInfo2.setSize(FileUtils.byte2FitMemorySize2(new File(path4).length()));
                    mediaQualityInfo2.setLocalFile(true);
                }
            }
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Bundle arguments4 = getArguments();
        booleanRef.element = arguments4 != null ? arguments4.getBoolean("showOrg", false) : false;
        this.adapter = new AnonymousClass3(parcelableArrayList, booleanRef, this, R.layout.adapter_definition_item);
        TvRecyclerView recyclerView = getBinding().recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.disableRefreshAnimation(recyclerView);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> baseQuickAdapter2 = this.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$initData$decoration$1
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public boolean needDrawDecoration(int position) {
                return true;
            }

            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int position) {
                BaseQuickAdapter baseQuickAdapter3 = this.this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                MediaQualityInfo mediaQualityInfo3 = (MediaQualityInfo) baseQuickAdapter3.getItemOrNull(position);
                if (mediaQualityInfo3 != null && mediaQualityInfo3.isLocalFile()) {
                    return "Downloaded on " + mediaQualityInfo3.getTime();
                }
                if (mediaQualityInfo3 != null) {
                    String title = mediaQualityInfo3.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    return title;
                }
                return "";
            }

            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int position) {
                View viewInflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.video_quality_title, (ViewGroup) null, false);
                BaseQuickAdapter baseQuickAdapter3 = this.this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                MediaQualityInfo mediaQualityInfo3 = (MediaQualityInfo) baseQuickAdapter3.getItemOrNull(position);
                TextView textView = (TextView) viewInflate.findViewById(R.id.tvTime);
                if (mediaQualityInfo3 != null && mediaQualityInfo3.isLocalFile()) {
                    Intrinsics.checkNotNull(textView);
                    CommonExtKt.gone(textView);
                    ((TextView) viewInflate.findViewById(R.id.tvName)).setText("Downloaded File");
                } else {
                    TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvName);
                    BaseQuickAdapter baseQuickAdapter4 = this.this$0.adapter;
                    if (baseQuickAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        baseQuickAdapter4 = null;
                    }
                    MediaQualityInfo mediaQualityInfo4 = (MediaQualityInfo) baseQuickAdapter4.getItemOrNull(position);
                    textView2.setText(mediaQualityInfo4 != null ? mediaQualityInfo4.getTitle() : null);
                    Intrinsics.checkNotNull(textView);
                    CommonExtKt.visible(textView);
                    BaseQuickAdapter baseQuickAdapter5 = this.this$0.adapter;
                    if (baseQuickAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        baseQuickAdapter5 = null;
                    }
                    MediaQualityInfo mediaQualityInfo5 = (MediaQualityInfo) baseQuickAdapter5.getItemOrNull(position);
                    textView.setText(mediaQualityInfo5 != null ? mediaQualityInfo5.getTime() : null);
                }
                Intrinsics.checkNotNull(viewInflate);
                return viewInflate;
            }
        }).setGroupBackground(CommonExtKt.colorInt(R.color.color_main_back)).setDivideHeight(0).setGroupHeight((int) ScreenUtils.dpToPx(70.0f)).setCacheEnable(true).setSticky(false).build();
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 1);
        if (powerfulStickyDecorationBuild != null) {
            powerfulStickyDecorationBuild.resetSpan(getBinding().recyclerView, v7GridLayoutManager);
        }
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView2 = getBinding().recyclerView;
        Intrinsics.checkNotNull(powerfulStickyDecorationBuild);
        tvRecyclerView2.addItemDecoration(powerfulStickyDecorationBuild);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$initData$3, reason: invalid class name */
    /* JADX INFO: compiled from: VideoQualityDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0014¨\u0006\b"}, d2 = {"com/movieboxpro/android/view/dialog/VideoQualityDialogFragment$initData$3", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "convert", "", "holder", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass3 extends BaseQuickAdapter<MediaQualityInfo, BaseViewHolder> {
        final /* synthetic */ Ref.BooleanRef $showOrg;
        final /* synthetic */ VideoQualityDialogFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ArrayList<MediaQualityInfo> arrayList, Ref.BooleanRef booleanRef, VideoQualityDialogFragment videoQualityDialogFragment, int i) {
            super(i, arrayList);
            this.$showOrg = booleanRef;
            this.this$0 = videoQualityDialogFragment;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:47:0x00f0  */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(BaseViewHolder holder, MediaQualityInfo item) {
            int i;
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(item, "item");
            LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.constraintLayout);
            if (item.getOriginal() != 1 || this.$showOrg.element) {
                this.this$0.setViewVisibility(linearLayout, true);
            } else {
                this.this$0.setViewVisibility(linearLayout, false);
            }
            View view = holder.itemView;
            final Ref.BooleanRef booleanRef = this.$showOrg;
            final VideoQualityDialogFragment videoQualityDialogFragment = this.this$0;
            view.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.VideoQualityDialogFragment$initData$3$$ExternalSyntheticLambda0
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view2, int i2, KeyEvent keyEvent) {
                    return VideoQualityDialogFragment.AnonymousClass3.convert$lambda$0(booleanRef, videoQualityDialogFragment, view2, i2, keyEvent);
                }
            });
            ImageView imageView = (ImageView) holder.getView(R.id.ivCode);
            String real_quality = item.getReal_quality();
            Intrinsics.checkNotNullExpressionValue(real_quality, "getReal_quality(...)");
            String lowerCase = real_quality.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (Intrinsics.areEqual(lowerCase, "auto")) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
                if (item.getH265() == 1) {
                    imageView.setImageResource(com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat);
                } else {
                    imageView.setImageResource(com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat2);
                }
            }
            String real_quality2 = item.getReal_quality();
            Intrinsics.checkNotNullExpressionValue(real_quality2, "getReal_quality(...)");
            String lowerCase2 = real_quality2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            switch (lowerCase2) {
                case "4k":
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_4k;
                    break;
                case "8k":
                    i = R.mipmap.ic_choose_8k;
                    break;
                case "org":
                    i = R.mipmap.ic_origin_rate;
                    break;
                case "360p":
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                    break;
                case "720p":
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_hd;
                    break;
                case "auto":
                    i = R.mipmap.ic_choose_auto;
                    break;
                case "1080p":
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_fullhd;
                    break;
                default:
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                    break;
            }
            ImageView imageView2 = (ImageView) holder.getView(R.id.ivOrg);
            if (item.getOriginal() == 1) {
                CommonExtKt.visible(imageView2);
            } else {
                CommonExtKt.gone(imageView2);
            }
            ((ImageView) holder.getView(R.id.ivDefinition)).setImageResource(i);
            TextView textView = (TextView) holder.getView(R.id.tv_rate);
            LinearLayout linearLayout2 = (LinearLayout) holder.getView(R.id.ll_rate);
            String bitstream = item.getBitstream();
            if (bitstream == null || bitstream.length() == 0) {
                CommonExtKt.gone(textView);
                CommonExtKt.gone(linearLayout2);
            } else {
                CommonExtKt.visible(textView);
                CommonExtKt.visible(linearLayout2);
                textView.setText(item.getBitstream());
            }
            ImageView imageView3 = (ImageView) holder.getView(R.id.ivVip);
            Object isVip = item.getIsVip();
            if (isVip == null) {
                isVip = 0;
            }
            if (Intrinsics.areEqual(isVip, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                CommonExtKt.visible(imageView3);
            } else {
                CommonExtKt.gone(imageView3);
            }
            holder.setText(R.id.tv_size, item.getSize());
            ImageView imageView4 = (ImageView) holder.getView(R.id.ivHdr);
            if (item.getHdr() == 1) {
                CommonExtKt.visible(imageView4);
            } else {
                CommonExtKt.gone(imageView4);
            }
            ImageView imageView5 = (ImageView) holder.getView(R.id.ivColorBit);
            int colorbit = item.getColorbit();
            if (colorbit == 10) {
                CommonExtKt.visible(imageView5);
                imageView5.setImageResource(R.mipmap.ic_color_bit_10);
            } else if (colorbit != 12) {
                CommonExtKt.gone(imageView5);
            } else {
                CommonExtKt.visible(imageView5);
                imageView5.setImageResource(R.mipmap.ic_color_bit_12);
            }
            ImageView imageView6 = (ImageView) holder.getView(R.id.iv_selected);
            if (item.isSelect()) {
                CommonExtKt.visible(imageView6);
            } else {
                CommonExtKt.gone(imageView6);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean convert$lambda$0(Ref.BooleanRef booleanRef, VideoQualityDialogFragment videoQualityDialogFragment, View view, int i, KeyEvent keyEvent) {
            if ((i != 21 && i != 22) || booleanRef.element) {
                return false;
            }
            booleanRef.element = true;
            SwitchDefinitionCallback switchDefinitionCallback = videoQualityDialogFragment.callback;
            if (switchDefinitionCallback != null) {
                switchDefinitionCallback.onShowOrg();
            }
            BaseQuickAdapter baseQuickAdapter = videoQualityDialogFragment.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.notifyDataSetChanged();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewVisibility(View view, boolean visible) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (visible) {
            layoutParams2.height = DensityUtils.dp2px(60.0f);
            layoutParams2.width = -1;
            CommonExtKt.visible(view);
        } else {
            CommonExtKt.gone(view);
            layoutParams2.height = 0;
            layoutParams2.width = 0;
        }
        view.setLayoutParams(layoutParams2);
    }

    /* JADX INFO: compiled from: VideoQualityDialogFragment.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/VideoQualityDialogFragment;", "list", "", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "pos", "", "movieDownload", "Lcom/movieboxpro/android/model/BaseMediaModel;", "showOrg", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoQualityDialogFragment newInstance(List<MediaQualityInfo> list, int pos, BaseMediaModel movieDownload, boolean showOrg) {
            VideoQualityDialogFragment videoQualityDialogFragment = new VideoQualityDialogFragment();
            Bundle bundle = new Bundle();
            if (list == null) {
                list = new ArrayList<>();
            }
            bundle.putParcelableArrayList("list", new ArrayList<>(list));
            bundle.putSerializable("data", movieDownload);
            bundle.putInt("pos", pos);
            bundle.putBoolean("showOrg", showOrg);
            videoQualityDialogFragment.setArguments(bundle);
            return videoQualityDialogFragment;
        }
    }
}
