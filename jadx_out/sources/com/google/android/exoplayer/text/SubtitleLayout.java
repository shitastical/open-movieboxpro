package com.google.android.exoplayer.text;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class SubtitleLayout extends View {
    private static final int ABSOLUTE = 2;
    public static final float DEFAULT_BOTTOM_PADDING_FRACTION = 0.08f;
    public static final float DEFAULT_TEXT_SIZE_FRACTION = 0.0533f;
    private static final int FRACTIONAL = 0;
    private static final int FRACTIONAL_IGNORE_PADDING = 1;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private final List<CuePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public SubtitleLayout(Context context) {
        this(context, null);
    }

    public SubtitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.applyEmbeddedStyles = true;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    public void setCues(List<Cue> list) {
        if (this.cues == list) {
            return;
        }
        this.cues = list;
        int size = list == null ? 0 : list.size();
        while (this.painters.size() < size) {
            this.painters.add(new CuePainter(getContext()));
        }
        invalidate();
    }

    public void setFixedTextSize(int i, float f) {
        Resources resources;
        Context context = getContext();
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        setTextSize(2, TypedValue.applyDimension(i, f, resources.getDisplayMetrics()));
    }

    public void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    private void setTextSize(int i, float f) {
        if (this.textSizeType == i && this.textSize == f) {
            return;
        }
        this.textSizeType = i;
        this.textSize = f;
        invalidate();
    }

    public void setApplyEmbeddedStyles(boolean z) {
        if (this.applyEmbeddedStyles == z) {
            return;
        }
        this.applyEmbeddedStyles = z;
        invalidate();
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style == captionStyleCompat) {
            return;
        }
        this.style = captionStyleCompat;
        invalidate();
    }

    public void setBottomPaddingFraction(float f) {
        if (this.bottomPaddingFraction == f) {
            return;
        }
        this.bottomPaddingFraction = f;
        invalidate();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
        float f;
        List<Cue> list = this.cues;
        int i = 0;
        int size = list == null ? 0 : list.size();
        int top = getTop();
        int bottom = getBottom();
        int left = getLeft() + getPaddingLeft();
        int paddingTop = getPaddingTop() + top;
        int right = getRight() + getPaddingRight();
        int paddingBottom = bottom - getPaddingBottom();
        if (paddingBottom <= paddingTop || right <= left) {
            return;
        }
        int i2 = this.textSizeType;
        if (i2 == 2) {
            f = this.textSize;
        } else {
            f = (i2 == 0 ? paddingBottom - paddingTop : bottom - top) * this.textSize;
        }
        if (f <= 0.0f) {
            return;
        }
        while (i < size) {
            int i3 = paddingBottom;
            int i4 = right;
            this.painters.get(i).draw(this.cues.get(i), this.applyEmbeddedStyles, this.style, f, this.bottomPaddingFraction, canvas, left, paddingTop, i4, i3);
            i++;
            paddingBottom = i3;
            right = i4;
        }
    }
}
