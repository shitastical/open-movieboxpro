package com.owen.focus;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ColorFocusBorder extends AbsFocusBorder {
    private int mBorderColor;
    private Paint mBorderPaint;
    private View mBorderView;
    private float mBorderWidth;
    private float mRoundRadius;
    private ObjectAnimator mRoundRadiusAnimator;
    private int mShadowColor;
    private Paint mShadowPaint;
    private float mShadowWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }

    @Override // com.owen.focus.AbsFocusBorder
    List<Animator> getSequentiallyAnimators(float f, float f2, int i, int i2, AbsFocusBorder.Options options) {
        return null;
    }

    private ColorFocusBorder(Context context, Builder builder) {
        super(context, builder);
        this.mRoundRadius = 0.0f;
        this.mShadowColor = builder.mShadowColor;
        this.mShadowWidth = builder.mShadowWidth;
        this.mBorderColor = builder.mBorderColor;
        float f = builder.mBorderWidth;
        this.mBorderWidth = f;
        float f2 = this.mShadowWidth + f;
        this.mPaddingRectF.set(f2, f2, f2, f2);
        initPaint();
        BorderView borderView = new BorderView(context);
        this.mBorderView = borderView;
        borderView.setLayerType(1, null);
        addView(this.mBorderView, new ViewGroup.LayoutParams(-1, -1));
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mShadowPaint = paint;
        paint.setColor(this.mShadowColor);
        this.mShadowPaint.setMaskFilter(new BlurMaskFilter(this.mShadowWidth, BlurMaskFilter.Blur.OUTER));
        Paint paint2 = new Paint();
        this.mBorderPaint = paint2;
        paint2.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setMaskFilter(new BlurMaskFilter(0.5f, BlurMaskFilter.Blur.NORMAL));
    }

    @Override // com.owen.focus.AbsFocusBorder
    public View getBorderView() {
        return this.mBorderView;
    }

    protected void setRoundRadius(float f) {
        if (this.mRoundRadius != f) {
            this.mRoundRadius = f;
            ViewCompat.postInvalidateOnAnimation(this.mBorderView);
        }
    }

    @Override // com.owen.focus.AbsFocusBorder
    public float getRoundRadius() {
        return this.mRoundRadius;
    }

    @Override // com.owen.focus.AbsFocusBorder
    List<Animator> getTogetherAnimators(float f, float f2, int i, int i2, AbsFocusBorder.Options options) {
        if (options instanceof Options) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getRoundRadiusAnimator(((Options) options).roundRadius));
            return arrayList;
        }
        if (this.mRoundRadius == 0.0f) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getRoundRadiusAnimator(0.0f));
        return arrayList2;
    }

    private ObjectAnimator getRoundRadiusAnimator(float f) {
        ObjectAnimator objectAnimator = this.mRoundRadiusAnimator;
        if (objectAnimator == null) {
            this.mRoundRadiusAnimator = ObjectAnimator.ofFloat(this, "roundRadius", getRoundRadius(), f);
        } else {
            objectAnimator.setFloatValues(getRoundRadius(), f);
        }
        return this.mRoundRadiusAnimator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDrawShadow(Canvas canvas) {
        if (this.mShadowWidth > 0.0f) {
            canvas.save();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            this.mTempRectF.set(this.mFrameRectF);
            RectF rectF = this.mTempRectF;
            float f = this.mRoundRadius;
            rectF.inset(f / 2.0f, f / 2.0f);
            canvas.clipRect(this.mTempRectF, Region.Op.DIFFERENCE);
            RectF rectF2 = this.mFrameRectF;
            float f2 = this.mRoundRadius;
            canvas.drawRoundRect(rectF2, f2, f2, this.mShadowPaint);
            canvas.restore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDrawBorder(Canvas canvas) {
        if (this.mBorderWidth > 0.0f) {
            canvas.save();
            this.mTempRectF.set(this.mFrameRectF);
            RectF rectF = this.mTempRectF;
            float f = this.mRoundRadius;
            canvas.drawRoundRect(rectF, f, f, this.mBorderPaint);
            canvas.restore();
        }
    }

    class BorderView extends View {
        public BorderView(Context context) {
            super(context);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            ColorFocusBorder.this.onDrawShadow(canvas);
            ColorFocusBorder.this.onDrawBorder(canvas);
        }
    }

    public static class Options extends AbsFocusBorder.Options {
        private float roundRadius = 0.0f;

        Options() {
        }

        private static class OptionsHolder {
            private static final Options INSTANCE = new Options();

            private OptionsHolder() {
            }
        }

        public static Options get(float f, float f2, float f3) {
            return get(f, f2, f3, null);
        }

        public static Options get(float f, float f2, float f3, String str) {
            OptionsHolder.INSTANCE.scaleX = f;
            OptionsHolder.INSTANCE.scaleY = f2;
            OptionsHolder.INSTANCE.roundRadius = f3;
            OptionsHolder.INSTANCE.title = str;
            return OptionsHolder.INSTANCE;
        }
    }

    public static final class Builder extends AbsFocusBorder.Builder {
        private int mShadowColor = 0;
        private int mShadowColorRes = 0;
        private float mShadowWidth = 0.0f;
        private int mShadowWidthUnit = -1;
        private float mShadowWidthSrc = 0.0f;
        private int mBorderColor = 0;
        private int mBorderColorRes = 0;
        private float mBorderWidth = 0.0f;
        private int mBorderWidthUnit = -1;
        private float mBorderWidthSrc = 0.0f;

        public Builder shadowColor(int i) {
            this.mShadowColor = i;
            return this;
        }

        public Builder shadowColorRes(int i) {
            this.mShadowColorRes = i;
            return this;
        }

        public Builder shadowWidth(float f) {
            this.mShadowWidth = f;
            return this;
        }

        public Builder shadowWidth(int i, float f) {
            this.mShadowWidthUnit = i;
            this.mShadowWidthSrc = f;
            return this;
        }

        public Builder borderColor(int i) {
            this.mBorderColor = i;
            return this;
        }

        public Builder borderColorRes(int i) {
            this.mBorderColorRes = i;
            return this;
        }

        public Builder borderWidth(float f) {
            this.mBorderWidth = f;
            return this;
        }

        public Builder borderWidth(int i, float f) {
            this.mBorderWidthUnit = i;
            this.mBorderWidthSrc = f;
            return this;
        }

        @Override // com.owen.focus.AbsFocusBorder.Builder
        public FocusBorder build(Activity activity) {
            if (activity == null) {
                throw new NullPointerException("The activity cannot be null");
            }
            return build((ViewGroup) activity.findViewById(android.R.id.content));
        }

        @Override // com.owen.focus.AbsFocusBorder.Builder
        public FocusBorder build(ViewGroup viewGroup) {
            if (viewGroup == null) {
                throw new NullPointerException("The FocusBorder parent cannot be null");
            }
            int i = this.mBorderWidthUnit;
            if (i >= 0) {
                float f = this.mBorderWidthSrc;
                if (f > 0.0f) {
                    this.mBorderWidth = TypedValue.applyDimension(i, f, viewGroup.getResources().getDisplayMetrics());
                }
            }
            int i2 = this.mShadowWidthUnit;
            if (i2 >= 0) {
                float f2 = this.mShadowWidthSrc;
                if (f2 > 0.0f) {
                    this.mShadowWidth = TypedValue.applyDimension(i2, f2, viewGroup.getResources().getDisplayMetrics());
                }
            }
            if (this.mBorderColorRes > 0) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mBorderColor = viewGroup.getResources().getColor(this.mBorderColorRes, viewGroup.getContext().getTheme());
                } else {
                    this.mBorderColor = viewGroup.getResources().getColor(this.mBorderColorRes);
                }
            }
            if (this.mShadowColorRes > 0) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mShadowColor = viewGroup.getResources().getColor(this.mShadowColorRes, viewGroup.getContext().getTheme());
                } else {
                    this.mShadowColor = viewGroup.getResources().getColor(this.mShadowColorRes);
                }
            }
            ColorFocusBorder colorFocusBorder = new ColorFocusBorder(viewGroup.getContext().getApplicationContext(), this);
            viewGroup.addView(colorFocusBorder, new ViewGroup.LayoutParams(1, 1));
            return colorFocusBorder;
        }
    }
}
