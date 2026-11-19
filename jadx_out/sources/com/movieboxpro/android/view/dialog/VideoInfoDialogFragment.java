package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.VideoInfoDialogLayoutBinding;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: VideoInfoDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\n\u001a\u00020\tH\u0016J\u001a\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0016\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoInfoDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/VideoInfoDialogLayoutBinding;", "<init>", "()V", "info", "", "desc", "updateInfo", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showWithText", "manager", "Landroidx/fragment/app/FragmentManager;", "text", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VideoInfoDialogFragment extends BaseTransparentDialogFragment<VideoInfoDialogLayoutBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String desc;
    private String info;

    public final void showWithText(FragmentManager manager, String text) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(text, "text");
    }

    public VideoInfoDialogFragment() {
        super(R.layout.video_info_dialog_layout);
        this.info = "";
        this.desc = "";
    }

    /* JADX INFO: compiled from: VideoInfoDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/VideoInfoDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/VideoInfoDialogFragment;", "model", "Lcom/movieboxpro/android/model/BaseMediaModel;", "desc", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoInfoDialogFragment newInstance(BaseMediaModel model, String desc) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            VideoInfoDialogFragment videoInfoDialogFragment = new VideoInfoDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            bundle.putString("desc", desc);
            videoInfoDialogFragment.setArguments(bundle);
            return videoInfoDialogFragment;
        }
    }

    public final void updateInfo(String info) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.info = info;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8f);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable("model") : null;
        BaseMediaModel baseMediaModel = serializable instanceof BaseMediaModel ? (BaseMediaModel) serializable : null;
        Bundle arguments2 = getArguments();
        getBinding().tvDetail.setText(arguments2 != null ? arguments2.getString("desc") : null);
        TextView textView = getBinding().tvDirector;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Directors:%s", Arrays.copyOf(new Object[]{baseMediaModel != null ? baseMediaModel.director : null}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        if (baseMediaModel instanceof TvDetail) {
            TextView textView2 = getBinding().tvStars;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format("Stars:%s", Arrays.copyOf(new Object[]{((TvDetail) baseMediaModel).actors}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            textView2.setText(str2);
            return;
        }
        if (baseMediaModel instanceof MovieDetail) {
            TextView textView3 = getBinding().tvStars;
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str3 = String.format("Stars:%s", Arrays.copyOf(new Object[]{((MovieDetail) baseMediaModel).actors}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            textView3.setText(str3);
        }
    }
}
