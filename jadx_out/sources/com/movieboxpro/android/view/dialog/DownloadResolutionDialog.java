package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.livedata.DownloadRuleChangedLiveData;
import com.movieboxpro.android.model.DownloadQualityRule;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogDownloadResolutionBinding;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DownloadResolutionDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/dialog/DownloadResolutionDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogDownloadResolutionBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "", "initData", "dialogDimAmount", "", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadResolutionDialog extends BaseBindingCenterDialogFragment {
    private DialogDownloadResolutionBinding binding;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    protected float dialogDimAmount() {
        return 1.0f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_download_resolution, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogDownloadResolutionBinding dialogDownloadResolutionBinding = (DialogDownloadResolutionBinding) viewDataBindingInflate;
        this.binding = dialogDownloadResolutionBinding;
        if (dialogDownloadResolutionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDownloadResolutionBinding = null;
        }
        View root = dialogDownloadResolutionBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.movieboxpro.android.view.dialog.DownloadResolutionDialog$initView$adapter$1] */
    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
        final ArrayList arrayList = new ArrayList();
        for (String str : StringsKt.split$default((CharSequence) SettingManager.INSTANCE.getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null)) {
            arrayList.add(new DownloadQualityRule(str, str));
        }
        final int i = R.layout.adapter_download_quality;
        final ?? r2 = new CommonBaseAdapter<DownloadQualityRule>(arrayList, i) { // from class: com.movieboxpro.android.view.dialog.DownloadResolutionDialog$initView$adapter$1
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, DownloadQualityRule item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) holder.getView(R.id.tvName);
                if (Intrinsics.areEqual(item.getQuality(), "ORG")) {
                    SpanUtils spanUtilsWith = SpanUtils.with(textView);
                    Intrinsics.checkNotNullExpressionValue(spanUtilsWith, "with(...)");
                    String name = item.getName();
                    SpanUtils bold = CommonExtKt.addText(spanUtilsWith, name != null ? name : "", 24, R.color.white_30alpha).setBold();
                    Intrinsics.checkNotNullExpressionValue(bold, "setBold(...)");
                    CommonExtKt.addText(bold, " (Original Video)", 20, R.color.white_70alpha).create();
                } else {
                    SpanUtils spanUtilsWith2 = SpanUtils.with(textView);
                    Intrinsics.checkNotNullExpressionValue(spanUtilsWith2, "with(...)");
                    String name2 = item.getName();
                    CommonExtKt.addText(spanUtilsWith2, name2 != null ? name2 : "", 24, R.color.white_30alpha).setBold().create();
                }
                ImageView imageView = (ImageView) holder.getView(R.id.ivMove);
                if (holder.getAdapterPosition() == 0) {
                    imageView.setImageResource(R.mipmap.ic_move_top_disable);
                } else {
                    imageView.setImageResource(R.mipmap.ic_move_top);
                }
            }
        };
        r2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.DownloadResolutionDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                DownloadResolutionDialog.initView$lambda$2(r2, baseQuickAdapter, view, i2);
            }
        });
        DialogDownloadResolutionBinding dialogDownloadResolutionBinding = this.binding;
        DialogDownloadResolutionBinding dialogDownloadResolutionBinding2 = null;
        if (dialogDownloadResolutionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogDownloadResolutionBinding = null;
        }
        dialogDownloadResolutionBinding.recyclerView.setSpacingWithMargins(CommonExtKt.dp2Px(15), 0);
        DialogDownloadResolutionBinding dialogDownloadResolutionBinding3 = this.binding;
        if (dialogDownloadResolutionBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogDownloadResolutionBinding2 = dialogDownloadResolutionBinding3;
        }
        dialogDownloadResolutionBinding2.recyclerView.setAdapter((RecyclerView.Adapter) r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(DownloadResolutionDialog$initView$adapter$1 downloadResolutionDialog$initView$adapter$1, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "view");
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        Collections.swap(downloadResolutionDialog$initView$adapter$1.getData(), i, i2);
        downloadResolutionDialog$initView$adapter$1.notifyItemMoved(i, i2);
        int i3 = 0;
        if (i == 1) {
            downloadResolutionDialog$initView$adapter$1.notifyItemChanged(0);
            downloadResolutionDialog$initView$adapter$1.notifyItemChanged(1);
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : downloadResolutionDialog$initView$adapter$1.getData()) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            DownloadQualityRule downloadQualityRule = (DownloadQualityRule) obj;
            if (i3 != downloadResolutionDialog$initView$adapter$1.getData().size() - 1) {
                sb.append(downloadQualityRule.getQuality());
                sb.append(",");
            } else {
                sb.append(downloadQualityRule.getQuality());
            }
            i3 = i4;
        }
        SettingManager settingManager = SettingManager.INSTANCE;
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        settingManager.saveDownloadRule(string);
        DownloadRuleChangedLiveData.INSTANCE.get().setValue(sb.toString());
    }
}
