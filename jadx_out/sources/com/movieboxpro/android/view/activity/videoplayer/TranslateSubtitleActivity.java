package com.movieboxpro.android.view.activity.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.utils.SRT;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.TranslateSubtitleEvent;
import com.movieboxpro.android.livedata.SubtitleContentLiveData;
import com.movieboxpro.android.model.LanguageModel;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.ChooseLanguageDialog;
import com.movieboxpro.android.view.dialog.TranslateProgressDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityTranslateSubtitleBinding;
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
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TranslateSubtitleActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u000f\u0018\u0000 ?2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001?B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\b\u0010\u001a\u001a\u00020\u0018H\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0018\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\bH\u0002J\u0016\u0010\"\u001a\u00020\u00182\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0$H\u0016J\u0018\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0013H\u0016J\b\u0010*\u001a\u00020\u0018H\u0016J \u0010+\u001a\u00020\u00182\u0016\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\r0-j\b\u0012\u0004\u0012\u00020\r`.H\u0016J\u0010\u0010/\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J&\u00100\u001a\u00020\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\r012\u0006\u00102\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u00103\u001a\u00020\u0018H\u0016J\u0010\u00104\u001a\u00020\u00182\u0006\u00105\u001a\u00020\bH\u0016J\u0010\u00106\u001a\u00020\u00182\u0006\u00107\u001a\u00020\u0010H\u0002J\b\u00108\u001a\u00020\u0018H\u0002J\b\u00109\u001a\u00020\u0018H\u0002J\b\u0010:\u001a\u00020\u0018H\u0002J\b\u0010;\u001a\u00020\u0018H\u0014J\b\u0010<\u001a\u00020\u0010H\u0014J\b\u0010=\u001a\u00020\u0013H\u0014J\b\u0010>\u001a\u00020\u0002H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitlePresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityTranslateSubtitleBinding;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$View;", "<init>", "()V", CmcdConfiguration.KEY_SESSION_ID, "", "toLanguage", "toLanguageName", "subtitleAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/dl7/player/utils/SRT;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "haveTanslate", "", "translating", "originalCount", "", "boxType", "dialog", "Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog;", "initListener", "", "initView", "initData", "maybeStartTranslate", "resolveLanguageName", "languageCode", "equalsLanguages", "fromLanguage", "normalizeLanguageTag", "language", "showContent", "model", "", "progressDialog", "Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog;", "showProgress", "progress", "max", "dismissProgress", "showSubtitles", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onTranslateStarted", "appendTranslatedCues", "", "doneCount", "onTranslateFinished", "onTranslateFailed", "message", "setOkVisible", "visible", "showListLoading", "hideListLoading", "scrollListToBottom", "requestData", "isNeedLoadData", "getLayoutResId", "bindPresenter", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitleActivity extends BaseMvpActivity<TranslateSubtitlePresenter, ActivityTranslateSubtitleBinding> implements TranslateSubtitleContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ChooseLanguageDialog dialog;
    private boolean haveTanslate;
    private int originalCount;
    private TranslateProgressDialog progressDialog;
    private BaseQuickAdapter<SRT, BaseViewHolder> subtitleAdapter;
    private boolean translating;
    private String sid = "";
    private String toLanguage = "";
    private String toLanguageName = "";
    private int boxType = 1;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void showContent(List<? extends SRT> model) {
        Intrinsics.checkNotNullParameter(model, "model");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityTranslateSubtitleBinding) this.binding).tvFrom.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.initListener$lambda$0(this.f$0, view);
            }
        });
        ((ActivityTranslateSubtitleBinding) this.binding).tvTo.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.initListener$lambda$1(this.f$0, view);
            }
        });
        ((ActivityTranslateSubtitleBinding) this.binding).tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.initListener$lambda$2(this.f$0, view);
            }
        });
        ((ActivityTranslateSubtitleBinding) this.binding).tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(final TranslateSubtitleActivity translateSubtitleActivity, View view) {
        ChooseLanguageDialog chooseLanguageDialogNewInstance = ChooseLanguageDialog.INSTANCE.newInstance(((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvFrom.getText().toString());
        translateSubtitleActivity.dialog = chooseLanguageDialogNewInstance;
        Intrinsics.checkNotNull(chooseLanguageDialogNewInstance);
        chooseLanguageDialogNewInstance.setListener(new ChooseLanguageDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$initListener$1$1
            @Override // com.movieboxpro.android.view.dialog.ChooseLanguageDialog.OnLanguageListener
            public void onLanguageSelected(String language, String languageName) {
                Intrinsics.checkNotNullParameter(language, "language");
                Intrinsics.checkNotNullParameter(languageName, "languageName");
                ((ActivityTranslateSubtitleBinding) this.this$0.binding).tvFrom.setText(language);
                this.this$0.maybeStartTranslate();
            }
        });
        ChooseLanguageDialog chooseLanguageDialog = translateSubtitleActivity.dialog;
        Intrinsics.checkNotNull(chooseLanguageDialog);
        chooseLanguageDialog.show(translateSubtitleActivity.getSupportFragmentManager(), "ChooseLanguageDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(final TranslateSubtitleActivity translateSubtitleActivity, View view) {
        ChooseLanguageDialog chooseLanguageDialogNewInstance = ChooseLanguageDialog.INSTANCE.newInstance(translateSubtitleActivity.toLanguage);
        translateSubtitleActivity.dialog = chooseLanguageDialogNewInstance;
        Intrinsics.checkNotNull(chooseLanguageDialogNewInstance);
        chooseLanguageDialogNewInstance.setListener(new ChooseLanguageDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$initListener$2$1
            @Override // com.movieboxpro.android.view.dialog.ChooseLanguageDialog.OnLanguageListener
            public void onLanguageSelected(String language, String languageName) {
                Intrinsics.checkNotNullParameter(language, "language");
                Intrinsics.checkNotNullParameter(languageName, "languageName");
                ((ActivityTranslateSubtitleBinding) this.this$0.binding).tvTo.setText(language);
                this.this$0.toLanguage = language;
                this.this$0.toLanguageName = languageName;
                this.this$0.maybeStartTranslate();
            }
        });
        ChooseLanguageDialog chooseLanguageDialog = translateSubtitleActivity.dialog;
        Intrinsics.checkNotNull(chooseLanguageDialog);
        chooseLanguageDialog.show(translateSubtitleActivity.getSupportFragmentManager(), "ChooseLanguageDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(TranslateSubtitleActivity translateSubtitleActivity, View view) {
        if (translateSubtitleActivity.haveTanslate && translateSubtitleActivity.subtitleAdapter != null) {
            EventBus eventBus = EventBus.getDefault();
            BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = translateSubtitleActivity.subtitleAdapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            eventBus.post(new TranslateSubtitleEvent(baseQuickAdapter.getData()));
            translateSubtitleActivity.finish();
            return;
        }
        if (translateSubtitleActivity.translating) {
            return;
        }
        translateSubtitleActivity.finish();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ((ActivityTranslateSubtitleBinding) this.binding).tvTo.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TranslateSubtitleActivity.initView$lambda$4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(TranslateSubtitleActivity translateSubtitleActivity) {
        ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvTo.requestFocus();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.boxType = getIntent().getIntExtra("boxType", 1);
        String stringExtra = getIntent().getStringExtra(CmcdConfiguration.KEY_SESSION_ID);
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.sid = stringExtra;
        SubtitleContentLiveData.INSTANCE.get().observe(this, new TranslateSubtitleActivity$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitleActivity.initData$lambda$6(this.f$0, (List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$6(TranslateSubtitleActivity translateSubtitleActivity, List list) {
        final ArrayList arrayList = new ArrayList();
        Intrinsics.checkNotNull(list);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Subtitle subtitle = (Subtitle) it.next();
            SRT srt = new SRT();
            srt.setBeginTime(subtitle.start.mseconds);
            srt.setEndTime(subtitle.end.mseconds);
            srt.setSrtBody(subtitle.content);
            arrayList.add(srt);
        }
        translateSubtitleActivity.originalCount = arrayList.size();
        final int i = R.layout.adapter_subtitle_item;
        translateSubtitleActivity.subtitleAdapter = new BaseQuickAdapter<SRT, BaseViewHolder>(arrayList, i) { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$initData$1$2
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRT item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ((TextView) helper.getView(R.id.tvLanguage)).setText(Html.fromHtml(item.getSrtBody()));
            }
        };
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = null;
        ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).recyclerView.setItemAnimator(null);
        ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).recyclerView.setLayoutManager(new LinearLayoutManager(translateSubtitleActivity));
        RecyclerView recyclerView = ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).recyclerView;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter2 = translateSubtitleActivity.subtitleAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        String stringExtra = translateSubtitleActivity.getIntent().getStringExtra(TypedValues.TransitionType.S_FROM);
        if (stringExtra == null) {
            stringExtra = "";
        }
        String str = stringExtra;
        ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvFrom.setText(str);
        String deviceLang = App.deviceLang;
        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
        if (!translateSubtitleActivity.equalsLanguages(stringExtra, deviceLang)) {
            if (str.length() > 0) {
                ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvTo.setText(App.deviceLang);
                String deviceLang2 = App.deviceLang;
                Intrinsics.checkNotNullExpressionValue(deviceLang2, "deviceLang");
                translateSubtitleActivity.toLanguage = deviceLang2;
                String deviceLang3 = App.deviceLang;
                Intrinsics.checkNotNullExpressionValue(deviceLang3, "deviceLang");
                translateSubtitleActivity.toLanguageName = translateSubtitleActivity.resolveLanguageName(deviceLang3);
                translateSubtitleActivity.maybeStartTranslate();
            } else {
                ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvFrom.setText("Choose");
                ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvTo.setText(App.deviceLang);
                String deviceLang4 = App.deviceLang;
                Intrinsics.checkNotNullExpressionValue(deviceLang4, "deviceLang");
                translateSubtitleActivity.toLanguage = deviceLang4;
            }
        } else {
            ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvTo.setText("Choose");
            translateSubtitleActivity.toLanguage = ((ActivityTranslateSubtitleBinding) translateSubtitleActivity.binding).tvTo.getText().toString();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void maybeStartTranslate() {
        String string = ((ActivityTranslateSubtitleBinding) this.binding).tvFrom.getText().toString();
        if (string.length() == 0 || Intrinsics.areEqual(string, "Choose") || this.toLanguage.length() == 0 || Intrinsics.areEqual(this.toLanguage, "Choose") || equalsLanguages(string, this.toLanguage)) {
            return;
        }
        ((TranslateSubtitlePresenter) this.mPresenter).translate(string, this.toLanguage, this.toLanguageName, this.sid, this.boxType, this.originalCount);
    }

    private final String resolveLanguageName(String languageCode) {
        Object next;
        String fullLanguage;
        String[] stringArray = getResources().getStringArray(R.array.languages);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            Intrinsics.checkNotNull(str);
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() == 2) {
                arrayList.add(new LanguageModel((String) listSplit$default.get(0), (String) listSplit$default.get(1)));
            }
        }
        Iterator it = arrayList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((LanguageModel) next).getLanguage(), languageCode));
        LanguageModel languageModel = (LanguageModel) next;
        if (languageModel != null && (fullLanguage = languageModel.getFullLanguage()) != null) {
            return fullLanguage;
        }
        String displayLanguage = new Locale(languageCode).getDisplayLanguage(Locale.ENGLISH);
        Intrinsics.checkNotNullExpressionValue(displayLanguage, "getDisplayLanguage(...)");
        return displayLanguage;
    }

    private final boolean equalsLanguages(String fromLanguage, String toLanguage) {
        return StringsKt.equals(normalizeLanguageTag(fromLanguage), normalizeLanguageTag(toLanguage), true);
    }

    private final String normalizeLanguageTag(String language) {
        String string = StringsKt.trim((CharSequence) language).toString();
        return (StringsKt.equals(string, "zh", true) || StringsKt.equals(string, "zh-CN", true)) ? "zh-CN" : string;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void showProgress(int progress, int max) {
        if (progress == 0) {
            TranslateProgressDialog translateProgressDialogNewInstance = TranslateProgressDialog.INSTANCE.newInstance(max);
            this.progressDialog = translateProgressDialogNewInstance;
            if (translateProgressDialogNewInstance != null) {
                translateProgressDialogNewInstance.show(getSupportFragmentManager(), "TranslateProgressDialog");
                return;
            }
            return;
        }
        TranslateProgressDialog translateProgressDialog = this.progressDialog;
        if (translateProgressDialog != null) {
            translateProgressDialog.updateProgress(progress, max);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void dismissProgress() {
        TranslateProgressDialog translateProgressDialog = this.progressDialog;
        if (translateProgressDialog != null) {
            translateProgressDialog.dismissAllowingStateLoss();
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void showSubtitles(ArrayList<SRT> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.haveTanslate = true;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter != null) {
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.setList(list);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void onTranslateStarted(int originalCount) {
        this.haveTanslate = false;
        this.translating = true;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter != null) {
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.setList(CollectionsKt.emptyList());
        }
        showListLoading();
        setOkVisible(false);
        ((ActivityTranslateSubtitleBinding) this.binding).tvProgress.setText("Translating 0/" + originalCount);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void appendTranslatedCues(List<SRT> list, int doneCount, int originalCount) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (this.subtitleAdapter == null) {
            return;
        }
        List<SRT> list2 = list;
        if (!list2.isEmpty()) {
            BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.addData(list2);
        }
        scrollListToBottom();
        ((ActivityTranslateSubtitleBinding) this.binding).tvProgress.setText("Translating " + doneCount + "/" + originalCount);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void onTranslateFinished() {
        boolean z = false;
        this.translating = false;
        hideListLoading();
        setOkVisible(true);
        ((ActivityTranslateSubtitleBinding) this.binding).tvProgress.setText("");
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter != null) {
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            if (!baseQuickAdapter.getData().isEmpty()) {
                z = true;
            }
        }
        this.haveTanslate = z;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.View
    public void onTranslateFailed(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        boolean z = false;
        this.translating = false;
        hideListLoading();
        setOkVisible(true);
        ((ActivityTranslateSubtitleBinding) this.binding).tvProgress.setText("");
        ToastUtils.showShort("Translate failed: " + message, new Object[0]);
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter != null) {
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            if (!baseQuickAdapter.getData().isEmpty()) {
                z = true;
            }
        }
        this.haveTanslate = z;
    }

    private final void setOkVisible(boolean visible) {
        if (!visible && ((ActivityTranslateSubtitleBinding) this.binding).tvOk.hasFocus()) {
            ((ActivityTranslateSubtitleBinding) this.binding).tvCancel.requestFocus();
        }
        ((ActivityTranslateSubtitleBinding) this.binding).tvOk.setVisibility(visible ? 0 : 8);
    }

    private final void showListLoading() {
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter2 = this.subtitleAdapter;
        if (baseQuickAdapter2 == null) {
            return;
        }
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter2 = null;
        }
        if (baseQuickAdapter2.getFooterLayoutCount() == 0) {
            BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            } else {
                baseQuickAdapter = baseQuickAdapter3;
            }
            View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_translate_subtitle_loading_footer, (ViewGroup) ((ActivityTranslateSubtitleBinding) this.binding).recyclerView, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
            BaseQuickAdapter.addFooterView$default(baseQuickAdapter, viewInflate, 0, 0, 6, null);
        }
    }

    private final void hideListLoading() {
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter != null) {
            BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter2 = null;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                baseQuickAdapter = null;
            }
            if (baseQuickAdapter.getFooterLayoutCount() > 0) {
                BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter3 = this.subtitleAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter2.removeAllFooterView();
            }
        }
    }

    private final void scrollListToBottom() {
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            return;
        }
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        if (baseQuickAdapter.getData().isEmpty()) {
            return;
        }
        final RecyclerView recyclerView = ((ActivityTranslateSubtitleBinding) this.binding).recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                TranslateSubtitleActivity.scrollListToBottom$lambda$9(this.f$0, recyclerView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scrollListToBottom$lambda$9(TranslateSubtitleActivity translateSubtitleActivity, RecyclerView recyclerView) {
        if (translateSubtitleActivity.isFinishing() || translateSubtitleActivity.isDestroyed()) {
            return;
        }
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = translateSubtitleActivity.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        int itemCount = baseQuickAdapter.getItemCount() - 1;
        if (itemCount < 0) {
            return;
        }
        recyclerView.stopScroll();
        recyclerView.scrollToPosition(itemCount);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_translate_subtitle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TranslateSubtitlePresenter bindPresenter() {
        return new TranslateSubtitlePresenter(this);
    }

    /* JADX INFO: compiled from: TranslateSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "fromLanguage", "", CmcdConfiguration.KEY_SESSION_ID, "boxType", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String fromLanguage, String sid, int boxType) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) TranslateSubtitleActivity.class);
            intent.putExtra(TypedValues.TransitionType.S_FROM, fromLanguage);
            intent.putExtra(CmcdConfiguration.KEY_SESSION_ID, sid);
            intent.putExtra("boxType", boxType);
            context.startActivity(intent);
        }
    }
}
