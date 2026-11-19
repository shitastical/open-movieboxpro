package com.dueeeke.videoplayer.widget.SrtView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.dueeeke.videocontroller.R;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LrcView extends View {
    private static final long ADJUST_DURATION = 100;
    private static final long TIMELINE_KEEP_TIME = 4000;
    private Runnable hideTimelineRunnable;
    private boolean isFling;
    private boolean isShowTimeline;
    private boolean isTouching;
    private long mAnimationDuration;
    private ValueAnimator mAnimator;
    private int mCurrentLine;
    private int mCurrentTextColor;
    private String mDefaultLabel;
    private float mDividerHeight;
    private int mDrawableWidth;
    private Object mFlag;
    private GestureDetector mGestureDetector;
    private List<LrcEntry> mLrcEntryList;
    private float mLrcPadding;
    private TextPaint mLrcPaint;
    private int mNormalTextColor;
    private float mOffset;
    private OnPlayClickListener mOnPlayClickListener;
    private Drawable mPlayDrawable;
    private Scroller mScroller;
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener;
    private int mTextGravity;
    private Paint.FontMetrics mTimeFontMetrics;
    private TextPaint mTimePaint;
    private int mTimeTextColor;
    private int mTimeTextWidth;
    private int mTimelineColor;
    private int mTimelineTextColor;

    public interface OnPlayClickListener {
        boolean onPlayClick(long j);
    }

    static /* synthetic */ float access$1316(LrcView lrcView, float f) {
        float f2 = lrcView.mOffset + f;
        lrcView.mOffset = f2;
        return f2;
    }

    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LrcView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLrcEntryList = new ArrayList();
        this.mLrcPaint = new TextPaint();
        this.mTimePaint = new TextPaint();
        this.mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.6
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (LrcView.this.hasLrc() && LrcView.this.mOnPlayClickListener != null) {
                    LrcView.this.mScroller.forceFinished(true);
                    LrcView lrcView = LrcView.this;
                    lrcView.removeCallbacks(lrcView.hideTimelineRunnable);
                    LrcView.this.isTouching = true;
                    LrcView.this.isShowTimeline = true;
                    LrcView.this.invalidate();
                    return true;
                }
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (LrcView.this.hasLrc()) {
                    LrcView.access$1316(LrcView.this, -f2);
                    LrcView lrcView = LrcView.this;
                    lrcView.mOffset = Math.min(lrcView.mOffset, LrcView.this.getOffset(0));
                    LrcView lrcView2 = LrcView.this;
                    float f3 = lrcView2.mOffset;
                    LrcView lrcView3 = LrcView.this;
                    lrcView2.mOffset = Math.max(f3, lrcView3.getOffset(lrcView3.mLrcEntryList.size() - 1));
                    LrcView.this.invalidate();
                    return true;
                }
                return super.onScroll(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (LrcView.this.hasLrc()) {
                    LrcView lrcView = LrcView.this;
                    LrcView.this.mScroller.fling(0, (int) LrcView.this.mOffset, 0, (int) f2, 0, 0, (int) lrcView.getOffset(lrcView.mLrcEntryList.size() - 1), (int) LrcView.this.getOffset(0));
                    LrcView.this.isFling = true;
                    return true;
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (LrcView.this.hasLrc() && LrcView.this.isShowTimeline && LrcView.this.mPlayDrawable.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    int centerLine = LrcView.this.getCenterLine();
                    long time = ((LrcEntry) LrcView.this.mLrcEntryList.get(centerLine)).getTime();
                    if (LrcView.this.mOnPlayClickListener != null && LrcView.this.mOnPlayClickListener.onPlayClick(time)) {
                        LrcView.this.isShowTimeline = false;
                        LrcView lrcView = LrcView.this;
                        lrcView.removeCallbacks(lrcView.hideTimelineRunnable);
                        LrcView.this.mCurrentLine = centerLine;
                        LrcView.this.invalidate();
                        return true;
                    }
                }
                return super.onSingleTapConfirmed(motionEvent);
            }
        };
        this.hideTimelineRunnable = new Runnable() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.7
            @Override // java.lang.Runnable
            public void run() {
                if (LrcView.this.hasLrc() && LrcView.this.isShowTimeline) {
                    LrcView.this.isShowTimeline = false;
                    LrcView lrcView = LrcView.this;
                    lrcView.scrollTo(lrcView.mCurrentLine);
                }
            }
        };
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LrcView);
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTextSize, getResources().getDimension(R.dimen.lrc_text_size));
        this.mDividerHeight = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcDividerHeight, getResources().getDimension(R.dimen.lrc_divider_height));
        int integer = getResources().getInteger(R.integer.lrc_animation_duration);
        long j = typedArrayObtainStyledAttributes.getInt(R.styleable.LrcView_lrcAnimationDuration, integer);
        this.mAnimationDuration = j;
        if (j < 0) {
            j = integer;
        }
        this.mAnimationDuration = j;
        this.mNormalTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcNormalTextColor, getResources().getColor(R.color.lrc_normal_text_color));
        this.mCurrentTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcCurrentTextColor, getResources().getColor(R.color.lrc_current_text_color));
        this.mTimelineTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimelineTextColor, getResources().getColor(R.color.lrc_timeline_text_color));
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.LrcView_lrcLabel);
        this.mDefaultLabel = string;
        this.mDefaultLabel = TextUtils.isEmpty(string) ? getContext().getString(R.string.lrc_label) : this.mDefaultLabel;
        this.mLrcPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcPadding, 0.0f);
        this.mTimelineColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimelineColor, getResources().getColor(R.color.lrc_timeline_color));
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTimelineHeight, getResources().getDimension(R.dimen.lrc_timeline_height));
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.LrcView_lrcPlayDrawable);
        this.mPlayDrawable = drawable;
        if (drawable == null) {
            drawable = getResources().getDrawable(R.drawable.sel_btn_play);
        }
        this.mPlayDrawable = drawable;
        this.mTimeTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimeTextColor, getResources().getColor(R.color.lrc_time_text_color));
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTimeTextSize, getResources().getDimension(R.dimen.lrc_time_text_size));
        this.mTextGravity = typedArrayObtainStyledAttributes.getInteger(R.styleable.LrcView_lrcTextGravity, 2);
        typedArrayObtainStyledAttributes.recycle();
        this.mDrawableWidth = (int) getResources().getDimension(R.dimen.lrc_drawable_width);
        this.mTimeTextWidth = (int) getResources().getDimension(R.dimen.lrc_time_width);
        this.mLrcPaint.setAntiAlias(true);
        this.mLrcPaint.setTextSize(dimension);
        this.mLrcPaint.setTextAlign(Paint.Align.LEFT);
        this.mTimePaint.setAntiAlias(true);
        this.mTimePaint.setTextSize(dimension3);
        this.mTimePaint.setTextAlign(Paint.Align.CENTER);
        this.mTimePaint.setStrokeWidth(dimension2);
        this.mTimePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mTimeFontMetrics = this.mTimePaint.getFontMetrics();
        GestureDetector gestureDetector = new GestureDetector(getContext(), this.mSimpleOnGestureListener);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.mScroller = new Scroller(getContext());
    }

    public void setNormalColor(int i) {
        this.mNormalTextColor = i;
        postInvalidate();
    }

    public void setCurrentColor(int i) {
        this.mCurrentTextColor = i;
        postInvalidate();
    }

    public void setTimelineTextColor(int i) {
        this.mTimelineTextColor = i;
        postInvalidate();
    }

    public void setTimelineColor(int i) {
        this.mTimelineColor = i;
        postInvalidate();
    }

    public void setTimeTextColor(int i) {
        this.mTimeTextColor = i;
        postInvalidate();
    }

    public void setOnPlayClickListener(OnPlayClickListener onPlayClickListener) {
        this.mOnPlayClickListener = onPlayClickListener;
    }

    public void setLabel(final String str) {
        runOnUi(new Runnable() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.1
            @Override // java.lang.Runnable
            public void run() {
                LrcView.this.mDefaultLabel = str;
                LrcView.this.invalidate();
            }
        });
    }

    public void loadLrc(final File file) {
        runOnUi(new Runnable() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.2
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dueeeke.videoplayer.widget.SrtView.LrcView$2$1] */
            @Override // java.lang.Runnable
            public void run() {
                LrcView.this.reset();
                LrcView.this.setFlag(file);
                new AsyncTask<File, Integer, List<LrcEntry>>() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.2.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public List<LrcEntry> doInBackground(File... fileArr) {
                        return LrcEntry.parseLrc(fileArr[0]);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(List<LrcEntry> list) {
                        if (LrcView.this.getFlag() == file) {
                            LrcView.this.onLrcLoaded(list);
                            LrcView.this.setFlag(null);
                        }
                    }
                }.execute(file);
            }
        });
    }

    public void loadLrc(final String str) {
        runOnUi(new Runnable() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.3
            /* JADX WARN: Type inference failed for: r0v2, types: [com.dueeeke.videoplayer.widget.SrtView.LrcView$3$1] */
            @Override // java.lang.Runnable
            public void run() {
                LrcView.this.reset();
                LrcView.this.setFlag(str);
                new AsyncTask<String, Integer, List<LrcEntry>>() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.3.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public List<LrcEntry> doInBackground(String... strArr) {
                        return LrcEntry.parseLrc(strArr[0]);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(List<LrcEntry> list) {
                        if (LrcView.this.getFlag() == str) {
                            LrcView.this.onLrcLoaded(list);
                            LrcView.this.setFlag(null);
                        }
                    }
                }.execute(str);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.dueeeke.videoplayer.widget.SrtView.LrcView$4] */
    public void loadLrcByUrl(String str) {
        new AsyncTask<String, Integer, String>() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(String... strArr) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(10000);
                    if (httpURLConnection.getResponseCode() != 200) {
                        return null;
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    byte[] bArr = new byte[httpURLConnection.getContentLength()];
                    inputStream.read(bArr);
                    inputStream.close();
                    return new String(bArr);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str2) {
                LrcView.this.loadLrc(str2);
            }
        }.execute(str);
    }

    public boolean hasLrc() {
        return !this.mLrcEntryList.isEmpty();
    }

    public void updateTime(final long j) {
        runOnUi(new Runnable() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.5
            @Override // java.lang.Runnable
            public void run() {
                int iFindShowLine;
                if (LrcView.this.hasLrc() && (iFindShowLine = LrcView.this.findShowLine(j)) != LrcView.this.mCurrentLine) {
                    LrcView.this.mCurrentLine = iFindShowLine;
                    if (!LrcView.this.isShowTimeline) {
                        LrcView.this.scrollTo(iFindShowLine);
                    } else {
                        LrcView.this.invalidate();
                    }
                }
            }
        });
    }

    @Deprecated
    public void onDrag(long j) {
        updateTime(j);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            initEntryList();
            int i5 = (this.mTimeTextWidth - this.mDrawableWidth) / 2;
            int height = getHeight() / 2;
            int i6 = this.mDrawableWidth;
            int i7 = height - (i6 / 2);
            this.mPlayDrawable.setBounds(i5, i7, i5 + i6, i6 + i7);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        if (!hasLrc()) {
            this.mLrcPaint.setColor(this.mCurrentTextColor);
            drawText(canvas, new StaticLayout(this.mDefaultLabel, this.mLrcPaint, (int) getLrcWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false), height);
            return;
        }
        int centerLine = getCenterLine();
        if (this.isShowTimeline) {
            this.mPlayDrawable.draw(canvas);
            this.mTimePaint.setColor(this.mTimelineColor);
            float f = height;
            canvas.drawLine(this.mTimeTextWidth, f, getWidth() - this.mTimeTextWidth, f, this.mTimePaint);
            this.mTimePaint.setColor(this.mTimeTextColor);
            canvas.drawText(LrcUtils.formatTime(this.mLrcEntryList.get(centerLine).getTime()), getWidth() - (this.mTimeTextWidth / 2), f - ((this.mTimeFontMetrics.descent + this.mTimeFontMetrics.ascent) / 2.0f), this.mTimePaint);
        }
        float height2 = 0.0f;
        canvas.translate(0.0f, this.mOffset);
        for (int i = 0; i < this.mLrcEntryList.size(); i++) {
            if (i > 0) {
                height2 += ((this.mLrcEntryList.get(i - 1).getHeight() + this.mLrcEntryList.get(i).getHeight()) / 2) + this.mDividerHeight;
            }
            if (i == this.mCurrentLine) {
                this.mLrcPaint.setColor(this.mCurrentTextColor);
            } else if (this.isShowTimeline && i == centerLine) {
                this.mLrcPaint.setColor(this.mTimelineTextColor);
            } else {
                this.mLrcPaint.setColor(this.mNormalTextColor);
            }
            drawText(canvas, this.mLrcEntryList.get(i).getStaticLayout(), height2);
        }
    }

    private void drawText(Canvas canvas, StaticLayout staticLayout, float f) {
        canvas.save();
        canvas.translate(this.mLrcPadding, f - (staticLayout.getHeight() / 2));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.isTouching = false;
            if (hasLrc() && !this.isFling) {
                adjustCenter();
                postDelayed(this.hideTimelineRunnable, 4000L);
            }
        }
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            this.mOffset = this.mScroller.getCurrY();
            invalidate();
        }
        if (this.isFling && this.mScroller.isFinished()) {
            this.isFling = false;
            if (!hasLrc() || this.isTouching) {
                return;
            }
            adjustCenter();
            postDelayed(this.hideTimelineRunnable, 4000L);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.hideTimelineRunnable);
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLrcLoaded(List<LrcEntry> list) {
        if (list != null && !list.isEmpty()) {
            this.mLrcEntryList.addAll(list);
        }
        initEntryList();
        invalidate();
    }

    private void initEntryList() {
        if (!hasLrc() || getWidth() == 0) {
            return;
        }
        Collections.sort(this.mLrcEntryList);
        Iterator<LrcEntry> it = this.mLrcEntryList.iterator();
        while (it.hasNext()) {
            it.next().init(this.mLrcPaint, (int) getLrcWidth(), this.mTextGravity);
        }
        this.mOffset = getHeight() / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        endAnimation();
        this.mScroller.forceFinished(true);
        this.isShowTimeline = false;
        this.isTouching = false;
        this.isFling = false;
        removeCallbacks(this.hideTimelineRunnable);
        this.mLrcEntryList.clear();
        this.mOffset = 0.0f;
        this.mCurrentLine = 0;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollTo(int i) {
        scrollTo(i, this.mAnimationDuration);
    }

    private void adjustCenter() {
        scrollTo(getCenterLine(), ADJUST_DURATION);
    }

    private void scrollTo(int i, long j) {
        float offset = getOffset(i);
        endAnimation();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mOffset, offset);
        this.mAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(j);
        this.mAnimator.setInterpolator(new LinearInterpolator());
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dueeeke.videoplayer.widget.SrtView.LrcView.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LrcView.this.mOffset = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LrcView.this.invalidate();
            }
        });
        resetDurationScale();
        this.mAnimator.start();
    }

    private void resetDurationScale() {
        try {
            Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
            declaredField.setAccessible(true);
            declaredField.setFloat(null, 1.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void endAnimation() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mAnimator.end();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findShowLine(long j) {
        int size = this.mLrcEntryList.size();
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            if (j < this.mLrcEntryList.get(i2).getTime()) {
                size = i2 - 1;
            } else {
                i = i2 + 1;
                if (i >= this.mLrcEntryList.size() || j < this.mLrcEntryList.get(i).getTime()) {
                    return i2;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCenterLine() {
        int i = 0;
        float fAbs = Float.MAX_VALUE;
        for (int i2 = 0; i2 < this.mLrcEntryList.size(); i2++) {
            if (Math.abs(this.mOffset - getOffset(i2)) < fAbs) {
                fAbs = Math.abs(this.mOffset - getOffset(i2));
                i = i2;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getOffset(int i) {
        if (this.mLrcEntryList.get(i).getOffset() == Float.MIN_VALUE) {
            float height = getHeight() / 2;
            for (int i2 = 1; i2 <= i; i2++) {
                height -= ((this.mLrcEntryList.get(i2 - 1).getHeight() + this.mLrcEntryList.get(i2).getHeight()) / 2) + this.mDividerHeight;
            }
            this.mLrcEntryList.get(i).setOffset(height);
        }
        return this.mLrcEntryList.get(i).getOffset();
    }

    private float getLrcWidth() {
        return getWidth() - (this.mLrcPadding * 2.0f);
    }

    private void runOnUi(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object getFlag() {
        return this.mFlag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFlag(Object obj) {
        this.mFlag = obj;
    }
}
