package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.ExtrModel;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment;
import com.movieboxpro.android.event.OpenSubtitleSelectEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.ResponseUploadExtraSubtitle;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogPreviewSubtitleBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: PreviewSubtitleDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001FB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u00020?H\u0002J\b\u0010E\u001a\u00020?H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u0019\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR+\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0011\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010$\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0011\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R+\u0010(\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u001d8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u0011\u001a\u0004\b)\u0010 \"\u0004\b*\u0010\"R/\u0010,\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u0011\u001a\u0004\b-\u0010\r\"\u0004\b.\u0010\u000fR/\u00100\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010\u0011\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR/\u00104\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b7\u0010\u0011\u001a\u0004\b5\u0010\r\"\u0004\b6\u0010\u000fR/\u00108\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b;\u0010\u0011\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000fR\u000e\u0010<\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PreviewSubtitleDialog;", "Lcom/movieboxpro/android/base/BaseBottomFullScreenDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogPreviewSubtitleBinding;", "<init>", "()V", "subtitleAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/dl7/player/utils/SRT;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "<set-?>", "", "url", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "url$delegate", "Lkotlin/properties/ReadWriteProperty;", "Lcom/dueeeke/model/ExtrModel;", "extrModel", "getExtrModel", "()Lcom/dueeeke/model/ExtrModel;", "setExtrModel", "(Lcom/dueeeke/model/ExtrModel;)V", "extrModel$delegate", "fileName", "getFileName", "setFileName", "fileName$delegate", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", DealWatchNextService.SEASON, "getSeason", "setSeason", "season$delegate", DealWatchNextService.EPISODE, "getEpisode", "setEpisode", "episode$delegate", CmcdConfiguration.KEY_SESSION_ID, "getSid", "setSid", "sid$delegate", "lang", "getLang", "setLang", "lang$delegate", "language", "getLanguage", "setLanguage", "language$delegate", "id", "getId", "setId", "id$delegate", "transcodeFilePath", "subtitleContent", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "transcodeSubtitle", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PreviewSubtitleDialog extends BaseBottomFullScreenDialogFragment<DialogPreviewSubtitleBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "url", "getUrl()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "extrModel", "getExtrModel()Lcom/dueeeke/model/ExtrModel;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "fileName", "getFileName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, DealWatchNextService.SEASON, "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, DealWatchNextService.EPISODE, "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, CmcdConfiguration.KEY_SESSION_ID, "getSid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "lang", "getLang()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "id", "getId()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: boxType$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty boxType;

    /* JADX INFO: renamed from: episode$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty episode;

    /* JADX INFO: renamed from: extrModel$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty extrModel;

    /* JADX INFO: renamed from: fileName$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty fileName;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty id;

    /* JADX INFO: renamed from: lang$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty lang;

    /* JADX INFO: renamed from: language$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty language;

    /* JADX INFO: renamed from: season$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty season;

    /* JADX INFO: renamed from: sid$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty sid;
    private BaseQuickAdapter<SRT, BaseViewHolder> subtitleAdapter;
    private String subtitleContent;
    private String transcodeFilePath;

    /* JADX INFO: renamed from: url$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty url;

    public PreviewSubtitleDialog() {
        super(R.layout.dialog_preview_subtitle);
        PreviewSubtitleDialog previewSubtitleDialog = this;
        this.url = FragmentArgsKt.arg(previewSubtitleDialog);
        this.extrModel = FragmentArgsKt.arg(previewSubtitleDialog);
        this.fileName = FragmentArgsKt.arg(previewSubtitleDialog);
        this.boxType = FragmentArgsKt.arg(previewSubtitleDialog);
        this.season = FragmentArgsKt.arg(previewSubtitleDialog);
        this.episode = FragmentArgsKt.arg(previewSubtitleDialog);
        this.sid = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.lang = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.language = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.id = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.transcodeFilePath = "";
        this.subtitleContent = "";
    }

    private final String getUrl() {
        return (String) this.url.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUrl(String str) {
        this.url.setValue(this, $$delegatedProperties[0], str);
    }

    private final ExtrModel getExtrModel() {
        return (ExtrModel) this.extrModel.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setExtrModel(ExtrModel extrModel) {
        this.extrModel.setValue(this, $$delegatedProperties[1], extrModel);
    }

    private final String getFileName() {
        return (String) this.fileName.getValue(this, $$delegatedProperties[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFileName(String str) {
        this.fileName.setValue(this, $$delegatedProperties[2], str);
    }

    private final int getBoxType() {
        return ((Number) this.boxType.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final int getSeason() {
        return ((Number) this.season.getValue(this, $$delegatedProperties[4])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    private final int getEpisode() {
        return ((Number) this.episode.getValue(this, $$delegatedProperties[5])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpisode(int i) {
        this.episode.setValue(this, $$delegatedProperties[5], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSid() {
        return (String) this.sid.getValue(this, $$delegatedProperties[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSid(String str) {
        this.sid.setValue(this, $$delegatedProperties[6], str);
    }

    private final String getLang() {
        return (String) this.lang.getValue(this, $$delegatedProperties[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLang(String str) {
        this.lang.setValue(this, $$delegatedProperties[7], str);
    }

    private final String getLanguage() {
        return (String) this.language.getValue(this, $$delegatedProperties[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLanguage(String str) {
        this.language.setValue(this, $$delegatedProperties[8], str);
    }

    private final String getId() {
        return (String) this.id.getValue(this, $$delegatedProperties[9]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id.setValue(this, $$delegatedProperties[9], str);
    }

    @Override // com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.subtitleAdapter = new BaseQuickAdapter<SRT, BaseViewHolder>(R.layout.adapter_subtitle_item) { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog.onViewCreated.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRT item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ((TextView) helper.getView(R.id.tvLanguage)).setText(Html.fromHtml(item.getSrtBody()));
            }
        };
        TvRecyclerView tvRecyclerView = getBinding().rvSubtitle;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().rvSubtitle.setSelectedItemAtCentered(true);
        transcodeSubtitle();
        initListener();
    }

    private final void initListener() {
        getBinding().tvTranscode.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewSubtitleDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewSubtitleDialog.initListener$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(PreviewSubtitleDialog previewSubtitleDialog, View view) {
        Context context = previewSubtitleDialog.getContext();
        if (context != null) {
            TransCodeSubtitleActivity.Companion companion = TransCodeSubtitleActivity.INSTANCE;
            String url = previewSubtitleDialog.getUrl();
            String fileName = previewSubtitleDialog.getFileName();
            String lang = previewSubtitleDialog.getLang();
            if (lang == null) {
                lang = "";
            }
            companion.start(context, url, fileName, lang, previewSubtitleDialog.getId(), previewSubtitleDialog.getSeason(), previewSubtitleDialog.getEpisode(), previewSubtitleDialog.getExtrModel());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(final PreviewSubtitleDialog previewSubtitleDialog, View view) {
        HttpUploadRequest httpUploadRequestAddParam = new HttpUploadRequest(null, 1, null).addBaseParams(previewSubtitleDialog.getBoxType() == 1 ? "Upload_movie_srt_user" : "Upload_tv_srt_user", "text/plain", new File(previewSubtitleDialog.transcodeFilePath), IDataSource.SCHEME_FILE_TAG).addParam(CmcdConfiguration.KEY_SESSION_ID, previewSubtitleDialog.getSid());
        String id = previewSubtitleDialog.getId();
        if (id == null) {
            id = "";
        }
        HttpUploadRequest httpUploadRequestAddParam2 = httpUploadRequestAddParam.addParam("tid", id);
        String id2 = previewSubtitleDialog.getId();
        if (id2 == null) {
            id2 = "";
        }
        HttpUploadRequest httpUploadRequestAddParam3 = httpUploadRequestAddParam2.addParam("mid", id2).addParam(DealWatchNextService.SEASON, Integer.valueOf(previewSubtitleDialog.getSeason())).addParam(DealWatchNextService.EPISODE, Integer.valueOf(previewSubtitleDialog.getEpisode()));
        String language = previewSubtitleDialog.getLanguage();
        if (language == null) {
            language = "";
        }
        HttpUploadRequest httpUploadRequestAddParam4 = httpUploadRequestAddParam3.addParam("language", language).addParam(IjkMediaMeta.IJKM_KEY_FORMAT, "srt");
        String lang = previewSubtitleDialog.getLang();
        Observable<R> observableCompose = httpUploadRequestAddParam4.addParam("lang", lang != null ? lang : "").asRequest().compose(RxUtils.rxTranslate2Bean(ResponseUploadExtraSubtitle.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, previewSubtitleDialog), new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.initListener$lambda$5$lambda$2(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.initListener$lambda$5$lambda$3(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.initListener$lambda$5$lambda$4(this.f$0, (ResponseUploadExtraSubtitle) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$5$lambda$3(PreviewSubtitleDialog previewSubtitleDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        previewSubtitleDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$5$lambda$4(PreviewSubtitleDialog previewSubtitleDialog, ResponseUploadExtraSubtitle it) {
        Intrinsics.checkNotNullParameter(it, "it");
        previewSubtitleDialog.hideLoadingView();
        EventBus eventBus = EventBus.getDefault();
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = previewSubtitleDialog.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        List<SRT> data = baseQuickAdapter.getData();
        String str = previewSubtitleDialog.subtitleContent;
        String lang = previewSubtitleDialog.getLang();
        String str2 = it.getSrt_info().sid;
        if (str2 == null) {
            str2 = "";
        }
        eventBus.post(new OpenSubtitleSelectEvent(data, str, lang, str2));
        previewSubtitleDialog.dismissAllowingStateLoss();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$5$lambda$2(PreviewSubtitleDialog previewSubtitleDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        previewSubtitleDialog.hideLoadingView();
        ToastUtils.showShort("Save failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void transcodeSubtitle() {
        Observable<ResponseBody> observableDownload = HttpHelperKt.getApiService().download(getUrl());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$8(this.f$0, (ResponseBody) obj);
            }
        };
        Object objAs = observableDownload.flatMap(new Function() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$9(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$11(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$12(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$13(this.f$0, (ArrayList) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transcodeSubtitle$lambda$9(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource transcodeSubtitle$lambda$8(final PreviewSubtitleDialog previewSubtitleDialog, ResponseBody it) throws Throwable {
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_DOWNLOAD, previewSubtitleDialog.getFileName());
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromIS(file, it.byteStream());
        Observable<R> observableCompose = new HttpUploadRequest(null, 1, null).addBaseParams("Srt_convert_encoding", "text/plain", file, "zip_file").addParam("encoding", "UTF-8").asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$8$lambda$6(this.f$0, (TranscodeResponse) obj);
            }
        };
        return observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.dialog.PreviewSubtitleDialog$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PreviewSubtitleDialog.transcodeSubtitle$lambda$8$lambda$7(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList transcodeSubtitle$lambda$8$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList transcodeSubtitle$lambda$8$lambda$6(PreviewSubtitleDialog previewSubtitleDialog, TranscodeResponse it) throws Throwable {
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + "/" + URLDecoder.decode(it.getSrt_name(), "utf-8"));
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        previewSubtitleDialog.transcodeFilePath = path;
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromString(file, it.getSrt_content(), false);
        ArrayList arrayList = new ArrayList();
        previewSubtitleDialog.subtitleContent = it.getSrt_content();
        SrtParser.parseSRTContentSrt(it.getSrt_content(), arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transcodeSubtitle$lambda$12(PreviewSubtitleDialog previewSubtitleDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        previewSubtitleDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transcodeSubtitle$lambda$13(PreviewSubtitleDialog previewSubtitleDialog, ArrayList arrayList) {
        previewSubtitleDialog.hideLoadingView();
        previewSubtitleDialog.getBinding().llContainer.setBackgroundColor(Color.parseColor("#F2000000"));
        LinearLayout llBtn = previewSubtitleDialog.getBinding().llBtn;
        Intrinsics.checkNotNullExpressionValue(llBtn, "llBtn");
        CommonExtKt.visible(llBtn);
        TvRecyclerView rvSubtitle = previewSubtitleDialog.getBinding().rvSubtitle;
        Intrinsics.checkNotNullExpressionValue(rvSubtitle, "rvSubtitle");
        CommonExtKt.visible(rvSubtitle);
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = previewSubtitleDialog.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(arrayList);
        previewSubtitleDialog.getBinding().tvOk.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit transcodeSubtitle$lambda$11(PreviewSubtitleDialog previewSubtitleDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        previewSubtitleDialog.hideLoadingView();
        previewSubtitleDialog.dismissAllowingStateLoss();
        Context context = previewSubtitleDialog.getContext();
        if (context != null) {
            TransCodeSubtitleActivity.Companion companion = TransCodeSubtitleActivity.INSTANCE;
            String url = previewSubtitleDialog.getUrl();
            String fileName = previewSubtitleDialog.getFileName();
            String language = previewSubtitleDialog.getLanguage();
            if (language == null) {
                language = "";
            }
            companion.start(context, url, fileName, language, previewSubtitleDialog.getId(), previewSubtitleDialog.getSeason(), previewSubtitleDialog.getEpisode(), previewSubtitleDialog.getExtrModel());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: PreviewSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PreviewSubtitleDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/PreviewSubtitleDialog;", "model", "Lcom/dueeeke/model/ExtrModel;", "fileName", "", "url", "lang", "language", "boxType", "", "id", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PreviewSubtitleDialog newInstance(ExtrModel model, String fileName, String url, String lang, String language, int boxType, String id, int season, int episode) {
            Intrinsics.checkNotNullParameter(model, "model");
            PreviewSubtitleDialog previewSubtitleDialog = new PreviewSubtitleDialog();
            if (url == null) {
                url = "";
            }
            previewSubtitleDialog.setUrl(url);
            if (fileName == null) {
                fileName = "";
            }
            previewSubtitleDialog.setFileName(fileName);
            previewSubtitleDialog.setSid(previewSubtitleDialog.getSid());
            previewSubtitleDialog.setLang(lang);
            previewSubtitleDialog.setBoxType(boxType);
            previewSubtitleDialog.setId(id);
            previewSubtitleDialog.setSeason(season);
            previewSubtitleDialog.setEpisode(episode);
            previewSubtitleDialog.setLanguage(language);
            previewSubtitleDialog.setExtrModel(model);
            return previewSubtitleDialog;
        }
    }
}
