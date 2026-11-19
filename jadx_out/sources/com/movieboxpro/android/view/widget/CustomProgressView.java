package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomProgressView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u001a\u001a\u00020\u001b2\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0014J\u0010\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0014R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/widget/CustomProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "favoriteEpisodes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "viewWidth", "viewHeight", "lineWidth", "", "bgPaint", "Landroid/graphics/Paint;", "linePaint", "perWidth", "bgRectF", "Landroid/graphics/RectF;", "lineRectF", "complete", "", "setEpisodes", "", "episodes", "setComplete", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomProgressView extends View {
    private Paint bgPaint;
    private RectF bgRectF;
    private boolean complete;
    private ArrayList<Integer> favoriteEpisodes;
    private Paint linePaint;
    private RectF lineRectF;
    private float lineWidth;
    private float perWidth;
    private int viewHeight;
    private int viewWidth;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ CustomProgressView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.favoriteEpisodes = new ArrayList<>();
        this.lineWidth = CommonExtKt.dp2Px(4);
        this.bgPaint = new Paint();
        this.linePaint = new Paint();
        this.bgPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.bgPaint.setAntiAlias(true);
        this.bgPaint.setDither(true);
        this.bgPaint.setStrokeWidth(this.lineWidth);
        this.bgPaint.setStyle(Paint.Style.FILL);
        this.bgPaint.setColor(Color.parseColor("#21FFFFFF"));
        this.linePaint.setStrokeCap(Paint.Cap.SQUARE);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setDither(true);
        this.linePaint.setStrokeWidth(this.lineWidth);
        this.linePaint.setColor(CommonExtKt.colorInt(R.color.white_30alpha));
    }

    public final void setEpisodes(ArrayList<Integer> episodes) {
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        this.favoriteEpisodes.clear();
        this.favoriteEpisodes.addAll(episodes);
        this.perWidth = this.viewWidth / episodes.size();
        invalidate();
    }

    public final void setComplete(boolean complete) {
        this.complete = complete;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (size == 0 || size2 == 0) {
            return;
        }
        this.viewWidth = size;
        this.viewHeight = size2;
        if (this.favoriteEpisodes.isEmpty()) {
            return;
        }
        this.perWidth = this.viewWidth / this.favoriteEpisodes.size();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float f = this.lineWidth;
        canvas.drawRoundRect(0.0f, f, this.viewWidth, f * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.bgPaint);
        if (this.complete) {
            RectF rectF = this.lineRectF;
            if (rectF == null) {
                float f2 = this.lineWidth;
                this.lineRectF = new RectF(0.0f, f2, this.viewWidth, 2 * f2);
            } else {
                Intrinsics.checkNotNull(rectF);
                float f3 = this.lineWidth;
                rectF.set(0.0f, f3, this.viewWidth, 2 * f3);
            }
            RectF rectF2 = this.lineRectF;
            Intrinsics.checkNotNull(rectF2);
            canvas.drawRoundRect(rectF2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
            return;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Object obj : this.favoriteEpisodes) {
            int i4 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((Number) obj).intValue() == 1) {
                int i5 = i2 + 1;
                if (i == this.favoriteEpisodes.size() - 1) {
                    float f4 = this.perWidth;
                    float f5 = this.lineWidth;
                    canvas.drawRoundRect(i3 * f4, f5, (i3 + i5) * f4, f5 * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                }
                i2 = i5;
            } else {
                if (i2 != 0) {
                    float f6 = this.perWidth;
                    float f7 = this.lineWidth;
                    canvas.drawRoundRect(i3 * f6, f7, (i3 + i2) * f6, f7 * 2, CommonExtKt.dp2Px(2), CommonExtKt.dp2Px(2), this.linePaint);
                    i2 = 0;
                }
                i3 = i4;
            }
            i = i4;
        }
    }
}
