package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.ExtrModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.model.OpenSubtitleModel;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCClient;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCServerException;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentOpenSubtitleBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jingbin.web.WebProgress;

/* JADX INFO: compiled from: OpenSubtitleDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u000b\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002*+B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0014\u0010\u001f\u001a\u00020\u00192\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070!J\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010#\u001a\u00020\u0019H\u0016J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0002J\b\u0010'\u001a\u00020\u0019H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentOpenSubtitleBinding;", "<init>", "()V", "languageAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/OpenSubtitleModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "subtitleAdapter", "Lcom/dueeeke/model/ExtrModel;", "token", "", DealWatchNextService.SEASON, "", DealWatchNextService.EPISODE, "imdbId", "videoId", "loading", "Lcom/kaopiz/kprogresshud/KProgressHUD;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialogFragment$OnLoadSubtitleFinishListener;", "opeSubtitles", "", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setOpenSubtitleData", "list", "", "setLoadSubtitleFinishListener", "onDestroy", "signOutOpenSubtitle", "getOpenSubtitleData", "initListener", "initData", "showLoading", "hideLoading", "Companion", "OnLoadSubtitleFinishListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OpenSubtitleDialogFragment extends BaseTransparentDialogFragment<FragmentOpenSubtitleBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int episode;
    private String imdbId;
    private BaseQuickAdapter<OpenSubtitleModel, BaseViewHolder> languageAdapter;
    private OnLoadSubtitleFinishListener listener;
    private KProgressHUD loading;
    private List<? extends OpenSubtitleModel> opeSubtitles;
    private int season;
    private BaseQuickAdapter<ExtrModel, BaseViewHolder> subtitleAdapter;
    private String token;
    private String videoId;

    /* JADX INFO: compiled from: OpenSubtitleDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialogFragment$OnLoadSubtitleFinishListener;", "", "onResultFinish", "", "list", "", "Lcom/movieboxpro/android/model/OpenSubtitleModel;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnLoadSubtitleFinishListener {
        void onResultFinish(List<OpenSubtitleModel> list);
    }

    public OpenSubtitleDialogFragment() {
        super(R.layout.fragment_open_subtitle);
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

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        OpenSubtitleModel openSubtitleModel;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
        List<? extends OpenSubtitleModel> list = this.opeSubtitles;
        if (list == null || list.isEmpty()) {
            getOpenSubtitleData();
            return;
        }
        BaseQuickAdapter<OpenSubtitleModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        List<ExtrModel> subtitls = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(this.opeSubtitles);
        List<? extends OpenSubtitleModel> list2 = this.opeSubtitles;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        BaseQuickAdapter<ExtrModel, BaseViewHolder> baseQuickAdapter2 = this.subtitleAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter2 = null;
        }
        List<? extends OpenSubtitleModel> list3 = this.opeSubtitles;
        if (list3 != null && (openSubtitleModel = list3.get(0)) != null) {
            subtitls = openSubtitleModel.getSubtitls();
        }
        baseQuickAdapter2.setList(subtitls);
    }

    public final void setOpenSubtitleData(List<OpenSubtitleModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.opeSubtitles = list;
    }

    public final void setLoadSubtitleFinishListener(OnLoadSubtitleFinishListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        signOutOpenSubtitle();
        super.onDestroy();
    }

    private final void signOutOpenSubtitle() {
        URL url;
        try {
            url = new URL("https://api.opensubtitles.org/xml-rpc");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 256);
        xMLRPCClient.cancel(xMLRPCClient.callAsync(new XMLRPCCallback() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment$signOutOpenSubtitle$id$1
            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onError(long id, XMLRPCException error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onResponse(long id, Object result) {
                Intrinsics.checkNotNullParameter(result, "result");
            }

            @Override // com.movieboxpro.android.timroes.axmlrpc.XMLRPCCallback
            public void onServerError(long id, XMLRPCServerException error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }
        }, "LogOut", this.token));
    }

    private final void getOpenSubtitleData() {
        Observable observableJust = Observable.just("https://api.opensubtitles.org/xml-rpc");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OpenSubtitleDialogFragment.getOpenSubtitleData$lambda$4(this.f$0, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableJust.map(new Function() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return OpenSubtitleDialogFragment.getOpenSubtitleData$lambda$5(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<List<OpenSubtitleModel>>() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment.getOpenSubtitleData.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                OpenSubtitleDialogFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<OpenSubtitleModel> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                BaseQuickAdapter baseQuickAdapter = OpenSubtitleDialogFragment.this.languageAdapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                    baseQuickAdapter = null;
                }
                List<OpenSubtitleModel> list = model;
                baseQuickAdapter.setList(list);
                if (!list.isEmpty()) {
                    OnLoadSubtitleFinishListener onLoadSubtitleFinishListener = OpenSubtitleDialogFragment.this.listener;
                    if (onLoadSubtitleFinishListener != null) {
                        onLoadSubtitleFinishListener.onResultFinish(model);
                    }
                    BaseQuickAdapter baseQuickAdapter3 = OpenSubtitleDialogFragment.this.subtitleAdapter;
                    if (baseQuickAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                    } else {
                        baseQuickAdapter2 = baseQuickAdapter3;
                    }
                    baseQuickAdapter2.setList(model.get(0).getSubtitls());
                } else {
                    OpenSubtitleDialogFragment.this.dismiss();
                    ToastUtils.showShort("no subtitle", new Object[0]);
                }
                OpenSubtitleDialogFragment.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                OpenSubtitleDialogFragment.this.hideLoading();
                ToastUtils.showShort("load error:" + (e != null ? e.getMessage() : null), new Object[0]);
                OpenSubtitleDialogFragment.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getOpenSubtitleData$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getOpenSubtitleData$lambda$4(OpenSubtitleDialogFragment openSubtitleDialogFragment, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        URL url = new URL(it);
        XMLRPCClient xMLRPCClient = new XMLRPCClient(url, 256);
        String stringConfig = ConfigUtils.readStringConfig(ConfigKey.OPENSUBTITLE_AGENT);
        String str = stringConfig;
        if (str == null || StringsKt.isBlank(str)) {
            stringConfig = "nPlayer v3";
        }
        JSONObject object = JSONObject.parseObject(xMLRPCClient.call("LogIn", "", "", "cn", stringConfig).toString());
        ArrayList<ExtrModel> arrayList = new ArrayList();
        if (object.containsKey("token")) {
            openSubtitleDialogFragment.token = object.getString("token");
            XMLRPCClient xMLRPCClient2 = new XMLRPCClient(url, 256);
            JSONObject jSONObject = new JSONObject();
            String str2 = openSubtitleDialogFragment.imdbId;
            if (str2 == null || str2.length() == 0) {
                openSubtitleDialogFragment.imdbId = "tt";
            }
            JSONObject jSONObject2 = jSONObject;
            String str3 = openSubtitleDialogFragment.imdbId;
            jSONObject2.put("imdbid", str3 != null ? StringsKt.replace$default(str3, "tt", "", false, 4, (Object) null) : null);
            jSONObject2.put("sublanguageid", Locale.getDefault().getLanguage());
            int i = openSubtitleDialogFragment.season;
            if (i != 0) {
                jSONObject2.put(DealWatchNextService.SEASON, Integer.valueOf(i));
                jSONObject2.put(DealWatchNextService.EPISODE, Integer.valueOf(openSubtitleDialogFragment.episode));
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.add(jSONObject);
            new JSONObject().put("limit", Integer.valueOf(WebProgress.DO_END_PROGRESS_DURATION));
            JSONObject object2 = JSONObject.parseObject(xMLRPCClient2.call("SearchSubtitles", openSubtitleDialogFragment.token, jSONArray, jSONObject).toString());
            if (object2.containsKey("data")) {
                List array = JSONObject.parseArray(object2.getString("data"), ExtrModel.class);
                Intrinsics.checkNotNullExpressionValue(array, "parseArray(...)");
                arrayList.addAll(array);
            }
        }
        HashMap map = new HashMap();
        for (ExtrModel extrModel : arrayList) {
            if (map.containsKey(extrModel.getLanguageName())) {
                List list = (List) map.get(extrModel.getLanguageName());
                if (list != null) {
                    list.add(extrModel);
                }
            } else {
                map.put(extrModel.getLanguageName(), CollectionsKt.arrayListOf(extrModel));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            OpenSubtitleModel openSubtitleModel = new OpenSubtitleModel();
            openSubtitleModel.setLanguage((String) entry.getKey());
            openSubtitleModel.setSubtitls((List) entry.getValue());
            arrayList2.add(openSubtitleModel);
        }
        if (arrayList2.size() > 1) {
            CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment$getOpenSubtitleData$lambda$4$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((OpenSubtitleModel) t).getLanguage(), ((OpenSubtitleModel) t2).getLanguage());
                }
            });
        }
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            OpenSubtitleModel openSubtitleModel2 = (OpenSubtitleModel) arrayList2.get(i2);
            if (Intrinsics.areEqual("English", openSubtitleModel2.getLanguage())) {
                arrayList2.remove(i2);
                arrayList2.add(0, openSubtitleModel2);
                break;
            }
        }
        return arrayList2;
    }

    private final void initListener() {
        getBinding().rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment.initListener.1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                BaseQuickAdapter baseQuickAdapter = OpenSubtitleDialogFragment.this.subtitleAdapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                    baseQuickAdapter = null;
                }
                BaseQuickAdapter baseQuickAdapter3 = OpenSubtitleDialogFragment.this.languageAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter.setList(((OpenSubtitleModel) baseQuickAdapter2.getData().get(position)).getSubtitls());
            }
        });
        BaseQuickAdapter<ExtrModel, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                OpenSubtitleDialogFragment.initListener$lambda$7(this.f$0, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(OpenSubtitleDialogFragment openSubtitleDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (openSubtitleDialogFragment.getContext() != null) {
            BaseQuickAdapter<ExtrModel, BaseViewHolder> baseQuickAdapter2 = openSubtitleDialogFragment.subtitleAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter2 = null;
            }
            ExtrModel extrModel = baseQuickAdapter2.getData().get(i);
            PreviewSubtitleDialog.Companion companion = PreviewSubtitleDialog.INSTANCE;
            String subFileName = extrModel.getSubFileName();
            String zipDownloadLink = extrModel.getZipDownloadLink();
            String iso639 = extrModel.getISO639();
            String languageName = extrModel.getLanguageName();
            int i2 = openSubtitleDialogFragment.season;
            companion.newInstance(extrModel, subFileName, zipDownloadLink, iso639, languageName, (i2 == 0 && openSubtitleDialogFragment.episode == 0) ? 1 : 2, openSubtitleDialogFragment.videoId, i2, openSubtitleDialogFragment.episode).show(openSubtitleDialogFragment.getChildFragmentManager(), "PreviewSubtitleDialog");
        }
    }

    private final void initData() {
        this.languageAdapter = new BaseQuickAdapter<OpenSubtitleModel, BaseViewHolder>(R.layout.adapter_language_item) { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment.initData.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, OpenSubtitleModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ((TextView) helper.getView(R.id.tvLanguage)).setText(item.getLanguage());
            }
        };
        TvRecyclerView tvRecyclerView = getBinding().rvLanguage;
        BaseQuickAdapter<OpenSubtitleModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().rvLanguage.setSelectedItemAtCentered(true);
        this.subtitleAdapter = new BaseQuickAdapter<ExtrModel, BaseViewHolder>(R.layout.adapter_open_subtitle_item) { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialogFragment.initData.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, ExtrModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(R.id.tvSubtitleName);
                TextView textView2 = (TextView) helper.getView(R.id.tvTime);
                textView.setText(item.getSubFileName());
                textView2.setText(item.getSubAddDate());
            }
        };
        TvRecyclerView tvRecyclerView2 = getBinding().rvSubtitle;
        BaseQuickAdapter<ExtrModel, BaseViewHolder> baseQuickAdapter2 = this.subtitleAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter2);
        getBinding().rvSubtitle.setSelectedItemAtCentered(true);
        Bundle arguments = getArguments();
        this.imdbId = arguments != null ? arguments.getString("id") : null;
        Bundle arguments2 = getArguments();
        this.season = arguments2 != null ? arguments2.getInt(DealWatchNextService.SEASON) : 0;
        Bundle arguments3 = getArguments();
        this.episode = arguments3 != null ? arguments3.getInt(DealWatchNextService.EPISODE) : 0;
        Bundle arguments4 = getArguments();
        this.videoId = arguments4 != null ? arguments4.getString("videoId") : null;
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

    /* JADX INFO: compiled from: OpenSubtitleDialogFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialogFragment;", "imdbId", "", DealWatchNextService.SEASON, "", DealWatchNextService.EPISODE, "id", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OpenSubtitleDialogFragment newInstance(String imdbId, int season, int episode, String id) {
            OpenSubtitleDialogFragment openSubtitleDialogFragment = new OpenSubtitleDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", imdbId);
            bundle.putInt(DealWatchNextService.SEASON, season);
            bundle.putInt(DealWatchNextService.EPISODE, episode);
            bundle.putString("videoId", id);
            openSubtitleDialogFragment.setArguments(bundle);
            return openSubtitleDialogFragment;
        }
    }
}
