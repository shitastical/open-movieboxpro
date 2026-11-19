package com.movieboxpro.android.view.fragment.account;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.model.ImageHost;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChooseImageHostDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChooseImageHostDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseVerticalListDialogFragment;", "Lcom/movieboxpro/android/model/ImageHost;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/account/ChooseImageHostDialogFragment$OnChooseImageHost;", "setListener", "", "setItemLayout", "", "onStart", "onItemClicked", "position", "initData", "initItemView", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "OnChooseImageHost", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseImageHostDialogFragment extends BaseVerticalListDialogFragment<ImageHost> {
    private OnChooseImageHost listener;

    /* JADX INFO: compiled from: ChooseImageHostDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/ChooseImageHostDialogFragment$OnChooseImageHost;", "", "onChooseImageHost", "", "imageHost", "Lcom/movieboxpro/android/model/ImageHost;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnChooseImageHost {
        void onChooseImageHost(ImageHost imageHost);
    }

    public final void setListener(OnChooseImageHost listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public int setItemLayout() {
        return R.layout.adapter_image_host_item;
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

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void onItemClicked(int position) {
        OnChooseImageHost onChooseImageHost = this.listener;
        if (onChooseImageHost != null) {
            onChooseImageHost.onChooseImageHost(getAdapter().getItem(position));
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initData() {
        getAdapter().setList(JSONObject.parseArray(ConfigUtils.readStringConfig("image_host"), ImageHost.class));
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initItemView(BaseViewHolder helper, ImageHost item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvHost, item.getOriginal() + ShellUtil.COMMAND_LINE_END + item.getThumb());
    }
}
