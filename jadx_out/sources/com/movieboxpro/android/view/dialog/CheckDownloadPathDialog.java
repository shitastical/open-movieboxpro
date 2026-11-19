package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.databinding.DialogCheckDownloadPathBinding;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: CheckDownloadPathDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/view/dialog/CheckDownloadPathDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogCheckDownloadPathBinding;", "<set-?>", "", FileDownloadService.PARAMS_KEY_PATH, "getPath", "()Ljava/lang/String;", "setPath", "(Ljava/lang/String;)V", "path$delegate", "Lkotlin/properties/ReadWriteProperty;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "setListener", "", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "initListener", "initData", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CheckDownloadPathDialog extends BaseBindingCenterDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(CheckDownloadPathDialog.class, FileDownloadService.PARAMS_KEY_PATH, "getPath()Ljava/lang/String;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DialogCheckDownloadPathBinding binding;
    private DialogAction.ActionListener listener;

    /* JADX INFO: renamed from: path$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty path = FragmentArgsKt.arg(this);

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPath() {
        return (String) this.path.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPath(String str) {
        this.path.setValue(this, $$delegatedProperties[0], str);
    }

    public final void setListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialogOnCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkNotNullExpressionValue(dialogOnCreateDialog, "onCreateDialog(...)");
        dialogOnCreateDialog.setCancelable(false);
        dialogOnCreateDialog.setCanceledOnTouchOutside(false);
        return dialogOnCreateDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogCheckDownloadPathBinding dialogCheckDownloadPathBindingInflate = DialogCheckDownloadPathBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(dialogCheckDownloadPathBindingInflate, "inflate(...)");
        this.binding = dialogCheckDownloadPathBindingInflate;
        if (dialogCheckDownloadPathBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogCheckDownloadPathBindingInflate = null;
        }
        return dialogCheckDownloadPathBindingInflate.getRoot();
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.view.dialog.CheckDownloadPathDialog$initData$1, reason: invalid class name */
    /* JADX INFO: compiled from: CheckDownloadPathDialog.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.dialog.CheckDownloadPathDialog$initData$1", f = "CheckDownloadPathDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CheckDownloadPathDialog.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                File file = new File(CheckDownloadPathDialog.this.getPath());
                if (FileUtils.getAvailableSpace(CheckDownloadPathDialog.this.getPath()) < IjkMediaMeta.AV_CH_WIDE_RIGHT) {
                    DialogAction.ActionListener actionListener = CheckDownloadPathDialog.this.listener;
                    if (actionListener != null) {
                        actionListener.onClick();
                    }
                } else {
                    File file2 = new File(CheckDownloadPathDialog.this.getPath(), "temp.data");
                    FileUtils.createOrExistsDir(file);
                    file2.createNewFile();
                    App.getContext().getContentResolver().openFileDescriptor(Uri.fromFile(file2), "rw");
                }
            } catch (Exception e) {
                ToastUtils.showShort("Check failed:" + e.getMessage(), new Object[0]);
                CheckDownloadPathDialog.this.dismissAllowingStateLoss();
                DialogAction.ActionListener actionListener2 = CheckDownloadPathDialog.this.listener;
                if (actionListener2 != null) {
                    actionListener2.onClick();
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: compiled from: CheckDownloadPathDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/CheckDownloadPathDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/CheckDownloadPathDialog;", FileDownloadService.PARAMS_KEY_PATH, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CheckDownloadPathDialog newInstance(String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            CheckDownloadPathDialog checkDownloadPathDialog = new CheckDownloadPathDialog();
            checkDownloadPathDialog.setPath(path);
            return checkDownloadPathDialog;
        }
    }
}
