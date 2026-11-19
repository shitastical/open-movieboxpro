package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.model.AlphaColor;
import com.movieboxpro.android.model.SubtitleColor;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogSubtitleColorBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SubtitleColorDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J \u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\n0\u001dj\b\u0012\u0004\u0012\u00020\n`\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u0011H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SubtitleColorDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogSubtitleColorBinding;", "colorAdapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/model/SubtitleColor;", "alphaAdapter", "Lcom/movieboxpro/android/model/AlphaColor;", "colorPos", "", "alphaPos", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/SubtitleColorDialog$ChooseSubtitleColorListener;", "setListener", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initDialog", "initListener", "getAlphaColor", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "color", "", "initData", "initView", "ChooseSubtitleColorListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubtitleColorDialog extends BaseBindingCenterDialogFragment {
    private CommonBaseAdapter<AlphaColor> alphaAdapter;
    private int alphaPos;
    private DialogSubtitleColorBinding binding;
    private CommonBaseAdapter<SubtitleColor> colorAdapter;
    private int colorPos;
    private ChooseSubtitleColorListener listener;

    /* JADX INFO: compiled from: SubtitleColorDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SubtitleColorDialog$ChooseSubtitleColorListener;", "", "onChooseColor", "", "color", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseSubtitleColorListener {
        void onChooseColor(int color);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    public final void setListener(ChooseSubtitleColorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_subtitle_color, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogSubtitleColorBinding dialogSubtitleColorBinding = (DialogSubtitleColorBinding) viewDataBindingInflate;
        this.binding = dialogSubtitleColorBinding;
        if (dialogSubtitleColorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding = null;
        }
        View root = dialogSubtitleColorBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initDialog() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setDimAmount(0.0f);
        window.setDimAmount(0.0f);
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (getContext() != null) {
            attributes.width = CommonExtKt.dp2Px(760);
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogSubtitleColorBinding dialogSubtitleColorBinding = this.binding;
        if (dialogSubtitleColorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding = null;
        }
        dialogSubtitleColorBinding.tvSave.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitleColorDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        SubtitleColorDialog subtitleColorDialog = this;
        final FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(subtitleColorDialog);
        DialogSubtitleColorBinding dialogSubtitleColorBinding2 = this.binding;
        if (dialogSubtitleColorBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding2 = null;
        }
        dialogSubtitleColorBinding2.rvColor.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog.initListener.2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                CommonBaseAdapter commonBaseAdapter = SubtitleColorDialog.this.colorAdapter;
                CommonBaseAdapter commonBaseAdapter2 = null;
                if (commonBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                    commonBaseAdapter = null;
                }
                SubtitleColor subtitleColor = (SubtitleColor) commonBaseAdapter.getItemOrNull(position);
                if (subtitleColor != null) {
                    SubtitleColorDialog subtitleColorDialog2 = SubtitleColorDialog.this;
                    subtitleColor.setSelect(true);
                    CommonBaseAdapter commonBaseAdapter3 = subtitleColorDialog2.colorAdapter;
                    if (commonBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                        commonBaseAdapter3 = null;
                    }
                    commonBaseAdapter3.notifyItemChanged(position);
                    CommonBaseAdapter commonBaseAdapter4 = subtitleColorDialog2.alphaAdapter;
                    if (commonBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                        commonBaseAdapter4 = null;
                    }
                    commonBaseAdapter4.setList(subtitleColor.getAlphaColors());
                }
                if (SubtitleColorDialog.this.colorPos != position) {
                    CommonBaseAdapter commonBaseAdapter5 = SubtitleColorDialog.this.colorAdapter;
                    if (commonBaseAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                        commonBaseAdapter5 = null;
                    }
                    SubtitleColor subtitleColor2 = (SubtitleColor) commonBaseAdapter5.getItemOrNull(SubtitleColorDialog.this.colorPos);
                    if (subtitleColor2 != null) {
                        SubtitleColorDialog subtitleColorDialog3 = SubtitleColorDialog.this;
                        subtitleColor2.setSelect(false);
                        CommonBaseAdapter commonBaseAdapter6 = subtitleColorDialog3.colorAdapter;
                        if (commonBaseAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                        } else {
                            commonBaseAdapter2 = commonBaseAdapter6;
                        }
                        commonBaseAdapter2.notifyItemChanged(subtitleColorDialog3.colorPos);
                    }
                    SubtitleColorDialog.this.colorPos = position;
                }
                focusBorderBuild.onFocus(itemView, FocusBorder.OptionsFactory.get(1.05f, 1.05f, 0.0f));
            }
        });
        DialogSubtitleColorBinding dialogSubtitleColorBinding3 = this.binding;
        if (dialogSubtitleColorBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding3 = null;
        }
        dialogSubtitleColorBinding3.rvColor.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                focusBorderBuild.setVisible(z);
            }
        });
        DialogSubtitleColorBinding dialogSubtitleColorBinding4 = this.binding;
        if (dialogSubtitleColorBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding4 = null;
        }
        dialogSubtitleColorBinding4.rvAlphaColor.setMemoryFocus(false);
        final FocusBorder focusBorderBuild2 = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(subtitleColorDialog);
        DialogSubtitleColorBinding dialogSubtitleColorBinding5 = this.binding;
        if (dialogSubtitleColorBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding5 = null;
        }
        dialogSubtitleColorBinding5.rvAlphaColor.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                focusBorderBuild2.setVisible(z);
            }
        });
        DialogSubtitleColorBinding dialogSubtitleColorBinding6 = this.binding;
        if (dialogSubtitleColorBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding6 = null;
        }
        dialogSubtitleColorBinding6.rvAlphaColor.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog.initListener.5
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                CommonBaseAdapter commonBaseAdapter = SubtitleColorDialog.this.alphaAdapter;
                CommonBaseAdapter commonBaseAdapter2 = null;
                if (commonBaseAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                    commonBaseAdapter = null;
                }
                AlphaColor alphaColor = (AlphaColor) commonBaseAdapter.getItemOrNull(position);
                if (alphaColor != null) {
                    SubtitleColorDialog subtitleColorDialog2 = SubtitleColorDialog.this;
                    alphaColor.setSelect(true);
                    CommonBaseAdapter commonBaseAdapter3 = subtitleColorDialog2.alphaAdapter;
                    if (commonBaseAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                        commonBaseAdapter3 = null;
                    }
                    commonBaseAdapter3.notifyItemChanged(position);
                }
                if (SubtitleColorDialog.this.alphaPos != position) {
                    CommonBaseAdapter commonBaseAdapter4 = SubtitleColorDialog.this.alphaAdapter;
                    if (commonBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                        commonBaseAdapter4 = null;
                    }
                    AlphaColor alphaColor2 = (AlphaColor) commonBaseAdapter4.getItemOrNull(SubtitleColorDialog.this.alphaPos);
                    if (alphaColor2 != null) {
                        SubtitleColorDialog subtitleColorDialog3 = SubtitleColorDialog.this;
                        alphaColor2.setSelect(false);
                        CommonBaseAdapter commonBaseAdapter5 = subtitleColorDialog3.alphaAdapter;
                        if (commonBaseAdapter5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                        } else {
                            commonBaseAdapter2 = commonBaseAdapter5;
                        }
                        commonBaseAdapter2.notifyItemChanged(subtitleColorDialog3.alphaPos);
                    }
                    SubtitleColorDialog.this.alphaPos = position;
                }
                focusBorderBuild2.onFocus(itemView, FocusBorder.OptionsFactory.get(1.05f, 1.05f, 0.0f));
            }
        });
        String string = SPStaticUtils.getString(Constant.Prefs.SUBTITLE_COLOR, "");
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        String str = string;
        if (str != null && !StringsKt.isBlank(str)) {
            CommonBaseAdapter<SubtitleColor> commonBaseAdapter = this.colorAdapter;
            if (commonBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                commonBaseAdapter = null;
            }
            int i = 0;
            for (Object obj : commonBaseAdapter.getData()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                SubtitleColor subtitleColor = (SubtitleColor) obj;
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) StringsKt.replace$default(subtitleColor.getColor(), "#", "", false, 4, (Object) null), false, 2, (Object) null)) {
                    intRef.element = i;
                    subtitleColor.setSelect(true);
                    ArrayList<AlphaColor> alphaColors = subtitleColor.getAlphaColors();
                    if (alphaColors != null) {
                        int i3 = 0;
                        for (Object obj2 : alphaColors) {
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            AlphaColor alphaColor = (AlphaColor) obj2;
                            if (StringsKt.contains$default((CharSequence) str, (CharSequence) alphaColor.getColor(), false, 2, (Object) null)) {
                                intRef2.element = i3;
                                alphaColor.setSelect(true);
                            }
                            i3 = i4;
                        }
                    }
                }
                i = i2;
            }
        }
        Object objAs = Observable.timer(500L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, new Function0() { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubtitleColorDialog.initListener$lambda$6(this.f$0, intRef, intRef2);
            }
        }, null, null, null, 29, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(SubtitleColorDialog subtitleColorDialog, View view) {
        CommonBaseAdapter<AlphaColor> commonBaseAdapter = subtitleColorDialog.alphaAdapter;
        CommonBaseAdapter<AlphaColor> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
            commonBaseAdapter = null;
        }
        SPStaticUtils.put(Constant.Prefs.SUBTITLE_COLOR, commonBaseAdapter.getItem(subtitleColorDialog.alphaPos).getColor());
        ChooseSubtitleColorListener chooseSubtitleColorListener = subtitleColorDialog.listener;
        if (chooseSubtitleColorListener != null) {
            CommonBaseAdapter<AlphaColor> commonBaseAdapter3 = subtitleColorDialog.alphaAdapter;
            if (commonBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
            } else {
                commonBaseAdapter2 = commonBaseAdapter3;
            }
            chooseSubtitleColorListener.onChooseColor(Color.parseColor("#" + commonBaseAdapter2.getItem(subtitleColorDialog.alphaPos).getColor()));
        }
        subtitleColorDialog.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$6(SubtitleColorDialog subtitleColorDialog, Ref.IntRef intRef, Ref.IntRef intRef2) {
        subtitleColorDialog.colorPos = intRef.element;
        subtitleColorDialog.alphaPos = intRef2.element;
        DialogSubtitleColorBinding dialogSubtitleColorBinding = null;
        if (intRef.element != 0) {
            CommonBaseAdapter<SubtitleColor> commonBaseAdapter = subtitleColorDialog.colorAdapter;
            if (commonBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                commonBaseAdapter = null;
            }
            commonBaseAdapter.getItem(0).setSelect(false);
            CommonBaseAdapter<SubtitleColor> commonBaseAdapter2 = subtitleColorDialog.colorAdapter;
            if (commonBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
                commonBaseAdapter2 = null;
            }
            commonBaseAdapter2.notifyItemChanged(0);
        }
        if (intRef2.element != 0) {
            CommonBaseAdapter<AlphaColor> commonBaseAdapter3 = subtitleColorDialog.alphaAdapter;
            if (commonBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                commonBaseAdapter3 = null;
            }
            commonBaseAdapter3.getItem(0).setSelect(false);
            CommonBaseAdapter<AlphaColor> commonBaseAdapter4 = subtitleColorDialog.alphaAdapter;
            if (commonBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
                commonBaseAdapter4 = null;
            }
            commonBaseAdapter4.notifyItemChanged(0);
        }
        DialogSubtitleColorBinding dialogSubtitleColorBinding2 = subtitleColorDialog.binding;
        if (dialogSubtitleColorBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSubtitleColorBinding = dialogSubtitleColorBinding2;
        }
        dialogSubtitleColorBinding.rvColor.setSelection(subtitleColorDialog.colorPos);
        return Unit.INSTANCE;
    }

    private final ArrayList<AlphaColor> getAlphaColor(String color) {
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf("FF", "E6", "CC", "B3", "99", "80", "66", "4D", "33");
        ArrayList<AlphaColor> arrayList = new ArrayList<>();
        int i = 0;
        for (Object obj : arrayListArrayListOf) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (i == 0) {
                arrayList.add(new AlphaColor(str + color, false));
            } else {
                arrayList.add(new AlphaColor(str + color, false));
            }
            i = i2;
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SubtitleColor("#FFFFFF", getAlphaColor("FFFFFF"), false, 4, null));
        arrayList.add(new SubtitleColor("#E02020", getAlphaColor("E02020"), false, 4, null));
        arrayList.add(new SubtitleColor("#FA6400", getAlphaColor("FA6400"), false, 4, null));
        arrayList.add(new SubtitleColor("#F7B500", getAlphaColor("F7B500"), false, 4, null));
        arrayList.add(new SubtitleColor("#6DD400", getAlphaColor("6DD400"), false, 4, null));
        arrayList.add(new SubtitleColor("#44D7B6", getAlphaColor("44D7B6"), false, 4, null));
        arrayList.add(new SubtitleColor("#32C5FF", getAlphaColor("32C5FF"), false, 4, null));
        arrayList.add(new SubtitleColor("#0091FF", getAlphaColor("0091FF"), false, 4, null));
        arrayList.add(new SubtitleColor("#6236FF", getAlphaColor("6236FF"), false, 4, null));
        this.colorAdapter = new CommonBaseAdapter<SubtitleColor>(arrayList, R.layout.adapter_subtitle_color) { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog.initData.1
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SubtitleColor item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                View view = holder.getView(R.id.viewColor);
                ImageView imageView = (ImageView) holder.getView(R.id.ivSelect);
                if (item.getSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                view.setBackgroundColor(Color.parseColor(item.getColor()));
            }
        };
        DialogSubtitleColorBinding dialogSubtitleColorBinding = this.binding;
        CommonBaseAdapter<AlphaColor> commonBaseAdapter = null;
        if (dialogSubtitleColorBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding = null;
        }
        dialogSubtitleColorBinding.rvColor.setLayoutManager(new V7GridLayoutManager(getContext(), 9));
        DialogSubtitleColorBinding dialogSubtitleColorBinding2 = this.binding;
        if (dialogSubtitleColorBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding2 = null;
        }
        TvRecyclerView tvRecyclerView = dialogSubtitleColorBinding2.rvColor;
        CommonBaseAdapter<SubtitleColor> commonBaseAdapter2 = this.colorAdapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorAdapter");
            commonBaseAdapter2 = null;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter2);
        this.alphaAdapter = new CommonBaseAdapter<AlphaColor>(R.layout.adapter_subtitle_alpha_color, getAlphaColor("FFFFFF")) { // from class: com.movieboxpro.android.view.dialog.SubtitleColorDialog.initData.2
            {
                ArrayList<AlphaColor> arrayList2 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, AlphaColor item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                View view = holder.getView(R.id.viewColor);
                ImageView imageView = (ImageView) holder.getView(R.id.ivSelect);
                if (item.getSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                view.setBackgroundColor(Color.parseColor("#" + item.getColor()));
                holder.setText(R.id.tvAlpha, (100 - (holder.getAdapterPosition() * 10)) + "%");
            }
        };
        DialogSubtitleColorBinding dialogSubtitleColorBinding3 = this.binding;
        if (dialogSubtitleColorBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding3 = null;
        }
        dialogSubtitleColorBinding3.rvAlphaColor.setLayoutManager(new V7GridLayoutManager(getContext(), 9));
        DialogSubtitleColorBinding dialogSubtitleColorBinding4 = this.binding;
        if (dialogSubtitleColorBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSubtitleColorBinding4 = null;
        }
        TvRecyclerView tvRecyclerView2 = dialogSubtitleColorBinding4.rvAlphaColor;
        CommonBaseAdapter<AlphaColor> commonBaseAdapter3 = this.alphaAdapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alphaAdapter");
        } else {
            commonBaseAdapter = commonBaseAdapter3;
        }
        tvRecyclerView2.setAdapter(commonBaseAdapter);
    }
}
