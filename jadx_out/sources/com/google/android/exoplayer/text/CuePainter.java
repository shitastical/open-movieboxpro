package com.google.android.exoplayer.text;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes4.dex */
final class CuePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "CuePainter";
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private float bottomPaddingFraction;
    private final float cornerRadius;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final RectF lineBounds = new RectF();
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private float textSizePx;
    private int textTop;
    private int windowColor;

    public CuePainter(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.lineSpacingExtra, R.attr.lineSpacingMultiplier}, 0, 0);
        this.spacingAdd = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        float fRound = Math.round((context.getResources().getDisplayMetrics().densityDpi * 2.0f) / 160.0f);
        this.cornerRadius = fRound;
        this.outlineWidth = fRound;
        this.shadowRadius = fRound;
        this.shadowOffset = fRound;
        TextPaint textPaint = new TextPaint();
        this.textPaint = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Cue cue, boolean z, CaptionStyleCompat captionStyleCompat, float f, float f2, Canvas canvas, int i, int i2, int i3, int i4) {
        int iMax;
        int iMin;
        int i5;
        int iRound;
        int i6;
        CharSequence string = cue.text;
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (!z) {
            string = string.toString();
        }
        if (areCharSequencesEqual(this.cueText, string) && Util.areEqual(this.cueTextAlignment, cue.textAlignment) && this.cueLine == cue.line && this.cueLineType == cue.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue.lineAnchor)) && this.cuePosition == cue.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue.positionAnchor)) && this.cueSize == cue.size && this.applyEmbeddedStyles == z && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == captionStyleCompat.windowColor && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat.typeface) && this.textSizePx == f && this.bottomPaddingFraction == f2 && this.parentLeft == i && this.parentTop == i2 && this.parentRight == i3 && this.parentBottom == i4) {
            drawLayout(canvas);
            return;
        }
        this.cueText = string;
        this.cueTextAlignment = cue.textAlignment;
        this.cueLine = cue.line;
        this.cueLineType = cue.lineType;
        this.cueLineAnchor = cue.lineAnchor;
        this.cuePosition = cue.position;
        this.cuePositionAnchor = cue.positionAnchor;
        this.cueSize = cue.size;
        this.applyEmbeddedStyles = z;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = captionStyleCompat.windowColor;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.textSizePx = f;
        this.bottomPaddingFraction = f2;
        this.parentLeft = i;
        this.parentTop = i2;
        this.parentRight = i3;
        this.parentBottom = i4;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.textPaint.setTextSize(f);
        int i9 = (int) ((INNER_PADDING_RATIO * f) + 0.5f);
        int i10 = i9 * 2;
        int i11 = i7 - i10;
        float f3 = this.cueSize;
        if (f3 != Float.MIN_VALUE) {
            i11 = (int) (i11 * f3);
        }
        if (i11 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        Layout.Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        }
        StaticLayout staticLayout = new StaticLayout(string, this.textPaint, i11, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = staticLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i12 = 0;
        int iMax2 = 0;
        while (i12 < lineCount) {
            iMax2 = Math.max((int) Math.ceil(this.textLayout.getLineWidth(i12)), iMax2);
            i12++;
            height = height;
        }
        int i13 = height;
        if (this.cueSize == Float.MIN_VALUE || iMax2 >= i11) {
            i11 = iMax2;
        }
        int i14 = i11 + i10;
        float f4 = this.cuePosition;
        if (f4 != Float.MIN_VALUE) {
            int iRound2 = Math.round(i7 * f4);
            int i15 = this.parentLeft;
            int i16 = iRound2 + i15;
            int i17 = this.cuePositionAnchor;
            if (i17 == 2) {
                i16 -= i14;
            } else if (i17 == 1) {
                i16 = ((i16 * 2) - i14) / 2;
            }
            iMax = Math.max(i16, i15);
            iMin = Math.min(i14 + iMax, this.parentRight);
        } else {
            iMax = (i7 - i14) / 2;
            iMin = iMax + i14;
        }
        float f5 = this.cueLine;
        if (f5 != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                iRound = Math.round(i8 * f5);
                i6 = this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f6 = this.cueLine;
                if (f6 >= 0.0f) {
                    iRound = Math.round(f6 * lineBottom);
                    i6 = this.parentTop;
                } else {
                    iRound = Math.round(f6 * lineBottom);
                    i6 = this.parentBottom;
                }
            }
            i5 = iRound + i6;
            int i18 = this.cueLineAnchor;
            if (i18 == 2) {
                i5 -= i13;
            } else if (i18 == 1) {
                i5 = ((i5 * 2) - i13) / 2;
            }
            int i19 = i5 + i13;
            int i20 = this.parentBottom;
            if (i19 > i20) {
                i5 = i20 - i13;
            } else {
                int i21 = this.parentTop;
                if (i5 < i21) {
                    i5 = i21;
                }
            }
        } else {
            i5 = (this.parentBottom - i13) - ((int) (i8 * f2));
        }
        this.textLayout = new StaticLayout(string, this.textPaint, iMin - iMax, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLeft = iMax;
        this.textTop = i5;
        this.textPaddingX = i9;
        drawLayout(canvas);
    }

    private void drawLayout(Canvas canvas) {
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(this.textLeft, this.textTop);
        if (Color.alpha(this.windowColor) > 0) {
            this.paint.setColor(this.windowColor);
            canvas.drawRect(-this.textPaddingX, 0.0f, staticLayout.getWidth() + this.textPaddingX, staticLayout.getHeight(), this.paint);
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            this.paint.setColor(this.backgroundColor);
            float lineTop = staticLayout.getLineTop(0);
            int lineCount = staticLayout.getLineCount();
            for (int i = 0; i < lineCount; i++) {
                this.lineBounds.left = staticLayout.getLineLeft(i) - this.textPaddingX;
                this.lineBounds.right = staticLayout.getLineRight(i) + this.textPaddingX;
                this.lineBounds.top = lineTop;
                this.lineBounds.bottom = staticLayout.getLineBottom(i);
                lineTop = this.lineBounds.bottom;
                RectF rectF = this.lineBounds;
                float f = this.cornerRadius;
                canvas.drawRoundRect(rectF, f, f, this.paint);
            }
        }
        int i2 = this.edgeType;
        if (i2 == 1) {
            this.textPaint.setStrokeJoin(Paint.Join.ROUND);
            this.textPaint.setStrokeWidth(this.outlineWidth);
            this.textPaint.setColor(this.edgeColor);
            this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            staticLayout.draw(canvas);
        } else if (i2 == 2) {
            TextPaint textPaint = this.textPaint;
            float f2 = this.shadowRadius;
            float f3 = this.shadowOffset;
            textPaint.setShadowLayer(f2, f3, f3, this.edgeColor);
        } else if (i2 == 3 || i2 == 4) {
            boolean z = i2 == 3;
            int i3 = z ? -1 : this.edgeColor;
            int i4 = z ? this.edgeColor : -1;
            float f4 = this.shadowRadius / 2.0f;
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            float f5 = -f4;
            this.textPaint.setShadowLayer(this.shadowRadius, f5, f5, i3);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i4);
        }
        this.textPaint.setColor(this.foregroundColor);
        this.textPaint.setStyle(Paint.Style.FILL);
        staticLayout.draw(canvas);
        this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        canvas.restoreToCount(iSave);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
