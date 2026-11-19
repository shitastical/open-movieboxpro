package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChooseLanguageDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseVerticalListDialogFragment;", "Lcom/movieboxpro/android/model/BaseMediaModel$MoreLanguage;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "onStart", "", "onAttach", "context", "Landroid/content/Context;", "setItemLayout", "", "initItemView", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onItemClicked", "position", "initData", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseLanguageDialogFragment extends BaseVerticalListDialogFragment<BaseMediaModel.MoreLanguage> {
    public static final String TYPE = "ChooseLanguage";
    private CommonItemClickListener listener;

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initData() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment, com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = DensityUtils.dp2px(App.getContext(), 460.0f);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof CommonItemClickListener) {
            this.listener = (CommonItemClickListener) context;
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public int setItemLayout() {
        return R.layout.adapter_choose_language_item;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initItemView(BaseViewHolder helper, BaseMediaModel.MoreLanguage item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.title);
        helper.setText(R.id.tvLanguage, item.lang);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void onItemClicked(int position) {
        CommonItemClickListener commonItemClickListener = this.listener;
        if (commonItemClickListener != null) {
            commonItemClickListener.onItemClicked(position, TYPE);
        }
    }
}
