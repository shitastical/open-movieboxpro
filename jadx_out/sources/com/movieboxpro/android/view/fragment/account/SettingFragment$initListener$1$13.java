package com.movieboxpro.android.view.fragment.account;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.model.ImageHost;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/movieboxpro/android/view/fragment/account/SettingFragment$initListener$1$13", "Lcom/movieboxpro/android/view/fragment/account/ChooseImageHostDialogFragment$OnChooseImageHost;", "onChooseImageHost", "", "imageHost", "Lcom/movieboxpro/android/model/ImageHost;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SettingFragment$initListener$1$13 implements ChooseImageHostDialogFragment.OnChooseImageHost {
    final /* synthetic */ SettingFragment this$0;

    SettingFragment$initListener$1$13(SettingFragment settingFragment) {
        this.this$0 = settingFragment;
    }

    @Override // com.movieboxpro.android.view.fragment.account.ChooseImageHostDialogFragment.OnChooseImageHost
    public void onChooseImageHost(ImageHost imageHost) {
        Intrinsics.checkNotNullParameter(imageHost, "imageHost");
        PrefsUtils.getInstance().putString("image_host", JSONObject.toJSONString(imageHost));
        new MsgHintDialog.Builder(this.this$0.getContext()).setContent("The app will close. Please reopen it.").setNegativeGone().setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.account.SettingFragment$initListener$1$13$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                System.exit(0);
            }
        }).create().show();
    }
}
