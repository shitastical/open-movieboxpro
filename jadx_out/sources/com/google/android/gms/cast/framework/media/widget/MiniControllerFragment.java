package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private static final zzdw zzbf = new zzdw("MiniControllerFragment");
    private int zztt;
    private int zztu;
    private int zztv;
    private int zztw;
    private int zztx;
    private int zzty;
    private int zztz;
    private int zzua;
    private int zzub;
    private int zzuf;
    private int zzug;
    private int[] zzum;
    private ImageView[] zzun = new ImageView[3];
    private UIMediaController zzuw;
    private boolean zzve;
    private int zzvf;
    private int zzvg;
    private TextView zzvh;
    private int zzvi;
    private int zzvj;
    private int zzvk;
    private int zzvl;
    private int zzvm;
    private int zzvn;

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 3;
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        if (this.zzum == null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CastMiniController, R.attr.castMiniControllerStyle, R.style.CastMiniController);
            this.zzve = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CastMiniController_castShowImageThumbnail, true);
            this.zzvf = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castTitleTextAppearance, 0);
            this.zzvg = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
            this.zzvi = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castBackground, 0);
            this.zzvj = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castProgressBarColor, 0);
            this.zzuf = typedArrayObtainStyledAttributes.getColor(R.styleable.CastMiniController_castMiniControllerLoadingIndicatorColor, this.zzvj);
            this.zzug = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castButtonColor, 0);
            this.zztt = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zztu = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zztv = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzvl = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzvm = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzvn = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zztw = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
            this.zztx = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
            this.zzty = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
            this.zztz = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
            this.zzua = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
            this.zzub = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastMiniController_castControlButtons, 0);
            if (resourceId != 0) {
                TypedArray typedArrayObtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 3);
                this.zzum = new int[typedArrayObtainTypedArray.length()];
                for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                    this.zzum[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
                }
                typedArrayObtainTypedArray.recycle();
                if (this.zzve) {
                    this.zzum[0] = R.id.cast_button_type_empty;
                }
                this.zzvk = 0;
                for (int i2 : this.zzum) {
                    if (i2 != R.id.cast_button_type_empty) {
                        this.zzvk++;
                    }
                }
            } else {
                zzbf.w("Unable to read attribute castControlButtons.", new Object[0]);
                this.zzum = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zzuw = new UIMediaController(getActivity());
        View viewInflate = layoutInflater.inflate(R.layout.cast_mini_controller, viewGroup);
        viewInflate.setVisibility(8);
        this.zzuw.bindViewVisibilityToMediaSession(viewInflate, 8);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.container_current);
        int i = this.zzvi;
        if (i != 0) {
            relativeLayout.setBackgroundResource(i);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.icon_view);
        TextView textView = (TextView) viewInflate.findViewById(R.id.title_view);
        if (this.zzvf != 0) {
            textView.setTextAppearance(getActivity(), this.zzvf);
        }
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.subtitle_view);
        this.zzvh = textView2;
        if (this.zzvg != 0) {
            textView2.setTextAppearance(getActivity(), this.zzvg);
        }
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.progressBar);
        if (this.zzvj != 0) {
            ((LayerDrawable) progressBar.getProgressDrawable()).setColorFilter(this.zzvj, PorterDuff.Mode.SRC_IN);
        }
        this.zzuw.bindTextViewToMetadataOfCurrentItem(textView, MediaMetadata.KEY_TITLE);
        this.zzuw.bindTextViewToSmartSubtitle(this.zzvh);
        this.zzuw.bindProgressBar(progressBar);
        this.zzuw.bindViewToLaunchExpandedController(relativeLayout);
        if (this.zzve) {
            this.zzuw.bindImageViewToImageOfCurrentItem(imageView, new ImageHints(2, getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_height)), R.drawable.cast_album_art_placeholder);
        } else {
            imageView.setVisibility(8);
        }
        this.zzun[0] = (ImageView) relativeLayout.findViewById(R.id.button_0);
        this.zzun[1] = (ImageView) relativeLayout.findViewById(R.id.button_1);
        this.zzun[2] = (ImageView) relativeLayout.findViewById(R.id.button_2);
        zza(relativeLayout, R.id.button_0, 0);
        zza(relativeLayout, R.id.button_1, 1);
        zza(relativeLayout, R.id.button_2, 2);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        UIMediaController uIMediaController = this.zzuw;
        if (uIMediaController != null) {
            uIMediaController.dispose();
            this.zzuw = null;
        }
        super.onDestroy();
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzum[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzun[i];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        return this.zzuw;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void zza(RelativeLayout relativeLayout, int i, int i2) {
        ImageView imageView = (ImageView) relativeLayout.findViewById(i);
        int i3 = this.zzum[i2];
        if (i3 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i3 != R.id.cast_button_type_custom) {
            if (i3 == R.id.cast_button_type_play_pause_toggle) {
                int i4 = this.zztt;
                int i5 = this.zztu;
                int i6 = this.zztv;
                if (this.zzvk == 1) {
                    i4 = this.zzvl;
                    i5 = this.zzvm;
                    i6 = this.zzvn;
                }
                Drawable drawableZza = zze.zza(getContext(), this.zzug, i4);
                Drawable drawableZza2 = zze.zza(getContext(), this.zzug, i5);
                Drawable drawableZza3 = zze.zza(getContext(), this.zzug, i6);
                imageView.setImageDrawable(drawableZza2);
                ProgressBar progressBar = new ProgressBar(getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(8, i);
                layoutParams.addRule(6, i);
                layoutParams.addRule(5, i);
                layoutParams.addRule(7, i);
                layoutParams.addRule(15);
                progressBar.setLayoutParams(layoutParams);
                progressBar.setVisibility(8);
                Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
                int i7 = this.zzuf;
                if (i7 != 0 && indeterminateDrawable != null) {
                    indeterminateDrawable.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
                }
                relativeLayout.addView(progressBar);
                this.zzuw.bindImageViewToPlayPauseToggle(imageView, drawableZza, drawableZza2, drawableZza3, progressBar, true);
                return;
            }
            if (i3 == R.id.cast_button_type_skip_previous) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zztw));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
                this.zzuw.bindViewToSkipPrev(imageView, 0);
                return;
            }
            if (i3 == R.id.cast_button_type_skip_next) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zztx));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
                this.zzuw.bindViewToSkipNext(imageView, 0);
                return;
            }
            if (i3 == R.id.cast_button_type_rewind_30_seconds) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zzty));
                imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
                this.zzuw.bindViewToRewind(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i3 == R.id.cast_button_type_forward_30_seconds) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zztz));
                imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
                this.zzuw.bindViewToForward(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            } else if (i3 == R.id.cast_button_type_mute_toggle) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zzua));
                this.zzuw.bindImageViewToMuteToggle(imageView);
            } else if (i3 == R.id.cast_button_type_closed_caption) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzug, this.zzub));
                this.zzuw.bindViewToClosedCaption(imageView);
            }
        }
    }
}
