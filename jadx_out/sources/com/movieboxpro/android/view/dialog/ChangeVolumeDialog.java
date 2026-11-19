package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChangeVolumeBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChangeVolumeDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0014\u0015B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u000b\u001a\u00020\nH\u0016J\u001a\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\nH\u0003J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogChangeVolumeBinding;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$OnVolumeChangeListener;", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "setListener", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initData", "setVolume", "initListener", "Companion", "OnVolumeChangeListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChangeVolumeDialog extends BaseTransparentDialogFragment<DialogChangeVolumeBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private OnVolumeChangeListener listener;
    private int volume;

    /* JADX INFO: compiled from: ChangeVolumeDialog.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$OnVolumeChangeListener;", "", "onAddVolume", "", "onSubVolume", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnVolumeChangeListener {
        void onAddVolume();

        void onSubVolume();
    }

    public ChangeVolumeDialog() {
        super(R.layout.dialog_change_volume);
        this.volume = 100;
    }

    public final void setListener(OnVolumeChangeListener listener) {
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
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.7d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.2f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    private final void initData() {
        Bundle arguments = getArguments();
        this.volume = arguments != null ? arguments.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, 100) : 100;
        setVolume();
    }

    private final void setVolume() {
        int i = this.volume;
        if (i == 300) {
            getBinding().tvVolume.setText("300% (max)");
            return;
        }
        if (i == 100) {
            getBinding().tvVolume.setText("100% (min)");
            return;
        }
        getBinding().tvVolume.setText(this.volume + "%");
    }

    private final void initListener() {
        getBinding().ivAdd.requestFocus();
        getBinding().ivAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChangeVolumeDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeVolumeDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        getBinding().tvSub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ChangeVolumeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeVolumeDialog.initListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ChangeVolumeDialog changeVolumeDialog, View view) {
        int i = changeVolumeDialog.volume;
        if (i == 300) {
            return;
        }
        changeVolumeDialog.volume = i + 10;
        changeVolumeDialog.setVolume();
        OnVolumeChangeListener onVolumeChangeListener = changeVolumeDialog.listener;
        if (onVolumeChangeListener != null) {
            onVolumeChangeListener.onAddVolume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(ChangeVolumeDialog changeVolumeDialog, View view) {
        int i = changeVolumeDialog.volume;
        if (i == 100) {
            return;
        }
        changeVolumeDialog.volume = i - 10;
        changeVolumeDialog.setVolume();
        OnVolumeChangeListener onVolumeChangeListener = changeVolumeDialog.listener;
        if (onVolumeChangeListener != null) {
            onVolumeChangeListener.onSubVolume();
        }
    }

    /* JADX INFO: compiled from: ChangeVolumeDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChangeVolumeDialog;", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChangeVolumeDialog newInstance(int volume) {
            ChangeVolumeDialog changeVolumeDialog = new ChangeVolumeDialog();
            Bundle bundle = new Bundle();
            bundle.putInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, volume);
            changeVolumeDialog.setArguments(bundle);
            return changeVolumeDialog;
        }
    }
}
