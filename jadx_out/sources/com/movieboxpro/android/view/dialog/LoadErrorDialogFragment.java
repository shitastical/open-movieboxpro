package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.androidtv.databinding.LoadErrorLayoutBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoadErrorDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\r\u001a\u00020\tH\u0016J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/dialog/LoadErrorDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "binding", "Lcom/movieboxpro/androidtv/databinding/LoadErrorLayoutBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setActionListener", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadErrorDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private LoadErrorLayoutBinding binding;
    private DialogAction.ActionListener listener;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
    }

    public final void setActionListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
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
            attributes.dimAmount = 0.8f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        LoadErrorLayoutBinding loadErrorLayoutBindingInflate = LoadErrorLayoutBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(loadErrorLayoutBindingInflate, "inflate(...)");
        this.binding = loadErrorLayoutBindingInflate;
        if (loadErrorLayoutBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            loadErrorLayoutBindingInflate = null;
        }
        return loadErrorLayoutBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        LoadErrorLayoutBinding loadErrorLayoutBinding = null;
        String string = arguments != null ? arguments.getString(NotificationCompat.CATEGORY_MESSAGE) : null;
        LoadErrorLayoutBinding loadErrorLayoutBinding2 = this.binding;
        if (loadErrorLayoutBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            loadErrorLayoutBinding2 = null;
        }
        loadErrorLayoutBinding2.tvMsg.setText("Load failed:" + string);
        LoadErrorLayoutBinding loadErrorLayoutBinding3 = this.binding;
        if (loadErrorLayoutBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            loadErrorLayoutBinding = loadErrorLayoutBinding3;
        }
        loadErrorLayoutBinding.tvRefresh.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.LoadErrorDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LoadErrorDialogFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(LoadErrorDialogFragment loadErrorDialogFragment, View view) {
        DialogAction.ActionListener actionListener = loadErrorDialogFragment.listener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        loadErrorDialogFragment.dismiss();
    }

    /* JADX INFO: compiled from: LoadErrorDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/LoadErrorDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/LoadErrorDialogFragment;", NotificationCompat.CATEGORY_MESSAGE, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LoadErrorDialogFragment newInstance(String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            LoadErrorDialogFragment loadErrorDialogFragment = new LoadErrorDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_MESSAGE, msg);
            loadErrorDialogFragment.setArguments(bundle);
            return loadErrorDialogFragment;
        }
    }
}
