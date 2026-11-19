package com.xw.repo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import androidx.core.content.ContextCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.xw.repo.bubbleseekbar.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes5.dex */
public class BubbleSeekBar extends View {
    static final int NONE = -1;
    float dx;
    private boolean isAlwaysShowBubble;
    private boolean isAutoAdjustSectionMark;
    private boolean isFloatType;
    private boolean isHideBubble;
    private boolean isRtl;
    private boolean isSeekBySection;
    private boolean isSeekStepSection;
    private boolean isShowProgressInFloat;
    private boolean isShowSectionMark;
    private boolean isShowSectionText;
    private boolean isShowThumbText;
    private boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    private boolean isTouchToSeekAnimEnd;
    private long mAlwaysShowBubbleDelay;
    private long mAnimDuration;
    private float mBubbleCenterRawSolidX;
    private float mBubbleCenterRawSolidY;
    private float mBubbleCenterRawX;
    private int mBubbleColor;
    private int mBubbleRadius;
    private int mBubbleTextColor;
    private int mBubbleTextSize;
    private BubbleView mBubbleView;
    private float mDelta;
    private WindowManager.LayoutParams mLayoutParams;
    private float mLeft;
    private float mMax;
    private float mMin;
    private Paint mPaint;
    private int[] mPoint;
    private float mPreSecValue;
    private float mPreThumbCenterX;
    private float mProgress;
    private OnProgressChangedListener mProgressListener;
    private Rect mRectText;
    private float mRight;
    private int mSecondTrackColor;
    private int mSecondTrackSize;
    private int mSectionCount;
    private float mSectionOffset;
    private SparseArray<String> mSectionTextArray;
    private int mSectionTextColor;
    private int mSectionTextInterval;
    private int mSectionTextPosition;
    private int mSectionTextSize;
    private float mSectionValue;
    private int mTextSpace;
    private float mThumbCenterX;
    private int mThumbColor;
    private int mThumbRadius;
    private int mThumbRadiusOnDragging;
    private int mThumbTextColor;
    private int mThumbTextSize;
    private int mTrackColor;
    private float mTrackLength;
    private int mTrackSize;
    private WindowManager mWindowManager;
    private boolean triggerBubbleShowing;
    private boolean triggerSeekBySection;

    public interface CustomSectionTextArray {
        SparseArray<String> onCustomize(int i, SparseArray<String> sparseArray);
    }

    public interface OnProgressChangedListener {
        void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f);

