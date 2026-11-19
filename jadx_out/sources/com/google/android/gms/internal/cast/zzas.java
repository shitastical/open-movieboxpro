package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzas extends View implements zzbn {
    private List<AdBreakInfo> zzdj;
    private final SeekBar zzrp;
    private final int zzrr;
    private final int zzrs;
    private final int zzrt;
    private boolean zzru;
    private final Matrix zzrv;
    private final zzbh zzrw;
    private Paint zzrx;
    private Paint zzry;
    private Bitmap zzrz;

    public zzas(Context context, SeekBar seekBar, zzbh zzbhVar) {
        super(context);
        this.zzrv = new Matrix();
        this.zzdj = new ArrayList();
        this.zzrp = seekBar;
        this.zzrw = zzbhVar;
        Context context2 = getContext();
        this.zzrr = (int) (context2 == null ? Math.round(3.0d) : Math.round(((double) context2.getResources().getDisplayMetrics().density) * 3.0d));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castAdBreakMarkerColor, 0);
        this.zzrs = resourceId;
        this.zzrt = context.getResources().getColor(resourceId);
        typedArrayObtainStyledAttributes.recycle();
        boolean zZzei = com.google.android.gms.cast.framework.media.widget.zze.zzei();
        this.zzru = zZzei;
        if (zZzei) {
            seekBar.setAlpha(0.01f);
            invalidate();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzbn
    public final synchronized void zzde() {
        postInvalidate();
    }

    @Override // com.google.android.gms.internal.cast.zzbn
    public final synchronized void zzd(List<AdBreakInfo> list) {
        if (Objects.equal(this.zzdj, list)) {
            return;
        }
        this.zzdj = list == null ? new ArrayList() : new ArrayList(list);
        postInvalidate();
    }

    @Override // com.google.android.gms.internal.cast.zzbn
    public final synchronized void zzdf() {
        postInvalidate();
    }

    @Override // android.view.View
    protected final synchronized void onDraw(Canvas canvas) {
        Drawable drawableZza;
        super.onDraw(canvas);
        Bitmap bitmap = this.zzrz;
        if (bitmap == null || bitmap.getWidth() != getMeasuredWidth() || this.zzrz.getHeight() != getMeasuredHeight()) {
            this.zzrz = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
        this.zzrz.eraseColor(0);
        Canvas canvas2 = new Canvas(this.zzrz);
        if (this.zzru) {
            Drawable progressDrawable = this.zzrp.getProgressDrawable();
            int iSave = canvas2.save();
            canvas2.translate(getPaddingLeft(), getPaddingTop());
            progressDrawable.draw(canvas2);
            canvas2.restoreToCount(iSave);
        }
        if (this.zzrw.zzdo()) {
            zzdg();
            int iSave2 = canvas2.save();
            canvas2.translate(getPaddingLeft(), getPaddingTop());
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            double dZzdr = ((double) this.zzrw.zzdr()) / ((double) this.zzrw.zzdm());
            double dZzds = ((double) this.zzrw.zzds()) / ((double) this.zzrw.zzdm());
            double d = measuredWidth;
            int iFloor = (int) Math.floor(dZzdr * d);
            int iCeil = (int) Math.ceil(dZzds * d);
            if (iFloor > 0) {
                canvas2.drawRect(0.0f, 0.0f, iFloor, measuredHeight, this.zzry);
            }
            if (iCeil < measuredWidth) {
                canvas2.drawRect(iCeil, 0.0f, measuredWidth, measuredHeight, this.zzry);
            }
            canvas2.restoreToCount(iSave2);
        } else if (!this.zzrw.zzdo()) {
            int iZzdt = (int) (0 - this.zzrw.zzdt());
            zzdg();
            int iSave3 = canvas2.save();
            canvas2.translate(getPaddingLeft(), getPaddingTop());
            int measuredWidth2 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int measuredHeight2 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            int iFloor2 = (int) Math.floor((((double) iZzdt) / ((double) this.zzrw.zzdm())) * ((double) measuredWidth2));
            if (iFloor2 > 0) {
                canvas2.drawRect(0.0f, 0.0f, iFloor2, measuredHeight2, this.zzry);
            }
            canvas2.restoreToCount(iSave3);
        }
        if (!this.zzdj.isEmpty()) {
            if (this.zzrx == null) {
                Paint paint = new Paint(1);
                this.zzrx = paint;
                paint.setColor(this.zzrt);
                this.zzrx.setStyle(Paint.Style.FILL);
            }
            int max = this.zzrp.getMax();
            int iRound = Math.round(getMeasuredHeight() / 2.0f);
            int measuredWidth3 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (AdBreakInfo adBreakInfo : this.zzdj) {
                if (adBreakInfo != null) {
                    long playbackPositionInMs = adBreakInfo.getPlaybackPositionInMs();
                    int iMin = playbackPositionInMs == -1000 ? max : Math.min((int) (playbackPositionInMs - this.zzrw.zzdt()), max);
                    if (iMin >= 0) {
                        canvas2.drawCircle(getPaddingLeft() + ((int) ((((double) iMin) * ((double) measuredWidth3)) / ((double) max))), iRound, this.zzrr, this.zzrx);
                    }
                }
            }
        }
        if (this.zzru && (drawableZza = com.google.android.gms.cast.framework.media.widget.zze.zza(this.zzrp)) != null) {
            int iSave4 = canvas2.save();
            canvas2.translate(getPaddingLeft() - this.zzrp.getThumbOffset(), getPaddingTop());
            drawableZza.draw(canvas2);
            canvas2.restoreToCount(iSave4);
        }
        canvas.drawBitmap(this.zzrz, this.zzrv, null);
    }

    private final void zzdg() {
        if (this.zzry == null) {
            Paint paint = new Paint(1);
            this.zzry = paint;
            paint.setColor(-8421505);
            this.zzry.setStyle(Paint.Style.FILL);
            this.zzry.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        }
    }
}
