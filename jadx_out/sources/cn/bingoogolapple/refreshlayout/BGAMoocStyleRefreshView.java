package cn.bingoogolapple.refreshlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class BGAMoocStyleRefreshView extends View {
    private float mBezierControlOriginalY;
    private float mBezierControlX;
    private float mBezierControlY;
    private Path mBezierPath;
    private Canvas mCanvas;
    private boolean mIsBezierControlXIncrease;
    private boolean mIsRefreshing;
    private Bitmap mOriginalBitmap;
    private int mOriginalBitmapHeight;
    private int mOriginalBitmapWidth;
    private Paint mPaint;
    private Bitmap mUltimateBitmap;
    private float mWaveOriginalY;
    private float mWaveY;
    private PorterDuffXfermode mXfermode;

    public BGAMoocStyleRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsRefreshing = false;
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    private void initCanvas() {
        this.mOriginalBitmapWidth = this.mOriginalBitmap.getWidth();
        int height = this.mOriginalBitmap.getHeight();
        this.mOriginalBitmapHeight = height;
        float f = height;
        this.mWaveOriginalY = f;
        this.mWaveY = 1.2f * f;
        float f2 = f * 1.25f;
        this.mBezierControlOriginalY = f2;
        this.mBezierControlY = f2;
        this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.mBezierPath = new Path();
        this.mCanvas = new Canvas();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.mOriginalBitmapWidth, this.mOriginalBitmapHeight, Bitmap.Config.ARGB_8888);
        this.mUltimateBitmap = bitmapCreateBitmap;
        this.mCanvas.setBitmap(bitmapCreateBitmap);
    }

    public void setUltimateColor(int i) {
        this.mPaint.setColor(getResources().getColor(i));
    }

    public void setOriginalImage(int i) {
        this.mOriginalBitmap = BitmapFactory.decodeResource(getResources(), i);
        initCanvas();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mUltimateBitmap == null) {
            return;
        }
        drawUltimateBitmap();
        canvas.drawBitmap(this.mUltimateBitmap, getPaddingLeft(), getPaddingTop(), (Paint) null);
        if (this.mIsRefreshing) {
            invalidate();
        }
    }

    private void drawUltimateBitmap() {
        this.mBezierPath.reset();
        this.mUltimateBitmap.eraseColor(Color.parseColor("#00ffffff"));
        float f = this.mBezierControlX;
        if (f >= this.mOriginalBitmapWidth) {
            this.mIsBezierControlXIncrease = false;
        } else if (f <= 0) {
            this.mIsBezierControlXIncrease = true;
        }
        this.mBezierControlX = this.mIsBezierControlXIncrease ? f + 10.0f : f - 10.0f;
        float f2 = this.mBezierControlY;
        if (f2 >= 0.0f) {
            this.mBezierControlY = f2 - 2.0f;
            this.mWaveY -= 2.0f;
        } else {
            this.mWaveY = this.mWaveOriginalY;
            this.mBezierControlY = this.mBezierControlOriginalY;
        }
        this.mBezierPath.moveTo(0.0f, this.mWaveY);
        Path path = this.mBezierPath;
        float f3 = this.mBezierControlX;
        float f4 = this.mWaveY;
        float f5 = this.mBezierControlY;
        int i = this.mOriginalBitmapWidth;
        path.cubicTo(f3 / 2.0f, f4 - (f5 - f4), (f3 + i) / 2.0f, f5, i, f4);
        this.mBezierPath.lineTo(this.mOriginalBitmapWidth, this.mOriginalBitmapHeight);
        this.mBezierPath.lineTo(0.0f, this.mOriginalBitmapHeight);
        this.mBezierPath.close();
        this.mCanvas.drawBitmap(this.mOriginalBitmap, 0.0f, 0.0f, this.mPaint);
        this.mPaint.setXfermode(this.mXfermode);
        this.mCanvas.drawPath(this.mBezierPath, this.mPaint);
        this.mPaint.setXfermode(null);
    }

    public void startRefreshing() {
        this.mIsRefreshing = true;
        reset();
    }

    public void stopRefreshing() {
        this.mIsRefreshing = false;
        reset();
    }

    private void reset() {
        this.mWaveY = this.mWaveOriginalY;
        this.mBezierControlY = this.mBezierControlOriginalY;
        this.mBezierControlX = 0.0f;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMin;
        int iMin2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            iMin = size + getPaddingLeft() + getPaddingRight();
        } else {
            int paddingLeft = this.mOriginalBitmapWidth + getPaddingLeft() + getPaddingRight();
            iMin = mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
        }
        if (mode2 == 1073741824) {
            iMin2 = size2 + getPaddingTop() + getPaddingBottom();
        } else {
            int paddingTop = this.mOriginalBitmapHeight + getPaddingTop() + getPaddingBottom();
            iMin2 = mode2 == Integer.MIN_VALUE ? Math.min(paddingTop, size2) : paddingTop;
        }
        setMeasuredDimension(iMin, iMin2);
    }
}
