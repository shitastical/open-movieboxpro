package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.ExtrModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.LanguageModel;
import com.movieboxpro.android.model.OpenSubtitleFile;
import com.movieboxpro.android.model.OpenSubtitleItem;
import com.movieboxpro.android.model.OpenSubtitleResponse;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogOpenSubtitleBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OpenSubtitleDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003012B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010$\u001a\u00020%2\b\u0010\"\u001a\u0004\u0018\u00010#J\b\u0010&\u001a\u00020%H\u0016J\u0018\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00070(j\b\u0012\u0004\u0012\u00020\u0007`)H\u0002J\u001a\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020%H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R+\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0013\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0011R+\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u0013\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogOpenSubtitleBinding;", "<init>", "()V", "languageAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/LanguageModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "selectPos", "", "<set-?>", "", "imdbId", "getImdbId", "()Ljava/lang/String;", "setImdbId", "(Ljava/lang/String;)V", "imdbId$delegate", "Lkotlin/properties/ReadWriteProperty;", "id", "getId", "setId", "id$delegate", DealWatchNextService.SEASON, "getSeason", "()I", "setSeason", "(I)V", "season$delegate", DealWatchNextService.EPISODE, "getEpisode", "setEpisode", "episode$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$OnOpenSubtitleListener;", "setListener", "", "onStart", "initLanguages", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showChooseLanguage", "OnOpenSubtitleListener", "Companion", "OpenSubtitleListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OpenSubtitleDialog extends BaseTransparentDialogFragment<DialogOpenSubtitleBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleDialog.class, "imdbId", "getImdbId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleDialog.class, "id", "getId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleDialog.class, DealWatchNextService.SEASON, "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleDialog.class, DealWatchNextService.EPISODE, "getEpisode()I", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: episode$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty episode;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty id;

    /* JADX INFO: renamed from: imdbId$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty imdbId;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private OnOpenSubtitleListener listener;

    /* JADX INFO: renamed from: season$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty season;
    private int selectPos;

    /* JADX INFO: compiled from: OpenSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$OnOpenSubtitleListener;", "", "onSelectLanguage", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnOpenSubtitleListener {
        void onSelectLanguage();
    }

    @JvmStatic
    public static final OpenSubtitleDialog newInstance(String str, String str2, int i, int i2) {
        return INSTANCE.newInstance(str, str2, i, i2);
    }

    public OpenSubtitleDialog() {
        super(R.layout.dialog_open_subtitle);
        OpenSubtitleDialog openSubtitleDialog = this;
        this.imdbId = FragmentArgsKt.arg(openSubtitleDialog);
        this.id = FragmentArgsKt.arg(openSubtitleDialog);
        this.season = FragmentArgsKt.arg(openSubtitleDialog);
        this.episode = FragmentArgsKt.arg(openSubtitleDialog);
    }

    private final String getImdbId() {
        return (String) this.imdbId.getValue(this, $$delegatedProperties[0]);
    }

    private final void setImdbId(String str) {
        this.imdbId.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getId() {
        return (String) this.id.getValue(this, $$delegatedProperties[1]);
    }

    private final void setId(String str) {
        this.id.setValue(this, $$delegatedProperties[1], str);
    }

    private final int getSeason() {
        return ((Number) this.season.getValue(this, $$delegatedProperties[2])).intValue();
    }

    private final void setSeason(int i) {
        this.season.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    private final int getEpisode() {
        return ((Number) this.episode.getValue(this, $$delegatedProperties[3])).intValue();
    }

    private final void setEpisode(int i) {
        this.episode.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    public final void setListener(OnOpenSubtitleListener listener) {
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.7d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.3f;
            window.setAttributes(attributes);
        }
    }

    /* JADX INFO: compiled from: OpenSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog;", "imdbId", "", "id", DealWatchNextService.SEASON, "", DealWatchNextService.EPISODE, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final OpenSubtitleDialog newInstance(String imdbId, String id, int season, int episode) {
            Intrinsics.checkNotNullParameter(imdbId, "imdbId");
            Intrinsics.checkNotNullParameter(id, "id");
            OpenSubtitleDialog openSubtitleDialog = new OpenSubtitleDialog();
            Bundle bundle = new Bundle();
            bundle.putString("imdbId", imdbId);
            bundle.putString("id", id);
            bundle.putInt(DealWatchNextService.SEASON, season);
            bundle.putInt(DealWatchNextService.EPISODE, episode);
            openSubtitleDialog.setArguments(bundle);
            return openSubtitleDialog;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ArrayList<LanguageModel> initLanguages() {
        ArrayList<LanguageModel> arrayList = new ArrayList<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String selectOpenSubtitleLanguage = SettingManager.INSTANCE.getSelectOpenSubtitleLanguage();
        if (!StringsKt.isBlank(selectOpenSubtitleLanguage)) {
            List listSplit$default = StringsKt.split$default((CharSequence) selectOpenSubtitleLanguage, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() == 2) {
                String str = (String) listSplit$default.get(0);
                linkedHashMap.put(str, new LanguageModel(str, (String) listSplit$default.get(1)));
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(App.deviceLang, new LanguageModel(App.deviceLang, new Locale(App.deviceLang).getDisplayLanguage(Locale.ENGLISH)));
        String[] stringArray = getResources().getStringArray(R.array.usual_languages);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        for (String str2 : stringArray) {
            Intrinsics.checkNotNull(str2);
            List listSplit$default2 = StringsKt.split$default((CharSequence) str2, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default2.size() == 2) {
                String str3 = (String) listSplit$default2.get(0);
                linkedHashMap2.put(str3, new LanguageModel(str3, (String) listSplit$default2.get(1)));
            }
        }
        this.selectPos = 0;
        Iterator it = linkedHashMap2.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(((Map.Entry) it.next()).getValue());
        }
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getBinding().tvMore.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.showChooseLanguage();
            }
        });
        getBinding().tvMore.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                return OpenSubtitleDialog.onViewCreated$lambda$4(this.f$0, view2, i, keyEvent);
            }
        });
        ArrayList<LanguageModel> arrayListInitLanguages = initLanguages();
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(arrayListInitLanguages, R.layout.adapter_subtitle_language_item) { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog.onViewCreated.3
            {
                super(i, arrayListInitLanguages);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, LanguageModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.textView, item.getFullLanguage());
            }
        };
        TvRecyclerView tvRecyclerView = getBinding().rvLanguage;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.languageAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog.onViewCreated.4
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                OpenSubtitleDialog.this.getBinding().viewPager.setCurrentItem(position, false);
            }
        });
        ArrayList arrayList = new ArrayList();
        ArrayList<LanguageModel> arrayList2 = arrayListInitLanguages;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (LanguageModel languageModel : arrayList2) {
            OpenSubtitleListFragment.Companion companion = OpenSubtitleListFragment.INSTANCE;
            String imdbId = getImdbId();
            String language = languageModel.getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
            String fullLanguage = languageModel.getFullLanguage();
            Intrinsics.checkNotNullExpressionValue(fullLanguage, "getFullLanguage(...)");
            arrayList3.add(companion.newInstance(imdbId, language, fullLanguage, getId(), getSeason(), getEpisode()));
        }
        arrayList.addAll(arrayList3);
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList4.add(((LanguageModel) it.next()).getFullLanguage());
        }
        getBinding().viewPager.setAdapter(new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, (String[]) arrayList4.toArray(new String[0])));
        getBinding().viewPager.setOffscreenPageLimit(arrayList.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$4(OpenSubtitleDialog openSubtitleDialog, View view, int i, KeyEvent keyEvent) {
        if (i != 22 || keyEvent.getAction() != 0) {
            return false;
        }
        openSubtitleDialog.showChooseLanguage();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChooseLanguage() {
        ChooseLanguageDialog chooseLanguageDialog = new ChooseLanguageDialog();
        chooseLanguageDialog.setListener(new ChooseLanguageDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog.showChooseLanguage.1
            @Override // com.movieboxpro.android.view.dialog.ChooseLanguageDialog.OnLanguageListener
            public void onLanguageSelected(String language, String languageName) {
                Intrinsics.checkNotNullParameter(language, "language");
                Intrinsics.checkNotNullParameter(languageName, "languageName");
                SettingManager.INSTANCE.setSelectOpenSubtitleLanguage(language + "->" + languageName);
                OnOpenSubtitleListener onOpenSubtitleListener = OpenSubtitleDialog.this.listener;
                if (onOpenSubtitleListener != null) {
                    onOpenSubtitleListener.onSelectLanguage();
                }
                OpenSubtitleDialog.this.dismissAllowingStateLoss();
            }
        });
        chooseLanguageDialog.show(getChildFragmentManager(), "ChooseLanguageDialog");
    }

    /* JADX INFO: compiled from: OpenSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 :2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001:B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010&\u001a\u00020\u0007J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(H\u0014J\b\u0010)\u001a\u00020\u001bH\u0014J\b\u0010*\u001a\u00020+H\u0014J\b\u0010,\u001a\u00020\u001bH\u0014J\u0012\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0016\u00101\u001a\b\u0012\u0004\u0012\u00020\u0002022\u0006\u00103\u001a\u00020\u0003H\u0014J\b\u00104\u001a\u000205H\u0014J\u0018\u00106\u001a\u00020.2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0002H\u0014R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR+\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR+\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR+\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u0018\u0010\n\"\u0004\b\u0019\u0010\fR+\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u001b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u000e\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R+\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u001b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u000e\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 ¨\u0006;"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$OpenSubtitleListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/OpenSubtitleItem;", "Lcom/movieboxpro/android/model/OpenSubtitleResponse;", "<init>", "()V", "<set-?>", "", "imdbId", "getImdbId", "()Ljava/lang/String;", "setImdbId", "(Ljava/lang/String;)V", "imdbId$delegate", "Lkotlin/properties/ReadWriteProperty;", "languages", "getLanguages", "setLanguages", "languages$delegate", "languageName", "getLanguageName", "setLanguageName", "languageName$delegate", "id", "getId", "setId", "id$delegate", "", DealWatchNextService.SEASON, "getSeason", "()I", "setSeason", "(I)V", "season$delegate", DealWatchNextService.EPISODE, "getEpisode", "setEpisode", "episode$delegate", "getLanguage", "getServiceData", "Lio/reactivex/Observable;", "initItemLayout", "initScaleValue", "", "gridLayoutSpan", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OpenSubtitleListFragment extends BaseListFragment<OpenSubtitleItem, OpenSubtitleResponse> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, "imdbId", "getImdbId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, "languages", "getLanguages()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, "languageName", "getLanguageName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, "id", "getId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, DealWatchNextService.SEASON, "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(OpenSubtitleListFragment.class, DealWatchNextService.EPISODE, "getEpisode()I", 0))};

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: episode$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty episode;

        /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty id;

        /* JADX INFO: renamed from: imdbId$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty imdbId;

        /* JADX INFO: renamed from: languageName$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty languageName;

        /* JADX INFO: renamed from: languages$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty languages;

        /* JADX INFO: renamed from: season$delegate, reason: from kotlin metadata */
        private final ReadWriteProperty season;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 1;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected float initScaleValue() {
            return 1.0f;
        }

        public OpenSubtitleListFragment() {
            OpenSubtitleListFragment openSubtitleListFragment = this;
            this.imdbId = FragmentArgsKt.arg(openSubtitleListFragment);
            this.languages = FragmentArgsKt.arg(openSubtitleListFragment);
            this.languageName = FragmentArgsKt.arg(openSubtitleListFragment);
            this.id = FragmentArgsKt.arg(openSubtitleListFragment);
            this.season = FragmentArgsKt.arg(openSubtitleListFragment);
            this.episode = FragmentArgsKt.arg(openSubtitleListFragment);
        }

        private final String getImdbId() {
            return (String) this.imdbId.getValue(this, $$delegatedProperties[0]);
        }

        private final void setImdbId(String str) {
            this.imdbId.setValue(this, $$delegatedProperties[0], str);
        }

        private final String getLanguages() {
            return (String) this.languages.getValue(this, $$delegatedProperties[1]);
        }

        private final void setLanguages(String str) {
            this.languages.setValue(this, $$delegatedProperties[1], str);
        }

        private final String getLanguageName() {
            return (String) this.languageName.getValue(this, $$delegatedProperties[2]);
        }

        private final void setLanguageName(String str) {
            this.languageName.setValue(this, $$delegatedProperties[2], str);
        }

        private final String getId() {
            return (String) this.id.getValue(this, $$delegatedProperties[3]);
        }

        private final void setId(String str) {
            this.id.setValue(this, $$delegatedProperties[3], str);
        }

        private final int getSeason() {
            return ((Number) this.season.getValue(this, $$delegatedProperties[4])).intValue();
        }

        private final void setSeason(int i) {
            this.season.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
        }

        private final int getEpisode() {
            return ((Number) this.episode.getValue(this, $$delegatedProperties[5])).intValue();
        }

        private final void setEpisode(int i) {
            this.episode.setValue(this, $$delegatedProperties[5], Integer.valueOf(i));
        }

        /* JADX INFO: compiled from: OpenSubtitleDialog.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$OpenSubtitleListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/OpenSubtitleDialog$OpenSubtitleListFragment;", "imdbId", "", "languages", "languageName", "id", DealWatchNextService.SEASON, "", DealWatchNextService.EPISODE, "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final OpenSubtitleListFragment newInstance(String imdbId, String languages, String languageName, String id, int season, int episode) {
                Intrinsics.checkNotNullParameter(imdbId, "imdbId");
                Intrinsics.checkNotNullParameter(languages, "languages");
                Intrinsics.checkNotNullParameter(languageName, "languageName");
                Intrinsics.checkNotNullParameter(id, "id");
                OpenSubtitleListFragment openSubtitleListFragment = new OpenSubtitleListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("imdbId", imdbId);
                bundle.putString("languages", languages);
                bundle.putString("languageName", languageName);
                bundle.putString("id", id);
                bundle.putInt(DealWatchNextService.SEASON, season);
                bundle.putInt(DealWatchNextService.EPISODE, episode);
                openSubtitleListFragment.setArguments(bundle);
                return openSubtitleListFragment;
            }
        }

        public final String getLanguage() {
            return getLanguages();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.INSTANCE.post("Opensubtitles_search").params(Api.INSTANCE.getOpenSubtitle(getImdbId(), this.mCurrentPage, getLanguages(), 0, 0)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_open_subtitle;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mPageClass = OpenSubtitleResponse.class;
            this.mClass = OpenSubtitleItem.class;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<OpenSubtitleItem> getData(OpenSubtitleResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<OpenSubtitleItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$OpenSubtitleListFragment$$ExternalSyntheticLambda3
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    OpenSubtitleDialog.OpenSubtitleListFragment.onItemClick$lambda$3(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$3(final OpenSubtitleListFragment openSubtitleListFragment, BaseQuickAdapter adapter, View view, int i) {
            String file_id;
            OpenSubtitleFile openSubtitleFile;
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            Intrinsics.checkNotNullParameter(view, "view");
            Object item = adapter.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.OpenSubtitleItem");
            final OpenSubtitleItem openSubtitleItem = (OpenSubtitleItem) item;
            HttpRequest httpRequestPost = HttpRequest.INSTANCE.post("Opensubtitles_download");
            Api api = Api.INSTANCE;
            ArrayList<OpenSubtitleFile> files = openSubtitleItem.getFiles();
            if (files == null || (openSubtitleFile = (OpenSubtitleFile) CollectionsKt.firstOrNull((List) files)) == null || (file_id = openSubtitleFile.getFile_id()) == null) {
                file_id = "";
            }
            Observable<R> observableCompose = httpRequestPost.params(api.getOpenSubtitleUrl(file_id)).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class));
            Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, openSubtitleListFragment), new Function1() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$OpenSubtitleListFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return OpenSubtitleDialog.OpenSubtitleListFragment.onItemClick$lambda$3$lambda$0(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$OpenSubtitleListFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return OpenSubtitleDialog.OpenSubtitleListFragment.onItemClick$lambda$3$lambda$1(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.OpenSubtitleDialog$OpenSubtitleListFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return OpenSubtitleDialog.OpenSubtitleListFragment.onItemClick$lambda$3$lambda$2(this.f$0, openSubtitleItem, (HashMap) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$3$lambda$1(OpenSubtitleListFragment openSubtitleListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            openSubtitleListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$3$lambda$2(OpenSubtitleListFragment openSubtitleListFragment, OpenSubtitleItem openSubtitleItem, HashMap it) {
            OpenSubtitleFile openSubtitleFile;
            Intrinsics.checkNotNullParameter(it, "it");
            openSubtitleListFragment.hideLoadingView();
            String str = (String) it.get(VideoDownloadSQLiteHelper.Columns.FILE_NAME);
            if (str == null) {
                str = "";
            }
            String extensionName = FileUtils.getExtensionName(str);
            ExtrModel extrModel = new ExtrModel();
            extrModel.setZipDownloadLink((String) it.get("link"));
            ArrayList<OpenSubtitleFile> files = openSubtitleItem.getFiles();
            extrModel.setSubFileName(((files == null || (openSubtitleFile = (OpenSubtitleFile) CollectionsKt.firstOrNull((List) files)) == null) ? null : openSubtitleFile.getFile_name()) + "." + extensionName);
            extrModel.setISO639(openSubtitleItem.getLanguage());
            extrModel.setLanguageName(openSubtitleListFragment.getLanguageName());
            PreviewSubtitleDialog.INSTANCE.newInstance(extrModel, extrModel.getSubFileName(), extrModel.getZipDownloadLink(), extrModel.getISO639(), extrModel.getLanguageName(), (openSubtitleListFragment.getSeason() == 0 && openSubtitleListFragment.getEpisode() == 0) ? 1 : 2, openSubtitleListFragment.getId(), openSubtitleListFragment.getSeason(), openSubtitleListFragment.getEpisode()).show(openSubtitleListFragment.getChildFragmentManager(), "PreviewSubtitleDialog");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$3$lambda$0(OpenSubtitleListFragment openSubtitleListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            openSubtitleListFragment.hideLoadingView();
            ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, OpenSubtitleItem item) {
            OpenSubtitleFile openSubtitleFile;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ArrayList<OpenSubtitleFile> files = item.getFiles();
            if (files != null && (openSubtitleFile = (OpenSubtitleFile) CollectionsKt.firstOrNull((List) files)) != null) {
                helper.setText(R.id.tvTitle, openSubtitleFile.getFile_name());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
            Date date = simpleDateFormat.parse(item.getUpload_date());
            Intrinsics.checkNotNullExpressionValue(date, "parse(...)");
            helper.setText(R.id.tvTime, TimeUtils.INSTANCE.formatTime3(date.getTime()));
            TextView textView = (TextView) helper.getView(R.id.tvCount);
            if (item.getDownload_count() == 0) {
                CommonExtKt.gone(textView);
                return;
            }
            textView.setText(item.getDownload_count() + " downloads");
        }
    }
}
