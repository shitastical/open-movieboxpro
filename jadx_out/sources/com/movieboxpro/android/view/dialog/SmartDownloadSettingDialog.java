package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.movieboxpro.android.livedata.DownloadRuleChangedLiveData;
import com.movieboxpro.android.livedata.SmartDownloadChangedLiveData;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogSmartDownloadSettingBinding;
import com.owen.focus.FocusBorder;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SmartDownloadSettingDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J!\u0010\u0018\u001a\u00020\u00132\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u001a\"\u00020\tH\u0002¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SmartDownloadSettingDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogSmartDownloadSettingBinding;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "dialogDimAmount", "", "initListener", "", "initAutoDeleteStatus", "initSmartDownloadStatus", "initData", "initView", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "focusScale", "v", "scale", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SmartDownloadSettingDialog extends BaseBindingCenterDialogFragment {
    private DialogSmartDownloadSettingBinding binding;
    private FocusBorder mFocusBorder;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    protected float dialogDimAmount() {
        return 0.9f;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_smart_download_setting, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = (DialogSmartDownloadSettingBinding) viewDataBindingInflate;
        this.binding = dialogSmartDownloadSettingBinding;
        if (dialogSmartDownloadSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding = null;
        }
        View root = dialogSmartDownloadSettingBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = this.binding;
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding2 = null;
        if (dialogSmartDownloadSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding = null;
        }
        dialogSmartDownloadSettingBinding.flSmartDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadSettingDialog.initListener$lambda$0(this.f$0, view);
            }
        });
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding3 = this.binding;
        if (dialogSmartDownloadSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding3 = null;
        }
        dialogSmartDownloadSettingBinding3.flAutoDelete.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadSettingDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding4 = this.binding;
        if (dialogSmartDownloadSettingBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSmartDownloadSettingBinding2 = dialogSmartDownloadSettingBinding4;
        }
        dialogSmartDownloadSettingBinding2.flQuality.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmartDownloadSettingDialog.initListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(SmartDownloadSettingDialog smartDownloadSettingDialog, View view) {
        SettingManager.INSTANCE.saveSmartDownload(!SettingManager.INSTANCE.getSmartDownload());
        smartDownloadSettingDialog.initSmartDownloadStatus();
        SmartDownloadChangedLiveData.INSTANCE.get().setValue(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(SmartDownloadSettingDialog smartDownloadSettingDialog, View view) {
        SettingManager.INSTANCE.saveAutoDeleteWatched(!SettingManager.INSTANCE.getAutoDeleteWatched());
        smartDownloadSettingDialog.initAutoDeleteStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(SmartDownloadSettingDialog smartDownloadSettingDialog, View view) {
        ((DialogFragment) DownloadResolutionDialog.class.newInstance()).show(smartDownloadSettingDialog.getChildFragmentManager(), Reflection.getOrCreateKotlinClass(DownloadResolutionDialog.class).getSimpleName());
    }

    private final void initAutoDeleteStatus() {
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = null;
        if (SettingManager.INSTANCE.getAutoDeleteWatched()) {
            DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding2 = this.binding;
            if (dialogSmartDownloadSettingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogSmartDownloadSettingBinding = dialogSmartDownloadSettingBinding2;
            }
            dialogSmartDownloadSettingBinding.tvAutoDelete.setText("ON");
            return;
        }
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding3 = this.binding;
        if (dialogSmartDownloadSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSmartDownloadSettingBinding = dialogSmartDownloadSettingBinding3;
        }
        dialogSmartDownloadSettingBinding.tvAutoDelete.setText("OFF");
    }

    private final void initSmartDownloadStatus() {
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = null;
        if (SettingManager.INSTANCE.getSmartDownload()) {
            DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding2 = this.binding;
            if (dialogSmartDownloadSettingBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogSmartDownloadSettingBinding = dialogSmartDownloadSettingBinding2;
            }
            dialogSmartDownloadSettingBinding.tvSmartDownload.setText("ON");
            return;
        }
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding3 = this.binding;
        if (dialogSmartDownloadSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSmartDownloadSettingBinding = dialogSmartDownloadSettingBinding3;
        }
        dialogSmartDownloadSettingBinding.tvSmartDownload.setText("OFF");
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        DownloadRuleChangedLiveData.INSTANCE.get().observeInFragment(this, new SmartDownloadSettingDialog$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SmartDownloadSettingDialog.initData$lambda$3(this.f$0, (String) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$3(SmartDownloadSettingDialog smartDownloadSettingDialog, String str) {
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = smartDownloadSettingDialog.binding;
        if (dialogSmartDownloadSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding = null;
        }
        dialogSmartDownloadSettingBinding.tvQuality.setText(SettingManager.INSTANCE.getDownloadRule());
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        View[] viewArr = new View[3];
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = this.binding;
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding2 = null;
        if (dialogSmartDownloadSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding = null;
        }
        FrameLayout flSmartDownload = dialogSmartDownloadSettingBinding.flSmartDownload;
        Intrinsics.checkNotNullExpressionValue(flSmartDownload, "flSmartDownload");
        viewArr[0] = flSmartDownload;
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding3 = this.binding;
        if (dialogSmartDownloadSettingBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding3 = null;
        }
        FrameLayout flQuality = dialogSmartDownloadSettingBinding3.flQuality;
        Intrinsics.checkNotNullExpressionValue(flQuality, "flQuality");
        viewArr[1] = flQuality;
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding4 = this.binding;
        if (dialogSmartDownloadSettingBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding4 = null;
        }
        FrameLayout flAutoDelete = dialogSmartDownloadSettingBinding4.flAutoDelete;
        Intrinsics.checkNotNullExpressionValue(flAutoDelete, "flAutoDelete");
        viewArr[2] = flAutoDelete;
        setViewFocusListener(viewArr);
        Observable<Long> observableTimer = Observable.timer(200L, TimeUnit.MILLISECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SmartDownloadSettingDialog.initView$lambda$4(this.f$0, (Long) obj);
            }
        }, 15, null);
        initSmartDownloadStatus();
        initAutoDeleteStatus();
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding5 = this.binding;
        if (dialogSmartDownloadSettingBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogSmartDownloadSettingBinding2 = dialogSmartDownloadSettingBinding5;
        }
        dialogSmartDownloadSettingBinding2.tvQuality.setText(SettingManager.INSTANCE.getDownloadRule());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$4(SmartDownloadSettingDialog smartDownloadSettingDialog, Long l) {
        DialogSmartDownloadSettingBinding dialogSmartDownloadSettingBinding = smartDownloadSettingDialog.binding;
        if (dialogSmartDownloadSettingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogSmartDownloadSettingBinding = null;
        }
        dialogSmartDownloadSettingBinding.flSmartDownload.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$6$lambda$5(SmartDownloadSettingDialog smartDownloadSettingDialog, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            smartDownloadSettingDialog.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = smartDownloadSettingDialog.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.SmartDownloadSettingDialog$$ExternalSyntheticLambda1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    SmartDownloadSettingDialog.setViewFocusListener$lambda$6$lambda$5(this.f$0, view2, z);
                }
            });
        }
    }
}
