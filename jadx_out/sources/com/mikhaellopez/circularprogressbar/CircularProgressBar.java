package com.mikhaellopez.circularprogressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes5.dex */
public class CircularProgressBar extends View {
    private static final int DEFAULT_ANIMATION_DURATION = 1500;
    private static final float DEFAULT_MAX_VALUE = 100.0f;
    private static final float DEFAULT_START_ANGLE = 270.0f;
    private int backgroundColor;
    private Paint backgroundPaint;
    private float backgroundStrokeWidth;
    private int color;
    private Paint foregroundPaint;
    private boolean indeterminateMode;
    private IndeterminateModeChangeListener indeterminateModeChangeListener;
    private Handler indeterminateModeHandler;
    private Runnable indeterminateModeRunnable;
    private float progress;
    private ValueAnimator progressAnimator;
    private ProgressChangeListener progressChangeListener;
    private float progressMax;
    private RectF rectF;
    private boolean rightToLeft;
    private float startAngle;
    private float strokeWidth;

    public interface IndeterminateModeChangeListener {
        void onModeChange(boolean z);
    }

    public interface ProgressChangeListener {
        void onProgressChanged(float f);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.progress = 0.0f;
        this.progressMax = DEFAULT_MAX_VALUE;
        this.strokeWidth = getResources().getDimension(R.dimen.default_stroke_width);
        this.backgroundStrokeWidth = getResources().getDimension(R.dimen.default_background_stroke_width);
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.backgroundColor = -7829368;
        this.rightToLeft = true;
        this.indeterminateMode = false;
        this.startAngle = DEFAULT_START_ANGLE;
        this.indeterminateModeRunnable = new Runnable() { // from class: com.mikhaellopez.circularprogressbar.CircularProgressBar.2
            @Override // java.lang.Runnable
            public void run() {
                if (CircularProgressBar.this.indeterminateMode) {
                    CircularProgressBar.this.indeterminateModeHandler.postDelayed(CircularProgressBar.this.indeterminateModeRunnable, 1500L);
                    CircularProgressBar circularProgressBar = CircularProgressBar.this;
                    circularProgressBar.rightToLeft = !circularProgressBar.rightToLeft;
                    if (CircularProgressBar.this.rightToLeft) {
                        CircularProgressBar.this.setProgressWithAnimation(0.0f);
                    } else {
                        CircularProgressBar circularProgressBar2 = CircularProgressBar.this;
                        circularProgressBar2.setProgressWithAnimation(circularProgressBar2.progressMax);
                    }
                }
            }
        };
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.rectF = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CircularProgressBar, 0, 0);
        try {
            this.progress = typedArrayObtainStyledAttributes.getFloat(R.styleable.CircularProgressBar_cpb_progress, this.progress);
            this.progressMax = typedArrayObtainStyledAttributes.getFloat(R.styleable.CircularProgressBar_cpb_progress_max, this.progressMax);
            this.indeterminateMode = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CircularProgressBar_cpb_indeterminate_mode, this.indeterminateMode);
            this.strokeWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_cpb_progressbar_width, this.strokeWidth);
            this.backgroundStrokeWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_cpb_background_progressbar_width, this.backgroundStrokeWidth);
            this.color = typedArrayObtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpb_progressbar_color, this.color);
            this.backgroundColor = typedArrayObtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpb_background_progressbar_color, this.backgroundColor);
            typedArrayObtainStyledAttributes.recycle();
            Paint paint = new Paint(1);
            this.backgroundPaint = paint;
            paint.setColor(this.backgroundColor);
            this.backgroundPaint.setStyle(Paint.Style.STROKE);
            this.backgroundPaint.setStrokeWidth(this.backgroundStrokeWidth);
            Paint paint2 = new Paint(1);
            this.foregroundPaint = paint2;
            paint2.setColor(this.color);
            this.foregroundPaint.setStyle(Paint.Style.STROKE);
            this.foregroundPaint.setStrokeWidth(this.strokeWidth);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.indeterminateMode) {
            enableIndeterminateMode(true);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        Handler handler = this.indeterminateModeHandler;
        if (handler != null) {
            handler.removeCallbacks(this.indeterminateModeRunnable);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.rectF, this.backgroundPaint);
        canvas.drawArc(this.rectF, this.startAngle, ((this.rightToLeft ? 360 : -360) * ((this.progress * DEFAULT_MAX_VALUE) / this.progressMax)) / DEFAULT_MAX_VALUE, false, this.foregroundPaint);
    }

    private void reDraw() {
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMin = Math.min(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        setMeasuredDimension(iMin, iMin);
        float f = this.strokeWidth;
        float f2 = this.backgroundStrokeWidth;
        if (f <= f2) {
            f = f2;
        }
        float f3 = f / 2.0f;
        float f4 = 0.0f + f3;
        float f5 = iMin - f3;
        this.rectF.set(f4, f4, f5, f5);
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        setProgress(f, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(float f, boolean z) {
        ValueAnimator valueAnimator;
        if (!z && (valueAnimator = this.progressAnimator) != null) {
            valueAnimator.cancel();
            if (this.indeterminateMode) {
                enableIndeterminateMode(false);
            }
        }
        float f2 = this.progressMax;
        if (f <= f2) {
            f2 = f;
        }
        this.progress = f2;
        ProgressChangeListener progressChangeListener = this.progressChangeListener;
        if (progressChangeListener != null) {
            progressChangeListener.onProgressChanged(f);
        }
        invalidate();
    }

    public float getProgressMax() {
        return this.progressMax;
    }

    public void setProgressMax(float f) {
        if (f < 0.0f) {
            f = DEFAULT_MAX_VALUE;
        }
        this.progressMax = f;
        reDraw();
    }

    public float getProgressBarWidth() {
        return this.strokeWidth;
    }

    public void setProgressBarWidth(float f) {
        this.strokeWidth = f;
        this.foregroundPaint.setStrokeWidth(f);
        reDraw();
    }

    public float getBackgroundProgressBarWidth() {
        return this.backgroundStrokeWidth;
    }

    public void setBackgroundProgressBarWidth(float f) {
        this.backgroundStrokeWidth = f;
        this.backgroundPaint.setStrokeWidth(f);
        reDraw();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        this.foregroundPaint.setColor(i);
        reDraw();
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        this.backgroundPaint.setColor(i);
        reDraw();
    }

    public void setProgressWithAnimation(float f) {
        setProgressWithAnimation(f, 1500);
    }

    public void setProgressWithAnimation(float f, int i) {
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.progress, f);
        this.progressAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(i);
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.mikhaellopez.circularprogressbar.CircularProgressBar.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                float fFloatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                CircularProgressBar.this.setProgress(fFloatValue, true);
                if (CircularProgressBar.this.indeterminateMode) {
                    float f2 = (fFloatValue * 360.0f) / CircularProgressBar.DEFAULT_MAX_VALUE;
                    CircularProgressBar circularProgressBar = CircularProgressBar.this;
                    if (!circularProgressBar.rightToLeft) {
                        f2 = -f2;
                    }
                    circularProgressBar.startAngle = f2 + CircularProgressBar.DEFAULT_START_ANGLE;
                }
            }
        });
        this.progressAnimator.start();
    }

    public void enableIndeterminateMode(boolean z) {
        this.indeterminateMode = z;
        IndeterminateModeChangeListener indeterminateModeChangeListener = this.indeterminateModeChangeListener;
        if (indeterminateModeChangeListener != null) {
            indeterminateModeChangeListener.onModeChange(z);
        }
        this.rightToLeft = true;
        this.startAngle = DEFAULT_START_ANGLE;
        Handler handler = this.indeterminateModeHandler;
        if (handler != null) {
            handler.removeCallbacks(this.indeterminateModeRunnable);
        }
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        Handler handler2 = new Handler();
        this.indeterminateModeHandler = handler2;
        if (this.indeterminateMode) {
            handler2.post(this.indeterminateModeRunnable);
        } else {
            setProgress(0.0f, true);
        }
    }

    public void setOnProgressChangedListener(ProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    public void setOnIndeterminateModeChangeListener(IndeterminateModeChangeListener indeterminateModeChangeListener) {
        this.indeterminateModeChangeListener = indeterminateModeChangeListener;
    }
}
