package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.LanguageModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentSwitchLanguageBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChooseCountryDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002()B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\u0019\u001a\u00020\u001a2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u001a\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u001aH\u0002J\b\u0010\"\u001a\u00020\u001aH\u0002J\b\u0010#\u001a\u00020\u001aH\u0002J\u0018\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r0\fj\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentSwitchLanguageBinding;", "<init>", "()V", "keyAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "languageAdapter", "Lcom/movieboxpro/android/model/LanguageModel;", "map", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "country", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/FilterCountry;", "Lkotlin/collections/ArrayList;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "selectedPosition", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog$OnLanguageListener;", "setListener", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "initListener", "initData", "focusScale", "v", "scale", "", "Companion", "OnLanguageListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseCountryDialog extends BaseTransparentDialogFragment<FragmentSwitchLanguageBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ArrayList<FilterCountry> country;
    private BaseQuickAdapter<String, BaseViewHolder> keyAdapter;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private OnLanguageListener listener;
    private FocusBorder mFocusBorder;
    private final HashMap<String, List<LanguageModel>> map;
    private int selectedPosition;

    /* JADX INFO: compiled from: ChooseCountryDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog$OnLanguageListener;", "", "onLanguageSelected", "", "language", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnLanguageListener {
        void onLanguageSelected(String language);
    }

    public ChooseCountryDialog() {
        super(R.layout.fragment_switch_language);
        this.map = new HashMap<>();
        this.country = new ArrayList<>();
    }

    public final void setListener(ArrayList<FilterCountry> country, OnLanguageListener listener) {
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        this.country = country;
    }

    /* JADX INFO: compiled from: ChooseCountryDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseCountryDialog;", "language", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseCountryDialog newInstance(String language) {
            Intrinsics.checkNotNullParameter(language, "language");
            ChooseCountryDialog chooseCountryDialog = new ChooseCountryDialog();
            Bundle bundle = new Bundle();
            bundle.putString("language", language);
            chooseCountryDialog.setArguments(bundle);
            return chooseCountryDialog;
        }
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
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        initData();
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
    }

    private final void initListener() {
        getBinding().rvWordIndex.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog.initListener.1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                ChooseCountryDialog.this.focusScale(itemView, 1.15f);
                BaseQuickAdapter baseQuickAdapter = ChooseCountryDialog.this.languageAdapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                    baseQuickAdapter = null;
                }
                HashMap map = ChooseCountryDialog.this.map;
                BaseQuickAdapter baseQuickAdapter3 = ChooseCountryDialog.this.keyAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter.setList((Collection) map.get(baseQuickAdapter2.getData().get(position)));
            }
        });
        getBinding().rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog.initListener.2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                ChooseCountryDialog.this.focusScale(itemView, 1.15f);
            }
        });
        getBinding().rvWordIndex.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ChooseCountryDialog.initListener$lambda$1(this.f$0);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChooseCountryDialog chooseCountryDialog) {
        chooseCountryDialog.getBinding().rvWordIndex.setSelection(chooseCountryDialog.selectedPosition);
    }

    private final void initData() {
        Bundle arguments = getArguments();
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = null;
        String string = arguments != null ? arguments.getString("language") : null;
        ArrayList arrayList = new ArrayList();
        ArrayList<FilterCountry> arrayList2 = this.country;
        if (arrayList2.size() > 1) {
            CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog$initData$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    String country = ((FilterCountry) t).getCountry();
                    Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
                    Character chValueOf = Character.valueOf(StringsKt.first(country));
                    String country2 = ((FilterCountry) t2).getCountry();
                    Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
                    return ComparisonsKt.compareValues(chValueOf, Character.valueOf(StringsKt.first(country2)));
                }
            });
        }
        Iterator<T> it = this.country.iterator();
        while (it.hasNext()) {
            String country = ((FilterCountry) it.next()).getCountry();
            Intrinsics.checkNotNullExpressionValue(country, "getCountry(...)");
            String strValueOf = String.valueOf(StringsKt.first(country));
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String lowerCase = strValueOf.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (!this.map.containsKey(lowerCase)) {
                this.map.put(lowerCase, new ArrayList());
                arrayList.add(lowerCase);
            }
        }
        String str = "";
        for (FilterCountry filterCountry : this.country) {
            String country2 = filterCountry.getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
            String strValueOf2 = String.valueOf(StringsKt.first(country2));
            Intrinsics.checkNotNull(strValueOf2, "null cannot be cast to non-null type java.lang.String");
            String lowerCase2 = strValueOf2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (Intrinsics.areEqual(filterCountry.getCountry(), string)) {
                List<LanguageModel> list = this.map.get(lowerCase2);
                if (list != null) {
                    list.add(new LanguageModel(filterCountry.getCountry(), filterCountry.getCountry(), true));
                }
                str = lowerCase2;
            } else {
                List<LanguageModel> list2 = this.map.get(lowerCase2);
                if (list2 != null) {
                    list2.add(new LanguageModel(filterCountry.getCountry(), filterCountry.getCountry(), false));
                }
            }
        }
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual((String) obj, str)) {
                this.selectedPosition = i;
                break;
            }
            i = i2;
        }
        this.keyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(arrayList, R.layout.adapter_key_item) { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog.initData.5
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.textView, item);
            }
        };
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_switch_language_item) { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog.initData.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, LanguageModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(R.id.textView);
                textView.setText(item.getFullLanguage());
                if (item.isSelect()) {
                    CommonExtKt.textColor(textView, R.color.color_main_yellow);
                } else {
                    CommonExtKt.textColor(textView, com.dueeeke.videocontroller.R.color.white30_transparent);
                }
            }
        };
        getBinding().rvWordIndex.addItemDecoration(new LinearItemDecoration(0, 0, false));
        TvRecyclerView tvRecyclerView = getBinding().rvWordIndex;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = this.keyAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        getBinding().rvLanguage.addItemDecoration(new GridSpacingItemDecoration(4, DensityUtils.dp2px(App.getContext(), 32.0f), true));
        TvRecyclerView tvRecyclerView2 = getBinding().rvLanguage;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = this.languageAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = this.languageAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseCountryDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i3) {
                ChooseCountryDialog.initData$lambda$7(this.f$0, baseQuickAdapter5, view, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$7(ChooseCountryDialog chooseCountryDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        OnLanguageListener onLanguageListener = chooseCountryDialog.listener;
        if (onLanguageListener != null) {
            BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = chooseCountryDialog.languageAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                baseQuickAdapter2 = null;
            }
            String language = baseQuickAdapter2.getData().get(i).getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
            onLanguageListener.onLanguageSelected(language);
        }
        chooseCountryDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }
}
