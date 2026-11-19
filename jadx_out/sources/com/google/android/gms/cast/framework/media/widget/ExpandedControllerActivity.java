package com.google.android.gms.cast.framework.media.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzaa;
import com.google.android.gms.internal.cast.zzaq;
import com.google.android.gms.internal.cast.zzar;
import com.google.android.gms.internal.cast.zzbw;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.internal.cast.zzby;
import com.google.android.gms.internal.cast.zzca;
import java.util.Timer;

/* JADX INFO: loaded from: classes4.dex */
public class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private SessionManager zzhg;
    private final SessionManagerListener<CastSession> zzju;
    private SeekBar zzrp;
    private final RemoteMediaClient.Listener zzsc;
    private int zzts;
    private int zztt;
    private int zztu;
    private int zztv;
    private int zztw;
    private int zztx;
    private int zzty;
    private int zztz;
    private int zzua;
    private int zzub;
    private int zzuc;
    private int zzud;
    private int zzue;
    private int zzuf;
    private int zzug;
    private int zzuh;
    private int zzui;
    private TextView zzuj;
    private ImageView zzuk;
    private ImageView zzul;
    private int[] zzum;
    private ImageView[] zzun = new ImageView[4];
    private View zzuo;
    private View zzup;
    private ImageView zzuq;
    private TextView zzur;
    private TextView zzus;
    private TextView zzut;
    private TextView zzuu;
    private zzaa zzuv;
    private UIMediaController zzuw;
    private boolean zzux;
    private boolean zzuy;
    private Timer zzuz;

    private class zza implements RemoteMediaClient.Listener {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onPreloadStatusUpdated() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onQueueStatusUpdated() {
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onStatusUpdated() {
            RemoteMediaClient remoteMediaClient = ExpandedControllerActivity.this.getRemoteMediaClient();
            if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
                if (ExpandedControllerActivity.this.zzux) {
                    return;
                }
                ExpandedControllerActivity.this.finish();
            } else {
                ExpandedControllerActivity.zza(ExpandedControllerActivity.this, false);
                ExpandedControllerActivity.this.zzeg();
                ExpandedControllerActivity.this.zzeh();
            }
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onMetadataUpdated() {
            ExpandedControllerActivity.this.zzef();
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onSendingRemoteMediaRequest() {
            ExpandedControllerActivity.this.zzuj.setText(ExpandedControllerActivity.this.getResources().getString(R.string.cast_expanded_controller_loading));
        }

        @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
        public final void onAdBreakStatusUpdated() {
            ExpandedControllerActivity.this.zzeh();
        }

        /* synthetic */ zza(ExpandedControllerActivity expandedControllerActivity, com.google.android.gms.cast.framework.media.widget.zza zzaVar) {
            this();
        }
    }

    public ExpandedControllerActivity() {
        com.google.android.gms.cast.framework.media.widget.zza zzaVar = null;
        this.zzju = new zzb(this, zzaVar);
        this.zzsc = new zza(this, zzaVar);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 4;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SessionManager sessionManager = CastContext.getSharedInstance(this).getSessionManager();
        this.zzhg = sessionManager;
        if (sessionManager.getCurrentCastSession() == null) {
            finish();
        }
        UIMediaController uIMediaController = new UIMediaController(this);
        this.zzuw = uIMediaController;
        uIMediaController.setPostRemoteMediaClientListener(this.zzsc);
        setContentView(R.layout.cast_expanded_controller_activity);
        TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes(new int[]{androidx.appcompat.R.attr.selectableItemBackgroundBorderless});
        this.zzts = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        this.zzug = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castButtonColor, 0);
        this.zztt = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zztu = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zztv = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zztw = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zztx = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzty = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zztz = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzua = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzub = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castControlButtons, 0);
        if (resourceId != 0) {
            TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(resourceId);
            Preconditions.checkArgument(typedArrayObtainTypedArray.length() == 4);
            this.zzum = new int[typedArrayObtainTypedArray.length()];
            for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                this.zzum[i] = typedArrayObtainTypedArray.getResourceId(i, 0);
            }
            typedArrayObtainTypedArray.recycle();
        } else {
            this.zzum = new int[]{R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty, R.id.cast_button_type_empty};
        }
        this.zzuf = typedArrayObtainStyledAttributes2.getColor(R.styleable.CastExpandedController_castExpandedControllerLoadingIndicatorColor, 0);
        this.zzuc = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelColor, 0));
        this.zzud = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressTextColor, 0));
        this.zzue = getResources().getColor(typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextColor, 0));
        this.zzuh = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdLabelTextAppearance, 0);
        this.zzui = typedArrayObtainStyledAttributes2.getResourceId(R.styleable.CastExpandedController_castAdInProgressLabelTextAppearance, 0);
        typedArrayObtainStyledAttributes2.recycle();
        View viewFindViewById = findViewById(R.id.expanded_controller_layout);
        UIMediaController uIMediaController2 = this.zzuw;
        this.zzuk = (ImageView) viewFindViewById.findViewById(R.id.background_image_view);
        this.zzul = (ImageView) viewFindViewById.findViewById(R.id.blurred_background_image_view);
        View viewFindViewById2 = viewFindViewById.findViewById(R.id.background_place_holder_image_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        uIMediaController2.bindImageViewToImageOfCurrentItem(this.zzuk, new ImageHints(4, displayMetrics.widthPixels, displayMetrics.heightPixels), viewFindViewById2);
        this.zzuj = (TextView) viewFindViewById.findViewById(R.id.status_text);
        ProgressBar progressBar = (ProgressBar) viewFindViewById.findViewById(R.id.loading_indicator);
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        int i2 = this.zzuf;
        if (i2 != 0) {
            indeterminateDrawable.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        }
        uIMediaController2.bindViewToLoadingIndicator(progressBar);
        TextView textView = (TextView) viewFindViewById.findViewById(R.id.start_text);
        TextView textView2 = (TextView) viewFindViewById.findViewById(R.id.end_text);
        SeekBar seekBar = (SeekBar) viewFindViewById.findViewById(R.id.seek_bar);
        this.zzrp = seekBar;
        new zzar(this, uIMediaController2, seekBar);
        uIMediaController2.bindViewToUIController(textView, new zzby(textView, uIMediaController2.zzdh()));
        uIMediaController2.bindViewToUIController(textView2, new zzbw(textView2, uIMediaController2.zzdh()));
        View viewFindViewById3 = viewFindViewById.findViewById(R.id.live_indicators);
        UIMediaController uIMediaController3 = this.zzuw;
        uIMediaController3.bindViewToUIController(viewFindViewById3, new zzbx(viewFindViewById3, uIMediaController3.zzdh()));
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById.findViewById(R.id.tooltip_container);
        zzca zzcaVar = new zzca(relativeLayout, this.zzrp, this.zzuw.zzdh());
        this.zzuw.bindViewToUIController(relativeLayout, zzcaVar);
        this.zzuw.zza(zzcaVar);
        this.zzun[0] = (ImageView) viewFindViewById.findViewById(R.id.button_0);
        this.zzun[1] = (ImageView) viewFindViewById.findViewById(R.id.button_1);
        this.zzun[2] = (ImageView) viewFindViewById.findViewById(R.id.button_2);
        this.zzun[3] = (ImageView) viewFindViewById.findViewById(R.id.button_3);
        zza(viewFindViewById, R.id.button_0, this.zzum[0], uIMediaController2);
        zza(viewFindViewById, R.id.button_1, this.zzum[1], uIMediaController2);
        zza(viewFindViewById, R.id.button_play_pause_toggle, R.id.cast_button_type_play_pause_toggle, uIMediaController2);
        zza(viewFindViewById, R.id.button_2, this.zzum[2], uIMediaController2);
        zza(viewFindViewById, R.id.button_3, this.zzum[3], uIMediaController2);
        View viewFindViewById4 = findViewById(R.id.ad_container);
        this.zzuo = viewFindViewById4;
        this.zzuq = (ImageView) viewFindViewById4.findViewById(R.id.ad_image_view);
        this.zzup = this.zzuo.findViewById(R.id.ad_background_image_view);
        TextView textView3 = (TextView) this.zzuo.findViewById(R.id.ad_label);
        this.zzus = textView3;
        textView3.setTextColor(this.zzue);
        this.zzus.setBackgroundColor(this.zzuc);
        this.zzur = (TextView) this.zzuo.findViewById(R.id.ad_in_progress_label);
        this.zzuu = (TextView) findViewById(R.id.ad_skip_text);
        TextView textView4 = (TextView) findViewById(R.id.ad_skip_button);
        this.zzut = textView4;
        textView4.setOnClickListener(new com.google.android.gms.cast.framework.media.widget.zzb(this));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.quantum_ic_keyboard_arrow_down_white_36);
        }
        zzeg();
        zzef();
        zzaa zzaaVar = new zzaa(getApplicationContext(), new ImageHints(-1, this.zzuq.getWidth(), this.zzuq.getHeight()));
        this.zzuv = zzaaVar;
        zzaaVar.zza(new com.google.android.gms.cast.framework.media.widget.zza(this));
    }

    private class zzb implements SessionManagerListener<CastSession> {
        private zzb() {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
        }

        @Override // com.google.android.gms.cast.framework.SessionManagerListener
        public final /* synthetic */ void onSessionEnded(Session session, int i) {
            ExpandedControllerActivity.this.finish();
        }

        /* synthetic */ zzb(ExpandedControllerActivity expandedControllerActivity, com.google.android.gms.cast.framework.media.widget.zza zzaVar) {
            this();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        CastContext.getSharedInstance(this).getSessionManager().addSessionManagerListener(this.zzju, CastSession.class);
        CastSession currentCastSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || (!currentCastSession.isConnected() && !currentCastSession.isConnecting())) {
            finish();
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        this.zzux = remoteMediaClient == null || !remoteMediaClient.hasMediaSession();
        zzeg();
        zzeh();
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        CastContext.getSharedInstance(this).getSessionManager().removeSessionManagerListener(this.zzju, CastSession.class);
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.zzuv.clear();
        UIMediaController uIMediaController = this.zzuw;
        if (uIMediaController != null) {
            uIMediaController.setPostRemoteMediaClientListener(null);
            this.zzuw.dispose();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            int i = systemUiVisibility ^ 2;
            if (PlatformVersion.isAtLeastJellyBean()) {
                i = systemUiVisibility ^ 6;
            }
            if (PlatformVersion.isAtLeastKitKat()) {
                i ^= 4096;
            }
            getWindow().getDecorView().setSystemUiVisibility(i);
            if (PlatformVersion.isAtLeastJellyBeanMR2()) {
                setImmersive(true);
            }
        }
    }

    public TextView getStatusTextView() {
        return this.zzuj;
    }

    public SeekBar getSeekBar() {
        return this.zzrp;
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

    private final void zza(View view, int i, int i2, UIMediaController uIMediaController) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (i2 == R.id.cast_button_type_empty) {
            imageView.setVisibility(4);
            return;
        }
        if (i2 != R.id.cast_button_type_custom) {
            if (i2 == R.id.cast_button_type_play_pause_toggle) {
                imageView.setBackgroundResource(this.zzts);
                Drawable drawableZzb = zze.zzb(this, this.zzug, this.zztu);
                Drawable drawableZzb2 = zze.zzb(this, this.zzug, this.zztt);
                Drawable drawableZzb3 = zze.zzb(this, this.zzug, this.zztv);
                imageView.setImageDrawable(drawableZzb2);
                uIMediaController.bindImageViewToPlayPauseToggle(imageView, drawableZzb2, drawableZzb, drawableZzb3, null, false);
                return;
            }
            if (i2 == R.id.cast_button_type_skip_previous) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zztw));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_prev));
                uIMediaController.bindViewToSkipPrev(imageView, 0);
                return;
            }
            if (i2 == R.id.cast_button_type_skip_next) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zztx));
                imageView.setContentDescription(getResources().getString(R.string.cast_skip_next));
                uIMediaController.bindViewToSkipNext(imageView, 0);
                return;
            }
            if (i2 == R.id.cast_button_type_rewind_30_seconds) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zzty));
                imageView.setContentDescription(getResources().getString(R.string.cast_rewind_30));
                uIMediaController.bindViewToRewind(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
                return;
            }
            if (i2 == R.id.cast_button_type_forward_30_seconds) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zztz));
                imageView.setContentDescription(getResources().getString(R.string.cast_forward_30));
                uIMediaController.bindViewToForward(imageView, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
                return;
            }
            if (i2 == R.id.cast_button_type_mute_toggle) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zzua));
                uIMediaController.bindImageViewToMuteToggle(imageView);
            } else if (i2 == R.id.cast_button_type_closed_caption) {
                imageView.setBackgroundResource(this.zzts);
                imageView.setImageDrawable(zze.zzb(this, this.zzug, this.zzub));
                uIMediaController.bindViewToClosedCaption(imageView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RemoteMediaClient getRemoteMediaClient() {
        CastSession currentCastSession = this.zzhg.getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return null;
        }
        return currentCastSession.getRemoteMediaClient();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzef() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        ActionBar supportActionBar;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || (metadata = mediaInfo.getMetadata()) == null || (supportActionBar = getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setTitle(metadata.getString(MediaMetadata.KEY_TITLE));
        supportActionBar.setSubtitle(zzaq.zzb(metadata));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzeg() {
        CastDevice castDevice;
        CastSession currentCastSession = this.zzhg.getCurrentCastSession();
        if (currentCastSession != null && (castDevice = currentCastSession.getCastDevice()) != null) {
            String friendlyName = castDevice.getFriendlyName();
            if (!TextUtils.isEmpty(friendlyName)) {
                this.zzuj.setText(getResources().getString(R.string.cast_casting_to_device, friendlyName));
                return;
            }
        }
        this.zzuj.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzeh() {
        String string;
        Drawable drawable;
        Bitmap bitmap;
        Bitmap bitmapZza;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        String imageUrl = null;
        MediaStatus mediaStatus = remoteMediaClient == null ? null : remoteMediaClient.getMediaStatus();
        if (mediaStatus != null && mediaStatus.isPlayingAd()) {
            if (PlatformVersion.isAtLeastJellyBeanMR1() && this.zzul.getVisibility() == 8 && (drawable = this.zzuk.getDrawable()) != null && (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && (bitmapZza = zze.zza(this, bitmap, 0.25f, 7.5f)) != null) {
                this.zzul.setImageBitmap(bitmapZza);
                this.zzul.setVisibility(0);
            }
            AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
            if (currentAdBreakClip != null) {
                string = currentAdBreakClip.getTitle();
                imageUrl = currentAdBreakClip.getImageUrl();
            } else {
                string = null;
            }
            if (!TextUtils.isEmpty(imageUrl)) {
                this.zzuv.zza(Uri.parse(imageUrl));
                this.zzup.setVisibility(8);
            } else {
                this.zzur.setVisibility(0);
                this.zzup.setVisibility(0);
                this.zzuq.setVisibility(8);
            }
            TextView textView = this.zzus;
            if (TextUtils.isEmpty(string)) {
                string = getResources().getString(R.string.cast_ad_label);
            }
            textView.setText(string);
            if (PlatformVersion.isAtLeastM()) {
                this.zzus.setTextAppearance(this.zzuh);
            } else {
                this.zzus.setTextAppearance(this, this.zzuh);
            }
            this.zzuo.setVisibility(0);
            zza(currentAdBreakClip, remoteMediaClient);
            return;
        }
        this.zzuu.setVisibility(8);
        this.zzut.setVisibility(8);
        this.zzuo.setVisibility(8);
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            this.zzul.setVisibility(8);
            this.zzul.setImageBitmap(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(AdBreakClipInfo adBreakClipInfo, RemoteMediaClient remoteMediaClient) {
        if (this.zzux || remoteMediaClient.isBuffering()) {
            return;
        }
        this.zzut.setVisibility(8);
        if (adBreakClipInfo == null || adBreakClipInfo.getWhenSkippableInMs() == -1) {
            return;
        }
        if (!this.zzuy) {
            zzc zzcVar = new zzc(this, adBreakClipInfo, remoteMediaClient);
            Timer timer = new Timer();
            this.zzuz = timer;
            timer.scheduleAtFixedRate(zzcVar, 0L, 500L);
            this.zzuy = true;
        }
        float whenSkippableInMs = adBreakClipInfo.getWhenSkippableInMs() - remoteMediaClient.getApproximateAdBreakClipPositionMs();
        if (whenSkippableInMs <= 0.0f) {
            this.zzuu.setVisibility(8);
            if (this.zzuy) {
                this.zzuz.cancel();
                this.zzuy = false;
            }
            this.zzut.setVisibility(0);
            this.zzut.setClickable(true);
            return;
        }
        this.zzuu.setVisibility(0);
        this.zzuu.setText(getResources().getString(R.string.cast_expanded_controller_skip_ad_text, Integer.valueOf((int) Math.ceil(whenSkippableInMs / 1000.0f))));
        this.zzut.setClickable(false);
    }

    static /* synthetic */ boolean zza(ExpandedControllerActivity expandedControllerActivity, boolean z) {
        expandedControllerActivity.zzux = false;
        return false;
    }
}
