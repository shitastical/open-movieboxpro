package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;

/* JADX INFO: loaded from: classes4.dex */
public final class zzr extends RelativeLayout implements IntroductoryOverlay {
    private Activity zzij;
    private IntroductoryOverlay.OnOverlayDismissedListener zzin;
    private final boolean zzje;
    private boolean zzjg;
    private int zzjj;
    private final zzu zzjk;

    public zzr(IntroductoryOverlay.Builder builder) {
        this(builder, null, R.attr.castIntroOverlayStyle);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzr(IntroductoryOverlay.Builder builder, AttributeSet attributeSet, int i) {
        super(builder.getActivity(), null, i);
        zzs zzsVar = null;
        this.zzij = builder.getActivity();
        this.zzje = builder.zzal();
        this.zzin = builder.zzaj();
        TypedArray typedArrayObtainStyledAttributes = this.zzij.getTheme().obtainStyledAttributes(null, R.styleable.CastIntroOverlay, i, R.style.CastIntroOverlay);
        if (builder.zzai() != null) {
            Rect rect = new Rect();
            builder.zzai().getGlobalVisibleRect(rect);
            zzu zzuVar = new zzu(zzsVar);
            this.zzjk = zzuVar;
            zzuVar.x = rect.centerX();
            zzuVar.y = rect.centerY();
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
            Paint paint = new Paint();
            paint.setColor(-1);
            paint.setAlpha(0);
            paint.setXfermode(porterDuffXfermode);
            paint.setAntiAlias(true);
            zzuVar.zzjn = paint;
            zzuVar.zzjo = builder.zzao();
            if (zzuVar.zzjo == 0.0f) {
                zzuVar.zzjo = typedArrayObtainStyledAttributes.getDimension(R.styleable.CastIntroOverlay_castFocusRadius, 0.0f);
            }
        } else {
            this.zzjk = null;
        }
        LayoutInflater.from(this.zzij).inflate(R.layout.cast_intro_overlay, this);
        int iZzak = builder.zzak();
        this.zzjj = iZzak;
        if (iZzak == 0) {
            this.zzjj = typedArrayObtainStyledAttributes.getColor(R.styleable.CastIntroOverlay_castBackgroundColor, Color.argb(0, 0, 0, 0));
        }
        TextView textView = (TextView) findViewById(R.id.textTitle);
        if (!TextUtils.isEmpty(builder.zzam())) {
            textView.setText(builder.zzam());
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastIntroOverlay_castTitleTextAppearance, 0);
            if (resourceId != 0) {
                textView.setTextAppearance(this.zzij, resourceId);
            }
        }
        String strZzan = builder.zzan();
        strZzan = TextUtils.isEmpty(strZzan) ? typedArrayObtainStyledAttributes.getString(R.styleable.CastIntroOverlay_castButtonText) : strZzan;
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.CastIntroOverlay_castButtonBackgroundColor, Color.argb(0, 0, 0, 0));
        Button button = (Button) findViewById(R.id.button);
        button.setText(strZzan);
        button.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastIntroOverlay_castButtonTextAppearance, 0);
        if (resourceId2 != 0) {
            button.setTextAppearance(this.zzij, resourceId2);
        }
        button.setOnClickListener(new zzs(this));
        typedArrayObtainStyledAttributes.recycle();
        setFitsSystemWindows(true);
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        Activity activity = this.zzij;
        if (activity == null || zzn.zzg(activity)) {
            return;
        }
        if (this.zzje && IntroductoryOverlay.zza.zze(this.zzij)) {
            this.zzij = null;
            this.zzin = null;
        } else {
            if (this.zzjg) {
                return;
            }
            this.zzjg = true;
            ((ViewGroup) this.zzij.getWindow().getDecorView()).addView(this);
        }
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        Activity activity = this.zzij;
        if (activity != null) {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(this);
            this.zzij = null;
        }
        this.zzin = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void dispatchDraw(Canvas canvas) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        canvas2.drawColor(this.zzjj);
        zzu zzuVar = this.zzjk;
        if (zzuVar != null) {
            canvas2.drawCircle(zzuVar.x, this.zzjk.y, this.zzjk.zzjo, this.zzjk.zzjn);
        }
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        bitmapCreateBitmap.recycle();
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        if (this.zzij != null) {
            this.zzij = null;
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzaw() {
        IntroductoryOverlay.zza.zzd(this.zzij);
        IntroductoryOverlay.OnOverlayDismissedListener onOverlayDismissedListener = this.zzin;
        if (onOverlayDismissedListener != null) {
            onOverlayDismissedListener.onOverlayDismissed();
            this.zzin = null;
        }
        remove();
    }
}
