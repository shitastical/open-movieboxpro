package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import com.adorkable.iosdialog.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.listener.OnPlayNextListener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.owen.focus.AbsFocusBorder;
import com.owen.focus.FocusBorder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayNextEpisodeDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0007J)\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020 0\u001f\"\u00020 H\u0002¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010#\u001a\u00020\u0014J\b\u0010$\u001a\u00020\u0014H\u0002J \u0010%\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0002J\u0006\u0010)\u001a\u00020\u0014J\b\u0010*\u001a\u00020\u0014H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PlayNextEpisodeDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onPlayListener", "Lcom/movieboxpro/android/listener/OnPlayNextListener;", "nextEpisode", "Landroid/widget/LinearLayout;", "lastEpisode", "cancel", "Landroid/widget/TextView;", "frameLayout", "Landroid/widget/FrameLayout;", "handle", "Landroid/os/Handler;", "noMoreLast", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnPlayListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "runnable", "Ljava/lang/Runnable;", "setViewFocusListener", "focusBorder", "Lcom/owen/focus/FocusBorder;", "args", "", "Landroid/view/View;", "(Lcom/owen/focus/FocusBorder;[Landroid/view/View;)V", "setNoMoreLast", "hideLastEpisode", "clearHandle", "focusScale", "v", "scale", "", "showDialog", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayNextEpisodeDialog extends AppCompatDialog {
    private TextView cancel;
    private FrameLayout frameLayout;
    private Handler handle;
    private LinearLayout lastEpisode;
    private LinearLayout nextEpisode;
    private boolean noMoreLast;
    private OnPlayNextListener onPlayListener;
    private final Runnable runnable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayNextEpisodeDialog(Context context) {
        super(context, R.style.TopShowExitAnimation);
        Intrinsics.checkNotNullParameter(context, "context");
        this.runnable = new Runnable() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                PlayNextEpisodeDialog.runnable$lambda$1(this.f$0);
            }
        };
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), android.R.color.transparent));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 48;
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        initView();
    }

    public final void setOnPlayListener(OnPlayNextListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onPlayListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runnable$lambda$1(PlayNextEpisodeDialog playNextEpisodeDialog) {
        TextView textView = playNextEpisodeDialog.cancel;
        if (textView != null) {
            CommonExtKt.gone(textView);
        }
        LinearLayout linearLayout = playNextEpisodeDialog.lastEpisode;
        if (linearLayout != null) {
            CommonExtKt.gone(linearLayout);
        }
        FrameLayout frameLayout = playNextEpisodeDialog.frameLayout;
        if (frameLayout != null) {
            frameLayout.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$3$lambda$2(PlayNextEpisodeDialog playNextEpisodeDialog, FocusBorder focusBorder, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            playNextEpisodeDialog.focusScale(focusBorder, view, 1.1f);
            Handler handler = playNextEpisodeDialog.handle;
            if (handler != null) {
                handler.removeCallbacks(playNextEpisodeDialog.runnable);
            }
            Handler handler2 = playNextEpisodeDialog.handle;
            if (handler2 != null) {
                handler2.postDelayed(playNextEpisodeDialog.runnable, 2500L);
            }
        }
        focusBorder.setVisible(z);
    }

    public final void setNoMoreLast(boolean noMoreLast) {
        this.noMoreLast = noMoreLast;
    }

    public final void hideLastEpisode() {
        LinearLayout linearLayout = this.lastEpisode;
        if (linearLayout != null) {
            CommonExtKt.gone(linearLayout);
        }
    }

    private final void clearHandle() {
        Handler handler = this.handle;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
        Handler handler2 = this.handle;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        this.handle = null;
    }

    private final void focusScale(FocusBorder focusBorder, View v, float scale) {
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    public final void showDialog() {
        if (!this.noMoreLast) {
            LinearLayout linearLayout = this.lastEpisode;
            if (linearLayout != null) {
                CommonExtKt.visible(linearLayout);
            }
        } else {
            LinearLayout linearLayout2 = this.lastEpisode;
            if (linearLayout2 != null) {
                CommonExtKt.gone(linearLayout2);
            }
        }
        FrameLayout frameLayout = this.frameLayout;
        if (frameLayout != null) {
            frameLayout.requestFocus();
        }
        LinearLayout linearLayout3 = this.lastEpisode;
        if (linearLayout3 != null) {
            CommonExtKt.gone(linearLayout3);
        }
        TextView textView = this.cancel;
        if (textView != null) {
            CommonExtKt.gone(textView);
        }
        show();
    }

    private final void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(com.movieboxpro.androidtv.R.layout.play_next_episode_layout, (ViewGroup) null);
        setContentView(viewInflate);
        AbsFocusBorder.Builder builderNoBreathing = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.focus_shadow).shadowWidth(1, 10.0f).noShimmer().noBreathing();
        ViewParent parent = viewInflate.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        FocusBorder focusBorderBuild = builderNoBreathing.build((ViewGroup) parent);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.handle = new Handler();
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.llNextEpisode);
        this.nextEpisode = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayNextEpisodeDialog.initView$lambda$4(this.f$0, view);
            }
        });
        LinearLayout linearLayout2 = this.nextEpisode;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.clearFocus();
        LinearLayout linearLayout3 = (LinearLayout) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.llLastEpisode);
        this.lastEpisode = linearLayout3;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayNextEpisodeDialog.initView$lambda$5(this.f$0, view);
            }
        });
        LinearLayout linearLayout4 = this.lastEpisode;
        Intrinsics.checkNotNull(linearLayout4);
        LinearLayout linearLayout5 = this.nextEpisode;
        Intrinsics.checkNotNull(linearLayout5);
        TextView textView = this.cancel;
        Intrinsics.checkNotNull(textView);
        setViewFocusListener(focusBorderBuild, linearLayout4, linearLayout5, textView);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                PlayNextEpisodeDialog.initView$lambda$6(this.f$0, dialogInterface);
            }
        });
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(com.movieboxpro.androidtv.R.id.frameLayout);
        this.frameLayout = frameLayout;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.requestFocus();
        FrameLayout frameLayout2 = this.frameLayout;
        Intrinsics.checkNotNull(frameLayout2);
        frameLayout2.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return PlayNextEpisodeDialog.initView$lambda$7(this.f$0, view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(PlayNextEpisodeDialog playNextEpisodeDialog, View view) {
        OnPlayNextListener onPlayNextListener = playNextEpisodeDialog.onPlayListener;
        if (onPlayNextListener != null) {
            onPlayNextListener.onPlayNext();
        }
        playNextEpisodeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5(PlayNextEpisodeDialog playNextEpisodeDialog, View view) {
        OnPlayNextListener onPlayNextListener = playNextEpisodeDialog.onPlayListener;
        if (onPlayNextListener != null) {
            onPlayNextListener.onPlayLast();
        }
        playNextEpisodeDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$6(PlayNextEpisodeDialog playNextEpisodeDialog, DialogInterface dialogInterface) {
        OnPlayNextListener onPlayNextListener = playNextEpisodeDialog.onPlayListener;
        if (onPlayNextListener != null) {
            onPlayNextListener.onDismiss();
        }
        playNextEpisodeDialog.clearHandle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initView$lambda$7(PlayNextEpisodeDialog playNextEpisodeDialog, View view, int i, KeyEvent keyEvent) {
        if (i == 4) {
            return false;
        }
        LinearLayout linearLayout = playNextEpisodeDialog.lastEpisode;
        Intrinsics.checkNotNull(linearLayout);
        if (linearLayout.getVisibility() != 8) {
            return false;
        }
        if (!playNextEpisodeDialog.noMoreLast) {
            LinearLayout linearLayout2 = playNextEpisodeDialog.lastEpisode;
            Intrinsics.checkNotNull(linearLayout2);
            CommonExtKt.visible(linearLayout2);
        } else {
            LinearLayout linearLayout3 = playNextEpisodeDialog.lastEpisode;
            Intrinsics.checkNotNull(linearLayout3);
            CommonExtKt.gone(linearLayout3);
        }
        TextView textView = playNextEpisodeDialog.cancel;
        Intrinsics.checkNotNull(textView);
        CommonExtKt.visible(textView);
        LinearLayout linearLayout4 = playNextEpisodeDialog.nextEpisode;
        Intrinsics.checkNotNull(linearLayout4);
        linearLayout4.requestFocus();
        return true;
    }

    private final void setViewFocusListener(final FocusBorder focusBorder, View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog$$ExternalSyntheticLambda4
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    PlayNextEpisodeDialog.setViewFocusListener$lambda$3$lambda$2(this.f$0, focusBorder, view2, z);
                }
            });
        }
    }
}
