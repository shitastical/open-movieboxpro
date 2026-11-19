package com.movieboxpro.android.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogAudioSynBinding;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioSynDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/fragment/AudioSynDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogAudioSynBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "", "initData", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AudioSynDialog extends BaseBindingCenterDialogFragment {
    private DialogAudioSynBinding binding;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_audio_syn, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogAudioSynBinding dialogAudioSynBinding = (DialogAudioSynBinding) viewDataBindingInflate;
        this.binding = dialogAudioSynBinding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        View root = dialogAudioSynBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogAudioSynBinding dialogAudioSynBinding = this.binding;
        DialogAudioSynBinding dialogAudioSynBinding2 = null;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.AudioSynDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.dismiss();
            }
        });
        DialogAudioSynBinding dialogAudioSynBinding3 = this.binding;
        if (dialogAudioSynBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding3 = null;
        }
        dialogAudioSynBinding3.ivDelayAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.AudioSynDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioSynDialog.initListener$lambda$1(this.f$0, view);
            }
        });
        DialogAudioSynBinding dialogAudioSynBinding4 = this.binding;
        if (dialogAudioSynBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogAudioSynBinding2 = dialogAudioSynBinding4;
        }
        dialogAudioSynBinding2.ivDelaySub.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.AudioSynDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioSynDialog.initListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(AudioSynDialog audioSynDialog, View view) {
        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 700L) + ((long) 100);
        DialogAudioSynBinding dialogAudioSynBinding = audioSynDialog.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.tvDelay.setText((j / 1000.0f) + CmcdData.STREAMING_FORMAT_SS);
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_SYN_TIME, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(AudioSynDialog audioSynDialog, View view) {
        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 700L) - ((long) 100);
        DialogAudioSynBinding dialogAudioSynBinding = audioSynDialog.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.tvDelay.setText((j / 1000.0f) + CmcdData.STREAMING_FORMAT_SS);
        PrefsUtils.getInstance().putLong(Constant.Prefs.AUDIO_SYN_TIME, j);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        DialogAudioSynBinding dialogAudioSynBinding = this.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.tvDelay.setText((PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 700L) / 1000.0f) + CmcdData.STREAMING_FORMAT_SS);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
        Observable<Long> observableTimer = Observable.timer(200L, TimeUnit.MILLISECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.AudioSynDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioSynDialog.initView$lambda$3(this.f$0, (Long) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$3(AudioSynDialog audioSynDialog, Long l) {
        DialogAudioSynBinding dialogAudioSynBinding = audioSynDialog.binding;
        if (dialogAudioSynBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogAudioSynBinding = null;
        }
        dialogAudioSynBinding.ivDelayAdd.requestFocus();
        return Unit.INSTANCE;
    }
}
