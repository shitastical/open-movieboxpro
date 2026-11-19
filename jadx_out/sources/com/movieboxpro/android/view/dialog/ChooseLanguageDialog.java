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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChooseLanguageDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002$%B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u001a\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r0\fj\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentSwitchLanguageBinding;", "<init>", "()V", "keyAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "languageAdapter", "Lcom/movieboxpro/android/model/LanguageModel;", "map", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "selectedPosition", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog$OnLanguageListener;", "setListener", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "initListener", "initData", "focusScale", "v", "scale", "", "Companion", "OnLanguageListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseLanguageDialog extends BaseTransparentDialogFragment<FragmentSwitchLanguageBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<String, BaseViewHolder> keyAdapter;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private OnLanguageListener listener;
    private FocusBorder mFocusBorder;
    private final HashMap<String, List<LanguageModel>> map;
    private int selectedPosition;

    /* JADX INFO: compiled from: ChooseLanguageDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog$OnLanguageListener;", "", "onLanguageSelected", "", "language", "", "languageName", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnLanguageListener {
        void onLanguageSelected(String language, String languageName);
    }

    public ChooseLanguageDialog() {
        super(R.layout.fragment_switch_language);
        this.map = new HashMap<>();
    }

    public final void setListener(OnLanguageListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: compiled from: ChooseLanguageDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialog;", "language", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseLanguageDialog newInstance(String language) {
            Intrinsics.checkNotNullParameter(language, "language");
            ChooseLanguageDialog chooseLanguageDialog = new ChooseLanguageDialog();
            Bundle bundle = new Bundle();
            bundle.putString("language", language);
            chooseLanguageDialog.setArguments(bundle);
            return chooseLanguageDialog;
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
        getBinding().rvWordIndex.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog.initListener.1
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
                ChooseLanguageDialog.this.focusScale(itemView, 1.15f);
                BaseQuickAdapter baseQuickAdapter = ChooseLanguageDialog.this.languageAdapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                    baseQuickAdapter = null;
                }
                HashMap map = ChooseLanguageDialog.this.map;
                BaseQuickAdapter baseQuickAdapter3 = ChooseLanguageDialog.this.keyAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter.setList((Collection) map.get(baseQuickAdapter2.getData().get(position)));
            }
        });
        getBinding().rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog.initListener.2
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
                ChooseLanguageDialog.this.focusScale(itemView, 1.15f);
            }
        });
        getBinding().rvWordIndex.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ChooseLanguageDialog.initListener$lambda$1(this.f$0);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChooseLanguageDialog chooseLanguageDialog) {
        chooseLanguageDialog.getBinding().rvWordIndex.setSelection(chooseLanguageDialog.selectedPosition);
    }

    private final void initData() {
        int i;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter;
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("language") : null;
        String[] stringArray = getResources().getStringArray(R.array.languages);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        ArrayList arrayList = new ArrayList();
        arrayList.add("#");
        int length = stringArray.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = 2;
            if (i3 >= length) {
                break;
            }
            String str = stringArray[i3];
            Intrinsics.checkNotNull(str);
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() == 2) {
                String strValueOf = String.valueOf(StringsKt.first((CharSequence) listSplit$default.get(1)));
                Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                String lowerCase = strValueOf.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (!this.map.containsKey(lowerCase)) {
                    this.map.put(lowerCase, new ArrayList());
                    arrayList.add(lowerCase);
                }
            }
            i3++;
        }
        int length2 = stringArray.length;
        String str2 = "";
        int i4 = 0;
        while (i4 < length2) {
            String str3 = stringArray[i4];
            Intrinsics.checkNotNull(str3);
            List listSplit$default2 = StringsKt.split$default((CharSequence) str3, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default2.size() == i) {
                String str4 = (String) listSplit$default2.get(i2);
                String strValueOf2 = String.valueOf(StringsKt.first((CharSequence) listSplit$default2.get(1)));
                Intrinsics.checkNotNull(strValueOf2, "null cannot be cast to non-null type java.lang.String");
                String lowerCase2 = strValueOf2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (Intrinsics.areEqual(str4, string)) {
                    List<LanguageModel> list = this.map.get(lowerCase2);
                    if (list != null) {
                        list.add(new LanguageModel(str4, (String) listSplit$default2.get(1), true));
                    }
                    str2 = lowerCase2;
                } else {
                    string = string;
                    List<LanguageModel> list2 = this.map.get(lowerCase2);
                    if (list2 != null) {
                        list2.add(new LanguageModel(str4, (String) listSplit$default2.get(1), false));
                    }
                }
            } else {
                string = string;
            }
            i4++;
            string = string;
            i2 = 0;
            i = 2;
        }
        String[] stringArray2 = getResources().getStringArray(R.array.usual_languages);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        HashMap<String, List<LanguageModel>> map = this.map;
        ArrayList arrayList2 = new ArrayList(stringArray2.length);
        for (String str5 : stringArray2) {
            Intrinsics.checkNotNull(str5);
            List listSplit$default3 = StringsKt.split$default((CharSequence) str5, new String[]{"->"}, false, 0, 6, (Object) null);
            arrayList2.add(new LanguageModel((String) listSplit$default3.get(0), (String) listSplit$default3.get(1)));
        }
        map.put("#", CollectionsKt.toMutableList((Collection) arrayList2));
        int i5 = 0;
        for (Object obj : arrayList) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual((String) obj, str2)) {
                this.selectedPosition = i5;
                break;
            }
            i5 = i6;
        }
        this.keyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(arrayList, R.layout.adapter_key_item) { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog.initData.5
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
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_switch_language_item) { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog.initData.6
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
            baseQuickAdapter = null;
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseLanguageDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i7) {
                ChooseLanguageDialog.initData$lambda$7(this.f$0, baseQuickAdapter5, view, i7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$7(ChooseLanguageDialog chooseLanguageDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        OnLanguageListener onLanguageListener = chooseLanguageDialog.listener;
        if (onLanguageListener != null) {
            BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = chooseLanguageDialog.languageAdapter;
            BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = null;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                baseQuickAdapter2 = null;
            }
            String language = baseQuickAdapter2.getData().get(i).getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
            BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = chooseLanguageDialog.languageAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            } else {
                baseQuickAdapter3 = baseQuickAdapter4;
            }
            String fullLanguage = baseQuickAdapter3.getData().get(i).getFullLanguage();
            Intrinsics.checkNotNullExpressionValue(fullLanguage, "getFullLanguage(...)");
            onLanguageListener.onLanguageSelected(language, fullLanguage);
        }
        chooseLanguageDialog.dismiss();
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
