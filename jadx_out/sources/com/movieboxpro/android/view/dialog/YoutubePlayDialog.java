package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.os.BundleKt;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogYoutubePlayBinding;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: YoutubePlayDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002J\b\u0010%\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/view/dialog/YoutubePlayDialog;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogYoutubePlayBinding;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "prepared", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "initDialog", "", "hidePlayDisposable", "Lio/reactivex/disposables/Disposable;", "hidePlayPause", "hideSeekBarDisposable", "hideSeekBar", "seekDisposable", "seek", "currentPosition", "", "togglePlayPause", "initListener", "onStop", "initData", "playUrl", "url", "", "releasePlayer", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class YoutubePlayDialog extends BaseBindingCenterDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DialogYoutubePlayBinding binding;
    private Disposable hidePlayDisposable;
    private Disposable hideSeekBarDisposable;
    private ExoPlayer player;
    private boolean prepared;
    private Disposable seekDisposable;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    /* JADX INFO: compiled from: YoutubePlayDialog.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/YoutubePlayDialog$Companion;", "", "<init>", "()V", "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, String id) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(id, "id");
            YoutubePlayDialog youtubePlayDialog = new YoutubePlayDialog();
            youtubePlayDialog.setArguments(BundleKt.bundleOf(TuplesKt.to("id", id)));
            youtubePlayDialog.show(fragmentManager, "YoutubePlayDialog");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_youtube_play, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogYoutubePlayBinding dialogYoutubePlayBinding = (DialogYoutubePlayBinding) viewDataBindingInflate;
        this.binding = dialogYoutubePlayBinding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        View root = dialogYoutubePlayBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initDialog() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        Context context = getContext();
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "getDisplayMetrics(...)");
            attributes.width = (int) (displayMetrics.widthPixels * 0.8f);
            attributes.height = (int) (displayMetrics.heightPixels * 0.8f);
            window.setAttributes(attributes);
        }
    }

    private final void hidePlayPause() {
        Disposable disposable = this.hidePlayDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<Long> observableTimer = Observable.timer(2L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.hidePlayPause$lambda$1(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.hidePlayPause$lambda$2(this.f$0, (Long) obj);
            }
        }, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit hidePlayPause$lambda$1(YoutubePlayDialog youtubePlayDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        youtubePlayDialog.hidePlayDisposable = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit hidePlayPause$lambda$2(YoutubePlayDialog youtubePlayDialog, Long l) {
        DialogYoutubePlayBinding dialogYoutubePlayBinding = youtubePlayDialog.binding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        ImageView ivPlayPause = dialogYoutubePlayBinding.ivPlayPause;
        Intrinsics.checkNotNullExpressionValue(ivPlayPause, "ivPlayPause");
        CommonExtKt.gone(ivPlayPause);
        return Unit.INSTANCE;
    }

    private final void hideSeekBar() {
        Disposable disposable = this.hideSeekBarDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<Long> observableTimer = Observable.timer(3L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.hideSeekBar$lambda$3(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.hideSeekBar$lambda$4(this.f$0, (Long) obj);
            }
        }, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit hideSeekBar$lambda$3(YoutubePlayDialog youtubePlayDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        youtubePlayDialog.hideSeekBarDisposable = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit hideSeekBar$lambda$4(YoutubePlayDialog youtubePlayDialog, Long l) {
        DialogYoutubePlayBinding dialogYoutubePlayBinding = youtubePlayDialog.binding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        AppCompatSeekBar playerSeekBar = dialogYoutubePlayBinding.playerSeekBar;
        Intrinsics.checkNotNullExpressionValue(playerSeekBar, "playerSeekBar");
        CommonExtKt.gone(playerSeekBar);
        return Unit.INSTANCE;
    }

    private final void seek() {
        Disposable disposable = this.seekDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<Long> observableTimer = Observable.timer(1L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableTimer, this), null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.seek$lambda$5(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.seek$lambda$6(this.f$0, (Long) obj);
            }
        }, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit seek$lambda$5(YoutubePlayDialog youtubePlayDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        youtubePlayDialog.seekDisposable = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit seek$lambda$6(YoutubePlayDialog youtubePlayDialog, Long l) {
        ExoPlayer exoPlayer = youtubePlayDialog.player;
        if (exoPlayer != null) {
            DialogYoutubePlayBinding dialogYoutubePlayBinding = youtubePlayDialog.binding;
            if (dialogYoutubePlayBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogYoutubePlayBinding = null;
            }
            exoPlayer.seekTo(dialogYoutubePlayBinding.playerSeekBar.getProgress());
        }
        return Unit.INSTANCE;
    }

    private final int currentPosition() {
        ExoPlayer exoPlayer = this.player;
        return (int) (exoPlayer != null ? exoPlayer.getCurrentPosition() : 0L);
    }

    private final void togglePlayPause() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            return;
        }
        DialogYoutubePlayBinding dialogYoutubePlayBinding = null;
        if (exoPlayer.isPlaying()) {
            exoPlayer.pause();
            DialogYoutubePlayBinding dialogYoutubePlayBinding2 = this.binding;
            if (dialogYoutubePlayBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogYoutubePlayBinding2 = null;
            }
            dialogYoutubePlayBinding2.ivPlayPause.setImageResource(com.dl7.player.R.mipmap.ic_video_play);
            DialogYoutubePlayBinding dialogYoutubePlayBinding3 = this.binding;
            if (dialogYoutubePlayBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogYoutubePlayBinding = dialogYoutubePlayBinding3;
            }
            ImageView ivPlayPause = dialogYoutubePlayBinding.ivPlayPause;
            Intrinsics.checkNotNullExpressionValue(ivPlayPause, "ivPlayPause");
            CommonExtKt.visible(ivPlayPause);
            Disposable disposable = this.hidePlayDisposable;
            if (disposable != null) {
                disposable.dispose();
                return;
            }
            return;
        }
        exoPlayer.play();
        DialogYoutubePlayBinding dialogYoutubePlayBinding4 = this.binding;
        if (dialogYoutubePlayBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding4 = null;
        }
        dialogYoutubePlayBinding4.ivPlayPause.setImageResource(com.dl7.player.R.mipmap.ic_video_pause);
        DialogYoutubePlayBinding dialogYoutubePlayBinding5 = this.binding;
        if (dialogYoutubePlayBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogYoutubePlayBinding = dialogYoutubePlayBinding5;
        }
        ImageView ivPlayPause2 = dialogYoutubePlayBinding.ivPlayPause;
        Intrinsics.checkNotNullExpressionValue(ivPlayPause2, "ivPlayPause");
        CommonExtKt.visible(ivPlayPause2);
        Disposable disposable2 = this.hidePlayDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        hidePlayPause();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        DialogYoutubePlayBinding dialogYoutubePlayBinding = this.binding;
        DialogYoutubePlayBinding dialogYoutubePlayBinding2 = null;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        dialogYoutubePlayBinding.playerSeekBar.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda7
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return YoutubePlayDialog.initListener$lambda$7(this.f$0, view, i, keyEvent);
            }
        });
        DialogYoutubePlayBinding dialogYoutubePlayBinding3 = this.binding;
        if (dialogYoutubePlayBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding3 = null;
        }
        dialogYoutubePlayBinding3.playerView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                YoutubePlayDialog.initListener$lambda$8(this.f$0, view);
            }
        });
        DialogYoutubePlayBinding dialogYoutubePlayBinding4 = this.binding;
        if (dialogYoutubePlayBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogYoutubePlayBinding2 = dialogYoutubePlayBinding4;
        }
        dialogYoutubePlayBinding2.playerView.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda9
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return YoutubePlayDialog.initListener$lambda$9(this.f$0, view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$7(YoutubePlayDialog youtubePlayDialog, View view, int i, KeyEvent keyEvent) {
        if (!youtubePlayDialog.prepared || keyEvent.getAction() != 0) {
            return false;
        }
        int i2 = AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH;
        DialogYoutubePlayBinding dialogYoutubePlayBinding = null;
        if (i == 21) {
            int repeatCount = keyEvent.getRepeatCount();
            DialogYoutubePlayBinding dialogYoutubePlayBinding2 = youtubePlayDialog.binding;
            if (dialogYoutubePlayBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogYoutubePlayBinding2 = null;
            }
            int progress = dialogYoutubePlayBinding2.playerSeekBar.getProgress();
            if (repeatCount == 0 || repeatCount < 40) {
                i2 = 10000;
            } else if (repeatCount >= 70) {
                i2 = MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND;
            }
            DialogYoutubePlayBinding dialogYoutubePlayBinding3 = youtubePlayDialog.binding;
            if (dialogYoutubePlayBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogYoutubePlayBinding = dialogYoutubePlayBinding3;
            }
            dialogYoutubePlayBinding.playerSeekBar.setProgress(progress - i2);
            youtubePlayDialog.seek();
            youtubePlayDialog.hideSeekBar();
        } else {
            if (i != 22) {
                return false;
            }
            int repeatCount2 = keyEvent.getRepeatCount();
            DialogYoutubePlayBinding dialogYoutubePlayBinding4 = youtubePlayDialog.binding;
            if (dialogYoutubePlayBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogYoutubePlayBinding4 = null;
            }
            int progress2 = dialogYoutubePlayBinding4.playerSeekBar.getProgress();
            if (repeatCount2 == 0 || repeatCount2 < 40) {
                i2 = 10000;
            } else if (repeatCount2 >= 70) {
                i2 = MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND;
            }
            DialogYoutubePlayBinding dialogYoutubePlayBinding5 = youtubePlayDialog.binding;
            if (dialogYoutubePlayBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogYoutubePlayBinding = dialogYoutubePlayBinding5;
            }
            dialogYoutubePlayBinding.playerSeekBar.setProgress(progress2 + i2);
            youtubePlayDialog.seek();
            youtubePlayDialog.hideSeekBar();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(YoutubePlayDialog youtubePlayDialog, View view) {
        if (youtubePlayDialog.prepared) {
            youtubePlayDialog.togglePlayPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$9(YoutubePlayDialog youtubePlayDialog, View view, int i, KeyEvent keyEvent) {
        if (!youtubePlayDialog.prepared) {
            return false;
        }
        if (i == 66 || i == 85) {
            if (keyEvent.getAction() == 0) {
                youtubePlayDialog.togglePlayPause();
            }
        } else {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                    DialogYoutubePlayBinding dialogYoutubePlayBinding = youtubePlayDialog.binding;
                    DialogYoutubePlayBinding dialogYoutubePlayBinding2 = null;
                    if (dialogYoutubePlayBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogYoutubePlayBinding = null;
                    }
                    dialogYoutubePlayBinding.playerSeekBar.setProgress(youtubePlayDialog.currentPosition());
                    DialogYoutubePlayBinding dialogYoutubePlayBinding3 = youtubePlayDialog.binding;
                    if (dialogYoutubePlayBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogYoutubePlayBinding3 = null;
                    }
                    AppCompatSeekBar playerSeekBar = dialogYoutubePlayBinding3.playerSeekBar;
                    Intrinsics.checkNotNullExpressionValue(playerSeekBar, "playerSeekBar");
                    CommonExtKt.visible(playerSeekBar);
                    DialogYoutubePlayBinding dialogYoutubePlayBinding4 = youtubePlayDialog.binding;
                    if (dialogYoutubePlayBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogYoutubePlayBinding4 = null;
                    }
                    dialogYoutubePlayBinding4.playerSeekBar.requestFocus();
                    DialogYoutubePlayBinding dialogYoutubePlayBinding5 = youtubePlayDialog.binding;
                    if (dialogYoutubePlayBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        dialogYoutubePlayBinding5 = null;
                    }
                    dialogYoutubePlayBinding5.playerSeekBar.setFocusableInTouchMode(true);
                    if (i == 21) {
                        DialogYoutubePlayBinding dialogYoutubePlayBinding6 = youtubePlayDialog.binding;
                        if (dialogYoutubePlayBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            dialogYoutubePlayBinding2 = dialogYoutubePlayBinding6;
                        }
                        dialogYoutubePlayBinding2.playerSeekBar.setProgress(youtubePlayDialog.currentPosition() - 10000);
                    } else if (i == 22) {
                        DialogYoutubePlayBinding dialogYoutubePlayBinding7 = youtubePlayDialog.binding;
                        if (dialogYoutubePlayBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            dialogYoutubePlayBinding2 = dialogYoutubePlayBinding7;
                        }
                        dialogYoutubePlayBinding2.playerSeekBar.setProgress(youtubePlayDialog.currentPosition() + 10000);
                    }
                    youtubePlayDialog.hideSeekBar();
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        releasePlayer();
        super.onStop();
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("id") : null;
        String str = "";
        String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string2) && StringsKt.equals("0", string2, true)) {
            string2 = "";
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        Observable<R> observableCompose = HttpRequest.INSTANCE.post("Youtube_download").param("vid", string).param("oss", str).param("group", string2).asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, this), new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.initData$lambda$10(this.f$0, (ApiException) obj);
            }
        }, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return YoutubePlayDialog.initData$lambda$11(this.f$0, (HashMap) obj);
            }
        }, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$11(YoutubePlayDialog youtubePlayDialog, HashMap it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = (String) it.get("best_link");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            ToastUtils.showShort("Load failed: empty url", new Object[0]);
            youtubePlayDialog.dismiss();
            return Unit.INSTANCE;
        }
        youtubePlayDialog.playUrl(str);
        DialogYoutubePlayBinding dialogYoutubePlayBinding = youtubePlayDialog.binding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        dialogYoutubePlayBinding.frameLayout.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$10(YoutubePlayDialog youtubePlayDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        youtubePlayDialog.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        youtubePlayDialog.dismiss();
        return Unit.INSTANCE;
    }

    private final void playUrl(String url) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        final ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context).build();
        Intrinsics.checkNotNullExpressionValue(exoPlayerBuild, "build(...)");
        this.player = exoPlayerBuild;
        DialogYoutubePlayBinding dialogYoutubePlayBinding = this.binding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        dialogYoutubePlayBinding.playerView.setPlayer(exoPlayerBuild);
        exoPlayerBuild.addListener(new Player.Listener() { // from class: com.movieboxpro.android.view.dialog.YoutubePlayDialog.playUrl.1
            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioSessionIdChanged(int i) {
                Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onCues(CueGroup cueGroup) {
                Player.Listener.CC.$default$onCues(this, cueGroup);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onCues(List list) {
                Player.Listener.CC.$default$onCues(this, list);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
                Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onEvents(Player player, Player.Events events) {
                Player.Listener.CC.$default$onEvents(this, player, events);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onIsLoadingChanged(boolean z) {
                Player.Listener.CC.$default$onIsLoadingChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onIsPlayingChanged(boolean z) {
                Player.Listener.CC.$default$onIsPlayingChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onLoadingChanged(boolean z) {
                Player.Listener.CC.$default$onLoadingChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
                Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                Player.Listener.CC.$default$onMetadata(this, metadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
                Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRenderedFirstFrame() {
                Player.Listener.CC.$default$onRenderedFirstFrame(this);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRepeatModeChanged(int i) {
                Player.Listener.CC.$default$onRepeatModeChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
                Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
                Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTracksChanged(Tracks tracks) {
                Player.Listener.CC.$default$onTracksChanged(this, tracks);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onVolumeChanged(float f) {
                Player.Listener.CC.$default$onVolumeChanged(this, f);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState != 3) {
                    if (playbackState != 4) {
                        return;
                    }
                    YoutubePlayDialog.this.dismiss();
                    return;
                }
                DialogYoutubePlayBinding dialogYoutubePlayBinding2 = YoutubePlayDialog.this.binding;
                DialogYoutubePlayBinding dialogYoutubePlayBinding3 = null;
                if (dialogYoutubePlayBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    dialogYoutubePlayBinding2 = null;
                }
                ProgressBar progressBar = dialogYoutubePlayBinding2.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
                CommonExtKt.gone(progressBar);
                YoutubePlayDialog.this.prepared = true;
                long duration = exoPlayerBuild.getDuration();
                if (duration > 0) {
                    DialogYoutubePlayBinding dialogYoutubePlayBinding4 = YoutubePlayDialog.this.binding;
                    if (dialogYoutubePlayBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        dialogYoutubePlayBinding3 = dialogYoutubePlayBinding4;
                    }
                    dialogYoutubePlayBinding3.playerSeekBar.setMax((int) duration);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ToastUtils.showShort("Load failed:" + error.getMessage(), new Object[0]);
                YoutubePlayDialog.this.dismiss();
            }
        });
        exoPlayerBuild.setMediaItem(MediaItem.fromUri(url));
        exoPlayerBuild.prepare();
        exoPlayerBuild.play();
    }

    private final void releasePlayer() {
        DialogYoutubePlayBinding dialogYoutubePlayBinding = this.binding;
        if (dialogYoutubePlayBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogYoutubePlayBinding = null;
        }
        dialogYoutubePlayBinding.playerView.setPlayer(null);
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.release();
        }
        this.player = null;
        this.prepared = false;
    }
}
