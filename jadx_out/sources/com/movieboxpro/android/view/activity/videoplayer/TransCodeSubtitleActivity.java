package com.movieboxpro.android.view.activity.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.LanguageModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.OpenSubtitleSelectEvent;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityTransCodeBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TransCodeSubtitleActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0006\u0018\u0000 32\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u00013B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019H\u0016J\b\u0010$\u001a\u00020\"H\u0014J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020\"H\u0014J\b\u0010)\u001a\u00020\"H\u0014J\b\u0010*\u001a\u00020\u0002H\u0014J\b\u0010+\u001a\u00020\"H\u0014J.\u0010,\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\b\u00100\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u00101\u001a\u00020\"2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0.H\u0002J\u0016\u00102\u001a\u00020\"2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\nH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitlePresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityTransCodeBinding;", "Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleContract$View;", "<init>", "()V", "subtitleFile", "Ljava/io/File;", "encodeModels", "", "Lcom/dueeeke/model/EncodeModel;", "languageAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/dueeeke/model/LanguageModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "codeAdapter", "subtitleAdapter", "Lcom/dl7/player/utils/SRT;", "getLayoutResId", "", "lastSelectLanguagePosition", "lastClickedLanguagePosition", "lastSelectCodePosition", "subtitleContent", "", "subtitleLanguage", DealWatchNextService.SEASON, "Ljava/lang/Integer;", DealWatchNextService.EPISODE, "videoId", "extrModel", "Lcom/dueeeke/model/ExtrModel;", "setSubtitleContent", "", FirebaseAnalytics.Param.CONTENT, "initListener", "uploadSubtitle", "isNeedLoadData", "", "initView", "initData", "bindPresenter", "requestData", "showLanguages", "languageModel", "", "subtitles", IDataSource.SCHEME_FILE_TAG, "sortLanguages", "showSubtitle", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransCodeSubtitleActivity extends BaseMvpActivity<TransCodeSubtitlePresenter, ActivityTransCodeBinding> implements TransCodeSubtitleContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> codeAdapter;
    private List<? extends EncodeModel> encodeModels;
    private ExtrModel extrModel;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private int lastClickedLanguagePosition;
    private int lastSelectCodePosition;
    private int lastSelectLanguagePosition;
    private BaseQuickAdapter<SRT, BaseViewHolder> subtitleAdapter;
    private File subtitleFile;
    private String videoId;
    private String subtitleContent = "";
    private String subtitleLanguage = "";
    private Integer season = 0;
    private Integer episode = 0;

    private final void uploadSubtitle() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_trans_code;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleContract.View
    public void setSubtitleContent(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.subtitleContent = content;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivityTransCodeBinding) this.binding).rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity.initListener.1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                EncodeModel encodeModel;
                List<String> code;
                if (position == -1) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                List list = TransCodeSubtitleActivity.this.encodeModels;
                if (list != null && (encodeModel = (EncodeModel) list.get(position)) != null && (code = encodeModel.getCode()) != null) {
                    Iterator<T> it = code.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new LanguageModel((String) it.next()));
                    }
                }
                if (TransCodeSubtitleActivity.this.lastClickedLanguagePosition == position) {
                    ((LanguageModel) arrayList.get(TransCodeSubtitleActivity.this.lastSelectCodePosition)).isSelected = true;
                }
                BaseQuickAdapter baseQuickAdapter = TransCodeSubtitleActivity.this.codeAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
                    baseQuickAdapter = null;
                }
                baseQuickAdapter.setList(arrayList);
                TransCodeSubtitleActivity.this.lastSelectLanguagePosition = position;
            }
        });
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.codeAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                TransCodeSubtitleActivity.initListener$lambda$3(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        ((ActivityTransCodeBinding) this.binding).flDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransCodeSubtitleActivity.initListener$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(TransCodeSubtitleActivity transCodeSubtitleActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String str;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = transCodeSubtitleActivity.codeAdapter;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            baseQuickAdapter2 = null;
        }
        int i2 = 0;
        for (Object obj : baseQuickAdapter2.getData()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LanguageModel languageModel = (LanguageModel) obj;
            if (languageModel.isSelected) {
                languageModel.isSelected = false;
                BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = transCodeSubtitleActivity.codeAdapter;
                if (baseQuickAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
                    baseQuickAdapter4 = null;
                }
                baseQuickAdapter4.notifyItemChanged(i2);
                break;
            }
            i2 = i3;
        }
        transCodeSubtitleActivity.lastClickedLanguagePosition = transCodeSubtitleActivity.lastSelectLanguagePosition;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter5 = transCodeSubtitleActivity.codeAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            baseQuickAdapter5 = null;
        }
        LanguageModel languageModel2 = baseQuickAdapter5.getData().get(i);
        if (languageModel2 != null) {
            languageModel2.isSelected = true;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter6 = transCodeSubtitleActivity.codeAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.notifyItemChanged(i);
        transCodeSubtitleActivity.lastSelectCodePosition = i;
        File file = transCodeSubtitleActivity.subtitleFile;
        if (file != null) {
            TransCodeSubtitlePresenter transCodeSubtitlePresenter = (TransCodeSubtitlePresenter) transCodeSubtitleActivity.mPresenter;
            BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter7 = transCodeSubtitleActivity.codeAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            } else {
                baseQuickAdapter3 = baseQuickAdapter7;
            }
            LanguageModel languageModel3 = baseQuickAdapter3.getData().get(i);
            if (languageModel3 == null || (str = languageModel3.language) == null) {
                str = "UTF-8";
            }
            transCodeSubtitlePresenter.transCode(str, file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(final TransCodeSubtitleActivity transCodeSubtitleActivity, View view) {
        transCodeSubtitleActivity.uploadSubtitle();
        ((TransCodeSubtitlePresenter) transCodeSubtitleActivity.mPresenter).uploadSubtitle(transCodeSubtitleActivity.subtitleContent, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TransCodeSubtitleActivity.initListener$lambda$5$lambda$4(this.f$0, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$5$lambda$4(TransCodeSubtitleActivity transCodeSubtitleActivity, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        EventBus eventBus = EventBus.getDefault();
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = transCodeSubtitleActivity.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        eventBus.post(new OpenSubtitleSelectEvent(baseQuickAdapter.getData(), transCodeSubtitleActivity.subtitleContent, transCodeSubtitleActivity.subtitleLanguage, it));
        transCodeSubtitleActivity.finish();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.season = Integer.valueOf(getIntent().getIntExtra(DealWatchNextService.SEASON, 0));
        this.episode = Integer.valueOf(getIntent().getIntExtra(DealWatchNextService.EPISODE, 0));
        this.videoId = getIntent().getStringExtra("id");
        Serializable serializableExtra = getIntent().getSerializableExtra("model");
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = null;
        this.extrModel = serializableExtra instanceof ExtrModel ? (ExtrModel) serializableExtra : null;
        ((TransCodeSubtitlePresenter) this.mPresenter).initValue(this.season, this.episode, this.videoId, this.extrModel);
        String stringExtra = getIntent().getStringExtra("language");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.subtitleLanguage = stringExtra;
        this.subtitleAdapter = new BaseQuickAdapter<SRT, BaseViewHolder>(R.layout.adapter_subtitle_item) { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity.initData.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRT item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ((TextView) helper.getView(R.id.tvLanguage)).setText(Html.fromHtml(item.getSrtBody()));
            }
        };
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_language_item) { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity.initData.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, LanguageModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ((TextView) helper.getView(R.id.tvLanguage)).setText(item.getLanguage());
            }
        };
        this.codeAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_trans_code_item) { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity.initData.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, LanguageModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                ImageView imageView = (ImageView) helper.getView(R.id.ivSelected);
                if (item.isSelected) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                helper.setText(R.id.tvCodeName, item.getLanguage());
            }
        };
        TvRecyclerView tvRecyclerView = ((ActivityTransCodeBinding) this.binding).rvLanguage;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = this.languageAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        TvRecyclerView tvRecyclerView2 = ((ActivityTransCodeBinding) this.binding).rvCode;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = this.codeAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        TvRecyclerView tvRecyclerView3 = ((ActivityTransCodeBinding) this.binding).rvSubtitle;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter4 = this.subtitleAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter);
        ((ActivityTransCodeBinding) this.binding).rvSubtitle.setSelectedItemAtCentered(true);
        ((ActivityTransCodeBinding) this.binding).rvCode.setSelectedItemAtCentered(true);
        ((ActivityTransCodeBinding) this.binding).rvLanguage.setSelectedItemAtCentered(true);
        requestData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TransCodeSubtitlePresenter bindPresenter() {
        return new TransCodeSubtitlePresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        TransCodeSubtitlePresenter transCodeSubtitlePresenter = (TransCodeSubtitlePresenter) this.mPresenter;
        String stringExtra = getIntent().getStringExtra("url");
        if (stringExtra == null) {
            stringExtra = "";
        }
        String stringExtra2 = getIntent().getStringExtra("fileName");
        transCodeSubtitlePresenter.firstLoadData(stringExtra, stringExtra2 != null ? stringExtra2 : "");
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleContract.View
    public void showLanguages(List<EncodeModel> languageModel, List<? extends SRT> subtitles, File file) {
        Intrinsics.checkNotNullParameter(languageModel, "languageModel");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        this.encodeModels = languageModel;
        this.subtitleFile = file;
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(subtitles);
        sortLanguages(languageModel);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = languageModel.iterator();
        while (it.hasNext()) {
            arrayList.add(new LanguageModel(((EncodeModel) it.next()).getLanguage()));
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = this.languageAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setList(arrayList);
        ArrayList arrayList2 = new ArrayList();
        if (!languageModel.isEmpty()) {
            List<String> code = languageModel.get(0).getCode();
            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
            Iterator<T> it2 = code.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new LanguageModel((String) it2.next()));
            }
        }
        ArrayList arrayList3 = arrayList2;
        if (!arrayList3.isEmpty()) {
            ((LanguageModel) arrayList2.get(0)).isSelected = true;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = this.codeAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("codeAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.setList(arrayList3);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleContract.View
    public void showSubtitle(List<? extends SRT> subtitles) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        BaseQuickAdapter<SRT, BaseViewHolder> baseQuickAdapter = this.subtitleAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitleAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(subtitles);
    }

    /* JADX INFO: compiled from: TransCodeSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TransCodeSubtitleActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "url", "", "fileName", "language", "id", DealWatchNextService.SEASON, "", DealWatchNextService.EPISODE, "model", "Lcom/dueeeke/model/ExtrModel;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String url, String fileName, String language, String id, int season, int episode, ExtrModel model) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(language, "language");
            Intrinsics.checkNotNullParameter(model, "model");
            Intent intent = new Intent(context, (Class<?>) TransCodeSubtitleActivity.class);
            intent.putExtra("url", url);
            intent.putExtra("language", language);
            intent.putExtra(DealWatchNextService.SEASON, season);
            intent.putExtra(DealWatchNextService.EPISODE, episode);
            intent.putExtra("id", id);
            intent.putExtra("model", model);
            intent.putExtra("fileName", fileName);
            context.startActivity(intent);
        }
    }

    private final void sortLanguages(List<EncodeModel> encodeModels) {
        if (encodeModels.size() > 1) {
            CollectionsKt.sortWith(encodeModels, new Comparator() { // from class: com.movieboxpro.android.view.activity.videoplayer.TransCodeSubtitleActivity$sortLanguages$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((EncodeModel) t).getLanguage(), ((EncodeModel) t2).getLanguage());
                }
            });
        }
        int size = encodeModels.size();
        for (int i = 0; i < size; i++) {
            EncodeModel encodeModel = encodeModels.get(i);
            if (Intrinsics.areEqual("Full Unicode", encodeModel.getLanguage())) {
                encodeModels.remove(i);
                encodeModels.add(0, encodeModel);
                return;
            }
        }
    }
}
