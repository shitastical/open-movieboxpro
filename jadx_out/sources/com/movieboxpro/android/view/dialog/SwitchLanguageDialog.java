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
import com.movieboxpro.android.utils.PrefsUtils;
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
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SwitchLanguageDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u001a\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R6\u0010\u000b\u001a*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r0\fj\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SwitchLanguageDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentSwitchLanguageBinding;", "<init>", "()V", "keyAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "languageAdapter", "Lcom/movieboxpro/android/model/LanguageModel;", "map", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "selectedPosition", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/SwitchLanguageDialog$OnLanguageListener;", "setListener", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initView", "initListener", "initData", "focusScale", "v", "scale", "", "OnLanguageListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SwitchLanguageDialog extends BaseTransparentDialogFragment<FragmentSwitchLanguageBinding> {
    private BaseQuickAdapter<String, BaseViewHolder> keyAdapter;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private OnLanguageListener listener;
    private FocusBorder mFocusBorder;
    private final HashMap<String, List<LanguageModel>> map;
    private int selectedPosition;

    /* JADX INFO: compiled from: SwitchLanguageDialog.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SwitchLanguageDialog$OnLanguageListener;", "", "onLanguageSelected", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnLanguageListener {
        void onLanguageSelected();
    }

    public SwitchLanguageDialog() {
        super(R.layout.fragment_switch_language);
        this.map = new HashMap<>();
    }

    public final void setListener(OnLanguageListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
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
        getBinding().rvWordIndex.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog.initListener.1
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
                SwitchLanguageDialog.this.focusScale(itemView, 1.15f);
                BaseQuickAdapter baseQuickAdapter = SwitchLanguageDialog.this.languageAdapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
                    baseQuickAdapter = null;
                }
                HashMap map = SwitchLanguageDialog.this.map;
                BaseQuickAdapter baseQuickAdapter3 = SwitchLanguageDialog.this.keyAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAdapter");
                } else {
                    baseQuickAdapter2 = baseQuickAdapter3;
                }
                baseQuickAdapter.setList((Collection) map.get(baseQuickAdapter2.getData().get(position)));
            }
        });
        getBinding().rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog.initListener.2
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
                SwitchLanguageDialog.this.focusScale(itemView, 1.15f);
            }
        });
        getBinding().rvWordIndex.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SwitchLanguageDialog.initListener$lambda$1(this.f$0);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(SwitchLanguageDialog switchLanguageDialog) {
        switchLanguageDialog.getBinding().rvWordIndex.setSelection(switchLanguageDialog.selectedPosition);
    }

    private final void initData() {
        String[] stringArray = getResources().getStringArray(R.array.languages);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            Intrinsics.checkNotNull(str);
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() == 2) {
                String strValueOf = String.valueOf(Character.toLowerCase(StringsKt.first((CharSequence) listSplit$default.get(1))));
                if (!this.map.containsKey(strValueOf)) {
                    this.map.put(strValueOf, new ArrayList());
                    arrayList.add(strValueOf);
                }
            }
        }
        String str2 = "";
        for (String str3 : stringArray) {
            Intrinsics.checkNotNull(str3);
            List listSplit$default2 = StringsKt.split$default((CharSequence) str3, new String[]{"->"}, false, 0, 6, (Object) null);
            if (listSplit$default2.size() == 2) {
                String str4 = (String) listSplit$default2.get(0);
                String strValueOf2 = String.valueOf(Character.toLowerCase(StringsKt.first((CharSequence) listSplit$default2.get(1))));
                if (Intrinsics.areEqual(str4, App.deviceLang)) {
                    List<LanguageModel> list = this.map.get(strValueOf2);
                    if (list != null) {
                        list.add(new LanguageModel(str4, (String) listSplit$default2.get(1), true));
                    }
                    str2 = strValueOf2;
                } else {
                    List<LanguageModel> list2 = this.map.get(strValueOf2);
                    if (list2 != null) {
                        list2.add(new LanguageModel(str4, (String) listSplit$default2.get(1), false));
                    }
                }
            }
        }
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual((String) obj, str2)) {
                this.selectedPosition = i;
                break;
            }
            i = i2;
        }
        this.keyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(arrayList, R.layout.adapter_key_item) { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog.initData.4
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
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_switch_language_item) { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog.initData.5
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
                    CommonExtKt.textColor(textView, com.dueeeke.videocontroller.R.color.white90_transparent);
                }
            }
        };
        getBinding().rvWordIndex.addItemDecoration(new LinearItemDecoration(0, 0, false));
        TvRecyclerView tvRecyclerView = getBinding().rvWordIndex;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.keyAdapter;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
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
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.SwitchLanguageDialog$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i3) {
                SwitchLanguageDialog.initData$lambda$6(this.f$0, baseQuickAdapter5, view, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$6(SwitchLanguageDialog switchLanguageDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = switchLanguageDialog.languageAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            baseQuickAdapter2 = null;
        }
        String language = baseQuickAdapter2.getData().get(i).getLanguage();
        PrefsUtils.getInstance().putString("language", language);
        App.deviceLang = language;
        switchLanguageDialog.dismiss();
        OnLanguageListener onLanguageListener = switchLanguageDialog.listener;
        if (onLanguageListener != null) {
            onLanguageListener.onLanguageSelected();
        }
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