        void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);

        void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z);
    }

    public static abstract class OnProgressChangedListenerAdapter implements OnProgressChangedListener {
        @Override // com.xw.repo.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float f) {
        }

        @Override // com.xw.repo.BubbleSeekBar.OnProgressChangedListener
        public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }

        @Override // com.xw.repo.BubbleSeekBar.OnProgressChangedListener
        public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float f, boolean z) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TextPosition {
        public static final int BELOW_SECTION_MARK = 2;
        public static final int BOTTOM_SIDES = 1;
        public static final int SIDES = 0;
    }

    public BubbleSeekBar(Context context) {
        this(context, null);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSectionTextPosition = -1;
        this.mSectionTextArray = new SparseArray<>();
        this.mPoint = new int[2];
        this.isTouchToSeekAnimEnd = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BubbleSeekBar, i, 0);
        this.mMin = typedArrayObtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_min, 0.0f);
        this.mMax = typedArrayObtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_max, 100.0f);
        this.mProgress = typedArrayObtainStyledAttributes.getFloat(R.styleable.BubbleSeekBar_bsb_progress, this.mMin);
        this.isFloatType = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_is_float_type, false);
        this.mTrackSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_track_size, BubbleUtils.dp2px(2));
        this.mSecondTrackSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_second_track_size, this.mTrackSize + BubbleUtils.dp2px(2));
        this.mThumbRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius, this.mSecondTrackSize + BubbleUtils.dp2px(2));
        this.mThumbRadiusOnDragging = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_radius_on_dragging, this.mSecondTrackSize * 2);
        this.mSectionCount = typedArrayObtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_count, 10);
        this.mTrackColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_track_color, ContextCompat.getColor(context, R.color.colorPrimary));
        this.mSecondTrackColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_second_track_color, ContextCompat.getColor(context, R.color.colorAccent));
        this.mThumbColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_thumb_color, this.mSecondTrackColor);
        this.isShowSectionText = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_section_text, false);
        this.mSectionTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_section_text_size, BubbleUtils.sp2px(14));
        this.mSectionTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_section_text_color, this.mTrackColor);
        this.isSeekStepSection = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_seek_step_section, false);
        this.isSeekBySection = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_seek_by_section, false);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_text_position, -1);
        if (integer == 0) {
            this.mSectionTextPosition = 0;
        } else if (integer == 1) {
            this.mSectionTextPosition = 1;
        } else if (integer == 2) {
            this.mSectionTextPosition = 2;
        } else {
            this.mSectionTextPosition = -1;
        }
        this.mSectionTextInterval = typedArrayObtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_section_text_interval, 1);
        this.isShowThumbText = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_thumb_text, false);
        this.mThumbTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_thumb_text_size, BubbleUtils.sp2px(14));
        this.mThumbTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_thumb_text_color, this.mSecondTrackColor);
        this.mBubbleColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_bubble_color, this.mSecondTrackColor);
        this.mBubbleTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.BubbleSeekBar_bsb_bubble_text_size, BubbleUtils.sp2px(14));
        this.mBubbleTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.BubbleSeekBar_bsb_bubble_text_color, -1);
        this.isShowSectionMark = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_section_mark, false);
        this.isAutoAdjustSectionMark = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_auto_adjust_section_mark, false);
        this.isShowProgressInFloat = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_show_progress_in_float, false);
        int integer2 = typedArrayObtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_anim_duration, -1);
        this.mAnimDuration = integer2 < 0 ? 200L : integer2;
        this.isTouchToSeek = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_touch_to_seek, false);
        this.isAlwaysShowBubble = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_always_show_bubble, false);
        int integer3 = typedArrayObtainStyledAttributes.getInteger(R.styleable.BubbleSeekBar_bsb_always_show_bubble_delay, 0);
        this.mAlwaysShowBubbleDelay = integer3 < 0 ? 0L : integer3;
        this.isHideBubble = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_hide_bubble, false);
        this.isRtl = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_bsb_rtl, false);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BubbleSeekBar_android_enabled, isEnabled()));
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mRectText = new Rect();
        this.mTextSpace = BubbleUtils.dp2px(2);
        initConfigByPriority();
        if (this.isHideBubble) {
            return;
        }
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        BubbleView bubbleView = new BubbleView(this, context);
        this.mBubbleView = bubbleView;
        bubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.gravity = 8388659;
        this.mLayoutParams.width = -2;
        this.mLayoutParams.height = -2;
        this.mLayoutParams.format = -3;
        this.mLayoutParams.flags = 524328;
        if (BubbleUtils.isMIUI() || Build.VERSION.SDK_INT >= 25) {
            this.mLayoutParams.type = 2;
        } else {
            this.mLayoutParams.type = 2005;
        }
        calculateRadiusOfBubble();
    }

    private void initConfigByPriority() {
        if (this.mMin == this.mMax) {
            this.mMin = 0.0f;
            this.mMax = 100.0f;
        }
        float f = this.mMin;
        float f2 = this.mMax;
        if (f > f2) {
            this.mMax = f;
            this.mMin = f2;
        }
        float f3 = this.mProgress;
        float f4 = this.mMin;
        if (f3 < f4) {
            this.mProgress = f4;
        }
        float f5 = this.mProgress;
        float f6 = this.mMax;
        if (f5 > f6) {
            this.mProgress = f6;
        }
        int i = this.mSecondTrackSize;
        int i2 = this.mTrackSize;
        if (i < i2) {
            this.mSecondTrackSize = i2 + BubbleUtils.dp2px(2);
        }
        int i3 = this.mThumbRadius;
        int i4 = this.mSecondTrackSize;
        if (i3 <= i4) {
            this.mThumbRadius = i4 + BubbleUtils.dp2px(2);
        }
        int i5 = this.mThumbRadiusOnDragging;
        int i6 = this.mSecondTrackSize;
        if (i5 <= i6) {
            this.mThumbRadiusOnDragging = i6 * 2;
        }
        if (this.mSectionCount <= 0) {
            this.mSectionCount = 10;
        }
        float f7 = this.mMax - this.mMin;
        this.mDelta = f7;
        float f8 = f7 / this.mSectionCount;
        this.mSectionValue = f8;
        if (f8 < 1.0f) {
            this.isFloatType = true;
        }
        if (this.isFloatType) {
            this.isShowProgressInFloat = true;
        }
        int i7 = this.mSectionTextPosition;
        if (i7 != -1) {
            this.isShowSectionText = true;
        }
        if (this.isShowSectionText) {
            if (i7 == -1) {
                this.mSectionTextPosition = 0;
            }
            if (this.mSectionTextPosition == 2) {
                this.isShowSectionMark = true;
            }
        }
        if (this.mSectionTextInterval < 1) {
            this.mSectionTextInterval = 1;
        }
        initSectionTextArray();
        if (this.isSeekStepSection) {
            this.isSeekBySection = false;
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isAutoAdjustSectionMark && !this.isShowSectionMark) {
            this.isAutoAdjustSectionMark = false;
        }
        if (this.isSeekBySection) {
            float f9 = this.mMin;
            this.mPreSecValue = f9;
            if (this.mProgress != f9) {
                this.mPreSecValue = this.mSectionValue;
            }
            this.isShowSectionMark = true;
            this.isAutoAdjustSectionMark = true;
        }
        if (this.isHideBubble) {
            this.isAlwaysShowBubble = false;
        }
        if (this.isAlwaysShowBubble) {
            setProgress(this.mProgress);
        }
        this.mThumbTextSize = (this.isFloatType || this.isSeekBySection || (this.isShowSectionText && this.mSectionTextPosition == 2)) ? this.mSectionTextSize : this.mThumbTextSize;
    }

    private void calculateRadiusOfBubble() {
        String strFloat2String;
        String strFloat2String2;
        this.mPaint.setTextSize(this.mBubbleTextSize);
        if (this.isShowProgressInFloat) {
            strFloat2String = float2String(this.isRtl ? this.mMax : this.mMin);
        } else if (this.isRtl) {
            strFloat2String = this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
        } else {
            strFloat2String = this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
        }
        this.mPaint.getTextBounds(strFloat2String, 0, strFloat2String.length(), this.mRectText);
        int iWidth = (this.mRectText.width() + (this.mTextSpace * 2)) >> 1;
        if (this.isShowProgressInFloat) {
            strFloat2String2 = float2String(this.isRtl ? this.mMin : this.mMax);
        } else if (this.isRtl) {
            strFloat2String2 = this.isFloatType ? float2String(this.mMin) : String.valueOf((int) this.mMin);
        } else {
            strFloat2String2 = this.isFloatType ? float2String(this.mMax) : String.valueOf((int) this.mMax);
        }
        this.mPaint.getTextBounds(strFloat2String2, 0, strFloat2String2.length(), this.mRectText);
        int iWidth2 = (this.mRectText.width() + (this.mTextSpace * 2)) >> 1;
        int iDp2px = BubbleUtils.dp2px(14);
        this.mBubbleRadius = iDp2px;
        this.mBubbleRadius = Math.max(iDp2px, Math.max(iWidth, iWidth2)) + this.mTextSpace;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0052 A[PHI: r5
      0x0052: PHI (r5v3 float) = (r5v2 float), (r5v2 float), (r5v2 float), (r5v7 float) binds: [B:26:0x004d, B:27:0x004f, B:19:0x0030, B:25:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x005d, please report this as an issue */
    private void initSectionTextArray() {
        String strFloat2String;
        int i = 0;
        boolean z = this.mSectionTextPosition == 2;
        boolean z2 = this.mSectionTextInterval > 1 && this.mSectionCount % 2 == 0;
        while (true) {
            int i2 = this.mSectionCount;
            if (i > i2) {
                return;
            }
            boolean z3 = this.isRtl;
            float f = z3 ? this.mMax - (this.mSectionValue * i) : this.mMin + (this.mSectionValue * i);
            if (z) {
                if (z2) {
                    if (i % this.mSectionTextInterval == 0) {
                        f = z3 ? this.mMax - (this.mSectionValue * i) : this.mMin + (this.mSectionValue * i);
                        SparseArray<String> sparseArray = this.mSectionTextArray;
                        if (this.isFloatType) {
                            strFloat2String = float2String(f);
                        } else {
                            strFloat2String = ((int) f) + "";
                        }
                        sparseArray.put(i, strFloat2String);
                    }
                } else {
                    SparseArray<String> sparseArray2 = this.mSectionTextArray;
                    if (this.isFloatType) {
                        strFloat2String = float2String(f);
                    } else {
                        strFloat2String = ((int) f) + "";
                    }
                    sparseArray2.put(i, strFloat2String);
                }
            } else if (i == 0 || i == i2) {
                SparseArray<String> sparseArray3 = this.mSectionTextArray;
                if (this.isFloatType) {
                    strFloat2String = float2String(f);
                } else {
                    strFloat2String = ((int) f) + "";
                }
                sparseArray3.put(i, strFloat2String);
            }
            i++;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int iMax = this.mThumbRadiusOnDragging * 2;
        if (this.isShowThumbText) {
            this.mPaint.setTextSize(this.mThumbTextSize);
            this.mPaint.getTextBounds("j", 0, 1, this.mRectText);
            iMax += this.mRectText.height();
        }
        if (this.isShowSectionText && this.mSectionTextPosition >= 1) {
            this.mPaint.setTextSize(this.mSectionTextSize);
            this.mPaint.getTextBounds("j", 0, 1, this.mRectText);
            iMax = Math.max(iMax, (this.mThumbRadiusOnDragging * 2) + this.mRectText.height());
        }
        setMeasuredDimension(resolveSize(BubbleUtils.dp2px(Opcodes.GETFIELD), i), iMax + (this.mTextSpace * 2));
        this.mLeft = getPaddingLeft() + this.mThumbRadiusOnDragging;
        this.mRight = (getMeasuredWidth() - getPaddingRight()) - this.mThumbRadiusOnDragging;
        if (this.isShowSectionText) {
            this.mPaint.setTextSize(this.mSectionTextSize);
            int i3 = this.mSectionTextPosition;
            if (i3 == 0) {
                String str = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
                this.mLeft += this.mRectText.width() + this.mTextSpace;
                String str2 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str2, 0, str2.length(), this.mRectText);
                this.mRight -= this.mRectText.width() + this.mTextSpace;
            } else if (i3 >= 1) {
                String str3 = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str3, 0, str3.length(), this.mRectText);
                this.mLeft = getPaddingLeft() + Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f) + this.mTextSpace;
                String str4 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str4, 0, str4.length(), this.mRectText);
                this.mRight = ((getMeasuredWidth() - getPaddingRight()) - Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f)) - this.mTextSpace;
            }
        } else if (this.isShowThumbText && this.mSectionTextPosition == -1) {
            this.mPaint.setTextSize(this.mThumbTextSize);
            String str5 = this.mSectionTextArray.get(0);
            this.mPaint.getTextBounds(str5, 0, str5.length(), this.mRectText);
            this.mLeft = getPaddingLeft() + Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f) + this.mTextSpace;
            String str6 = this.mSectionTextArray.get(this.mSectionCount);
            this.mPaint.getTextBounds(str6, 0, str6.length(), this.mRectText);
            this.mRight = ((getMeasuredWidth() - getPaddingRight()) - Math.max(this.mThumbRadiusOnDragging, this.mRectText.width() / 2.0f)) - this.mTextSpace;
        }
        float f = this.mRight - this.mLeft;
        this.mTrackLength = f;
        this.mSectionOffset = (f * 1.0f) / this.mSectionCount;
        if (this.isHideBubble) {
            return;
        }
        this.mBubbleView.measure(i, i2);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.isHideBubble) {
            return;
        }
        locatePositionInWindow();
    }

    private void locatePositionInWindow() {
        Window window;
        getLocationInWindow(this.mPoint);
        Object parent = getParent();
        if (parent instanceof View) {
            View view = (View) parent;
            if (view.getMeasuredWidth() > 0) {
                int[] iArr = this.mPoint;
                iArr[0] = iArr[0] % view.getMeasuredWidth();
            }
        }
        if (this.isRtl) {
            this.mBubbleCenterRawSolidX = (this.mPoint[0] + this.mRight) - (this.mBubbleView.getMeasuredWidth() / 2.0f);
        } else {
            this.mBubbleCenterRawSolidX = (this.mPoint[0] + this.mLeft) - (this.mBubbleView.getMeasuredWidth() / 2.0f);
        }
        this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        float measuredHeight = this.mPoint[1] - this.mBubbleView.getMeasuredHeight();
        this.mBubbleCenterRawSolidY = measuredHeight;
        this.mBubbleCenterRawSolidY = measuredHeight - BubbleUtils.dp2px(24);
        if (BubbleUtils.isMIUI()) {
            this.mBubbleCenterRawSolidY -= BubbleUtils.dp2px(4);
        }
        Context context = getContext();
        if (!(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || (window.getAttributes().flags & 1024) == 0) {
            return;
        }
        Resources system = Resources.getSystem();
        this.mBubbleCenterRawSolidY += system.getDimensionPixelSize(system.getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* JADX WARN: Code duplicated, block: B:86:0x025d  */
    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float fAbs;
        super.onDraw(canvas);
        float paddingLeft = getPaddingLeft();
        float measuredWidth = getMeasuredWidth() - getPaddingRight();
        float paddingTop = getPaddingTop() + this.mThumbRadiusOnDragging;
        if (this.isShowSectionText) {
            this.mPaint.setColor(this.mSectionTextColor);
            this.mPaint.setTextSize(this.mSectionTextSize);
            this.mPaint.getTextBounds("0123456789", 0, 10, this.mRectText);
            int i = this.mSectionTextPosition;
            if (i == 0) {
                float fHeight = (this.mRectText.height() / 2.0f) + paddingTop;
                String str = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str, 0, str.length(), this.mRectText);
                canvas.drawText(str, (this.mRectText.width() / 2.0f) + paddingLeft, fHeight, this.mPaint);
                paddingLeft += this.mRectText.width() + this.mTextSpace;
                String str2 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str2, 0, str2.length(), this.mRectText);
                canvas.drawText(str2, measuredWidth - ((this.mRectText.width() + 0.5f) / 2.0f), fHeight, this.mPaint);
                measuredWidth -= this.mRectText.width() + this.mTextSpace;
            } else if (i >= 1) {
                float f = this.mThumbRadiusOnDragging + paddingTop + this.mTextSpace;
                String str3 = this.mSectionTextArray.get(0);
                this.mPaint.getTextBounds(str3, 0, str3.length(), this.mRectText);
                float fHeight2 = f + this.mRectText.height();
                float f2 = this.mLeft;
                if (this.mSectionTextPosition == 1) {
                    canvas.drawText(str3, f2, fHeight2, this.mPaint);
                }
                String str4 = this.mSectionTextArray.get(this.mSectionCount);
                this.mPaint.getTextBounds(str4, 0, str4.length(), this.mRectText);
                float f3 = this.mRight;
                if (this.mSectionTextPosition == 1) {
                    canvas.drawText(str4, f3, fHeight2, this.mPaint);
                }
                paddingLeft = f2;
                measuredWidth = f3;
            }
        } else if (this.isShowThumbText && this.mSectionTextPosition == -1) {
            paddingLeft = this.mLeft;
            measuredWidth = this.mRight;
        }
        boolean z = this.isShowSectionText;
        if ((!z && !this.isShowThumbText) || this.mSectionTextPosition == 0) {
            int i2 = this.mThumbRadiusOnDragging;
            paddingLeft += i2;
            measuredWidth -= i2;
        }
        float f4 = paddingLeft;
        float f5 = measuredWidth;
        boolean z2 = z && this.mSectionTextPosition == 2;
        if (z2 || this.isShowSectionMark) {
            this.mPaint.setTextSize(this.mSectionTextSize);
            this.mPaint.getTextBounds("0123456789", 0, 10, this.mRectText);
            float fHeight3 = this.mRectText.height() + paddingTop;
            int i3 = this.mThumbRadiusOnDragging;
            float f6 = fHeight3 + i3 + this.mTextSpace;
            float fDp2px = (i3 - BubbleUtils.dp2px(2)) / 2.0f;
            if (this.isRtl) {
                fAbs = this.mRight - ((this.mTrackLength / this.mDelta) * Math.abs(this.mProgress - this.mMin));
            } else {
                fAbs = this.mLeft + ((this.mTrackLength / this.mDelta) * Math.abs(this.mProgress - this.mMin));
            }
            for (int i4 = 0; i4 <= this.mSectionCount; i4++) {
                float f7 = (i4 * this.mSectionOffset) + f4;
                if (this.isRtl) {
                    this.mPaint.setColor(f7 <= fAbs ? this.mTrackColor : this.mSecondTrackColor);
                } else {
                    this.mPaint.setColor(f7 <= fAbs ? this.mSecondTrackColor : this.mTrackColor);
                }
                canvas.drawCircle(f7, paddingTop, fDp2px, this.mPaint);
                if (z2) {
                    this.mPaint.setColor(this.mSectionTextColor);
                    if (this.mSectionTextArray.get(i4, null) != null) {
                        canvas.drawText(this.mSectionTextArray.get(i4), f7, f6, this.mPaint);
                    }
                }
            }
        }
        boolean z3 = this.isThumbOnDragging;
        if (!z3 || this.isAlwaysShowBubble) {
            if (this.isRtl) {
                this.mThumbCenterX = f5 - ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin));
            } else {
                this.mThumbCenterX = ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin)) + f4;
            }
        }
        if (this.isShowThumbText && !z3 && this.isTouchToSeekAnimEnd) {
            this.mPaint.setColor(this.mThumbTextColor);
            this.mPaint.setTextSize(this.mThumbTextSize);
            this.mPaint.getTextBounds("0123456789", 0, 10, this.mRectText);
            float fHeight4 = this.mRectText.height() + paddingTop + this.mThumbRadiusOnDragging + this.mTextSpace;
            if (this.isFloatType) {
                canvas.drawText(String.valueOf(getProgressFloat()), this.mThumbCenterX, fHeight4, this.mPaint);
            } else {
                if (this.isShowProgressInFloat && this.mSectionTextPosition == 1) {
                    float f8 = this.mProgress;
                    if (f8 != this.mMin && f8 != this.mMax) {
                        canvas.drawText(String.valueOf(getProgressFloat()), this.mThumbCenterX, fHeight4, this.mPaint);
                    }
                }
                canvas.drawText(String.valueOf(getProgress()), this.mThumbCenterX, fHeight4, this.mPaint);
            }
        }
        this.mPaint.setColor(this.mSecondTrackColor);
        this.mPaint.setStrokeWidth(this.mSecondTrackSize);
        if (this.isRtl) {
            canvas.drawLine(f5, paddingTop, this.mThumbCenterX, paddingTop, this.mPaint);
        } else {
            canvas.drawLine(f4, paddingTop, this.mThumbCenterX, paddingTop, this.mPaint);
        }
        this.mPaint.setColor(this.mTrackColor);
        this.mPaint.setStrokeWidth(this.mTrackSize);
        if (this.isRtl) {
            canvas.drawLine(this.mThumbCenterX, paddingTop, f4, paddingTop, this.mPaint);
        } else {
            canvas.drawLine(this.mThumbCenterX, paddingTop, f5, paddingTop, this.mPaint);
        }
        this.mPaint.setColor(this.mThumbColor);
        canvas.drawCircle(this.mThumbCenterX, paddingTop, this.isThumbOnDragging ? this.mThumbRadiusOnDragging : this.mThumbRadius, this.mPaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.1
            @Override // java.lang.Runnable
            public void run() {
                BubbleSeekBar.this.requestLayout();
            }
        });
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        if (this.isHideBubble || !this.isAlwaysShowBubble) {
            return;
        }
        if (i != 0) {
            hideBubble();
        } else if (this.triggerBubbleShowing) {
            showBubble();
        }
        super.onVisibilityChanged(view, i);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        hideBubble();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:38:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:41:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:42:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:46:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:50:0x00db  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:55:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:58:0x0103  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        long j;
        OnProgressChangedListener onProgressChangedListener;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            performClick();
            getParent().requestDisallowInterceptTouchEvent(true);
            boolean zIsThumbTouched = isThumbTouched(motionEvent);
            this.isThumbOnDragging = zIsThumbTouched;
            if (zIsThumbTouched) {
                if (this.isSeekBySection && !this.triggerSeekBySection) {
                    this.triggerSeekBySection = true;
                }
                if (this.isAlwaysShowBubble && !this.triggerBubbleShowing) {
                    this.triggerBubbleShowing = true;
                }
                if (!this.isHideBubble) {
                    showBubble();
                }
                invalidate();
            } else if (this.isTouchToSeek && isTrackTouched(motionEvent)) {
                this.isThumbOnDragging = true;
                if (this.isSeekBySection && !this.triggerSeekBySection) {
                    this.triggerSeekBySection = true;
                }
                if (this.isAlwaysShowBubble) {
                    hideBubble();
                    this.triggerBubbleShowing = true;
                }
                if (this.isSeekStepSection) {
                    float fCalThumbCxWhenSeekStepSection = calThumbCxWhenSeekStepSection(motionEvent.getX());
                    this.mPreThumbCenterX = fCalThumbCxWhenSeekStepSection;
                    this.mThumbCenterX = fCalThumbCxWhenSeekStepSection;
                } else {
                    float x = motionEvent.getX();
                    this.mThumbCenterX = x;
                    float f = this.mLeft;
                    if (x < f) {
                        this.mThumbCenterX = f;
                    }
                    float f2 = this.mThumbCenterX;
                    float f3 = this.mRight;
                    if (f2 > f3) {
                        this.mThumbCenterX = f3;
                    }
                }
                this.mProgress = calculateProgress();
                if (!this.isHideBubble) {
                    this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
                    showBubble();
                }
                invalidate();
            }
            this.dx = this.mThumbCenterX - motionEvent.getX();
        } else if (actionMasked == 1) {
            getParent().requestDisallowInterceptTouchEvent(false);
            if (this.isAutoAdjustSectionMark) {
                if (this.isTouchToSeek) {
                    postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.2
                        @Override // java.lang.Runnable
                        public void run() {
                            BubbleSeekBar.this.isTouchToSeekAnimEnd = false;
                            BubbleSeekBar.this.autoAdjustSection();
                        }
                    }, this.mAnimDuration);
                } else {
                    autoAdjustSection();
                }
            } else if (!this.isThumbOnDragging || this.isTouchToSeek) {
                if (this.isHideBubble) {
                    ViewPropertyAnimator duration = animate().setDuration(this.mAnimDuration);
                    if (this.isThumbOnDragging && this.isTouchToSeek) {
                        j = 300;
                    } else {
                        j = 0;
                    }
                    duration.setStartDelay(j).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            BubbleSeekBar.this.isThumbOnDragging = false;
                            BubbleSeekBar.this.invalidate();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                            BubbleSeekBar.this.isThumbOnDragging = false;
                            BubbleSeekBar.this.invalidate();
                        }
                    }).start();
                } else {
                    postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.4
                        @Override // java.lang.Runnable
                        public void run() {
                            BubbleSeekBar.this.mBubbleView.animate().alpha(BubbleSeekBar.this.isAlwaysShowBubble ? 1.0f : 0.0f).setDuration(BubbleSeekBar.this.mAnimDuration).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.4.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                        BubbleSeekBar.this.hideBubble();
                                    }
                                    BubbleSeekBar.this.isThumbOnDragging = false;
                                    BubbleSeekBar.this.invalidate();
                                }

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationCancel(Animator animator) {
                                    if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                        BubbleSeekBar.this.hideBubble();
                                    }
                                    BubbleSeekBar.this.isThumbOnDragging = false;
                                    BubbleSeekBar.this.invalidate();
                                }
                            }).start();
                        }
                    }, this.mAnimDuration);
                }
            }
            onProgressChangedListener = this.mProgressListener;
            if (onProgressChangedListener != null) {
                onProgressChangedListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                this.mProgressListener.getProgressOnActionUp(this, getProgress(), getProgressFloat());
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                getParent().requestDisallowInterceptTouchEvent(false);
                if (this.isAutoAdjustSectionMark) {
                    if (this.isTouchToSeek) {
                        postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.2
                            @Override // java.lang.Runnable
                            public void run() {
                                BubbleSeekBar.this.isTouchToSeekAnimEnd = false;
                                BubbleSeekBar.this.autoAdjustSection();
                            }
                        }, this.mAnimDuration);
                    } else {
                        autoAdjustSection();
                    }
                } else if (!this.isThumbOnDragging) {
                    if (this.isHideBubble) {
                        ViewPropertyAnimator duration2 = animate().setDuration(this.mAnimDuration);
                        if (this.isThumbOnDragging) {
                            j = 0;
                        } else {
                            j = 0;
                        }
                        duration2.setStartDelay(j).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.3
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animator) {
                                BubbleSeekBar.this.isThumbOnDragging = false;
                                BubbleSeekBar.this.invalidate();
                            }

                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationCancel(Animator animator) {
                                BubbleSeekBar.this.isThumbOnDragging = false;
                                BubbleSeekBar.this.invalidate();
                            }
                        }).start();
                    } else {
                        postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.4
                            @Override // java.lang.Runnable
                            public void run() {
                                BubbleSeekBar.this.mBubbleView.animate().alpha(BubbleSeekBar.this.isAlwaysShowBubble ? 1.0f : 0.0f).setDuration(BubbleSeekBar.this.mAnimDuration).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.4.1
                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public void onAnimationEnd(Animator animator) {
                                        if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                            BubbleSeekBar.this.hideBubble();
                                        }
                                        BubbleSeekBar.this.isThumbOnDragging = false;
                                        BubbleSeekBar.this.invalidate();
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public void onAnimationCancel(Animator animator) {
                                        if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                            BubbleSeekBar.this.hideBubble();
                                        }
                                        BubbleSeekBar.this.isThumbOnDragging = false;
                                        BubbleSeekBar.this.invalidate();
                                    }
                                }).start();
                            }
                        }, this.mAnimDuration);
                    }
                } else if (this.isHideBubble) {
                    ViewPropertyAnimator duration3 = animate().setDuration(this.mAnimDuration);
                    if (this.isThumbOnDragging) {
                        j = 0;
                    } else {
                        j = 0;
                    }
                    duration3.setStartDelay(j).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.3
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            BubbleSeekBar.this.isThumbOnDragging = false;
                            BubbleSeekBar.this.invalidate();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                            BubbleSeekBar.this.isThumbOnDragging = false;
                            BubbleSeekBar.this.invalidate();
                        }
                    }).start();
                } else {
                    postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.4
                        @Override // java.lang.Runnable
                        public void run() {
                            BubbleSeekBar.this.mBubbleView.animate().alpha(BubbleSeekBar.this.isAlwaysShowBubble ? 1.0f : 0.0f).setDuration(BubbleSeekBar.this.mAnimDuration).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.4.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator) {
                                    if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                        BubbleSeekBar.this.hideBubble();
                                    }
                                    BubbleSeekBar.this.isThumbOnDragging = false;
                                    BubbleSeekBar.this.invalidate();
                                }

                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationCancel(Animator animator) {
                                    if (!BubbleSeekBar.this.isAlwaysShowBubble) {
                                        BubbleSeekBar.this.hideBubble();
                                    }
                                    BubbleSeekBar.this.isThumbOnDragging = false;
                                    BubbleSeekBar.this.invalidate();
                                }
                            }).start();
                        }
                    }, this.mAnimDuration);
                }
                onProgressChangedListener = this.mProgressListener;
                if (onProgressChangedListener != null) {
                    onProgressChangedListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                    this.mProgressListener.getProgressOnActionUp(this, getProgress(), getProgressFloat());
                }
            }
        } else if (this.isThumbOnDragging) {
            if (this.isSeekStepSection) {
                float fCalThumbCxWhenSeekStepSection2 = calThumbCxWhenSeekStepSection(motionEvent.getX());
                if (fCalThumbCxWhenSeekStepSection2 != this.mPreThumbCenterX) {
                    this.mPreThumbCenterX = fCalThumbCxWhenSeekStepSection2;
                    this.mThumbCenterX = fCalThumbCxWhenSeekStepSection2;
                }
            } else {
                float x2 = motionEvent.getX() + this.dx;
                this.mThumbCenterX = x2;
                float f4 = this.mLeft;
                if (x2 < f4) {
                    this.mThumbCenterX = f4;
                }
                float f5 = this.mThumbCenterX;
                float f6 = this.mRight;
                if (f5 > f6) {
                    this.mThumbCenterX = f6;
                }
            }
            this.mProgress = calculateProgress();
            if (!this.isHideBubble && this.mBubbleView.getParent() != null) {
                float fCalculateCenterRawXofBubbleView = calculateCenterRawXofBubbleView();
                this.mBubbleCenterRawX = fCalculateCenterRawXofBubbleView;
                this.mLayoutParams.x = (int) (fCalculateCenterRawXofBubbleView + 0.5f);
                this.mWindowManager.updateViewLayout(this.mBubbleView, this.mLayoutParams);
                this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
            } else {
                processProgress();
            }
            invalidate();
            OnProgressChangedListener onProgressChangedListener2 = this.mProgressListener;
            if (onProgressChangedListener2 != null) {
                onProgressChangedListener2.onProgressChanged(this, getProgress(), getProgressFloat(), true);
            }
        }
        return this.isThumbOnDragging || this.isTouchToSeek || super.onTouchEvent(motionEvent);
    }

    private boolean isThumbTouched(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f = (this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin);
        float f2 = this.isRtl ? this.mRight - f : this.mLeft + f;
        float measuredHeight = getMeasuredHeight() / 2.0f;
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (this.mLeft + ((float) BubbleUtils.dp2px(8))) * (this.mLeft + ((float) BubbleUtils.dp2px(8)));
    }

    private boolean isTrackTouched(MotionEvent motionEvent) {
        return isEnabled() && motionEvent.getX() >= ((float) getPaddingLeft()) && motionEvent.getX() <= ((float) (getMeasuredWidth() - getPaddingRight())) && motionEvent.getY() >= ((float) getPaddingTop()) && motionEvent.getY() <= ((float) (getMeasuredHeight() - getPaddingBottom()));
    }

    private float calThumbCxWhenSeekStepSection(float f) {
        float f2 = this.mLeft;
        if (f <= f2) {
            return f2;
        }
        float f3 = this.mRight;
        if (f >= f3) {
            return f3;
        }
        float f4 = 0.0f;
        int i = 0;
        while (i <= this.mSectionCount) {
            float f5 = this.mSectionOffset;
            f4 = (i * f5) + this.mLeft;
            if (f4 <= f && f - f4 <= f5) {
                break;
            }
            i++;
        }
        float f6 = f - f4;
        float f7 = this.mSectionOffset;
        return f6 <= f7 / 2.0f ? f4 : ((i + 1) * f7) + this.mLeft;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoAdjustSection() {
        ValueAnimator valueAnimatorOfFloat;
        int i = 0;
        float f = 0.0f;
        while (i <= this.mSectionCount) {
            float f2 = this.mSectionOffset;
            f = (i * f2) + this.mLeft;
            float f3 = this.mThumbCenterX;
            if (f <= f3 && f3 - f <= f2) {
                break;
            } else {
                i++;
            }
        }
        boolean z = BigDecimal.valueOf((double) this.mThumbCenterX).setScale(1, 4).floatValue() == f;
        AnimatorSet animatorSet = new AnimatorSet();
        if (z) {
            valueAnimatorOfFloat = null;
        } else {
            float f4 = this.mThumbCenterX;
            float f5 = f4 - f;
            float f6 = this.mSectionOffset;
            if (f5 <= f6 / 2.0f) {
                valueAnimatorOfFloat = ValueAnimator.ofFloat(f4, f);
            } else {
                valueAnimatorOfFloat = ValueAnimator.ofFloat(f4, ((i + 1) * f6) + this.mLeft);
            }
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xw.repo.BubbleSeekBar.5
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BubbleSeekBar.this.mThumbCenterX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                    bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                    if (BubbleSeekBar.this.isHideBubble) {
                        BubbleSeekBar.this.processProgress();
                    } else {
                        BubbleSeekBar bubbleSeekBar2 = BubbleSeekBar.this;
                        bubbleSeekBar2.mBubbleCenterRawX = bubbleSeekBar2.calculateCenterRawXofBubbleView();
                        BubbleSeekBar.this.mLayoutParams.x = (int) (BubbleSeekBar.this.mBubbleCenterRawX + 0.5f);
                        if (BubbleSeekBar.this.mBubbleView.getParent() != null) {
                            BubbleSeekBar.this.mWindowManager.updateViewLayout(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
                        }
                        BubbleSeekBar.this.mBubbleView.setProgressText(BubbleSeekBar.this.isShowProgressInFloat ? String.valueOf(BubbleSeekBar.this.getProgressFloat()) : String.valueOf(BubbleSeekBar.this.getProgress()));
                    }
                    BubbleSeekBar.this.invalidate();
                    if (BubbleSeekBar.this.mProgressListener != null) {
                        OnProgressChangedListener onProgressChangedListener = BubbleSeekBar.this.mProgressListener;
                        BubbleSeekBar bubbleSeekBar3 = BubbleSeekBar.this;
                        onProgressChangedListener.onProgressChanged(bubbleSeekBar3, bubbleSeekBar3.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                    }
                }
            });
        }
        if (!this.isHideBubble) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mBubbleView, (Property<BubbleView, Float>) View.ALPHA, this.isAlwaysShowBubble ? 1.0f : 0.0f);
            if (z) {
                animatorSet.setDuration(this.mAnimDuration).play(objectAnimatorOfFloat);
            } else {
                animatorSet.setDuration(this.mAnimDuration).playTogether(valueAnimatorOfFloat, objectAnimatorOfFloat);
            }
        } else if (!z) {
            animatorSet.setDuration(this.mAnimDuration).playTogether(valueAnimatorOfFloat);
        }
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                BubbleSeekBar.this.isThumbOnDragging = false;
                BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
                if (BubbleSeekBar.this.mProgressListener != null) {
                    OnProgressChangedListener onProgressChangedListener = BubbleSeekBar.this.mProgressListener;
                    BubbleSeekBar bubbleSeekBar2 = BubbleSeekBar.this;
                    onProgressChangedListener.getProgressOnFinally(bubbleSeekBar2, bubbleSeekBar2.getProgress(), BubbleSeekBar.this.getProgressFloat(), true);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (!BubbleSeekBar.this.isHideBubble && !BubbleSeekBar.this.isAlwaysShowBubble) {
                    BubbleSeekBar.this.hideBubble();
                }
                BubbleSeekBar bubbleSeekBar = BubbleSeekBar.this;
                bubbleSeekBar.mProgress = bubbleSeekBar.calculateProgress();
                BubbleSeekBar.this.isThumbOnDragging = false;
                BubbleSeekBar.this.isTouchToSeekAnimEnd = true;
                BubbleSeekBar.this.invalidate();
            }
        });
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBubble() {
        BubbleView bubbleView = this.mBubbleView;
        if (bubbleView == null || bubbleView.getParent() != null) {
            return;
        }
        this.mLayoutParams.x = (int) (this.mBubbleCenterRawX + 0.5f);
        this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
        this.mBubbleView.setAlpha(0.0f);
        this.mBubbleView.setVisibility(0);
        this.mBubbleView.animate().alpha(1.0f).setDuration(this.isTouchToSeek ? 0L : this.mAnimDuration).setListener(new AnimatorListenerAdapter() { // from class: com.xw.repo.BubbleSeekBar.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BubbleSeekBar.this.mWindowManager.addView(BubbleSeekBar.this.mBubbleView, BubbleSeekBar.this.mLayoutParams);
            }
        }).start();
        this.mBubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideBubble() {
        BubbleView bubbleView = this.mBubbleView;
        if (bubbleView == null) {
            return;
        }
        bubbleView.setVisibility(8);
        if (this.mBubbleView.getParent() != null) {
            this.mWindowManager.removeViewImmediate(this.mBubbleView);
        }
    }

    private String float2String(float f) {
        return String.valueOf(formatFloat(f));
    }

    private float formatFloat(float f) {
        return BigDecimal.valueOf(f).setScale(1, 4).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateCenterRawXofBubbleView() {
        if (this.isRtl) {
            return this.mBubbleCenterRawSolidX - ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta);
        }
        return this.mBubbleCenterRawSolidX + ((this.mTrackLength * (this.mProgress - this.mMin)) / this.mDelta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateProgress() {
        float f;
        float f2;
        if (this.isRtl) {
            f = ((this.mRight - this.mThumbCenterX) * this.mDelta) / this.mTrackLength;
            f2 = this.mMin;
        } else {
            f = ((this.mThumbCenterX - this.mLeft) * this.mDelta) / this.mTrackLength;
            f2 = this.mMin;
        }
        return f + f2;
    }

    public void correctOffsetWhenContainerOnScrolling() {
        if (this.isHideBubble) {
            return;
        }
        locatePositionInWindow();
        if (this.mBubbleView.getParent() != null) {
            if (this.isAlwaysShowBubble) {
                this.mLayoutParams.y = (int) (this.mBubbleCenterRawSolidY + 0.5f);
                this.mWindowManager.updateViewLayout(this.mBubbleView, this.mLayoutParams);
                return;
            }
            postInvalidate();
        }
    }

    public float getMin() {
        return this.mMin;
    }

    public float getMax() {
        return this.mMax;
    }

    public void setProgress(float f) {
        this.mProgress = f;
        OnProgressChangedListener onProgressChangedListener = this.mProgressListener;
        if (onProgressChangedListener != null) {
            onProgressChangedListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            this.mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        if (!this.isHideBubble) {
            this.mBubbleCenterRawX = calculateCenterRawXofBubbleView();
        }
        if (this.isAlwaysShowBubble) {
            hideBubble();
            postDelayed(new Runnable() { // from class: com.xw.repo.BubbleSeekBar.8
                @Override // java.lang.Runnable
                public void run() {
                    BubbleSeekBar.this.showBubble();
                    BubbleSeekBar.this.triggerBubbleShowing = true;
                }
            }, this.mAlwaysShowBubbleDelay);
        }
        if (this.isSeekBySection) {
            this.triggerSeekBySection = false;
        }
        postInvalidate();
    }

    public int getProgress() {
        return Math.round(processProgress());
    }

    public float getProgressFloat() {
        return formatFloat(processProgress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float processProgress() {
        float f = this.mProgress;
        if (!this.isSeekBySection || !this.triggerSeekBySection) {
            return f;
        }
        float f2 = this.mSectionValue / 2.0f;
        if (this.isTouchToSeek) {
            if (f == this.mMin || f == this.mMax) {
                return f;
            }
            for (int i = 0; i <= this.mSectionCount; i++) {
                float f3 = this.mSectionValue;
                float f4 = i * f3;
                if (f4 < f && f4 + f3 >= f) {
                    return f2 + f4 > f ? f4 : f4 + f3;
                }
            }
        }
        float f5 = this.mPreSecValue;
        if (f >= f5) {
            if (f < f2 + f5) {
                return f5;
            }
            float f6 = f5 + this.mSectionValue;
            this.mPreSecValue = f6;
            return f6;
        }
        if (f >= f5 - f2) {
            return f5;
        }
        float f7 = f5 - this.mSectionValue;
        this.mPreSecValue = f7;
        return f7;
    }

    public OnProgressChangedListener getOnProgressChangedListener() {
        return this.mProgressListener;
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mProgressListener = onProgressChangedListener;
    }

    public void setTrackColor(int i) {
        if (this.mTrackColor != i) {
            this.mTrackColor = i;
            invalidate();
        }
    }

    public void setSecondTrackColor(int i) {
        if (this.mSecondTrackColor != i) {
            this.mSecondTrackColor = i;
            invalidate();
        }
    }

    public void setThumbColor(int i) {
        if (this.mThumbColor != i) {
            this.mThumbColor = i;
            invalidate();
        }
    }

    public void setBubbleColor(int i) {
        if (this.mBubbleColor != i) {
            this.mBubbleColor = i;
            BubbleView bubbleView = this.mBubbleView;
            if (bubbleView != null) {
                bubbleView.invalidate();
            }
        }
    }

    public void setCustomSectionTextArray(CustomSectionTextArray customSectionTextArray) {
        this.mSectionTextArray = customSectionTextArray.onCustomize(this.mSectionCount, this.mSectionTextArray);
        for (int i = 0; i <= this.mSectionCount; i++) {
            if (this.mSectionTextArray.get(i) == null) {
                this.mSectionTextArray.put(i, "");
            }
        }
        this.isShowThumbText = false;
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save_instance", super.onSaveInstanceState());
        bundle.putFloat("progress", this.mProgress);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgress = bundle.getFloat("progress");
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
            BubbleView bubbleView = this.mBubbleView;
            if (bubbleView != null) {
                bubbleView.setProgressText(this.isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress()));
            }
            setProgress(this.mProgress);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private class BubbleView extends View {
        private Paint mBubblePaint;
        private Path mBubblePath;
        private RectF mBubbleRectF;
        private String mProgressText;
        private Rect mRect;

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context) {
            this(bubbleSeekBar, context, null);
        }

        BubbleView(BubbleSeekBar bubbleSeekBar, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        BubbleView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mProgressText = "";
            Paint paint = new Paint();
            this.mBubblePaint = paint;
            paint.setAntiAlias(true);
            this.mBubblePaint.setTextAlign(Paint.Align.CENTER);
            this.mBubblePath = new Path();
            this.mBubbleRectF = new RectF();
            this.mRect = new Rect();
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            setMeasuredDimension(BubbleSeekBar.this.mBubbleRadius * 3, BubbleSeekBar.this.mBubbleRadius * 3);
            this.mBubbleRectF.set((getMeasuredWidth() / 2.0f) - BubbleSeekBar.this.mBubbleRadius, 0.0f, (getMeasuredWidth() / 2.0f) + BubbleSeekBar.this.mBubbleRadius, BubbleSeekBar.this.mBubbleRadius * 2);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.mBubblePath.reset();
            float measuredWidth = getMeasuredWidth() / 2.0f;
            float measuredHeight = getMeasuredHeight() - (BubbleSeekBar.this.mBubbleRadius / 3.0f);
            this.mBubblePath.moveTo(measuredWidth, measuredHeight);
            float measuredWidth2 = (float) (((double) (getMeasuredWidth() / 2.0f)) - ((Math.sqrt(3.0d) / 2.0d) * ((double) BubbleSeekBar.this.mBubbleRadius)));
            float f = BubbleSeekBar.this.mBubbleRadius * 1.5f;
            this.mBubblePath.quadTo(measuredWidth2 - BubbleUtils.dp2px(2), f - BubbleUtils.dp2px(2), measuredWidth2, f);
            this.mBubblePath.arcTo(this.mBubbleRectF, 150.0f, 240.0f);
            this.mBubblePath.quadTo(((float) (((double) (getMeasuredWidth() / 2.0f)) + ((Math.sqrt(3.0d) / 2.0d) * ((double) BubbleSeekBar.this.mBubbleRadius)))) + BubbleUtils.dp2px(2), f - BubbleUtils.dp2px(2), measuredWidth, measuredHeight);
            this.mBubblePath.close();
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleColor);
            canvas.drawPath(this.mBubblePath, this.mBubblePaint);
            this.mBubblePaint.setTextSize(BubbleSeekBar.this.mBubbleTextSize);
            this.mBubblePaint.setColor(BubbleSeekBar.this.mBubbleTextColor);
            Paint paint = this.mBubblePaint;
            String str = this.mProgressText;
            paint.getTextBounds(str, 0, str.length(), this.mRect);
            Paint.FontMetrics fontMetrics = this.mBubblePaint.getFontMetrics();
            canvas.drawText(this.mProgressText, getMeasuredWidth() / 2.0f, (BubbleSeekBar.this.mBubbleRadius + ((fontMetrics.descent - fontMetrics.ascent) / 2.0f)) - fontMetrics.descent, this.mBubblePaint);
        }

        void setProgressText(String str) {
            if (str == null || this.mProgressText.equals(str)) {
                return;
            }
            this.mProgressText = str;
            invalidate();
        }
    }
}
